import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_bn extends rp_cx implements ActionListener
{
    private rp_bn(final byte b) {
    }
    
    protected final void a(final List list) {
        int n = 0;
        try {
            for (final Runnable runnable : list) {
                ++n;
                runnable.run();
            }
        }
        finally {
            if (n < list.size()) {
                final Runnable[] array = new Runnable[list.size() - n];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = list.get(n + i);
                }
                this.a(true, (Object[])array);
            }
        }
    }
    
    protected final void a() {
        final Timer timer;
        (timer = new Timer(33, this)).setRepeats(false);
        timer.start();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.run();
    }
}
