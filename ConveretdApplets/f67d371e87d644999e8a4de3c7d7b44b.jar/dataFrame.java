import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class dataFrame extends Frame
{
    int locOffset;
    int date;
    int month;
    int year;
    double latitude;
    double longitude;
    double height;
    
    public dataFrame(final String versStr, final int d, final int m, final int y, final int offset, final double lat, final double longi, final int dt, final boolean online, final double H) {
        this.locOffset = offset;
        this.date = d;
        this.month = m;
        this.year = y;
        this.latitude = lat;
        this.longitude = longi;
        this.height = H;
        final char deg = '°';
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        String latStr = new StringBuffer().append(Math.abs(this.latitude)).append(deg).toString();
        if (this.latitude >= 0.0) {
            latStr = String.valueOf(latStr) + " N";
        }
        else {
            latStr = String.valueOf(latStr) + " S";
        }
        String longStr = new StringBuffer().append(Math.abs(this.longitude)).append(deg).toString();
        if (this.longitude >= 0.0) {
            longStr = String.valueOf(longStr) + " E";
        }
        else {
            longStr = String.valueOf(longStr) + " W";
        }
        final String titleString = String.valueOf(latStr) + ", " + longStr + ", " + (this.year + 1900) + " " + monthArray[this.month] + " " + this.date;
        this.setTitle(titleString);
        final BorderLayout bl = new BorderLayout();
        this.setLayout(bl);
        final TextArea ta = new TextArea("", 30, 10, 1);
        ta.setFont(new Font("Courier", 0, 10));
        this.add(ta, "Center");
        String timeString = String.valueOf(this.locOffset) + " h";
        if (this.locOffset > 0) {
            timeString = "+" + timeString;
        }
        timeString = "UT " + timeString;
        ta.append("SunShadow" + versStr + " - (c) J. Giesen - www.GeoAstro.de" + "\n");
        ta.append(String.valueOf(titleString) + ", " + timeString + "\n" + "\n");
        final Compute comp = new Compute();
        final double K = 0.017453292519943295;
        if (dt == 0) {
            ta.append("Azim.   Elev.  Local Time   Shadow Length\n");
            ta.append("  °       °                      m\n");
            final int delta = 5;
            for (int i = 0; i < 72; ++i) {
                final double elev = this.rechneElevAusAzim(i * delta, 1);
                if (elev > 0.0) {
                    String str1 = String.valueOf(Math.round(10.0 * elev) / 10.0);
                    if (elev < 10.0) {
                        str1 = " " + str1;
                    }
                    String str2 = String.valueOf(i * delta);
                    if (i * delta < 100) {
                        str2 = " " + str2;
                    }
                    final String str3 = " " + this.makeTimeString(this.rechneElevAusAzim(i * delta, 2));
                    final String str4 = "          " + Math.round(100.0 * this.height / Math.tan(0.017453292519943295 * elev)) / 100.0;
                    if (online && i % 2 == 0) {
                        ta.append("online demo\n");
                    }
                    else {
                        ta.append(str2 + "     " + str1 + "    " + str3 + str4 + "\n");
                    }
                }
            }
        }
        else {
            final int di = (int)(1440.0 / dt);
            ta.append("         Elev.     Azim.   dAzim.    Shadow Length\n");
            double sum = 0.0;
            int n = 0;
            String str5 = "";
            for (int j = -this.locOffset; j < -this.locOffset + di; ++j) {
                double jd = comp.JD(this.date, this.month + 1, this.year + 1900, j * dt / 60.0);
                final double dec = comp.DeclinationRightAscension(1, jd);
                final double GHA = comp.computeGHA(this.date, this.month + 1, this.year + 1900, j * dt / 60.0);
                final double hoehe = comp.computeHeight(dec, this.latitude, this.longitude, GHA);
                if (hoehe > 0.0) {
                    String str1 = new StringBuffer().append(Math.round(10.0 * hoehe) / 10.0).append(deg).toString();
                    if (hoehe < 10.0) {
                        str1 = " " + str1;
                    }
                    final double az = comp.computeAzimut(dec, this.latitude, this.longitude, GHA, hoehe);
                    if (j > 0) {
                        jd = comp.JD(this.date, this.month + 1, this.year + 1900, (j - 1) * dt / 60.0);
                        final double dec2 = comp.DeclinationRightAscension(1, jd);
                        final double GHA2 = comp.computeGHA(this.date, this.month + 1, this.year + 1900, (j - 1) * dt / 60.0);
                        final double hoehe2 = comp.computeHeight(dec2, this.latitude, this.longitude, GHA2);
                        final double az2 = comp.computeAzimut(dec2, this.latitude, this.longitude, GHA2, hoehe2);
                        str5 = "    " + Math.round(10.0 * (az - az2)) / 10.0 + deg;
                        sum += az - az2;
                        ++n;
                    }
                    String str2 = new StringBuffer().append(Math.round(10.0 * az) / 10.0).append(deg).toString();
                    if (az < 10.0) {
                        str2 = " " + str2;
                    }
                    if (az < 100.0) {
                        str2 = " " + str2;
                    }
                    final String str6 = "        " + Math.round(100.0 * this.height / Math.tan(0.017453292519943295 * hoehe)) / 100.0;
                    String str7 = this.makeTimeString(j * dt / 60.0 + this.locOffset) + "    " + str1 + "    " + str2 + str5 + str6 + "\n";
                    if (online && j % 3 == 0) {
                        str7 = "online demo\n";
                    }
                    ta.append(str7);
                }
            }
            sum /= n;
            String str7 = new StringBuffer().append(Math.round(10.0 * sum) / 10.0).append(deg).toString();
            ta.append("Mean                        " + str7);
        }
        this.repaint();
    }
    
    public double rechneElevAusAzim(final int azim, final int what) {
        double ut1 = 0.0;
        int min1 = 0;
        final Compute comp = new Compute();
        final double[] az = new double[52];
        for (int i = -this.locOffset; i < -this.locOffset + 50; ++i) {
            final double jd = comp.JD(this.date, this.month + 1, this.year + 1900, i * 0.5);
            final double dec = comp.DeclinationRightAscension(1, jd);
            final double GHA = comp.computeGHA(this.date, this.month + 1, this.year + 1900, i * 0.5);
            final double hoehe = comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            az[i + this.locOffset] = comp.computeAzimut(dec, this.latitude, this.longitude, GHA, hoehe);
        }
        double time1;
        if (this.latitude >= 0.0) {
            for (int j = 1; j < 50; ++j) {
                if (az[j] > azim && az[j - 1] < azim) {
                    ut1 = 0.5 * j;
                    break;
                }
            }
            ut1 -= 2.0;
            for (int k = 0; k <= 240; ++k) {
                final double jd = comp.JD(this.date, this.month + 1, this.year + 1900, ut1 + k / 120.0);
                final double dec = comp.DeclinationRightAscension(1, jd);
                final double GHA = comp.computeGHA(this.date, this.month + 1, this.year + 1900, ut1 + k / 120.0);
                final double hoehe = comp.computeHeight(dec, this.latitude, this.longitude, GHA);
                final double AZ = comp.computeAzimut(dec, this.latitude, this.longitude, GHA, hoehe);
                if (AZ > azim) {
                    min1 = k;
                    break;
                }
            }
            time1 = ut1 + min1 / 120.0 - 0.004166666666666667;
        }
        else {
            for (int j = 1; j < 50; ++j) {
                if (az[j] < azim && az[j - 1] > azim) {
                    ut1 = 0.5 * j;
                    break;
                }
            }
            ut1 -= 2.0;
            for (int k = 0; k <= 240; ++k) {
                final double jd = comp.JD(this.date, this.month + 1, this.year + 1900, ut1 + k / 120.05);
                final double dec = comp.DeclinationRightAscension(1, jd);
                final double GHA = comp.computeGHA(this.date, this.month + 1, this.year + 1900, ut1 + k / 120.0);
                final double hoehe = comp.computeHeight(dec, this.latitude, this.longitude, GHA);
                final double AZ = comp.computeAzimut(dec, this.latitude, this.longitude, GHA, hoehe);
                if (AZ < azim) {
                    min1 = k;
                    break;
                }
            }
            time1 = ut1 + min1 / 120.0 - 0.004166666666666667;
        }
        final double jd = comp.JD(this.date, this.month + 1, this.year + 1900, time1);
        final double dec = comp.DeclinationRightAscension(1, jd);
        final double GHA = comp.computeGHA(this.date, this.month + 1, this.year + 1900, time1);
        final double hoehe = comp.computeHeight(dec, this.latitude, this.longitude, GHA);
        if (what == 1) {
            return hoehe;
        }
        return time1 + this.locOffset;
    }
    
    public String makeTimeString(double time) {
        String str = "?";
        if (time < 0.0) {
            time += 24.0;
        }
        if (time > 24.0) {
            time -= 24.0;
        }
        final double diff = time - (int)time;
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++time;
        }
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + str + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        return str;
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
