import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.Date;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends Frame
{
    TextField a;
    Button b;
    Button c;
    be d;
    bf e;
    long f;
    boolean g;
    
    void a(final bf e) {
        this.e = e;
    }
    
    void a(final String s) {
        this.g = true;
        this.e.a(b("9@\t=ZB") + s + "\n");
        this.d.d(s, true);
        this.f = new Date().getTime();
    }
    
    void a() {
        if (!this.g) {
            return;
        }
        this.g = false;
        String s = "";
        final long n = (new Date().getTime() - this.f) / 1000L;
        final long n2 = n / 3600L;
        final long n3 = n % 3600L;
        final long n4 = n3 / 60L;
        final long n5 = n3 % 60L;
        if (n2 != 0L) {
            s = n2 + bm.cw;
        }
        if (n4 != 0L) {
            s = s + n4 + bm.cx;
        }
        this.d.d(s + n5 + bm.cy, false);
    }
    
    n() {
        super(bm.cu);
        this.a = new TextField(80);
        this.b = new Button(b("7\\"));
        this.c = new Button(b(";v&\u0007\u001f\u0014"));
        this.g = false;
        this.setLayout(new GridLayout(3, 1));
        final Panel panel = new Panel();
        this.add(new Label(bm.cv));
        this.add(this.a);
        this.add(panel);
        panel.add(this.b);
        panel.add(this.c);
        this.resize(400, 200);
        this.a.requestFocus();
    }
    
    void a(final be d) {
        this.d = d;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.b || event.target == this.a) {
            final String text = this.a.getText();
            if (!text.equals("")) {
                this.a(text);
                this.hide();
            }
        }
        if (event.target == this.c) {
            this.hide();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.hide();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'x';
                    break;
                }
                case 1: {
                    c2 = '\u0017';
                    break;
                }
                case 2: {
                    c2 = 'H';
                    break;
                }
                case 3: {
                    c2 = 'd';
                    break;
                }
                default: {
                    c2 = 'z';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
