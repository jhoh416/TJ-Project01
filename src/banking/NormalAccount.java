package banking;



class NormalAccount extends Account{

	int interest;

	public NormalAccount(String accountnum, String name, int balance, int interest) {
		super(accountnum, name, balance);
		this.interest = interest;
	}
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자"+interest+"%");
	}
	@Override
	public void depo(int depo) {
		
		System.out.println("일반계좌 :" + depo);
		System.out.println("이자율 >> " + interest);
		System.out.println("기본이자 >> " + balance * interest/100);
		balance = balance + (balance * interest / 100) + depo;
		
	}
	@Override
	public String toString() {
		return  "계좌번호 : " + accountnum + "\n" +  "이름 : " + name + "\n" +  "잔고: "
				+ balance  + "\n" + "기본이자: " + interest +"\n" +"\n" +"\n";
	}
	
}
