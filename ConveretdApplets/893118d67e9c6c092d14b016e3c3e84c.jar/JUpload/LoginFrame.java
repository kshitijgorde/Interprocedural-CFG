// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import javax.swing.JOptionPane;
import javax.swing.JDialog;

public class LoginFrame extends JDialog
{
    private String strPassword;
    private String strUsername;
    
    LoginFrame() {
        this.strUsername = JOptionPane.showInputDialog("Username?");
        this.strPassword = JOptionPane.showInputDialog("Password?");
    }
    
    protected String getPassword() {
        return this.strPassword;
    }
    
    protected String getUsername() {
        return this.strUsername;
    }
}
