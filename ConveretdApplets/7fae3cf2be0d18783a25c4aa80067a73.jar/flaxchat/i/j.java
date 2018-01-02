// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.awt.event.MouseEvent;
import java.awt.Image;
import flaxchat.d.d;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import flaxchat.e.c;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class j extends i implements ActionListener, MouseListener
{
    private static String z;
    
    public j() {
        this(true);
    }
    
    public j(final boolean b) {
        super(b);
        this.setLayout(new c(1, 3));
        this.a();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    protected flaxchat.i.e a(final String name, final String s, final String s2) {
        final flaxchat.i.e e = new flaxchat.i.e(name, flaxchat.d.c.a(s), flaxchat.d.d.f(s2));
        e.addMouseListener(this);
        e.setName(name);
        return e;
    }
    
    protected Image d() {
        return flaxchat.d.d.f(j.z);
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.a(((flaxchat.i.e)mouseEvent.getSource()).m());
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.a((String)null);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void a() {
    }
    
    static {
        j.z = z(z("^\u001b\rd]E\u0018\u001fq@"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '2';
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
                    c2 = '*';
                    break;
                }
                case 1: {
                    c2 = 't';
                    break;
                }
                case 2: {
                    c2 = '}';
                    break;
                }
                case 3: {
                    c2 = '\u0010';
                    break;
                }
                default: {
                    c2 = '2';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
