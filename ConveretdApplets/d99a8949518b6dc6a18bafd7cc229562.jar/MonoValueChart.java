import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Image;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class MonoValueChart extends Canvas implements MouseListener, MouseMotionListener
{
    public static final int LINK_NONE = 0;
    public static final int LINK_RECTANGLE = 1;
    public static final int LINK_FILL = 2;
    public static final int LINK_LINE = 3;
    private Vector listeners;
    private Color cInk;
    private Color cLink;
    private Image backImg;
    private int im;
    private boolean amin;
    private boolean amax;
    private double min;
    private double max;
    private int nbp;
    private double pixel_value;
    private int pixel_inter_value;
    private Vector pv;
    private double pas;
    private Point center;
    public int border;
    private MVCDisplayer pd;
    public int first;
    private Graphics gOff;
    private Image imgOff;
    private Dimension ld;
    public int ipointed;
    private int lm;
    
    public MonoValueChart() {
        this.cInk = Color.black;
        this.cLink = Color.blue;
        this.im = 1;
        this.amin = true;
        this.amax = true;
        this.nbp = 10;
        this.border = 4;
        this.ld = new Dimension(0, 0);
        this.ipointed = -1;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.listeners = new Vector(5);
        this.pv = new Vector(20);
    }
    
    public void setInk(final Color cInk) {
        this.cInk = cInk;
    }
    
    public Color getInk() {
        return this.cInk;
    }
    
    public void setLinkColor(final Color cLink) {
        this.cLink = cLink;
    }
    
    public Color getLinkColor() {
        return this.cLink;
    }
    
    public void setBackImage(final Image backImg) {
        if (this.backImg != backImg) {
            this.backImg = backImg;
            this.repaint();
        }
    }
    
    public Image getBackImage() {
        return this.backImg;
    }
    
    public void setImageMode(final int im) {
        this.im = im;
    }
    
    public int getImageMode() {
        return this.im;
    }
    
    public void setNbPoint(final int nbp) {
        this.nbp = nbp;
    }
    
    public int getNbPoint() {
        return this.nbp;
    }
    
    public void setMin(final int n) {
        this.min = n;
        this.amin = false;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public void setMax(final int n) {
        this.max = n;
        this.amax = false;
    }
    
    public double getMax(final int n) {
        return this.max;
    }
    
    public void setLinkMode(final int lm) {
        if (this.lm != lm) {
            this.lm = lm;
            this.repaint();
        }
    }
    
    public double getLinkMode() {
        return this.lm;
    }
    
    public void setBorder(final int border) {
        if (border != this.border) {
            this.border = border;
            this.repaint();
        }
    }
    
    public double getBorder() {
        return this.border;
    }
    
    public void setMVCDisplayer(final MVCDisplayer pd) {
        this.pd = pd;
        if (this.pd == null) {
            return;
        }
        this.drawOff();
    }
    
    public MVCDisplayer setMVCDisplayer() {
        return this.pd;
    }
    
    public void addMonoValueChartListener(final MonoValueChartListener monoValueChartListener) {
        if (this.listeners.indexOf(monoValueChartListener) == -1) {
            this.listeners.addElement(monoValueChartListener);
        }
    }
    
    public void removeMonoValueChartListener(final MonoValueChartListener monoValueChartListener) {
        this.listeners.removeElement(monoValueChartListener);
    }
    
    protected void notifyListenersClick() {
        final Enumeration<MonoValueChartListener> elements = this.listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().onValueCliked(this);
        }
    }
    
    public double getPixelValue() {
        return this.pixel_value;
    }
    
    protected void drawOff() {
        this.imgOff = this.createImage(this.ld.width, this.ld.height);
        if (this.imgOff == null || this.imgOff.getWidth(this) <= 0 || this.imgOff.getHeight(this) <= 0) {
            return;
        }
        this.gOff = this.imgOff.getGraphics();
        if (this.backImg != null) {
            BackGroundImageDraw.draw(this.gOff, new Rectangle(this.ld), this.backImg, this.im, this);
        }
        else {
            this.gOff.setColor(this.getBackground());
            this.gOff.fillRect(0, 0, this.ld.width, this.ld.height);
        }
        if (this.amin) {
            this.min = this.pd.getMinValue();
        }
        if (this.amax) {
            this.max = this.pd.getMaxValue();
        }
        this.max += this.max / 10.0;
        this.min -= this.min / 10.0;
        this.pas = this.pd.getYValueStep();
        final int n = this.ld.height - this.border * 2;
        final int n2 = this.ld.width - this.border * 2;
        this.pixel_value = n / (this.max - this.min);
        this.center = new Point(this.border + n2 / 10, n - (int)(this.border + this.min * this.pixel_value));
        this.pixel_inter_value = n2 / this.nbp;
        this.drawLines(this.gOff);
    }
    
    protected void drawPoints(final Graphics graphics) {
        int first = this.first;
        int n = 0;
        this.pv.removeAllElements();
        while (n < this.nbp && first < this.pd.getNbValue()) {
            final Point point = new Point(this.center.x + n * this.pixel_inter_value, this.center.y);
            graphics.setColor(this.cInk);
            this.pv.addElement(this.pd.drawPoint(graphics, first, point, -this.pixel_value));
            this.pd.drawXStep(graphics, first, point);
            this.drawLink(graphics, n++, first++);
        }
    }
    
    protected void drawLink(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.cLink);
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = this.center.x + n * this.pixel_inter_value;
        array[1] = ((n == this.pd.getNbValue() - 1) ? array[0] : (this.center.x + (n + 1) * this.pixel_inter_value));
        array2[0] = this.center.y - (int)(this.pixel_value * this.pd.getValue(n2));
        array2[1] = ((n == this.pd.getNbValue() - 1) ? array2[0] : (this.center.y - (int)(this.pixel_value * this.pd.getValue(n2 + 1))));
        switch (this.lm) {
            case 1: {
                graphics.fillRect(array[0], array2[0], this.pixel_inter_value - 1, this.center.y - array2[0]);
            }
            case 2: {
                array[2] = array[1];
                array[3] = array[0];
                array2[2] = this.center.y;
                array2[3] = this.center.y;
                graphics.fillPolygon(array, array2, 4);
            }
            case 3: {
                graphics.drawLine(array[0], array2[0], array[1], array2[1]);
            }
            default: {}
        }
    }
    
    protected void drawLines(final Graphics graphics) {
        graphics.setColor(this.cInk);
        graphics.drawLine(this.center.x, this.border, this.center.x, this.ld.height - this.border);
        int x = this.center.x;
        for (double n = 0.0; n < this.nbp; ++n) {
            graphics.drawLine(x, this.center.y, x, this.center.y + 4);
            x += this.pixel_inter_value;
        }
        graphics.drawLine(this.border, this.center.y, this.ld.width - this.border, this.center.y);
        for (double min = this.min; min < this.max; min += this.pas) {
            this.drawYBar(graphics, min);
        }
    }
    
    protected void drawYBar(final Graphics graphics, final double n) {
        final String value = String.valueOf(n);
        final int stringWidth = graphics.getFontMetrics().stringWidth(value);
        final int n2 = this.center.y - (int)(this.pixel_value * n);
        graphics.drawLine(this.center.x - 4, n2, this.center.x, n2);
        graphics.drawString(value, this.center.x - 6 - stringWidth, n2);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.gOff == null || this.getSize() != this.ld) {
            this.ld = this.getSize();
            this.drawOff();
        }
        graphics.drawImage(this.imgOff, 0, 0, this);
        this.drawPoints(graphics);
    }
    
    public boolean moveLeft(final int n) {
        if (this.first - n < 0) {
            return false;
        }
        this.first -= n;
        this.repaint();
        return true;
    }
    
    public boolean moveRight(final int n) {
        if (this.first + n >= this.pd.getNbValue() - this.nbp) {
            return false;
        }
        this.first += n;
        this.repaint();
        return true;
    }
    
    protected int pointed(final int n, final int n2) {
        int first = this.first;
        final Enumeration<Rectangle> elements = (Enumeration<Rectangle>)this.pv.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().contains(n, n2)) {
                return first;
            }
            ++first;
        }
        return -1;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.ipointed = -1;
        if (this.pd != null) {
            this.pd.onMouseExitValue(this, this.getGraphics(), this.ipointed, new Point(this.center.x + this.ipointed * this.pixel_inter_value, this.center.y));
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int pointed = this.pointed(mouseEvent.getX(), mouseEvent.getY());
        if (pointed != this.ipointed) {
            if (this.ipointed != -1) {
                this.pd.onMouseExitValue(this, this.getGraphics(), this.ipointed, new Point(this.center.x + (this.ipointed - this.first) * this.pixel_inter_value, this.center.y));
            }
            if ((this.ipointed = pointed) == -1) {
                this.setCursor(new Cursor(0));
                return;
            }
            this.setCursor(new Cursor(12));
            this.pd.onMouseEnterValue(this, this.getGraphics(), this.ipointed, new Point(this.center.x + (this.ipointed - this.first) * this.pixel_inter_value, this.center.y));
        }
    }
}
