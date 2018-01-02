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
    int delay;
    int aWidth;
    int aHeight;
    Image offScreenImage;
    Graphics offScreen;
    Font wFont1;
    Font wFont2;
    int cT1;
    int cT2;
    FontMetrics wM;
    
    public void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        final String parameter = this.getParameter("speed");
        this.speed = ((parameter == null) ? 1 : Integer.parseInt(parameter));
        final String parameter2 = this.getParameter("delay");
        this.delay = ((parameter2 == null) ? 20 : Integer.parseInt(parameter2));
        final String parameter3 = this.getParameter("pas");
        this.iPas = ((parameter3 == null) ? 20 : Integer.parseInt(parameter3));
        final String parameter4 = this.getParameter("bgcolor");
        this.cBk = ((parameter4 == null) ? 2105472 : Integer.parseInt(parameter4));
        final String parameter5 = this.getParameter("cSep");
        if (parameter5 != null) {
            this.cSep = parameter5.charAt(0);
        }
        if (this.getParameter("local") != null) {
            this.bLoc = true;
        }
        String s = this.getParameter("Font1");
        if (s == null) {
            s = "Arail, 10, 1, " + 16777215 + " ";
        }
        this.setFont(1, s);
        String s2 = this.getParameter("Font2");
        if (s2 == null) {
            s2 = "Arail, 10, 2, " + 16776960 + " ";
        }
        this.setFont(2, s2);
        String parameter6 = this.getParameter("file");
        if (parameter6 == null) {
            parameter6 = "mess.txt";
        }
        this.wM = this.getFontMetrics(this.wFont1);
        this.readFile(parameter6);
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
            return;
        }
        this.wFont2 = font;
        this.cT2 = int3;
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
                s2 = this.getParameter(String.valueOf(s) + n);
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
                this.s_h = -this.wM.stringWidth(this.sMess);
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
            final int stringWidth = this.wM.stringWidth(this.dFile.elementAt(iActif).sM);
            if (x > i && x < i + stringWidth) {
                iCur = iActif;
                break;
            }
            i = i + this.iPas + stringWidth;
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
        if (this.dFile.elementAt(this.iCur).sU != null) {
            try {
                if (this.dFile.elementAt(this.iCur).sT.equals("_script")) {
                    JSObject.getWindow((Applet)this).eval(this.dFile.elementAt(this.iCur).sU);
                    return;
                }
                URL url;
                if (this.dFile.elementAt(this.iCur).sU.charAt(0) == '.') {
                    url = new URL(this.getCodeBase(), this.dFile.elementAt(this.iCur).sU);
                }
                else {
                    url = new URL(this.dFile.elementAt(this.iCur).sU);
                }
                this.getAppletContext().showDocument(url, this.dFile.elementAt(this.iCur).sT);
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
        Label_0115: {
            if (this.aWidth == width && this.aHeight == height) {
                if (this.offScreen != null) {
                    break Label_0115;
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
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        graphics.clearRect(0, 0, this.aWidth, this.aHeight);
        graphics.setColor(new Color(this.cBk));
        graphics.fillRect(0, 0, this.aWidth, this.aHeight);
        int iActif = this.iActif;
        int i = this.x_coord;
        do {
            if (this.iCur == iActif) {
                graphics.setColor(new Color(this.cT2));
                graphics.setFont(this.wFont2);
            }
            else {
                graphics.setColor(new Color(this.cT1));
                graphics.setFont(this.wFont1);
            }
            graphics.drawString(this.dFile.elementAt(iActif).sM, i, this.y_coord);
            i += this.iPas + this.wM.stringWidth(this.dFile.elementAt(iActif).sM);
            if (++iActif >= this.iMax) {
                iActif = 0;
            }
        } while (i <= this.aWidth);
    }
    
    public aTicker() {
        this.dFile = new Vector(5, 5);
        this.cSep = ';';
        this.bLoc = false;
        this.iActif = 999;
        this.iCur = -1;
        this.cT1 = 16777215;
        this.cT2 = 16776960;
    }
}
