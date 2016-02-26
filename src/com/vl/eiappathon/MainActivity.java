package com.vl.eiappathon;

import java.util.ArrayList;

import com.vl.eiappathon.utils.CurrentAccount;
import com.vl.eiappathon.utils.FutureAccount;
import com.vl.eiappathon.utils.PastAccount;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	private String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try
		{
		CSVReader.readCsvFile("/sdcard/tmp/PastAccount.csv", 0, this);
		CSVReader.readCsvFile("/sdcard/tmp/CurrentAccount.csv", 1, this);
		CSVReader.readCsvFile("/sdcard/tmp/FutureAccount.csv", 2, this);
		
		DatabaseDetailsDataSource datasource = new DatabaseDetailsDataSource(this);
		datasource.open();
		ArrayList<PastAccount> pAList = datasource.getPastAccountDetails();
		if(pAList != null)
		{
		for(int i=0;i< pAList.size();i++)
		{
			Log.d(TAG , "Indexing through pAList "+pAList.get(i).getP_Result_Currency());
		}
		}
		
		ArrayList<CurrentAccount> cAList = datasource.getCurrentAccountDetails();
		if(cAList != null)
		{
		for(int i=0;i< cAList.size();i++)
		{
			Log.d(TAG , "Indexing through cAList "+cAList.get(i).getC_Result_AccountNo());
		}
		}
		
		ArrayList<FutureAccount> fAList = datasource.getFutureAccountDetails();
		if(fAList != null)
		{
		for(int i=0;i< fAList.size();i++)
		{
			Log.d(TAG , "Indexing through fAList "+fAList.get(i).getF_Result_InstType());
		}
		}
		
		datasource.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
