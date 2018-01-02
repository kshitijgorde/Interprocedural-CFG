// 
// Decompiled by Procyon v0.5.30
// 

package rene.gui;

import java.awt.event.FocusEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

public class CloseDialog extends Dialog implements WindowListener, ActionListener, DoActionListener, KeyListener, FocusListener
{
    boolean Dispose;
    
    public CloseDialog(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
        this.Dispose = true;
        if (Global.ControlBackground != null) {
            this.setBackground(Global.ControlBackground);
        }
        this.addWindowListener(this);
        this.addKeyListener(this);
        this.addFocusListener(this);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.close()) {
            this.doclose();
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
        if ("Close".equals(s) && this.close()) {
            this.doclose();
        }
    }
    
    public void itemAction(final String s, final boolean b) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27 && this.escape()) {
            this.doclose();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void doclose() {
        this.setVisible(false);
        if (this.Dispose) {
            this.dispose();
        }
    }
    
    public void center(final Frame frame) {
        final Dimension size = frame.getSize();
        final Dimension size2 = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Point location = frame.getLocation();
        int n = location.x + size.width / 2 - size2.width / 2;
        int n2 = location.y + size.height / 2 - size2.height / 2;
        if (n + size2.width > screenSize.width) {
            n = screenSize.width - size2.width - 10;
        }
        if (n < 10) {
            n = 10;
        }
        if (n2 + size2.height > screenSize.height) {
            n2 = screenSize.height - size2.height - 10;
        }
        if (n2 < 10) {
            n2 = 10;
        }
        this.setLocation(n, n2);
    }
    
    public void centerOut(final Frame frame) {
        final Dimension size = frame.getSize();
        final Dimension size2 = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Point location = frame.getLocation();
        int n = location.x + size.width - this.getSize().width + 20;
        int n2 = location.y + size.height / 2 + 40;
        if (n + size2.width > screenSize.width) {
            n = screenSize.width - size2.width - 10;
        }
        if (n < 10) {
            n = 10;
        }
        if (n2 + size2.height > screenSize.height) {
            n2 = screenSize.height - size2.height - 10;
        }
        if (n2 < 10) {
            n2 = 10;
        }
        this.setLocation(n, n2);
    }
    
    public void center() {
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void noteSize(final String s) {
        final Dimension size = this.getSize();
        Global.setParameter(String.valueOf(s) + ".w", size.width);
        Global.setParameter(String.valueOf(s) + ".h", size.height);
    }
    
    public void setSize(final String s) {
        if (!Global.haveParameter(String.valueOf(s) + ".w")) {
            this.pack();
            return;
        }
        final Dimension size = this.getSize();
        this.setSize(Global.getParameter(String.valueOf(s) + ".w", size.width), Global.getParameter(String.valueOf(s) + ".h", size.height));
    }
    
    public void setDispose(final boolean dispose) {
        this.Dispose = dispose;
    }
}
