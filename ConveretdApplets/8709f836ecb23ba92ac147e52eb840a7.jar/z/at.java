// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Collection;
import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;

public class at
{
    public at a;
    public ArrayList b;
    public String c;
    public String d;
    public File e;
    public final int f;
    public o g;
    private static /* synthetic */ boolean h;
    
    public at(final at a) {
        this.b = new ArrayList();
        this.a = a;
        if (a == null) {
            this.f = 0;
            this.g = o.b;
            return;
        }
        this.f = a.f + 1;
        this.g = o.a;
    }
    
    public final at a(final File e) {
        final at at;
        (at = new at(this)).e = e;
        at.c = e.getName();
        at.d = e.getAbsolutePath();
        if (at.c.length() == 0) {
            at.c = at.d;
        }
        this.b.add(at);
        return at;
    }
    
    public final String toString() {
        return this.f + " " + this.c;
    }
    
    public final boolean a(final String s) {
        final Iterator<at> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().c.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public final at b(final String s) {
        final Iterator<at> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            final at at;
            if ((at = iterator.next()).c.equals(s)) {
                return at;
            }
        }
        return null;
    }
    
    public final boolean c(final String s) {
        return this.d.equals(s) || (this.d + File.separator).equals(s);
    }
    
    public final at d(final String s) {
        if (s.equals(this.d) || (s + File.separator).equals(this.d) || s.equals(this.d + File.separator)) {
            return this;
        }
        final Iterator<at> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            final at d;
            if ((d = iterator.next().d(s)) != null) {
                return d;
            }
        }
        return null;
    }
    
    public final at a(final String s, final int n) {
        if (!at.h && n < 0) {
            throw new AssertionError();
        }
        if (s.equals(this.c)) {
            return this;
        }
        if (n > 0) {
            final Iterator<at> iterator = this.b.iterator();
            while (iterator.hasNext()) {
                final at a;
                if ((a = iterator.next().a(s, n - 1)) != null) {
                    return a;
                }
            }
        }
        return null;
    }
    
    public final ArrayList a(final int n) {
        if (!at.h && n < 0) {
            throw new AssertionError();
        }
        final ArrayList<File> list = new ArrayList<File>();
        if (this.f >= n) {
            return list;
        }
        if (this.f == n - 1) {
            for (final at at : this.b) {
                if (!at.h && at.f != n) {
                    throw new AssertionError();
                }
                list.add(at.e);
            }
        }
        else {
            final Iterator<at> iterator2 = (Iterator<at>)this.b.iterator();
            while (iterator2.hasNext()) {
                list.addAll(iterator2.next().a(n));
            }
        }
        return list;
    }
    
    static {
        at.h = !at.class.desiredAssertionStatus();
    }
}
