// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import java.awt.event.FocusListener;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;

public final class g extends JPanel
{
    private final JTextField a;
    private final f b;
    private Color c;
    
    public g() {
        this.a = new JTextField(7);
        this.b = new f(this);
        this.c = null;
        this.setLayout(new BorderLayout(4, 4));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.add(this.b);
        this.add(this.a, "South");
        this.b.addMouseListener(new c(this));
        this.b.setToolTipText("Click to pick a color...");
        this.a(Color.GRAY);
        this.a.getDocument().addDocumentListener(new e(this));
        this.a.addFocusListener(new d(this));
    }
    
    public final void a(final Color c) {
        this.c = c;
        this.b();
        this.repaint();
    }
    
    private void b() {
        String s;
        for (s = Integer.toHexString(this.c.getRGB() & 0xFFFFFF); s.length() < 6; s = "0" + s) {}
        this.a.setText("#" + s);
    }
    
    public final Color a() {
        return this.c;
    }
}
