import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends Dialog
{
    Label a;
    
    h(final Frame frame, final String s) {
        super(frame, s, true);
        this.a = new Label();
        this.setLayout(new GridLayout(2, 1));
        this.add(this.a);
        this.add(new Button(b("H4")));
        this.resize(300, 120);
    }
    
    public boolean action(final Event event, final Object o) {
        this.hide();
        return true;
    }
    
    void a(final String text) {
        this.a.setText(text);
        this.show(true);
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
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = '\u007f';
                    break;
                }
                case 2: {
                    c2 = '4';
                    break;
                }
                case 3: {
                    c2 = '`';
                    break;
                }
                default: {
                    c2 = '\u0010';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
