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

public class NetComponentSmall extends JLabel implements MouseListener
{
    private static ImageIcon _imgCloud;
    private char _network;
    private int _index;
    
    public NetComponentSmall(final int x, final int y, final char network, final int index) {
        this._network = network;
        this._index = index;
        this.setLocation(x, y);
        this.setBackground(Main.WINDOW_COLOR);
        this.setForeground(Color.BLACK);
        this.setHorizontalTextPosition(0);
        this.setHorizontalAlignment(0);
        this.setVerticalTextPosition(0);
        this.setVerticalAlignment(0);
        this.setText("<html>&nbsp;&nbsp;" + network + "</html>");
        this.addMouseListener(this);
        if (NetComponentSmall._imgCloud == null) {
            final ImageLoader loader = new ImageLoader();
            NetComponentSmall._imgCloud = loader.getImageIcon("images/netsm.gif");
        }
        final int width = (NetComponentSmall._imgCloud != null) ? (NetComponentSmall._imgCloud.getIconWidth() + 2) : 69;
        final int height = (NetComponentSmall._imgCloud != null) ? (NetComponentSmall._imgCloud.getIconHeight() + 2) : 52;
        if (this.getSize().getWidth() != width || this.getSize().getHeight() != height) {
            this.setSize(width, height);
        }
        this.setIcon(NetComponentSmall._imgCloud);
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
    
    public char getNetwork() {
        return this._network;
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
        NetComponentSmall._imgCloud = null;
    }
}
