// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.barcode2;

import ca.odell.glazedlists.GlazedLists;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FourColorTree
{
    private final ListToByteCoder b;
    private FourColorNode c;
    private final List d;
    private final Comparator e;
    static final /* synthetic */ boolean a;
    
    public FourColorTree(final ListToByteCoder b, final Comparator e) {
        this.c = null;
        this.d = new ArrayList();
        if (b == null) {
            throw new NullPointerException("Coder cannot be null.");
        }
        if (e == null) {
            throw new NullPointerException("Comparator cannot be null.");
        }
        this.b = b;
        this.e = e;
    }
    
    public FourColorTree(final ListToByteCoder listToByteCoder) {
        this(listToByteCoder, GlazedLists.a());
    }
    
    public Element a(int n, final byte b) {
        if (this.c == null) {
            throw new IndexOutOfBoundsException();
        }
        FourColorNode fourColorNode = this.c;
        while (FourColorTree.a || fourColorNode != null) {
            if (!FourColorTree.a && n < 0) {
                throw new AssertionError();
            }
            final FourColorNode i = fourColorNode.i;
            final int n2 = (i != null) ? i.a(b) : 0;
            if (n < n2) {
                fourColorNode = i;
            }
            else {
                n -= n2;
                final int b2 = fourColorNode.b(b);
                if (n < b2) {
                    return fourColorNode;
                }
                n -= b2;
                fourColorNode = fourColorNode.j;
            }
        }
        throw new AssertionError();
    }
    
    public Element a(final int n, final byte b, final byte b2, final Object o, final int n2) {
        if (!FourColorTree.a && n < 0) {
            throw new AssertionError();
        }
        if (!FourColorTree.a && n > this.a(b)) {
            throw new AssertionError();
        }
        if (!FourColorTree.a && n2 < 0) {
            throw new AssertionError();
        }
        if (this.c == null) {
            if (n != 0) {
                throw new IndexOutOfBoundsException();
            }
            this.c = new FourColorNode(b2, n2, o, null);
            if (!FourColorTree.a && !this.e()) {
                throw new AssertionError();
            }
            return this.c;
        }
        else {
            final FourColorNode a = this.a(this.c, n, b, b2, o, n2);
            if (!FourColorTree.a && !this.e()) {
                throw new AssertionError();
            }
            return a;
        }
    }
    
    private FourColorNode a(FourColorNode fourColorNode, int n, final byte b, final byte b2, final Object o, final int n2) {
        while (FourColorTree.a || fourColorNode != null) {
            if (!FourColorTree.a && n < 0) {
                throw new AssertionError();
            }
            final FourColorNode i = fourColorNode.i;
            final int n3 = (i != null) ? i.a(b) : 0;
            int n4 = n3 + fourColorNode.b(b);
            if (b2 == fourColorNode.e && o == fourColorNode.f && o != null && n >= n3 && n <= n4) {
                final FourColorNode fourColorNode2 = fourColorNode;
                fourColorNode2.g += n2;
                this.a(fourColorNode, b2, n2);
                return fourColorNode;
            }
            if (n <= n3) {
                if (i == null) {
                    final FourColorNode j = new FourColorNode(b2, n2, o, fourColorNode);
                    fourColorNode.i = j;
                    this.a(fourColorNode, b2, n2);
                    this.a(fourColorNode, false);
                    return j;
                }
                fourColorNode = i;
            }
            else {
                if (n < n4) {
                    final int n5 = n4 - n;
                    final FourColorNode fourColorNode3 = fourColorNode;
                    fourColorNode3.g -= n5;
                    this.a(fourColorNode, fourColorNode.e, -n5);
                    this.a(fourColorNode, n, b, fourColorNode.e, null, n5).a(fourColorNode.f);
                    n4 = n3 + fourColorNode.b(b);
                }
                final int a = fourColorNode.a(b);
                if (!FourColorTree.a && n > a) {
                    throw new AssertionError();
                }
                final FourColorNode k = fourColorNode.j;
                if (k == null) {
                    final FourColorNode l = new FourColorNode(b2, n2, o, fourColorNode);
                    fourColorNode.j = l;
                    this.a(fourColorNode, b2, n2);
                    this.a(fourColorNode, false);
                    return l;
                }
                fourColorNode = k;
                n -= n4;
            }
        }
        throw new AssertionError();
    }
    
    private final void a(FourColorNode fourColorNode, final byte b, final int n) {
        if (b == 1) {
            while (fourColorNode != null) {
                final FourColorNode fourColorNode2 = fourColorNode;
                fourColorNode2.a += n;
                fourColorNode = fourColorNode.k;
            }
        }
        if (b == 2) {
            while (fourColorNode != null) {
                final FourColorNode fourColorNode3 = fourColorNode;
                fourColorNode3.b += n;
                fourColorNode = fourColorNode.k;
            }
        }
        if (b == 4) {
            while (fourColorNode != null) {
                final FourColorNode fourColorNode4 = fourColorNode;
                fourColorNode4.c += n;
                fourColorNode = fourColorNode.k;
            }
        }
        if (b == 8) {
            while (fourColorNode != null) {
                final FourColorNode fourColorNode5 = fourColorNode;
                fourColorNode5.d += n;
                fourColorNode = fourColorNode.k;
            }
        }
    }
    
    private final void a(FourColorNode fourColorNode, final boolean b) {
        while (fourColorNode != null) {
            final byte b2 = (byte)((fourColorNode.i != null) ? fourColorNode.i.h : 0);
            final byte b3 = (byte)((fourColorNode.j != null) ? fourColorNode.j.h : 0);
            if (b2 > b3 && b2 - b3 == 2) {
                if (((fourColorNode.i.j != null) ? fourColorNode.i.j.h : 0) > ((fourColorNode.i.i != null) ? fourColorNode.i.i.h : 0)) {
                    this.d(fourColorNode.i);
                }
                fourColorNode = this.c(fourColorNode);
            }
            else if (b3 > b2 && b3 - b2 == 2) {
                if (((fourColorNode.j.i != null) ? fourColorNode.j.i.h : 0) > ((fourColorNode.j.j != null) ? fourColorNode.j.j.h : 0)) {
                    this.c(fourColorNode.j);
                }
                fourColorNode = this.d(fourColorNode);
            }
            final byte h = (byte)(Math.max((fourColorNode.i != null) ? fourColorNode.i.h : 0, (fourColorNode.j != null) ? fourColorNode.j.h : 0) + 1);
            if (!b && fourColorNode.h == h) {
                return;
            }
            fourColorNode.h = h;
            fourColorNode = fourColorNode.k;
        }
    }
    
    private final FourColorNode c(final FourColorNode fourColorNode) {
        if (!FourColorTree.a && fourColorNode.i == null) {
            throw new AssertionError();
        }
        final FourColorNode i = fourColorNode.i;
        fourColorNode.i = i.j;
        if (i.j != null) {
            i.j.k = fourColorNode;
        }
        i.k = fourColorNode.k;
        if (i.k != null) {
            if (i.k.i == fourColorNode) {
                i.k.i = i;
            }
            else {
                if (i.k.j != fourColorNode) {
                    throw new IllegalStateException();
                }
                i.k.j = i;
            }
        }
        else {
            this.c = i;
        }
        i.j = fourColorNode;
        fourColorNode.k = i;
        fourColorNode.h = (byte)(Math.max((fourColorNode.i != null) ? fourColorNode.i.h : 0, (fourColorNode.j != null) ? fourColorNode.j.h : 0) + 1);
        fourColorNode.c();
        i.h = (byte)(Math.max((i.i != null) ? i.i.h : 0, (i.j != null) ? i.j.h : 0) + 1);
        i.c();
        return i;
    }
    
    private final FourColorNode d(final FourColorNode fourColorNode) {
        if (!FourColorTree.a && fourColorNode.j == null) {
            throw new AssertionError();
        }
        final FourColorNode j = fourColorNode.j;
        fourColorNode.j = j.i;
        if (j.i != null) {
            j.i.k = fourColorNode;
        }
        j.k = fourColorNode.k;
        if (j.k != null) {
            if (j.k.i == fourColorNode) {
                j.k.i = j;
            }
            else {
                if (j.k.j != fourColorNode) {
                    throw new IllegalStateException();
                }
                j.k.j = j;
            }
        }
        else {
            this.c = j;
        }
        j.i = fourColorNode;
        fourColorNode.k = j;
        fourColorNode.h = (byte)(Math.max((fourColorNode.i != null) ? fourColorNode.i.h : 0, (fourColorNode.j != null) ? fourColorNode.j.h : 0) + 1);
        fourColorNode.c();
        j.h = (byte)(Math.max((j.i != null) ? j.i.h : 0, (j.j != null) ? j.j.h : 0) + 1);
        j.c();
        return j;
    }
    
    public void a(final int n, final byte b, final int n2) {
        if (n2 == 0) {
            return;
        }
        if (!FourColorTree.a && n < 0) {
            throw new AssertionError();
        }
        if (!FourColorTree.a && n + n2 > this.a(b)) {
            throw new AssertionError();
        }
        if (!FourColorTree.a && this.c == null) {
            throw new AssertionError();
        }
        this.a(this.c, n, b, n2);
        this.d();
        if (!FourColorTree.a && !this.e()) {
            throw new AssertionError();
        }
    }
    
    private void d() {
        for (int i = 0; i < this.d.size(); ++i) {
            final FourColorNode fourColorNode = this.d.get(i);
            if (!FourColorTree.a && fourColorNode.g != 0) {
                throw new AssertionError();
            }
            if (fourColorNode.j == null) {
                this.a(fourColorNode, fourColorNode.i);
            }
            else if (fourColorNode.i == null) {
                this.a(fourColorNode, fourColorNode.j);
            }
            else {
                this.e(fourColorNode);
            }
        }
        this.d.clear();
    }
    
    private void a(FourColorNode j, int n, final byte b, int i) {
        while (i > 0) {
            if (!FourColorTree.a && j == null) {
                throw new AssertionError();
            }
            if (!FourColorTree.a && n < 0) {
                throw new AssertionError();
            }
            final FourColorNode k = j.i;
            int n2 = (k != null) ? k.a(b) : 0;
            if (n < n2) {
                if (n + i <= n2) {
                    j = k;
                    continue;
                }
                final int n3 = n2 - n;
                this.a(k, n, b, n3);
                i -= n3;
                n2 -= n3;
            }
            if (!FourColorTree.a && n < n2) {
                throw new AssertionError();
            }
            int n4 = n2 + j.b(b);
            if (n < n4) {
                final int min = Math.min(n4 - n, i);
                final FourColorNode fourColorNode = j;
                fourColorNode.g -= min;
                i -= min;
                n4 -= min;
                this.a(j, j.e, -min);
                if (j.g == 0) {
                    this.d.add(j);
                }
                if (i == 0) {
                    return;
                }
            }
            if (!FourColorTree.a && n < n4) {
                throw new AssertionError();
            }
            n -= n4;
            j = j.j;
        }
    }
    
    private void a(final FourColorNode fourColorNode, final FourColorNode j) {
        final FourColorNode k = fourColorNode.k;
        if (k == null) {
            if (!FourColorTree.a && fourColorNode != this.c) {
                throw new AssertionError();
            }
            this.c = j;
        }
        else if (k.i == fourColorNode) {
            k.i = j;
        }
        else if (k.j == fourColorNode) {
            k.j = j;
        }
        if (j != null) {
            j.k = k;
        }
        this.a(k, true);
    }
    
    private FourColorNode e(final FourColorNode fourColorNode) {
        if (!FourColorTree.a && fourColorNode.g != 0) {
            throw new AssertionError();
        }
        if (!FourColorTree.a && fourColorNode.i == null) {
            throw new AssertionError();
        }
        if (!FourColorTree.a && fourColorNode.j == null) {
            throw new AssertionError();
        }
        FourColorNode fourColorNode2;
        for (fourColorNode2 = fourColorNode.i; fourColorNode2.j != null; fourColorNode2 = fourColorNode2.j) {}
        if (!FourColorTree.a && fourColorNode2.j != null) {
            throw new AssertionError();
        }
        this.a(fourColorNode2, fourColorNode2.e, -fourColorNode2.g);
        this.a(fourColorNode2, fourColorNode2.i);
        fourColorNode2.i = fourColorNode.i;
        if (fourColorNode2.i != null) {
            fourColorNode2.i.k = fourColorNode2;
        }
        fourColorNode2.j = fourColorNode.j;
        if (fourColorNode2.j != null) {
            fourColorNode2.j.k = fourColorNode2;
        }
        fourColorNode2.h = fourColorNode.h;
        fourColorNode2.c();
        this.a(fourColorNode, fourColorNode2);
        this.a(fourColorNode2.k, fourColorNode2.e, fourColorNode2.g);
        return fourColorNode2;
    }
    
    public Element b(final int n, final byte b, final byte b2, final Object o, final int n2) {
        this.a(n, b, n2);
        return this.a(n, b, b2, o, n2);
    }
    
    public void a() {
        this.c = null;
    }
    
    public int a(final Element element, final byte b) {
        FourColorNode k = (FourColorNode)element;
        int n = (k.i != null) ? k.i.a(b) : 0;
        while (k.k != null) {
            if (k.k.j == k) {
                n = n + ((k.k.i != null) ? k.k.i.a(b) : 0) + k.k.b(b);
            }
            k = k.k;
        }
        return n;
    }
    
    public int a(int n, final byte b, final byte b2) {
        if (this.c != null) {
            int n2 = 0;
            FourColorNode fourColorNode = this.c;
            while (FourColorTree.a || fourColorNode != null) {
                if (!FourColorTree.a && n < 0) {
                    throw new AssertionError();
                }
                final FourColorNode i = fourColorNode.i;
                final int n3 = (i != null) ? i.a(b) : 0;
                if (n < n3) {
                    fourColorNode = i;
                }
                else {
                    if (i != null) {
                        n2 += i.a(b2);
                    }
                    n -= n3;
                    final int b3 = fourColorNode.b(b);
                    if (n < b3) {
                        if ((b2 & fourColorNode.e) > 0) {
                            n2 += n;
                        }
                        else {
                            --n2;
                        }
                        return n2;
                    }
                    n2 += fourColorNode.b(b2);
                    n -= b3;
                    fourColorNode = fourColorNode.j;
                }
            }
            throw new AssertionError();
        }
        if (n == 0) {
            return 0;
        }
        throw new IndexOutOfBoundsException();
    }
    
    public int a(final byte b) {
        if (this.c == null) {
            return 0;
        }
        return this.c.a(b);
    }
    
    public String toString() {
        if (this.c == null) {
            return "";
        }
        return this.c.a(this.b.a());
    }
    
    public String b() {
        if (this.c == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for (FourColorNode fourColorNode = this.c(); fourColorNode != null; fourColorNode = a(fourColorNode)) {
            final Object value = this.b.a().get(b(fourColorNode.e));
            for (int i = 0; i < fourColorNode.g; ++i) {
                sb.append(value);
            }
        }
        return sb.toString();
    }
    
    public static FourColorNode a(final FourColorNode fourColorNode) {
        if (fourColorNode.j != null) {
            FourColorNode fourColorNode2;
            for (fourColorNode2 = fourColorNode.j; fourColorNode2.i != null; fourColorNode2 = fourColorNode2.i) {}
            return fourColorNode2;
        }
        FourColorNode k;
        for (k = fourColorNode; k.k != null && k.k.j == k; k = k.k) {}
        return k.k;
    }
    
    public static FourColorNode b(final FourColorNode fourColorNode) {
        if (fourColorNode.i != null) {
            FourColorNode fourColorNode2;
            for (fourColorNode2 = fourColorNode.i; fourColorNode2.j != null; fourColorNode2 = fourColorNode2.j) {}
            return fourColorNode2;
        }
        FourColorNode k;
        for (k = fourColorNode; k.k != null && k.k.i == k; k = k.k) {}
        return k.k;
    }
    
    FourColorNode c() {
        if (this.c == null) {
            return null;
        }
        FourColorNode fourColorNode;
        for (fourColorNode = this.c; fourColorNode.i != null; fourColorNode = fourColorNode.i) {}
        return fourColorNode;
    }
    
    private boolean e() {
        for (FourColorNode fourColorNode = this.c(); fourColorNode != null; fourColorNode = a(fourColorNode)) {
            final int a = fourColorNode.a;
            final int b = fourColorNode.b;
            final int c = fourColorNode.c;
            final int d = fourColorNode.d;
            fourColorNode.c();
            if (!FourColorTree.a && a != fourColorNode.a) {
                throw new AssertionError((Object)("Incorrect count 0 on node: \n" + fourColorNode + "\n Expected " + fourColorNode.a + " but was " + a));
            }
            if (!FourColorTree.a && b != fourColorNode.b) {
                throw new AssertionError((Object)("Incorrect count 1 on node: \n" + fourColorNode + "\n Expected " + fourColorNode.b + " but was " + b));
            }
            if (!FourColorTree.a && c != fourColorNode.c) {
                throw new AssertionError((Object)("Incorrect count 2 on node: \n" + fourColorNode + "\n Expected " + fourColorNode.c + " but was " + c));
            }
            if (!FourColorTree.a && d != fourColorNode.d) {
                throw new AssertionError((Object)("Incorrect count 3 on node: \n" + fourColorNode + "\n Expected " + fourColorNode.d + " but was " + d));
            }
            final byte b2 = (byte)((fourColorNode.i != null) ? fourColorNode.i.h : 0);
            final byte b3 = (byte)((fourColorNode.j != null) ? fourColorNode.j.h : 0);
            if (!FourColorTree.a && Math.max(b2, b3) + 1 != fourColorNode.h) {
                throw new AssertionError();
            }
            if (!FourColorTree.a && fourColorNode.i != null && fourColorNode.i.k != fourColorNode) {
                throw new AssertionError();
            }
            if (!FourColorTree.a && fourColorNode.j != null && fourColorNode.j.k != fourColorNode) {
                throw new AssertionError();
            }
            if (!FourColorTree.a && Math.abs(b2 - b3) >= 2) {
                throw new AssertionError((Object)("Subtree is not AVL: \n" + fourColorNode));
            }
        }
        return true;
    }
    
    static final int b(final byte b) {
        switch (b) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 4: {
                return 2;
            }
            case 8: {
                return 3;
            }
            case 16: {
                return 4;
            }
            case 32: {
                return 5;
            }
            case 64: {
                return 6;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    static {
        a = !FourColorTree.class.desiredAssertionStatus();
    }
}
