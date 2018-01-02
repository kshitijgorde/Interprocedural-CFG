import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FourierNew extends Applet
{
    public void init() {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        panel3.setBackground(Color.lightGray);
        this.setLayout(layout);
        this.setSize(480, 400);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(panel2, gridBagConstraints);
        this.add(panel2);
        panel.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        final SigPanel sigPanel = new SigPanel();
        panel.add(sigPanel, "Center");
        panel.add(new SigControls(sigPanel), "East");
        panel.add(panel3, "North");
        final FourierControls fourierControls = new FourierControls(sigPanel);
        panel2.add(fourierControls, "North");
        final MagPanel magPanel = new MagPanel();
        panel2.add(magPanel, "Center");
        sigPanel.setControls(fourierControls, magPanel);
    }
}
