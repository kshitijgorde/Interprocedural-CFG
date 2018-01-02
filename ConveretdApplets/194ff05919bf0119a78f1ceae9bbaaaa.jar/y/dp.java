// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Vector;

public abstract class dp
{
    public abstract int a(final Object p0, final Object p1);
    
    public final void a(final Vector vector) {
        this.a(vector, 0, vector.size() - 1);
    }
    
    public final int a(final Vector vector, final Object o) {
        int i = 0;
        int size = vector.size();
        if (vector.size() == 0) {
            return 0;
        }
        while (i < size) {
            final int n = (i + size) / 2;
            final Object element;
            if ((element = vector.elementAt(n)) == o) {
                return n;
            }
            if (this.a(o, element) > 0) {
                i = n + 1;
            }
            else {
                size = n;
            }
        }
        return i;
    }
    
    private void a(final Vector vector, final int n, final int n2) {
        if (n2 > n) {
            int i = n;
            int n3 = n2;
            final Object element = vector.elementAt((n + n2) / 2);
            while (i <= n3) {
                while (i < n2 && this.a(element, vector.elementAt(i)) > 0) {
                    ++i;
                }
                while (n3 > n && this.a(element, vector.elementAt(n3)) < 0) {
                    --n3;
                }
                if (i <= n3) {
                    final Object element2 = vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(n3), i);
                    vector.setElementAt(element2, n3);
                    ++i;
                    --n3;
                }
            }
            this.a(vector, n, n3);
            this.a(vector, i, n2);
        }
    }
}
