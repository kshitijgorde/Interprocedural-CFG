// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Container;
import wordle.a.b;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import java.awt.event.FocusListener;
import javax.swing.border.Border;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.JDialog;

public final class m extends JDialog
{
    private static final CompoundBorder a;
    private final JLabel b;
    private final JTextField c;
    private final JLabel d;
    private final JTextField e;
    private final JLabel f;
    private final JTextArea g;
    private final JButton h;
    private final JButton i;
    private final JLabel j;
    private volatile boolean k;
    
    static {
        a = BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }
    
    private static JLabel b(final String s) {
        final JLabel label;
        (label = new JLabel(s)).setFont(label.getFont().deriveFont(12.0f));
        return label;
    }
    
    private static JTextField a(final String s, final int n) {
        final JTextField textField;
        ((AbstractDocument)(textField = new JTextField(s, 30)).getDocument()).setDocumentFilter(new V(n));
        textField.setFont(textField.getFont().deriveFont(14.0f));
        textField.setBorder(m.a);
        textField.addFocusListener(new T(textField));
        return textField;
    }
    
    public m(final WordleApplet wordleApplet) {
        super((Frame)SwingUtilities.getAncestorOfClass(Frame.class, wordleApplet), "Save This Wordle", true);
        this.b = b("Title");
        this.c = a("Untitled", 80);
        this.d = b("Username");
        this.e = a("Anonymous", 60);
        this.f = b("<html>Comment<br><i>optional</i></html>");
        final JTextArea g;
        (g = new JTextArea(4, 30)).setFont(new Font("Dialog", 0, 14));
        g.setLineWrap(true);
        g.setWrapStyleWord(true);
        g.setBorder(m.a);
        g.setEnabled(true);
        g.setEditable(true);
        this.g = g;
        this.h = new JButton("OK");
        this.i = new JButton("Cancel");
        this.j = b("<html>When you save a Wordle, it is viewable by the entire internet, and<br/>there is no way to delete it. If there's any personal information<br/>in your Wordle, think twice about saving it.</html>");
        this.k = false;
        if (EventQueue.isDispatchThread()) {
            this.a(wordleApplet);
            return;
        }
        try {
            EventQueue.invokeAndWait(new ae(this, wordleApplet));
        }
        catch (Exception ex) {
            throw new RuntimeException();
        }
    }
    
    public final aa a() {
        this.setTitle("Save this Wordle");
        this.setVisible(true);
        this.dispose();
        if (this.k) {
            return aa.a;
        }
        return aa.b;
    }
    
    public final String getTitle() {
        return this.c.getText();
    }
    
    public final void a(final String text) {
        this.e.setText(text);
    }
    
    public final String b() {
        return this.e.getText();
    }
    
    public final String c() {
        return this.g.getText();
    }
    
    private void a(final WordleApplet locationRelativeTo) {
        final Container contentPane;
        (contentPane = this.getContentPane()).setLayout(new BorderLayout());
        final JPanel panel;
        (panel = new JPanel(new SpringLayout())).setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.add(new JLabel(""));
        panel.add(this.j);
        panel.add(this.b);
        panel.add(this.c);
        this.b.setLabelFor(this.c);
        panel.add(this.d);
        panel.add(this.e);
        this.b.setLabelFor(this.e);
        panel.add(this.f);
        panel.add(this.g);
        this.f.setLabelFor(this.g);
        wordle.a.b.a(panel, 4, 2, 0, 0, 6, 6);
        contentPane.add(panel);
        final JPanel panel2;
        (panel2 = new JPanel()).setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 8));
        panel2.setLayout(new BoxLayout(panel2, 0));
        panel2.add(Box.createHorizontalGlue());
        panel2.add(this.h);
        panel2.add(Box.createHorizontalStrut(8));
        panel2.add(this.i);
        contentPane.add(panel2, "South");
        this.h.addActionListener(new af(this));
        this.i.addActionListener(new ag(this));
        this.c.selectAll();
        this.getRootPane().setDefaultButton(this.h);
        this.pack();
        this.setLocationRelativeTo(locationRelativeTo);
    }
}
