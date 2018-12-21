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
		mSalesmenu = new JMenu("판매관리");
		menuBar.add(mSalesmenu);

		mSalesReg = new JMenuItem("판매등록");
		mSalesmenu.add(mSalesReg);

		mSalesStatus = new JMenuItem("판매현황");
		mSalesmenu.add(mSalesStatus);

		// menu_2
		mStockmenu = new JMenu("재고관리");
		menuBar.add(mStockmenu);

		mStock = new JMenuItem("재고조회");
		mStockmenu.add(mStock);

		// menu_3
		mAdmMenu = new JMenu("관리자메뉴");
		menuBar.add(mAdmMenu);

		mNewProdReg = new JMenuItem("신상품 등록");
		mAdmMenu.add(mNewProdReg);

		mProdInfoModify = new JMenuItem("상품정보 수정");
		mAdmMenu.add(mProdInfoModify);

		mAccount = new JMenuItem("계정 조회/생성");
		mAdmMenu.add(mAccount);

		// menu_4
		mLogoutMenu = new JMenu("로그아웃");
		menuBar.add(mLogoutMenu);

		mLogout = new JMenuItem("로그아웃");
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
		getContentPane().add(contentPane); // ContentPane 만을 child로 가질수 있음. getContentPane()먼저 해줘야함
		add(contentPane);
		contentPane.add("SalesReg", new SalesReg()); //판매등록
		contentPane.add("SalesStatus", new SalesStatus()); //판매현황
		contentPane.add("Stock", new StockSearch()); //재고조회
		contentPane.add("NewProdReg", new NewProReg()); //신상품등록
		contentPane.add("ProdInfoModify", new ProdInfoModify()); // 상품정보 수정 
		contentPane.add("Account", new AccountLookupCreate()); // 계정조희 및 생성

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
			String[] option = { "예", "아니오" };
			result = JOptionPane.showOptionDialog(this, "로그아웃 하시겠습니까?", "logout", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, option, option[1]);

			if (result == 0) {
				// 예
				// 로그인 화면으로 이동
				dispose();
			} else {
				// 아니오
			}
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
