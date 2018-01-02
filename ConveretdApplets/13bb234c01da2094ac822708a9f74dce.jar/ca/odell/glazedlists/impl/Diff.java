// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import ca.odell.glazedlists.EventList;

public final class Diff
{
    public static void a(final EventList list, final List list2, final boolean b) {
        a(list, list2, b, GlazedListsImpl.a());
    }
    
    public static void a(final EventList list, final List list2, final boolean b, final Comparator comparator) {
        final List a = a(new Diff$ListDiffMatcher(list, list2, comparator));
        int n = 0;
        int n2 = 0;
        Diff$Point diff$Point = null;
        for (final Diff$Point diff$Point2 : a) {
            if (diff$Point == null) {
                diff$Point = diff$Point2;
            }
            else {
                final int n3 = diff$Point2.b() - diff$Point.b();
                final int n4 = diff$Point2.c() - diff$Point.c();
                if (n3 == n4) {
                    if (b) {
                        for (int i = 0; i < n3; ++i) {
                            list.set(n + i, list2.get(n2 + i));
                        }
                    }
                    n += n3;
                    n2 += n4;
                }
                else if (n3 == 1 && n4 == 0) {
                    list.remove(n);
                }
                else {
                    if (n3 != 0 || n4 != 1) {
                        throw new IllegalStateException();
                    }
                    list.add(n, list2.get(n2));
                    ++n2;
                    ++n;
                }
                diff$Point = diff$Point2;
            }
        }
    }
    
    private static List a(final Diff$DiffMatcher diff$DiffMatcher) {
        final int a = diff$DiffMatcher.a();
        final int b = diff$DiffMatcher.b();
        final Diff$Point diff$Point = new Diff$Point(a, b);
        final int n = a + b;
        final HashMap<Object, Diff$Point> hashMap = new HashMap<Object, Diff$Point>();
        for (int i = 0; i <= n; ++i) {
            for (int j = -i; j <= i; j += 2) {
                final Diff$Point diff$Point2 = hashMap.get(new Integer(j - 1));
                final Diff$Point diff$Point3 = hashMap.get(new Integer(j + 1));
                Diff$Point diff$Point4;
                if (hashMap.isEmpty()) {
                    diff$Point4 = new Diff$Point(0, 0);
                }
                else if (j == -i || (j != i && diff$Point2.b() < diff$Point3.b())) {
                    diff$Point4 = diff$Point3.a(0, 1);
                }
                else {
                    diff$Point4 = diff$Point2.a(1, 0);
                }
                while (diff$Point4.a(diff$Point) && diff$DiffMatcher.a(diff$Point4.b(), diff$Point4.c())) {
                    diff$Point4 = diff$Point4.a();
                }
                hashMap.put(new Integer(j), diff$Point4);
                if (diff$Point4.b(diff$Point)) {
                    return diff$Point4.d();
                }
            }
        }
        throw new IllegalStateException();
    }
}
