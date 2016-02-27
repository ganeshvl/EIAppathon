
package com.vl.eiappathon;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.filter.Approximator;
import com.github.mikephil.charting.data.filter.Approximator.ApproximatorType;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.vl.eiappathon.notimportant.DemoBase;
import com.vl.eiappathon.utils.CurrentAccount;
import com.vl.eiappathon.utils.FutureAccount;
import com.vl.eiappathon.utils.PastAccount;

public class AnotherBarActivity extends DemoBase implements OnSeekBarChangeListener, OnChartValueSelectedListener {

	private BarChart mChart;
	private SeekBar mSeekBarX, mSeekBarY;
	private TextView tvX, tvY;
	private String TAG = AnotherBarActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_barchart);

		tvX = (TextView)findViewById(R.id.tvXMax);
		tvY = (TextView)findViewById(R.id.tvYMax);

		mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBarX.setOnSeekBarChangeListener(this);

		mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
		mSeekBarY.setOnSeekBarChangeListener(this);

		mSeekBarX.setVisibility(View.GONE);
		mSeekBarY.setVisibility(View.GONE);
		tvX.setVisibility(View.GONE);
		tvY.setVisibility(View.GONE);

		mChart = (BarChart) findViewById(R.id.chart1);

		mChart.setDescription("");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60);

		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);

		mChart.setDrawBarShadow(false);
		mChart.setDrawGridBackground(false);

		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setSpaceBetweenLabels(0);
		xAxis.setDrawGridLines(false);

		mChart.getAxisLeft().setDrawGridLines(false);

		// setting data
		mSeekBarX.setProgress(10);
		mSeekBarY.setProgress(100);

		// add a nice and smooth animation
		mChart.animateY(2500);

		mChart.getLegend().setEnabled(false);

		// Legend l = mChart.getLegend();
		// l.setPosition(LegendPosition.BELOW_CHART_CENTER);
		// l.setFormSize(8f);
		// l.setFormToTextSpace(4f);
		// l.setXEntrySpace(6f);

		// mChart.setDrawLegend(false);
		ArrayList<String> xVals = new ArrayList<String>();
		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		int totalSize =5;
		try
		{

			DatabaseDetailsDataSource datasource = new DatabaseDetailsDataSource(this);
			datasource.open();	

			ArrayList<PastAccount> pAList = datasource.getPastAccountDetails();
			if(pAList == null)
			{
				CSVReader.readCsvFile("/sdcard/tmp/PastAccount.csv", 0, this);
				CSVReader.readCsvFile("/sdcard/tmp/CurrentAccount.csv", 1, this);
				CSVReader.readCsvFile("/sdcard/tmp/FutureAccount.csv", 2, this);
			}
			if(pAList != null)
			{
				for(int i=0;i< pAList.size();i++)
				{
					Log.d(TAG , "Indexing through pAList "+pAList.get(i).getP_Result_Currency());
//					if(pAList.get(i).getP_Result_Date().equalsIgnoreCase("07-01-2016"))
//					{
//						xVals.add("27-02-2016");	
//					}else
						xVals.add(pAList.get(i).getP_Result_Date());
					try
					{
						yVals1.add(new BarEntry(Float.parseFloat(pAList.get(i).getP_Result_Balance()), i));
					}
					catch(Exception e)
					{
						yVals1.add(new BarEntry((float)-1, i));
					}
				}
			}

			ArrayList<CurrentAccount> cAList = datasource.getCurrentAccountDetails();
			if(cAList != null)
			{
				for(int i=0;i< cAList.size();i++)
				{
					Log.d(TAG  , "Indexing through cAList "+cAList.get(i).getC_Result_AccountNo());
					xVals.add(cAList.get(i).getC_Result_CurrentDate());
					yVals1.add(new BarEntry((float)cAList.get(i).getC_Result_CurrentBalance(), i));
				}
			}

			ArrayList<FutureAccount> fAList = datasource.getFutureAccountDetails();
			if(fAList != null)
			{
				for(int i=0;i< fAList.size();i++)
				{
					Log.d(TAG , "Indexing through fAList "+fAList.get(i).getF_Result_InstType());
					xVals.add(fAList.get(i).getF_Result_Date());

					try
					{
						yVals1.add(new BarEntry(Float.parseFloat(fAList.get(i).getF_Result_ClosingBalance()), i));
					}
					catch(Exception e)
					{
						yVals1.add(new BarEntry((float)-1, i));
					}
				}
			}
			if(pAList != null && fAList != null)
				totalSize = pAList.size() + fAList.size();

			datasource.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		//		for (int i = 0; i < totalSize - 13; i++) {
		//			float mult = (500 + 1);
		//			float val1 = (float) (Math.random() * mult) + mult / 3;
		//			val1 = val1 - 325;
		//			yVals1.add(new BarEntry((int) val1, i));
		//		}


		//      
		//      for (int i = 0; i < 12; i++) {
		//          xVals.add("28/02/2016");
		//      }

		BarDataSet set1 = new BarDataSet(yVals1, "Data Set");
		set1.setColors(ColorTemplate.LIBERTY_COLORS);
		set1.setDrawValues(true);
		set1.setHighlightEnabled(true);

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);
		mChart.setExtraTopOffset(-30f);
		//        mChart.setExtraBottomOffset(10f);

		mChart.setDrawBarShadow(false);

		mChart.setDescription("hello");

		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(true);
		//        mChart.setScrollX(250);
		mChart.setDrawGridBackground(false);
		mChart.setData(data);


		mChart.invalidate();

		data.setHighlightEnabled(true);
		mChart.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP)
				{
					startActivity(new Intent(AnotherBarActivity.this, PopupActivity.class));
				}
				return false;
			}
		});

		//mChart.setOnChartValueSelectedListener(this);



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bar, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.actionToggleValues: {

			for (IDataSet set : mChart.getData().getDataSets())
				set.setDrawValues(!set.isDrawValuesEnabled());

			mChart.invalidate();
			break;
		}
		case R.id.actionToggleHighlight: {

			if(mChart.getData() != null) {
				mChart.getData().setHighlightEnabled(!mChart.getData().isHighlightEnabled());
				mChart.invalidate();
			}
			break;
		}
		case R.id.actionTogglePinch: {
			if (mChart.isPinchZoomEnabled())
				mChart.setPinchZoom(false);
			else
				mChart.setPinchZoom(true);

			mChart.invalidate();
			break;
		}
		case R.id.actionToggleAutoScaleMinMax: {
			mChart.setAutoScaleMinMaxEnabled(!mChart.isAutoScaleMinMaxEnabled());
			mChart.notifyDataSetChanged();
			break;
		}
		case R.id.actionToggleHighlightArrow: {
			if (mChart.isDrawHighlightArrowEnabled())
				mChart.setDrawHighlightArrow(false);
			else
				mChart.setDrawHighlightArrow(true);
			mChart.invalidate();
			break;
		}
		case R.id.actionToggleStartzero: {

			mChart.getAxisLeft().setStartAtZero(!mChart.getAxisLeft().isStartAtZeroEnabled());
			mChart.getAxisRight().setStartAtZero(!mChart.getAxisRight().isStartAtZeroEnabled());
			mChart.invalidate();
			break;
		}
		case R.id.animateX: {
			mChart.animateX(3000);
			break;
		}
		case R.id.animateY: {
			mChart.animateY(3000);
			break;
		}
		case R.id.animateXY: {

			mChart.animateXY(3000, 3000);
			break;
		}
		case R.id.actionToggleFilter: {

			Approximator a = new Approximator(ApproximatorType.DOUGLAS_PEUCKER, 25);

			if (!mChart.isFilteringEnabled()) {
				mChart.enableFiltering(a);
			} else {
				mChart.disableFiltering();
			}
			mChart.invalidate();
			break;
		}
		case R.id.actionSave: {
			if (mChart.saveToGallery("title" + System.currentTimeMillis(), 50)) {
				Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
						Toast.LENGTH_SHORT).show();
			} else
				Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
				.show();
			break;
		}
		}
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		tvX.setText("" + (mSeekBarX.getProgress() + 1));
		tvY.setText("" + (mSeekBarY.getProgress()));

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (int i = 0; i < mSeekBarX.getProgress() + 1; i++) {
			float mult = (mSeekBarY.getProgress() + 1);
			float val1 = (float) (Math.random() * mult) + mult / 3;
			yVals1.add(new BarEntry((int) val1, i));
		}

		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < mSeekBarX.getProgress() + 1; i++) {
			xVals.add((int) yVals1.get(i).getVal() + "");
		}

		BarDataSet set1 = new BarDataSet(yVals1, "Data Set");
		set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
		set1.setDrawValues(false);

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);

		mChart.setData(data);
		mChart.invalidate();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
		Log.d(TAG, "index is "+dataSetIndex);
		Log.d(TAG, "Highlight is "+h.getStackIndex());

	}

	@Override
	public void onNothingSelected() {
		// TODO Auto-generated method stub

	}
}
