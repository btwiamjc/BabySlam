import java.awt.*;
import javax.swing.*; 

public class Main extends JFrame{
	private static Main instance;
	public final MenuPanel menuPanel;
	public final CreditsPanel creditsPanel;
	public final PlayPanel playPanel;
	public final Details detailsPanel;
	public final Client clientPanel;
	public final GameoverPanel gameOverPanel;
	public final HelpPanel helpPanel;
	private final JPanel jpanel;
	private final CardLayout layout;
	
	public Main(){
		setUndecorated(true);
        setLayout(null);
        setSize(910, 643);
        //setLocationRelativeTo(null);
        setResizable(false);

		menuPanel = new MenuPanel(this);
		menuPanel.setFocusable(false);
		menuPanel.setOpaque(false);

		detailsPanel = new Details(this);
		detailsPanel.setFocusable(false);
		detailsPanel.setOpaque(false);
		
		creditsPanel = new CreditsPanel(this);
		creditsPanel.setFocusable(false);
		creditsPanel.setOpaque(false);
		
		playPanel = new PlayPanel(this);
		playPanel.setFocusable(false);
		playPanel.setOpaque(false);
		
		helpPanel = new HelpPanel(this);
		helpPanel.setFocusable(false);
		helpPanel.setOpaque(false);

		clientPanel = new Client(this);
		clientPanel.setFocusable(false);
		clientPanel.setOpaque(false);

		gameOverPanel= new GameoverPanel(this);
		gameOverPanel.setFocusable(false);
		gameOverPanel.setOpaque(false);

		layout = new CardLayout();
		
		jpanel = new JPanel();
        jpanel.setLocation(0,0);
		jpanel.setSize(906,500);
		jpanel.setLayout(layout);
		
		jpanel.add(menuPanel, "Menu");
		jpanel.add(creditsPanel, "Credits");
		jpanel.add(detailsPanel, "Details");
		jpanel.add(clientPanel, "Client");
		jpanel.add(gameOverPanel, "Gameover");
		jpanel.add(playPanel, "Play");
		jpanel.add(helpPanel, "Help");
		add(jpanel);
		
		setVisible(true);
	}
	
	public static Main getInstance(){
        if(instance == null)
            instance = new Main();
        return instance; 
    }

    public void switchCard(String string){
		layout.show(jpanel, string);
    }
	
	public static void main(String[] args){
		Main main = new Main();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(910, 643);
		main.setResizable(false);
		//main.setLocationRelativeTo(null);
		main.setContentPane(main.menuPanel.menuLabel);
		main.setVisible(true);
		main.setLayout(null);
	}
}