package working;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SalesReg extends JPanel implements ActionListener{
	private DefaultTableModel firstTabModel, secTabModel;
	private JTable firstTab, secTab;
	private JScrollPane firstSc, secSc;
	private JLabel lab, lblDiv, lblCode, lblColor, lblSize, lblPrice, lblQty, lblSqty, lblSprice;
	private JTextField txtCode, txtPrice, txtQty, txtSqty, txtSprice;
	private JButton btnSearch, btnReg, btnDelete;
	private JComboBox<String> divCB, colorCB, sizeCB;
	
	private DBcon myDBcon;
	
	String divS[] = {"�Ǹ�","��ǰ"};
	String sizeS[] = {"S","M","L","XL"};	
	LocalDate currDate = LocalDate.now();
	
	String code = null;
	int totalPrice = 0;
	/*
	 * currDate.getYear() ��
	 * currDate.getMonthValue() ��
	 * currDate.getDayOfMonth() ��
	 * currDate.getDayOfWeek() ����
	 */
	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}
	
	public SalesReg(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// 1
		JPanel p1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) p1.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(p1);
		lab = new JLabel("�Ǹŵ��");
		lab.setFont(new Font("����", Font.PLAIN, 18));
		p1.add(lab);
		
		// 2
			//* ���Ǹűݾ� - ���Ǹűݾ� �Ѿ� ���
		String firstTabName[] = { "�Ǹ�����", "���Ǹűݾ�" };
		Object firstData[][] = { { currDate, totalPrice } };
		firstTabModel = new DefaultTableModel(firstData, firstTabName){
			public boolean isCellEditable(int row, int col) {
				return false; // ���̺� ���� ���ϰ�
			}
		};
		firstTab = new JTable(firstTabModel);
		firstTab.getTableHeader().setReorderingAllowed(false); // ���̺� �� ����
		firstSc = new JScrollPane(firstTab);
		firstSc.setPreferredSize(new Dimension(450, 80));
		add(firstSc);
		
		// 3
		JPanel panel = new JPanel();	add(panel);
		JPanel p2 = new JPanel();
		panel.add(p2);
		p2.setLayout(new GridLayout(2, 9, 0, 5));
		
			// 1��
		lblDiv = new JLabel(" ����");		p2.add(lblDiv);		
		divCB = new JComboBox<String>(divS);
		p2.add(divCB);
		
		lblCode = new JLabel(" ǰ��");	p2.add(lblCode);		
		txtCode = new JTextField();
		p2.add(txtCode);
		
		lblColor = new JLabel(" ����"); 	p2.add(lblColor);		
		colorCB = new JComboBox<String>();
		dbcon.combo_color(colorCB);
		p2.add(colorCB);
		
		lblSize = new JLabel(" ������");	p2.add(lblSize);		
		sizeCB = new JComboBox<String>(sizeS);
		p2.add(sizeCB);
		
		btnSearch = new JButton("��ȸ");
		btnSearch.addActionListener(this);
		p2.add(btnSearch);
		
			// 2��
		lblPrice = new JLabel(" �ǸŴܰ�");	p2.add(lblPrice);		
		txtPrice = new JTextField();
		txtPrice.setText("0");
		txtPrice.setEditable(false);
		p2.add(txtPrice);
		
		lblQty = new JLabel(" ���");	p2.add(lblQty);		
		txtQty = new JTextField();
		txtQty.setText("0");
		txtQty.setEditable(false);
		p2.add(txtQty);
		
		lblSqty = new JLabel(" ����");	p2.add(lblSqty);		
		txtSqty = new JTextField();
		txtSqty.setText("0");
		p2.add(txtSqty);
		
		lblSprice = new JLabel(" ���Ǹűݾ�");	p2.add(lblSprice);		
		txtSprice = new JTextField();
		txtSprice.setText("0");
		p2.add(txtSprice);
		
		btnReg = new JButton("���");
		btnReg.addActionListener(this);
		p2.add(btnReg);
				
		// 4 - Ŭ���ؼ� ����	
		String secTabName[] = { "��ȣ", "����", "ǰ��", "����", "������", "�ǸŴܰ�", "����", "���Ǹűݾ�"};
		Object secData[][] = new Object[0][8];
		secTabModel = new DefaultTableModel(secData, secTabName){
			public boolean isCellEditable(int row, int col) {
				return false; // ���̺� ���� ���ϰ�
			}
		};
		secTab = new JTable(secTabModel);
		secTab.getTableHeader().setReorderingAllowed(false); // ���̺� �� ����
		secSc = new JScrollPane(secTab);
		add(secSc);		
		dbcon.salesStatusSearch(secTab);
		totalPrice = dbcon.getTotalPrice();
		System.out.println(totalPrice);
		
		JPanel p3 = new JPanel();
		add(p3);		
		btnDelete = new JButton("����");
		btnDelete.addActionListener(this);
		p3.add(btnDelete);

		// table center align
		DefaultTableCellRenderer tCellRenderer = new DefaultTableCellRenderer();
		tCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel t1ColModel = firstTab.getColumnModel();
		TableColumnModel t2ColModel = secTab.getColumnModel();

		for (int i = 0; i < t1ColModel.getColumnCount(); i++)
			t1ColModel.getColumn(i).setCellRenderer(tCellRenderer);

		for (int i = 0; i < t2ColModel.getColumnCount(); i++)
			t2ColModel.getColumn(i).setCellRenderer(tCellRenderer);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String group = (String)divCB.getSelectedItem();
		String no = txtCode.getText();
		String color = (String)colorCB.getSelectedItem();
		String size = (String)sizeCB.getSelectedItem();
		String s_qty = txtSqty.getText();
		String s_price = txtSprice.getText();
		
		if (e.getSource() == btnSearch) {			//��ȸ
			myDBcon.pro_select(no,color,size);
			
			//�ǸŴܰ�, ��� �������� +)��ǰ�ڵ尡������
			String price = myDBcon.getPrice().toString();
			String qty = myDBcon.getQty().toString();
			
			txtPrice.setText(price);	txtQty.setText(qty);
			txtSqty.setText("1");	txtSprice.setText(price);	
		}
		
		if (e.getSource() == btnReg) {			//���
			myDBcon.clear(secTab);
			myDBcon.pro_reg(secTab,group,s_qty,s_price);
		}	
		if (e.getSource() == btnDelete) {			//����
			//������ ���̺� �� ����
			//������ ���̺� ������ �ѱ��
		}
	}
}