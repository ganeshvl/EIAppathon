package com.vl.eiappathon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private String TAG = "MySQLiteHelper";

	// SQLite static strings
	public static final String CASH_PAYMENT = "cashPayment.db";
	private static final int DATABASE_VERSION = 1;

	public static final String ROW_ID = "_id";

	public static final String PAST_ACCOUNT = "PastAccount";
	public static final String PAST_Result_SNO = "SNo";
	public static final String PAST_Result_AccountNo = "AccountNo";
	public static final String PAST_Result_Date = "Date";
	public static final String PAST_Result_TXNRefNo = "TXNREFNo";
	public static final String PAST_Result_TXNDESC = "TEXDESCRIPTION";
	public static final String PAST_Result_Currency = "Currency";
	public static final String PAST_Result_Amount = "Amount";
	public static final String PAST_Result_Balance = "Balance";

	public static final String CURRENT_ACCOUNT = "CurrentAccount";
	public static final String CURRENT_Result_AccountNo = "AccountNo";
	public static final String CURRENT_Result_AccountName = "AccountName";
	public static final String CURRENT_Result_Currency = "Currency";
	public static final String CURRENT_Result_CurrentBalance = "Balance";
	public static final String CURRENT_Result_ThresholdBalance = "ThresholdBalance";
	public static final String CURRENT_Result_CurrentDate = "Date";

	public static final String FUTURE_ACCOUNT = "FutureAccount";
	public static final String Future_Result_InstType = "InstType";
	public static final String Future_Result_Date = "Date";
	public static final String Future_Result_TXNDesc = "TXNDesc";
	public static final String Future_Result_VendorCustomer = "VendorCustomer";
	public static final String Future_Result_Indicator = "Indicator";
	public static final String Future_Result_Original_Currency = "OriginalCurrency";
	public static final String Future_Result_Amount = "OriginalAMount";
	public static final String Future_Result_LocalCurrency = "LocalCurrency";
	public static final String Future_Result_LocalAmount = "LocalAmount";


	
	public static final String USER = "User";

	// Creating Past Account Details table
	private String createPastAccountTableCmd = "CREATE TABLE IF NOT EXISTS "
			+ PAST_ACCOUNT
			+ "("
			+ ROW_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ PAST_Result_SNO
			+ " VARCHAR2(5),"
			+ PAST_Result_AccountNo
			+ " VARCHAR2(10),"
			+ PAST_Result_Date
			+ " VARCHAR2(30),"
			+ PAST_Result_TXNRefNo
			+ " VARCHAR2(10),"
			+ PAST_Result_TXNDESC
			+ " VARCHAR2(20),"
			+ PAST_Result_Currency
			+ " VARCHAR2(3),"
			+ PAST_Result_Amount
			+ " VARCHAR2(16),"
			+ PAST_Result_Balance
			+ " VARCHAR2(16));";

	// Creating Current Account Details table
	private String createCurrentAccountTableCmd = "CREATE TABLE IF NOT EXISTS "
			+ CURRENT_ACCOUNT + "(" + ROW_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ CURRENT_Result_AccountNo
			+ " VARCHAR2(10),"
			+ CURRENT_Result_AccountName
			+ " VARCHAR2(20),"
			+ CURRENT_Result_Currency
			+ " VARCHAR2(3),"
			+ CURRENT_Result_CurrentBalance
			+ " VARCHAR2(16),"
			+ CURRENT_Result_ThresholdBalance
			+ " VARCHAR2(16),"
			+ CURRENT_Result_CurrentDate
			+ " VARCHAR2(16));";

	// Creating Future Account Details table
	private String createFutureAccountTableCmd = "CREATE TABLE IF NOT EXISTS "
			+ FUTURE_ACCOUNT
			+ "("
			+ ROW_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ Future_Result_InstType
			+ " VARCHAR2(2),"
			+ Future_Result_Date
			+ " VARCHAR2(30),"
			+ Future_Result_TXNDesc
			+ " VARCHAR2(20),"
			+ Future_Result_VendorCustomer
			+ " VARCHAR2(20),"
			+ Future_Result_Indicator
			+ " VARCHAR2(1),"
			+ Future_Result_Original_Currency
			+ " VARCHAR2(3),"
			+ Future_Result_Amount
			+ " VARCHAR2(16),"
			+ Future_Result_LocalCurrency
			+ " VARCHAR2(3),"
			+ Future_Result_LocalAmount
			+ " VARCHAR2(16));";

	

	/**Constructs the sqlitehelper class
	 * @param context
	 */
	public MySQLiteHelper(Context context) {
		super(context, CASH_PAYMENT, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}// constructor

	@Override
	public void onCreate(SQLiteDatabase database) {

		database.execSQL(createPastAccountTableCmd);
		database.execSQL(createCurrentAccountTableCmd);
		database.execSQL(createFutureAccountTableCmd);
		

	}// onCreate()

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
		}

	}// onOpen()

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
			Log.d(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS" + createPastAccountTableCmd);
		db.execSQL("DROP TABLE IF EXISTS" + createCurrentAccountTableCmd);
		db.execSQL("DROP TABLE IF EXISTS" + createFutureAccountTableCmd);
		

		onCreate(db);
	}// onUpgrade()

}// MySQLiteHelper-class
