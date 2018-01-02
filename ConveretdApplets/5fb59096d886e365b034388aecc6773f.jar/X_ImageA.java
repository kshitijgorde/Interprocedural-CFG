import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class X_ImageA extends Applet implements Runnable
{
    int mWAIT_TIME;
    int mTOTALTRANSITIONCOUNT;
    int mWAIT_TIME_BETWEEN_TRANSITIONS;
    int mANIM_TYPE;
    int mANIM_TYPE_DETAIL1;
    int mANIM_TYPE_DETAIL2;
    Thread mThread;
    Image mIMAGE1;
    Image mIMAGE2;
    Image mIMAGEX;
    Vector mImages;
    int mCurrent_Image_Index;
    X_Img mCurrent_Image;
    Graphics mGraphics1;
    Graphics mGraphics2;
    Graphics mGraphicsX;
    int mApplet_Width;
    int mApplet_Height;
    int mLoop_Counter;
    short mPart;
    int mCurrentTransition;
    
    private void CenterTOOuter_Animation() {
        int lcX = 0;
        int lcY = 0;
        lcX = (short)(this.mApplet_Width / 2 - this.mCurrentTransition * this.mApplet_Width / this.mTOTALTRANSITIONCOUNT);
        lcY = (short)(this.mApplet_Height / 2 - this.mCurrentTransition * this.mApplet_Height / this.mTOTALTRANSITIONCOUNT);
        this.mGraphicsX.setClip(lcX, lcY, (this.mApplet_Width / 2 - lcX) * 2, (this.mApplet_Height / 2 - lcY) * 2);
        this.mGraphicsX.drawImage(this.mCurrent_Image.Img, 0, 0, this);
    }
    
    public void stop() {
        try {
            this.mThread.stop();
            this.mThread = null;
        }
        catch (Exception e) {
            System.out.println("Text Applet, module stop() error : " + e.toString());
        }
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        this.setCursor(new Cursor(12));
        return true;
    }
    
    private void Random_Animation() {
        final int lcWidth = this.mPart;
        final int lcHeight = this.mPart;
        int lcX = (short)(Math.random() * (this.mApplet_Width - this.mPart));
        lcX = ((lcX > 0) ? lcX : (-1 * lcX));
        int lcY = (short)(Math.random() * (this.mApplet_Height - this.mPart));
        lcY = ((lcY > 0) ? lcY : (-1 * lcY));
        this.mGraphicsX.drawImage(this.mCurrent_Image.Img, lcX, lcY, lcX + lcWidth, lcY + lcHeight, lcX, lcY, lcX + lcWidth, lcY + lcHeight, Color.blue, this);
    }
    
    private void LeftToRight_Animation(final short argDirection1, final short argDirection2) {
        int lcX = 0;
        int lcY = 0;
        if (argDirection1 == 0) {
            lcX = (short)(this.mApplet_Width * (this.mTOTALTRANSITIONCOUNT - this.mCurrentTransition) / this.mTOTALTRANSITIONCOUNT);
        }
        else if (argDirection1 == 1) {
            lcX = (short)(this.mApplet_Width * this.mCurrentTransition / this.mTOTALTRANSITIONCOUNT) - this.mApplet_Width;
        }
        if (argDirection2 == 0) {
            lcY = (short)(this.mApplet_Height * (this.mTOTALTRANSITIONCOUNT - this.mCurrentTransition) / this.mTOTALTRANSITIONCOUNT);
        }
        else if (argDirection2 == 1) {
            lcY = (short)(this.mApplet_Height * this.mCurrentTransition / this.mTOTALTRANSITIONCOUNT) - this.mApplet_Height;
        }
        this.mGraphicsX.drawImage(this.mCurrent_Image.Img, lcX, lcY, this);
    }
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        this.setCursor(new Cursor(0));
        return true;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.mIMAGEX, 0, 0, this);
    }
    
    public X_ImageA() {
        this.mWAIT_TIME = 25;
        this.mTOTALTRANSITIONCOUNT = 50;
        this.mWAIT_TIME_BETWEEN_TRANSITIONS = 3000;
        this.mANIM_TYPE = 2;
        this.mANIM_TYPE_DETAIL1 = 1;
        this.mANIM_TYPE_DETAIL2 = 0;
        this.mCurrent_Image_Index = 1;
        this.mPart = 3;
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        this.showURL(this.mCurrent_Image.Link, this.mCurrent_Image.TargetFrame);
        return true;
    }
    
    public void destroy() {
        try {
            System.gc();
        }
        catch (Exception e) {
            System.out.println("error on destroy() -> " + e.toString());
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void start() {
        try {
            (this.mThread = new Thread(this)).setPriority(1);
            this.mThread.start();
        }
        catch (Exception e) {
            System.out.println("Text Applet, module start() error : " + e.toString());
        }
    }
    
    public String getAppletInfo() {
        return "X_ImageA version 1.0\r\nCopyright 2000 by Adem AKKAYA (akkayaa@deja.com)";
    }
    
    public void showURL(final String argURL, final String argTargetFrame) {
        String Path;
        if (argURL.startsWith("http") || argURL.startsWith("mail")) {
            Path = "";
        }
        else {
            Path = "" + this.getDocumentBase();
            if (!Path.substring(Path.length() - 1).equals("/")) {
                Path = Path.substring(0, Path.lastIndexOf("/"));
                Path += "/";
            }
        }
        URL url;
        try {
            url = new URL(Path + argURL);
        }
        catch (Exception e) {
            this.showStatus("ShowURL Exception");
            return;
        }
        this.getAppletContext().showDocument(url, argTargetFrame);
    }
    
    public void run() {
        while (true) {
            if (this.mCurrentTransition++ >= this.mTOTALTRANSITIONCOUNT) {
                this.mCurrentTransition = 0;
                this.mGraphicsX.drawImage(this.mCurrent_Image.Img, 0, 0, this);
                this.mCurrent_Image_Index = ++this.mCurrent_Image_Index % this.mImages.size();
                this.mCurrent_Image = this.mImages.elementAt(this.mCurrent_Image_Index);
                this.mGraphicsX.setClip(0, 0, this.mApplet_Width, this.mApplet_Height);
                this.repaint();
                try {
                    Thread.sleep(this.mWAIT_TIME_BETWEEN_TRANSITIONS);
                }
                catch (Exception e) {
                    System.out.println("Text Applet, module run() error : " + e.toString());
                }
            }
            if (this.mANIM_TYPE == 0) {
                this.Random_Animation();
            }
            else if (this.mANIM_TYPE == 1) {
                this.LeftToRight_Animation((short)this.mANIM_TYPE_DETAIL1, (short)this.mANIM_TYPE_DETAIL2);
            }
            else if (this.mANIM_TYPE == 2) {
                this.CenterTOOuter_Animation();
            }
            this.repaint();
            try {
                Thread.sleep(this.mWAIT_TIME);
            }
            catch (Exception e) {
                System.out.println("Text Applet, module run() error : " + e.toString());
            }
        }
    }
    
    public void init() {
        this.sbReadParams();
        (this.mGraphicsX = this.mIMAGEX.getGraphics()).drawImage(this.mIMAGE1, 0, 0, this);
        this.mApplet_Width = this.mIMAGE1.getWidth(this);
        this.mApplet_Height = this.mIMAGE1.getHeight(this);
        this.resize(this.mApplet_Width, this.mApplet_Height);
    }
    
    private Color stringToColor(final String argParamValue) {
        final int lcRed = Integer.decode("0x" + argParamValue.substring(0, 2));
        final int lcGreen = Integer.decode("0x" + argParamValue.substring(2, 4));
        final int lcBlue = Integer.decode("0x" + argParamValue.substring(4, 6));
        return new Color(lcRed, lcGreen, lcBlue);
    }
    
    private void sbReadParams() {
        final MediaTracker lcTracker = new MediaTracker(this);
        this.showStatus("Reading params ...");
        String lcTempStr = this.getParameter("WAIT_TIME");
        if (lcTempStr != null && !lcTempStr.equals("")) {
            this.mWAIT_TIME = Integer.parseInt(lcTempStr);
        }
        lcTempStr = this.getParameter("WAIT_TIME_BETWEEN_TRANSITIONS");
        if (lcTempStr != null && !lcTempStr.equals("")) {
            this.mWAIT_TIME_BETWEEN_TRANSITIONS = Integer.parseInt(lcTempStr);
        }
        lcTempStr = this.getParameter("ANIM_TYPE");
        if (lcTempStr != null && !lcTempStr.equals("")) {
            this.mANIM_TYPE = (short)Integer.parseInt(lcTempStr);
        }
        lcTempStr = this.getParameter("ANIM_TYPE_DETAIL1");
        if (lcTempStr != null && !lcTempStr.equals("")) {
            this.mANIM_TYPE_DETAIL1 = (short)Integer.parseInt(lcTempStr);
        }
        lcTempStr = this.getParameter("ANIM_TYPE_DETAIL2");
        if (lcTempStr != null && !lcTempStr.equals("")) {
            this.mANIM_TYPE_DETAIL2 = (short)Integer.parseInt(lcTempStr);
        }
        lcTempStr = this.getParameter("TOTALTRANSITIONCOUNT");
        if (lcTempStr != null && !lcTempStr.equals("")) {
            this.mTOTALTRANSITIONCOUNT = (short)Integer.parseInt(lcTempStr);
        }
        int lcIndex = 0;
        this.mImages = new Vector(1, 1);
        for (lcTempStr = this.getParameter("img" + String.valueOf(lcIndex)); lcTempStr != null && !lcTempStr.equals(""); lcTempStr = this.getParameter("img" + String.valueOf(lcIndex))) {
            final StringTokenizer lcTok = new StringTokenizer(lcTempStr, ",");
            final String lcDummy = lcTok.nextToken();
            lcTracker.addImage(this.mIMAGEX = this.getImage(this.getDocumentBase(), lcDummy), 0);
            try {
                lcTracker.waitForID(0);
            }
            catch (Exception e) {
                System.out.println("Text Applet, module sbReadParams() error : " + e.toString());
            }
            final Image lcImage = this.createImage(this.mIMAGEX.getWidth(this), this.mIMAGEX.getHeight(this));
            lcImage.getGraphics().drawImage(this.mIMAGEX, 0, 0, this);
            final String lcStatus = lcTok.nextToken();
            final String lcLink = lcTok.nextToken();
            final String lcTargetFrame = lcTok.nextToken();
            this.mImages.addElement(new X_Img(lcImage, lcStatus, lcLink, lcTargetFrame));
            ++lcIndex;
        }
        this.mIMAGE1 = this.mImages.elementAt(0).Img;
        this.mIMAGE2 = this.mImages.elementAt(1).Img;
        this.mIMAGEX = this.createImage(this.mIMAGEX.getWidth(this), this.mIMAGEX.getHeight(this));
        (this.mGraphicsX = this.mIMAGEX.getGraphics()).drawImage(this.mIMAGE1, 0, 0, this);
        this.mCurrent_Image_Index = 1;
        this.mCurrent_Image = this.mImages.elementAt(this.mCurrent_Image_Index);
        this.showStatus("");
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.showStatus(this.mCurrent_Image.Status);
        return true;
    }
}
