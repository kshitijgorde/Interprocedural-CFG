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
    TextArea ta;
    
    public scrollFrame(final String titleStr, final int n, final String[] Str, final boolean online) {
        this.setTitle(titleStr);
        (this.ta = new TextArea("", 30, 10, 1)).setFont(new Font("Courier", 0, 10));
        this.add(this.ta, "Center");
        for (int i = 0; i <= n; ++i) {
            if (Str[i] != null && online && i % 3 == 0) {
                Str[i] = " D E M O\n";
            }
        }
        for (int j = 0; j <= n; ++j) {
            if (Str[j] != null) {
                this.ta.append(Str[j]);
            }
        }
    }
    
    public void schreiben(final String[] Str, final int n) {
        for (int i = 0; i <= n; ++i) {
            this.ta.append(Str[i]);
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
