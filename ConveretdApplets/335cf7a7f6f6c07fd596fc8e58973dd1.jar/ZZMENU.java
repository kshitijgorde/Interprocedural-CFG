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
    public String P8;
    public int Mid;
    public String P9;
    public String BID;
    String ppp;
    public int Uab;
    public String thisM;
    public String nextM;
    Color[] cColor;
    int fontsz;
    int m_nMaxID;
    String[] m_sID;
    
    public boolean setId(final int n, final String s) {
        if (n > 0 && n <= this.m_sID.length) {
            this.m_sID[n - 1] = s.trim().toUpperCase();
            this.inVal();
            return true;
        }
        return false;
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
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
        final int curM = -1;
        final int curS = -1;
        if (curM != this.curM || curS != this.curS) {
            this.curM = curM;
            this.curS = curS;
            this.bpaint(this.dBuf);
            this.repaint();
        }
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
    
    String repLnk(final String s, final String s2, final String s3) {
        final int length = s2.length();
        if (length < 1) {
            return s;
        }
        final int index = s.indexOf(s2);
        if (index > 0) {
            return s.substring(0, index) + s3 + s.substring(index + length);
        }
        return s;
    }
    
    public ZZMENU() {
        this.runner = null;
        this.curM = -1;
        this.curO = -1;
        this.curS = -1;
        this.Mid = 0;
        this.BID = "null";
        this.Uab = 0;
        this.thisM = "";
        this.nextM = "";
        this.fontsz = 15;
        this.m_nMaxID = 20;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void notifyLoaded() {
        final String parameter = this.getParameter("LOAD");
        if (parameter != null) {
            try {
                JSObject.getWindow((Applet)this).eval(parameter);
            }
            catch (Exception ex) {}
        }
    }
    
    public void bpaint(final Graphics graphics) {
        graphics.setColor(this.cColor[0]);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        int n = 0;
        for (int i = 0; i < this.mainM.length; ++i) {
            if (this.mainS[i] == 1) {
                graphics.setColor(this.cColor[1]);
                graphics.drawLine(0, n + this.fontsz * 2 / 3, this.fontsz / 3 + 10, n + this.fontsz * 2 / 3);
                graphics.drawLine(this.mainX[i], n + this.fontsz * 2 / 3, 320, n + this.fontsz * 2 / 3);
            }
            else {
                if (i == this.curM) {
                    graphics.setColor(this.cColor[2]);
                }
                else {
                    graphics.setColor(this.cColor[3]);
                }
                if (this.mainL[i] == null) {
                    graphics.drawRect(this.fontsz / 3, n + this.fontsz / 3 + 1, 8, 8);
                    graphics.drawLine(this.fontsz / 3 + 2, n + this.fontsz / 3 + 5, this.fontsz / 3 + 6, n + this.fontsz / 3 + 5);
                    if (i != this.curO) {
                        graphics.drawLine(this.fontsz / 3 + 4, n + this.fontsz / 3 + 3, this.fontsz / 3 + 4, n + this.fontsz / 3 + 7);
                    }
                }
            }
            graphics.drawString(this.mainM[i], this.fontsz / 3 + 12, n + this.cBase);
            n += this.fontsz * 4 / 3;
            if (i == this.curO) {
                for (int j = 0; j < this.subM[i].length; ++j) {
                    if (j == this.curS) {
                        graphics.setColor(this.cColor[2]);
                    }
                    else {
                        graphics.setColor(this.cColor[3]);
                    }
                    graphics.drawLine(this.fontsz / 3 + 11 + this.fontsz / 2, n + this.fontsz * 2 / 3, this.fontsz / 3 + 10 + this.fontsz, n + this.fontsz * 2 / 3);
                    graphics.drawLine(this.fontsz / 3 + 11 + this.fontsz / 2, n - ((j == 0) ? 0 : (this.fontsz * 2 / 3)), this.fontsz / 3 + 11 + this.fontsz / 2, n + this.fontsz * 2 / 3);
                    graphics.drawString(this.subM[i][j], this.fontsz / 3 + 12 + this.fontsz, n + this.cBase);
                    n += this.fontsz * 4 / 3;
                }
            }
        }
    }
    
    public void setMenu(final String ppp) {
        this.ppp = ppp;
        this.inVal();
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public String Replace_ID(String repLnk) {
        for (int i = 0; i < this.m_sID.length; ++i) {
            if (this.m_sID[i] != null) {
                repLnk = this.repLnk(repLnk, "?" + (i + 1) + "?", this.m_sID[i]);
            }
        }
        return repLnk;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.Uab == 1) {
            return true;
        }
        String s = null;
        final int curM = n2 / (this.fontsz * 4 / 3);
        if (this.curO == -1) {
            if (curM < this.mainM.length && n < this.mainX[curM]) {
                if (this.mainS[curM] == 1) {
                    return true;
                }
                if (this.mainL[curM] != null) {
                    s = this.mainL[curM];
                }
                else {
                    final int n3 = curM;
                    this.curO = n3;
                    this.curM = n3;
                    this.curS = -1;
                    this.bpaint(this.dBuf);
                    this.repaint();
                }
            }
        }
        else if (curM < this.curO) {
            if (n < this.mainX[curM]) {
                if (this.mainS[curM] == 1) {
                    return true;
                }
                if (this.mainL[curM] != null) {
                    s = this.mainL[curM];
                }
                else {
                    final int n4 = curM;
                    this.curO = n4;
                    this.curM = n4;
                    this.curS = -1;
                    this.bpaint(this.dBuf);
                    this.repaint();
                }
            }
        }
        else if (curM == this.curO) {
            if (n < this.mainX[curM]) {
                this.curM = curM;
                this.curO = -1;
                this.curS = -1;
                this.bpaint(this.dBuf);
                this.repaint();
            }
        }
        else if (curM <= this.curO + this.subM[this.curO].length) {
            if (n < this.subX[this.curO][curM - this.curO - 1]) {
                s = this.subL[this.curO][curM - this.curO - 1];
            }
        }
        else if (curM < this.mainM.length + this.subM[this.curO].length && n < this.mainX[curM - this.subM[this.curO].length]) {
            if (this.mainS[curM - this.subM[this.curO].length] == 1) {
                return true;
            }
            if (this.mainL[curM - this.subM[this.curO].length] != null) {
                s = this.mainL[curM - this.subM[this.curO].length];
            }
            else {
                final int n5 = curM - this.subM[this.curO].length;
                this.curO = n5;
                this.curM = n5;
                this.curS = -1;
                this.bpaint(this.dBuf);
                this.repaint();
            }
        }
        if (s != null) {
            final String replace_ID = this.Replace_ID(this.repLnk(this.repLnk(this.repLnk(this.repLnk(this.repLnk(this.repLnk(s, "?1?", this.P1), "?2?", this.P2), "?3?", this.P3), "?8?", this.P8), "?9?", this.P9), "?BID?", this.BID));
            try {
                String substring = replace_ID;
                String substring2 = "down";
                if (substring.startsWith("javascript:")) {
                    JSObject.getWindow((Applet)this).eval(substring.substring(11, substring.length()));
                }
                else {
                    final int index = substring.indexOf(40);
                    final int lastIndex = substring.lastIndexOf(41);
                    if (index >= 0 && lastIndex >= 0) {
                        substring2 = substring.substring(index + 1, lastIndex);
                        substring = substring.substring(0, index);
                    }
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), substring), substring2);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
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
        this.P8 = this.getParameter("P8");
        this.P9 = this.getParameter("P9");
        this.BID = this.getParameter("BID");
        final String parameter = this.getParameter("MAX_ID");
        if (parameter != null) {
            this.m_nMaxID = Integer.parseInt(parameter, 10);
        }
        if (this.m_nMaxID != 0) {
            this.m_sID = new String[this.m_nMaxID];
        }
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
        final Font font = new Font("\u7d30\u660e\u9ad4", 0, this.fontsz);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.cBase = fontMetrics.getAscent() + this.fontsz / 5;
        this.dBuf.setFont(font);
        final String[] parse = this.parse(this.ppp, " ");
        this.mainM = new String[parse.length];
        this.mainL = new String[parse.length];
        this.mainX = new int[parse.length];
        this.mainS = new int[parse.length];
        this.subM = new String[parse.length][];
        this.subL = new String[parse.length][];
        this.subX = new int[parse.length][];
        for (int i = 0; i < parse.length; ++i) {
            final String[] parse2 = this.parse(parse[i], ",");
            this.mainM[i] = parse2[0];
            this.mainX[i] = fontMetrics.stringWidth(this.mainM[i]) + 17;
            this.mainS[i] = 0;
            if (parse2.length == 1) {
                this.mainS[i] = 1;
            }
            else if (parse2.length == 2) {
                this.mainL[i] = parse2[1];
            }
            else {
                final int n = (parse2.length - 1) / 2;
                this.subM[i] = new String[n];
                this.subL[i] = new String[n];
                this.subX[i] = new int[n];
                for (int j = 0; j < n; ++j) {
                    this.subM[i][j] = this.Replace_ID(parse2[j * 2 + 1]);
                    this.subL[i][j] = this.Replace_ID(parse2[j * 2 + 2]);
                    this.subX[i][j] = fontMetrics.stringWidth(this.subM[i][j]) + 32;
                }
            }
        }
        this.bpaint(this.dBuf);
        this.repaint();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        int curO = n2 / (this.fontsz * 4 / 3);
        int curS = -1;
        if (this.curO == -1) {
            if (curO >= this.mainM.length || n >= this.mainX[curO]) {
                curO = -1;
            }
        }
        else if (curO <= this.curO && n < this.mainX[curO]) {
            if (this.mainS[curO] == 1) {
                curO = -1;
            }
        }
        else if (curO > this.curO && curO <= this.curO + this.subM[this.curO].length && n < this.subX[this.curO][curO - this.curO - 1]) {
            curS = curO - this.curO - 1;
            curO = this.curO;
        }
        else if (curO > this.curO + this.subM[this.curO].length && curO < this.mainM.length + this.subM[this.curO].length && n < this.mainX[curO - this.subM[this.curO].length]) {
            if (this.mainS[curO - this.subM[this.curO].length] == 1) {
                curO = -1;
            }
            else {
                curO -= this.subM[this.curO].length;
            }
        }
        else {
            curO = -1;
        }
        if (curO != this.curM || curS != this.curS) {
            this.curM = curO;
            this.curS = curS;
            this.bpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
}
