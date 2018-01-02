import java.awt.FontMetrics;
import java.awt.Font;
import java.net.URL;
import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZZMENU extends Applet implements Runnable
{
    Thread runner;
    String[] mainM;
    String[] mainL;
    int[] mainX;
    int[] mainS;
    String[][] subM;
    String[][] subL;
    int[][] subX;
    int curM;
    int curO;
    int curS;
    Graphics dBuf;
    Image offScr;
    int cBase;
    public String P1;
    public String P2;
    public String P3;
    public int Mid;
    String ppp;
    public int Uab;
    Color[] cColor;
    int fontsz;
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    String[] parse(final String s, final String sep) {
        final StringTokenizer st = new StringTokenizer(s, sep);
        final String[] result = new String[st.countTokens()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = st.nextToken();
        }
        return result;
    }
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        final int i = -1;
        final int j = -1;
        if (i != this.curM || j != this.curS) {
            this.curM = i;
            this.curS = j;
            this.bpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
    
    Color[] parseC(final String s, final String sep) {
        final StringTokenizer st = new StringTokenizer(s, sep);
        final Color[] result = new Color[st.countTokens()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = new Color(Integer.parseInt(st.nextToken(), 16));
        }
        return result;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offScr, 0, 0, this);
    }
    
    String repLnk(final String nLink, final String ss, final String pp) {
        final int len = ss.length();
        if (len < 1) {
            return nLink;
        }
        final int j = nLink.indexOf(ss);
        String sss;
        if (j > 0) {
            sss = nLink.substring(0, j) + pp + nLink.substring(j + len);
        }
        else {
            sss = nLink;
        }
        return sss;
    }
    
    public ZZMENU() {
        this.runner = null;
        this.curM = -1;
        this.curO = -1;
        this.curS = -1;
        this.Mid = 0;
        this.Uab = 0;
        this.fontsz = 15;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private void notifyLoaded() {
        final String cmdLoad = this.getParameter("LOAD");
        if (cmdLoad != null) {
            try {
                final JSObject window = JSObject.getWindow((Applet)this);
                window.eval(cmdLoad);
            }
            catch (Exception ex) {}
        }
    }
    
    public void bpaint(final Graphics dBuf) {
        dBuf.setColor(this.cColor[0]);
        dBuf.fillRect(0, 0, this.size().width, this.size().height);
        int yy = 0;
        for (int i = 0; i < this.mainM.length; ++i) {
            if (this.mainS[i] == 1) {
                dBuf.setColor(this.cColor[1]);
                dBuf.drawLine(0, yy + this.fontsz * 2 / 3, this.fontsz / 3 + 10, yy + this.fontsz * 2 / 3);
                dBuf.drawLine(this.mainX[i], yy + this.fontsz * 2 / 3, 320, yy + this.fontsz * 2 / 3);
            }
            else {
                if (i == this.curM) {
                    dBuf.setColor(this.cColor[2]);
                }
                else {
                    dBuf.setColor(this.cColor[3]);
                }
                if (this.mainL[i] == null) {
                    dBuf.drawRect(this.fontsz / 3, yy + this.fontsz / 3 + 1, 8, 8);
                    dBuf.drawLine(this.fontsz / 3 + 2, yy + this.fontsz / 3 + 5, this.fontsz / 3 + 6, yy + this.fontsz / 3 + 5);
                    if (i != this.curO) {
                        dBuf.drawLine(this.fontsz / 3 + 4, yy + this.fontsz / 3 + 3, this.fontsz / 3 + 4, yy + this.fontsz / 3 + 7);
                    }
                }
            }
            dBuf.drawString(this.mainM[i], this.fontsz / 3 + 12, yy + this.cBase);
            yy += this.fontsz * 4 / 3;
            if (i == this.curO) {
                for (int j = 0; j < this.subM[i].length; ++j) {
                    if (j == this.curS) {
                        dBuf.setColor(this.cColor[2]);
                    }
                    else {
                        dBuf.setColor(this.cColor[3]);
                    }
                    dBuf.drawLine(this.fontsz / 3 + 11 + this.fontsz / 2, yy + this.fontsz * 2 / 3, this.fontsz / 3 + 10 + this.fontsz, yy + this.fontsz * 2 / 3);
                    dBuf.drawLine(this.fontsz / 3 + 11 + this.fontsz / 2, yy - ((j == 0) ? 0 : (this.fontsz * 2 / 3)), this.fontsz / 3 + 11 + this.fontsz / 2, yy + this.fontsz * 2 / 3);
                    dBuf.drawString(this.subM[i][j], this.fontsz / 3 + 12 + this.fontsz, yy + this.cBase);
                    yy += this.fontsz * 4 / 3;
                }
            }
        }
    }
    
    public void setMenu(final String pin) {
        this.ppp = pin;
        this.inVal();
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (this.Uab == 1) {
            return true;
        }
        String nLink = null;
        final int i = y / (this.fontsz * 4 / 3);
        if (this.curO == -1) {
            if (i < this.mainM.length && x < this.mainX[i]) {
                if (this.mainS[i] == 1) {
                    return true;
                }
                if (this.mainL[i] != null) {
                    nLink = this.mainL[i];
                }
                else {
                    final int n = i;
                    this.curO = n;
                    this.curM = n;
                    this.curS = -1;
                    this.bpaint(this.dBuf);
                    this.repaint();
                }
            }
        }
        else if (i < this.curO) {
            if (x < this.mainX[i]) {
                if (this.mainS[i] == 1) {
                    return true;
                }
                if (this.mainL[i] != null) {
                    nLink = this.mainL[i];
                }
                else {
                    final int n2 = i;
                    this.curO = n2;
                    this.curM = n2;
                    this.curS = -1;
                    this.bpaint(this.dBuf);
                    this.repaint();
                }
            }
        }
        else if (i == this.curO) {
            if (x < this.mainX[i]) {
                this.curM = i;
                this.curO = -1;
                this.curS = -1;
                this.bpaint(this.dBuf);
                this.repaint();
            }
        }
        else if (i <= this.curO + this.subM[this.curO].length) {
            if (x < this.subX[this.curO][i - this.curO - 1]) {
                nLink = this.subL[this.curO][i - this.curO - 1];
            }
        }
        else if (i < this.mainM.length + this.subM[this.curO].length && x < this.mainX[i - this.subM[this.curO].length]) {
            if (this.mainS[i - this.subM[this.curO].length] == 1) {
                return true;
            }
            if (this.mainL[i - this.subM[this.curO].length] != null) {
                nLink = this.mainL[i - this.subM[this.curO].length];
            }
            else {
                final int n3 = i - this.subM[this.curO].length;
                this.curO = n3;
                this.curM = n3;
                this.curS = -1;
                this.bpaint(this.dBuf);
                this.repaint();
            }
        }
        if (nLink != null) {
            nLink = this.repLnk(nLink, "?1?", this.P1);
            nLink = this.repLnk(nLink, "?2?", this.P2);
            nLink = this.repLnk(nLink, "?3?", this.P3);
            try {
                String sUrl = nLink;
                String sTarget = "down";
                final int idx = sUrl.indexOf(40);
                final int idx2 = sUrl.lastIndexOf(41);
                if (idx >= 0 && idx2 >= 0) {
                    sTarget = sUrl.substring(idx + 1, idx2);
                    sUrl = sUrl.substring(0, idx);
                }
                final URL url = new URL(this.getDocumentBase(), sUrl);
                this.getAppletContext().showDocument(url, sTarget);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    public void run() {
        final Thread me = Thread.currentThread();
        while (this.runner == me) {
            if (this.curM == -1) {
                this.repaint();
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.offScr = this.createImage(this.size().width, this.size().height);
        this.dBuf = this.offScr.getGraphics();
        this.ppp = this.getParameter("MENU");
        this.P1 = this.getParameter("P1");
        this.P2 = this.getParameter("P2");
        this.P3 = this.getParameter("P3");
        if (this.getParameter("DISABLE") != null) {
            this.Uab = 1;
        }
        if (this.getParameter("COLOR") != null) {
            this.cColor = this.parseC(this.getParameter("COLOR"), ",");
        }
        else {
            this.cColor = this.parseC("e2ebec,0,c3007d,6288", ",");
        }
        if (this.getParameter("FONTSIZE") != null) {
            this.fontsz = Integer.parseInt(this.getParameter("FONTSIZE"));
        }
        this.inVal();
        this.notifyLoaded();
    }
    
    public void inVal() {
        this.curM = -1;
        this.curO = -1;
        this.curS = -1;
        final Font vFont = new Font("\u7d30\u660e\u9ad4", 0, this.fontsz);
        final FontMetrics vMetrics = this.getFontMetrics(vFont);
        this.cBase = vMetrics.getAscent() + this.fontsz / 5;
        this.dBuf.setFont(vFont);
        final String[] para = this.parse(this.ppp, " ");
        this.mainM = new String[para.length];
        this.mainL = new String[para.length];
        this.mainX = new int[para.length];
        this.mainS = new int[para.length];
        this.subM = new String[para.length][];
        this.subL = new String[para.length][];
        this.subX = new int[para.length][];
        for (int i = 0; i < para.length; ++i) {
            final String[] para2 = this.parse(para[i], ",");
            this.mainM[i] = para2[0];
            this.mainX[i] = vMetrics.stringWidth(this.mainM[i]) + 17;
            this.mainS[i] = 0;
            if (para2.length == 1) {
                this.mainS[i] = 1;
            }
            else if (para2.length == 2) {
                this.mainL[i] = para2[1];
            }
            else {
                final int j = (para2.length - 1) / 2;
                this.subM[i] = new String[j];
                this.subL[i] = new String[j];
                this.subX[i] = new int[j];
                for (int k = 0; k < j; ++k) {
                    this.subM[i][k] = para2[k * 2 + 1];
                    this.subL[i][k] = para2[k * 2 + 2];
                    this.subX[i][k] = vMetrics.stringWidth(this.subM[i][k]) + 32;
                }
            }
        }
        this.bpaint(this.dBuf);
        this.repaint();
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        int i = y / (this.fontsz * 4 / 3);
        int j = -1;
        if (this.curO == -1) {
            if (i >= this.mainM.length || x >= this.mainX[i]) {
                i = -1;
            }
        }
        else if (i <= this.curO && x < this.mainX[i]) {
            if (this.mainS[i] == 1) {
                i = -1;
            }
        }
        else if (i > this.curO && i <= this.curO + this.subM[this.curO].length && x < this.subX[this.curO][i - this.curO - 1]) {
            j = i - this.curO - 1;
            i = this.curO;
        }
        else if (i > this.curO + this.subM[this.curO].length && i < this.mainM.length + this.subM[this.curO].length && x < this.mainX[i - this.subM[this.curO].length]) {
            if (this.mainS[i - this.subM[this.curO].length] == 1) {
                i = -1;
            }
            else {
                i -= this.subM[this.curO].length;
            }
        }
        else {
            i = -1;
        }
        if (i != this.curM || j != this.curS) {
            this.curM = i;
            this.curS = j;
            this.bpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
}
