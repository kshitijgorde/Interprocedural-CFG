import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class scrollFrame extends Frame
{
    public scrollFrame(final String titleStr, final int n, final double[][] data, final boolean online) {
        this.setTitle(titleStr);
        final TextArea ta = new TextArea("", 30, 10, 1);
        ta.setFont(new Font("Courier", 0, 10));
        this.add(ta, "Center");
        ta.append(" UT          x         y       Azim      Elev\n");
        double maxX = 0.0;
        double maxY = 0.0;
        double minY = 0.0;
        for (int i = 0; i <= n; ++i) {
            String str1;
            for (str1 = String.valueOf(data[i][1]); str1.length() < 8; str1 = " " + str1) {}
            String str2;
            for (str2 = String.valueOf(data[i][2]); str2.length() < 8; str2 = " " + str2) {}
            String str3;
            for (str3 = String.valueOf(Math.round(100.0 * data[i][3]) / 100.0); str3.length() < 8; str3 = " " + str3) {}
            String str4;
            for (str4 = String.valueOf(Math.round(100.0 * data[i][4]) / 100.0); str4.length() < 8; str4 = " " + str4) {}
            ta.append(String.valueOf(this.calDat(data[i][0])) + "  " + str1 + "  " + str2 + "  " + str3 + "  " + str4 + "\n");
            if (data[i][1] > maxX) {
                maxX = data[i][1];
            }
            if (data[i][2] > maxY) {
                maxY = data[i][2];
            }
            if (data[i][2] < minY) {
                minY = data[i][2];
            }
        }
        ta.append("max. East:  " + Math.round(100.0 * maxX) / 100.0 + " km" + "\n");
        ta.append("max. North: " + Math.round(100.0 * maxY) / 100.0 + " km" + "\n");
        ta.append("max. South: " + Math.round(100.0 * minY) / 100.0 + " km" + "\n");
        this.repaint();
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public String calDat(final double jd) {
        final double JD0 = (int)(jd + 0.5);
        double hour = 24.0 * (jd + 0.5 - JD0);
        int min = (int)Math.round(60.0 * this.frac(hour));
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
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
}
