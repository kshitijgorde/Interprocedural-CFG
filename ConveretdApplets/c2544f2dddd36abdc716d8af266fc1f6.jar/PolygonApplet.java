import javax.swing.border.Border;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolygonApplet extends Applet
{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() {
        final double[] pops = { -1.0, 10.0, 17.0, 20.0, 21.0, 12.0, 18.0, 10.0 };
        final boolean improveLayout = true;
        final boolean simpleVersion = true;
        final int diagramType = 3;
        final PolygonPanel polygonPanel = new PolygonPanel(diagramType, improveLayout, simpleVersion, pops);
        final Dimension polygonPanelSize = new Dimension(500, 500);
        polygonPanel.setPreferredSize(polygonPanelSize);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final ExperimentPanel experimentPanel = new ExperimentPanel(polygonPanel, diagramType, 1, improveLayout, simpleVersion, true);
        experimentPanel.setBorder(emptyBorder);
        final GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = 11;
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(polygonPanel, c);
        this.add(polygonPanel);
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(experimentPanel, c);
        this.add(experimentPanel);
        this.setVisible(true);
        experimentPanel.requestFocus();
    }
    
    @Override
    public String getAppletInfo() {
        return "Three Polygon Area-Proportional Venn Diagrams by Peter Rodgers and Jean Flower";
    }
}
