import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class X_ImageS extends Applet implements Runnable
{
    Thread mThread;
    Image mBackGroundImage;
    Graphics mBackGroundGraphics;
    Image mIMAGEX;
    Graphics mGraphicsX;
    Vector mImages;
    int mApplet_Width;
    int mApplet_Height;
    Random mRND;
    boolean mDragDrop;
    int mOldX;
    int mOldY;
    int mActiveImageIndex;
    int mWAIT_TIME;
    int mSPEED;
    Color mACTIVE_COLOR;
    Color mBACKCOLOR;
    private boolean mValidRegCode;
    
    public void stop() {
        try {
            this.mThread.stop();
            this.mThread = null;
        }
        catch (Exception ex) {
            System.out.println("Text Applet, module stop() error : " + ex.toString());
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(12));
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.mIMAGEX, 0, 0, this);
    }
    
    public X_ImageS() {
        this.mRND = new Random((int)System.currentTimeMillis());
        this.mDragDrop = false;
        this.mOldX = 0;
        this.mOldY = 0;
        this.mActiveImageIndex = -1;
        this.mWAIT_TIME = 100;
        this.mSPEED = 10;
        this.mACTIVE_COLOR = this.stringToColor("FF0000");
        this.mBACKCOLOR = this.stringToColor("000000");
        this.mValidRegCode = false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if ((event.controlDown() && this.mActiveImageIndex >= 0) || event.clickCount >= 2) {
            final X_Img x_Img = this.mImages.elementAt(this.mActiveImageIndex);
            if (!x_Img.Link.equalsIgnoreCase("")) {
                this.showURL(x_Img.Link, x_Img.TargetFrame);
            }
        }
        this.mDragDrop = false;
        return true;
    }
    
    public void destroy() {
        try {
            System.gc();
        }
        catch (Exception ex) {
            System.out.println("error on destroy() -> " + ex.toString());
        }
    }
    
    private void PrepareImagesToShow() {
        for (int i = 0; i < this.mImages.size(); ++i) {
            final X_Img x_Img = this.mImages.elementAt(i);
            if (!x_Img.selected) {
                if ((x_Img.direction == 0 && x_Img.x > this.mApplet_Width) || (x_Img.direction == 1 && x_Img.x < -x_Img.width) || (x_Img.direction == 2 && x_Img.y > this.mApplet_Height) || (x_Img.direction == 3 && x_Img.y < -x_Img.height) || (x_Img.direction == 4 && (x_Img.x > this.mApplet_Width || x_Img.y > this.mApplet_Height)) || (x_Img.direction == 5 && (x_Img.x < -x_Img.width || x_Img.y < -x_Img.height)) || (x_Img.direction == 6 && (x_Img.x < -x_Img.width || x_Img.y > this.mApplet_Height)) || (x_Img.direction == 7 && (x_Img.x > this.mApplet_Width || x_Img.y < -x_Img.height))) {
                    x_Img.direction = (int)(this.mRND.nextDouble() * 9999.0) % 8;
                    System.out.println(String.valueOf(x_Img.direction));
                    x_Img.speed = (int)(this.mRND.nextDouble() * this.mSPEED + this.mSPEED);
                    switch (x_Img.direction) {
                        case 0: {
                            x_Img.x = (int)(-1.0 * this.mRND.nextDouble() * this.mApplet_Width) - this.mApplet_Width;
                            x_Img.y = (int)(this.mRND.nextDouble() * (this.mApplet_Height - 2 * x_Img.height)) + x_Img.height;
                            break;
                        }
                        case 1: {
                            x_Img.x = (int)(this.mRND.nextDouble() * this.mApplet_Width + this.mApplet_Width);
                            x_Img.y = (int)(this.mRND.nextDouble() * (this.mApplet_Height - 2 * x_Img.height)) + x_Img.height;
                            break;
                        }
                        case 2: {
                            x_Img.x = (int)(this.mRND.nextDouble() * (this.mApplet_Width - 2 * x_Img.width)) + x_Img.width;
                            x_Img.y = (int)(-1.0 * this.mRND.nextDouble() * this.mApplet_Height) - this.mApplet_Height;
                            break;
                        }
                        case 3: {
                            x_Img.x = (int)(this.mRND.nextDouble() * (this.mApplet_Width - 2 * x_Img.width)) + x_Img.width;
                            x_Img.y = (int)(this.mRND.nextDouble() * this.mApplet_Height);
                            break;
                        }
                        case 4: {
                            final double nextDouble = this.mRND.nextDouble();
                            x_Img.x = (int)(-1.0 * nextDouble * this.mApplet_Width) - this.mApplet_Width;
                            x_Img.y = (int)(-1.0 * nextDouble * this.mApplet_Height) - this.mApplet_Height;
                            break;
                        }
                        case 5: {
                            final double nextDouble2 = this.mRND.nextDouble();
                            x_Img.x = (int)(nextDouble2 * (this.mApplet_Width - 2 * x_Img.width)) + x_Img.width;
                            x_Img.y = (int)(nextDouble2 * (this.mApplet_Height - 2 * x_Img.height)) + x_Img.height;
                            break;
                        }
                        case 6: {
                            final double nextDouble3 = this.mRND.nextDouble();
                            x_Img.x = (int)(nextDouble3 * this.mApplet_Width) + this.mApplet_Width;
                            x_Img.y = (int)(-1.0 * nextDouble3 * this.mApplet_Height);
                            break;
                        }
                        case 7: {
                            final double nextDouble4 = this.mRND.nextDouble();
                            x_Img.x = (int)(-1.0 * nextDouble4 * this.mApplet_Width);
                            x_Img.y = (int)(nextDouble4 * this.mApplet_Height) + this.mApplet_Height;
                            break;
                        }
                    }
                }
                else {
                    switch (x_Img.direction) {
                        case 0: {
                            final X_Img x_Img2 = x_Img;
                            x_Img2.x += x_Img.speed;
                            break;
                        }
                        case 1: {
                            final X_Img x_Img3 = x_Img;
                            x_Img3.x -= x_Img.speed;
                            break;
                        }
                        case 2: {
                            final X_Img x_Img4 = x_Img;
                            x_Img4.y += x_Img.speed;
                            break;
                        }
                        case 3: {
                            final X_Img x_Img5 = x_Img;
                            x_Img5.y -= x_Img.speed;
                            break;
                        }
                        case 4: {
                            final X_Img x_Img6 = x_Img;
                            x_Img6.x += x_Img.speed;
                            final X_Img x_Img7 = x_Img;
                            x_Img7.y += x_Img.speed * this.mApplet_Height / this.mApplet_Width;
                            break;
                        }
                        case 5: {
                            final X_Img x_Img8 = x_Img;
                            x_Img8.x -= x_Img.speed;
                            final X_Img x_Img9 = x_Img;
                            x_Img9.y -= x_Img.speed * this.mApplet_Height / this.mApplet_Width;
                            break;
                        }
                        case 6: {
                            final X_Img x_Img10 = x_Img;
                            x_Img10.x -= x_Img.speed;
                            final X_Img x_Img11 = x_Img;
                            x_Img11.y += x_Img.speed * this.mApplet_Height / this.mApplet_Width;
                            break;
                        }
                        case 7: {
                            final X_Img x_Img12 = x_Img;
                            x_Img12.x += x_Img.speed;
                            final X_Img x_Img13 = x_Img;
                            x_Img13.y -= x_Img.speed * this.mApplet_Height / this.mApplet_Width;
                            break;
                        }
                    }
                }
            }
        }
        this.mGraphicsX.setColor(this.mBACKCOLOR);
        this.mGraphicsX.fillRect(0, 0, this.mApplet_Width, this.mApplet_Height);
        if (this.mBackGroundImage != null) {
            this.mGraphicsX.drawImage(this.mBackGroundImage, (this.mApplet_Width - this.mBackGroundImage.getWidth(this)) / 2, (this.mApplet_Height - this.mBackGroundImage.getHeight(this)) / 2, this);
        }
        int n = -1;
        for (int j = 0; j < this.mImages.size(); ++j) {
            final X_Img x_Img14 = this.mImages.elementAt(j);
            if (x_Img14.selected) {
                n = j;
            }
            else {
                this.mGraphicsX.drawImage(x_Img14.Img, x_Img14.x, x_Img14.y, this);
            }
        }
        if (n >= 0) {
            final X_Img x_Img15 = this.mImages.elementAt(n);
            this.mGraphicsX.drawImage(x_Img15.Img, x_Img15.x, x_Img15.y, this);
            this.mGraphicsX.setColor(this.mACTIVE_COLOR);
            this.mGraphicsX.draw3DRect(x_Img15.x, x_Img15.y, x_Img15.width, x_Img15.height, true);
            this.mGraphicsX.draw3DRect(x_Img15.x + 1, x_Img15.y + 1, x_Img15.width - 2, x_Img15.height - 2, true);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        try {
            (this.mThread = new Thread(this)).setPriority(1);
            this.mThread.start();
        }
        catch (Exception ex) {
            System.out.println("Text Applet, module start() error : " + ex.toString());
        }
    }
    
    public String getAppletInfo() {
        return "X_ImageS version 2.1\r\nCopyright 2000 by Applet World (appletworld@usa.net)";
    }
    
    public void showURL(final String s, final String s2) {
        String s3;
        if (s.startsWith("http") || s.startsWith("mail")) {
            s3 = "";
        }
        else {
            s3 = "" + this.getDocumentBase();
            if (!s3.substring(s3.length() - 1).equals("/")) {
                s3 = s3.substring(0, s3.lastIndexOf("/")) + "/";
            }
        }
        URL url;
        try {
            url = new URL(s3 + s);
        }
        catch (Exception ex) {
            this.showStatus("ShowURL Exception");
            return;
        }
        this.getAppletContext().showDocument(url, s2);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mImages.size(); ++i) {
            final X_Img x_Img = this.mImages.elementAt(i);
            if (n > x_Img.x && n < x_Img.x + x_Img.width && n2 > x_Img.y && n2 < x_Img.y + x_Img.height && x_Img.selected) {
                this.mActiveImageIndex = i;
                x_Img.selected = true;
                this.mDragDrop = true;
                this.mOldX = n;
                this.mOldY = n2;
                return true;
            }
            x_Img.selected = false;
        }
        for (int j = this.mImages.size() - 1; j >= 0; --j) {
            final X_Img x_Img2 = this.mImages.elementAt(j);
            if (n > x_Img2.x && n < x_Img2.x + x_Img2.width && n2 > x_Img2.y && n2 < x_Img2.y + x_Img2.height) {
                this.mActiveImageIndex = j;
                x_Img2.selected = true;
                this.mDragDrop = true;
                this.mOldX = n;
                this.mOldY = n2;
                return true;
            }
        }
        this.mActiveImageIndex = -1;
        return true;
    }
    
    private void GiveLoadingMessage() {
        this.getGraphics().setFont(new Font("Courier", 1, 12));
        this.getGraphics().setColor(Color.red);
        this.getGraphics().drawString("Loading Applet and related files ...", 20, 20);
    }
    
    public void initX() {
        this.RegCodeCheck(this.getCodeBase().toString(), this.getParameter("regcode"));
        this.sbReadParams();
        this.mApplet_Width = this.getSize().width;
        this.mApplet_Height = this.getSize().height;
        this.mIMAGEX = this.createImage(this.mApplet_Width, this.mApplet_Height);
        this.mGraphicsX = this.mIMAGEX.getGraphics();
    }
    
    private void RegCodeCheck(final String s, String s2) {
        final String s3 = new String("");
        int n = 210;
        s2 = ((s2 == null) ? "" : s2);
        final String s4 = (s == null) ? "" : s;
        String substring = s4.startsWith("http://") ? s4.substring(7) : s4;
        if (substring.indexOf("/") >= 0) {
            substring = substring.substring(0, substring.indexOf("/"));
        }
        if (substring.toLowerCase().indexOf("tskb") >= 0) {
            this.mValidRegCode = true;
            return;
        }
        if (substring.startsWith("file")) {
            this.mValidRegCode = true;
            return;
        }
        char c;
        for (c = '\0'; c < substring.length(); ++c) {
            n = (n + substring.charAt(c) * c) * c * '\u0002' % 1000000 + c;
        }
        if (("AW" + String.valueOf((n / c + 123654) * c % 1000000)).equalsIgnoreCase(s2)) {
            this.mValidRegCode = true;
        }
    }
    
    public void run() {
        this.GiveLoadingMessage();
        this.initX();
    Label_0008_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        if (!this.mValidRegCode) {
                            this.showStatus(" <<->> (C)2000 AppletWorld -> Web : http://go.to/appletworld, E-Mail : appletworld@usa.net <<->>");
                        }
                        if (this.mApplet_Width != this.getSize().width || this.mApplet_Height != this.getSize().height) {
                            this.mApplet_Width = this.getSize().width;
                            this.mApplet_Height = this.getSize().height;
                            this.mIMAGEX = this.createImage(this.mApplet_Width, this.mApplet_Height);
                            this.mGraphicsX = this.mIMAGEX.getGraphics();
                        }
                        this.PrepareImagesToShow();
                        this.repaint();
                        Thread.sleep(this.mWAIT_TIME);
                    }
                }
                catch (Exception ex) {
                    System.out.println("Text Applet, module run() error : " + ex.toString());
                    continue Label_0008_Outer;
                }
                continue;
            }
        }
    }
    
    public boolean mouseDrag(final Event event, final int mOldX, final int mOldY) {
        for (int i = 0; i < this.mImages.size(); ++i) {
            final X_Img x_Img = this.mImages.elementAt(i);
            if (x_Img.selected) {
                final X_Img x_Img2 = x_Img;
                x_Img2.x += mOldX - this.mOldX;
                final X_Img x_Img3 = x_Img;
                x_Img3.y += mOldY - this.mOldY;
                this.mOldX = mOldX;
                this.mOldY = mOldY;
                break;
            }
        }
        return true;
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    private void sbReadParams() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final String parameter = this.getParameter("wait_time");
        if (parameter != null && parameter != "") {
            this.mWAIT_TIME = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("speed");
        if (parameter2 != null && parameter2 != "") {
            this.mSPEED = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("active_color");
        if (parameter3 != null && parameter3 != "") {
            this.mACTIVE_COLOR = this.stringToColor(parameter3);
        }
        final String parameter4 = this.getParameter("backcolor");
        if (parameter4 != null && parameter4 != "") {
            this.mBACKCOLOR = this.stringToColor(parameter4);
        }
        int n = 0;
        this.mImages = new Vector(1, 1);
        for (String s = this.getParameter("img" + String.valueOf(n)); s != null && !s.equals(""); s = this.getParameter("img" + String.valueOf(n))) {
            String nextToken = "";
            String nextToken2 = "";
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            mediaTracker.addImage(this.mIMAGEX = this.getImage(this.getDocumentBase(), stringTokenizer.nextToken()), 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                System.out.println("Text Applet, module sbReadParams() error : " + ex.toString());
            }
            final Image image = this.createImage(this.mIMAGEX.getWidth(this), this.mIMAGEX.getHeight(this));
            image.getGraphics().drawImage(this.mIMAGEX, 0, 0, this);
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
            }
            this.mImages.addElement(new X_Img(image, nextToken, nextToken2));
            ++n;
        }
        final String parameter5 = this.getParameter("imgb");
        if (parameter5 != null && !parameter5.equalsIgnoreCase("")) {
            mediaTracker.addImage(this.mIMAGEX = this.getImage(this.getDocumentBase(), parameter5), 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex2) {
                System.out.println("Text Applet, module sbReadParams() error : " + ex2.toString());
            }
            this.mBackGroundImage = this.createImage(this.mIMAGEX.getWidth(this), this.mIMAGEX.getHeight(this));
            (this.mBackGroundGraphics = this.mBackGroundImage.getGraphics()).drawImage(this.mIMAGEX, 0, 0, this);
        }
        this.showStatus("");
    }
}
