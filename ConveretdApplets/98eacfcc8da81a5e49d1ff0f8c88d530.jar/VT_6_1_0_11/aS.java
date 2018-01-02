// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.net.MalformedURLException;
import com.hw.client.util.a;
import java.net.URL;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;

final class aS implements ActionListener, WindowListener
{
    private final U a;
    
    private aS(final U a, final byte b) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals("ACTION_OPTIONS")) {
            this.a.l().show(this.a.j, 0, 28);
            return;
        }
        if (actionCommand.equals("ACTION_DETACH")) {
            U.a(this.a, new JFrame(this.a.h.getName()));
            U.a(this.a).getRootPane().setSize(this.a.h.getSize());
            U.a(this.a).getRootPane().setPreferredSize(this.a.h.getSize());
            U.a(this.a).getContentPane().add(this.a.h);
            U.a(this.a).setDefaultCloseOperation(0);
            U.a(this.a).addWindowListener(this.a.i);
            this.a.k().setVisible(false);
            U.a(this.a).pack();
            U.a(this.a).setLocation(this.a.h.t.getLocationOnScreen());
            U.a(this.a).setVisible(true);
            this.a.h.t.getContentPane().remove(this.a.h);
            this.a.h.t.getContentPane().add(this.a());
            this.a.h.t.getContentPane().validate();
            this.a.h.t.getContentPane().repaint();
            return;
        }
        if (actionCommand.equals("ACTION_IMPORT")) {
            this.a.h.B();
            return;
        }
        if (actionCommand.equals("ACTION_EXPORT")) {
            this.a.h.C();
            return;
        }
        if (actionCommand.equals("ACTION_EXPAND_ALL")) {
            this.a.h.a(false);
            return;
        }
        if (actionCommand.equals("ACTION_COLLAPSE_ALL")) {
            this.a.h.A();
            return;
        }
        if (actionCommand.equals("ACTION_REFRESH")) {
            this.a.h.a(true);
            return;
        }
        if (actionCommand.equals("ACTION_ATTACH")) {
            this.b();
            return;
        }
        if (actionCommand.equals("ACTION_POWERED_BY")) {
            try {
                this.a.h.t.getAppletContext().showDocument(new URL(this.a.h.a().getString("website")), "_blank");
                return;
            }
            catch (MalformedURLException ex) {
                com.hw.client.util.a.d("VoiceDirectContentPane.action: Could not open " + this.a.h.a().getString("website"));
                return;
            }
        }
        if (actionCommand.equals("ACTION_FILTER")) {
            this.a.h.t();
        }
    }
    
    private JButton a() {
        if (U.b(this.a) == null) {
            U.a(this.a, new cP("<html>" + this.a.h.c("detach_msg", this.a.h.getName(), this.getClass().getClassLoader().getResource("images/common/detached.png").toExternalForm(), this.a.h.getName())));
            U.b(this.a).setHorizontalAlignment(0);
            U.b(this.a).setVerticalAlignment(0);
            U.b(this.a).setBackground(h.b());
            U.b(this.a).setBorderPainted(false);
            U.b(this.a).setFocusPainted(false);
            U.b(this.a).setActionCommand("ACTION_ATTACH");
            U.b(this.a).addActionListener(this.a.i);
        }
        return U.b(this.a);
    }
    
    private void b() {
        U.a(this.a).removeWindowListener(this);
        U.a(this.a).setVisible(false);
        U.a(this.a).getContentPane().remove(this.a.h);
        U.a(this.a).dispose();
        this.a.k().setVisible(true);
        this.a.h.t.getContentPane().remove(this.a());
        this.a.h.t.getContentPane().add(this.a.h);
        this.a.h.t.getContentPane().validate();
        this.a.h.t.getContentPane().repaint();
    }
    
    public final void windowOpened(final WindowEvent windowEvent) {
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        this.b();
    }
    
    public final void windowClosed(final WindowEvent windowEvent) {
    }
    
    public final void windowIconified(final WindowEvent windowEvent) {
    }
    
    public final void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public final void windowActivated(final WindowEvent windowEvent) {
    }
    
    public final void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    aS(final U u) {
        this(u, (byte)0);
    }
}
