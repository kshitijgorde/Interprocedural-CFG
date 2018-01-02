import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Event;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class rotateinfo extends Applet implements Runnable
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
    int vpos;
    int hpos;
    boolean link;
    int bgr;
    int bgg;
    int bgb;
    int titlebgr;
    int titlebgg;
    int titlebgb;
    int titler;
    int titleg;
    int titleb;
    int textbgr;
    int textbgg;
    int textbgb;
    int textr;
    int textg;
    int textb;
    int maxcount;
    int count;
    int rr;
    int gg;
    int bb;
    Color tc;
    Color tb;
    
    public rotateinfo() {
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
        this.bgr = 255;
        this.bgg = 255;
        this.bgb = 255;
        this.titlebgr = 128;
        this.titlebgg = 128;
        this.titlebgb = 128;
        this.titler = 0;
        this.titleg = 0;
        this.titleb = 0;
        this.textbgr = 255;
        this.textbgg = 255;
        this.textbgb = 255;
        this.textr = 0;
        this.textg = 0;
        this.textb = 255;
        this.maxcount = 20;
        this.count = 0;
        this.rr = 0;
        this.gg = 0;
        this.bb = 0;
    }
    
    public void init() {
        super.init();
        int i = 0;
        this.font = this.getParameter("font");
        this.fontsize = Integer.parseInt(this.getParameter("fontsize"));
        final int[] int1 = this.parseInt(this.getParameter("bgcolor"));
        this.bgr = int1[0];
        this.bgg = int1[1];
        this.bgb = int1[2];
        final int[] int2 = this.parseInt(this.getParameter("titlecolor"));
        this.titler = int2[0];
        this.titleg = int2[1];
        this.titleb = int2[2];
        final int[] int3 = this.parseInt(this.getParameter("titlebgcolor"));
        this.titlebgr = int3[0];
        this.titlebgg = int3[1];
        this.titlebgb = int3[2];
        final int[] int4 = this.parseInt(this.getParameter("textcolor"));
        this.textr = int4[0];
        this.textg = int4[1];
        this.textb = int4[2];
        final int[] int5 = this.parseInt(this.getParameter("textbgcolor"));
        this.textbgr = int5[0];
        this.textbgg = int5[1];
        this.textbgb = int5[2];
        this.pause = Integer.parseInt(this.getParameter("pause"));
        this.file = this.getParameter("file");
        DataInputStream dataInputStream = null;
        final String file = this.file;
        this.showStatus("Reading rotateinfo datafile " + this.file + "...");
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
            dataInputStream.readLine();
            for (i = 0; i < this.npages; ++i) {
                this.showStatus("Reading news item " + i + "...");
                final String[] parse = this.parse(dataInputStream.readLine(), "|");
                this.pg[i][0] = parse[0];
                this.pg[i][1] = parse[1];
                this.pg[i][2] = dataInputStream.readLine();
                boolean b = true;
                while (b) {
                    final String line = dataInputStream.readLine();
                    if (line.trim().length() != 0) {
                        final String[] array = this.pg[i];
                        final int n = 2;
                        array[n] = String.valueOf(array[n]) + " " + line;
                    }
                    else {
                        b = false;
                    }
                }
            }
            dataInputStream.close();
        }
        catch (EOFException ex2) {
            if (i != this.npages - 1) {
                this.showStatus("[Error]: Exception: " + ex2 + " File: " + file + " (i=" + i + ")");
            }
        }
        catch (IOException ex3) {
            this.showStatus("[Error]: Exception: " + ex3 + " File: " + file);
        }
        this.showStatus("'" + this.file + "'" + "  file reading done");
        this.width = this.size().width;
        this.height = this.size().height;
        this.resize(this.width, this.height);
        this.oi = this.createImage(this.width, this.height);
        this.og = this.oi.getGraphics();
        this.showStatus("rotateinfo (C) The J Maker 2000");
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            URL url;
            if (this.link) {
                url = new URL(this.getDocumentBase(), this.pg[this.id][1]);
            }
            else {
                url = new URL(this.getDocumentBase(), "http://www.thejmaker.com/");
            }
            this.showStatus("Linking to " + this.pg[this.id][1] + "...");
            this.getAppletContext().showDocument(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.link = false;
        this.showStatus("rotateinfo (C) The J Maker 2000");
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus(String.valueOf(this.pg[this.id][1]));
        this.link = true;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.og.setFont(new Font(this.font, 1, this.fontsize + 2));
        this.fh = this.og.getFontMetrics().getHeight();
        this.og.setColor(new Color(this.bgr, this.bgg, this.bgb));
        this.og.fillRect(0, 0, this.width, this.fh + 3);
        this.og.setColor(new Color(this.titlebgr, this.titlebgg, this.titlebgb));
        this.og.fillRoundRect(0, 0, this.width, this.fh + 10, 20, 20);
        if (this.link) {
            this.og.setColor(Color.red);
            this.og.drawRoundRect(0, 0, this.width - 1, this.fh + 10, 20, 20);
        }
        this.og.setColor(new Color(this.textbgr, this.textbgg, this.textbgb));
        this.og.fillRect(0, this.fh, this.width - 1, this.height - 1);
        if (this.link) {
            this.og.setColor(Color.red);
        }
        else {
            this.og.setColor(new Color(this.titlebgr, this.titlebgg, this.titlebgb));
        }
        this.og.drawRect(0, this.fh, this.width - 1, this.height - 1 - this.fh);
        this.fd = this.og.getFontMetrics().getDescent();
        this.fw = this.og.getFontMetrics().stringWidth(this.pg[this.id][0]);
        if (this.link) {
            this.og.setColor(Color.black);
        }
        else {
            this.rr = this.count * this.titlebgr / this.maxcount;
            this.gg = this.count * this.titlebgg / this.maxcount;
            this.bb = this.count * this.titlebgb / this.maxcount;
            this.og.setColor(new Color(this.rr, this.gg, this.bb));
        }
        this.og.drawString(this.pg[this.id][0], (this.width - this.fw) / 2 + 2, this.fh - this.fd);
        if (this.link) {
            this.og.setColor(Color.red);
        }
        else {
            this.rr = (this.maxcount - this.count) * this.titler / this.maxcount + this.count * this.titlebgr / this.maxcount;
            this.gg = (this.maxcount - this.count) * this.titleg / this.maxcount + this.count * this.titlebgg / this.maxcount;
            this.bb = (this.maxcount - this.count) * this.titleb / this.maxcount + this.count * this.titlebgb / this.maxcount;
            this.og.setColor(new Color(this.rr, this.gg, this.bb));
        }
        this.og.drawString(this.pg[this.id][0], (this.width - this.fw) / 2, this.fh - this.fd - 2);
        if (!this.link) {
            this.rr = this.count * this.textbgr / this.maxcount;
            this.gg = this.count * this.textbgg / this.maxcount;
            this.bb = this.count * this.textbgb / this.maxcount;
            this.tb = new Color(this.rr, this.gg, this.bb);
            this.rr = (this.maxcount - this.count) * this.textr / this.maxcount + this.count * this.textbgr / this.maxcount;
            this.gg = (this.maxcount - this.count) * this.textg / this.maxcount + this.count * this.textbgg / this.maxcount;
            this.bb = (this.maxcount - this.count) * this.textb / this.maxcount + this.count * this.textbgb / this.maxcount;
            this.tc = new Color(this.rr, this.gg, this.bb);
        }
        else {
            this.tb = Color.black;
            this.tc = Color.red;
        }
        this.vpos = this.fh + 5;
        this.og.setFont(new Font(this.font, 0, this.fontsize));
        this.fh = this.og.getFontMetrics().getHeight();
        this.vpos += this.fh;
        this.hpos = 0;
        final String[] parse = this.parse(this.pg[this.id][2], " ");
        for (int i = 0; i < parse.length; ++i) {
            this.fw = this.og.getFontMetrics().stringWidth(parse[i]);
            if (this.hpos + 4 + this.fw < this.width) {
                this.hpos += 2;
            }
            else {
                this.hpos = 2;
                this.vpos += this.fh;
            }
            this.og.setColor(this.tb);
            this.og.drawString(parse[i], this.hpos + 1, this.vpos + 1);
            this.og.setColor(this.tc);
            this.og.drawString(parse[i], this.hpos, this.vpos);
            this.hpos += this.fw;
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
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
            this.repaint();
            try {
                if (this.count == 0) {
                    Thread.sleep(this.pause);
                }
                else {
                    Thread.sleep(50L);
                }
            }
            catch (InterruptedException ex) {}
            ++this.count;
            if (this.count > this.maxcount) {
                this.count = 0;
                if (this.link) {
                    continue;
                }
                this.id = (this.id + 1 + this.npages) % this.npages;
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
