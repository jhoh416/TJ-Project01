package banking;

import java.io.Serializable;
import java.util.Objects;

abstract class Account implements Serializable{

	String accountnum;
	String name;
	int balance;

	
	public Account(String accountnum, String name, int balance) {
		this.accountnum = accountnum;
		this.name = name;
		this.balance = balance;
		
	}	

	public void showAccInfo() {
		
	}
	
	public abstract void depo(int depo) ;
	
	public int hashCode() {
//		int returnCode = accountnum.hashCode();
		int returnCode=Objects.hash(this.accountnum);
		return returnCode;
	}
	
	public boolean equals(Object obj) {
		Account normal = (Account)obj;
		if (normal.accountnum.equals(accountnum)) 
		{
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "계좌번호: " + accountnum + "이름: " + name + "잔고: " + balance + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}

}

