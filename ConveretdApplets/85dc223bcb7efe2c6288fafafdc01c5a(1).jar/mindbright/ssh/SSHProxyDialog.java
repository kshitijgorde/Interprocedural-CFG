// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.awt.Container;
import java.awt.event.WindowListener;
import mindbright.util.AWTConvenience;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;

public final class SSHProxyDialog
{
    private static Dialog proxyDialog;
    private static Choice choicePrxType;
    private static Checkbox cbNeedAuth;
    private static TextField textPrxHost;
    private static TextField textPrxPort;
    private static TextField textPrxUser;
    private static TextField textPrxPasswd;
    private static String[] prxTypes;
    private static SSHPropertyHandler propsHandler;
    
    public static void show(final String title, final Frame parent, final SSHPropertyHandler props) {
        SSHProxyDialog.propsHandler = props;
        if (SSHProxyDialog.proxyDialog == null) {
            SSHProxyDialog.prxTypes = SSH.getProxyTypes();
            SSHProxyDialog.proxyDialog = new Dialog(parent, title, true);
            final GridBagLayout grid = new GridBagLayout();
            final GridBagConstraints gridc = new GridBagConstraints();
            SSHProxyDialog.proxyDialog.setLayout(grid);
            gridc.fill = 2;
            gridc.anchor = 17;
            gridc.gridwidth = 1;
            gridc.insets = new Insets(4, 4, 0, 4);
            gridc.gridy = 0;
            Label lbl = new Label("Proxy type:");
            gridc.gridwidth = 2;
            grid.setConstraints(lbl, gridc);
            SSHProxyDialog.proxyDialog.add(lbl);
            SSHProxyDialog.choicePrxType = new Choice();
            for (int i = 0; i < SSHProxyDialog.prxTypes.length; ++i) {
                SSHProxyDialog.choicePrxType.add(SSHProxyDialog.prxTypes[i]);
            }
            grid.setConstraints(SSHProxyDialog.choicePrxType, gridc);
            SSHProxyDialog.proxyDialog.add(SSHProxyDialog.choicePrxType);
            final ItemListener il;
            SSHProxyDialog.choicePrxType.addItemListener(il = new ItemListener() {
                public void itemStateChanged(final ItemEvent e) {
                    if (e.getSource() == SSHProxyDialog.choicePrxType) {
                        SSHProxyDialog.textPrxPort.setText(String.valueOf(SSH.defaultProxyPorts[SSH.getProxyType(SSHProxyDialog.choicePrxType.getSelectedItem())]));
                    }
                    updateFromType();
                }
            });
            gridc.gridy = 1;
            lbl = new Label("Server:");
            grid.setConstraints(lbl, gridc);
            SSHProxyDialog.proxyDialog.add(lbl);
            gridc.gridwidth = 4;
            grid.setConstraints(SSHProxyDialog.textPrxHost = new TextField("", 16), gridc);
            SSHProxyDialog.proxyDialog.add(SSHProxyDialog.textPrxHost);
            gridc.gridwidth = 1;
            lbl = new Label("Port:");
            grid.setConstraints(lbl, gridc);
            SSHProxyDialog.proxyDialog.add(lbl);
            grid.setConstraints(SSHProxyDialog.textPrxPort = new TextField("", 4), gridc);
            SSHProxyDialog.proxyDialog.add(SSHProxyDialog.textPrxPort);
            gridc.gridy = 2;
            gridc.gridwidth = 2;
            lbl = new Label("Username:");
            grid.setConstraints(lbl, gridc);
            SSHProxyDialog.proxyDialog.add(lbl);
            grid.setConstraints(SSHProxyDialog.textPrxUser = new TextField("", 10), gridc);
            SSHProxyDialog.proxyDialog.add(SSHProxyDialog.textPrxUser);
            lbl = new Label("Password:");
            grid.setConstraints(lbl, gridc);
            SSHProxyDialog.proxyDialog.add(lbl);
            (SSHProxyDialog.textPrxPasswd = new TextField("", 10)).setEchoChar('*');
            grid.setConstraints(SSHProxyDialog.textPrxPasswd, gridc);
            SSHProxyDialog.proxyDialog.add(SSHProxyDialog.textPrxPasswd);
            gridc.gridy = 3;
            gridc.gridwidth = 4;
            grid.setConstraints(SSHProxyDialog.cbNeedAuth = new Checkbox("Need authentication"), gridc);
            SSHProxyDialog.proxyDialog.add(SSHProxyDialog.cbNeedAuth);
            SSHProxyDialog.cbNeedAuth.addItemListener(il);
            final Panel bp = new Panel(new FlowLayout());
            Button b;
            bp.add(b = new Button("OK"));
            b.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    try {
                        final String prxTypeStr = SSHProxyDialog.choicePrxType.getSelectedItem();
                        SSHProxyDialog.propsHandler.setProperty("proxytype", prxTypeStr);
                        if (!"none".equalsIgnoreCase(prxTypeStr)) {
                            SSHProxyDialog.propsHandler.setProperty("proxyhost", SSHProxyDialog.textPrxHost.getText());
                            SSHProxyDialog.propsHandler.setProperty("proxyport", SSHProxyDialog.textPrxPort.getText());
                        }
                        if (SSHProxyDialog.cbNeedAuth.getState()) {
                            SSHProxyDialog.propsHandler.setProperty("proxyuser", SSHProxyDialog.textPrxUser.getText());
                            SSHProxyDialog.propsHandler.setProperty("prxpassword", SSHProxyDialog.textPrxPasswd.getText());
                        }
                        else if ("socks4".equals(prxTypeStr)) {
                            SSHProxyDialog.propsHandler.setProperty("proxyuser", SSHProxyDialog.textPrxUser.getText());
                        }
                        SSHProxyDialog.proxyDialog.setVisible(false);
                    }
                    catch (Exception ex) {}
                }
            });
            bp.add(b = new Button("Cancel"));
            b.addActionListener(new AWTConvenience.CloseAction(SSHProxyDialog.proxyDialog));
            gridc.gridy = 4;
            gridc.anchor = 10;
            gridc.fill = 0;
            gridc.gridwidth = 0;
            grid.setConstraints(bp, gridc);
            SSHProxyDialog.proxyDialog.add(bp);
            SSHProxyDialog.proxyDialog.addWindowListener(new AWTConvenience.CloseAdapter(b));
            AWTConvenience.setBackgroundOfChildren(SSHProxyDialog.proxyDialog);
            SSHProxyDialog.proxyDialog.setResizable(true);
            SSHProxyDialog.proxyDialog.pack();
        }
        SSHProxyDialog.proxyDialog.setTitle(title);
        final String prxType = SSHProxyDialog.propsHandler.getProperty("proxytype");
        SSHProxyDialog.choicePrxType.select(prxType);
        final String prxUser = SSHProxyDialog.propsHandler.getProperty("proxyuser");
        final boolean needAuth = prxUser != null && prxUser.trim().length() > 0;
        SSHProxyDialog.cbNeedAuth.setState(needAuth);
        SSHProxyDialog.textPrxHost.setText(SSHProxyDialog.propsHandler.getProperty("proxyhost"));
        SSHProxyDialog.textPrxPort.setText(SSHProxyDialog.propsHandler.getProperty("proxyport"));
        SSHProxyDialog.textPrxUser.setText(SSHProxyDialog.propsHandler.getProperty("proxyuser"));
        updateFromType();
        AWTConvenience.placeDialog(SSHProxyDialog.proxyDialog);
        SSHProxyDialog.proxyDialog.setVisible(true);
    }
    
    private static void updateFromType() {
        boolean proxyEnable = false;
        boolean authEnable = false;
        final String proxyType = SSHProxyDialog.choicePrxType.getSelectedItem();
        int type = 0;
        try {
            type = SSH.getProxyType(proxyType);
            switch (type) {
                case 1:
                case 3:
                case 4: {
                    authEnable = true;
                }
                case 2: {
                    proxyEnable = true;
                    break;
                }
            }
        }
        catch (Exception ex) {}
        SSHProxyDialog.textPrxHost.setEnabled(proxyEnable);
        SSHProxyDialog.textPrxPort.setEnabled(proxyEnable);
        SSHProxyDialog.cbNeedAuth.setEnabled(authEnable);
        if (!authEnable) {
            SSHProxyDialog.cbNeedAuth.setState(false);
        }
        final boolean needAuth = SSHProxyDialog.cbNeedAuth.getState();
        SSHProxyDialog.textPrxUser.setEnabled(needAuth);
        SSHProxyDialog.textPrxPasswd.setEnabled(needAuth);
        if (proxyEnable) {
            if (SSHProxyDialog.textPrxHost.getText().length() == 0) {
                SSHProxyDialog.textPrxHost.setText(SSHProxyDialog.propsHandler.getProperty("proxyhost"));
            }
            if (SSHProxyDialog.textPrxPort.getText().length() == 0) {
                SSHProxyDialog.textPrxPort.setText(SSHProxyDialog.propsHandler.getProperty("proxyport"));
            }
        }
        else {
            SSHProxyDialog.textPrxHost.setText("");
            SSHProxyDialog.textPrxPort.setText("");
        }
        if (needAuth) {
            if (SSHProxyDialog.textPrxUser.getText().length() == 0) {
                SSHProxyDialog.textPrxUser.setText(SSHProxyDialog.propsHandler.getProperty("proxyuser"));
            }
        }
        else {
            SSHProxyDialog.textPrxUser.setText("");
            SSHProxyDialog.textPrxPasswd.setText("");
        }
        if (type == 2) {
            SSHProxyDialog.textPrxUser.setEnabled(true);
            String user = SSHProxyDialog.propsHandler.getProperty("proxyuser");
            if (SSHProxyDialog.textPrxUser.getText().length() == 0) {
                if (user == null) {
                    user = "anonymous";
                }
                SSHProxyDialog.textPrxUser.setText(user);
            }
        }
        if (proxyEnable) {
            SSHProxyDialog.textPrxHost.requestFocus();
        }
    }
    
    static {
        SSHProxyDialog.proxyDialog = null;
    }
}
