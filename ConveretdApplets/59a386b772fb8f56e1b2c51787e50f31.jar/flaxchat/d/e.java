// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import java.awt.event.FocusEvent;
import flaxchat.e.g;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.event.KeyEvent;
import flaxchat.e.c;
import flaxchat.i.d;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import flaxchat.i.b;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class e extends Panel implements MouseListener, FocusListener, KeyListener
{
    protected String a;
    protected Image b;
    protected ActionListener c;
    protected Dimension d;
    protected String e;
    protected int f;
    protected boolean g;
    protected boolean h;
    protected boolean i;
    private boolean j;
    Color k;
    private Hashtable l;
    private static String z;
    
    public e(final String s) {
        this(s, s);
    }
    
    public e(final String s, final String s2) {
        this(s, s2, null);
    }
    
    public e(final String e, final String a, final Image b) {
        this.f = 0;
        this.j = false;
        this.l = new Hashtable();
        this.e = e;
        this.a = a;
        this.b = b;
        this.setFont(flaxchat.i.b.d(flaxchat.d.e.z));
        this.a();
        this.addMouseListener(this);
        this.addFocusListener(this);
        this.addKeyListener(this);
    }
    
    public boolean isFocusTraversable() {
        return this.j;
    }
    
    public void a(final boolean j) {
        this.j = j;
    }
    
    protected void a() {
        this.d = new Dimension(10, 10);
        if (this.b != null) {
            final int n = (this.a == null) ? 0 : (this.c() + 13);
            final int n2 = (this.a == null) ? 0 : 8;
            final Dimension d = this.d;
            d.width += this.b.getWidth(this) + n;
            this.d.height = Math.max(this.d.height, this.b.getHeight(this)) + n2;
            return;
        }
        if (this.a == null) {
            return;
        }
        final Dimension d2 = this.d;
        d2.width += this.c() + 10;
        this.d.height = Math.max(this.d.height, this.getFont().getSize()) + 10;
    }
    
    public Dimension getPreferredSize() {
        if (this.d == null) {
            return super.getPreferredSize();
        }
        return this.d;
    }
    
    public void a(final ActionListener c) {
        this.c = c;
    }
    
    protected void b() {
        if (this.c == null) {
            return;
        }
        this.c.actionPerformed(new ActionEvent(this, 1001, this.e));
    }
    
    private void a(final Graphics graphics) {
        this.k = graphics.getColor();
    }
    
    private void b(final Graphics graphics) {
        graphics.setColor(this.k);
    }
    
    private void a(final Graphics graphics, final Dimension dimension, final Image image, final Color color) {
        if (image == null) {
            this.a(graphics);
            graphics.setColor(color);
            graphics.fillRoundRect(1, 1, dimension.width, dimension.height, 10, 10);
            this.b(graphics);
            return;
        }
        final int width = image.getWidth(this);
        final int n = image.getWidth(this) / 3;
        final int height = image.getHeight(this);
        final int width2 = dimension.width;
        final int height2 = dimension.height;
        try {
            graphics.drawImage(image, 0, 0, n, height2, 0, 0, n, height, this);
            graphics.drawImage(image, width2 - n, 0, width2, height2, width - n, 0, width, height, this);
            graphics.drawImage(image, n, 0, width2 - n, height2, n, 0, width - n, height, this);
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean g = flaxchat.d.i.g;
        final Dimension size = this.getSize();
        Label_0085: {
            if (this.g) {
                this.a(graphics, size, flaxchat.i.d.e(), this.h ? Color.gray : this.getBackground());
                if (!g) {
                    break Label_0085;
                }
            }
            if (this.h) {
                this.a(graphics, size, flaxchat.i.d.e(), Color.gray);
                if (!g) {
                    break Label_0085;
                }
            }
            this.a(graphics, size, flaxchat.i.d.d(), this.getBackground());
        }
        int n = 0;
        int width = 0;
        int c = 0;
        if (this.b != null) {
            width = this.b.getWidth(this);
            n += width + 2;
        }
        if (this.a != null) {
            c = this.c();
            n += c;
        }
        int b = 0;
        Label_0157: {
            if (this.f == 0) {
                b = this.b(n);
                if (!g) {
                    break Label_0157;
                }
            }
            b = 3;
        }
        this.a(graphics, b);
        this.a(graphics, b + width + 2, c);
        if (!this.i) {
            return;
        }
        final int n2 = 3;
        flaxchat.e.c.b(Color.black, graphics, n2, n2, size.width - n2 * 2, size.height - n2 * 2);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10 || keyEvent.getKeyCode() == 32) {
            keyEvent.consume();
            this.h = true;
            this.repaint();
            if (this.c != null) {
                this.c.actionPerformed(new ActionEvent(this, 1001, this.e, keyEvent.getModifiers()));
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 10) {
            return;
        }
        keyEvent.consume();
        this.h = false;
        this.repaint();
    }
    
    private int c() {
        final char[] charArray = this.a.toCharArray();
        try {
            return this.getFontMetrics(this.getFont()).charsWidth(charArray, 0, charArray.length);
        }
        catch (Exception ex) {
            return charArray.length * 5;
        }
    }
    
    public int a(final int n) {
        return this.getSize().height / 2 - n / 2;
    }
    
    public int b(final int n) {
        return this.getSize().width / 2 - n / 2;
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        if (this.a == null) {
            return;
        }
        final Color color = graphics.getColor();
        graphics.setColor(this.getForeground());
        if (this.h && this.d()) {
            final Font font = graphics.getFont();
            graphics.setFont(new Font(font.getName(), 1, font.getSize()));
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(this.a, n, this.a(fontMetrics.getHeight()) + fontMetrics.getAscent());
        graphics.setColor(color);
    }
    
    public boolean d() {
        return false;
    }
    
    public void a(final Graphics graphics, final int n) {
        if (this.b == null) {
            return;
        }
        graphics.drawImage(this.b, n, this.a(this.b.getHeight(this)), this.b.getWidth(this), this.b.getHeight(this), this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!flaxchat.e.g.b(mouseEvent)) {
            return;
        }
        this.h = false;
        this.g = false;
        this.repaint();
        this.b();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.g = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.g = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.b(mouseEvent)) {
            this.h = true;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.b(mouseEvent)) {
            this.h = false;
            this.repaint();
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.i = true;
        this.repaint();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.i = false;
        this.repaint();
    }
    
    public void a(final Object o, final Object o2) {
        this.l.put(o, o2);
    }
    
    public Object a(final Object o) {
        return this.l.get(o);
    }
    
    public String e() {
        return this.e;
    }
    
    public String f() {
        return this.a;
    }
    
    public void a(final String a) {
        this.a = a;
        this.a();
        this.repaint();
    }
    
    public void b(final String e) {
        this.e = e;
        this.repaint();
    }
    
    public void c(final int f) {
        this.f = f;
    }
    
    public void c(final String e) {
        this.e = e;
    }
    
    public String g() {
        return this.e;
    }
    
    static {
        e.z = z(z("fL3\nQj\u007f(\u0010J"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '>';
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
                    c2 = '\u0004';
                    break;
                }
                case 1: {
                    c2 = '9';
                    break;
                }
                case 2: {
                    c2 = 'G';
                    break;
                }
                case 3: {
                    c2 = '~';
                    break;
                }
                default: {
                    c2 = '>';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
