import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.UnknownHostException;
import java.awt.Color;
import java.util.Date;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.net.InetAddress;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mpanel2 extends Applet implements Runnable
{
    int TRUE;
    int FALSE;
    int locationError;
    InetAddress i;
    InetAddress dummy;
    int domainThread;
    int domainOk;
    String domainName;
    String ipAddress;
    String tempDomain;
    String domain1;
    String domain2;
    int LINES;
    int update_ms;
    Thread myThread;
    Image offScrImage;
    Graphics offScrGC;
    int text_lines;
    int copyrights;
    int msgState;
    int msgNumber;
    int date_update;
    int frameWidth;
    int appletWidth;
    int appletHeight;
    int msgWidth;
    int msgHeight;
    int bold;
    int italic;
    int font_accent;
    int urlState;
    int width;
    FontMetrics fm;
    Font msgFont;
    String DateMsg;
    String urlStr;
    String s;
    String num;
    Date date;
    int oldSeconds;
    int def_dly;
    Color def_msgColor;
    Color def_bgColor;
    Color def_upFrameColor;
    Color def_lowFrameColor;
    int[] msgLen;
    int[] msgPos;
    int[] dly;
    String[] msgText;
    Color[] msgColor;
    Color[] bgColor;
    Color[] upFrameColor;
    Color[] lowFrameColor;
    
    public void init() {
        try {
            this.domainThread = this.TRUE;
            this.domainOk = this.FALSE;
            this.urlState = this.FALSE;
            this.msgState = 1;
            this.msgNumber = 0;
            this.update_ms = 350;
            this.date_update = 0;
            this.appletWidth = this.size().width;
            if (this.appletWidth < 5 || this.appletWidth > 1024) {
                this.appletWidth = 520;
            }
            this.appletHeight = this.size().height;
            if (this.appletHeight < 5 || this.appletHeight > 768) {
                this.appletHeight = 34;
            }
            if (this.offScrImage == null) {
                this.offScrImage = this.createImage(this.appletWidth, this.appletHeight);
            }
            if (this.offScrGC == null) {
                this.offScrGC = this.offScrImage.getGraphics();
            }
            this.offScrGC.setColor(Color.black);
            this.offScrGC.fillRect(0, 0, this.appletWidth, this.appletHeight);
            this.copyrights = this.FALSE;
            this.s = this.getParameter("copyright1");
            if (this.s != null && (this.s.compareTo("Adeveloper Copyright (c) 1999") == 0 || this.s.compareTo("Adeveloper Copyright (c) 1998") == 0 || this.s.compareTo("Adeveloper Copyright (c) 1997") == 0)) {
                this.s = this.getParameter("copyright2");
                if (this.s != null) {
                    if (this.s.compareTo("http://www.Adeveloper.com/") == 0) {
                        this.copyrights = this.TRUE;
                    }
                    else if (this.s.compareTo("http://www.adeveloper.com/") == 0) {
                        this.copyrights = this.TRUE;
                    }
                }
            }
            if (this.copyrights == this.TRUE) {
                this.s = this.getParameter("url");
                if (this.s != null) {
                    if (this.s.length() > 3) {
                        this.urlStr = this.s;
                        this.urlState = this.TRUE;
                    }
                    else {
                        this.urlStr = " ";
                        this.urlState = this.FALSE;
                    }
                }
                else {
                    this.urlStr = " ";
                    this.urlState = this.FALSE;
                }
            }
            else {
                this.urlStr = "http://www.Adeveloper.com/";
                this.urlState = this.TRUE;
            }
            this.s = this.getParameter("frame");
            if (this.s != null) {
                this.frameWidth = Integer.parseInt(this.s);
            }
            else {
                this.frameWidth = 0;
            }
            this.s = this.getParameter("bold");
            if (this.s != null) {
                this.bold = Integer.parseInt(this.s);
            }
            else {
                this.bold = 0;
            }
            this.s = this.getParameter("italic");
            if (this.s != null) {
                this.italic = Integer.parseInt(this.s);
            }
            else {
                this.italic = 0;
            }
            this.font_accent = 0;
            if (this.bold == 1) {
                this.font_accent |= 0x1;
            }
            if (this.italic == 1) {
                this.font_accent |= 0x2;
            }
            this.msgFont = new Font("System", this.font_accent, this.appletHeight - this.frameWidth * 2 - this.appletHeight / 6);
            this.offScrGC.setFont(this.msgFont);
            this.fm = this.offScrGC.getFontMetrics();
            this.msgHeight = (this.appletHeight + this.fm.getMaxAscent() - 4) / 2;
            this.s = this.getParameter("def_dly");
            if (this.s != null) {
                this.def_dly = Integer.parseInt(this.s);
            }
            else {
                this.def_dly = 2500;
            }
            this.s = this.getParameter("def_msgCol");
            if (this.s != null) {
                this.def_msgColor = this.GetColor(this.s, Color.white);
            }
            else {
                this.def_msgColor = Color.white;
            }
            this.s = this.getParameter("def_bgCol");
            if (this.s != null && this.copyrights == this.TRUE) {
                this.def_bgColor = this.GetColor(this.s, Color.black);
            }
            else {
                this.def_bgColor = Color.black;
            }
            this.s = this.getParameter("def_upFrCol");
            if (this.s != null) {
                this.def_upFrameColor = this.GetColor(this.s, Color.white);
            }
            else {
                this.def_upFrameColor = Color.white;
            }
            this.s = this.getParameter("def_lowFrCol");
            if (this.s != null) {
                this.def_lowFrameColor = this.GetColor(this.s, Color.gray);
            }
            else {
                this.def_lowFrameColor = Color.gray;
            }
            for (int i = 0; i < this.LINES; ++i) {
                this.msgLen[i] = 0;
                this.msgPos[i] = 0;
                this.msgText[i] = "";
                this.dly[i] = this.def_dly;
                this.msgColor[i] = this.def_msgColor;
                this.bgColor[i] = this.def_bgColor;
                this.upFrameColor[i] = this.def_upFrameColor;
                this.lowFrameColor[i] = this.def_lowFrameColor;
            }
            if (this.copyrights == this.TRUE) {
                this.s = this.getParameter("lines");
                if (this.s != null) {
                    this.text_lines = Integer.parseInt(this.s);
                }
                else {
                    this.text_lines = 0;
                }
                if (this.text_lines > this.LINES) {
                    this.text_lines = 0;
                }
                if (this.text_lines > 0 && this.text_lines <= this.LINES) {
                    for (int j = 0; j < this.text_lines; ++j) {
                        final int n = j + 1;
                        if (n < 10) {
                            this.num = String.valueOf(n);
                        }
                        else {
                            this.num = n / 10 + "" + (n - n / 10 * 10);
                        }
                        this.s = this.getParameter("msg_" + this.num);
                        if (this.s != null) {
                            this.msgText[j] = this.s;
                        }
                        this.s = this.getParameter("dly_" + this.num);
                        if (this.s != null) {
                            this.dly[j] = Integer.parseInt(this.s);
                        }
                        this.s = this.getParameter("msgCol_" + this.num);
                        if (this.s != null) {
                            this.msgColor[j] = this.GetColor(this.s, this.def_msgColor);
                        }
                        this.s = this.getParameter("bgCol_" + this.num);
                        if (this.s != null) {
                            this.bgColor[j] = this.GetColor(this.s, this.def_bgColor);
                        }
                        this.s = this.getParameter("upFrCol_" + this.num);
                        if (this.s != null) {
                            this.upFrameColor[j] = this.GetColor(this.s, this.def_upFrameColor);
                        }
                        this.s = this.getParameter("lowFrCol_" + this.num);
                        if (this.s != null) {
                            this.lowFrameColor[j] = this.GetColor(this.s, this.def_lowFrameColor);
                        }
                    }
                }
            }
            if (this.copyrights == this.FALSE) {
                this.text_lines = 9;
                this.msgText[0] = "Adeveloper.com";
                this.msgColor[0] = Color.white;
                this.bgColor[0] = Color.blue;
                this.dly[0] = 2500;
                this.msgText[1] = "Free Java Message Panel applet";
                this.msgColor[1] = Color.red;
                this.dly[1] = 2500;
                this.msgText[2] = "Version 2.0";
                this.msgColor[2] = Color.white;
                this.dly[2] = 1200;
                this.msgText[3] = "Copyright (c) 1999, 1998, 1997";
                this.msgColor[3] = Color.gray;
                this.dly[3] = 1400;
                this.msgText[4] = "domain";
                this.msgColor[4] = Color.blue;
                this.dly[4] = 1300;
                this.msgText[5] = "ip";
                this.msgColor[5] = Color.white;
                this.dly[5] = 1300;
                this.msgText[6] = "date";
                this.msgColor[6] = Color.yellow;
                this.dly[6] = 4000;
                this.msgText[7] = "Get your Message Panel";
                this.msgColor[7] = Color.cyan;
                this.dly[7] = 2000;
                this.msgText[8] = "CLICK ON THIS PANEL!";
                this.msgColor[8] = Color.white;
                this.dly[8] = 2200;
            }
            this.domainOk = this.FALSE;
            this.domainThread = this.TRUE;
            (this.myThread = new Thread(this)).start();
            this.visitor();
            this.myThread.stop();
            this.myThread = null;
            this.domainThread = this.FALSE;
            for (int k = 0; k < this.text_lines; ++k) {
                this.msgLen[k] = this.msgText[k].length();
                if (this.msgLen[k] > 0) {
                    if (this.msgText[k].compareTo("domain") == 0) {
                        if (this.getDomainName().length() > 0) {
                            this.msgText[k] = new String(this.getDomainName());
                        }
                        else {
                            this.msgText[k] = new String(" ");
                        }
                    }
                    else if (this.msgText[k].compareTo("ip") == 0) {
                        if (this.getIPAddress().length() > 0) {
                            this.msgText[k] = new String(this.getIPAddress());
                        }
                        else {
                            this.msgText[k] = new String(" ");
                        }
                    }
                }
                else {
                    this.msgText[k] = new String(" ");
                }
                this.msgLen[k] = this.msgText[k].length();
                this.width = this.fm.stringWidth(this.msgText[k]);
                this.msgPos[k] = (this.appletWidth - this.width) / 2;
            }
        }
        catch (RuntimeException ex) {
            this.msgText[0] = "Init() RuntimeException";
            this.msgText[1] = ex.getMessage();
            this.MessageError();
        }
    }
    
    public void start() {
        if (this.myThread == null) {
            this.msgState = 1;
            this.msgNumber = 0;
            (this.myThread = new Thread(this)).start();
        }
        this.repaint();
    }
    
    public void stop() {
        if (this.myThread != null) {
            this.update_ms = 500;
            this.date_update = 0;
            this.msgState = 1;
            this.msgNumber = 0;
            this.myThread.stop();
            this.myThread = null;
        }
    }
    
    public void run() {
        while (this.myThread != null) {
            try {
                if (this.domainThread == this.TRUE) {
                    try {
                        if (this.domainOk == this.FALSE) {
                            this.i = InetAddress.getLocalHost();
                        }
                    }
                    catch (UnknownHostException ex3) {}
                    catch (RuntimeException ex4) {}
                }
                else {
                    switch (this.msgState) {
                        case 1: {
                            if (this.msgLen[this.msgNumber] > 0 && this.msgNumber < this.text_lines) {
                                if (this.msgText[this.msgNumber].compareTo("date") != 0) {
                                    this.update_ms = this.dly[this.msgNumber];
                                    this.DrawMessage(this.msgText[this.msgNumber]);
                                }
                                else {
                                    this.date_update = this.dly[this.msgNumber];
                                    this.update_ms = 200;
                                    this.msgState = 2;
                                    this.date = new Date();
                                    this.oldSeconds = this.date.getSeconds();
                                    this.GetDateString();
                                    this.msgPos[this.msgNumber] = (this.appletWidth - this.fm.stringWidth(this.DateMsg)) / 2;
                                    this.DrawMessage(this.DateMsg);
                                    this.msgState = 2;
                                }
                                this.DrawFrame();
                                this.repaint();
                                break;
                            }
                            this.msgNumber = 0;
                            this.update_ms = 50;
                            break;
                        }
                        case 2: {
                            if (this.msgText[this.msgNumber].compareTo("date") != 0) {
                                this.msgNumber = 0;
                                this.msgState = 0;
                                this.update_ms = 50;
                                break;
                            }
                            this.date = new Date();
                            final int seconds = this.date.getSeconds();
                            if (seconds != this.oldSeconds) {
                                this.oldSeconds = seconds;
                                this.GetDateString();
                                this.msgPos[this.msgNumber] = (this.appletWidth - this.fm.stringWidth(this.DateMsg)) / 2;
                                this.DrawMessage(this.DateMsg);
                                this.DrawFrame();
                                this.repaint();
                            }
                            this.date_update -= this.update_ms;
                            if (this.date_update <= 0) {
                                this.date_update = 0;
                                this.msgState = 1;
                                break;
                            }
                            break;
                        }
                        default: {
                            this.msgText[0] = "msgState default case Error";
                            this.msgText[1] = "";
                            this.MessageError();
                            break;
                        }
                    }
                }
                if (this.msgState == 1 && ++this.msgNumber >= this.text_lines) {
                    this.msgNumber = 0;
                }
                try {
                    if (this.update_ms < 10) {
                        this.update_ms = 10;
                    }
                    Thread.sleep(this.update_ms);
                }
                catch (InterruptedException ex) {
                    this.msgText[0] = "run() InterruptedException";
                    this.msgText[1] = ex.getMessage();
                    this.MessageError();
                }
            }
            catch (RuntimeException ex2) {
                this.msgText[0] = "run() RuntimeException";
                this.msgText[1] = ex2.getMessage();
                this.MessageError();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        try {
            graphics.drawImage(this.offScrImage, 0, 0, this);
        }
        catch (RuntimeException ex) {
            this.msgText[0] = "paint() RuntimeException";
            this.msgText[1] = ex.getMessage();
            this.MessageError();
        }
    }
    
    public void update(final Graphics graphics) {
        try {
            this.paint(graphics);
        }
        catch (RuntimeException ex) {
            this.msgText[0] = "update() RuntimeException";
            this.msgText[1] = ex.getMessage();
            this.MessageError();
        }
    }
    
    public Color GetColor(final String s, final Color color) {
        final Color black = Color.black;
        Color color2;
        try {
            if (s.compareTo("black") == 0) {
                color2 = Color.black;
            }
            else if (s.compareTo("white") == 0) {
                color2 = Color.white;
            }
            else if (s.compareTo("lightGray") == 0) {
                color2 = Color.lightGray;
            }
            else if (s.compareTo("gray") == 0) {
                color2 = Color.gray;
            }
            else if (s.compareTo("darkGray") == 0) {
                color2 = Color.darkGray;
            }
            else if (s.compareTo("green") == 0) {
                color2 = Color.green;
            }
            else if (s.compareTo("yellow") == 0) {
                color2 = Color.yellow;
            }
            else if (s.compareTo("blue") == 0) {
                color2 = Color.blue;
            }
            else if (s.compareTo("red") == 0) {
                color2 = Color.red;
            }
            else if (s.compareTo("cyan") == 0) {
                color2 = Color.cyan;
            }
            else if (s.compareTo("orange") == 0) {
                color2 = Color.orange;
            }
            else if (s.compareTo("pink") == 0) {
                color2 = Color.pink;
            }
            else if (s.compareTo("magenta") == 0) {
                color2 = Color.magenta;
            }
            else {
                color2 = color;
            }
        }
        catch (RuntimeException ex) {
            color2 = Color.black;
            this.msgText[0] = "GetColor() RuntimeException";
            this.msgText[1] = ex.getMessage();
            this.MessageError();
        }
        return color2;
    }
    
    public void GetDateString() {
        try {
            switch (this.date.getDay()) {
                case 0: {
                    this.DateMsg = "Sunday  ";
                    break;
                }
                case 1: {
                    this.DateMsg = "Monday  ";
                    break;
                }
                case 2: {
                    this.DateMsg = "Tuesday  ";
                    break;
                }
                case 3: {
                    this.DateMsg = "Wednesday  ";
                    break;
                }
                case 4: {
                    this.DateMsg = "Thursday  ";
                    break;
                }
                case 5: {
                    this.DateMsg = "Friday  ";
                    break;
                }
                case 6: {
                    this.DateMsg = "Saturday  ";
                    break;
                }
                default: {
                    this.DateMsg = " ";
                    break;
                }
            }
            final int n = this.date.getMonth() + 1;
            if (n < 10) {
                this.DateMsg = String.valueOf(this.DateMsg) + "0" + n + "/";
            }
            else {
                this.DateMsg = String.valueOf(this.DateMsg) + n + "/";
            }
            final int date = this.date.getDate();
            if (date < 10) {
                this.DateMsg = String.valueOf(this.DateMsg) + "0" + date + "/";
            }
            else {
                this.DateMsg = String.valueOf(this.DateMsg) + date + "/";
            }
            int year = this.date.getYear();
            if (year > 99) {
                year -= 100;
            }
            if (year < 10) {
                this.DateMsg = String.valueOf(this.DateMsg) + "0" + year + "  ";
            }
            else {
                this.DateMsg = String.valueOf(this.DateMsg) + year + "  ";
            }
            int hours = this.date.getHours();
            String s;
            if (hours < 12) {
                s = "AM";
            }
            else {
                s = "PM";
                hours -= 12;
            }
            if (hours == 0) {
                hours = 12;
            }
            if (hours < 10) {
                this.DateMsg = String.valueOf(this.DateMsg) + "0" + hours + ":";
            }
            else {
                this.DateMsg = String.valueOf(this.DateMsg) + hours + ":";
            }
            final int minutes = this.date.getMinutes();
            if (minutes < 10) {
                this.DateMsg = String.valueOf(this.DateMsg) + "0" + minutes + ":";
            }
            else {
                this.DateMsg = String.valueOf(this.DateMsg) + minutes + ":";
            }
            final int seconds = this.date.getSeconds();
            if (seconds < 10) {
                this.DateMsg = String.valueOf(this.DateMsg) + "0" + seconds + "  ";
            }
            else {
                this.DateMsg = String.valueOf(this.DateMsg) + seconds + "  ";
            }
            this.DateMsg = String.valueOf(this.DateMsg) + s;
        }
        catch (RuntimeException ex) {
            this.DateMsg = " ";
            this.msgText[0] = "GetDateString() RuntimeException";
            this.msgText[1] = ex.getMessage();
            this.MessageError();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (this.urlState == this.TRUE && event.clickCount >= 1) {
                if (this.urlStr.length() > 1) {
                    this.getAppletContext().showDocument(new URL(this.urlStr));
                }
                else {
                    this.urlState = this.FALSE;
                }
            }
        }
        catch (MalformedURLException ex) {
            this.msgText[0] = "mouseDown() MalformedURLException";
            this.msgText[1] = ex.getMessage();
            this.MessageError();
        }
        catch (RuntimeException ex2) {
            this.msgText[0] = "mouseDown() RuntimeException";
            this.msgText[1] = ex2.getMessage();
            this.MessageError();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        try {
            if (this.urlState == this.TRUE && this != null) {
                ((Frame)this.getParent()).setCursor(12);
            }
        }
        catch (RuntimeException ex) {}
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        try {
            if (this.urlState == this.TRUE && this != null) {
                ((Frame)this.getParent()).setCursor(0);
            }
        }
        catch (RuntimeException ex) {}
        return true;
    }
    
    public void DrawMessage(final String s) {
        this.offScrGC.setColor(this.bgColor[this.msgNumber]);
        this.offScrGC.fillRect(0, 0, this.appletWidth, this.appletHeight);
        this.offScrGC.setColor(this.msgColor[this.msgNumber]);
        this.offScrGC.drawString(s, this.msgPos[this.msgNumber], this.msgHeight);
    }
    
    public void DrawFrame() {
        if (this.frameWidth > 0) {
            for (int i = 0; i < this.frameWidth; ++i) {
                this.offScrGC.setColor(this.upFrameColor[this.msgNumber]);
                this.offScrGC.drawLine(i, i, i, this.appletHeight - i);
                this.offScrGC.drawLine(i, i, this.appletWidth - i, i);
                this.offScrGC.setColor(this.lowFrameColor[this.msgNumber]);
                this.offScrGC.drawLine(this.appletWidth - i, i, this.appletWidth - i, this.appletHeight - i);
                this.offScrGC.drawLine(i, this.appletHeight - i, this.appletWidth - i, this.appletHeight - i);
            }
        }
    }
    
    public void MessageError() {
        this.update_ms = 1000;
        this.urlState = this.FALSE;
        this.date_update = 0;
        this.text_lines = 1;
        this.msgState = 1;
        this.msgNumber = 0;
        this.msgLen[0] = this.msgText[0].length();
        this.width = this.fm.stringWidth(this.msgText[0]);
        this.msgPos[0] = (this.appletWidth - this.width) / 2;
        this.dly[0] = 4000;
        this.msgColor[0] = Color.red;
        this.bgColor[0] = Color.black;
        this.msgLen[1] = this.msgText[1].length();
        if (this.msgLen[1] > 0) {
            this.text_lines = 2;
            this.width = this.fm.stringWidth(this.msgText[1]);
            this.msgPos[1] = (this.appletWidth - this.width) / 2;
            this.dly[1] = 4000;
            this.msgColor[1] = Color.red;
            this.bgColor[1] = Color.black;
        }
    }
    
    public void visitor() {
        try {
            if (this.locationError == this.FALSE) {
                while (this.i == null) {}
                for (int i = 0; i < 1000; ++i) {
                    this.dummy = InetAddress.getLocalHost();
                    if (this.i != null) {
                        final byte[] address = this.i.getAddress();
                        final byte b = address[0];
                        final byte b2 = address[1];
                        final byte b3 = address[2];
                        final byte b4 = address[3];
                        if (b != 127 || b2 != 0 || b3 != 0 || b4 != 1) {
                            this.domainOk = this.TRUE;
                            break;
                        }
                    }
                }
                this.domainName = this.i.getHostName();
                this.ipAddress = "";
                final byte[] address2 = this.i.getAddress();
                for (int j = 0; j < address2.length; ++j) {
                    this.ipAddress = String.valueOf(this.ipAddress) + ((address2[j] < 0) ? (address2[j] + 256) : address2[j]);
                    if (j < address2.length - 1) {
                        this.ipAddress = String.valueOf(this.ipAddress) + ".";
                    }
                }
            }
        }
        catch (UnknownHostException ex2) {
            this.locationError = this.TRUE;
            this.domainName = "Domain Unknown Host";
            this.ipAddress = "IP Unknown Host";
        }
        catch (RuntimeException ex) {
            this.locationError = this.TRUE;
            this.domainName = "visitor(Name) RuntimeException";
            this.ipAddress = ex.getMessage();
        }
    }
    
    public String getDomainName() {
        return this.domainName;
    }
    
    public String getIPAddress() {
        return this.ipAddress;
    }
    
    public mpanel2() {
        this.TRUE = 1;
        this.locationError = this.FALSE;
        this.LINES = 99;
        this.msgLen = new int[this.LINES];
        this.msgPos = new int[this.LINES];
        this.dly = new int[this.LINES];
        this.msgText = new String[this.LINES];
        this.msgColor = new Color[this.LINES];
        this.bgColor = new Color[this.LINES];
        this.upFrameColor = new Color[this.LINES];
        this.lowFrameColor = new Color[this.LINES];
    }
}
