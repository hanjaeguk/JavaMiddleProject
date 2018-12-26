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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AccountLookupCreate extends JPanel {
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public AccountLookupCreate() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("���� ����");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 99, 32);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(518, 17, 70, 25);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 52, 600, 58);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID *", "PW *", "����� *", "�̸�", "��ȭ��ȣ", "����� ID(���常)"
			}
		));
		
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		
		JPanel p1 = new JPanel();
		p1.setBounds(337, 17, 183, 32);
		add(p1);
		ButtonGroup grp1 = new ButtonGroup();
		JRadioButton[] rb1 = new JRadioButton[2];
		
		rb1[0] = new JRadioButton("����");
		rb1[1] = new JRadioButton("����");
		

		for(JRadioButton jb1 : rb1) {
			grp1.add(jb1);
			p1.add(jb1);
		}
	
		p1.setVisible(true);
		
		
		JLabel label = new JLabel("���� ��ȸ");
		label.setFont(new Font("����", Font.BOLD, 20));
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
				"����", "ID", "�����", "����� �̸�", "��ȭ��ȣ"
			}
		));
		scrollPane_1.setViewportView(table_1);
		table_1.setRowHeight(30);
		
		JPanel p2 = new JPanel();
		p2.setBounds(230, 163, 278, 32);
		add(p2);
		ButtonGroup grp2 = new ButtonGroup(); // �ϳ��� ���õǰ� �ϱ����ؼ�, ���� �׷쿡���� �ϳ��� �����̵ȴ�.
		JRadioButton[] rb2 = new JRadioButton[3];
		
		rb2[0] = new JRadioButton("����+����");
		rb2[1] = new JRadioButton("����");
		rb2[2] = new JRadioButton("����");
		

		
		for(JRadioButton jb2 : rb2) {
			grp2.add(jb2);
			p2.add(jb2);
		}
		
		p2.setVisible(true);
		


		JButton button = new JButton("��ȸ");
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
