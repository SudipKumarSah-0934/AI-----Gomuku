import java.util.ArrayList;


public class MinMax {
	
	private static final int highestScore = 500000000;
	public static int moveCount = 0;
	private Board board;
	
	public MinMax(Board board) {
		this.board = board;
	}
	
	public static int getWinScore() {
		return highestScore;
	}
	
	
	

	
	public int[] findNextMove(int depth) {

		int[] move = new int[2];

		Object[] bestMove = isThereAnyWinningMove(board); 
		if(bestMove != null ) {
			System.out.println("Finished!");
			move[0] = (Integer)(bestMove[1]);
			move[1] = (Integer)(bestMove[2]);

			
		} else {

			bestMove = MiniMax(depth, board, true, -1.0, getWinScore());
			if(bestMove[1] == null) {
				move = null;
			} else {
				move[0] = (Integer)(bestMove[1]);
				move[1] = (Integer)(bestMove[2]);
			}
		}

		
		return move;
		
		
	}
	
	
	private static Object[] isThereAnyWinningMove(Board board) {
		ArrayList<int[]> allPossibleMoves = board.generateMoves();
		Object[] winningMove = new Object[3];
		

		for(int[] move : allPossibleMoves) {
			moveCount++;

			Board dummyBoard = new Board(board);

			dummyBoard.addStoneNoGUI(move[1], move[0], false);
			

			if(getScore(dummyBoard,false,false) >= highestScore) {
				winningMove[1] = move[0];
				winningMove[2] = move[1];
				System.out.println(move[0] + " " + move[1]);
				return winningMove;
			}
		}
		return null;
		
	}
	
	public static int getScore(Board board, boolean isHuman, boolean HumansTurn) {
		
		
		int[][] boardMatrix = board.getBoardMatrix();
		return horizontalChecking(boardMatrix, isHuman, HumansTurn) +
				verticalChecking(boardMatrix, isHuman, HumansTurn) +
				diagonalChecking(boardMatrix, isHuman, HumansTurn);
	}
	
	public static double doEvaluation(Board board, boolean HumansTurn) {
		
		
		double blackScore = getScore(board, true, HumansTurn);
		double whiteScore = getScore(board, false, HumansTurn);
		
		return whiteScore / blackScore;
		
	}

	private static Object[] MiniMax(int depth, Board board, boolean isComputer, double alpha, double beta) {
		
			if(depth == 0) {
			
				Object[] x = {doEvaluation(board, !isComputer), null, null};
				return x;
			}
		
			ArrayList<int[]> allPossibleMoves = board.generateMoves();
		
			if(allPossibleMoves.size() == 0) {
			
				Object[] x = {doEvaluation(board, !isComputer), null, null};
				return x;
			}
		
		Object[] bestMove = new Object[3];
		
		
		if(isComputer) {
			bestMove[0] = -1.0;

			for(int[] move : allPossibleMoves) {

				Board dummyBoard = new Board(board);

				dummyBoard.addStoneNoGUI(move[1], move[0], false);
				

				Object[] tempMove = MiniMax(depth-1, dummyBoard, !isComputer, alpha, beta);
				

				if((Double)(tempMove[0]) > alpha) {
					alpha = (Double)(tempMove[0]);
				}
				
				// Pruning with beta
				if((Double)(tempMove[0]) >= beta) {
					return tempMove;
				}
				if((Double)tempMove[0] > (Double)bestMove[0]) {
					bestMove = tempMove;
					bestMove[1] = move[0];
					bestMove[2] = move[1];
				}
			}
			
		}
		else {
			bestMove[0] = 100000000.0;

			
			for(int[] move : allPossibleMoves) {
				Board dummyBoard = new Board(board);
				dummyBoard.addStoneNoGUI(move[1], move[0], true);
				
				Object[] tempMove = MiniMax(depth-1, dummyBoard, !isComputer, alpha, beta);
				

				if(((Double)tempMove[0]) < beta) {
					beta = (Double)(tempMove[0]);
				}
				
				// Pruning with alpha
				if((Double)(tempMove[0]) <= alpha) {
					return tempMove;
				}
				if((Double)tempMove[0] < (Double)bestMove[0]) {
					bestMove = tempMove;
					bestMove[1] = move[0];
					bestMove[2] = move[1];
				}
			}
		}
		return bestMove;
	}
	
	
	
	public static int horizontalChecking(int[][] boardMatrix, boolean isHuman, boolean HumansTurn ) {
		
		int countConsecutive = 0;
		int openEnds = 0;
		int score = 0;
		
		for(int i=0; i<boardMatrix.length; i++) {
			for(int j=0; j<boardMatrix[0].length; j++) {
				
				if(boardMatrix[i][j] == (isHuman ? 2 : 1)) {
					countConsecutive++;
				}
				
				else if(boardMatrix[i][j] == 0) {
					if(countConsecutive > 0) {
						openEnds++;
						score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
						countConsecutive = 0;
						openEnds = 1;
					}
					else {
						openEnds = 1;
					}
				}
				
				else if(countConsecutive > 0) {
					score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
					countConsecutive = 0;
					openEnds = 0;
				}
				
				else {
					openEnds = 0;
				}
			
			}
		
			if(countConsecutive > 0) {
				score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
				
			}
			countConsecutive = 0;
			openEnds = 0;
			
		}
		return score;
	}
	
	public static  int verticalChecking(int[][] boardMatrix, boolean isHuman, boolean HumansTurn ) {
		
		int countConsecutive = 0;
		int openEnds = 0;
		int score = 0;
		
		for(int j=0; j<boardMatrix[0].length; j++) {
			for(int i=0; i<boardMatrix.length; i++) {
					if(boardMatrix[i][j] == (isHuman ? 2 : 1)) {
						countConsecutive++;
					}
					
					else if(boardMatrix[i][j] == 0) {
						if(countConsecutive > 0) {
							openEnds++;
							score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
							countConsecutive = 0;
							openEnds = 1;
						}
						else {
							openEnds = 1;
						}
					}
					
					else if(countConsecutive > 0) {
						score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
						countConsecutive = 0;
						openEnds = 0;
					}
					
					else {
						openEnds = 0;
					}
				
			}
			
			if(countConsecutive > 0) {
				score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
				
			}
			countConsecutive = 0;
			openEnds = 0;
			
		}
		return score;
	}
	
	public static  int diagonalChecking(int[][] boardMatrix, boolean isHuman, boolean HumansTurn ) {
		
		int countConsecutive = 0;
		int openEnds = 0;
		int score = 0;
		
		
		// Bottom-left to top-right diagonally, PLEASE DON'T DELETE
		for (int k = 0; k <= 2 * (boardMatrix.length - 1); k++) {
		    int iStart = Math.max(0, k - boardMatrix.length + 1);
		    int iEnd = Math.min(boardMatrix.length - 1, k);
		    
		    for (int i = iStart; i <= iEnd; ++i) {
		        int j = k - i;
		        
		        if(boardMatrix[i][j] == (isHuman ? 2 : 1)) {
					countConsecutive++;
				}
				
				else if(boardMatrix[i][j] == 0) {
					if(countConsecutive > 0) {
						openEnds++;
						score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
						countConsecutive = 0;
						openEnds = 1;
					}
					else {
						openEnds = 1;
					}
				}
				
				else if(countConsecutive > 0) {
					score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
					countConsecutive = 0;
					openEnds = 0;
				}
				
				else {
					openEnds = 0;
				}
			
			}
			
			if(countConsecutive > 0) {
				score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
				
			}
			countConsecutive = 0;
			openEnds = 0;
		
		}
		
		
		// Top-left to bottom-right diagonally, PLEASE DON'T DELETE
		for (int k = 1-boardMatrix.length; k < boardMatrix.length; k++) {
		    int iStart = Math.max(0, k);
		    int iEnd = Math.min(boardMatrix.length + k - 1, boardMatrix.length-1);
		    
		    for (int i = iStart; i <= iEnd; ++i) {
		        int j = i - k;
		        
		        if(boardMatrix[i][j] == (isHuman ? 2 : 1)) {
					countConsecutive++;
				}
				
				else if(boardMatrix[i][j] == 0) {
					if(countConsecutive > 0) {
						openEnds++;
						score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
						countConsecutive = 0;
						openEnds = 1;
					}
					else {
						openEnds = 1;
					}
				}
				
				else if(countConsecutive > 0) {
					score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
					countConsecutive = 0;
					openEnds = 0;
				}
				
				else {
					openEnds = 0;
				}
			
		    }
		
			if(countConsecutive > 0) {
				score += EvaluationFunction(countConsecutive, openEnds, isHuman == HumansTurn);
				
			}
			countConsecutive = 0;
			openEnds = 0;
			
		}
		return score;
	}
	
	
	public static  int EvaluationFunction(int consecutive, int openEnds, boolean currentTurn) {	

		if (openEnds == 0 && consecutive < 5)
			return 0;
		
		
		switch (consecutive) {
		case 5: 
			return highestScore;
			
		case 4:
			if(currentTurn) return 2000000;
			else {
				if(openEnds == 2) return 500000;
				else return 500;
			}

			
		case 3:
			if(openEnds == 2) {
				if(currentTurn) return 100000;
				else return 400;
			}
			else {
				if(currentTurn) return 30;
				else return 10;
			}
			
		case 2:
			if(openEnds == 2) {
				if(currentTurn) return 15;
				else return 10;
			}
			else {
				return 5;
			}
			
		case 1:				
				return 1;

		default:
			return 0;
		}
		
	}
}
