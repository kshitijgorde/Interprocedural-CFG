// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.ActionListener;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import wordle.a.i;
import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JDialog;

public final class j extends JDialog
{
    private final JTable a;
    private final JButton b;
    
    public j(final WordleApplet wordleApplet, final TableModel tableModel) {
        super((Frame)SwingUtilities.getAncestorOfClass(Frame.class, wordleApplet), "Word Counts", true);
        this.b = new JButton("Done");
        (this.a = new JTable(tableModel)).setFont(new Font("Monospaced", 0, 12));
        ((i)tableModel).a(this.a.getTableHeader());
        if (EventQueue.isDispatchThread()) {
            this.a(wordleApplet);
            return;
        }
        try {
            EventQueue.invokeAndWait(new H(this, wordleApplet));
        }
        catch (Exception ex) {
            throw new RuntimeException();
        }
    }
    
    private void a(final WordleApplet locationRelativeTo) {
        final Container contentPane;
        (contentPane = this.getContentPane()).setLayout(new BorderLayout());
        final JPanel panel;
        (panel = new JPanel(new BorderLayout())).setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.add(new JScrollPane(this.a));
        contentPane.add(panel);
        final JPanel panel2;
        (panel2 = new JPanel()).setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 8));
        panel2.setLayout(new BoxLayout(panel2, 0));
        panel2.add(Box.createHorizontalGlue());
        panel2.add(this.b);
        contentPane.add(panel2, "South");
        this.b.addActionListener(new G(this));
        this.getRootPane().setDefaultButton(this.b);
        this.pack();
        this.setLocationRelativeTo(locationRelativeTo);
    }
}
