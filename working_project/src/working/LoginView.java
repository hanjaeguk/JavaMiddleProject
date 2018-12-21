package working;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JRadioButton;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField pwField;
	private Start start;
	JRadioButton[] rdbtn = new JRadioButton[2];
	String[] radioText = { "����", "����" };

	public LoginView() {
		// layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// radio button
		MyItemListener itemlis = new MyItemListener(); // ������ ������
		ButtonGroup g = new ButtonGroup(); // ���� ��ư ���� �׷�

		// ���� ��ư �׷쿡 ��ư 2�� �����Ͽ� ����
		for (int i = 0; i < rdbtn.length; i++) {
			rdbtn[i] = new JRadioButton(radioText[i]);
			g.add(rdbtn[i]);
			add(rdbtn[i]);

			rdbtn[i].addItemListener(itemlis); // ������ ����
		}
		
		rdbtn[0].setSelected(true); // �ش� ��ư�� ���õ� ����
		rdbtn[0].setBounds(240, 84, 62, 23);
		rdbtn[1].setBounds(317, 84, 121, 23);
		
		////////////////////////////////////////

		// label, field
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(228, 134, 57, 15);
		contentPane.add(lblId);

		JLabel lblPw = new JLabel("PW :");
		lblPw.setBounds(228, 191, 36, 15);
		contentPane.add(lblPw);

		idField = new JTextField();
		idField.setBounds(263, 131, 116, 21);
		contentPane.add(idField);
		idField.setColumns(10);

		pwField = new JPasswordField();
		pwField.setBounds(263, 188, 116, 21);
		contentPane.add(pwField);

		// button
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginCheck();
			}
		});
		btnNewButton.setBounds(251, 252, 97, 23);
		contentPane.add(btnNewButton);
		
		
		// show
		setVisible(true);
	}
	
	// radio listener
	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (rdbtn[0].isSelected()) {
				// ���� ����
			} else {
				// ���� ����
			}
		}
	}

	// login check
	public void loginCheck() {
		if (idField.getText().equals("a") && new String(pwField.getPassword()).equals("a")) {
			JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");

			start.showMainFrame();
		} else {
			JOptionPane.showMessageDialog(null, "ID/PW�� Ȯ�����ּ���.");
		}
	}

	// main conn
	public void setMain(Start start) {
		this.start = start;
	}
}
