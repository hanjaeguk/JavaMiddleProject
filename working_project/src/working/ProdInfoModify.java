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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class ProdInfoModify extends JPanel {
	private JTextField ProNoTextField_1;
	private JTextField OriTextField;
	private JTextField ChangeTextField;
	private JTextField ProNoTextField_2;

	private DBcon myDBcon;
	private JTextField S_Size_Field;
	private JTextField M_Size_Field;
	private JTextField L_Size_Field;
	private JTextField XL_Size_Field;
	
	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public ProdInfoModify(DBcon dbcon) {
		setDBcon(dbcon);
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

		JLabel ProNoLabel_1 = new JLabel("품번 :");
		ProNoLabel_1.setBounds(12, 31, 57, 16);
		ProNoLabel_1.setFont(new Font("굴림", Font.BOLD, 13));
		ProNoLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		p1.add(ProNoLabel_1);

		ProNoTextField_1 = new JTextField();
		ProNoTextField_1.setBounds(59, 27, 109, 26);
		p1.add(ProNoTextField_1);
		ProNoTextField_1.setColumns(10);

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
				myDBcon.searchProduct(ProNoTextField_1.getText());
				price = myDBcon.getPrice().toString();
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
				String p_no = ProNoTextField_1.getText();
				myDBcon.updatePrice(c_price, p_no);
				
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

		JLabel StoreLabel = new JLabel("매장 :");
		StoreLabel.setBounds(11, 26, 54, 36);
		StoreLabel.setFont(new Font("굴림", Font.BOLD, 13));
		p2.add(StoreLabel);

		JComboBox StoreComboBox = new JComboBox();
		StoreComboBox.setBounds(67, 31, 148, 26);
		StoreComboBox.setFont(new Font("굴림", Font.PLAIN, 13));
		StoreComboBox.setMaximumRowCount(100);
		p2.add(StoreComboBox);
		myDBcon.getStoreNameCombobox(StoreComboBox);
		
		JLabel ProNoLabel_2 = new JLabel("품번 :");
		ProNoLabel_2.setBounds(239, 26, 59, 36);
		ProNoLabel_2.setFont(new Font("굴림", Font.BOLD, 13));
		p2.add(ProNoLabel_2);

		ProNoTextField_2 = new JTextField();
		ProNoTextField_2.setColumns(10);
		ProNoTextField_2.setBounds(290, 32, 109, 26);
		p2.add(ProNoTextField_2);
		
		JComboBox ColorComboBox = new JComboBox();
		ColorComboBox.setBounds(67, 77, 105, 26);
		p2.add(ColorComboBox);
		ColorComboBox.setMaximumRowCount(100);
		ColorComboBox.setFont(new Font("굴림", Font.PLAIN, 13));
		

		
		

		JButton SearchButton_2 = new JButton("조회");
		SearchButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ColorComboBox.removeAllItems();
				String p_no = ProNoTextField_2.getText();
				myDBcon.searchStock(ColorComboBox, StoreComboBox, p_no);

			}
		});
		

		
		SearchButton_2.setFont(new Font("굴림", Font.PLAIN, 12));
		SearchButton_2.setBounds(411, 32, 70, 25);
		p2.add(SearchButton_2);

		JLabel ColorLabel = new JLabel("색상 :");
		ColorLabel.setBounds(11, 72, 54, 36);
		p2.add(ColorLabel);
		ColorLabel.setFont(new Font("굴림", Font.BOLD, 13));


		JLabel SizeLabel = new JLabel("사이즈 :");
		SizeLabel.setBounds(11, 118, 67, 26);
		p2.add(SizeLabel);
		SizeLabel.setFont(new Font("굴림", Font.BOLD, 13));


		
		JPanel panel = new JPanel();
		panel.setBounds(67, 118, 359, 65);
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
		
		ColorComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s_name = (String) StoreComboBox.getSelectedItem();
				String p_no = ProNoTextField_2.getText();
				String p_color = (String) ColorComboBox.getSelectedItem();
				String size = "S";
				String qty = "0";
				
				myDBcon.searchSize(s_name, p_no, p_color, size);
				qty = myDBcon.getQty().toString();
				S_Size_Field.setText(qty);
				
			}
			
		});
		
		
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
