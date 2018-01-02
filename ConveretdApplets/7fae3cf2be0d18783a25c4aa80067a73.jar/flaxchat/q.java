// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import flaxchat.c.f;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import flaxchat.a.h;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import flaxchat.a.e;
import java.awt.MenuItem;
import flaxchat.d.a;
import flaxchat.d.b;
import flaxchat.h.c;
import java.awt.Dimension;
import flaxchat.g.k;
import flaxchat.a.r;
import java.awt.PopupMenu;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.TextArea;

public final class q extends TextArea implements KeyListener, MouseListener, TextListener, Runnable, ActionListener, FocusListener
{
    private n a;
    private boolean b;
    private boolean c;
    private PopupMenu d;
    private PopupMenu e;
    private PopupMenu f;
    private r g;
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
    
    public q(final n a) {
        final int w = flaxchat.n.w;
        super("", 1, 0, 3);
        this.f = new PopupMenu();
        this.g = new r(this);
        this.i = new Dimension(0, 22);
        this.n = false;
        this.o = new c();
        this.p = 2;
        this.a = a;
        this.setFont(flaxchat.d.b.d(q.z[7]));
        this.g = new r(this);
        this.h = new k(a);
        this.b = flaxchat.d.a.a(q.z[9], false);
        this.addMouseListener(this);
        this.addKeyListener(this);
        a.h().a(a.l().a(q.z[5], 512));
        this.p = flaxchat.d.a.a(q.z[8], 10);
        (this.d = new PopupMenu()).add(new MenuItem(q.z[6]));
        this.add(this.d);
        this.addTextListener(this);
        if (w != 0) {
            int c = flaxchat.a.e.c;
            flaxchat.a.e.c = ++c;
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
        if (!flaxchat.d.b.a(q.z[10], false)) {
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
        if (!flaxchat.d.b.a(q.z[10], false)) {
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
        if (flaxchat.a.h.a(mouseEvent)) {
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
        this.c();
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
        this.a(keyEvent);
        if (keyEvent.getKeyCode() == 525 && !this.b) {
            keyEvent.consume();
            this.requestFocus();
            this.setEnabled(false);
            this.a.l.a(q.z[4]);
            return;
        }
        if (keyEvent.getKeyCode() == 75 && keyEvent.isControlDown()) {
            keyEvent.consume();
            this.a();
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
            Label_0226: {
                if (a != -1) {
                    this.o.c(a);
                    this.o.b(trim);
                    if (flaxchat.n.w == 0) {
                        break Label_0226;
                    }
                }
                if (this.o.a() + 1 > this.p) {
                    this.o.c(0);
                }
                this.o.c(trim);
            }
            this.d();
        }
        else if (keyEvent.getKeyCode() == 40) {
            if (!(this.a.e() instanceof flaxchat.c.c)) {
                return;
            }
            this.b();
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
    
    private void a() {
        final flaxchat.c.a e = this.a.e();
        Label_0046: {
            if (e instanceof flaxchat.c.c) {
                ((flaxchat.c.c)e).h();
                if (flaxchat.n.w == 0) {
                    break Label_0046;
                }
            }
            if (e instanceof flaxchat.c.e) {
                ((flaxchat.c.e)e).g();
            }
        }
        final String text = this.a.g().getText();
        if (text.length() == 0) {
            this.a.a('\u0003');
            return;
        }
        final char char1 = text.charAt(text.length() - 1);
        if (char1 == '\u0003' || char1 == '\u0003') {
            return;
        }
        this.a.a('\u0003');
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (!this.a.l().a(q.z[11], false)) {
            return;
        }
        if (this.getText().length() < this.a.h().k()) {
            return;
        }
        if (this.b(keyEvent)) {
            return;
        }
        keyEvent.consume();
        flaxchat.a.h.a();
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
                if (flaxchat.n.w == 0) {
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
        if (actionCommand.startsWith(q.z[2])) {
            this.o.d();
            return;
        }
        if (actionCommand.startsWith(q.z[3])) {
            try {
                final int int1 = Integer.parseInt(actionCommand.substring(2));
                final String text = (String)this.o.d(int1);
                this.o.c(int1);
                this.o.c(text);
                this.setText(text);
                this.h.actionPerformed(new ActionEvent(this, 1001, q.z[0]));
                return;
            }
            catch (Exception ex) {
                return;
            }
        }
        if (actionCommand.startsWith(q.z[1])) {
            final String substring = actionCommand.substring(2);
            final int index = substring.indexOf(",");
            final int int2 = Integer.parseInt(substring.substring(0, index));
            final String substring2 = substring.substring(index + 1);
            final int index2 = substring2.indexOf(",");
            this.a(int2, Integer.parseInt(substring2.substring(0, index2)), substring2.substring(index2 + 1));
        }
    }
    
    protected void b() {
        final int w = flaxchat.n.w;
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
                if (w == 0) {
                    break Label_0187;
                }
                final String s = (String)g.d(n);
                final MenuItem menuItem = new MenuItem(s);
                menuItem.setActionCommand(q.z[1] + a + "," + "" + caretPosition + "," + s);
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
        final int w = n.w;
        int n2 = n;
        while (true) {
            Label_0040: {
                if (w == 0) {
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
    
    private void c() {
        final flaxchat.c.a e = this.a.e();
        f f = null;
        if (e instanceof flaxchat.c.c) {
            f = ((flaxchat.c.c)e).n();
        }
        else if (e instanceof flaxchat.c.e) {
            f = ((flaxchat.c.e)e).n();
        }
        if (f != null) {
            f.a();
        }
    }
    
    public void d() {
        this.h.actionPerformed(new ActionEvent(this, 1001, q.z[0]));
    }
    
    static {
        q.z = new String[] { z(z("\tpK")), z(z("Y'")), z(z("\tqJH-")), z(z("['")), z(z("!tC@+J\\L")), z(z("\u0007|Wj7\u000bo")), z(z(",qNQ\u001c\u0002|[")), z(z("\u000bm_o0\u0004i")), z(z("\u0007|Wd,\rUFZ+\u0005oV")), z(z("\u000bqCF(:|\\]:")), z(z(">d_@1\rP\\N")), z(z("\u000erAF++qCF(%kJ[\u0012\u000be")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '_';
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
                    c2 = 'j';
                    break;
                }
                case 1: {
                    c2 = '\u001d';
                    break;
                }
                case 2: {
                    c2 = '/';
                    break;
                }
                case 3: {
                    c2 = ')';
                    break;
                }
                default: {
                    c2 = '_';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
