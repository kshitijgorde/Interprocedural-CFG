// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Event;
import java.util.Vector;
import java.awt.Component;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public final class bX extends G
{
    private static String w;
    private TextField q;
    private TextField w;
    private TextField e;
    private ad t;
    private Checkbox q;
    private Choice q;
    private TextField r;
    private TextArea q;
    private TextField t;
    private TextField y;
    private dx q;
    private dx w;
    private Label q;
    private ad y;
    private TextField u;
    private TextField i;
    private TextField o;
    private TextField p;
    private TextField a;
    private Checkbox w;
    private TextField s;
    private Choice w;
    private ad u;
    private static String e;
    private static String r;
    public static final String q;
    private boolean e;
    
    public final bp q() {
        final dt dt = new dt(-999, "");
        dt.s = super.q.w;
        return dt;
    }
    
    private void t() {
        this.q.q(0L);
        this.w.q(0L);
        this.q.setText(bX.q);
    }
    
    public final void q(final bp bp) {
        final dt dt = (dt)bp;
        this.q.setText(dt.s + "");
        this.w.setText(dt.a);
        if (dt.a != null && dt.a.length() > 0) {
            this.w.setEnabled(false);
        }
        else {
            this.w.setEnabled(true);
        }
        this.q.setState(dt.q(61));
        this.e.setText(new Integer(dt.q).toString());
        if (dt.w > 0L) {
            this.q.q(dt.w);
            if (dt.y > 0) {
                this.w.q(dt.q());
                this.q.setText(dx.q(this.q.q(), this.w.q()));
            }
            else {
                this.t();
            }
        }
        else {
            this.t();
        }
        this.r.setText(String.valueOf(dt.u));
        this.q.setText(dt.r);
        this.t.setText(dt.w);
        this.y.setText(dt.e);
        this.q.removeAll();
        this.q.add(bX.e);
        for (int i = 0; i < this.q.q; ++i) {
            final String a;
            if (!(a = ((bp)this.q.q(i)).a).equals(bp.a)) {
                this.q.add(a);
            }
        }
        this.u.setText(dt.t);
        this.i.setText(dt.y);
        this.o.setText(dt.u);
        this.p.setText(dt.i);
        if (dt.i != null && dt.i.length() > 0) {
            this.p.setEnabled(false);
        }
        else {
            this.p.setEnabled(true);
        }
        this.a.setText(dt.o);
        this.w.setState(dt.q());
        this.s.setText(dt.p);
    }
    
    public final boolean q(final bp bp) {
        final L l = (L)bp;
        final String text = this.w.getText();
        super.q.q((bJ)bp);
        final int int1 = Integer.parseInt(this.q.getText());
        int i = 0;
        while (true) {
            while (i < this.q()) {
                final bp q = this.q(i);
                if (bp != q && q.s == int1) {
                    final boolean b = true;
                    if (b) {
                        this.q.requestFocus();
                        new dd(super.q, bX.r, be.w("A site with this ID already exists.  Please choose another ID for this site."), super.q).setVisible(true);
                        return false;
                    }
                    if (Integer.parseInt(this.q.getText()) < 1000) {
                        this.q.requestFocus();
                        new dd(super.q, bX.r, be.w("You must specify a site ID greater than 1000.  Please choose another ID for this site."), super.q).setVisible(true);
                        return false;
                    }
                    if (text.length() == 0) {
                        this.w.requestFocus();
                        new dd(super.q, bX.r, be.w("You must provide a name for this site.  Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    final String s = text;
                    int j = 0;
                    while (true) {
                        while (j < this.q()) {
                            final bp q2 = this.q(j);
                            if (bp != q2 && q2.a.equalsIgnoreCase(s)) {
                                final boolean b2 = true;
                                if (b2) {
                                    this.w.requestFocus();
                                    new dd(super.q, bX.r, be.w("A site with this name already exists.  Please choose another name for this site."), super.q).setVisible(true);
                                    return false;
                                }
                                this.w();
                                for (int k = 0; k < text.length(); ++k) {
                                    final char char1;
                                    if ((char1 = text.charAt(k)) != '_' && !Character.isLetterOrDigit(char1)) {
                                        this.w.requestFocus();
                                        new dd(super.q, bX.r, be.w("Site names may contain only letters, digits, and the underscore character (_).  Please choose another name for this site."), super.q).setVisible(true);
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
                                    new dd(super.q, bX.r, be.w("The user count you entered is invalid. Please re-enter."), super.q).setVisible(true);
                                    return false;
                                }
                                l.a = text;
                                l.q(61, this.q.getState());
                                l.q = int2;
                                l.s = Integer.parseInt(this.q.getText());
                                l.w = this.t.getText();
                                l.e = this.y.getText();
                                final String selectedItem = this.q.getSelectedItem();
                                if (!bX.e.equals(selectedItem)) {
                                    l.q = selectedItem;
                                }
                                if (dN.u) {
                                    if (this.q.q() < 0L) {
                                        return false;
                                    }
                                    if (this.w.q() < 0L) {
                                        return false;
                                    }
                                    l.w = this.q.q();
                                    l.y = dx.q(l.w, this.w.q());
                                    final String text2 = this.r.getText();
                                    int int3;
                                    try {
                                        int3 = Integer.parseInt(text2);
                                    }
                                    catch (Exception ex2) {
                                        this.r.requestFocus();
                                        this.r.selectAll();
                                        new dd(super.q, bX.r, be.w("You entered int value in invalid format!"), super.q).setVisible(true);
                                        return false;
                                    }
                                    l.u = int3;
                                    l.r = this.q.getText();
                                    l.t = this.u.getText();
                                    l.y = this.i.getText();
                                    l.u = this.o.getText();
                                }
                                l.i = this.p.getText();
                                l.o = this.a.getText();
                                l.q(this.w.getState());
                                l.p = this.s.getText();
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
    }
    
    public final void q(final dK dk) {
        dk.q(new u(be.w("Each site must have a unique name.  This name is not displayed to users, but is used to identify the site and store its settings and resources on the server.")), 2, 1.0f, 0.0f);
        dk.q(be.w("Name:"), new Component[] { this.w, new Label(be.w("Site ID:")), this.q });
        dk.q(be.w("Display Name:"), this.t, 0);
        dk.q(be.w("Right Menu Text:"), this.y, 0);
        dk.q(be.w("Moderated Option:"), this.q, 0);
        dk.q(be.w("Suspend:"), new Component[] { this.w, this.s });
        dk.q(be.w("Max users (0 for unlimited):"), this.e, 0);
        dk.q(be.w("FTP Username:"), this.p, 0);
        dk.q(be.w("FTP Password:"), this.a, 0);
        dk.q(be.w("Copy Settings From Site:"), this.q, 0);
        if (dN.u) {
            dk.q(be.w("Start Date:"), new Component[] { this.q, this.y });
            dk.q(be.w("End Date:"), new Component[] { this.w, this.q });
            dk.q(be.w("Payment:"), this.r, 0);
            dk.q(be.w("Site:"), this.u, 0);
            dk.q(be.w("Phone:"), this.i, 0);
            dk.q(be.w("E-mail:"), this.o, 0);
            dk.q(be.w("Comment:"), this.q, 0);
        }
    }
    
    public final void q() {
        final Vector<L> vector = new Vector<L>();
        final Vector<L> vector2 = new Vector<L>();
        for (int i = 0; i < this.q(); ++i) {
            final L l;
            if ((l = (L)this.q(i)).q(63)) {
                vector2.addElement(l);
            }
            else {
                final L j;
                if ((j = (L)this.q.r.w(l.s)) == null || l.q(j) != 0) {
                    vector.addElement(l);
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
        final int r = super.q.R;
        final dI di;
        (di = new dI(17238273, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final L l = vector.elementAt(i);
            di.q(i, l.w());
            di.q(i, 0, l.s);
            di.q(i, 1, l.q);
            di.q(i, 2, r);
            di.q(i, 3, l.w);
            di.q(i, 5, l.y);
            di.q(i, 6, l.u);
            if (!l.q(63)) {
                di.q(i, 0, l.a);
            }
            di.q(i, 1, l.r);
            di.q(i, 2, l.w);
            di.q(i, 3, l.q);
            di.q(i, 4, l.t);
            di.q(i, 5, l.y);
            di.q(i, 6, l.u);
            di.q(i, 7, l.e);
            di.q(i, 8, l.i);
            di.q(i, 10, l.p);
            di.q(i, 0, new dD(l.o));
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17238274, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final L l = vector.elementAt(i);
            di.q(i, l.w());
            di.q(i, 0, l.s);
        }
        super.q.o(di);
    }
    
    public final void w(final bp bp) {
        this.w.removeAll();
        this.w.add(bX.w);
        if (bp == null || bp.s == 0) {
            this.t.e();
            this.w.setEnabled(false);
            this.u.e();
        }
        else if (bp.s != 0) {
            this.t.q();
            this.w.setEnabled(true);
            final L l = (L)bp;
            for (int i = 0; i < l.q().length; ++i) {
                this.w.add(l.q()[i]);
            }
        }
        super.w(bp);
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.y) {
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
                                this.q.setText(dx.q(this.q.q(), this.w.q()));
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
                final bp bp = (bp)super.q.q();
                if (event.target == this.t) {
                    final bp bp2 = bp;
                    final dI di = new dI(33622276, 1);
                    di.q(0, 0, bp2.s);
                    super.q.o(di);
                }
                if (event.target == this.u) {
                    final String selectedItem = this.w.getSelectedItem();
                    final dI di2;
                    (di2 = new dI(33622277, 1)).q(0, 0, bp.s);
                    di2.q(0, 0, L.q(selectedItem));
                    super.q.o(di2);
                }
                if (event.target == this.w) {
                    if (!this.w.getSelectedItem().equals(bX.w)) {
                        this.u.q();
                    }
                    else {
                        this.u.e();
                    }
                }
                return super.handleEvent(event);
            }
            case 701: {
                this.w((bp)event.arg);
                return true;
            }
            case 702: {
                this.w((bp)null);
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
            if (!((L)this.q(i)).q(63)) {
                ++n;
            }
        }
        return n;
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < this.q.r.q; ++i) {
                final dt dt = new dt((L)this.q.r.q(i));
                super.q.q(dt, dt.s != 0);
                this.e(dt);
            }
        }
        finally {}
    }
    
    public final void e(final bp bp) {
        super.q.q(bp, bp.s);
        final int q;
        if ((q = super.q.q((bJ)bp)) == -1) {
            super.q.e(bp);
            if (bp.q(62)) {
                super.q.q(bp, true);
            }
            if (super.q != null) {
                final bp q2;
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
            super.q.q(bp, q);
            if (super.q != null) {
                super.q.dispose();
                super.q = null;
            }
        }
        this.w(bp);
    }
    
    public bX(final ap ap) {
        super(ap, be.w("Sites"), be.w("Site"));
        new SimpleDateFormat("dd/MM/yyyy");
        this.e = false;
        this.q = new TextField(5);
        this.w = new TextField(10);
        this.t = new TextField(20);
        this.y = new TextField(20);
        this.e = new TextField(5);
        this.q = new dx();
        this.y = new ad(be.w("Now"));
        this.w = new dx();
        this.q = new Label(bX.q + "               ");
        this.r = new TextField(5);
        this.u = new TextField(7);
        this.i = new TextField(7);
        this.o = new TextField(7);
        this.q = new TextArea(3, 20);
        this.t = new ad(80, 20);
        this.q = new Checkbox();
        this.q = new Choice();
        this.p = new TextField(10);
        this.a = new TextField(10);
        this.w = new Checkbox();
        this.s = new TextField(20);
        this.t.q(be.w("Restart Site"));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.t.getFontMetrics(this.t.getFont())).stringWidth(be.w("Restart Site"));
        stringWidth += 20;
        this.t.resize(stringWidth, 20);
        this.t.e();
        (this.w = new Choice()).disable();
        (this.u = new ad(80, 20)).q(be.w("Restore"));
        this.u.resize(fontMetrics.stringWidth(be.w("Restore")) + 20, 20);
        this.u.e();
        this.q("", new Component[] { this.t, this.w, this.u });
    }
    
    static {
        bX.w = be.w("--Select Backup--");
        bX.e = be.w("None");
        bX.r = be.w("Note");
        q = bX.e + "              ";
        be.w("Month");
        be.w("Incorrect start date");
    }
}
