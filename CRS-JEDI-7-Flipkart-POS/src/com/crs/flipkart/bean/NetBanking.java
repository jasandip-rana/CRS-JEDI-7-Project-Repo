/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author Raj
 *
 */
public class NetBanking extends Online{

	private String modeOfTransfer;
	private String IfscCode;
	private String accountNumber;

	/**
	 * @return the modeOfTransfer
	 */
	public String getModeOfTransfer() {
		return modeOfTransfer;
	}

	/**
	 * @param modeOfTransfer the modeOfTransfer to set
	 */
	public void setModeOfTransfer(String modeOfTransfer) {
		this.modeOfTransfer = modeOfTransfer;
	}

	/**
	 * @return the ifscCode
	 */
	public String getIfscCode() {
		return IfscCode;
	}

	/**
	 * @param ifscCode the ifscCode to set
	 */
	public void setIfscCode(String ifscCode) {
		IfscCode = ifscCode;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
