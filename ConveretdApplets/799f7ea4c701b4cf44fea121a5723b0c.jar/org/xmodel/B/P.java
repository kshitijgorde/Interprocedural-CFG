// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.HashMap;
import org.xmodel.IPathListener;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IPath;
import java.util.Map;

public class P implements A
{
    static Map<String, D> B;
    private R[] A;
    private IPath F;
    private IContext D;
    private IPathListener E;
    private boolean C;
    
    static {
        P.B = new HashMap<String, D>();
    }
    
    public P(final IPath f, final A a, final IContext d, final IPathListener e) {
        this.F = f;
        this.D = d;
        this.E = e;
        this.C = false;
        final R[] c = a.C();
        this.A = new R[c.length];
        for (int i = 0; i < c.length; ++i) {
            this.A[i] = c[i].A(this);
        }
    }
    
    public P(final IPath f, final IContext d, final IPathListener e) {
        this.F = f;
        this.D = d;
        this.E = e;
        this.C = false;
        this.F();
    }
    
    @Override
    public R[] C() {
        if (this.A == null) {
            return new R[0];
        }
        return this.A;
    }
    
    @Override
    public IPath D() {
        return this.F;
    }
    
    @Override
    public IContext B() {
        return this.D;
    }
    
    @Override
    public IPathListener A() {
        return this.C ? this.E : null;
    }
    
    @Override
    public void B(final IModelObject modelObject) {
        if (this.C) {
            return;
        }
        this.C = true;
        R[] a;
        for (int length = (a = this.A).length, i = 0; i < length; ++i) {
            a[i].B(this.D);
        }
        if (this.A.length > 0) {
            final ArrayList<IModelObject> list = new ArrayList<IModelObject>(1);
            list.add(modelObject);
            this.A[0].D(list);
        }
    }
    
    @Override
    public void A(final IModelObject modelObject) {
        if (!this.C) {
            return;
        }
        R[] a;
        for (int length = (a = this.A).length, i = 0; i < length; ++i) {
            a[i].A(this.D);
        }
        if (this.A.length > 0) {
            final ArrayList<IModelObject> list = new ArrayList<IModelObject>(1);
            list.add(modelObject);
            this.A[0].B(list);
        }
        this.C = false;
    }
    
    public static D A(String s) {
        synchronized (P.B) {
            final IPath path = XPath.createPath(s);
            if (path == null) {
                final IExpression expression = XPath.createExpression(s);
                if (expression == null) {
                    // monitorexit(P.B)
                    return null;
                }
                s = expression.toString();
            }
            else {
                s = path.toString();
            }
            D d = P.B.get(s);
            if (d == null) {
                d = new D();
                P.B.put(s, d);
            }
            // monitorexit(P.B)
            return d;
        }
    }
    
    public static void G() {
        synchronized (P.B) {
            P.B.clear();
        }
        // monitorexit(P.B)
    }
    
    @Override
    public void B(final List<IModelObject> list, final int n) {
        if (list.size() == 0) {
            return;
        }
        final D e = this.E();
        if (e != null && (!e.B() || n == this.F.length())) {
            final L l = new L(L._A.C, this.F, n);
            l.A(list);
            e.A(l);
        }
    }
    
    @Override
    public void A(final List<IModelObject> list, final int n) {
        if (list.size() == 0) {
            return;
        }
        final D e = this.E();
        if (e != null && (!e.B() || n == this.F.length())) {
            final L l = new L(L._A.A, this.F, n);
            l.A(list);
            e.A(l);
        }
    }
    
    @Override
    public void D(final List<IModelObject> list, final int n) {
        final D e = this.E();
        if (e != null && (!e.B() || n == this.F.length())) {
            final L l = new L(L._A.C, this.F, n);
            l.A(list);
            e.A(l);
        }
    }
    
    @Override
    public void C(final List<IModelObject> list, final int n) {
        final D e = this.E();
        if (e != null && (!e.B() || n == this.F.length())) {
            final L l = new L(L._A.A, this.F, n);
            l.A(list);
            e.A(l);
        }
    }
    
    private D E() {
        if (P.B.size() > 0) {
            return P.B.get(this.F.toString());
        }
        return null;
    }
    
    private void F() {
        final ArrayList<R> list = new ArrayList<R>(this.F.length());
        for (int i = 0; i <= this.F.length(); ++i) {
            R a = this.A(i);
            if (i > 0 && this.F.getPathElement(i - 1).predicate() != null) {
                a = new Q(a);
            }
            list.add(a);
        }
        this.A = list.toArray(new R[0]);
    }
    
    private R A(final int n) {
        if (n >= this.F.length()) {
            return new E(this);
        }
        final org.xmodel.B pathElement = this.F.getPathElement(n);
        switch (pathElement.axis()) {
            case 4:
            case 6: {
                return new K(this, n);
            }
            case 128: {
                return new H(this, n);
            }
            case 16: {
                return new B(this, n);
            }
            case 32:
            case 34: {
                return new C(this, n);
            }
            case 64:
            case 66: {
                return new I(this, n);
            }
            case 8: {
                return new G(this, n);
            }
            case 1: {
                return new M(this, n);
            }
            case 2: {
                return new O(this, n);
            }
            default: {
                throw new IllegalArgumentException("Unsupported axis: " + pathElement);
            }
        }
    }
    
    @Override
    public int hashCode() {
        return this.F.hashCode() + this.D.hashCode() + this.E.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            final A a = (A)o;
            return a.D() == this.F && a.A() == this.E && a.B().equals(this.D);
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.E instanceof org.xmodel.xpath.expression.A) {
            sb.append(((org.xmodel.xpath.expression.A)this.E).P());
        }
        else {
            sb.append(this.E.getClass().getSimpleName());
        }
        sb.append(", ");
        sb.append(this.D.getObject());
        return sb.toString();
    }
}
