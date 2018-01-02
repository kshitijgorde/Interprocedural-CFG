// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

import java.awt.Font;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;
import java.awt.Dimension;

public class onScreenMenu
{
    private Dimension a;
    private Image b;
    private Component c;
    private Graphics d;
    private Graphics2D e;
    private String[][] f;
    private int[][] g;
    
    public onScreenMenu(final Dimension a, final Image b, final Component c, final String[][] f) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (this.b != null) {
            this.d = this.b.getGraphics();
            this.e = (Graphics2D)this.d;
        }
        this.f = new String[6][4];
        this.g = new int[1][1];
        this.f = f;
    }
    
    public void setOffscreenImage(final Image b) {
        if (this.d != null) {
            this.d.dispose();
        }
        this.b = b;
        this.d = this.b.getGraphics();
        this.e = (Graphics2D)this.d;
    }
    
    public void changeMenuText(final int n, final int n2, final String s) {
        this.f[n][n2] = s;
    }
    
    public Image getImage() {
        final int n = (int)(Math.min(this.a.width, this.a.height) * 0.07f);
        final int n2 = (int)(Math.min(this.a.width, this.a.height) * 0.05f);
        final int n3 = this.a.height - (n << 1) - 8 - (n2 << 1);
        final int n4 = this.a.width - (n << 1) - 8 - (n2 << 1);
        final int n5 = this.g.length - 1;
        final int length = this.g[n5].length;
        final int min = Math.min(n3 / (this.f[n5].length + 1), 32);
        this.e.setColor(Color.BLACK);
        final Composite composite = this.e.getComposite();
        this.e.setComposite(AlphaComposite.Src);
        this.e.setBackground(new Color(0, 0, 0, 0));
        this.e.clearRect(0, 0, this.a.width, this.a.height);
        this.e.setComposite(composite);
        this.e.setColor(Color.WHITE);
        this.e.setStroke(new BasicStroke(4.0f));
        this.e.drawRoundRect(n, n, this.a.width - (n << 1), this.a.height - (n << 1), 20, 20);
        this.e.setColor(new Color(10, 10, 10, 140));
        this.e.fillRoundRect(n, n, this.a.width - (n << 1), this.a.height - (n << 1), 20, 20);
        this.e.setColor(Color.WHITE);
        this.e.setFont(new Font("SansSerif", 1, min));
        final int n6 = n + 4 + n2 + this.e.getFontMetrics().getHeight() / 2;
        this.e.drawString(this.f[n5][0], (this.a.width - this.e.getFontMetrics().stringWidth(this.f[n5][0])) / 2, n6);
        for (int i = 1; i < this.f[n5].length; ++i) {
            if (this.f[n5][i] != null) {
                if (i == length) {
                    this.e.setColor(Color.WHITE);
                    this.e.fillRect(n, n6 + 4 + min * i, n4 + (n2 << 1) + 8, min);
                    this.e.setColor(Color.BLACK);
                    this.e.drawString(this.f[n5][i], (this.a.width - this.e.getFontMetrics().stringWidth(this.f[n5][i])) / 2, n6 + min + min * i);
                }
                else {
                    this.e.setColor(Color.WHITE);
                    this.e.drawString(this.f[n5][i], (this.a.width - this.e.getFontMetrics().stringWidth(this.f[n5][i])) / 2, n6 + min + min * i);
                }
            }
        }
        return this.b;
    }
    
    public int[][] getMenuPosition() {
        return this.g;
    }
    
    public void moveDown() {
        final int n = this.g.length - 1;
        int n2;
        if ((n2 = this.g[n].length + 1) == this.f[n].length) {
            n2 = 1;
        }
        if (this.f[n][n2] == null) {
            n2 = 1;
        }
        this.g = new int[this.g.length][n2];
        this.c.repaint();
    }
    
    public void moveUp() {
        final int n = this.g.length - 1;
        int n2;
        if ((n2 = this.g[n].length - 1) == 0) {
            n2 = this.f[n].length - 1;
        }
        while (this.f[n][n2] == null) {
            --n2;
        }
        this.g = new int[this.g.length][n2];
        this.c.repaint();
    }
    
    public void switchMenu(final int[][] g) {
        this.g = g;
        this.c.repaint();
    }
}
