// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class af implements ActionListener
{
    private /* synthetic */ m a;
    
    af(final m a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        m.a(this.a, true);
        this.a.setVisible(false);
    }
}
