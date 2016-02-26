package com.vl.eiappathon.utils;

public class CurrentAccount {

	//CurrentAccount

	private int C_Result_AccountNo = 0;
	public int getC_Result_AccountNo() {
		return C_Result_AccountNo;
	}
	public void setC_Result_AccountNo(int c_Result_AccountNo) {
		C_Result_AccountNo = c_Result_AccountNo;
	}
	public String getC_Result_AccountName() {
		return C_Result_AccountName;
	}
	public void setC_Result_AccountName(String c_Result_AccountName) {
		C_Result_AccountName = c_Result_AccountName;
	}
	public String getC_Result_Currency() {
		return C_Result_Currency;
	}
	public void setC_Result_Currency(String c_Result_Currency) {
		C_Result_Currency = c_Result_Currency;
	}
	public double getC_Result_CurrentBalance() {
		return C_Result_CurrentBalance;
	}
	public void setC_Result_CurrentBalance(double c_Result_CurrentBalance) {
		C_Result_CurrentBalance = c_Result_CurrentBalance;
	}
	public double getC_Result_ThresholdBalance() {
		return C_Result_ThresholdBalance;
	}
	public void setC_Result_ThresholdBalance(double c_Result_ThresholdBalance) {
		C_Result_ThresholdBalance = c_Result_ThresholdBalance;
	}
	public String getC_Result_CurrentDate() {
		return C_Result_CurrentDate;
	}
	public void setC_Result_CurrentDate(String c_Result_CurrentDate) {
		C_Result_CurrentDate = c_Result_CurrentDate;
	}
	private String C_Result_AccountName = null;
	private String C_Result_Currency = null;
	private double C_Result_CurrentBalance = 0;
	private double C_Result_ThresholdBalance = 0;
	private String C_Result_CurrentDate = null;



}
