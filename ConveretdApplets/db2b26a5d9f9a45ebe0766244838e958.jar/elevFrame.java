import java.awt.Event;
import java.awt.Component;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class elevFrame extends Frame
{
    TextField fieldAz;
    final double K = 0.017453292519943295;
    final char deg = '°';
    double ELEV;
    double Jd0;
    compute comp;
    double latitude;
    double longitude;
    int locOffset;
    Label L1;
    Label L2;
    
    public elevFrame(final String titleStr, final double jd0, final double LAT, final double LONG, final int offset, final boolean demo) {
        this.comp = new compute();
        this.setBackground(Color.white);
        final BorderLayout bl = new BorderLayout();
        final Panel p = new Panel();
        this.fieldAz = new TextField(6);
        final Label L = new Label("Enter elevation (decimal) and press return");
        p.add(L);
        p.add(this.fieldAz);
        p.add(this.L1 = new Label("                                                                                "));
        p.add(this.L2 = new Label("                                                                                "));
        this.add(p);
        this.Jd0 = jd0;
        this.latitude = LAT;
        this.longitude = LONG;
        this.locOffset = offset;
        String offsetStr = ",  UT ";
        if (this.locOffset >= 0) {
            offsetStr = String.valueOf(offsetStr) + "+";
        }
        offsetStr = String.valueOf(offsetStr) + this.locOffset + " h";
        this.setTitle(String.valueOf(titleStr) + offsetStr);
    }
    
    double THETA0(final double jd) {
        final double T = (jd - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (jd - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
    }
    
    public double azimuth(final double jd, final double ra, final double dec, final double latitude, final double longitude) {
        final double H = this.THETA0(jd) + longitude - 15.0 * ra;
        return 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * latitude) - Math.tan(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * latitude)) / 0.017453292519943295;
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        double tau = 15.0 * (this.comp.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof TextField) {
            final String str = this.fieldAz.getText();
            for (int i = 0; i < str.length(); ++i) {
                final char c = str.charAt(i);
                if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                    this.fieldAz.setText("");
                    return true;
                }
            }
            final Double azDouble = Double.valueOf(str);
            this.ELEV = azDouble;
            this.find();
        }
        return true;
    }
    
    public void find() {
        double az = 0.0;
        double jdFin = 0.0;
        int n = 0;
        final double[] JD = new double[100];
        final double[] AZ = new double[100];
        for (int i = 0; i <= 1440; ++i) {
            final double jd = this.Jd0 + i / 1440.0;
            final double dec = this.comp.sunDecRA(jd, 1);
            final double ra = this.comp.sunDecRA(jd, 2);
            final double elev = this.sun_elev(jd, this.latitude, -this.longitude, dec, ra);
            if (Math.abs(elev - this.ELEV) < 0.4) {
                JD[n] = jd;
                AZ[n] = elev;
                ++n;
            }
        }
        for (int j = 0; j < n; ++j) {
            if (AZ[j] < this.ELEV && AZ[j + 1] > this.ELEV) {
                final double delta = (this.ELEV - AZ[j]) / (AZ[j + 1] - AZ[j]);
                jdFin = JD[j] + delta / 1440.0;
                break;
            }
        }
        double dec = this.comp.sunDecRA(jdFin, 1);
        double ra = this.comp.sunDecRA(jdFin, 2);
        az = this.azimuth(jdFin, ra, dec, this.latitude, this.longitude);
        double elev = this.sun_elev(jdFin, this.latitude, -this.longitude, dec, ra);
        if (n > 1) {
            this.L1.setText(String.valueOf(this.calDat(jdFin + this.locOffset / 24.0)) + "  Elev=" + Math.round(10.0 * elev) / 10.0 + '°' + "  Az=" + Math.round(10.0 * az) / 10.0 + '°' + "  Solar Time: " + this.calDatHourApp(jdFin + this.locOffset / 24.0));
        }
        for (int k = 0; k < n; ++k) {
            if (AZ[k] > this.ELEV && AZ[k + 1] < this.ELEV) {
                final double delta = (this.ELEV - AZ[k]) / (AZ[k + 1] - AZ[k]);
                jdFin = JD[k] + delta / 1440.0;
                break;
            }
        }
        dec = this.comp.sunDecRA(jdFin, 1);
        ra = this.comp.sunDecRA(jdFin, 2);
        az = this.azimuth(jdFin, ra, dec, this.latitude, this.longitude);
        elev = this.sun_elev(jdFin, this.latitude, -this.longitude, dec, ra);
        if (n > 1) {
            this.L2.setText(String.valueOf(this.calDat(jdFin + this.locOffset / 24.0)) + "  Elev=" + Math.round(10.0 * elev) / 10.0 + '°' + "  Az=" + Math.round(10.0 * az) / 10.0 + '°' + "  Solar Time: " + this.calDatHourApp(jdFin + this.locOffset / 24.0));
        }
        this.repaint();
    }
    
    public String calDat(final double jd) {
        final double JD0 = (int)(jd + 0.5);
        final double hour = 24.0 * (jd + 0.5 - JD0);
        return this.comp.makeTimeString(hour);
    }
    
    public String calDatHourApp(final double jd) {
        final double JD0 = (int)(jd + 0.5);
        double hour = 24.0 * (jd + 0.5 - JD0);
        hour = hour - this.locOffset + this.comp.eot(jd) / 60.0 + this.longitude / 15.0;
        return this.comp.makeTimeString(hour);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
