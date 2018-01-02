import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.MediaTracker;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TF_Ripple extends Applet implements MouseListener, MouseMotionListener, Runnable
{
    private Thread _$8930;
    private MediaTracker _$1788;
    private MemoryImageSource _$171177;
    private Image _$34822;
    private Image _$34823;
    private String _$34824;
    private String _$3253;
    private Graphics _$34825;
    private int[][][] _$34826;
    private int[][] _$34827;
    private int[] _$1916;
    private int[] _$34828;
    private int[] _$34829;
    private int _$59061;
    private double _$176409;
    private double _$176410;
    private Color _$6634;
    private Color _$8929;
    private int _$1819;
    private int _$1820;
    private int _$34832;
    private int _$34833;
    private double _$55541;
    private double _$55542;
    private static int _$34834;
    private static int _$171178;
    private static int _$34835;
    private static int _$1821;
    private static int _$34836;
    private static int _$34837;
    private static int _$1817;
    private boolean _$23702;
    private boolean _$8968;
    private boolean _$443668;
    
    public TF_Ripple() {
        this._$8930 = null;
        this._$171177 = null;
        this._$34822 = null;
        this._$34823 = null;
        this._$34824 = null;
        this._$3253 = null;
        this._$34825 = null;
        this._$59061 = 0;
        this._$176409 = 0.07;
        this._$176410 = 0.08;
        this._$6634 = new Color(0);
        this._$8929 = new Color(16777215);
        this._$34833 = 0;
        this._$23702 = false;
        this._$8968 = true;
        this._$443668 = true;
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        this._$1819 = this.getSize().width;
        this._$1820 = this.getSize().height;
        this._$34829 = new int[this._$1820];
        this._$1916 = new int[this._$1819 * this._$1820];
        this._$34828 = new int[this._$1819 * this._$1820];
        this._$34826 = new int[2][this._$1819][this._$1820];
        this._$34827 = new int[TF_Ripple._$34834 * 2][TF_Ripple._$34834 * 2];
        this._$1788 = new MediaTracker(this);
        this._$1841();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        System.gc();
    }
    
    private Color _$1865(String color) {
        final StringBuffer temp = new StringBuffer();
        char c = ' ';
        for (int i = 0; i < color.length(); ++i) {
            c = color.charAt(i);
            if (c == ' ') {
                temp.append("");
            }
            else {
                temp.append(c);
            }
        }
        color = temp.toString();
        if (color != null && color.length() >= 5) {
            try {
                int i = color.indexOf(",");
                int red = Integer.parseInt(color.substring(0, i));
                color = color.substring(i + 1);
                i = color.indexOf(",");
                int green = Integer.parseInt(color.substring(0, i));
                int blue = Integer.parseInt(color.substring(i + 1));
                red = ((red > 255) ? 255 : red);
                red = ((red < 0) ? 0 : red);
                green = ((green > 255) ? 255 : green);
                green = ((green < 0) ? 0 : green);
                blue = ((blue > 255) ? 255 : blue);
                blue = ((blue < 0) ? 0 : blue);
                return new Color(red, green, blue);
            }
            catch (Exception e) {
                return new Color(16777215);
            }
        }
        return new Color(16777215);
    }
    
    private Frame _$1860(final Component c) {
        if (c != null && !(c instanceof Frame)) {
            return (Frame)c.getParent();
        }
        return null;
    }
    
    private void _$35392() {
        this._$35393((int)(1 + Math.random() * (this._$1819 - 2)), (int)(1 + Math.random() * (this._$1820 - 2)));
    }
    
    private void _$46864() {
        final int changeMove = 100;
        this._$176409 += Math.cos(this._$59061 % changeMove) * 0.01;
        this._$176410 += Math.cos(this._$59061 % changeMove) * 0.02;
        this._$176409 = ((this._$176409 <= 0.02) ? 0.04 : ((this._$176409 >= 1.0) ? 0.04 : this._$176409));
        this._$176410 = ((this._$176410 <= 0.02) ? 0.05 : ((this._$176410 >= 1.0) ? 0.05 : this._$176410));
        this._$55541 += this._$176409;
        this._$55542 += this._$176410;
        this._$35393((int)(Math.cos(this._$55541) * 100 + this._$1819 / 2), (int)(Math.sin(this._$55542) * 100 + this._$1820 / 2));
    }
    
    private void _$34838() {
        final int r = TF_Ripple._$34834 * TF_Ripple._$34834;
        for (int x = -TF_Ripple._$34834; x < TF_Ripple._$34834; ++x) {
            final int squarex = x * x;
            for (int y = -TF_Ripple._$34834; y < TF_Ripple._$34834; ++y) {
                final int squarey = y * y;
                final double dist = Math.sqrt(squarex + squarey);
                if (squarex + squarey < r) {
                    final int[] array = this._$34827[TF_Ripple._$34834 + x];
                    final int n = TF_Ripple._$34834 + y;
                    array[n] += (int)(TF_Ripple._$171178 * (TF_Ripple._$34834 - dist));
                }
            }
        }
    }
    
    private void _$35393(final int cx, final int cy) {
        final int r = TF_Ripple._$34834 * TF_Ripple._$34834;
        final int left = (cx < TF_Ripple._$34834) ? (-cx + 1) : (-TF_Ripple._$34834);
        final int right = (cx > this._$1819 - 1 - TF_Ripple._$34834) ? (this._$1819 - 1 - cx) : TF_Ripple._$34834;
        final int top = (cy < TF_Ripple._$34834) ? (-cy + 1) : (-TF_Ripple._$34834);
        final int bottom = (cy > this._$1820 - 1 - TF_Ripple._$34834) ? (this._$1820 - 1 - cy) : TF_Ripple._$34834;
        for (int x = left; x < right; ++x) {
            final int xsqr = x * x;
            for (int y = top; y < bottom; ++y) {
                if (xsqr + y * y < r) {
                    final int[] array = this._$34826[this._$34833 ^ 0x1][cx + x];
                    final int n = cy + y;
                    array[n] += this._$34827[TF_Ripple._$34834 + x][TF_Ripple._$34834 + y];
                }
            }
        }
    }
    
    public void mouseMoved(final MouseEvent me) {
        if (!this._$23702) {
            this._$23702 = true;
        }
        this._$35393(me.getX(), me.getY());
    }
    
    public void mouseClicked(final MouseEvent me) {
        this._$35393(me.getX(), me.getY());
    }
    
    public void mouseExited(final MouseEvent me) {
        this._$23702 = false;
        this._$1860(this).setCursor(0);
    }
    
    public void mouseEntered(final MouseEvent me) {
        this._$23702 = true;
        if (!this._$34824.equals("")) {
            this._$1860(this).setCursor(12);
        }
    }
    
    public void mousePressed(final MouseEvent me) {
        if (!this._$34824.equals("")) {
            URL link = null;
            if (this._$34824 != null) {
                try {
                    link = new URL(this.getDocumentBase(), this._$34824);
                    this.getAppletContext().showDocument(link, this._$3253);
                }
                catch (MalformedURLException e) {
                    link = null;
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent me) {
    }
    
    public void mouseDragged(final MouseEvent me) {
    }
    
    public void paint(final Graphics g) {
        if (this._$34822 != null) {
            g.drawImage(this._$34822, 0, 0, null);
        }
    }
    
    public void update(final Graphics g) {
        if (this._$8968) {
            g.setColor(this._$6634);
            g.fillRect(0, 0, this._$1819, this._$1820);
            g.setColor(this._$8929);
            g.drawString("Please Wait . . .", this._$1819 / 2 - 30, this._$1820 / 2 + 5);
        }
        this._$171177.newPixels(0, 0, this._$1819, this._$1820);
        this._$34825.drawImage(this._$34822, 0, 0, null);
        g.drawImage(this._$34823, 0, 0, null);
    }
    
    public void start() {
        if (this._$8930 == null) {
            (this._$8930 = new Thread(this)).setPriority(1);
            this._$8930.start();
        }
    }
    
    public void stop() {
        if (this._$8930 != null) {
            this._$8930.stop();
            this._$8930 = null;
        }
    }
    
    public void run() {
        this.repaint();
        try {
            this._$1788.addImage(this._$34823, 0);
            this._$1788.waitForAll();
            final PixelGrabber pg = new PixelGrabber(this._$34823, 0, 0, this._$1819, this._$1820, this._$1916, 0, this._$1819);
            pg.grabPixels();
            System.arraycopy(this._$1916, 0, this._$34828, 0, this._$1819 * this._$1820);
            (this._$171177 = new MemoryImageSource(this._$1819, this._$1820, this._$34828, 0, this._$1819)).setAnimated(true);
            this._$34822 = this.createImage(this._$171177);
            this._$34823 = this.createImage(this._$1819, this._$1820);
            this._$34825 = this._$34823.getGraphics();
            this._$34838();
            for (int y = 0; y < this._$1820; ++y) {
                this._$34829[y] = y * this._$1819;
            }
            this._$34832 = this._$1819 * this._$1820 - 1;
        }
        catch (Exception ex) {}
        this._$8968 = false;
        while (this._$8930 != null) {
            this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
            this._$58300();
            this._$58301();
            this.repaint();
            this._$34833 ^= 0x1;
            try {
                if (!this._$23702 && this._$443668) {
                    if (this._$59061 % TF_Ripple._$1817 == 0) {
                        this._$46864();
                    }
                    ++this._$59061;
                }
                Thread.currentThread();
                Thread.sleep(2L);
                continue;
            }
            catch (InterruptedException e) {
                this.stop();
                continue;
            }
            finally {
                System.gc();
            }
            break;
        }
    }
    
    private void _$58300() {
        for (int x = 1; x < this._$1819 - 1; ++x) {
            for (int y = 1; y < this._$1820 - 1; ++y) {
                final int n = y - 1;
                final int s = y + 1;
                final int e = x + 1;
                final int w = x - 1;
                final int value = (this._$34826[this._$34833][w][n] + this._$34826[this._$34833][x][n] + this._$34826[this._$34833][e][n] + this._$34826[this._$34833][w][y] + this._$34826[this._$34833][e][y] + this._$34826[this._$34833][w][s] + this._$34826[this._$34833][x][s] + this._$34826[this._$34833][e][s] >> 2) - this._$34826[this._$34833 ^ 0x1][x][y];
                this._$34826[this._$34833 ^ 0x1][x][y] = value - (value >> TF_Ripple._$34835);
            }
        }
    }
    
    private void _$58301() {
        for (int y = 0; y < this._$1820 - 1; ++y) {
            for (int x = 0; x < this._$1819 - 1; ++x) {
                final int deltax = this._$34826[this._$34833][x][y] - this._$34826[this._$34833][x + 1][y];
                final int deltay = this._$34826[this._$34833][x][y] - this._$34826[this._$34833][x][y + 1];
                final int offsetx = (deltax >> TF_Ripple._$34836) + x;
                final int offsety = (deltay >> TF_Ripple._$34837) + y;
                int offset = (offsety * this._$1819 + offsetx) % this._$34832;
                offset = ((offset < 0) ? (this._$34832 + offset) : offset);
                final int pixel = this._$1916[offset];
                int red = pixel >> 16 & 0xFF;
                int green = pixel >> 8 & 0xFF;
                int blue = pixel & 0xFF;
                final int light = deltax >> TF_Ripple._$1821;
                red -= light;
                green -= light;
                blue -= light;
                red = ((red > 255) ? 255 : ((red < 0) ? 0 : red));
                green = ((green > 255) ? 255 : ((green < 0) ? 0 : green));
                blue = ((blue > 255) ? 255 : ((blue < 0) ? 0 : blue));
                this._$34828[this._$34829[y] + x] = (0xFF000000 | red << 16 | green << 8 | blue);
            }
        }
    }
    
    private void _$1841() {
        try {
            this._$6634 = this._$1865(this.getParameter("bg_color"));
        }
        catch (Exception e) {
            this._$6634 = new Color(0);
        }
        try {
            this._$8929 = this._$1865(this.getParameter("fg_color"));
        }
        catch (Exception e) {
            this._$8929 = new Color(16777215);
        }
        try {
            final String param = this.getParameter("auto_splash");
            this._$443668 = (param.equalsIgnoreCase("yes") || param.equalsIgnoreCase("1"));
        }
        catch (Exception e) {
            this._$443668 = true;
        }
        try {
            this._$34823 = this.getImage(this.getDocumentBase(), this.getParameter("image"));
        }
        catch (Exception e) {
            this._$34823 = null;
        }
        try {
            this._$34824 = this.getParameter("link");
        }
        catch (Exception e) {
            this._$34824 = "http://www.fouda.de";
        }
        try {
            this._$34824 = this.getParameter("target");
        }
        catch (Exception e) {
            this._$34824 = "_top";
        }
    }
    
    public String getAppletInfo() {
        return "Name : TF_Ripple\r\nAuthor : Tarek Fouda\r\nContact : tarek@fouda.de\r\nHomepage : http://www.fouda.de\r\nLast Modify : 06.06.2002\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";
    }
    
    static {
        TF_Ripple._$34834 = 16;
        TF_Ripple._$171178 = 480;
        TF_Ripple._$34835 = 7;
        TF_Ripple._$1821 = 6;
        TF_Ripple._$34836 = 7;
        TF_Ripple._$34837 = 7;
        TF_Ripple._$1817 = 2;
    }
}
