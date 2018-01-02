import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.text.NumberFormat;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Scrollbar;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class EulerFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseListener, MouseMotionListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Choice modeChooser;
    Choice centerChooser;
    Checkbox animateCheck;
    Scrollbar zoomBar;
    Scrollbar termsBar;
    double zoom;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    int[] xpoints;
    int[] ypoints;
    int pause;
    Euler applet;
    static final int MODE_RESULT = 0;
    static final int MODE_ARG = 1;
    double zr;
    double zi;
    double orgx;
    double orgy;
    boolean mouseDown;
    EulerCanvas cv;
    long lastTime;
    
    public String getAppletInfo() {
        return "Euler by Paul Falstad";
    }
    
    EulerFrame(final Euler applet) {
        super("Euler's Formula Applet v1.1");
        this.engine = null;
        this.zr = 0.0;
        this.zi = 2.356194490192345;
        this.orgx = 0.0;
        this.orgy = 0.0;
        this.lastTime = 0L;
        this.applet = applet;
    }
    
    public void init() {
        this.setLayout(new EulerLayout());
        (this.cv = new EulerCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        (this.animateCheck = new Checkbox("Animate")).addItemListener(this);
        this.animateCheck.setState(true);
        this.add(this.animateCheck);
        (this.modeChooser = new Choice()).add("Mouse = Select exp(z)");
        this.modeChooser.add("Mouse = Select z");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.centerChooser = new Choice()).add("Center = Origin");
        this.centerChooser.add("Center = exp(z)");
        this.centerChooser.addItemListener(this);
        this.add(this.centerChooser);
        this.add(new Label("Zoom", 1));
        this.add(this.zoomBar = new Scrollbar(0, 96, 1, 60, 200));
        this.zoomBar.addAdjustmentListener(this);
        this.add(new Label("# of Terms", 1));
        this.add(this.termsBar = new Scrollbar(0, 50, 1, 1, 50));
        this.termsBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.resize(550, 550);
        this.handleResize();
        this.show();
    }
    
    void handleResize() {
        this.reinit();
    }
    
    void reinit() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateEuler(final Graphics graphics) {
        this.zoom = Math.exp(-(this.zoomBar.getValue() - 100) * 0.3);
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        final double exp = Math.exp(this.zr);
        final double orgx = Math.cos(this.zi) * exp;
        final double orgy = Math.sin(this.zi) * exp;
        if (this.centerChooser.getSelectedIndex() != 0) {
            this.orgx = orgx;
            this.orgy = orgy;
        }
        else {
            final double n = 0.0;
            this.orgy = n;
            this.orgx = n;
        }
        graphics2.setColor(Color.darkGray);
        int i;
        for (i = 1; i * 10 < this.zoom; i *= 10) {}
        do {
            for (int j = -10; j <= 10; ++j) {
                this.map2d(j * i, -10 * i, 0);
                this.map2d(j * i, 10 * i, 1);
                this.drawLine(graphics2);
                this.map2d(-10 * i, j * i, 0);
                this.map2d(10 * i, j * i, 1);
                this.drawLine(graphics2);
            }
            i /= 10;
        } while (i > 0);
        final int value = this.termsBar.getValue();
        if (value < 20) {
            for (int k = -100; k <= 100; ++k) {
                double n2 = 1.0;
                double n3 = 0.0;
                double n4 = 0.0;
                double n5 = 0.0;
                final double n6 = 3.141592653589793 * k / 50.0;
                for (int l = 0; l < value; ++l) {
                    n4 += n2;
                    n5 += n3;
                    final double n7 = n2 * this.zr - n3 * n6;
                    final double n8 = n3 * this.zr + n2 * n6;
                    n2 = n7 / (l + 1);
                    n3 = n8 / (l + 1);
                }
                this.map2d(n4, n5, 1);
                if (k > -100) {
                    this.drawLine(graphics2);
                }
                this.xpoints[0] = this.xpoints[1];
                this.ypoints[0] = this.ypoints[1];
            }
        }
        this.map2d(-exp, exp, 0);
        this.map2d(exp, -exp, 1);
        this.drawOval(graphics2, this.xpoints[0], this.ypoints[0], this.xpoints[1] - this.xpoints[0], this.ypoints[1] - this.ypoints[0]);
        this.map2d(this.zr, this.zi, 0);
        this.map2d(this.zr + 1.0, this.zi, 1);
        this.drawLine(graphics2);
        graphics2.setColor(Color.gray);
        this.map2d(0.0, 0.0, 0);
        this.drawLine(graphics2, 0, this.ypoints[0], this.winSize.width - 1, this.ypoints[0]);
        this.drawLine(graphics2, this.xpoints[0], 0, this.xpoints[0], this.winSize.height - 1);
        this.map2d(-1.0, 1.0, 0);
        this.map2d(1.0, -1.0, 1);
        this.drawOval(graphics2, this.xpoints[0], this.ypoints[0], this.xpoints[1] - this.xpoints[0], this.ypoints[1] - this.ypoints[0]);
        double n9 = 1.0;
        double n10 = 0.0;
        double n11 = 0.0;
        double n12 = 0.0;
        int n13 = 0;
        this.map2d(0.0, 0.0, 0);
        this.map2d(orgx, orgy, 1);
        this.drawLine(graphics2);
        graphics2.setColor(Color.white);
        while (n13 < value) {
            graphics2.setColor((n13 % 2 == 0) ? Color.blue : Color.cyan);
            this.map2d(n11, n12, 0);
            n11 += n9;
            n12 += n10;
            this.map2d(n11, n12, 1);
            this.drawLine(graphics2);
            final double n14 = n9 * this.zr - n10 * this.zi;
            final double n15 = n10 * this.zr + n9 * this.zi;
            n9 = n14 / (n13 + 1);
            n10 = n15 / (n13 + 1);
            ++n13;
        }
        graphics2.setColor(Color.red);
        this.drawDot(graphics2, orgx, orgy);
        graphics2.setColor(Color.green);
        this.drawDot(graphics2, this.zr, this.zi);
        final int n16 = 20;
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, this.winSize.height - n16, this.winSize.width - 1, n16);
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(3);
        final FontMetrics fontMetrics = graphics2.getFontMetrics();
        final String string = "z = " + this.formatNumber(this.zr, this.zi, instance);
        final String string2 = "  exp(z) = " + this.formatNumber(orgx, orgy, instance);
        final int n17 = this.winSize.height - n16 / 4;
        graphics2.setColor(Color.green);
        graphics2.drawString(string, 10, n17);
        graphics2.setColor(Color.red);
        graphics2.drawString(string2, 10 + fontMetrics.stringWidth(string), n17);
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (this.animateCheck.getState() && !this.mouseDown) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime == 0L) {
                this.lastTime = currentTimeMillis;
            }
            this.zi += 0.031415926535897934 * (currentTimeMillis - this.lastTime) / 17.0;
            if (this.zi > 3.141592653589793) {
                this.zi = -3.141592653589793;
            }
            this.lastTime = currentTimeMillis;
            this.cv.repaint(this.pause);
        }
        else {
            this.lastTime = 0L;
        }
    }
    
    String formatNumber(final double n, final double n2, final NumberFormat numberFormat) {
        if (n == 0.0) {
            if (n2 == 0.0) {
                return "0";
            }
            return numberFormat.format(n2) + "i";
        }
        else {
            if (n2 == 0.0) {
                return numberFormat.format(n);
            }
            return numberFormat.format(n) + ((n2 < 0.0) ? "" : "+") + numberFormat.format(n2) + "i";
        }
    }
    
    void drawOval(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.onScreen(n, n2) && this.onScreen(n + n3, n2 + n4)) {
            graphics.drawOval(n, n2, n3, n4);
        }
    }
    
    boolean onScreen(final int n, final int n2) {
        return n >= 0 && n < this.winSize.width && n2 >= 0 && n2 < this.winSize.height;
    }
    
    boolean maxInt(final int n) {
        return n == -214748368 || n == Integer.MAX_VALUE;
    }
    
    void drawLine(final Graphics graphics) {
        this.drawLine(graphics, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
    }
    
    void drawLine(final Graphics graphics, int n, int n2, int n3, int n4) {
        if (this.onScreen(n, n2) && this.onScreen(n3, n4)) {
            graphics.drawLine(n, n2, n3, n4);
            return;
        }
        if (this.maxInt(n) || this.maxInt(n2) || this.maxInt(n3) || this.maxInt(n4)) {
            return;
        }
        if ((n < 0 && n3 < 0) || (n2 < 0 && n4 < 0) || (n >= this.winSize.width && n3 >= this.winSize.width) || (n2 >= this.winSize.height && n4 >= this.winSize.height)) {
            return;
        }
        if (n == n3) {
            if (n2 < 0) {
                n2 = 0;
            }
            if (n4 < 0) {
                n4 = 0;
            }
            if (n2 >= this.winSize.height) {
                n2 = this.winSize.height - 1;
            }
            if (n4 >= this.winSize.height) {
                n4 = this.winSize.height - 1;
            }
            graphics.drawLine(n, n2, n, n4);
            return;
        }
        final double n5 = (n4 - n2) / (n3 - n);
        if (n < 0) {
            n2 -= (int)(n * n5);
            n = 0;
        }
        if (n2 < 0) {
            n -= (int)(n2 / n5);
            n2 = 0;
        }
        if (n3 < 0) {
            n4 -= (int)(n3 * n5);
            n3 = 0;
        }
        if (n4 < 0) {
            n3 -= (int)(n4 / n5);
            n4 = 0;
        }
        if (n >= this.winSize.width) {
            final int n6 = this.winSize.width - 1 - n;
            n2 += (int)(n6 * n5);
            n += n6;
        }
        if (n3 >= this.winSize.width) {
            final int n7 = this.winSize.width - 1 - n3;
            n4 += (int)(n7 * n5);
            n3 += n7;
        }
        if (n2 >= this.winSize.height) {
            final int n8 = this.winSize.height - 1 - n2;
            n2 += n8;
            n += (int)(n8 / n5);
        }
        if (n4 >= this.winSize.height) {
            final int n9 = this.winSize.height - 1 - n4;
            n4 += n9;
            n3 += (int)(n9 / n5);
        }
        if (this.onScreen(n, n2) && this.onScreen(n3, n4)) {
            graphics.drawLine(n, n2, n3, n4);
        }
    }
    
    void drawDot(final Graphics graphics, final double n, final double n2) {
        this.map2d(n, n2, 0);
        if (this.onScreen(this.xpoints[0], this.ypoints[0])) {
            graphics.fillOval(this.xpoints[0] - 2, this.ypoints[0] - 2, 5, 5);
        }
    }
    
    void map2d(final double n, final double n2, final int n3) {
        this.xpoints[n3] = (int)(this.winSize.width * (this.zoom + n - this.orgx) / (this.zoom * 2.0));
        this.ypoints[n3] = (int)(this.winSize.height * (this.zoom - n2 + this.orgy) / (this.zoom * 2.0));
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(this.pause);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.cv.repaint(this.pause);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.doMouse(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.doMouse(mouseEvent);
    }
    
    void doMouse(final MouseEvent mouseEvent) {
        this.mouseDown = true;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        double zr = x * this.zoom * 2.0 / this.winSize.width - this.zoom + this.orgx;
        final double zi = -(y * this.zoom * 2.0 / this.winSize.height - this.zoom - this.orgy);
        if (this.modeChooser.getSelectedIndex() == 0) {
            if (zr == 0.0 && zi == 0.0) {
                zr = 1.0E-5;
            }
            this.zr = 0.5 * Math.log(zr * zr + zi * zi);
            this.zi = Math.atan2(zi, zr);
        }
        else {
            this.zr = zr;
            this.zi = zi;
        }
        this.cv.repaint(this.pause);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseDown = false;
        this.cv.repaint(this.pause);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.cv.repaint(this.pause);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
}
