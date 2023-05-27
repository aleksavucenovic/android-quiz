package ftn.quizapp.model;

public class GameStats {
    private int scoreRange;
    private int totalGamesPlayed;
    private int gamesWon;
    private int gamesLost;

    public GameStats(int scoreRange, int totalGamesPlayed, int gamesWon, int gamesLost) {
        this.scoreRange = scoreRange;
        this.totalGamesPlayed = totalGamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }

    public int getScoreRange() {
        return scoreRange;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }
}