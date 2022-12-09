package usecase_rolldice;

public class RollDiceOutputData {

    private String message;

    public RollDiceOutputData( String message){
        this.message = message;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
