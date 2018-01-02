// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Calendar;
import java.text.DateFormat;
import java.awt.event.AdjustmentEvent;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.Font;
import java.awt.event.AdjustmentListener;
import java.awt.Dialog;

public class a0 extends Dialog implements AdjustmentListener
{
    public static String void;
    public static String for;
    public static String goto;
    public static String j;
    public static String case;
    public static String null;
    public static String e;
    public static String c;
    public static String try;
    public static String new;
    public static String a;
    public static String i;
    public static Font if;
    public static Font f;
    protected ChartManager do;
    protected Scrollbar char;
    protected Panel long;
    protected Label[] int;
    protected Label[] k;
    private static Date h;
    protected static SimpleDateFormat b;
    protected aa d;
    private int byte;
    protected boolean else;
    private int g;
    
    public a0(final Frame frame, final String title, final ChartManager do1) {
        super(frame, title, true);
        this.char = new Scrollbar(0);
        this.long = new Panel();
        this.int = new Label[26];
        this.k = new Label[26];
        this.byte = 0;
        this.else = true;
        this.g = 0;
        this.setFont(a0.if);
        this.setTitle(title);
        this.setResizable(false);
        this.setModal(false);
        final Dimension size = frame.getSize();
        this.setLocation(size.width / 2, size.height / 2);
        this.do = do1;
        this.char.addAdjustmentListener(this);
        this.setLayout(new BorderLayout());
        this.add("North", this.char);
        this.add("Center", this.long);
        this.addWindowListener(new a());
        this.d = do1.eZ;
    }
    
    protected void a() {
        this.long.removeAll();
        this.byte = 0;
        this.k[0] = new Label(a0.goto);
        this.k[1] = new Label(a0.j);
        this.k[2] = new Label(a0.case);
        if (this.do.eS.null().C().do()) {
            this.k[3] = new Label(a0.null);
            this.byte = 4;
        }
        else {
            this.byte = 3;
            this.g = 3;
            if (this.do.eS.dO.int() instanceof am) {
                (this.k[this.byte] = new Label(a0.void)).setFont(new Font(this.getFont().getName(), 1, this.getFont().getSize()));
                this.int[this.byte] = new Label("");
                ++this.byte;
                ++this.g;
            }
            this.k[this.byte++] = new Label(a0.new);
            this.k[this.byte++] = new Label(a0.a);
            this.k[this.byte++] = new Label(a0.i);
            this.k[this.byte++] = new Label(a0.try);
        }
        final ap ed = this.do.eS.ed;
        for (int i = 0; i < ed.if(); ++i) {
            final CompositeAnalysis if1 = ed.if(i);
            if (if1 != null) {
                this.k[this.byte] = new Label(if1.g() + ((if1.bX == null) ? "" : " (1)") + ": ");
                ++this.byte;
                if (if1.bX != null) {
                    this.k[this.byte] = new Label(if1.g() + " (2): ");
                    ++this.byte;
                }
            }
        }
        final s er = this.do.eR;
        for (int j = 0; j < er.if(); ++j) {
            final Analyse a = er.a(j);
            if (a != null) {
                this.k[this.byte] = new Label(a.g() + ((a.new(1) == null) ? "" : " (1)") + ": ");
                ++this.byte;
                if (a.new(1) != null) {
                    this.k[this.byte] = new Label(a.g() + " (2): ");
                    ++this.byte;
                }
            }
        }
        final i bo = this.do.eS.null().bO;
        for (int k = 0; k < bo.a.size(); ++k) {
            final aj if2 = bo.if(k);
            if (if2 != null && if2.for()) {
                this.k[this.byte] = new Label("T" + new Integer(k) + ": ");
                ++this.byte;
            }
        }
        for (int l = 0; l < this.do.eS.dO.int.size(); ++l) {
            if (l != this.do.eS.dO.do()) {
                final ChartBody int1 = this.do.eS.dO.int(l);
                if (int1.fn) {
                    (this.k[this.byte] = new Label(a0.new)).setForeground(int1.ft);
                    ++this.byte;
                }
            }
        }
        if (this.do.eS.dO.int() instanceof am) {
            (this.k[this.byte] = new Label(a0.for)).setFont(new Font(this.getFont().getName(), 1, this.getFont().getSize()));
            this.int[this.byte++] = new Label("");
            this.k[this.byte++] = new Label(a0.new);
        }
        this.long.setLayout(new GridLayout(this.byte, 2));
        for (int n = 0; n < this.byte; ++n) {
            this.long.add(this.k[n]);
            (this.int[n] = new Label("")).setForeground(this.k[n].getForeground());
            this.long.add(this.int[n]);
        }
    }
    
    public void if(final int n) {
        if (this.else) {
            this.a();
        }
        this.char.setValues(n, 0, this.do.eS.null().u(), this.do.eS.null().l() + 1);
        this.a(n);
        if (this.else) {
            this.pack();
            this.setVisible(true);
            this.else = false;
        }
    }
    
    protected void a(final int n) {
        this.int[0].setText(new Integer(n).toString());
        a0.h.setTime((long)(this.do.eS.null().b6.elementAt(n) * 1000.0));
        a0.b.applyPattern("MM/dd/yy");
        this.int[1].setText(a0.b.format(a0.h));
        a0.b.applyPattern("HH:mm:ss");
        this.int[2].setText(a0.b.format(a0.h));
        float n2 = Float.NaN;
        int g = this.g;
        if (this.do.eS.null().C().do()) {
            final float if1 = this.do.eS.null().bW.if(n);
            if (if1 == Float.MIN_VALUE) {
                this.int[g++].setText("N/A");
            }
            else {
                this.int[g++].setText(this.d.a(if1));
            }
        }
        else {
            final float if2 = this.do.eS.null().bW.if(n);
            if (if2 == Float.MIN_VALUE) {
                this.int[g++].setText("N/A");
            }
            else {
                this.int[g++].setText(this.d.a(if2));
            }
            final float if3 = this.do.eS.null().bL.if(n);
            if (if3 == Float.MIN_VALUE) {
                this.int[g++].setText("N/A");
            }
            else {
                this.int[g++].setText(this.d.a(if3));
            }
            final float if4 = this.do.eS.null().b5.if(n);
            if (if4 == Float.MIN_VALUE) {
                this.int[g++].setText("N/A");
            }
            else {
                this.int[g++].setText(this.d.a(if4));
            }
            final float if5 = this.do.eS.null().bX.if(n);
            if (if5 == Float.MIN_VALUE) {
                this.int[g++].setText("N/A");
            }
            else {
                this.int[g++].setText(this.d.a(if5));
            }
        }
        int n3 = g;
        final ap ed = this.do.eS.ed;
        for (int i = 0; i < ed.if(); ++i) {
            final CompositeAnalysis if6 = ed.if(i);
            if (if6 != null) {
                float n4;
                if (n < if6.bW - 1) {
                    n4 = (n2 = Float.NaN);
                }
                else {
                    n4 = if6.b2[n];
                    if (if6.bX != null) {
                        n2 = if6.bX[n];
                    }
                }
                this.int[n3].setText(this.do.eZ.a(n4));
                ++n3;
                if (if6.bX != null) {
                    this.int[n3].setText(this.do.eZ.a(n2));
                    ++n3;
                }
            }
        }
        final s er = this.do.eR;
        for (int j = 0; j < er.if(); ++j) {
            final Analyse a = er.a(j);
            if (a != null) {
                float n5;
                if (n < a.cu) {
                    n5 = Float.NaN;
                }
                else {
                    n5 = a.new(0)[n];
                }
                if (a.new(1) != null) {
                    n2 = a.new(1)[n];
                }
                if (a instanceof v || a instanceof e) {
                    this.int[n3++].setText(new Long((long)n5).toString());
                }
                else {
                    this.int[n3].setText(this.do.eZ.a(n5));
                    if (a.new(1) != null) {
                        ++n3;
                        this.int[n3].setText(this.do.eZ.a(n2));
                    }
                }
                ++n3;
            }
        }
        final i bo = this.do.eS.null().bO;
        for (int k = 0; k < bo.a.size(); ++k) {
            final aj if7 = bo.if(k);
            if (if7 != null && if7.for()) {
                float if8;
                if (if7.c() == 3 || (n >= if7.char() && if7.c() != 3)) {
                    if8 = if7.if((float)n);
                }
                else {
                    if8 = Float.NaN;
                }
                this.int[n3].setText(this.do.eZ.a(if8));
                ++n3;
            }
        }
        for (int l = 0; l < this.do.eS.dO.int.size(); ++l) {
            if (l != this.do.eS.dO.do()) {
                final ChartBody int1 = this.do.eS.dO.int(l);
                if (int1.fn) {
                    final float if9 = int1.al().bW.if(n);
                    if (if9 == Float.MIN_VALUE) {
                        this.int[n3].setText("N/A");
                    }
                    else {
                        this.int[n3].setText(this.d.a(if9));
                    }
                    ++n3;
                }
            }
        }
        int n6 = n3;
        if (this.do.eS.dO.int() instanceof am) {
            ++n6;
            if (this.do.eS.null().bY != null) {
                this.int[n6].setText(this.d.a(this.do.eS.null().bY.if(n)));
            }
            else {
                this.int[n6].setText("N/A");
            }
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final be es = this.do.eS;
        es.z().do();
        try {
            final int n = es.case().left + es.g(adjustmentEvent.getValue() - es.null().u());
            es.dI = adjustmentEvent.getValue();
            this.do.a(es, n, -1, true);
            this.if(adjustmentEvent.getValue());
        }
        finally {
            es.z().a();
        }
    }
    
    static {
        a0.void = new String("Bid");
        a0.for = new String("Ask");
        a0.goto = new String("ID:");
        a0.j = new String("Date:");
        a0.case = new String("Time:");
        a0.null = new String("Tick Price:");
        a0.e = new String("Volume:");
        a0.c = new String("Open Interest:");
        a0.try = new String("Open Price:");
        a0.new = new String("Close Price:");
        a0.a = new String("High Price:");
        a0.i = new String("Low Price:");
        a0.if = new Font("Dialog", 0, 11);
        a0.f = new Font("Dialog", 0, 11);
        a0.h = new Date(0L);
        (a0.b = (SimpleDateFormat)DateFormat.getDateTimeInstance(1, 1)).setCalendar(Calendar.getInstance());
    }
    
    class a extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            a0.this.do.eS.d8 = 1;
            a0.this.do.eS.dQ = false;
            a0.this.do.eS.dP = false;
            a0.this.do.eS.setCursor(new Cursor(0));
            a0.this.do.eS.repaint();
            a0.this.setVisible(false);
            a0.this.dispose();
        }
    }
}
