package working;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBcon {
	JTable table; // stock_select
	String code; // stock_select, getPrice
	String userID, userPW; // login
	int logCnt; // login
	int price;

	// db 연동
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public DBcon() {
		connect();
	}

	public void connect() {
		String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String ID = "project1";
		String PW = "pro1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, ID, PW);
			System.out.println("DB접속");

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB접속 오류");
			e.printStackTrace();
		}
	}

	public void disconn() {
		try {
			System.out.println("DB 종료");
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB 종료 오류");
		}
	}

	public void loginCheck(String id, String pw, String radio) {
		String query;
		if (radio.equals("매장")) {
			query = "select m_id, m_pw from manager";
		} else { // radio = 본사
			query = "select h_id, h_pw from head";
		}

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				this.userID = rs.getString(1);
				this.userPW = rs.getString(2);

				System.out.println(userID + userPW);

				if (id.equals(userID) && pw.equals(userPW)) {
					this.logCnt = 1;
					break;
				} else {
					this.logCnt = 2;
				}
			}
			System.out.println("login query 성공");
		} catch (SQLException e) {
			System.out.println("login query 오류");
			e.printStackTrace();
		} finally {
			disconn();
		}
	}

	public Integer getLogCnt() {
		return logCnt;
	}

	public void stock_select(JTable table, String code) {
		this.table = table;
		this.code = code;

		// 품번, 단가/ 색상, 사이즈, 매장코드, 매장명, 전화번호, 재고
		String query = "select p_no, p_price, p_color, p_size, store.s_code, s_name, s_phone, stock.p_qty\r\n"
				+ "from product, stock, store\r\n" + "where product.p_code=stock.p_code\r\n"
				+ "and store.s_code=stock.s_code\r\n" + "and p_no='" + code + "'\r\n" + "order by store.s_code";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int p_price = rs.getInt(2);
				String color = rs.getString(3);
				String size = rs.getString(4);
				String s_code = rs.getString(5);
				String s_name = rs.getString(6);
				String phone = rs.getString(7);
				String qty = rs.getString(8);

				this.price = p_price;
				Object data[] = { color, size, s_code, s_name, phone, qty };
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(data);
			}
			System.out.println("stock_select 성공");
		} catch (SQLException e) {
			System.out.println("stock_select 오류");
			e.printStackTrace();
		} finally {
			disconn();
		}
	}

	public Integer getPrice() {
		return price;
	}

	public void combo_color(JComboBox combo) {
		String query = "select distinct p_color from product";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				combo.addItem(rs.getString(1));
			}
			System.out.println("combo_color 성공");
		} catch (SQLException e) {
			System.out.println("combo_color 오류");
			e.printStackTrace();
		} finally {
			disconn();
		}
	}

	// 수정해야함
	public void pro_select(String code, String color, String size) {
		String query = "select distinct p_color from product";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rs.getString(1);
			}
			System.out.println("pro_select 성공");
		} catch (SQLException e) {
			System.out.println("pro_select 오류");
			e.printStackTrace();
		} finally {
			disconn();
		}
	}

	public void clear(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	public void Insertpro(String p_code, String p_no, String p_color, String p_size, String p_price) {

		String query = "INSERT INTO PRODUCT VALUES(UPPER('" + p_code + "')," + p_no + ",UPPER('" + p_color
				+ "'),UPPER('" + p_size + "')," + p_price + ")";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			JOptionPane.showMessageDialog(null, "입력되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "입력오류.");
		} finally {
			disconn();
		}
	}
	
	public void SearchPro(String p_no) {
		String query = "SELECT DISTINCT P_PRICE FROM PRODUCT WHERE P_NO ="+
						p_no;
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int str = rs.getInt(1);
				this.price = str;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		
	}
	
	public void UpdatePrice(String c_price,String p_no) {
		String query = "UPDATE PRODUCT SET P_PRICE = " + c_price + "WHERE P_NO = "
						+ p_no;
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			JOptionPane.showMessageDialog(null, "변경되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
