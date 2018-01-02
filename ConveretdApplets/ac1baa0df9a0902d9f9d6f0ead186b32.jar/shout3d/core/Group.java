// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;
import java.util.Vector;

public class Group extends Node
{
    public final Node[] defaultChildArray;
    public final NodeArrayField children;
    public final BooleanField hidden;
    private float[] a;
    private float[] b;
    private float[] c;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private float[] h;
    private float i;
    protected boolean j;
    protected int k;
    protected boolean l;
    private int m;
    private int n;
    private int o;
    private float[] p;
    private float[] q;
    private Vector r;
    private int s;
    private int t;
    private float[] u;
    private float[] v;
    float w;
    float x;
    int y;
    int z;
    int A;
    protected float[] B;
    
    public void addChildren(final Node[] value) {
        if (this.children.a != null) {
            final Vector vector = new Vector<Node>(value.length);
            int n = 0;
            for (int i = 0; i < value.length; ++i) {
                boolean b = false;
                for (int j = 0; j < this.children.a.length; ++j) {
                    if (this.children.a[j] == null) {
                        System.out.println("children.value[j]  " + this.children.a[j]);
                    }
                    if (this.children.a[j].equals(value[i])) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    vector.addElement(value[i]);
                    ++n;
                }
            }
            if (n > 0) {
                final Node[] a = new Node[this.children.a.length + n];
                System.arraycopy(this.children.a, 0, a, 0, this.children.a.length);
                int length = this.children.a.length;
                for (int k = 0; k < vector.size(); ++k) {
                    a[length++] = vector.elementAt(k);
                }
                this.children.a = a;
                this.children.setValue(this.children.a);
            }
        }
        else if (value != null) {
            this.children.setValue(value);
        }
    }
    
    protected void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[] array5) {
        System.arraycopy(array, 9, array4, 0, 3);
        System.arraycopy(array, 9, array5, 0, 3);
        this.A = 0;
        this.z = 0;
        while (this.z < 3) {
            this.y = 0;
            while (this.y < 3) {
                this.w = array[this.A] * array2[this.z];
                this.x = array[this.A] * array3[this.z];
                if (this.w < this.x) {
                    final int y = this.y;
                    array4[y] += this.w;
                    final int y2 = this.y;
                    array5[y2] += this.x;
                }
                else {
                    final int y3 = this.y;
                    array4[y3] += this.x;
                    final int y4 = this.y;
                    array5[y4] += this.w;
                }
                ++this.A;
                ++this.y;
            }
            ++this.z;
        }
    }
    
    public Group() {
        this.defaultChildArray = new Node[0];
        this.children = new NodeArrayField(this, "children", 0, this.defaultChildArray);
        this.hidden = new BooleanField(this, "hidden", 0, false);
        this.a = new float[3];
        this.b = new float[3];
        this.c = new float[3];
        this.d = new float[3];
        this.e = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.f = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.g = new float[3];
        this.h = new float[3];
        this.i = Float.MAX_VALUE;
        this.j = false;
        this.p = new float[3];
        this.q = new float[3];
        this.r = new Vector();
        this.u = new float[3];
        this.v = new float[3];
        this.A = 0;
        this.B = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
    }
    
    public float[] getMatrix() {
        return new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
    }
    
    public float[] getInverseMatrix() {
        return new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
    }
    
    public float[] getLocalBBoxMax() {
        return this.h;
    }
    
    public float[] getCameraBBoxMax(final Node[] array, final Node[] array2) {
        this.e = MatUtil.a(array2, true);
        this.f = MatUtil.a(array, false);
        MatUtil.h(this.e, this.f);
        this.a(this.e, this.g, this.h, this.c, this.d);
        return this.d;
    }
    
    protected boolean c(final g g) {
        this.l = g.bf;
        this.a(g.b(), this.g, this.h, this.p, this.q);
        if (g.bf) {
            this.k = g.a(this.p, this.q);
            if (this.k == 0) {
                return true;
            }
            if (this.k == 1) {
                g.bf = false;
            }
            else {
                g.bf = true;
            }
        }
        return false;
    }
    
    protected void a(final g g) {
        if (this.hidden.g) {
            return;
        }
        if (this.children.a == null) {
            return;
        }
        g.a((Node)this);
        this.n = 0;
        while (this.n < this.children.a.length) {
            this.children.a[this.n].a(g);
            ++this.n;
        }
        g.c();
    }
    
    public void a(final float[] array) {
        System.arraycopy(array, 0, this.B, 0, 12);
        if (this.children.a == null) {
            return;
        }
        for (int i = 0; i < this.children.a.length; ++i) {
            if (this.children.a[i] instanceof Group) {
                ((Group)this.children.a[i]).a(this.B);
            }
        }
    }
    
    public float[] getWorldBBoxMin(final Node[] array) {
        this.a(this.e = MatUtil.a(array, true), this.g, this.h, this.a, this.b);
        return this.a;
    }
    
    protected void d(final g g) {
        this.j = false;
        this.g[0] = this.i;
        this.h[0] = -this.i;
        this.g[1] = this.i;
        this.h[1] = -this.i;
        this.g[2] = this.i;
        this.h[2] = -this.i;
        if (this.children.a == null || this.children.a.length == 0) {
            return;
        }
        g.a((Node)this);
        this.s = 0;
        while (this.s < this.children.a.length) {
            if (this.children.a[this.s] instanceof Shape) {
                if (((Shape)this.children.a[this.s]).geometry.a != null) {
                    final Shape shape = (Shape)this.children.a[this.s];
                    if (shape != null) {
                        final Geometry geometry = (Geometry)shape.geometry.a;
                        geometry.c(g);
                        if (geometry.a[0] < this.g[0]) {
                            this.g[0] = geometry.a[0];
                        }
                        if (geometry.b[0] > this.h[0]) {
                            this.h[0] = geometry.b[0];
                        }
                        if (geometry.a[1] < this.g[1]) {
                            this.g[1] = geometry.a[1];
                        }
                        if (geometry.b[1] > this.h[1]) {
                            this.h[1] = geometry.b[1];
                        }
                        if (geometry.a[2] < this.g[2]) {
                            this.g[2] = geometry.a[2];
                        }
                        if (geometry.b[2] > this.h[2]) {
                            this.h[2] = geometry.b[2];
                        }
                        this.j = true;
                    }
                }
            }
            else if (this.children.a[this.s] instanceof Group) {
                final Group group = (Group)this.children.a[this.s];
                group.d(g);
                if (group.j) {
                    if (group.getTypeName().equals("Group")) {
                        System.arraycopy(group.g, 0, this.u, 0, 3);
                        System.arraycopy(group.h, 0, this.v, 0, 3);
                    }
                    else {
                        this.a(group.b(), group.g, group.h, this.u, this.v);
                    }
                    if (this.u[0] < this.g[0]) {
                        this.g[0] = this.u[0];
                    }
                    if (this.u[1] < this.g[1]) {
                        this.g[1] = this.u[1];
                    }
                    if (this.u[2] < this.g[2]) {
                        this.g[2] = this.u[2];
                    }
                    if (this.v[0] > this.h[0]) {
                        this.h[0] = this.v[0];
                    }
                    if (this.v[1] > this.h[1]) {
                        this.h[1] = this.v[1];
                    }
                    if (this.v[2] > this.h[2]) {
                        this.h[2] = this.v[2];
                    }
                    this.j = true;
                }
            }
            ++this.s;
        }
        g.c();
    }
    
    public Node[] a(final Node[] array) {
        Node[] a = array;
        if (this.children.a != null) {
            for (int i = 0; i < this.children.a.length; ++i) {
                if (this.children.a[i] != null) {
                    a = this.children.a[i].a(a);
                }
            }
        }
        return a;
    }
    
    protected void e(final g g) {
        g.bf = this.l;
    }
    
    public float[] a() {
        return this.B;
    }
    
    protected float[] a(final float[][] array, final int n) {
        return this.c();
    }
    
    public void b(final g g) {
        if (this.children.a == null || this.hidden.g) {
            return;
        }
        if (this.c(g)) {
            return;
        }
        this.f(g);
        this.n = 0;
        while (this.n < this.children.a.length) {
            if (!(this.children.a[this.n] instanceof Light)) {
                this.children.a[this.n].b(g);
            }
            ++this.n;
        }
        this.g(g);
        this.e(g);
    }
    
    protected void f(final g g) {
        this.r.setSize(0);
        this.n = 0;
        while (this.n < this.children.a.length) {
            if (this.children.a[this.n] instanceof Light) {
                final Light light = (Light)this.children.a[this.n];
                if (light.affectedGroups.a == null) {
                    g.be.addElement(light);
                    this.r.addElement(light);
                }
            }
            ++this.n;
        }
        final String defName = this.getDEFName();
        this.n = 0;
        while (this.n < g.bd.size()) {
            final Light light2 = g.bd.elementAt(this.n);
            if (light2.affectedGroups.a != null) {
                boolean b = false;
                this.o = 0;
                while (this.o < light2.affectedGroups.a.length) {
                    final String s = light2.affectedGroups.a[this.o];
                    if (s != null && s.equals(defName)) {
                        b = true;
                    }
                    else if (s != null && s.equals("#Root") && g.W == this) {
                        b = true;
                    }
                    ++this.o;
                }
                if (b) {
                    g.be.addElement(light2);
                    this.r.addElement(light2);
                }
            }
            ++this.n;
        }
    }
    
    protected void g(final g g) {
        this.n = 0;
        while (this.n < this.r.size()) {
            g.be.removeElement(this.r.elementAt(this.n));
            ++this.n;
        }
    }
    
    public void removeChildren(final Node[] array) {
        if (this.children.a != null) {
            final Vector vector = new Vector<Node>(this.children.a.length);
            for (int i = 0; i < this.children.a.length; ++i) {
                vector.addElement(this.children.a[i]);
            }
            boolean b = false;
            for (int j = 0; j < array.length; ++j) {
                if (vector.removeElement(array[j])) {
                    b = true;
                }
            }
            if (b) {
                if (vector.size() == 0) {
                    this.children.a = null;
                }
                else {
                    vector.copyInto(this.children.a = new Node[vector.size()]);
                }
                this.children.setValue(this.children.a);
            }
        }
    }
    
    public float[] getLocalBBoxMin() {
        return this.g;
    }
    
    public float[] getCameraBBoxMin(final Node[] array, final Node[] array2) {
        this.e = MatUtil.a(array2, true);
        this.f = MatUtil.a(array, false);
        MatUtil.h(this.e, this.f);
        this.a(this.e, this.g, this.h, this.c, this.d);
        return this.c;
    }
    
    public float[] b() {
        return new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
    }
    
    public float[] getWorldBBoxMax(final Node[] array) {
        this.a(this.e = MatUtil.a(array, true), this.g, this.h, this.a, this.b);
        return this.b;
    }
    
    public float[] c() {
        return new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
    }
}
