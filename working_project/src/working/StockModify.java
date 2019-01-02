package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class StockModify extends JPanel {
	private JTextField ProNoTextField_2;

	private DBcon myDBcon;
	private JTextField S_Size_Field;
	private JTextField M_Size_Field;
	private JTextField L_Size_Field;
	private JTextField XL_Size_Field;
	
	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public StockModify(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(null);

		JLabel Title = new JLabel("상품정보 수정");
		Title.setFont(new Font("굴림", Font.BOLD, 20));
		Title.setBounds(12, 10, 201, 26);
		add(Title);

		TitledBorder Tb1 = new TitledBorder(new LineBorder(Color.black), "단가수정");
		Tb1.setTitleColor(Color.black);

		TitledBorder Tb2 = new TitledBorder(new LineBorder(Color.black), "재고 등록 / 수정");
		Tb2.setTitleColor(Color.black);

		JPanel p2 = new JPanel();
		p2.setBounds(12, 53, 540, 314);

		add(p2);
		p2.setLayout(null);
		p2.setBorder(Tb2);

		JLabel StoreLabel = new JLabel("매장 :");
		StoreLabel.setBounds(11, 39, 54, 36);
		StoreLabel.setFont(new Font("굴림", Font.BOLD, 13));
		p2.add(StoreLabel);

		JComboBox StoreComboBox = new JComboBox();
		StoreComboBox.setBounds(67, 44, 148, 26);
		StoreComboBox.setFont(new Font("굴림", Font.PLAIN, 13));
		StoreComboBox.setMaximumRowCount(100);
		p2.add(StoreComboBox);
		myDBcon.getStoreNameCombobox(StoreComboBox);
		
		JLabel ProNoLabel_2 = new JLabel("품번 :");
		ProNoLabel_2.setBounds(239, 39, 59, 36);
		ProNoLabel_2.setFont(new Font("굴림", Font.BOLD, 13));
		p2.add(ProNoLabel_2);

		ProNoTextField_2 = new JTextField();
		ProNoTextField_2.setColumns(10);
		ProNoTextField_2.setBounds(289, 45, 109, 26);
		p2.add(ProNoTextField_2);
		
		JComboBox ColorComboBox = new JComboBox();
		ColorComboBox.setBounds(67, 104, 105, 26);
		p2.add(ColorComboBox);
		ColorComboBox.setMaximumRowCount(100);
		ColorComboBox.setFont(new Font("굴림", Font.PLAIN, 13));
		

		JButton SearchColorBtn = new JButton("색상 조회");
		SearchColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ColorComboBox.removeAllItems();
				S_Size_Field.setText(null);
				M_Size_Field.setText(null);
				L_Size_Field.setText(null);
				XL_Size_Field.setText(null);
				String p_no = ProNoTextField_2.getText();
				myDBcon.searchStockColor(ColorComboBox, StoreComboBox, p_no);
			}
		});
		SearchColorBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		SearchColorBtn.setBounds(406, 45, 111, 25);
		p2.add(SearchColorBtn);

		JLabel ColorLabel = new JLabel("색상 :");
		ColorLabel.setBounds(11, 99, 54, 36);
		p2.add(ColorLabel);
		ColorLabel.setFont(new Font("굴림", Font.BOLD, 13));
	
		JButton SearchSizeBtn = new JButton("재고 조회");
		SearchSizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qty = "0";
				String s_name = StoreComboBox.getSelectedItem().toString();
				String p_no = ProNoTextField_2.getText();
				String p_color = ColorComboBox.getSelectedItem().toString();
				
				
				myDBcon.searchSize(s_name, p_no, p_color, "S");
				qty = myDBcon.getQty().toString();
				S_Size_Field.setText(qty);
				
				myDBcon.searchSize(s_name, p_no, p_color, "M");
				qty = myDBcon.getQty().toString();
				M_Size_Field.setText(qty);

				myDBcon.searchSize(s_name, p_no, p_color, "L");
				qty = myDBcon.getQty().toString();
				L_Size_Field.setText(qty);
				
				myDBcon.searchSize(s_name, p_no, p_color, "XL");
				qty = myDBcon.getQty().toString();
				XL_Size_Field.setText(qty);
	
			}
		});
		SearchSizeBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		SearchSizeBtn.setBounds(196, 105, 102, 25);
		p2.add(SearchSizeBtn);


		JLabel SizeLabel = new JLabel("사이즈 :");
		SizeLabel.setBounds(11, 169, 79, 26);
		p2.add(SizeLabel);
		SizeLabel.setFont(new Font("굴림", Font.BOLD, 13));


		
		JPanel panel = new JPanel();
		panel.setBounds(42, 205, 384, 75);
		p2.add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel S_Size_Label = new JLabel("S");
		S_Size_Label.setHorizontalAlignment(SwingConstants.CENTER);
		S_Size_Label.setBackground(Color.LIGHT_GRAY);
		panel.add(S_Size_Label);
		S_Size_Label.setOpaque(true);
//		Border border =BorderFactory.createLineBorder(Color.BLACK); // 라벨 테두리
		S_Size_Label.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));  // 원하는 위치에 라벨 테두리
		
		JLabel M_Size_Label = new JLabel("M");
		M_Size_Label.setHorizontalAlignment(SwingConstants.CENTER);
		M_Size_Label.setBackground(Color.LIGHT_GRAY);
		panel.add(M_Size_Label);
		M_Size_Label.setOpaque(true);
		M_Size_Label.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK)); 


		JLabel L_Size_Label = new JLabel("L");
		L_Size_Label.setHorizontalAlignment(SwingConstants.CENTER);
		L_Size_Label.setBackground(Color.LIGHT_GRAY);
		panel.add(L_Size_Label);
		L_Size_Label.setOpaque(true);
		L_Size_Label.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK)); 


		JLabel XL_Size_Label = new JLabel("XL");
		XL_Size_Label.setHorizontalAlignment(SwingConstants.CENTER);
		XL_Size_Label.setBackground(Color.LIGHT_GRAY);
		panel.add(XL_Size_Label);
		XL_Size_Label.setOpaque(true);
		XL_Size_Label.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK)); 
		
		
		S_Size_Field = new JTextField();
		panel.add(S_Size_Field);
		S_Size_Field.setColumns(10);
		
		M_Size_Field = new JTextField();
		panel.add(M_Size_Field);
		M_Size_Field.setColumns(10);
		
		L_Size_Field = new JTextField();
		panel.add(L_Size_Field);
		L_Size_Field.setColumns(10);
		
		XL_Size_Field = new JTextField();
		panel.add(XL_Size_Field);
		XL_Size_Field.setColumns(10);
		
		
		JButton UpdateStockBtn = new JButton("확인");
		UpdateStockBtn.setBounds(438, 215, 79, 55);
		p2.add(UpdateStockBtn);
		UpdateStockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String c_stock = S_Size_Field.getText();
				String s_name = StoreComboBox.getSelectedItem().toString();
				String p_no = ProNoTextField_2.getText();
				String p_color = ColorComboBox.getSelectedItem().toString();

				myDBcon.updateStock(c_stock, s_name, p_no, p_color, "S");										
				myDBcon.updateStock(c_stock, s_name, p_no, p_color, "M");
				myDBcon.updateStock(c_stock, s_name, p_no, p_color, "L");
				myDBcon.updateStock(c_stock, s_name, p_no, p_color, "XL");

			}
		});
		UpdateStockBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		


	}
}
