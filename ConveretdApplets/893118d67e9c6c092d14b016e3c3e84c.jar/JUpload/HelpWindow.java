// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class HelpWindow extends JFrame
{
    HelpWindow(final String content) {
        this.setTitle("JUpload Help Page");
        this.setSize(400, 300);
        final JTextArea tf = new JTextArea(content);
        tf.setFont(new Font("Courier", 0, 12));
        final JScrollPane scroll = new JScrollPane(tf);
        this.getContentPane().add(scroll);
        this.setVisible(true);
        this.show();
    }
}
