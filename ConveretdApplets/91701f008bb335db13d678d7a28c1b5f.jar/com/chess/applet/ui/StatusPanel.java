// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.chess.chessboard.StatusViewer;
import javax.swing.JPanel;

public class StatusPanel extends JPanel implements StatusViewer
{
    private ImageIcon loadingIcon;
    private volatile boolean humanTurn;
    private boolean gameOver;
    private String score;
    private boolean scoreVisible;
    private JPanel emptyPanel;
    private JPanel labelsPanel;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private JPanel scorePanel;
    private JLabel scoreTitle;
    private JLabel thinkingLabel;
    private Runnable computerWaiterRunnable;
    
    public StatusPanel() {
        this.computerWaiterRunnable = new Runnable() {
            public void run() {
                StatusPanel.this.thinkingLabel.setVisible(false);
                StatusPanel.this.messageLabel.setVisible(false);
                try {
                    Thread.sleep(1500L);
                }
                catch (InterruptedException ex) {}
                if (!StatusPanel.this.humanTurn) {
                    if (!StatusPanel.this.gameOver) {
                        StatusPanel.this.thinkingLabel.setVisible(true);
                    }
                    StatusPanel.this.messageLabel.setVisible(true);
                }
            }
        };
        this.initComponents();
        this.loadingIcon = (ImageIcon)this.thinkingLabel.getIcon();
        this.thinkingLabel.setIcon(this.loadingIcon);
        this.scoreTitle.setText(this.scoreVisible ? "Score: " : "");
    }
    
    private void initComponents() {
        this.emptyPanel = new JPanel();
        this.labelsPanel = new JPanel();
        this.thinkingLabel = new JLabel();
        this.messageLabel = new JLabel();
        this.scorePanel = new JPanel();
        this.scoreTitle = new JLabel();
        this.scoreLabel = new JLabel();
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.emptyPanel.setMinimumSize(new Dimension(100, 10));
        this.emptyPanel.setOpaque(false);
        this.emptyPanel.setPreferredSize(new Dimension(100, 10));
        this.add(this.emptyPanel, "West");
        this.labelsPanel.setOpaque(false);
        this.thinkingLabel.setHorizontalAlignment(4);
        this.thinkingLabel.setIcon(new ImageIcon(this.getClass().getResource("/ajax-loader.gif")));
        this.labelsPanel.add(this.thinkingLabel);
        this.messageLabel.setFont(this.messageLabel.getFont().deriveFont(16.0f));
        this.messageLabel.setHorizontalAlignment(2);
        this.labelsPanel.add(this.messageLabel);
        this.add(this.labelsPanel, "Center");
        this.scorePanel.setMinimumSize(new Dimension(100, 27));
        this.scorePanel.setOpaque(false);
        this.scorePanel.setPreferredSize(new Dimension(100, 27));
        this.scorePanel.setLayout(new FlowLayout(2));
        this.scoreTitle.setText("Score: ");
        this.scorePanel.add(this.scoreTitle);
        this.scorePanel.add(this.scoreLabel);
        this.add(this.scorePanel, "East");
    }
    
    public void showMessage(final String message) {
        this.messageLabel.setText(message);
    }
    
    public void turnChanged(final boolean human) {
        this.humanTurn = human;
        this.thinkingLabel.setIcon(human ? null : this.loadingIcon);
        if (human) {
            this.thinkingLabel.setVisible(false);
            this.messageLabel.setVisible(true);
        }
        else {
            new Thread(this.computerWaiterRunnable).start();
        }
    }
    
    public void gameOver() {
        this.gameOver = true;
        this.thinkingLabel.setVisible(false);
        this.messageLabel.setVisible(true);
        this.messageLabel.setForeground(Color.RED);
        this.scoreLabel.setText("");
    }
    
    public void gameStarted() {
        this.gameOver = false;
        this.messageLabel.setText("");
        this.messageLabel.setForeground(Color.BLACK);
        this.messageLabel.setVisible(true);
    }
    
    public void setScore(final String score) {
        this.score = score;
        if (this.scoreVisible) {
            this.scoreLabel.setText(score);
        }
    }
    
    public void setScoreVisible(final boolean visible) {
        this.scoreVisible = visible;
        if (visible) {
            this.scoreTitle.setText("Score: ");
            this.scoreLabel.setText(this.score);
        }
        else {
            this.scoreTitle.setText("");
            this.scoreLabel.setText("");
        }
    }
}
