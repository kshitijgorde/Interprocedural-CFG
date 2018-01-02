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

public class HostComponent extends JLabel implements MouseListener
{
    private static ImageIcon _imgHost;
    private char _network;
    private char _host;
    
    public HostComponent(final int x, final int y, final char network, final char host) {
        this._network = network;
        this._host = host;
        this.setLocation(x, y);
        this.setBackground(Main.WINDOW_COLOR);
        this.setForeground(Color.WHITE);
        this.setHorizontalTextPosition(0);
        this.setHorizontalAlignment(0);
        this.setVerticalTextPosition(0);
        this.setVerticalAlignment(0);
        this.setText("<html>&nbsp;" + this._network + "." + this._host + "</html>");
        this.addMouseListener(this);
        if (HostComponent._imgHost == null) {
            final ImageLoader loader = new ImageLoader();
            HostComponent._imgHost = loader.getImageIcon("images/host.gif");
        }
        final int width = (HostComponent._imgHost != null) ? (HostComponent._imgHost.getIconWidth() + 2) : 48;
        final int height = (HostComponent._imgHost != null) ? (HostComponent._imgHost.getIconHeight() + 2) : 48;
        if (this.getSize().getWidth() != width || this.getSize().getHeight() != height) {
            this.setSize(width, height);
        }
        this.setIcon(HostComponent._imgHost);
    }
    
    public char getNetwork() {
        return this._network;
    }
    
    public char getHost() {
        return this._host;
    }
    
    public Point getNorthWest() {
        final Point pointNW = new Point(this.getX() + 6, this.getY() + 4);
        return pointNW;
    }
    
    public Point getNorthEast() {
        final Point pointNE = new Point(this.getX() + this.getWidth() - 2, this.getY() + 4);
        return pointNE;
    }
    
    public Point getSouthWest() {
        final Point pointSW = new Point(this.getX() + 6, this.getY() + this.getHeight());
        return pointSW;
    }
    
    public Point getSouthEast() {
        final Point pointSE = new Point(this.getX() + this.getWidth() - 2, this.getY() + this.getHeight());
        return pointSE;
    }
    
    public Point getNorth() {
        final Point pointN = new Point(this.getX() + this.getWidth() / 2, this.getY() + 2);
        return pointN;
    }
    
    public Point getSouth() {
        final Point pointS = new Point(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight());
        return pointS;
    }
    
    public Point getEast() {
        final Point pointE = new Point(this.getX() + this.getWidth() - 1, this.getY() + this.getHeight() / 2 + 2);
        return pointE;
    }
    
    public Point getWest() {
        final Point pointW = new Point(this.getX(), this.getY() + this.getHeight() / 2 + 2);
        return pointW;
    }
    
    public void mouseClicked(final MouseEvent e) {
        System.out.println("Clicked");
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
        HostComponent._imgHost = null;
    }
}
