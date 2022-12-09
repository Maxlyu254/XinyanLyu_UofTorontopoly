package usecase_rolldice;

import entities_main.CampaignAccess;
import usecase_advance.*;

public class RollDiceInteractor implements RollDiceInputBoundary{

    private RollDiceOutputBoundary output;

    private CampaignAccess campaignAccess;

    private AdvanceInputBoundary nextInputBoundary;

    public RollDiceInteractor(RollDiceOutputBoundary output, CampaignAccess campaignAccess, AdvanceInputBoundary
            nextInputBoundary) {
        this.output = output;
        this.campaignAccess = campaignAccess;
        this.nextInputBoundary = nextInputBoundary;
    }

    public RollDiceInteractor(){

    }

    /**
     * Performs the dice rolls, update the presenter, and call advance use case
     * @param input RollDiceInputData used to check if the
     * @throws Exception if the tile that the user lands on is not valid
     */
    @Override
    public void performAction(RollDiceInputData input) throws Exception {
        int dice1 = getDiceRoll();
        int dice2 = getDiceRoll();
        String message = "Successfully rolled dice: " + dice1 + " + " + dice2 + " = " + (dice1 + dice2);
        output.performAction(new RollDiceOutputData(message));
        AdvanceInputData inputData = new AdvanceInputData(dice1 + dice2);
        nextInputBoundary.performAction(inputData);
    }

    /**
     * @return a random integer from 1 to 6 (inclusive)
     */
    private int getDiceRoll() {
        return (int) (Math.random() * 6 + 1);
    }

    // Getters and Setters

    public RollDiceOutputBoundary getOutput() {
        return output;
    }

    public AdvanceInputBoundary getNextInputBoundary() {
        return nextInputBoundary;
    }

    public CampaignAccess getCampaignAccess() {
        return campaignAccess;
    }

    public void setNextInputBoundary(AdvanceInputBoundary nextInputBoundary) {
        this.nextInputBoundary = nextInputBoundary;
    }

    public void setCampaignAccess(CampaignAccess campaignAccess) {
        this.campaignAccess = campaignAccess;
    }

    public void setOutput(RollDiceOutputBoundary output) {
        this.output = output;
    }
}
