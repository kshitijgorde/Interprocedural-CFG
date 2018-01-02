import javax.swing.border.Border;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolygonWindow extends JFrame
{
    private static final long serialVersionUID = 1L;
    
    public static void main(final String[] args) {
        new PolygonWindow();
    }
    
    public PolygonWindow() {
        super("Venn 3 Visualization Window");
        this.setDefaultCloseOperation(3);
        final double[] pops = { -1.0, 19.0, 100.0, 100.0, 5.0, 50.0, 13.0, 10.0 };
        final int diagramType = 3;
        final boolean improveLayout = false;
        this.init(pops, diagramType, improveLayout);
    }
    
    public PolygonWindow(final double a, final double b, final double c, final double ab, final double ac, final double bc, final double abc, final int diagramType, final boolean improveLayout, final String title, final int close_behaviour) {
        super(title);
        this.setDefaultCloseOperation(close_behaviour);
        final double[] pops = { -1.0, a, b, ab, c, ac, bc, abc };
        this.init(pops, diagramType, improveLayout);
    }
    
    private void init(final double[] pops, final int diagramType, final boolean improveLayout) {
        final PolygonPanel polygonPanel = new PolygonPanel(1, improveLayout, false, pops);
        final Dimension polygonPanelSize = new Dimension(500, 500);
        polygonPanel.setPreferredSize(polygonPanelSize);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final ExperimentPanel experimentPanel = new ExperimentPanel(polygonPanel, diagramType, 3, improveLayout, false, false);
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
