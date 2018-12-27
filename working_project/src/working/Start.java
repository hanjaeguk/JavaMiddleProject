package working;

public class Start {
	LoginView loginView;
	MainFrame mainFrame;

	public static void main(String[] args) {
		Start start = new Start();
		
		// loginView show
		start.loginView = new LoginView(); // 로그인창 보이기 
		start.loginView.setMain(start); // 로그인창에게 메인 클래스 보내기
	}
	
	public void showMainFrame() {
		loginView.dispose(); // loginView close
		this.mainFrame = new MainFrame(); // mainFrame show
	}
}
