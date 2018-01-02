// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import gui.dialog.DialogContentPane;
import gui.dialog.PasswordContentPane;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import anon.infoservice.ImmutableProxyInterface;
import anon.util.IPasswordReader;

final class JAPFirewallPasswdDlg implements IPasswordReader
{
    public String readPassword(final ImmutableProxyInterface immutableProxyInterface) {
        final JAPDialog japDialog = new JAPDialog(JAPController.getInstance().getCurrentView(), JAPMessages.getString("passwdDlgTitle"), true);
        japDialog.setAlwaysOnTop(true);
        final PasswordContentPane passwordContentPane = new PasswordContentPane(japDialog, 2, JAPMessages.getString("passwdDlgInput") + "<br><br>" + JAPMessages.getString("passwdDlgHost") + ": " + immutableProxyInterface.getHost() + "<br>" + JAPMessages.getString("passwdDlgPort") + ": " + immutableProxyInterface.getPort() + "<br>" + JAPMessages.getString("passwdDlgUserID") + ": " + immutableProxyInterface.getAuthenticationUserID()) {
            public CheckError[] checkCancel() {
                return super.checkCancel();
            }
        };
        passwordContentPane.updateDialog();
        japDialog.pack();
        japDialog.setResizable(false);
        japDialog.setVisible(true);
        if (passwordContentPane.getButtonValue() != 0 || passwordContentPane.getPassword() == null) {
            return null;
        }
        return new String(passwordContentPane.getPassword());
    }
}
