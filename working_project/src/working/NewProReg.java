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
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;

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
		
		JLabel lblNewLabel = new JLabel("신상품 등록");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 10, 200, 48);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(376, 63, 62, 23);
		add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 96, 426, 194);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("품번 : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(0, 1, 213, 48);
		lblNewLabel_2.setBackground(Color.BLUE);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(213, 10, 213, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("색상 : ");
		lblNewLabel_3.setBounds(0, 49, 213, 48);
		panel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(213, 58, 213, 29);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("사이즈 : ");
		lblNewLabel_1.setBounds(0, 97, 213, 48);
		panel.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(213, 107, 213, 29);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("판매단가 :");
		lblNewLabel_4.setBounds(0, 145, 213, 48);
		panel.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(213, 155, 213, 29);
		panel.add(textField_3);
		textField_3.setColumns(10);

	}
}
