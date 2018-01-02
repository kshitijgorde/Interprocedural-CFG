// 
// Decompiled by Procyon v0.5.30
// 

package de.mmkh.tams;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.TextField;
import java.util.Random;
import java.awt.Panel;

public class YieldDemo extends Panel
{
    Random generator;
    Panel yieldPanel;
    TextField xsizeTF;
    TextField ysizeTF;
    TextField dxTF;
    TextField dyTF;
    double xsize;
    double ysize;
    double xoffset;
    double yoffset;
    double wafersize;
    double defectdensity;
    int n_defects;
    int n_chips;
    int n_bad_chips;
    public final Color SALMON;
    public final Color MISTY_ROSE;
    public final Color LIGHT_PINK;
    public final Color LIGHT_GREEN;
    public final Color WAFER_COLOR;
    public final Color BORDER_COLOR;
    public final Color GOOD_COLOR;
    public final Color BAD_COLOR;
    public final Color DEFECT_COLOR;
    public final int OUTSIDE;
    public final int GOOD;
    public final int BAD;
    public final double PIXELS_PER_MM;
    public final double CUTOFF;
    int[][] chips;
    double[] defects_x;
    double[] defects_y;
    
    public void addSelftestMouseListener() {
        if (this == null) {
            throw null;
        }
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                System.out.println("mousePressed");
                YieldDemo.this.xsize = 2.0 + YieldDemo.this.generator.nextDouble() * 50.0;
                YieldDemo.this.ysize = 2.0 + YieldDemo.this.generator.nextDouble() * 50.0;
                YieldDemo.this.defectdensity = YieldDemo.this.generator.nextDouble() * 0.05;
                YieldDemo.this.n_defects = (int)(YieldDemo.this.generator.nextDouble() * 1000.0);
                YieldDemo.this.updateAll();
            }
            
            {
                this.constructor$0(YieldDemo.this);
            }
            
            private final void constructor$0(final YieldDemo yieldDemo) {
            }
        });
    }
    
    public void updateAll() {
        this.makeChips();
        this.makeDefects();
        this.repaint();
    }
    
    public double getXSize() {
        return this.xsize;
    }
    
    public double getYSize() {
        return this.ysize;
    }
    
    public double getXOffset() {
        return this.xoffset;
    }
    
    public double getYOffset() {
        return this.yoffset;
    }
    
    public double getWaferSize() {
        return this.wafersize;
    }
    
    public int getNumberOfDefects() {
        return this.n_defects;
    }
    
    public int countChips() {
        return this.n_chips;
    }
    
    public int countBadChips() {
        return this.n_bad_chips;
    }
    
    public double getYield() {
        if (this.n_chips == 0) {
            return 1.0;
        }
        return 1.0 * (this.n_chips - this.n_bad_chips) / this.n_chips;
    }
    
    public void setXSize(final double xsize) {
        this.xsize = xsize;
    }
    
    public void setYSize(final double ysize) {
        this.ysize = ysize;
    }
    
    public void setXOffset(final double xoffset) {
        this.xoffset = xoffset;
    }
    
    public void setYOffset(final double yoffset) {
        this.yoffset = yoffset;
    }
    
    public void setWaferSize(final double wafersize) {
        this.wafersize = wafersize;
    }
    
    public void setNumberOfDefects(final int n_defects) {
        this.n_defects = n_defects;
    }
    
    public void makeChips() {
        this.n_chips = 0;
        final int n = 2 * (int)Math.ceil(0.5 * this.wafersize / this.xsize);
        final int n2 = 2 * (int)Math.ceil(0.5 * this.wafersize / this.ysize);
        this.chips = new int[n][n2];
        System.out.println("...makeChips: " + n + " " + n2);
        final double n3 = this.xoffset - n / 2 * this.xsize;
        final double n4 = this.yoffset - n2 / 2 * this.ysize;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                final double n5 = n3 + i * this.xsize;
                final double n6 = n4 + j * this.ysize;
                if (this.isInsideWafer(n5, n6) && this.isInsideWafer(n5 + this.xsize, n6) && this.isInsideWafer(n5, n6 + this.ysize) && this.isInsideWafer(n5 + this.xsize, n6 + this.ysize)) {
                    this.chips[i][j] = 3;
                    ++this.n_chips;
                }
                else {
                    this.chips[i][j] = 1;
                }
            }
        }
    }
    
    public void makeDefects() {
        this.n_bad_chips = 0;
        this.defects_x = new double[this.n_defects];
        this.defects_y = new double[this.n_defects];
        final int length = this.chips.length;
        final int length2 = this.chips[0].length;
        int i = 0;
        while (i < this.n_defects) {
            final double n = this.generator.nextDouble() * this.wafersize - 0.5 * this.wafersize;
            final double n2 = this.generator.nextDouble() * this.wafersize - 0.5 * this.wafersize;
            if (this.isInsideWafer(n, n2)) {
                this.defects_x[i] = n;
                this.defects_y[i] = n2;
                ++i;
                final int n3 = length / 2 + (int)Math.floor((n - this.xoffset) / this.xsize);
                final int n4 = length2 / 2 + (int)Math.floor((n2 - this.yoffset) / this.ysize);
                if (this.chips[n3][n4] != 3) {
                    continue;
                }
                this.chips[n3][n4] = 4;
                ++this.n_bad_chips;
            }
        }
        System.out.println("makeDefects: " + i);
    }
    
    public boolean isInsideWafer(final double n, final double n2) {
        final double n3 = 0.5 * this.wafersize;
        final boolean b = Math.sqrt(n * n + n2 * n2) < n3;
        final boolean b2 = n2 > 0.9 * n3;
        return b && !b2;
    }
    
    public int scale(final double n) {
        return (int)(n * 2.834645669291339);
    }
    
    public int getScreenX(final double n) {
        return this.getSize().width / 2 + this.scale(n);
    }
    
    public int getScreenY(final double n) {
        return this.getSize().height / 2 + this.scale(n);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(500, 500);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, size.width, size.height);
        this.drawWafer(graphics);
        this.drawChips(graphics);
        this.drawDefects(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void drawWafer(final Graphics graphics) {
        final Dimension size = this.getSize();
        final int n = size.width / 2;
        final int n2 = size.height / 2;
        final int scale = this.scale(0.5 * this.wafersize);
        graphics.setColor(this.WAFER_COLOR);
        graphics.fillOval(n - scale, n2 - scale, 2 * scale, 2 * scale);
        final int n3 = n2 + (int)(0.9 * scale);
        graphics.setColor(Color.white);
        graphics.fillRect(0, n3, size.width, n3);
        graphics.setColor(this.WAFER_COLOR);
        graphics.drawOval(n - scale, n2 - scale, 2 * scale, 2 * scale);
    }
    
    public void drawChips(final Graphics graphics) {
        System.out.println("...drawChips...");
        final int length = this.chips.length;
        final int length2 = this.chips[0].length;
        final double n = this.xoffset - length / 2 * this.xsize;
        final double n2 = this.yoffset - length2 / 2 * this.ysize;
        final int scale = this.scale(this.xsize);
        final int scale2 = this.scale(this.ysize);
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length2; ++j) {
                final int screenX = this.getScreenX(n + i * this.xsize);
                final int screenY = this.getScreenY(n2 + j * this.ysize);
                final int n3 = this.chips[i][j];
                if (n3 == 3) {
                    graphics.setColor(this.BORDER_COLOR);
                    graphics.drawRect(screenX, screenY, scale, scale2);
                    graphics.setColor(this.GOOD_COLOR);
                    graphics.fillRect(screenX + 1, screenY + 1, scale - 2, scale2 - 2);
                }
                else if (n3 == 4) {
                    graphics.setColor(this.BORDER_COLOR);
                    graphics.drawRect(screenX, screenY, scale, scale2);
                    graphics.setColor(this.BAD_COLOR);
                    graphics.fillRect(screenX + 1, screenY + 1, scale - 2, scale2 - 2);
                }
                else if (n3 == 1) {
                    graphics.setColor(this.BORDER_COLOR);
                    graphics.drawRect(screenX, screenY, scale, scale2);
                }
            }
        }
    }
    
    public void drawDefects(final Graphics graphics) {
        System.out.println("...drawDefects...");
        graphics.setColor(this.DEFECT_COLOR);
        for (int i = 0; i < this.defects_x.length; ++i) {
            graphics.fillRect(this.getScreenX(this.defects_x[i]), this.getScreenY(this.defects_y[i]), 2, 2);
        }
    }
    
    public static void main(final String[] array) {
        final YieldDemo yieldDemo = new YieldDemo();
        yieldDemo.addSelftestMouseListener();
        final Frame frame = new Frame("Yield Demo");
        frame.add("Center", yieldDemo);
        frame.setSize(600, 600);
        frame.show();
        frame.repaint();
    }
    
    public YieldDemo() {
        this.generator = new Random();
        this.n_chips = 0;
        this.n_bad_chips = 0;
        this.SALMON = new Color(250, 128, 114);
        this.MISTY_ROSE = new Color(238, 213, 210);
        this.LIGHT_PINK = new Color(255, 182, 193);
        this.LIGHT_GREEN = new Color(144, 238, 144);
        this.WAFER_COLOR = this.MISTY_ROSE;
        this.BORDER_COLOR = Color.white;
        this.GOOD_COLOR = this.LIGHT_GREEN;
        this.BAD_COLOR = Color.red.darker();
        this.DEFECT_COLOR = Color.black;
        this.OUTSIDE = 1;
        this.GOOD = 3;
        this.BAD = 4;
        this.PIXELS_PER_MM = 2.834645669291339;
        this.CUTOFF = 0.9;
        this.setLayout(new BorderLayout());
        this.xsize = 5.0;
        this.ysize = 8.0;
        this.xoffset = 0.0;
        this.yoffset = 0.0;
        this.wafersize = 152.39999999999998;
        this.defectdensity = 0.01;
        this.n_defects = 20;
        this.makeChips();
        this.makeDefects();
    }
}
