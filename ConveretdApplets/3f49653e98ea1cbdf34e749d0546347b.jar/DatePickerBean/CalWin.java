// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Frame;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dialog;

class CalWin extends Dialog
{
    public CalWin(final Dialog dialog, final boolean b, final calendarpanel calendarpanel) {
        super(dialog, "SELECT DATE", b);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(calendarpanel);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                CalWin.this.setVisible(false);
            }
        });
    }
    
    public CalWin(final Frame frame, final boolean b, final calendarpanel calendarpanel) {
        super(frame, "SELECT DATE", b);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(calendarpanel);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                CalWin.this.setVisible(false);
            }
        });
    }
}
