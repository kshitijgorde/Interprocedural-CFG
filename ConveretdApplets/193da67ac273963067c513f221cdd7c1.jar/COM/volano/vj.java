// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ComponentListener;
import javax.swing.event.DocumentListener;
import java.awt.Insets;
import javax.swing.text.Caret;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class vj extends vk
{
    public JScrollPane a;
    public JTextArea b;
    
    public vj() {
        this.b = new JTextArea(10, 30);
        this.a = new JScrollPane(this.b, 22, 31);
        this.b.setLineWrap(true);
        this.b.setWrapStyleWord(true);
        this.b.setCaret(new vaw());
        this.b.setMargin(new Insets(0, 5, 0, 5));
        this.b.getDocument().addDocumentListener(new vax(this.b, 25000, 5000));
        this.b.addComponentListener(new vay(this));
        this.a.addComponentListener(new vay(this));
        this.setLayout(new BorderLayout());
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.a);
        this.add(panel);
        try {
            this.b.setFocusable(true);
        }
        catch (Throwable t) {}
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.a.setBackground(color);
        this.b.setBackground(color);
        this.a.getVerticalScrollBar().setBackground(color);
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.a.setForeground(color);
        this.b.setForeground(color);
        this.a.getVerticalScrollBar().setForeground(color);
    }
    
    public void setFont(final Font font) {
        this.b.setFont(font);
    }
    
    public boolean a() {
        return this.b.isEditable();
    }
    
    public void a(final boolean editable) {
        this.b.setEditable(editable);
    }
    
    public void a(final String s) {
        SwingUtilities.invokeLater(new vba(this, s));
    }
}
