package com.vl.eiappathon;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PopupActivity extends Activity {

	private String TAG = PopupActivity.class.getSimpleName();
	
	/**
	 * MODIFY THIS!
	 * 
	 * Stores the storage connection string.
	 */
	public static final String storageConnectionString = "DefaultEndpointsProtocol=https;"
			+ "AccountName=opsworkbenchdoc;"
			+ "AccountKey=N6HLa4Sq4NJnTMTx0ejp8xT1zsluTABVscQ+eMM2Emgc5L4b7P1wvmbZHk++G3n6CzD0CWsaI9r5kTCgfEDJwQ==";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		try
		{
			Button button = (Button) findViewById(R.id.cross_mark_imageview);
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					finish();
					
				}
			});
			
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
		
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Prints the specified text value to the view and to LogCat.
	 * 
	 * @param view
	 *            The view to print to.
	 * @param value
	 *            The value to print.
	 */
	public void outputText(final TextView view, final String value) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				view.append(value + "\n");
				System.out.println(view);
			}
		});
	}

	/**
	 * Clears the text from the specified view.
	 * 
	 * @param view
	 *            The view to clear.
	 */
	public void clearText(final TextView view) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				view.setText("");
			}
		});
	}

	/**
	 * Prints out the exception information .
	 */
	public void printException(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		t.printStackTrace(printWriter);
		outputText(
				(TextView) findViewById(R.id.textView),
				String.format(
						"Got an exception from running samples. Exception details:\n%s\n",
						stringWriter.toString()));
	}

	/**
	 * Prints out the sample start information .
	 */
	public void printSampleStartInfo(String sampleName) {
		TextView view = (TextView) findViewById(R.id.textView);
		clearText(view);
		outputText(view, String.format(
				"The Azure storage client library sample %s is starting...",
				sampleName));
	}

	/**
	 * Prints out the sample complete information .
	 */
	public void printSampleCompleteInfo(String sampleName) {
		outputText((TextView) findViewById(R.id.textView), String.format(
				"The Azure storage client library sample %s completed.\n",
				sampleName));
	}
}
