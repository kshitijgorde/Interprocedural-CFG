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

public class P extends Panel
{
    private static final String w;
    private static final String e;
    private static final String r;
    private static final String t;
    protected static String q;
    private TextField q;
    private Choice q;
    
    public P(final boolean b, final boolean b2) {
        this.q = new TextField(5);
        this.q = new dR();
        if (!b) {
            this.q.addItem(P.w);
        }
        this.q.addItem(P.e);
        if (b) {
            this.q.addItem(P.r);
            this.q.addItem(P.t);
        }
        if (b2) {
            this.q.addItem(P.q);
        }
        this.add(this.q);
        this.add(this.q);
    }
    
    public final int q() {
        try {
            final int int1;
            if (!P.q.equals(this.q.getSelectedItem()) && (((int1 = Integer.parseInt(this.q.getText().trim())) < 10 && P.w.equals(this.q.getSelectedItem())) || int1 <= 0)) {
                new dd(null, be.w("Error"), new String[] { be.w("OK") }, new String[] { "Entered time must be at least ten seconds long.  Please specify a larger value." }, null, null).setVisible(true);
                this.q.selectAll();
                this.q.requestFocus();
                return -1;
            }
        }
        catch (NumberFormatException ex) {
            new dd(null, be.w("Error"), new String[] { be.w("OK") }, new String[] { "Entered time is invalid." }, null, null).setVisible(true);
            this.q.selectAll();
            this.q.requestFocus();
            return -1;
        }
        if (P.q.equals(this.q.getSelectedItem())) {
            return Integer.MAX_VALUE;
        }
        if (P.w.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText());
        }
        if (P.e.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60;
        }
        if (P.r.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60 * 60;
        }
        if (P.t.equals(this.q.getSelectedItem())) {
            return Integer.parseInt(this.q.getText()) * 60 * 60 * 24;
        }
        return 0;
    }
    
    public final void q(final int n) {
        this.q.setEnabled(false);
        this.q.setText("NaN");
        this.q.select(P.q);
        if (n == Integer.MAX_VALUE) {
            return;
        }
        this.q.setEnabled(true);
        if (n / 86400 > 0) {
            this.q.setText(n / 86400 + "");
            this.q.select(P.t);
            return;
        }
        if (n / 3600 > 0) {
            this.q.setText(n / 3600 + "");
            this.q.select(P.r);
            return;
        }
        if (n / 60 > 0) {
            this.q.setText(n / 60 + "");
            this.q.select(P.e);
            return;
        }
        this.q.setText(n + "");
        this.q.select(P.w);
    }
    
    public static String q(int n) {
        if (n == Integer.MAX_VALUE) {
            return P.q;
        }
        final StringBuffer sb = new StringBuffer();
        if (n / 86400 > 0) {
            sb.append(n / 86400).append("d ");
            n -= n / 86400 * 86400;
        }
        if (n / 3600 > 0) {
            sb.append(n / 3600).append("h ");
            n -= n / 3600 * 3600;
        }
        if (n / 60 > 0) {
            sb.append(n / 60).append("m ");
            n -= n / 60 * 60;
        }
        if (n > 0) {
            sb.append(n).append("s");
        }
        return sb.toString();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.q) {
                    this.q.setEnabled(!P.q.equals(this.q.getSelectedItem()));
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static {
        w = be.w("seconds");
        e = be.w("minutes");
        r = be.w("hours");
        t = be.w("days");
        P.q = be.w("never");
    }
}
