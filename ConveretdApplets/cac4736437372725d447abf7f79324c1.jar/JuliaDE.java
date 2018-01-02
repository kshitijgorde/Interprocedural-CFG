import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.TextField;
import java.awt.Label;
import java.awt.image.IndexColorModel;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JuliaDE extends Applet implements MouseListener, KeyListener, Runnable
{
    Thread JuliaTrace;
    Image imgM;
    Image imgJ;
    IndexColorModel RainbowColor;
    double maxIZI2;
    double Zoom;
    double Imid;
    double Rmid;
    double DelR;
    double stM;
    double trCr;
    double trCi;
    double Xmid;
    double Ymid;
    double DelX;
    double stJ;
    double[] xDemo;
    double[] yDemo;
    double PeriodPrecision2;
    int MaxIt;
    int maxColor;
    int ColorWhite;
    int delay;
    int numSteps;
    int wJ;
    int wM;
    int[] MArr;
    int[] JArr;
    int maxDemo;
    int[] nDemo;
    int lbSize;
    boolean animate;
    Label lbSteps;
    Label lbDelay;
    Label lbIt;
    Label lbPeriod;
    TextField tfSteps;
    TextField tfDelay;
    TextField tfIt;
    TextField tfPeriod;
    TextField tfRI;
    
    public JuliaDE() {
        this.maxIZI2 = 4.0;
        this.Zoom = 2.0;
        this.Imid = 0.0;
        this.Rmid = -0.75;
        this.DelR = 3.0;
        this.trCr = 0.0;
        this.trCi = 0.0;
        this.Xmid = 0.0;
        this.Ymid = 0.0;
        this.DelX = 4.0;
        this.PeriodPrecision2 = 1.0E-16;
        this.MaxIt = 128;
        this.maxColor = 96;
        this.delay = 20;
        this.numSteps = 100;
        this.lbSize = 30;
        this.animate = false;
    }
    
    public void init() {
        this.wJ = this.getSize().height - this.lbSize;
        this.wM = this.getSize().width - this.wJ;
        this.JArr = new int[this.wJ * (this.wJ + 2)];
        this.MArr = new int[this.wM * (this.wM + 2)];
        final String parameter = this.getParameter("MaxIt");
        if (parameter != null) {
            this.MaxIt = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("MaxColor");
        if (parameter2 != null) {
            this.maxColor = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("Delay");
        if (parameter3 != null) {
            this.delay = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("numSteps");
        if (parameter4 != null) {
            this.numSteps = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("XYdelX");
        if (parameter5 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter5);
            this.Xmid = Double.valueOf(stringTokenizer.nextToken());
            this.Ymid = Double.valueOf(stringTokenizer.nextToken());
            this.DelX = Double.valueOf(stringTokenizer.nextToken());
        }
        final String parameter6 = this.getParameter("RIdelR");
        if (parameter6 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter6);
            this.Rmid = Double.valueOf(stringTokenizer2.nextToken());
            this.Imid = Double.valueOf(stringTokenizer2.nextToken());
            this.DelR = Double.valueOf(stringTokenizer2.nextToken());
        }
        final String parameter7 = this.getParameter("maxDemo");
        if (parameter7 != null) {
            this.maxDemo = Integer.parseInt(parameter7);
        }
        else {
            this.maxDemo = 1;
        }
        this.nDemo = new int[this.maxDemo];
        this.xDemo = new double[this.maxDemo];
        this.yDemo = new double[this.maxDemo];
        if (parameter7 != null) {
            for (int i = 0; i < this.maxDemo; ++i) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getParameter("Demo" + i));
                this.xDemo[i] = Double.valueOf(stringTokenizer3.nextToken());
                this.yDemo[i] = Double.valueOf(stringTokenizer3.nextToken());
                this.nDemo[i] = Integer.parseInt(stringTokenizer3.nextToken());
            }
            this.trCr = this.xDemo[this.maxDemo - 1];
            this.trCi = this.yDemo[this.maxDemo - 1];
        }
        final int n = this.maxColor / 3;
        final int n2 = 2 * n;
        this.maxColor = 3 * n;
        final long n3 = n * n * n * n;
        final byte[] array = new byte[this.maxColor + 2];
        final byte[] array2 = new byte[this.maxColor + 2];
        final byte[] array3 = new byte[this.maxColor + 2];
        for (int j = 1; j < n2; ++j) {
            final long n4 = n - j;
            final long n5 = n4 * n4;
            final long n6 = n5 * n5;
            final byte[] array4 = array3;
            final int n7 = j;
            final byte[] array5 = array2;
            final int n8 = (j + n) % this.maxColor;
            final byte[] array6 = array;
            final int n9 = (j + n2) % this.maxColor;
            final byte b = (byte)(255L - 255L * n6 / n3);
            array6[n9] = b;
            array4[n7] = (array5[n8] = b);
        }
        this.ColorWhite = this.maxColor + 1;
        final byte[] array7 = array3;
        final int colorWhite = this.ColorWhite;
        final byte[] array8 = array2;
        final int colorWhite2 = this.ColorWhite;
        final byte[] array9 = array;
        final int colorWhite3 = this.ColorWhite;
        final byte b2 = -1;
        array9[colorWhite3] = b2;
        array7[colorWhite] = (array8[colorWhite2] = b2);
        this.RainbowColor = new IndexColorModel(8, this.maxColor + 2, array, array3, array2);
        this.stM = this.DelR / this.wM;
        this.stJ = this.DelX / this.wJ;
        final int[] array10 = new int[this.wM * (this.wM + 2)];
        this.Mandelbrot();
        this.add(this.lbSteps = new Label("Steps"));
        this.add(this.tfSteps = new TextField("" + this.numSteps, 3));
        this.add(this.lbDelay = new Label("Delay"));
        this.add(this.tfDelay = new TextField("" + this.delay, 3));
        this.add(this.lbIt = new Label("It"));
        this.add(this.tfIt = new TextField("" + this.MaxIt, 3));
        this.add(this.tfRI = new TextField("", 20));
        this.add(this.lbPeriod = new Label("Period"));
        this.add(this.tfPeriod = new TextField("", 3));
        this.tfSteps.addKeyListener(this);
        this.tfDelay.addKeyListener(this);
        this.tfIt.addKeyListener(this);
        this.addMouseListener(this);
        (this.JuliaTrace = new Thread(this)).start();
    }
    
    public void destroy() {
        this.removeMouseListener(this);
        this.JuliaTrace = null;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            try {
                this.numSteps = Integer.parseInt(this.tfSteps.getText());
                this.delay = Integer.parseInt(this.tfDelay.getText());
                this.MaxIt = Integer.parseInt(this.tfIt.getText());
            }
            catch (NumberFormatException ex) {}
        }
        keyEvent.consume();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        if (x > this.wM) {
            if (this.JuliaTrace != null || (!mouseEvent.isAltDown() && !mouseEvent.isControlDown())) {
                this.JuliaTrace = null;
                return;
            }
            this.Xmid += (x - this.wM - this.wJ / 2) * this.stJ;
            this.Ymid -= (mouseEvent.getY() - this.lbSize - this.wJ / 2) * this.stJ;
            if (mouseEvent.isControlDown()) {
                if (mouseEvent.isShiftDown()) {
                    this.stJ *= this.Zoom * 1.5;
                }
                else {
                    this.stJ *= this.Zoom;
                }
            }
            else if (mouseEvent.isShiftDown()) {
                this.stJ /= this.Zoom * 1.5;
            }
            else {
                this.stJ /= this.Zoom;
            }
            this.Julia();
            this.repaint();
        }
        else {
            if (!mouseEvent.isAltDown() && !mouseEvent.isControlDown()) {
                this.xDemo[0] = this.Rmid + (mouseEvent.getX() - this.wM / 2) * this.stM;
                this.yDemo[0] = this.Imid - (mouseEvent.getY() - this.lbSize - this.wM / 2) * this.stM;
                this.nDemo[0] = this.numSteps;
                this.maxDemo = 1;
                (this.JuliaTrace = new Thread(this)).start();
                return;
            }
            if (this.JuliaTrace != null) {
                this.JuliaTrace = null;
                return;
            }
            this.Rmid += (x - this.wM / 2) * this.stM;
            this.Imid -= (mouseEvent.getY() - this.lbSize - this.wM / 2) * this.stM;
            if (mouseEvent.isControlDown()) {
                if (mouseEvent.isShiftDown()) {
                    this.stM *= this.Zoom * 1.5;
                }
                else {
                    this.stM *= this.Zoom;
                }
            }
            else if (mouseEvent.isShiftDown()) {
                this.stM /= this.Zoom * 1.5;
            }
            else {
                this.stM /= this.Zoom;
            }
            this.Mandelbrot();
            this.repaint();
        }
    }
    
    public void run() {
        do {
            for (int i = 0; i < this.maxDemo; ++i) {
                final int n = this.nDemo[i];
                final double n2 = (this.xDemo[i] - this.trCr) / n;
                final double n3 = (this.yDemo[i] - this.trCi) / n;
                for (int j = n; j > 0; --j) {
                    if (Thread.currentThread() != this.JuliaTrace) {
                        return;
                    }
                    this.trCr += n2;
                    this.trCi += n3;
                    this.tfRI.setText("" + (float)this.trCr + "+" + (float)this.trCi + "i");
                    this.animate = true;
                    this.repaint();
                    try {
                        Thread.sleep(this.delay);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            if (this.maxDemo == 1) {
                this.JuliaTrace = null;
                return;
            }
        } while (this.nDemo[this.maxDemo - 1] != 0);
        this.JuliaTrace = null;
    }
    
    public void Orbits() {
        double n = 0.0;
        double n2 = 0.0;
        for (int i = 0; i < 3 * this.MaxIt; ++i) {
            final int n3 = (int)((n2 - this.Xmid) / this.stJ) + this.wJ / 2;
            final int n4 = -(int)((n - this.Ymid) / this.stJ) + this.wJ / 2;
            if (n3 > 0 && n3 < this.wJ && n4 > 0 && n4 < this.wJ) {
                try {
                    this.JArr[n3 + this.wJ * (n4 + 1)] = this.ColorWhite;
                }
                catch (IndexOutOfBoundsException ex) {}
            }
            final double n5 = n * n;
            n = (n2 + n2) * n + this.trCi;
            n2 = n2 * n2 - n5 + this.trCr;
        }
        final double n6 = n;
        final double n7 = n2;
        for (int j = 1; j < this.MaxIt; ++j) {
            final double n8 = n * n;
            n = (n2 + n2) * n + this.trCi;
            n2 = n2 * n2 - n8 + this.trCr;
            final double n9 = n - n6;
            final double n10 = n2 - n7;
            if (n9 * n9 + n10 * n10 < this.PeriodPrecision2) {
                this.tfPeriod.setText("" + j);
                return;
            }
        }
        this.tfPeriod.setText("Inf");
    }
    
    public void stop() {
        this.JuliaTrace = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.animate) {
            this.Julia();
            this.animate = false;
        }
        graphics.drawImage(this.imgM, 0, this.lbSize, this);
        if (this.imgJ != null) {
            graphics.drawImage(this.imgJ, this.wM, this.lbSize, this);
        }
        graphics.setColor(Color.white);
        final int n = (int)((this.trCr - this.Rmid) / this.stM) + this.wM / 2;
        final int n2 = -(int)((this.trCi - this.Imid) / this.stM) + this.wM / 2 + this.lbSize;
        graphics.drawLine(n - 3, n2, n + 3, n2);
        graphics.drawLine(n, n2 - 3, n, n2 + 3);
    }
    
    public void Mandelbrot() {
        int wm = this.wM;
        int i = 0;
        for (double n = this.Imid + this.stM * this.wM / 2.0; i < this.wM; ++i, n -= this.stM) {
            int j = 0;
            for (double n2 = this.Rmid - this.stM * (this.wM / 2 - 1); j < this.wM; ++j, n2 += this.stM, ++wm) {
                double n8;
                double n7;
                double n6;
                double n5;
                double n4;
                double n3 = n4 = (n5 = (n6 = (n7 = (n8 = 0.0))));
                int n9 = 0;
                do {
                    final double n10 = 2.0 * (n3 * n7 - n4 * n8) + 1.0;
                    n8 = 2.0 * (n3 * n8 + n4 * n7);
                    n7 = n10;
                    n4 = (n3 + n3) * n4 + n;
                    n3 = n6 - n5 + n2;
                    n6 = n3 * n3;
                    n5 = n4 * n4;
                    ++n9;
                } while (n6 + n5 < 100.0 && n9 < this.MaxIt);
                int maxColor;
                if (n9 == this.MaxIt) {
                    maxColor = this.maxColor;
                }
                else {
                    double n11 = -18.0 * Math.log(Math.log(n6 + n5) * Math.sqrt((n6 + n5) / (n7 * n7 + n8 * n8)));
                    if (n11 < 0.0) {
                        n11 = 0.0;
                    }
                    maxColor = (int)n11 % this.maxColor;
                }
                this.MArr[wm] = maxColor;
            }
        }
        this.imgM = this.createImage(new MemoryImageSource(this.wM, this.wM, this.RainbowColor, this.MArr, this.wM, this.wM));
    }
    
    public void Julia() {
        int wj = this.wJ;
        int i = 0;
        for (double n = this.Ymid + this.stJ * this.wJ / 2.0; i < this.wJ; ++i, n -= this.stJ) {
            int j = 0;
            for (double n2 = this.Xmid - this.stJ * (this.wJ / 2 - 1); j < this.wJ; ++j, n2 += this.stJ, ++wj) {
                double n3 = n;
                double n4 = n2;
                double n5 = n3 * n3;
                double n6 = n4 * n4;
                double n8;
                double n7 = n8 = 0.7;
                int n9 = 0;
                do {
                    final double n10 = n4 * n8 - n3 * n7;
                    n7 = n4 * n7 + n3 * n8;
                    n8 = n10;
                    n3 = (n4 + n4) * n3 + this.trCi;
                    n4 = n6 - n5 + this.trCr;
                    n6 = n4 * n4;
                    n5 = n3 * n3;
                    ++n9;
                } while (n6 + n5 < 100.0 && n9 < this.MaxIt);
                int maxColor;
                if (n9 == this.MaxIt) {
                    maxColor = this.maxColor;
                }
                else {
                    double n11 = -18.0 * (Math.log(Math.log(n6 + n5) * Math.sqrt((n6 + n5) / (n8 * n8 + n7 * n7))) - n9 * 0.693);
                    if (n11 < 0.0) {
                        n11 = 0.0;
                    }
                    maxColor = (int)n11 % this.maxColor;
                }
                this.JArr[wj] = maxColor;
            }
        }
        this.Orbits();
        if (this.imgJ != null) {
            this.imgJ.flush();
        }
        this.imgJ = this.createImage(new MemoryImageSource(this.wJ, this.wJ, this.RainbowColor, this.JArr, this.wJ, this.wJ));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
