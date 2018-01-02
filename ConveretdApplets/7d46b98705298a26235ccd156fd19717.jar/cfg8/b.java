// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.Frame;

class b extends Frame implements ActionListener
{
    final a a;
    private static String[] z;
    
    b(final a a, final String s, final String s2, final String s3, final String s4) {
        super(s);
        this.a = a;
        cfg8.a.c = true;
        this.setBackground(SystemColor.controlHighlight);
        this.setSize(450, 190);
        try {
            this.a();
        }
        catch (Exception ex) {}
        this.setLayout(null);
        final Button button = new Button(s3);
        button.setActionCommand(b.z[1]);
        button.addActionListener(this);
        button.setBounds((s4 != null) ? 150 : 195, 120, 70, 25);
        final TextArea textArea = new TextArea(s2, 1, 1, 1);
        textArea.setBackground(Color.white);
        textArea.setEditable(false);
        textArea.setBounds(5, 35, 440, 80);
        this.add(textArea);
        this.add(button);
        if (RotationImageFilter.a == 0) {
            if (s4 != null) {
                final Button button2 = new Button(s4);
                button2.setActionCommand(b.z[0]);
                button2.addActionListener(this);
                button2.setBounds(250, 120, 70, 25);
                this.add(button2);
            }
            this.enableEvents(201L);
            a.setEnabled(false);
        }
    }
    
    protected void a() {
        final Point locationOnScreen = this.a.getLocationOnScreen();
        final Dimension size = this.a.getSize();
        final Point point = locationOnScreen;
        point.x += size.width / 2 - 225;
        final Point point2 = locationOnScreen;
        point2.y += size.height / 2 - 75;
        this.setLocation(locationOnScreen);
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        final int a = RotationImageFilter.a;
        int id;
        final int n = id = windowEvent.getID();
        Object source = null;
        Label_0074: {
            final int id2;
            final int n2;
            Label_0067: {
                Label_0056: {
                    if (a == 0) {
                        if (n != 201) {
                            id2 = windowEvent.getID();
                            n2 = 203;
                            if (a != 0) {
                                break Label_0067;
                            }
                            if (id2 != n2) {
                                break Label_0056;
                            }
                        }
                        this.a.setEnabled(true);
                        this.dispose();
                        cfg8.a.d = false;
                        id = (false ? 1 : 0);
                    }
                    cfg8.a.c = (id != 0);
                    if (a == 0) {
                        return;
                    }
                }
                source = windowEvent;
                if (a != 0) {
                    break Label_0074;
                }
                windowEvent.getID();
            }
            if (id2 != n2) {
                return;
            }
            source = windowEvent.getSource();
        }
        if (source == this) {
            this.toFront();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int a = RotationImageFilter.a;
        final boolean equals = actionEvent.getActionCommand().equals(b.z[0]);
        if (a == 0 && equals) {
            cfg8.a.d = false;
            if (a != 0) {
                goto Label_0031;
            }
        }
        else {
            cfg8.a.d = equals;
        }
        cfg8.a.c = false;
        this.a.setEnabled(true);
        this.dispose();
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "U8R\u0006|Z".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0016';
                            break;
                        }
                        case 1: {
                            c2 = 'y';
                            break;
                        }
                        case 2: {
                            c2 = '\u001c';
                            break;
                        }
                        case 3: {
                            c2 = 'E';
                            break;
                        }
                        default: {
                            c2 = '9';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "Y2".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0016';
                            break;
                        }
                        case 1: {
                            c4 = 'y';
                            break;
                        }
                        case 2: {
                            c4 = '\u001c';
                            break;
                        }
                        case 3: {
                            c4 = 'E';
                            break;
                        }
                        default: {
                            c4 = '9';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                b.z = z;
                return;
            }
            continue;
        }
    }
}
