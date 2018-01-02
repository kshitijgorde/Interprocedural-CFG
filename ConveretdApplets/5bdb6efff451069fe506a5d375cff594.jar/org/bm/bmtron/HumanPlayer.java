// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.KeyListener;

public class HumanPlayer extends Player implements KeyListener
{
    private int control;
    
    HumanPlayer(final Field field, final String s, final Color color, final int control) {
        super(field, s, color);
        this.control = 0;
        this.control = control;
    }
    
    private int keyCodeToDirection(final int n) {
        switch (this.control) {
            case 0: {
                switch (n) {
                    case 87: {
                        final Field field = super.field;
                        return 0;
                    }
                    case 68: {
                        final Field field2 = super.field;
                        return 1;
                    }
                    case 83: {
                        final Field field3 = super.field;
                        return 2;
                    }
                    case 65: {
                        final Field field4 = super.field;
                        return 3;
                    }
                    default: {
                        return -1;
                    }
                }
                break;
            }
            case 1: {
                switch (n) {
                    case 89: {
                        final Field field5 = super.field;
                        return 0;
                    }
                    case 74: {
                        final Field field6 = super.field;
                        return 1;
                    }
                    case 72: {
                        final Field field7 = super.field;
                        return 2;
                    }
                    case 71: {
                        final Field field8 = super.field;
                        return 3;
                    }
                    default: {
                        return -1;
                    }
                }
                break;
            }
            case 2: {
                switch (n) {
                    case 80: {
                        final Field field9 = super.field;
                        return 0;
                    }
                    case 222: {
                        final Field field10 = super.field;
                        return 1;
                    }
                    case 59:
                    case 186: {
                        final Field field11 = super.field;
                        return 2;
                    }
                    case 76: {
                        final Field field12 = super.field;
                        return 3;
                    }
                    default: {
                        return -1;
                    }
                }
                break;
            }
            case 3: {
                switch (n) {
                    case 38: {
                        final Field field13 = super.field;
                        return 0;
                    }
                    case 39: {
                        final Field field14 = super.field;
                        return 1;
                    }
                    case 40: {
                        final Field field15 = super.field;
                        return 2;
                    }
                    case 37: {
                        final Field field16 = super.field;
                        return 3;
                    }
                    default: {
                        return -1;
                    }
                }
                break;
            }
            default: {
                return -1;
            }
        }
    }
    
    void stop() {
        super.field.removeKeyListener(this);
        super.stop();
    }
    
    void start() {
        super.field.addKeyListener(this);
        super.start();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCodeToDirection = this.keyCodeToDirection(keyEvent.getKeyCode());
        if (keyCodeToDirection >= 0) {
            this.setDirection(keyCodeToDirection);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
