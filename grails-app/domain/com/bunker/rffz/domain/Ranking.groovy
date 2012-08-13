package com.bunker.rffz.domain

class Ranking {

    RankingType type
	
	int rank
	String team
	
	int points
	int gamesPlayed
	int gamesWon
	int gamesTied
	int gamesLost
	int goalsScored
	int goalsReceived
			
	static constraints = {
		points(nullable: true)
		gamesPlayed(nullable: true)
		gamesWon(nullable: true)
		gamesTied(nullable: true)
		gamesLost(nullable: true)
		goalsScored(nullable: true)
		goalsReceived(nullable: true)
	}
	
	static mapping = {
		version false
	}

	@Override
	public String toString() {
		return "Ranking [type=" + type + ", rank=" + rank + ", team=" + team + ", points=" + points + ", gamesPlayed=" + gamesPlayed + ", gamesWon=" + gamesWon + ", gamesTied=" + gamesTied + ", gamesLost=" + gamesLost + ", goalsScored=" + goalsScored + ", goalsReceived=" + goalsReceived + "]";
	}
	
}
