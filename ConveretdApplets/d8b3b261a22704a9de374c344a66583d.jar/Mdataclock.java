import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mdataclock extends Applet implements Runnable
{
    String o_c;
    Thread t;
    Date d;
    int c_o;
    int c_v;
    int f_s;
    int f_t;
    int c_ty;
    String gi;
    String date;
    String me;
    String an;
    String hr;
    String mi;
    String ampm;
    String se;
    String f_n;
    Color sf;
    Color g_s_c;
    Color c_t;
    Color g_t_c;
    boolean v_c;
    boolean isStandalone;
    String aut_001;
    
    public Mdataclock() {
        this.g_s_c = new Color(255, 255, 255);
        this.g_t_c = new Color(0, 0, 0);
        this.v_c = false;
        this.isStandalone = false;
    }
    
    final void EN(final Graphics g) {
        final Font font = new Font(this.f_n, this.f_t, this.f_s);
        g.setFont(font);
        final FontMetrics fontmetrics = g.getFontMetrics(font);
        final Dimension dm = this.size();
        final int o = dm.width;
        final int i1 = dm.height;
        final int gg = this.d.getDay();
        if (gg == 0) {
            this.gi = "Sunday";
        }
        if (gg == 1) {
            this.gi = "Monday";
        }
        if (gg == 2) {
            this.gi = "Tuesday";
        }
        if (gg == 3) {
            this.gi = "Wednesday";
        }
        if (gg == 4) {
            this.gi = "Thursday";
        }
        if (gg == 5) {
            this.gi = "Friday";
        }
        if (gg == 6) {
            this.gi = "Saturday";
        }
        final int j = this.d.getDate();
        this.date = Integer.toString(j);
        final int mm = this.d.getMonth();
        if (mm == 0) {
            this.me = "January";
        }
        if (mm == 1) {
            this.me = "Febuary";
        }
        if (mm == 2) {
            this.me = "March";
        }
        if (mm == 3) {
            this.me = "April";
        }
        if (mm == 4) {
            this.me = "May";
        }
        if (mm == 5) {
            this.me = "June";
        }
        if (mm == 6) {
            this.me = "July";
        }
        if (mm == 7) {
            this.me = "August";
        }
        if (mm == 8) {
            this.me = "September";
        }
        if (mm == 9) {
            this.me = "October";
        }
        if (mm == 10) {
            this.me = "November";
        }
        if (mm == 11) {
            this.me = "December";
        }
        final int l = this.d.getYear() + 1900;
        this.an = Integer.toString(l);
        final int a1 = this.d.getHours();
        this.hr = Integer.toString(a1);
        final int b1 = this.d.getMinutes();
        this.mi = Integer.toString(b1);
        final int c1 = this.d.getSeconds();
        this.se = Integer.toString(c1);
        if (a1 < 10) {
            this.hr = "0" + this.hr;
        }
        this.hr = this.hr;
        if (b1 < 10) {
            this.mi = "0" + this.mi;
        }
        this.mi = this.mi;
        if (c1 < 10) {
            this.se = "0" + this.se;
        }
        this.se = this.se;
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.sf);
        }
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.c_t);
        }
        this.o_c = String.valueOf(this.gi) + " " + this.date + " " + this.me + " " + this.an + " " + this.hr + ":" + this.mi + ":" + this.se;
        this.c_o = (this.size().width - fontmetrics.stringWidth(this.o_c)) / 2;
        this.c_v = (this.size().height + (fontmetrics.getAscent() - fontmetrics.getDescent())) / 2;
        g.drawString(this.o_c, this.c_o, this.c_v);
    }
    
    final void EN_AMPM(final Graphics g) {
        final Font font = new Font(this.f_n, this.f_t, this.f_s);
        g.setFont(font);
        final FontMetrics fontmetrics = g.getFontMetrics(font);
        final Dimension dm = this.size();
        final int o = dm.width;
        final int i1 = dm.height;
        final int gg = this.d.getDay();
        if (gg == 0) {
            this.gi = "Sunday";
        }
        if (gg == 1) {
            this.gi = "Monday";
        }
        if (gg == 2) {
            this.gi = "Tuesday";
        }
        if (gg == 3) {
            this.gi = "Wednesday";
        }
        if (gg == 4) {
            this.gi = "Thursday";
        }
        if (gg == 5) {
            this.gi = "Friday";
        }
        if (gg == 6) {
            this.gi = "Saturday";
        }
        final int j = this.d.getDate();
        this.date = Integer.toString(j);
        final int mm = this.d.getMonth();
        if (mm == 0) {
            this.me = "January";
        }
        if (mm == 1) {
            this.me = "Febuary";
        }
        if (mm == 2) {
            this.me = "March";
        }
        if (mm == 3) {
            this.me = "April";
        }
        if (mm == 4) {
            this.me = "May";
        }
        if (mm == 5) {
            this.me = "June";
        }
        if (mm == 6) {
            this.me = "July";
        }
        if (mm == 7) {
            this.me = "August";
        }
        if (mm == 8) {
            this.me = "September";
        }
        if (mm == 9) {
            this.me = "October";
        }
        if (mm == 10) {
            this.me = "November";
        }
        if (mm == 11) {
            this.me = "December";
        }
        final int l = this.d.getYear() + 1900;
        this.an = Integer.toString(l);
        final int a1 = this.d.getHours();
        this.hr = Integer.toString(a1);
        final int b1 = this.d.getMinutes();
        this.mi = Integer.toString(b1);
        final int c1 = this.d.getSeconds();
        this.se = Integer.toString(c1);
        if (a1 < 10) {
            this.hr = "0" + this.hr;
        }
        this.hr = this.hr;
        if (b1 < 10) {
            this.mi = "0" + this.mi;
        }
        this.mi = this.mi;
        if (c1 < 10) {
            this.se = "0" + this.se;
        }
        this.se = this.se;
        this.ampm = ((a1 < 12) ? "AM" : "PM");
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.sf);
        }
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.c_t);
        }
        this.o_c = String.valueOf(this.gi) + " " + this.date + " " + this.me + " " + this.an + " " + this.hr + ":" + this.mi + ":" + this.se + " " + this.ampm;
        this.c_o = (this.size().width - fontmetrics.stringWidth(this.o_c)) / 2;
        this.c_v = (this.size().height + (fontmetrics.getAscent() - fontmetrics.getDescent())) / 2;
        g.drawString(this.o_c, this.c_o, this.c_v);
    }
    
    final void EN_ampm(final Graphics g) {
        final Font font = new Font(this.f_n, this.f_t, this.f_s);
        g.setFont(font);
        final FontMetrics fontmetrics = g.getFontMetrics(font);
        final Dimension dm = this.size();
        final int o = dm.width;
        final int i1 = dm.height;
        final int gg = this.d.getDay();
        if (gg == 0) {
            this.gi = "Sunday";
        }
        if (gg == 1) {
            this.gi = "Monday";
        }
        if (gg == 2) {
            this.gi = "Tuesday";
        }
        if (gg == 3) {
            this.gi = "Wednesday";
        }
        if (gg == 4) {
            this.gi = "Thursday";
        }
        if (gg == 5) {
            this.gi = "Friday";
        }
        if (gg == 6) {
            this.gi = "Saturday";
        }
        final int j = this.d.getDate();
        this.date = Integer.toString(j);
        final int mm = this.d.getMonth();
        if (mm == 0) {
            this.me = "January";
        }
        if (mm == 1) {
            this.me = "Febuary";
        }
        if (mm == 2) {
            this.me = "March";
        }
        if (mm == 3) {
            this.me = "April";
        }
        if (mm == 4) {
            this.me = "May";
        }
        if (mm == 5) {
            this.me = "June";
        }
        if (mm == 6) {
            this.me = "July";
        }
        if (mm == 7) {
            this.me = "August";
        }
        if (mm == 8) {
            this.me = "September";
        }
        if (mm == 9) {
            this.me = "October";
        }
        if (mm == 10) {
            this.me = "November";
        }
        if (mm == 11) {
            this.me = "December";
        }
        final int l = this.d.getYear() + 1900;
        this.an = Integer.toString(l);
        final int a1 = this.d.getHours();
        this.hr = Integer.toString(a1);
        final int b1 = this.d.getMinutes();
        this.mi = Integer.toString(b1);
        final int c1 = this.d.getSeconds();
        this.se = Integer.toString(c1);
        if (a1 < 10) {
            this.hr = "0" + this.hr;
        }
        this.hr = this.hr;
        if (b1 < 10) {
            this.mi = "0" + this.mi;
        }
        this.mi = this.mi;
        if (c1 < 10) {
            this.se = "0" + this.se;
        }
        this.se = this.se;
        this.ampm = ((a1 < 12) ? "am" : "pm");
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.sf);
        }
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.c_t);
        }
        this.o_c = String.valueOf(this.gi) + " " + this.date + " " + this.me + " " + this.an + " " + this.hr + ":" + this.mi + ":" + this.se + " " + this.ampm;
        this.c_o = (this.size().width - fontmetrics.stringWidth(this.o_c)) / 2;
        this.c_v = (this.size().height + (fontmetrics.getAscent() - fontmetrics.getDescent())) / 2;
        g.drawString(this.o_c, this.c_o, this.c_v);
    }
    
    final void IT(final Graphics g) {
        final Font font = new Font(this.f_n, this.f_t, this.f_s);
        g.setFont(font);
        final FontMetrics fontmetrics = g.getFontMetrics(font);
        final Dimension dm = this.size();
        final int o = dm.width;
        final int i1 = dm.height;
        final int gg = this.d.getDay();
        if (gg == 0) {
            this.gi = "Domenica";
        }
        if (gg == 1) {
            this.gi = "Luned\u00ec";
        }
        if (gg == 2) {
            this.gi = "Marted\u00ec";
        }
        if (gg == 3) {
            this.gi = "Mercoled\u00ec";
        }
        if (gg == 4) {
            this.gi = "Gioved\u00ec";
        }
        if (gg == 5) {
            this.gi = "Venerd\u00ec";
        }
        if (gg == 6) {
            this.gi = "Sabato";
        }
        final int j = this.d.getDate();
        this.date = Integer.toString(j);
        final int mm = this.d.getMonth();
        if (mm == 0) {
            this.me = "Gennaio";
        }
        if (mm == 1) {
            this.me = "Febbraio";
        }
        if (mm == 2) {
            this.me = "Marzo";
        }
        if (mm == 3) {
            this.me = "Aprile";
        }
        if (mm == 4) {
            this.me = "Maggio";
        }
        if (mm == 5) {
            this.me = "Giugno";
        }
        if (mm == 6) {
            this.me = "Luglio";
        }
        if (mm == 7) {
            this.me = "Agosto";
        }
        if (mm == 8) {
            this.me = "Settembre";
        }
        if (mm == 9) {
            this.me = "Ottobre";
        }
        if (mm == 10) {
            this.me = "Nonembre";
        }
        if (mm == 11) {
            this.me = "Dicembre";
        }
        final int l = this.d.getYear() + 1900;
        this.an = Integer.toString(l);
        final int a1 = this.d.getHours();
        this.hr = Integer.toString(a1);
        final int b1 = this.d.getMinutes();
        this.mi = Integer.toString(b1);
        final int c1 = this.d.getSeconds();
        this.se = Integer.toString(c1);
        if (a1 < 10) {
            this.hr = "0" + this.hr;
        }
        this.hr = this.hr;
        if (b1 < 10) {
            this.mi = "0" + this.mi;
        }
        this.mi = this.mi;
        if (c1 < 10) {
            this.se = "0" + this.se;
        }
        this.se = this.se;
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.sf);
        }
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.c_t);
        }
        this.o_c = String.valueOf(this.gi) + " " + this.date + " " + this.me + " " + this.an + " " + this.hr + ":" + this.mi + ":" + this.se;
        this.c_o = (this.size().width - fontmetrics.stringWidth(this.o_c)) / 2;
        this.c_v = (this.size().height + (fontmetrics.getAscent() - fontmetrics.getDescent())) / 2;
        g.drawString(this.o_c, this.c_o, this.c_v);
    }
    
    final void IT_AMPM(final Graphics g) {
        final Font font = new Font(this.f_n, this.f_t, this.f_s);
        g.setFont(font);
        final FontMetrics fontmetrics = g.getFontMetrics(font);
        final Dimension dm = this.size();
        final int o = dm.width;
        final int i1 = dm.height;
        final int gg = this.d.getDay();
        if (gg == 0) {
            this.gi = "Domenica";
        }
        if (gg == 1) {
            this.gi = "Luned\u00ec";
        }
        if (gg == 2) {
            this.gi = "Marted\u00ec";
        }
        if (gg == 3) {
            this.gi = "Mercoled\u00ec";
        }
        if (gg == 4) {
            this.gi = "Gioved\u00ec";
        }
        if (gg == 5) {
            this.gi = "Venerd\u00ec";
        }
        if (gg == 6) {
            this.gi = "Sabato";
        }
        final int j = this.d.getDate();
        this.date = Integer.toString(j);
        final int mm = this.d.getMonth();
        if (mm == 0) {
            this.me = "Gennaio";
        }
        if (mm == 1) {
            this.me = "Febbraio";
        }
        if (mm == 2) {
            this.me = "Marzo";
        }
        if (mm == 3) {
            this.me = "Aprile";
        }
        if (mm == 4) {
            this.me = "Maggio";
        }
        if (mm == 5) {
            this.me = "Giugno";
        }
        if (mm == 6) {
            this.me = "Luglio";
        }
        if (mm == 7) {
            this.me = "Agosto";
        }
        if (mm == 8) {
            this.me = "Settembre";
        }
        if (mm == 9) {
            this.me = "Ottobre";
        }
        if (mm == 10) {
            this.me = "Nonembre";
        }
        if (mm == 11) {
            this.me = "Dicembre";
        }
        final int l = this.d.getYear() + 1900;
        this.an = Integer.toString(l);
        final int a1 = this.d.getHours();
        this.hr = Integer.toString(a1);
        final int b1 = this.d.getMinutes();
        this.mi = Integer.toString(b1);
        final int c1 = this.d.getSeconds();
        this.se = Integer.toString(c1);
        if (a1 < 10) {
            this.hr = "0" + this.hr;
        }
        this.hr = this.hr;
        if (b1 < 10) {
            this.mi = "0" + this.mi;
        }
        this.mi = this.mi;
        if (c1 < 10) {
            this.se = "0" + this.se;
        }
        this.ampm = ((a1 < 12) ? "AM" : "PM");
        this.se = this.se;
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.sf);
        }
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.c_t);
        }
        this.o_c = String.valueOf(this.gi) + " " + this.date + " " + this.me + " " + this.an + " " + this.hr + ":" + this.mi + ":" + this.se + " " + this.ampm;
        this.c_o = (this.size().width - fontmetrics.stringWidth(this.o_c)) / 2;
        this.c_v = (this.size().height + (fontmetrics.getAscent() - fontmetrics.getDescent())) / 2;
        g.drawString(this.o_c, this.c_o, this.c_v);
    }
    
    final void IT_ampm(final Graphics g) {
        final Font font = new Font(this.f_n, this.f_t, this.f_s);
        g.setFont(font);
        final FontMetrics fontmetrics = g.getFontMetrics(font);
        final Dimension dm = this.size();
        final int o = dm.width;
        final int i1 = dm.height;
        final int gg = this.d.getDay();
        if (gg == 0) {
            this.gi = "Domenica";
        }
        if (gg == 1) {
            this.gi = "Luned\u00ec";
        }
        if (gg == 2) {
            this.gi = "Marted\u00ec";
        }
        if (gg == 3) {
            this.gi = "Mercoled\u00ec";
        }
        if (gg == 4) {
            this.gi = "Gioved\u00ec";
        }
        if (gg == 5) {
            this.gi = "Venerd\u00ec";
        }
        if (gg == 6) {
            this.gi = "Sabato";
        }
        final int j = this.d.getDate();
        this.date = Integer.toString(j);
        final int mm = this.d.getMonth();
        if (mm == 0) {
            this.me = "Gennaio";
        }
        if (mm == 1) {
            this.me = "Febbraio";
        }
        if (mm == 2) {
            this.me = "Marzo";
        }
        if (mm == 3) {
            this.me = "Aprile";
        }
        if (mm == 4) {
            this.me = "Maggio";
        }
        if (mm == 5) {
            this.me = "Giugno";
        }
        if (mm == 6) {
            this.me = "Luglio";
        }
        if (mm == 7) {
            this.me = "Agosto";
        }
        if (mm == 8) {
            this.me = "Settembre";
        }
        if (mm == 9) {
            this.me = "Ottobre";
        }
        if (mm == 10) {
            this.me = "Nonembre";
        }
        if (mm == 11) {
            this.me = "Dicembre";
        }
        final int l = this.d.getYear() + 1900;
        this.an = Integer.toString(l);
        final int a1 = this.d.getHours();
        this.hr = Integer.toString(a1);
        final int b1 = this.d.getMinutes();
        this.mi = Integer.toString(b1);
        final int c1 = this.d.getSeconds();
        this.se = Integer.toString(c1);
        if (a1 < 10) {
            this.hr = "0" + this.hr;
        }
        this.hr = this.hr;
        if (b1 < 10) {
            this.mi = "0" + this.mi;
        }
        this.mi = this.mi;
        if (c1 < 10) {
            this.se = "0" + this.se;
        }
        this.se = this.se;
        this.ampm = ((a1 < 12) ? "am" : "pm");
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.sf);
        }
        g.fillRect(0, 0, dm.width, dm.height);
        if (!this.v_c) {
            g.setColor(this.c_t);
        }
        this.o_c = String.valueOf(this.gi) + " " + this.date + " " + this.me + " " + this.an + " " + this.hr + ":" + this.mi + ":" + this.se + " " + this.ampm;
        this.c_o = (this.size().width - fontmetrics.stringWidth(this.o_c)) / 2;
        this.c_v = (this.size().height + (fontmetrics.getAscent() - fontmetrics.getDescent())) / 2;
        g.drawString(this.o_c, this.c_o, this.c_v);
    }
    
    public String getAppletInfo() {
        String s = "";
        s = String.valueOf(s) + "Titolo: Mdataclock\r\n";
        s = String.valueOf(s) + "Autore: Massimo Giari\r\n";
        s = String.valueOf(s) + "E-mail: motore@iol.it\r\n";
        s = String.valueOf(s) + "Copyright Massimo Giari 21/07/2002";
        return s;
    }
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) == null) ? def : this.getParameter(key));
    }
    
    public void init() {
        try {
            this.aut_001 = this.getParameter("autore", "");
        }
        catch (Exception ex) {}
        if (!this.aut_001.equalsIgnoreCase("Massimo Giari")) {
            System.exit(0);
        }
        this.sf = new Color(this.set_rgb("background", this.g_s_c));
        this.c_t = new Color(this.set_rgb("font_color", this.g_t_c));
        this.setBackground(this.sf);
        this.f_n = this.getParameter("font");
        if (this.f_n == null) {
            this.f_n = "Arial";
        }
        final String s = this.getParameter("font_size");
        if (s == null) {
            this.f_s = 18;
            return;
        }
        this.f_s = Integer.parseInt(s);
        if (this.f_s >= 21) {
            this.f_s = 20;
        }
        StringTokenizer stringtokenizer = new StringTokenizer(this.getParameter("font_type"));
        this.f_t = Integer.parseInt(stringtokenizer.nextToken());
        if (this.f_t >= 4) {
            this.f_t = 1;
        }
        stringtokenizer = new StringTokenizer(this.getParameter("clock_type"));
        this.c_ty = Integer.parseInt(stringtokenizer.nextToken());
        if (this.c_ty >= 6) {
            this.c_ty = 1;
        }
    }
    
    public boolean mouseEnter(final Event event, final int i, final int j) {
        this.showStatus("Applet Mdataclock by ( Massimo Giari )");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int i, final int j) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics g) {
        switch (this.c_ty) {
            case 0: {
                this.IT(g);
                break;
            }
            case 1: {
                this.IT_ampm(g);
                break;
            }
            case 2: {
                this.IT_AMPM(g);
                break;
            }
            case 3: {
                this.EN(g);
                break;
            }
            case 4: {
                this.EN_ampm(g);
                break;
            }
            case 5: {
                this.EN_AMPM(g);
                break;
            }
        }
    }
    
    public void run() {
        while (true) {
            this.d = new Date();
            this.repaint();
            this.t = null;
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private int set_rgb(final String s, final Color color) {
        final String s2 = this.getParameter(s);
        if (s2 != null) {
            final Integer integer = Integer.valueOf(s2, 16);
            return integer;
        }
        return color.getRGB();
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.t = null;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
