import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Checkbox;

// 
// Decompiled by Procyon v0.5.30
// 

class cj extends _
{
    Checkbox g;
    Checkbox h;
    Checkbox i;
    Checkbox j;
    TextField k;
    TextField l;
    o m;
    private final Xi ta;
    
    cj(final Xi ta, final Frame frame, final o m) {
        super(new BorderLayout(), 1, new Insets(4, 4, 4, 4));
        this.ta = ta;
        this.m = m;
        final a a = new a(new BorderLayout(), 2, new Insets(4, 4, 4, 4));
        a.setLayout(new GridLayout(3, 4, 5, 4));
        a.add(new Label(m.b("strAvgType")));
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        a.add(this.g = new Checkbox(m.b("rbSimpleAvg"), Xi.b(ta) != 0, checkboxGroup));
        a.add(this.h = new Checkbox(m.b("rbExpAvg"), Xi.b(ta) == 0, checkboxGroup));
        a.add(new Label(m.b("")));
        a.add(new Label(m.b("strAvgPeriod")));
        (this.k = new c(3, true)).setText(String.valueOf(Xi._(ta)));
        a.add(this.k);
        a.add(new Label(m.b("")));
        a.add(new Label(m.b("")));
        a.add(new Label(m.b("strDisplacement")));
        (this.l = new c(3, true)).setText(String.valueOf(Math.abs(Xi.a(ta))));
        a.add(this.l);
        final CheckboxGroup checkboxGroup2 = new CheckboxGroup();
        a.add(this.j = new Checkbox(m.b("rbDisplaceBackward"), Xi.a(ta) <= 0, checkboxGroup2));
        a.add(this.i = new Checkbox(m.b("rbDisplaceForward"), Xi.a(ta) > 0, checkboxGroup2));
        this.add(a);
    }
    
    public String j() {
        int int1 = -1;
        try {
            int1 = Integer.parseInt(this.k.getText());
        }
        catch (NumberFormatException ex) {}
        if (int1 < 2 || int1 > 999) {
            return this.m.b("msgInvalidAvgPeriod");
        }
        int int2 = -1;
        try {
            int2 = Integer.parseInt(this.k.getText());
        }
        catch (NumberFormatException ex2) {}
        if (int2 < 0 || int2 > 999) {
            return this.m.b("msgInvalidDisplacement");
        }
        return null;
    }
}
