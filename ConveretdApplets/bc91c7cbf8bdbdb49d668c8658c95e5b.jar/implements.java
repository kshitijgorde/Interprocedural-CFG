import java.awt.Event;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class implements extends Frame
{
    private Button ojc;
    private Button pjc;
    private Button qjc;
    private instanceof[] rjc;
    private int ta;
    private static String Ea = "\u80b1\u8087\u808e\u8087\u8081\u8096\u80c2\u8094\u8083\u808e\u8097\u8087\u8091\u80c2\u8096\u808d\u80c2\u808b\u808c\u8092\u8097\u8096";
    private static String Fa = "\u80b0\u8087\u8091\u8087\u8096";
    private static String Ga = "\u80a1\u8083\u808c\u8081\u8087\u808e";
    private static String Ha = "\u80ad\u80a9";
    private static String Ia = "\u80b4\u8083\u808e\u8097\u8087\u8091\u80c2\u8096\u808d\u80c2\u808b\u808c\u8092\u8097\u8096\u80c2\u8091\u8087\u808e\u8087\u8081\u8096\u808d\u8090";
    private static String Ja = "\u80ac\u808d\u8090\u8096\u808a";
    private static String Ka = "\u80b1\u808d\u8097\u8096\u808a";
    private static String La = "\u80a1\u8087\u808c\u8096\u8087\u8090";
    
    public implements(final instanceof[] rjc, final int ta, final Font font) {
        super(implements.Ea);
        this.ojc = new Button(implements.Fa);
        this.pjc = new Button(implements.Ga);
        this.qjc = new Button(implements.Ha);
        this.rjc = rjc;
        this.ta = ta;
        this.setLayout(new BorderLayout());
        final Label label = new Label(implements.Ia);
        label.setFont(font);
        label.requestFocus();
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(this.ojc);
        panel.add(this.pjc);
        panel.add(this.qjc);
        this.ojc.setFont(font);
        this.pjc.setFont(font);
        this.qjc.setFont(font);
        this.add(implements.Ja, label);
        this.add(implements.Ka, panel);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(ta, 1));
        for (int i = 0; i < this.ta; ++i) {
            panel2.add(this.rjc[i]);
        }
        this.add(implements.La, panel2);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (event.target == this.qjc) {
                this.n();
                return true;
            }
            if (event.target == this.pjc) {
                this.c();
                return true;
            }
            if (event.target == this.ojc) {
                for (int i = 0; i < this.ta; ++i) {
                    this.rjc[i].l();
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            this.n();
            return true;
        }
        if (n == 27) {
            this.c();
            return true;
        }
        return false;
    }
    
    private void c() {
        this.hide();
    }
    
    private void n() {
        for (int i = 0; i < this.ta; ++i) {
            this.rjc[i].m();
        }
        this.d();
        this.hide();
    }
    
    public void d() {
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE80E2);
        }
        return new String(array);
    }
    
    static {
        implements.Ea = _(implements.Ea);
        implements.Fa = _(implements.Fa);
        implements.Ga = _(implements.Ga);
        implements.Ha = _(implements.Ha);
        implements.Ia = _(implements.Ia);
        implements.Ja = _(implements.Ja);
        implements.Ka = _(implements.Ka);
        implements.La = _(implements.La);
    }
}
