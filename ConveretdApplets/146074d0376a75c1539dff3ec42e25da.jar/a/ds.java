// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Event;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Component;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public final class ds extends bs
{
    private static String w;
    private TextField q;
    private TextField w;
    private TextField e;
    private g y;
    private Checkbox q;
    private Choice q;
    private TextField r;
    private TextArea q;
    private TextField t;
    private TextField y;
    private T q;
    private T w;
    private Label q;
    private g u;
    private TextField u;
    private TextField i;
    private TextField o;
    private TextField p;
    private TextField a;
    private Checkbox w;
    private TextField s;
    private Checkbox e;
    private Choice w;
    private g i;
    private static String e;
    private static String r;
    public static final String q;
    private boolean e;
    
    public final bZ q() {
        final cv cv;
        (cv = new cv(-999, "")).w = true;
        cv.e(super.q.w());
        return cv;
    }
    
    private void t() {
        this.q.q(0L);
        this.w.q(0L);
        this.q.setText(ds.q);
    }
    
    public final void q(final bZ bz) {
        final cv cv = (cv)bz;
        this.q.setText(cv.q() + "");
        this.w.setText(cv.getName());
        if (cv.getName() != null && cv.getName().length() > 0) {
            this.w.setEnabled(false);
        }
        else {
            this.w.setEnabled(true);
        }
        this.q.setState(cv.q(61));
        this.e.setText(new Integer(cv.q).toString());
        if (cv.r > 0L) {
            this.q.q(cv.r);
            if (cv.s > 0) {
                this.w.q(cv.w());
                this.q.setText(T.q(this.q.q(), this.w.q()));
            }
            else {
                this.t();
            }
        }
        else {
            this.t();
        }
        this.r.setText(String.valueOf(cv.d));
        this.q.setText(cv.r);
        this.t.setText(cv.w);
        this.y.setText(cv.e);
        this.q.removeAll();
        this.q.add(ds.e);
        for (int i = 0; i < this.q.q(); ++i) {
            final String name;
            if (!(name = ((bZ)this.q.q(i)).getName()).equals(bz.getName())) {
                this.q.add(name);
            }
        }
        this.u.setText(cv.y);
        this.i.setText(cv.u);
        this.o.setText(cv.i);
        this.p.setText(cv.o);
        if (cv.o != null && cv.o.length() > 0) {
            this.p.setEnabled(false);
        }
        else {
            this.p.setEnabled(true);
        }
        this.a.setText(cv.p);
        this.w.setState(cv.q());
        this.s.setText(cv.a);
        this.e.setState(cv.w());
    }
    
    public final boolean q(final bZ bz) {
        final cu cu = (cu)bz;
        final String text = this.w.getText();
        super.q.q((aF)bz);
        final int int1 = Integer.parseInt(this.q.getText());
        int i = 0;
        while (true) {
            while (i < this.q()) {
                final bZ q = this.q(i);
                if (bz != q && q.q() == int1) {
                    final boolean b = true;
                    if (b) {
                        this.q.requestFocus();
                        new b(super.q, ds.r, eb.q("A site with this ID already exists.  Please choose another ID for this site."), super.q).setVisible(true);
                        return false;
                    }
                    if (Integer.parseInt(this.q.getText()) < 1000) {
                        this.q.requestFocus();
                        new b(super.q, ds.r, eb.q("You must specify a site ID greater than 1000.  Please choose another ID for this site."), super.q).setVisible(true);
                        return false;
                    }
                    if (text.length() == 0) {
                        this.w.requestFocus();
                        new b(super.q, ds.r, eb.q("You must provide a name for this site.  Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    final String s = text;
                    int j = 0;
                    while (true) {
                        while (j < this.q()) {
                            final bZ q2 = this.q(j);
                            if (bz != q2 && q2.getName().equalsIgnoreCase(s)) {
                                final boolean b2 = true;
                                if (b2) {
                                    this.w.requestFocus();
                                    new b(super.q, ds.r, eb.q("A site with this name already exists.  Please choose another name for this site."), super.q).setVisible(true);
                                    return false;
                                }
                                this.w();
                                super.q.h();
                                for (int k = 0; k < text.length(); ++k) {
                                    final char char1;
                                    if ((char1 = text.charAt(k)) != '_' && !Character.isLetterOrDigit(char1)) {
                                        this.w.requestFocus();
                                        new b(super.q, ds.r, eb.q("Site names may contain only letters, digits, and the underscore character (_).  Please choose another name for this site."), super.q).setVisible(true);
                                        return false;
                                    }
                                }
                                final String trim = this.e.getText().trim();
                                int int2;
                                try {
                                    int2 = Integer.parseInt(trim);
                                }
                                catch (NumberFormatException ex) {
                                    this.e.requestFocus();
                                    this.e.selectAll();
                                    new b(super.q, ds.r, eb.q("The user count you entered is invalid. Please re-enter."), super.q).setVisible(true);
                                    return false;
                                }
                                cu.a_(text);
                                cu.q(61, this.q.getState());
                                cu.q = int2;
                                cu.e(Integer.parseInt(this.q.getText()));
                                cu.w = this.t.getText();
                                cu.e = this.y.getText();
                                final String selectedItem = this.q.getSelectedItem();
                                if (!ds.e.equals(selectedItem)) {
                                    cu.q = selectedItem;
                                }
                                if (a.a.r) {
                                    if (this.q.q() < 0L) {
                                        return false;
                                    }
                                    if (this.w.q() < 0L) {
                                        return false;
                                    }
                                    cu.r = this.q.q();
                                    cu.s = T.q(cu.r, this.w.q());
                                    final String text2 = this.r.getText();
                                    int int3;
                                    try {
                                        int3 = Integer.parseInt(text2);
                                    }
                                    catch (Exception ex2) {
                                        this.r.requestFocus();
                                        this.r.selectAll();
                                        new b(super.q, ds.r, eb.q("You entered int value in invalid format!"), super.q).setVisible(true);
                                        return false;
                                    }
                                    cu.d = int3;
                                    cu.r = this.q.getText();
                                    cu.y = this.u.getText();
                                    cu.u = this.i.getText();
                                    cu.i = this.o.getText();
                                }
                                cu.o = this.p.getText();
                                cu.p = this.a.getText();
                                cu.q(this.w.getState());
                                cu.a = this.s.getText();
                                cu.w(this.e.getState());
                                return true;
                            }
                            else {
                                ++j;
                            }
                        }
                        final boolean b2 = false;
                        continue;
                    }
                }
                else {
                    ++i;
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    public final void e() {
        this.w();
        super.q.h();
    }
    
    public final void q(final bw bw) {
        bw.q(new H(eb.q("Each site must have a unique name.  This name is not displayed to users, but is used to identify the site and store its settings and resources on the server.")), 2, 1.0f, 0.0f);
        bw.q(eb.q("Name:"), new Component[] { this.w, new Label(eb.q("Site ID:")), this.q });
        bw.q(eb.q("Display Name:"), this.t);
        bw.q(eb.q("Right Menu Text:"), this.y);
        bw.q(eb.q("Moderated Option:"), this.q);
        bw.q(eb.q("Suspend:"), new Component[] { this.w, this.s });
        bw.q(eb.q("Stop FTP:"), this.e);
        bw.q(eb.q("Max users (0 for unlimited):"), this.e);
        bw.q(eb.q("FTP Username:"), this.p);
        bw.q(eb.q("FTP Password:"), this.a);
        bw.q(eb.q("Copy Settings From Site:"), this.q);
        if (a.a.r) {
            bw.q(eb.q("Start Date:"), new Component[] { this.q, this.u });
            bw.q(eb.q("End Date:"), new Component[] { this.w, this.q });
            bw.q(eb.q("Payment:"), this.r);
            bw.q(eb.q("Site:"), this.u);
            bw.q(eb.q("Phone:"), this.i);
            bw.q(eb.q("E-mail:"), this.o);
            bw.q(eb.q("Comment:"), this.q);
        }
    }
    
    public final void q() {
        final Vector<cu> vector = new Vector<cu>();
        final Vector<cu> vector2 = new Vector<cu>();
        for (int i = 0; i < this.q(); ++i) {
            final cu cu;
            if ((cu = (cu)this.q(i)).q(63)) {
                vector2.addElement(cu);
            }
            else {
                final cu cu2;
                if ((cu2 = (cu)this.q.n.w(cu.q())) == null || cu.q(cu2) != 0) {
                    vector.addElement(cu);
                }
            }
        }
        this.w(vector2);
        this.q(vector);
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final int x = super.q.X;
        final es es;
        (es = new es(17238273, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cu cu = vector.elementAt(i);
            es.q(i, cu.q());
            es.q(i, 0, cu.q());
            es.q(i, 1, cu.q);
            es.q(i, 2, x);
            es.q(i, 3, cu.r);
            es.q(i, 5, cu.s);
            es.q(i, 6, cu.d);
            if (!cu.q(63)) {
                es.q(i, 0, cu.getName());
            }
            es.q(i, 1, cu.r);
            es.q(i, 2, cu.w);
            es.q(i, 3, cu.q);
            es.q(i, 4, cu.y);
            es.q(i, 5, cu.u);
            es.q(i, 6, cu.i);
            es.q(i, 7, cu.e);
            es.q(i, 8, cu.o);
            es.q(i, 10, cu.a);
            es.q(i, 0, new ep(cu.p));
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17238274, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cu cu = vector.elementAt(i);
            es.q(i, cu.q());
            es.q(i, 0, cu.q());
        }
        super.q.q(es);
    }
    
    public final void w(final bZ bz) {
        final Dimension size = this.w.getSize();
        this.w.removeAll();
        this.w.add(ds.w);
        if (bz == null || bz.q() == 0) {
            this.y.e();
            this.w.setEnabled(false);
            this.i.e();
        }
        else if (bz.q() != 0) {
            this.y.q();
            this.w.setEnabled(true);
            final cu cu = (cu)bz;
            for (int i = 0; i < cu.q().length; ++i) {
                this.w.add(cu.q()[i]);
            }
        }
        if (size.width < 120) {
            size.width = 120;
        }
        this.w.setSize(size);
        super.w(bz);
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.u) {
                    this.q.q(new Date().getTime());
                    this.w.q(new Date().getTime());
                    return true;
                }
            }
            case 401: {
                final Component[] components = this.w.getComponents();
                for (int i = 0; i < components.length; ++i) {
                    if (event.target == components[i]) {
                        this.e = true;
                    }
                }
                break;
            }
            case 1005: {
                if (this.e) {
                    final Component[] components2 = this.w.getComponents();
                    int j = 0;
                    while (j < components2.length) {
                        if (event.target == components2[j]) {
                            this.e = false;
                            if (this.q.q() > 0L && this.w.q() > 0L) {
                                this.q.setText(T.q(this.q.q(), this.w.q()));
                                break;
                            }
                            break;
                        }
                        else {
                            ++j;
                        }
                    }
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final bZ bz = (bZ)super.q.q();
                if (event.target == this.y) {
                    final es es;
                    (es = new es(33622276, 1)).q(0, 0, bz.q());
                    super.q.q(es);
                }
                if (event.target == this.i) {
                    final String selectedItem = this.w.getSelectedItem();
                    final es es2;
                    (es2 = new es(33622277, 1)).q(0, 0, bz.q());
                    es2.q(0, 0, cu.q(selectedItem));
                    super.q.q(es2);
                }
                if (event.target == this.w) {
                    if (!this.w.getSelectedItem().equals(ds.w)) {
                        this.i.q();
                    }
                    else {
                        this.i.e();
                    }
                }
                return super.handleEvent(event);
            }
            case 701: {
                this.w((bZ)event.arg);
                return true;
            }
            case 702: {
                this.w((bZ)null);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    private final int w() {
        int n = 0;
        for (int i = 0; i < this.q(); ++i) {
            if (!((cu)this.q(i)).q(63)) {
                ++n;
            }
        }
        return n;
    }
    
    public final void w() {
        super.w();
        final dW n = this.q.n;
        dW.q();
        try {
            for (int i = 0; i < this.q.n.q(); ++i) {
                final cv cv = new cv((cu)this.q.n.q(i));
                super.q.q(cv, cv.q() != 0);
                cv.w = false;
                cv.w = (cv.q() != 0);
                this.e(cv);
            }
        }
        finally {
            final dW n2 = this.q.n;
            dW.w();
        }
    }
    
    public final void e(final bZ bz) {
        super.q.q(bz, bz.q());
        final int q;
        if ((q = super.q.q((aF)bz)) == -1) {
            super.q.e(bz);
            if (bz.q(62)) {
                super.q.q(bz, true);
            }
            if (super.q != null) {
                final bZ q2;
                if ((q2 = this.q()) != null) {
                    super.q.q(q2);
                }
                else {
                    super.q.dispose();
                    super.q = null;
                }
            }
        }
        else {
            super.q.q(bz, q);
            if (super.q != null) {
                super.q.dispose();
                super.q = null;
            }
        }
        this.w(bz);
    }
    
    public ds(final cV cv) {
        super(cv, eb.q("Sites"), eb.q("Site"));
        new SimpleDateFormat("dd/MM/yyyy");
        this.e = false;
        this.q = new TextField(5);
        this.w = new TextField(10);
        this.t = new TextField(20);
        this.y = new TextField(20);
        this.e = new TextField(5);
        this.q = new T();
        this.u = new g(eb.q("Now"));
        this.w = new T();
        this.q = new Label(ds.q + "               ");
        this.r = new TextField(5);
        this.u = new TextField(7);
        this.i = new TextField(7);
        this.o = new TextField(7);
        this.q = new TextArea(3, 20);
        this.y = new g(80, 20);
        this.q = new Checkbox();
        this.q = new Choice();
        this.p = new TextField(10);
        this.a = new TextField(10);
        this.w = new Checkbox();
        this.s = new TextField(20);
        this.e = new Checkbox();
        this.y.q(eb.q("Restart Site"));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.y.getFontMetrics(this.y.getFont())).stringWidth(eb.q("Restart Site"));
        stringWidth += 20;
        this.y.resize(stringWidth, 20);
        this.y.e();
        (this.w = new Choice()).disable();
        (this.i = new g(80, 20)).q(eb.q("Restore"));
        this.i.resize(fontMetrics.stringWidth(eb.q("Restore")) + 20, 20);
        this.i.e();
        this.q("", new Component[] { this.y, this.w, this.i });
    }
    
    static {
        ds.w = eb.q("--Select Backup--");
        ds.e = eb.q("None");
        ds.r = eb.q("Note");
        q = ds.e + "              ";
        eb.q("Month");
        eb.q("Incorrect start date");
    }
}
