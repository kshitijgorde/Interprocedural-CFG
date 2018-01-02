import java.awt.Color;
import java.awt.image.ImageObserver;
import netscape.javascript.JSObject;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class aTicker extends Applet implements Runnable, MouseMotionListener, MouseListener
{
    public Vector dFile;
    Thread scrollmessage;
    private char cSep;
    String sMess;
    String sSpacing;
    boolean bLoc;
    int s_h;
    int iMax;
    int iActif;
    int iCur;
    int cBk;
    int x_coord;
    int y_coord;
    int speed;
    int iPas;
    int iDivider;
    int delay;
    int aWidth;
    int aHeight;
    Image offScreenImage;
    Graphics offScreen;
    Font wFont1;
    Font wFont2;
    Font wFont3;
    int cT1;
    int cT2;
    int cT3;
    FontMetrics wM;
    
    public aTicker() {
        this.dFile = new Vector(5, 5);
        this.cSep = ';';
        this.bLoc = false;
        this.iActif = 999;
        this.iCur = -1;
        this.cBk = 0;
        this.cT1 = 16777215;
        this.cT2 = 16776960;
        this.cT3 = 16776960;
    }
    
    public void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        final String parameter = this.getParameter("speed");
        this.speed = ((parameter == null) ? 1 : Integer.parseInt(parameter));
        final String parameter2 = this.getParameter("delay");
        this.delay = ((parameter2 == null) ? 20 : Integer.parseInt(parameter2));
        final String parameter3 = this.getParameter("pas");
        this.iPas = ((parameter3 == null) ? 20 : Integer.parseInt(parameter3));
        final String parameter4 = this.getParameter("divider");
        this.iDivider = ((parameter4 == null) ? 20 : Integer.parseInt(parameter4));
        this.sSpacing = "";
        for (int i = 0; i < this.iDivider; ++i) {
            this.sSpacing += " ";
        }
        final String parameter5 = this.getParameter("bgcolor");
        this.cBk = ((parameter5 == null) ? 2105472 : Integer.parseInt(parameter5));
        final String parameter6 = this.getParameter("cSep");
        if (parameter6 != null) {
            this.cSep = parameter6.charAt(0);
        }
        if (this.getParameter("local") != null) {
            this.bLoc = true;
        }
        String parameter7 = this.getParameter("Font1");
        if (parameter7 == null) {
            parameter7 = "Arial, 10, 1, 16777215 ";
        }
        this.setFont(1, parameter7);
        String parameter8 = this.getParameter("Font2");
        if (parameter8 == null) {
            parameter8 = "Arial, 10, 2, 16776960 ";
        }
        this.setFont(2, parameter8);
        String parameter9 = this.getParameter("Font3");
        if (parameter9 == null) {
            parameter9 = "Arial, 10, 2, 16776960 ";
        }
        this.setFont(3, parameter9);
        String parameter10 = this.getParameter("file");
        if (parameter10 == null) {
            parameter10 = "mess.txt";
        }
        this.wM = this.getFontMetrics(this.wFont1);
        this.readFile(parameter10);
    }
    
    public void setFont(final int n, final String s) {
        int n2 = 0;
        int n3 = 0;
        String s2 = "";
        int int1 = 10;
        int int2 = 1;
        int int3 = 16777215;
        while (true) {
            final int index = s.indexOf(44, n2);
            String s3;
            if (index > 0) {
                s3 = new String(s.substring(n2, index).trim());
            }
            else {
                s3 = new String(s.substring(n2).trim());
            }
            if (index > -1 || s3.length() > 0) {
                if (n3 == 0) {
                    s2 = new String(s3);
                }
                if (n3 == 1) {
                    int1 = Integer.parseInt(s3);
                }
                if (n3 == 2) {
                    int2 = Integer.parseInt(s3);
                }
                if (n3 == 3) {
                    int3 = Integer.parseInt(s3);
                }
                ++n3;
            }
            if (index == -1) {
                break;
            }
            n2 = index + 1;
        }
        Font font = new Font(s2, int2, int1);
        if (font == null) {
            font = this.getFont();
        }
        if (n == 1) {
            this.wFont1 = font;
            this.cT1 = int3;
        }
        else if (n == 2) {
            this.wFont2 = font;
            this.cT2 = int3;
        }
        else {
            this.wFont3 = font;
            this.cT3 = int3;
        }
    }
    
    public void readFile(final String s) {
        this.dFile.removeAllElements();
        this.iMax = 0;
        BufferedReader bufferedReader = null;
        if (!this.bLoc) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getCodeBase(), s).openStream()));
            }
            catch (Exception ex) {
                bufferedReader = null;
                this.bLoc = true;
            }
        }
        int n = 0;
        while (true) {
            final rp rp = new rp();
            String s2;
            if (this.bLoc) {
                s2 = this.getParameter(s + n);
                ++n;
            }
            else {
                try {
                    s2 = bufferedReader.readLine();
                }
                catch (Exception ex2) {
                    break;
                }
            }
            if (!rp.get(s2, this.cSep, "_new", 16777215, 16776960)) {
                break;
            }
            if (rp.sM == null) {
                continue;
            }
            this.dFile.addElement(rp);
            ++this.iMax;
        }
        this.iActif = 0;
        this.sMess = this.dFile.elementAt(this.iActif).sM;
        this.s_h = -this.wM.stringWidth(this.sMess);
    }
    
    public void start() {
        (this.scrollmessage = new Thread(this)).start();
    }
    
    public void stop() {
        this.scrollmessage.stop();
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            final long n = System.currentTimeMillis() + this.delay;
            this.x_coord -= this.speed;
            if (this.x_coord < this.s_h) {
                ++this.iActif;
                this.x_coord += -this.s_h + this.iPas;
                if (this.iActif >= this.iMax) {
                    this.iActif = 0;
                }
                this.sMess = this.dFile.elementAt(this.iActif).sM;
                if (this.sMess.trim().equals("<br>")) {
                    this.s_h = -this.wM.stringWidth(this.sSpacing);
                }
                else {
                    this.s_h = -this.wM.stringWidth(this.sMess);
                }
            }
            this.repaint();
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < n) {
                try {
                    Thread.currentThread();
                    Thread.sleep((int)(n - currentTimeMillis));
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        int iActif = this.iActif;
        int i = this.x_coord;
        int iCur = -1;
        do {
            int n;
            if (((rp)this.dFile.elementAt(iActif)).sM.trim().equals("<br>")) {
                n = this.wM.stringWidth(this.sSpacing);
            }
            else {
                n = this.wM.stringWidth(this.dFile.elementAt(iActif).sM);
            }
            if (x > i && x < i + n) {
                iCur = iActif;
                break;
            }
            i = i + this.iPas + n;
            if (++iActif < this.iMax) {
                continue;
            }
            iActif = 0;
        } while (i <= this.aWidth);
        if (iCur != this.iCur) {
            if (iCur == -1) {
                this.setCursor(new Cursor(0));
            }
            else {
                this.setCursor(new Cursor(12));
            }
            this.iCur = iCur;
            if (this.iCur == -1) {
                this.showStatus("");
            }
            else if (this.dFile.elementAt(this.iCur).sH == null) {
                this.showStatus(this.dFile.elementAt(this.iCur).sU);
            }
            else {
                this.showStatus(this.dFile.elementAt(this.iCur).sH);
            }
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.iCur == -1) {
            return;
        }
        if (this.dFile.elementAt(this.iCur).sU != null && !this.dFile.elementAt(this.iCur).sU.trim().equals("")) {
            try {
                if (this.dFile.elementAt(this.iCur).sT.equals("_script")) {
                    JSObject.getWindow((Applet)this).eval(this.dFile.elementAt(this.iCur).sU);
                }
                else {
                    URL url;
                    if (this.dFile.elementAt(this.iCur).sU.charAt(0) == '.') {
                        url = new URL(this.getCodeBase(), this.dFile.elementAt(this.iCur).sU);
                    }
                    else {
                        url = new URL(this.dFile.elementAt(this.iCur).sU);
                    }
                    this.getAppletContext().showDocument(url, this.dFile.elementAt(this.iCur).sT);
                }
            }
            catch (Exception ex) {
                this.showStatus("Bad URL! =" + this.dFile.elementAt(this.iCur).sU);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        try {
            this.scrollmessage.suspend();
        }
        catch (Exception ex) {}
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        try {
            this.scrollmessage.resume();
        }
        catch (Exception ex) {}
        this.iCur = -1;
        this.showStatus("");
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public synchronized void update(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        Label_0119: {
            if (this.aWidth == width && this.aHeight == height) {
                if (this.offScreen != null) {
                    break Label_0119;
                }
            }
            try {
                this.offScreenImage = this.createImage(width, height);
                this.offScreen = this.offScreenImage.getGraphics();
            }
            catch (Exception ex) {
                this.offScreen = null;
            }
            this.aWidth = width;
            this.aHeight = height;
            this.y_coord = this.aHeight / 2 + (this.wM.getHeight() - this.wM.getDescent()) / 2;
            this.x_coord = this.aWidth;
        }
        if (this.offScreen != null) {
            this.paintApplet(this.offScreen);
            graphics.drawImage(this.offScreenImage, 0, 0, this);
        }
        else {
            this.paintApplet(graphics);
        }
    }
    
    public void paintApplet(final Graphics graphics) {
        graphics.clearRect(0, 0, this.aWidth, this.aHeight);
        graphics.setColor(new Color(this.cBk));
        graphics.fillRect(0, 0, this.aWidth, this.aHeight);
        int iActif = this.iActif;
        int x_coord = this.x_coord;
        do {
            try {
                if (this.iCur == iActif) {
                    if (!this.dFile.elementAt(this.iCur).sU.trim().equals("")) {
                        graphics.setColor(new Color(this.cT3));
                        graphics.setFont(this.wFont3);
                        graphics.drawLine(x_coord, this.y_coord + 2, x_coord + this.wM.stringWidth(this.dFile.elementAt(iActif).sM), this.y_coord + 2);
                    }
                    else {
                        graphics.setColor(new Color(this.cT1));
                        graphics.setFont(this.wFont1);
                    }
                }
                else if (!((rp)this.dFile.elementAt(iActif)).sU.trim().equals("")) {
                    graphics.setColor(new Color(this.cT2));
                    graphics.setFont(this.wFont2);
                    graphics.drawLine(x_coord, this.y_coord + 2, x_coord + this.wM.stringWidth(this.dFile.elementAt(iActif).sM), this.y_coord + 2);
                }
                else {
                    graphics.setColor(new Color(this.cT1));
                    graphics.setFont(this.wFont1);
                }
            }
            catch (Exception ex) {
                this.showStatus("EXCEPTION: " + ex.getMessage());
            }
            if (((rp)this.dFile.elementAt(iActif)).sM.trim().equals("<br>")) {
                graphics.drawString(this.sSpacing, x_coord, this.y_coord);
                x_coord += this.iPas + this.wM.stringWidth(this.sSpacing);
            }
            else {
                graphics.drawString(this.dFile.elementAt(iActif).sM, x_coord, this.y_coord);
                x_coord += this.iPas + this.wM.stringWidth(this.dFile.elementAt(iActif).sM);
            }
            if (++iActif >= this.iMax) {
                iActif = 0;
            }
        } while (x_coord <= this.aWidth);
    }
}
