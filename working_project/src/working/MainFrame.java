package working;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{

	private DBcon myDBcon;
	String userCode;
	
	JPanel contentPane;
	JMenu mSalesmenu, mStockmenu,mAdmMenu,mLogoutMenu;
	JMenuItem mSalesReg, mSalesStatus,mStock,mStockModify, mLogout,mNewProdReg,mProdInfoModify,mAccount;
	CardLayout card = new CardLayout();
	
	public MainFrame(DBcon dbcon) {
		setDBcon(dbcon);
		userCode = myDBcon.getUser().substring(0, 1);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				int dialogResult = JOptionPane.showConfirmDialog(contentPane, "종료 하시겠습니까?", "exit", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(dialogResult == 0) { // 예  
					myDBcon.disconn();
					System.exit(0);
				}
			}
		});
		
		setBounds(100, 100, 650, 450);

		contentPane = new JPanel();
		contentPane.setLayout(card);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);	
		
		System.out.println(userCode);

		// menu_1
		if(userCode.equals("S")) {
			mSalesmenu = new JMenu("판매관리");
			menuBar.add(mSalesmenu);
	
			mSalesReg = new JMenuItem("판매등록");
			mSalesmenu.add(mSalesReg);
			mSalesReg.addActionListener(this);
			contentPane.add("SalesReg", new SalesReg(myDBcon));
	
			mSalesStatus = new JMenuItem("판매현황");
			mSalesmenu.add(mSalesStatus);
			mSalesStatus.addActionListener(this);
			contentPane.add("SalesStatus", new SalesStatus(myDBcon));
		}

		// menu_2
		mStockmenu = new JMenu("재고관리");
		menuBar.add(mStockmenu);	

		if(userCode.equals("H")) {
			mStockModify = new JMenuItem("재고 등록/수정");
			mStockmenu.add(mStockModify);	
			mStockModify.addActionListener(this);
			contentPane.add("StockModify", new StockModify(myDBcon));
		}
			
		mStock = new JMenuItem("재고조회");
		mStockmenu.add(mStock);	
		mStock.addActionListener(this);
		contentPane.add("Stock", new StockSearch(myDBcon));
			
		
		// menu_3
		if(userCode.equals("H")) {
			mAdmMenu = new JMenu("관리자메뉴");
			menuBar.add(mAdmMenu);
	
			mNewProdReg = new JMenuItem("신상품 등록");
			mAdmMenu.add(mNewProdReg);
			mNewProdReg.addActionListener(this);
			contentPane.add("NewProdReg", new NewProReg(myDBcon));
			
			mProdInfoModify = new JMenuItem("상품단가 수정");
			mAdmMenu.add(mProdInfoModify);
			mProdInfoModify.addActionListener(this);
			contentPane.add("ProdInfoModify", new ProdInfoModify(myDBcon));
			
			mAccount = new JMenuItem("계정 생성/조회");
			mAdmMenu.add(mAccount);
			mAccount.addActionListener(this);
			contentPane.add("Account", new AccountLookupCreate(myDBcon));
		}
		
		// menu_4
		mLogoutMenu = new JMenu("로그아웃");
		menuBar.add(mLogoutMenu);

		mLogout = new JMenuItem("로그아웃");
		mLogoutMenu.add(mLogout);
		mLogout.addActionListener(this);

		add(contentPane);
		setVisible(true);
	}
	
	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() ==mSalesReg) {
			card.show(contentPane, "SalesReg");
		} else if (e.getSource() == mSalesStatus) {
			card.show(contentPane, "SalesStatus");
		} else if (e.getSource() == mStockModify) {	
			card.show(contentPane, "StockModify");
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
			int dialogResult = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?", "logout", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(dialogResult == 0) { // 예  
				// +)로그인 화면으로 이동
				myDBcon.disconn();
				dispose();
			} 			
		}
	}

}