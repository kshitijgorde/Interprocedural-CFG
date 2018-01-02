import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class dclock extends Applet implements Runnable
{
    private Image digits;
    private Image bgpic;
    private Image offImage;
    private MediaTracker mtracker;
    private Color bgcolor;
    private boolean show_seconds;
    private boolean flash;
    private boolean mil_format;
    private int gmtoffset;
    private int t_hr;
    private int t_min;
    private int t_sec;
    private int sec;
    private Thread timer;
    private Graphics offGraphics;
    private Dimension offDim;
    private Dimension dim;
    
    public void init() {
        this.mtracker = new MediaTracker(this);
        this.printInfo();
        String parameter = this.getParameter("digits");
        if (parameter == null) {
            parameter = "bit1.gif";
        }
        this.digits = this.getImage(this.getCodeBase(), parameter);
        this.mtracker.addImage(this.digits, 0);
        System.out.println("Digit Style: " + parameter);
        final String parameter2 = this.getParameter("background");
        if (parameter2 == null) {
            this.bgpic = null;
            System.out.println("Background: No");
        }
        else {
            this.bgpic = this.getImage(this.getCodeBase(), parameter2);
            this.mtracker.addImage(this.bgpic, 1);
            System.out.println("Background Picture: " + parameter2);
        }
        String parameter3 = this.getParameter("bgcolor");
        if (parameter3 == null) {
            parameter3 = "000000";
        }
        try {
            this.bgcolor = new Color(Integer.parseInt(parameter3.substring(0, 2), 16), Integer.parseInt(parameter3.substring(2, 4), 16), Integer.parseInt(parameter3.substring(4), 16));
        }
        catch (StringIndexOutOfBoundsException ex) {
            this.bgcolor = new Color(0, 0, 0);
            parameter3 = "000000";
        }
        System.out.println("Background Color: " + parameter3);
        this.setBackground(this.bgcolor);
        final String parameter4 = this.getParameter("seconds");
        final String s = "no";
        this.show_seconds = (parameter4 == null || !parameter4.equalsIgnoreCase(s));
        System.out.println("Show Seconds: " + (this.show_seconds ? "Yes" : "No"));
        final String parameter5 = this.getParameter("24hour");
        final String s2 = "yes";
        this.mil_format = (parameter5 != null && parameter5.equalsIgnoreCase(s2));
        System.out.println("Time Format: " + (this.mil_format ? "24Hour" : "AM/PM"));
        final String parameter6 = this.getParameter("timezone");
        if (parameter6 != null) {
            try {
                if (parameter6.startsWith("+")) {
                    this.gmtoffset = Integer.parseInt(parameter6.substring(1));
                }
                else {
                    this.gmtoffset = Integer.parseInt(parameter6);
                }
                System.out.println("Time Zone: " + parameter6);
                return;
            }
            catch (NumberFormatException ex2) {
                System.out.println("Time Zone: Error in the parameter, use local time");
                this.gmtoffset = -9999;
                return;
            }
        }
        System.out.println("Time Zone: Local");
    }
    
    public void start() {
        if (this.timer == null) {
            (this.timer = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.timer = null;
        this.offGraphics = null;
        this.offImage = null;
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (this.timer != null) {
            try {
                Thread.sleep(150L);
            }
            catch (InterruptedException ex) {}
            final Date date = new Date();
            final String trim = date.toGMTString().substring(11, 20).trim();
            this.t_sec = date.getSeconds();
            if (this.sec != this.t_sec) {
                this.sec = this.t_sec;
                if (this.gmtoffset == -9999) {
                    this.t_min = date.getMinutes();
                    this.t_hr = date.getHours();
                }
                else {
                    this.t_min = Integer.parseInt(trim.substring(3, 5)) + this.gmtoffset % 100;
                    this.t_hr = Integer.parseInt(trim.substring(0, 2)) + this.gmtoffset / 100;
                    if (this.t_min > 59) {
                        this.t_min %= 60;
                        ++this.t_hr;
                    }
                    else if (this.t_min < 0) {
                        --this.t_hr;
                        this.t_min += 60;
                    }
                    if (this.t_hr < 0) {
                        this.t_hr += 24;
                    }
                    else {
                        this.t_hr %= 24;
                    }
                }
                this.repaint();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    private void paintDigits() {
        final int height = this.digits.getHeight(this);
        final int width = this.digits.getWidth(this);
        final int n = this.dim.height / 2 - height / 28;
        if (this.bgpic != null) {
            this.offGraphics.drawImage(this.bgpic, 0, 0, this);
        }
        if (this.mil_format) {
            this.offGraphics.clipRect(this.dim.width / 2 - width * 4, n, width * 8, height / 14);
        }
        int n2;
        int n3;
        if (this.show_seconds) {
            if (this.mil_format) {
                n2 = this.dim.width / 2 - width * 4;
                this.offGraphics.clipRect(n2, n, width * 8, height / 14);
            }
            else {
                n2 = this.dim.width / 2 - (int)Math.round(width * 4.5);
                this.offGraphics.clipRect(n2, n, width * 9, height / 14);
            }
            this.offGraphics.drawImage(this.digits, n2 + width * 2, n - 11 * height / 14, this);
            this.offGraphics.drawImage(this.digits, n2 + width * 5, n - 11 * height / 14, this);
            this.offGraphics.drawImage(this.digits, n2 + width * 7, n - this.t_sec % 10 * height / 14, this);
            this.t_sec /= 10;
            this.offGraphics.drawImage(this.digits, n2 + width * 6, n - this.t_sec * height / 14, this);
            n3 = 8;
        }
        else {
            if (this.mil_format) {
                n2 = this.dim.width / 2 - (int)Math.round(width * 2.5);
                this.offGraphics.clipRect(n2, n, width * 5, height / 14);
            }
            else {
                n2 = this.dim.width / 2 - width * 3;
                this.offGraphics.clipRect(n2, n, width * 6, height / 14);
            }
            if (this.flash) {
                this.offGraphics.drawImage(this.digits, n2 + width * 2, n - 11 * height / 14, this);
                this.flash = false;
            }
            else {
                this.offGraphics.drawImage(this.digits, n2 + width * 2, n - 10 * height / 14, this);
                this.flash = true;
            }
            n3 = 5;
        }
        if (!this.mil_format) {
            if (this.t_hr == 0) {
                this.t_hr = 12;
                this.offGraphics.drawImage(this.digits, n2 + width * n3, n - height * 12 / 14, this);
            }
            else if (this.t_hr < 12) {
                this.offGraphics.drawImage(this.digits, n2 + width * n3, n - height * 12 / 14, this);
            }
            else {
                if (this.t_hr != 12) {
                    this.t_hr -= 12;
                }
                this.offGraphics.drawImage(this.digits, n2 + width * n3, n - height * 13 / 14, this);
            }
        }
        this.offGraphics.drawImage(this.digits, n2 + width, n - this.t_hr % 10 * height / 14, this);
        if (this.t_hr > 9) {
            this.t_hr /= 10;
            this.offGraphics.drawImage(this.digits, n2, n - this.t_hr * height / 14, this);
        }
        else {
            this.offGraphics.drawImage(this.digits, n2, n - 10 * height / 14, this);
        }
        this.offGraphics.drawImage(this.digits, n2 + width * 4, n - this.t_min % 10 * height / 14, this);
        this.t_min /= 10;
        this.offGraphics.drawImage(this.digits, n2 + width * 3, n - this.t_min * height / 14, this);
    }
    
    public void update(final Graphics graphics) {
        if (this.timer != null && this.mtracker.statusAll(true) == 8) {
            this.dim = this.size();
            if (this.offGraphics == null || this.dim.width != this.offDim.width || this.dim.height != this.offDim.height) {
                this.offDim = this.dim;
                this.offImage = this.createImage(this.dim.width, this.dim.height);
                this.offGraphics = this.offImage.getGraphics();
            }
            this.paintDigits();
            graphics.drawImage(this.offImage, 0, 0, this);
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503: {
                if (this.mtracker.statusAll(true) == 8) {
                    this.showStatus("Digital Clock V2.01 by David Zhao (http://www.siusa.com/dclock/).");
                    break;
                }
                this.showStatus("Digital Clock: Loading digits, please wait ...");
                break;
            }
        }
        return true;
    }
    
    public String getAppletInfo() {
        return "Copyright (c) 1996 David Zhao, All Rights Reserved.";
    }
    
    private void printInfo() {
        System.out.println("\nDigital Clock by David Zhao");
        System.out.println("Version 2.01");
        System.out.println(this.getAppletInfo());
        System.out.println("E-Mail:dzhao@Cory.EECS.Berkeley.EDU");
        System.out.println("December 23, 1996");
        System.out.println("\nYou can find a copy of this program at:");
        System.out.println("http://www.siusa.com/dclock/");
        System.out.println("Permission to distribute this software is given for");
        System.out.println("non-commerial purposes only.\n");
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "24hour", "boolean", "12/24hour format, default: no" }, { "background", "URL", "filename of the background, default: no" }, { "bgcolor", "int", "RGB value in hex format, default: 000000" }, { "digits", "URL", "filename of digits, default: bit1.gif" }, { "seconds", "boolean", "show/hide seconds, default: no" }, { "timezone", "int", "clock timezone, default: local" } };
    }
    
    public dclock() {
        this.flash = false;
        this.gmtoffset = -9999;
        this.sec = 70;
    }
}
