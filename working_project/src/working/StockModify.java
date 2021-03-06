package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class StockModify extends JPanel {
	private JTextField proNoTextField;

	private DBcon myDBcon;
	private JTextField S_SizeField;
	private JTextField M_SizeField;
	private JTextField L_SizeField;
	private JTextField XL_SizeField;

	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public StockModify(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(null);

		JLabel Title = new JLabel("재고 수정(등록)");
		Title.setFont(new Font("굴림", Font.BOLD, 20));
		Title.setBounds(12, 10, 201, 26);
		add(Title);

		TitledBorder Tb = new TitledBorder(new LineBorder(Color.black), "수정(등록)");
		Tb.setTitleColor(Color.black);

		JPanel stockModifyPanel = new JPanel();
		stockModifyPanel.setBounds(12, 50, 500, 300);

		add(stockModifyPanel);
		stockModifyPanel.setLayout(null);
		stockModifyPanel.setBorder(Tb);

		JLabel storeLabel = new JLabel("매장 :");
		storeLabel.setBounds(11, 39, 54, 36);
		storeLabel.setFont(new Font("굴림", Font.BOLD, 13));
		stockModifyPanel.add(storeLabel);

		JComboBox storeComboBox = new JComboBox();
		storeComboBox.setBounds(67, 44, 132, 26);
		storeComboBox.setFont(new Font("굴림", Font.PLAIN, 13));
		storeComboBox.setMaximumRowCount(100);
		stockModifyPanel.add(storeComboBox);
		myDBcon.getStoreNameCombobox(storeComboBox);

		JLabel proNoLabel = new JLabel("품번 :");
		proNoLabel.setBounds(211, 39, 59, 36);
		proNoLabel.setFont(new Font("굴림", Font.BOLD, 13));
		stockModifyPanel.add(proNoLabel);

		proNoTextField = new JTextField();
		proNoTextField.setColumns(10);
		proNoTextField.setBounds(263, 45, 109, 26);
		stockModifyPanel.add(proNoTextField);

		JComboBox colorComboBox = new JComboBox();
		colorComboBox.setBounds(67, 104, 105, 26);
		stockModifyPanel.add(colorComboBox);
		colorComboBox.setMaximumRowCount(100);
		colorComboBox.setFont(new Font("굴림", Font.PLAIN, 13));

		JButton searchColorButton = new JButton("색상 조회");
		searchColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorComboBox.removeAllItems(); // 매장명, 품번으로 해당 색상을 조회
												// 매번 할떄마다 콤보박스 초기화
				S_SizeField.setText(null);
				M_SizeField.setText(null);
				L_SizeField.setText(null);
				XL_SizeField.setText(null);
				String productNo = proNoTextField.getText();
				myDBcon.searchStockColor(colorComboBox, storeComboBox, productNo);
			}
		});
		searchColorButton.setFont(new Font("굴림", Font.PLAIN, 12));
		searchColorButton.setBounds(384, 45, 104, 25);
		stockModifyPanel.add(searchColorButton);

		JLabel colorLabel = new JLabel("색상 :");
		colorLabel.setBounds(11, 99, 54, 36);
		stockModifyPanel.add(colorLabel);
		colorLabel.setFont(new Font("굴림", Font.BOLD, 13));

		JButton searchSizeButton = new JButton("재고 조회");
		searchSizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (colorComboBox.getSelectedItem() != null) {
				String productNo = proNoTextField.getText();
				String storeName = storeComboBox.getSelectedItem().toString();
				String productColor = colorComboBox.getSelectedItem().toString();
				String stockQuantity = "0";

				myDBcon.searchSize(storeName, productNo, productColor, "S");
				stockQuantity = myDBcon.getStockQuantity().toString();
				S_SizeField.setText(stockQuantity);

				myDBcon.searchSize(storeName, productNo, productColor, "M");
				stockQuantity = myDBcon.getStockQuantity().toString();
				M_SizeField.setText(stockQuantity);

				myDBcon.searchSize(storeName, productNo, productColor, "L");
				stockQuantity = myDBcon.getStockQuantity().toString();
				L_SizeField.setText(stockQuantity);

				myDBcon.searchSize(storeName, productNo, productColor, "XL");
				stockQuantity = myDBcon.getStockQuantity().toString();
				XL_SizeField.setText(stockQuantity);
				}else {
					JOptionPane.showMessageDialog(null, "품번 조회 후 색상을 선택해 주세요.");
				}
			}
		});

		searchSizeButton.setFont(new Font("굴림", Font.PLAIN, 12));
		searchSizeButton.setBounds(196, 105, 102, 25);
		stockModifyPanel.add(searchSizeButton);

		JLabel sizeLabel = new JLabel("사이즈 :");
		sizeLabel.setBounds(11, 155, 79, 26);
		stockModifyPanel.add(sizeLabel);
		sizeLabel.setFont(new Font("굴림", Font.BOLD, 13));

		JPanel sizePanel = new JPanel();
		sizePanel.setBounds(21, 191, 384, 75);
		stockModifyPanel.add(sizePanel);
		sizePanel.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel S_SizeLabel = new JLabel("S");
		S_SizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		S_SizeLabel.setBackground(Color.LIGHT_GRAY);
		sizePanel.add(S_SizeLabel);
		S_SizeLabel.setOpaque(true);
//		Border border =BorderFactory.createLineBorder(Color.BLACK); // 라벨 테두리
		S_SizeLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK)); // 원하는 위치에 라벨 테두리

		JLabel M_SizeLabel = new JLabel("M");
		M_SizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		M_SizeLabel.setBackground(Color.LIGHT_GRAY);
		sizePanel.add(M_SizeLabel);
		M_SizeLabel.setOpaque(true);
		M_SizeLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));

		JLabel L_SizeLabel = new JLabel("L");
		L_SizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		L_SizeLabel.setBackground(Color.LIGHT_GRAY);
		sizePanel.add(L_SizeLabel);
		L_SizeLabel.setOpaque(true);
		L_SizeLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));

		JLabel XL_SizeLabel = new JLabel("XL");
		XL_SizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		XL_SizeLabel.setBackground(Color.LIGHT_GRAY);
		sizePanel.add(XL_SizeLabel);
		XL_SizeLabel.setOpaque(true);
		XL_SizeLabel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

		S_SizeField = new JTextField();
		sizePanel.add(S_SizeField);
		S_SizeField.setColumns(10);

		M_SizeField = new JTextField();
		sizePanel.add(M_SizeField);
		M_SizeField.setColumns(10);

		L_SizeField = new JTextField();
		sizePanel.add(L_SizeField);
		L_SizeField.setColumns(10);

		XL_SizeField = new JTextField();
		sizePanel.add(XL_SizeField);
		XL_SizeField.setColumns(10);

		JButton updateButton = new JButton("확인");
		updateButton.setBounds(417, 202, 71, 44);
		stockModifyPanel.add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(colorComboBox.getSelectedItem() != null) {
					
					String productNo = proNoTextField.getText();
					String storeName = storeComboBox.getSelectedItem().toString();
					String productColor = colorComboBox.getSelectedItem().toString();
					String S_changeStockQuantity = S_SizeField.getText();
					String M_changeStockQuantity = M_SizeField.getText();
					String L_changeStockQuantity = L_SizeField.getText();
					String XL_changeStockQuantity = XL_SizeField.getText();
					
					myDBcon.updateStock(S_changeStockQuantity, storeName, productNo, productColor);
										
					proNoTextField.setText(null);
					colorComboBox.removeAllItems();
					S_SizeField.setText(null);
					M_SizeField.setText(null);
					L_SizeField.setText(null);
					XL_SizeField.setText(null);
					
				}else {
					JOptionPane.showMessageDialog(null, "상품 재고를 먼저 조회해주세요.");
				}
			}
		});
		updateButton.setFont(new Font("굴림", Font.PLAIN, 12));

	}
}
