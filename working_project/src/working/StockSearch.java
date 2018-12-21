package working;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class StockSearch extends JPanel {
	JLabel lab;
	DefaultTableModel firstTabModel, secTabModel;
	JTable firstTab, secTab;
	JScrollPane firstSc, secSc;
	private JRadioButton[] rdbtn = new JRadioButton[2];
	private JButton btnSearch;
	String[] radioText = { "매장별", "사이즈별" };
	private JRadioButton rdbtnH;
	private JRadioButton rdbtnJj;
	private JRadioButton rdbtnKk;

	public StockSearch() {
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
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		add(p2);
		// radio*
		MyItemListener itemlis = new MyItemListener(); // 아이템 감시자
		ButtonGroup g = new ButtonGroup(); // 라디오 버튼 묶을 그룹

		for (int i = 0; i < rdbtn.length; i++) {
			rdbtn[i] = new JRadioButton(radioText[i]);
			g.add(rdbtn[i]);
			p2.add(rdbtn[i]); // 라디오 버튼 그룹에 버튼 2개 생성하여 부착

			rdbtn[i].addItemListener(itemlis); // 감시자 부착
		}
		rdbtn[0].setSelected(true); // 해당 버튼이 선택된 상태

		// btn
		btnSearch = new JButton("조회");
		p2.add(btnSearch);

		// 3
		String firstTabName[] = { "품 번", "판매단가" };
		Object firstData[][] = { { "181001", "59,000" } };
		firstTabModel = new DefaultTableModel(firstData, firstTabName);
		firstTab = new JTable(firstTabModel);
		firstSc = new JScrollPane(firstTab);
		firstSc.setPreferredSize(new Dimension(450, 100));
		add(firstSc);

		// 4
		String secTabName[] = { "색상", "사이즈", "매장코드", "매장명", "전화번호", "재고" };
		Object secData[][] = { { "BK", "S", "H0001", "본사창고", "031-777-1111", "5" },
				{ "BK", "M", "H0001", "본사창고", "031-777-1111", "4" },
				{ "BK", "S", "S3210", "뉴코아광명", "010-8888-8888", "3" } };
		secTabModel = new DefaultTableModel(secData, secTabName);
		secTab = new JTable(secTabModel);
		secSc = new JScrollPane(secTab);
		add(secSc);

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

	// radio listener
	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (rdbtn[0].isSelected()) {
				// 매장별
			} else {
				// 사이즈별
			}
		}
	}
}
