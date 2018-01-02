// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Color;
import dk.midas.web.chart.applet.a.c;
import java.awt.Font;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Frame;
import java.util.Vector;
import java.awt.event.AdjustmentListener;

public class k extends a0 implements AdjustmentListener
{
    private Vector l;
    
    public k(final Frame frame, final String s, final ChartManager chartManager) {
        super(frame, s, chartManager);
        this.l = new Vector();
    }
    
    protected void a() {
        this.l.removeAllElements();
        this.l.addElement(new h());
        this.l.addElement(new g(super.do.eS, a0.j, "MM/dd/yy"));
        this.l.addElement(new g(super.do.eS, a0.case, "HH:mm:ss"));
        if (super.do.eS.null().C().do()) {
            this.l.addElement(new f(a0.null, super.do.eS.null().bW));
        }
        else {
            if (super.do.eS.dO.int() instanceof am) {
                this.l.addElement(new c(a0.void));
            }
            this.l.addElement(new f(a0.new, super.do.eS.null().bW));
            this.l.addElement(new f(a0.a, super.do.eS.null().bL));
            this.l.addElement(new f(a0.i, super.do.eS.null().b5));
            this.l.addElement(new f(a0.try, super.do.eS.null().bX));
        }
        final ap ed = super.do.eS.ed;
        for (int i = 0; i < ed.if(); ++i) {
            final CompositeAnalysis if1 = ed.if(i);
            if (if1 != null) {
                for (int j = 0; j < if1.e(); ++j) {
                    this.l.addElement(new j(if1, j));
                }
            }
        }
        final s er = super.do.eR;
        for (int k = 0; k < er.if(); ++k) {
            final Analyse a = er.a(k);
            if (a != null) {
                for (int l = 0; l < a.e(); ++l) {
                    this.l.addElement(this.a(a, l));
                }
            }
        }
        final dk.midas.web.chart.applet.i bo = super.do.eS.null().bO;
        for (int n = 0; n < bo.a.size(); ++n) {
            final aj if2 = bo.if(n);
            if (if2 != null && if2.for()) {
                this.l.addElement(new d(if2, n));
            }
        }
        for (int n2 = 0; n2 < super.do.eS.dO.int.size(); ++n2) {
            if (n2 != super.do.eS.dO.do()) {
                final ChartBody int1 = super.do.eS.dO.int(n2);
                if (int1.fn) {
                    this.l.addElement(new f(a0.new, int1.al().bW, int1.ft));
                }
            }
        }
        if (super.do.eS.dO.int() instanceof am) {
            this.l.addElement(new c(a0.for));
            this.l.addElement(new f(a0.new, super.do.eS.null().bY));
        }
        super.long.removeAll();
        super.long.setLayout(new GridLayout(this.l.size(), 2));
        for (int n3 = 0; n3 < this.l.size(); ++n3) {
            final Object element = this.l.elementAt(n3);
            if (element instanceof e) {
                super.k[n3] = new Label();
                ((e)element).a(super.k[n3]);
                super.long.add(super.k[n3]);
                (super.int[n3] = new Label("")).setForeground(super.k[n3].getForeground());
                super.long.add(super.int[n3]);
            }
        }
    }
    
    private e a(final Analyse analyse, final int n) {
        k k;
        if (analyse instanceof v || analyse instanceof dk.midas.web.chart.applet.e) {
            k = new b(analyse, n);
        }
        else if (analyse instanceof PFAnalyse) {
            k = new a(analyse);
        }
        else {
            k = new k(analyse, n);
        }
        return k;
    }
    
    protected void a(final int n) {
        for (int i = 0; i < this.l.size(); ++i) {
            final Object element = this.l.elementAt(i);
            if (element instanceof e) {
                ((e)element).a(super.int[i], n);
            }
        }
    }
    
    private class c implements e
    {
        private String a;
        
        public c(final String a) {
            this.a = a;
        }
        
        public void a(final Label label) {
            final Font font = new Font(k.this.getFont().getName(), 1, k.this.getFont().getSize());
            label.setText(this.a);
            label.setFont(font);
        }
        
        public void a(final Label label, final int n) {
            label.setText("");
        }
    }
    
    private interface e
    {
        void a(final Label p0);
        
        void a(final Label p0, final int p1);
    }
    
    private class d implements e
    {
        private aj do;
        private int if;
        
        public d(final aj do1, final int if1) {
            this.do = do1;
            this.if = if1;
        }
        
        public void a(final Label label) {
            label.setText("T" + new Integer(this.if) + ": ");
        }
        
        public void a(final Label label, final int n) {
            label.setText("");
            if (this.do != null) {
                float if1;
                if (this.do.c() == 3 || (n >= this.do.char() && this.do.c() != 3)) {
                    if1 = this.do.if((float)n);
                }
                else {
                    if1 = Float.NaN;
                }
                label.setText(k.this.do.eZ.a(if1));
            }
        }
    }
    
    private class a extends k
    {
        public a(final Analyse analyse) {
            super(analyse, 0);
        }
        
        public void a(final Label label) {
            label.setText(super.else.g() + " trend : ");
        }
        
        public void a(final Label label, final int n) {
            if (super.else != null) {
                final int e = ((PFAnalyse)super.else).e(n);
                String text = "unknown";
                switch (e) {
                    case 1: {
                        text = "down";
                        break;
                    }
                    case 3: {
                        text = "from down to up";
                        break;
                    }
                    case 2: {
                        text = "up";
                        break;
                    }
                    case 4: {
                        text = "from up to down";
                        break;
                    }
                }
                label.setText(text);
            }
            else {
                label.setText("");
            }
        }
    }
    
    private class k extends i
    {
        public k(final Analyse analyse, final int n) {
            super(analyse, n);
        }
        
        public void a(final Label label) {
            String s = super.else.g();
            if (super.else.e() > 1) {
                String s2 = ((Analyse)super.else).d(super.char);
                if (dk.midas.web.chart.applet.a.c.a(s2)) {
                    s2 = "" + (super.char + 1);
                }
                s = s + " (" + s2 + ")";
            }
            label.setText(s + ": ");
        }
        
        public void a(final Label label, final int n) {
            super.a(label, n);
            label.setForeground(((Analyse)super.else).void(super.char));
        }
        
        protected boolean if(final int n) {
            return n >= ((Analyse)super.else).goto(super.char);
        }
    }
    
    private abstract class i implements e
    {
        protected q else;
        protected int char;
        
        public i(final q else1, final int char1) {
            this.else = else1;
            this.char = char1;
        }
        
        public void a(final Label label) {
            label.setText(this.else.g() + ((this.else.e() <= 1) ? "" : (" (" + (this.char + 1) + ")")) + ": ");
        }
        
        public void a(final Label label, final int n) {
            if (this.else != null) {
                label.setText(this.a(this.a(n)));
            }
            else {
                label.setText("");
            }
        }
        
        protected float a(final int n) {
            float n2 = Float.NaN;
            final float[] new1 = this.else.new(this.char);
            if (new1 != null && this.if(n)) {
                n2 = new1[n];
            }
            return n2;
        }
        
        protected abstract boolean if(final int p0);
        
        protected String a(final float n) {
            return k.this.do.eZ.a(n);
        }
    }
    
    private class b extends k
    {
        public b(final Analyse analyse, final int n) {
            super(analyse, n);
        }
        
        protected String a(final float n) {
            return new Long((long)n).toString();
        }
    }
    
    private class j extends i
    {
        public j(final CompositeAnalysis compositeAnalysis, final int n) {
            super(compositeAnalysis, n);
        }
        
        protected boolean if(final int n) {
            return n >= ((CompositeAnalysis)super.else).bW - 1;
        }
    }
    
    private class f implements e
    {
        private bk new;
        private String int;
        private Color for;
        
        public f(final k k, final String s, final bk bk) {
            this(k, s, bk, Color.black);
        }
        
        public f(final String int1, final bk new1, final Color for1) {
            this.int = int1;
            this.new = new1;
            this.for = for1;
        }
        
        public void a(final Label label) {
            label.setText(this.int);
            label.setForeground(this.for);
        }
        
        public void a(final Label label, final int n) {
            if (this.new != null && 0 <= n && n < this.new.case()) {
                final float if1 = this.new.if(n);
                if (if1 == Float.MIN_VALUE) {
                    label.setText("N/A");
                }
                else {
                    label.setText(k.this.d.a(if1));
                }
            }
            else {
                label.setText("");
            }
        }
    }
    
    private class g implements e
    {
        private be case;
        private SimpleDateFormat byte;
        private String try;
        
        protected g(final be case1, final String try1, final String s) {
            this.case = case1;
            this.try = try1;
            (this.byte = (SimpleDateFormat)DateFormat.getDateTimeInstance(1, 1)).setCalendar(Calendar.getInstance());
            this.byte.applyPattern(s);
        }
        
        public void a(final Label label) {
            label.setText(this.try);
        }
        
        public void a(final Label label, final int n) {
            if (this.case.null().b6 != null && 0 <= n && n < this.case.null().b6.size()) {
                label.setText(this.byte.format(new Date((long)(this.case.null().b6.elementAt(n) * 1000.0))));
            }
            else {
                label.setText("");
            }
        }
    }
    
    private class h implements e
    {
        public void a(final Label label) {
            label.setText(a0.goto);
        }
        
        public void a(final Label label, final int n) {
            label.setText(new Integer(n).toString());
        }
    }
}
