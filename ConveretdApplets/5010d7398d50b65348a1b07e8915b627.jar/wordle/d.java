// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;

final class d implements Runnable
{
    private /* synthetic */ WordleApplet a;
    private final /* synthetic */ String b;
    
    d(final WordleApplet a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        System.err.println(this.b);
        this.a.getContentPane().removeAll();
        this.a.getContentPane().setLayout(new BorderLayout());
        this.a.getContentPane().setBackground(Color.WHITE);
        final JLabel label;
        (label = new JLabel("<html>" + this.b.replace("\n", "<br>") + "</html>")).setFont(new Font("serif", 0, 24));
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        this.a.getContentPane().add(label);
    }
}
