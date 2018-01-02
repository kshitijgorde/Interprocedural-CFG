// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Checkbox;

public final class dy extends G
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
    private Choice q;
    private cV q;
    
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
        final int selectedIndex = this.q.getSelectedIndex();
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (NumberFormatException ex) {
            this.q.requestFocus();
            this.q.selectAll();
            throw new cF(eb.q("The max chars you entered is not valid.  Please re-enter this information."));
        }
        int int2;
        try {
            int2 = Integer.parseInt(trim2);
        }
        catch (NumberFormatException ex2) {
            this.w.requestFocus();
            this.w.selectAll();
            throw new cF(eb.q("The max chatwatches you entered is not valid.  Please re-enter this information."));
        }
        int int3;
        try {
            int3 = Integer.parseInt(trim3);
        }
        catch (NumberFormatException ex3) {
            this.e.requestFocus();
            this.e.selectAll();
            throw new cF(eb.q("The max connection you entered is not valid.  Please re-enter this information."));
        }
        int int4;
        try {
            int4 = Integer.parseInt(trim4);
        }
        catch (NumberFormatException ex4) {
            this.r.requestFocus();
            this.r.selectAll();
            throw new cF(eb.q("The max emotions you entered is not valid.  Please re-enter this information."));
        }
        int int5;
        try {
            int5 = Integer.parseInt(trim5);
        }
        catch (NumberFormatException ex5) {
            this.t.requestFocus();
            this.t.selectAll();
            throw new cF(eb.q("The max flood lines you entered is not valid.  Please re-enter this information."));
        }
        int int6;
        try {
            int6 = Integer.parseInt(trim11);
        }
        catch (NumberFormatException ex6) {
            this.y.requestFocus();
            this.y.selectAll();
            throw new cF(eb.q("The max nickname you entered is not valid.  Please re-enter this information."));
        }
        int int7;
        try {
            int7 = Integer.parseInt(trim6);
        }
        catch (NumberFormatException ex7) {
            this.u.requestFocus();
            this.u.selectAll();
            throw new cF(eb.q("The max chars you entered is not valid.  Please re-enter this information."));
        }
        int int8;
        try {
            int8 = Integer.parseInt(trim7);
        }
        catch (NumberFormatException ex8) {
            this.i.requestFocus();
            this.i.selectAll();
            throw new cF(eb.q("The max chatwatches you entered is not valid.  Please re-enter this information."));
        }
        int int9;
        try {
            int9 = Integer.parseInt(trim8);
        }
        catch (NumberFormatException ex9) {
            this.o.requestFocus();
            this.o.selectAll();
            throw new cF(eb.q("The max connection you entered is not valid.  Please re-enter this information."));
        }
        int int10;
        try {
            int10 = Integer.parseInt(trim9);
        }
        catch (NumberFormatException ex10) {
            this.p.requestFocus();
            this.p.selectAll();
            throw new cF(eb.q("The max emotions you entered is not valid.  Please re-enter this information."));
        }
        int int11;
        try {
            int11 = Integer.parseInt(trim10);
        }
        catch (NumberFormatException ex11) {
            this.a.requestFocus();
            this.a.selectAll();
            throw new cF(eb.q("The max flood lines you entered is not valid.  Please re-enter this information."));
        }
        final long q = es.q(es.q(es.q(es.q(es.q(es.q(es.q(es.q(0L, 59, this.q.getState()), 58, this.w.getState()), 57, this.e.getState()), 55, this.t.getState()), 54, this.r.getState()), 53, this.u.getState()), 52, this.y.getState()), 56, this.i.getState());
        final es es;
        (es = new es(67844, 1)).q(0, 0, int1);
        es.q(0, 1, int2);
        es.q(0, 2, int3);
        es.q(0, 3, int4);
        es.q(0, 4, int5);
        es.q(0, 5, int6);
        es.q(0, 6, int7);
        es.q(0, 7, int8);
        es.q(0, 8, int9);
        es.q(0, 9, int10);
        es.q(0, 10, int11);
        es.q(0, 11, selectedIndex);
        es.q(0, q);
        es.w = -1;
        es.q = -1;
        this.q.q(es);
    }
    
    public final void w() {
        this.q.setState(es.q(((dz)this.q).t, 59));
        this.w.setState(es.q(((dz)this.q).t, 58));
        this.e.setState(es.q(((dz)this.q).t, 57));
        this.t.setState(es.q(((dz)this.q).t, 55));
        this.r.setState(es.q(((dz)this.q).t, 54));
        this.u.setState(es.q(((dz)this.q).t, 53));
        this.y.setState(es.q(((dz)this.q).t, 52));
        this.i.setState(es.q(((dz)this.q).t, 56));
        this.q.setText(new Integer(((dz)this.q).T).toString());
        this.w.setText(new Integer(((dz)this.q).I).toString());
        this.e.setText(new Integer(((dz)this.q).C).toString());
        this.r.setText(new Integer(((dz)this.q).U).toString());
        this.t.setText(new Integer(((dz)this.q).Y).toString());
        this.y.setText(new Integer(((dz)this.q).O).toString());
        this.u.setText(new Integer(((dz)this.q).A).toString());
        this.i.setText(new Integer(((dz)this.q).S).toString());
        this.o.setText(new Integer(((dz)this.q).V).toString());
        this.p.setText(new Integer(((dz)this.q).D).toString());
        this.a.setText(new Integer(((dz)this.q).P).toString());
        this.q.select(((dz)this.q).Z);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return eb.q("Check this box if you wish to enable ability to login with different icon from default.");
        }
        if (o == this.w) {
            return eb.q("Check this box if you wish to enable emotions button.");
        }
        if (o == this.e) {
            return eb.q("Check this box if you wish to enable shortcut button.");
        }
        if (o == this.t) {
            return eb.q("Check this box if you wish to give master ability to use font styles in messages.");
        }
        if (o == this.r) {
            return eb.q("Check this box if you wish to give guest ability to use font styles in messages.");
        }
        if (o == this.q) {
            return eb.q("Enter a max chars in one user message.");
        }
        if (o == this.w) {
            return eb.q("Enter a max chatwatches in one user message.");
        }
        if (o == this.e) {
            return eb.q("Enter a max connections from one ip.");
        }
        if (o == this.r) {
            return eb.q("Enter a max emotions in one user message.");
        }
        if (o == this.t) {
            return eb.q("Enter a max flood lines.");
        }
        if (o == this.y) {
            return eb.q("Enter a max nickname length.");
        }
        return null;
    }
    
    public dy(final cV q) {
        super(eb.q("Extra Options"));
        this.q = new Checkbox(eb.q("Guest icon login"));
        this.w = new Checkbox(eb.q("Emotions button"));
        this.e = new Checkbox(eb.q("Shortcut button"));
        this.t = new Checkbox(eb.q("Master"));
        this.r = new Checkbox(eb.q("Guest"));
        this.u = new Checkbox(eb.q("Master"));
        this.y = new Checkbox(eb.q("Guest"));
        this.i = new Checkbox(eb.q("Start color day"));
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
        final Choice q2;
        (q2 = new Choice()).setForeground(Color.black);
        q2.addItem(bV.q);
        q2.addItem(bV.w);
        q2.addItem(bV.e);
        q2.addItem(bV.r);
        this.q = q2;
        this.q = q;
        this.q("", new Component[] { new Label(eb.q("Masters")), new Label(eb.q("Guests")) });
        this.q(eb.q("Max chars in message:"), new Component[] { this.q, this.u });
        this.q(eb.q("Max chatwatches in message:"), new Component[] { this.w, this.i });
        this.q(eb.q("Max connection from one ip:"), new Component[] { this.e, this.o });
        this.q(eb.q("Max emotions in message:"), new Component[] { this.r, this.p });
        this.q(eb.q("Max flood lines:"), new Component[] { this.t, this.a });
        this.q(eb.q("Max nickname length:"), this.y);
        this.q(eb.q("Bad words filter sensetivity:"), this.q);
        this.q(eb.q("Show emotions button:"), this.w);
        this.q(eb.q("Show shortcut button:"), this.e);
        this.q(eb.q("Login guests in random colors:"), this.i);
    }
}
