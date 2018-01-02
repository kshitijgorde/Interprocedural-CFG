import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Polygon;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Penrose extends JApplet implements Runnable
{
    Thread Z0001;
    DrawingArea Z0014;
    MouseAction Z0035;
    Color[] Z0042;
    Color Z0002;
    double[] Z0010;
    double[] Z0065;
    double[] Z0016;
    double Z0040;
    double Z0041;
    double Z0011;
    double Z0012;
    double Z0013;
    double scale;
    double Z0064;
    double Z0043;
    int[] Z0046;
    int[] Z0048;
    int[] Z0047;
    int[] Z0049;
    int Z0006;
    int Z0044;
    int Z0045;
    int Z0033;
    int Z0018;
    int Z0037;
    int left;
    int top;
    boolean Z0036;
    boolean Z0051;
    
    public void init() {
        this.Z0061();
        this.Z0002 = new Color(204, 255, 255);
        this.getContentPane().add(this.Z0014 = new DrawingArea(), "Center");
        this.Z0035 = new MouseAction();
        this.Z0014.addMouseListener(this.Z0035);
        this.Z0014.addMouseMotionListener(this.Z0035);
        this.Z0001 = null;
    }
    
    public void start() {
        if (this.Z0001 == null) {
            (this.Z0001 = new Thread(this)).setPriority(1);
            this.Z0001.start();
        }
        final Dimension size = this.Z0014.getSize();
        final int n = size.width - size.height;
        this.Z0014.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0054();
    }
    
    public void stop() {
        this.Z0001 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0001) {
            if (this.Z0036 || this.Z0051) {
                this.Z0014.repaint();
            }
            this.Z0051 = false;
            if (!this.Z0063(5L)) {
                break;
            }
        }
    }
    
    boolean Z0063(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0061() {
    }
    
    void Z0054() {
        final Dimension size = this.Z0014.getSize();
        final Insets insets = this.Z0014.getInsets();
        this.Z0006 = Math.min(size.width - insets.left - insets.right, size.height - insets.top - insets.bottom);
        this.left = insets.left;
        this.top = insets.top;
        this.Z0057();
        this.Z0040 = 0.0;
        this.Z0041 = 0.0;
        this.Z0060();
        this.Z0043 = 20.0;
        this.scale = this.Z0006 / (2.0 * this.Z0043);
        this.Z0064 = this.Z0006 / 2.0;
        this.Z0033 = (int)(-this.Z0043 * this.scale + this.Z0064);
        this.Z0018 = (int)(this.Z0043 * this.scale + this.Z0064);
        this.Z0044 = this.Z0006 / 2;
        this.Z0045 = this.Z0006 / 2;
        this.Z0037 = 10;
        this.Z0062();
        this.Z0036 = false;
        this.Z0051 = true;
    }
    
    void Z0057() {
        this.Z0042 = new Color[10];
        this.Z0010 = new double[5];
        this.Z0065 = new double[5];
        this.Z0016 = new double[5];
        this.Z0046 = new int[4];
        this.Z0048 = new int[4];
        this.Z0047 = new int[4];
        this.Z0049 = new int[4];
    }
    
    void Z0060() {
        double n = 0.0;
        for (int i = 0; i < 5; ++i) {
            this.Z0010[i] = Math.cos(6.283185307179586 * i / 5.0);
            this.Z0065[i] = Math.sin(6.283185307179586 * i / 5.0);
            if (i < 4) {
                this.Z0016[i] = 0.1 * (i + 1);
                n += this.Z0016[i];
            }
            else {
                this.Z0016[4] = -n;
            }
        }
    }
    
    void Z0058(final int n, final int n2, final int n3) {
        switch (n3) {
            case 1: {
                this.Z0036 = true;
                this.Z0044 = n;
                this.Z0045 = n2;
                break;
            }
            case 0: {
                if (this.Z0036) {
                    this.Z0044 = n;
                    this.Z0045 = n2;
                    break;
                }
                break;
            }
            case -1: {
                this.Z0036 = false;
                break;
            }
        }
    }
    
    void Z0059(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle2D.Double clip = new Rectangle2D.Double(this.left, this.top, this.Z0006, this.Z0006);
        final Shape clip2 = graphics2D.getClip();
        graphics2D.setClip(clip);
        this.Z0011 = 10.0 * (2.0 * this.Z0044 / this.Z0006 - 1.0);
        this.Z0012 = 10.0 * (2.0 * this.Z0045 / this.Z0006 - 1.0);
        this.Z0040 += this.Z0011;
        this.Z0041 += this.Z0012;
        final int n = (int)(this.Z0043 * 1.25);
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = i + 1; j < 5; ++j) {
                if (++n6 == 10) {
                    n6 = 0;
                }
                graphics2D.setPaint(this.Z0042[n6]);
                this.Z0013 = 1.0 / (this.Z0010[i] * this.Z0065[j] - this.Z0010[j] * this.Z0065[i]);
                for (int k = 0; k < 4; ++k) {
                    switch (k) {
                        case 0: {
                            n2 = 0;
                            n4 = 1;
                            n3 = 0;
                            n5 = 1;
                            break;
                        }
                        case 1: {
                            n2 = -1;
                            n4 = -1;
                            n3 = 0;
                            n5 = 1;
                            break;
                        }
                        case 2: {
                            n2 = -1;
                            n4 = -1;
                            n3 = -1;
                            n5 = -1;
                            break;
                        }
                        case 3: {
                            n2 = 0;
                            n4 = 1;
                            n3 = -1;
                            n5 = -1;
                            break;
                        }
                    }
                    for (int n7 = n2; Math.abs(n7) != n; n7 += n4) {
                        for (int n8 = n3; Math.abs(n8) != n; n8 += n5) {
                            if (this.Z0055(i, j, n7, n8)) {
                                for (int l = 0; l < 4; ++l) {
                                    this.Z0047[l] = this.Z0046[l] + this.left;
                                    this.Z0049[l] = this.Z0048[l] + this.top;
                                }
                                graphics2D.fill(new Polygon(this.Z0047, this.Z0049, 4));
                            }
                        }
                    }
                }
            }
        }
        graphics2D.setClip(clip2);
    }
    
    boolean Z0055(final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[5];
        final double n5 = (this.Z0065[n2] * (n3 - this.Z0016[n]) - this.Z0065[n] * (n4 - this.Z0016[n2])) * this.Z0013;
        final double n6 = -(this.Z0010[n2] * (n3 - this.Z0016[n]) - this.Z0010[n] * (n4 - this.Z0016[n2])) * this.Z0013;
        array[n] = n3;
        array[n2] = n4;
        for (int i = 0; i < 5; ++i) {
            if (i != n && i != n2) {
                double n7 = this.Z0010[i] * n5 + this.Z0065[i] * n6 + this.Z0016[i];
                if (n7 > 0.0) {
                    n7 += 0.999999999;
                }
                array[i] = (int)n7;
            }
        }
        for (int j = 0; j < 4; ++j) {
            double n8 = 0.0;
            double n9 = 0.0;
            for (int k = 0; k < 5; ++k) {
                int n10 = array[k];
                if (k == n && (j == 1 || j == 2)) {
                    ++n10;
                }
                if (k == n2 && (j == 2 || j == 3)) {
                    ++n10;
                }
                n8 += n10 * this.Z0010[k];
                n9 += n10 * this.Z0065[k];
            }
            this.Z0046[j] = (int)(n8 * this.scale + this.Z0064 + this.Z0040);
            this.Z0048[j] = (int)(n9 * this.scale + this.Z0064 + this.Z0041);
        }
        int n11 = this.Z0018 + 1;
        int n12 = this.Z0033 - 1;
        int n13 = this.Z0018 + 1;
        int n14 = this.Z0033 - 1;
        for (int l = 0; l < 4; ++l) {
            if (this.Z0046[l] < n11) {
                n11 = this.Z0046[l];
            }
            if (this.Z0046[l] > n12) {
                n12 = this.Z0046[l];
            }
            if (this.Z0048[l] < n13) {
                n13 = this.Z0048[l];
            }
            if (this.Z0048[l] > n14) {
                n14 = this.Z0048[l];
            }
        }
        return n11 < this.Z0018 && n12 > this.Z0033 && n13 < this.Z0018 && n14 > this.Z0033;
    }
    
    void Z0062() {
        double n3;
        double n2;
        double n = n2 = (n3 = 0.0);
        for (int i = 0; i < this.Z0037; ++i) {
            final double n4 = i / this.Z0037;
            final double n6;
            final double n5 = n6 = 1.0;
            int n7 = (int)(6.0 * n4);
            final double n8 = n4 * 6.0 - n7;
            final double n9 = n5 * (1.0 - n6);
            final double n10 = n5 * (1.0 - n8 * n6);
            final double n11 = n5 * (1.0 - (1.0 - n8) * n6);
            if (n7 == 6) {
                n7 = 0;
            }
            switch (n7) {
                case 0: {
                    n2 = n5;
                    n = n11;
                    n3 = n9;
                    break;
                }
                case 1: {
                    n2 = n10;
                    n = n5;
                    n3 = n9;
                    break;
                }
                case 2: {
                    n2 = n9;
                    n = n5;
                    n3 = n11;
                    break;
                }
                case 3: {
                    n2 = n9;
                    n = n10;
                    n3 = n5;
                    break;
                }
                case 4: {
                    n2 = n11;
                    n = n9;
                    n3 = n5;
                    break;
                }
                case 5: {
                    n2 = n5;
                    n = n9;
                    n3 = n10;
                    break;
                }
            }
            this.Z0042[i] = new Color((int)(255.0 * n2), (int)(255.0 * n), (int)(255.0 * n3));
        }
    }
    
    class MouseAction extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Penrose.this.Z0058(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Penrose.this.Z0058(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Penrose.this.Z0058(mouseEvent.getX(), mouseEvent.getY(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Penrose.this.Z0059(graphics);
        }
    }
}
