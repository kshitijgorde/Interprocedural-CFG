// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Button;

public class ComputeButton extends Button
{
    private Controller onUserAction;
    
    public ComputeButton() {
        this("Compute!");
    }
    
    public ComputeButton(final String s) {
        super(s);
        this.setBackground(Color.lightGray);
        this.enableEvents(128L);
    }
    
    public void setOnUserAction(final Controller onUserAction) {
        this.onUserAction = onUserAction;
    }
    
    public Controller getOnUserAction() {
        return this.onUserAction;
    }
    
    public void processActionEvent(final ActionEvent actionEvent) {
        if (this.onUserAction != null) {
            this.onUserAction.compute();
        }
        super.processActionEvent(actionEvent);
    }
}
