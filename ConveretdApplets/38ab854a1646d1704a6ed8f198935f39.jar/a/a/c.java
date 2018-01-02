// 
// Decompiled by Procyon v0.5.30
// 

package a.a;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.ListModel;
import java.util.Vector;
import java.awt.Frame;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class c extends b
{
    private JList long;
    private JScrollPane null;
    JPanel else;
    JPanel char;
    private DefaultListModel goto;
    private a.a.e case;
    private h.n void;
    private Frame parent;
    
    public c(final Frame frame, final h.n void1) {
        this(frame, void1.a(), void1.a(new Frame()));
        this.void = void1;
    }
    
    public void if() {
        this.void.a(this.a(this.goto.toArray()));
    }
    
    public void a() {
        this.a((Vector)this.void.if());
    }
    
    private Vector a(final Object[] array) {
        final Vector<Object> vector = new Vector<Object>();
        for (int i = 0; i < array.length; ++i) {
            vector.addElement(array[i]);
        }
        return vector;
    }
    
    public c(final Frame parent, final String s, final a.a.e case1) {
        this.goto = new DefaultListModel();
        this.parent = parent;
        this.case = case1;
        this.long = new JList(this.goto);
        this.setLayout(new BoxLayout(this, 0));
        this.else = new JPanel();
        this.char = new JPanel(new GridLayout(8, 1));
        this.else.setLayout(new FlowLayout());
        this.char.add(new a.a.f(new a.a.d() {
            public void a() {
                c.this.byte();
            }
        }, "Add"));
        this.char.add(new a.a.f(new a.a.d() {
            public void a() {
                c.this.case();
            }
        }, "Edit"));
        this.char.add(new a.a.f(new a.a.d() {
            public void a() {
                c.this.try();
            }
        }, "Remove"));
        this.char.add(new a.a.f(new a.a.d() {
            public void a() {
                c.this.for();
            }
        }, "Defaults"));
        this.char.add(new a.a.f(new a.a.d() {
            public void a() {
                c.this.if();
            }
        }, "Save"));
        this.char.add(new a.a.f(new a.a.d() {
            public void a() {
                c.this.a();
            }
        }, "Load"));
        this.char.add(new a.a.f(new a.a.d() {
            public void a() {
                c.this.new();
            }
        }, "Clear"));
        this.else.add(this.char);
        (this.null = new JScrollPane(this.long)).setHorizontalScrollBarPolicy(30);
        this.null.setPreferredSize(new Dimension(500, 200));
        this.add(this.null);
        this.add(this.else);
    }
    
    private void for() {
        this.goto.clear();
        final Object[] a = this.case.a();
        if (a != null) {
            for (int i = 0; i < a.length; ++i) {
                this.a(a[i]);
            }
        }
        this.null.revalidate();
        this.null.repaint();
    }
    
    private void new() {
        this.goto.clear();
        this.null.revalidate();
        this.null.repaint();
    }
    
    public Object[] char() {
        return this.goto.toArray();
    }
    
    public void if(final Object[] array) {
        this.goto.clear();
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                this.goto.addElement(array[i]);
            }
        }
        this.null.revalidate();
    }
    
    public void a(final Vector vector) {
        this.goto.clear();
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                this.goto.addElement(vector.elementAt(i));
            }
        }
        this.null.revalidate();
    }
    
    void byte() {
        final Object do1 = this.case.do();
        if (do1 != null && !do1.toString().equals("")) {
            this.a(do1);
        }
    }
    
    void case() {
        if (!this.long.isSelectionEmpty()) {
            if (this.long.getSelectedValues().length == 1) {
                this.case.a(this.long.getSelectedValue());
            }
            else {
                this.case.a(this.long.getSelectedValues());
            }
            this.null.revalidate();
            this.null.repaint();
        }
    }
    
    void a(final Object o) {
        this.goto.addElement(o);
        this.null.revalidate();
        this.null.repaint();
    }
    
    void try() {
        final Object[] selectedValues = this.long.getSelectedValues();
        for (int i = 0; i < selectedValues.length; ++i) {
            this.goto.removeElement(selectedValues[i]);
        }
        this.null.revalidate();
        this.null.repaint();
    }
    
    void int() {
        this.setVisible(false);
    }
    
    public class a extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            c.this.int();
        }
    }
}
