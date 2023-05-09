/**
 * Completed 09-05-2023 by FJ Lessing
 * The following things were done to improve the code:
 * - I rewrote the class to make use of an internal class for more code safety and readability, and to get the solution as DRY as possible.
 * - I made use of absolute comparisons to reduce the amount of code and if statements.
 * - I declared helper functions to make the code more readable.
 * - I reduced the amount of comparisons done greatly to increase performance.
 */
public class TennisGame2 implements TennisGame {

    /**
     * Helper class to define players.
     */
    static class Player {
        public final String name;
        public int point;

        Player(String name) {
            this.name = name;
            this.point = 0;
        }


        /**
         * Convert a point into the string representation
         * of the score.
         * This function is meant to make the code more DRY
         *
         * @return A string representation of the score.
         */
        private String score() {
            return switch (point) {
                case 3 -> "Forty";
                case 2 -> "Thirty";
                case 1 -> "Fifteen";
                case 0 -> "Love";
                default -> throw new IllegalStateException("Unexpected value: " + point);
            };

        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private final Player player1;
    private final Player player2;


    public TennisGame2(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
    }


    /**
     * Helper function to check if a player has won.
     *
     * @return If there is a winner.
     */
    private boolean hasWinner() {
        return Math.abs(player1.point - player2.point) >= 2;
    }

    /**
     * Helper function to check if there is an advantage.
     *
     * @return If there is an advantage.
     */
    private boolean hasAdvantage() {
        return Math.abs(player1.point - player2.point) >= 1;
    }

    /**
     * Helper function to determine if there is a deuce
     *
     * @return If there is a deuce
     */
    private boolean isDeuce() {
        return player2.point == player1.point && player1.point >= 3;
    }

    /**
     * Helper function to check which player is in the lead.
     *
     * @return The player currently in the lead, or null if there is a draw.
     */
    private Player getLeader() {
        if (player1.point > player2.point) return player1;
        else if (player2.point > player1.point) return player2;
        return null;
    }

    public String getScore() {
        // Check if scores are tied.
        if (player1.point == player2.point) {
            if (isDeuce()) {
                return "Deuce";
            }
            return player1.score() + "-All";
        }

        // Check if there is a winner.
        if (player1.point >= 4 || player2.point >= 4) {
            if (hasWinner()) {
                return "Win for " + getLeader();
            }
            if (hasAdvantage()) {
                return "Advantage " + getLeader();
            }
        }

        // If none of the above, just return the scores.
        return player1.score() + '-' + player2.score();
    }

    /**
     * Return the victor's score.
     * <p>
     * I adjusted this function to use the array and break for player one.
     *
     * @param playerName The player's name.
     */
    public void wonPoint(String playerName) {
        if (playerName.equals(player1.name)) {
            player1.point++;
        } else if (playerName.equals(player2.name)) {
            player2.point++;
        } else {
            throw new IllegalStateException("There is no player with the name " + playerName + " in the current game.");
        }
    }
}