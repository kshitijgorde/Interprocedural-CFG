// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import flaxchat.c.f;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import flaxchat.e.g;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import flaxchat.i.b;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import flaxchat.e.e;
import java.awt.MenuItem;
import flaxchat.i.a;
import flaxchat.f.c;
import java.awt.Dimension;
import flaxchat.a.k;
import flaxchat.e.q;
import java.awt.PopupMenu;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.TextArea;

public final class p extends TextArea implements KeyListener, MouseListener, TextListener, Runnable, ActionListener, FocusListener
{
    private m a;
    private boolean b;
    private boolean c;
    private PopupMenu d;
    private PopupMenu e;
    private PopupMenu f;
    private q g;
    private k h;
    private Dimension i;
    private c j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private c o;
    private int p;
    private static String[] z;
    
    public p(final m a) {
        final boolean s = flaxchat.m.s;
        super("", 1, 0, 3);
        this.f = new PopupMenu();
        this.g = new q(this);
        this.i = new Dimension(0, 22);
        this.n = false;
        this.o = new c();
        this.p = 2;
        this.a = a;
        this.g = new q(this);
        this.h = new k(a);
        this.b = flaxchat.i.a.a(flaxchat.p.z[3], false);
        this.addMouseListener(this);
        this.addKeyListener(this);
        a.h().a(a.l().a(flaxchat.p.z[1], 512));
        this.p = flaxchat.i.a.a(flaxchat.p.z[4], 10);
        (this.d = new PopupMenu()).add(new MenuItem(flaxchat.p.z[2]));
        this.add(this.d);
        this.addTextListener(this);
        if (s) {
            flaxchat.e.e.c = !flaxchat.e.e.c;
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.c = true;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.c = false;
    }
    
    public Dimension getPreferredSize() {
        return this.i;
    }
    
    public void run() {
        if (this.n) {
            this.a((KeyEvent)null);
            return;
        }
        if (!flaxchat.i.b.a(flaxchat.p.z[6], false)) {
            return;
        }
        final flaxchat.c.a e = this.a.e();
        if (e == null) {
            return;
        }
        e.c(false);
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        final int length = this.getText().length();
        if (length == 0) {
            return;
        }
        if (length % 10 != 0) {
            return;
        }
        if (!flaxchat.i.b.a(flaxchat.p.z[6], false)) {
            return;
        }
        final flaxchat.c.a e = this.a.e();
        if (!(e instanceof flaxchat.c.e)) {
            return;
        }
        e.c(true);
        this.g.a();
    }
    
    private void a(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.a(mouseEvent)) {
            if (this.b) {
                return;
            }
            mouseEvent.consume();
        }
        else {
            if (this.getText().trim().length() != 0) {
                return;
            }
            if (this.c) {
                return;
            }
            mouseEvent.consume();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.requestFocus();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.b();
        this.a(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 525 && !this.b) {
            keyEvent.consume();
            this.requestFocus();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean s = flaxchat.m.s;
        this.a(keyEvent);
        if (keyEvent.getKeyCode() == 525 && !this.b) {
            keyEvent.consume();
            this.requestFocus();
            this.setEnabled(false);
            this.a.i.a(flaxchat.p.z[9]);
            return;
        }
        if (keyEvent.getKeyCode() == 75 && keyEvent.isControlDown()) {
            keyEvent.consume();
            final flaxchat.c.a e = this.a.e();
            Label_0122: {
                if (e instanceof flaxchat.c.c) {
                    ((flaxchat.c.c)e).i();
                    if (!s) {
                        break Label_0122;
                    }
                }
                if (e instanceof flaxchat.c.e) {
                    ((flaxchat.c.e)e).h();
                }
            }
            this.a.a('\u0003');
            return;
        }
        if (keyEvent.getKeyCode() == 69 && keyEvent.isControlDown()) {
            keyEvent.consume();
            this.a.a('\u000f');
            return;
        }
        if (keyEvent.getKeyCode() == 27) {
            keyEvent.consume();
            this.a.x();
            return;
        }
        if (keyEvent.getKeyCode() == 10) {
            keyEvent.consume();
            final String trim = this.getText().trim();
            if (trim.length() == 0) {
                return;
            }
            final int a = this.o.a(trim);
            Label_0280: {
                if (a != -1) {
                    this.o.c(a);
                    this.o.b(trim);
                    if (!s) {
                        break Label_0280;
                    }
                }
                if (this.o.a() + 1 > this.p) {
                    this.o.c(0);
                }
                this.o.c(trim);
            }
            this.c();
        }
        else if (keyEvent.getKeyCode() == 40) {
            if (!(this.a.e() instanceof flaxchat.c.c)) {
                return;
            }
            this.a();
            keyEvent.consume();
        }
        else {
            if (keyEvent.getKeyCode() == 38) {
                keyEvent.consume();
                return;
            }
            if (keyEvent.getKeyCode() == 9) {
                if (keyEvent.isShiftDown()) {
                    this.a.y();
                    keyEvent.consume();
                    return;
                }
                if (this.a.e() instanceof flaxchat.c.c) {
                    this.a(this.a.e());
                    keyEvent.consume();
                    return;
                }
                if (this.a.e() instanceof flaxchat.c.e) {
                    this.a(this.a.e());
                    keyEvent.consume();
                }
            }
            else {
                this.c(keyEvent);
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (!this.a.l().a(flaxchat.p.z[10], false)) {
            return;
        }
        if (this.getText().length() < this.a.h().k()) {
            return;
        }
        if (this.b(keyEvent)) {
            return;
        }
        keyEvent.consume();
        flaxchat.e.g.a();
    }
    
    protected void a(final flaxchat.c.a a) {
        if (this.j == null) {
            final int caretPosition = this.getCaretPosition();
            final StringBuffer sb = new StringBuffer(50);
            final int a2 = this.a(this.getText(), sb, caretPosition - 1);
            this.l = ((a2 == -1) ? 0 : a2);
            this.m = caretPosition;
            this.j = a.g(sb.toString());
            if (this.j.a() == 0) {
                return;
            }
            this.k = 0;
        }
        if (this.j.a() == 0) {
            this.a((KeyEvent)null);
            return;
        }
        if (this.k >= this.j.a()) {
            this.k = 0;
        }
        final String s = (String)this.j.d(this.k);
        Label_0216: {
            if (this.l == 0) {
                this.b(0, this.m, s);
                this.setCaretPosition(s.length());
                this.m = s.length();
                if (!flaxchat.m.s) {
                    break Label_0216;
                }
            }
            this.b(this.l + 1, this.m, s);
            this.setCaretPosition(this.m = this.l + s.length() + 1);
        }
        ++this.k;
        this.n = true;
        this.repaint();
        this.g.a();
    }
    
    private void a(final KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getKeyCode() == 9) {
            return;
        }
        this.j = null;
        this.n = false;
        this.k = -1;
        this.l = -1;
        this.m = -1;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.startsWith(flaxchat.p.z[7])) {
            this.o.d();
            return;
        }
        if (actionCommand.startsWith(flaxchat.p.z[8])) {
            try {
                final int int1 = Integer.parseInt(actionCommand.substring(2));
                final String text = (String)this.o.d(int1);
                this.o.c(int1);
                this.o.c(text);
                this.setText(text);
                this.h.actionPerformed(new ActionEvent(this, 1001, flaxchat.p.z[0]));
                return;
            }
            catch (Exception ex) {
                return;
            }
        }
        if (actionCommand.startsWith(flaxchat.p.z[5])) {
            final String substring = actionCommand.substring(2);
            final int index = substring.indexOf(",");
            final int int2 = Integer.parseInt(substring.substring(0, index));
            final String substring2 = substring.substring(index + 1);
            final int index2 = substring2.indexOf(",");
            this.a(int2, Integer.parseInt(substring2.substring(0, index2)), substring2.substring(index2 + 1));
        }
    }
    
    protected void a() {
        final boolean s = flaxchat.m.s;
        if (this.e == null) {
            this.add(this.e = new PopupMenu());
        }
        final flaxchat.c.c c = (flaxchat.c.c)this.a.e();
        final int caretPosition = this.getCaretPosition();
        final StringBuffer sb = new StringBuffer(50);
        final int a = this.a(this.getText(), sb, caretPosition - 1);
        final c g = c.g(sb.toString());
        this.e.removeAll();
        int n = 0;
        while (true) {
            Label_0187: {
                if (!s) {
                    break Label_0187;
                }
                final String s2 = (String)g.d(n);
                final MenuItem menuItem = new MenuItem(s2);
                menuItem.setActionCommand(flaxchat.p.z[5] + a + "," + "" + caretPosition + "," + s2);
                menuItem.addActionListener(this);
                this.e.add(menuItem);
                ++n;
            }
            if (n >= g.a()) {
                final Font font = this.getFont();
                int charsWidth = 0;
                final int width = this.getSize().width;
                if (caretPosition != 0) {
                    charsWidth = this.getFontMetrics(font).charsWidth(this.getText().toCharArray(), 0, caretPosition - 1);
                    if (charsWidth > width) {
                        charsWidth = width;
                    }
                }
                this.e.show(this, charsWidth, this.getSize().height);
                return;
            }
            continue;
        }
    }
    
    private void a(int n, final int n2, final String s) {
        final String text = this.getText();
        if (n < text.length()) {
            ++n;
        }
        this.setText(String.valueOf(text.substring(0, n)) + s + text.substring(n2));
        this.setCaretPosition(n + s.length() + 1);
    }
    
    private void b(final int n, final int n2, final String s) {
        final String text = this.getText();
        this.setText(String.valueOf(text.substring(0, n)) + s + text.substring(n2));
        this.setCaretPosition(n + s.length() + 1);
    }
    
    private int a(final String s, final StringBuffer sb, final int n) {
        final boolean s2 = flaxchat.m.s;
        int n2 = n;
        while (true) {
            Label_0040: {
                if (!s2) {
                    break Label_0040;
                }
                final char char1 = s.charAt(n2);
                if (Character.isWhitespace(char1)) {
                    return n2;
                }
                sb.insert(0, char1);
                --n2;
            }
            if (n2 > -1) {
                continue;
            }
            break;
        }
        return n2;
    }
    
    private boolean b(final KeyEvent keyEvent) {
        return this.a(keyEvent.getKeyChar());
    }
    
    private boolean a(final int n) {
        switch (n) {
            case 10: {
                return true;
            }
            case 127: {
                return true;
            }
            case 8: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private void c(final KeyEvent keyEvent) {
        if (this.b) {
            return;
        }
        if (keyEvent.getKeyCode() == 86 && keyEvent.isControlDown()) {
            keyEvent.consume();
            return;
        }
        if (keyEvent.getKeyCode() == 155 && keyEvent.isShiftDown()) {
            keyEvent.consume();
        }
    }
    
    private void b() {
        final flaxchat.c.a e = this.a.e();
        f f = null;
        if (e instanceof flaxchat.c.c) {
            f = ((flaxchat.c.c)e).o();
        }
        else if (e instanceof flaxchat.c.e) {
            f = ((flaxchat.c.e)e).j();
        }
        if (f != null) {
            f.b();
        }
    }
    
    public void c() {
        this.h.actionPerformed(new ActionEvent(this, 1001, flaxchat.p.z[0]));
    }
    
    static {
        p.z = new String[] { z(z("J\u0016m")), z(z("D\u001aq,<H\t")), z(z("o\u0017h\u0017\u0017A\u001a}")), z(z("H\u0017e\u0000#y\u001az\u001b1")), z(z("D\u001aq\"'N3`\u001c F\tp")), z(z("\u001aA")), z(z("}\u0002y\u0006:N6z\b")), z(z("J\u0017l\u000e&")), z(z("\u0018A")), z(z("b\u0012e\u0006 \t:j")), z(z("M\u0014g\u0000 h\u0017e\u0000#f\rl\u001d\u0019H\u0003")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'T';
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
                    c2 = ')';
                    break;
                }
                case 1: {
                    c2 = '{';
                    break;
                }
                case 2: {
                    c2 = '\t';
                    break;
                }
                case 3: {
                    c2 = 'o';
                    break;
                }
                default: {
                    c2 = 'T';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
