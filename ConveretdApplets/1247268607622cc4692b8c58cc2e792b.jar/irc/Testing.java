// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.JFrame;

public class Testing extends JFrame
{
    MutableAttributeSet newAttr;
    private JScrollPane scrollpanel;
    private JTextPane textzone;
    
    public Testing(final String title) {
        this.newAttr = new SimpleAttributeSet();
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.scrollpanel = new JScrollPane();
        this.textzone = new JTextPane();
        this.scrollpanel.getViewport().add(this.textzone);
        this.textzone.setEditable(false);
        this.scrollpanel.getViewport().add(this.textzone);
        this.getContentPane().add(this.scrollpanel);
        this.setSize(1000, 500);
        this.show();
        this.setTitle(title);
    }
    
    public void addd(final String s) {
        try {
            if (s.startsWith("E")) {
                StyleConstants.setForeground(this.newAttr, Color.BLUE);
            }
            else {
                StyleConstants.setForeground(this.newAttr, Color.RED);
            }
            this.textzone.getDocument().insertString(this.textzone.getDocument().getLength(), s + "\n", this.newAttr);
        }
        catch (BadLocationException ex2) {}
        if (this.textzone.getDocument().getLength() > 500000) {
            try {
                this.textzone.getDocument().remove(0, 10000);
            }
            catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }
}
