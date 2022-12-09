package usecase_rolldice;
import viewmodel.*;

public class RollDicePresenter implements RollDiceOutputBoundary{

    private CommandPanelViewModel commandPanelViewModel;
    private InputMapDictionary mapDictionary;

    public RollDicePresenter(CommandPanelViewModel commandPanelViewModel,
                             InputMapDictionary mapDictionary){
        this.commandPanelViewModel = commandPanelViewModel;
        this.mapDictionary = mapDictionary;

    }

    public RollDicePresenter(){}


    /**
     * Displays the results of the dice roll.
     * @param diceOutputData output data from the interactor.
     */
    @Override
    public void performAction(RollDiceOutputData diceOutputData){
        // Append command line
        String diceRollMessage = diceOutputData.getMessage();
        commandPanelViewModel.appendOutput(diceRollMessage);
        commandPanelViewModel.notifyListeners();

        // update input map dictionary
        mapDictionary.setCurrentMapName("after_move");
    }

    public CommandPanelViewModel getCommandPanelViewModel() {
        return commandPanelViewModel;
    }

    public InputMapDictionary getMapDictionary() {
        return mapDictionary;
    }

    public void setCommandPanelViewModel(CommandPanelViewModel commandPanelViewModel) {
        this.commandPanelViewModel = commandPanelViewModel;
    }

    public void setMapDictionary(InputMapDictionary mapDictionary) {
        this.mapDictionary = mapDictionary;
    }
}
