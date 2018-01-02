import java.awt.Event;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class zzzsf extends Frame
{
    private Button zzzqf;
    private Button zzzrf;
    
    public zzzsf(final Button zzzqf) {
        this.setTitle("A propos d'Anagrammes");
        this.setBackground(Color.orange);
        this.zzzqf = zzzqf;
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        (this.zzzrf = new Button("OK")).setBackground(zzznc.zzzyb);
        this.zzzrf.setForeground(zzznc.zzzac);
        panel.add(this.zzzrf);
        this.add("South", panel);
        this.add("Center", new zzzib());
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("OK")) {
            this.zzzqf.enable();
            this.hide();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.zzzqf.enable();
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
}
