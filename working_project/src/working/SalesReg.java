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
		JLabel lblSalesReg = new JLabel("�Ǹŵ��");
		lblSalesReg.setBounds(201, 5, 48, 15);
		p1.add(lblSalesReg);

		// 2
		String firstTabName[] = { "�Ǹ�����", "���Ǹűݾ�" };
		Object firstData[][] = { { "2018-11-02", "20,000" } };
		firstTabModel = new DefaultTableModel(firstData, firstTabName);
		firstTab = new JTable(firstTabModel);
		firstSc = new JScrollPane(firstTab);
		add(firstSc);
		
		// 3
		String secTabName[] = { "����","ǰ��","����","������","�ǸŴܰ�","���","����","���Ǹűݾ�","���" };
		Object secData[][] = { { "�Ǹ�","000","����","����","88000","3","1","88000","���" } };
		secTabModel = new DefaultTableModel(secData, secTabName);
		secTab = new JTable(secTabModel);
		secSc = new JScrollPane(secTab);
		add(secSc);
		
		// 4
		String thrTabName[] = { "����","ǰ��","����","������","�ǸŴܰ�","����","���Ǹűݾ�","����" };
		Object thrData[][] = { { "�Ǹ�","1","BK","S","5000","1","5000","����" },
				{ "��ǰ","2","WH","M","7000","1","7000","����" },
				{ "�Ǹ�","3","BK","L","6000","1","6000","����" }};
		thrTabModel = new DefaultTableModel(thrData, thrTabName);
		thrTab = new JTable(thrTabModel);
		thrSc = new JScrollPane(thrTab);
		add(thrSc);		
	}

}
