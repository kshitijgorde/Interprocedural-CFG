import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Event;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class newsflash extends Applet implements Runnable
{
    Thread t;
    boolean suspended;
    Image oi;
    Graphics og;
    int width;
    int height;
    int npages;
    int id;
    String[][] pg;
    String font;
    int fontsize;
    int fw;
    int fh;
    int fd;
    int pause;
    String file;
    int vpos1;
    int vpos2;
    boolean link;
    int catbgr;
    int catbgg;
    int catbgb;
    int catr;
    int catg;
    int catb;
    int textbgr;
    int textbgg;
    int textbgb;
    int textr;
    int textg;
    int textb;
    int border;
    Color c1;
    Color c2;
    
    public newsflash() {
        this.t = null;
        this.suspended = false;
        this.width = 0;
        this.height = 0;
        this.id = 0;
        this.font = "Helvetica";
        this.fontsize = 12;
        this.fw = 0;
        this.fh = 0;
        this.fd = 0;
        this.pause = 500;
        this.link = false;
        this.catbgr = 128;
        this.catbgg = 128;
        this.catbgb = 128;
        this.catr = 128;
        this.catg = 128;
        this.catb = 128;
        this.textbgr = 255;
        this.textbgg = 255;
        this.textbgb = 255;
        this.textr = 0;
        this.textg = 0;
        this.textb = 255;
        this.border = 0;
        this.c1 = new Color(220, 220, 220);
        this.c2 = new Color(50, 50, 50);
    }
    
    public void init() {
        this.border = Integer.parseInt(this.getParameter("border"));
        this.font = this.getParameter("font");
        this.fontsize = Integer.parseInt(this.getParameter("fontsize"));
        final int[] int1 = this.parseInt(this.getParameter("textcolor"));
        this.textr = int1[0];
        this.textg = int1[1];
        this.textb = int1[2];
        final int[] int2 = this.parseInt(this.getParameter("textbgcolor"));
        this.textbgr = int2[0];
        this.textbgg = int2[1];
        this.textbgb = int2[2];
        final int[] int3 = this.parseInt(this.getParameter("titlecolor"));
        this.catr = int3[0];
        this.catg = int3[1];
        this.catb = int3[2];
        final int[] int4 = this.parseInt(this.getParameter("titlebgcolor"));
        this.catbgr = int4[0];
        this.catbgg = int4[1];
        this.catbgb = int4[2];
        this.pause = Integer.parseInt(this.getParameter("pause"));
        this.file = this.getParameter("file");
        DataInputStream dataInputStream = null;
        final String file = this.file;
        this.showStatus("Reading newsflash datafile " + this.file + "...");
        try {
            dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), file).openStream());
        }
        catch (Exception ex) {
            this.showStatus("[Error]: Exception: " + ex + " File = " + file);
            this.stop();
        }
        try {
            this.npages = this.parseInt(dataInputStream.readLine())[0];
            this.pg = new String[this.npages][3];
            for (int i = 0; i < this.npages; ++i) {
                final String[] parse = this.parse(dataInputStream.readLine());
                this.pg[i][0] = parse[0];
                this.pg[i][1] = parse[1];
                this.pg[i][2] = parse[2];
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {
            this.showStatus("[Error]: Exception: " + ex2 + " File: " + file);
        }
        this.showStatus("'" + this.file + "'" + "  file reading done");
        this.width = this.size().width;
        this.height = this.size().height;
        this.resize(this.width, this.height);
        this.oi = this.createImage(this.width, this.height);
        this.og = this.oi.getGraphics();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.showStatus(String.valueOf(this.pg[this.id][2]));
        try {
            if (!this.pg[this.id][2].toUpperCase().startsWith("NONE")) {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.pg[this.id][2]));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.link = false;
        this.showStatus("newsflash by The J Maker (C) 2000");
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus(String.valueOf(this.pg[this.id][2]));
        this.link = true;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.og.setColor(this.getBackground());
        this.og.fillRect(0, 0, this.width, this.height);
        this.og.setFont(new Font(this.font, 1, this.fontsize));
        this.fh = this.og.getFontMetrics().getHeight();
        this.og.setColor(new Color(this.catbgr, this.catbgg, this.catbgb));
        this.fd = this.og.getFontMetrics().getDescent();
        this.fw = this.og.getFontMetrics().stringWidth(this.pg[this.id][0]);
        this.og.fillRect(0, 0, this.fw + 10 + this.border, this.height);
        this.og.setColor(this.c1);
        this.og.drawString(this.pg[this.id][0], this.border + 5 - 1, (this.height + this.fh) / 2 - this.fd);
        this.og.drawString(this.pg[this.id][0], this.border + 5, (this.height + this.fh) / 2 - this.fd - 1);
        this.og.drawString(this.pg[this.id][0], this.border + 5 - 1, (this.height + this.fh) / 2 - this.fd - 1);
        this.og.setColor(this.c2);
        this.og.drawString(this.pg[this.id][0], this.border + 5 + 1, (this.height + this.fh) / 2 - this.fd);
        this.og.drawString(this.pg[this.id][0], this.border + 5, (this.height + this.fh) / 2 - this.fd + 1);
        this.og.drawString(this.pg[this.id][0], this.border + 5 + 1, (this.height + this.fh) / 2 - this.fd + 1);
        if (this.link) {
            this.og.setColor(Color.red);
        }
        else {
            this.og.setColor(new Color(this.catr, this.catg, this.catb));
        }
        this.og.drawString(this.pg[this.id][0], this.border + 5, (this.height + this.fh) / 2 - this.fd);
        this.og.setFont(new Font(this.font, 0, this.fontsize));
        this.fh = this.og.getFontMetrics().getHeight();
        this.og.setColor(new Color(this.textbgr, this.textbgg, this.textbgb));
        this.og.fillRect(this.fw + 10 + this.border, 0, this.width - this.fw - 10 - this.border, this.height);
        this.fd = this.og.getFontMetrics().getDescent();
        if (this.link) {
            this.og.setColor(Color.red);
        }
        else {
            this.og.setColor(new Color(this.textr, this.textg, this.textb));
        }
        this.og.drawString(this.pg[this.id][1], this.fw + 15 + this.border, (this.height + this.fh) / 2 - this.fd);
        if (this.border != 0) {
            this.og.setColor(new Color(128, 128, 128));
            for (int i = 0; i < this.border; ++i) {
                if (i < (this.border + 1) / 2) {
                    this.og.draw3DRect(i, i, this.width - 1 - i * 2, this.height - 1 - i * 2, true);
                }
                else {
                    this.og.draw3DRect(i, i, this.width - 1 - i * 2, this.height - 1 - i * 2, false);
                }
            }
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    String[] parse(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public void run() {
        Thread.currentThread().setPriority(4);
        while (true) {
            if (!this.link) {
                this.repaint();
                try {
                    Thread.sleep(this.pause);
                }
                catch (InterruptedException ex) {}
                ++this.id;
                if (this.id < this.npages) {
                    continue;
                }
                this.id = 0;
            }
        }
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null && this.t.isAlive()) {
            this.t.stop();
        }
        this.t = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
