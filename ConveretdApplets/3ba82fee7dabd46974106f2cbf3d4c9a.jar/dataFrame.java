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
    public dataFrame(final String titleStr, final String versStr, final String str, final String[] Data, final int nData, final boolean demo) {
        this.setTitle(titleStr);
        final TextArea ta = new TextArea("", 30, 10, 1);
        ta.setFont(new Font("Courier", 0, 10));
        this.add(ta, "Center");
        ta.append(String.valueOf(versStr) + "\n");
        ta.append("(c) 2006-2008 J. Giesen - www.GeoAstro.de\n");
        ta.append(String.valueOf(str) + "\n");
        ta.append("Magnitude    Obscuration (%)\n");
        for (int i = 0; i < nData; ++i) {
            if (demo && i % 4 == 0) {
                Data[i] = " demo\n";
            }
            ta.append(Data[i]);
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
