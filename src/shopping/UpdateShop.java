package shopping;

import java.sql.SQLException;
import java.sql.Types;

public class UpdateShop extends IConnectImpl{

	public UpdateShop() {
		super("education","1234");
	}
	@Override
	public void execute() {
		
				try {
					csmt = con.prepareCall("{call SHOPUPDATEGOODS(?,?,?,?,?)}");

					csmt.setString(1, scanValue("수정할 제품명"));
					csmt.setString(2, scanValue("수정할 제품가격"));
					csmt.setString(3, scanValue("수정할 상품코드"));
					csmt.setString(4, scanValue("수정할 제품코드"));

					csmt.registerOutParameter(5, Types.NUMERIC);

					csmt.execute();
					
					System.out.println("수정프로시저 실행결과:");
					System.out.println(csmt.getString(5) + "행이 수정되었습니다.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public static void main(String[] args) {
		new UpdateShop().execute();

	}

}
