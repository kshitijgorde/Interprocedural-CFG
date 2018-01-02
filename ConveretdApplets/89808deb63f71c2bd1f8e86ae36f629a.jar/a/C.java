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

public class C extends Panel
{
    private static final String w;
    private static final String e;
    private static final String r;
    private static final String t;
    protected static String q;
    private TextField q;
    private Choice q;
    
    public C(final boolean b, final boolean b2) {
        this.q = new TextField(5);
        this.q = new cw();
        if (!b) {
            this.q.addItem(C.w);
        }
        this.q.addItem(C.e);
        if (b) {
            this.q.addItem(C.r);
            this.q.addItem(C.t);
        }
        if (b2) {
            this.q.addItem(C.q);
        }
        this.add(this.q);
        this.add(this.q);
    }
    
    public final int q() {
        try {
            final int int1;
            if (!C.q.equals(this.q.getSelectedItem()) && (((int1 = Integer.parseInt(this.q.getText().trim())) < 10 && C.w.equals(this.q.getSelectedItem())) || int1 <= 0)) {
                new bT(null, ak.q("Error"), new String[] { ak.q("OK") }, new String[] { "Entered time must be at least ten seconds long.  Please specify a larger value." }, null, null).setVisible(true);
                this.q.selectAll();
                this.q.requestFocus();
                return -1;
            }
        }
        catch (NumberFormatException ex) {
            new bT(null, ak.q("Error"), new String[] { ak.q("OK") }, new String[] { "Entered time is invalid." }, null, null).setVisible(true);
            this.q.selectAll();
            this.q.requestFocus();
            return -1;
        }
        if (C.q.equals(this.q.getSelectedItem())) {
            return Integer.MAX_VALUE;
        }
        if (C.w.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText());
        }
        if (C.e.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60;
        }
        if (C.r.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60 * 60;
        }
        if (C.t.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60 * 60 * 24;
        }
        return 0;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.q) {
                    this.q.setEnabled(!C.q.equals(this.q.getSelectedItem()));
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static {
        w = ak.q("seconds");
        e = ak.q("minutes");
        r = ak.q("hours");
        t = ak.q("days");
        C.q = ak.q("never");
    }
}
