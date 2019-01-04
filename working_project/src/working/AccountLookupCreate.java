package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AccountLookupCreate extends JPanel implements ActionListener {
	private JTable table_1;

	private DBcon myDBcon;
	private JTextField id_Field;
	private JTextField pw_Field;
	private JTextField storeName_Field;
	private JTextField name_Field;
	private JTextField phone_Field;
	private JTextField manage_Field;
	private JButton btnNewButton;
	private JRadioButton[] rdbtn = new JRadioButton[2];
	private JRadioButton[] rdbtn2 = new JRadioButton[3];
	String[] radioText = { "매장", "본사" };
	String[] radioText2 = { "매장+본사", "매장", "본사" };
	String radio = "매장";
	String radio2 = "매장+본사";

	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public AccountLookupCreate(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("계정 생성");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 99, 32);
		add(lblNewLabel);

		JPanel p2 = new JPanel();
		p2.setBounds(323, 10, 183, 32);
		add(p2);

		ButtonGroup grp1 = new ButtonGroup();
		for (int i = 0; i < rdbtn.length; i++) {
			rdbtn[i] = new JRadioButton(radioText[i]);
			grp1.add(rdbtn[i]);
			p2.add(rdbtn[i]);
			rdbtn[i].addActionListener(this);
		}
		rdbtn[0].setSelected(true);

		p2.setVisible(true);

		btnNewButton = new JButton("생성");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(518, 17, 70, 25);
		add(btnNewButton);

		JPanel p1 = new JPanel();
		p1.setBounds(0, 52, 600, 74);
		add(p1);
		p1.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel idLabel = new JLabel("ID *");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(idLabel);

		JLabel pwLabel = new JLabel("PW *");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(pwLabel);

		JLabel storeNameLabel = new JLabel("매장명 *");
		storeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(storeNameLabel);

		JLabel nameLabel = new JLabel("이름 *");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(nameLabel);

		JLabel phoneLabel = new JLabel("전화번호");
		phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(phoneLabel);

		JLabel manageLabel = new JLabel("담당자 ID(매장)*");
		manageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(manageLabel);

		idLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		pwLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		storeNameLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		nameLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		phoneLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		manageLabel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

		id_Field = new JTextField();
		p1.add(id_Field);
		id_Field.setColumns(10);

		pw_Field = new JTextField();
		p1.add(pw_Field);
		pw_Field.setColumns(10);

		storeName_Field = new JTextField();
		p1.add(storeName_Field);
		storeName_Field.setColumns(10);

		name_Field = new JTextField();
		p1.add(name_Field);
		name_Field.setColumns(10);

		phone_Field = new JTextField();
		p1.add(phone_Field);
		phone_Field.setColumns(10);

		manage_Field = new JTextField();
		p1.add(manage_Field);
		manage_Field.setColumns(10);

		JLabel label = new JLabel("계정 조회");
		label.setFont(new Font("굴림", Font.BOLD, 20));
		label.setBounds(12, 136, 99, 32);
		add(label);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 178, 600, 200);
		add(scrollPane_1);

		table_1 = new JTable() {
			public boolean isCellEditable(int row, int col) {
				return false; // 테이블 수정 못하게
			}
		};
//		table_1.setEnabled(false); // 수정불가
		table_1.getTableHeader().setReorderingAllowed(false); // 이동불가
		table_1.getTableHeader().setResizingAllowed(false); // 크기조절 불가

		table_1.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "구분", "ID", "매장명", "담당자 이름", "전화번호" }));

		scrollPane_1.setViewportView(table_1);
		table_1.setRowHeight(30);

		JButton button = new JButton("조회");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDBcon.clear(table_1);
				myDBcon.searchAccount(table_1, radio2);
				System.out.println(radio2);
			}
		});
		button.setBounds(518, 143, 70, 25);
		add(button);

		JPanel p3 = new JPanel();
		p3.setBounds(228, 136, 278, 32);
		add(p3);
		ButtonGroup grp2 = new ButtonGroup();
		for (int i = 0; i < rdbtn2.length; i++) {
			rdbtn2[i] = new JRadioButton(radioText2[i]);
			grp2.add(rdbtn2[i]);
			p3.add(rdbtn2[i]);
			rdbtn2[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 계정 조회부분 라디오버튼 Action
					String es1 = e.getActionCommand();
					if (es1.equals(rdbtn2[0].getText())) {
						radio2 = es1;
					} else if (es1.equals(rdbtn2[1].getText())) {
						radio2 = es1;
					} else if (es1.equals(rdbtn2[2].getText())) {
						radio2 = es1;
					}

				}
			});
		}
		rdbtn2[0].setSelected(true);

		p3.setVisible(true);

		table_1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 계정 생성부분 라디오버튼 Action
		String es = e.getActionCommand();
		if (es.equals(rdbtn[0].getText())) {
			this.radio = es;
			manage_Field.setEditable(true);
		} else if (es.equals(rdbtn[1].getText())) {
			this.radio = es;
			manage_Field.setText(null);
			manage_Field.setEditable(false);
		}

		if (e.getSource() == btnNewButton) {
//			String id = null;
//			String pw = null;
//			String storeName = null;
//			String personName = null;
//			String phone = null;
//			String manage = null;
			
			String id = id_Field.getText();
			String pw = pw_Field.getText();
			String storeName = storeName_Field.getText();
			String personName = name_Field.getText();
			String phone = phone_Field.getText();
			String manage = manage_Field.getText();
			


			if (radio.equals("본사")) {
				if(id.isEmpty() || storeName.isEmpty() || personName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "필수사항(*)을 입력해주세요.");
				}else {
					myDBcon.createAccount(id, pw, personName, phone, storeName, id, radio);					
				}

			} else {
				if(id.isEmpty() || storeName.isEmpty() || personName.isEmpty() || manage.isEmpty()) {
					JOptionPane.showMessageDialog(null, "필수사항(*)을 입력해주세요.");
				}else {
					myDBcon.createAccount(id, pw, personName, phone, storeName, manage, radio);					
				}
			}

		}

	}

}
