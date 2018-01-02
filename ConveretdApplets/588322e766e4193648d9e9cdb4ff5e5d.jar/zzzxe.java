import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class zzzxe extends Frame
{
    zzzpb zzzue;
    anagram zzzve;
    Button zzzwe;
    
    public zzzxe(final anagram zzzve) {
        this.zzzue = new zzzpb();
        this.zzzve = zzzve;
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        (this.zzzwe = new Button("OK")).setBackground(zzznc.zzzyb);
        this.zzzwe.setForeground(zzznc.zzzac);
        panel.add(this.zzzwe);
        this.add("South", panel);
        this.add("Center", this.zzzue);
    }
    
    public void zzzse() {
        this.resize(512, 360);
        this.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("OK")) {
            this.hide();
            return this.zzzve.zzzjd = true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return this.zzzve.zzzjd = true;
        }
        return super.handleEvent(event);
    }
    
    public void zzzte(final String title, final String s, final String s2, final String s3, final String s4) {
        this.setTitle(title);
        this.setBackground(Color.red);
        this.zzzue.zzzjb(s, s2, s3, s4);
    }
}
