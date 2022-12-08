package AdvanceUseCase;
import BuyPropertyUseCase.BuyPropertyInputData;
import MainEntities.Player;
import MainEntities.GameBoard;
import Tiles.*;
import MainEntities.CampaignAccess;
import ViewModel.InputMapDictionary;


public class AdvanceInteractor implements AdvanceInputBoundary{
    /*
    The Advance use case advances the player to a specific location on the board depending on the dice roll passed by
    AdvanceController.
     */

    AdvanceOutputBoundary output;

    CampaignAccess campaign;

    public AdvanceInteractor(AdvanceOutputBoundary output, CampaignAccess campaign) {
        this.output = output;
        this.campaign = campaign;
    }

    public AdvanceInteractor(){

    }

    /**
     * Advances the player to a new tile based off of diceSum. Calls on performTileAction to allow the user to
     * perform the tile's corresponding action.
     * @param diceSum the result of two dice rolls
     * @return if inputMap should be updated in ViewModel layer.
     */
    public boolean advancePlayer(int diceSum) throws Exception {

        Player player = campaign.getCampaign().getCurrentPlayer();
        GameBoard board = campaign.getCampaign().getBoard();

        int tilesMoved = diceSum + player.getLocation();

        // If the player moves past start, add $200 to the player's cash
        if (tilesMoved > board.getSize()){
            player.setLocation(tilesMoved - board.getSize());
            player.gainCash(200);
        }
        else {
            player.setLocation(tilesMoved);
        }

        return performTileAction(player.getLocation());
    }

    /**
     * Allows the player to make an action based off the tile they are on.
     * @param tileIndex the index of the tile that the player is on.
     * @return if inputMap should be updated in ViewModel layer.
     */
    public boolean performTileAction(int tileIndex) throws Exception {
        GameBoard board = campaign.getCampaign().getBoard();
        Tile tile = board.getTileAt(tileIndex);

        if (tile instanceof DrawCardTile){
            // Calls on DrawCard use case after it is finished.
            return true;
        } else if (tile instanceof GoToJailTile) {
            // Calls on GoToJail use case after it is finished.
            return true;
        } else if (tile instanceof JailTile) {
            // Do nothing as the tile has no user actions.
            return true;
        } else if (tile instanceof ParkingTile) {
            // Do nothing as the tile has no user actions.
            return true;
        } else if (tile instanceof PropertyTile) {
            // Calls on BuyProperty and PayRent use case after it is finished.
            // What happens if the property is owned? If it isn't?

            if (((PropertyTile) tile).getProperty().isOwnerless()) {
                return false;
            }
            else {
                // TODO
                return true;
            }
        } else if (tile instanceof StartTile) {
            return true;
        }
        else{
            throw new Exception("Tile not found.");
        }
    }

    // Todo: may need to change output message depending on tile type.

    /**
     * Performs the Advance action by moving the player forwards the appropriate number of tiles. The Advance use case
     * will be called by the RollDice use case and Card use cases.
     * Precondition: The player has confirmed that they will advance, either by confirming their dice roll or card.
     * @param input AdvanceInputData containing if the user confirmed the advance or not.
     * @throws Exception if the tile that the user lands on is not valid
     */
    @Override
    public void performAction(AdvanceInputData input) throws Exception {

        Player player = campaign.getCampaign().getCurrentPlayer();
        boolean updateInputMap;

        try {
            if (input.isConfirmRoll()) {
                updateInputMap = advancePlayer(input.diceSum);
                AdvanceOutputData outputMessage =
                        new AdvanceOutputData("You have landed on this tile: " + player.getLocation(),
                                true, player.getLocation(), campaign.getCampaign().getCurrPlayerIndex(),
                                updateInputMap);
                output.performAction(outputMessage);
            }
        }
        catch (Exception e){
            AdvanceOutputData outputMessage =
                    new AdvanceOutputData("Error: Tile not found", false,
                            player.getLocation(), campaign.getCampaign().getCurrPlayerIndex(), false);
            output.performAction(outputMessage);
        }
    }
}
