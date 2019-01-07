package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class NewProReg extends JPanel {
	private JTextField productNoField;
	private JTextField colorField;
	private JTextField sizeField;
	private JTextField priceField;

	private DBcon myDBcon;

	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public NewProReg(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(null);

		TitledBorder Tb = new TitledBorder(new LineBorder(Color.black), "상품등록");
		Tb.setTitleColor(Color.black);

		JPanel newProductPanel = new JPanel();
		newProductPanel.setBounds(12, 50, 500, 300);
		add(newProductPanel);
		newProductPanel.setLayout(null);
		newProductPanel.setBorder(Tb);

		JLabel productNoLabel = new JLabel("품번 : ");
		productNoLabel.setFont(new Font("굴림", Font.BOLD, 13));
		productNoLabel.setBounds(12, 50, 62, 29);
		newProductPanel.add(productNoLabel);

		JLabel colorLabel = new JLabel("색상 : ");
		colorLabel.setFont(new Font("굴림", Font.BOLD, 13));
		colorLabel.setBounds(12, 100, 62, 29);
		newProductPanel.add(colorLabel);

		JLabel sizeLabel = new JLabel("사이즈 : ");
		sizeLabel.setFont(new Font("굴림", Font.BOLD, 13));
		sizeLabel.setBounds(12, 150, 62, 29);
		newProductPanel.add(sizeLabel);

		JLabel priceLabel = new JLabel("판매단가 : ");
		priceLabel.setFont(new Font("굴림", Font.BOLD, 13));
		priceLabel.setBounds(12, 200, 76, 29);
		newProductPanel.add(priceLabel);

		productNoField = new JTextField(); // 품번입력
		productNoField.setBounds(84, 50, 213, 29);
		newProductPanel.add(productNoField);
		productNoField.setColumns(10);

		colorField = new JTextField(); // 색상입력
		colorField.setBounds(84, 100, 213, 29);
		newProductPanel.add(colorField);
		colorField.setColumns(10);

		sizeField = new JTextField(); // 사이즈입력
		sizeField.setBounds(84, 150, 213, 29);
		newProductPanel.add(sizeField);
		sizeField.setColumns(10);

		priceField = new JTextField(); // 판매단가
		priceField.setBounds(84, 200, 213, 29);
		newProductPanel.add(priceField);
		priceField.setColumns(10);

		JButton insertButton = new JButton("확인");
		insertButton.setBounds(342, 110, 89, 63);
		newProductPanel.add(insertButton);
		
		JLabel lblNewLabel = new JLabel("신상품등록");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 144, 30);
		add(lblNewLabel);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String productCode = productNoField.getText() + colorField.getText() + sizeField.getText();
				String productNo = productNoField.getText();
				String productColor = colorField.getText();
				String productSize = sizeField.getText();
				String productPrice = priceField.getText();

				String checkProductNo = "\\d{7}"; // 품번은 7자리로 고정
				String checkProductColor = "[a-zA-Z]{2}"; // 컬러는 영어 2자리
				String checkProductSize = "[sml]|(xl)|[SML]|(XL)|(xL)|(Xl){1,2}"; // 사이즈는 s,m,l,xl중 하나
				String checkProductPrice = "^[1-9]\\d*"; // 숫자는 1~9로 시작하는 숫자

				boolean isProductNo = Pattern.matches(checkProductNo, productNo);
				boolean isProductColor = Pattern.matches(checkProductColor, productColor);
				boolean isProductSize = Pattern.matches(checkProductSize, productSize);
				boolean isProductPrice = Pattern.matches(checkProductPrice, productPrice);

				if (productNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "품번을 입력해주세요.");
				} else if (isProductNo == false) {
					JOptionPane.showMessageDialog(null, "품번은 숫자7자리로 입력해주세요.");
				} else if (productColor.isEmpty()) {
					JOptionPane.showMessageDialog(null, "색상을 입력해주세요.");
				} else if (isProductColor == false) {
					JOptionPane.showMessageDialog(null, "색상은 2자리 영어로 입력해주세요. ");
				} else if (productSize.isEmpty()) {
					JOptionPane.showMessageDialog(null, "사이즈를 입력해주세요.");
				} else if (isProductSize == false) {
					JOptionPane.showMessageDialog(null, "사이즈는 S,M,L,XL로 입력해주세요. ");
				} else if (productPrice.isEmpty()) {
					JOptionPane.showMessageDialog(null, "가격을 입력해주세요.");
				} else if (isProductPrice == false) {
					JOptionPane.showMessageDialog(null, "단가는 숫자로 입력해주세요. ");
				} else {
					myDBcon.insertProduct(productCode, productNo, productColor, productSize, productPrice);
					// productCode(상품코드) = 품번 + 색상 + 사이즈로 자동생성
				}

			}
		});
	}
}