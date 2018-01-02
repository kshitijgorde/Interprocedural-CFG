// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.awt;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.Component;

public class DdiFocusImageButton extends DdiImageButton
{
    boolean isFocused;
    boolean isFocuseable;
    Component nextFocusableComponent;
    Component previousFocusableComponent;
    
    public DdiFocusImageButton() {
        this.isFocused = false;
        this.isFocuseable = true;
    }
    
    public void requestFocus() {
        if (this.isFocuseable) {
            this.isFocused = true;
            this.getParent().addKeyListener(new ButtonInFocusKeys(this));
        }
        else {
            this.nextFocusableComponent.requestFocus();
        }
    }
    
    public void setNextFocusableComponent(final Component nextFocusableComponent) {
        this.nextFocusableComponent = nextFocusableComponent;
    }
    
    public void setPreviousFocusableComponent(final Component previousFocusableComponent) {
        this.previousFocusableComponent = previousFocusableComponent;
    }
    
    public void setPreviousAndNextFocusableComponent(final Component previousFocusableComponent, final Component nextFocusableComponent) {
        this.nextFocusableComponent = nextFocusableComponent;
        this.previousFocusableComponent = previousFocusableComponent;
    }
    
    class ButtonInFocusKeys extends KeyAdapter
    {
        final DdiFocusImageButton button;
        
        ButtonInFocusKeys(final DdiFocusImageButton button) {
            this.button = button;
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case 10:
                case 32: {
                    DdiFocusImageButton.this.buttonListener.actionPerformed(new ActionEvent(keyEvent.getSource(), 1001, "KEY_TYPED"));
                    break;
                }
                case 9: {
                    if (keyEvent.isShiftDown()) {
                        DdiFocusImageButton.this.previousFocusableComponent.requestFocus();
                        this.button.removeKeyListener(this);
                        break;
                    }
                    DdiFocusImageButton.this.nextFocusableComponent.requestFocus();
                    this.button.removeKeyListener(this);
                    break;
                }
            }
        }
    }
}
