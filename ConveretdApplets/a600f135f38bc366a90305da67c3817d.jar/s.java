import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.AWTEventMulticaster;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends Canvas implements Runnable
{
    public static final byte Q = -1;
    public static final byte R = 1;
    protected byte S;
    protected volatile Thread q;
    protected int T;
    protected int U;
    protected Vector V;
    protected Hashtable W;
    private boolean X;
    private boolean Y;
    private ActionListener Z;
    private case _a;
    private int aa;
    private static String l = "\ua494\ua4ad\ua4be\ua4a9\ua4b7\ua4b4\ua4ba\ua4bf\ua4f5\ua48f\ua4b2\ua4b8\ua4b0\ua4be\ua4a9\ua488\ua4b8\ua4a9\ua4b4\ua4b7\ua4b7";
    
    public s(final int aa) {
        this.T = 1;
        this.U = 10;
        this.X = true;
        this.Y = true;
        this.aa = aa;
        this.V = new Vector();
        this.W = new Hashtable();
        this.S = -1;
        final catch catch1 = new catch(this);
        this.addMouseListener(catch1);
        this.addMouseMotionListener(catch1);
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.Z = AWTEventMulticaster.add(this.Z, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.Z = AWTEventMulticaster.remove(this.Z, actionListener);
    }
    
    public void _(final u u, final Object o, final v v) {
        final Dimension size = this.getSize();
        final case case1 = this.W.get(o);
        synchronized (this.V) {
            if (case1 != null) {
                case1.a(u, this.X || case1.getX() > size.width);
                final int index = this.V.indexOf(case1);
                int n = case1.getX() + case1.getWidth();
                for (int size2 = this.V.size(), i = index + 1; i < size2; ++i) {
                    final case case2 = this.V.elementAt(i);
                    case2.b(n);
                    n += case2.getWidth();
                }
            }
            else {
                final case case3 = new case(u, v, o);
                if (this.V.isEmpty()) {
                    case3.b(0);
                    this.V.addElement(case3);
                }
                else {
                    final case case4 = this.V.lastElement();
                    case3.b(case4.getX() + case4.getWidth());
                    this.V.addElement(case3);
                }
                this.W.put(o, case3);
            }
        }
        // monitorexit(this.V)
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        synchronized (this.V) {
            int n = -1;
            final int size2 = this.V.size();
            if (size2 != 0) {
                case case1;
                for (int i = this.V.firstElement().getX(); i < size.width; i += case1.getWidth()) {
                    if (n == size2 - 1) {
                        n = -1;
                    }
                    case1 = this.V.elementAt(++n);
                    case1.b(this, graphics, i, case1 == this._a);
                }
            }
        }
        // monitorexit(this.V)
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            Thread.sleep(100L);
            this.repaint();
            if (this.aa > 0) {
                Thread.sleep(this.aa);
            }
        }
        catch (InterruptedException ex) {}
        long currentTimeMillis = System.currentTimeMillis();
        this.X = false;
        while (this.q == currentThread) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            if (this.Y) {
                this.g();
                if (currentTimeMillis2 - currentTimeMillis > 5000L) {
                    currentTimeMillis = Long.MAX_VALUE;
                }
                this.repaint();
            }
            final long n = this.U - (System.currentTimeMillis() - currentTimeMillis2);
            if (n > 1L) {
                try {
                    Thread.sleep(n);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void b(final boolean y) {
        this.Y = y;
    }
    
    private void g() {
        synchronized (this.V) {
            if (this.V.isEmpty()) {
                // monitorexit(this.V)
                return;
            }
            if (this.S == -1) {
                try {
                    final case case1 = this.V.firstElement();
                    if (case1.getX() + case1.getWidth() < 0) {
                        if (case1.b()) {
                            this.V.removeElementAt(0);
                            this.W.remove(case1.b());
                        }
                        else {
                            final case case2 = this.V.lastElement();
                            if (case2 != case1) {
                                this.V.removeElementAt(0);
                                case1.b(case2.getX() + case2.getWidth());
                                this.V.addElement(case1);
                            }
                            else {
                                case1.b(0);
                            }
                            case1.validate();
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                final case case3 = this.V.firstElement();
                if (case3.getX() > 0) {
                    case case4 = null;
                    while (!this.V.isEmpty() && (case4 = this.V.lastElement()).b()) {
                        this.V.removeElement(case4);
                        this.W.remove(case4.b());
                    }
                    if (this.V.isEmpty()) {
                        // monitorexit(this.V)
                        return;
                    }
                    if (case4 != case3) {
                        this.V.removeElement(case4);
                        case4.b(case3.getX() - case4.getWidth());
                        this.V.insertElementAt(case4, 0);
                    }
                }
            }
            for (int i = 0; i < this.V.size(); ++i) {
                ((case)this.V.elementAt(i))._(this.S * this.T);
            }
        }
        // monitorexit(this.V)
    }
    
    public void start() {
        (this.q = new Thread(this, s.l)).setPriority(10);
        this.q.start();
    }
    
    public void stop() {
        this.q = null;
    }
    
    public void a(final int u) {
        this.U = u;
    }
    
    public void _(final byte s) {
        this.S = s;
    }
    
    public byte b() {
        return this.S;
    }
    
    public void g(final int t) {
        this.T = t;
    }
    
    public void h() {
        final int width = this.getSize().width;
        synchronized (this.V) {
            for (int i = 0; i < this.V.size(); ++i) {
                final case case1 = this.V.elementAt(i);
                if (case1.b() == l.ba) {
                    if (i != 0) {
                        final case case2 = this.V.elementAt(i - 1);
                        case1.b(case2.getX() + case2.getWidth());
                    }
                }
                else {
                    if (width < case1.getX()) {
                        this.V.removeElementAt(i);
                        this.W.remove(case1.b());
                        --i;
                    }
                    else {
                        case1.b();
                    }
                    case1.b();
                }
            }
        }
        // monitorexit(this.V)
    }
    
    private case a(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int width = this.getSize().width;
        synchronized (this.V) {
            if (this._a != null && this._a.contains(x - this._a.getX(), mouseEvent.getY())) {
                // monitorexit(this.V)
                return this._a;
            }
            if (this.V.size() == 0) {
                // monitorexit(this.V)
                return null;
            }
            final int x2;
            int i = x2 = this.V.elementAt(0).getX();
            while (i < width) {
                int j = 0;
                while (j < this.V.size()) {
                    final case case2 = this.V.elementAt(j);
                    if (i <= x && x < i + case2.getWidth()) {
                        if (case2.contains(x - i, mouseEvent.getY())) {
                            // monitorexit(this.V)
                            return case2;
                        }
                        // monitorexit(this.V)
                        return null;
                    }
                    else {
                        i += case2.getWidth();
                        ++j;
                    }
                }
                if (x2 == i) {
                    // monitorexit(this.V)
                    return null;
                }
            }
        }
        // monitorexit(this.V)
        return null;
    }
    
    static case _(final s s) {
        return s._a;
    }
    
    static case _(final s s, final MouseEvent mouseEvent) {
        return s.a(mouseEvent);
    }
    
    static void _(final s s, final case a) {
        s._a = a;
    }
    
    static ActionListener _(final s s) {
        return s.Z;
    }
    
    private static String j(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFA4DB);
        }
        return new String(array);
    }
    
    static {
        s.l = j(s.l);
    }
}
