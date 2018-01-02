import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Label;
import I.I;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

final class T extends Dialog
{
    private static Frame D;
    private Button add;
    
    private T(final Frame frame, final String s) {
        super(frame, ztmPlayer.legalPlayerName, true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.add = new Button(I.I(591));
        int n = 0;
        int i;
        do {
            i = s.indexOf(I.I(594), n);
            String s2;
            if (i >= 0) {
                s2 = s.substring(n, i);
                n = 1 + i;
            }
            else {
                s2 = s.substring(n);
            }
            final Label label = new Label(I.I(596) + s2);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            final boolean fill = false;
            gridBagConstraints4.gridwidth = (fill ? 1 : 0);
            gridBagConstraints3.ipady = (fill ? 1 : 0);
            gridBagConstraints2.fill = (fill ? 1 : 0);
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
            final double n2 = 0.0;
            gridBagConstraints6.weighty = n2;
            gridBagConstraints5.weightx = n2;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 20;
            gridBagConstraints.ipadx = 5;
            if (i < 0) {
                gridBagConstraints.gridheight = -1;
            }
            else {
                gridBagConstraints.gridheight = 11;
            }
            layout.setConstraints(label, gridBagConstraints);
            this.add(label);
        } while (i >= 0);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 20;
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        final boolean gridheight = false;
        gridBagConstraints9.ipady = (gridheight ? 1 : 0);
        gridBagConstraints8.ipadx = (gridheight ? 1 : 0);
        gridBagConstraints7.gridheight = (gridheight ? 1 : 0);
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
        final double n3 = 0.0;
        gridBagConstraints11.weighty = n3;
        gridBagConstraints10.weightx = n3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(5, 0, 10, 0);
        layout.setConstraints(this.add, gridBagConstraints);
        this.add.addMouseListener(new R(this));
        this.add(this.add);
        this.pack();
    }
    
    static final void I(final String s) {
        if (null == T.D) {
            T.D = new Frame(I.I(589));
        }
        final T t = new T(T.D, s);
        final Dimension size = t.getSize();
        T.D.setSize(size.width, size.height);
        final Dimension size2 = t.getSize();
        final Dimension screenSize = t.getToolkit().getScreenSize();
        t.setLocation(screenSize.width - size2.width >> 1, screenSize.height - size2.height >> 1);
        t.pack();
        t.toFront();
        t.setVisible(true);
    }
    
    static final Frame I() {
        return T.D;
    }
}
