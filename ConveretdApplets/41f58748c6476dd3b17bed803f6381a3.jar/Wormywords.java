import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Wormywords extends Applet implements Runnable
{
    Thread th;
    private Image dbImage;
    private Graphics dbg;
    int h;
    int w;
    Color background;
    Color text;
    Font font1;
    String word;
    int length;
    String[] chars;
    int[] chary;
    int[] charx;
    int[] ncharx;
    int diff;
    int finish;
    String[] evilchars;
    int draw;
    int textx;
    int status;
    boolean divide;
    int middle;
    boolean isStoped;
    
    public Wormywords() {
        this.finish = 0;
        this.draw = 0;
        this.status = 0;
        this.isStoped = false;
    }
    
    public void init() {
        this.h = this.getSize().height;
        this.w = this.getSize().width;
        this.textx = this.w;
        this.isStoped = false;
        this.font1 = new Font("Arial", 0, 20);
        this.word = this.getParameter("word");
        this.length = this.word.length();
        this.chars = new String[this.length];
        this.chary = new int[this.length];
        this.charx = new int[this.length];
        this.ncharx = new int[this.length];
        if (this.length % 2 == 0) {
            this.divide = false;
            this.middle = this.length / 2;
        }
        else {
            this.divide = true;
            this.middle = (this.length + 1) / 2;
        }
        for (int i = 0; i < this.length; ++i) {
            this.chars[i] = this.word.substring(i, i + 1);
        }
        final char[] array = { '\u0006', '\u0002', '\u0002', '\u0002', '\u000e', '\u0005', '\u0006', '\u000e' };
        for (int j = 0; j < this.length; ++j) {
            this.chary[j] = this.h - 5;
        }
        for (int k = 0; k < this.length; ++k) {
            this.ncharx[k] = (this.charx[k] = this.textx + this.draw);
            this.draw += 11;
            if (this.chars[k].equals("f") || this.chars[k].equals("r") || this.chars[k].equals("t")) {
                this.draw -= 3;
            }
            if (this.chars[k].equals("m") || this.chars[k].equals("w")) {
                this.draw += 5;
            }
            if (this.chars[k].equals("i") || this.chars[k].equals("j") || this.chars[k].equals("l")) {
                this.draw -= 7;
            }
        }
        final String parameter = this.getParameter("bg_color");
        this.background = new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4), 16));
        final String parameter2 = this.getParameter("fg_color");
        this.text = new Color(Integer.parseInt(parameter2.substring(0, 2), 16), Integer.parseInt(parameter2.substring(2, 4), 16), Integer.parseInt(parameter2.substring(4), 16));
        this.setBackground(this.background);
    }
    
    public void start() {
        if (this.th == null) {
            (this.th = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.th == Thread.currentThread()) {
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
    
    public void stop() {
        this.isStoped = true;
        if (this.th != null) {
            this.th = null;
        }
    }
    
    public void destroy() {
        this.isStoped = true;
    }
    
    public void updateWorm() {
        if (this.status == 0) {
            if (this.chary[this.middle] > this.h - 5 - 35) {
                final int[] chary = this.chary;
                final int middle = this.middle;
                chary[middle] -= 5;
            }
            if (this.chary[this.middle + 1] > this.h - 5 - 25) {
                final int[] chary2 = this.chary;
                final int n = this.middle + 1;
                final int[] chary3 = this.chary;
                final int n2 = this.middle - 1;
                chary2[n] = (chary3[n2] -= 4);
            }
            if (this.chary[this.middle + 2] > this.h - 5 - 10) {
                final int[] chary4 = this.chary;
                final int n3 = this.middle + 2;
                final int[] chary5 = this.chary;
                final int n4 = this.middle - 2;
                chary4[n3] = (chary5[n4] -= 4);
            }
            if (this.chary[this.middle] < this.h - 5 - 34) {
                this.status = 1;
            }
        }
        if (this.status == 1) {
            for (int i = this.middle - 2; i < this.middle + 3; ++i) {
                final int[] charx = this.charx;
                final int n5 = i;
                charx[n5] -= (i - (this.middle - 2)) * 5;
            }
            for (int j = this.middle + 3; j < this.length; ++j) {
                final int[] charx2 = this.charx;
                final int n6 = j;
                charx2[n6] -= 30;
            }
            this.status = 2;
        }
        if (this.status == 2) {
            for (int k = 0; k < this.middle - 4; ++k) {
                if (this.charx[k] > this.charx[k + 1] - 20) {
                    final int[] charx3 = this.charx;
                    final int n7 = k;
                    charx3[n7] -= 2;
                }
            }
            if (this.charx[0] < this.charx[1] - 18) {
                this.status = 3;
                this.diff = this.ncharx[0] - this.charx[0];
                for (int l = 0; l < this.length; ++l) {
                    final int[] ncharx = this.ncharx;
                    final int n8 = l;
                    ncharx[n8] -= this.diff;
                }
            }
        }
        if (this.status == 3) {
            for (int n9 = this.middle - 2; n9 < this.middle + 3; ++n9) {
                if (this.chary[n9] != this.chary[0]) {
                    final int[] chary6 = this.chary;
                    final int n10 = n9;
                    ++chary6[n10];
                }
            }
            for (int n11 = 0; n11 < this.length; ++n11) {
                if (this.charx[n11] < this.ncharx[n11]) {
                    final int[] charx4 = this.charx;
                    final int n12 = n11;
                    ++charx4[n12];
                }
                else if (this.charx[n11] > this.ncharx[n11]) {
                    final int[] charx5 = this.charx;
                    final int n13 = n11;
                    charx5[n13] -= 2;
                }
            }
            for (int n14 = 0; n14 < this.length; ++n14) {
                if (this.charx[n14] == this.ncharx[n14]) {
                    ++this.finish;
                }
            }
            if (this.finish == this.length && this.chary[this.middle] == this.chary[0]) {
                this.status = 0;
            }
            else {
                this.finish = 0;
            }
        }
        if (this.charx[this.length - 1] + 10 < 0) {
            this.init();
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.w, this.h);
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.w, this.h);
        this.dbg.setColor(this.getForeground());
        this.paint(this.dbg);
        graphics.drawImage(this.dbImage, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isStoped) {
            graphics.setColor(this.text);
            graphics.drawString("Error", 0, 0);
        }
        else {
            graphics.setColor(this.text);
            graphics.setFont(this.font1);
            for (int i = 0; i < this.length; ++i) {
                graphics.drawString(this.chars[i], this.charx[i], this.chary[i]);
            }
            this.draw = 0;
            this.updateWorm();
        }
    }
}
