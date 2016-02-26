package com.vl.eiappathon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.vl.eiappathon.utils.CurrentAccount;
import com.vl.eiappathon.utils.FutureAccount;
import com.vl.eiappathon.utils.PastAccount;

public class DatabaseDetailsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	private Activity _activity;
	private String currentUser = null;

	public DatabaseDetailsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	/**
	 * Open SQLite database
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/**
	 * Close SQLite database
	 */
	public void close() {
		dbHelper.close();
	}

	/**Insert PastAccount Details 
	 * @param bean PastAccount bean
	 * @return 1
	 */
	public long insertPastAccountDetails(PastAccount bean) {
		try {
			ContentValues contentValue = new ContentValues();
			contentValue.put(MySQLiteHelper.PAST_Result_SNO, bean.getP_Result_SNO());
			contentValue.put(MySQLiteHelper.PAST_Result_AccountNo, bean.getP_Result_AccountNo());
			contentValue.put(MySQLiteHelper.PAST_Result_Date,bean.getP_Result_Date());
			contentValue.put(MySQLiteHelper.PAST_Result_TXNRefNo, bean.getP_Result_TXNRefNo());
			contentValue.put(MySQLiteHelper.PAST_Result_TXNDESC, bean.getP_Result_TXNDESC());
			contentValue.put(MySQLiteHelper.PAST_Result_Currency,bean.getP_Result_Currency());
			contentValue.put(MySQLiteHelper.PAST_Result_Amount, bean.getP_Result_Amount());
			contentValue.put(MySQLiteHelper.PAST_Result_Balance, bean.getP_Result_Balance());
			return database.insert(MySQLiteHelper.PAST_ACCOUNT, null, contentValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}// insertPastAccountDetails()


	/**Get PastAccount from db
	 * @return PastAccount beanArraylist values
	 */
	public ArrayList<PastAccount> getPastAccountDetails() {
		ArrayList<PastAccount> cBeanAL = null;
		String[] columns = new String[] { MySQLiteHelper.PAST_Result_SNO,
				MySQLiteHelper.PAST_Result_AccountNo, MySQLiteHelper.PAST_Result_Date, MySQLiteHelper.PAST_Result_TXNRefNo,
				MySQLiteHelper.PAST_Result_TXNDESC, MySQLiteHelper.PAST_Result_Currency, MySQLiteHelper.PAST_Result_Amount,
				MySQLiteHelper.PAST_Result_Balance };

		String selection = /* MySQLiteHelper.CS_C1_CONDITION+" = ?" */null;
		String[] selectionArgs = /* new String[]{Condition} */null;

		try {
			Cursor mCur = database.query(MySQLiteHelper.PAST_ACCOUNT,
					columns, selection, selectionArgs, null, null, null);
			if (mCur != null) {
				if (mCur.getCount() > 0) {
					if (mCur.moveToFirst()) {
						cBeanAL = new ArrayList<PastAccount>();
						do {
							PastAccount sBean = new PastAccount();
							sBean.setP_Result_SNO(mCur.getInt(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_SNO)));
							sBean.setP_Result_AccountNo(mCur.getInt(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_AccountNo)));
							sBean.setP_Result_Date(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_Date)).trim());
							sBean.setP_Result_TXNRefNo(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_TXNRefNo)));
							sBean.setP_Result_TXNDESC(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_TXNDESC)).trim());
							sBean.setP_Result_Currency(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_Currency)).trim());
							sBean.setP_Result_Amount(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_Amount)));
							sBean.setP_Result_Balance(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.PAST_Result_Balance)));
							cBeanAL.add(sBean);
						} while (mCur.moveToNext());
					}
				}
				mCur.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cBeanAL;
	}// getConditionDetails()

	/**Delete PastAccount values
	 * @return integer
	 */
	public int deletePastAccountDetails() {
		String whereClause = null;
		String[] whereArgs = null;
		return database.delete(MySQLiteHelper.PAST_ACCOUNT, whereClause,
				whereArgs);
	}// deletePastAccountDetails()
	
	
	
	
	/**Insert PastAccount Details 
	 * @param bean PastAccount bean
	 * @return 1
	 */
	public long insertCurrentAccountDetails(CurrentAccount bean) {
		try {
			ContentValues contentValue = new ContentValues();
			contentValue.put(MySQLiteHelper.CURRENT_Result_AccountNo, bean.getC_Result_AccountNo());
			contentValue.put(MySQLiteHelper.CURRENT_Result_AccountName, bean.getC_Result_AccountName());
			contentValue.put(MySQLiteHelper.CURRENT_Result_Currency,bean.getC_Result_Currency());
			contentValue.put(MySQLiteHelper.CURRENT_Result_CurrentBalance, bean.getC_Result_CurrentBalance());
			contentValue.put(MySQLiteHelper.CURRENT_Result_ThresholdBalance, bean.getC_Result_ThresholdBalance());
			contentValue.put(MySQLiteHelper.CURRENT_Result_CurrentDate,bean.getC_Result_CurrentDate());
			return database.insert(MySQLiteHelper.CURRENT_ACCOUNT, null, contentValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}// insertPastAccountDetails()


	/**Get PastAccount from db
	 * @return PastAccount beanArraylist values
	 */
	public ArrayList<CurrentAccount> getCurrentAccountDetails() {
		ArrayList<CurrentAccount> cBeanAL = null;
		String[] columns = new String[] { MySQLiteHelper.CURRENT_Result_AccountNo,
				MySQLiteHelper.CURRENT_Result_AccountName, MySQLiteHelper.CURRENT_Result_Currency, MySQLiteHelper.CURRENT_Result_CurrentBalance,
				MySQLiteHelper.CURRENT_Result_ThresholdBalance, MySQLiteHelper.CURRENT_Result_CurrentDate };

		String selection = /* MySQLiteHelper.CS_C1_CONDITION+" = ?" */null;
		String[] selectionArgs = /* new String[]{Condition} */null;

		try {
			Cursor mCur = database.query(MySQLiteHelper.CURRENT_ACCOUNT,
					columns, selection, selectionArgs, null, null, null);
			if (mCur != null) {
				if (mCur.getCount() > 0) {
					if (mCur.moveToFirst()) {
						cBeanAL = new ArrayList<CurrentAccount>();
						do {
							CurrentAccount sBean = new CurrentAccount();
							sBean.setC_Result_AccountNo(mCur.getInt(mCur.getColumnIndex(MySQLiteHelper.CURRENT_Result_AccountNo)));
							sBean.setC_Result_AccountName(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.CURRENT_Result_AccountName)));
							sBean.setC_Result_Currency(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.CURRENT_Result_Currency)).trim());
							sBean.setC_Result_CurrentBalance(mCur.getLong(mCur.getColumnIndex(MySQLiteHelper.CURRENT_Result_CurrentBalance)));
							sBean.setC_Result_ThresholdBalance(mCur.getLong(mCur.getColumnIndex(MySQLiteHelper.CURRENT_Result_ThresholdBalance)));
							sBean.setC_Result_CurrentDate(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.CURRENT_Result_CurrentDate)).trim());
							cBeanAL.add(sBean);
						} while (mCur.moveToNext());
					}
				}
				mCur.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cBeanAL;
	}// getConditionDetails()

	/**Delete CurrentAccount values
	 * @return integer
	 */
	public int deleteCurrentAccountDetails() {
		String whereClause = null;
		String[] whereArgs = null;
		return database.delete(MySQLiteHelper.CURRENT_ACCOUNT, whereClause,
				whereArgs);
	}// deletePastAccountDetails()
	
	
	
	
	
	
	/**Insert FutureAccount Details 
	 * @param bean PastAccount bean
	 * @return 1
	 */
	public long insertFutureAccountDetails(FutureAccount bean) {
		try {
			ContentValues contentValue = new ContentValues();
			contentValue.put(MySQLiteHelper.Future_Result_InstType, bean.getF_Result_InstType());
			contentValue.put(MySQLiteHelper.Future_Result_Date, bean.getF_Result_Date());
			contentValue.put(MySQLiteHelper.Future_Result_TXNDesc,bean.getF_Result_TXNDesc());
			contentValue.put(MySQLiteHelper.Future_Result_VendorCustomer, bean.getF_Result_VendorCustomer());
			contentValue.put(MySQLiteHelper.Future_Result_Indicator, bean.getF_Result_Indicator());
			contentValue.put(MySQLiteHelper.Future_Result_Original_Currency,bean.getF_Result_Original_Currency());
			contentValue.put(MySQLiteHelper.Future_Result_Amount, bean.getF_Result_Amount());
			contentValue.put(MySQLiteHelper.Future_Result_LocalCurrency, bean.getF_Result_LocalCurrency());
			contentValue.put(MySQLiteHelper.Future_Result_LocalAmount, bean.getF_Result_LocalAmount());
			return database.insert(MySQLiteHelper.FUTURE_ACCOUNT, null, contentValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}// insertFutureAccountDetails()


	/**Get FutureAccount from db
	 * @return FutureAccount beanArraylist values
	 */
	public ArrayList<FutureAccount> getFutureAccountDetails() {
		ArrayList<FutureAccount> cBeanAL = null;
		String[] columns = new String[] { MySQLiteHelper.Future_Result_InstType,
				MySQLiteHelper.Future_Result_Date, MySQLiteHelper.Future_Result_TXNDesc, MySQLiteHelper.Future_Result_VendorCustomer,
				MySQLiteHelper.Future_Result_Indicator, MySQLiteHelper.Future_Result_Original_Currency, MySQLiteHelper.Future_Result_Amount,
				MySQLiteHelper.Future_Result_LocalCurrency, MySQLiteHelper.Future_Result_LocalAmount };

		String selection = /* MySQLiteHelper.CS_C1_CONDITION+" = ?" */null;
		String[] selectionArgs = /* new String[]{Condition} */null;

		try {
			Cursor mCur = database.query(MySQLiteHelper.FUTURE_ACCOUNT,
					columns, selection, selectionArgs, null, null, null);
			if (mCur != null) {
				if (mCur.getCount() > 0) {
					if (mCur.moveToFirst()) {
						cBeanAL = new ArrayList<FutureAccount>();
						do {
							FutureAccount sBean = new FutureAccount();
							sBean.setF_Result_InstType(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.Future_Result_InstType)));
							sBean.setF_Result_Date(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.Future_Result_Date)));
							sBean.setF_Result_TXNDesc(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.Future_Result_TXNDesc)).trim());
							sBean.setF_Result_VendorCustomer(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.Future_Result_VendorCustomer)));
							sBean.setF_Result_Indicator(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.Future_Result_Indicator)).trim());
							sBean.setF_Result_Original_Currency(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.Future_Result_Original_Currency)).trim());
							sBean.setF_Result_Amount(mCur.getLong(mCur.getColumnIndex(MySQLiteHelper.Future_Result_Amount)));
							sBean.setF_Result_LocalCurrency(mCur.getString(mCur.getColumnIndex(MySQLiteHelper.Future_Result_LocalCurrency)));
							sBean.setF_Result_LocalAmount(mCur.getLong(mCur.getColumnIndex(MySQLiteHelper.Future_Result_LocalAmount)));
							cBeanAL.add(sBean);
						} while (mCur.moveToNext());
					}
				}
				mCur.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cBeanAL;
	}// getConditionDetails()

	/**Delete PastAccount values
	 * @return integer
	 */
	public int deleteFutureAccountDetails() {
		String whereClause = null;
		String[] whereArgs = null;
		return database.delete(MySQLiteHelper.FUTURE_ACCOUNT, whereClause,
				whereArgs);
	}// deletePastAccountDetails()
	
	public String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

// generate a hash

   
	
	
	
	
}// DatabaseDetailsDataSource