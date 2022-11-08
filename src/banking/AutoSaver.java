package banking;

import java.io.IOException;
import java.util.Scanner;

public class AutoSaver extends Thread {
	
	MenuSelectException MenuEx = new MenuSelectException();
	AccountManager ac;
	
	public AutoSaver(AccountManager accountManager) {
		ac = accountManager;
	}

	public void Saver(AutoSaver thread) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1.자동저장On");
		System.out.println("2.자동저장Off");
		int onoff = scan.nextInt();
		if(onoff==1) {	
			System.out.println("자동저장시작");
			if(thread.isAlive()) {
				System.out.println("이미 자동저장이 실행중입니다");
			}else {
				thread.start();
			}
		}
		else if(onoff==2) {
			thread.interrupt();
			System.out.println("자동저장 종료");
		}
	}
	
	public void run() {
		while(true) {
			try {
				ac.savefile();
				sleep(5000);
			}
			catch(InterruptedException e) {
				System.out.println("쓰레드 종료");
		
				break;
			}catch(Exception e) {
				
			}
		}
	}

	


}