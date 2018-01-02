// 
// Decompiled by Procyon v0.5.30
// 

package a.a;

import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import javax.swing.JButton;
import java.util.Vector;
import javax.swing.JDialog;

public class a extends JDialog
{
    private Vector a;
    private JButton if;
    private Frame parent;
    
    public a(final Frame parent, final String s) {
        super(parent, s, true);
        this.a = new Vector();
        this.if = new JButton("OK");
        this.parent = parent;
        this.if.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                a.a.a.this.hide();
            }
        });
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), 1));
    }
    
    public void a(final h h) {
        this.a.add(b.a(this.parent, h));
    }
    
    public void a(final h[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.a(array[i]);
        }
    }
    
    public void a(final h.a a) {
        this.a(a.a());
    }
    
    public void pack() {
        this.getContentPane().removeAll();
        for (int i = 0; i < this.a.size(); ++i) {
            this.getContentPane().add((Component)this.a.elementAt(i));
        }
        this.getContentPane().add(this.if);
        super.pack();
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.getWidth() / 2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.getHeight() / 2);
    }
    
    public void a() {
        for (int i = 0; i < this.a.size(); ++i) {
            ((b)this.a.elementAt(i)).a();
        }
        super.show();
    }
    
    public void if() {
        for (int i = 0; i < this.a.size(); ++i) {
            ((b)this.a.elementAt(i)).if();
        }
        super.hide();
    }
}
