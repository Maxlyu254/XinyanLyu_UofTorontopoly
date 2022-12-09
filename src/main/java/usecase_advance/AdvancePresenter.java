package usecase_advance;

import viewmodel.*;

public class AdvancePresenter implements AdvanceOutputBoundary {

    private CommandPanelViewModel commandPanelVM;

    private BoardPanelViewModel boardPanelVM;

    private PlayerPanelViewModel playerPanelVM;

    private InputMapDictionary mapDictionary;

    public AdvancePresenter(){

    }

    /**
     * Updates viewmodel's command panel with results of the advance.
     * @param outputData message telling player which tile they have advanced to or an error
     *                   message if the tile wasn't found.
     */
    @Override
    public void performAction(AdvanceOutputData outputData){

        int playerIndex = outputData.getPlayerIndex();

        // update player panel VM
        PlayerViewModel playerVM = playerPanelVM.getPlayerVMAt(playerIndex);
        playerVM.setPosition(outputData.getPlayerLocation());
        playerPanelVM.notifyListeners();

        // update command panel VM
        String message = outputData.getMessage();
        if (! message.isEmpty()) {
            commandPanelVM.appendOutput(message);
            commandPanelVM.notifyListeners();
        }

        // update input map dictionary
        if (outputData.isUpdateInputMap()){
            mapDictionary.setCurrentMapName("after_move");
        }
    }

    //Getters and Setters
    public void setCommandPanelVM(CommandPanelViewModel commandPanelVM) {
        this.commandPanelVM = commandPanelVM;
    }

    public void setBoardPanelVM(BoardPanelViewModel boardPanelVM) {
        this.boardPanelVM = boardPanelVM;
    }

    public void setMapDictionary(InputMapDictionary mapDictionary) {
        this.mapDictionary = mapDictionary;
    }

    public void setPlayerPanelVM(PlayerPanelViewModel playerPanelVM) {
        this.playerPanelVM = playerPanelVM;
    }

    public CommandPanelViewModel getCommandPanelVM() {
        return commandPanelVM;
    }

    public BoardPanelViewModel getBoardPanelVM() {
        return boardPanelVM;
    }

    public InputMapDictionary getMapDictionary() {
        return mapDictionary;
    }

    public PlayerPanelViewModel getPlayerPanelVM() {
        return playerPanelVM;
    }
}
