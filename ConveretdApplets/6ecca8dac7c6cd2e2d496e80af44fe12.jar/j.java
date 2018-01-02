import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends Dialog
{
    bf a;
    String b;
    boolean c;
    TextField d;
    Label e;
    v f;
    bu g;
    boolean h;
    
    void a(final bf a) {
        this.a = a;
    }
    
    j(final String s, final String s2, final String b, final boolean b2) {
        super(new Frame(), s, true);
        this.d = new TextField(80);
        this.f = null;
        this.b = b;
        this.setLayout(new GridLayout(2, 1));
        this.add(this.e = new Label(s2));
        this.add(this.d);
        this.setBounds(200, 200, 300, 100);
    }
    
    public boolean a() {
        return this.h;
    }
    
    public boolean action(final Event event, final Object o) {
        final String text = this.d.getText();
        if (this.c) {
            text.concat("\u0001");
        }
        if (this.b != null && !text.equals("")) {
            this.a.a(this.b + text + c("3,"));
        }
        if (this.f != null) {
            this.f.a(c("9\u0006") + this.g.a() + " " + text, bn.p, true);
        }
        this.h = false;
        this.hide();
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.h = true;
                this.hide();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    synchronized void a(final String text) {
        this.e.setText(text);
    }
    
    void a(final v f, final bu g) {
        this.f = f;
        this.g = g;
    }
    
    void b(final String text) {
        this.d.setText(text);
    }
    
    String b() {
        return this.d.getText();
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0013';
                    break;
                }
                case 1: {
                    c2 = '&';
                    break;
                }
                case 2: {
                    c2 = '&';
                    break;
                }
                case 3: {
                    c2 = '5';
                    break;
                }
                default: {
                    c2 = ')';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
