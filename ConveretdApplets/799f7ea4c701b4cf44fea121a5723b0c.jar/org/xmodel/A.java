// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.Iterator;
import org.xmodel.A.F;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.ArrayList;
import org.xmodel.A.G;
import org.xmodel.A.H;
import org.xmodel.A.D;
import org.xmodel.A.C;
import org.xmodel.A.E;
import org.xmodel.A.B;
import java.util.List;
import org.xmodel.log.Log;

public final class A
{
    private static Log C;
    private int A;
    private List<B> G;
    private E I;
    private org.xmodel.A.A J;
    private C D;
    private D B;
    private H E;
    private G K;
    private boolean F;
    private IChangeSet H;
    
    static {
        A.C = Log.getLog("org.xmodel");
    }
    
    public A() {
        this.G = new ArrayList<B>(3);
        this.I = new E();
        this.J = new org.xmodel.A.A();
        this.D = new C();
        this.B = new D();
        this.E = new H();
        this.K = new G();
        this.F = false;
    }
    
    public void C() {
        for (int i = 0; i < this.G.size(); ++i) {
            this.G.get(i).A();
        }
        this.G.clear();
        this.F = false;
    }
    
    public void A(final IModelObject h, final String i, final Object j, final Object g) {
        this.I.H = h;
        this.I.I = i;
        this.I.J = j;
        this.I.G = g;
        this.G.add(this.I);
    }
    
    public void A(final IModelObject w, final String x, final Object v) {
        this.J.W = w;
        this.J.X = x;
        this.J.V = v;
        this.G.add(this.J);
    }
    
    public void B(final IModelObject modelObject, final IModelObject q, final int a) {
        this.D.B = modelObject;
        this.D.C = q;
        this.D.A = a;
        this.G.add(this.D);
        this.B.E = q.getParent();
        if (this.B.E != null) {
            final List<IModelObject> children = this.B.E.getChildren();
            this.B.F = q;
            this.B.D = children.indexOf(q);
        }
        this.G.add(this.B);
        this.K.Q = q;
        this.K.P = modelObject;
        this.K.O = q.getParent();
        this.G.add(this.K);
    }
    
    public void A(final IModelObject e, final IModelObject modelObject, final int d) {
        this.B.E = e;
        this.B.F = modelObject;
        this.B.D = d;
        this.G.add(this.B);
        this.K.Q = modelObject;
        this.K.P = null;
        this.K.O = modelObject.getParent();
        this.G.add(this.K);
    }
    
    public void A(final IModelObject s, final IModelObject u, final int t, final int r) {
        this.E.S = s;
        this.E.U = u;
        this.E.T = t;
        this.E.R = r;
        this.G.add(this.E);
    }
    
    public void A(final IVariableScope l, final String n, final Object m, final Object k) {
        final F f = new F();
        f.L = l;
        f.N = n;
        f.M = m;
        f.K = k;
        this.G.add(f);
    }
    
    public void A(final int a) {
        this.A = a;
    }
    
    public int F() {
        return this.A;
    }
    
    public void G() {
        if (!this.F) {
            this.F = true;
            for (final B b : this.G) {
                try {
                    b.B();
                }
                catch (Exception ex) {
                    System.err.println("Warning: Two or more listeners have updated the object which triggered their\n  notification. The prior state of the model could not be restored and some\n  listeners may not have triggered appropriately. This problem is an indication\n  of a design problem and can always be avoided.");
                    org.xmodel.A.C.exception(ex);
                }
            }
        }
    }
    
    public void B() {
        if (this.F) {
            this.F = false;
            for (final B b : this.G) {
                try {
                    b.C();
                }
                catch (Exception ex) {
                    System.err.println("Warning: Two or more listeners have updated the object which triggered their\n  notification. The prior state of the model could not be restored and some\n  listeners may not have triggered appropriately. This problem is an indication\n  of a design problem and can always be avoided.");
                    org.xmodel.A.C.exception(ex);
                }
            }
        }
    }
    
    public boolean D() {
        return this.F;
    }
    
    public IChangeSet A() {
        if (this.H == null) {
            this.H = new ChangeSet();
        }
        return this.H;
    }
    
    public void E() {
        final IChangeSet h = this.H;
        if (this.H != null) {
            this.H = null;
            h.applyChanges();
        }
    }
}
