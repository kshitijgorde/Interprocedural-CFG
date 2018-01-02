// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Panel;

public class D extends Panel
{
    private static final String w;
    private static final String e;
    private static final String r;
    private static final String t;
    protected static String q;
    private TextField q;
    private Choice q;
    
    public D(final boolean b, final boolean b2) {
        this.q = new TextField(5);
        this.q = new Choice();
        if (!b) {
            this.q.addItem(D.w);
        }
        this.q.addItem(D.e);
        if (b) {
            this.q.addItem(D.r);
            this.q.addItem(D.t);
        }
        if (b2) {
            this.q.addItem(D.q);
        }
        this.add(this.q);
        this.add(this.q);
    }
    
    public final int q() {
        try {
            final int int1;
            if (!D.q.equals(this.q.getSelectedItem()) && (((int1 = Integer.parseInt(this.q.getText().trim())) < 10 && D.w.equals(this.q.getSelectedItem())) || int1 <= 0)) {
                new p(null, al.q("Error"), new String[] { al.q("OK") }, new String[] { "Entered time must be at least ten seconds long.  Please specify a larger value." }, null, null).setVisible(true);
                this.q.selectAll();
                this.q.requestFocus();
                return -1;
            }
        }
        catch (NumberFormatException ex) {
            new p(null, al.q("Error"), new String[] { al.q("OK") }, new String[] { "Entered time is invalid." }, null, null).setVisible(true);
            this.q.selectAll();
            this.q.requestFocus();
            return -1;
        }
        if (D.q.equals(this.q.getSelectedItem())) {
            return Integer.MAX_VALUE;
        }
        if (D.w.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText());
        }
        if (D.e.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60;
        }
        if (D.r.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60 * 60;
        }
        if (D.t.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60 * 60 * 24;
        }
        return 0;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.q) {
                    this.q.setEnabled(!D.q.equals(this.q.getSelectedItem()));
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static {
        w = al.q("seconds");
        e = al.q("minutes");
        r = al.q("hours");
        t = al.q("days");
        D.q = al.q("never");
    }
}
