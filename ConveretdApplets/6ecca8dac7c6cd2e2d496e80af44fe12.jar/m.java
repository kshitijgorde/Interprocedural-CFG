import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends Frame implements ActionListener, WindowListener
{
    String a;
    TextArea b;
    Button c;
    
    m() {
        this.a = a("#Kf_L\taf_L\taf_L") + irc.j + " " + irc.a + " " + p.a + a("#KkRA\u0004lkRA\u0004lkRA\u0004lkRA\u0004lkRA\u0004lkRA#Kf_L\ta\u0016\u001e\u0018[(%\u0014Lm4%\r\u0003]K") + a("\taf_L\u001evf\r\u0019La\"\nLJ3#\n\u0014\t 3_\u001eL/'\r\b#af_L\tprO\\\u0019a\u0005>)gKf_L\ta\u0000--g\u0002\u0003uf") + a("\taf_LL,'\u0016\u0000\t{f\u000f\r]3/\u001c\u0007i%3\u001c\u001eF5h\u0010\u001eNK") + a("\taf_LA52\u000fV\u0006n1\b\u001b\u0007+'\t\u0005[\"h\u001c\u0003D");
        this.c = new Button(a("f\n"));
        this.b = new TextArea(this.a, 10, 20);
        this.add(a("j$(\u000b\t["), this.b);
        this.add(a("z.3\u000b\u0004"), this.c);
        this.setSize(508, 250);
        this.setTitle(a("h#)\n\u0018"));
        this.b.setEditable(false);
        this.c.addActionListener(this);
        this.addWindowListener(this);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            this.setVisible(false);
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
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
                    c2 = ')';
                    break;
                }
                case 1: {
                    c2 = 'A';
                    break;
                }
                case 2: {
                    c2 = 'F';
                    break;
                }
                case 3: {
                    c2 = '\u007f';
                    break;
                }
                default: {
                    c2 = 'l';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
