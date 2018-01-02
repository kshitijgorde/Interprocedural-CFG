// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AppletContext;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

class InfoDlg extends Dialog implements WindowListener, ActionListener, KeyListener
{
    private static InfoDlg focalList;
    private InfoDlg nextFocalReceiver;
    private InfoDlg prevFocalReceiver;
    private Component focusReceiver;
    private Button okButton;
    private MultiLineLabel text;
    
    public InfoDlg(final Frame frame, final AppletContext appletContext, final String s, final Component focusReceiver, final String s2, final String s3, final boolean b) {
        super(frame, s2, b);
        if (InfoDlg.focalList != null) {
            InfoDlg.focalList.prevFocalReceiver = this;
        }
        this.nextFocalReceiver = InfoDlg.focalList;
        InfoDlg.focalList = this;
        this.focusReceiver = focusReceiver;
        this.setLayout(new BorderLayout(5, 5));
        this.add("Center", this.text = new MultiLineLabel(s3, appletContext, s, 20, 20, 1));
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 5, 5));
        panel.add(this.okButton = new Button("OK"));
        this.add("South", panel);
        this.addWindowListener(this);
        this.addKeyListener(this);
        this.okButton.addActionListener(this);
        this.okButton.addKeyListener(this);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.pack();
        this.setLocation((screenSize.width - this.getSize().width) / 2, (screenSize.height - this.getSize().height) / 2);
    }
    
    public void setText(final String s) {
        this.text.newLabel(s);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.closeMe();
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.okButton) {
            this.closeMe();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 10:
            case 27:
            case 32: {
                this.closeMe();
                break;
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void closeMe() {
        this.setVisible(false);
        this.dispose();
        if (this.nextFocalReceiver != null) {
            this.nextFocalReceiver.prevFocalReceiver = this.prevFocalReceiver;
        }
        if (this.prevFocalReceiver == null) {
            InfoDlg.focalList = this.nextFocalReceiver;
        }
        else {
            this.prevFocalReceiver.nextFocalReceiver = this.nextFocalReceiver;
        }
        if (InfoDlg.focalList != null) {
            InfoDlg.focalList.requestFocus();
            return;
        }
        if (this.focusReceiver != null) {
            this.focusReceiver.requestFocus();
        }
    }
    
    static {
        InfoDlg.focalList = null;
    }
}
