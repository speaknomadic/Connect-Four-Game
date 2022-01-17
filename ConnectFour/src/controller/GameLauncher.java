package controller;

import exceptions.*;
import model.Game;
import view.GameView;

public class GameLauncher {
	private Game game;
	GameView gameView;

	public GameLauncher() {
		gameView = new GameView();
		game=gameView.makeGame();
		boolean didGameEnd = false;
		while (true) {
			if (getGame().checkIfGameEnded()) {
				didGameEnd = true;
				break;
			}
			gameView.printBoard(getGame());
			int columnToInsertIn=-1;
			try {
				columnToInsertIn = gameView.playTurn(game.getTurnPlayer().getName());
			} catch (NumberFormatException e1) {
				System.out.println("PLEASE ENTER A NUMBER");
			}
			try {
				if (getGame().insertDisc(columnToInsertIn)) {
					break;
				}
				getGame().endTurn();
			} catch (ColumnIsFullException e) {
				System.out.println("THIS COLUMN IS FULL");
			}
			catch(InvalidColumnException e2) {
				System.out.println("PLEASE SPECIFY A CORRECT COLUMN TO INSERT IN");
			}

		}
		if (didGameEnd) {
			System.out.println("GAME ENDED, THERE IS NO WINNER");
		} else {
			gameView.printBoard(getGame());
			System.out.println("************************************************************");
			System.out.println("************************************************************");
			System.out.println(getGame().getTurnPlayer() + " HAS WON!!!!!!!!!!");
			System.out.println("************************************************************");
			System.out.println("************************************************************");
		}

	}

	public static void main(String[] args) {
		new GameLauncher();
	}

	public Game getGame() {
		return game;
	}


}
