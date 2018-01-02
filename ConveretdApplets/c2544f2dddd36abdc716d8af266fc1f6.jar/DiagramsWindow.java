import javax.swing.border.Border;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class DiagramsWindow extends JFrame
{
    private static final long serialVersionUID = 1L;
    
    public static void main(final String[] args) {
        new DiagramsWindow();
    }
    
    public DiagramsWindow() {
        super("Venn 3 Visualization Window");
        this.setDefaultCloseOperation(3);
        this.init();
    }
    
    private void init() {
        final double[] pops = { -1.0, 19.0, 20.0, 15.0, 25.0, 7.0, 13.0, 5.0 };
        final boolean improveLayout = true;
        final boolean simpleVersion = false;
        final int diagramType = 3;
        final int labelsStatus = 1;
        final PolygonPanel polygonPanel = new PolygonPanel(diagramType, improveLayout, simpleVersion, pops);
        final Dimension polygonPanelSize = new Dimension(500, 500);
        polygonPanel.setPreferredSize(polygonPanelSize);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final ExperimentPanel experimentPanel = new ExperimentPanel(polygonPanel, diagramType, labelsStatus, improveLayout, simpleVersion, true);
        experimentPanel.setBorder(emptyBorder);
        polygonPanel.setExperimentPanel(experimentPanel);
        this.getContentPane().add(polygonPanel, "West");
        this.getContentPane().add(experimentPanel, "East");
        this.pack();
        this.setVisible(true);
        experimentPanel.requestFocus();
        final Dimension frameDim = Toolkit.getDefaultToolkit().getScreenSize();
        final int posX = (frameDim.width - this.getSize().width) / 2;
        final int posY = (frameDim.height - this.getSize().height) / 2;
        this.setLocation(posX, posY);
    }
}
