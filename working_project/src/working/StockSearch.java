package working;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class StockSearch extends JPanel implements ActionListener {
	private DefaultTableModel firstTabModel;
	private JTable firstTab;
	private JScrollPane firstSc;
	private JButton btnSearch;
	private JLabel lab, lblCode, lblPrice, lblPriceNum;
	private JTextField txtCode;
	
	private DBcon myDBcon;
	
	String price = "0";
	
	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}
	
	public StockSearch(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// 1
		JPanel p1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) p1.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(p1);
		lab = new JLabel("재고조회");
		p1.add(lab);
		lab.setFont(new Font("굴림", Font.PLAIN, 18));

		// 2
		JPanel p2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) p2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(p2);

		lblCode = new JLabel("품번");
		p2.add(lblCode);
		txtCode = new JTextField();
		txtCode.setColumns(10);
		p2.add(txtCode);

		btnSearch = new JButton("조회");
		btnSearch.addActionListener(this);
		p2.add(btnSearch);
		
		lblPrice = new JLabel(" 판매단가 : ");
		p2.add(lblPrice);		
		lblPriceNum = new JLabel(price);
		p2.add(lblPriceNum);

		// 3
		JPanel p3 = new JPanel();
		FlowLayout fl_p3 = (FlowLayout) p3.getLayout();
		fl_p3.setAlignment(FlowLayout.RIGHT);
		add(p3);

		// 4
		String firstTabName[] = { "색상", "사이즈", "매장코드", "매장명", "전화번호", "재고" };
		Object firstData[][] = new Object[0][6];
		firstTabModel = new DefaultTableModel(firstData, firstTabName){
			public boolean isCellEditable(int row, int col) {
				return false; // 테이블 수정 못하게
			}
		};
		firstTab = new JTable(firstTabModel);
		firstSc = new JScrollPane(firstTab);
		add(firstSc);

		// table center align
		DefaultTableCellRenderer tCellRenderer = new DefaultTableCellRenderer();
		tCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel t1ColModel = firstTab.getColumnModel();

		for (int i = 0; i < t1ColModel.getColumnCount(); i++)
			t1ColModel.getColumn(i).setCellRenderer(tCellRenderer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			String no = txtCode.getText();
			
			myDBcon.clear(firstTab);
			myDBcon.stock_select(firstTab,no);
			price = myDBcon.getPrice().toString();
			lblPriceNum.setText(price);
		}
	}
}