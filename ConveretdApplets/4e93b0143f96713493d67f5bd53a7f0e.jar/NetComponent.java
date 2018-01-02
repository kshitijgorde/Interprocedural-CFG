import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

public class NetComponent extends JLabel implements MouseListener
{
    private static ImageIcon _imgCloud;
    private char _network;
    private int _index;
    
    public NetComponent(final int x, final int y, final char network, final int index) {
        this._network = network;
        this._index = index;
        this.setLocation(x, y);
        this.setBackground(Main.WINDOW_COLOR);
        this.setForeground(Color.BLACK);
        this.setHorizontalTextPosition(0);
        this.setHorizontalAlignment(0);
        this.setVerticalTextPosition(0);
        this.setVerticalAlignment(0);
        this.setText("<html>&nbsp;&nbsp;Network " + this._network + "</html>");
        this.addMouseListener(this);
        if (NetComponent._imgCloud == null) {
            final ImageLoader loader = new ImageLoader();
            NetComponent._imgCloud = loader.getImageIcon("images/netreg.gif");
        }
        final int width = (NetComponent._imgCloud != null) ? (NetComponent._imgCloud.getIconWidth() + 2) : 98;
        final int height = (NetComponent._imgCloud != null) ? (NetComponent._imgCloud.getIconHeight() + 2) : 74;
        if (this.getSize().getWidth() != width || this.getSize().getHeight() != height) {
            this.setSize(width, height);
        }
        this.setIcon(NetComponent._imgCloud);
    }
    
    public Point getNorthWest() {
        final Point pointNW = new Point(this.getX() + 16, this.getY() + 16);
        return pointNW;
    }
    
    public Point getNorthEast() {
        final Point pointNE = new Point(this.getX() + this.getWidth() - 9, this.getY() + 17);
        return pointNE;
    }
    
    public Point getSouthWest() {
        final Point pointSW = new Point(this.getX() + 20, this.getY() + this.getHeight() - 12);
        return pointSW;
    }
    
    public Point getSouthEast() {
        final Point pointSE = new Point(this.getX() + this.getWidth() - 14, this.getY() + this.getHeight() - 10);
        return pointSE;
    }
    
    public Point getNorth() {
        final Point pointN = new Point(this.getX() + this.getWidth() / 2 + 4, this.getY() + 0);
        return pointN;
    }
    
    public Point getSouth() {
        final Point pointS = new Point(this.getX() + this.getWidth() / 2 + 4, this.getY() + this.getHeight() - 2);
        return pointS;
    }
    
    public Point getEast() {
        final Point pointE = new Point(this.getX() + this.getWidth() - 4, this.getY() + this.getHeight() / 2 + 0);
        return pointE;
    }
    
    public Point getWest() {
        final Point pointW = new Point(this.getX() + 0, this.getY() + this.getHeight() / 2 + 0);
        return pointW;
    }
    
    public void mouseClicked(final MouseEvent e) {
        UI_NetRoute._tabMain.setSelectedIndex(this._index);
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    static {
        NetComponent._imgCloud = null;
    }
}
