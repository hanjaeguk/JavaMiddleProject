package working;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import working.*;

public class MainFrame extends JFrame implements ActionListener {

	JPanel contentPane;
	JMenu mSalesmenu, mStockmenu, mAdmMenu, mLogoutMenu;
	JMenuItem mSalesReg, mSalesStatus, mStock, mLogout, mNewProdReg, mProdInfoModify, mAccount;
	CardLayout card = new CardLayout();

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);

		contentPane = new JPanel();
		contentPane.setLayout(card);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// menu_1
		mSalesmenu = new JMenu("�ǸŰ���");
		menuBar.add(mSalesmenu);

		mSalesReg = new JMenuItem("�Ǹŵ��");
		mSalesmenu.add(mSalesReg);

		mSalesStatus = new JMenuItem("�Ǹ���Ȳ");
		mSalesmenu.add(mSalesStatus);

		// menu_2
		mStockmenu = new JMenu("������");
		menuBar.add(mStockmenu);

		mStock = new JMenuItem("�����ȸ");
		mStockmenu.add(mStock);

		// menu_3
		mAdmMenu = new JMenu("�����ڸ޴�");
		menuBar.add(mAdmMenu);

		mNewProdReg = new JMenuItem("�Ż�ǰ ���");
		mAdmMenu.add(mNewProdReg);

		mProdInfoModify = new JMenuItem("��ǰ���� ����");
		mAdmMenu.add(mProdInfoModify);

		mAccount = new JMenuItem("���� ��ȸ/����");
		mAdmMenu.add(mAccount);

		// menu_4
		mLogoutMenu = new JMenu("�α׾ƿ�");
		menuBar.add(mLogoutMenu);

		mLogout = new JMenuItem("�α׾ƿ�");
		mLogoutMenu.add(mLogout);

		
		
		mSalesReg.addActionListener(this);
		mSalesStatus.addActionListener(this);
		mStock.addActionListener(this);
		mNewProdReg.addActionListener(this);
		mProdInfoModify.addActionListener(this);
		mAccount.addActionListener(this);
		mLogout.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getContentPane().add(contentPane); // ContentPane ���� child�� ������ ����. getContentPane()���� �������
		add(contentPane);
		contentPane.add("SalesReg", new SalesReg()); //�Ǹŵ��
		contentPane.add("SalesStatus", new SalesStatus()); //�Ǹ���Ȳ
		contentPane.add("Stock", new StockSearch()); //�����ȸ
		contentPane.add("NewProdReg", new NewProReg()); //�Ż�ǰ���
		contentPane.add("ProdInfoModify", new ProdInfoModify()); // ��ǰ���� ���� 
		contentPane.add("Account", new AccountLookupCreate()); // �������� �� ����

		if (e.getSource() ==mSalesReg) {
			card.show(contentPane, "SalesReg");
		} else if (e.getSource() == mSalesStatus) {
			card.show(contentPane, "SalesStatus");
		} else if (e.getSource() == mStock) {
			card.show(contentPane, "Stock");
		} else if (e.getSource() == mNewProdReg) {
			card.show(contentPane, "NewProdReg");
		} else if(e.getSource() == mProdInfoModify) {
			card.show(contentPane, "ProdInfoModify");
		} else if (e.getSource() == mAccount) {
			card.show(contentPane, "Account");
		}
		else if (e.getSource() == mLogout) {
			int result;
			String[] option = { "��", "�ƴϿ�" };
			result = JOptionPane.showOptionDialog(this, "�α׾ƿ� �Ͻðڽ��ϱ�?", "logout", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);

			if (result == 0) {
				// ��
				// �α��� ȭ������ �̵�
				dispose();
			} else {
				// �ƴϿ�
			}
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
