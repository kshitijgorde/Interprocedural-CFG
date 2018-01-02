import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class dataFrame extends Frame
{
    public dataFrame(final String titleStr, final String versStr, final double[][] AzElevData, final int nData, final boolean demo) {
        this.setTitle(titleStr);
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final TextArea ta = new TextArea("", 30, 10, 1);
        ta.setFont(new Font("Courier", 0, 10));
        this.add(ta, "Center");
        ta.append(String.valueOf(versStr) + "\n");
        ta.append("(c) J. Giesen - www.GeoAstro.de\n");
        ta.append("         Azimuth    Elevation\n");
        for (int i = 0; i < nData; ++i) {
            final String str1 = monthArray[(int)AzElevData[i][0]];
            final int d = (int)AzElevData[i][1];
            String str2 = String.valueOf(d);
            if (d < 10) {
                str2 = " " + str2;
            }
            String str3 = String.valueOf(Math.round(10.0 * AzElevData[i][2]) / 10.0);
            String str4;
            if (demo) {
                if (i % 4 == 0) {
                    str3 = "demo";
                }
                else {
                    str3 = String.valueOf(Math.round(10.0 * AzElevData[i][2]) / 10.0);
                }
                if (i % 5 == 0) {
                    str4 = "demo";
                }
                else {
                    str4 = String.valueOf(Math.round(10.0 * AzElevData[i][3]) / 10.0);
                }
            }
            else {
                str3 = String.valueOf(Math.round(10.0 * AzElevData[i][2]) / 10.0);
                str4 = String.valueOf(Math.round(10.0 * AzElevData[i][3]) / 10.0);
            }
            ta.append(String.valueOf(str1) + " " + str2 + "     " + str3 + "      " + str4 + "\n");
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
