import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class bq implements ActionListener
{
    private bm a;
    
    public bq(final bm a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.a(true);
        if (this.a.b) {
            this.a.y.setVisible(true);
        }
    }
}
