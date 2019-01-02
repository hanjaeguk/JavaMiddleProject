package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
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

public class AccountLookupCreate extends JPanel {
	private JTable table_1;

	private DBcon myDBcon;
	private JTextField id_Field;
	private JTextField pw_Field;
	private JTextField storeName_Field;
	private JTextField name_Field;
	private JTextField phone_Field;
	private JTextField manage_Field;
	
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
		p2.setBounds(337, 17, 183, 32);
		add(p2);
		ButtonGroup grp1 = new ButtonGroup();
		JRadioButton[] rb1 = new JRadioButton[2];
		
		rb1[0] = new JRadioButton("본사");
		rb1[1] = new JRadioButton("매장");
	
		

		for(JRadioButton jb1 : rb1) {
			grp1.add(jb1);
			p2.add(jb1);
		}
	
		p2.setVisible(true);
		
		JButton btnNewButton = new JButton("생성");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = id_Field.getText();
				String pw = pw_Field.getText();
				String storeName = storeName_Field.getText();
				String personName = name_Field.getText();
				String phone = phone_Field.getText();
				String manage = manage_Field.getText();
				String radio = null;


				if (rb1[0].getText() == "매장") {
					radio="매장";
					myDBcon.createAccount
					(id, pw, personName, phone, "2", storeName, id, manage, radio);
										
				}else if (rb1[1].getText() == "본사"){
					
				}
				
			}
		});
		btnNewButton.setBounds(518, 17, 70, 25);
		add(btnNewButton);
		
		JPanel p1 = new JPanel();
		p1.setBounds(12, 59, 576, 84);
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
		
		JLabel manageLabel = new JLabel("담당자 ID *");
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
		label.setBounds(12, 153, 99, 32);
		add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 195, 600, 205);
		add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"구분", "ID", "매장명", "담당자 이름", "전화번호"
			}
		));
		scrollPane_1.setViewportView(table_1);
		table_1.setRowHeight(30);
		
		JPanel p3 = new JPanel();
		p3.setBounds(230, 163, 278, 32);
		add(p3);
		ButtonGroup grp2 = new ButtonGroup();
		JRadioButton[] rb2 = new JRadioButton[3];
		
		rb2[0] = new JRadioButton("본사+매장");
		rb2[1] = new JRadioButton("본사");
		rb2[2] = new JRadioButton("매장");
		

		
		for(JRadioButton jb2 : rb2) {
			grp2.add(jb2);
			p3.add(jb2);
		}
		
		p3.setVisible(true);
		


		JButton button = new JButton("조회");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(518, 163, 70, 25);
		add(button);
		

		
		table_1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
}
