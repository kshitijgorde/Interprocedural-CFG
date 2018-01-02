// 
// Decompiled by Procyon v0.5.30
// 

package a.a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class f extends JButton
{
    public f(final d d, final String s) {
        super(s);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                d.a();
            }
        });
    }
}
