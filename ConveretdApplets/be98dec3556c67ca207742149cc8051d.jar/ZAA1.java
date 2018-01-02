import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Event;
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
    boolean bNoScroll;
    int nLineDist;
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.bStop = true;
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.bStop = false;
        return true;
    }
    
    Color[] parseC(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Color[] array = new Color[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        }
        return array;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offScr, 0, 0, this);
    }
    
    public ZAA1() {
        this.runner = null;
        this.waiting = 300000;
        this.bStop = false;
        this.iCol = 38;
        this.bNoScroll = false;
        this.nLineDist = 15;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void bpaint(final Graphics graphics, final int n) {
        if (n == 1) {
            graphics.copyArea(0, this.nLineDist * this.movI + 4, this.size().width, this.size().height + 2 * this.nLineDist - this.nLineDist * this.movI, 0, -1);
            return;
        }
        graphics.setColor(this.cColor[0]);
        graphics.fillRect(0, 0, this.size().width, this.size().height + this.nLineDist * 2);
        int i = this.nLineDist;
        for (int j = 0; j < this.movI; ++j, i += this.nLineDist) {
            this.aLine(graphics, j, i);
        }
        i += 3;
        if (this.movI > 0) {
            graphics.setColor(this.cColor[2]);
            graphics.drawLine(0, i - this.nLineDist - 1, this.size().width, i - this.nLineDist - 1);
        }
        int n2;
        if (this.bNoScroll) {
            n2 = this.size().height / this.nLineDist * this.nLineDist + 3;
        }
        else {
            n2 = this.size().height / this.nLineDist * this.nLineDist + this.nLineDist + 3;
        }
        int curI = this.curI;
        while (i <= n2) {
            this.aLine(graphics, curI, i);
            if (++curI == this.hLine.length) {
                if (this.bNoScroll) {
                    break;
                }
                curI = this.movI + 1;
            }
            i += this.nLineDist;
        }
        if (++this.curI == this.hLine.length) {
            this.curI = this.movI + 1;
        }
    }
    
    public void aLine(final Graphics graphics, final int n, int n2) {
        if (n % 2 == 1) {
            graphics.setColor(this.cColor[1]);
            graphics.fillRect(0, n2 - this.nLineDist + 1, this.size().width, this.nLineDist);
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
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            this.checkNews();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            if (!this.bNoScroll && !this.bStop) {
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
    
    public void init() {
        this.offScr = this.createImage(this.size().width, this.size().height + 30);
        this.dBuf = this.offScr.getGraphics();
        int int1 = 12;
        if (this.getParameter("FontSize") != null) {
            final String parameter = this.getParameter("FontSize");
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        final Font font = new Font("\u7d30\u660e\u9ad4", 0, int1);
        this.vMetrics = this.getFontMetrics(font);
        this.nLineDist = this.vMetrics.getHeight();
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
        if (this.getParameter("NOSCROLL") != null) {
            this.bNoScroll = true;
        }
        this.checkNews();
    }
    
    String getURLfile(final String s) {
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), s).openConnection();
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            final int contentLength = openConnection.getContentLength();
            final byte[] array = new byte[contentLength];
            int n = 0;
            int read;
            for (byte[] array2 = new byte[1024]; -1 != (read = inputStream.read(array2)) && n + read <= contentLength; n += read) {
                System.arraycopy(array2, 0, array, n, read);
            }
            inputStream.close();
            if (n != contentLength) {
                return "";
            }
            return new String(array, "Big5");
        }
        catch (Exception ex) {
            return "\u8cc7\u6599ERR N/A N/A N/A N/A\r\n\u8cc7\u6599ERR N/A N/A N/A N/A";
        }
    }
}
