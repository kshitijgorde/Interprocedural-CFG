// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class JUserNameableFrame extends JFrame
{
    private String vBaseTitle;
    private String vUserTitle;
    
    public JUserNameableFrame() {
        this.vBaseTitle = "";
        this.vUserTitle = "";
        this.bindPopupMenu();
    }
    
    private void bindPopupMenu() {
        this.addMouseListener(new JPopupMenuListener(UserNameablePopupMenuFactory.getMenu(this)));
    }
    
    public void setBaseTitle(final String s) {
        this.vBaseTitle = s.trim();
        this.setTitle(this.getUserTitle());
    }
    
    public String getBaseTitle() {
        return this.vBaseTitle;
    }
    
    public String getUserTitle() {
        return this.vUserTitle;
    }
    
    public void setTitle(final String s) {
        this.vUserTitle = s.trim();
        String title;
        if (this.vUserTitle.equals("")) {
            title = this.vBaseTitle;
        }
        else {
            title = this.vUserTitle + " - " + this.vBaseTitle;
        }
        super.setTitle(title);
    }
    
    public void showTitleDialog() {
        final String showInputDialog = JOptionPane.showInputDialog(this, "Change frame title to:", this.getUserTitle());
        if (showInputDialog != null) {
            this.setTitle(showInputDialog);
        }
    }
}
