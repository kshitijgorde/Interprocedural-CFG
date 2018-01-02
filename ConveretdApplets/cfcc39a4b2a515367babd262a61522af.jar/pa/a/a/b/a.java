// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.b;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import java.awt.Container;

public class a extends Container implements ActionListener
{
    public static String byte;
    public static String for;
    private String if;
    private pa.a.a.a.a[] int;
    private String[] do;
    private int new;
    private int try;
    private Hashtable a;
    
    static {
        a.byte = "reset";
        a.for = "initialstate";
    }
    
    public a(final int try1) {
        this.if = pa.a.a.b.a.for;
        this.new = 0;
        this.try = try1;
        this.int = new pa.a.a.a.a[try1];
        this.do = new String[try1];
        this.a = new Hashtable(try1);
        this.setLayout(new FlowLayout(0, 0, 0));
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("zoomIn")) {
            this.do(actionEvent.getActionCommand());
        }
        if (actionEvent.getActionCommand().equals("zoomOut")) {
            this.do(actionEvent.getActionCommand());
        }
        if (actionEvent.getActionCommand().equals("pan")) {
            this.do(actionEvent.getActionCommand());
        }
        if (actionEvent.getActionCommand().equals("reset")) {
            this.do(actionEvent.getActionCommand());
        }
        if (actionEvent.getActionCommand().equals("rotate")) {
            this.do(actionEvent.getActionCommand());
        }
    }
    
    public void a() {
        for (int i = 0; i < this.try; ++i) {
            this.int[i].a(this);
            this.add(this.int[i]);
        }
    }
    
    public pa.a.a.a.a a(final String s) {
        return this.a.get(s);
    }
    
    public int if(final String s) {
        final pa.a.a.a.a a = this.a.get(s);
        return (a != null) ? a.a() : pa.a.a.a.a.goto;
    }
    
    private String if() {
        return this.if;
    }
    
    public void a(final pa.a.a.a.a[] int1, final String[] do1, final int try1) {
        this.int = int1;
        this.try = try1;
        this.do = do1;
        this.a.clear();
        for (int i = 0; i < try1; ++i) {
            this.a.put(do1[i], int1[i]);
        }
    }
    
    public void a(final String s, final int n) {
        final pa.a.a.a.a a = this.a.get(s);
        if (a != null) {
            a.a(n);
        }
    }
    
    private void for(final String if1) {
        this.if = if1;
    }
    
    public void do(final String s) {
        if (!s.equals(pa.a.a.b.a.byte)) {
            if (!this.if().equals(pa.a.a.b.a.for)) {
                this.a.get(this.if()).a(pa.a.a.a.a.goto);
            }
            this.a.get(s).a(pa.a.a.a.a.int);
            this.for(s);
        }
    }
}
