import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class while extends Dialog implements WindowListener, ActionListener
{
    private Button za;
    private a Aa;
    private boolean Ba;
    private o Ca;
    private Frame Da;
    private _ ya;
    
    public while(final Frame da, final String s, final o ca, final _ ya) {
        super(da, s + " - " + ca.b("strIndicatorSettings"), true);
        this.Ba = false;
        this.Ca = ca;
        this.Da = da;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.ya = ya, "Center");
        this.Aa = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.za = new Button(this.Ca.b("btnOK-EnableIndicator"));
        final Button button = new Button(this.Ca.b("btnCancel-DisableIndicator"));
        this.za.addActionListener(this);
        button.addActionListener(this);
        this.Aa.add(this.za);
        this.Aa.add(button);
        this.add(this.Aa, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.za) {
            final String j = this.ya.j();
            if (j == null) {
                this.Ba = true;
                this.dispose();
            }
            else {
                new b(this.Da, this.Ca.b("strIndicatorSettingsError"), j, this.Ca.b("btnOK"), null).show();
            }
        }
        else {
            this.dispose();
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public boolean a() {
        return this.Ba;
    }
}
