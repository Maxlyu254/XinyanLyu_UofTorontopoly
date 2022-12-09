package usecase_rolldice;

import exception.WrongCommandArgsException;
import usecase_universal.CommandPerformer;

public class RollDiceController implements CommandPerformer{

    RollDiceInputBoundary inputBoundary;
    String userInput;

    public RollDiceController(RollDiceInputBoundary inputBoundary, String userInput) {
        this.inputBoundary = inputBoundary;
        this.userInput = userInput;
    }

    public RollDiceController(){

    }

    /**
     * Returns if userInput is valid - meaning that the user comfirmed the dice roll.
     * @return If the user input is blank.
     */
    boolean isUserInputValid(){
        return userInput.isBlank();
    }

    /**
     * The command accepted by this controller should be in the following pattern: <br>
     * roll <br>
     * This command takes in no argument<br>
     * @param command the command string (user input)
     */
    @Override
    public void performCommand(String command) throws Exception {
        String[] words = command.split("\\s+");
        if (words.length != 1) {
            throw new WrongCommandArgsException(0);
        }
        RollDiceInputData inputData = new RollDiceInputData(isUserInputValid());
        inputBoundary.performAction(inputData);
    }

    // Getters and Setters


    public RollDiceInputBoundary getInputBoundary() {
        return inputBoundary;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public void setInputBoundary(RollDiceInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
}
