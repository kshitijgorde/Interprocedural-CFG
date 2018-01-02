// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Component;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Frame;

public class Message extends Frame
{
    private transient TextArea _txtarea;
    
    public Message(final String msg) {
        this(msg, null, null, 12, 40, 3);
    }
    
    public Message(final String msg, final Color background, final Color foreground, final int rows, final int columns, final int scrollbars) {
        if (background != null) {
            this.setBackground(background);
        }
        if (foreground != null) {
            this.setForeground(foreground);
        }
        (this._txtarea = new TextArea(msg, rows, columns, scrollbars)).setEditable(false);
        this.add("Center", this._txtarea);
        final Button button = new Button("Close");
        final Panel panel = new Panel();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                Message.this.dispose();
            }
        });
        panel.add(button);
        this.add("South", panel);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                Message.this.dispose();
            }
        });
        this.pack();
        this.setVisible(true);
    }
}
