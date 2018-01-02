import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Checkbox;

// 
// Decompiled by Procyon v0.5.30
// 

class volatile extends _
{
    Checkbox fla;
    Checkbox gla;
    Checkbox hla;
    Checkbox ila;
    TextField k;
    Checkbox jla;
    Checkbox kla;
    Checkbox lla;
    Checkbox mla;
    Checkbox nla;
    o m;
    private final package ta;
    
    volatile(final package ta, final Frame frame, final o m) {
        super(new BorderLayout(), 1, new Insets(4, 4, 4, 4));
        this.ta = ta;
        this.m = m;
        final a a = new a(new BorderLayout(), 2, new Insets(4, 4, 4, 4));
        a.add(new Label(m.b("strCalculation")), "North");
        final Panel panel = new Panel(new FlowLayout(0));
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        panel.add(this.hla = new Checkbox(m.b("rbMonthly"), package._(ta) == 3, checkboxGroup));
        panel.add(this.gla = new Checkbox(m.b("rbWeekly"), package._(ta) == 2, checkboxGroup));
        panel.add(this.fla = new Checkbox(m.b("rbDaily"), package._(ta) == 1, checkboxGroup));
        panel.add(this.ila = new Checkbox(m.b("rbArbitraryPeriod"), package._(ta) == 0, checkboxGroup));
        (this.k = new c(3, true)).setText(String.valueOf(package.a(ta)));
        panel.add(this.k);
        a.add(panel, "Center");
        this.add(a, "North");
        final a a2 = new a(new GridLayout(6, 1), 2, new Insets(4, 4, 4, 4));
        a2.add(new Label(m.b("strDisplaySettings")));
        a2.add(this.jla = new Checkbox(m.b("cbDisplayPivotPointLevels"), package.a(ta)));
        a2.add(this.kla = new Checkbox(m.b("cbDisplayR1S1Levels"), package.b(ta)));
        a2.add(this.lla = new Checkbox(m.b("cbDisplayR2S2Levels"), package._(ta)));
        final CheckboxGroup checkboxGroup2 = new CheckboxGroup();
        a2.add(this.nla = new Checkbox(m.b("rbDisplayAllPeriods"), !package.k(ta), checkboxGroup2));
        a2.add(this.mla = new Checkbox(m.b("rbDisplayLastPeriodOnly"), package.k(ta), checkboxGroup2));
        this.add(a2, "Center");
    }
    
    public String j() {
        int int1 = -1;
        try {
            int1 = Integer.parseInt(this.k.getText());
        }
        catch (NumberFormatException ex) {}
        if (int1 < 1 || int1 > 999) {
            return this.m.b("msgInvalidPeriodCount");
        }
        if (!this.jla.getState() && !this.kla.getState() && !this.lla.getState()) {
            return this.m.b("msgNoDisplayElementSelected");
        }
        return null;
    }
}
