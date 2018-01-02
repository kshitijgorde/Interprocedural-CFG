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

public class implements extends Canvas implements Runnable
{
    public static final byte Oa = -1;
    public static final byte Pa = 1;
    protected byte Qa;
    protected volatile Thread la;
    protected int Ra;
    protected int Sa;
    protected Vector Ta;
    protected Hashtable Ua;
    private boolean Va;
    private boolean Wa;
    private ActionListener Xa;
    private null Ya;
    private int Za;
    private static String ja = "\u97f1\u97c8\u97db\u97cc\u97d2\u97d1\u97df\u97da\u9790\u97ea\u97d7\u97dd\u97d5\u97db\u97cc\u97ed\u97dd\u97cc\u97d1\u97d2\u97d2";
    
    public implements(final int za) {
        this.Ra = 1;
        this.Sa = 10;
        this.Va = true;
        this.Wa = true;
        this.Za = za;
        this.Ta = new Vector();
        this.Ua = new Hashtable();
        this.Qa = -1;
        final package package1 = new package(this);
        this.addMouseListener(package1);
        this.addMouseMotionListener(package1);
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.Xa = AWTEventMulticaster.add(this.Xa, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.Xa = AWTEventMulticaster.remove(this.Xa, actionListener);
    }
    
    public void b(final instanceof instanceof1, final Object o, final interface interface1) {
        final Dimension size = this.getSize();
        final null null = this.Ua.get(o);
        synchronized (this.Ta) {
            if (null != null) {
                null._(instanceof1, this.Va || null.getX() > size.width);
                final int index = this.Ta.indexOf(null);
                int n = null.getX() + null.getWidth();
                for (int size2 = this.Ta.size(), i = index + 1; i < size2; ++i) {
                    final null null2 = this.Ta.elementAt(i);
                    null2.b(n);
                    n += null2.getWidth();
                }
            }
            else {
                final null null3 = new null(instanceof1, interface1, o);
                if (this.Ta.isEmpty()) {
                    null3.b(0);
                    this.Ta.addElement(null3);
                }
                else {
                    final null null4 = this.Ta.lastElement();
                    null3.b(null4.getX() + null4.getWidth());
                    this.Ta.addElement(null3);
                }
                this.Ua.put(o, null3);
            }
        }
        // monitorexit(this.Ta)
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        synchronized (this.Ta) {
            int n = -1;
            final int size2 = this.Ta.size();
            if (size2 != 0) {
                null null;
                for (int i = this.Ta.firstElement().getX(); i < size.width; i += null.getWidth()) {
                    if (n == size2 - 1) {
                        n = -1;
                    }
                    null = this.Ta.elementAt(++n);
                    null.a(this, graphics, i, null == this.Ya);
                }
            }
        }
        // monitorexit(this.Ta)
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            Thread.sleep(100L);
            this.repaint();
            if (this.Za > 0) {
                Thread.sleep(this.Za);
            }
        }
        catch (InterruptedException ex) {}
        long currentTimeMillis = System.currentTimeMillis();
        this.Va = false;
        while (this.la == currentThread) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            if (this.Wa) {
                this.m();
                if (currentTimeMillis2 - currentTimeMillis > 5000L) {
                    currentTimeMillis = Long.MAX_VALUE;
                }
                this.repaint();
            }
            final long n = this.Sa - (System.currentTimeMillis() - currentTimeMillis2);
            if (n > 1L) {
                try {
                    Thread.sleep(n);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void b(final boolean wa) {
        this.Wa = wa;
    }
    
    private void m() {
        synchronized (this.Ta) {
            if (this.Ta.isEmpty()) {
                // monitorexit(this.Ta)
                return;
            }
            if (this.Qa == -1) {
                try {
                    final null null = this.Ta.firstElement();
                    if (null.getX() + null.getWidth() < 0) {
                        if (null._()) {
                            this.Ta.removeElementAt(0);
                            this.Ua.remove(null.a());
                        }
                        else {
                            final null null2 = this.Ta.lastElement();
                            if (null2 != null) {
                                this.Ta.removeElementAt(0);
                                null.b(null2.getX() + null2.getWidth());
                                this.Ta.addElement(null);
                            }
                            else {
                                null.b(0);
                            }
                            null.validate();
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                final null null3 = this.Ta.firstElement();
                if (null3.getX() > 0) {
                    null null4 = null;
                    while (!this.Ta.isEmpty() && (null4 = this.Ta.lastElement())._()) {
                        this.Ta.removeElement(null4);
                        this.Ua.remove(null4.a());
                    }
                    if (this.Ta.isEmpty()) {
                        // monitorexit(this.Ta)
                        return;
                    }
                    if (null4 != null3) {
                        this.Ta.removeElement(null4);
                        null4.b(null3.getX() - null4.getWidth());
                        this.Ta.insertElementAt(null4, 0);
                    }
                }
            }
            for (int i = 0; i < this.Ta.size(); ++i) {
                ((null)this.Ta.elementAt(i))._(this.Qa * this.Ra);
            }
        }
        // monitorexit(this.Ta)
    }
    
    public void start() {
        (this.la = new Thread(this, implements.ja)).setPriority(10);
        this.la.start();
    }
    
    public void stop() {
        this.la = null;
    }
    
    public void a(final int sa) {
        this.Sa = sa;
    }
    
    public void a(final byte qa) {
        this.Qa = qa;
    }
    
    public byte a() {
        return this.Qa;
    }
    
    public void j(final int ra) {
        this.Ra = ra;
    }
    
    public void n() {
        final int width = this.getSize().width;
        synchronized (this.Ta) {
            for (int i = 0; i < this.Ta.size(); ++i) {
                final null null = this.Ta.elementAt(i);
                if (null.a() == else._b) {
                    if (i != 0) {
                        final null null2 = this.Ta.elementAt(i - 1);
                        null.b(null2.getX() + null2.getWidth());
                    }
                }
                else {
                    if (width < null.getX()) {
                        this.Ta.removeElementAt(i);
                        this.Ua.remove(null.a());
                        --i;
                    }
                    else {
                        null._();
                    }
                    null._();
                }
            }
        }
        // monitorexit(this.Ta)
    }
    
    private null _(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int width = this.getSize().width;
        synchronized (this.Ta) {
            if (this.Ya != null && this.Ya.contains(x - this.Ya.getX(), mouseEvent.getY())) {
                // monitorexit(this.Ta)
                return this.Ya;
            }
            if (this.Ta.size() == 0) {
                // monitorexit(this.Ta)
                return null;
            }
            final int x2;
            int i = x2 = this.Ta.elementAt(0).getX();
            while (i < width) {
                int j = 0;
                while (j < this.Ta.size()) {
                    final null null2 = this.Ta.elementAt(j);
                    if (i <= x && x < i + null2.getWidth()) {
                        if (null2.contains(x - i, mouseEvent.getY())) {
                            // monitorexit(this.Ta)
                            return null2;
                        }
                        // monitorexit(this.Ta)
                        return null;
                    }
                    else {
                        i += null2.getWidth();
                        ++j;
                    }
                }
                if (x2 == i) {
                    // monitorexit(this.Ta)
                    return null;
                }
            }
        }
        // monitorexit(this.Ta)
        return null;
    }
    
    static null a(final implements implements1) {
        return implements1.Ya;
    }
    
    static null a(final implements implements1, final MouseEvent mouseEvent) {
        return implements1._(mouseEvent);
    }
    
    static void b(final implements implements1, final null ya) {
        implements1.Ya = ya;
    }
    
    static ActionListener a(final implements implements1) {
        return implements1.Xa;
    }
    
    private static String i(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE97BE);
        }
        return new String(array);
    }
    
    static {
        implements.ja = i(implements.ja);
    }
}
