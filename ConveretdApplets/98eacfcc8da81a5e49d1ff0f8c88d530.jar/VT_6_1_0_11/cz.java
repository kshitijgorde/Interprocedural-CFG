// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ComponentListener;
import javax.swing.JLayeredPane;
import com.hw.client.util.a;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;

public class cz extends JDesktopPane
{
    private static Integer a;
    private static Integer b;
    private static Integer c;
    private JComponent d;
    private T e;
    private dE f;
    private JTextPane g;
    private aw h;
    private dk i;
    
    public cz() {
        this.h = new aw(this);
        this.i = new dk(this);
        (this.g = new aq()).setEditable(false);
        this.g.setBackground(VT_6_1_0_11.h.b());
        this.g.setFont(new Font("Dialog", 0, 10));
        this.h.a(this.g);
        this.add(this.g, cz.b);
        (this.f = new dE()).setVisible(false);
        this.a(this.f, cz.a);
        (this.e = new T(this)).a(true);
        this.e.setVisible(false);
        this.h.a(this.e);
        this.add(this.e, cz.c);
    }
    
    public final T E() {
        return this.e;
    }
    
    public final void f(final String text) {
        com.hw.client.util.a.d("ModalDesktopPane.setText: text:" + text);
        if (text == null) {
            this.g.setVisible(false);
            return;
        }
        this.g.setText(text);
        this.g.setVisible(true);
    }
    
    public final dE F() {
        return this.f;
    }
    
    public final void a(final JComponent d) {
        if (this.d != null) {
            this.h.b(this.d);
            this.remove(this.d);
        }
        this.d = d;
        if (this.d != null) {
            this.h.a(this.d);
            this.add(this.d, JLayeredPane.FRAME_CONTENT_LAYER);
        }
    }
    
    public final void a(final aM am, final int n) {
        final Component[] componentsInLayer;
        if ((componentsInLayer = this.getComponentsInLayer(n)).length > 0) {
            componentsInLayer[1].removeComponentListener(this.i);
            this.remove(componentsInLayer[1]);
        }
        if (componentsInLayer.length == 0) {
            this.h.a(am.e());
            final Integer n2 = new Integer(n);
            this.add(am.e(), n2);
            this.add(am, n2);
            am.addComponentListener(this.i);
            am.e().setVisible(am.isVisible());
        }
    }
    
    static {
        cz.a = new Integer(30);
        cz.b = new Integer(40);
        new Integer(10);
        new Integer(12);
        new Integer(14);
        new Integer(16);
        cz.c = new Integer(1000);
    }
}
