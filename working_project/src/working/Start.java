package working;

public class Start {
	private DBcon myDBcon;
	LoginView loginView;
	MainFrame mainFrame;

	public static void main(String[] args) {
		Start start = new Start();
		
		start.loginView = new LoginView(); // �α���â ���̱� 
		start.loginView.setMain(start); // �α���â���� ���� Ŭ���� ������
	}
	
	// �α��� �� ���� �޾ƿͼ� myDBcon�� �Ѱ��ֱ�
	public void setDBcon(DBcon dbcon) {
		this.myDBcon=dbcon;
	}
	
	public void showMainFrame() {
		loginView.dispose(); // loginView close
		this.mainFrame = new MainFrame(myDBcon); // mainFrame show
	}
}


