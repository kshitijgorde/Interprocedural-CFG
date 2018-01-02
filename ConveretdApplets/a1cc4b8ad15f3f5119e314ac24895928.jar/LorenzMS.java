import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LorenzMS extends Applet implements Runnable
{
    private static Thread carmen;
    private static int defaultp;
    private static int pause;
    private static Image mOSI;
    private static Graphics mOSG;
    private static int width;
    private static int height;
    private static int cX;
    private static int cY;
    private static int x0;
    private static int y0;
    private static int z0;
    private static int px1;
    private static int px2;
    private static int py1;
    private static int py2;
    private static int pz1;
    private static int pz2;
    private static int mode;
    private static final int nAngles = 17;
    private static double[] m;
    private static final double[] deg;
    private static final double PI = 3.141592653589793;
    private static final double PI2 = 1.5707963267948966;
    private static double x;
    private static double y;
    private static double z;
    private static double a;
    private static double b;
    private static double c;
    private static Color bgColor;
    private static Color[] color;
    private static boolean[] show;
    private static boolean fly;
    
    public void init() {
        LorenzMS.width = this.size().width;
        LorenzMS.height = this.size().height;
        LorenzMS.cX = LorenzMS.width / 2;
        LorenzMS.cY = LorenzMS.height / 2;
        LorenzMS.mOSI = this.createImage(LorenzMS.width, LorenzMS.height);
        LorenzMS.mOSG = LorenzMS.mOSI.getGraphics();
    }
    
    public void update(final Graphics graphics) {
        this.paint(LorenzMS.mOSG);
        graphics.drawImage(LorenzMS.mOSI, 0, 0, this);
    }
    
    public void start() {
        if (LorenzMS.carmen == null) {
            (LorenzMS.carmen = new Thread(this)).start();
        }
        if (this.flip()) {
            LorenzMS.a = 10.0;
            LorenzMS.b = 28.0;
            LorenzMS.c = 2.6666666666666665;
        }
        else {
            LorenzMS.a = 28.0;
            LorenzMS.b = 46.92;
            LorenzMS.c = 4.0;
        }
        LorenzMS.x = this.rnd(33) - 15;
        LorenzMS.y = this.rnd(42) - 19;
        LorenzMS.z = this.rnd(46);
        int rnd = this.rnd(256);
        int rnd2 = this.rnd(256);
        int rnd3 = this.rnd(256);
        if (this.flip()) {
            rnd = 255 - rnd;
            rnd2 = 255 - rnd2;
            rnd3 = 255 - rnd3;
        }
        this.setBackground(LorenzMS.bgColor = new Color(rnd, rnd2, rnd3));
        for (int i = 0; i < 3; ++i) {
            int rnd4 = this.rnd(rnd);
            int rnd5 = this.rnd(rnd2);
            int rnd6 = this.rnd(rnd3);
            if (this.flip()) {
                rnd4 = 255 - rnd4;
                rnd5 = 255 - rnd5;
                rnd6 = 255 - rnd6;
            }
            LorenzMS.color[i] = new Color(rnd4, rnd5, rnd6);
            LorenzMS.color[i + 3] = new Color(255 - rnd4, 255 - rnd5, 255 - rnd6);
        }
        if (this.flip()) {
            final Color[] color = LorenzMS.color;
            final int n = 0;
            final Color[] color2 = LorenzMS.color;
            final int n2 = 1;
            final Color[] color3 = LorenzMS.color;
            final int n3 = 2;
            final Color[] color4 = LorenzMS.color;
            final int n4 = 3;
            final Color[] color5 = LorenzMS.color;
            final int n5 = 4;
            final Color color6 = LorenzMS.color[5];
            color5[n5] = color6;
            color3[n3] = (color4[n4] = color6);
            color[n] = (color2[n2] = color6);
        }
        do {
            this.rnd(64);
            final int n6 = 63;
            LorenzMS.show[0] = ((n6 & 0x1) != 0x0);
            LorenzMS.show[1] = ((n6 & 0x2) != 0x0);
            LorenzMS.show[2] = ((n6 & 0x4) != 0x0);
            LorenzMS.show[3] = ((n6 & 0x8) != 0x0);
            LorenzMS.show[4] = ((n6 & 0x10) != 0x0);
            LorenzMS.show[5] = ((n6 & 0x20) != 0x0);
        } while (!LorenzMS.show[0] && !LorenzMS.show[1] && !LorenzMS.show[2] && !LorenzMS.show[3] && !LorenzMS.show[4] && !LorenzMS.show[5]);
        LorenzMS.mode = this.rnd(6);
        LorenzMS.x0 = (LorenzMS.y0 = (LorenzMS.z0 = 0));
        LorenzMS.pause = LorenzMS.defaultp;
        LorenzMS.fly = true;
    }
    
    public void stop() {
        if (LorenzMS.carmen != null) {
            LorenzMS.carmen.stop();
            LorenzMS.carmen = null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.currentThread();
                Thread.sleep(LorenzMS.pause);
            }
            catch (InterruptedException ex) {
                break;
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(LorenzMS.bgColor);
        graphics.fillRect(0, 0, LorenzMS.width, LorenzMS.height);
        if (LorenzMS.fly) {
            final double x = LorenzMS.x + 0.01 * LorenzMS.a * (LorenzMS.y - LorenzMS.x);
            final double y = LorenzMS.y + 0.01 * (LorenzMS.x * (LorenzMS.b - LorenzMS.z) - LorenzMS.y);
            final double z = LorenzMS.z + 0.01 * (LorenzMS.x * LorenzMS.y - LorenzMS.c * LorenzMS.z);
            final int n = (int)(LorenzMS.x * 7.0 + LorenzMS.cX);
            final int n2 = (int)(x * 7.0 + LorenzMS.cX);
            final int n3 = (int)(-LorenzMS.z * 7.0 + LorenzMS.width);
            final int n4 = (int)(-z * 7.0 + LorenzMS.width);
            final int n5 = (int)(-LorenzMS.y * 7.0 + LorenzMS.cY);
            final int n6 = (int)(-y * 7.0 + LorenzMS.cY);
            LorenzMS.x = x;
            LorenzMS.y = y;
            LorenzMS.z = z;
            switch (LorenzMS.mode) {
                case 0: {
                    LorenzMS.px1 = n;
                    LorenzMS.px2 = n2;
                    LorenzMS.py1 = n5;
                    LorenzMS.py2 = n6;
                    LorenzMS.pz1 = n3;
                    LorenzMS.pz2 = n4;
                    break;
                }
                case 1: {
                    LorenzMS.px1 = n;
                    LorenzMS.px2 = n2;
                    LorenzMS.py1 = n3;
                    LorenzMS.py2 = n4;
                    LorenzMS.pz1 = n5;
                    LorenzMS.pz2 = n6;
                    break;
                }
                case 2: {
                    LorenzMS.px1 = n5;
                    LorenzMS.px2 = n6;
                    LorenzMS.py1 = n;
                    LorenzMS.py2 = n2;
                    LorenzMS.pz1 = n3;
                    LorenzMS.pz2 = n4;
                    break;
                }
                case 3: {
                    LorenzMS.px1 = n5;
                    LorenzMS.px2 = n6;
                    LorenzMS.py1 = n3;
                    LorenzMS.py2 = n4;
                    LorenzMS.pz1 = n;
                    LorenzMS.pz2 = n2;
                    break;
                }
                case 4: {
                    LorenzMS.px1 = n3;
                    LorenzMS.px2 = n4;
                    LorenzMS.py1 = n;
                    LorenzMS.py2 = n2;
                    LorenzMS.pz1 = n5;
                    LorenzMS.pz2 = n6;
                    break;
                }
                case 5: {
                    LorenzMS.px1 = n3;
                    LorenzMS.px2 = n4;
                    LorenzMS.py1 = n5;
                    LorenzMS.py2 = n6;
                    LorenzMS.pz1 = n;
                    LorenzMS.pz2 = n2;
                    break;
                }
            }
        }
        final int[] array = { 0, 1, 2 };
        final int n7 = LorenzMS.px1 - LorenzMS.px2;
        final int n8 = LorenzMS.py1 - LorenzMS.py2;
        final int n9 = LorenzMS.pz1 - LorenzMS.pz2;
        final int n10 = n7 * n7;
        final int n11 = n8 * n8;
        final int n12 = n9 * n9;
        final int[] array2 = { n10 + n11, n10 + n12, n11 + n12 };
        if (array2[array[0]] > array2[array[1]]) {
            final int n13 = array[0];
            array[0] = array[1];
            array[1] = n13;
        }
        if (array2[array[1]] > array2[array[2]]) {
            final int n14 = array[1];
            array[1] = array[2];
            array[2] = n14;
        }
        if (array2[array[0]] > array2[array[1]]) {
            final int n15 = array[0];
            array[0] = array[1];
            array[1] = n15;
        }
        for (int i = 0; i < 3; ++i) {
            if (LorenzMS.show[array[i]]) {
                this.drawButterfly(graphics, array[i]);
            }
            if (LorenzMS.show[2 * array[i] + 1]) {
                this.drawButterfly(graphics, 2 * array[i] + 1);
            }
        }
    }
    
    public void drawButterfly(final Graphics graphics, final int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        Color white = Color.white;
        switch (n) {
            case 0: {
                n2 = LorenzMS.px1;
                n3 = LorenzMS.py1;
                n4 = LorenzMS.px2;
                n5 = LorenzMS.py2;
                white = LorenzMS.color[0];
                break;
            }
            case 1: {
                n2 = LorenzMS.py1;
                n3 = LorenzMS.px1;
                n4 = LorenzMS.py2;
                n5 = LorenzMS.px2;
                white = LorenzMS.color[3];
                break;
            }
            case 2: {
                n2 = LorenzMS.px1;
                n3 = LorenzMS.pz1;
                n4 = LorenzMS.px2;
                n5 = LorenzMS.pz2;
                white = LorenzMS.color[1];
                break;
            }
            case 3: {
                n2 = LorenzMS.pz1;
                n3 = LorenzMS.px1;
                n4 = LorenzMS.pz2;
                n5 = LorenzMS.px2;
                white = LorenzMS.color[4];
                break;
            }
            case 4: {
                n2 = LorenzMS.py1;
                n3 = LorenzMS.pz1;
                n4 = LorenzMS.py2;
                n5 = LorenzMS.pz2;
                white = LorenzMS.color[2];
                break;
            }
            case 5: {
                n2 = LorenzMS.pz1;
                n3 = LorenzMS.py1;
                n4 = LorenzMS.pz2;
                n5 = LorenzMS.py2;
                white = LorenzMS.color[5];
                break;
            }
        }
        final int n6 = n2 + LorenzMS.x0;
        final int n7 = n3 + LorenzMS.y0;
        final int n8 = n4 + LorenzMS.x0;
        final int n9 = n5 + LorenzMS.y0;
        final double n10 = (n9 - n7) / (n8 - n6);
        int i = 0;
        int n11 = 16;
        int n12;
        do {
            n12 = (i + n11) / 2;
            if (n10 < LorenzMS.m[n12]) {
                n11 = --n12;
            }
            else {
                i = ++n12;
            }
        } while (i < n11);
        final int max = Math.max(n8 - n6 - LorenzMS.z0, 0);
        final int max2 = Math.max(n9 - n7 - LorenzMS.z0, 0);
        double n13 = 2.0 * Math.sqrt(max * max + max2 * max2);
        if (n8 > n6) {
            n13 = -n13;
        }
        final double cos = Math.cos(LorenzMS.deg[n12]);
        final double sin = Math.sin(LorenzMS.deg[n12]);
        final int n14 = (int)(n13 * cos) + n8;
        final int n15 = (int)(n13 * sin) + n9;
        final int n16 = (int)(n13 / 5.0 * cos) + n8;
        final int n17 = (int)(n13 / 5.0 * sin) + n9;
        final int n18 = (int)Math.abs(n13 / 3.0);
        final double n19 = LorenzMS.deg[n12] + 1.5707963267948966;
        final int n20 = (int)(n18 * Math.cos(n19)) + n16;
        final int n21 = (int)(n18 * Math.sin(n19)) + n17;
        double n22;
        if (n6 > n8) {
            n22 = n19 + 1.5707963267948966;
        }
        else {
            n22 = n19 + 3.141592653589793;
        }
        graphics.setColor(Color.black);
        for (int j = 0; j < 90; ++j) {
            graphics.fillRect((int)(n18 * Math.cos(n22)) + n20, (int)(n18 * Math.sin(n22)) + n21, 1, 1);
            n22 += 0.02;
        }
        final double n23 = LorenzMS.deg[n12] - 1.5707963267948966;
        final int n24 = (int)(n18 * Math.cos(n23)) + n16;
        final int n25 = (int)(n18 * Math.sin(n23)) + n17;
        double n26;
        if (n6 > n8) {
            n26 = n23 - 1.5707963267948966;
        }
        else {
            n26 = n23 - 3.141592653589793;
        }
        for (int k = 0; k < 90; ++k) {
            graphics.fillRect((int)(n18 * Math.cos(n26)) + n24, (int)(n18 * Math.sin(n26)) + n25, 1, 1);
            n26 -= 0.02;
        }
        graphics.drawLine(n16, n17, n14, n15);
        graphics.setColor(white);
        final double n27 = n13 * 2.0 / 5.0;
        final int n28 = (int)(n27 * cos) + n8;
        final int n29 = (int)(n27 * sin) + n9;
        final double n30 = n13 * 4.0 / 15.0;
        final double cos2 = Math.cos(LorenzMS.deg[n12] + 1.5707963267948966);
        final double sin2 = Math.sin(LorenzMS.deg[n12] + 1.5707963267948966);
        this.fillO(graphics, (int)(n30 * cos2) + n28, (int)(n30 * sin2) + n29, (int)n30);
        this.fillO(graphics, (int)(-n30 * cos2) + n28, (int)(-n30 * sin2) + n29, (int)n30);
        final double n31 = n13 * 4.0 / 5.0;
        final int n32 = (int)(n31 * cos) + n8;
        final int n33 = (int)(n31 * sin) + n9;
        final double n34 = n13 * 2.0 / 10.0;
        this.fillO(graphics, (int)((int)n34 * cos2) + n32, (int)(n34 * sin2) + n33, (int)n34);
        this.fillO(graphics, (int)(-n34 * cos2) + n32, (int)(-n34 * sin2) + n33, (int)n34);
    }
    
    public void fillO(final Graphics graphics, final int n, final int n2, final int n3) {
        final int n4 = 90;
        final double n5 = 6.283185307179586 / n4;
        double n6 = 0.0;
        for (int i = 0; i < n4; ++i) {
            graphics.drawLine(n, n2, (int)(n3 * Math.cos(n6)) + n, (int)(n3 * Math.sin(n6)) + n2);
            n6 += n5;
        }
    }
    
    public int rnd(final int n) {
        return (int)(Math.random() * n);
    }
    
    public boolean flip() {
        return this.rnd(2) == 0;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 0:
            case 53:
            case 65535: {
                LorenzMS.fly = !LorenzMS.fly;
                break;
            }
            case 55:
            case 1000: {
                LorenzMS.x0 += 7;
                LorenzMS.y0 += 7;
                break;
            }
            case 56:
            case 1004: {
                LorenzMS.y0 += 10;
                break;
            }
            case 57:
            case 1002: {
                LorenzMS.x0 -= 7;
                LorenzMS.y0 += 7;
                break;
            }
            case 52:
            case 1006: {
                LorenzMS.x0 += 10;
                break;
            }
            case 54:
            case 1007: {
                LorenzMS.x0 -= 10;
                break;
            }
            case 49:
            case 1001: {
                LorenzMS.x0 += 7;
                LorenzMS.y0 -= 7;
                break;
            }
            case 50:
            case 1005: {
                LorenzMS.y0 -= 10;
                break;
            }
            case 51:
            case 1003: {
                LorenzMS.x0 -= 7;
                LorenzMS.y0 -= 7;
                break;
            }
            case 43: {
                --LorenzMS.z0;
                break;
            }
            case 45: {
                ++LorenzMS.z0;
                break;
            }
            case 10: {
                LorenzMS.z0 = 0;
                break;
            }
            case 48:
            case 1025: {
                LorenzMS.x0 = (LorenzMS.y0 = 0);
                break;
            }
            case 47: {
                LorenzMS.pause += 5;
                break;
            }
            case 42: {
                LorenzMS.pause = Math.max(LorenzMS.pause - 5, 0);
                break;
            }
            case 1023: {
                LorenzMS.pause = LorenzMS.defaultp;
                break;
            }
            case 46:
            case 127: {
                LorenzMS.x0 = (LorenzMS.y0 = (LorenzMS.z0 = 0));
                LorenzMS.pause = LorenzMS.defaultp;
                LorenzMS.fly = true;
                break;
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        LorenzMS.x0 -= n - LorenzMS.cX;
        LorenzMS.y0 -= n2 - LorenzMS.cY;
        return true;
    }
    
    static {
        LorenzMS.carmen = null;
        LorenzMS.defaultp = 200;
        LorenzMS.pause = LorenzMS.defaultp;
        LorenzMS.m = new double[17];
        deg = new double[17];
        LorenzMS.color = new Color[6];
        LorenzMS.show = new boolean[6];
        LorenzMS.deg[0] = -1.5707963267948966;
        final double n = 0.20943951023931953;
        for (int i = 0; i < 16; ++i) {
            LorenzMS.m[i] = Math.sin(LorenzMS.deg[i]) / Math.cos(LorenzMS.deg[i]);
            LorenzMS.deg[i + 1] = LorenzMS.deg[i] + n;
        }
    }
}
