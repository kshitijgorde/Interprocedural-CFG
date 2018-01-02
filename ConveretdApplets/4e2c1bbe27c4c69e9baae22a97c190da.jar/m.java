import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends Dialog implements ActionListener
{
    public String a;
    public Button b;
    public Button c;
    public TextField d;
    public TextField e;
    
    public m(final Frame frame, final String s) {
        super(frame, s, true);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        this.d = new TextField(40);
        panel.setLayout(new FlowLayout(2));
        panel.add(new Label(zkmToString("c\u000f\b^\u001cW\u0011\b\fH")));
        panel.add(this.d);
        this.add(zkmToString("x\u0013\u001fX\u001a"), panel);
        (this.e = new TextField(40)).setEchoChar('*');
        panel2.setLayout(new FlowLayout(2));
        panel2.add(new Label(zkmToString("f\u001d\u001e_\u0005Y\u000e\t\fH")));
        panel2.add(this.e);
        this.add(zkmToString("u\u0019\u0003X\u0017D"), panel2);
        panel3.setLayout(new FlowLayout(2));
        panel3.add(this.b = new Button(zkmToString("y7")));
        this.b.addActionListener(this);
        panel3.add(this.c = new Button(zkmToString("u\u001d\u0003O\u0017Z")));
        this.c.addActionListener(this);
        this.add(zkmToString("e\u0013\u0018X\u001a"), panel3);
        this.setResizable(false);
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Dimension size = this.getSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.b) {
            this.a = this.a(this.d.getText() + ":" + this.e.getText());
            this.setVisible(false);
        }
        else if (actionEvent.getSource() == this.c) {
            this.a = null;
            this.setVisible(false);
        }
    }
    
    public String a(final String s) {
        final byte[] array = new byte[s.length() + 2];
        s.getBytes(0, s.length(), array, 0);
        final byte[] array2 = new byte[array.length / 3 * 4];
        for (int i = 0, n = 0; i < s.length(); i += 3, n += 4) {
            array2[n] = (byte)(array[i] >>> 2 & 0x3F);
            array2[n + 1] = (byte)((array[i + 1] >>> 4 & 0x3F) | (array[i] << 4 & 0x3F));
            array2[n + 2] = (byte)((array[i + 2] >>> 6 & 0x3F) | (array[i + 1] << 2 & 0x3F));
            array2[n + 3] = (byte)(array[i + 2] & 0x3F);
        }
        for (int j = 0; j < array2.length; ++j) {
            if (array2[j] < 26) {
                array2[j] += 65;
            }
            else if (array2[j] < 52) {
                array2[j] = (byte)(array2[j] + 97 - 26);
            }
            else if (array2[j] < 62) {
                array2[j] = (byte)(array2[j] + 48 - 52);
            }
            else if (array2[j] < 63) {
                array2[j] = 43;
            }
            else {
                array2[j] = 47;
            }
        }
        for (int k = array2.length - 1; k > s.length() * 4 / 3; --k) {
            array2[k] = 61;
        }
        return new String(array2, 0);
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '6';
                    break;
                }
                case 1: {
                    c2 = '|';
                    break;
                }
                case 2: {
                    c2 = 'm';
                    break;
                }
                case 3: {
                    c2 = ',';
                    break;
                }
                default: {
                    c2 = 'r';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
