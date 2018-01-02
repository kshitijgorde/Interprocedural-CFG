// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
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
        this.addMouseListener(new MouseAdapter() {
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
                    HintPanel.this.thread.interrupted = true;
                    try {
                        HintPanel.this.thread.join();
                    }
                    catch (InterruptedException ex) {}
                    HintPanel.this.thread = null;
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
                    HintPanel.this.thread.interrupted = true;
                    try {
                        HintPanel.this.thread.join();
                    }
                    catch (InterruptedException ex) {}
                    HintPanel.this.thread = null;
                }
            }
        });
    }
    
    public synchronized void destroyHint() {
        this.isInside = false;
        if (this.thread != null && this.thread.isAlive()) {
            this.thread.interrupted = true;
            try {
                this.thread.join();
            }
            catch (InterruptedException ex) {}
            this.thread = null;
        }
        if (this.bubble != null && this.bubble.isShowing()) {
            this.bubble.dispose();
        }
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
    
    public synchronized void forceBubbleHelp(final String text) {
        if (this.bubble == null) {
            this.bubbleText = text;
            if (this.thread != null && this.thread.isAlive()) {
                this.thread.interrupted = true;
                try {
                    this.thread.join();
                }
                catch (InterruptedException ex) {}
                this.thread = null;
            }
            this.showBubbleHelp();
            return;
        }
        if (text == null) {
            this.bubble.dispose();
            this.bubbleText = null;
            return;
        }
        this.bubble.show();
        if (!text.equals(this.bubbleText)) {
            this.bubbleText = text;
            this.bubble.setText(text);
        }
        this.showBubbleHelp();
    }
    
    public synchronized void updateBubbleHelp(final String text) {
        if (this.bubble == null) {
            this.bubbleText = text;
            if (this.isInside) {
                if (this.thread != null && this.thread.isAlive()) {
                    this.thread.interrupted = true;
                    try {
                        this.thread.join();
                    }
                    catch (InterruptedException ex) {}
                    this.thread = null;
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
}
