import java.net.InetAddress;
import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class quickserv extends Applet implements Runnable
{
    public Thread theThread;
    public boolean bRunning;
    public boolean bDemo;
    public Vector vItems;
    public String sPercent;
    public float iBarLength;
    public int nLoaded;
    public boolean bAuto;
    public String sRedirect;
    public Image bbImage;
    public Graphics bbGraphics;
    public Image imgLogo;
    public int iWidth;
    public int iHeight;
    public Font fFont;
    public FontMetrics fMetrics;
    
    public void init() {
        this.checkRegID();
        this.iWidth = this.size().width;
        this.iHeight = this.size().height;
        this.bbImage = this.createImage(this.iWidth, this.iHeight);
        this.bbGraphics = this.bbImage.getGraphics();
        this.fFont = new Font("SansSerif", 0, 9);
        this.bbGraphics.setFont(this.fFont);
        this.fMetrics = this.bbGraphics.getFontMetrics();
        final String parameter;
        if ((parameter = this.getParameter("redirect")) != null) {
            this.sRedirect = parameter;
        }
        if (this.getParameter("autoload") != null) {
            this.bAuto = true;
        }
        this.loadLogo();
        final String parameter2 = this.getParameter("file");
        if (parameter2 == null) {
            this.parseSpecifiedItems();
            return;
        }
        this.parseSpecifiedFile(parameter2);
    }
    
    public void start() {
        if (this.bAuto) {
            this.bRunning = true;
            new Thread(this).start();
        }
    }
    
    public void stop() {
        this.bRunning = false;
    }
    
    public void loadLogo() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.imgLogo = this.getImage(this.getCodeBase(), "QSLogo.gif");
        try {
            mediaTracker.addImage(this.imgLogo, 0);
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {}
    }
    
    public void showStatus(final String s) {
        this.getAppletContext().showStatus(s);
    }
    
    public void parseSpecifiedItems() {
        int n = 0;
        String parameter;
        while ((parameter = this.getParameter("item" + Integer.toString(n++))) != null) {
            this.vItems.addElement(parameter);
            if (this.bDemo && n > 4) {
                return;
            }
        }
    }
    
    public void parseSpecifiedFile(final String s) {
        int n = 0;
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            openConnection.setUseCaches(true);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                this.vItems.addElement(line);
                ++n;
                if (this.bDemo && n > 4) {
                    break;
                }
            }
            dataInputStream.close();
        }
        catch (IOException ex) {
            System.out.println("File Error: " + s);
        }
    }
    
    public void cacheItem(final String s) {
        final byte[] array = new byte[10240];
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            openConnection.setUseCaches(true);
            final InputStream inputStream = openConnection.getInputStream();
            while (inputStream.read(array) != -1) {}
            inputStream.close();
        }
        catch (IOException ex) {
            System.out.println("Error Preloading: " + s);
        }
    }
    
    public void setBarLength(final int n) {
        this.iBarLength = 82.0f * ((n + 1) / this.vItems.size());
        this.sPercent = String.valueOf(String.valueOf((int)(this.iBarLength / 82.0f * 100.0f))) + "%";
    }
    
    public void Redirect() {
        try {
            this.getAppletContext().showDocument(new URL(this.sRedirect));
        }
        catch (Exception ex) {
            System.out.println("Error Redirect: " + this.sRedirect);
        }
    }
    
    public void run() {
        while (this.nLoaded < this.vItems.size()) {
            if (!this.bRunning) {
                return;
            }
            this.cacheItem(this.vItems.elementAt(this.nLoaded));
            this.setBarLength(this.nLoaded);
            this.repaint();
            ++this.nLoaded;
        }
        this.showStatus("QuickServ v2.5 : Heathco Software");
        if (this.sRedirect != null) {
            this.Redirect();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.imgLogo != null) {
            this.bbGraphics.drawImage(this.imgLogo, 0, 0, this);
        }
        this.bbGraphics.setColor(Color.blue);
        this.bbGraphics.fillRect(3, 12, (int)this.iBarLength, 7);
        this.bbGraphics.setColor(Color.white);
        this.bbGraphics.drawString(this.sPercent, 45 - this.fMetrics.stringWidth(this.sPercent) / 2, 19);
        graphics.drawImage(this.bbImage, 0, 0, this);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.bRunning) {
            this.bAuto = true;
            this.start();
        }
        else {
            this.stop();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("QuickServ v2.5 : Heathco Software");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public void checkRegID() {
        String hostAddress = null;
        final String parameter = this.getParameter("RegID");
        if (parameter == null) {
            System.out.println("No Valid Registration ID Found");
            System.exit(1);
        }
        if (parameter.equals("-QuickServ Demo- http://www.heathcosoft.com")) {
            System.out.println("QuickServ Demo in use\nVisit http://www.heathcosoft.com");
            this.bDemo = true;
            return;
        }
        try {
            hostAddress = InetAddress.getByName(this.getCodeBase().getHost()).getHostAddress();
        }
        catch (Exception ex) {
            System.out.println("Unable To Lookup IP Address");
            System.exit(1);
        }
        if (!parameter.equals(this.getCode(hostAddress))) {
            System.out.println("Invalid Registration ID Number");
            System.exit(1);
        }
    }
    
    public String getCode(final String s) {
        final int n = 0;
        final int index = s.indexOf(".", n);
        final int int1 = Integer.parseInt(s.substring(n, index));
        final int n2 = index + 1;
        final int index2 = s.indexOf(".", n2);
        final int int2 = Integer.parseInt(s.substring(n2, index2));
        final int n3 = index2 + 1;
        final int index3 = s.indexOf(".", n3);
        final int int3 = Integer.parseInt(s.substring(n3, index3));
        final int n4 = index3 + 1;
        s.indexOf(".", n4);
        final int int4 = Integer.parseInt(s.substring(n4, s.length()));
        return this.getCodeString(int4 * int3 + 1 | int2 * int1 + 1 | int1 + 4 | 0x48, int4 * int2 + 2 | int3 * int1 + 2 | (int2 + 3 & 0x53), int4 * int1 + 3 | int3 * int2 + 3 | int3 + 2 | 0x30, int4 * int3 + 4 | int3 * int2 + 4 | (int4 + 1 & 0x31));
    }
    
    public String getCodeString(final int n, final int n2, final int n3, final int n4) {
        return String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(Integer.toString(n))).append(Integer.toString(n2)).toString())).append(Integer.toString(n3)).toString()) + Integer.toString(n4);
    }
    
    public quickserv() {
        this.bRunning = false;
        this.bDemo = false;
        this.vItems = new Vector();
        this.sPercent = "0%";
        this.bAuto = false;
    }
}
