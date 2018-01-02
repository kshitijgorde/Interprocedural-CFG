// 
// Decompiled by Procyon v0.5.30
// 

package org.chaingang.game.imageshuffle;

import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUp extends JPopupMenu
{
    private JMenuItem mPlay;
    private JMenuItem mPeek;
    private JMenuItem mUnpeek;
    private JMenuItem mRestart;
    private JMenuItem mStatus;
    private JMenuItem mAbout;
    private JMenuItem firstMenuItem;
    private int mouseMove;
    private boolean isWin;
    
    public PopUp(final ActionListener actionListener) {
        this.isWin = false;
        final JMenuItem mPeek = new JMenuItem("Peek");
        mPeek.setActionCommand("c_peek");
        mPeek.addActionListener(actionListener);
        this.add(mPeek);
        this.mPeek = mPeek;
        final JMenuItem mPlay = new JMenuItem("Play");
        mPlay.setActionCommand("c_play");
        mPlay.addActionListener(actionListener);
        this.add(mPlay);
        this.mPlay = mPlay;
        final JMenuItem mRestart = new JMenuItem("Restart");
        mRestart.setActionCommand("c_play");
        mRestart.addActionListener(actionListener);
        this.add(mRestart);
        this.mRestart = mRestart;
        final JMenuItem mUnpeek = new JMenuItem("Resume");
        mUnpeek.setActionCommand("c_unpeek");
        mUnpeek.addActionListener(actionListener);
        this.add(mUnpeek);
        this.mUnpeek = mUnpeek;
        final JMenuItem mAbout = new JMenuItem("About");
        mAbout.setActionCommand("c_about");
        mAbout.addActionListener(actionListener);
        this.add(mAbout);
        this.mAbout = mAbout;
        this.addSeparator();
        final JMenuItem mStatus = new JMenuItem("-");
        mStatus.setEnabled(false);
        this.add(mStatus);
        this.mStatus = mStatus;
        this.setPlay();
    }
    
    private void setPlay() {
        this.mPlay.setVisible(true);
        this.mPeek.setVisible(false);
        this.mUnpeek.setVisible(false);
        this.mRestart.setVisible(false);
        this.firstMenuItem = this.mPlay;
        this.mStatus.setText("-");
    }
    
    private void setPlaying() {
        this.mPlay.setVisible(false);
        this.mPeek.setVisible(true);
        this.mUnpeek.setVisible(false);
        this.mRestart.setVisible(true);
        this.firstMenuItem = this.mPeek;
    }
    
    private void setPeek() {
        this.mPlay.setVisible(false);
        this.mPeek.setVisible(false);
        this.mUnpeek.setVisible(true);
        this.mRestart.setVisible(false);
        this.firstMenuItem = this.mUnpeek;
    }
    
    public void setStatus(final String s) {
        if (s.equals("c_play")) {
            this.setPlaying();
        }
        else if (s.equals("c_peek")) {
            this.setPeek();
        }
        else if (s.equals("c_unpeek")) {
            this.setPlaying();
        }
        else if (s.equals("c_win")) {
            this.setPlay();
        }
        this.pack();
    }
    
    public void show(final MouseEvent mouseEvent) {
        this.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void fireFirst() {
        this.firstMenuItem.doClick();
    }
    
    private void updateStatusText() {
        if (this.isWin) {
            this.mStatus.setText("won in " + String.valueOf(this.mouseMove) + " moves");
        }
        else {
            this.mStatus.setText("played " + String.valueOf(this.mouseMove) + " move" + ((this.mouseMove == 1) ? "" : "s"));
        }
    }
    
    public void setMoves(final int mouseMove) {
        this.mouseMove = mouseMove;
        this.updateStatusText();
    }
    
    public void setWin(final boolean isWin) {
        this.isWin = isWin;
        this.updateStatusText();
    }
}
