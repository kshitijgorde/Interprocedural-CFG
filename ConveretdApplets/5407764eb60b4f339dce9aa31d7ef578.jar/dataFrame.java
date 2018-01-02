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
    public dataFrame(final String ptStr, final String titleStr, final String versStr, final String[] Data, final int nData, final boolean demo) {
        this.setTitle(titleStr);
        final TextArea ta = new TextArea("", 30, 10, 1);
        ta.setFont(new Font("Courier", 0, 10));
        this.add(ta, "Center");
        ta.append(String.valueOf(versStr) + "\n");
        ta.append("(c) 2003-2011 J. Giesen - www.GeoAstro.de\n");
        ta.append("\n");
        ta.append(String.valueOf(ptStr) + "\n" + "\n");
        for (int i = 0; i <= nData; ++i) {
            if (demo && i / 12 % 2 == 0 && i > 0) {
                Data[i] = " demo";
            }
            ta.append(String.valueOf(Data[i]) + "\n");
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
