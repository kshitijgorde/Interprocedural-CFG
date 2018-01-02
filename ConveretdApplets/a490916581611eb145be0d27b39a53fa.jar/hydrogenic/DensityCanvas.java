// 
// Decompiled by Procyon v0.5.30
// 

package hydrogenic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import sTools.Format;
import java.awt.Image;
import java.awt.Canvas;

public final class DensityCanvas extends Canvas implements Runnable
{
    Image img;
    static String calcString;
    private Thread calcThread;
    int[] red;
    int[] green;
    int[] blue;
    int m;
    int n;
    int l;
    int ao;
    double[] magx;
    double[] modRnl;
    double[] modYlm;
    double pi;
    double ylm;
    double rnl;
    boolean showPhaseColor;
    double[] amp;
    double leg;
    double datamaxR;
    double datamaxY;
    double brightness;
    int originx;
    int originy;
    int theta;
    int radius;
    double rx;
    double ry;
    double val;
    int npr;
    int npa;
    Format format;
    int boxWidth;
    private boolean invokedStandalone;
    
    public DensityCanvas() {
        this.img = null;
        this.red = new int[256];
        this.green = new int[256];
        this.blue = new int[256];
        this.m = 0;
        this.n = 0;
        this.l = 0;
        this.magx = null;
        this.modRnl = null;
        this.modYlm = null;
        this.pi = 3.141592653589793;
        this.amp = new double[2];
        this.npr = 0;
        this.npa = 181;
        this.format = new Format("%-+6.3g");
        this.boxWidth = 0;
        this.invokedStandalone = false;
        this.makePallet();
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final int n = (Math.max(width, height) - Math.min(width, height)) / 2;
        if (this.img == null) {
            super.paint(graphics);
            graphics.setColor(Color.black);
            graphics.drawString(DensityCanvas.calcString, (width - graphics.getFontMetrics().stringWidth(DensityCanvas.calcString)) / 2, height / 2);
        }
        else {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, width, height);
            final int min = Math.min(width, height);
            final int min2 = Math.min(min, height);
            if (this.getSize().width < this.getSize().height) {
                graphics.drawImage(this.img, 0, n, min, min2, this);
            }
            else {
                graphics.drawImage(this.img, n, 0, min, min2, this);
            }
        }
    }
    
    public synchronized void stop() {
        if (this.calcThread == null) {
            return;
        }
        this.calcThread.stop();
        try {
            this.calcThread.join();
        }
        catch (InterruptedException ex) {}
        this.calcThread = null;
    }
    
    public void start() {
        if (this.calcThread == null) {
            this.calcThread = new Thread(this);
        }
        this.calcThread.start();
    }
    
    public void run() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final int[] array = new int[width * height];
        final int[] array2 = new int[4];
        int n = 0;
        this.originx = width / 2;
        this.originy = height / 2;
        this.npr = (int)Math.sqrt((this.originx + 1) * (this.originx + 1) + (this.originy + 1) * (this.originy + 1)) + 1;
        this.modRnl = new double[this.npr];
        this.modYlm = new double[this.npa];
        this.magx = new double[this.npr];
        this.calcRnl(this.modRnl);
        this.calcYlm(this.modYlm);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                final int[] array3 = array2;
                final int n2 = 0;
                final int[] array4 = array2;
                final int n3 = 1;
                final int[] array5 = array2;
                final int n4 = 2;
                final boolean b = false;
                array5[n4] = (b ? 1 : 0);
                array3[n2] = (array4[n3] = (b ? 1 : 0));
                array2[3] = 255;
                this.colorFunction(array2, j, i, width, height, this.modRnl, this.modYlm);
                array[n++] = (array2[3] << 24 | array2[0] << 16 | array2[1] << 8 | array2[2] << 0);
            }
        }
        this.newImage(width, height, array);
        this.repaint();
        this.stop();
    }
    
    synchronized void newImage(final int n, final int n2, final int[] array) {
        this.img = this.createImage(new MemoryImageSource(n, n2, ColorModel.getRGBdefault(), array, 0, n));
    }
    
    public double[] calcYlm(final double[] array) {
        final double[] array2 = new double[this.npa];
        final double sqrt = Math.sqrt((2 * this.l + 1) * SpecialFunctions.factorial(this.l - this.m) / (SpecialFunctions.factorial(this.l + this.m) * 4.0 * this.pi));
        this.datamaxY = 0.0;
        if (this.l == 0 && this.m == 0) {
            for (int i = 0; i < this.npa; ++i) {
                array[i] = Math.sqrt(1 / (4.0 * this.pi));
            }
            this.datamaxY = Math.sqrt(1 / (4.0 * this.pi));
        }
        else {
            for (int j = 0; j < this.npa; ++j) {
                array2[j] = (this.leg = SpecialFunctions.legendre(this.l, this.m, Math.cos(this.pi * j / 180.0)));
                array[j] = sqrt * this.leg;
                if (this.datamaxY < Math.abs(array[j])) {
                    this.datamaxY = Math.abs(array[j]);
                }
            }
        }
        return array;
    }
    
    public double[] calcRnl(final double[] array) {
        double n = 0.0;
        final double n2 = (4 * this.n * this.n + 2) / this.npr;
        final double n3 = 2.0 / (this.n * this.n) * Math.sqrt(SpecialFunctions.factorial(this.n - this.l - 1) / SpecialFunctions.factorial(this.n + this.l));
        this.datamaxR = 0.0;
        this.brightness = 0.0;
        if (this.n == 1 && this.l == 0) {
            for (int i = 0; i < this.npr; ++i) {
                array[i] = (this.rnl = 2.0 * Math.exp(-n));
                this.brightness += Math.abs(this.rnl * i);
                this.magx[i] = n;
                n += n2;
                if (this.datamaxR < Math.abs(array[i])) {
                    this.datamaxR = Math.abs(array[i]);
                }
            }
        }
        else {
            for (int j = 0; j < this.npr; ++j) {
                array[j] = (this.rnl = n3 * Math.pow(2.0 * n / this.n, this.l) * Math.exp(-n / this.n) * SpecialFunctions.laguerre(2 * this.l + 1, this.n - this.l - 1, 2.0 * n / this.n));
                this.brightness += Math.abs(this.rnl * j);
                this.magx[j] = n;
                n += n2;
                if (this.datamaxR < Math.abs(array[j])) {
                    this.datamaxR = Math.abs(array[j]);
                }
            }
        }
        this.brightness = this.brightness / 7000.0 / this.datamaxR;
        return array;
    }
    
    public double[] amplitude(final int n, final int n2, final double[] array, final double[] array2, final double[] array3) {
        this.getWaveFunction(n, n2);
        final double n3 = array[this.radius];
        double n4 = array2[this.radius] * array3[this.theta];
        if ((this.m & 0x1) == 0x1) {
            n4 = -n4;
        }
        if (this.m < 0) {
            n4 = -n4;
        }
        if (n - this.originx < 0 && (this.m & 0x1) == 0x1) {
            n4 = -n4;
        }
        this.amp[0] = n3;
        this.amp[1] = n4;
        return this.amp;
    }
    
    double[] getWaveFunction(final int n, final int n2) {
        this.rx = n - this.originx;
        this.ry = n2 - this.originy;
        this.radius = (int)Math.round(Math.sqrt(this.rx * this.rx + this.ry * this.ry));
        this.theta = (int)((this.pi / 2 - Math.atan2(this.originy - n2, Math.abs(this.rx))) * (180.0 / this.pi));
        this.val = 255.0 * (this.modRnl[this.radius] / this.datamaxR * (this.modYlm[this.theta] / this.datamaxY));
        if ((this.m & 0x1) == 0x1) {
            this.val = -this.val;
        }
        if (this.m < 0) {
            this.val = -this.val;
        }
        this.amp[0] = this.val;
        this.amp[1] = 0.0;
        return this.amp;
    }
    
    void colorFunction(final int[] array, final int n, final int n2, final int n3, final int n4, final double[] array2, final double[] array3) {
        this.amp = this.getWaveFunction(n, n2);
        double n5 = this.amp[0];
        if (n - this.originx < 0 && (this.m & 0x1) == 0x1) {
            n5 = -n5;
        }
        final boolean b = n5 < 0;
        final int min = Math.min((int)Math.round(255 * (1 - Math.exp(-Math.abs(n5) / (this.brightness * 255.0)))), 255);
        if (!this.showPhaseColor) {
            array[0] = min;
            array[2] = (array[1] = min);
        }
        else if (b) {
            array[0] = 0;
            array[1] = this.green[min];
            array[2] = this.blue[min];
        }
        else {
            array[0] = this.red[min];
            array[1] = this.green[min];
            array[2] = 0;
        }
    }
    
    void makePallet() {
        for (int i = 0; i < 256; ++i) {
            this.red[i] = i;
            this.blue[i] = i;
            this.green[i] = i;
        }
    }
    
    void setM(final int m) {
        this.m = m;
    }
    
    void setN(final int n) {
        this.n = n;
    }
    
    void setL(final int l) {
        this.l = l;
    }
    
    void setPhase(final boolean showPhaseColor) {
        this.showPhaseColor = showPhaseColor;
    }
    
    private void jbInit() throws Exception {
        this.addMouseMotionListener(new 1());
        this.addMouseListener(new 2());
    }
    
    double xFromPix(final int n) {
        return n;
    }
    
    double yFromPix(final int n) {
        return n;
    }
    
    public void drawCoords(final int n, final int n2) {
        this.amplitude(n, n2, this.magx, this.modRnl, this.modYlm);
        final String concat = String.valueOf(String.valueOf(String.valueOf("Bohr radii:").concat(String.valueOf(this.format.form(this.amp[0])))).concat(String.valueOf(" Amplitude:"))).concat(String.valueOf(this.format.form(this.amp[1])));
        final Graphics graphics = this.getGraphics();
        final Rectangle bounds = this.getBounds();
        graphics.setColor(Color.yellow);
        this.boxWidth = Math.max(20 + graphics.getFontMetrics(graphics.getFont()).stringWidth(concat), this.boxWidth);
        graphics.fillRect(0, bounds.height - 20, this.boxWidth, 20);
        graphics.setColor(Color.black);
        graphics.drawString(concat, 10, bounds.height - 5);
        graphics.dispose();
    }
    
    void this_mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            if (this.img == null) {
                return;
            }
            final DensityFrame densityFrame = new DensityFrame(this.img);
            densityFrame.setTitle(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Psi (n, l, m) = Psi (").concat(String.valueOf(this.n))).concat(String.valueOf(", "))).concat(String.valueOf(this.l))).concat(String.valueOf(", "))).concat(String.valueOf(this.m))).concat(String.valueOf(")")));
            densityFrame.show();
        }
        else {
            this.drawCoords(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    void this_mouseDragged(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            return;
        }
        this.drawCoords(mouseEvent.getX(), mouseEvent.getY());
    }
    
    void this_mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            return;
        }
        this.repaint(0, this.getBounds().height - 20, this.boxWidth, 20);
        this.boxWidth = 0;
    }
    
    void this_mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(1));
    }
    
    void this_mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public static void main(final String[] array) {
        new DensityCanvas().invokedStandalone = true;
    }
    
    static {
        DensityCanvas.calcString = "Please Wait...";
    }
    
    class 1 extends MouseMotionAdapter
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            DensityCanvas.this.this_mouseDragged(mouseEvent);
        }
    }
    
    class 2 extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            DensityCanvas.this.this_mousePressed(mouseEvent);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            DensityCanvas.this.this_mouseReleased(mouseEvent);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            DensityCanvas.this.this_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            DensityCanvas.this.this_mouseExited(mouseEvent);
        }
    }
}
