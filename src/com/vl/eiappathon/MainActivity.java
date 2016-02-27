package com.vl.eiappathon;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.vl.eiappathon.utils.BlobGettingStartedTask;
import com.vl.eiappathon.utils.CurrentAccount;
import com.vl.eiappathon.utils.FutureAccount;
import com.vl.eiappathon.utils.PastAccount;

public class MainActivity extends ActionBarActivity {

	private String TAG = MainActivity.class.getSimpleName();
	
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
		setContentView(R.layout.activity_main1);
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

		Button submitButton = (Button) findViewById(R.id.submit_doc);
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				takePicture(v);
			}
		});
		

	}
	
	// Run an Intent to start up the Android camera
		static final int REQUEST_TAKE_PHOTO = 1;
		public Uri mPhotoFileUri = null;
		public File mPhotoFile = null;

		public void takePicture(View view) {
			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// Ensure that there's a camera activity to handle the intent
			if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
				// Create the File where the photo should go
				try {
					mPhotoFile = createImageFile();
				} catch (IOException ex) {
					// Error occurred while creating the File
					//
				}
				// Continue only if the File was successfully created
				if (mPhotoFile != null) {
					mPhotoFileUri = Uri.fromFile(mPhotoFile);
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileUri);
					startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);


				}
			}
		}

		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == REQUEST_TAKE_PHOTO
					&& resultCode == RESULT_OK) {   

				Log.d("test1",""+mPhotoFileUri);
				TextView textView = (TextView) findViewById(R.id.textView);
				new BlobGettingStartedTask(this, textView, mPhotoFileUri)
				.execute();
				            Intent shareIntent = new Intent(this, AnotherBarActivity.class);
				            shareIntent.putExtra("photo",""+mPhotoFileUri);
				            startActivity(shareIntent);
			}

		}
	

	// Create a File object for storing the photo
	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(
				imageFileName,  /* prefix */
				".jpg",         /* suffix */
				storageDir      /* directory */
				);
		return image;
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
