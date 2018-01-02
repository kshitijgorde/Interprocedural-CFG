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

final class H implements Runnable
{
    private /* synthetic */ j a;
    private final /* synthetic */ WordleApplet b;
    
    H(final j a, final WordleApplet b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        this.a.a(this.b);
    }
}
