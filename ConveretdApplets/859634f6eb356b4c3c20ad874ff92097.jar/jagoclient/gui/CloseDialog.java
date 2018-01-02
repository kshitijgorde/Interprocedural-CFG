// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.KeyEvent;
import jagoclient.Global;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

public class CloseDialog extends Dialog implements WindowListener, ActionListener, DoActionListener, KeyListener
{
    public CloseDialog(final Frame frame, final String title, final boolean b) {
        super(frame, "", b);
        this.addWindowListener(this);
        this.setTitle(title);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.close()) {
            this.setVisible(false);
            this.dispose();
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public boolean close() {
        return true;
    }
    
    public boolean escape() {
        return this.close();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.doAction(actionEvent.getActionCommand());
    }
    
    public void doAction(final String s) {
        if (Global.resourceString("Close").equals(s) && this.close()) {
            this.setVisible(false);
            this.dispose();
            return;
        }
        if ("Close".equals(s) && this.close()) {
            this.setVisible(false);
            this.dispose();
        }
    }
    
    public void itemAction(final String s, final boolean b) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27 && this.close()) {
            this.dispose();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
