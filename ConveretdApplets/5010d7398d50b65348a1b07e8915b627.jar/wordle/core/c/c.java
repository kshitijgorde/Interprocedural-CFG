// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.c;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

final class c extends AbstractAction
{
    private /* synthetic */ a a;
    
    c(final a a, final String s) {
        this.a = a;
        super(s);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.d();
    }
}
