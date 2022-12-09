package usecase_advance;

import java.util.Objects;

public class AdvanceInputData {
    public static final String PLAYER_UNSPECIFIED = null;
    private String playername;
    private int diceSum;

    public AdvanceInputData(String playername, int diceSum) {
        this.playername = playername;
        this.diceSum = diceSum;
    }

    public AdvanceInputData(int diceSum) {
        this.playername = PLAYER_UNSPECIFIED;
        this.diceSum = diceSum;
    }

    public AdvanceInputData() {

    }

    //getters

    public int getDiceSum() {
        return diceSum;
    }

    public String getPlayername() {
        return playername;
    }

    //setters

    public void setDiceSum(int diceSum) {
        this.diceSum = diceSum;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    //other getters

    public boolean isPlayerUnspecified() {
        return Objects.equals(playername, PLAYER_UNSPECIFIED);
    }
}

