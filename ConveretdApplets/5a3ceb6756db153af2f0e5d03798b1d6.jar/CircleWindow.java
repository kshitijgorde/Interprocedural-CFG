import javax.swing.border.Border;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class CircleWindow extends JFrame
{
    public static void main(final String[] args) {
        final double[] pops = { 0.0, 55.0, 183.0, 184.0, 42.0, 53.0, 195.0, 143.0 };
        final CircleLayout[] circles = LayoutAlgorithms.computeThreeCircleLayout(pops);
        final Rectangle bbox = new Rectangle(80, 80, 400, 400);
        CircleGeometry.transformCircles(circles, bbox);
        new CircleWindow(circles, pops);
    }
    
    public CircleWindow(final CircleLayout[] circles, final double[] pops) {
        super("Venn Visualization Window");
        this.setDefaultCloseOperation(3);
        final Dimension circlePanelSize = new Dimension(560, 560);
        final CirclePanel circlePanel = new CirclePanel(circles, pops);
        circlePanel.setPreferredSize(circlePanelSize);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final ExperimentPanel experimentPanel = new ExperimentPanel(circlePanel, false, 4);
        experimentPanel.setBorder(emptyBorder);
        this.getContentPane().add(circlePanel, "West");
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
