package model;

public class Player {

	private String name;
	private PlayerType type;

	public Player(String name,PlayerType type) {
		this.name = name;
		this.type=type;
	}

	public PlayerType getType() {
		return type;
	}

	public String getName() {
		return this.name;
	}


	public String toString() {
		return this.name;
	}
}
