import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class durationFrame extends Frame
{
    TextArea ta;
    final double K = 0.017453292519943295;
    final char deg = '°';
    String[] monthArray;
    int month;
    int year;
    int date;
    double[] Lat;
    double latitude;
    double TLAT;
    compute comp;
    
    public durationFrame(final Date dat, final double LAT) {
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.Lat = new double[50];
        this.month = dat.getMonth();
        this.date = dat.getDate();
        this.year = dat.getYear();
        this.latitude = LAT;
        final String dateStr = this.year + 1900 + ", " + this.monthArray[this.month] + " " + this.date;
        this.setTitle("Duration and Latitude " + dateStr);
        this.duration();
        (this.ta = new TextArea("", 30, 10, 1)).setFont(new Font("Courier", 0, 10));
        this.add(this.ta, "Center");
        this.schreiben();
        final drawDurationFrame drawf = new drawDurationFrame(dateStr, this.Lat, this.TLAT, LAT);
        drawf.resize(570, 450);
        drawf.show();
    }
    
    public void duration() {
        final double startLat = -90.0;
        final double cosZ = Math.cos(1.5853401554517652);
        final double dT = 0.05;
        this.comp = new compute();
        final double delta = this.comp.sunDecRA(1, this.comp.JD(this.date, this.month + 1, this.year + 1900, 12.0));
        final double sinDelta = Math.sin(0.017453292519943295 * delta);
        final double cosDelta = Math.cos(0.017453292519943295 * delta);
        for (int t = 1; t <= 47; ++t) {
            for (int i = 0; i < 180000; ++i) {
                final double L = startLat + i * 0.001;
                final double T = 0.13333333333333333 * Math.acos((cosZ - Math.sin(0.017453292519943295 * L) * sinDelta) / (Math.cos(0.017453292519943295 * L) * cosDelta)) / 0.017453292519943295;
                if (Math.abs(T - t * 0.5) < dT) {
                    this.Lat[t] = L;
                    break;
                }
            }
        }
        for (int i = 0; i < 1800000; ++i) {
            final double L = startLat + 1.0E-4 * i;
            final double T = 0.13333333333333333 * Math.acos((cosZ - Math.sin(0.017453292519943295 * L) * sinDelta) / (Math.cos(0.017453292519943295 * L) * cosDelta)) / 0.017453292519943295;
            if (T > 24.0 - dT) {
                this.Lat[48] = L;
                break;
            }
        }
        for (int j = 0; j < 1800000; ++j) {
            final double L = startLat + 1.0E-4 * j;
            final double T = 0.13333333333333333 * Math.acos((cosZ - Math.sin(0.017453292519943295 * L) * sinDelta) / (Math.cos(0.017453292519943295 * L) * cosDelta)) / 0.017453292519943295;
            if (T < dT) {
                this.Lat[0] = L;
                break;
            }
        }
        this.TLAT = 0.13333333333333333 * Math.acos((cosZ - Math.sin(0.017453292519943295 * this.latitude) * sinDelta) / (Math.cos(0.017453292519943295 * this.latitude) * cosDelta)) / 0.017453292519943295;
    }
    
    public void schreiben() {
        for (int t = 0; t <= 48; ++t) {
            this.ta.append("Duration of Daylight " + t * 0.5 + " Hours: " + Math.round(100.0 * this.Lat[t]) / 100.0 + '°' + "\n");
        }
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
