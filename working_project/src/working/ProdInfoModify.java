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

	private DBcon myDBcon;
	
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
		p1.setBounds(12, 46, 540, 254);
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
		OriPriceLabel.setBounds(12, 85, 100, 36);
		p1.add(OriPriceLabel);
		OriPriceLabel.setFont(new Font("굴림", Font.BOLD, 13));

		OriTextField = new JTextField();
		OriTextField.setBounds(118, 91, 109, 26);
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
		ChangePriceLabel.setBounds(12, 143, 100, 36);
		p1.add(ChangePriceLabel);
		ChangePriceLabel.setFont(new Font("굴림", Font.BOLD, 13));
		ChangePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

		ChangeTextField = new JTextField();
		ChangeTextField.setBounds(118, 149, 109, 26);
		p1.add(ChangeTextField);
		ChangeTextField.setColumns(10);

		JButton OkButton = new JButton("확인");
		OkButton.setBounds(252, 134, 83, 54);
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

	}
}
