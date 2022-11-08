package banking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;

import java.util.Scanner;



public class AccountManager {
	
//	static MenuSelectException MenuEx = new MenuSelectException();
	
	//Account[] accArray =new Account[50];
	HashSet<Account>accArray;
	
	//public int numOfAccounts =0 ;

	String accountnum, name, credit;
	static int balance;
	int interest;

	
//	HashSet<Account>set = new HashSet<>(Arrays.asList(accArray));
	
	public AccountManager(){
		accArray =   new HashSet<Account>();
	}
	

	public void makeAccount(int pick) {//,) {
		
		String newnum, newname,  newcre;
		int newbal, newint;
		Scanner scan = new Scanner(System.in);
		System.out.println("***신규계좌개설***");
		System.out.println("--------------");
		System.out.println("계좌번호 :");newnum = scan.nextLine();
		System.out.println("고객이름 :");newname = scan.nextLine();
		System.out.println("잔고 :");newbal = scan.nextInt();
		
		if(pick ==1) {
			System.out.print("기본이자%(정수형태로입력): "); newint = scan.nextInt(); 
			scan.nextLine();
			NormalAccount normal = new NormalAccount(newnum, newname, newbal, newint);
			//HashSet 에 넣을 때 .add( 넣을객체명 )
			//accArray.add(normal);

			if(accArray.add(normal)){
				accArray.add(normal);		
			}
			else {
				System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String cover = scan.nextLine();
				if(cover.equalsIgnoreCase("y")){
					accArray.remove(normal);
					accArray.add(normal);
				}else if(cover.equalsIgnoreCase("n")){
					//아무것도안함.
				}
			}
			return;
		}
		
		else {
			System.out.print("기본이자%(정수형태로입력): "); newint = scan.nextInt(); 
			scan.nextLine();
			System.out.print("신용등급(A,B,C등급): "); newcre = scan.nextLine();
			if(newcre.equalsIgnoreCase("A")||newcre.equalsIgnoreCase("B")||newcre.equalsIgnoreCase("C")) {
				HighCreditAccount hca =	new HighCreditAccount(newnum, newname, newbal, newint, newcre);
				//accArray.add(hca); 
				
				if(!(accArray.add(hca))){
					
					System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
					String cover = scan.nextLine();
					if(cover.equalsIgnoreCase("y")){
						accArray.remove(hca);
						accArray.add(hca);
					}
					else if(cover.equalsIgnoreCase("n")){
						
					}
				}
				else {
					accArray.add(hca);
				}
			}else {
				System.err.println("잘못입력됨");
				return;
			}
		}
		System.out.println("--------------");
		System.out.println("계좌개설이 완료되었습니다.");	
		return;
	}
	
	//입금하는 메서드
	void depositMoney(){
		Scanner scan = new Scanner(System.in);

		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.println("계좌번호");

		String accountnum = scan.nextLine();
		
		System.out.println("입금액");
		try {
			int depo = scan.nextInt();
			scan.nextLine();
			
			//for(int i = 0 ; i < numOfAccounts ; i++) {
			for(Account a : accArray) {
				if(accountnum.equals(a.accountnum)) {
					try {
						if(depo>0) {
							try {
								if(depo%500==0) {
									a.depo(depo);
									System.out.println("입금이 완료되었습니다.");
									}
									else {
										throw new Exception();
									}
								}
								catch(Exception e) {
									scan.nextLine();
									System.out.println("입금액은 500원단위로 가능하다.");
									e.printStackTrace();
								}
							}
							else {
								throw new Exception();
							}
					}
					catch(Exception e) {
						scan.nextLine();
						System.out.println("음수를 입금 할 수 없습니다.");
						e.printStackTrace();		
					}
				}
				
			}		
		}
		catch(InputMismatchException e) {
			scan.nextLine();
			System.out.println("금액 입력시 문자를 입력할 수 없습니다.");
			e.printStackTrace();
		}
	}

	
//출금하는 메서드
	void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.println("계좌번호");
		String accountnum = scan.nextLine();
		
		System.out.println("출금액");
		int withd = scan.nextInt();
		
		if(accountnum.equals(null)) {
			System.out.println("계좌정보를 다시 입력하세요");
		}
		//for(int i = 0 ; i < numOfAccounts ; i++) {
		for(Account a : accArray) {
			if(accountnum.equals(a.accountnum)) {
				try {
						if(withd>0) {
							try {
								if(a.balance > withd) {
									try {
										if(withd % 1000 == 0) {
										a.balance-= withd;
										System.out.println("출금이 완료되었습니다.");
										}
										else {
											throw new Exception();
										}
									}
									catch(Exception e){
										System.out.println("출금은 1000원 단위로만 가능합니다.");
									}
								}
								else {
									throw new Exception();
								}
							}
							catch(Exception e) {
								scan.nextLine();
								
									System.out.println("■ 잔고가 부족합니다. 금액전체를 출금할까요?");
									System.out.println("Y : 금액전체 출금처리");
									System.out.println("N : 출금요청취소");
									String answer=scan.nextLine();
									if(answer.equalsIgnoreCase("Y")) {
										a.balance -= a.balance;//a.balance = a.balance - a.balance ; 
										
									}
									else if(answer.equalsIgnoreCase("N")){
										return;										
									}
									else {
										try {
											MenuSelectException MenuEx = new MenuSelectException();
											throw MenuEx;
											}
											catch(MenuSelectException e1) {
												scan.nextLine();
												System.out.println(e1.getMessage());
												e1.printStackTrace();
											}
									}
							}
						}
						else {
							throw new InputMismatchException();
						}
					}
				
				catch(InputMismatchException e){
					scan.nextLine();
					System.out.println("음수를 출금 할 수 없습니다.");
					e.printStackTrace();	
				}
			}
		}			
	}
	
	//정보출력하는 메서드
	public void showAccInfo() {
		Scanner scan = new Scanner(System.in);
		//for(int i =0 ; i < numOfAccounts ; i++) {
		for(Account a : accArray) {
			System.out.println("***계좌정보출력***");
			System.out.println("계좌번호:"+ a.accountnum);
			System.out.println("고객이름:"+ a.name);
			System.out.println("잔고:" +a.balance);
			a.showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		
		
	}
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out =
				new ObjectOutputStream(
					new FileOutputStream("src/banking/AccountInfo.obj")
				);
			for(Account Ac : accArray) {
				out.writeObject(Ac);
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("계좌정보 직렬화 중 예외발생");
		}		
	}
	public void readAccountInfo() {
		try {
			 
			ObjectInputStream in =
				new ObjectInputStream(
					new FileInputStream("src/banking/AccountInfo.obj")
				);
	
			while(true) {			
				Account Ac = (Account) in.readObject();
				accArray.add(Ac);			
			}
		}
		catch (Exception e) {
			
			System.out.println("계좌정보가 복원되었습니다.");
			e.printStackTrace();
		}		

	}


	public void savefile() throws IOException{
//		MenuSelectException MenuEx = new MenuSelectException();
		PrintWriter asa = new PrintWriter(
				new FileWriter("src/banking/AutoSaveAccount.txt"));
		for(Account Ac : accArray) {
			asa.println(Ac);
			asa.println();
		}
		asa.close();
	}
	String src= "src/banking/AutoSaveAccount.bin";


}
