package shopping;

import java.sql.SQLException;
import java.sql.Types;



public class InsertShop extends IConnectImpl{

	public InsertShop() {
		super("education", "1234");
	}
	@Override
	public void execute() {
		String query = "insert into sh_goods values (seq_total_idx.nextval, ?, ?, sysdate, ?)"; 
		try {
			psmt=con.prepareStatement(query);
			
			psmt.setString(1, scanValue("상품명"));
			psmt.setString(2, scanValue("상품가격"));
			psmt.setString(3, scanValue("상품코드"));
			
		
			int affected = psmt.executeUpdate();
			
			System.out.println(affected +"행 입력성공");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		new InsertShop().execute();
	}
	
}
