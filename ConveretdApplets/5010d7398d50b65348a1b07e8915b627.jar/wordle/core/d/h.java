// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.Frame;
import javax.swing.JDialog;
import wordle.core.b.a.e;
import wordle.core.b.a.d;
import wordle.core.b.a.c;
import javax.swing.event.ChangeEvent;
import javax.swing.border.Border;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

public final class h extends JPanel implements ChangeListener
{
    private final JSpinner a;
    private final g b;
    private final g[] c;
    private final JPanel d;
    
    private h(final Color color, final Color[] array) {
        this.b = new g();
        this.c = new g[5];
        this.setLayout(new BorderLayout(4, 4));
        final JPanel panel = new JPanel();
        this.add(panel, "North");
        panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        panel.setLayout(new BoxLayout(panel, 0));
        panel.add(new JLabel("Number of colors"));
        (this.a = new JSpinner(new SpinnerNumberModel(array.length, 1, 5, 1))).setMaximumSize(new Dimension(140, this.a.getMaximumSize().height));
        panel.add(this.a);
        panel.add(Box.createHorizontalGlue());
        this.a.addChangeListener(this);
        final JPanel panel2 = new JPanel();
        this.add(panel2);
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Background"), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        this.b.a(color);
        panel2.add(this.b);
        panel2.add(Box.createHorizontalStrut(4));
        (this.d = new JPanel()).setLayout(new BoxLayout(this.d, 0));
        this.d.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Foreground Colors"), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        panel2.add(this.d);
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = new g();
            if (i < array.length) {
                this.c[i].a(array[i]);
                this.d.add(this.c[i]);
            }
            else {
                this.c[i].a(Color.GRAY);
            }
        }
    }
    
    public final void stateChanged(final ChangeEvent changeEvent) {
        final int intValue;
        if ((intValue = ((Number)this.a.getValue()).intValue()) > this.d.getComponentCount()) {
            this.d.add(this.c[intValue - 1]);
        }
        else {
            this.d.remove(this.c[intValue]);
        }
        this.validate();
        this.firePropertyChange("I WIN", null, "WOO");
    }
    
    private c a() {
        final Color[] array = new Color[this.d.getComponentCount()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.c[i].a();
        }
        return new d(array, this.b.a());
    }
    
    public static c a(final e e) {
        final JDialog dialog = new JDialog((Frame)null, "Create a Custom Palette", true);
        final h h = new h(e.b(), e.a());
        final Container contentPane;
        (contentPane = dialog.getContentPane()).setLayout(new BorderLayout(4, 4));
        contentPane.add(h);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final b b = new b(atomicBoolean, dialog);
        final JButton button;
        (button = new JButton("Cancel")).addActionListener(b);
        final JButton defaultButton;
        (defaultButton = new JButton("OK")).setActionCommand("OK");
        defaultButton.addActionListener(b);
        dialog.getRootPane().setDefaultButton(defaultButton);
        final JPanel panel;
        (panel = new JPanel()).setLayout(new BoxLayout(panel, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        panel.add(Box.createHorizontalGlue());
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(defaultButton);
        contentPane.add(panel, "South");
        h.addPropertyChangeListener("I WIN", new a(dialog));
        dialog.pack();
        dialog.setVisible(true);
        if (atomicBoolean.get()) {
            return h.a();
        }
        return null;
    }
}
