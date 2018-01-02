// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import java.awt.Container;
import java.awt.Component;
import flaxchat.f.d;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import flaxchat.a.h;
import java.awt.event.MouseEvent;
import java.awt.Color;
import flaxchat.i.g;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.TextArea;

public final class f extends TextArea implements MouseListener, KeyListener, ActionListener
{
    private a a;
    private g b;
    private static String[] z;
    
    public f(final a a, final g b) {
        super("", 4, 30, 1);
        this.a = a;
        this.b = b;
        this.setEditable(false);
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addKeyListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (h.b(mouseEvent)) {
            return;
        }
        this.a(mouseEvent, mouseEvent.getPoint());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27) {
            this.a();
            return;
        }
        if (keyEvent.getKeyCode() == 116) {
            this.setText(this.b.d().b());
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void a(final InputEvent inputEvent, final Point point) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (f.z[2].equals(actionCommand)) {
            this.selectAll();
            return;
        }
        if (f.z[1].equals(actionCommand)) {
            h.b(this.getSelectedText());
            this.a();
        }
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 525) {
            this.a(keyEvent, new Point(0, 0));
            return;
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void a(String b) {
        if (this.getParent() != null) {
            return;
        }
        if (b == null) {
            b = this.b.d().b();
        }
        this.setText(d.c(b));
        final Container l = this.a.l();
        l.remove(this.b);
        this.b();
        l.add(this, f.z[0]);
        this.b();
    }
    
    public void a() {
        if (this.getParent() == null) {
            return;
        }
        final Container l = this.a.l();
        l.remove(this);
        this.b();
        l.add(this.b, f.z[0]);
        this.b();
    }
    
    public void b() {
        final Container l = this.a.l();
        l.getParent().invalidate();
        l.getParent().validate();
    }
    
    static {
        f.z = new String[] { z(z("\tI=$z8")), z(z(")C#)")), z(z("9I?5|>m?<")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u001f';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'J';
                    break;
                }
                case 1: {
                    c2 = ',';
                    break;
                }
                case 2: {
                    c2 = 'S';
                    break;
                }
                case 3: {
                    c2 = 'P';
                    break;
                }
                default: {
                    c2 = '\u001f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
