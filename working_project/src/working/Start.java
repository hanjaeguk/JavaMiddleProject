package working;

public class Start {
	private DBcon myDBcon;
	LoginView loginView;
	MainFrame mainFrame;

	public static void main(String[] args) {
		Start start = new Start();
		
		start.loginView = new LoginView(); // 로그인창 보이기 
		start.loginView.setMain(start); // 로그인창에게 메인 클래스 보내기
	}
	
	// 로그인 된 계정 받아와서 myDBcon에 넘겨주기
	public void setDBcon(DBcon dbcon) {
		this.myDBcon=dbcon;
	}
	
	public void showMainFrame() {
		loginView.dispose(); // loginView close
		this.mainFrame = new MainFrame(myDBcon); // mainFrame show
	}
}


