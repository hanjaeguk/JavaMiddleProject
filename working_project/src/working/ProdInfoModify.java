package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class ProdInfoModify extends JPanel {
	private JTextField productNoField;
	private JTextField originalPriceField;
	private JTextField priceModifyField;

	private DBcon myDBcon;

	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public ProdInfoModify(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(null);

		JLabel Title = new JLabel("��ǰ�ܰ� ����");
		Title.setFont(new Font("����", Font.BOLD, 20));
		Title.setBounds(12, 10, 201, 26);
		add(Title);

		TitledBorder Tb = new TitledBorder(new LineBorder(Color.black), "�ܰ�����");
		Tb.setTitleColor(Color.black);

		JPanel priceModifyPanel = new JPanel();
		priceModifyPanel.setBounds(12, 50, 500, 300);
		add(priceModifyPanel);
		priceModifyPanel.setLayout(null);
		priceModifyPanel.setBorder(Tb);

		JLabel productNoLabel = new JLabel("ǰ�� :");
		productNoLabel.setBounds(12, 56, 94, 30);
		productNoLabel.setFont(new Font("����", Font.BOLD, 13));
		productNoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		priceModifyPanel.add(productNoLabel);

		productNoField = new JTextField();
		productNoField.setBounds(64, 56, 163, 30);
		priceModifyPanel.add(productNoField);
		productNoField.setColumns(10);

		JLabel originalPriceLabel = new JLabel("���� �ǸŴܰ� :");
		originalPriceLabel.setBounds(12, 130, 100, 30);
		priceModifyPanel.add(originalPriceLabel);
		originalPriceLabel.setFont(new Font("����", Font.BOLD, 13));

		originalPriceField = new JTextField();
		originalPriceField.setBounds(118, 130, 148, 30);
		priceModifyPanel.add(originalPriceField);
		originalPriceField.setColumns(10);
		originalPriceField.setEditable(false);

		JButton searchButton = new JButton("��ȸ");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String price;
				String productNo = productNoField.getText();
				if (productNo.isEmpty()) { // ǰ���� �����̸�
					JOptionPane.showMessageDialog(null, "ǰ���� �Է����ּ���.");
				} else { // ǰ���� ������ �ƴϸ� �� ǰ�� ��ȸ
					myDBcon.searchProduct(productNo);
					price = myDBcon.getProductPrice().toString();
					originalPriceField.setText(price);
				}
			}

		});
		searchButton.setBounds(252, 56, 83, 30);
		priceModifyPanel.add(searchButton);

		JLabel priceModifyLabel = new JLabel("���� �ǸŴܰ� :");
		priceModifyLabel.setBounds(12, 200, 100, 30);
		priceModifyPanel.add(priceModifyLabel);
		priceModifyLabel.setFont(new Font("����", Font.BOLD, 13));
		priceModifyLabel.setHorizontalAlignment(SwingConstants.CENTER);

		priceModifyField = new JTextField();
		priceModifyField.setBounds(118, 200, 148, 30);
		priceModifyPanel.add(priceModifyField);
		priceModifyField.setColumns(10);

		JButton updateButton = new JButton("Ȯ��");
		updateButton.setBounds(300, 200, 83, 30);
		priceModifyPanel.add(updateButton);
		updateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String originalPrice = originalPriceField.getText();
				String priceModify = priceModifyField.getText();
				String productNo = productNoField.getText();

				String checkProductNo = "^[1-9]\\d*"; // ���氡���� ���ڸ� �Է°����ϰ�!(���ۼ��� 0���Ұ�)

				boolean isProductPrice = Pattern.matches(checkProductNo, priceModify);

				if (originalPrice.isEmpty()) { // ��ȸ�� ǰ�� ������ �����̸�
					JOptionPane.showMessageDialog(null, "������ ǰ���� ���� ��ȸ���ּ���.");
				} else { // ��ȸ�� �Ͽ�����
					if (priceModify.isEmpty()) { // �����ҷ��� �ϴ� ������ �����̸�
						JOptionPane.showMessageDialog(null, "������ ������ �Է����ּ���.");
					} else if (isProductPrice == false) {
						JOptionPane.showMessageDialog(null, "������ ���ڷ� �Է����ּ���.");
					} else {
						myDBcon.updatePrice(priceModify, productNo);
					}
				}

			}
		});
		updateButton.setFont(new Font("����", Font.PLAIN, 12));

	}
}
