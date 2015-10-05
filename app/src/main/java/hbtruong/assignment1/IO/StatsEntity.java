package hbtruong.assignment1.IO;

import java.util.ArrayList;

/**
 * Created by huy on 24/09/15.
 *
 * hbtruong-reflex is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * hbtruong-reflex is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with hbtruong-reflex.  If not, see <http://www.gnu.org/licenses/>.
 */
public class StatsEntity {

    private ArrayList<Integer> onePlayers = new ArrayList<>();
    private TwoPlayers twoPlayers = new TwoPlayers();
    private ThreePlayers threePlayers = new ThreePlayers();
    private FourPlayers fourPlayers = new FourPlayers();

    public ArrayList<Integer> getOnePlayers() {
        return onePlayers;
    }

    public void setOnePlayers(ArrayList<Integer> onePlayers) {
        this.onePlayers = onePlayers;
    }

    public TwoPlayers getTwoPlayers() {
        return twoPlayers;
    }

    public void setTwoPlayers(TwoPlayers twoPlayers) {
        this.twoPlayers = twoPlayers;
    }

    public ThreePlayers getThreePlayers() {
        return threePlayers;
    }

    public void setThreePlayers(ThreePlayers threePlayers) {
        this.threePlayers = threePlayers;
    }

    public FourPlayers getFourPlayers() {
        return fourPlayers;
    }

    public void setFourPlayers(FourPlayers fourPlayers) {
        this.fourPlayers = fourPlayers;
    }

    // static nested class for JsonObject
    public class TwoPlayers {
        private int player1 = 0;
        private int player2 = 0;

        public int getPlayer1() {
            return player1;
        }

        public void setPlayer1(int player1) {
            this.player1 = player1;
        }

        public int getPlayer2() {
            return player2;
        }

        public void setPlayer2(int player2) {
            this.player2 = player2;
        }

        @Override
        public String toString() {
            return "Player 1: " + player1 + " - Player 2: " + player2;
        }
    }

    public class ThreePlayers {
        private int player1 = 0;
        private int player2 = 0;
        private int player3 = 0;

        public int getPlayer1() {
            return player1;
        }

        public void setPlayer1(int player1) {
            this.player1 = player1;
        }

        public int getPlayer2() {
            return player2;
        }

        public void setPlayer2(int player2) {
            this.player2 = player2;
        }

        public int getPlayer3() {
            return player3;
        }

        public void setPlayer3(int player3) {
            this.player3 = player3;
        }

        @Override
        public String toString() {
            return "Player 1: " + player1 + " - Player 2: " + player2 + " - Player 3: " + player3;
        }
    }

    public class FourPlayers {
        private int player1 = 0;
        private int player2 = 0;
        private int player3 = 0;
        private int player4 = 0;

        public int getPlayer1() {
            return player1;
        }

        public void setPlayer1(int player1) {
            this.player1 = player1;
        }

        public int getPlayer2() {
            return player2;
        }

        public void setPlayer2(int player2) {
            this.player2 = player2;
        }

        public int getPlayer3() {
            return player3;
        }

        public void setPlayer3(int player3) {
            this.player3 = player3;
        }

        public int getPlayer4() {
            return player4;
        }

        public void setPlayer4(int player4) {
            this.player4 = player4;
        }

        @Override
        public String toString() {
            return "Player 1: " + player1 + " - Player 2: " + player2 + " - Player 3: " + player3 + " - Player 4: " + player4;
        }
    }
}
