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
	int logCnt; // login
	int price, qty; // pro_select
	int groupInt;
	int statusCnt = 1;
	LocalDate currDate = LocalDate.now();

	// DB ����
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
			System.out.println("DB����");

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB���� ����");
			e.printStackTrace();
		}
	}

	// DB disconnect
	// +) �α׾ƿ�, â�ݱ� �� �� ����
	public void disconn() {
		try {
			System.out.println("DB ����");
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB ���� ����");
		}
	}

	// LoginView - login check
	public void loginCheck(String id, String pw, String radio) {
		String query;
		if (radio.equals("����")) {
			query = "select m_id, m_pw from manager";
		} else { // radio = ����
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
						String s_user = rs.getString(1); // �α��� ������ ���� �ڵ� user�� �Է�
						this.user = s_user;
					}
					System.out.println(user);
					break;
				} else {
					this.logCnt = 2;
				}
			}
			System.out.println("login query ����");
		} catch (SQLException e) {
			System.out.println("login query ����");
			e.printStackTrace();
		}
	}

	public Integer getLogCnt() {
		return logCnt; // �α��� ���� ���� count ��ȯ
	}

	// StockSearch - stock search
	public void stock_select(JTable table, String no) {
		this.table = table;
		this.no = no;

		// ǰ��, �ܰ�/ ����, ������, �����ڵ�, �����, ��ȭ��ȣ, ���
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
			System.out.println("stock_select ����");
		} catch (SQLException e) {
			System.out.println("stock_select ����");
			e.printStackTrace();
		}
	}

	public Integer getPrice() {
		return price; // �ش� ǰ�� �ǸŴܰ� ��ȯ
	}

	// SalesReg - ��ǰ ��ü�� �÷� combobox list�� �߰�
	public void combo_color(JComboBox<String> combo) {
		String query = "select distinct p_color from product";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				combo.addItem(rs.getString(1));
			}
			System.out.println("combo_color ����");
		} catch (SQLException e) {
			System.out.println("combo_color ����");
			e.printStackTrace();
		}
	}

	// SalesReg - ��ǰ ��ȸ
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
			System.out.println("pro_select ����");
		} catch (SQLException e) {
			System.out.println("pro_select ����");
			e.printStackTrace();
		}
	}

	public Integer getQty() {
		return qty; // �ش� ǰ�� �ǸŴܰ� ��ȯ
	}

	// SalesReg - ��ǰ ��� //////////////������~//////
	// +) insert �ϰ�, pro_select �ؼ� ���̺� �ѷ��ֱ�?
	public void pro_reg(JTable table, String group, String s_qty, String s_price) {
		this.table = table;
		if (group.equals("�Ǹ�")) {
			this.groupInt = 1;
		} else {
			this.groupInt = 2;
		}

		//String query = "";
		// insert into SAL_TABLE
		// values(statusCnt,currDate,user,this.group,code,s_qty,s_price);

		try {
//			pstmt = con.prepareStatement(query);
//			rs = pstmt.executeQuery();

			// insert
			// ���̺� ������ ���̺� ���� �� insert
			
			// select �� �޼ҵ� ���� �����..?
			int cnt=1;
			String query = "select sa_group, p_code, sa_qty, sa_price from SAL_S1101201812 order by sa_no"; // jtable�� �ѷ��� ���� select ��
			//SAL_S1101201812 : SAL_ �����ڵ�<user> ����<currDate.getYear()> ��<currDate.getMonthValue()>
			// where sa_date=currDate
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				groupInt = rs.getInt(1);
				String code = rs.getString(2);
				String qty = rs.getString(3);
				String priceStr = rs.getString(4);
				
				String groupStr;
				if(groupInt == 1) {
					groupStr = "�Ǹ�";
				} else {
					groupStr = "��ǰ";
				}
				this.no = code.substring(0, 7);
				this.color = code.substring(7,9);
				this.size = code.substring(9);
				
				System.out.println(groupStr+" "+no+" "+color+" "+size+" "+price+" "+qty+" "+priceStr);

				Object data[] = { cnt ,groupStr, no, color, size, price, qty, priceStr};
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(data);
				cnt++;
			}

			System.out.println("pro_reg ����");
		} catch (SQLException e) {
			System.out.println("pro_reg ����");
			e.printStackTrace();
		}
	}

	// JTable �ʵ� �ʱ�ȭ
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
			
			JOptionPane.showMessageDialog(null, "�ԷµǾ����ϴ�.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�Է¿���.");
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
		}
	}
	
	public void UpdatePrice(String c_price,String p_no) {
		String query = "UPDATE PRODUCT SET P_PRICE = " + c_price + "WHERE P_NO = "
						+ p_no;
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}