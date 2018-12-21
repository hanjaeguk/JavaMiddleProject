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

public class ProInfoModi extends JPanel {

	/**
	 * Create the panel.
	 */
	DefaultTableModel FristTabModel, SecTabModel, ThrTabModel;
	JTable FristTab,SecTab,ThrTab;
	JScrollPane FristSc,SecSc,ThrSc;
	
	public ProInfoModi() {
		setLayout(new GridLayout(0, 1, 0, 0));
				
		JPanel panel = new JPanel();
		add(panel);
		JLabel lblNewLabel = new JLabel("상품 정보 수정");
		panel.setBounds(50, 10, 350, 27);
		panel.add(lblNewLabel);
		
		String FristTabName[] = { "품번", "기존 판매단가", "변경 판매단가" };
		Object FristTableData[][] = { { "131234", "20,000" ,"30,000"} };
		FristTabModel = new DefaultTableModel(FristTableData,FristTabName);
		FristTab = new JTable(FristTabModel);
		FristSc = new JScrollPane(FristTab);
		

	}
}
