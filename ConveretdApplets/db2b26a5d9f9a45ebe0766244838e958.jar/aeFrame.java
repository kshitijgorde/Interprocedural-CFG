import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class aeFrame extends Frame
{
    double K;
    final char deg = '°';
    int N;
    int[] az;
    int[] elev;
    int[][] AZ;
    int[][] ELEV;
    int[][] AZ1;
    int[][] ELEV1;
    boolean demo;
    boolean sun;
    boolean large;
    double currentAzim;
    double currentElev;
    double time;
    double transTime;
    double transElev;
    String[] monthArray;
    String titleString;
    Checkbox JanJun;
    Checkbox JulDec;
    Checkbox JanDec;
    Checkbox Ana;
    Checkbox More;
    boolean janjun;
    boolean juldec;
    boolean ana;
    int nData;
    int locOffset;
    double latitude;
    double longitude;
    boolean month1_6;
    int year;
    boolean more;
    
    public aeFrame(final boolean largo, final String titleStr, final int YEAR, final double LAT, final double LONG, final double[] azData, final double[] elevData, final int n, final int y, final double trans, final double transAlt, final boolean isSun, final double curAz, final double curElev, final double UT, final int offset, final boolean isDemo) {
        this.K = 0.017453292519943295;
        this.az = new int[600];
        this.elev = new int[600];
        this.AZ = new int[12][100];
        this.ELEV = new int[12][100];
        this.AZ1 = new int[12][100];
        this.ELEV1 = new int[12][100];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.janjun = true;
        this.juldec = true;
        this.ana = true;
        this.more = false;
        this.setTitle(titleStr);
        this.setBackground(Color.white);
        this.JanJun = new Checkbox("Dec-Jun");
        this.JulDec = new Checkbox("Jun-Dec");
        this.Ana = new Checkbox("Analemma");
        this.More = new Checkbox("More");
        this.nData = n;
        this.locOffset = offset;
        this.latitude = LAT;
        this.longitude = LONG;
        this.year = YEAR;
        this.N = this.nData;
        this.transTime = trans;
        this.demo = isDemo;
        this.transElev = transAlt;
        this.sun = isSun;
        this.currentAzim = curAz;
        this.currentElev = curElev;
        this.time = UT + offset;
        this.transTime = trans;
        this.large = largo;
        this.titleString = titleStr;
        if (largo) {
            final GridBagLayout gbl = new GridBagLayout();
            final GridBagConstraints gbc = new GridBagConstraints();
            this.setLayout(gbl);
            gbc.weightx = 1.0;
            gbc.weighty = 0.0;
            this.JanJun.setState(this.janjun);
            gbc.insets = new Insets(5, 20, 0, 0);
            gbl.setConstraints(this.JanJun, gbc);
            this.add(this.JanJun);
            this.JulDec.setState(this.juldec);
            gbc.insets = new Insets(5, 0, 0, 0);
            gbl.setConstraints(this.JulDec, gbc);
            this.add(this.JulDec);
            this.More.setState(this.more);
            gbc.insets = new Insets(5, 0, 0, 0);
            gbl.setConstraints(this.More, gbc);
            this.add(this.More);
            this.Ana.setState(this.ana);
            gbc.insets = new Insets(5, 0, 0, 0);
            gbl.setConstraints(this.Ana, gbc);
            this.add(this.Ana);
            gbc.gridy = 1;
            gbc.weighty = 160.0;
            final Canvas myCan = new Canvas();
            gbl.setConstraints(myCan, gbc);
            this.add(myCan);
            this.janjun = true;
            this.juldec = true;
            this.computeData();
            this.computeData1();
            this.N = this.nData;
        }
        else {
            if (this.titleString.indexOf("Jan") != -1) {
                this.month1_6 = true;
            }
            else {
                this.month1_6 = false;
            }
            for (int i = 0; i <= this.nData; ++i) {
                this.az[i] = (int)Math.round(azData[i]);
                this.elev[i] = (int)Math.round(2.0 * elevData[i]);
            }
        }
        this.repaint();
    }
    
    public void computeData() {
        final compute comp = new compute();
        for (int m = 0; m <= 11; ++m) {
            for (int i = -this.locOffset * 4; i < -this.locOffset * 4 + 96; ++i) {
                final double Jd = this.JD(21, m + 1, this.year, i * 0.25);
                final double dec = comp.sunDecRA(Jd, 1);
                final double ra = comp.sunDecRA(Jd, 2);
                final double gha = comp.sun_GHA(Jd, ra);
                final double hoehe = comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra);
                final double azim = comp.computeAzimut(dec, this.latitude, this.longitude, gha, hoehe);
                this.AZ[m][i + this.locOffset * 4] = (int)Math.round(2.0 * azim);
                this.ELEV[m][i + this.locOffset * 4] = (int)Math.round(5.0 * hoehe);
            }
        }
    }
    
    public void computeData1() {
        final compute comp = new compute();
        for (int m = 0; m <= 11; ++m) {
            for (int i = -this.locOffset * 4; i < -this.locOffset * 4 + 96; ++i) {
                final double Jd = this.JD(6, m + 1, this.year, i * 0.25);
                final double dec = comp.sunDecRA(Jd, 1);
                final double ra = comp.sunDecRA(Jd, 2);
                final double gha = comp.sun_GHA(Jd, ra);
                final double hoehe = comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra);
                final double azim = comp.computeAzimut(dec, this.latitude, this.longitude, gha, hoehe);
                this.AZ1[m][i + this.locOffset * 4] = (int)Math.round(2.0 * azim);
                this.ELEV1[m][i + this.locOffset * 4] = (int)Math.round(5.0 * hoehe);
            }
        }
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    public void paint(final Graphics g) {
        int y0 = 400;
        if (this.large) {
            y0 = 500;
        }
        final int links = 30;
        final compute comp = new compute();
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        int dy = 0;
        if (!this.large) {
            dy = 30;
        }
        else {
            dy = 75;
        }
        int X = 0;
        if (this.large) {
            X = 720;
        }
        else {
            X = 360;
        }
        if (this.large) {
            g.setColor(Color.gray);
            for (int a = 0; a < 18; ++a) {
                g.drawLine(links, y0 - a * 25, links + X, y0 - a * 25);
            }
        }
        g.setColor(Color.darkGray);
        for (int a = 0; a < 13; ++a) {
            g.drawLine(links, y0 - a * dy, links + X, y0 - a * dy);
        }
        int dx = 0;
        if (!this.large) {
            dx = 30;
        }
        else {
            dx = 60;
        }
        int Y = 0;
        if (!this.large) {
            Y = 360;
        }
        else {
            Y = 450;
        }
        if (this.large) {
            g.setColor(Color.gray);
            for (int n = 0; n < 36; ++n) {
                g.drawLine(links + n * 20, y0, links + n * 20, y0 - Y);
            }
        }
        g.setColor(Color.darkGray);
        for (int n = 0; n < 13; ++n) {
            g.drawLine(links + n * dx, y0, links + n * dx, y0 - Y);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        for (int i = -6; i <= 6; ++i) {
            if (!this.large) {
                g.drawString(new StringBuffer().append(i * 15).append('°').toString(), links - 25, y0 - 180 - i * 30 + 2);
            }
            else {
                g.drawString(new StringBuffer().append(i * 15).append('°').toString(), links - 25, y0 - i * 75 + 2);
            }
        }
        g.drawString("Elevation", links, 37);
        g.drawString("Azimuth", links + X + 10, y0 + 4);
        for (int j = 0; j <= 12; ++j) {
            if (!this.large) {
                g.drawString(new StringBuffer().append(30 * j).append('°').toString(), links + j * 30 - 9, y0 + 15);
            }
            else {
                g.drawString(new StringBuffer().append(30 * j).append('°').toString(), links + j * 60 - 9, y0 + 15);
            }
        }
        g.setColor(Color.blue);
        if (!this.large) {
            g.drawLine(links, y0 - 180, links + 360, y0 - 180);
        }
        g.setColor(Color.red);
        if (!this.large) {
            g.drawLine(links + 180, y0, links + 180, y0 - 360);
            g.drawLine(links, y0 - 180, links + 360, y0 - 180);
        }
        final boolean north = this.titleString.indexOf("N") != -1;
        g.setColor(Color.blue);
        if (this.large) {
            g.drawString("Dec 21", links + 360 - 15, y0 - this.ELEV[11][48] + 13);
            g.drawString("Jun 21", links + 360 - 15, y0 - this.ELEV[5][48] - 5);
            if (this.janjun) {
                for (int m = 0; m < 5; ++m) {
                    g.drawString(String.valueOf(this.monthArray[m]) + " 21", links + 360 - 35, y0 - this.ELEV[m][48] - 5);
                }
                if (this.more) {
                    g.setColor(Color.darkGray);
                    for (int k = 0; k < 5; ++k) {
                        g.drawString(String.valueOf(this.monthArray[k]) + " 6", links + 360 - 35, y0 - this.ELEV1[k][48] - 5);
                    }
                }
            }
            if (this.juldec) {
                g.setColor(Color.blue);
                for (int m = 6; m <= 10; ++m) {
                    g.drawString(String.valueOf(this.monthArray[m]) + " 21", links + 360 + 10, y0 - this.ELEV[m][48] - 5);
                }
                if (this.more) {
                    g.setColor(Color.darkGray);
                    for (int k = 6; k <= 10; ++k) {
                        g.drawString(String.valueOf(this.monthArray[k]) + " 6", links + 360 + 10, y0 - this.ELEV1[k][48] - 5);
                    }
                }
            }
            g.setColor(Color.red);
            for (int l = -7; l <= 8; ++l) {
                if (l < 0) {
                    g.drawString(String.valueOf(12 + l), links + this.AZ[5][48 + l * 4] - 15, y0 - this.ELEV[5][48 + l * 4] - 5);
                }
                if (l > 0) {
                    g.drawString(String.valueOf(12 + l), links + this.AZ[5][48 + l * 4] + 5, y0 - this.ELEV[5][48 + l * 4] - 5);
                }
            }
            g.setColor(Color.blue);
            if (this.janjun) {
                for (int i2 = 1; i2 < 96; ++i2) {
                    g.drawLine(links + this.AZ[11][i2 - 1], y0 - this.ELEV[11][i2 - 1], links + this.AZ[11][i2], y0 - this.ELEV[11][i2]);
                }
                for (int m2 = 0; m2 <= 5; ++m2) {
                    for (int i3 = 1; i3 < 96; ++i3) {
                        g.drawLine(links + this.AZ[m2][i3 - 1], y0 - this.ELEV[m2][i3 - 1], links + this.AZ[m2][i3], y0 - this.ELEV[m2][i3]);
                    }
                }
                if (this.more) {
                    g.setColor(Color.darkGray);
                    for (int m3 = 0; m3 <= 5; ++m3) {
                        for (int i4 = 1; i4 < 96; ++i4) {
                            g.drawLine(links + this.AZ1[m3][i4 - 1], y0 - this.ELEV1[m3][i4 - 1], links + this.AZ1[m3][i4], y0 - this.ELEV1[m3][i4]);
                        }
                    }
                }
            }
            g.setColor(Color.blue);
            if (this.juldec) {
                for (int k = 5; k <= 11; ++k) {
                    for (int i5 = 1; i5 < 96; ++i5) {
                        g.drawLine(links + this.AZ[k][i5 - 1], y0 - this.ELEV[k][i5 - 1], links + this.AZ[k][i5], y0 - this.ELEV[k][i5]);
                    }
                }
                if (this.more) {
                    g.setColor(Color.darkGray);
                    for (int m2 = 5; m2 <= 11; ++m2) {
                        for (int i3 = 1; i3 < 96; ++i3) {
                            g.drawLine(links + this.AZ1[m2][i3 - 1], y0 - this.ELEV1[m2][i3 - 1], links + this.AZ1[m2][i3], y0 - this.ELEV1[m2][i3]);
                        }
                    }
                }
            }
            g.setColor(Color.red);
            if (this.ana) {
                for (int i2 = -7; i2 <= 8; ++i2) {
                    if (this.janjun) {
                        g.drawLine(links + this.AZ[11][48 + i2 * 4], y0 - this.ELEV[11][48 + i2 * 4], links + this.AZ[0][48 + i2 * 4], y0 - this.ELEV[0][48 + i2 * 4]);
                        for (int m2 = 0; m2 < 5; ++m2) {
                            g.drawLine(links + this.AZ[m2][48 + i2 * 4], y0 - this.ELEV[m2][48 + i2 * 4], links + this.AZ[m2 + 1][48 + i2 * 4], y0 - this.ELEV[m2 + 1][48 + i2 * 4]);
                        }
                    }
                    if (this.juldec) {
                        for (int m2 = 5; m2 < 11; ++m2) {
                            g.drawLine(links + this.AZ[m2][48 + i2 * 4], y0 - this.ELEV[m2][48 + i2 * 4], links + this.AZ[m2 + 1][48 + i2 * 4], y0 - this.ELEV[m2 + 1][48 + i2 * 4]);
                        }
                    }
                }
            }
            else {
                for (int i2 = -7; i2 <= 8; ++i2) {
                    g.drawLine(links + this.AZ[11][48 + i2 * 4], y0 - this.ELEV[11][48 + i2 * 4], links + this.AZ[5][48 + i2 * 4], y0 - this.ELEV[5][48 + i2 * 4]);
                }
            }
            g.clearRect(20, y0 + 1, 730, 80);
            g.setColor(Color.black);
            for (int i2 = 0; i2 <= 12; ++i2) {
                g.drawString(new StringBuffer().append(30 * i2).append('°').toString(), links + i2 * 60 - 9, y0 + 15);
            }
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Helvetica", 0, 10));
        if (!this.large) {
            for (int l = 0; l < this.nData; ++l) {
                g.drawOval(links + this.az[l], y0 - 180 - this.elev[l] - 1, 1, 1);
                if (l % 96 == 0 && north && this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
                if (l % 96 == 48 && !north && this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
                if (l % 96 == 0 && north && this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
                if (l % 96 == 48 && !north && this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
                if (l % 96 == 0 && north && !this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[6 + l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
                if (l % 96 == 48 && !north && !this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[6 + l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
                if (l % 96 == 0 && north && !this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[6 + l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
                if (l % 96 == 48 && !north && !this.month1_6) {
                    g.drawString(String.valueOf(this.monthArray[6 + l / 96]) + " 21", links + 360 + 10, y0 - 180 - this.elev[l] + 3);
                }
            }
            if (this.N < 100) {
                final int y2 = (int)Math.round(2.0 * this.transElev);
                g.setColor(Color.black);
                g.drawString(new StringBuffer().append(Math.round(10.0 * this.transElev) / 10.0).append('°').toString(), links + 375, y0 - 180 - y2 + 4);
                g.drawString("Trans.", links + 375, y0 - 180 - y2 - 18);
                g.drawString(comp.makeTimeString(this.transTime), links + 375, y0 - 180 - y2 - 7);
                g.drawLine(links + 360, y0 - 180 - y2, links + 370, y0 - 180 - y2);
            }
            if (this.sun) {
                int azSun = (int)Math.round(this.currentAzim);
                final int elevSun = (int)Math.round(2.0 * this.currentElev);
                g.setColor(Color.yellow);
                g.fillOval(links + azSun - 6, y0 - 180 - elevSun - 6, 12, 12);
                g.setColor(Color.black);
                g.drawOval(links + azSun - 6, y0 - 180 - elevSun - 6, 12, 12);
                g.setColor(Color.blue);
                final int y3 = (int)Math.round(2.0 * this.currentElev);
                g.drawString(new StringBuffer().append(Math.round(10.0 * this.currentElev) / 10.0).append('°').toString(), links + 415, y0 - 180 - y3 + 5);
                g.drawLine(links + 360, y0 - 180 - y3, links + 370, y0 - 180 - y3);
                g.setColor(Color.red);
                azSun = (int)Math.round(this.currentAzim);
                g.drawString(new StringBuffer().append(Math.round(10.0 * this.currentAzim) / 10.0).append('°').toString(), links + azSun - 15, y0 + 30);
            }
        }
        g.setColor(Color.black);
        g.drawRect(links, y0 - Y, X, Y);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 100));
            g.setColor(Color.red);
            g.drawString("D E M O", 40, 300);
        }
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.JanJun) {
                this.janjun ^= true;
                this.JanJun.setState(this.janjun);
            }
            if (event.target == this.JulDec) {
                this.juldec ^= true;
                this.JulDec.setState(this.juldec);
            }
            if (event.target == this.More) {
                this.more ^= true;
                this.More.setState(this.more);
            }
            if (event.target == this.Ana) {
                this.ana ^= true;
                this.Ana.setState(this.ana);
            }
            this.repaint();
            return true;
        }
        return true;
    }
}
