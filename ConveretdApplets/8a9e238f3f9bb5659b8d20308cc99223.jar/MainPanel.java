import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MainPanel extends JPanel
{
    TwosComplementControl control;
    BitWordPanel bits;
    TestCompletePanel done;
    
    public MainPanel(final String op, final String[] selections) {
        this.setLayout(new GridBagLayout());
        this.bits = new BitWordPanel(op);
        this.done = new TestCompletePanel();
        this.control = new TwosComplementControl(this.bits, this.done, selections);
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = 10;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipady = 0;
        c.weightx = 1.0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(this.bits, c);
        c.gridx = 3;
        c.gridwidth = 1;
        this.done.showTheComponents(false);
        this.add(this.done, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(20, 0, 0, 0);
        this.add(this.control, c);
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.bits != null) {
            this.bits.setBackground(c);
        }
        if (this.done != null) {
            this.done.setBackground(c);
        }
        if (this.control != null) {
            this.control.setBackground(c);
        }
    }
}