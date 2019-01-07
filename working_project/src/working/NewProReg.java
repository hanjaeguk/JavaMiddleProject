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

		TitledBorder Tb = new TitledBorder(new LineBorder(Color.black), "��ǰ���");
		Tb.setTitleColor(Color.black);

		JPanel newProductPanel = new JPanel();
		newProductPanel.setBounds(12, 50, 500, 300);
		add(newProductPanel);
		newProductPanel.setLayout(null);
		newProductPanel.setBorder(Tb);

		JLabel productNoLabel = new JLabel("ǰ�� : ");
		productNoLabel.setFont(new Font("����", Font.BOLD, 13));
		productNoLabel.setBounds(12, 50, 62, 29);
		newProductPanel.add(productNoLabel);

		JLabel colorLabel = new JLabel("���� : ");
		colorLabel.setFont(new Font("����", Font.BOLD, 13));
		colorLabel.setBounds(12, 100, 62, 29);
		newProductPanel.add(colorLabel);

		JLabel sizeLabel = new JLabel("������ : ");
		sizeLabel.setFont(new Font("����", Font.BOLD, 13));
		sizeLabel.setBounds(12, 150, 62, 29);
		newProductPanel.add(sizeLabel);

		JLabel priceLabel = new JLabel("�ǸŴܰ� : ");
		priceLabel.setFont(new Font("����", Font.BOLD, 13));
		priceLabel.setBounds(12, 200, 76, 29);
		newProductPanel.add(priceLabel);

		productNoField = new JTextField(); // ǰ���Է�
		productNoField.setBounds(84, 50, 213, 29);
		newProductPanel.add(productNoField);
		productNoField.setColumns(10);

		colorField = new JTextField(); // �����Է�
		colorField.setBounds(84, 100, 213, 29);
		newProductPanel.add(colorField);
		colorField.setColumns(10);

		sizeField = new JTextField(); // �������Է�
		sizeField.setBounds(84, 150, 213, 29);
		newProductPanel.add(sizeField);
		sizeField.setColumns(10);

		priceField = new JTextField(); // �ǸŴܰ�
		priceField.setBounds(84, 200, 213, 29);
		newProductPanel.add(priceField);
		priceField.setColumns(10);

		JButton insertButton = new JButton("Ȯ��");
		insertButton.setBounds(342, 110, 89, 63);
		newProductPanel.add(insertButton);
		
		JLabel lblNewLabel = new JLabel("�Ż�ǰ���");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 144, 30);
		add(lblNewLabel);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String productCode = productNoField.getText() + colorField.getText() + sizeField.getText();
				String productNo = productNoField.getText();
				String productColor = colorField.getText();
				String productSize = sizeField.getText();
				String productPrice = priceField.getText();

				String checkProductNo = "\\d{7}"; // ǰ���� 7�ڸ��� ����
				String checkProductColor = "[a-zA-Z]{2}"; // �÷��� ���� 2�ڸ�
				String checkProductSize = "[sml]|(xl)|[SML]|(XL)|(xL)|(Xl){1,2}"; // ������� s,m,l,xl�� �ϳ�
				String checkProductPrice = "^[1-9]\\d*"; // ���ڴ� 1~9�� �����ϴ� ����

				boolean isProductNo = Pattern.matches(checkProductNo, productNo);
				boolean isProductColor = Pattern.matches(checkProductColor, productColor);
				boolean isProductSize = Pattern.matches(checkProductSize, productSize);
				boolean isProductPrice = Pattern.matches(checkProductPrice, productPrice);

				if (productNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "ǰ���� �Է����ּ���.");
				} else if (isProductNo == false) {
					JOptionPane.showMessageDialog(null, "ǰ���� ����7�ڸ��� �Է����ּ���.");
				} else if (productColor.isEmpty()) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.");
				} else if (isProductColor == false) {
					JOptionPane.showMessageDialog(null, "������ 2�ڸ� ����� �Է����ּ���. ");
				} else if (productSize.isEmpty()) {
					JOptionPane.showMessageDialog(null, "����� �Է����ּ���.");
				} else if (isProductSize == false) {
					JOptionPane.showMessageDialog(null, "������� S,M,L,XL�� �Է����ּ���. ");
				} else if (productPrice.isEmpty()) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.");
				} else if (isProductPrice == false) {
					JOptionPane.showMessageDialog(null, "�ܰ��� ���ڷ� �Է����ּ���. ");
				} else {
					myDBcon.insertProduct(productCode, productNo, productColor, productSize, productPrice);
					// productCode(��ǰ�ڵ�) = ǰ�� + ���� + ������� �ڵ�����
				}

			}
		});
	}
}