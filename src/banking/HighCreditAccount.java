package banking;

import java.util.Scanner;

class HighCreditAccount extends Account{

	int interest;
	String credit;
	int extraint;
	
	MenuSelectException MenuEx = new MenuSelectException();
	
	public HighCreditAccount(String accountnum, String name, int balance, int interest, String credit) {
		super(accountnum, name, balance);
		this.interest = interest;
		this.credit = credit; 
	}
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자"+interest+"%");
		System.out.println("신용등급"+credit);
		
	}
	@Override
	public void depo(int depo) {
//		신용계좌 : 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
//		balance = balance + (balance * interest/100) +(balance * extraint/100) + depo;
	Scanner scanner = new Scanner(System.in);
	try {	
		if(credit .equalsIgnoreCase("A")){
			extraint = ICustomDefine.A;
		}
		else if(credit .equalsIgnoreCase("B")){
			extraint = ICustomDefine.B;
		}
		else if(credit .equalsIgnoreCase("C")){
			extraint = ICustomDefine.C;
		}
		else {
			throw MenuEx;
		}
	}
	catch(MenuSelectException e) {
		scanner.nextLine();
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	balance = balance + (balance * interest/100) +(balance * extraint/100) + depo;
		
		
	}
	@Override
	public String toString() {
		return "계좌번호: " + accountnum +  "\n" + "이름: " + name + "\n" + 
				"잔고: " + balance + "\n" + "기본이자: " + interest +"\n" +  
				"신용등급: " + credit + "\n" +  "추가이자: " + extraint +"\n" +"\n" +"\n";
	}

}
