import java.awt.Event;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class if extends Frame
{
    private Label sjc;
    private Label[] tjc;
    private Label[] ujc;
    private Label vjc;
    private TextField wjc;
    private TextField[] xjc;
    private Button Da;
    private Panel yjc;
    private Panel zjc;
    private Panel Ajc;
    private String Bjc;
    private String Cjc;
    private interface Djc;
    private interface[] Ejc;
    private int Fjc;
    private int Za;
    private static String Ea = "";
    private static String Fa = "\u0c99\u0c9d";
    private static String Ga = "\u0c93\u0cb7\u0ca5\u0ca2";
    private static String Ha = "\u0c85\u0cb9\u0ca3\u0ca2\u0cbe";
    private static String Ia = "\u0c95\u0cb3\u0cb8\u0ca2\u0cb3\u0ca4";
    private static String Ja = "\u0c80\u0cb7\u0cba\u0ca3\u0cb3\u0cf6\u0cb0\u0cb9\u0ca4\u0cf6";
    private static String Ka = "\u0cf6\u0cbf\u0ca5\u0cf6\u0cb8\u0cb9\u0ca2\u0cf6\u0cb7\u0cf6\u0ca0\u0cb7\u0cba\u0cbf\u0cb2\u0cf6\u0cb8\u0ca3\u0cbb\u0cb4\u0cb3\u0ca4";
    
    public if(final String s, final Image image, final int n, final int n2) {
        super(s);
        this.tjc = new Label[10];
        this.ujc = new Label[10];
        this.xjc = new TextField[10];
        this.yjc = new Panel();
        this.zjc = new Panel();
        this.Ajc = new Panel();
        this.setLayout(new FlowLayout());
        this.yjc.setLayout(new GridLayout(2, 1));
        this.sjc = new Label(if.Ea, 0);
        this.vjc = new Label(if.Ea, 0);
        this.yjc.add(this.vjc);
        this.yjc.add(this.sjc);
        this.zjc.setLayout(new GridLayout(1, 1));
        this.Da = new Button(if.Fa);
        this.zjc.add(if.Ga, this.Da);
        this.Ajc.setLayout(new GridLayout(1, 1));
        this.Ajc.add(new this(image, n, n2));
        this.add(this.Ajc);
        this.add(this.yjc);
        this.add(this.zjc);
        this.Za = 3;
    }
    
    public if(final String s) {
        super(s);
        this.tjc = new Label[10];
        this.ujc = new Label[10];
        this.xjc = new TextField[10];
        this.yjc = new Panel();
        this.zjc = new Panel();
        this.Ajc = new Panel();
        this.setLayout(new FlowLayout());
        this.yjc.setLayout(new GridLayout(2, 1));
        this.sjc = new Label(if.Ea, 0);
        this.vjc = new Label(if.Ea, 0);
        this.yjc.add(this.vjc);
        this.yjc.add(this.sjc);
        this.zjc.setLayout(new GridLayout(1, 1));
        this.Da = new Button(if.Fa);
        this.zjc.add(if.Ga, this.Da);
        this.add(this.yjc);
        this.add(this.zjc);
        this.Za = 4;
    }
    
    public if(final String s, final interface djc) {
        super(s);
        this.tjc = new Label[10];
        this.ujc = new Label[10];
        this.xjc = new TextField[10];
        this.yjc = new Panel();
        this.zjc = new Panel();
        this.Ajc = new Panel();
        this.setLayout(new BorderLayout());
        this.Djc = djc;
        this.yjc.setLayout(new GridLayout(2, 1));
        this.sjc = new Label(if.Ea);
        this.zjc.setLayout(new FlowLayout());
        this.vjc = new Label(if.Ea);
        this.wjc = new TextField(10);
        this.zjc.add(this.vjc);
        this.zjc.add(this.wjc);
        this.yjc.add(this.sjc);
        this.yjc.add(this.zjc);
        this.Ajc.setLayout(new BorderLayout());
        this.Da = new Button(if.Fa);
        this.Ajc.add(if.Ha, this.Da);
        this.add(if.Ia, this.yjc);
        this.add(if.Ga, this.Ajc);
        this.Za = 1;
    }
    
    public if(final String s, final interface djc, final boolean b) {
        super(s);
        this.tjc = new Label[10];
        this.ujc = new Label[10];
        this.xjc = new TextField[10];
        this.yjc = new Panel();
        this.zjc = new Panel();
        this.Ajc = new Panel();
        this.Djc = djc;
        if (b) {
            this.setLayout(new BorderLayout());
            this.yjc.setLayout(new GridLayout(2, 1));
            this.sjc = new Label(if.Ea);
            this.zjc.setLayout(new FlowLayout());
            this.vjc = new Label(if.Ea);
            this.wjc = new TextField(10);
            this.zjc.add(this.vjc);
            this.zjc.add(this.wjc);
            this.yjc.add(this.sjc);
            this.yjc.add(this.zjc);
            this.Ajc.setLayout(new BorderLayout());
            this.Da = new Button(if.Fa);
            this.Ajc.add(if.Ha, this.Da);
            this.add(if.Ia, this.yjc);
            this.add(if.Ga, this.Ajc);
            this.Za = 2;
        }
    }
    
    public if(final String s, final interface[] ejc, final boolean b, final int fjc) {
        super(s);
        this.tjc = new Label[10];
        this.ujc = new Label[10];
        this.xjc = new TextField[10];
        this.yjc = new Panel();
        this.zjc = new Panel();
        this.Ajc = new Panel();
        this.Ejc = ejc;
        this.Fjc = fjc;
        if (b) {
            this.setLayout(new BorderLayout());
            this.yjc.setLayout(new GridLayout(2, 1));
            this.sjc = new Label(if.Ea);
            this.zjc.setLayout(new FlowLayout());
            for (int i = 0; i < this.Fjc; ++i) {
                this.tjc[i] = new Label(if.Ea);
                this.xjc[i] = new TextField(10);
                this.ujc[i] = new Label(if.Ea);
                this.zjc.add(this.tjc[i]);
                this.zjc.add(this.xjc[i]);
                this.zjc.add(this.ujc[i]);
            }
            this.yjc.add(this.sjc);
            this.yjc.add(this.zjc);
            this.Ajc.setLayout(new BorderLayout());
            this.Da = new Button(if.Fa);
            this.Ajc.add(if.Ha, this.Da);
            this.add(if.Ia, this.yjc);
            this.add(if.Ga, this.Ajc);
            this.Za = 5;
        }
    }
    
    public void _(final String bjc, final String cjc) {
        this.Bjc = bjc;
        this.Cjc = cjc;
        this.vjc.setText(this.Bjc);
        this.sjc.setText(this.Cjc);
    }
    
    public void a(final String bjc, final String cjc, final String text) {
        this.Bjc = bjc;
        this.Cjc = cjc;
        this.vjc.setText(this.Bjc);
        this.sjc.setText(this.Cjc);
        this.wjc.setText(text);
    }
    
    public void b(final String text, final interface[] array) {
        this.sjc.setText(text);
        for (int i = 0; i < this.Fjc; ++i) {
            final String text2 = new String(array[i]._());
            final String text3 = new String(array[i].g());
            final String text4 = new String(array[i].b().f());
            this.tjc[i].setText(text2);
            this.xjc[i].setText(text3);
            this.ujc[i].setText(text4);
        }
    }
    
    public void b(final interface interface1) {
        final String s = new String(interface1.h());
        final String text = new String(interface1.g());
        final String text2 = new String(interface1._());
        this.sjc.setText(if.Ja + s + if.Ka);
        this.vjc.setText(text2);
        this.wjc.setText(text);
    }
    
    public void _(final Font font) {
        this.sjc.setFont(font);
        this.vjc.setFont(font);
        this.Da.setFont(font);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.n();
            return true;
        }
        return false;
    }
    
    private void n() {
        this.hide();
        if (this.Za == 1 || this.Za == 2) {
            this.Djc.a(this.wjc.getText());
        }
        if (this.Za == 5) {
            for (int i = 0; i < this.Fjc; ++i) {
                this.Ejc[i].a(this.xjc[i].getText());
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            this.n();
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
            array[i] = (char)(s.charAt(i) ^ 0x10CD6);
        }
        return new String(array);
    }
    
    static {
        if.Ea = _(if.Ea);
        if.Fa = _(if.Fa);
        if.Ga = _(if.Ga);
        if.Ha = _(if.Ha);
        if.Ia = _(if.Ia);
        if.Ja = _(if.Ja);
        if.Ka = _(if.Ka);
    }
}
