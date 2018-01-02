import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ControlPanel extends Panel
{
    private Label senseLabel;
    private Label levelLabel;
    private Button newGameButton;
    private Button bRatSensePlus;
    private Button bRatSenseMinus;
    private Button bMazeLevelPlus;
    private Button bMazeLevelMinus;
    private TextField ratSenseText;
    private TextField gameLevelText;
    private MazeGame mg;
    private static final int MIN_SENSE_LEVEL = 1;
    private static final int MAX_SENSE_LEVEL = 4;
    private static final int MIN_GAME_LEVEL = 6;
    private static final int MAX_GAME_LEVEL = 20;
    private int noOfRows;
    private int noOfSmellBlocks;
    
    public ControlPanel(final MazeGame mg) {
        this.noOfRows = 10;
        this.noOfSmellBlocks = 2;
        this.setLayout(null);
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.setFont(new Font("Times", 1, 12));
        this.senseLabel = new Label("Sense Power");
        this.levelLabel = new Label("Game Level");
        this.bRatSensePlus = new Button("+");
        this.bRatSenseMinus = new Button("-");
        this.bMazeLevelPlus = new Button("+");
        this.bMazeLevelMinus = new Button("-");
        this.ratSenseText = new TextField("");
        this.gameLevelText = new TextField("");
        this.newGameButton = new Button("NEW GAME");
        this.levelLabel.reshape(24, 12, 81, 21);
        this.senseLabel.reshape(24, 84, 96, 25);
        this.bMazeLevelMinus.reshape(24, 48, 26, 24);
        this.bMazeLevelPlus.reshape(96, 48, 26, 24);
        this.bRatSenseMinus.reshape(24, 120, 26, 24);
        this.bRatSensePlus.reshape(96, 120, 26, 24);
        this.ratSenseText.reshape(60, 48, 26, 24);
        this.gameLevelText.reshape(60, 120, 26, 24);
        this.newGameButton.reshape(24, 168, 98, 28);
        this.add(this.senseLabel);
        this.add(this.levelLabel);
        this.add(this.bRatSensePlus);
        this.add(this.bRatSenseMinus);
        this.add(this.bMazeLevelPlus);
        this.add(this.bMazeLevelMinus);
        this.add(this.ratSenseText);
        this.add(this.gameLevelText);
        this.add(this.newGameButton);
        this.validate();
        this.mg = mg;
        this.updateLevels();
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    public int getGameLevel() {
        return this.noOfRows;
    }
    
    public int getSensingPower() {
        return this.noOfSmellBlocks;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.newGameButton) {
            this.mg.makePlayAgain();
        }
        else if (event.target == this.bMazeLevelMinus) {
            this.setGameLevel(false);
        }
        else if (event.target == this.bMazeLevelPlus) {
            this.setGameLevel(true);
        }
        else if (event.target == this.bRatSenseMinus) {
            this.setSenseLevel(false);
        }
        else if (event.target == this.bRatSensePlus) {
            this.setSenseLevel(true);
        }
        return true;
    }
    
    private void setGameLevel(final boolean b) {
        if (b) {
            if (this.noOfRows < 20) {
                ++this.noOfRows;
            }
        }
        else if (this.noOfRows > 6) {
            --this.noOfRows;
        }
        this.updateLevels();
    }
    
    private void setSenseLevel(final boolean b) {
        if (b) {
            if (this.noOfSmellBlocks < 4) {
                ++this.noOfSmellBlocks;
            }
        }
        else if (this.noOfSmellBlocks > 1) {
            --this.noOfSmellBlocks;
        }
        this.updateLevels();
    }
    
    private void updateLevels() {
        final String value = String.valueOf(this.getGameLevel());
        final String value2 = String.valueOf(this.getSensingPower());
        this.ratSenseText.setText(value);
        this.gameLevelText.setText(value2);
    }
}
