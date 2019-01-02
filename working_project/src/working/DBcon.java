package working;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBcon {
	JTable table; // stock_select
	String no, color, size; // stock_select, getPrice
	String user; // login
	String code;
	String p_code;
	int logCnt; // login
	int price, qty; // pro_select
	int groupInt;
	int statusCnt = 1;
	LocalDate currDate = LocalDate.now();

	// DB 연동
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public DBcon() {
		connect();
	}

	// DB connect
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

	// DB disconnect
	// +) 로그아웃, 창닫기 할 때 종료
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

	// LoginView - login check
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
				if (id.equals(rs.getString(1)) && pw.equals(rs.getString(2))) {
					this.logCnt = 1;

					query = "select s_code from store where m_id='" + id + "'";
					pstmt = con.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						String s_user = rs.getString(1); // 로그인 유저의 매장 코드 user에 입력
						this.user = s_user;
					}
					System.out.println(user);
					break;
				} else {
					this.logCnt = 2;
				}
			}
			System.out.println("login query 성공");
		} catch (SQLException e) {
			System.out.println("login query 오류");
			e.printStackTrace();
		}
	}

	public Integer getLogCnt() {
		return logCnt; // 로그인 성공 여부 count 반환
	}

	// StockSearch - stock search
	public void stock_select(JTable table, String no) {
		this.table = table;
		this.no = no;

		// 품번, 단가/ 색상, 사이즈, 매장코드, 매장명, 전화번호, 재고
		String query = "select p_no, p_price, p_color, p_size, store.s_code, s_name, s_phone, stock.p_qty\r\n"
				+ "from product, stock, store\r\n" + "where product.p_code=stock.p_code\r\n"
				+ "and store.s_code=stock.s_code\r\n" + "and p_no='" + no + "'\r\n" + "order by store.s_code";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int p_price = rs.getInt(2);
				String color = rs.getString(3);
				String size = rs.getString(4);
				String s_no = rs.getString(5);
				String s_name = rs.getString(6);
				String phone = rs.getString(7);
				String qty = rs.getString(8);

				this.price = p_price;
				Object data[] = { color, size, s_no, s_name, phone, qty };
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(data);
			}
			System.out.println("stock_select 성공");
		} catch (SQLException e) {
			System.out.println("stock_select 오류");
			e.printStackTrace();
		}
	}

	public Integer getPrice() {
		return price; // 해당 품번 판매단가 반환
	}

	// SalesReg - 상품 전체의 컬러 combobox list에 추가
	public void combo_color(JComboBox<String> combo) {
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
		}
	}

	// SalesReg - 상품 조회
	public void pro_select(String no, String color, String size) {
		String query = "select p_price, p_qty, product.p_code from product, stock\r\n"
				+ "where product.p_code=stock.p_code \r\n" + "and s_code='" + this.user + "'\r\n" + "and p_no='" + no
				+ "' and p_color='" + color + "' and p_size='" + size + "'";

		this.no = no;
		this.color = color;
		this.size = size;
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				this.price = rs.getInt(1);
				this.qty = rs.getInt(2);
				this.code = rs.getString(3);
			}
			System.out.println("pro_select 성공");
		} catch (SQLException e) {
			System.out.println("pro_select 오류");
			e.printStackTrace();
		}
	}

	public Integer getQty() {
		return qty;
	}

	// SalesReg - 상품 등록 //////////////수정쓰~//////
	// +) insert 하고, pro_select 해서 테이블에 뿌려주기?
	public void pro_reg(JTable table, String group, String s_qty, String s_price) {
		this.table = table;
		if (group.equals("판매")) {
			this.groupInt = 1;
		} else {
			this.groupInt = 2;
		}

		// String query = "";
		// insert into SAL_TABLE
		// values(statusCnt,currDate,user,this.group,code,s_qty,s_price);

		try {
//			pstmt = con.prepareStatement(query);
//			rs = pstmt.executeQuery();

			// insert
			// 테이블 없으면 테이블 생성 후 insert

			// select 문 메소드 따로 만들까..?
			int cnt = 1;
			String query = "select sa_group, p_code, sa_qty, sa_price from SAL_S1101201812 order by sa_no"; // jtable에
																											// 뿌려줄 내용
																											// select 문
			// SAL_S1101201812 : SAL_ 매장코드<user> 연도<currDate.getYear()>
			// 월<currDate.getMonthValue()>
			// where sa_date=currDate
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				groupInt = rs.getInt(1);
				String code = rs.getString(2);
				String qty = rs.getString(3);
				String priceStr = rs.getString(4);

				String groupStr;
				if (groupInt == 1) {
					groupStr = "판매";
				} else {
					groupStr = "반품";
				}
				this.no = code.substring(0, 7);
				this.color = code.substring(7, 9);
				this.size = code.substring(9);

				System.out.println(
						groupStr + " " + no + " " + color + " " + size + " " + price + " " + qty + " " + priceStr);

				Object data[] = { cnt, groupStr, no, color, size, price, qty, priceStr };
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(data);
				cnt++;
			}

			System.out.println("pro_reg 성공");
		} catch (SQLException e) {
			System.out.println("pro_reg 오류");
			e.printStackTrace();
		}
	}

	// JTable 필드 초기화
	public void clear(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	public void insertProduct(String p_code, String p_no, String p_color, String p_size, String p_price) {

		String query = "INSERT INTO PRODUCT VALUES(UPPER('" + p_code + "')," + p_no + ",UPPER('" + p_color
				+ "'),UPPER('" + p_size + "')," + p_price + ")";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			JOptionPane.showMessageDialog(null, "입력되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "입력오류.");
		}
	}

	public void searchProduct(String p_no) {
		String query = "SELECT DISTINCT P_PRICE FROM PRODUCT WHERE P_NO =" + p_no;
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int str = rs.getInt(1);
				this.price = str;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePrice(String c_price, String p_no) {
		String query = "UPDATE PRODUCT SET P_PRICE = " + c_price + "WHERE P_NO = " + p_no;

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			JOptionPane.showMessageDialog(null, "변경되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getStoreNameCombobox(JComboBox storeComboBox) {
		String query = "select distinct sr.s_name from store sr, stock sc "
				+ "where sr.s_code = sc.s_code and sc.p_qty > 0";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeComboBox.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void searchStockColor(JComboBox colorComboBox, JComboBox getStoreComboBox, String p_no) {
		String query = "select distinct pro.p_color from product pro,stock sc,store sr\r\n"
				+ "Where pro.p_code = sc.p_code and\r\n" + "sr.s_code = sc.s_code and\r\n" + "pro.p_no =" + p_no
				+ "\r\n" + "and sr.s_name ='" + getStoreComboBox.getSelectedItem() + "'";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				colorComboBox.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void searchSize(String s_name, String p_no, String p_color, String size) {
		String query = "select sc.p_qty from product pro,stock sc,store sr\r\n" + "Where pro.p_code = sc.p_code and\r\n"
				+ "sr.s_code=sc.s_code and sr.s_name = '" + s_name + "'and pro.p_no =" + p_no + "and pro.p_color = '"
				+ p_color + "' and pro.p_size = '" + size + "'";

		try {

			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

//			while(rs.next()) {
//				int qt = rs.getInt(1);
//				this.qty = qt;
//			}

			if (rs.next()) {
				int qt = rs.getInt(1);
				this.qty = qt;
			} else {
				this.qty = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateStock(String c_stock, String s_name, String p_no, String p_color, String p_size) {
		String query = "update stock\r\n" + "set p_qty = " + c_stock + "\r\n"
				+ "where p_code in(select sc.p_code from stock sc, store sr, product pro where sc.s_code = sr.s_code and sc.p_code = pro.p_code\r\n"
				+ "and sr.s_name = '" + s_name + "' and pro.p_no = " + p_no + " and pro.p_color = '" + p_color
				+ "' and pro.p_size = '" + p_size + "') and\r\n"
				+ "s_code in(select sc.s_code from stock sc, store sr, product pro where sc.s_code = sr.s_code and sc.p_code = pro.p_code\r\n"
				+ "and sr.s_name = '" + s_name + "' and pro.p_no =" + p_no + " and pro.p_color = '" + p_color
				+ "' and pro.p_size = '" + p_size + "')";
		
		String query1 = "select p_code from product \r\n" + 
				"where p_no = "+p_no+" and p_color = '"+p_color+"' \r\n" + 
				"and p_size = '"+p_size+"'";

		try {
			PreparedStatement pstmt1 = null;
			ResultSet rs1 = null;
			pstmt1 = con.prepareStatement(query1);
			rs1 = pstmt1.executeQuery();
			
			if(rs1.next()) {
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				JOptionPane.showMessageDialog(null, p_size+"수정되었습니다.");				
			}else {
				JOptionPane.showMessageDialog(null, p_size+"사이즈 상품등록이 필요합니다.");
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}