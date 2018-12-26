package working;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class NewProReg extends JPanel {
	private JTextField p_no_field;
	private JTextField color_field;
	private JTextField size_field;
	private JTextField price_field;

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

		JLabel p_no_label = new JLabel("품번 : ");
		p_no_label.setBounds(12, 40, 62, 29);
		panel.add(p_no_label);

		JLabel col_label = new JLabel("색상 : ");
		col_label.setBounds(12, 80, 62, 29);
		panel.add(col_label);

		JLabel size_label = new JLabel("사이즈 : ");
		size_label.setBounds(12, 120, 62, 29);
		panel.add(size_label);

		JLabel price_label = new JLabel("판매단가 : ");
		price_label.setBounds(12, 160, 62, 29);
		panel.add(price_label);

		p_no_field = new JTextField(); // 품번
		p_no_field.setBounds(74, 40, 213, 29);
		panel.add(p_no_field);
		p_no_field.setColumns(10);

		color_field = new JTextField(); // 색상
		color_field.setBounds(74, 79, 213, 29);
		panel.add(color_field);
		color_field.setColumns(10);

		size_field = new JTextField(); // 사이즈
		size_field.setBounds(74, 121, 213, 29);
		panel.add(size_field);
		size_field.setColumns(10);

		price_field = new JTextField(); // 판매단가
		price_field.setBounds(74, 160, 213, 29);
		panel.add(price_field);
		price_field.setColumns(10);

		JButton OkBtn = new JButton("확인");
		OkBtn.setBounds(333, 67, 70, 58);
		panel.add(OkBtn);

		OkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {					
					String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
					String ID = "ora_user3";
					String PW = "han3";
					String sql = null;		

					Connection conn = null;
					Statement stmt = null;
					
					// DB 드라이버 로딩
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} // 1try end

					// DB USER 접속
					try {
						conn = DriverManager.getConnection(URL, ID, PW);
						stmt = conn.createStatement();
						
//							String p_no = p_no_field.getText();
//							String p_price = price_field.getText();
//							int no = Integer.parseInt(p_no);
//							int price = Integer.parseInt(p_price);
							
							sql =	"INSERT INTO PRODUCT VALUES(UPPER('"+
									p_no_field.getText()+
									color_field.getText()+
									size_field.getText()+"'),"+
									p_no_field.getText()+",UPPER('"+
									color_field.getText()+"'),UPPER('"+
									size_field.getText()+"'),"+
									price_field.getText()+")";
							
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "입력되었습니다.");			

					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "이미 있는 품번입니다.");
					} finally {
						try {
							stmt.close();
							conn.close();
						}catch(Exception e){
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "형식에 맞지 않습니다.");
				}
			}

		});

	}
}

