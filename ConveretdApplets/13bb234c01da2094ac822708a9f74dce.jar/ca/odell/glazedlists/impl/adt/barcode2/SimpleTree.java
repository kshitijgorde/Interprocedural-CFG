// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.barcode2;

import ca.odell.glazedlists.GlazedLists;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleTree
{
    private SimpleNode b;
    private final List c;
    private final Comparator d;
    static final /* synthetic */ boolean a;
    
    public SimpleTree(final Comparator d) {
        this.b = null;
        this.c = new ArrayList();
        if (d == null) {
            throw new NullPointerException("Comparator cannot be null.");
        }
        this.d = d;
    }
    
    public SimpleTree() {
        this(GlazedLists.a());
    }
    
    public Comparator a() {
        return this.d;
    }
    
    public Element a(int n) {
        if (this.b == null) {
            throw new IndexOutOfBoundsException();
        }
        SimpleNode simpleNode = this.b;
        while (SimpleTree.a || simpleNode != null) {
            if (!SimpleTree.a && n < 0) {
                throw new AssertionError();
            }
            final SimpleNode d = simpleNode.d;
            final int n2 = (d != null) ? d.a : 0;
            if (n < n2) {
                simpleNode = d;
            }
            else {
                n -= n2;
                final int n3 = 1;
                if (n < n3) {
                    return simpleNode;
                }
                n -= n3;
                simpleNode = simpleNode.e;
            }
        }
        throw new AssertionError();
    }
    
    public Element a(final int n, final Object o, final int n2) {
        if (!SimpleTree.a && n < 0) {
            throw new AssertionError();
        }
        if (!SimpleTree.a && n > this.b()) {
            throw new AssertionError();
        }
        if (!SimpleTree.a && n2 < 0) {
            throw new AssertionError();
        }
        if (this.b == null) {
            if (n != 0) {
                throw new IndexOutOfBoundsException();
            }
            this.b = new SimpleNode(n2, o, null);
            if (!SimpleTree.a && !this.e()) {
                throw new AssertionError();
            }
            return this.b;
        }
        else {
            final SimpleNode a = this.a(this.b, n, o, n2);
            if (!SimpleTree.a && !this.e()) {
                throw new AssertionError();
            }
            return a;
        }
    }
    
    private SimpleNode a(SimpleNode simpleNode, int n, final Object o, final int n2) {
        while (SimpleTree.a || simpleNode != null) {
            if (!SimpleTree.a && n < 0) {
                throw new AssertionError();
            }
            final SimpleNode d = simpleNode.d;
            final int n3 = (d != null) ? d.a : 0;
            int n4 = n3 + 1;
            if (n <= n3) {
                if (d == null) {
                    final SimpleNode d2 = new SimpleNode(n2, o, simpleNode);
                    simpleNode.d = d2;
                    this.a(simpleNode, n2);
                    this.a(simpleNode, false);
                    return d2;
                }
                simpleNode = d;
            }
            else {
                if (n < n4) {
                    final int n5 = n4 - n;
                    this.a(simpleNode, -n5);
                    this.a(simpleNode, n, null, n5).a(simpleNode.b);
                    n4 = n3 + 1;
                }
                final int a = simpleNode.a;
                if (!SimpleTree.a && n > a) {
                    throw new AssertionError();
                }
                final SimpleNode e = simpleNode.e;
                if (e == null) {
                    final SimpleNode e2 = new SimpleNode(n2, o, simpleNode);
                    simpleNode.e = e2;
                    this.a(simpleNode, n2);
                    this.a(simpleNode, false);
                    return e2;
                }
                simpleNode = e;
                n -= n4;
            }
        }
        throw new AssertionError();
    }
    
    public Element a(final byte b, final Object o, final int n) {
        if (!SimpleTree.a && n < 0) {
            throw new AssertionError();
        }
        if (this.b == null) {
            this.b = new SimpleNode(n, o, null);
            if (!SimpleTree.a && !this.e()) {
                throw new AssertionError();
            }
            return this.b;
        }
        else {
            final SimpleNode a = this.a(this.b, o, n);
            if (!SimpleTree.a && !this.e()) {
                throw new AssertionError();
            }
            return a;
        }
    }
    
    private SimpleNode a(SimpleNode simpleNode, final Object o, final int n) {
    Label_0000:
        while (SimpleTree.a || simpleNode != null) {
            SimpleNode a = simpleNode;
            while (true) {
                while (a != null) {
                    if (a.g == 0) {
                        final int compare = this.d.compare(o, a.b);
                        if (false || compare < 0 || (compare == 0 && simpleNode.d == null) || (compare == 0 && simpleNode.e != null && simpleNode.d.c < simpleNode.e.c)) {
                            final SimpleNode d = simpleNode.d;
                            if (d == null) {
                                final SimpleNode d2 = new SimpleNode(n, o, simpleNode);
                                simpleNode.d = d2;
                                this.a(simpleNode, n);
                                this.a(simpleNode, false);
                                return d2;
                            }
                            simpleNode = d;
                            continue Label_0000;
                        }
                        else {
                            final SimpleNode e = simpleNode.e;
                            if (e == null) {
                                final SimpleNode e2 = new SimpleNode(n, o, simpleNode);
                                simpleNode.e = e2;
                                this.a(simpleNode, n);
                                this.a(simpleNode, false);
                                return e2;
                            }
                            simpleNode = e;
                            continue Label_0000;
                        }
                    }
                    else {
                        a = a(a);
                    }
                }
                final int compare = -1;
                continue;
            }
        }
        throw new AssertionError();
    }
    
    private final void a(SimpleNode f, final int n) {
        while (f != null) {
            final SimpleNode simpleNode = f;
            simpleNode.a += n;
            f = f.f;
        }
    }
    
    private final void a(SimpleNode simpleNode, final boolean b) {
        while (simpleNode != null) {
            final byte b2 = (byte)((simpleNode.d != null) ? simpleNode.d.c : 0);
            final byte b3 = (byte)((simpleNode.e != null) ? simpleNode.e.c : 0);
            if (b2 > b3 && b2 - b3 == 2) {
                if (((simpleNode.d.e != null) ? simpleNode.d.e.c : 0) > ((simpleNode.d.d != null) ? simpleNode.d.d.c : 0)) {
                    this.d(simpleNode.d);
                }
                simpleNode = this.c(simpleNode);
            }
            else if (b3 > b2 && b3 - b2 == 2) {
                if (((simpleNode.e.d != null) ? simpleNode.e.d.c : 0) > ((simpleNode.e.e != null) ? simpleNode.e.e.c : 0)) {
                    this.c(simpleNode.e);
                }
                simpleNode = this.d(simpleNode);
            }
            final byte c = (byte)(Math.max((simpleNode.d != null) ? simpleNode.d.c : 0, (simpleNode.e != null) ? simpleNode.e.c : 0) + 1);
            if (!b && simpleNode.c == c) {
                return;
            }
            simpleNode.c = c;
            simpleNode = simpleNode.f;
        }
    }
    
    private final SimpleNode c(final SimpleNode simpleNode) {
        if (!SimpleTree.a && simpleNode.d == null) {
            throw new AssertionError();
        }
        final SimpleNode d = simpleNode.d;
        simpleNode.d = d.e;
        if (d.e != null) {
            d.e.f = simpleNode;
        }
        d.f = simpleNode.f;
        if (d.f != null) {
            if (d.f.d == simpleNode) {
                d.f.d = d;
            }
            else {
                if (d.f.e != simpleNode) {
                    throw new IllegalStateException();
                }
                d.f.e = d;
            }
        }
        else {
            this.b = d;
        }
        d.e = simpleNode;
        simpleNode.f = d;
        simpleNode.c = (byte)(Math.max((simpleNode.d != null) ? simpleNode.d.c : 0, (simpleNode.e != null) ? simpleNode.e.c : 0) + 1);
        simpleNode.a(!this.c.contains(simpleNode));
        d.c = (byte)(Math.max((d.d != null) ? d.d.c : 0, (d.e != null) ? d.e.c : 0) + 1);
        d.a(!this.c.contains(d));
        return d;
    }
    
    private final SimpleNode d(final SimpleNode simpleNode) {
        if (!SimpleTree.a && simpleNode.e == null) {
            throw new AssertionError();
        }
        final SimpleNode e = simpleNode.e;
        simpleNode.e = e.d;
        if (e.d != null) {
            e.d.f = simpleNode;
        }
        e.f = simpleNode.f;
        if (e.f != null) {
            if (e.f.d == simpleNode) {
                e.f.d = e;
            }
            else {
                if (e.f.e != simpleNode) {
                    throw new IllegalStateException();
                }
                e.f.e = e;
            }
        }
        else {
            this.b = e;
        }
        e.d = simpleNode;
        simpleNode.f = e;
        simpleNode.c = (byte)(Math.max((simpleNode.d != null) ? simpleNode.d.c : 0, (simpleNode.e != null) ? simpleNode.e.c : 0) + 1);
        simpleNode.a(!this.c.contains(simpleNode));
        e.c = (byte)(Math.max((e.d != null) ? e.d.c : 0, (e.e != null) ? e.e.c : 0) + 1);
        e.a(!this.c.contains(e));
        return e;
    }
    
    public void a(final Element element) {
        final SimpleNode simpleNode = (SimpleNode)element;
        if (!SimpleTree.a && this.b == null) {
            throw new AssertionError();
        }
        this.a(simpleNode, -1);
        this.c.add(simpleNode);
        this.d();
        if (!SimpleTree.a && !this.e()) {
            throw new AssertionError();
        }
    }
    
    public void a(final int n, final int n2) {
        if (n2 == 0) {
            return;
        }
        if (!SimpleTree.a && n < 0) {
            throw new AssertionError();
        }
        if (!SimpleTree.a && n + n2 > this.b()) {
            throw new AssertionError();
        }
        if (!SimpleTree.a && this.b == null) {
            throw new AssertionError();
        }
        this.a(this.b, n, n2);
        this.d();
        if (!SimpleTree.a && !this.e()) {
            throw new AssertionError();
        }
    }
    
    private void d() {
        for (int i = 0; i < this.c.size(); ++i) {
            final SimpleNode simpleNode = this.c.get(i);
            if (simpleNode.e == null) {
                this.a(simpleNode, simpleNode.d);
            }
            else if (simpleNode.d == null) {
                this.a(simpleNode, simpleNode.e);
            }
            else {
                this.e(simpleNode);
            }
        }
        this.c.clear();
    }
    
    private void a(SimpleNode e, int n, int i) {
        while (i > 0) {
            if (!SimpleTree.a && e == null) {
                throw new AssertionError();
            }
            if (!SimpleTree.a && n < 0) {
                throw new AssertionError();
            }
            final SimpleNode d = e.d;
            int n2 = (d != null) ? d.a : 0;
            if (n < n2) {
                if (n + i <= n2) {
                    e = d;
                    continue;
                }
                final int n3 = n2 - n;
                this.a(d, n, n3);
                i -= n3;
                n2 -= n3;
            }
            if (!SimpleTree.a && n < n2) {
                throw new AssertionError();
            }
            int n4 = n2 + 1;
            if (n < n4) {
                final int min = Math.min(n4 - n, i);
                i -= min;
                n4 -= min;
                this.a(e, -min);
                this.c.add(e);
                if (i == 0) {
                    return;
                }
            }
            if (!SimpleTree.a && n < n4) {
                throw new AssertionError();
            }
            n -= n4;
            e = e.e;
        }
    }
    
    private void a(final SimpleNode simpleNode, final SimpleNode e) {
        final SimpleNode f = simpleNode.f;
        if (f == null) {
            if (!SimpleTree.a && simpleNode != this.b) {
                throw new AssertionError();
            }
            this.b = e;
        }
        else if (f.d == simpleNode) {
            f.d = e;
        }
        else if (f.e == simpleNode) {
            f.e = e;
        }
        if (e != null) {
            e.f = f;
        }
        this.a(f, true);
    }
    
    private SimpleNode e(final SimpleNode simpleNode) {
        if (!SimpleTree.a && simpleNode.d == null) {
            throw new AssertionError();
        }
        if (!SimpleTree.a && simpleNode.e == null) {
            throw new AssertionError();
        }
        SimpleNode simpleNode2;
        for (simpleNode2 = simpleNode.d; simpleNode2.e != null; simpleNode2 = simpleNode2.e) {}
        if (!SimpleTree.a && simpleNode2.e != null) {
            throw new AssertionError();
        }
        this.a(simpleNode2, -1);
        this.a(simpleNode2, simpleNode2.d);
        simpleNode2.d = simpleNode.d;
        if (simpleNode2.d != null) {
            simpleNode2.d.f = simpleNode2;
        }
        simpleNode2.e = simpleNode.e;
        if (simpleNode2.e != null) {
            simpleNode2.e.f = simpleNode2;
        }
        simpleNode2.c = simpleNode.c;
        simpleNode2.a(!this.c.contains(simpleNode2));
        this.a(simpleNode, simpleNode2);
        this.a(simpleNode2.f, 1);
        return simpleNode2;
    }
    
    public int a(final Element element, final byte b) {
        SimpleNode f = (SimpleNode)element;
        int n = (f.d != null) ? f.d.a : 0;
        while (f.f != null) {
            if (f.f.e == f) {
                n += ((f.f.d != null) ? f.f.d.a : 0);
                ++n;
            }
            f = f.f;
        }
        return n;
    }
    
    public int a(final Object o, final boolean b, final boolean b2, final byte b3) {
        int n = 0;
        boolean b4 = false;
        SimpleNode simpleNode = this.b;
        while (simpleNode != null) {
            final int compare = this.d.compare(o, simpleNode.a());
            if (compare < 0) {
                simpleNode = simpleNode.d;
            }
            else {
                final SimpleNode d = simpleNode.d;
                if (compare == 0) {
                    b4 = true;
                    if (b) {
                        simpleNode = d;
                        continue;
                    }
                }
                n += ((d != null) ? d.a : 0);
                ++n;
                simpleNode = simpleNode.e;
            }
        }
        if (b4 && !b) {
            --n;
        }
        if (b4 || b2) {
            return n;
        }
        return -1;
    }
    
    public int b() {
        if (this.b == null) {
            return 0;
        }
        return this.b.a;
    }
    
    public String toString() {
        if (this.b == null) {
            return "";
        }
        return this.b.toString();
    }
    
    public static SimpleNode a(final SimpleNode simpleNode) {
        if (simpleNode.e != null) {
            SimpleNode simpleNode2;
            for (simpleNode2 = simpleNode.e; simpleNode2.d != null; simpleNode2 = simpleNode2.d) {}
            return simpleNode2;
        }
        SimpleNode f;
        for (f = simpleNode; f.f != null && f.f.e == f; f = f.f) {}
        return f.f;
    }
    
    public static SimpleNode b(final SimpleNode simpleNode) {
        if (simpleNode.d != null) {
            SimpleNode simpleNode2;
            for (simpleNode2 = simpleNode.d; simpleNode2.e != null; simpleNode2 = simpleNode2.e) {}
            return simpleNode2;
        }
        SimpleNode f;
        for (f = simpleNode; f.f != null && f.f.d == f; f = f.f) {}
        return f.f;
    }
    
    SimpleNode c() {
        if (this.b == null) {
            return null;
        }
        SimpleNode simpleNode;
        for (simpleNode = this.b; simpleNode.d != null; simpleNode = simpleNode.d) {}
        return simpleNode;
    }
    
    private boolean e() {
        for (SimpleNode simpleNode = this.c(); simpleNode != null; simpleNode = a(simpleNode)) {
            final int a = simpleNode.a;
            simpleNode.a(!this.c.contains(simpleNode));
            if (!SimpleTree.a && a != simpleNode.a) {
                throw new AssertionError((Object)("Incorrect count 0 on node: \n" + simpleNode + "\n Expected " + simpleNode.a + " but was " + a));
            }
            final byte b = (byte)((simpleNode.d != null) ? simpleNode.d.c : 0);
            final byte b2 = (byte)((simpleNode.e != null) ? simpleNode.e.c : 0);
            if (!SimpleTree.a && Math.max(b, b2) + 1 != simpleNode.c) {
                throw new AssertionError();
            }
            if (!SimpleTree.a && simpleNode.d != null && simpleNode.d.f != simpleNode) {
                throw new AssertionError();
            }
            if (!SimpleTree.a && simpleNode.e != null && simpleNode.e.f != simpleNode) {
                throw new AssertionError();
            }
            if (!SimpleTree.a && Math.abs(b - b2) >= 2) {
                throw new AssertionError((Object)("Subtree is not AVL: \n" + simpleNode));
            }
        }
        return true;
    }
    
    static {
        a = !SimpleTree.class.desiredAssertionStatus();
    }
}
