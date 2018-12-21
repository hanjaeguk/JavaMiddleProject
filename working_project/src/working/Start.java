package working;

public class Start {
	LoginView loginView;
	MainFrame mainFrame;

	public static void main(String[] args) {
		Start start = new Start();
		
		// loginView show
		start.loginView = new LoginView(); 
		start.loginView.setMain(start);
	}
	
	public void showMainFrame() {
		loginView.dispose(); // loginView close
		this.mainFrame = new MainFrame(); // mainFrame show
	}
}
