package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	
	AccountManager am = new AccountManager();
	
	
	
	static void showMenu() {
		
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.저장옵션");
		System.out.println("6.프로그램종료");
	}
	public static void main(String[] args) {
		
		System.out.println("1차프로젝트(학원)");
		
		Scanner scanner = new Scanner(System.in);
		AccountManager accountManager = new AccountManager();
		MenuSelectException MenuEx = new MenuSelectException();
		AutoSaver thread = new AutoSaver(accountManager);
		thread.setDaemon(true);
		
		accountManager.readAccountInfo();
		
		while(true) {
			showMenu();
			try {
				int choice = scanner.nextInt();
				//scanner.nextLine();				
				try {
					
					if(choice > 0 && choice < 7) {
						
						switch(choice) {
						case ICustomDefine.MAKE:
							System.out.println("신규개설계좌선택");
							System.out.println("1.보통계좌");
							System.out.println("2.신용신뢰계좌");
							int pick = scanner.nextInt();
//							scanner.nextLine();
//							scanner.nextLine();
							try {
								if(0 < pick && pick < 3 ) {
									accountManager.makeAccount(pick);
								}else {throw MenuEx;}
							}
							catch(MenuSelectException e) {
									scanner.nextLine();
									System.out.println(e.getMessage());
									e.printStackTrace();
							}
							finally {
								break;
							}
						case ICustomDefine. DEPOSIT:
							accountManager.depositMoney();
							break;
						case ICustomDefine. WITHDRAW:
							accountManager.withdrawMoney();
							break;
						case ICustomDefine. INQUIRE:
							accountManager.showAccInfo();
							break;
						case ICustomDefine. SAVE:
							thread.Saver(thread);
							break;	
						case ICustomDefine.EXIT:
							System.out.println("프로그램종료");
							return;
						}
					}else {
						throw MenuEx;
					}
				}
				catch(MenuSelectException e) {
					scanner.nextLine();
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			catch(InputMismatchException e){
				scanner.nextLine();
				System.out.println("메뉴선택할때 문자를 입력할 수 없습니다.");
//				System.out.println("예외메세지:"+ e.getMessage());
				
				e.printStackTrace();

			}		
		}
	}
}

