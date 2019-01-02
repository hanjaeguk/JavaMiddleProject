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
	
	String divS[] = {"판매","반품"};
	String sizeS[] = {"S","M","L","XL"};	
	LocalDate currDate = LocalDate.now();
	
	String code = null;
	int totalPrice = 0;
	/*
	 * currDate.getYear() 년
	 * currDate.getMonthValue() 월
	 * currDate.getDayOfMonth() 일
	 * currDate.getDayOfWeek() 요일
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
		lab = new JLabel("판매등록");
		lab.setFont(new Font("굴림", Font.PLAIN, 18));
		p1.add(lab);
		
		// 2
			//* 총판매금액 - 실판매금액 총액 계산
		String firstTabName[] = { "판매일자", "총판매금액" };
		Object firstData[][] = { { currDate, totalPrice } };
		firstTabModel = new DefaultTableModel(firstData, firstTabName){
			public boolean isCellEditable(int row, int col) {
				return false; // 테이블 수정 못하게
			}
		};
		firstTab = new JTable(firstTabModel);
		firstTab.getTableHeader().setReorderingAllowed(false); // 테이블 열 고정
		firstSc = new JScrollPane(firstTab);
		firstSc.setPreferredSize(new Dimension(450, 80));
		add(firstSc);
		
		// 3
		JPanel panel = new JPanel();	add(panel);
		JPanel p2 = new JPanel();
		panel.add(p2);
		p2.setLayout(new GridLayout(2, 9, 0, 5));
		
			// 1행
		lblDiv = new JLabel(" 구분");		p2.add(lblDiv);		
		divCB = new JComboBox<String>(divS);
		p2.add(divCB);
		
		lblCode = new JLabel(" 품번");	p2.add(lblCode);		
		txtCode = new JTextField();
		p2.add(txtCode);
		
		lblColor = new JLabel(" 색상"); 	p2.add(lblColor);		
		colorCB = new JComboBox<String>();
		dbcon.combo_color(colorCB);
		p2.add(colorCB);
		
		lblSize = new JLabel(" 사이즈");	p2.add(lblSize);		
		sizeCB = new JComboBox<String>(sizeS);
		p2.add(sizeCB);
		
		btnSearch = new JButton("조회");
		btnSearch.addActionListener(this);
		p2.add(btnSearch);
		
			// 2행
		lblPrice = new JLabel(" 판매단가");	p2.add(lblPrice);		
		txtPrice = new JTextField();
		txtPrice.setText("0");
		txtPrice.setEditable(false);
		p2.add(txtPrice);
		
		lblQty = new JLabel(" 재고");	p2.add(lblQty);		
		txtQty = new JTextField();
		txtQty.setText("0");
		txtQty.setEditable(false);
		p2.add(txtQty);
		
		lblSqty = new JLabel(" 수량");	p2.add(lblSqty);		
		txtSqty = new JTextField();
		txtSqty.setText("0");
		p2.add(txtSqty);
		
		lblSprice = new JLabel(" 실판매금액");	p2.add(lblSprice);		
		txtSprice = new JTextField();
		txtSprice.setText("0");
		p2.add(txtSprice);
		
		btnReg = new JButton("등록");
		btnReg.addActionListener(this);
		p2.add(btnReg);
				
		// 4 - 클릭해서 삭제	
		String secTabName[] = { "번호", "구분", "품번", "색상", "사이즈", "판매단가", "수량", "실판매금액"};
		Object secData[][] = new Object[0][8];
		secTabModel = new DefaultTableModel(secData, secTabName){
			public boolean isCellEditable(int row, int col) {
				return false; // 테이블 수정 못하게
			}
		};
		secTab = new JTable(secTabModel);
		secTab.getTableHeader().setReorderingAllowed(false); // 테이블 열 고정
		secSc = new JScrollPane(secTab);
		add(secSc);		
		dbcon.salesStatusSearch(secTab);
		totalPrice = dbcon.getTotalPrice();
		System.out.println(totalPrice);
		
		JPanel p3 = new JPanel();
		add(p3);		
		btnDelete = new JButton("삭제");
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
		
		if (e.getSource() == btnSearch) {			//조회
			myDBcon.pro_select(no,color,size);
			
			//판매단가, 재고 가져오기 +)상품코드가져오기
			String price = myDBcon.getPrice().toString();
			String qty = myDBcon.getQty().toString();
			
			txtPrice.setText(price);	txtQty.setText(qty);
			txtSqty.setText("1");	txtSprice.setText(price);	
		}
		
		if (e.getSource() == btnReg) {			//등록
			myDBcon.clear(secTab);
			myDBcon.pro_reg(secTab,group,s_qty,s_price);
		}	
		if (e.getSource() == btnDelete) {			//삭제
			//선택한 테이블 행 삭제
			//선택한 테이블 데이터 넘기기
		}
	}
}