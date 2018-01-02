// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.Frame;
import jmaster.util.swing.GUIHelper;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class GenericDialog extends JDialog implements ActionListener
{
    private static final long E = -4286916155427005004L;
    public static final int ACTION_OK = 0;
    public static final int ACTION_CANCEL = 1;
    public static final String PREFIX = "genericDialog";
    protected JButton C;
    protected JButton A;
    protected int B;
    protected GUIHelper D;
    
    public GenericDialog(final Frame frame, final JPanel panel) {
        super(frame, true);
        this.C = new JButton();
        this.A = new JButton();
        this.D = GUIHelper.getInstance();
        final JPanel panel2 = new JPanel();
        this.D.injectProperties(this, "genericDialog");
        this.D.injectProperties(panel2, "genericDialog", "panel");
        this.D.injectProperties(this.C, "genericDialog", "cmdOk");
        this.D.injectProperties(this.A, "genericDialog", "cmdCancel");
        this.C.addActionListener(this);
        this.A.addActionListener(this);
        this.getContentPane().add(panel2);
        panel2.setLayout(new BorderLayout());
        panel2.add(panel, "Center");
        final JPanel panel3 = new JPanel();
        panel3.add(this.C);
        panel3.add(this.A);
        panel2.add(panel3, "South");
        this.pack();
        this.centerParent();
        this.setResizable(false);
    }
    
    public int getActionCode() {
        return this.B;
    }
    
    public void centerParent() {
        final Container parent = this.getParent();
        final Point locationOnScreen = parent.getLocationOnScreen();
        final Dimension size = parent.getSize();
        final Dimension size2 = this.getSize();
        int x;
        if (size.width > size2.width) {
            x = (size.width - size2.width) / 2 + locationOnScreen.x;
        }
        else {
            x = locationOnScreen.x;
        }
        int y;
        if (size.height > size2.height) {
            y = (size.height - size2.height) / 2 + locationOnScreen.y;
        }
        else {
            y = locationOnScreen.y;
        }
        this.setLocation(x, y);
    }
    
    public void popup() {
        this.B = 1;
        this.setVisible(true);
    }
    
    public void dispose() {
        this.dispose(1);
    }
    
    public void dispose(final int b) {
        this.B = b;
        this.setVisible(false);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.C)) {
            this.dispose(0);
        }
        else if (actionEvent.getSource().equals(this.A)) {
            this.dispose(1);
        }
    }
}
