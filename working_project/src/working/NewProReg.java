package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

public class NewProReg extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public NewProReg() {
		setLayout(null);

		TitledBorder Tb = new TitledBorder(new LineBorder(Color.black), "신상품등록");
		Tb.setTitleColor(Color.black);
		Tb.setTitleFont(new Font("굴림", Font.BOLD, 18));

		JPanel panel = new JPanel();
		panel.setBounds(12, 22, 426, 240);
		add(panel);
		panel.setLayout(null);
		panel.setBorder(Tb);

		JLabel lblNewLabel_2 = new JLabel("품번 : ");
		lblNewLabel_2.setBounds(12, 40, 62, 29);
		panel.add(lblNewLabel_2);

		JLabel label = new JLabel("색상 : ");
		label.setBounds(12, 80, 62, 29);
		panel.add(label);

		JLabel label_1 = new JLabel("사이즈 : ");
		label_1.setBounds(12, 120, 62, 29);
		panel.add(label_1);

		JLabel label_2 = new JLabel("판매단가 : ");
		label_2.setBounds(12, 160, 62, 29);
		panel.add(label_2);

		textField = new JTextField();
		textField.setBounds(74, 40, 213, 29);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(74, 79, 213, 29);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(74, 121, 213, 29);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(74, 160, 213, 29);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("확인");
		btnNewButton.setBounds(333, 67, 70, 58);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

	}
}
