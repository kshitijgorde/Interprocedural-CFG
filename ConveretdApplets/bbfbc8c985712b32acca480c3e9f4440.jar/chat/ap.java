// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.event.ItemEvent;
import java.awt.Font;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Event;
import java.text.DateFormatSymbols;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Choice;
import java.util.Locale;
import java.util.Calendar;
import java.awt.Color;
import java.awt.event.ItemListener;

public final class ap extends bC implements ItemListener
{
    private by[] a;
    private by b;
    by a;
    private int a;
    private Color a;
    private String[] a;
    private Calendar a;
    private Calendar b;
    private Locale a;
    private Choice a;
    private Choice b;
    private TextField a;
    private cr a;
    private cr b;
    private Object a;
    cA a;
    cA b;
    cA c;
    
    public ap(final Frame frame, final cs cs, final TextField a, final Object a2, final Calendar calendar) {
        super(frame, aS.a(645), true);
        this.a = a2;
        super.a = new cr(80, 20);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.setBackground(cs.a.a);
        final bF bf = new bF();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        new bi(aS.a(531), (byte)0);
        this.a = a;
        this.setResizable(false);
        this.a = new ac(new bK(0, 0, 0, 0, this.getBackground()), new al(0, Color.white, Color.gray));
        this.b = new ac(new bK(1, 1, 1, 1, Color.red), new bK(1, 1, 1, 1, this.getBackground()));
        this.c = new ac(new bK(1, 1, 1, 1, Color.blue), new bK(1, 1, 1, 1, this.getBackground()));
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        bf.setBackground(cs.a.h);
        bf.setForeground(cs.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.a = Locale.US;
        this.a = new by[49];
        final Panel panel;
        (panel = new Panel()).setLayout(new GridLayout(1, 2));
        final Panel panel2 = new Panel();
        this.b = null;
        final Calendar instance;
        (instance = Calendar.getInstance(this.a)).setFirstDayOfWeek(7);
        this.b = (Calendar)instance.clone();
        panel2.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                final int n = j + i * 7;
                if (i == 0) {
                    (this.a[n] = new by(this, Color.white)).setBackground(new Color(200, 200, 255));
                }
                else {
                    this.a[n] = new by(this, "x", Color.white);
                }
                panel2.add(this.a[n]);
            }
        }
        this.b = new Choice();
        this.a = new Choice();
        this.b.setBackground(Color.white);
        this.a.setBackground(Color.white);
        this.b.setForeground(Color.black);
        this.a.setForeground(Color.black);
        for (int k = 2007; k <= 2020; ++k) {
            this.b.addItem(Integer.toString(k));
        }
        final String[] months = new DateFormatSymbols(this.a).getMonths();
        for (int l = 0; l < 12; ++l) {
            this.a.addItem(months[l]);
        }
        this.a.addItemListener(this);
        this.b.addItemListener(this);
        panel.add(this.a);
        panel.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        bf.add(panel);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel2, gridBagConstraints);
        bf.add(panel2);
        this.a = (Calendar)calendar.clone();
        this.a();
        this.b.select(String.valueOf(this.a.get(1)));
        this.a.select(this.a.get(2));
        this.a(this.a.get(5));
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(aS.a(3));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(aS.a(2));
        this.a.d();
        final g g = new g(this.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.pack();
    }
    
    private void a() {
        this.a.setFirstDayOfWeek(7);
        int firstDayOfWeek = this.a.getFirstDayOfWeek();
        this.a = new DateFormatSymbols(this.a).getShortWeekdays();
        for (int i = 0; i < 7; ++i) {
            this.a[i].a(this.a[firstDayOfWeek]);
            this.a[i].setForeground(Color.blue);
            if (firstDayOfWeek < 7) {
                ++firstDayOfWeek;
            }
            else {
                firstDayOfWeek -= 6;
            }
        }
        this.a = Color.white;
        this.b();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
                    this.a.c();
                    return true;
                }
                if (event.key == 27 || (event.key == 46 && (event.modifiers & 0x4) != 0x0)) {
                    this.b.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.a.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(this.a.getTime()));
                    ((bC)this.a).handleEvent(new Event(this.a, 7691, null));
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                break;
            }
            case 201: {
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    private void b() {
        final Calendar calendar;
        final int firstDayOfWeek = (calendar = (Calendar)this.a.clone()).getFirstDayOfWeek();
        calendar.set(5, 1);
        int n;
        if ((n = calendar.get(7) - firstDayOfWeek) < 0) {
            n += 7;
        }
        int i;
        for (i = 0; i < n; ++i) {
            this.a[i + 7].setVisible(false);
            this.a[i + 7].a("");
        }
        calendar.add(2, 1);
        final Date time = calendar.getTime();
        calendar.add(2, -1);
        Date date = calendar.getTime();
        int n2 = 0;
        final Color foreground = this.getForeground();
        while (date.before(time)) {
            this.a[i + n2 + 7].a(Integer.toString(n2 + 1));
            this.a[i + n2 + 7].setVisible(true);
            if (calendar.get(6) == this.b.get(6) && calendar.get(1) == this.b.get(1)) {
                this.a[i + n2 + 7].setForeground(Color.red);
            }
            else {
                this.a[i + n2 + 7].setForeground(foreground);
            }
            if (n2 + 1 == this.a) {
                this.a[i + n2 + 7].setBackground(Color.gray);
                (this.b = this.a[i + n2 + 7]).a(true);
            }
            else {
                this.a[i + n2 + 7].setBackground(this.a);
                this.a[i + n2 + 7].a(false);
            }
            ++n2;
            calendar.add(5, 1);
            date = calendar.getTime();
        }
        for (int j = n2 + i + 7; j < 49; ++j) {
            this.a[j].setVisible(false);
            this.a[j].a("");
        }
    }
    
    public final Locale getLocale() {
        return this.a;
    }
    
    public final void setLocale(final Locale a) {
        this.a = a;
        this.a();
    }
    
    public final void setFont(final Font font) {
        if (this.a != null) {
            for (int i = 0; i < 49; ++i) {
                this.a[i].setFont(font);
            }
        }
    }
    
    public final void setForeground(final Color color) {
        super.setForeground(color);
        if (this.a != null) {
            for (int i = 7; i < 49; ++i) {
                this.a[i].setForeground(color);
            }
            this.b();
        }
    }
    
    public final void a(int i) {
        if (i < 1) {
            i = 1;
        }
        final Calendar calendar;
        (calendar = (Calendar)this.a.clone()).set(5, 1);
        calendar.add(2, 1);
        calendar.add(5, -1);
        final int value = calendar.get(5);
        if (i > value) {
            i = value;
        }
        this.a = i;
        if (this.b != null) {
            this.b.setBackground(this.a);
            this.b.a(false);
            this.b.repaint();
        }
        for (i = 7; i < 42; ++i) {
            if (this.a[i].a.a().equals(Integer.toString(this.a))) {
                (this.b = this.a[i]).setBackground(Color.gray);
                this.b.a(true);
                break;
            }
        }
        this.a.set(5, this.a);
    }
    
    public final String getName() {
        return "JDayChooser";
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (this.a == null) {
            return;
        }
        final Calendar calendar = (Calendar)this.a.clone();
        if (itemEvent.getSource() == this.a) {
            calendar.set(2, this.a.getSelectedIndex());
            this.a(calendar);
            final ap ap = this;
            final int selectedIndex = this.a.getSelectedIndex();
            this = ap;
            ap.a.set(2, selectedIndex);
            this.a(this.a);
            this.b();
            return;
        }
        if (itemEvent.getSource() == this.b) {
            calendar.set(1, Integer.parseInt(this.b.getSelectedItem()));
            this.a(calendar);
            final ap ap2 = this;
            final int int1 = Integer.parseInt(this.b.getSelectedItem());
            this = ap2;
            ap2.a.set(1, int1);
            this.b();
        }
    }
    
    private void a(final Calendar a) {
        this.a = a;
        this.b();
    }
}
