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

public class private extends Canvas implements Runnable
{
    public static final byte Aa = -1;
    public static final byte Ba = 1;
    protected byte Ca;
    protected volatile Thread Y;
    protected int Da;
    protected int Ea;
    protected Vector Fa;
    protected Hashtable Ga;
    private boolean Ha;
    private boolean Ia;
    private ActionListener Ja;
    private switch Ka;
    private int La;
    private static String V = "\u0cd1\u0ce8\u0cfb\u0cec\u0cf2\u0cf1\u0cff\u0cfa\u0cb0\u0cca\u0cf7\u0cfd\u0cf5\u0cfb\u0cec\u0ccd\u0cfd\u0cec\u0cf1\u0cf2\u0cf2";
    
    public private(final int la) {
        this.Da = 1;
        this.Ea = 10;
        this.Ha = true;
        this.Ia = true;
        this.La = la;
        this.Fa = new Vector();
        this.Ga = new Hashtable();
        this.Ca = -1;
        final synchronized synchronized1 = new synchronized(this);
        this.addMouseListener(synchronized1);
        this.addMouseMotionListener(synchronized1);
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.Ja = AWTEventMulticaster.add(this.Ja, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.Ja = AWTEventMulticaster.remove(this.Ja, actionListener);
    }
    
    public void a(final public public1, final Object o, final return return1) {
        final Dimension size = this.getSize();
        final switch switch1 = this.Ga.get(o);
        synchronized (this.Fa) {
            if (switch1 != null) {
                switch1._(public1, this.Ha || switch1.getX() > size.width);
                final int index = this.Fa.indexOf(switch1);
                int n = switch1.getX() + switch1.getWidth();
                for (int size2 = this.Fa.size(), i = index + 1; i < size2; ++i) {
                    final switch switch2 = this.Fa.elementAt(i);
                    switch2._(n);
                    n += switch2.getWidth();
                }
            }
            else {
                final switch switch3 = new switch(public1, return1, o);
                if (this.Fa.isEmpty()) {
                    switch3._(0);
                    this.Fa.addElement(switch3);
                }
                else {
                    final switch switch4 = this.Fa.lastElement();
                    switch3._(switch4.getX() + switch4.getWidth());
                    this.Fa.addElement(switch3);
                }
                this.Ga.put(o, switch3);
            }
        }
        // monitorexit(this.Fa)
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        synchronized (this.Fa) {
            int n = -1;
            final int size2 = this.Fa.size();
            if (size2 != 0) {
                switch switch1;
                for (int i = this.Fa.firstElement().getX(); i < size.width; i += switch1.getWidth()) {
                    if (n == size2 - 1) {
                        n = -1;
                    }
                    switch1 = this.Fa.elementAt(++n);
                    switch1.b(this, graphics, i, switch1 == this.Ka);
                }
            }
        }
        // monitorexit(this.Fa)
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            Thread.sleep(100L);
            this.repaint();
            if (this.La > 0) {
                Thread.sleep(this.La);
            }
        }
        catch (InterruptedException ex) {}
        long currentTimeMillis = System.currentTimeMillis();
        this.Ha = false;
        while (this.Y == currentThread) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            if (this.Ia) {
                this.c();
                if (currentTimeMillis2 - currentTimeMillis > 5000L) {
                    currentTimeMillis = Long.MAX_VALUE;
                }
                this.repaint();
            }
            final long n = this.Ea - (System.currentTimeMillis() - currentTimeMillis2);
            if (n > 1L) {
                try {
                    Thread.sleep(n);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void a(final boolean ia) {
        this.Ia = ia;
    }
    
    private void c() {
        synchronized (this.Fa) {
            if (this.Fa.isEmpty()) {
                // monitorexit(this.Fa)
                return;
            }
            if (this.Ca == -1) {
                try {
                    final switch switch1 = this.Fa.firstElement();
                    if (switch1.getX() + switch1.getWidth() < 0) {
                        if (switch1._()) {
                            this.Fa.removeElementAt(0);
                            this.Ga.remove(switch1.b());
                        }
                        else {
                            final switch switch2 = this.Fa.lastElement();
                            if (switch2 != switch1) {
                                this.Fa.removeElementAt(0);
                                switch1._(switch2.getX() + switch2.getWidth());
                                this.Fa.addElement(switch1);
                            }
                            else {
                                switch1._(0);
                            }
                            switch1.validate();
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                final switch switch3 = this.Fa.firstElement();
                if (switch3.getX() > 0) {
                    switch switch4 = null;
                    while (!this.Fa.isEmpty() && (switch4 = this.Fa.lastElement())._()) {
                        this.Fa.removeElement(switch4);
                        this.Ga.remove(switch4.b());
                    }
                    if (this.Fa.isEmpty()) {
                        // monitorexit(this.Fa)
                        return;
                    }
                    if (switch4 != switch3) {
                        this.Fa.removeElement(switch4);
                        switch4._(switch3.getX() - switch4.getWidth());
                        this.Fa.insertElementAt(switch4, 0);
                    }
                    else {
                        switch3._(switch3.getX() - switch3.getWidth());
                    }
                }
            }
            for (int i = 0; i < this.Fa.size(); ++i) {
                ((switch)this.Fa.elementAt(i)).a(this.Ca * this.Da);
            }
        }
        // monitorexit(this.Fa)
    }
    
    public void start() {
        (this.Y = new Thread(this, private.V)).setPriority(10);
        this.Y.start();
    }
    
    public void stop() {
        this.Y = null;
    }
    
    public void b(final int ea) {
        this.Ea = ea;
    }
    
    public void b(final byte ca) {
        this.Ca = ca;
    }
    
    public byte _() {
        return this.Ca;
    }
    
    public void k(final int da) {
        this.Da = da;
    }
    
    public void d() {
        final int width = this.getSize().width;
        synchronized (this.Fa) {
            for (int i = 0; i < this.Fa.size(); ++i) {
                final switch switch1 = this.Fa.elementAt(i);
                if (switch1.b() == import.Ma) {
                    if (i != 0) {
                        final switch switch2 = this.Fa.elementAt(i - 1);
                        switch1._(switch2.getX() + switch2.getWidth());
                    }
                }
                else {
                    if (width < switch1.getX()) {
                        this.Fa.removeElementAt(i);
                        this.Ga.remove(switch1.b());
                        --i;
                    }
                    else {
                        switch1.b();
                    }
                    switch1.b();
                }
            }
        }
        // monitorexit(this.Fa)
    }
    
    private switch _(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int width = this.getSize().width;
        synchronized (this.Fa) {
            if (this.Ka != null && this.Ka.contains(x - this.Ka.getX(), mouseEvent.getY())) {
                // monitorexit(this.Fa)
                return this.Ka;
            }
            if (this.Fa.size() == 0) {
                // monitorexit(this.Fa)
                return null;
            }
            final int x2;
            int i = x2 = this.Fa.elementAt(0).getX();
            while (i < width) {
                int j = 0;
                while (j < this.Fa.size()) {
                    final switch switch2 = this.Fa.elementAt(j);
                    if (i <= x && x < i + switch2.getWidth()) {
                        if (switch2.contains(x - i, mouseEvent.getY())) {
                            // monitorexit(this.Fa)
                            return switch2;
                        }
                        // monitorexit(this.Fa)
                        return null;
                    }
                    else {
                        i += switch2.getWidth();
                        ++j;
                    }
                }
                if (x2 == i) {
                    // monitorexit(this.Fa)
                    return null;
                }
            }
        }
        // monitorexit(this.Fa)
        return null;
    }
    
    static switch a(final private private1) {
        return private1.Ka;
    }
    
    static switch b(final private private1, final MouseEvent mouseEvent) {
        return private1._(mouseEvent);
    }
    
    static void b(final private private1, final switch ka) {
        private1.Ka = ka;
    }
    
    static ActionListener b(final private private1) {
        return private1.Ja;
    }
    
    private static String n(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF0C9E);
        }
        return new String(array);
    }
    
    static {
        private.V = n(private.V);
    }
}
