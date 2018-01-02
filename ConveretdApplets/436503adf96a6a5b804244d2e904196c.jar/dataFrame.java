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
    public dataFrame(final String titleStr, final String versStr, final String[] data, final int nData, final boolean demo) {
        this.setTitle(titleStr);
        final TextArea ta = new TextArea("", 30, 10, 1);
        ta.setFont(new Font("Courier", 0, 10));
        this.add(ta, "Center");
        ta.append(String.valueOf(versStr) + "\n");
        ta.append("(c) 2006-2010 J. Giesen  -  www.GeoAstro.de\n\n");
        if (demo) {
            for (int i = 0; i <= nData; ++i) {
                if (i % 5 == 1) {
                    data[i] = "D E M O\n";
                }
            }
        }
        for (int i = 0; i <= nData; ++i) {
            ta.append(data[i]);
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