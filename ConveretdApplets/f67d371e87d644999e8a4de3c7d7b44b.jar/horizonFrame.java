import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Image;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class horizonFrame extends Frame
{
    boolean isOnline;
    Image horizonImg;
    int xSun;
    int hS;
    double hSun;
    double ph;
    int NorthSouth;
    TextField fieldHeight;
    Button runButton;
    Label L1;
    Label L2;
    Label L3;
    Label L4;
    Label L5;
    Label L6;
    Label L7;
    Label L8;
    Label L9;
    Compute comp;
    int date;
    int month;
    int year;
    double UT;
    int hours;
    int minutes;
    int seconds;
    int locOffset;
    double latitude;
    double longitude;
    char deg;
    Suncanvas myCan;
    double hTrans;
    TextField fieldShadow;
    final double K = 0.017453292519943295;
    double height;
    double hoeheTrans;
    TextField fieldAzimuth;
    
    public horizonFrame(final Date DAT, final double LAT, final double LONG, final int offset, final double HTRANS, final double HEIGHT) {
        this.deg = '°';
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.latitude = LAT;
        this.longitude = LONG;
        this.date = DAT.getDate();
        this.month = DAT.getMonth();
        this.year = DAT.getYear();
        this.hours = DAT.getHours();
        this.minutes = DAT.getMinutes();
        this.locOffset = offset;
        this.hTrans = HTRANS;
        this.height = HEIGHT;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0;
        String latStr = new StringBuffer().append(Math.abs(this.latitude)).append(this.deg).toString();
        if (this.latitude >= 0.0) {
            latStr = String.valueOf(latStr) + "N";
        }
        else {
            latStr = String.valueOf(latStr) + "S";
        }
        String longStr = new StringBuffer().append(Math.abs(this.longitude)).append(this.deg).toString();
        if (this.longitude >= 0.0) {
            longStr = String.valueOf(longStr) + "E";
        }
        else {
            longStr = String.valueOf(longStr) + "W";
        }
        String str = "UT";
        if (offset >= 0) {
            str = String.valueOf(str) + "+" + offset + "h";
        }
        else {
            str = String.valueOf(str) + offset + "h";
        }
        this.setTitle(String.valueOf(latStr) + ", " + longStr + ",  " + (this.year + 1900) + " " + monthArray[this.month] + " " + this.date + ",  " + str);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 5.0;
        gbc.weighty = 2.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 1;
        gbl.setConstraints(this.L1 = new Label("Enter elevation < " + Math.floor(10.0 * this.hTrans) / 10.0 + this.deg), gbc);
        this.add(this.L1);
        gbc.gridx = 2;
        gbl.setConstraints(this.fieldHeight = new TextField("", 6), gbc);
        this.add(this.fieldHeight);
        gbc.gridx = 3;
        gbl.setConstraints(this.L2 = new Label("   AM      "), gbc);
        this.add(this.L2);
        gbc.gridx = 4;
        gbl.setConstraints(this.L3 = new Label("   PM      "), gbc);
        this.add(this.L3);
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbl.setConstraints(this.L4 = new Label("enter shadow length > " + Math.ceil(10.0 * this.height / Math.tan(0.017453292519943295 * this.hTrans)) / 10.0 + " m"), gbc);
        this.add(this.L4);
        gbc.gridx = 2;
        gbl.setConstraints(this.fieldShadow = new TextField("", 6), gbc);
        this.add(this.fieldShadow);
        gbc.gridx = 3;
        gbl.setConstraints(this.L5 = new Label("   AM      "), gbc);
        this.add(this.L5);
        gbc.gridx = 4;
        gbl.setConstraints(this.L6 = new Label("   PM      "), gbc);
        this.add(this.L6);
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbl.setConstraints(this.L7 = new Label("enter azimuth"), gbc);
        this.add(this.L7);
        gbc.gridx = 2;
        gbl.setConstraints(this.fieldAzimuth = new TextField("", 6), gbc);
        this.add(this.fieldAzimuth);
        gbc.gridx = 3;
        gbl.setConstraints(this.L8 = new Label("   AM/PM"), gbc);
        this.add(this.L8);
        gbc.gridy = 4;
        gbc.gridx = 2;
        gbl.setConstraints(this.L9 = new Label("Press RETURN !"), gbc);
        this.add(this.L9);
        this.setLocation(200, 400);
        this.repaint();
    }
    
    public void rechneHoehe(final double elev) {
        int ut1 = 0;
        int min1 = 0;
        int ut2 = 0;
        int min2 = 0;
        this.comp = new Compute();
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, i);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            if (hoehe > elev) {
                ut1 = i;
                break;
            }
        }
        --ut1;
        for (int j = -10; j <= 70; ++j) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, ut1 + j / 60.0);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, ut1 + j / 60.0);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            if (hoehe > elev) {
                min1 = j;
                break;
            }
        }
        final double time1 = ut1 + min1 / 60.0 - 0.008333333333333333 + this.locOffset;
        this.L2.setText(this.makeTimeString(time1));
        for (int k = -this.locOffset; k < -this.locOffset + 24; ++k) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, k);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, k);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            if (hoehe < elev && k > ut1 + 1) {
                ut2 = k;
                break;
            }
        }
        --ut2;
        for (int l = 0; l <= 120; ++l) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, ut2 + l / 120.0);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, ut2 + l / 120.0);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            if (hoehe < elev && l > ut2 + 0.5) {
                min2 = l;
                break;
            }
        }
        final double time2 = ut2 + min2 / 120.0 - 0.004166666666666667 + this.locOffset;
        this.L3.setText(this.makeTimeString(time2));
    }
    
    public void rechneLaenge(final double laenge) {
        int ut1 = 0;
        int min1 = 0;
        int ut2 = 0;
        int min2 = 0;
        double shadow = 1.0E7;
        this.comp = new Compute();
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, i);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            shadow = this.height / Math.tan(0.017453292519943295 * hoehe);
            if (shadow > 0.0 && shadow < laenge) {
                ut1 = i;
                break;
            }
        }
        --ut1;
        for (int j = -10; j <= 70; ++j) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, ut1 + j / 60.0);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, ut1 + j / 60.0);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            shadow = this.height / Math.tan(0.017453292519943295 * hoehe);
            if (shadow > 0.0 && shadow < laenge) {
                min1 = j;
                break;
            }
        }
        final double time1 = ut1 + min1 / 60.0 - 0.008333333333333333 + this.locOffset;
        this.L5.setText(this.makeTimeString(time1));
        ut1 = (int)(time1 - this.locOffset + 1.0);
        System.out.println(ut1);
        for (int k = ut1; k < ut1 + 12; ++k) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, k);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, k);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            shadow = this.height / Math.tan(0.017453292519943295 * hoehe);
            if (shadow > 0.0 && shadow > laenge) {
                ut2 = k;
                break;
            }
        }
        --ut2;
        for (int l = 0; l <= 120; ++l) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, ut2 + l / 120.0);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, ut2 + l / 120.0);
            double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            hoehe += this.comp.refract(hoehe);
            shadow = this.height / Math.tan(0.017453292519943295 * hoehe);
            if (hoehe > 0.0 && shadow > laenge) {
                min2 = l;
                break;
            }
        }
        final double time2 = ut2 + min2 / 120.0 - 0.004166666666666667 + this.locOffset;
        this.L6.setText(this.makeTimeString(time2));
    }
    
    public void rechneAzim(final double azim) {
        double ut1 = 0.0;
        int min1 = 0;
        this.comp = new Compute();
        final double[] az = new double[27];
        for (int i = -this.locOffset; i < -this.locOffset + 26; ++i) {
            final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, i);
            final double dec = this.comp.DeclinationRightAscension(1, jd);
            final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i);
            final double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
            az[i + this.locOffset] = this.comp.computeAzimut(dec, this.latitude, this.longitude, GHA, hoehe);
        }
        double time1;
        if (this.latitude >= 0.0) {
            for (int j = 1; j < 26; ++j) {
                if (az[j] > azim && az[j - 1] < azim) {
                    ut1 = j;
                    break;
                }
            }
            ut1 = ut1 - 2.0 - this.locOffset;
            for (int k = 0; k <= 240; ++k) {
                final double UT = ut1 + k / 60.0;
                final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, UT);
                final double dec = this.comp.DeclinationRightAscension(1, jd);
                final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, UT);
                final double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
                final double AZ = this.comp.computeAzimut(dec, this.latitude, this.longitude, GHA, hoehe);
                if (AZ > azim) {
                    min1 = k;
                    break;
                }
            }
            time1 = ut1 + min1 / 60.0 - 0.008333333333333333 + this.locOffset;
        }
        else {
            for (int j = 1; j < 26; ++j) {
                if (az[j] < azim && az[j - 1] > azim) {
                    ut1 = j;
                    break;
                }
            }
            ut1 -= 2.0;
            for (int k = 0; k <= 240; ++k) {
                final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, ut1 + k / 120.0);
                final double dec = this.comp.DeclinationRightAscension(1, jd);
                final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, ut1 + k / 120.0);
                final double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
                final double AZ = this.comp.computeAzimut(dec, this.latitude, this.longitude, GHA, hoehe);
                if (AZ < azim) {
                    min1 = k;
                    break;
                }
            }
            time1 = ut1 + min1 / 120.0 - 0.004166666666666667 + this.locOffset;
        }
        this.L8.setText(this.makeTimeString(time1));
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
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof TextField) {
            String str = "";
            final char c1 = ',';
            if (event.target == this.fieldHeight) {
                str = this.fieldHeight.getText();
                if (str.length() == 0 || str.charAt(0) == '.' || str.charAt(0) == ',') {
                    this.fieldHeight.setText("");
                    return true;
                }
                for (int i = 0; i < str.length(); ++i) {
                    final char c2 = str.charAt(i);
                    if (c2 == ',') {
                        str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                    }
                }
                for (int j = 0; j < str.length(); ++j) {
                    final char c2 = str.charAt(j);
                    if (c2 != '0' && c2 != '1' && c2 != '2' && c2 != '3' && c2 != '4' && c2 != '5' && c2 != '6' && c2 != '7' && c2 != '8' && c2 != '9' && c2 != '.') {
                        this.fieldHeight.setText("");
                        return true;
                    }
                }
                final Double elevValue = Double.valueOf(str);
                final double elev = elevValue;
                if (elev < this.hTrans) {
                    this.rechneHoehe(elev);
                }
                else {
                    this.fieldHeight.setText("");
                    this.L2.setText("   AM      ");
                    this.L3.setText("   PM      ");
                }
                this.repaint();
                return true;
            }
            else if (event.target == this.fieldShadow) {
                str = this.fieldShadow.getText();
                if (str.length() == 0 || str.charAt(0) == '.' || str.charAt(0) == ',') {
                    this.fieldShadow.setText("");
                    return true;
                }
                for (int i = 0; i < str.length(); ++i) {
                    final char c2 = str.charAt(i);
                    if (c2 == ',') {
                        str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                    }
                }
                for (int j = 0; j < str.length(); ++j) {
                    final char c2 = str.charAt(j);
                    if (c2 != '0' && c2 != '1' && c2 != '2' && c2 != '3' && c2 != '4' && c2 != '5' && c2 != '6' && c2 != '7' && c2 != '8' && c2 != '9' && c2 != '.') {
                        this.fieldShadow.setText("");
                        return true;
                    }
                }
                final Double lengthValue = Double.valueOf(str);
                final double laenge = lengthValue;
                if (laenge > this.height / Math.tan(0.017453292519943295 * this.hTrans)) {
                    this.rechneLaenge(laenge);
                }
                else {
                    this.fieldShadow.setText("");
                    this.L5.setText("   AM      ");
                    this.L6.setText("   PM      ");
                }
                this.repaint();
                return true;
            }
            else if (event.target == this.fieldAzimuth) {
                str = this.fieldAzimuth.getText();
                if (str.length() == 0 || str.charAt(0) == '.' || str.charAt(0) == ',') {
                    this.fieldAzimuth.setText("");
                    return true;
                }
                for (int i = 0; i < str.length(); ++i) {
                    final char c2 = str.charAt(i);
                    if (c2 == ',') {
                        str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                    }
                }
                for (int j = 0; j < str.length(); ++j) {
                    final char c2 = str.charAt(j);
                    if (c2 != '0' && c2 != '1' && c2 != '2' && c2 != '3' && c2 != '4' && c2 != '5' && c2 != '6' && c2 != '7' && c2 != '8' && c2 != '9' && c2 != '.') {
                        this.fieldAzimuth.setText("");
                        return true;
                    }
                }
                final Double azValue = Double.valueOf(str);
                final double azim = azValue;
                if (azim <= 360.0) {
                    this.rechneAzim(azim);
                }
                else {
                    this.fieldAzimuth.setText("");
                    this.L8.setText("   AM/PM");
                }
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 200);
        }
    }
}
