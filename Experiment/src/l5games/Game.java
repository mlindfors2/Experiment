package l5games;

class Game {
	private String team1;
	private String team2;
	private int goal1;
	private int goal2;
	
	public Game(String team1, String team2) {
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public void increaseGoal1() {
		this.goal1++;
	}
	
	public void increaseGoal2() {
		this.goal2++;
	}

	public String getTeam1() {
		return team1;
	}

	public String getTeam2() {
		return team2;
	}

	public int getGoal1() {
		return goal1;
	}

	public int getGoal2() {
		return goal2;
	}
	
	public boolean equals(Object obj) {
		boolean res = false;
		if(obj instanceof Game) {
			Game game = (Game)obj;
			res = team1.equals(game.getTeam1()) && team2.equals(game.getTeam2());
		}
		return res;
	}
	
	public String toString() {
		return String.format("%s-%s%4d-%1d",team1,team2,goal1,goal2);
	}
}