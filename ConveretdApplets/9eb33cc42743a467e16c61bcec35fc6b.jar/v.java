import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class v implements ItemListener
{
    public final void itemStateChanged(final ItemEvent itemEvent) {
        String s;
        int n;
        for (s = (String)itemEvent.getItem(), n = 0; n < x.fb.length && !s.equals(x.fb[n]); ++n) {}
        if (n > 0) {
            x.ff.setText(String.valueOf(x.fa[n]));
        }
    }
}
