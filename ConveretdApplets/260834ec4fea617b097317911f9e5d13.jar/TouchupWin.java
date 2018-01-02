import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class TouchupWin extends Frame implements ActionListener, WindowListener
{
    private Dialog dialog;
    private Button openDialogButton;
    private Button closeDialogButton;
    private Button closeFrameButton;
    
    public TouchupWin(final String s, final String s2) {
        super(s);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.add(new TextArea(s2, 25, 80, 1));
        this.pack();
        this.show();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        final Window window = windowEvent.getWindow();
        if (window.equals(this)) {
            this.dispose();
            return;
        }
        if (window.equals(this.dialog)) {
            this.dialog.dispose();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Close")) {
            this.processEvent(new WindowEvent(this, 201));
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
}
