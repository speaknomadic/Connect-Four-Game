package model;

import exceptions.ColumnIsFullException;
import exceptions.InvalidColumnException;

public class Game {

	public static final int WIN_NUMBER = 4;

	private Disc[][] board;
	private Player player1;
	private Player player2;
	private final int numberOfRows = 6;
	private final int numberOfColumns = 7;
	private boolean turn;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		randomizePlayers();
		board = new Disc[numberOfRows][numberOfColumns];
	}

	public boolean checkIfGameEnded() {
		for (int i = 0; i < numberOfColumns; i++) {
			if (board[0][i] == null) {
				return false;
			}
		}
		return true;
	}

	public void randomizePlayers() {
		double randomNumber = Math.random();
		Player tempPlayer;
		if (randomNumber >= 0 && randomNumber < 0.5) {
			tempPlayer = player1;
			player1 = player2;
			player2 = tempPlayer;
		}
	}

	public void endTurn() {
		turn = !turn;
	}

	public Player getTurnPlayer() {
		if (turn) {
			return player2;
		} else {
			return player1;
		}
	}

	public boolean insertDisc(int columnNumber) throws ColumnIsFullException,InvalidColumnException {
		Disc disc;
		if(getTurnPlayer().getType()==PlayerType.PLAYERONE) {
			 disc= new PlayerOneDisc(getTurnPlayer());	
		}else {
			 disc= new PlayerTwoDisc(getTurnPlayer());	

		}
		if(columnNumber>=numberOfColumns || columnNumber<0) {
			throw new InvalidColumnException ();
		}
		if ( board[0][columnNumber] != null) {
			throw new ColumnIsFullException();
		}
		for (int i = 0; i < numberOfRows - 1; i++) {
			
			
			if (board[i + 1][columnNumber] != null) {
				board[i][columnNumber] = disc;
				return checkWin(i, columnNumber, getTurnPlayer());

			}
		}
		board[numberOfRows - 1][columnNumber] = disc;
		return checkWin(numberOfRows - 1, columnNumber, getTurnPlayer());

	}

	public boolean checkWin(int rowNumber, int columnNumber, Player turnPlayer) {
		if (checkDown(rowNumber, columnNumber, turnPlayer))
			return true;
		if (checkRight(rowNumber, columnNumber, turnPlayer))
			return true;
		if (checkLeft(rowNumber, columnNumber, turnPlayer))
			return true;
		if (checkDownRight(rowNumber, columnNumber, turnPlayer))
			return true;
		if (checkDownLeft(rowNumber, columnNumber, turnPlayer))
			return true;
		if (checkUpright(rowNumber, columnNumber, turnPlayer))
			return true;
		if (checkUpleft(rowNumber, columnNumber, turnPlayer))
			return true;

		return false;
	}

	private boolean checkUpleft(int rowNumber, int columnNumber, Player turnPlayer) {
		int count=0;

		for (int i = rowNumber, j = columnNumber; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == null) {
				break;
			}
			if (board[i][j].getPlayer() == turnPlayer) {
				count++;
				if (count == WIN_NUMBER) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	private boolean checkUpright(int rowNumber, int columnNumber, Player turnPlayer) {
		int count=  0;

		for (int i = rowNumber, j = columnNumber; i >= 0 && j < numberOfColumns; i--, j++) {
			if (board[i][j] == null) {
				break;
			}
			if (board[i][j].getPlayer() == turnPlayer) {
				count++;
				if (count == WIN_NUMBER) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	private boolean checkDownLeft(int rowNumber, int columnNumber, Player turnPlayer) {
		int count = 0;

		for (int i = rowNumber, j = columnNumber; i < numberOfRows && j >= 0; i++, j--) {
			if (board[i][j] == null) {
				break;
			}
			if (board[i][j].getPlayer() == turnPlayer) {
				count++;
				if (count == WIN_NUMBER) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	private boolean checkDownRight(int rowNumber, int columnNumber, Player turnPlayer) {
		int count;
		count = 0;

		for (int i = rowNumber, j = columnNumber; i < numberOfRows && j < numberOfColumns; i++, j++) {
			if (board[i][j] == null) {
				break;
			}
			if (board[i][j].getPlayer() == turnPlayer) {
				count++;
				if (count == WIN_NUMBER) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	private boolean checkLeft(int rowNumber, int columnNumber, Player turnPlayer) {
		int count = 0;

		for (int i = columnNumber; i >= 0; i--) {
			if (board[rowNumber][i] == null) {
				break;
			}
			if (board[rowNumber][i].getPlayer() == turnPlayer) {
				count++;
				if (count == WIN_NUMBER) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	private boolean checkRight(int rowNumber, int columnNumber, Player turnPlayer) {
		int count = 0;

		for (int i = columnNumber; i < numberOfColumns; i++) {
			if (board[rowNumber][i] == null) {
				break;
			}
			if (board[rowNumber][i].getPlayer() == turnPlayer) {
				count++;
				if (count == WIN_NUMBER) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	private boolean checkDown(int rowNumber, int columnNumber, Player turnPlayer) {
		int count = 0;

		for (int i = rowNumber; i < numberOfRows; i++) {
			if (board[i][columnNumber].getPlayer() == turnPlayer) {
				count++;
				if (count == WIN_NUMBER) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	public Disc[][] getBoard() {
		return board;
	}


}
