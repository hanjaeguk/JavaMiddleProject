package working;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame implements ActionListener{

	private Start start;
	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField pwField;
	private JButton btnLogin;
	private JRadioButton[] rdbtn = new JRadioButton[2];
	
	String[] radioText = { "����", "����" };
	String radio = "����";

	public LoginView() {
		// layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// radio button
		ButtonGroup g = new ButtonGroup(); // ���� ��ư ���� �׷�
		for (int i = 0; i < rdbtn.length; i++) {
			rdbtn[i] = new JRadioButton(radioText[i]);
			g.add(rdbtn[i]);
			add(rdbtn[i]);

			rdbtn[i].addActionListener(this);
		}
		rdbtn[0].setSelected(true); // �ش� ��ư�� ���õ� ����
		rdbtn[0].setBounds(240, 84, 62, 23);
		rdbtn[1].setBounds(317, 84, 121, 23);		

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
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(251, 252, 97, 23);
		contentPane.add(btnLogin);
			
		setVisible(true);
	}	

	// main conn
	public void setMain(Start start) {
		this.start = start;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// radio btn result return
		String es = e.getActionCommand();
		if (es.equals(rdbtn[0].getText())) {
			this.radio = es;
		}else if (es.equals(rdbtn[1].getText())){
			this.radio = es;
		}
		
		// login btn 
		// +) id/pw���� �� �ؽ�Ʈ�ʵ� reset, Ŀ���̵�
		if (e.getSource() == btnLogin) {
			DBcon dbcon = new DBcon(); // db ����
			
			String id = idField.getText();
			String pw = new String(pwField.getPassword());
			dbcon.loginCheck(id, pw, radio);
			
			if(dbcon.getLogCnt() == 1) {
				JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
				start.setDBcon(dbcon); // �α��� �� ���� DB �ѱ��
				start.showMainFrame();
			} else {
				JOptionPane.showMessageDialog(null, "ID/PW�� Ȯ�����ּ���.");
			}			
		}
	}
	
}