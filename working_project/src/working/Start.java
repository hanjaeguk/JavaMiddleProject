package working;

/*
 * 시작 파일
 * 
 * 로그인창을 열어 로그인하고
 * 로그인 된 계정을 받아와서 메인창을 열어주는 클래스
 * 
 */

public class Start {
	private DBcon myDBcon;
	LoginView loginView;
	MainFrame mainFrame;

	public static void main(String[] args) {
		Start start = new Start();
		start.loginView = new LoginView(); // 로그인창 열기
		start.loginView.setMain(start); // 로그인창에게 메인 클래스 보내기
	}
	
	// 로그인 된 계정 받아와서 myDBcon에 넘겨주는 메서드
	public void setDBcon(DBcon dbcon) {
		this.myDBcon=dbcon;
	}
	
	// 로그인창 닫고 메인창 열어주는 메서드
	public void showMainFrame() {
		loginView.dispose(); // 로그인창 닫기
		this.mainFrame = new MainFrame(myDBcon); // 메인창 열기
	}
}