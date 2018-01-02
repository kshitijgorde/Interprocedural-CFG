import java.awt.Event;
import java.awt.Component;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class gtFrame extends Frame
{
    TextField fieldAlt;
    TextField fieldAz;
    final double K = 0.017453292519943295;
    final char deg = '°';
    double azimuth;
    double Jd0;
    compute comp;
    double latitude;
    double longitude;
    int locOffset;
    Label L1;
    
    public gtFrame() {
        this.comp = new compute();
        final BorderLayout bl = new BorderLayout();
        final Panel p = new Panel();
        this.fieldAlt = new TextField(6);
        final Label L = new Label("Enter latitude");
        final Label L2 = new Label("Enter longitude");
        p.add(L);
        p.add(this.fieldAlt);
        p.add(L2);
        p.add(this.fieldAz = new TextField(6));
        this.add(p);
        this.setTitle("HALLO");
    }
    
    public double getAz() {
        return this.azimuth;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof TextField && event.target == this.fieldAz) {
            final String str = this.fieldAz.getText();
            for (int i = 0; i < str.length(); ++i) {
                final char c = str.charAt(i);
                if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                    this.fieldAz.setText("");
                    return true;
                }
            }
            final Double azDouble = Double.valueOf(str);
            this.azimuth = azDouble;
            this.setTitle(String.valueOf(this.azimuth));
        }
        return true;
    }
    
    public String calDat(final double jd) {
        final double JD0 = (int)(jd + 0.5);
        double hour = 24.0 * (jd + 0.5 - JD0);
        int min = (int)Math.round(60.0 * this.comp.frac(hour));
        if (min == 60) {
            min = 0;
            ++hour;
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        String hourStr = String.valueOf((int)hour) + str + min;
        if ((int)hour < 10) {
            hourStr = "0" + hourStr;
        }
        return hourStr;
    }
    
    public boolean handleEvent(final Event e) {
        return e.id == 201 || super.handleEvent(e);
    }
}
