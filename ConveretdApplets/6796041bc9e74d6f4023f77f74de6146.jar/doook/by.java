// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Checkbox;

public class by extends o
{
    private Checkbox A;
    private Checkbox g;
    private Checkbox h;
    private Checkbox i;
    private Checkbox B;
    private Checkbox C;
    private Checkbox D;
    private Checkbox E;
    private Checkbox F;
    private Checkbox G;
    private Choice x;
    private TextField e;
    private TextField f;
    private TextField g;
    private TextField h;
    private TextField B;
    private TextField C;
    private TextField D;
    private TextField E;
    private TextField F;
    private TextField G;
    private u k;
    
    public void c() {
        final String trim = this.e.getText().trim();
        final String trim2 = this.f.getText().trim();
        final String trim3 = this.g.getText().trim();
        final String trim4 = this.h.getText().trim();
        final String trim5 = this.B.getText().trim();
        final String trim6 = this.C.getText().trim();
        final String trim7 = this.D.getText().trim();
        final String trim8 = this.E.getText().trim();
        final String trim9 = this.F.getText().trim();
        final String trim10 = this.G.getText().trim();
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (NumberFormatException ex) {
            this.e.requestFocus();
            this.e.selectAll();
            throw new aq(ao.e("The icon id you entered is invalid.  Please re-enter this information."));
        }
        int int2;
        try {
            int2 = Integer.parseInt(trim2);
        }
        catch (NumberFormatException ex2) {
            this.f.requestFocus();
            this.f.selectAll();
            throw new aq(ao.e("The number of maximum bot connections you entered is invalid.  Please re-enter this information."));
        }
        int int3;
        try {
            int3 = Integer.parseInt(trim3);
        }
        catch (NumberFormatException ex3) {
            this.g.requestFocus();
            this.g.selectAll();
            throw new aq(ao.e("The number of replacements allowed for guest you entered is invalid.  Please re-enter this information."));
        }
        int int4;
        try {
            int4 = Integer.parseInt(trim4);
        }
        catch (NumberFormatException ex4) {
            this.h.requestFocus();
            this.h.selectAll();
            throw new aq(ao.e("The number of smiles allowed for guest you entered is invalid.  Please re-enter this information."));
        }
        int int5;
        try {
            int5 = Integer.parseInt(trim5);
        }
        catch (NumberFormatException ex5) {
            this.B.requestFocus();
            this.B.selectAll();
            throw new aq(ao.e("The number of replacements allowed for super you entered is invalid.  Please re-enter this information."));
        }
        int int6;
        try {
            int6 = Integer.parseInt(trim6);
        }
        catch (NumberFormatException ex6) {
            this.C.requestFocus();
            this.C.selectAll();
            throw new aq(ao.e("The number of smiles allowed for super you entered is invalid.  Please re-enter this information."));
        }
        int int7;
        try {
            int7 = Integer.parseInt(trim7);
        }
        catch (NumberFormatException ex7) {
            this.D.requestFocus();
            this.D.selectAll();
            throw new aq(ao.e("Room life time you entered is invalid.  Please re-enter this information."));
        }
        int int8;
        try {
            int8 = Integer.parseInt(trim8);
        }
        catch (NumberFormatException ex8) {
            this.E.requestFocus();
            this.E.selectAll();
            throw new aq(ao.e("The maximum number of lines per message you entered is invalid.  Please re-enter this information."));
        }
        int int9;
        try {
            int9 = Integer.parseInt(trim9);
        }
        catch (NumberFormatException ex9) {
            this.F.requestFocus();
            this.F.selectAll();
            throw new aq(ao.e("The maximum number of charachters per message you entered is invalid.  Please re-enter this information."));
        }
        int int10;
        try {
            int10 = Integer.parseInt(trim10);
        }
        catch (NumberFormatException ex10) {
            this.G.requestFocus();
            this.G.selectAll();
            throw new aq(ao.e("The maximum number of repeated messages you entered is invalid.  Please re-enter this information."));
        }
        final long a = cD.a(cD.a(cD.a(cD.a(cD.a(cD.a(cD.a(cD.a(cD.a(cD.a(this.k.c, 41, this.A.getState()), 42, this.g.getState()), 43, this.h.getState()), 44, this.i.getState()), 45, this.B.getState()), 46, this.C.getState()), 47, this.D.getState()), 48, this.E.getState()), 49, this.F.getState()), 50, this.G.getState());
        final cD cd = new cD(262400, 1);
        cd.a(0, 0, int1);
        cd.a(0, 1, int2);
        cd.a(0, 2, int3);
        cd.a(0, 3, int4);
        cd.a(0, 4, int5);
        cd.a(0, 5, int6);
        cd.a(0, 6, int7);
        cd.a(0, 7, int8);
        cd.a(0, 8, int9);
        cd.a(0, 9, int10);
        cd.a(0, 10, this.x.getSelectedIndex());
        cd.a(0, a);
        cd.j = -1;
        cd.o = -1;
        this.k.o(cd);
    }
    
    public void d() {
        this.A.setState(cG.a[0]);
        this.g.setState(cG.a[1]);
        this.h.setState(cG.a[2]);
        this.i.setState(cG.a[3]);
        this.B.setState(cG.a[4]);
        this.C.setState(cG.a[5]);
        this.D.setState(cG.a[6]);
        this.E.setState(cG.a[7]);
        this.F.setState(cG.a[8]);
        this.G.setState(cG.a[9]);
        this.e.setText(new Integer(cG.g[0]).toString());
        this.f.setText(new Integer(cG.g[1]).toString());
        this.g.setText(new Integer(cG.g[2]).toString());
        this.h.setText(new Integer(cG.g[3]).toString());
        this.B.setText(new Integer(cG.g[4]).toString());
        this.C.setText(new Integer(cG.g[5]).toString());
        this.D.setText(new Integer(cG.g[6]).toString());
        this.E.setText(new Integer(cG.g[7]).toString());
        this.F.setText(new Integer(cG.g[8]).toString());
        this.G.setText(new Integer(cG.g[9]).toString());
        this.x.select(cG.g[10]);
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public by(final u k) {
        super(ao.e("Doook Plus"), k);
        this.A = new Checkbox(ao.e("Disable special name for guests"));
        this.g = new Checkbox(ao.e("Auto kick bots"));
        this.h = new Checkbox(ao.e("Use custom icons (requires restart)"));
        this.i = new Checkbox(ao.e("Cut display names more than 35"));
        this.B = new Checkbox(ao.e("Emoticons button"));
        this.C = new Checkbox(ao.e("Shortcuts button"));
        this.D = new Checkbox(ao.e("Guest"));
        this.E = new Checkbox(ao.e("Master"));
        this.F = new Checkbox(ao.e("Guest"));
        this.G = new Checkbox(ao.e("Master"));
        (this.x = new Choice()).setForeground(Color.black);
        this.x.setBackground(Color.white);
        this.x.add(ao.e("Disable Filter"));
        this.x.add(ao.e("Low Sensitivity"));
        this.x.add(ao.e("Medium Sensitivity"));
        this.x.add(ao.e("High Sensitivity"));
        this.e = new TextField(5);
        this.f = new TextField(5);
        this.g = new TextField(5);
        this.h = new TextField(5);
        this.B = new TextField(5);
        this.C = new TextField(5);
        this.D = new TextField(5);
        this.E = new TextField(5);
        this.F = new TextField(5);
        this.G = new TextField(5);
        this.k = k;
        this.a(ao.e("DoookSecurity Icon ID:"), this.e);
        this.a(ao.e("Maximum Bot Connections:"), this.f);
        this.a(ao.e("Number Of Replacements For Guest:"), this.g);
        this.a(ao.e("Number Of Smiles For Guest:"), this.h);
        this.a(ao.e("Number Of Replacements For Super:"), this.B);
        this.a(ao.e("Number Of Smiles For Super:"), this.C);
        this.a(ao.e("Temp Room Life Time (Minutes):"), this.D);
        this.a(ao.e("Number of lines per message:"), this.E);
        this.a(ao.e("Number of chars per message (requires restart):"), this.F);
        this.a(ao.e("Number of repeated messages (requires restart):"), this.G);
        this.a(ao.e("Bad words filter sensitivity:"), this.x);
        this.a("", this.A);
        this.a("", this.g);
        this.a("", this.i);
        this.a("", this.h);
        this.a(ao.e("Show emoticons button:"), this.B);
        this.a(ao.e("Show shortcuts button:"), this.C);
        this.a(ao.e("Show font styles for:"), this.D, this.E);
        this.a(ao.e("Show colors for:"), this.F, this.G);
    }
}
