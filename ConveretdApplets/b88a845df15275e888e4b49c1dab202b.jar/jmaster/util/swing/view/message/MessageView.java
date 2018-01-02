// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.view.message;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import jmaster.util.swing.input.ActionLabel;
import javax.swing.JLabel;
import jmaster.util.swing.GUIHelper;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MessageView extends JPanel implements ActionListener
{
    private static final long E = 8268631162146549122L;
    public static final String PREFIX = "messageView";
    protected GUIHelper B;
    protected JLabel A;
    protected JLabel D;
    protected ActionLabel C;
    
    public MessageView() {
        this.B = GUIHelper.getInstance();
        this.A = new JLabel();
        this.D = new JLabel();
        this.C = new ActionLabel();
        this.A();
    }
    
    public MessageView(final String message) {
        this.B = GUIHelper.getInstance();
        this.A = new JLabel();
        this.D = new JLabel();
        this.C = new ActionLabel();
        this.A();
        this.setMessage(message);
    }
    
    private void A() {
        this.B.injectProperties(this, "messageView", null);
        this.B.injectProperties(this.A, "messageView", "lblIcon");
        this.B.injectProperties(this.D, "messageView", "lblMessage");
        this.B.injectProperties(this.C, "messageView", "lblOk");
        this.C.addActionListener(this);
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        this.add(this.A, GUIHelper.initGBC(gridBagConstraints, 0, 0, 1, 1, 0, 0, 0, 18));
        this.add(this.D, GUIHelper.initGBC(gridBagConstraints, 1, 0, 1, 1, 1, 1, 1, 18));
        this.add(this.C, GUIHelper.initGBC(gridBagConstraints, 2, 0, 1, 1, 0, 0, 0, 12));
        this.setVisible(false);
    }
    
    public void setMessage(final String text) {
        this.setText(text);
    }
    
    public void setText(final String text) {
        this.D.setText(text);
        this.setVisible(text != null);
    }
    
    public void setError(final Exception ex) {
        if (ex == null) {
            this.setMessage(null);
        }
        else {
            this.setMessage((ex.getMessage() == null) ? ("" + ex) : ex.getMessage());
        }
    }
    
    public void setIcon(final Icon icon) {
        this.A.setIcon(icon);
    }
    
    public void setCmdOkVisible(final boolean visible) {
        this.C.setVisible(visible);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.C.equals(actionEvent.getSource())) {
            this.setMessage(null);
        }
    }
}
