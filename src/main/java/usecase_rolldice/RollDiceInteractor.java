package usecase_rolldice;

import entities_main.CampaignAccess;
import usecase_advance.*;

public class RollDiceInteractor implements RollDiceInputBoundary{

    RollDiceOutputBoundary output;

    CampaignAccess campaignAccess;

    AdvanceInputBoundary advanceInputBoundary;

    public RollDiceInteractor(RollDiceOutputBoundary output, CampaignAccess campaignAccess, AdvanceInputBoundary
                              advanceInputBoundary) {
        this.output = output;
        this.campaignAccess = campaignAccess;
        this.advanceInputBoundary = advanceInputBoundary;
    }

    public RollDiceInteractor(){

    }

    /**
     * Performs the dice rolls and updates the presenter.
     * @param input RollDiceInputData used to check if the
     * @throws Exception if the tile that the user lands on is not valid
     */
    @Override
    public void performAction(RollDiceInputData input) throws Exception {
        // TODO: roll dice should change the input map

    }

    // Getters and Setters

    public RollDiceOutputBoundary getOutput() {
        return output;
    }

    public AdvanceInputBoundary getAdvanceInputBoundary() {
        return advanceInputBoundary;
    }

    public CampaignAccess getCampaignAccess() {
        return campaignAccess;
    }

    public void setAdvanceInputBoundary(AdvanceInputBoundary advanceInputBoundary) {
        this.advanceInputBoundary = advanceInputBoundary;
    }

    public void setCampaignAccess(CampaignAccess campaignAccess) {
        this.campaignAccess = campaignAccess;
    }

    public void setOutput(RollDiceOutputBoundary output) {
        this.output = output;
    }
}
