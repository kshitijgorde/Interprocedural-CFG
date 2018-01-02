import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlotPoint extends JLabel
{
    public static final int IMG_SQUARE = 0;
    public static final int IMG_CIRCLE = 1;
    private static ImageIcon _imgSquare;
    private static ImageIcon _imgCircle;
    private static int _height;
    private static int _width;
    
    public PlotPoint(final int x, final int y, final String toolTip, final int image) {
        if (PlotPoint._imgSquare == null) {
            final ImageLoader loader = new ImageLoader();
            PlotPoint._imgSquare = loader.getImageIcon("images/square.gif");
            PlotPoint._height = PlotPoint._imgSquare.getIconHeight() + 1;
            PlotPoint._width = PlotPoint._imgSquare.getIconWidth() + 1;
        }
        if (PlotPoint._imgCircle == null) {
            final ImageLoader loader = new ImageLoader();
            PlotPoint._imgCircle = loader.getImageIcon("images/circle.gif");
        }
        this.setOpaque(false);
        this.setToolTipText(toolTip);
        this.setBounds(x - PlotPoint._width / 2, y - PlotPoint._height / 2, PlotPoint._width, PlotPoint._height);
        this.setIcon((image == 0) ? PlotPoint._imgSquare : PlotPoint._imgCircle);
    }
    
    static {
        PlotPoint._imgSquare = null;
        PlotPoint._imgCircle = null;
    }
}
