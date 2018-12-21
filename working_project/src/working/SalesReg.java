package working;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SalesReg extends JPanel {
	DefaultTableModel firstTabModel,secTabModel,thrTabModel;
	JTable firstTab, secTab, thrTab;
	JScrollPane firstSc,secSc,thrSc;

	public SalesReg() {
		setLayout(new GridLayout(0, 1, 0, 0));

		// 1
		JPanel p1 = new JPanel();
		add(p1);
		JLabel lblSalesReg = new JLabel("판매등록");
		lblSalesReg.setBounds(201, 5, 48, 15);
		p1.add(lblSalesReg);

		// 2
		String firstTabName[] = { "판매일자", "총판매금액" };
		Object firstData[][] = { { "2018-11-02", "20,000" } };
		firstTabModel = new DefaultTableModel(firstData, firstTabName);
		firstTab = new JTable(firstTabModel);
		firstSc = new JScrollPane(firstTab);
		add(firstSc);
		
		// 3
		String secTabName[] = { "구분","품번","색상","사이즈","판매단가","재고","수량","실판매금액","등록" };
		Object secData[][] = { { "판매","000","선택","선택","88000","3","1","88000","등록" } };
		secTabModel = new DefaultTableModel(secData, secTabName);
		secTab = new JTable(secTabModel);
		secSc = new JScrollPane(secTab);
		add(secSc);
		
		// 4
		String thrTabName[] = { "구분","품번","색상","사이즈","판매단가","수량","실판매금액","삭제" };
		Object thrData[][] = { { "판매","1","BK","S","5000","1","5000","삭제" },
				{ "반품","2","WH","M","7000","1","7000","삭제" },
				{ "판매","3","BK","L","6000","1","6000","삭제" }};
		thrTabModel = new DefaultTableModel(thrData, thrTabName);
		thrTab = new JTable(thrTabModel);
		thrSc = new JScrollPane(thrTab);
		add(thrSc);		
	}

}
