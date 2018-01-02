import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.AbstractButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.border.Border;
import java.awt.CardLayout;
import javax.swing.BorderFactory;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import javax.swing.SpringLayout;
import java.awt.event.ComponentListener;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.awt.Window;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class rp_bC extends rp_gd
{
    private /* synthetic */ rp_bl a;
    
    public rp_bC(final rp_bl a) {
        this.a = a;
        rp_bl.a(a, true);
    }
    
    public final void a() {
        rp_bl.a(this.a, false);
        try {
            final rp_bg rp_bg;
            if (!(rp_bg = (rp_bg)this.get()).a()) {
                rp_bd.a(this.a.a.a(), rp_au.a("err"), "<html>" + rp_bg.a(1, "<br>").replace("\\n", "<br>").replace("\\\"", "\"") + "</html>", rp_au.a("OK"));
                return;
            }
            this.a.dispose();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        catch (ExecutionException ex2) {
            ex2.printStackTrace();
        }
    }
}
