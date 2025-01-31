package usecase_confirmbuyproperty;

import usecase_universal.CommandPerformer;

public class ConfirmBuyPropertyController implements CommandPerformer {

    private ConfirmBuyPropertyInputBoundary inputBoundaryBuyProperty;

    public ConfirmBuyPropertyController(ConfirmBuyPropertyInputBoundary inputBoundaryBuyProperty) {
        this.inputBoundaryBuyProperty = inputBoundaryBuyProperty;
    }

    public ConfirmBuyPropertyController(){

    }

    /**
     *
     * @param command The string command input that the player types when the question to
     *                initiate buy property is asked. The command can either be yes if the
     *                player wants to purchase the landed on property, no otherwise. If any
     *                command other than yes or no is received, throws error indicating the
     *                input is not found.
     * @throws Exception Throws exception otherwise, when the input is not found.
     */
    public void performCommand(String command) throws Exception {
        boolean decision;
        if (command.equals("yes")){
            decision = true;
        } else if (command.equals("no")) {
            decision = false;
        } else {
            throw new Exception("Error: Input not found.");
        }
        ConfirmBuyPropertyInputData inputData = new ConfirmBuyPropertyInputData(decision);
        inputBoundaryBuyProperty.performAction(inputData);
    }

    // Getter:
    public ConfirmBuyPropertyInputBoundary getInputBoundaryBuyProperty(){
        return inputBoundaryBuyProperty;
    }
    
    // Setter:
    public void setInputBoundaryBuyProperty(ConfirmBuyPropertyInputBoundary inputBoundaryBuyProperty){
        this.inputBoundaryBuyProperty = inputBoundaryBuyProperty;
    }
    }
