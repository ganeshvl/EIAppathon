package com.vl.eiappathon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.vl.eiappathon.utils.CurrentAccount;
import com.vl.eiappathon.utils.FutureAccount;
import com.vl.eiappathon.utils.PastAccount;



public class CSVReader {

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";

	// PastAccount
	private static final int P_Result_SNO = 2;
	private static final int P_Result_AccountNo = 0;
	private static final int P_Result_Date = 1;
	private static final int P_Result_TXNRefNo = 3;
	private static final int P_Result_TXNDESC = 4;
	private static final int P_Result_Currency = 5;
	private static final int P_Result_Amount = 6;
	private static final int P_Result_Balance = 7;

	//CurrentAccount

	private static final int C_Result_AccountNo = 0;
	private static final int C_Result_AccountName = 1;
	private static final int C_Result_Currency = 2;
	private static final int C_Result_CurrentBalance = 3;
	private static final int C_Result_ThresholdBalance = 4;
	private static final int C_Result_CurrentDate = 5;


	//FutureAccount
	private static final int F_Result_InstType = 0;
	private static final int F_Result_Date = 1;
	private static final int F_Result_TXNDesc = 2;
	private static final int F_Result_VendorCustomer = 3;
	private static final int F_Result_Indicator = 4;
	private static final int F_Result_Original_Amount = 6;
	private static final int F_Result_Original_Currency = 5;
	private static final int F_Result_LocalCurrency = 7;
	private static final int F_Result_LocalAmount = 8;

	private static String TAG = CSVReader.class.getSimpleName();
	private static List<Result> Results;

	/** 
	 * clear file data and closes it
	 * @param fileName
	 * @throws Exception
	 */
	public static void clearCsv(String fileName) throws Exception {
		FileWriter fw = new FileWriter(fileName, false); 
		PrintWriter pw = new PrintWriter(fw, false);
		pw.flush();
		pw.close();
		fw.close();
	}

	public static void open(String fileName)
	{
		BufferedReader fileReader = null;

		try
		{
			fileReader = new BufferedReader(new FileReader(fileName));
		}
		catch(Exception e)
		{
			Log.e(TAG, "Exception:", e);
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				// Logger.getInstance().d("Error while closing fileReader !!!");
				Log.e(TAG, "Exception:", e);
			}
		}

	}

	/**
	 * 
	 * @param fileName
	 */
	public static void readCsvFile(String fileName, int accountType, Activity activity) {

		BufferedReader fileReader = null;
		DatabaseDetailsDataSource datasource = new DatabaseDetailsDataSource(activity);
		datasource.open();

		try {
			

			// Create a new list of Result to be filled by CSV file data
			Results = new ArrayList<Result>();

			String line = "";

			File f = new File(fileName);
			if(f.exists() && !f.isDirectory()) { 

				// Create the file reader
				fileReader = new BufferedReader(new FileReader(fileName));

				// Read the CSV file header to skip it
				fileReader.readLine();
				
				

				// Read the file line by line starting from the second line
				while ((line = fileReader.readLine()) != null) {
					// Get all tokens available in line
					String[] tokens = line.split(COMMA_DELIMITER);
					if (tokens.length > 0) {
						// Create a new Result object and fill his data
						if(accountType == 0)
						{
							//PastAccount
							
							PastAccount sBean = new PastAccount();
							sBean.setP_Result_SNO(Integer.parseInt(tokens[P_Result_SNO]));
							sBean.setP_Result_AccountNo(Integer.parseInt(tokens[P_Result_AccountNo]));
							sBean.setP_Result_Date(tokens[P_Result_Date]);
							sBean.setP_Result_TXNRefNo(tokens[P_Result_TXNRefNo]);
							sBean.setP_Result_TXNDESC(tokens[P_Result_TXNDESC]);
							
							sBean.setP_Result_Amount(tokens[P_Result_Amount]);
							sBean.setP_Result_Balance(tokens[P_Result_Balance]);
							
							String currency = tokens[P_Result_Currency];
							
							
							 String password="asdf";
							    MessageDigest digest=null;
							    String hash = null;
							    try {
							        digest = MessageDigest.getInstance("SHA-256");
							        digest.update(password.getBytes());

							        hash = datasource.bytesToHexString(digest.digest());

							        Log.i("Eamorr", "result is " + hash);
							    } catch (NoSuchAlgorithmException e1) {
							        // TODO Auto-generated catch block
							        e1.printStackTrace();
							    }
							    sBean.setP_Result_Currency(hash);
							datasource.insertPastAccountDetails(sBean);

						} else if(accountType == 1)
						{
							//CurrentAccount
							CurrentAccount sBean = new CurrentAccount();
							sBean.setC_Result_AccountNo(Integer.parseInt(tokens[C_Result_AccountNo]));
							sBean.setC_Result_AccountName(tokens[C_Result_AccountName]);
							sBean.setC_Result_Currency(tokens[C_Result_Currency]);
							sBean.setC_Result_CurrentBalance(Double.parseDouble(tokens[C_Result_CurrentBalance]));
							sBean.setC_Result_ThresholdBalance(Double.parseDouble(tokens[C_Result_ThresholdBalance]));
							sBean.setC_Result_CurrentDate(tokens[C_Result_CurrentDate]);
							datasource.insertCurrentAccountDetails(sBean);
						}else if(accountType == 2)
						{
							//FutureAccount
							FutureAccount sBean = new FutureAccount();
							sBean.setF_Result_InstType(tokens[F_Result_InstType]);
							sBean.setF_Result_Date(tokens[F_Result_Date]);
							sBean.setF_Result_TXNDesc(tokens[F_Result_TXNDesc]);
							sBean.setF_Result_VendorCustomer(tokens[F_Result_VendorCustomer]);
							sBean.setF_Result_Indicator(tokens[F_Result_Indicator]);
							sBean.setF_Result_Original_Currency(tokens[F_Result_Original_Currency]);
							sBean.setF_Result_Amount(Double.parseDouble(tokens[F_Result_Original_Amount]));
							sBean.setF_Result_LocalCurrency(tokens[F_Result_LocalCurrency]);
							sBean.setF_Result_LocalAmount(Double.parseDouble(tokens[F_Result_LocalAmount]));
							datasource.insertFutureAccountDetails(sBean);
						}
					}
				}
				datasource.close();

				// Print the new Result list
				for (Result Result : Results) {
					Log.d(TAG, Result.toString());
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "Error in CsvFileReader !!!:", e);
		} finally {
			datasource.close();
			try {
				fileReader.close();
			} catch (IOException e) {
				Log.e(TAG, "Exception:", e);
			}
		}

	}


}
