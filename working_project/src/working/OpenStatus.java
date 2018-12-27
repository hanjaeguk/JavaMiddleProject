package working;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class OpenStatus extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DefaultTableModel firstTabModel, secTabModel;
	JTable firstTab, secTab;
	JScrollPane firstSc, secSc;

	public OpenStatus() {
		setBounds(100, 100, 450, 300);
		setSize(600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			// 1
			String firstTabName[] = { "판매일자", "총판매금액" };
			Object firstData[][] = { { "2018-11-02", "20,000" } };
			firstTabModel = new DefaultTableModel(firstData, firstTabName);
			contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
			firstTab = new JTable(firstTabModel);
			firstSc = new JScrollPane(firstTab);
			firstSc.setPreferredSize(new Dimension(450, 80));
			contentPanel.add(firstSc);
			
			
			// 2
			String secTabName[] = { "일자", "요일", "수량", "단가금액", "실판매금액", "누적금액(실판매)" };
			Object secData[][] = { { "2018-09-01", "토", "30", "748,600", "748,600", "748,600" },
					{ "2018-09-02", "일", "25", "668,500", "657,500", "1,406,100" } };
			secTabModel = new DefaultTableModel(secData, secTabName);
			secTab = new JTable(secTabModel);
			secTab.getTableHeader().setReorderingAllowed(false); // 열 이동 불가
			secSc = new JScrollPane(secTab);
			contentPanel.add(secSc);
			
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
		
		setModal(true);
		setVisible(true);
	}

}
