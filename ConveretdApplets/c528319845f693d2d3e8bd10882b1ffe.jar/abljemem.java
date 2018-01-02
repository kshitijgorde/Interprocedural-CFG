import java.awt.Event;
import java.awt.Toolkit;
import java.awt.Label;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class abljemem extends Frame
{
    Panel a;
    Button b;
    Dimension c;
    Dimension d;
    int e;
    static Color f;
    
    abljemem(final String s, String substring) {
        super("Ended");
        this.e = substring.length();
        if (this.e > 8 && substring.substring(this.e - 8).equals("<BR><BR>")) {
            substring = substring.substring(0, this.e - 8);
        }
        if (abljemem.f != null) {
            this.setBackground(abljemem.f);
        }
        this.setLayout(new BorderLayout());
        (this.a = new Panel()).setLayout(new FlowLayout());
        this.b = new Button("OK");
        this.a.add(this.b);
        this.add("North", new Label(new String("   ").concat(s)));
        this.add("Center", new Label(new String("   ").concat(substring)));
        this.add("South", this.a);
        this.pack();
        this.d = Toolkit.getDefaultToolkit().getScreenSize();
        this.c = this.size();
        this.move((this.d.width - this.c.width) / 2, (this.d.height - this.c.height) / 2);
        this.pack();
        this.show();
        this.requestFocus();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.b) {
            this.dispose();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
    
    static {
        abljemem.f = null;
    }
}
