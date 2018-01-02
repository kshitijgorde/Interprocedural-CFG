// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.c;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.AbstractAction;
import java.util.Collection;
import java.util.ArrayList;

public final class a
{
    private final ArrayList c;
    private int d;
    private final Collection e;
    public final AbstractAction a;
    public final AbstractAction b;
    
    public a() {
        this.c = new ArrayList();
        this.d = 0;
        this.e = new CopyOnWriteArrayList();
        this.a = new c(this, "Undo");
        this.b = new b(this, "Redo");
        this.d = 0;
        this.c.clear();
        this.a.setEnabled(false);
        this.b.setEnabled(false);
    }
    
    public final void a(final d d) {
        this.e.add(d);
    }
    
    public final synchronized void a(final f f) {
        if (this.c.size() == 0) {
            this.c.add(f);
            this.c();
            return;
        }
        while (this.c.size() - 1 > this.d) {
            this.c.remove(this.c.size() - 1);
        }
        ++this.d;
        this.c.add(f);
        this.a.setEnabled(true);
        this.a.putValue("Name", "Undo " + f.a);
        this.b.setEnabled(false);
        this.b.putValue("Name", "Redo");
        if (this.c.size() > 8) {
            this.c.remove(0);
            --this.d;
        }
        this.c();
    }
    
    private void c() {
        final f a = this.a();
        final Iterator<d> iterator = (Iterator<d>)this.e.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(a);
        }
    }
    
    public final synchronized f a() {
        return this.c.get(this.d);
    }
    
    private synchronized void d() {
        if (this.d == 0) {
            throw new IllegalStateException("You shouldn't have been able to undo");
        }
        this.b.setEnabled(true);
        this.b.putValue("Name", "Redo " + this.a().a);
        --this.d;
        this.a.setEnabled(this.d > 0);
        this.a.putValue("Name", "Undo" + ((this.d > 0) ? (" " + this.a().a) : ""));
        this.c();
    }
    
    private synchronized void e() {
        if (this.d == this.c.size() - 1) {
            throw new IllegalStateException("You shouldn't have been able to redo");
        }
        ++this.d;
        this.a.setEnabled(true);
        this.a.putValue("Name", "Undo " + this.a().a);
        final boolean enabled = this.d < this.c.size() - 1;
        this.b.setEnabled(enabled);
        this.b.putValue("Name", "Redo" + (enabled ? (" " + this.c.get(this.d + 1).a) : ""));
        this.c();
    }
    
    private synchronized void f() {
        this.c.clear();
        this.d = 0;
    }
    
    public final void b() {
        this.e.clear();
        this.f();
    }
}
