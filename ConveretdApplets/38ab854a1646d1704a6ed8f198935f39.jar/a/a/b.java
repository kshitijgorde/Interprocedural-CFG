// 
// Decompiled by Procyon v0.5.30
// 

package a.a;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.Frame;
import javax.swing.JPanel;

public abstract class b extends JPanel
{
    public abstract void a();
    
    public abstract void if();
    
    public static b a(final Frame frame, final h h) {
        if (h instanceof h.f) {
            return new a((h.d)h);
        }
        if (h instanceof h.h) {
            return new f((h.h)h);
        }
        if (h instanceof h.c) {
            return new e((h.d)h);
        }
        if (h instanceof h.d) {
            return new b((h.d)h);
        }
        if (h instanceof h.i) {
            return new d((h.i)h);
        }
        if (h instanceof h.a) {
            return new c(frame, ((h.a)h).a());
        }
        if (h instanceof h.n) {
            return new a.a.c(frame, (h.n)h);
        }
        throw new Error("No ParameterPanel for " + h.getClass().toString());
    }
    
    public static class c extends b
    {
        private Vector a;
        private JButton if;
        private Frame parent;
        
        public c(final Frame parent) {
            this.a = new Vector();
            this.if = new JButton("OK");
            this.parent = parent;
            this.setLayout(new BoxLayout(this, 1));
        }
        
        public c(final Frame frame, final h[] array) {
            this(frame);
            for (int i = 0; i < array.length; ++i) {
                this.a(array[i]);
            }
        }
        
        public void a(final h h) {
            this.a.add(b.a(this.parent, h));
            this.do();
        }
        
        public void a(final h[] array) {
            for (int i = 0; i < array.length; ++i) {
                this.a(array[i]);
            }
        }
        
        public void a(final h.a a) {
            this.a(a.a());
        }
        
        public void do() {
            this.removeAll();
            for (int i = 0; i < this.a.size(); ++i) {
                this.add((Component)this.a.elementAt(i));
            }
        }
        
        public void a() {
            for (int i = 0; i < this.a.size(); ++i) {
                ((b)this.a.elementAt(i)).a();
            }
        }
        
        public void if() {
            for (int i = 0; i < this.a.size(); ++i) {
                ((b)this.a.elementAt(i)).if();
            }
        }
    }
    
    public static class f extends b
    {
        h.h try;
        JTextField byte;
        
        public f(final h.h try1) {
            this.byte = new JTextField(10);
            this.try = try1;
            this.setLayout(new GridLayout(1, 2));
            this.add(new JLabel(try1.a()));
            this.add(this.byte);
        }
        
        public void a() {
            this.byte.setText(this.try.if(this.try.if()));
        }
        
        public void if() {
            this.try.a(this.try.a(this.byte.getText()));
        }
    }
    
    public static class b extends a.a.b
    {
        h.d b;
        JComboBox c;
        
        public b(final h.d b) {
            this.b = b;
            this.setLayout(new GridLayout(1, 2));
            this.add(new JLabel(b.a()));
            (this.c = new JComboBox((E[])b.do())).setEditable(false);
            this.add(this.c);
        }
        
        public void a() {
            this.c.setSelectedItem(this.b.if());
        }
        
        public void if() {
            this.b.a(this.c.getSelectedItem());
        }
    }
    
    public static class a extends b
    {
        public a(final h.d d) {
            super(d);
            super.c.setEditable(true);
        }
        
        public void if() {
            if (super.c.getSelectedIndex() == -1) {
                super.b.a(((h.h)super.b).a((String)super.c.getSelectedItem()));
            }
            else {
                super.b.a(super.c.getSelectedItem());
            }
        }
    }
    
    public static class e extends b
    {
        h.d int;
        JComboBox new;
        
        public e(final h.d int1) {
            this.int = int1;
            this.setLayout(new GridLayout(1, 2));
            this.add(new JLabel(int1.a()));
            (this.new = new JComboBox((E[])int1.do())).setEditable(true);
            this.add(this.new);
        }
        
        public void a() {
            this.new.setSelectedItem(this.int.if());
        }
        
        public void if() {
            if (this.new.getSelectedIndex() != -1) {
                this.int.a(this.new.getSelectedItem());
            }
        }
    }
    
    public static class d extends b
    {
        h.i do;
        JCheckBox for;
        
        public d(final h.i do1) {
            this.for = new JCheckBox();
            this.do = do1;
            this.setLayout(new GridLayout(1, 2));
            this.add(new JLabel(do1.a()));
            this.add(this.for);
        }
        
        public void a() {
            this.for.setSelected(this.do.byte());
        }
        
        public void if() {
            this.do.a(this.for.isSelected());
        }
    }
}
