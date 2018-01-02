import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTHumanPlayer extends BMTPlayer implements KeyListener
{
    private int control;
    
    BMTHumanPlayer(final BMTField bmtField, final String s, final Color color, final int control) {
        super(bmtField, s, color);
        this.control = 0;
        this.control = control;
    }
    
    private int keyCodeToDirection(final int n) {
        switch (this.control) {
            case 0: {
                switch (n) {
                    case 87: {
                        return 0;
                    }
                    case 68: {
                        return 1;
                    }
                    case 83: {
                        return 2;
                    }
                    case 65: {
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
                        return 0;
                    }
                    case 74: {
                        return 1;
                    }
                    case 72: {
                        return 2;
                    }
                    case 71: {
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
                        return 0;
                    }
                    case 222: {
                        return 1;
                    }
                    case 59: {
                        return 2;
                    }
                    case 76: {
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
                        return 0;
                    }
                    case 39: {
                        return 1;
                    }
                    case 40: {
                        return 2;
                    }
                    case 37: {
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
