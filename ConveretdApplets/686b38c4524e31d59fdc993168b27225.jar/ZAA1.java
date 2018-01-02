import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Font;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZAA1 extends Applet implements Runnable
{
    Thread runner;
    int waiting;
    String[] hLine;
    int curI;
    int ofsI;
    int movI;
    boolean bStop;
    Graphics dBuf;
    Image offScr;
    FontMetrics vMetrics;
    Color[] cColor;
    String sTXT;
    int iCol;
    
    public ZAA1() {
        this.runner = null;
        this.waiting = 300000;
        this.bStop = false;
        this.iCol = 38;
    }
    
    public void aLine(final Graphics graphics, final int n, int n2) {
        if (n % 2 == 1) {
            graphics.setColor(this.cColor[1]);
            graphics.fillRect(0, n2 - 14, this.size().width, 15);
        }
        n2 -= 2;
        final String[] parse = this.parse(this.hLine[n], " ");
        graphics.setColor(this.cColor[3]);
        graphics.drawString(parse[0], 0, n2);
        graphics.setColor(this.cColor[4]);
        graphics.drawString(parse[1], this.size().width * 21 / this.iCol - this.vMetrics.stringWidth(parse[1]), n2);
        if (parse[2].charAt(0) == '+') {
            graphics.setColor(this.cColor[6]);
        }
        else if (parse[2].charAt(0) == '-') {
            graphics.setColor(this.cColor[7]);
        }
        else {
            graphics.setColor(this.cColor[5]);
        }
        graphics.drawString(parse[2], this.size().width * 30 / this.iCol - this.vMetrics.stringWidth(parse[2]), n2);
        if (this.iCol == 38) {
            graphics.drawString(parse[3], this.size().width - this.vMetrics.stringWidth(parse[3]), n2);
        }
    }
    
    public void bpaint(final Graphics graphics, final int n) {
        if (n == 1) {
            graphics.copyArea(0, 15 * this.movI + 4, this.size().width, this.size().height + 30 - 15 * this.movI, 0, -1);
            return;
        }
        graphics.setColor(this.cColor[0]);
        graphics.fillRect(0, 0, this.size().width, this.size().height + 30);
        int i = 15;
        for (int j = 0; j < this.movI; ++j, i += 15) {
            this.aLine(graphics, j, i);
        }
        i += 3;
        if (this.movI > 0) {
            graphics.setColor(this.cColor[2]);
            graphics.drawLine(0, i - 16, this.size().width, i - 16);
        }
        int curI = this.curI;
        while (i <= this.size().height / 15 * 15 + 33) {
            this.aLine(graphics, curI, i);
            if (++curI == this.hLine.length) {
                curI = this.movI + 1;
            }
            i += 15;
        }
        if (++this.curI == this.hLine.length) {
            this.curI = this.movI + 1;
        }
    }
    
    public void checkNews() {
        this.waiting += 100;
        if (this.waiting >= 300000) {
            this.waiting = 0;
            this.hLine = this.parse(this.getURLfile(this.sTXT), "\r\n");
            this.movI = -1;
            for (int i = 0; i < this.hLine.length; ++i) {
                if (this.hLine[i].charAt(0) == '-') {
                    this.movI = i;
                }
            }
            this.curI = this.movI + 1;
            this.ofsI = 0;
            this.bpaint(this.dBuf, 0);
            this.repaint();
        }
    }
    
    String getURLfile(final String s) {
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), s).openConnection();
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            final byte[] array = new byte[3048];
            int n = 0;
            int read;
            while ((read = inputStream.read()) != -1) {
                array[n] = (byte)read;
                ++n;
            }
            return new String(array, 0, n);
        }
        catch (Exception ex) {
            return "\u8cc7\u6599ERR N/A N/A N/A N/A\r\n\u8cc7\u6599ERR N/A N/A N/A N/A";
        }
    }
    
    public void init() {
        this.offScr = this.createImage(this.size().width, this.size().height + 30);
        this.dBuf = this.offScr.getGraphics();
        final Font font = new Font("\u7d30\u660e\u9ad4", 0, 12);
        this.vMetrics = this.getFontMetrics(font);
        this.dBuf.setFont(font);
        if (this.getParameter("COLOR") != null) {
            this.cColor = this.parseC(this.getParameter("COLOR"), ",");
        }
        else {
            this.cColor = this.parseC("ffffff,e0eff0,cc,0,0,666666,990000,6600", ",");
        }
        if (this.getParameter("TXT") != null) {
            this.sTXT = this.getParameter("TXT");
        }
        else {
            this.sTXT = "/z/za/zaa/CZAA1.txt";
        }
        if (this.getParameter("SHORT") != null) {
            this.iCol = 30;
        }
        this.checkNews();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.bStop = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.bStop = false;
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offScr, 0, 0, this);
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    Color[] parseC(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Color[] array = new Color[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        }
        return array;
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            this.checkNews();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            if (!this.bStop) {
                if (this.ofsI == 15) {
                    this.bpaint(this.dBuf, 0);
                    this.ofsI = 0;
                }
                else {
                    this.bpaint(this.dBuf, 1);
                }
                this.repaint();
                ++this.ofsI;
            }
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
