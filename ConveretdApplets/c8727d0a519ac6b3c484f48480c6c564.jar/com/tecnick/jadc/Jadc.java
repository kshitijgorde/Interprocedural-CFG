// 
// Decompiled by Procyon v0.5.30
// 

package com.tecnick.jadc;

import java.text.ParsePosition;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.util.Calendar;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

public class Jadc extends Applet implements Runnable
{
    private static final long serialVersionUID = 6109313958742450102L;
    private boolean isStandalone;
    private static final String JADC_VERSION = "1.0.007";
    Thread timer;
    private DisplayPanel dpanel;
    final Applet a;
    private boolean p_counter_mode;
    private String p_counter_time;
    private boolean p_local_time;
    private int p_timezone_hours;
    private int p_timezone_minutes;
    private String p_input_pattern;
    private String p_display_pattern;
    private Color p_background_color;
    private Image p_background_image;
    private Image[] p_img;
    private Date now;
    private SimpleDateFormat formatter;
    private SimpleDateFormat target_formatter;
    private TimeZone tz;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_DAY = 86400;
    private static final int SECONDS_IN_MONTH = 2592000;
    private static final int SECONDS_IN_YEAR = 31536000;
    private static final int SLEEP_TIME = 500;
    
    private static Image loadImage(final Applet applet, final URL url) {
        if (url == null) {
            return null;
        }
        final MediaTracker mediaTracker = new MediaTracker(applet);
        final Image image = applet.getImage(url);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("loadImage exception: " + ex);
        }
        return image;
    }
    
    private String getParameter(final String s, final String s2) {
        if (this.isStandalone) {
            return System.getProperty(s, s2);
        }
        final String parameter = this.getParameter(s);
        if (parameter != null && parameter.length() > 0) {
            return parameter;
        }
        return s2;
    }
    
    private URL setURL(final String s) {
        if (s.length() <= 0) {
            return null;
        }
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("ERROR - Malformed URL: " + s);
        }
        return url;
    }
    
    private void getParameters() {
        try {
            this.p_counter_mode = Boolean.valueOf(this.getParameter("counter_mode", "false"));
            this.p_counter_time = this.getParameter("counter_time", "2000-01-01 00:00:00");
            this.p_local_time = Boolean.valueOf(this.getParameter("local_time", "true"));
            this.p_timezone_hours = Integer.parseInt(this.getParameter("timezone_hours", "0"));
            this.p_timezone_minutes = Integer.parseInt(this.getParameter("timezone_minutes", "0"));
            this.p_input_pattern = this.getParameter("input_pattern", "yyyy-MM-dd HH:mm:ss");
            this.p_display_pattern = this.getParameter("display_pattern", "yyyy-MM-dd HH:mm:ss");
            this.p_background_color = new Color(Integer.parseInt(this.getParameter("background_color", "FFFFFF"), 16));
            this.p_background_image = loadImage(this, this.setURL(this.getParameter("background_image", "")));
            this.p_img[0] = loadImage(this, this.setURL(this.getParameter("img_0", "")));
            this.p_img[1] = loadImage(this, this.setURL(this.getParameter("img_1", "")));
            this.p_img[2] = loadImage(this, this.setURL(this.getParameter("img_2", "")));
            this.p_img[3] = loadImage(this, this.setURL(this.getParameter("img_3", "")));
            this.p_img[4] = loadImage(this, this.setURL(this.getParameter("img_4", "")));
            this.p_img[5] = loadImage(this, this.setURL(this.getParameter("img_5", "")));
            this.p_img[6] = loadImage(this, this.setURL(this.getParameter("img_6", "")));
            this.p_img[7] = loadImage(this, this.setURL(this.getParameter("img_7", "")));
            this.p_img[8] = loadImage(this, this.setURL(this.getParameter("img_8", "")));
            this.p_img[9] = loadImage(this, this.setURL(this.getParameter("img_9", "")));
            this.p_img[10] = loadImage(this, this.setURL(this.getParameter("img_sep", "")));
            this.p_img[11] = loadImage(this, this.setURL(this.getParameter("img_dec", "")));
            this.p_img[12] = loadImage(this, this.setURL(this.getParameter("img_blk", "")));
            this.p_img[13] = loadImage(this, this.setURL(this.getParameter("img_pos", "")));
            this.p_img[14] = loadImage(this, this.setURL(this.getParameter("img_neg", "")));
            if (this.p_counter_mode) {
                final Calendar instance = Calendar.getInstance();
                if (this.p_input_pattern.indexOf("yyyy") < 0) {
                    this.p_input_pattern = String.valueOf(this.p_input_pattern) + " yyyy";
                    this.p_counter_time = String.valueOf(this.p_counter_time) + " " + String.valueOf(instance.get(1));
                }
                if (this.p_input_pattern.indexOf("MM") < 0 && this.p_input_pattern.indexOf("D") < 0) {
                    this.p_input_pattern = String.valueOf(this.p_input_pattern) + " MM";
                    final int n = instance.get(2) + 1;
                    this.p_counter_time = String.valueOf(this.p_counter_time) + " ";
                    if (n < 10) {
                        this.p_counter_time = String.valueOf(this.p_counter_time) + "0";
                    }
                    this.p_counter_time = String.valueOf(this.p_counter_time) + String.valueOf(n);
                }
                if (this.p_input_pattern.indexOf("dd") < 0 && this.p_input_pattern.indexOf("D") < 0) {
                    this.p_input_pattern = String.valueOf(this.p_input_pattern) + " dd";
                    final int value = instance.get(5);
                    this.p_counter_time = String.valueOf(this.p_counter_time) + " ";
                    if (value < 10) {
                        this.p_counter_time = String.valueOf(this.p_counter_time) + "0";
                    }
                    this.p_counter_time = String.valueOf(this.p_counter_time) + String.valueOf(value);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Jadc() {
        this.isStandalone = false;
        this.dpanel = null;
        this.a = this;
        this.p_img = new Image[15];
    }
    
    public void init() {
        System.out.println(" ");
        System.out.println("JADC (Drop Down Menu) 1.0.007");
        System.out.println("http://jadc.sourceforge.net");
        System.out.println("Author: Nicola Asuni");
        System.out.println("Copyright (c) 2002-2006 Tecnick.com s.r.l. - www.tecnick.com");
        System.out.println("Open Source License: GPL 2");
        System.out.println(" ");
        this.add(new Label("Loading..."));
        this.validate();
        this.getParameters();
        this.removeAll();
        this.formatter = new SimpleDateFormat(this.p_display_pattern);
        if (this.p_local_time) {
            this.tz = TimeZone.getDefault();
        }
        else {
            (this.tz = TimeZone.getTimeZone("GMT")).setRawOffset((this.p_timezone_hours * 3600 + this.p_timezone_minutes * 60) * 1000);
        }
        this.formatter.setTimeZone(this.tz);
        String s = this.p_display_pattern;
        int length = this.p_display_pattern.length();
        if (this.p_counter_mode) {
            s = " " + s;
            ++length;
        }
        this.dpanel = new DisplayPanel(length, s, this.p_background_image, this.p_img, this.a.getSize().width, this.a.getSize().height);
        this.setLayout(new BorderLayout());
        this.setBackground(this.p_background_color);
        this.add(this.dpanel, "Center");
    }
    
    public void start() {
        (this.timer = new Thread(this)).start();
    }
    
    public void stop() {
        this.timer = null;
    }
    
    public void destroy() {
    }
    
    public void run() {
        while (this.timer == Thread.currentThread()) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            this.now = new Date();
            if (this.p_counter_mode) {
                (this.target_formatter = new SimpleDateFormat(this.p_input_pattern)).setLenient(true);
                this.target_formatter.setTimeZone(this.tz);
                Date parse = new Date();
                try {
                    parse = this.target_formatter.parse(this.p_counter_time, new ParsePosition(0));
                }
                catch (NullPointerException ex2) {}
                this.dpanel.setInfo(this.millisecToPattern(this.now.getTime() - parse.getTime()));
            }
            else {
                this.dpanel.setInfo(this.formatter.format(this.now));
            }
        }
    }
    
    private String millisecToPattern(final long n) {
        String s = this.p_display_pattern;
        final long n2 = n / 1000L;
        if (this.p_counter_mode) {
            if (n2 > 0L) {
                s = "+" + s;
            }
            else if (n2 < 0L) {
                s = "-" + s;
            }
            else {
                s = " " + s;
            }
        }
        long abs = Math.abs(n2);
        final char[] charArray = s.toCharArray();
        if (s.indexOf(121) >= 0) {
            final int n3 = (int)(abs / 31536000L);
            abs %= 31536000L;
            final String value = String.valueOf(n3);
            int n4 = value.length() - 1;
            for (int i = s.lastIndexOf(121); i >= s.indexOf(121); --i) {
                if (n4 >= 0) {
                    charArray[i] = value.charAt(n4);
                    --n4;
                }
                else {
                    charArray[i] = '0';
                }
            }
        }
        if (s.indexOf(77) >= 0) {
            final int n5 = (int)(abs / 2592000L);
            abs %= 2592000L;
            final String value2 = String.valueOf(n5);
            int n6 = value2.length() - 1;
            for (int j = s.lastIndexOf(77); j >= s.indexOf(77); --j) {
                if (n6 >= 0) {
                    charArray[j] = value2.charAt(n6);
                    --n6;
                }
                else {
                    charArray[j] = '0';
                }
            }
        }
        if (s.indexOf(100) >= 0) {
            final int n7 = (int)(abs / 86400L);
            abs %= 86400L;
            final String value3 = String.valueOf(n7);
            int n8 = value3.length() - 1;
            for (int k = s.lastIndexOf(100); k >= s.indexOf(100); --k) {
                if (n8 >= 0) {
                    charArray[k] = value3.charAt(n8);
                    --n8;
                }
                else {
                    charArray[k] = '0';
                }
            }
        }
        if (s.indexOf(72) >= 0) {
            final int n9 = (int)(abs / 3600L);
            abs %= 3600L;
            final String value4 = String.valueOf(n9);
            int n10 = value4.length() - 1;
            for (int l = s.lastIndexOf(72); l >= s.indexOf(72); --l) {
                if (n10 >= 0) {
                    charArray[l] = value4.charAt(n10);
                    --n10;
                }
                else {
                    charArray[l] = '0';
                }
            }
        }
        if (s.indexOf(109) >= 0) {
            final int n11 = (int)(abs / 60L);
            abs %= 60L;
            final String value5 = String.valueOf(n11);
            int n12 = value5.length() - 1;
            for (int lastIndex = s.lastIndexOf(109); lastIndex >= s.indexOf(109); --lastIndex) {
                if (n12 >= 0) {
                    charArray[lastIndex] = value5.charAt(n12);
                    --n12;
                }
                else {
                    charArray[lastIndex] = '0';
                }
            }
        }
        if (s.indexOf(115) >= 0) {
            final String value6 = String.valueOf(abs);
            int n13 = value6.length() - 1;
            for (int lastIndex2 = s.lastIndexOf(115); lastIndex2 >= s.indexOf(115); --lastIndex2) {
                if (n13 >= 0) {
                    charArray[lastIndex2] = value6.charAt(n13);
                    --n13;
                }
                else {
                    charArray[lastIndex2] = '0';
                }
            }
        }
        return new String(charArray);
    }
    
    public String getAppletInfo() {
        return String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("JADC (Advanced Digital Clock) 1.0.007\n")).append("http://jadc.sourceforge.net\n").toString())).append("Author: Nicola Asuni\n").toString())).append("Copyright (c) 2002-2006 Tecnick.com s.r.l. - www.tecnick.com\n").toString()) + "Open Source License: GPL 2\n";
    }
}
