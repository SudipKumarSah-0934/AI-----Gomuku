import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Main {
	
	public static void main(String[] args) {
		
		final int width = 1000;

		final GUI gui = new GUI(width, width, "Beat Me If You Can");
		
		Board board = new Board(width, 10);
				
		final ModeHumanVsHuman humanGame = new ModeHumanVsHuman(board);
		final ModeAI game = new ModeAI(board);
			

		gui.listenGameStartButton(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				boolean computerStarts = false;
				
				boolean playingWithComputerEasy = gui.playWithComputerEasy();
				boolean playingWithComputerMedium = gui.playWithComputerMedium();
				boolean playingWithComputerHard = gui.playWithComputerHard();
				
				
				gui.attachBoard(board.getGUI());			
				gui.showBoard();
				
				if(playingWithComputerEasy) {
					int depth = 1;
					game.setAIDepth(depth);
					game.setAIStarts(computerStarts);
					game.start();

				}
				
				else if(playingWithComputerMedium) {
					int depth = 2;
					game.setAIDepth(depth);
					game.setAIStarts(computerStarts);
					game.start();

				}
				
				else if(playingWithComputerHard) {
					int depth = 4;
					game.setAIDepth(depth);
					game.setAIStarts(computerStarts);					
					game.start();

				}
				else {									

					humanGame.start();
				}
				
			}
			
		});		
	}
}
