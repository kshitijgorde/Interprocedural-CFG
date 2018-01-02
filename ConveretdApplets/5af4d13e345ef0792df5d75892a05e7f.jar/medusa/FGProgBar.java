// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.awt.Frame;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.JFrame;

abstract class FGProgBar
{
    private JFrame parent;
    private String text;
    private JWindow win;
    private boolean indeterminate;
    private JProgressBar prog;
    
    public abstract void doWork();
    
    public FGProgBar(final JFrame parent, final String s) throws IllegalArgumentException {
        this.indeterminate = true;
        this.parent = parent;
        if (this.parent == null) {
            throw new IllegalArgumentException("parent == null !");
        }
        this.text = s;
        if (this.text == null) {
            this.text = "Processing";
        }
        (this.prog = new JProgressBar()).setStringPainted(true);
        this.prog.setString(s);
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.add(this.prog);
        (this.win = new JWindow(parent)).setSize(200, 25);
        this.win.getContentPane().add(panel);
        this.win.pack();
    }
    
    public void setIndeterminate(final boolean indeterminate) {
        this.indeterminate = indeterminate;
    }
    
    public void setProgress(final int value) {
        this.prog.setValue(value);
    }
    
    public void show() {
        this.win.setVisible(true);
        new Thread(new Runnable() {
            public void run() {
                FGProgBar.this.doWork();
                FGProgBar.this.win.setVisible(false);
                FGProgBar.this.win.dispose();
            }
        }).start();
    }
}
