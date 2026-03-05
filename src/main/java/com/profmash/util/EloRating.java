package com.profmash.util;

public class EloRating {

    private static final double K_FACTOR = 32.0;

    /**
     * Calculate expected score for player A against player B
     */
    public static double expectedScore(double ratingA, double ratingB) {
        return 1.0 / (1.0 + Math.pow(10.0, (ratingB - ratingA) / 400.0));
    }

    /**
     * Update Elo ratings after a match.
     * @param winnerRating  Current rating of the winner
     * @param loserRating   Current rating of the loser
     * @return double[] { newWinnerRating, newLoserRating }
     */
    public static double[] updateRatings(double winnerRating, double loserRating) {
        double expectedWinner = expectedScore(winnerRating, loserRating);
        double expectedLoser  = expectedScore(loserRating, winnerRating);

        double newWinnerRating = winnerRating + K_FACTOR * (1.0 - expectedWinner);
        double newLoserRating  = loserRating  + K_FACTOR * (0.0 - expectedLoser);

        return new double[]{ newWinnerRating, newLoserRating };
    }
}
