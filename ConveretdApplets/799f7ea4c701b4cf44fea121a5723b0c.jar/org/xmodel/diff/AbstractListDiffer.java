// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListDiffer implements IListDiffer
{
    @Override
    public void diff(final List list, final List list2) {
        List<_A> list3 = null;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final int size = list.size();
        final int size2 = list2.size();
        while (n2 < size && n3 < size2) {
            final int runOfEqualObjects = this.findRunOfEqualObjects(list, n2, list2, n3);
            if (runOfEqualObjects > 0) {
                this.notifyEqual(list, n2, n, list2, n3, runOfEqualObjects);
                n2 += runOfEqualObjects;
                n3 += runOfEqualObjects;
            }
            final int runMissingFromRight = this.findRunMissingFromRight(list, n2, list2, n3);
            if (n2 + runMissingFromRight < size && runMissingFromRight == 1) {
                this.notifyRemove(list, n2, n, list2, runMissingFromRight);
                --n;
                n2 += runMissingFromRight;
            }
            else if (runMissingFromRight > 0) {
                final int runMissingFromLeft = this.findRunMissingFromLeft(list, n2, list2, n3);
                if (runMissingFromLeft > 0 && (runMissingFromLeft < runMissingFromRight || n2 + runMissingFromRight == size)) {
                    if (list3 == null) {
                        list3 = new ArrayList<_A>();
                    }
                    list3.add(new _A(n2, n, n3, runMissingFromLeft));
                    n3 += runMissingFromLeft;
                }
                else {
                    this.notifyRemove(list, n2, n, list2, runMissingFromRight);
                    n -= runMissingFromRight;
                    n2 += runMissingFromRight;
                }
            }
            else {
                if (runMissingFromRight >= 0) {
                    continue;
                }
                final int n4 = -runMissingFromRight;
                this.notifyRemove(list, n2, n, list2, n4);
                if (list3 == null) {
                    list3 = new ArrayList<_A>();
                }
                list3.add(new _A(n2, n, n3, n4));
                n -= n4;
                n2 += n4;
                n3 += n4;
            }
        }
        if (n2 < size) {
            final int n5 = size - n2;
            this.notifyRemove(list, n2, n, list2, n5);
            n -= n5;
        }
        if (list3 != null) {
            int n6 = 0;
            for (final _A a : list3) {
                this.notifyInsert(list, a.B, a.C + n6, list2, a.E, a.D);
                n6 += a.D;
                n += a.D;
            }
        }
        if (n3 < size2) {
            this.notifyInsert(list, n2, n, list2, n3, size2 - n3);
        }
    }
    
    @Override
    public boolean isMatch(final Object o, final Object o2) {
        return o.equals(o2);
    }
    
    @Override
    public void notifyEqual(final List list, final int n, final int n2, final List list2, final int n3, final int n4) {
    }
    
    public int findRunOfEqualObjects(final List list, int n, final List list2, int n2) {
        final int size = list.size();
        final int size2 = list2.size();
        int n3 = 0;
        while (n < size && n2 < size2) {
            if (!this.isMatch(list.get(n++), list2.get(n2++))) {
                return n3;
            }
            ++n3;
        }
        return n3;
    }
    
    public int findRunMissingFromLeft(final List list, int n, final List list2, int i) {
        int n2 = 0;
        final int size = list.size();
        final int size2 = list2.size();
        if (size <= n) {
            return 0;
        }
        final Object value = list.get(n);
        while (i < size2) {
            final Object value2 = list2.get(i++);
            if (size <= n) {
                return n2;
            }
            if (this.isMatch(list.get(n++), value2)) {
                return -n2;
            }
            if (this.isMatch(value2, value)) {
                return n2;
            }
            ++n2;
        }
        return n2;
    }
    
    public int findRunMissingFromRight(final List list, int i, final List list2, int n) {
        int n2 = 0;
        final int size = list.size();
        final int size2 = list2.size();
        if (size2 <= n) {
            return 0;
        }
        final Object value = list2.get(n);
        while (i < size) {
            final Object value2 = list.get(i++);
            if (size2 <= n) {
                return n2;
            }
            if (this.isMatch(value2, list2.get(n++))) {
                return -n2;
            }
            if (this.isMatch(value2, value)) {
                return n2;
            }
            ++n2;
        }
        return n2;
    }
    
    public static void main(final String[] array) throws Exception {
        final ArrayList<Character> list = new ArrayList<Character>();
        final ArrayList<Character> list2 = new ArrayList<Character>();
        list.add('A');
        list2.add('A');
        list2.add('B');
        list2.add('C');
        list.add('D');
        list2.add('D');
        new AbstractListDiffer() {
            @Override
            public void notifyEqual(final List list, final int n, final int n2, final List list2, final int n3, final int n4) {
                for (int i = 0; i < n4; ++i) {
                    System.out.println("EQL: left=" + list.get(n + i) + ", right=" + list2.get(n3 + i));
                }
            }
            
            @Override
            public void notifyInsert(final List list, final int n, final int n2, final List list2, final int n3, final int n4) {
                for (int i = 0; i < n4; ++i) {
                    System.out.println("INS: right=" + list2.get(n3 + i) + ", at=" + (n + n2 + i));
                }
            }
            
            @Override
            public void notifyRemove(final List list, final int n, final int n2, final List list2, final int n3) {
                for (int i = 0; i < n3; ++i) {
                    System.out.println("DEL: left=" + list.get(n + i) + ", at=" + (n + n2));
                }
            }
        }.diff(list, list2);
    }
    
    final class _A
    {
        int B;
        int C;
        int E;
        int D;
        
        _A(final int b, final int c, final int e, final int d) {
            this.B = b;
            this.C = c;
            this.E = e;
            this.D = d;
        }
    }
}
