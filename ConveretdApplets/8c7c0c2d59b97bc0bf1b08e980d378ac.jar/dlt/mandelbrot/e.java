// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot;

import dlt.a.a.k;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;
import dlt.a.a.i;
import dlt.a.a.h;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import dlt.mandelbrot.a.j;
import dlt.a.a.m;
import dlt.a.a.l;
import dlt.a.d.d;
import dlt.a.e.b;
import dlt.a.a.a;
import dlt.a.b.f;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class e extends JPanel implements MouseMotionListener, KeyListener, c
{
    private f I;
    private f F;
    protected dlt.a.a.a T;
    protected int O;
    protected int N;
    protected dlt.a.e.b W;
    protected d H;
    protected dlt.a.d.b M;
    protected dlt.a.e.a Z;
    protected l aa;
    protected dlt.a.a.f X;
    protected m L;
    protected b J;
    protected a C;
    private dlt.a.c.d D;
    public j E;
    private dlt.mandelbrot.a U;
    int Q;
    int P;
    int G;
    int S;
    private f V;
    private dlt.a.b.c R;
    private dlt.a.b.c K;
    private Object ab;
    private boolean Y;
    
    public e(final int n, final int n2, final dlt.mandelbrot.a u) {
        this.I = new f(0.0, 0.0, 0.0);
        this.F = new f(0.0, 0.0, 0.0);
        this.O = 0;
        this.N = 0;
        this.Q = 5;
        this.P = 100;
        this.G = 20;
        this.S = 60;
        this.V = new f(-45.0, 0.0, 0.0);
        this.R = new dlt.a.b.c(0.0, 600.0, -600.0);
        this.K = new dlt.a.b.c(0.0, 10000.0, -80000.0);
        this.ab = new Object();
        this.Y = false;
        this.C = new a();
        this.a(n, n2);
        this.U = u;
    }
    
    private void a(final int n, final int n2) {
        this.enableEvents(1L);
        this.enableEvents(8L);
        this.setLayout(new BorderLayout());
        (this.T = new dlt.a.a.a(n, n2)).addMouseMotionListener(this);
        this.T.addKeyListener(this);
        this.add(this.T, "Center");
        this.T.setBackground(Color.black);
        this.O = 200;
        this.N = 200;
        this.do();
        this.J = new b();
        this.enableEvents(1L);
        this.T.setVisible(false);
    }
    
    public void processComponentEvent(final ComponentEvent componentEvent) {
        switch (componentEvent.getID()) {
            case 101: {}
            case 102: {
                this.T.setVisible(true);
                this.C.if();
                this.J.for();
                break;
            }
            case 103: {
                this.J.a();
                break;
            }
        }
        super.processComponentEvent(componentEvent);
    }
    
    public void do() {
        this.T.setBackground(Color.blue);
        this.Z = new dlt.a.e.a(this.R.if(), this.V.for());
        this.M = new dlt.a.d.b(this.K.if());
        this.H = new dlt.a.d.c(this.M, 0.0);
        this.W = new dlt.a.e.b(this.H, this.Z);
        this.aa = new h();
        this.X = new i(this.T, this.aa);
        this.L = new m(100.0, 100000.0);
        this.T.if(this.L);
    }
    
    public void int() {
        final Vector a = this.W.a();
        this.W.if();
        this.X.a(this.W, this.L);
        this.T.a();
        final f new1 = this.new();
        final f for1 = this.for();
        final f do1 = this.Z.do();
        do1.a(do1.if() + new1.if(), do1.a(), do1.int());
        this.Z.a(do1);
        for (int i = 0; i < a.size(); ++i) {
            this.W.a(a.elementAt(i), this.Z.if(), for1);
        }
        final dlt.a.b.c a2 = this.M.a();
        dlt.a.b.a.a(for1).a(a2);
        this.M.a(a2);
    }
    
    public f new() {
        final f i = this.I;
        this.I = new f(0.0, 0.0, 0.0);
        return i;
    }
    
    public f for() {
        final f f = this.F;
        this.F = new f(0.0, 0.0, 0.0);
        return f;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (this.isVisible()) {
            final dlt.a.b.c if1 = this.Z.if();
            if (keyEvent.getKeyChar() == '[') {
                this.J.try();
            }
            if (keyEvent.getKeyChar() == ']') {
                this.J.do();
            }
            if (keyEvent.getKeyChar() == 'p') {
                this.J.if();
            }
            if (keyEvent.getKeyChar() == 'o') {
                this.J.new();
            }
            if (keyEvent.getKeyChar() == 'i') {
                this.J.int();
            }
            if (keyEvent.getKeyChar() == 'q') {
                if1.a(if1.for(), if1.a() + 100.0, if1.int());
            }
            if (keyEvent.getKeyChar() == 'a') {
                if1.a(if1.for(), if1.a() - 100.0, if1.int());
            }
            if (keyEvent.getKeyChar() == 's') {
                if1.a(if1.for(), if1.a(), if1.int() + 100.0);
            }
            if (keyEvent.getKeyChar() == 'x') {
                if1.a(if1.for(), if1.a(), if1.int() - 100.0);
            }
            if (keyEvent.getKeyChar() == 'z') {
                if1.a(if1.for() - 100.0, if1.a(), if1.int());
            }
            if (keyEvent.getKeyChar() == 'c') {
                if1.a(if1.for() + 100.0, if1.a(), if1.int());
            }
            this.Z.a(if1);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.isVisible()) {
            int n = 0;
            int n2 = 0;
            if (mouseEvent.getX() < this.O) {
                n2 = 5;
            }
            if (mouseEvent.getX() > this.O) {
                n2 = 355;
            }
            if (mouseEvent.getY() < this.N) {
                n = 5;
            }
            if (mouseEvent.getY() > this.N) {
                n = 355;
            }
            this.I.a(n, 0.0, 0.0);
            this.F.a(0.0, n2, 0.0);
            this.O = mouseEvent.getX();
            this.N = mouseEvent.getY();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void plotted(final int[][] array) {
        this.S = this.U.getGridSize();
        this.G = this.U.getCellSize();
        this.P = this.U.getModulus();
        this.Q = this.U.getMultiplier();
        int n = array.length / this.S;
        if (n < 1) {
            n = 1;
        }
        final int n2 = array.length / n;
        final int n3 = array[0].length / n;
        final int[][] array2 = new int[n2][n3];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                array2[i][j] = array[i * n][j * n];
            }
        }
        this.C.a(array2);
    }
    
    class a implements Runnable
    {
        private int[][] a;
        
        public void a(final int[][] a) {
            this.a = a;
            this.a();
        }
        
        public void if() {
            new Thread(this).start();
        }
        
        private void a() {
            e.this.Y = false;
        }
        
        public void run() {
            synchronized (e.this.ab) {
                try {
                    if (this.a != null) {
                        e.this.Y = true;
                        final Vector a = e.this.W.a();
                        for (int i = 0; i < a.size(); ++i) {
                            e.this.W.a(a.elementAt(i));
                        }
                        final Vector<dlt.a.g.d> vector = new Vector<dlt.a.g.d>();
                        final int length = this.a.length;
                        final int length2 = this.a[0].length;
                        final dlt.a.g.b[][] array = new dlt.a.g.b[length][length2];
                        for (int j = 0; j < length; ++j) {
                            for (int k = 0; k < length2; ++k) {
                                if (!e.this.Y) {
                                    throw new Exception();
                                }
                                array[j][k] = new dlt.a.g.b(new dlt.a.b.c(j * e.this.G, this.a[j][k] * e.this.Q % e.this.P, k * e.this.G));
                            }
                        }
                        final Color[] a2 = e.this.E.a();
                        for (int l = 0; l < length - 1; ++l) {
                            for (int n = 0; n < length2 - 1; ++n) {
                                if (!e.this.Y) {
                                    throw new Exception();
                                }
                                final dlt.a.g.b b = array[l][n];
                                final dlt.a.g.b b2 = array[l + 1][n];
                                final dlt.a.g.b b3 = array[l + 1][n + 1];
                                final dlt.a.g.b b4 = array[l][n + 1];
                                final Color color = a2[e.this.E.if(this.a[l][n])];
                                vector.add(new dlt.a.g.d(new dlt.a.g.b[] { b, b3, b2 }, color));
                                vector.add(new dlt.a.g.d(new dlt.a.g.b[] { b, b4, b3 }, color));
                            }
                        }
                        final dlt.a.g.c c = new dlt.a.g.c(vector.toArray(new dlt.a.g.d[(length - 1) * (length2 - 1) * 2]));
                        c.do();
                        e.this.D = new dlt.a.c.c().a(c);
                        e.this.W.do(e.this.D);
                        System.out.println("FINISHED");
                        this.a = null;
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    class b implements Runnable
    {
        public boolean if;
        private int a;
        
        b() {
            this.if = true;
            this.a = 0;
        }
        
        public void try() {
            this.a = 1;
        }
        
        public void do() {
            this.a = 2;
        }
        
        public void if() {
            this.a = 3;
        }
        
        public void new() {
            this.a = 4;
        }
        
        public void int() {
            this.a = 5;
        }
        
        public void for() {
            final Vector a = e.this.W.a();
            for (int i = 0; i < a.size(); ++i) {
                a.elementAt(i).for();
            }
            e.this.Z.a(e.this.V.for());
            e.this.Z.a(e.this.R.if());
            e.this.M.a(e.this.K.if());
            new Thread(this).start();
        }
        
        public void a() {
            this.if = false;
        }
        
        public void run() {
            this.if = true;
            while (this.if) {
                if (e.this.X != null) {
                    if (this.a == 1) {
                        e.this.T.a(e.this.X);
                        e.this.X = new i(e.this.T, new h());
                    }
                    else if (this.a == 2) {
                        e.this.T.a(e.this.X);
                        e.this.X = new k(e.this.T, new h());
                    }
                    else if (this.a == 3) {
                        e.this.T.a(e.this.X);
                        e.this.X = new dlt.a.a.j(e.this.T, new h());
                    }
                    else if (this.a == 4) {
                        e.this.T.a(e.this.X);
                        e.this.X = new dlt.a.a.e(e.this.T, new h());
                    }
                    else if (this.a == 5) {
                        e.this.T.a(e.this.X);
                        e.this.X = new dlt.a.a.b(e.this.T, new h());
                    }
                    this.a = 0;
                }
                e.this.int();
            }
        }
    }
}
