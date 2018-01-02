import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.applet.AppletContext;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SplashPanel extends Panel implements MouseListener
{
    AppletContext m_ctxApp;
    
    public SplashPanel(final String s, final int n, final String s2, final AppletContext ctxApp) {
        this.m_ctxApp = ctxApp;
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(Color.cyan);
        final Label label = new Label(s, 1);
        label.setFont(new Font("TimesRoman", 1, n));
        final Label label2 = new Label(s2, 1);
        this.add(label);
        this.add(label2);
        label.setForeground(Color.black);
        label2.setForeground(Color.black);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.m_ctxApp.showDocument(new URL("http://www.ugosweb.com"), "_blank");
        }
        catch (Exception ex) {}
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
