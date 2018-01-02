// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.ui;

import com.chess.game.Player;
import com.chess.chessboard.ChessboardModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.chess.applet.GameController;
import javax.swing.JPanel;

public class GameControlPanel extends JPanel
{
    private GameController gameContrller;
    private int previousSelectedIndex;
    private boolean applyImmediately;
    private JComboBox colorComboBox;
    private JLabel colorLabel;
    private JPanel controlsPanel;
    private JPanel labelsPanel;
    private JComboBox levelComboBox;
    private JLabel levelLabel;
    private JButton newGameButton;
    
    public GameControlPanel() {
        this.initComponents();
        this.previousSelectedIndex = this.levelComboBox.getSelectedIndex();
        this.applyImmediately = true;
    }
    
    private void initComponents() {
        this.labelsPanel = new JPanel();
        this.colorLabel = new JLabel();
        this.levelLabel = new JLabel();
        this.controlsPanel = new JPanel();
        this.colorComboBox = new JComboBox();
        this.levelComboBox = new JComboBox();
        this.newGameButton = new JButton();
        this.setLayout(new BorderLayout(5, 5));
        this.labelsPanel.setOpaque(false);
        this.labelsPanel.setLayout(new GridLayout(2, 0));
        this.colorLabel.setText("I Play As");
        this.labelsPanel.add(this.colorLabel);
        this.levelLabel.setText("Level");
        this.labelsPanel.add(this.levelLabel);
        this.add(this.labelsPanel, "West");
        this.controlsPanel.setOpaque(false);
        this.controlsPanel.setLayout(new GridLayout(2, 0));
        this.colorComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "White", "Black" }));
        this.colorComboBox.setOpaque(false);
        this.controlsPanel.add(this.colorComboBox);
        this.levelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Basic (~800)", "Easy (~1200)", "Medium (~1600)", "Hard (2000+)" }));
        this.levelComboBox.setOpaque(false);
        this.levelComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GameControlPanel.this.levelComboBoxActionPerformed(evt);
            }
        });
        this.controlsPanel.add(this.levelComboBox);
        this.add(this.controlsPanel, "Center");
        this.newGameButton.setText("New Game");
        this.newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                GameControlPanel.this.newGameButtonActionPerformed(evt);
            }
        });
        this.add(this.newGameButton, "South");
    }
    
    private void newGameButtonActionPerformed(final ActionEvent evt) {
        new Thread("AWT-Child (New Game)") {
            public void run() {
                GameControlPanel.this.newGameButton.setEnabled(false);
                if (GameControlPanel.this.gameContrller != null) {
                    final ChessboardModel.PieceColor newHumanColor = ChessboardModel.PieceColor.valueOf(GameControlPanel.this.colorComboBox.getSelectedItem().toString().toUpperCase());
                    String selectedLevel = GameControlPanel.this.levelComboBox.getSelectedItem().toString().toUpperCase();
                    selectedLevel = selectedLevel.substring(0, selectedLevel.indexOf(32));
                    final Player.Level computerLevel = Player.Level.valueOf(selectedLevel);
                    GameControlPanel.this.gameContrller.startNewGame(newHumanColor, computerLevel, null, null);
                }
                GameControlPanel.this.newGameButton.setEnabled(true);
            }
        }.start();
    }
    
    private void levelComboBoxActionPerformed(final ActionEvent evt) {
        if (this.applyImmediately) {
            String selectedLevel = this.levelComboBox.getSelectedItem().toString().toUpperCase();
            selectedLevel = selectedLevel.substring(0, selectedLevel.indexOf(32));
            final Player.Level level = Player.Level.valueOf(selectedLevel);
            this.gameContrller.setComputerLevel(level);
        }
        else {
            this.applyImmediately = true;
        }
    }
    
    void setGameController(final GameController controller) {
        this.gameContrller = controller;
    }
    
    public void init(final ChessboardModel.PieceColor humanColor, final Player.Level computerLevel) {
        switch (humanColor) {
            case WHITE: {
                this.colorComboBox.setSelectedIndex(0);
                break;
            }
            case BLACK: {
                this.colorComboBox.setSelectedIndex(1);
                break;
            }
        }
        this.applyImmediately = false;
        switch (computerLevel) {
            case BASIC: {
                this.previousSelectedIndex = 0;
                break;
            }
            case EASY: {
                this.previousSelectedIndex = 1;
                break;
            }
            case MEDIUM: {
                this.previousSelectedIndex = 2;
                break;
            }
            case HARD: {
                this.previousSelectedIndex = 3;
                break;
            }
        }
        this.levelComboBox.setSelectedIndex(this.previousSelectedIndex);
    }
}
