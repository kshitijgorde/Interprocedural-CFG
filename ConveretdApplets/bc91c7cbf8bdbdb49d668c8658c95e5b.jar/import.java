import java.awt.Event;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class import extends Frame
{
    Button Da;
    TextArea ya;
    String sa;
    public String za;
    public String Ric;
    public String Sic;
    public String Tic;
    public String Uic;
    public String Vic;
    public String Ra;
    public String Pa;
    public String Sa;
    public String Wic;
    public String Xic;
    public String Yic;
    public String Zic;
    public String _jc;
    public String ajc;
    public String bjc;
    public String cjc;
    public String djc;
    public String ejc;
    public String fjc;
    public String gjc;
    public String hjc;
    public String ijc;
    public String jjc;
    public String kjc;
    public boolean ljc;
    public boolean mjc;
    public boolean njc;
    private static String Ea = "\u5d0c\u5d08\u5d63\u5d22\u5d2d\u5d27\u5d63\u5d20\u5d2f\u5d2c\u5d30\u5d26";
    private static String Fa = "\u5d10\u5d2c\u5d36\u5d37\u5d2b";
    private static String Ga = "\u5d00\u5d26\u5d2d\u5d37\u5d26\u5d31";
    
    public import(final String s) {
        super(s);
        final Panel panel = new Panel();
        this.Da = new Button(import.Ea);
        panel.setLayout(new FlowLayout());
        panel.add(this.Da);
        this.ya = new TextArea();
        this.setLayout(new BorderLayout());
        this.add(import.Fa, panel);
        this.add(import.Ga, this.ya);
    }
    
    public void l(final String text) {
        this.ya.setText(text);
    }
    
    public void _(final Font font) {
        this.ya.setFont(font);
        this.Da.setFont(font);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.hide();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x15D43);
        }
        return new String(array);
    }
    
    static {
        import.Ea = _(import.Ea);
        import.Fa = _(import.Fa);
        import.Ga = _(import.Ga);
    }
}
