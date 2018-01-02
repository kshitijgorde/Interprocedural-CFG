// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.TextComponent;
import java.awt.Frame;
import java.util.Date;
import java.util.Calendar;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.util.GregorianCalendar;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;

public final class T extends Panel
{
    private static final String q;
    private static final String w;
    private static final String e;
    private static final String r;
    private TextField q;
    private TextField w;
    private TextField e;
    private Label q;
    private Label w;
    private Label e;
    private GregorianCalendar q;
    
    public T() {
        this.q = new TextField(1);
        this.w = new TextField(1);
        this.e = new TextField(2);
        this.q = new Label(T.w);
        this.w = new Label(T.e);
        this.e = new Label(T.r);
        ((TextComponent)(this.q = new GregorianCalendar())).setBackground(Color.white);
        this.q.setForeground(Color.black);
        this.w.setBackground(Color.white);
        this.w.setForeground(Color.black);
        this.e.setBackground(Color.white);
        this.e.setForeground(Color.black);
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).gridwidth = 1;
        this.add(this.q, gridBagConstraints);
        this.add(this.q, gridBagConstraints);
        this.add(this.w, gridBagConstraints);
        this.add(this.w, gridBagConstraints);
        this.add(this.e, gridBagConstraints);
        this.add(this.e, gridBagConstraints);
    }
    
    public final long q() {
        if (!this.isEnabled()) {
            return 0L;
        }
        if ("".equals(this.e.getText().trim()) && "".equals(this.w.getText().trim()) && "".equals(this.q.getText().trim())) {
            return 0L;
        }
        final int q;
        if ((q = q(this.e, 1970, 9999)) < 0) {
            return -1L;
        }
        this.q.set(1, q);
        final int n;
        if ((n = q(this.w, 1, 12) - 1) < 0) {
            return -1L;
        }
        this.q.set(2, n);
        final TextField q2 = this.q;
        final int n2 = 1;
        final GregorianCalendar q3;
        int leastMaximum = (q3 = this.q).getLeastMaximum(5);
        final int maximum = q3.getMaximum(5);
        int n3;
        if (leastMaximum == maximum) {
            n3 = leastMaximum;
        }
        else {
            final Calendar calendar;
            (calendar = (Calendar)q3.clone()).setLenient(true);
            int n4 = leastMaximum;
            do {
                calendar.set(5, leastMaximum);
                if (calendar.get(5) != leastMaximum) {
                    break;
                }
                n4 = leastMaximum;
            } while (++leastMaximum <= maximum);
            n3 = n4;
        }
        final int q4;
        if ((q4 = q(q2, n2, n3)) < 0) {
            return -1L;
        }
        this.q.set(5, q4);
        return this.q.getTime().getTime();
    }
    
    public final void q(final long n) {
        this.q.setTime(new Date(n));
        if (n == 0L) {
            this.q.setText("");
            this.w.setText("");
            this.e.setText("");
            return;
        }
        this.q.setText("" + this.q.get(5));
        this.w.setText("" + (this.q.get(2) + 1));
        this.e.setText("" + this.q.get(1));
    }
    
    private static int q(final TextField textField, final int n, final int n2) {
        try {
            final int int1;
            if ((int1 = Integer.parseInt(textField.getText().trim())) < n || int1 > n2) {
                throw new NumberFormatException();
            }
            return int1;
        }
        catch (NumberFormatException ex) {
            new b(null, eb.q("Error"), new String[] { eb.q("OK") }, new String[] { "Entered time is invalid." }, null, null).setVisible(true);
            textField.selectAll();
            textField.requestFocus();
            return -1;
        }
    }
    
    public static String q(final int n) {
        final Calendar instance = Calendar.getInstance();
        if (n <= 0) {
            return T.q;
        }
        final Date time;
        (time = new Date()).setTime(n * 24L * 60L * 60L * 1000L);
        instance.setTime(time);
        final StringBuffer sb = new StringBuffer();
        final int n2;
        if ((n2 = instance.get(1) - 1970) > 0) {
            sb.append(n2).append(" year");
            if (n2 > 1) {
                sb.append("s");
            }
            sb.append(", ");
        }
        final int value;
        if ((value = instance.get(2)) > 0) {
            sb.append(value).append(" month");
            if (value > 1) {
                sb.append("s");
            }
            sb.append(", ");
        }
        final int n3;
        if ((n3 = instance.get(5) - 1) > 0) {
            sb.append(n3).append(" day");
            if (n3 > 1) {
                sb.append("s");
            }
        }
        String s = sb.toString();
        if ("".equals(s)) {
            s = T.q;
        }
        return s;
    }
    
    public static String q(final long n, final long n2) {
        if (n < 0L || n2 < 0L) {
            return T.q;
        }
        final Calendar instance;
        (instance = Calendar.getInstance()).setTime(new Date(n));
        final Calendar instance2;
        (instance2 = Calendar.getInstance()).setTime(new Date(n2));
        return q((int)q(instance, instance2));
    }
    
    public static int q(final long n, final long n2) {
        final Calendar instance;
        (instance = Calendar.getInstance()).setTime(new Date(n));
        final Calendar instance2;
        (instance2 = Calendar.getInstance()).setTime(new Date(n2));
        return (int)q(instance, instance2);
    }
    
    private static long q(Calendar calendar, final Calendar calendar2) {
        calendar = (Calendar)calendar.clone();
        long n = 0L;
        while (calendar.before(calendar2)) {
            calendar.add(5, 1);
            ++n;
        }
        return n;
    }
    
    static {
        q = eb.q("None");
        w = eb.q("D:");
        e = eb.q("M:");
        r = eb.q("Y:");
    }
}
