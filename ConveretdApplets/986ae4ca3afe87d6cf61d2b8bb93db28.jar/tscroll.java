import java.awt.Color;
import java.awt.image.ImageObserver;
import java.io.StreamTokenizer;
import java.net.URL;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tscroll extends Applet implements Runnable
{
    public Vector dFile;
    Thread scrollmessage;
    private String[] ligne;
    int iLigne;
    int s_h;
    int s_v;
    int iMax;
    int iActif;
    boolean iCharge;
    boolean iEffect;
    int cBk;
    int cTx;
    int iR;
    int iB;
    int iG;
    int iRp;
    int iBp;
    int iGp;
    int iR2;
    int iB2;
    int iG2;
    int iR2p;
    int iB2p;
    int iG2p;
    int x_coord;
    int y_coord;
    int x_effect;
    int speed;
    int iScroll;
    int iPause;
    int delay;
    int appletWidth;
    int appletHeight;
    Image offScreenImage;
    Image fdImg;
    Image gdImg;
    Graphics offScreen;
    Font wordFont;
    FontMetrics wordMetrics;
    
    public void stop() {
        this.scrollmessage.stop();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.dFile.elementAt(this.iActif).sU);
        return true;
    }
    
    public tscroll() {
        this.dFile = new Vector(5, 5);
        this.ligne = new String[20];
        this.iActif = 999;
        this.iCharge = true;
        this.cTx = 16777215;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "file", "text", "file mess URL" }, { "image", "text", "background img" }, { "fgimg", "text", "foreground img" }, { "speed", "int", "speed scroll" }, { "delay", "int", "msecs between jumps" }, { "pause", "int", "msecs wait v scroll" }, { "tscr", "int", "scroll type 1..4" } };
    }
    
    public void readFile(final String s) {
        this.dFile.removeAllElements();
        this.iMax = 0;
        try {
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new URL(this.getCodeBase(), s).openStream());
            streamTokenizer.commentChar(59);
            streamTokenizer.quoteChar(34);
            streamTokenizer.eolIsSignificant(true);
            while (true) {
                final rd2 rd2 = new rd2();
                if (!rd2.get(rd2, streamTokenizer)) {
                    break;
                }
                this.dFile.addElement(rd2);
                ++this.iMax;
            }
        }
        catch (Exception ex) {}
    }
    
    public void Charge() {
        ++this.iActif;
        if (this.iActif > this.iMax) {
            this.iActif = 0;
        }
        final String s = new String(this.dFile.elementAt(this.iActif).sM);
        if (this.iScroll == 1) {
            this.ligne[0] = new String(s);
            this.iLigne = 1;
        }
        else {
            int n = 0;
            this.iLigne = 0;
            for (int i = s.indexOf("\n", n); i > -1; i = s.indexOf("\n", n)) {
                this.ligne[this.iLigne++] = s.substring(n, i);
                n = i + 1;
            }
            this.ligne[this.iLigne++] = s.substring(n, s.length());
        }
        this.iCharge = false;
        if (this.dFile.elementAt(this.iActif).wFont != null) {
            this.wordFont = this.dFile.elementAt(this.iActif).wFont;
            this.wordMetrics = this.getFontMetrics(this.wordFont);
        }
        this.s_h = -this.wordMetrics.stringWidth(this.ligne[0]);
        this.s_v = this.wordMetrics.getHeight();
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.offScreen != null) {
            this.paintApplet(this.offScreen);
            graphics.drawImage(this.offScreenImage, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        if (this.iCharge) {
            this.Charge();
            if (this.dFile.elementAt(this.iActif).iB > -1) {
                this.cBk = this.dFile.elementAt(this.iActif).iB;
            }
            if (this.dFile.elementAt(this.iActif).iT > -1) {
                this.cTx = this.dFile.elementAt(this.iActif).iT;
            }
            graphics.setFont(this.wordFont);
            if (this.iActif + 1 == this.iMax) {
                this.iR = 0;
            }
            else {
                this.iR = this.iActif + 1;
            }
            Color color;
            if (this.dFile.elementAt(this.iR).iB > -1) {
                color = new Color(this.dFile.elementAt(this.iR).iB);
            }
            else {
                color = new Color(this.cBk);
            }
            final Color color2 = new Color(this.cBk);
            this.iRp = color2.getRed() - color.getRed();
            this.iBp = color2.getBlue() - color.getBlue();
            this.iGp = color2.getGreen() - color.getGreen();
            final Color color3 = new Color(this.cTx);
            this.iR2 = color3.getRed();
            this.iB2 = color3.getBlue();
            this.iG2 = color3.getGreen();
            this.iR2p = this.iR2 - color.getRed();
            this.iB2p = this.iB2 - color.getBlue();
            this.iG2p = this.iG2 - color.getGreen();
            final Color color4 = new Color(this.cBk);
            this.iR = color4.getRed();
            this.iB = color4.getBlue();
            this.iG = color4.getGreen();
        }
        graphics.clearRect(0, 0, this.appletWidth, this.appletHeight);
        if (this.iEffect && this.iScroll == 3) {
            graphics.setColor(new Color(this.iR - this.iRp * this.x_effect / 20, this.iG - this.iGp * this.x_effect / 20, this.iB - this.iBp * this.x_effect / 20));
        }
        else {
            graphics.setColor(new Color(this.cBk));
        }
        graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
        if (this.iEffect && this.iScroll == 3) {
            graphics.setColor(new Color(this.iR2 - this.iR2p * this.x_effect / 20, this.iG2 - this.iG2p * this.x_effect / 20, this.iB2 - this.iB2p * this.x_effect / 20));
        }
        else {
            graphics.setColor(new Color(this.cTx));
        }
        if (this.fdImg != null) {
            graphics.drawImage(this.fdImg, 0, 0, this.appletWidth, this.appletHeight, this);
        }
        for (int i = 0; i < this.iLigne; ++i) {
            if (this.iScroll != 1) {
                if (this.dFile.elementAt(this.iActif).iScr == 1) {
                    this.x_coord = this.appletWidth - this.wordMetrics.stringWidth(this.ligne[i]) - 5;
                }
                else if (this.dFile.elementAt(this.iActif).iScr == 2) {
                    this.x_coord = 5;
                }
                else {
                    this.x_coord = (this.appletWidth - this.wordMetrics.stringWidth(this.ligne[i])) / 2;
                }
            }
            graphics.drawString(this.ligne[i], this.x_coord, this.y_coord + this.s_v * i);
        }
        if (this.iEffect) {
            ++this.x_effect;
            if (this.iScroll == 2) {
                graphics.fillRect(0, 0, this.appletWidth * this.x_effect / 20, this.appletHeight * this.x_effect / 20);
            }
            if (this.x_effect >= 20) {
                this.iEffect = false;
                this.iCharge = true;
                this.x_effect = 0;
                if (this.iScroll == 1) {
                    this.y_coord = this.appletHeight / 2 + (this.wordMetrics.getHeight() - this.wordMetrics.getDescent()) / 2;
                    this.x_coord = this.appletWidth;
                }
                else {
                    this.x_coord = (this.appletWidth + this.s_h) / 2;
                    this.y_coord = this.appletHeight + this.wordMetrics.getHeight();
                }
            }
        }
        if (this.gdImg != null) {
            graphics.drawImage(this.gdImg, 0, 0, this.appletWidth, this.appletHeight, this);
        }
    }
    
    public void start() {
        if (this.iScroll == 1) {
            this.y_coord = this.appletHeight / 2 + (this.wordMetrics.getHeight() - this.wordMetrics.getDescent()) / 2;
            this.x_coord = this.appletWidth;
        }
        else {
            this.x_coord = (this.appletWidth + this.s_h) / 2;
            this.y_coord = this.appletHeight + this.wordMetrics.getHeight();
        }
        (this.scrollmessage = new Thread(this)).start();
    }
    
    public String getAppletInfo() {
        return "tscroll v2.10 * Message Applet by R. BERTHOU (1997) - E-Mail : rberthou@pratique.fr";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.dFile.elementAt(this.iActif).sU != null) {
            try {
                URL url;
                if (this.dFile.elementAt(this.iActif).sU.charAt(0) == '.') {
                    url = new URL(this.getCodeBase(), this.dFile.elementAt(this.iActif).sU);
                }
                else {
                    url = new URL(this.dFile.elementAt(this.iActif).sU);
                }
                this.getAppletContext().showDocument(url, this.dFile.elementAt(this.iActif).sTarg);
            }
            catch (Exception ex) {
                this.showStatus("Bad URL! =" + this.dFile.elementAt(this.iActif).sU);
            }
        }
        return true;
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            long n = System.currentTimeMillis() + this.delay;
            if (!this.iEffect) {
                if (this.iScroll == 1) {
                    this.x_coord -= this.speed;
                    if (this.x_coord < this.s_h) {
                        this.x_coord = this.appletWidth;
                    }
                }
                else {
                    this.y_coord -= this.speed;
                    if (this.y_coord < -this.s_v * (this.iLigne - 1)) {
                        this.y_coord = this.appletHeight + this.wordMetrics.getHeight();
                    }
                }
            }
            this.repaint();
            if (!this.iEffect && this.y_coord >= this.s_v && this.y_coord - this.speed < this.s_v) {
                n += this.iPause;
                this.x_effect = -1;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < n) {
                try {
                    Thread.currentThread();
                    Thread.sleep((int)(n - currentTimeMillis));
                }
                catch (InterruptedException ex) {}
            }
            if (this.x_effect == -1) {
                this.x_effect = 0;
                if (this.iScroll != 4) {
                    this.iEffect = true;
                }
                else {
                    this.iCharge = true;
                    this.x_coord = (this.appletWidth + this.s_h) / 2;
                    this.y_coord = this.appletHeight + this.wordMetrics.getHeight();
                }
            }
        }
    }
    
    public void init() {
        this.appletWidth = this.size().width;
        this.appletHeight = this.size().height;
        try {
            this.offScreenImage = this.createImage(this.appletWidth, this.appletHeight);
            this.offScreen = this.offScreenImage.getGraphics();
        }
        catch (Exception ex) {
            this.offScreen = null;
        }
        this.wordFont = this.getFont();
        this.wordMetrics = this.getFontMetrics(this.wordFont);
        final String parameter = this.getParameter("speed");
        this.speed = ((parameter == null) ? 1 : Integer.parseInt(parameter));
        final String parameter2 = this.getParameter("delay");
        this.delay = ((parameter2 == null) ? 100 : Integer.parseInt(parameter2));
        final String parameter3 = this.getParameter("pause");
        this.iPause = ((parameter3 == null) ? 0 : Integer.parseInt(parameter3));
        final String parameter4 = this.getParameter("tscr");
        this.iScroll = ((parameter4 == null) ? 1 : Integer.parseInt(parameter4));
        final String parameter5 = this.getParameter("image");
        if (parameter5 != null) {
            this.fdImg = this.getImage(this.getCodeBase(), parameter5);
        }
        final String parameter6 = this.getParameter("fgimg");
        if (parameter6 != null) {
            this.gdImg = this.getImage(this.getCodeBase(), parameter6);
        }
        this.readFile(this.getParameter("file"));
    }
}
