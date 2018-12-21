package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProInfoModi extends JPanel {
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */

	
	public ProInfoModi() {
		setLayout(null);
		
		JLabel TitleLabel = new JLabel("»óÇ°Á¤º¸ ¼öÁ¤");
		TitleLabel.setFont(new Font("±¼¸²", Font.BOLD, 20));
		TitleLabel.setBounds(12, 10, 201, 26);
		add(TitleLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uB2E8\uAC00 \uC218\uC815");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 17));
		lblNewLabel_1.setBounds(12, 46, 102, 26);
		add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 82, 329, 36);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\uD488\uBC88 :");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 5, 164, 26);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(164, 5, 164, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(386, 150, 57, 23);
		add(btnNewButton);
		
		JButton button = new JButton("\uC870\uD68C");
		button.setBounds(347, 93, 57, 23);
		add(button);
		
		JLabel label_1 = new JLabel("\uB2E8\uAC00 \uC218\uC815");
		label_1.setFont(new Font("±¼¸²", Font.BOLD, 17));
		label_1.setBounds(12, 171, 102, 26);
		add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 115, 374, 46);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\uAE30\uC874 \uD310\uB9E4\uB2E8\uAC00 :");
		label.setFont(new Font("±¼¸²", Font.BOLD, 12));
		label.setBounds(0, 5, 100, 36);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);
		
		JLabel lblNewLabel_3 = new JLabel("\uBCC0\uACBD \uD310\uB9E4\uB2E8\uAC00 :");
		lblNewLabel_3.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblNewLabel_3.setBounds(184, 5, 100, 36);
		panel_1.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(98, 10, 83, 26);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(285, 10, 83, 26);
		panel_1.add(textField_4);
		textField_4.setColumns(10);

		

	}
}
