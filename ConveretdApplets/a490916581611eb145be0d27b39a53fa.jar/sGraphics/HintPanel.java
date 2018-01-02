// 
// Decompiled by Procyon v0.5.30
// 

package sGraphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class HintPanel extends Panel
{
    private Bubble bubble;
    private String bubbleText;
    private HintThread thread;
    private long bubbleInterval;
    private boolean isInside;
    
    public HintPanel() {
        this.bubbleText = null;
        this.thread = null;
        this.bubbleInterval = 200L;
        this.isInside = false;
        this.addMouseListener(new 1());
    }
    
    void showBubbleHelp() {
        if (this.bubbleText != null) {
            final Dimension size = this.getSize();
            final Point locationOnScreen = this.getLocationOnScreen();
            if (this.bubble == null) {
                this.bubble = new Bubble(this, this.bubbleText);
            }
            this.bubble.setLocation(locationOnScreen.x, locationOnScreen.y + size.height + 2);
            this.bubble.setVisible(true);
        }
    }
    
    public void setBubbleHelp(final String bubbleText) {
        this.bubbleText = bubbleText;
    }
    
    public void updateBubbleHelp(final String text) {
        if (this.bubble == null) {
            this.bubbleText = text;
            if (this.isInside) {
                if (this.thread != null && this.thread.isAlive()) {
                    this.thread.stop();
                    try {
                        this.thread.join();
                    }
                    catch (InterruptedException ex) {}
                }
                this.showBubbleHelp();
            }
            return;
        }
        if (this.isInside && text == null) {
            this.bubble.dispose();
            this.bubbleText = null;
            return;
        }
        this.bubble.show();
        if (this.isInside && !text.equals(this.bubbleText)) {
            this.bubbleText = text;
            this.bubble.setText(text);
        }
        this.showBubbleHelp();
    }
    
    public void setBubbleInterval(final long bubbleInterval) {
        this.bubbleInterval = bubbleInterval;
    }
    
    public long getBubbleInterval() {
        return this.bubbleInterval;
    }
    
    class 1 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            HintPanel.this.isInside = true;
            if (HintPanel.this.bubbleText != null) {
                if (HintPanel.this.bubbleInterval == 0) {
                    HintPanel.this.showBubbleHelp();
                }
                else {
                    HintPanel.this.thread = new HintThread(HintPanel.this);
                    HintPanel.this.thread.start();
                }
            }
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            if (HintPanel.this.bubble != null && HintPanel.this.bubble.isShowing()) {
                HintPanel.this.bubble.dispose();
            }
            if (HintPanel.this.thread != null && HintPanel.this.thread.isAlive()) {
                HintPanel.this.thread.stop();
                try {
                    HintPanel.this.thread.join();
                }
                catch (InterruptedException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (HintPanel.this.bubbleText != null) {
                if (HintPanel.this.bubbleInterval == 0) {
                    HintPanel.this.showBubbleHelp();
                }
                else {
                    HintPanel.this.thread = new HintThread(HintPanel.this);
                    HintPanel.this.thread.start();
                }
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            HintPanel.this.isInside = false;
            if (HintPanel.this.bubble != null && HintPanel.this.bubble.isShowing()) {
                HintPanel.this.bubble.dispose();
            }
            if (HintPanel.this.thread != null && HintPanel.this.thread.isAlive()) {
                HintPanel.this.thread.stop();
                try {
                    HintPanel.this.thread.join();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}
