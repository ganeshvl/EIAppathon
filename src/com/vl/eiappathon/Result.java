package com.vl.eiappathon;

public class Result {
	
	/**
	 * 
	 * @param account_no
	 * @param Date
	 * @param s_no
	 * @param TXN_Ref
	 * @param TXN_Descr
	 * @param currency
	 * @param amount
	 * @param balance
	 * @param account_name
	 * @param threshold_balance
	 * @param currentDate
	 * @param InstType
	 * @param vendorCustomerName
	 * @param Indicator
	 * @param original_currency
	 * @param original_amount
	 * @param local_amount
	 * @param local_currency
	 */
	public Result(int account_no,String Date,
			 int s_no,
			 int TXN_Ref,
			 String TXN_Descr,
			 long currency,
			 long amount,
			 long balance,
			 String account_name,
			 long threshold_balance,
			 String currentDate,
			 String InstType,
			 String vendorCustomerName,
			 String Indicator,
			 long original_currency,
			 long original_amount,
			 long local_amount,
			 long local_currency) {
		super();
		this.account_no = account_no;
		this.Date = Date;
		this.s_no = s_no;
		this.TXN_Ref = TXN_Ref;
		this.TXN_Descr = TXN_Descr;
		this.currency = currency;
		this.amount = amount;
		this.balance = balance;
		this.account_name = account_name;
		this.threshold_balance = threshold_balance;
		this.currentDate = currentDate;
		this.InstType = InstType;
		this.vendorCustomerName = vendorCustomerName;
		this.Indicator = Indicator;
		this.original_currency = original_currency;
		this.original_amount = original_amount;
		this.local_amount = local_amount;
		this.local_currency = local_currency;
	}
	
	private int account_no;
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public int getTXN_Ref() {
		return TXN_Ref;
	}
	public void setTXN_Ref(int tXN_Ref) {
		TXN_Ref = tXN_Ref;
	}
	public String getTXN_Descr() {
		return TXN_Descr;
	}
	public void setTXN_Descr(String tXN_Descr) {
		TXN_Descr = tXN_Descr;
	}
	public long getCurrency() {
		return currency;
	}
	public void setCurrency(long currency) {
		this.currency = currency;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public long getThreshold_balance() {
		return threshold_balance;
	}
	public void setThreshold_balance(long threshold_balance) {
		this.threshold_balance = threshold_balance;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getInstType() {
		return InstType;
	}
	public void setInstType(String instType) {
		InstType = instType;
	}
	public String getVendorCustomerName() {
		return vendorCustomerName;
	}
	public void setVendorCustomerName(String vendorCustomerName) {
		this.vendorCustomerName = vendorCustomerName;
	}
	public String getIndicator() {
		return Indicator;
	}
	public void setIndicator(String indicator) {
		Indicator = indicator;
	}
	public long getOriginal_currency() {
		return original_currency;
	}
	public void setOriginal_currency(long original_currency) {
		this.original_currency = original_currency;
	}
	public long getOriginal_amount() {
		return original_amount;
	}
	public void setOriginal_amount(long original_amount) {
		this.original_amount = original_amount;
	}
	public long getLocal_amount() {
		return local_amount;
	}
	public void setLocal_amount(long local_amount) {
		this.local_amount = local_amount;
	}
	public long getLocal_currency() {
		return local_currency;
	}
	public void setLocal_currency(long local_currency) {
		this.local_currency = local_currency;
	}
	private String Date;
	private int s_no;
	private int TXN_Ref;
	private String TXN_Descr;
	private long currency;
	private long amount;
	private long balance;
	
	private String account_name;
	private long threshold_balance;
	private String currentDate;
	
	private String InstType;
	private String vendorCustomerName;
	private String Indicator;
	private long original_currency;
	private long original_amount;
	private long local_amount;
	private long local_currency;

}
