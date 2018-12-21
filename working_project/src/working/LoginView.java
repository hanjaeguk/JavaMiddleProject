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
	String[] radioText = { "매장", "본사" };

	public LoginView() {
		// layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// radio button
		MyItemListener itemlis = new MyItemListener(); // 아이템 감시자
		ButtonGroup g = new ButtonGroup(); // 라디오 버튼 묶을 그룹

		// 라디오 버튼 그룹에 버튼 2개 생성하여 부착
		for (int i = 0; i < rdbtn.length; i++) {
			rdbtn[i] = new JRadioButton(radioText[i]);
			g.add(rdbtn[i]);
			add(rdbtn[i]);

			rdbtn[i].addItemListener(itemlis); // 감시자 부착
		}
		
		rdbtn[0].setSelected(true); // 해당 버튼이 선택된 상태
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
				// 매장 선택
			} else {
				// 본사 선택
			}
		}
	}

	// login check
	public void loginCheck() {
		if (idField.getText().equals("a") && new String(pwField.getPassword()).equals("a")) {
			JOptionPane.showMessageDialog(null, "로그인 되었습니다.");

			start.showMainFrame();
		} else {
			JOptionPane.showMessageDialog(null, "ID/PW를 확인해주세요.");
		}
	}

	// main conn
	public void setMain(Start start) {
		this.start = start;
	}
}
