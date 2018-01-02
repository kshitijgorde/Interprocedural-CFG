import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class DLk implements ActionListener
{
    private final rush l;
    
    DLk(final rush l) {
        this.l = l;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.l.bdhbLF) {
            this.l.bdhblf(actionEvent);
        }
    }
}
