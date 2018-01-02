import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.Event;
import java.net.URL;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DJClock extends Applet implements Runnable
{
    int frameNumber;
    int iTemp;
    int delay;
    public int showYear;
    public int ampm;
    public int sepDT;
    public int iMsgTxt;
    public int showColon;
    public int showSec;
    public int showDate;
    public int txtTime;
    public int secdiff;
    public int mindiff;
    public int hourdiff;
    public int halfhr;
    public int gmtoffset;
    public int r;
    public int dsize;
    public int tsize;
    public int msize;
    public int dcol;
    public int tcol;
    public int mcol;
    public int dsty;
    public int tsty;
    public int msty;
    public int bgcol;
    public int bgsty;
    public int wkdayFmt;
    public int monthFmt;
    public int tYPos;
    public int dYPos;
    public int mYPos;
    public int bwidth;
    public int blcol;
    public int bdcol;
    public int btype;
    MediaTracker tracker1;
    Thread animatorThread;
    boolean frozen;
    Dimension offDimension;
    public Image offImage;
    public Image face;
    Graphics offGraphics;
    public Date now;
    Image candyimage;
    Image bgImage;
    public String str;
    public String str2;
    public String str3;
    public String strImg;
    public String Tmp;
    public String Msg;
    public String MsgTime;
    public String dFont;
    public String tFont;
    public String mFont;
    public String bgImgFile;
    String[] wkday;
    String[] month;
    URL imageURL;
    Image[] ImgArray;
    int fontcount;
    int imgcnt;
    
    public DJClock() {
        this.frameNumber = -1;
        this.delay = 1000;
        this.showYear = 2;
        this.sepDT = 1;
        this.iMsgTxt = 3;
        this.showColon = 1;
        this.showSec = 1;
        this.tYPos = -50;
        this.dYPos = -50;
        this.mYPos = -50;
        this.bwidth = 4;
        this.str = "";
        this.str2 = "";
        this.str3 = "";
        this.strImg = "";
        this.Tmp = "";
        this.Msg = "";
        this.MsgTime = "";
        this.dFont = "";
        this.tFont = "";
        this.mFont = "";
        this.bgImgFile = "";
        this.wkday = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        this.month = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
    }
    
    public void start() {
        if (!this.frozen) {
            if (this.animatorThread == null) {
                this.animatorThread = new Thread(this);
            }
            this.animatorThread.start();
        }
    }
    
    public void stop() {
        this.animatorThread = null;
        this.offGraphics = null;
        this.offImage = null;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        if (this.frozen) {
            this.frozen = false;
            this.start();
        }
        else {
            this.frozen = true;
            this.animatorThread = null;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        this.showStatus("Copyright © 2000 Naeem Malik. All Rights Reserved");
        return true;
    }
    
    public String getAppletInfo() {
        return "Name: DJClock v1.1 \r\n" + "Author: Naeem Malik\r\n" + "Copyright (c). All rights reserved.";
    }
    
    public void run() {
        try {
            this.tracker1.waitForAll();
        }
        catch (InterruptedException ex) {}
        long startTime = System.currentTimeMillis();
        while (Thread.currentThread() == this.animatorThread) {
            ++this.frameNumber;
            this.repaint();
            try {
                startTime += this.delay;
                Thread.sleep(Math.max(0L, startTime - System.currentTimeMillis()));
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
    
    public void destroy() {
    }
    
    public void init() {
        this.imageURL = this.getCodeBase();
        final String parameter = this.getParameter("showdate");
        this.Tmp = parameter;
        if (parameter != null) {
            this.showDate = 1;
        }
        if ((this.Tmp = this.getParameter("showampm")) != null) {
            this.ampm = 1;
        }
        if ((this.Tmp = this.getParameter("UseTextTime")) != null) {
            this.txtTime = 1;
        }
        if ((this.Tmp = this.getParameter("onelinedatetime")) != null) {
            this.sepDT = 0;
        }
        if ((this.Tmp = this.getParameter("HideColon")) != null) {
            this.showColon = 0;
        }
        if ((this.Tmp = this.getParameter("HideSeconds")) != null) {
            this.showSec = 0;
        }
        this.dFont = this.getParameter("DateFont");
        this.tFont = this.getParameter("TimeFont");
        this.mFont = this.getParameter("MsgFont");
        this.Msg = this.getParameter("Message");
        this.bgImgFile = this.getParameter("BgImage");
        if ((this.Tmp = this.getParameter("yearformat")) != null) {
            this.showYear = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("TimeYPos")) != null) {
            this.tYPos = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("DateYPos")) != null) {
            this.dYPos = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("MsgYPos")) != null) {
            this.mYPos = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("TimeFontSize")) != null) {
            this.tsize = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("DateFontSize")) != null) {
            this.dsize = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("MsgFontSize")) != null) {
            this.msize = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("TimeFontColor")) != null) {
            this.tcol = Integer.parseInt(this.Tmp, 16);
        }
        if ((this.Tmp = this.getParameter("DateFontColor")) != null) {
            this.dcol = Integer.parseInt(this.Tmp, 16);
        }
        if ((this.Tmp = this.getParameter("MsgFontColor")) != null) {
            this.mcol = Integer.parseInt(this.Tmp, 16);
        }
        if ((this.Tmp = this.getParameter("BgColor")) != null) {
            this.bgcol = Integer.parseInt(this.Tmp, 16);
        }
        if ((this.Tmp = this.getParameter("DateFontStyle")) != null) {
            if (this.Tmp.indexOf("b") != -1) {
                this.dsty |= 0x1;
            }
            if (this.Tmp.indexOf("i") != -1) {
                this.dsty |= 0x2;
            }
        }
        if ((this.Tmp = this.getParameter("TimeFontStyle")) != null) {
            if (this.Tmp.indexOf("b") != -1) {
                this.dsty |= 0x1;
            }
            if (this.Tmp.indexOf("i") != -1) {
                this.dsty |= 0x2;
            }
        }
        if ((this.Tmp = this.getParameter("MsgFontStyle")) != null) {
            if (this.Tmp.indexOf("b") != -1) {
                this.dsty |= 0x1;
            }
            if (this.Tmp.indexOf("i") != -1) {
                this.dsty |= 0x2;
            }
        }
        if ((this.Tmp = this.getParameter("WeekDayFormat")) != null) {
            this.wkdayFmt = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("MonthFormat")) != null) {
            this.monthFmt = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("MsgLocation")) != null) {
            this.iMsgTxt = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("BorderDarkColor")) != null) {
            this.bdcol = Integer.parseInt(this.Tmp, 16);
        }
        if ((this.Tmp = this.getParameter("BorderLightColor")) != null) {
            this.blcol = Integer.parseInt(this.Tmp, 16);
        }
        if ((this.Tmp = this.getParameter("BorderWidth")) != null) {
            this.bwidth = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("BorderStyle")) != null) {
            this.btype = Integer.parseInt(this.Tmp);
        }
        if ((this.Tmp = this.getParameter("BGStyle")) != null) {
            this.bgsty = Integer.parseInt(this.Tmp);
        }
        this.str = this.getParameter("imageurl");
        try {
            if (this.str != null) {
                this.imageURL = new URL(this.str);
            }
        }
        catch (MalformedURLException ex) {}
        this.tracker1 = new MediaTracker(this);
        if (this.txtTime != 1) {
            this.face = this.getImage(this.imageURL, "digits.gif");
            this.tracker1.addImage(this.face, 0);
        }
        if (this.bgImgFile != null && this.bgImgFile.length() != 0) {
            this.bgImage = this.getImage(this.imageURL, this.bgImgFile);
            this.tracker1.addImage(this.bgImage, 1);
        }
        this.str = this.getParameter("gmtoffset");
        try {
            final URL myurl = new URL(this.getParameter("serverfile"));
            final URLConnection myConnection = myurl.openConnection();
            myConnection.setUseCaches(false);
            this.str3 = myConnection.getHeaderField("Date");
            System.out.print("str3:" + this.str3);
        }
        catch (IllegalArgumentException ee) {}
        catch (IOException ee) {}
        catch (Exception ex2) {}
        try {
            if (this.str != null) {
                this.gmtoffset = Integer.parseInt(this.str);
                final Date myDate = new Date();
                if (this.str3 != null && this.str3.length() != 0) {
                    final Date serverDate = new Date(this.str3);
                    this.iTemp = this.str3.indexOf(58);
                    this.secdiff = Integer.parseInt(this.str3.substring(this.iTemp + 4, this.iTemp + 6)) - myDate.getSeconds();
                    this.mindiff = Integer.parseInt(this.str3.substring(this.iTemp + 1, this.iTemp + 3)) - myDate.getMinutes();
                    this.hourdiff = Integer.parseInt(this.str3.substring(this.iTemp - 2, this.iTemp)) - myDate.getHours();
                }
                else {
                    this.hourdiff -= myDate.getTimezoneOffset();
                }
                this.hourdiff += this.gmtoffset;
                if (this.gmtoffset >= 0) {
                    this.mindiff += this.halfhr;
                }
                else {
                    this.mindiff -= this.halfhr;
                }
            }
        }
        catch (Exception ee) {
            System.out.print(ee);
        }
        try {
            if (this.txtTime != 1) {
                final URL stripurl = new URL(this.imageURL.toString() + "digits.gif");
                final BufferedInputStream is = new BufferedInputStream(stripurl.openStream());
                while (true) {
                    int br = is.read();
                    if (br == 33) {
                        br = is.read();
                        if (br == 254) {
                            break;
                        }
                        continue;
                    }
                }
                int br = is.read();
                final byte[] b = new byte[255];
                is.read(b, 0, br);
                this.strImg = new String(b, -16777216, 0, br);
                System.out.println(this.strImg);
            }
        }
        catch (MalformedURLException ee) {}
        catch (IOException ex3) {}
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    public void update(final Graphics g) {
        final Dimension d = this.size();
        final int x = 0;
        final int y = 0;
        int ftheight = 0;
        final int w = d.width;
        final int h = d.height;
        int nYPOS = 0;
        if (this.offGraphics == null || d.width != this.offDimension.width || d.height != this.offDimension.height) {
            this.offDimension = d;
            this.offImage = this.createImage(w, h);
            this.offGraphics = this.offImage.getGraphics();
        }
        if (this.r == 0 && this.tracker1.checkID(0)) {
            ++this.r;
            if (this.txtTime != 1) {
                this.ImgArray = new Image[15];
                int ind = 0;
                final int iTemp = this.strImg.indexOf(":", 0);
                this.imgcnt = Integer.parseInt(this.strImg.substring(0, iTemp));
                System.out.println(String.valueOf(this.imgcnt));
                ind = this.strImg.indexOf(":", iTemp + 1);
                int imgst = Integer.parseInt(this.strImg.substring(iTemp + 1, ind));
                for (int i = 0; i < this.imgcnt; ++i) {
                    final int ini = ind;
                    ind = this.strImg.indexOf(":", ini + 1);
                    if (ind == -1) {
                        ind = this.strImg.length();
                    }
                    int p = 0;
                    p = Integer.parseInt(this.strImg.substring(ini + 1, ind));
                    this.ImgArray[i] = this.createImage(p - imgst, this.face.getHeight(this));
                    final Graphics tmpGraph = this.ImgArray[i].getGraphics();
                    tmpGraph.drawImage(this.face, -imgst, 0, this);
                    imgst = p;
                }
            }
        }
        final Color tmpclr = new Color(this.bgcol);
        this.offGraphics.setColor(tmpclr);
        this.offGraphics.fillRect(0, 0, w, h);
        if (this.bgImgFile != null && this.bgImgFile.length() != 0 && this.tracker1.checkID(1)) {
            if (this.bgsty == 1) {
                this.offGraphics.drawImage(this.bgImage, (w - this.bgImage.getWidth(this)) / 2, h - this.bgImage.getHeight(this), this);
            }
            else if (this.bgsty == 2) {
                this.offGraphics.drawImage(this.bgImage, 0, 0, w, h, this);
            }
            else {
                final int xT = w / this.bgImage.getWidth(this) + 1;
                final int yT = h / this.bgImage.getHeight(this) + 1;
                for (int pp = 0; pp < xT; ++pp) {
                    for (int qq = 0; qq < yT; ++qq) {
                        this.offGraphics.drawImage(this.bgImage, this.bgImage.getWidth(this) * pp, this.bgImage.getHeight(this) * qq, this);
                    }
                }
            }
        }
        if (this.str3 != null && this.str3.length() != 0) {
            final int gs = this.str3.indexOf("GMT");
            final StringBuffer sb = new StringBuffer(this.str3);
            int hs = 0;
            do {
                sb.setCharAt(gs + hs, ' ');
            } while (++hs < 3);
            final String strsb = sb.toString();
            this.now = new Date(strsb);
        }
        else {
            this.now = new Date();
        }
        final Date now2 = new Date();
        this.now.setSeconds(now2.getSeconds() + this.secdiff);
        this.now.setMinutes(now2.getMinutes() + this.mindiff);
        this.now.setHours(now2.getHours() + this.hourdiff);
        int minute = this.now.getMinutes();
        int hour = this.now.getHours();
        int second = this.now.getSeconds();
        if (minute >= 60) {
            minute -= 60;
        }
        if (hour >= 60) {
            hour -= 60;
        }
        if (second >= 60) {
            second -= 60;
        }
        int isampm = 0;
        String strClock = "";
        if (this.ampm == 1 && hour > 12) {
            hour -= 12;
            isampm = 1;
        }
        if (hour < 10) {
            strClock += "0";
        }
        strClock += hour;
        if (this.showColon == 1) {
            strClock += ":";
        }
        if (minute < 10) {
            strClock += "0";
        }
        strClock += minute;
        if (this.showColon == 1) {
            strClock += ":";
        }
        if (second < 10) {
            strClock += "0";
        }
        if (this.showSec == 1) {
            strClock += second;
        }
        if (this.ampm == 1) {
            if (isampm == 1) {
                strClock += "PM";
            }
            else {
                strClock += "AM";
            }
        }
        if (this.txtTime != 1 && this.r > 0) {
            final Image tmpImage = this.createImage(this.face.getWidth(this), this.face.getHeight(this));
            final Graphics tmpGraphics = tmpImage.getGraphics();
            tmpGraphics.setColor(tmpclr);
            tmpGraphics.fillRect(0, 0, w, h);
            int imgpl = 0;
            for (int j = 0; j < strClock.length(); ++j) {
                final char c = strClock.charAt(j);
                final int iTemp2 = Character.digit(c, 10);
                if (c == ':') {
                    if (this.imgcnt > 10) {
                        tmpGraphics.drawImage(this.ImgArray[10], imgpl, 0, this);
                        imgpl += this.ImgArray[10].getWidth(this);
                    }
                    else {
                        imgpl += this.ImgArray[0].getWidth(this) / 2;
                    }
                }
                else if (c == 'A' || c == 'P') {
                    if (this.imgcnt > 11) {
                        int ap;
                        if (c == 'P') {
                            ap = 12;
                        }
                        else {
                            ap = 11;
                        }
                        tmpGraphics.drawImage(this.ImgArray[ap], imgpl, 0, this);
                        imgpl += this.ImgArray[ap].getWidth(this);
                    }
                }
                else if (c != 'M') {
                    tmpGraphics.drawImage(this.ImgArray[iTemp2], imgpl, 0, this);
                    imgpl += this.ImgArray[iTemp2].getWidth(this);
                }
            }
            if (this.tYPos == -50) {
                if (this.showDate == 1 || this.iMsgTxt == 3) {
                    nYPOS = 10;
                }
                else {
                    nYPOS = (h - this.face.getHeight(this)) / 2;
                }
            }
            else {
                nYPOS = this.tYPos;
            }
            this.offGraphics.clipRect((w - imgpl) / 2, nYPOS, imgpl, this.face.getHeight(this));
            this.offGraphics.drawImage(tmpImage, (w - imgpl) / 2, nYPOS, this);
            this.offGraphics = this.offImage.getGraphics();
        }
        else if (this.txtTime == 1) {
            String timmy = "";
            if (this.iMsgTxt == 2) {
                timmy = this.Msg + " ";
            }
            timmy += strClock;
            final Color clr = new Color(this.tcol);
            this.offGraphics.setColor(clr);
            if (this.tFont != null) {
                final Font fnt = new Font(this.tFont, this.tsty, this.tsize);
                this.offGraphics.setFont(fnt);
            }
            final FontMetrics fm = this.offGraphics.getFontMetrics();
            final int fwidth = fm.stringWidth(timmy);
            ftheight = fm.getMaxAscent() + fm.getMaxDescent();
            if (this.sepDT == 1) {
                if (this.tYPos == -50) {
                    if (this.showDate == 1 || this.iMsgTxt == 3) {
                        nYPOS = 10 + ftheight;
                    }
                    else {
                        nYPOS = (h - ftheight) / 2 + ftheight;
                    }
                }
                else {
                    nYPOS = this.tYPos;
                }
                this.offGraphics.drawString(timmy, (w - fwidth) / 2, nYPOS);
            }
        }
        if (this.showDate == 1) {
            String datty = "";
            if (this.iMsgTxt == 1) {
                datty = this.Msg + " ";
            }
            if (this.wkdayFmt < 4) {
                datty += this.wkday[this.now.getDay()].substring(0, this.wkdayFmt);
            }
            else {
                datty += this.wkday[this.now.getDay()];
            }
            if (this.wkdayFmt != 0) {
                datty += " ";
            }
            String gm = (this.now.getMonth() + 1 < 10) ? "0" : "";
            gm += this.now.getMonth() + 1;
            if (this.monthFmt == -1) {
                datty = datty + gm + "/" + ((this.now.getDate() < 10) ? "0" : "") + this.now.getDate();
            }
            else if (this.monthFmt == 0) {
                datty = datty + ((this.now.getDate() < 10) ? "0" : "") + this.now.getDate() + "/" + gm;
            }
            else {
                datty = datty + this.now.getDate() + " ";
                if (this.monthFmt == 3) {
                    datty += this.month[this.now.getMonth()].substring(0, 3);
                }
                else {
                    datty += this.month[this.now.getMonth()];
                }
            }
            if (this.showYear != 0) {
                if (this.monthFmt != 0 && this.monthFmt != -1) {
                    datty += " ";
                }
                else {
                    datty += "/";
                }
                final int yr = this.now.getYear() + 1900;
                if (this.showYear == 4) {
                    datty += yr;
                }
                else {
                    datty += String.valueOf(yr).substring(2);
                }
            }
            if (this.sepDT == 0) {
                datty = datty + " " + strClock;
            }
            final Color clr2 = new Color(this.dcol);
            this.offGraphics.setColor(clr2);
            if (this.dFont != null) {
                final Font fnt2 = new Font(this.dFont, this.dsty, this.dsize);
                this.offGraphics.setFont(fnt2);
            }
            final FontMetrics fm2 = this.offGraphics.getFontMetrics();
            final int fwidth2 = fm2.stringWidth(datty);
            final int fheight = fm2.getMaxAscent() + fm2.getMaxDescent() + 5;
            if (this.dYPos == -50) {
                if (this.tYPos == -50) {
                    nYPOS = 10 + this.face.getHeight(this) + fheight + ftheight;
                }
                else {
                    nYPOS = this.tYPos + fheight;
                }
            }
            else {
                nYPOS = this.dYPos;
            }
            this.offGraphics.drawString(datty, (w - fwidth2) / 2, nYPOS);
        }
        if (this.iMsgTxt == 3) {
            final Color clr3 = new Color(this.mcol);
            this.offGraphics.setColor(clr3);
            if (this.mFont != null) {
                final Font fnt3 = new Font(this.mFont, this.msty, this.msize);
                this.offGraphics.setFont(fnt3);
            }
            final FontMetrics fm3 = this.offGraphics.getFontMetrics();
            final int fwidth3 = fm3.stringWidth(this.Msg);
            this.offGraphics.drawString(this.Msg, (w - fwidth3) / 2, this.mYPos);
        }
        int bs = 0;
        if (this.btype == 0) {
            bs = this.bwidth / 2;
        }
        else if (this.btype == 1) {
            bs = this.bwidth + 1;
        }
        else {
            bs = this.bwidth / 2;
        }
        final Color r = new Color(this.blcol);
        final Color gr = new Color(this.bdcol);
        for (int j = 0; j < this.bwidth; ++j) {
            if (j < bs) {
                this.offGraphics.setColor(r);
            }
            else {
                this.offGraphics.setColor(gr);
            }
            this.offGraphics.drawLine(j, h - 1 - j, w - 1 - j, h - 1 - j);
            this.offGraphics.drawLine(w - 1 - j, j, w - 1 - j, h - 1 - j);
            if (j < bs) {
                if (this.btype == 2) {
                    this.offGraphics.setColor(r);
                }
                else {
                    this.offGraphics.setColor(gr);
                }
            }
            else if (this.btype == 2) {
                this.offGraphics.setColor(gr);
            }
            else {
                this.offGraphics.setColor(r);
            }
            this.offGraphics.drawLine(j, j, w - 1 - j, j);
            this.offGraphics.drawLine(j, j, j, h - 1 - j);
        }
        g.drawImage(this.offImage, 0, 0, this);
    }
}
