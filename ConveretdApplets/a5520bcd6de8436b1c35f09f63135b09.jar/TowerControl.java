import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class TowerControl extends Panel
{
    TextField s;
    TextField e;
    TowerPanel pa;
    
    public TowerControl(final TowerPanel pa) {
        this.pa = pa;
        this.add(this.s = new TextField("8", 4));
        this.add(new Button("Reset Blocks Number"));
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.pa.reset(((String)o).equals("Reset Blocks Number"), Integer.parseInt(this.s.getText().trim()));
            return true;
        }
        return false;
    }
}
