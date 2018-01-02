import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.util.StringTokenizer;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Label;
import java.awt.image.IndexColorModel;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JuliaStar extends Applet implements MouseListener, ItemListener, KeyListener, Runnable
{
    Thread JuliaTrace;
    Image imgMan;
    Image imgJulia;
    IndexColorModel RainbowColor;
    double maxIZI2;
    double Zoom;
    double Imid;
    double Rmid;
    double DelR;
    double stMan;
    double trCr;
    double trCi;
    double Xmid;
    double Ymid;
    double stJulia;
    double[] xDemo;
    double[] yDemo;
    double PeriodPrecision2;
    double Rstar;
    double Istar;
    int MaxIt;
    int maxColor;
    int ColorWhite;
    int delay;
    int numSteps;
    int wJulia;
    int wMan;
    int Nstar;
    int[] pArr;
    int maxDemo;
    int[] nDemo;
    int lbSize;
    boolean animate;
    Label lbSteps;
    Label lbDelay;
    Label lbIt;
    Label lbBailOut;
    Label lbZoom;
    Label lbPeriod;
    TextField tfSteps;
    TextField tfDelay;
    TextField tfIt;
    TextField tfRI;
    TextField tfBailOut;
    TextField tfZoom;
    TextField tfPeriod;
    Checkbox cbGrid;
    boolean bDrawGrid;
    
    public JuliaStar() {
        this.maxIZI2 = 4.0;
        this.Zoom = 4.66920160910299;
        this.Imid = 0.0;
        this.Rmid = -0.75;
        this.DelR = 3.0;
        this.trCr = 0.0;
        this.trCi = 0.0;
        this.Xmid = 0.0;
        this.Ymid = 0.0;
        this.PeriodPrecision2 = 1.0E-16;
        this.MaxIt = 128;
        this.maxColor = 96;
        this.delay = 100;
        this.numSteps = 100;
        this.lbSize = 70;
        this.animate = false;
    }
    
    public void init() {
        this.wJulia = this.getSize().height - this.lbSize;
        this.wMan = this.getSize().width - this.wJulia;
        this.pArr = new int[this.wJulia * (this.wJulia + 2)];
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
        final String parameter5 = this.getParameter("maxDemo");
        if (parameter5 != null) {
            this.maxDemo = Integer.parseInt(parameter5);
        }
        else {
            this.maxDemo = 1;
        }
        this.nDemo = new int[this.maxDemo];
        this.xDemo = new double[this.maxDemo];
        this.yDemo = new double[this.maxDemo];
        if (parameter5 != null) {
            for (int i = 0; i < this.maxDemo; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("Demo" + i));
                this.xDemo[i] = Double.valueOf(stringTokenizer.nextToken());
                this.yDemo[i] = Double.valueOf(stringTokenizer.nextToken());
                this.nDemo[i] = Integer.parseInt(stringTokenizer.nextToken());
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
        this.stMan = this.DelR / this.wMan;
        final int[] array10 = new int[this.wMan * (this.wMan + 2)];
        this.BTracing(this.Rmid, this.Imid, this.stMan, this.wMan, this.wMan, array10, new ManZ2(this.MaxIt, this.maxColor, this.maxIZI2));
        this.imgMan = this.createImage(new MemoryImageSource(this.wMan, this.wMan, this.RainbowColor, array10, this.wMan, this.wMan));
        this.add(this.lbSteps = new Label("Steps"));
        this.add(this.tfSteps = new TextField("" + this.numSteps, 3));
        this.add(this.lbDelay = new Label("Delay"));
        this.add(this.tfDelay = new TextField("" + this.delay, 3));
        this.add(this.lbIt = new Label("It"));
        this.add(this.tfIt = new TextField("" + this.MaxIt, 3));
        this.add(this.tfRI = new TextField("", 20));
        this.add(this.cbGrid = new Checkbox("Grid"));
        this.add(this.lbZoom = new Label("Zoom^2"));
        this.add(this.tfZoom = new TextField("" + this.Zoom, 10));
        this.Zoom = Math.sqrt(this.Zoom);
        this.add(this.lbBailOut = new Label("BailOut"));
        this.add(this.tfBailOut = new TextField("2.0", 3));
        this.add(this.lbPeriod = new Label("Period"));
        this.add(this.tfPeriod = new TextField("", 3));
        this.tfSteps.addKeyListener(this);
        this.tfDelay.addKeyListener(this);
        this.tfZoom.addKeyListener(this);
        this.tfBailOut.addKeyListener(this);
        this.tfIt.addKeyListener(this);
        this.cbGrid.addItemListener(this);
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
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.bDrawGrid = this.cbGrid.getState();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            try {
                this.numSteps = Integer.parseInt(this.tfSteps.getText());
                this.delay = Integer.parseInt(this.tfDelay.getText());
                this.MaxIt = Integer.parseInt(this.tfIt.getText());
                this.Zoom = Math.sqrt(Double.valueOf(this.tfZoom.getText()));
                this.maxIZI2 = Double.valueOf(this.tfBailOut.getText());
                this.maxIZI2 *= this.maxIZI2;
            }
            catch (NumberFormatException ex) {}
        }
        keyEvent.consume();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        if (x > this.wMan) {
            if (this.JuliaTrace != null || (!mouseEvent.isAltDown() && !mouseEvent.isControlDown())) {
                this.JuliaTrace = null;
                return;
            }
            this.Xmid += (x - this.wMan - this.wJulia / 2) * this.stJulia;
            this.Ymid -= (mouseEvent.getY() - this.lbSize - this.wJulia / 2) * this.stJulia;
            if (mouseEvent.isControlDown()) {
                if (mouseEvent.isShiftDown()) {
                    this.stJulia *= this.Zoom * this.Zoom;
                }
                else {
                    this.stJulia *= this.Zoom;
                }
            }
            else if (mouseEvent.isShiftDown()) {
                this.stJulia /= this.Zoom * this.Zoom;
            }
            else {
                this.stJulia /= this.Zoom;
            }
            this.BTracing(this.Xmid, this.Ymid, this.stJulia, this.wJulia, this.wJulia, this.pArr, new JuliaZ2(this.trCr, this.trCi, this.MaxIt, this.maxColor, this.maxIZI2));
            if (this.imgJulia != null) {
                this.imgJulia.flush();
            }
            this.imgJulia = this.createImage(new MemoryImageSource(this.wJulia, this.wJulia, this.RainbowColor, this.pArr, this.wJulia, this.wJulia));
            this.repaint();
        }
        else {
            if (!mouseEvent.isAltDown() && !mouseEvent.isControlDown()) {
                this.xDemo[0] = this.Rmid + (mouseEvent.getX() - this.wMan / 2) * this.stMan;
                this.yDemo[0] = this.Imid - (mouseEvent.getY() - this.lbSize - this.wMan / 2) * this.stMan;
                this.nDemo[0] = this.numSteps;
                this.maxDemo = 1;
                (this.JuliaTrace = new Thread(this)).start();
                return;
            }
            if (this.JuliaTrace != null) {
                this.JuliaTrace = null;
                return;
            }
            this.Rmid += (x - this.wMan / 2) * this.stMan;
            this.Imid -= (mouseEvent.getY() - this.lbSize - this.wMan / 2) * this.stMan;
            if (mouseEvent.isControlDown()) {
                if (mouseEvent.isShiftDown()) {
                    this.stMan *= this.Zoom * this.Zoom;
                }
                else {
                    this.stMan *= this.Zoom;
                }
            }
            else if (mouseEvent.isShiftDown()) {
                this.stMan /= this.Zoom * this.Zoom;
            }
            else {
                this.stMan /= this.Zoom;
            }
            this.BTracing(this.Rmid, this.Imid, this.stMan, this.wMan, this.wMan, this.pArr, new ManZ2(this.MaxIt, this.maxColor, this.maxIZI2));
            if (this.bDrawGrid) {
                int n2;
                for (int n = n2 = this.wMan / 4, i = 0; i < 3; ++i, n2 += n) {
                    for (int j = 0; j < this.wMan; ++j) {
                        this.pArr[j + this.wMan * (n2 + 1)] = (this.pArr[n2 + this.wMan * (j + 1)] = this.ColorWhite);
                    }
                }
            }
            this.imgMan = this.createImage(new MemoryImageSource(this.wMan, this.wMan, this.RainbowColor, this.pArr, this.wMan, this.wMan));
            this.repaint();
        }
    }
    
    public void run() {
        this.Xmid = 0.0;
        this.Ymid = 0.0;
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
                    this.stJulia = (4.0 - (2.0 + this.trCr) * 0.5) / this.wJulia;
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
        double istar = 0.0;
        double rstar = 0.0;
        this.Nstar = 0;
        for (int i = 0; i < 3 * this.MaxIt; ++i) {
            final double n = istar * istar;
            istar = (rstar + rstar) * istar + this.trCi;
            rstar = rstar * rstar - n + this.trCr;
            final int n2 = (int)(rstar / this.stJulia) + this.wJulia / 2;
            final int n3 = -(int)(istar / this.stJulia) + this.wJulia / 2;
            try {
                this.pArr[n2 + this.wJulia * (n3 + 1)] = this.ColorWhite;
            }
            catch (IndexOutOfBoundsException ex) {
                this.tfPeriod.setText(">");
                return;
            }
        }
        final double n4 = istar;
        final double n5 = rstar;
        for (int j = 1; j < this.MaxIt; ++j) {
            final double n6 = istar * istar;
            istar = (rstar + rstar) * istar + this.trCi;
            rstar = rstar * rstar - n6 + this.trCr;
            final double n7 = istar - n4;
            final double n8 = rstar - n5;
            if (n7 * n7 + n8 * n8 < this.PeriodPrecision2) {
                this.Rstar = rstar;
                this.Istar = istar;
                this.Nstar = j;
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
            this.BTracing(0.0, this.stJulia * this.wJulia / 4.0, this.stJulia, this.wJulia, this.wJulia / 2 + 1, this.pArr, new JuliaZ2(this.trCr, this.trCi, this.MaxIt, this.maxColor, this.maxIZI2));
            for (int i = this.wJulia; i < this.wJulia * (this.wJulia / 2 + 1); ++i) {
                this.pArr[this.wJulia * (this.wJulia + 1) - i] = this.pArr[i];
            }
            if (this.imgJulia != null) {
                this.imgJulia.flush();
            }
            this.Orbits();
            this.imgJulia = this.createImage(new MemoryImageSource(this.wJulia, this.wJulia, this.RainbowColor, this.pArr, this.wJulia, this.wJulia));
            this.animate = false;
        }
        graphics.drawImage(this.imgMan, 0, this.lbSize, this);
        if (this.imgJulia != null) {
            graphics.drawImage(this.imgJulia, this.wMan, this.lbSize, this);
        }
        graphics.setColor(Color.white);
        final int n = (int)((this.trCr - this.Rmid) / this.stMan) + this.wMan / 2;
        final int n2 = -(int)((this.trCi - this.Imid) / this.stMan) + this.wMan / 2 + this.lbSize;
        graphics.drawLine(n - 3, n2, n + 3, n2);
        graphics.drawLine(n, n2 - 3, n, n2 + 3);
        if (this.Nstar > 1) {
            double istar = this.Istar;
            double rstar = this.Rstar;
            int n3 = (int)((rstar - this.Xmid) / this.stJulia) + this.wJulia / 2 + this.wMan;
            int n4 = -(int)((istar - this.Ymid) / this.stJulia) + this.wJulia / 2 + this.lbSize;
            for (int j = 0; j < this.Nstar; ++j) {
                final double n5 = istar * istar;
                istar = (rstar + rstar) * istar + this.trCi;
                rstar = rstar * rstar - n5 + this.trCr;
                final int n6 = (int)((rstar - this.Xmid) / this.stJulia) + this.wJulia / 2 + this.wMan;
                final int n7 = -(int)((istar - this.Ymid) / this.stJulia) + this.wJulia / 2 + this.lbSize;
                graphics.drawLine(n3, n4, n6, n7);
                n3 = n6;
                n4 = n7;
            }
        }
    }
    
    public void BTracing(final double n, final double n2, final double n3, final int n4, final int n5, final int[] array, final Formula formula) {
        for (int i = 0; i < n4; ++i) {
            array[i] = (array[i + n4 * (n5 + 1)] = -2);
        }
        for (int j = n4; j < n4 * (n5 + 1); ++j) {
            array[j] = -1;
        }
        for (int k = n4; k < n4 * (n5 + 2); k += n4) {
            array[k] = (array[k - 1] = -2);
        }
        final double[] array2 = { n3, 0.0, -n3, 0.0 };
        final double[] array3 = { 0.0, -n3, 0.0, n3 };
        int n6 = n4 + 1;
        final int[] array4 = { 1, n4, -1, -n4 };
        int l = 0;
        for (double n7 = n2 + n3 * n5 / 2.0; l < n5; ++l, n7 -= n3, n6 += 2) {
            int n8 = 1;
            for (double n9 = n - n3 * (n4 / 2 - 1); n8 < n4 - 1; ++n8, n9 += n3, ++n6) {
                if (array[n6] == -1) {
                    double n10 = n9;
                    double n11 = n7;
                    int n13;
                    int n12 = n13 = n6;
                    int n14 = 0;
                    final int n15 = n6;
                    final int iterate = formula.iterate(n9, n7);
                    array[n15] = iterate;
                    int n16;
                    for (n16 = iterate; array[n12 - n4] == n16; n12 = (n13 = n12 - n4), n11 += n3) {}
                    do {
                        for (int n17 = n14 + 3; n17 < n14 + 7; ++n17) {
                            final int n18 = n17 & 0x3;
                            final int n19 = n13 + array4[n18];
                            final double n20 = n10 + array2[n18];
                            final double n21 = n11 + array3[n18];
                            int n22;
                            if ((n22 = array[n19]) == -1) {
                                final int n23 = n19;
                                final int iterate2 = formula.iterate(n20, n21);
                                array[n23] = iterate2;
                                n22 = iterate2;
                            }
                            if (n22 == n16) {
                                n14 = n18;
                                n13 = n19;
                                n10 = n20;
                                n11 = n21;
                                break;
                            }
                        }
                    } while (n13 != n12);
                    int n24 = 0;
                    do {
                        for (int n25 = n24 + 3; n25 < n24 + 7; ++n25) {
                            final int n26 = n25 & 0x3;
                            final int n27 = n13 + array4[n26];
                            if (array[n27] == n16) {
                                if ((n24 = n26) == 3) {
                                    int n28 = n13;
                                    int n29;
                                    while ((n29 = array[++n28]) == -1 || n29 == n16) {
                                        array[n28] = n16;
                                    }
                                }
                                n13 = n27;
                                break;
                            }
                        }
                    } while (n13 != n12);
                }
            }
        }
        for (int n30 = n4; n30 < n4 * (n5 + 1); n30 += n4) {
            array[n30] = array[n30 + 1];
            array[n30 + n4 - 1] = array[n30 + n4 - 2];
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
