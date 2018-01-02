import java.util.Date;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Label;
import java.util.Calendar;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Choice;
import java.awt.Image;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class worldclock extends Applet
{
    int y;
    int x;
    int mittelpunkt;
    int kreis;
    int breite;
    int hoehe;
    int radstd;
    int std;
    float minstd1;
    int minstd2;
    int stdx;
    int stdy;
    int stdx2;
    int stdy2;
    int stdx3;
    int stdy3;
    int dickestd;
    int dickestd2;
    Polygon pstd;
    int radmin;
    int min;
    int minx;
    int miny;
    int minx2;
    int miny2;
    int minx3;
    int miny3;
    int dickemin;
    int dickemin2;
    Polygon pmin;
    int radsek;
    int sek;
    int sekx;
    int seky;
    int sekx2;
    int seky2;
    int sekx3;
    int seky3;
    int dickesek;
    int dickesek2;
    Polygon psek;
    ZeitThread Zeit;
    Font AnzeigeFont;
    String tageszeit;
    long Lokaloffset;
    Color uhrfarbe;
    Color bgfarbe;
    Color clockfarbe;
    Color stundecolor;
    Color minutecolor;
    Color secondcolor;
    Color ampmcolor;
    int[] zeitstd;
    int[] zeitmin;
    URL URLnow;
    URLConnection timeConnection;
    int offsetstd;
    int offsetmin;
    boolean networkerror;
    static int anzahlStaedte;
    int language;
    Image buffer;
    Image uhrimg;
    Choice pulldown;
    Graphics bg;
    String errorlabel;
    private String[][] info;
    
    static {
        worldclock.anzahlStaedte = 0;
    }
    
    public worldclock() {
        this.y = 50;
        this.x = 60;
        this.mittelpunkt = 2;
        this.kreis = 6;
        this.hoehe = 150;
        this.radstd = 25;
        this.dickestd = 2;
        this.dickestd2 = 1;
        this.pstd = new Polygon();
        this.radmin = 40;
        this.dickemin = 2;
        this.dickemin2 = 1;
        this.pmin = new Polygon();
        this.radsek = 40;
        this.dickesek = 1;
        this.dickesek2 = 1;
        this.psek = new Polygon();
        this.tageszeit = "A.M.";
        this.offsetstd = 0;
        this.offsetmin = 0;
        this.networkerror = false;
        this.language = 0;
        this.info = new String[][] { { "image", "Datei", "Uhrengrafik 100 x 100 Pixel" }, { "foreground", "Farbwert (ff00ff)", "Farbe der Uhr" }, { "background", "Farbwert (ff00ff)", "Hintergrundfarbe" }, { "clockcolor", "Farbwert (ff00ff)", "Uhrenfl\u00e4chenfarbe" }, { "hourcolor", "Farbwert (ff00ff)", "Stundenzeigerfarbe" }, { "minutecolor", "Farbwert (ff00ff)", "Minutenzeigerfarbe" }, { "secondcolor", "Farbwert (ff00ff)", "Sekundenzeigerfarbe" }, { "ampmcolor", "Farbwert (ff00ff)", "Farbe der Uhrzeit" }, { "homebase", "String", "Alternativstring fuer ersten Eintrag" }, { "townX", "Nummer", "Datenbanknummer des X.(1-5) Eintrags" }, { "language", "Integer (1=Deutschland)", "Sprache der Uhr" } };
    }
    
    public String getAppletInfo() {
        return "WorldClock Version 2.2   Written by Ulrich Szagun";
    }
    
    protected Color getColorParameter(final String s) {
        try {
            return new Color(Integer.parseInt(this.getParameter(s), 16));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected double getFloat(final String s) {
        double doubleValue;
        try {
            doubleValue = Double.valueOf(s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            doubleValue = 0.0;
        }
        return doubleValue;
    }
    
    protected int getHours(final double n) {
        try {
            return (int)(n - n % 1.0);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    protected int getInteger(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return 1000;
        }
    }
    
    protected int getMinutes(final double n) {
        try {
            return (int)Math.round(n % 1.0 * 60.0);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public String[][] getParameterInfo() {
        return this.info;
    }
    
    protected int getParameterlaenge(final String s) {
        try {
            return this.getParameter(s).length();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    protected String getTowns(final String s) {
        String string = "";
        for (int i = 1; i <= 5; ++i) {
            try {
                string = String.valueOf(string) + this.getParameter(String.valueOf(s) + i) + ":";
                ++worldclock.anzahlStaedte;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        String s2 = "?countries=" + string;
        if (this.language == 1) {
            s2 = String.valueOf(s2) + "&lang=d";
        }
        return s2;
    }
    
    public void init() {
        this.breite = this.getSize().width;
        this.x = this.breite / 2;
        this.AnzeigeFont = new Font("Sans-Serif", 0, 12);
        this.uhrfarbe = new Color(0, 0, 0);
        this.stundecolor = new Color(175, 175, 175);
        this.minutecolor = new Color(175, 175, 175);
        this.secondcolor = new Color(175, 175, 175);
        this.bgfarbe = new Color(255, 255, 255);
        this.clockfarbe = new Color(255, 255, 255);
        this.ampmcolor = new Color(0, 0, 0);
        this.offsetstd = 0;
        this.offsetmin = 0;
        this.language = this.getInteger(this.getParameter("language"));
        if (this.language > 1) {
            this.language = 0;
        }
        String parameter = "images/uhr.gif";
        if (this.getParameterlaenge("Image") > 0) {
            parameter = this.getParameter("Image");
        }
        this.uhrimg = this.getImage(this.getCodeBase(), parameter);
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.uhrimg, 0);
            mediaTracker.waitForID(0);
        }
        catch (Exception ex2) {}
        this.buffer = this.createImage(this.breite, this.hoehe);
        this.bg = this.buffer.getGraphics();
        if (this.getColorParameter("background") != null) {
            this.setBackground(this.getColorParameter("background"));
        }
        if (this.getColorParameter("background") != null) {
            this.bgfarbe = this.getColorParameter("background");
        }
        this.clockfarbe = this.bgfarbe;
        if (this.getColorParameter("foreground") != null) {
            this.uhrfarbe = this.getColorParameter("foreground");
        }
        if (this.getColorParameter("clockcolor") != null) {
            this.clockfarbe = this.getColorParameter("clockcolor");
        }
        if (this.getColorParameter("hourcolor") != null) {
            this.stundecolor = this.getColorParameter("hourcolor");
        }
        if (this.getColorParameter("minutecolor") != null) {
            this.minutecolor = this.getColorParameter("minutecolor");
        }
        if (this.getColorParameter("secondcolor") != null) {
            this.secondcolor = this.getColorParameter("secondcolor");
        }
        if (this.getColorParameter("ampmcolor") != null) {
            this.ampmcolor = this.getColorParameter("ampmcolor");
        }
        final Panel panel = new Panel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        panel.setLayout(layout);
        this.setLayout(new BorderLayout());
        (this.pulldown = new Choice()).setFont(new Font("Sans-Serif", 1, 10));
        final String string = "/ts-uhr/timenow.asp" + this.getTowns("town");
        this.Lokaloffset = 0L;
        int integer = 0;
        int integer2 = 0;
        int integer3 = 0;
        int integer4 = 0;
        int integer5 = 0;
        int integer6 = 0;
        this.zeitstd = new int[5];
        this.zeitmin = new int[5];
        this.zeitstd[0] = 0;
        this.zeitmin[0] = 0;
        boolean b = false;
        boolean b2 = false;
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), string).openConnection();
            openConnection.setUseCaches(false);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            integer = this.getInteger(bufferedReader.readLine());
            if (integer == 1000) {
                this.networkerror = true;
            }
            integer2 = this.getInteger(bufferedReader.readLine());
            integer3 = this.getInteger(bufferedReader.readLine());
            integer4 = this.getInteger(bufferedReader.readLine());
            integer5 = this.getInteger(bufferedReader.readLine());
            integer6 = this.getInteger(bufferedReader.readLine());
            b2 = true;
            for (int i = 1; i <= worldclock.anzahlStaedte; ++i) {
                String s = bufferedReader.readLine();
                final double float1 = this.getFloat(bufferedReader.readLine());
                this.zeitstd[i - 1] = this.getHours(float1);
                this.zeitmin[i - 1] = this.getMinutes(float1);
                if (s != null) {
                    if (i == 1 && this.getParameterlaenge("homebase") > 0 && this.getParameterlaenge("homebase") < 25) {
                        s = this.getParameter("homebase");
                    }
                    this.pulldown.addItem(s);
                    b = true;
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            this.networkerror = true;
            ex.printStackTrace();
        }
        if (b2) {
            final Calendar instance = Calendar.getInstance();
            final Calendar instance2 = Calendar.getInstance();
            instance2.set(integer, integer2, integer3, integer4, integer5, integer6);
            this.Lokaloffset = instance.getTime().getTime() - instance2.getTime().getTime();
            if (!b) {
                this.pulldown.addItem("GMT");
                this.zeitstd[0] = 0;
                this.zeitmin[0] = 0;
            }
        }
        else if (!b) {
            this.pulldown.addItem("Computer Time");
        }
        gridBagConstraints.gridwidth = 0;
        panel.add(this.pulldown, gridBagConstraints);
        final Label label = new Label("© by TravelShop", 1);
        final Font font = new Font("Sans-Serif", 1, 9);
        label.setBackground(this.bgfarbe);
        label.setFont(font);
        panel.add(label, gridBagConstraints);
        this.add(panel, "South");
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.travelshop.de/"));
            this.showStatus("");
        }
        catch (Exception ex) {
            this.showStatus("Fehler");
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        String s = "The TravelShop WorldClock";
        if (this.language == 1) {
            s = "Die TravelShop Weltzeituhr";
        }
        this.showStatus(s);
        this.setCursor(Cursor.getPredefinedCursor(12));
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        this.setCursor(Cursor.getDefaultCursor());
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.pmin.addPoint(this.x + this.minx + this.minx3, this.y + this.miny + this.miny3);
        this.pmin.addPoint(this.x + this.minx, this.y + this.miny);
        this.pmin.addPoint(this.x + this.minx - this.minx3, this.y + this.miny - this.miny3);
        this.pmin.addPoint(this.x - this.minx2, this.y - this.miny2);
        this.pmin.addPoint(this.x + this.minx2, this.y + this.miny2);
        this.pstd.addPoint(this.x + this.stdx + this.stdx3, this.y + this.stdy + this.stdy3);
        this.pstd.addPoint(this.x + this.stdx, this.y + this.stdy);
        this.pstd.addPoint(this.x + this.stdx - this.stdx3, this.y + this.stdy - this.stdy3);
        this.pstd.addPoint(this.x - this.stdx2, this.y - this.stdy2);
        this.pstd.addPoint(this.x + this.stdx2, this.y + this.stdy2);
        this.bg.setFont(this.AnzeigeFont);
        this.bg.setColor(this.bgfarbe);
        this.bg.fillRect(0, 0, this.breite, this.hoehe);
        this.bg.setColor(this.clockfarbe);
        this.bg.fillOval(this.x - this.radmin - this.kreis + 2, this.y - this.radmin - this.kreis + 2, 2 * this.radmin + 2 * this.kreis - 5, 2 * this.radmin + 2 * this.kreis - 4);
        this.bg.drawImage(this.uhrimg, this.x - 50, 0, this);
        final FontMetrics fontMetrics = this.bg.getFontMetrics(this.AnzeigeFont);
        final int n = this.x - fontMetrics.stringWidth(this.tageszeit) / 2;
        final int n2 = fontMetrics.getHeight() + fontMetrics.getAscent();
        this.bg.setColor(this.ampmcolor);
        this.bg.drawString(this.tageszeit, n, 108);
        this.bg.setColor(this.minutecolor);
        this.bg.fillPolygon(this.pmin);
        this.bg.setColor(this.stundecolor);
        this.bg.fillPolygon(this.pstd);
        this.bg.setColor(this.secondcolor);
        this.bg.drawLine(this.x, this.y, this.x + this.sekx, this.y + this.seky);
        this.bg.fillOval(this.x - this.mittelpunkt, this.y - this.mittelpunkt, 2 * this.mittelpunkt + 1, 2 * this.mittelpunkt + 1);
        this.networkerror = false;
        if (this.networkerror) {
            this.bg.setColor(this.bgfarbe);
            this.bg.fillRect(0, 0, this.breite, this.hoehe);
            this.bg.setColor(Color.black);
            if (this.language == 1) {
                this.bg.drawString("Momentan kann", this.x - fontMetrics.stringWidth("Momentan kann") / 2, 20);
                this.bg.drawString("leider keine Uhrzeit", this.x - fontMetrics.stringWidth("leider keine Uhrzeit") / 2, 40);
                this.bg.drawString("angezeigt werden...", this.x - fontMetrics.stringWidth("angezeigt werden...") / 2, 60);
            }
            else {
                this.bg.drawString("Sorry, currently", this.x - fontMetrics.stringWidth("Sorry, currently") / 2, 20);
                this.bg.drawString("no local time", this.x - fontMetrics.stringWidth("no local time") / 2, 40);
                this.bg.drawString("can be displayed!", this.x - fontMetrics.stringWidth("can be displayed!") / 2, 60);
            }
        }
        graphics.drawImage(this.buffer, 0, 0, this);
        this.pmin.npoints = 0;
        this.pstd.npoints = 0;
        this.psek.npoints = 0;
    }
    
    public void setOffset(final String s) {
        try {
            this.offsetstd = Integer.parseInt(s);
        }
        catch (Exception ex) {}
    }
    
    public void start() {
        (this.Zeit = new ZeitThread()).start();
    }
    
    public void stop() {
        this.Zeit = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    class ZeitThread extends Thread
    {
        public void run() {
            while (worldclock.this.Zeit == Thread.currentThread()) {
                final Date date = new Date();
                final Calendar instance = Calendar.getInstance();
                worldclock.this.offsetstd = worldclock.this.zeitstd[worldclock.this.pulldown.getSelectedIndex()];
                worldclock.this.offsetmin = worldclock.this.zeitmin[worldclock.this.pulldown.getSelectedIndex()];
                instance.setTime(new Date(instance.getTime().getTime() - worldclock.this.Lokaloffset));
                instance.add(10, worldclock.this.offsetstd);
                instance.add(12, worldclock.this.offsetmin);
                worldclock.this.std = instance.get(11);
                worldclock.this.min = instance.get(12);
                worldclock.this.sek = instance.get(13);
                String s;
                if (instance.get(5) < 10) {
                    s = "0" + instance.get(5);
                }
                else {
                    s = String.valueOf(instance.get(5));
                }
                final int n = instance.get(2) + 1;
                String s2;
                if (n < 10) {
                    s2 = "0" + n;
                }
                else {
                    s2 = String.valueOf(n);
                }
                if (worldclock.this.language > 0) {
                    worldclock.this.tageszeit = String.valueOf(s) + "." + s2 + "." + instance.get(1);
                }
                else {
                    worldclock.this.tageszeit = String.valueOf(s2) + "/" + s + "/" + instance.get(1);
                }
                if (worldclock.this.std > 11) {
                    worldclock.this.tageszeit = String.valueOf(worldclock.this.tageszeit) + ", P.M.";
                }
                else {
                    worldclock.this.tageszeit = String.valueOf(worldclock.this.tageszeit) + ", A.M.";
                }
                worldclock.this.minstd1 = worldclock.this.min / 2;
                worldclock.this.minstd2 = Math.round(worldclock.this.minstd1);
                worldclock.this.stdx = (int)Math.round(worldclock.this.radstd * Math.cos((worldclock.this.std * 30 + worldclock.this.minstd2 + 270) * 0.017453292519943295));
                worldclock.this.stdy = (int)Math.round(worldclock.this.radstd * Math.sin((worldclock.this.std * 30 + worldclock.this.minstd2 + 270) * 0.017453292519943295));
                worldclock.this.stdx2 = (int)Math.round(worldclock.this.dickestd * Math.cos((worldclock.this.std * 30 + worldclock.this.minstd2 + 180) * 0.017453292519943295));
                worldclock.this.stdy2 = (int)Math.round(worldclock.this.dickestd * Math.sin((worldclock.this.std * 30 + worldclock.this.minstd2 + 180) * 0.017453292519943295));
                worldclock.this.stdx3 = (int)Math.round(worldclock.this.dickestd2 * Math.cos((worldclock.this.std * 30 + worldclock.this.minstd2 + 180) * 0.017453292519943295));
                worldclock.this.stdy3 = (int)Math.round(worldclock.this.dickestd2 * Math.sin((worldclock.this.std * 30 + worldclock.this.minstd2 + 180) * 0.017453292519943295));
                worldclock.this.minx = (int)Math.round(worldclock.this.radmin * Math.cos((worldclock.this.min * 6 + 270) * 0.017453292519943295));
                worldclock.this.miny = (int)Math.round(worldclock.this.radmin * Math.sin((worldclock.this.min * 6 + 270) * 0.017453292519943295));
                worldclock.this.minx2 = (int)Math.round(worldclock.this.dickemin * Math.cos((worldclock.this.min * 6 + 180) * 0.017453292519943295));
                worldclock.this.miny2 = (int)Math.round(worldclock.this.dickemin * Math.sin((worldclock.this.min * 6 + 180) * 0.017453292519943295));
                worldclock.this.minx3 = (int)Math.round(worldclock.this.dickemin2 * Math.cos((worldclock.this.min * 6 + 180) * 0.017453292519943295));
                worldclock.this.miny3 = (int)Math.round(worldclock.this.dickemin2 * Math.sin((worldclock.this.min * 6 + 180) * 0.017453292519943295));
                worldclock.this.sekx = (int)Math.round(worldclock.this.radsek * Math.cos((worldclock.this.sek * 6 + 270) * 0.017453292519943295));
                worldclock.this.seky = (int)Math.round(worldclock.this.radsek * Math.sin((worldclock.this.sek * 6 + 270) * 0.017453292519943295));
                worldclock.this.sekx2 = (int)Math.round(worldclock.this.dickesek * Math.cos((worldclock.this.sek * 6 + 180) * 0.017453292519943295));
                worldclock.this.seky2 = (int)Math.round(worldclock.this.dickesek * Math.sin((worldclock.this.sek * 6 + 180) * 0.017453292519943295));
                worldclock.this.sekx3 = (int)Math.round(worldclock.this.dickesek2 * Math.cos((worldclock.this.sek * 6 + 180) * 0.017453292519943295));
                worldclock.this.seky3 = (int)Math.round(worldclock.this.dickesek2 * Math.sin((worldclock.this.sek * 6 + 180) * 0.017453292519943295));
                worldclock.this.repaint();
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}
