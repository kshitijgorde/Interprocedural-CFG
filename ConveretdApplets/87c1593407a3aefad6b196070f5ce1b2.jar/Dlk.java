import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Dlk implements ActionListener
{
    private final rush l;
    
    Dlk(final rush l) {
        this.l = l;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        rush.Bdhblf(this.l, actionEvent);
    }
}
