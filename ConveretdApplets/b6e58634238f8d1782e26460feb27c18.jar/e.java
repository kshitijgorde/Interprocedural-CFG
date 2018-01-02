import java.awt.Event;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Date;
import java.awt.Component;
import java.util.Properties;
import java.awt.Frame;
import java.util.Hashtable;
import java.util.Vector;
import java.io.PrintStream;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public final class e extends Dialog
{
    public static final PrintStream a;
    public static final String[] b;
    public static final String[] c;
    public static final Vector d;
    public static final Hashtable e;
    public d f;
    public boolean g;
    public String h;
    public Vector i;
    
    public e(final d f) {
        super(new Frame(), false);
        this.i = new Vector(1);
        this.f = f;
    }
    
    public final void a(final Properties properties) {
        final String property;
        if ((property = properties.getProperty("ShowErrorDialogs")) != null) {
            try {
                this.g = this.f.d().e(property);
            }
            catch (Exception ex) {
                this.a((Component)this.f.s, 4, new Object[] { property, "ShowErrorDialogs", ex.getMessage() });
            }
        }
    }
    
    public final void a() {
        synchronized (this.i) {
            while (this.i.size() > 0) {
                final Dialog dialog = this.i.lastElement();
                this.i.removeElementAt(this.i.size() - 1);
                dialog.dispose();
            }
        }
    }
    
    public final String a(final Component component, final int n, final Object[] array) {
        final String s = "ERROR";
        String h = null;
        if (n != 0) {
            final String a = a(n, array);
            e.a.println(s + " (" + new Date() + "): " + a);
            if (n < 0 && this.g) {
                Frame a2;
                if ((a2 = this.f.d().a(component)) == null) {
                    a2 = new Frame();
                }
                final e e = new e(this.f, a2);
                e.setLayout(new BorderLayout());
                e.add("Center", new Label(a, 1));
                final String[] array2;
                if ((array2 = e.e.get(new Integer(n))) != null) {
                    final Panel panel = new Panel();
                    panel.setLayout(new FlowLayout(1));
                    for (int i = 0; i < array2.length; ++i) {
                        panel.add(new Button(array2[i]));
                    }
                    e.add("South", panel);
                }
                e.pack();
                if (n == 14) {
                    e.resize(e.preferredSize());
                }
                Rectangle rectangle;
                if (a2.isShowing()) {
                    rectangle = new Rectangle(a2.location(), a2.size());
                }
                else {
                    rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                }
                final Dimension size = e.size();
                e.move(rectangle.x + (rectangle.width - size.width) / 2, rectangle.y + (rectangle.height - size.height) / 2);
                synchronized (this.i) {
                    this.i.addElement(e);
                }
                e.show();
                h = e.h;
                e.a.println(s + " (" + new Date() + "): Response: " + h);
            }
        }
        return h;
    }
    
    public final boolean action(final Event event, final Object o) {
        this.h = (String)event.arg;
        this.dispose();
        return true;
    }
    
    private e(final d f, final Frame frame) {
        super(frame, "Heatmaps® Error", true);
        this.i = new Vector(1);
        this.f = f;
        this.setResizable(true);
    }
    
    public static final String a(final int n, final Object[] array) {
        final StringBuffer sb = new StringBuffer();
        switch (n) {
            case 14: {
                sb.append("Java 1.1 (not " + array[0] + ") is required for Heatmaps®!");
                break;
            }
            case -1: {
                sb.append("Network connection or server unavailable. Retry?");
                break;
            }
            default: {
                sb.append("An internal error occurred! (" + n);
                if (array != null) {
                    for (int i = 0; i < array.length; ++i) {
                        sb.append(", " + array[i]);
                    }
                }
                sb.append(")");
                break;
            }
        }
        return sb.toString();
    }
    
    public final void b() {
        this.dispose();
        this.h = null;
        if (this.i != null) {
            for (int i = 0; i < this.i.size(); ++i) {
                ((e)this.i.elementAt(i)).b();
            }
            this.i.removeAllElements();
            this.i = null;
        }
        this.f = null;
    }
    
    static {
        a = System.err;
        b = new String[] { "OK" };
        c = new String[] { "Yes", "No" };
        d = new Vector();
        e = new Hashtable();
        final Integer n = new Integer(15);
        e.d.addElement(n);
        e.e.put(n, e.b);
        final Integer n2 = new Integer(14);
        e.d.addElement(n2);
        e.e.put(n2, e.b);
        final Integer n3 = new Integer(13);
        e.d.addElement(n3);
        e.e.put(n3, e.b);
        final Integer n4 = new Integer(-1);
        e.d.addElement(n4);
        e.e.put(n4, e.c);
        final Integer n5 = new Integer(12);
        e.d.addElement(n5);
        e.e.put(n5, e.b);
        final Integer n6 = new Integer(11);
        e.d.addElement(n6);
        e.e.put(n6, e.b);
        e.d.addElement(new Integer(0));
        e.d.addElement(new Integer(1));
        e.d.addElement(new Integer(2));
        e.d.addElement(new Integer(3));
        e.d.addElement(new Integer(4));
        e.d.addElement(new Integer(5));
        e.d.addElement(new Integer(6));
        e.d.addElement(new Integer(7));
        final Integer n7 = new Integer(8);
        e.d.addElement(n7);
        e.e.put(n7, e.b);
        e.d.addElement(new Integer(9));
        e.d.addElement(new Integer(10));
        e.d.addElement(new Integer(16));
        e.d.addElement(new Integer(17));
        e.d.addElement(new Integer(18));
        e.d.addElement(new Integer(19));
        e.d.addElement(new Integer(20));
    }
}
