import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class msgbox extends Frame
{
    msgbox(final String s, final String s2) {
        super(s);
        this.setLayout(new BorderLayout());
        this.add("Center", new TextArea(s2));
        this.add("South", new Button("OK"));
    }
    
    public boolean action(final Event event, final Object o) {
        this.hide();
        return true;
    }
}
