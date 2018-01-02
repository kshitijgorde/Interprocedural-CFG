// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.ActionEvent;
import java.util.concurrent.Executor;
import javax.swing.AbstractAction;

public final class P extends AbstractAction
{
    private final Executor a;
    private final Runnable b;
    
    public P(final String s, final Executor a, final Runnable b) {
        super(s);
        this.a = a;
        this.b = b;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        (this = this).a.execute(this.b);
    }
}
