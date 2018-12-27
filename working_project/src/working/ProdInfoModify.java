package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ProdInfoModify extends JPanel {
	private JTextField ProNoTextField;
	private JTextField OriTextField;
	private JTextField ChangeTextField;
	private JTextField ProNoTextField_1;
	private JTable SizeTable;

	/**
	 * Create the panel.
	 */

	public ProdInfoModify() {
		setLayout(null);

		JLabel Title = new JLabel("상품정보 수정");
		Title.setFont(new Font("굴림", Font.BOLD, 20));
		Title.setBounds(12, 10, 201, 26);
		add(Title);

		TitledBorder Tb1 = new TitledBorder(new LineBorder(Color.black), "단가수정");
		Tb1.setTitleColor(Color.black);

		JPanel p1 = new JPanel();
		p1.setBounds(12, 46, 540, 113);
		add(p1);
		p1.setLayout(null);
		p1.setBorder(Tb1);

		JLabel ProNoLabel = new JLabel("품번 :");
		ProNoLabel.setBounds(12, 31, 57, 16);
		ProNoLabel.setFont(new Font("굴림", Font.BOLD, 13));
		ProNoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		p1.add(ProNoLabel);

		ProNoTextField = new JTextField();
		ProNoTextField.setBounds(59, 27, 109, 26);
		p1.add(ProNoTextField);
		ProNoTextField.setColumns(10);

		JLabel OriPriceLabel = new JLabel("기존 판매단가 :");
		OriPriceLabel.setBounds(12, 57, 100, 36);
		p1.add(OriPriceLabel);
		OriPriceLabel.setFont(new Font("굴림", Font.BOLD, 13));

		OriTextField = new JTextField();
		OriTextField.setBounds(118, 63, 109, 26);
		p1.add(OriTextField);
		OriTextField.setColumns(10);
		OriTextField.setEditable(false);

		JButton SearchButton = new JButton("조회");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String price = "0";
				DBcon db = new DBcon();
				db.SearchPro(ProNoTextField.getText());
				price = db.getPrice().toString();
				OriTextField.setText(price);
			}

		});
		SearchButton.setBounds(180, 27, 70, 25);
		p1.add(SearchButton);

		JLabel ChangePriceLabel = new JLabel("변경 판매단가 :");
		ChangePriceLabel.setBounds(237, 57, 100, 36);
		p1.add(ChangePriceLabel);
		ChangePriceLabel.setFont(new Font("굴림", Font.BOLD, 13));
		ChangePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

		ChangeTextField = new JTextField();
		ChangeTextField.setBounds(338, 63, 109, 26);
		p1.add(ChangeTextField);
		ChangeTextField.setColumns(10);

		JButton OkButton = new JButton("확인");
		OkButton.setBounds(458, 63, 70, 25);
		p1.add(OkButton);
		OkButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String c_price =ChangeTextField.getText();
				String p_no = ProNoTextField.getText();
				DBcon db = new DBcon();
				db.UpdatePrice(c_price, p_no);
				
			}
		});
		OkButton.setFont(new Font("굴림", Font.PLAIN, 12));

		TitledBorder Tb2 = new TitledBorder(new LineBorder(Color.black), "재고 등록 / 수정");
		Tb2.setTitleColor(Color.black);

		JPanel p2 = new JPanel();
		p2.setBounds(12, 169, 540, 205);

		add(p2);
		p2.setLayout(null);
		p2.setBorder(Tb2);

		JLabel ShopLabel = new JLabel("매장 :");
		ShopLabel.setBounds(11, 26, 54, 36);
		ShopLabel.setFont(new Font("굴림", Font.BOLD, 13));
		p2.add(ShopLabel);

		JComboBox ShopComboBox = new JComboBox();
		ShopComboBox.setBounds(67, 31, 148, 26);
		ShopComboBox.setFont(new Font("굴림", Font.PLAIN, 13));
		ShopComboBox.setMaximumRowCount(100);
		p2.add(ShopComboBox);

		try {
			String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
			String ID = "ora_user3";
			String PW = "han3";
			String sql = null;

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(URL, ID, PW);
				stmt = conn.createStatement();
				sql = "select distinct sh.s_name from shop sh, stock st "
						+ "where sh.s_code = st.s_code and st.p_qty > 0";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ShopComboBox.addItem(rs.getString(1));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel ProNoLabel_1 = new JLabel("품번 :");
		ProNoLabel_1.setBounds(239, 26, 59, 36);
		ProNoLabel_1.setFont(new Font("굴림", Font.BOLD, 13));
		p2.add(ProNoLabel_1);

		ProNoTextField_1 = new JTextField();
		ProNoTextField_1.setColumns(10);
		ProNoTextField_1.setBounds(290, 32, 109, 26);
		p2.add(ProNoTextField_1);
		
		JComboBox ColorComboBox = new JComboBox();
		ColorComboBox.setBounds(67, 77, 105, 26);
		p2.add(ColorComboBox);
		ColorComboBox.setMaximumRowCount(100);
		ColorComboBox.setFont(new Font("굴림", Font.PLAIN, 13));

		JButton SearchButton_1 = new JButton("조회");
		SearchButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
					String ID = "ora_user3";
					String PW = "han3";
					String sql = null;

					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;

					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					try {
						conn = DriverManager.getConnection(URL, ID, PW);
						stmt = conn.createStatement();
						
						sql = "select distinct pr.p_color from product pr,stock st,shop sh\r\n" + 
								"Where pr.p_code = st.p_code and\r\n" + 
								"sh.s_code=st.s_code and\r\n" + 
								"pr.p_no =" + ProNoTextField_1.getText() +
								"and sh.s_name ='"+ShopComboBox.getSelectedItem()+"'";

						rs = stmt.executeQuery(sql);
						
						while(rs.next()) {
							ColorComboBox.addItem(rs.getString(1));
						}

						
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							stmt.close();
							conn.close();
							rs.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

		
		
		SearchButton_1.setFont(new Font("굴림", Font.PLAIN, 12));
		SearchButton_1.setBounds(411, 32, 70, 25);
		p2.add(SearchButton_1);

		JLabel ColorLabel = new JLabel("색상 :");
		ColorLabel.setBounds(11, 72, 54, 36);
		p2.add(ColorLabel);
		ColorLabel.setFont(new Font("굴림", Font.BOLD, 13));

		

		JScrollPane SizeScrollPane = new JScrollPane();
		SizeScrollPane.setBounds(67, 113, 373, 63);
		p2.add(SizeScrollPane);
		
		SizeTable = new JTable();
		SizeScrollPane.setViewportView(SizeTable);
		SizeTable.setFont(new Font("굴림", Font.PLAIN, 13));
		SizeTable.setModel(new DefaultTableModel(new Object[][] { { }, },
				new String[] { "S", "M", "L", "XL" }));

		SizeTable.setRowHeight(35);


		JLabel SizeLabel = new JLabel("사이즈 :");
		SizeLabel.setBounds(11, 118, 67, 26);
		p2.add(SizeLabel);
		SizeLabel.setFont(new Font("굴림", Font.BOLD, 13));

		JButton button = new JButton("확인");
		button.setBounds(452, 118, 70, 36);
		p2.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setFont(new Font("굴림", Font.PLAIN, 12));

	}
}
