import java.awt.Event;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.List;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class i extends Dialog
{
    bq a;
    GridBagLayout b;
    GridBagConstraints c;
    List d;
    TextField e;
    Button f;
    Button g;
    Button h;
    Frame i;
    h j;
    
    void a() {
        this.d.removeAll();
        int n = 0;
        while (true) {
            Label_0034: {
                if (!bm.dX) {
                    break Label_0034;
                }
                this.d.add(this.a.c[n]);
                ++n;
            }
            if (n == this.a.b) {
                return;
            }
            continue;
        }
    }
    
    i(final Frame i, final boolean b, final bq a) {
        super(i, bm.cG, b);
        this.b = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.d = new List(10, false);
        this.e = new TextField(10);
        this.f = new Button(bm.cM);
        this.g = new Button(bm.cN);
        this.h = new Button(bm.cO);
        this.i = i;
        this.a = a;
        this.j = new h(i, bm.dn);
        this.setLayout(this.b);
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.gridwidth = 10;
        this.c.gridheight = 5;
        this.b.setConstraints(this.d, this.c);
        this.add(this.d);
        this.c.gridwidth = 5;
        this.c.gridheight = 1;
        this.c.gridx = 10;
        this.c.gridy = 0;
        final Label label = new Label(a("]1\u007f)\u0004"));
        this.b.setConstraints(label, this.c);
        this.add(label);
        this.c.gridx = 15;
        this.c.gridy = 0;
        this.c.gridwidth = 0;
        this.c.gridheight = 1;
        this.b.setConstraints(this.e, this.c);
        this.add(this.e);
        this.c.gridwidth = 0;
        this.c.gridheight = 1;
        this.c.gridx = 10;
        this.c.gridy = 2;
        final Label label2 = new Label(a("]1\u007f)\u0004]"));
        this.b.setConstraints(label2, this.c);
        this.add(label2);
        this.c.gridwidth = 0;
        this.c.gridheight = 1;
        this.c.gridx = 15;
        this.c.gridy = 3;
        this.b.setConstraints(this.f, this.c);
        this.add(this.f);
        this.c.gridx = 15;
        this.c.gridy = 4;
        this.b.setConstraints(this.g, this.c);
        this.add(this.g);
        this.c.gridx = 15;
        this.c.gridy = 6;
        this.b.setConstraints(this.h, this.c);
        this.add(this.h);
        this.resize(350, 270);
    }
    
    public boolean action(final Event event, final Object o) {
        final boolean b = event.target instanceof TextField;
        if (event.target == this.d) {
            this.e.setText(((List)event.target).getSelectedItem());
        }
        if (event.target instanceof Button || b) {
            if (o.equals(bm.cM) || b) {
                final String text = this.e.getText();
                Label_0126: {
                    if (text.length() != 0) {
                        final String c = this.a.c(text);
                        if (!c.equals("")) {
                            this.j.a(c);
                            if (!bm.dX) {
                                break Label_0126;
                            }
                        }
                        this.d.add(text);
                    }
                }
                this.e.setText("");
            }
            if (o.equals(bm.cN)) {
                final String text2 = this.e.getText();
                this.a.d(text2);
                this.d.remove(text2);
                this.e.setText("");
            }
            if (o.equals(bm.cO)) {
                this.hide();
            }
        }
        return true;
    }
    
    void b() {
        this.a();
        this.show(true);
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
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '}';
                    break;
                }
                case 1: {
                    c2 = '\u0011';
                    break;
                }
                case 2: {
                    c2 = '_';
                    break;
                }
                case 3: {
                    c2 = '\t';
                    break;
                }
                default: {
                    c2 = '$';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}