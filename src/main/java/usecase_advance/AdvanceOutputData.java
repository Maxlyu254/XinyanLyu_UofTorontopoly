package usecase_advance;

public class AdvanceOutputData {
    private String message;
    private int playerLocation;
    private int playerIndex;
    private int playerCash;
    private boolean updateInputMap;

    public AdvanceOutputData(String message, int playerLocation, int playerIndex,
                             int playerCash, boolean updateInputMap) {
        this.message = message;
        this.playerLocation = playerLocation;
        this.playerIndex = playerIndex;
        this.playerCash = playerCash;
        this.updateInputMap = updateInputMap;
    }

    //Getter and Setter Methods
    public String getMessage() {
        return message;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getPlayerLocation() {
        return playerLocation;
    }

    public int getPlayerCash() {
        return playerCash;
    }

    public boolean isUpdateInputMap() {
        return updateInputMap;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPlayerLocation(int playerLocation) {
        this.playerLocation = playerLocation;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setPlayerCash(int playerCash) {
        this.playerCash = playerCash;
    }

    public void setUpdateInputMap(boolean updateInputMap) {
        this.updateInputMap = updateInputMap;
    }
}

