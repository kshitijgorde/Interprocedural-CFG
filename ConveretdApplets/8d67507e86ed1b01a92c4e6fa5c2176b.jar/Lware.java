import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Label;
import java.applet.AppletContext;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Lware extends Frame
{
    static final String a = "tm`\u0012536c\u0015x2xz\u0004vh|u\u000f!\u007fvy";
    private AppletContext b;
    
    public Lware(final AppletContext b, final Label label) {
        this.b = b;
        this.setFont(new Font(toString("O`g\u0016jq"), 1, 12));
        this.setLayout(new GridLayout(5, 1, 1, 2));
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        final Label label2 = new Label(toString("Luq\u0003|y9b\u000b|um4$n~p{BLulw\u0001f;j4\u0015j~9g\u000b{y9z\rx"));
        final Label label3 = new Label(toString("hv4\u0006`kwx\rnx9`\nj<uu\u0016jom4\u0003az``\u0007nq7w\rb<|r\u0004j\u007fmgC"));
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(new Button(toString("_vz\fj\u007fm5")));
        this.add(new Button(toString("_u{\u0011j")));
        this.pack();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (o.equals(toString("_u{\u0011j"))) {
            this.hide();
        }
        else if (o.equals(toString("_vz\fj\u007fm5"))) {
            this.hide();
            URL url = null;
            this.b.showStatus(toString("Ppz\tfr~4\u0015fhq4\u0003az``\u0007nq7w\rb"));
            try {
                url = new URL(toString("tm`\u0012536c\u0015x2xz\u0004vh|u\u000f!\u007fvy"));
            }
            catch (MalformedURLException ex) {
                this.b.showStatus(toString("Ykf\r}<u}\fduwsBxkn:\u0003az``\u0007nq7w\rb"));
            }
            this.b.showDocument(url, toString("C{x\u0003aw"));
        }
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id != 203 && event.id != 201) {
            return super.handleEvent(event);
        }
        this.hide();
        return true;
    }
    
    public final void show() {
        super.show();
    }
    
    private static String toString(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = '\u001c';
                char[] array2;
                char[] array = array2 = charArray;
                int n4;
                int n3 = n4 = n;
                while (true) {
                    array[n3] = (char)(c ^ array2[n4]);
                    Label_0047: {
                        Label_0039: {
                            Label_0029: {
                            Label_0021:
                                while (true) {
                                    ++n;
                                    ++n2;
                                    if (length == n) {
                                        break Label_0010;
                                    }
                                    switch (n2) {
                                        case 5: {
                                            continue Label_0010;
                                        }
                                        case 1: {
                                            break Label_0021;
                                        }
                                        case 2: {
                                            break Label_0029;
                                        }
                                        case 3: {
                                            break Label_0039;
                                        }
                                        case 4: {
                                            break Label_0047;
                                        }
                                        default: {
                                            continue;
                                        }
                                    }
                                }
                                c = '\u0019';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '\u0014';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'b';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '\u000f';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
