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

public class do extends Canvas implements Runnable
{
    public static final byte n = -1;
    public static final byte o = 1;
    protected byte p;
    protected volatile Thread sb;
    protected int q;
    protected int r;
    protected Vector s;
    protected Hashtable t;
    private boolean u;
    private boolean v;
    private ActionListener w;
    private goto x;
    private int y;
    private static String a = "\ua16d\ua154\ua147\ua150\ua14e\ua14d\ua143\ua146\ua10c\ua176\ua14b\ua141\ua149\ua147\ua150\ua171\ua141\ua150\ua14d\ua14e\ua14e";
    
    public do(final int y) {
        this.q = 1;
        this.r = 10;
        this.u = true;
        this.v = true;
        this.y = y;
        this.s = new Vector();
        this.t = new Hashtable();
        this.p = -1;
        final if if1 = new if(this);
        this.addMouseListener(if1);
        this.addMouseMotionListener(if1);
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.w = AWTEventMulticaster.add(this.w, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.w = AWTEventMulticaster.remove(this.w, actionListener);
    }
    
    public void a(final final final1, final Object o, final finally finally1) {
        final Dimension size = this.getSize();
        final goto goto1 = this.t.get(o);
        synchronized (this.s) {
            if (goto1 != null) {
                goto1.a(final1, this.u || goto1.getX() > size.width);
                final int index = this.s.indexOf(goto1);
                int n = goto1.getX() + goto1.getWidth();
                for (int size2 = this.s.size(), i = index + 1; i < size2; ++i) {
                    final goto goto2 = this.s.elementAt(i);
                    goto2.b(n);
                    n += goto2.getWidth();
                }
            }
            else {
                final goto goto3 = new goto(final1, finally1, o);
                if (this.s.isEmpty()) {
                    goto3.b(0);
                    this.s.addElement(goto3);
                }
                else {
                    final goto goto4 = this.s.lastElement();
                    goto3.b(goto4.getX() + goto4.getWidth());
                    this.s.addElement(goto3);
                }
                this.t.put(o, goto3);
            }
        }
        // monitorexit(this.s)
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        synchronized (this.s) {
            int n = -1;
            final int size2 = this.s.size();
            if (size2 != 0) {
                goto goto1;
                for (int i = this.s.firstElement().getX(); i < size.width; i += goto1.getWidth()) {
                    if (n == size2 - 1) {
                        n = -1;
                    }
                    goto1 = this.s.elementAt(++n);
                    goto1.b(this, graphics, i, goto1 == this.x);
                }
            }
        }
        // monitorexit(this.s)
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            Thread.sleep(100L);
            this.repaint();
            if (this.y > 0) {
                Thread.sleep(this.y);
            }
        }
        catch (InterruptedException ex) {}
        long currentTimeMillis = System.currentTimeMillis();
        this.u = false;
        while (this.sb == currentThread) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            if (this.v) {
                this.i();
                if (currentTimeMillis2 - currentTimeMillis > 5000L) {
                    currentTimeMillis = Long.MAX_VALUE;
                }
                this.repaint();
            }
            final long n = this.r - (System.currentTimeMillis() - currentTimeMillis2);
            if (n > 1L) {
                try {
                    Thread.sleep(n);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void a(final boolean v) {
        this.v = v;
    }
    
    private void i() {
        synchronized (this.s) {
            if (this.s.isEmpty()) {
                // monitorexit(this.s)
                return;
            }
            if (this.p == -1) {
                try {
                    final goto goto1 = this.s.firstElement();
                    if (goto1.getX() + goto1.getWidth() < 0) {
                        if (goto1.a()) {
                            this.s.removeElementAt(0);
                            this.t.remove(goto1._());
                        }
                        else {
                            final goto goto2 = this.s.lastElement();
                            if (goto2 != goto1) {
                                this.s.removeElementAt(0);
                                goto1.b(goto2.getX() + goto2.getWidth());
                                this.s.addElement(goto1);
                            }
                            else {
                                goto1.b(0);
                            }
                            goto1.validate();
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                final goto goto3 = this.s.firstElement();
                if (goto3.getX() > 0) {
                    goto goto4 = null;
                    while (!this.s.isEmpty() && (goto4 = this.s.lastElement()).a()) {
                        this.s.removeElement(goto4);
                        this.t.remove(goto4._());
                    }
                    if (this.s.isEmpty()) {
                        // monitorexit(this.s)
                        return;
                    }
                    if (goto4 != goto3) {
                        this.s.removeElement(goto4);
                        goto4.b(goto3.getX() - goto4.getWidth());
                        this.s.insertElementAt(goto4, 0);
                    }
                    else {
                        goto3.b(goto3.getX() - goto3.getWidth());
                    }
                }
            }
            for (int i = 0; i < this.s.size(); ++i) {
                ((goto)this.s.elementAt(i))._(this.p * this.q);
            }
        }
        // monitorexit(this.s)
    }
    
    public void start() {
        (this.sb = new Thread(this, do.a)).setPriority(10);
        this.sb.start();
    }
    
    public void stop() {
        this.sb = null;
    }
    
    public void a(final int r) {
        this.r = r;
    }
    
    public void b(final byte p) {
        this.p = p;
    }
    
    public byte a() {
        return this.p;
    }
    
    public void n(final int q) {
        this.q = q;
    }
    
    public void j() {
        final int width = this.getSize().width;
        synchronized (this.s) {
            for (int i = 0; i < this.s.size(); ++i) {
                final goto goto1 = this.s.elementAt(i);
                if (goto1._() == case.z) {
                    if (i != 0) {
                        final goto goto2 = this.s.elementAt(i - 1);
                        goto1.b(goto2.getX() + goto2.getWidth());
                    }
                }
                else {
                    if (width < goto1.getX()) {
                        this.s.removeElementAt(i);
                        this.t.remove(goto1._());
                        --i;
                    }
                    else {
                        goto1.a();
                    }
                    goto1.a();
                }
            }
        }
        // monitorexit(this.s)
    }
    
    private goto _(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int width = this.getSize().width;
        synchronized (this.s) {
            if (this.x != null && this.x.contains(x - this.x.getX(), mouseEvent.getY())) {
                // monitorexit(this.s)
                return this.x;
            }
            if (this.s.size() == 0) {
                // monitorexit(this.s)
                return null;
            }
            final int x3;
            int i = x3 = this.s.elementAt(0).getX();
            while (i < width) {
                int j = 0;
                while (j < this.s.size()) {
                    final goto goto2 = this.s.elementAt(j);
                    if (i <= x && x < i + goto2.getWidth()) {
                        if (goto2.contains(x - i, mouseEvent.getY())) {
                            // monitorexit(this.s)
                            return goto2;
                        }
                        // monitorexit(this.s)
                        return null;
                    }
                    else {
                        i += goto2.getWidth();
                        ++j;
                    }
                }
                if (x3 == i) {
                    // monitorexit(this.s)
                    return null;
                }
            }
        }
        // monitorexit(this.s)
        return null;
    }
    
    static goto b(final do do1) {
        return do1.x;
    }
    
    static goto a(final do do1, final MouseEvent mouseEvent) {
        return do1._(mouseEvent);
    }
    
    static void a(final do do1, final goto x) {
        do1.x = x;
    }
    
    static ActionListener _(final do do1) {
        return do1.w;
    }
    
    private static String n(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFA122);
        }
        return new String(array);
    }
    
    static {
        do.a = n(do.a);
    }
}
