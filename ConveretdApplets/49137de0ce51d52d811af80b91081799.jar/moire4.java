import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Button;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Checkbox;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class moire4 extends Applet implements ActionListener, ItemListener, MouseListener, MouseMotionListener
{
    final double pi = 3.1415926;
    final double r3 = 1.7320508;
    final double haba = 0.9;
    final int gray = 100;
    final int lines = 40;
    final int lines0 = 30;
    final double rad0 = 0.1;
    final int circles = 40;
    final int lines1 = 20;
    final int lines2 = 50;
    double[] radialx;
    double[] radialy;
    int sheet0;
    int sheet1;
    double xoff;
    double yoff;
    double bai;
    double px;
    double py;
    double ex;
    double ey;
    double fx;
    double fy;
    vpoint pnow;
    int keep;
    int at;
    int mox;
    int moy;
    Checkbox sh00;
    Checkbox sh01;
    Checkbox sh02;
    Checkbox sh03;
    Checkbox sh04;
    Checkbox sh05;
    Checkbox sh10;
    Checkbox sh11;
    Checkbox sh12;
    Checkbox sh13;
    Checkbox sh14;
    Checkbox sh15;
    Image image1;
    Graphics offg;
    
    public moire4() {
        this.radialx = new double[120];
        this.radialy = new double[120];
        this.keep = -1;
        this.at = -1;
    }
    
    public void init() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.xoff = width / 2.0;
        this.yoff = height / 2.0 + 20.0;
        this.bai = height / 20;
        this.px = 0.0;
        this.py = 0.0;
        this.ex = 8.0;
        this.ey = 0.0;
        this.fx = 0.0;
        this.fy = 8.0;
        this.sheet0 = 0;
        this.sheet1 = 0;
        this.image1 = this.createImage(width, height);
        this.offg = this.image1.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLayout(new FlowLayout(0));
        final Panel panel = new Panel();
        final Button button = new Button("Reset");
        panel.add(button);
        button.addActionListener(this);
        this.add("West", panel);
        final Panel panel2 = new Panel();
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.sh10 = new Checkbox("line1", checkboxGroup, true);
        this.sh11 = new Checkbox("line2", checkboxGroup, false);
        this.sh12 = new Checkbox("radial", checkboxGroup, false);
        this.sh13 = new Checkbox("circle", checkboxGroup, false);
        this.sh14 = new Checkbox("tri", checkboxGroup, false);
        this.sh15 = new Checkbox("hexa", checkboxGroup, false);
        panel2.add(this.sh10);
        panel2.add(this.sh11);
        panel2.add(this.sh12);
        panel2.add(this.sh13);
        panel2.add(this.sh14);
        panel2.add(this.sh15);
        this.sh10.addItemListener(this);
        this.sh11.addItemListener(this);
        this.sh12.addItemListener(this);
        this.sh13.addItemListener(this);
        this.sh14.addItemListener(this);
        this.sh15.addItemListener(this);
        this.add(panel2);
        this.init_radial();
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.offg.setColor(new Color(100, 100, 100));
        this.offg.fillRect(0, 0, width, height);
        this.offg.setColor(new Color(0, 0, 0));
        this.offg.drawRect(0, 0, width - 2, height - 2);
        this.draw_sheet1(this.offg);
        this.draw_sheet0(this.offg);
        this.draw_cur(this.offg);
        graphics.drawImage(this.image1, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.sh00) {
            this.sheet0 = 0;
        }
        else if (itemEvent.getItemSelectable() == this.sh01) {
            this.sheet0 = 1;
        }
        else if (itemEvent.getItemSelectable() == this.sh02) {
            this.sheet0 = 2;
        }
        else if (itemEvent.getItemSelectable() == this.sh03) {
            this.sheet0 = 3;
        }
        else if (itemEvent.getItemSelectable() == this.sh04) {
            this.sheet0 = 4;
        }
        else if (itemEvent.getItemSelectable() == this.sh05) {
            this.sheet0 = 5;
        }
        else if (itemEvent.getItemSelectable() == this.sh10) {
            this.sheet1 = 0;
        }
        else if (itemEvent.getItemSelectable() == this.sh11) {
            this.sheet1 = 1;
        }
        else if (itemEvent.getItemSelectable() == this.sh12) {
            this.sheet1 = 2;
        }
        else if (itemEvent.getItemSelectable() == this.sh13) {
            this.sheet1 = 3;
        }
        else if (itemEvent.getItemSelectable() == this.sh14) {
            this.sheet1 = 4;
        }
        else if (itemEvent.getItemSelectable() == this.sh15) {
            this.sheet1 = 5;
        }
        this.sheet0 = this.sheet1;
        this.px = 0.0;
        this.py = 0.0;
        this.ex = 8.0;
        this.ey = 0.0;
        this.fx = 0.0;
        this.fy = 8.0;
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Reset")) {
            this.px = 0.0;
            this.py = 0.0;
            this.ex = 8.0;
            this.ey = 0.0;
            this.fx = 0.0;
            this.fy = 8.0;
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.keep = -1;
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final vpoint d2v = this.d2v(new dpoint(mouseEvent.getX(), mouseEvent.getY()));
        switch (this.keep) {
            case 0: {
                this.px += d2v.x - this.pnow.x;
                this.py += d2v.y - this.pnow.y;
                break;
            }
            case 1: {
                this.rot_ef(d2v, this.pnow);
                break;
            }
            case 2: {
                this.resize(d2v, this.pnow);
                break;
            }
        }
        this.pnow = d2v;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final dpoint dpoint = new dpoint(mouseEvent.getX(), mouseEvent.getY());
        switch (this.inRange(this.v2s(this.d2v(dpoint)))) {
            case 0: {
                this.pnow = this.d2v(dpoint);
                this.keep = 0;
                break;
            }
            case 1: {
                this.pnow = this.d2v(dpoint);
                this.keep = 1;
                break;
            }
            case 2: {
                this.pnow = this.d2v(dpoint);
                this.keep = 2;
                break;
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mox = mouseEvent.getX();
        this.moy = mouseEvent.getY();
        this.at = this.inRange(this.v2s(this.d2v(new dpoint(this.mox, this.moy))));
        this.repaint();
    }
    
    private void rot_ef(final vpoint vpoint, final vpoint vpoint2) {
        final double n = vpoint2.x - this.px;
        final double n2 = vpoint2.y - this.py;
        final double n3 = vpoint.x - this.px;
        final double n4 = vpoint.y - this.py;
        final double n5 = n * n3 + n2 * n4;
        final double sqrt = Math.sqrt(n * n + n2 * n2);
        final double sqrt2 = Math.sqrt(n3 * n3 + n4 * n4);
        final double n6 = n5 / sqrt / sqrt2;
        final double n7 = (n * n4 - n2 * n3) / sqrt / sqrt2;
        final double ex = this.ex;
        final double ey = this.ey;
        final double fx = this.fx;
        final double fy = this.fy;
        this.ex = n6 * ex - n7 * ey;
        this.ey = n7 * ex + n6 * ey;
        this.fx = n6 * fx - n7 * fy;
        this.fy = n7 * fx + n6 * fy;
    }
    
    private void resize(final vpoint vpoint, final vpoint vpoint2) {
        final double n = vpoint2.x - this.px;
        final double n2 = vpoint2.y - this.py;
        final double n3 = vpoint.x - this.px;
        final double n4 = vpoint.y - this.py;
        final double sqrt = Math.sqrt(n * n + n2 * n2);
        final double sqrt2 = Math.sqrt(n3 * n3 + n4 * n4);
        this.ex *= sqrt2 / sqrt;
        this.ey *= sqrt2 / sqrt;
        this.fx *= sqrt2 / sqrt;
        this.fy *= sqrt2 / sqrt;
    }
    
    private vpoint d2v(final dpoint dpoint) {
        return new vpoint((dpoint.x - this.xoff) / this.bai, -(dpoint.y - this.yoff) / this.bai);
    }
    
    private dpoint v2d(final vpoint vpoint) {
        return new dpoint((int)(this.bai * vpoint.x + this.xoff), (int)(-this.bai * vpoint.y + this.yoff));
    }
    
    private vpoint s2v(final spoint spoint) {
        return new vpoint(this.px + spoint.u * this.ex + spoint.v * this.fx, this.py + spoint.u * this.ey + spoint.v * this.fy);
    }
    
    private vpoint ks2v(final spoint spoint) {
        return new vpoint(0.0 + spoint.u * 8.0 + spoint.v * 0.0, 0.0 + spoint.u * 0.0 + spoint.v * 8.0);
    }
    
    private spoint v2s(final vpoint vpoint) {
        final double n = this.ex * this.fy - this.fx * this.ey;
        return new spoint(1.0 / n * (this.fy * (vpoint.x - this.px) - this.fx * (vpoint.y - this.py)), 1.0 / n * (-this.ey * (vpoint.x - this.px) + this.ex * (vpoint.y - this.py)));
    }
    
    private int inRange(final spoint spoint) {
        if (spoint.u >= -0.9 && spoint.u <= 0.9 && spoint.v >= -0.9 && spoint.v <= 0.9) {
            return 0;
        }
        if (spoint.u < -1.0 || spoint.u > 1.0 || spoint.v < -1.0 || spoint.v > 1.0) {
            return -1;
        }
        if (Math.abs(spoint.u) > 0.9 && Math.abs(spoint.v) > 0.9) {
            return 2;
        }
        return 1;
    }
    
    private void dline(final Graphics graphics, final vpoint vpoint, final vpoint vpoint2) {
        graphics.drawLine((int)(this.bai * vpoint.x + this.xoff), (int)(-this.bai * vpoint.y + this.yoff), (int)(this.bai * vpoint2.x + this.xoff), (int)(-this.bai * vpoint2.y + this.yoff));
    }
    
    private void dcircle(final Graphics graphics, final vpoint vpoint, final double n, final int n2, final int n3) {
        final int n4 = (int)(this.bai * vpoint.x + this.xoff);
        final int n5 = (int)(-this.bai * vpoint.y + this.yoff);
        final int n6 = (int)(this.bai * n);
        graphics.drawArc(n4 - n6, n5 - n6, 2 * n6, 2 * n6, n2, n3);
    }
    
    private void init_radial() {
        final double n = 0.052359876666666666;
        for (int i = 0; i < 30; ++i) {
            final double n2 = -0.78539815 + n * i;
            this.radialx[i] = 1.0;
            this.radialy[i] = Math.tan(n2);
        }
        for (int j = 30; j < 60; ++j) {
            this.radialx[j] = -this.radialy[j - 30];
            this.radialy[j] = 1.0;
        }
        for (int k = 60; k < 120; ++k) {
            this.radialx[k] = -this.radialx[k - 60];
            this.radialy[k] = -this.radialy[k - 60];
        }
    }
    
    private void draw_sheet0(final Graphics graphics) {
        final spoint spoint = new spoint(0.0, 0.0);
        final spoint spoint2 = new spoint(0.0, 0.0);
        final spoint spoint3 = new spoint();
        final vpoint vpoint = new vpoint(0.0, 0.0);
        final vpoint vpoint2 = new vpoint(0.0, 0.0);
        final vpoint vpoint3 = new vpoint(0.0, 0.0);
        final vpoint vpoint4 = new vpoint(0.0, 0.0);
        this.offg.setColor(new Color(0, 0, 100));
        switch (this.sheet0) {
            case 0: {
                spoint.u = -1.0;
                spoint2.u = 1.0;
                for (int i = -40; i <= 40; ++i) {
                    spoint.v = i / 40.0;
                    spoint2.v = i / 40.0;
                    this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                }
                spoint.u = 1.0;
                spoint.v = -1.0;
                final vpoint s2v = this.s2v(spoint);
                spoint2.u = 1.0;
                spoint2.v = 1.0;
                this.dline(graphics, s2v, this.s2v(spoint2));
                spoint.u = -1.0;
                spoint.v = -1.0;
                final vpoint s2v2 = this.s2v(spoint);
                spoint2.u = -1.0;
                spoint2.v = 1.0;
                this.dline(graphics, s2v2, this.s2v(spoint2));
                break;
            }
            case 1: {
                spoint.u = -1.0;
                spoint2.u = 1.0;
                for (int j = -40; j <= 40; ++j) {
                    spoint.v = j / 40.0;
                    spoint2.v = j / 40.0;
                    this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                }
                spoint.v = -1.0;
                spoint2.v = 1.0;
                for (int k = -40; k <= 40; ++k) {
                    spoint.u = k / 40.0;
                    spoint2.u = k / 40.0;
                    this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                }
                break;
            }
            case 2: {
                for (int l = 0; l < 120; ++l) {
                    if (l % 2 == 0) {
                        spoint.u = 0.0;
                        spoint.v = 0.0;
                    }
                    else {
                        spoint.u = 0.1 * Math.cos(l * 3.1415926 / 2.0 / 30.0 - 0.78539815);
                        spoint.v = 0.1 * Math.sin(l * 3.1415926 / 2.0 / 30.0 - 0.78539815);
                    }
                    spoint3.u = this.radialx[l];
                    spoint3.v = this.radialy[l];
                    this.dline(graphics, this.s2v(spoint), this.s2v(spoint3));
                }
                spoint3.u = this.radialx[0];
                spoint3.v = this.radialy[0];
                final vpoint s2v3 = this.s2v(spoint3);
                spoint3.u = this.radialx[30];
                spoint3.v = this.radialy[30];
                final vpoint s2v4 = this.s2v(spoint3);
                spoint3.u = this.radialx[60];
                spoint3.v = this.radialy[60];
                final vpoint s2v5 = this.s2v(spoint3);
                spoint3.u = this.radialx[90];
                spoint3.v = this.radialy[90];
                final vpoint s2v6 = this.s2v(spoint3);
                this.dline(graphics, s2v3, s2v4);
                this.dline(graphics, s2v4, s2v5);
                this.dline(graphics, s2v5, s2v6);
                this.dline(graphics, s2v6, s2v3);
                break;
            }
            case 3: {
                spoint.u = 0.0;
                spoint.v = 0.0;
                final vpoint s2v7 = this.s2v(spoint);
                final double sqrt = Math.sqrt(this.ex * this.ex + this.ey * this.ey);
                final double asin = Math.asin(this.ey / sqrt);
                for (int n = 1; n <= 56; ++n) {
                    final double n2 = n * sqrt / 40.0;
                    if (n <= 40) {
                        this.dcircle(graphics, s2v7, n2, 0, 360);
                    }
                    else {
                        final double acos = Math.acos(sqrt / n2);
                        int n3 = (int)((acos + asin) / 2.0 / 3.1415926 * 360.0);
                        final int n4 = (int)(2.0 * (0.78539815 - acos) / 2.0 / 3.1415926 * 360.0);
                        this.dcircle(graphics, s2v7, n2, n3, n4);
                        n3 += 90;
                        this.dcircle(graphics, s2v7, n2, n3, n4);
                        n3 += 90;
                        this.dcircle(graphics, s2v7, n2, n3, n4);
                        n3 += 90;
                        this.dcircle(graphics, s2v7, n2, n3, n4);
                    }
                }
                spoint3.u = this.radialx[0];
                spoint3.v = this.radialy[0];
                final vpoint s2v8 = this.s2v(spoint3);
                spoint3.u = this.radialx[30];
                spoint3.v = this.radialy[30];
                final vpoint s2v9 = this.s2v(spoint3);
                spoint3.u = this.radialx[60];
                spoint3.v = this.radialy[60];
                final vpoint s2v10 = this.s2v(spoint3);
                spoint3.u = this.radialx[90];
                spoint3.v = this.radialy[90];
                final vpoint s2v11 = this.s2v(spoint3);
                this.dline(graphics, s2v8, s2v9);
                this.dline(graphics, s2v9, s2v10);
                this.dline(graphics, s2v10, s2v11);
                this.dline(graphics, s2v11, s2v8);
                break;
            }
            case 4: {
                for (int n5 = 27, n6 = -n5; n6 <= 20 + n5; ++n6) {
                    if (n6 < 0) {
                        spoint.u = n6 * 0.8452994565748302 / 20.0 + 1.1547005434251698 - 1.0;
                        spoint.v = 1.0;
                        spoint2.u = -1.0;
                        spoint2.v = n6 * -1.4641015999999998 / 20.0 - 1.0;
                        this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                        spoint.v = -spoint.v;
                        spoint2.v = -spoint2.v;
                        this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                    }
                    else if (n6 <= 20) {
                        spoint.u = n6 * 0.8452994565748302 / 20.0 - 1.0;
                        spoint.v = -1.0;
                        spoint2.u = spoint.u + 1.1547005434251698;
                        spoint2.v = 1.0;
                        this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                        spoint.v = -spoint.v;
                        spoint2.v = -spoint2.v;
                        this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                    }
                    else {
                        spoint.u = n6 * 0.8452994565748302 / 20.0 - 1.0;
                        spoint.v = -1.0;
                        spoint2.u = 1.0;
                        spoint2.v = n6 * -1.4641015999999998 / 20.0 + 3.4641016 - 1.0;
                        this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                        spoint.v = -spoint.v;
                        spoint2.v = -spoint2.v;
                        this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                    }
                }
                for (int n7 = 27, n8 = -n7; n8 <= n7; ++n8) {
                    spoint.u = -1.0;
                    spoint.v = n8 * 0.036602539999999996;
                    spoint2.u = 1.0;
                    spoint2.v = spoint.v;
                    this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                }
                break;
            }
            case 5: {
                for (int n9 = 68, n10 = -n9; n10 <= 50 + n9; ++n10) {
                    if (n10 % 2 != 0) {
                        if (n10 < 0) {
                            spoint.u = n10 * 0.8452994565748302 / 50.0 + 1.1547005434251698 - 1.0;
                            spoint.v = 1.0;
                            spoint2.u = -1.0;
                            spoint2.v = n10 * -1.4641015999999998 / 50.0 - 1.0;
                            this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                            spoint.v = -spoint.v;
                            spoint2.v = -spoint2.v;
                            this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                        }
                        else if (n10 <= 50) {
                            spoint.u = n10 * 0.8452994565748302 / 50.0 - 1.0;
                            spoint.v = -1.0;
                            spoint2.u = spoint.u + 1.1547005434251698;
                            spoint2.v = 1.0;
                            this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                            spoint.v = -spoint.v;
                            spoint2.v = -spoint2.v;
                            this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                        }
                        else {
                            spoint.u = n10 * 0.8452994565748302 / 50.0 - 1.0;
                            spoint.v = -1.0;
                            spoint2.u = 1.0;
                            spoint2.v = n10 * -1.4641015999999998 / 50.0 + 3.4641016 - 1.0;
                            this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                            spoint.v = -spoint.v;
                            spoint2.v = -spoint2.v;
                            this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                        }
                    }
                }
                for (int n11 = 68, n12 = -n11; n12 <= n11; ++n12) {
                    if (n12 % 2 != 0) {
                        spoint.u = -1.0;
                        spoint.v = n12 * 0.014641015999999998;
                        spoint2.u = 1.0;
                        spoint2.v = spoint.v;
                        this.dline(graphics, this.s2v(spoint), this.s2v(spoint2));
                    }
                }
                break;
            }
        }
    }
    
    private void draw_sheet1(final Graphics graphics) {
        final vpoint vpoint = new vpoint(-8.0, -8.0);
        final vpoint vpoint2 = new vpoint(8.0, 8.0);
        final spoint spoint = new spoint(0.0, 0.0);
        final spoint spoint2 = new spoint(0.0, 0.0);
        this.offg.setColor(new Color(255, 200, 200));
        this.offg.fillRect(this.v2d(vpoint).x, this.v2d(vpoint2).y, this.v2d(vpoint2).x - this.v2d(vpoint).x, -this.v2d(vpoint2).y + this.v2d(vpoint).y);
        this.offg.setColor(new Color(0, 0, 100));
        switch (this.sheet1) {
            case 0: {
                vpoint.x = -8.0;
                vpoint2.x = 8.0;
                for (int i = -40; i <= 40; ++i) {
                    vpoint.y = 8 * i / 40.0;
                    vpoint2.y = 8 * i / 40.0;
                    this.dline(graphics, vpoint, vpoint2);
                }
                break;
            }
            case 1: {
                vpoint.x = -8.0;
                vpoint2.x = 8.0;
                for (int j = -40; j <= 40; ++j) {
                    vpoint.y = 8 * j / 40.0;
                    vpoint2.y = 8 * j / 40.0;
                    this.dline(graphics, vpoint, vpoint2);
                }
                vpoint.y = -8.0;
                vpoint2.y = 8.0;
                for (int k = -40; k <= 40; ++k) {
                    vpoint.x = 8 * k / 40.0;
                    vpoint2.x = 8 * k / 40.0;
                    this.dline(graphics, vpoint, vpoint2);
                }
                break;
            }
            case 2: {
                for (int l = 0; l < 120; ++l) {
                    if (l % 2 == 0) {
                        vpoint.x = 0.0;
                        vpoint.y = 0.0;
                    }
                    else {
                        vpoint.x = 0.8 * Math.cos(l * 3.1415926 / 2.0 / 30.0 - 0.78539815);
                        vpoint.y = 0.8 * Math.sin(l * 3.1415926 / 2.0 / 30.0 - 0.78539815);
                    }
                    vpoint2.x = 8.0 * this.radialx[l];
                    vpoint2.y = 8.0 * this.radialy[l];
                    this.dline(graphics, vpoint, vpoint2);
                }
                break;
            }
            case 3: {
                vpoint.x = 0.0;
                vpoint.y = 0.0;
                for (int n = 1; n <= 56; ++n) {
                    final double n2 = n * 8 / 40.0;
                    if (n <= 40) {
                        this.dcircle(graphics, vpoint, n2, 0, 360);
                    }
                    else {
                        final double acos = Math.acos(8.0 / n2);
                        int n3 = (int)(acos / 2.0 / 3.1415926 * 360.0);
                        final int n4 = (int)(2.0 * (0.78539815 - acos) / 2.0 / 3.1415926 * 360.0);
                        this.dcircle(graphics, vpoint, n2, n3, n4);
                        n3 += 90;
                        this.dcircle(graphics, vpoint, n2, n3, n4);
                        n3 += 90;
                        this.dcircle(graphics, vpoint, n2, n3, n4);
                        n3 += 90;
                        this.dcircle(graphics, vpoint, n2, n3, n4);
                    }
                }
                break;
            }
            case 4: {
                for (int n5 = 27, n6 = -n5; n6 <= 20 + n5; ++n6) {
                    if (n6 < 0) {
                        spoint.u = n6 * 0.8452994565748302 / 20.0 + 1.1547005434251698 - 1.0;
                        spoint.v = 1.0;
                        spoint2.u = -1.0;
                        spoint2.v = n6 * -1.4641015999999998 / 20.0 - 1.0;
                        this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                        spoint.v = -spoint.v;
                        spoint2.v = -spoint2.v;
                        this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                    }
                    else if (n6 <= 20) {
                        spoint.u = n6 * 0.8452994565748302 / 20.0 - 1.0;
                        spoint.v = -1.0;
                        spoint2.u = spoint.u + 1.1547005434251698;
                        spoint2.v = 1.0;
                        this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                        spoint.v = -spoint.v;
                        spoint2.v = -spoint2.v;
                        this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                    }
                    else {
                        spoint.u = n6 * 0.8452994565748302 / 20.0 - 1.0;
                        spoint.v = -1.0;
                        spoint2.u = 1.0;
                        spoint2.v = n6 * -1.4641015999999998 / 20.0 + 3.4641016 - 1.0;
                        this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                        spoint.v = -spoint.v;
                        spoint2.v = -spoint2.v;
                        this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                    }
                }
                for (int n7 = 27, n8 = -n7; n8 <= n7; ++n8) {
                    spoint.u = -1.0;
                    spoint.v = n8 * 0.036602539999999996;
                    spoint2.u = 1.0;
                    spoint2.v = spoint.v;
                    this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                }
                break;
            }
            case 5: {
                for (int n9 = 68, n10 = -n9; n10 <= 50 + n9; ++n10) {
                    if (n10 % 2 != 0) {
                        if (n10 < 0) {
                            spoint.u = n10 * 0.8452994565748302 / 50.0 + 1.1547005434251698 - 1.0;
                            spoint.v = 1.0;
                            spoint2.u = -1.0;
                            spoint2.v = n10 * -1.4641015999999998 / 50.0 - 1.0;
                            this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                            spoint.v = -spoint.v;
                            spoint2.v = -spoint2.v;
                            this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                        }
                        else if (n10 <= 50) {
                            spoint.u = n10 * 0.8452994565748302 / 50.0 - 1.0;
                            spoint.v = -1.0;
                            spoint2.u = spoint.u + 1.1547005434251698;
                            spoint2.v = 1.0;
                            this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                            spoint.v = -spoint.v;
                            spoint2.v = -spoint2.v;
                            this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                        }
                        else {
                            spoint.u = n10 * 0.8452994565748302 / 50.0 - 1.0;
                            spoint.v = -1.0;
                            spoint2.u = 1.0;
                            spoint2.v = n10 * -1.4641015999999998 / 50.0 + 3.4641016 - 1.0;
                            this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                            spoint.v = -spoint.v;
                            spoint2.v = -spoint2.v;
                            this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                        }
                    }
                }
                for (int n11 = 68, n12 = -n11; n12 <= n11; ++n12) {
                    if (n12 % 2 != 0) {
                        spoint.u = -1.0;
                        spoint.v = n12 * 0.014641015999999998;
                        spoint2.u = 1.0;
                        spoint2.v = spoint.v;
                        this.dline(graphics, this.ks2v(spoint), this.ks2v(spoint2));
                    }
                }
                break;
            }
        }
    }
    
    private void draw_cur(final Graphics graphics) {
        String s = "";
        switch (this.at) {
            case 0: {
                s = "Translation";
                graphics.setColor(new Color(255, 0, 0));
                break;
            }
            case 1: {
                s = "Rotation";
                graphics.setColor(new Color(255, 255, 0));
                break;
            }
            case 2: {
                s = "Scaling";
                graphics.setColor(new Color(0, 255, 255));
                break;
            }
        }
        graphics.setFont(new Font("Arial", 0, 18));
        graphics.drawString(s, this.mox, this.moy);
    }
    
    private class dpoint
    {
        int x;
        int y;
        
        dpoint() {
        }
        
        dpoint(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private class vpoint
    {
        double x;
        double y;
        
        vpoint() {
        }
        
        vpoint(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private class spoint
    {
        double u;
        double v;
        
        spoint() {
        }
        
        spoint(final double u, final double v) {
            this.u = u;
            this.v = v;
        }
    }
}
