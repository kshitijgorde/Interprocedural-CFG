// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I8;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.Window;
import jlog.awt.$L.$I9;
import jlog.awt.$L.$G9;
import java.awt.event.WindowAdapter;

public class $GOB extends WindowAdapter implements $G9, $H9
{
    $I9 $HOB;
    Window $EX;
    String $IOB;
    
    public void $A0(final KeyEvent keyEvent) {
    }
    
    public void $B0(final KeyEvent keyEvent) {
    }
    
    public String $JOB() {
        return this.$IOB;
    }
    
    public void $Z9(final KeyEvent keyEvent) {
        final Window $ex = this.$EX;
        if (keyEvent.getKeyCode() == 27 && $ex != null) {
            this.$IOB = "Cancel";
            this.close($ex);
        }
    }
    
    public $GOB() {
        this.$EX = null;
        this.$IOB = "";
        (this.$HOB = new $I9()).$W9(this);
    }
    
    public void close(final Window window) {
        if (window != null) {
            window.setVisible(false);
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        this.$EX = windowEvent.getWindow();
        this.$HOB.$X9(this.$EX);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.$IOB = "CLOSEW";
        this.close(windowEvent.getWindow());
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        if (this.$EX == windowEvent.getWindow()) {
            this.$EX = null;
        }
        this.$HOB.removeComponent(windowEvent.getWindow());
    }
    
    public void windowOpenened(final WindowEvent windowEvent) {
        this.$IOB = "";
        windowEvent.getWindow().requestFocus();
    }
}
