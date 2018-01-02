import java.awt.Event;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Checkbox;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PieSundial082 extends Applet
{
    String versStr;
    public Seloc loc;
    public Seloc time;
    Checkbox checkBox;
    Checkbox gnomonBox;
    Checkbox writeBox;
    boolean demo;
    boolean online;
    boolean gnomon;
    boolean write;
    double usrLat;
    double usrLong;
    public double latitude;
    public double longitude;
    double homeLat;
    double homeLong;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    public Choice locChoice;
    public Choice timeChoice;
    Choice ewChoice;
    Choice nsChoice;
    Choice radiusChoice;
    public String locString;
    public String timeString;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    String usrStr;
    String userString;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    int timezoneoffset;
    String timezoneoffsetStr;
    String separator;
    int pieRadius;
    String pieRadiusStr;
    int offset;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        num += 8865L;
        return (int)num;
    }
    
    public void init() {
        final Color background = new Color(255, 255, 255);
        this.setBackground(background);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.timezoneoffsetStr = this.getParameter("timezoneoffset");
        this.timezoneoffset = Integer.parseInt(this.timezoneoffsetStr);
        this.pieRadiusStr = this.getParameter("radius");
        this.pieRadius = Integer.parseInt(this.pieRadiusStr);
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.timeString = String.valueOf(this.timezoneoffset);
        if (this.timezoneoffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = " " + this.timeString + " h";
        final Font f = new Font("Helvetica", 0, 10);
        this.setFont(f);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 10, 0, 0);
        final Label L11 = new Label();
        L11.setText("Time Zone Offset");
        gbl.setConstraints(L11, gbc);
        this.add(L11);
        gbc.insets = new Insets(5, 0, 0, 0);
        final Label L12 = new Label();
        L12.setText("Location");
        gbl.setConstraints(L12, gbc);
        this.add(L12);
        gbc.weightx = 10.0;
        final Label L13 = new Label();
        L13.setText("Latitude: xx.xx");
        gbl.setConstraints(L13, gbc);
        this.add(L13);
        final Label L14 = new Label();
        L14.setText("N / S");
        gbl.setConstraints(L14, gbc);
        this.add(L14);
        final Label L15 = new Label();
        L15.setText("Longitude: xx.xx");
        gbl.setConstraints(L15, gbc);
        this.add(L15);
        final Label L16 = new Label();
        L16.setText("E / W");
        gbl.setConstraints(L16, gbc);
        this.add(L16);
        final Label L17 = new Label();
        L17.setText("Radius (pix.)");
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(L17, gbc);
        this.add(L17);
        final Label L18 = new Label();
        L18.setForeground(Color.red);
        L18.setText("Insert Gnomon");
        gbc.insets = new Insets(5, 0, 0, 15);
        gbl.setConstraints(L18, gbc);
        this.add(L18);
        final Label L19 = new Label("Write Hours");
        gbc.insets = new Insets(5, 0, 0, 15);
        gbl.setConstraints(L19, gbc);
        this.add(L19);
        gbc.gridy = 1;
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 10, 0, 0);
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem(this.separator);
        (this.loc = new Seloc()).locMenu(this.locChoice);
        gbl.setConstraints(this.locChoice, gbc);
        this.add(this.locChoice);
        this.locChoice.select(this.locString);
        final Double latDouble = Double.valueOf(this.homeLatStr);
        this.latitude = latDouble;
        this.homeLat = this.latitude;
        if (this.latitude < 0.0) {
            this.nsStr = "S";
        }
        else {
            this.nsStr = "N";
        }
        final Double longDouble = Double.valueOf(this.homeLongStr);
        this.longitude = longDouble;
        this.homeLong = this.longitude;
        if (this.longitude < 0.0) {
            this.ewStr = "W";
        }
        else {
            this.ewStr = "E";
        }
        this.latStr = String.valueOf(Math.abs(this.latitude));
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 4), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 5), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        this.radiusChoice = new Choice();
        for (int i = 0; i < 5; ++i) {
            this.radiusChoice.addItem(String.valueOf(200 + i * 25));
        }
        this.radiusChoice.select(this.pieRadiusStr);
        gbl.setConstraints(this.radiusChoice, gbc);
        this.add(this.radiusChoice);
        (this.gnomonBox = new Checkbox()).setBackground(Color.red);
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(this.gnomonBox, gbc);
        this.gnomonBox.setState(false);
        this.add(this.gnomonBox);
        this.writeBox = new Checkbox();
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(this.writeBox, gbc);
        this.writeBox.setState(true);
        this.add(this.writeBox);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (this.email.length() == 0 || Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
        this.repaint();
    }
    
    public int getOffset(final double longitude) {
        int x;
        if (longitude >= -7.5) {
            x = (int)((longitude + 7.5) / 15.0);
        }
        else {
            x = -(int)(-(longitude - 7.5) / 15.0);
        }
        return x;
    }
    
    public void paint(final Graphics g) {
        final double K = 0.017453292519943295;
        final int leftMargin = 30;
        final int topMargin = 40;
        final double[] alphaPie = new double[30];
        final double[] alphaEqu = new double[30];
        int xPieMitte = this.size().width / 2 - this.pieRadius;
        final int yPieMitte = topMargin + (this.size().height - topMargin) / 2 - 5;
        if (xPieMitte - this.pieRadius < 10) {
            xPieMitte = this.pieRadius + 10;
        }
        final int equRadius = (int)Math.round(this.pieRadius * Math.sin(K * Math.abs(this.latitude)));
        g.drawOval(xPieMitte - this.pieRadius, yPieMitte - this.pieRadius, 2 * this.pieRadius, 2 * this.pieRadius);
        g.drawLine(xPieMitte + this.pieRadius, topMargin, xPieMitte + this.pieRadius, this.size().height - leftMargin);
        g.setColor(Color.blue);
        g.drawLine(xPieMitte, yPieMitte, xPieMitte + this.pieRadius, yPieMitte);
        g.setColor(Color.black);
        final int xEqMitte = xPieMitte + this.pieRadius + equRadius;
        final int yEqMitte = yPieMitte;
        g.drawOval(xEqMitte - equRadius, yPieMitte - equRadius, 2 * equRadius, 2 * equRadius);
        g.drawString("Equatorial Plane", xEqMitte, topMargin + 30);
        g.drawString("Horizontal Plane", xPieMitte + this.pieRadius - 100, topMargin + 30);
        alphaEqu[12] = -(this.longitude % 15.0);
        double winkel = alphaEqu[12];
        int hMittag = 12;
        if (Math.abs(this.longitude) - 15 * Math.abs(this.timezoneoffset) > 0.0) {
            hMittag = 11;
        }
        if (Math.abs(this.longitude) - 15 * Math.abs(this.timezoneoffset) <= -15.0) {
            hMittag = 13;
        }
        g.setColor(Color.red);
        if (this.latitude > 0.0) {
            g.drawLine(xEqMitte, yEqMitte, xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel)), yEqMitte - (int)Math.round(equRadius * Math.sin(K * winkel)));
        }
        else {
            g.drawLine(xEqMitte, yEqMitte, xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel)), yEqMitte + (int)Math.round(equRadius * Math.sin(K * winkel)));
        }
        if (!this.gnomon) {
            winkel = Math.atan(equRadius * Math.tan(K * winkel) / this.pieRadius) / K;
        }
        else {
            winkel = Math.atan(equRadius * Math.tan(K * winkel) / this.pieRadius) / K;
            if (winkel > 0.0) {
                winkel += Math.abs(this.latitude);
            }
            else {
                winkel -= Math.abs(this.latitude);
            }
        }
        if (this.latitude > 0.0) {
            g.drawLine(xPieMitte, yPieMitte, xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel)), yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel)));
        }
        else {
            g.drawLine(xPieMitte, yPieMitte, xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel)), yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel)));
        }
        int x = xPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.cos(K * winkel));
        int y;
        if (this.latitude > 0.0) {
            y = yPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
        }
        else {
            y = yPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
        }
        g.drawString(String.valueOf(hMittag + 1), x - 15, y);
        final int nMorgen = 7;
        final int nAbend = 7;
        g.setColor(Color.blue);
        for (int i = 1; i <= nAbend; ++i) {
            winkel = alphaEqu[12] - i * 15;
            alphaEqu[12 + i] = winkel;
            x = xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel));
            if (this.latitude < 0.0) {
                winkel = -winkel;
            }
            y = yEqMitte - (int)Math.round(equRadius * Math.sin(K * winkel));
            g.drawLine(xEqMitte, yEqMitte, x, y);
        }
        for (int j = 1; j <= nMorgen; ++j) {
            winkel = alphaEqu[12] + j * 15;
            alphaEqu[12 - j] = winkel;
            x = xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel));
            if (this.latitude < 0.0) {
                winkel = -winkel;
            }
            y = yEqMitte - (int)Math.round(equRadius * Math.sin(K * winkel));
            g.drawLine(xEqMitte, yEqMitte, x, y);
        }
        g.setColor(Color.black);
        for (int k = 1; k <= nMorgen; ++k) {
            winkel = alphaEqu[12 - k];
            x = xEqMitte - (int)Math.round(0.8 * equRadius * Math.cos(K * winkel));
            if (this.latitude > 0.0) {
                y = yEqMitte - (int)Math.round(0.8 * equRadius * Math.sin(K * winkel));
            }
            else {
                y = yEqMitte + (int)Math.round(0.8 * equRadius * Math.sin(K * winkel));
            }
            int stunde;
            if (alphaEqu[12] == 0.0) {
                stunde = hMittag - k;
            }
            else {
                stunde = hMittag - k + 1;
            }
            g.drawString(String.valueOf(stunde), x - 15, y);
        }
        g.setColor(Color.black);
        for (int l = 1; l <= nAbend; ++l) {
            winkel = alphaEqu[12 + l];
            x = xEqMitte - (int)Math.round(0.8 * equRadius * Math.cos(K * winkel));
            if (this.latitude > 0.0) {
                y = yEqMitte - (int)Math.round(0.8 * equRadius * Math.sin(K * winkel));
            }
            else {
                y = yEqMitte + (int)Math.round(0.8 * equRadius * Math.sin(K * winkel));
            }
            int stunde;
            if (alphaEqu[12] == 0.0) {
                stunde = hMittag + l;
            }
            else {
                stunde = hMittag + 1 + l;
            }
            g.drawString(String.valueOf(stunde), x - 15, y);
        }
        winkel = alphaEqu[12];
        x = xEqMitte - (int)Math.round(0.8 * equRadius * Math.cos(K * winkel));
        if (this.latitude > 0.0) {
            y = yEqMitte - (int)Math.round(0.8 * equRadius * Math.sin(K * winkel));
        }
        else {
            y = yEqMitte + (int)Math.round(0.8 * equRadius * Math.sin(K * winkel));
        }
        g.drawString(String.valueOf(hMittag + 1), x - 15, y);
        for (int m = 1; m <= nMorgen; ++m) {
            winkel = alphaEqu[12 - m];
            if (this.latitude < 0.0) {
                winkel = -winkel;
            }
            if ((int)Math.round(equRadius * Math.cos(K * winkel)) > 0) {
                g.drawLine(xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel)), yEqMitte - (int)Math.round(equRadius * Math.sin(K * winkel)), xPieMitte + this.pieRadius, yEqMitte - (int)Math.round(equRadius * Math.tan(K * winkel)));
            }
            else {
                g.drawLine(xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel)), yEqMitte - (int)Math.round(equRadius * Math.sin(K * winkel)), xEqMitte + equRadius, yEqMitte + (int)Math.round(equRadius * Math.tan(K * winkel)));
            }
        }
        for (int i2 = 1; i2 <= nAbend; ++i2) {
            winkel = alphaEqu[12 + i2];
            if (this.latitude < 0.0) {
                winkel = -winkel;
            }
            if (Math.round(equRadius * Math.cos(K * winkel)) >= 0L) {
                g.drawLine(xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel)), yEqMitte - (int)Math.round(equRadius * Math.sin(K * winkel)), xPieMitte + this.pieRadius, yEqMitte - (int)Math.round(equRadius * Math.tan(K * winkel)));
            }
            else {
                g.drawLine(xEqMitte - (int)Math.round(equRadius * Math.cos(K * winkel)), yEqMitte - (int)Math.round(equRadius * Math.sin(K * winkel)), xEqMitte + equRadius, yEqMitte + (int)Math.round(equRadius * Math.tan(K * winkel)));
            }
        }
        if (!this.gnomon) {
            for (int i3 = 1; i3 <= nMorgen; ++i3) {
                g.setColor(Color.black);
                winkel = alphaEqu[12 - i3];
                alphaPie[i3] = Math.atan(equRadius * Math.tan(K * winkel) / this.pieRadius) / K;
                winkel = alphaPie[i3];
                if (winkel > 0.0) {
                    x = xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                else {
                    x = xPieMitte - (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                g.drawLine(xPieMitte, yPieMitte, x, y);
                g.setColor(Color.blue);
                if (winkel > 0.0) {
                    if (this.latitude > 0.0) {
                        g.drawLine(x, y, xPieMitte + this.pieRadius, yPieMitte - (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                    }
                    else {
                        g.drawLine(x, y, xPieMitte + this.pieRadius, yPieMitte + (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                    }
                }
                else if (this.latitude > 0.0) {
                    g.drawLine(x, y, xPieMitte - this.pieRadius, yPieMitte + (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                }
                else {
                    g.drawLine(x, y, xPieMitte - this.pieRadius, yPieMitte - (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                }
            }
            for (int i4 = 1; i4 <= nAbend; ++i4) {
                g.setColor(Color.black);
                winkel = alphaEqu[12 + i4];
                alphaPie[i4] = Math.atan(equRadius * Math.tan(K * winkel) / this.pieRadius) / K;
                winkel = alphaPie[i4];
                if (winkel < 0.0) {
                    x = xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                else {
                    x = xPieMitte - (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                g.drawLine(xPieMitte, yPieMitte, x, y);
                g.setColor(Color.blue);
                if (winkel < 0.0) {
                    if (this.latitude > 0.0) {
                        g.drawLine(x, y, xPieMitte + this.pieRadius, yPieMitte - (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                    }
                    else {
                        g.drawLine(x, y, xPieMitte + this.pieRadius, yPieMitte + (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                    }
                }
                else if (this.latitude > 0.0) {
                    g.drawLine(x, y, xPieMitte - this.pieRadius, yPieMitte + (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                }
                else {
                    g.drawLine(x, y, xPieMitte - this.pieRadius, yPieMitte - (int)Math.round(this.pieRadius * Math.tan(K * winkel)));
                }
            }
        }
        double winkelMorgen = 0.0;
        double winkelAbend = 0.0;
        if (this.gnomon) {
            for (int i5 = 1; i5 <= nMorgen; ++i5) {
                winkel = alphaEqu[12 - i5];
                alphaPie[i5] = Math.atan(equRadius * Math.tan(K * winkel) / this.pieRadius) / K;
                alphaPie[i5] += Math.abs(this.latitude);
                winkel = alphaPie[i5];
                if (i5 == nMorgen) {
                    winkelMorgen = winkel;
                }
                if (winkel > 0.0) {
                    x = xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                else {
                    x = xPieMitte - (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                if (Math.abs(winkel) < 170.0) {
                    g.drawLine(xPieMitte, yPieMitte, x, y);
                }
                if (this.write) {
                    if (winkel > 0.0) {
                        x = xPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.cos(K * winkel));
                        if (this.latitude > 0.0) {
                            y = yPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                        else {
                            y = yPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                    }
                    else {
                        x = xPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.cos(K * winkel));
                        if (this.latitude > 0.0) {
                            y = yPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                        else {
                            y = yPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                    }
                    g.drawString(String.valueOf(hMittag + 1 - i5), x - 15, y - 15);
                }
            }
            winkel = Math.abs(this.latitude);
            g.setColor(Color.blue);
            x = xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel));
            if (this.latitude > 0.0) {
                y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
            }
            else {
                y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
            }
            g.drawLine(xPieMitte, yPieMitte, x, y);
            g.setColor(Color.magenta);
            g.drawLine(xPieMitte, yPieMitte, xPieMitte + this.pieRadius, yPieMitte);
            if (!this.write) {
                if (this.latitude > 0.0) {
                    g.drawString("peak fold pointing North", xPieMitte + 50, yPieMitte - 5);
                }
                else {
                    g.drawString("peak fold pointing South", xPieMitte + 50, yPieMitte - 5);
                }
                x = xPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.cos(K * Math.abs(this.latitude)));
                if (this.latitude > 0.0) {
                    y = yPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.sin(K * Math.abs(this.latitude)));
                }
                else {
                    y = yPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.sin(K * Math.abs(this.latitude)));
                }
                g.drawString("valley fold", x + 10, y);
                x = xPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.cos(K * Math.abs(this.latitude)));
                if (this.latitude > 0.0) {
                    y = yPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.sin(K * Math.abs(this.latitude)));
                }
                else {
                    y = yPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.sin(K * Math.abs(this.latitude)));
                }
                g.drawString("valley fold", x, y + 10);
            }
            g.setColor(Color.black);
            for (int i6 = 1; i6 <= nAbend; ++i6) {
                winkel = alphaEqu[12 + i6];
                alphaPie[i6] = Math.atan(equRadius * Math.tan(K * winkel) / this.pieRadius) / K;
                alphaPie[i6] -= Math.abs(this.latitude);
                winkel = alphaPie[i6];
                if (i6 == nAbend) {
                    winkelAbend = winkel;
                }
                if (winkel < 0.0) {
                    x = xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                else {
                    x = xPieMitte - (int)Math.round(this.pieRadius * Math.cos(K * winkel));
                    if (this.latitude > 0.0) {
                        y = yPieMitte + (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                    else {
                        y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
                    }
                }
                g.drawLine(xPieMitte, yPieMitte, x, y);
                if (this.write) {
                    if (winkel < 0.0) {
                        x = xPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.cos(K * winkel));
                        if (this.latitude > 0.0) {
                            y = yPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                        else {
                            y = yPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                    }
                    else {
                        x = xPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.cos(K * winkel));
                        if (this.latitude > 0.0) {
                            y = yPieMitte + (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                        else {
                            y = yPieMitte - (int)Math.round(0.8 * this.pieRadius * Math.sin(K * winkel));
                        }
                    }
                    g.drawString(String.valueOf(hMittag + 1 + i6), x - 15, y + 15);
                }
            }
            g.setColor(Color.lightGray);
            g.fillArc(xPieMitte - this.pieRadius, yPieMitte - this.pieRadius, 2 * this.pieRadius, 2 * this.pieRadius, (int)Math.round(winkelMorgen) + 180, (int)Math.round(Math.abs(winkelMorgen) + Math.abs(winkelAbend) - 1.0));
            winkel = -this.latitude;
            x = xPieMitte + (int)Math.round(this.pieRadius * Math.cos(K * winkel));
            y = yPieMitte - (int)Math.round(this.pieRadius * Math.sin(K * winkel));
            g.setColor(Color.blue);
            g.drawLine(xPieMitte, yPieMitte, x, y);
            g.setColor(Color.black);
            g.drawString("CUT AWAY THIS SECTOR", xPieMitte / 2 - 75, yPieMitte);
            g.drawString("Latitude  = " + Math.abs(this.latitude) + " " + this.nsStr, leftMargin, topMargin + 30);
            g.drawString("Longitude = " + Math.abs(this.longitude) + " " + this.ewStr, leftMargin, topMargin + 45);
        }
        g.clearRect(0, 0, this.size().width, topMargin + 10);
        g.setFont(new Font("Courier", 0, 10));
        final int links = 100;
        g.setColor(Color.black);
        g.clearRect(0, this.size().height - 20, this.size().width, 20);
        this.str = " - Credits: RASC Halifax Centre and Steve Lelievre";
        g.drawString(String.valueOf(this.versStr) + " -  © 2002-2008 J.Giesen  www.GeoAstro.de" + this.str, 20, this.size().height - 5);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        if (this.demo) {
            this.usrStr = "demo running";
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 150, 300);
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof TextField) {
            this.str = "";
            if (event.target == this.fieldLatDeg) {
                this.str = this.fieldLatDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c = this.str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                        this.fieldLatDeg.setText(String.valueOf(Math.abs(oldLat)));
                        return true;
                    }
                }
                if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double latDouble = Double.valueOf(this.str);
                    this.usrLat = latDouble;
                }
                this.latStr = this.str;
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                this.fieldLatDeg.setText(this.str);
                if (Math.abs(this.latitude) > 90.0) {
                    this.latitude = oldLat;
                    this.latStr = String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0));
                    this.fieldLatDeg.setText(this.latStr);
                }
                else {
                    if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                        this.latStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                this.fieldLatDeg.nextFocus();
                this.fieldLongDeg.selectAll();
                this.repaint();
                return true;
            }
            if (event.target == this.fieldLongDeg) {
                this.str = this.fieldLongDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c = this.str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                        this.fieldLongDeg.setText(String.valueOf(Math.abs(oldLong)));
                        return true;
                    }
                }
                if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double longDouble = Double.valueOf(this.str);
                    this.usrLong = longDouble;
                }
                this.longStr = this.str;
                final Double longDouble = Double.valueOf(this.str);
                this.longitude = longDouble;
                if (this.ewChoice.getSelectedItem().equals("W")) {
                    this.longitude = -Math.abs(this.longitude);
                    this.usrLong = this.longitude;
                }
                this.fieldLongDeg.setText(this.longStr);
                if (Math.abs(this.longitude) >= 180.0) {
                    this.longitude = oldLong;
                    this.longStr = String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0));
                    this.fieldLongDeg.setText(this.longStr);
                }
                else {
                    if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                        this.longStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                this.offset = this.getOffset(this.longitude);
                this.timeString = Math.abs(this.offset) + " h";
                if (this.offset >= 0) {
                    this.timeString = " +" + this.timeString;
                }
                else {
                    this.timeString = " -" + this.timeString;
                }
                this.timeChoice.select(this.timeString);
                this.timezoneoffset = this.offset;
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Checkbox) {
            if (event.target == this.gnomonBox) {
                if (this.gnomon) {
                    this.gnomon = false;
                }
                else {
                    this.gnomon = true;
                }
                this.gnomonBox.setState(this.gnomon);
                this.repaint();
                return true;
            }
            if (event.target == this.writeBox) {
                if (this.write) {
                    this.write = false;
                }
                else {
                    this.write = true;
                }
                this.writeBox.setState(this.write);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.radiusChoice) {
                if (this.radiusChoice.getSelectedItem().equals("200")) {
                    this.pieRadius = 200;
                }
                if (this.radiusChoice.getSelectedItem().equals("225")) {
                    this.pieRadius = 225;
                }
                if (this.radiusChoice.getSelectedItem().equals("250")) {
                    this.pieRadius = 250;
                }
                if (this.radiusChoice.getSelectedItem().equals("275")) {
                    this.pieRadius = 275;
                }
                if (this.radiusChoice.getSelectedItem().equals("300")) {
                    this.pieRadius = 300;
                }
                if (this.radiusChoice.getSelectedItem().equals("325")) {
                    this.pieRadius = 325;
                }
                this.repaint();
            }
            if (event.target == this.locChoice) {
                if (this.locChoice.getSelectedItem().lastIndexOf("--") != -1) {
                    this.locChoice.select(this.locString);
                    return true;
                }
                if (this.locChoice.getSelectedItem().equals(this.separator)) {
                    this.locChoice.select(this.locString);
                    return true;
                }
                if (this.locChoice.getSelectedItem().equals("Home")) {
                    this.locString = this.homeString;
                    this.locChoice.select(this.locString);
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
                }
                this.locString = this.locChoice.getSelectedItem();
                if (this.locChoice.getSelectedItem().equals(this.homeString)) {
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
                }
                else {
                    this.latitude = this.loc.getLatLong(this.locString, 1);
                    this.longitude = this.loc.getLatLong(this.locString, 2);
                }
                if (this.locChoice.getSelectedItem().equals("User Input")) {
                    this.latitude = this.usrLat;
                    this.longitude = this.usrLong;
                }
                this.latStr = String.valueOf(Math.abs(this.latitude));
                this.fieldLatDeg.setText(this.latStr);
                this.longStr = String.valueOf(Math.abs(this.longitude));
                this.fieldLongDeg.setText(this.longStr);
                if (this.longitude <= 0.0) {
                    this.ewChoice.select("W");
                }
                else {
                    this.ewChoice.select("E");
                }
                if (this.latitude <= 0.0) {
                    this.nsChoice.select("S");
                    this.nsStr = "S";
                }
                else {
                    this.nsChoice.select("N");
                    this.nsStr = "N";
                }
                this.offset = this.getOffset(this.longitude);
                this.timeString = Math.abs(this.offset) + " h";
                if (this.offset >= 0) {
                    this.timeString = " +" + this.timeString;
                }
                else {
                    this.timeString = " -" + this.timeString;
                }
                this.timeChoice.select(this.timeString);
                this.timezoneoffset = this.offset;
                this.repaint();
                return true;
            }
            else {
                if (event.target == this.ewChoice) {
                    if (!this.ewChoice.getSelectedItem().equals(this.ewStr) || !this.fieldLongDeg.getText().equals(this.longStr)) {
                        this.locChoice.select("User Input");
                        this.locString = "User Input";
                    }
                    this.ewStr = this.ewChoice.getSelectedItem();
                    if (this.longStr.length() > 0) {
                        final Double longDouble = Double.valueOf(this.longStr);
                        this.longitude = longDouble;
                    }
                    if (this.ewChoice.getSelectedItem().equals("W")) {
                        this.ewStr = "W";
                        this.longitude = -this.longitude;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
                    this.offset = this.getOffset(this.longitude);
                    this.timeString = Math.abs(this.offset) + " h";
                    if (this.offset >= 0) {
                        this.timeString = " +" + this.timeString;
                    }
                    else {
                        this.timeString = " -" + this.timeString;
                    }
                    this.timeChoice.select(this.timeString);
                    this.timezoneoffset = this.offset;
                    this.repaint();
                    return true;
                }
                if (event.target == this.nsChoice) {
                    if (!this.nsChoice.getSelectedItem().equals(this.nsStr) || !this.fieldLatDeg.getText().equals(this.latStr)) {
                        this.locChoice.select("User Input");
                        this.locString = "User Input";
                    }
                    this.nsStr = this.nsChoice.getSelectedItem();
                    if (this.latStr.length() > 0) {
                        final Double latDouble = Double.valueOf(this.latStr);
                        this.latitude = latDouble;
                    }
                    if (this.nsChoice.getSelectedItem().equals("S")) {
                        this.nsStr = "S";
                        this.latitude = -this.latitude;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.timeString = this.timeChoice.getSelectedItem();
                    this.timezoneoffset = this.loc.getTimeZone(this.timeString);
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                    return true;
                }
            }
        }
        return true;
    }
    
    public PieSundial082() {
        this.versStr = "PieSundial Applet 0.82";
        this.demo = true;
        this.online = false;
        this.gnomon = false;
        this.write = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.usrStr = "demo";
        this.userString = "?";
        this.separator = "---------------";
        this.pieRadius = 300;
    }
}
