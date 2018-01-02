// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Label;
import java.awt.Component;
import java.awt.TextField;
import java.awt.Checkbox;

public final class aU extends cV
{
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private Checkbox y;
    private Checkbox u;
    private Checkbox i;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private TextField t;
    private TextField y;
    private TextField u;
    private TextField i;
    private TextField o;
    private TextField p;
    private TextField a;
    private ap q;
    
    public final void q() {
        final String trim = this.q.getText().trim();
        final String trim2 = this.w.getText().trim();
        final String trim3 = this.e.getText().trim();
        final String trim4 = this.r.getText().trim();
        final String trim5 = this.t.getText().trim();
        final String trim6 = this.u.getText().trim();
        final String trim7 = this.i.getText().trim();
        final String trim8 = this.o.getText().trim();
        final String trim9 = this.p.getText().trim();
        final String trim10 = this.a.getText().trim();
        final String trim11 = this.y.getText().trim();
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (NumberFormatException ex) {
            this.q.requestFocus();
            this.q.selectAll();
            throw new Q(be.w("The max chars you entered is not valid.  Please re-enter this information."));
        }
        int int2;
        try {
            int2 = Integer.parseInt(trim2);
        }
        catch (NumberFormatException ex2) {
            this.w.requestFocus();
            this.w.selectAll();
            throw new Q(be.w("The max chatwatches you entered is not valid.  Please re-enter this information."));
        }
        int int3;
        try {
            int3 = Integer.parseInt(trim3);
        }
        catch (NumberFormatException ex3) {
            this.e.requestFocus();
            this.e.selectAll();
            throw new Q(be.w("The max connection you entered is not valid.  Please re-enter this information."));
        }
        int int4;
        try {
            int4 = Integer.parseInt(trim4);
        }
        catch (NumberFormatException ex4) {
            this.r.requestFocus();
            this.r.selectAll();
            throw new Q(be.w("The max emotions you entered is not valid.  Please re-enter this information."));
        }
        int int5;
        try {
            int5 = Integer.parseInt(trim5);
        }
        catch (NumberFormatException ex5) {
            this.t.requestFocus();
            this.t.selectAll();
            throw new Q(be.w("The max flood lines you entered is not valid.  Please re-enter this information."));
        }
        int int6;
        try {
            int6 = Integer.parseInt(trim11);
        }
        catch (NumberFormatException ex6) {
            this.y.requestFocus();
            this.y.selectAll();
            throw new Q(be.w("The max nickname you entered is not valid.  Please re-enter this information."));
        }
        int int7;
        try {
            int7 = Integer.parseInt(trim6);
        }
        catch (NumberFormatException ex7) {
            this.u.requestFocus();
            this.u.selectAll();
            throw new Q(be.w("The max chars you entered is not valid.  Please re-enter this information."));
        }
        int int8;
        try {
            int8 = Integer.parseInt(trim7);
        }
        catch (NumberFormatException ex8) {
            this.i.requestFocus();
            this.i.selectAll();
            throw new Q(be.w("The max chatwatches you entered is not valid.  Please re-enter this information."));
        }
        int int9;
        try {
            int9 = Integer.parseInt(trim8);
        }
        catch (NumberFormatException ex9) {
            this.o.requestFocus();
            this.o.selectAll();
            throw new Q(be.w("The max connection you entered is not valid.  Please re-enter this information."));
        }
        int int10;
        try {
            int10 = Integer.parseInt(trim9);
        }
        catch (NumberFormatException ex10) {
            this.p.requestFocus();
            this.p.selectAll();
            throw new Q(be.w("The max emotions you entered is not valid.  Please re-enter this information."));
        }
        int int11;
        try {
            int11 = Integer.parseInt(trim10);
        }
        catch (NumberFormatException ex11) {
            this.a.requestFocus();
            this.a.selectAll();
            throw new Q(be.w("The max flood lines you entered is not valid.  Please re-enter this information."));
        }
        final long q = dI.q(dI.q(dI.q(dI.q(dI.q(dI.q(dI.q(dI.q(0L, 59, this.q.getState()), 58, this.w.getState()), 57, this.e.getState()), 55, this.t.getState()), 54, this.r.getState()), 53, this.u.getState()), 52, this.y.getState()), 56, this.i.getState());
        final dI di;
        (di = new dI(67844, 1)).q(0, 0, int1);
        di.q(0, 1, int2);
        di.q(0, 2, int3);
        di.q(0, 3, int4);
        di.q(0, 4, int5);
        di.q(0, 5, int6);
        di.q(0, 6, int7);
        di.q(0, 7, int8);
        di.q(0, 8, int9);
        di.q(0, 9, int10);
        di.q(0, 10, int11);
        di.q(0, q);
        di.w = -1;
        di.q = -1;
        this.q.o(di);
    }
    
    public final void w() {
        this.q.setState(dI.q(((cT)this.q).y, 59));
        this.w.setState(dI.q(((cT)this.q).y, 58));
        this.e.setState(dI.q(((cT)this.q).y, 57));
        this.t.setState(dI.q(((cT)this.q).y, 55));
        this.r.setState(dI.q(((cT)this.q).y, 54));
        this.u.setState(dI.q(((cT)this.q).y, 53));
        this.y.setState(dI.q(((cT)this.q).y, 52));
        this.i.setState(dI.q(((cT)this.q).y, 56));
        this.q.setText(new Integer(((cT)this.q).Z).toString());
        this.w.setText(new Integer(((cT)this.q).V).toString());
        this.e.setText(new Integer(((cT)this.q).T).toString());
        this.r.setText(new Integer(((cT)this.q).C).toString());
        this.t.setText(new Integer(((cT)this.q).X).toString());
        this.y.setText(new Integer(((cT)this.q).B).toString());
        this.u.setText(new Integer(((cT)this.q).M).toString());
        this.i.setText(new Integer(((cT)this.q).aa).toString());
        this.o.setText(new Integer(((cT)this.q).Y).toString());
        this.p.setText(new Integer(((cT)this.q).ab).toString());
        this.a.setText(new Integer(((cT)this.q).N).toString());
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return be.w("Check this box if you wish to enable ability to login with different icon from default.");
        }
        if (o == this.w) {
            return be.w("Check this box if you wish to enable emotions button.");
        }
        if (o == this.e) {
            return be.w("Check this box if you wish to enable shortcut button.");
        }
        if (o == this.t) {
            return be.w("Check this box if you wish to give master ability to use font styles in messages.");
        }
        if (o == this.r) {
            return be.w("Check this box if you wish to give guest ability to use font styles in messages.");
        }
        if (o == this.q) {
            return be.w("Enter a max chars in one user message.");
        }
        if (o == this.w) {
            return be.w("Enter a max chatwatches in one user message.");
        }
        if (o == this.e) {
            return be.w("Enter a max connections from one ip.");
        }
        if (o == this.r) {
            return be.w("Enter a max emotions in one user message.");
        }
        if (o == this.t) {
            return be.w("Enter a max flood lines.");
        }
        if (o == this.y) {
            return be.w("Enter a max nickname length.");
        }
        return null;
    }
    
    public aU(final ap q) {
        super(be.w("Options2"));
        this.q = new Checkbox(be.w("Guest icon login"));
        this.w = new Checkbox(be.w("Emotions button"));
        this.e = new Checkbox(be.w("Shortcut button"));
        this.t = new Checkbox(be.w("Master"));
        this.r = new Checkbox(be.w("Guest"));
        this.u = new Checkbox(be.w("Master"));
        this.y = new Checkbox(be.w("Guest"));
        this.i = new Checkbox(be.w("Start color day"));
        this.q = new TextField(5);
        this.w = new TextField(5);
        this.e = new TextField(5);
        this.r = new TextField(5);
        this.t = new TextField(5);
        this.y = new TextField(5);
        this.u = new TextField(5);
        this.i = new TextField(5);
        this.o = new TextField(5);
        this.p = new TextField(5);
        this.a = new TextField(5);
        this.q = q;
        this.q("", new Component[] { new Label(be.w("Masters")), new Label(be.w("Guests")) });
        this.q(be.w("Max chars in message:"), new Component[] { this.q, this.u });
        this.q(be.w("Max chatwatches in message:"), new Component[] { this.w, this.i });
        this.q(be.w("Max connection from one ip:"), new Component[] { this.e, this.o });
        this.q(be.w("Max emotions in message:"), new Component[] { this.r, this.p });
        this.q(be.w("Max flood lines:"), new Component[] { this.t, this.a });
        this.q(be.w("Max nickname length:"), this.y);
        this.q(be.w("Show emotions button:"), this.w);
        this.q(be.w("Show shortcut button:"), this.e);
        this.q(be.w("Login guests in random colors:"), this.i);
        this.q(be.w("Show font styles:"), this.t, this.r);
        this.q(be.w("Show color button:"), this.u, this.y);
    }
}
