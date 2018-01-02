// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import javax.swing.Box;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.Container;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;

public class JSimpleDialog extends JDialog
{
    private JButton vCloseButton;
    static /* synthetic */ Class class$blackmagic$swing$JSimpleDialog;
    
    public JSimpleDialog(final JFrame frame, final String s, final String s2, final boolean b) {
        super(frame, s2, b);
        this.initialise(frame, this.getScrollPane(s));
    }
    
    public JSimpleDialog(final JFrame frame, final JComponent component, final String s, final boolean b) {
        super(frame, s, b);
        this.initialise(frame, this.getScrollPane(component));
    }
    
    private JScrollPane getScrollPane(final String s) {
        return this.bindScrollPane(new JLabel(s));
    }
    
    private JScrollPane getScrollPane(final JComponent component) {
        return this.bindScrollPane(component);
    }
    
    private JScrollPane bindScrollPane(final JComponent component) {
        component.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));
        return new JScrollPane(component);
    }
    
    private void initialise(final Container locationRelativeTo, final JComponent component) {
        assert component != null : "pComponent was null.";
        final Container contentPane = this.getContentPane();
        component.setEnabled(false);
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        panel.setLayout(layout);
        gridBagConstraints.insets = new Insets(17, 0, 11, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = 10;
        panel.add(this.getCloseButton(), gridBagConstraints);
        contentPane.add(Box.createVerticalStrut(11), "North");
        contentPane.add(Box.createHorizontalStrut(11), "West");
        contentPane.add(Box.createHorizontalStrut(11), "East");
        contentPane.add(component, "Center");
        contentPane.add(panel, "South");
        this.getRootPane().setDefaultButton(this.getCloseButton());
        this.pack();
        if (locationRelativeTo != null) {
            this.setLocationRelativeTo(locationRelativeTo);
        }
        else {
            JUtilities.centerWindow(this);
        }
    }
    
    private JButton getCloseButton() {
        if (this.vCloseButton != null) {
            return this.vCloseButton;
        }
        (this.vCloseButton = new JButton("Close")).setMnemonic(67);
        this.vCloseButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JSimpleDialog.this.setVisible(false);
            }
        });
        return this.vCloseButton;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        $assertionsDisabled = !((JSimpleDialog.class$blackmagic$swing$JSimpleDialog == null) ? (JSimpleDialog.class$blackmagic$swing$JSimpleDialog = class$("blackmagic.swing.JSimpleDialog")) : JSimpleDialog.class$blackmagic$swing$JSimpleDialog).desiredAssertionStatus();
    }
}
