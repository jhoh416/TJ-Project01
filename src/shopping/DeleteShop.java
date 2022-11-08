package shopping;

import java.sql.SQLException;
import java.sql.Types;

public class DeleteShop extends IConnectImpl {

	public DeleteShop() {
		super("education","1234");
	}
	
	
	@Override
	public void execute() {
		//	Callable Statement 사용하는 DeleteProcCall 클래스 참조
		//프로시저 호출을 위한 CallableStatement 객체 생성
		//g_idx in    returnVal   out
		try {
			csmt = con.prepareCall("{call ShopDeleteGoods(?,?)}");
			//인파라미터 설정
			csmt.setInt(1, scanInt("삭제할 제품번호"));
			//아웃파라미터의 반환타입 설정
			csmt.registerOutParameter(2, Types.NUMERIC);
			//프로시저 실행
			csmt.execute();
			//아웃파라미터가 문자형이므로 getString()으로 출력한다.
			System.out.println("삭제프로시저 실행결과:");
			System.out.println(csmt.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		new DeleteShop().execute();
	}
	
	
}
