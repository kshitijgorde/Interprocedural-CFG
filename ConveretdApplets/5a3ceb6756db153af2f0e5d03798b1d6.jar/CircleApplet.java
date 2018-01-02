import javax.swing.border.Border;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CircleApplet extends Applet
{
    public void init() {
        final double[] pops = { 0.0, 15.0, 30.0, 8.0, 3.0, 4.0, 6.0, 6.0 };
        final CircleLayout[] circles = LayoutAlgorithms.computeThreeCircleLayout(pops);
        final Rectangle bbox = new Rectangle(80, 80, 400, 400);
        CircleGeometry.transformCircles(circles, bbox);
        final Dimension circlePanelSize = new Dimension(560, 560);
        final CirclePanel circlePanel = new CirclePanel(circles, pops);
        circlePanel.setPreferredSize(circlePanelSize);
        circlePanel.runHillClimber(false);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final ExperimentPanel experimentPanel = new ExperimentPanel(circlePanel, true, 1);
        experimentPanel.setBorder(emptyBorder);
        final GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = 11;
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(circlePanel, c);
        this.add(circlePanel);
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(experimentPanel, c);
        this.add(experimentPanel);
        this.setVisible(true);
        experimentPanel.requestFocus();
    }
    
    public String getAppletInfo() {
        return "3 Circle Area Proportional Venn Diagrams by Stirling Chow and Peter Rodgers";
    }
}
