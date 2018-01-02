import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class bk implements ActionListener
{
    private ah a;
    
    public bk(final ah a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.c();
        this.a.b();
    }
}
