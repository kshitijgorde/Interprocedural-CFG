// 
// Decompiled by Procyon v0.5.30
// 

package com.tecnick.jadc;

import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Panel;

public class DisplayPanel extends Panel
{
    private static final long serialVersionUID = 4474611071354733117L;
    private Graphics bufferGraphics;
    private Image offscreen;
    private Image img_bg;
    private Image[] dig;
    private Image[] display_digits;
    private String current_info;
    private int num_digits;
    private int w;
    private int h;
    private int dw;
    private int dh;
    private int dx;
    private int dy;
    private int bw;
    private int bh;
    private int bx;
    private int by;
    
    public DisplayPanel() {
        this.dig = new Image[15];
        this.current_info = "";
        this.num_digits = 1;
        this.img_bg = null;
        this.dig = null;
    }
    
    public DisplayPanel(final int num_digits, final String current_info, final Image img_bg, final Image[] dig, final int w, final int h) {
        this.dig = new Image[15];
        this.num_digits = num_digits;
        this.display_digits = new Image[num_digits];
        this.current_info = current_info;
        this.img_bg = img_bg;
        this.dig = dig;
        this.w = w;
        this.h = h;
        this.setLayout(null);
        this.resize();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setInfo(final String current_info) {
        this.current_info = current_info;
        this.repaint();
    }
    
    public void setBackgroundImage(final Image img_bg) {
        this.img_bg = img_bg;
        this.repaint();
    }
    
    public void setNumDigits(final int num_digits) {
        this.num_digits = num_digits;
        this.display_digits = new Image[num_digits];
        this.repaint();
    }
    
    public void setDigitsImages(final Image[] dig) {
        this.dig = dig;
        this.repaint();
    }
    
    public synchronized void addNotify() {
        this.resize();
        super.addNotify();
    }
    
    public void resize() {
        this.bw = 0;
        this.bh = 0;
        this.bx = 0;
        this.by = 0;
        this.dw = 0;
        this.dh = 0;
        this.dx = 0;
        this.dy = 0;
        if (this.img_bg != null) {
            this.bw = this.img_bg.getWidth(this);
            this.bh = this.img_bg.getHeight(this);
        }
        if (this.current_info != null) {
            if (this.current_info.length() > this.num_digits) {
                this.current_info = this.current_info.substring(0, this.num_digits);
            }
            for (int i = 0; i < this.num_digits; ++i) {
                int int1;
                try {
                    int1 = Integer.parseInt(this.current_info.substring(i, i + 1));
                }
                catch (NumberFormatException ex) {
                    if (this.current_info.substring(i, i + 1).compareTo(":") == 0) {
                        int1 = 10;
                    }
                    else if (this.current_info.substring(i, i + 1).compareTo(".") == 0) {
                        int1 = 11;
                    }
                    else if (this.current_info.substring(i, i + 1).compareTo(" ") == 0) {
                        int1 = 12;
                    }
                    else if (this.current_info.substring(i, i + 1).compareTo("+") == 0) {
                        int1 = 13;
                    }
                    else if (this.current_info.substring(i, i + 1).compareTo("-") == 0) {
                        int1 = 14;
                    }
                    else {
                        int1 = 12;
                    }
                }
                if (this.dig[int1] != null) {
                    this.dw += this.dig[int1].getWidth(this);
                    this.dh = Math.max(this.dh, this.dig[int1].getHeight(this));
                    this.display_digits[i] = this.dig[int1];
                }
            }
        }
        this.dx = (this.w - this.dw) / 2;
        this.dy = (this.h - this.dh) / 2;
        this.bx = (this.w - this.bw) / 2;
        this.by = (this.h - this.bh) / 2;
        this.setSize(this.w, this.h);
    }
    
    protected void paintImage(final Graphics graphics, final Image image, final int n, final int n2) {
        if (image != null) {
            if (graphics == null) {
                return;
            }
            graphics.drawImage(image, n, n2, this);
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        this.resize();
        this.offscreen = this.createImage(this.w, this.h);
        (this.bufferGraphics = this.offscreen.getGraphics()).clearRect(0, 0, this.w, this.h);
        if (this.img_bg != null) {
            this.paintImage(this.bufferGraphics, this.img_bg, this.bx, this.by);
        }
        int dx = this.dx;
        for (int i = 0; i < this.num_digits; ++i) {
            this.paintImage(this.bufferGraphics, this.display_digits[i], dx, this.dy);
            dx += this.display_digits[i].getWidth(this);
        }
        graphics.drawImage(this.offscreen, 0, 0, this);
    }
}
