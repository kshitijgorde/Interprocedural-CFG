import java.awt.Event;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.List;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class C33 extends Frame
{
    C49 e;
    List f;
    public static String g;
    Button h;
    Hashtable i;
    C32 j;
    int k;
    
    public void a(final String s) {
        final C17 c17 = this.i.get(s);
        if (c17 != null) {
            this.e.E(c17);
        }
    }
    
    public C33(final C49 e, final Vector vector) {
        super("Click on a layer");
        this.e = e;
        this.k = 1;
        try {
            this.setFont(new Font("Arial", 0, 12));
        }
        catch (Throwable t) {}
        this.i = new Hashtable();
        int n = 0;
        this.add("South", this.h = new Button("Done"));
        this.f = new List((vector.size() - 1 > 10) ? 10 : (vector.size() - 1), true);
        if (vector.size() > 0) {
            this.j = vector.elementAt(0);
            for (int i = vector.size() - 1; i > 0; --i) {
                final C32 c32 = vector.elementAt(i);
                this.f.addItem(c32.b());
                if (!c32.c()) {
                    this.f.select(n);
                }
                this.i.put(new Integer(n++), c32);
            }
            this.add("Center", this.f);
        }
        this.add("North", new Button("Show all layers"));
    }
    
    static {
        C33.g = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    protected void finalize() throws Throwable {
        this.hide();
    }
    
    public void show() {
        super.show();
        this.resize(180, 200);
        this.invalidate();
        this.validate();
        this.toFront();
    }
    
    public C33(final C49 e, final Hashtable i) {
        super("Select a view");
        this.e = e;
        this.k = 0;
        try {
            this.setFont(new Font("Arial", 0, 12));
        }
        catch (Throwable t) {}
        this.i = i;
        this.add("South", this.h = new Button("Done"));
        this.f = new List((i.size() > 10) ? 10 : i.size(), false);
        final Enumeration b = this.b(i.keys());
        while (b.hasMoreElements()) {
            this.f.addItem(i.get(b.nextElement()).c());
        }
        this.add("Center", this.f);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.h) {
            this.hide();
            return true;
        }
        if (event.target instanceof Button && this.k == 1) {
            for (int i = 0; i < this.f.countItems(); ++i) {
                this.f.select(i);
            }
            this.e.P(this.j.e(), true);
            this.e.repaint();
        }
        return false;
    }
    
    private Enumeration b(final Enumeration enumeration) {
        final Vector<String> vector = new Vector<String>();
        while (enumeration.hasMoreElements()) {
            String s;
            int n;
            for (s = enumeration.nextElement(), n = 0; n < vector.size() && vector.elementAt(n).compareTo(s) <= 0; ++n) {}
            vector.insertElementAt(s, n);
        }
        return vector.elements();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return true;
        }
        if (event.target != this.f || !(event.arg instanceof Integer) || (event.id != 701 && event.id != 702) || this.k != 1) {
            if (this.k == 0 && event.target == this.f && event.id == 701) {
                final String selectedItem = this.f.getSelectedItem();
                if (selectedItem != null) {
                    final C17 c17 = this.i.get(selectedItem);
                    if (c17 != null) {
                        this.e.E(c17);
                    }
                }
            }
            return super.handleEvent(event);
        }
        boolean b = false;
        if (event.id == 701) {
            b = true;
        }
        final int intValue = (int)event.arg;
        if (intValue != 0 && b) {
            this.f.select(0);
        }
        final C32 c18 = this.i.get(new Integer(intValue));
        if (c18 == null || c18.e() == null) {
            System.out.println("ERROR #24665 " + intValue);
            return true;
        }
        this.e.P(c18.e(), b);
        this.e.repaint();
        return true;
    }
}
