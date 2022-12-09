package usecase_advance;

import exception.WrongCommandArgsException;
import usecase_universal.CommandPerformer;

public class AdvanceController implements CommandPerformer {

    private AdvanceInputBoundary inputBoundary;

    public AdvanceController(AdvanceInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public AdvanceController() {
    }

    /**
     * The command accepted by this controller should be in the following pattern: <br>
     * advance [player] [number] <br>
     * player: the name of the player to be advanced <br>
     * number: the number of tiles to be advanced <br>
     * @param command the command string (user input)
     */
    @Override
    public void performCommand(String command) throws Exception {
        String[] words = command.split("\\s+");
        if (words.length != 3) {
            throw new WrongCommandArgsException("This command only takes in two arguments");
        }
        if (! words[2].matches("\\d+")) {
            throw new WrongCommandArgsException("The second argument of the command must be a non-negative integer");
        }
        AdvanceInputData inputData = new AdvanceInputData();
        inputBoundary.performAction(inputData);
    }

    // Getters and Setters

    public AdvanceInputBoundary getInputBoundary() {
        return inputBoundary;
    }

    public void setInputBoundary(AdvanceInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
}