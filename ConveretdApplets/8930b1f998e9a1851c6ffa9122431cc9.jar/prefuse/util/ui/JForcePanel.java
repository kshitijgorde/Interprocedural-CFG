// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import javax.swing.event.ChangeEvent;
import javax.swing.JFrame;
import java.awt.Dimension;
import prefuse.util.force.Force;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Color;
import prefuse.util.force.ForceSimulator;
import javax.swing.JPanel;

public class JForcePanel extends JPanel
{
    private ForcePanelChangeListener lstnr;
    private ForceSimulator fsim;
    
    public JForcePanel(final ForceSimulator fsim) {
        this.lstnr = new ForcePanelChangeListener();
        this.fsim = fsim;
        this.setBackground(Color.WHITE);
        this.initUI();
    }
    
    private void initUI() {
        this.setLayout(new BoxLayout(this, 1));
        final Force[] forces = this.fsim.getForces();
        for (int i = 0; i < forces.length; ++i) {
            final Force force = forces[i];
            final Box box = new Box(1);
            for (int j = 0; j < force.getParameterCount(); ++j) {
                final JValueSlider field = createField(force, j);
                field.addChangeListener(this.lstnr);
                box.add(field);
            }
            final String name = force.getClass().getName();
            box.setBorder(BorderFactory.createTitledBorder(name.substring(name.lastIndexOf(".") + 1)));
            this.add(box);
        }
    }
    
    private static JValueSlider createField(final Force force, final int n) {
        final JValueSlider valueSlider = new JValueSlider(force.getParameterName(n), force.getMinValue(n), force.getMaxValue(n), (double)force.getParameter(n));
        valueSlider.setBackground(Color.WHITE);
        valueSlider.putClientProperty("force", force);
        valueSlider.putClientProperty("param", new Integer(n));
        valueSlider.setPreferredSize(new Dimension(300, 30));
        valueSlider.setMaximumSize(new Dimension(300, 30));
        return valueSlider;
    }
    
    public static JFrame showForcePanel(final ForceSimulator forceSimulator) {
        final JFrame frame = new JFrame("prefuse Force Simulator");
        frame.setContentPane(new JForcePanel(forceSimulator));
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    
    private static class ForcePanelChangeListener implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            final JValueSlider valueSlider = (JValueSlider)changeEvent.getSource();
            ((Force)valueSlider.getClientProperty("force")).setParameter((int)valueSlider.getClientProperty("param"), valueSlider.getValue().floatValue());
        }
    }
}
