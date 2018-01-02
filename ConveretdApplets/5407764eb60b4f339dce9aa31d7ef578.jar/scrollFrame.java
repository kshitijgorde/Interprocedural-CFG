import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class scrollFrame extends Frame
{
    Button okButton;
    
    public scrollFrame(final String titleStr, final int n, final String[] Str, final boolean online) {
        this.setTitle(titleStr);
        final BorderLayout bl = new BorderLayout();
        this.setLayout(bl);
        final TextArea ta = new TextArea("", 30, 10, 1);
        ta.setFont(new Font("Courier", 0, 10));
        this.add(ta, "Center");
        for (int i = 0; i <= n; ++i) {
            if (Str[i] != null && online && i % 3 == 0) {
                Str[i] = " D E M O\n";
            }
        }
        for (int j = 0; j <= n; ++j) {
            if (Str[j] != null) {
                ta.append(Str[j]);
            }
        }
        this.add(this.okButton = new Button("hide window"), "South");
        this.pack();
        this.repaint();
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Button && evt.target == this.okButton) {
            this.hide();
        }
        return true;
    }
}
