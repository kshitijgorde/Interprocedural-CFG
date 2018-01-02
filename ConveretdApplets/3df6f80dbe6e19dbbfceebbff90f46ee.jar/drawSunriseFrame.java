import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class drawSunriseFrame extends Frame
{
    double[][] hRISE;
    final char deg = '°';
    double latitude;
    Checkbox box;
    Checkbox allBox;
    Choice rsChoice;
    boolean show;
    final double K = 0.017453292519943295;
    final String[] monthArray;
    compute comp;
    int Year;
    double UTRISE;
    double UTSET;
    double zero1;
    double zero2;
    double HOUR;
    double YE;
    int NZ;
    boolean RISE;
    boolean SETT;
    boolean rise;
    boolean set;
    boolean online;
    double longitude;
    int locOffset;
    boolean all;
    
    public drawSunriseFrame(final int year, final double lat, final double longi, final int offset, final boolean on) {
        this.hRISE = new double[400][20];
        this.show = true;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.rise = true;
        this.set = false;
        this.all = true;
        String str = String.valueOf(Math.abs(longi));
        if (longi >= 0.0) {
            str = String.valueOf(str) + "° E";
        }
        else {
            str = String.valueOf(str) + "° W";
        }
        this.setTitle(year + "  Sunrise or Sunset  " + str);
        this.setLocation(250, 200);
        this.Year = year;
        this.latitude = lat;
        this.longitude = longi;
        this.locOffset = offset;
        this.online = on;
        this.comp = new compute();
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 0, 0, 0);
        gbc.gridy = 0;
        gbl.setConstraints(this.box = new Checkbox("Show Lat. " + this.latitude + '°'), gbc);
        this.box.setState(this.show);
        this.add(this.box);
        gbl.setConstraints(this.allBox = new Checkbox("Show All"), gbc);
        this.allBox.setState(this.all);
        this.add(this.allBox);
        gbl.setConstraints(this.rsChoice = new Choice(), gbc);
        this.rsChoice.add("Sunrise");
        this.rsChoice.add("Sunset");
        this.add(this.rsChoice);
        gbc.gridy = 1;
        gbc.weighty = 160.0;
        final Canvas can = new Canvas();
        gbl.setConstraints(can, gbc);
        this.add(can);
        this.calculate();
    }
    
    public void calculate() {
        int F;
        if (this.latitude >= 0.0) {
            F = 1;
        }
        else {
            F = -1;
        }
        for (int L = 0; L <= 8; ++L) {
            double LAT;
            if (L < 7) {
                LAT = F * L * 10;
            }
            else if (L == 7) {
                LAT = F * 65.0;
            }
            else {
                LAT = this.latitude;
            }
            int k = 0;
            for (int m = 0; m < 12; ++m) {
                for (int d = 1; d <= this.comp.daysInMonth(m, this.Year); ++d) {
                    this.RISE = false;
                    this.SETT = false;
                    if (this.rise) {
                        for (int i = 1; i < 24; ++i) {
                            this.sunRiseSet(d, m + 1, this.Year, i, LAT, this.longitude);
                            if (this.RISE) {
                                break;
                            }
                        }
                        if (this.RISE) {
                            this.hRISE[k][L] = this.UTRISE + this.locOffset;
                        }
                    }
                    if (this.set) {
                        for (int i = 1; i < 24; ++i) {
                            this.sunRiseSet(d, m + 1, this.Year, i, LAT, this.longitude);
                            if (this.SETT) {
                                break;
                            }
                        }
                        if (this.SETT) {
                            this.hRISE[k][L] = this.UTSET + this.locOffset;
                        }
                    }
                    ++k;
                }
            }
        }
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 800);
        final int x0 = 40;
        final int y0 = 630;
        final int F = -1;
        int DT;
        if (this.rise) {
            DT = 0;
        }
        else {
            DT = 12;
        }
        int f;
        if (this.latitude >= 0.0) {
            f = 1;
        }
        else {
            f = -1;
        }
        g.setFont(new Font("Helvetica", 0, 11));
        g.setColor(Color.black);
        g.drawLine(x0, y0 - 576, x0, y0);
        g.setColor(Color.gray);
        for (int i = 0; i <= 48; ++i) {
            if (i % 2 == 0) {
                g.setColor(Color.gray);
            }
            else {
                g.setColor(Color.lightGray);
            }
            g.drawLine(x0, y0 - i * 12, x0 + 730, y0 - i * 12);
            g.setColor(Color.black);
            if (i % 4 == 0) {
                if (this.rise) {
                    g.drawString((int)(i * 0.25) + ":00", x0 - 30, y0 - i * 12 + 5);
                }
                else {
                    g.drawString((int)(12.0 + i * 0.25) + ":00", x0 - 30, y0 - i * 12 + 5);
                }
            }
        }
        int x2 = x0;
        g.setColor(Color.lightGray);
        for (int j = 1; j <= 5; ++j) {
            final int x3 = x0 + 10 * j;
            g.drawLine(x3, y0, x3, y0 - 576);
        }
        for (int m = 0; m < 12; ++m) {
            x2 += 2 * this.comp.daysInMonth(m, this.Year);
            if (m < 11) {
                for (int k = 1; k <= 5; ++k) {
                    final int x3 = x2 + 10 * k;
                    g.setColor(Color.lightGray);
                    g.drawLine(x3, y0, x3, y0 - 576);
                }
            }
            g.setColor(Color.gray);
            g.drawLine(x2, y0, x2, y0 - 576);
            g.setColor(Color.black);
            g.drawString(this.monthArray[m], x2 - 40, y0 + 20);
        }
        g.setColor(Color.black);
        g.drawLine(x0, y0, x0 + 730, y0);
        if (this.all) {
            for (int L = 0; L <= 7; ++L) {
                if (L % 2 == 0) {
                    g.setColor(Color.blue);
                }
                else {
                    g.setColor(Color.black);
                }
                if (L < 7) {
                    g.drawString(new StringBuffer().append(f * L * 10).append('°').toString(), x0 + 740, y0 - (int)Math.round(48 * F * (DT - this.hRISE[364][L])));
                }
                if (L == 7) {
                    g.drawString(new StringBuffer().append(f * 65).append('°').toString(), x0 + 740, y0 - (int)Math.round(48 * F * (DT - this.hRISE[364][L])));
                }
                for (int n = 0; n < 364; ++n) {
                    x2 = x0 + 2 * n;
                    final int y2 = y0 - (int)Math.round(48 * F * (DT - this.hRISE[n][L]));
                    final int x3 = x0 + 2 * (n + 1);
                    final int y3 = y0 - (int)Math.round(48 * F * (DT - this.hRISE[n + 1][L]));
                    g.drawLine(x2, y2, x3, y3);
                }
            }
        }
        if (this.show) {
            g.setColor(Color.red);
            for (int n2 = 0; n2 < 364; ++n2) {
                x2 = x0 + 2 * n2;
                final int y2 = y0 - (int)Math.round(48 * F * (DT - this.hRISE[n2][8]));
                final int x3 = x0 + 2 * (n2 + 1);
                final int y3 = y0 - (int)Math.round(48 * F * (DT - this.hRISE[n2 + 1][8]));
                g.drawLine(x2, y2, x3, y3);
            }
        }
        if (this.online) {
            g.setFont(new Font("Helvetica", 0, 36));
            g.drawString("O N L I N E", 300, y0 - 120);
        }
    }
    
    public void sunRiseSet(final int DATE, final int MONTH, final int YEAR, final double HOUR, final double latitude, final double longitude) {
        final double sh = Math.sin(-0.014538592669112765);
        final double jd = this.comp.JD(DATE, MONTH, YEAR, HOUR);
        double dec = this.comp.sunDecRA(1, jd);
        double ra = this.comp.sunDecRA(2, jd);
        final double Y0 = this.comp.sin_elev(jd, latitude, -longitude, dec, ra) - sh;
        final double jdPlus = this.comp.JD(DATE, MONTH, YEAR, HOUR) + 0.041666666666666664;
        dec = this.comp.sunDecRA(1, jdPlus);
        ra = this.comp.sunDecRA(2, jdPlus);
        final double yPlus = this.comp.sin_elev(jdPlus, latitude, -longitude, dec, ra) - sh;
        final double jdMinus = this.comp.JD(DATE, MONTH, YEAR, HOUR) - 0.041666666666666664;
        dec = this.comp.sunDecRA(1, jdMinus);
        ra = this.comp.sunDecRA(2, jdMinus);
        final double yMinus = this.comp.sin_elev(jdMinus, latitude, -longitude, dec, ra) - sh;
        final boolean ABOVE = yMinus > 0.0;
        this.QUAD(Y0, yMinus, yPlus);
        switch (this.NZ) {
            case 1: {
                if (yMinus < 0.0) {
                    this.UTRISE = HOUR + this.zero1;
                    this.RISE = true;
                    break;
                }
                this.UTSET = HOUR + this.zero1;
                this.SETT = true;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = HOUR + this.zero2;
                    this.UTSET = HOUR + this.zero1;
                }
                else {
                    this.UTRISE = HOUR + this.zero1;
                    this.UTSET = HOUR + this.zero2;
                }
                this.RISE = true;
                this.SETT = true;
                break;
            }
        }
    }
    
    public void QUAD(final double Y0, final double yMinus, final double yPlus) {
        this.NZ = 0;
        final double A = 0.5 * (yMinus + yPlus) - Y0;
        final double B = 0.5 * (yPlus - yMinus);
        final double XE = -B / (2.0 * A);
        this.YE = (A * XE + B) * XE + Y0;
        final double DIS = B * B - 4.0 * A * Y0;
        if (DIS >= 0.0) {
            final double DX = 0.5 * Math.sqrt(DIS) / Math.abs(A);
            this.zero1 = XE - DX;
            this.zero2 = XE + DX;
            if (Math.abs(this.zero1) <= 1.0) {
                ++this.NZ;
            }
            if (Math.abs(this.zero2) <= 1.0) {
                ++this.NZ;
            }
            if (this.zero1 < -1.0) {
                this.zero1 = this.zero2;
            }
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.box) {
                this.show ^= true;
            }
            if (event.target == this.allBox) {
                this.all ^= true;
            }
            this.repaint();
        }
        if (event.target instanceof Choice && event.target == this.rsChoice) {
            if (this.rsChoice.getSelectedItem().equals("Sunrise")) {
                this.rise = true;
                this.set = false;
            }
            if (this.rsChoice.getSelectedItem().equals("Sunset")) {
                this.rise = false;
                this.set = true;
            }
            this.calculate();
            this.repaint();
        }
        return true;
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
