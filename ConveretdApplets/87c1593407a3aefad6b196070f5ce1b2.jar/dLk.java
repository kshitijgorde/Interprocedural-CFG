import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class dLk implements ActionListener
{
    private final rush l;
    
    dLk(final rush l) {
        this.l = l;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        rush.bDhblf(this.l, actionEvent);
    }
}
