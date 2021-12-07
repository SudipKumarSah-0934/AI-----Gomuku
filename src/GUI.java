import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;


public class GUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private int difficulty;
	
	private JPanel boardPanel;
	private final JPanel setupPanel;
	private final JPanel selectionPanel;
	private final JPanel welcomePanel;
	private final JPanel startingPanel;
	
	
	private final JButton buttonStart;
	private final JRadioButton rComputerEasy;
	private final JRadioButton rComputerMedium;
	private final JRadioButton rComputerHard;
	private final JRadioButton rHuman;
	
	private final ButtonGroup bgDifficulty ;

	
	private final JLabel playWith;
	private final JLabel welcomeMsg;

	private JFrame frame;

	
	public GUI(int width, int height, String title) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame(title);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBackground(Color.white);
	    frame.setSize(width, height);

			
		
		selectionPanel = new JPanel();
		selectionPanel.setBounds(0, height/3, width, height/3);

		
		welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, width,  height/3);
		
		startingPanel = new JPanel();
		startingPanel.setBounds(0, 2*height/3, width,  height);

		
		welcomeMsg = new JLabel("    WELCOME TO THE GOMOKU GAME   ");
		buttonStart = new JButton("START!");
		
		rComputerEasy = new JRadioButton(" Easy AI");
		rComputerMedium = new JRadioButton(" Medium AI");
		rComputerHard = new JRadioButton(" Hard AI");
		
		rHuman = new JRadioButton(" Human vs Human");		
		
		bgDifficulty = new ButtonGroup();
		bgDifficulty.add(rComputerEasy);
		bgDifficulty.add(rComputerMedium);
		bgDifficulty.add(rComputerHard);
		bgDifficulty.add(rHuman);
		rComputerEasy.isSelected();
		rComputerMedium.isSelected();
		rComputerHard.isSelected();
			
		playWith = new JLabel(" Game Mode :  ");
		
		
		startingPanel.add(buttonStart);
		welcomePanel.add(welcomeMsg);
		
		selectionPanel.add(playWith);
		selectionPanel.add(rComputerEasy);
		selectionPanel.add(rComputerMedium);
		selectionPanel.add(rComputerHard);
		selectionPanel.add(rHuman);
		
		setupPanel = new JPanel();
		setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.Y_AXIS));
		
		setupPanel.add(welcomePanel);
		setupPanel.add(Box.createVerticalGlue());
		setupPanel.add(selectionPanel);
		setupPanel.add(Box.createVerticalGlue());
		setupPanel.add(startingPanel);
		
		
		

		frame.getContentPane().add(setupPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	public boolean playWithComputerEasy() {
		return rComputerEasy.isSelected();
	}
	
	public boolean playWithComputerMedium() {
		return rComputerMedium.isSelected();
	}
	
	public boolean playWithComputerHard() {
		return rComputerHard.isSelected();
	}
	
	public void listenGameStartButton(ActionListener listener) {
		buttonStart.addActionListener(listener);
	}
	
	
	public void attachBoard(JPanel board) {
		boardPanel = board;
	}
	
	
	public void showBoard() {
		frame.setContentPane(boardPanel);
		invalidate();
		validate();
		frame.pack();
	}
	
	
}