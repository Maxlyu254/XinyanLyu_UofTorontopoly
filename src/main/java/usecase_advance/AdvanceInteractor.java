package usecase_advance;
import entities_main.Campaign;
import entities_properties.Property;
import entities_tiles.DrawCardTile;
import entities_tiles.GoToJailTile;
import entities_tiles.PropertyTile;
import entities_tiles.Tile;
import exception.WrongCommandArgsException;
import usecase_gotojail.GoToJailInputBoundary;
import usecase_initiatebuyproperty.InitiateBuyPropertyInputBoundary;
import entities_main.Player;
import usecase_payrent.PayRentInputBoundary;
import entities_main.CampaignAccess;
import usecase_drawcard.*;


public class AdvanceInteractor implements AdvanceInputBoundary{
    public static final int START_BONUS = 200;
    private AdvanceOutputBoundary outputBoundary;
    private CampaignAccess campaignAccess;
    private DrawCardInputBoundary drawCardInputBoundary;
    private InitiateBuyPropertyInputBoundary initiateBuyPropertyIP;
    private PayRentInputBoundary payRentInputBoundary;

    private GoToJailInputBoundary jailInputBoundary;

    public AdvanceInteractor(AdvanceOutputBoundary outputBoundary, CampaignAccess campaignAccess, DrawCardInputBoundary
            drawCardInputBoundary, InitiateBuyPropertyInputBoundary initiateBuyPropertyIP, PayRentInputBoundary
                             payRentInputBoundary, GoToJailInputBoundary jailInputBoundary) {
        this.outputBoundary = outputBoundary;
        this.campaignAccess = campaignAccess;
        this.drawCardInputBoundary = drawCardInputBoundary;
        this.initiateBuyPropertyIP = initiateBuyPropertyIP;
        this.payRentInputBoundary = payRentInputBoundary;
        this.jailInputBoundary = jailInputBoundary;
    }

    public AdvanceInteractor(){

    }

    /**
     * Less constructor variables for testing.
     */
    public AdvanceInteractor(AdvanceOutputBoundary outputBoundary, CampaignAccess campaignAccess) {
        this.outputBoundary = outputBoundary;
        this.campaignAccess = campaignAccess;
    }
    @Override
    public void performAction(AdvanceInputData inputData) throws Exception {
        Campaign campaign = campaignAccess.getCampaign();
        // Find the player that need to be advanced
        Player player = inputData.isPlayerUnspecified() ?
                campaign.getCurrentPlayer():
                campaign.getPlayerCalled(inputData.getPlayername());
        if (player == null) throw new WrongCommandArgsException("Player name is invalid");

        // Update player location
        int startPasses = 0;
        if (player.getLocation() + inputData.getDiceSum() > campaign.getBoardSize() - 1) {
            startPasses = player.getLocation() / campaign.getBoardSize();
            player.setLocation((player.getLocation() + inputData.getDiceSum()) % campaign.getBoardSize());
            player.gainCash(startPasses * START_BONUS);
        } else {
            player.setLocation(player.getLocation() + inputData.getDiceSum());
        }
        // From output data for passing the start line
        String message = "";
        if (startPasses == 1) {
            message = "You passed the start tile, gained " + (startPasses * START_BONUS) + " T-bucks";
        } else if (startPasses > 1) {
            message = "You passed the start tile" + startPasses +
                    "times, gained " + (startPasses * START_BONUS) + " T-bucks";
        }
        AdvanceOutputData outputData = new AdvanceOutputData(
                message, player.getLocation(), campaign.getPlayerIndex(player), player.getCash(), false);
        outputBoundary.performAction(outputData);

        // Perform Tile action
        Tile tile = campaign.getTileUnderPlayer(player);
        if (tile instanceof PropertyTile) {
            Property property = ((PropertyTile) tile).getProperty();
            if (property.isOwnerless()) {
                // call initiate buy property use case TODO: implement the use cases to complete this part
            } else if (property.getOwner() != player) {
                //Let player pay rent to the owner TODO: implement the use cases to complete this part
            }
        } else if (tile instanceof DrawCardTile) {
            // call draw card use case TODO: implement the use cases to complete this part
        } else if (tile instanceof GoToJailTile) {
            // call go to jail use case TODO: implement the use cases to complete this part
        } else {
            // do nothing
        }
    }

    // Getters and Setters

    public AdvanceOutputBoundary getOutputBoundary() {
        return outputBoundary;
    }

    public CampaignAccess getCampaignAccess() {
        return campaignAccess;
    }

    public DrawCardInputBoundary getDrawCardInputBoundary() {
        return drawCardInputBoundary;
    }

    public GoToJailInputBoundary getJailInputBoundary() {
        return jailInputBoundary;
    }

    public InitiateBuyPropertyInputBoundary getInitiateBuyPropertyIP() {
        return initiateBuyPropertyIP;
    }

    public PayRentInputBoundary getPayRentInputBoundary() {
        return payRentInputBoundary;
    }

    public void setOutputBoundary(AdvanceOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void setCampaignAccess(CampaignAccess campaignAccess) {
        this.campaignAccess = campaignAccess;
    }

    public void setDrawCardInputBoundary(DrawCardInputBoundary drawCardInputBoundary) {
        this.drawCardInputBoundary = drawCardInputBoundary;
    }

    public void setInitiateBuyPropertyIP(InitiateBuyPropertyInputBoundary initiateBuyPropertyIP) {
        this.initiateBuyPropertyIP = initiateBuyPropertyIP;
    }

    public void setJailInputBoundary(GoToJailInputBoundary jailInputBoundary) {
        this.jailInputBoundary = jailInputBoundary;
    }

    public void setPayRentInputBoundary(PayRentInputBoundary payRentInputBoundary) {
        this.payRentInputBoundary = payRentInputBoundary;
    }
}
