// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Cursor;
import irc.com.utils.IEAutoStart;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.net.URL;
import irc.managers.Resources;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class NovipPanel extends JPanel implements MouseListener
{
    EIRC eirc;
    ImageIcon img;
    JLabel Image;
    JLabel Text;
    BorderLayout borderLayout;
    
    public NovipPanel(final EIRC eirc) {
        this.img = Resources.getImagesIcon("freindsnovip");
        this.borderLayout = new BorderLayout();
        this.eirc = eirc;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setOpaque(false);
        this.Image.setOpaque(false);
        this.Text.setOpaque(false);
    }
    
    ImageIcon createImageIcon(final String s) {
        final URL resource = this.getClass().getResource(s);
        if (resource != null) {
            return new ImageIcon(resource);
        }
        System.err.println("Couldn't find file: " + s);
        return null;
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.borderLayout);
        this.Image = new JLabel(this.img, 0);
        (this.Text = new JLabel()).setHorizontalAlignment(0);
        this.Text.setHorizontalTextPosition(0);
        this.Text.setText("<html><font size=\"4\" color=\"blue\"><CENTER><u>Gardez contact avec vos amis <br>en vous inscrivant d\u00e8s maintenant sur VIP ! <br>Inscription Gratuite en cliquant ici </u></CENTER></font></html>");
        this.add(this.Text, "South");
        this.add(this.Image, "Center");
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        IEAutoStart.run("http://vip.chat-land.org/");
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.Text.setText("<html><font size=\"4\" color=\"red\"><CENTER><u>Gardez contact avec vos amis <br>en vous inscrivant d\u00e8s maintenant sur VIP ! <br>Inscription Gratuite en cliquant ici </u></CENTER></font></html>");
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.Text.setText("<html><font size=\"4\" color=\"blue\"><CENTER><u>Gardez contact avec vos amis <br>en vous inscrivant d\u00e8s maintenant sur VIP ! <br>Inscription Gratuite en cliquant ici </u></CENTER></font></html>");
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    public void setConnected(final boolean enabled) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setEnabled(enabled);
        }
    }
}
