// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.awt.TextArea;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import mindbright.util.AWTConvenience;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Dialog;

public final class SSHMiscDialogs
{
    private static Dialog alertDialog;
    private static Label alertLabel;
    private static Button okAlertBut;
    private static Dialog passwordDialog;
    private static Label pwdMsgLabel;
    private static String pwdAnswer;
    private static TextField pwdPassword;
    private static Dialog setPasswordDialog;
    private static Label setPwdMsgLabel;
    private static String setPwdAnswer;
    private static TextField setPwdText;
    private static TextField setPwdText2;
    private static Dialog confirmDialog;
    private static Label confirmLabel;
    private static boolean confirmRet;
    private static Button yesBut;
    private static Button noBut;
    
    public static void alert(final String title, final String message, final Frame parent) {
        if (SSHMiscDialogs.alertDialog == null) {
            SSHMiscDialogs.alertDialog = new Dialog(parent, title, true);
            final GridBagLayout grid = new GridBagLayout();
            final GridBagConstraints gridc = new GridBagConstraints();
            SSHMiscDialogs.alertDialog.setLayout(grid);
            gridc.fill = 2;
            gridc.weightx = 1.0;
            gridc.weighty = 1.0;
            gridc.gridwidth = 0;
            gridc.anchor = 10;
            gridc.insets = new Insets(8, 4, 4, 8);
            gridc.gridy = 0;
            grid.setConstraints(SSHMiscDialogs.alertLabel = new Label(), gridc);
            SSHMiscDialogs.alertDialog.add(SSHMiscDialogs.alertLabel);
            (SSHMiscDialogs.okAlertBut = new Button("OK")).addActionListener(new AWTConvenience.CloseAction(SSHMiscDialogs.alertDialog));
            gridc.fill = 0;
            gridc.gridy = 1;
            grid.setConstraints(SSHMiscDialogs.okAlertBut, gridc);
            SSHMiscDialogs.alertDialog.add(SSHMiscDialogs.okAlertBut);
            SSHMiscDialogs.alertDialog.addWindowListener(new AWTConvenience.CloseAdapter(SSHMiscDialogs.okAlertBut));
            AWTConvenience.setBackgroundOfChildren(SSHMiscDialogs.alertDialog);
            SSHMiscDialogs.alertDialog.setResizable(true);
        }
        SSHMiscDialogs.alertDialog.setTitle(title);
        SSHMiscDialogs.alertDialog.remove(SSHMiscDialogs.alertLabel);
        SSHMiscDialogs.alertLabel.setText(message);
        SSHMiscDialogs.alertDialog.add(SSHMiscDialogs.alertLabel);
        SSHMiscDialogs.alertDialog.pack();
        AWTConvenience.placeDialog(SSHMiscDialogs.alertDialog);
        SSHMiscDialogs.okAlertBut.requestFocus();
        SSHMiscDialogs.alertDialog.setVisible(true);
    }
    
    public static String password(final String title, final String message, final Frame parent) {
        if (SSHMiscDialogs.passwordDialog == null) {
            SSHMiscDialogs.passwordDialog = new Dialog(parent, title, true);
            final GridBagLayout grid = new GridBagLayout();
            final GridBagConstraints gridc = new GridBagConstraints();
            SSHMiscDialogs.passwordDialog.setLayout(grid);
            gridc.fill = 2;
            gridc.gridwidth = 0;
            gridc.anchor = 10;
            gridc.insets = new Insets(8, 4, 4, 8);
            gridc.gridy = 0;
            grid.setConstraints(SSHMiscDialogs.pwdMsgLabel = new Label(), gridc);
            SSHMiscDialogs.passwordDialog.add(SSHMiscDialogs.pwdMsgLabel);
            gridc.gridy = 1;
            gridc.gridwidth = 1;
            gridc.anchor = 17;
            final Label lbl = new Label("Password:");
            grid.setConstraints(lbl, gridc);
            SSHMiscDialogs.passwordDialog.add(lbl);
            SSHMiscDialogs.pwdPassword = new TextField();
            gridc.gridwidth = 0;
            grid.setConstraints(SSHMiscDialogs.pwdPassword, gridc);
            SSHMiscDialogs.pwdPassword.setEchoChar('*');
            SSHMiscDialogs.passwordDialog.add(SSHMiscDialogs.pwdPassword);
            final Panel bp = new Panel(new FlowLayout());
            final Button okBut;
            bp.add(okBut = new Button("OK"));
            final ActionListener al;
            okBut.addActionListener(al = new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (e.getActionCommand().equals("OK")) {
                        SSHMiscDialogs.pwdAnswer = SSHMiscDialogs.pwdPassword.getText();
                    }
                    else {
                        SSHMiscDialogs.pwdAnswer = null;
                    }
                    SSHMiscDialogs.passwordDialog.setVisible(false);
                }
            });
            final Button cancBut;
            bp.add(cancBut = new Button("Cancel"));
            cancBut.addActionListener(al);
            gridc.gridy = 2;
            gridc.gridwidth = 0;
            grid.setConstraints(bp, gridc);
            SSHMiscDialogs.passwordDialog.add(bp);
            SSHMiscDialogs.passwordDialog.addWindowListener(new AWTConvenience.CloseAdapter(cancBut));
            AWTConvenience.setKeyListenerOfChildren(SSHMiscDialogs.passwordDialog, new AWTConvenience.OKCancelAdapter(okBut, cancBut), null);
            AWTConvenience.setBackgroundOfChildren(SSHMiscDialogs.passwordDialog);
            SSHMiscDialogs.passwordDialog.setResizable(true);
        }
        SSHMiscDialogs.passwordDialog.setTitle(title);
        SSHMiscDialogs.passwordDialog.remove(SSHMiscDialogs.pwdMsgLabel);
        SSHMiscDialogs.pwdMsgLabel.setText(message);
        SSHMiscDialogs.pwdPassword.setText("");
        SSHMiscDialogs.passwordDialog.add(SSHMiscDialogs.pwdMsgLabel);
        SSHMiscDialogs.passwordDialog.pack();
        AWTConvenience.placeDialog(SSHMiscDialogs.passwordDialog);
        SSHMiscDialogs.passwordDialog.setVisible(true);
        return SSHMiscDialogs.pwdAnswer;
    }
    
    public static String setPassword(final String title, final String message, final Frame parent) {
        if (SSHMiscDialogs.setPasswordDialog == null) {
            SSHMiscDialogs.setPasswordDialog = new Dialog(parent, title, true);
            final GridBagLayout grid = new GridBagLayout();
            final GridBagConstraints gridc = new GridBagConstraints();
            SSHMiscDialogs.setPasswordDialog.setLayout(grid);
            gridc.fill = 2;
            gridc.gridwidth = 0;
            gridc.anchor = 10;
            gridc.insets = new Insets(8, 4, 4, 8);
            gridc.gridy = 0;
            grid.setConstraints(SSHMiscDialogs.setPwdMsgLabel = new Label(), gridc);
            SSHMiscDialogs.setPasswordDialog.add(SSHMiscDialogs.setPwdMsgLabel);
            gridc.gridy = 1;
            gridc.gridwidth = 1;
            gridc.anchor = 17;
            Label lbl = new Label("Password:");
            grid.setConstraints(lbl, gridc);
            SSHMiscDialogs.setPasswordDialog.add(lbl);
            grid.setConstraints(SSHMiscDialogs.setPwdText = new TextField("", 12), gridc);
            SSHMiscDialogs.setPwdText.setEchoChar('*');
            SSHMiscDialogs.setPasswordDialog.add(SSHMiscDialogs.setPwdText);
            gridc.gridy = 2;
            lbl = new Label("Password again:");
            grid.setConstraints(lbl, gridc);
            SSHMiscDialogs.setPasswordDialog.add(lbl);
            grid.setConstraints(SSHMiscDialogs.setPwdText2 = new TextField("", 12), gridc);
            SSHMiscDialogs.setPwdText2.setEchoChar('*');
            SSHMiscDialogs.setPasswordDialog.add(SSHMiscDialogs.setPwdText2);
            final Panel bp = new Panel(new FlowLayout());
            final Button okBut;
            bp.add(okBut = new Button("OK"));
            final ActionListener al;
            okBut.addActionListener(al = new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (e.getActionCommand().equals("OK")) {
                        SSHMiscDialogs.setPwdAnswer = SSHMiscDialogs.setPwdText.getText();
                        if (!SSHMiscDialogs.setPwdAnswer.equals(SSHMiscDialogs.setPwdText2.getText())) {
                            SSHMiscDialogs.setPwdText.setText("");
                            SSHMiscDialogs.setPwdText2.setText("");
                            return;
                        }
                    }
                    else {
                        SSHMiscDialogs.setPwdAnswer = null;
                    }
                    SSHMiscDialogs.setPasswordDialog.setVisible(false);
                }
            });
            final Button cancBut;
            bp.add(cancBut = new Button("Cancel"));
            cancBut.addActionListener(al);
            gridc.gridy = 3;
            gridc.gridwidth = 0;
            grid.setConstraints(bp, gridc);
            SSHMiscDialogs.setPasswordDialog.add(bp);
            SSHMiscDialogs.setPasswordDialog.addWindowListener(new AWTConvenience.CloseAdapter(cancBut));
            AWTConvenience.setKeyListenerOfChildren(SSHMiscDialogs.setPasswordDialog, new AWTConvenience.OKCancelAdapter(okBut, cancBut), null);
            AWTConvenience.setBackgroundOfChildren(SSHMiscDialogs.setPasswordDialog);
            SSHMiscDialogs.setPasswordDialog.setResizable(true);
        }
        SSHMiscDialogs.setPasswordDialog.setTitle(title);
        SSHMiscDialogs.setPasswordDialog.remove(SSHMiscDialogs.setPwdMsgLabel);
        SSHMiscDialogs.setPwdMsgLabel.setText(message);
        SSHMiscDialogs.setPwdText.setText("");
        SSHMiscDialogs.setPwdText2.setText("");
        SSHMiscDialogs.setPasswordDialog.add(SSHMiscDialogs.setPwdMsgLabel);
        SSHMiscDialogs.setPasswordDialog.pack();
        AWTConvenience.placeDialog(SSHMiscDialogs.setPasswordDialog);
        SSHMiscDialogs.setPasswordDialog.setVisible(true);
        return SSHMiscDialogs.setPwdAnswer;
    }
    
    public static boolean confirm(final String title, final String message, final boolean defAnswer, final Frame parent) {
        if (SSHMiscDialogs.confirmDialog == null) {
            SSHMiscDialogs.confirmDialog = new Dialog(parent, title, true);
            final GridBagLayout grid = new GridBagLayout();
            final GridBagConstraints gridc = new GridBagConstraints();
            SSHMiscDialogs.confirmDialog.setLayout(grid);
            gridc.fill = 2;
            gridc.gridwidth = 0;
            gridc.anchor = 10;
            gridc.insets = new Insets(8, 4, 4, 8);
            gridc.gridy = 0;
            grid.setConstraints(SSHMiscDialogs.confirmLabel = new Label(), gridc);
            SSHMiscDialogs.confirmDialog.add(SSHMiscDialogs.confirmLabel);
            final Panel bp = new Panel(new FlowLayout());
            bp.add(SSHMiscDialogs.yesBut = new Button("Yes"));
            final ActionListener al;
            SSHMiscDialogs.yesBut.addActionListener(al = new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (e.getActionCommand().equals("Yes")) {
                        SSHMiscDialogs.confirmRet = true;
                    }
                    else {
                        SSHMiscDialogs.confirmRet = false;
                    }
                    SSHMiscDialogs.confirmDialog.setVisible(false);
                }
            });
            bp.add(SSHMiscDialogs.noBut = new Button("No"));
            SSHMiscDialogs.noBut.addActionListener(al);
            gridc.gridy = 1;
            gridc.gridwidth = 0;
            grid.setConstraints(bp, gridc);
            SSHMiscDialogs.confirmDialog.add(bp);
            SSHMiscDialogs.confirmDialog.addWindowListener(new AWTConvenience.CloseAdapter(SSHMiscDialogs.noBut));
            AWTConvenience.setBackgroundOfChildren(SSHMiscDialogs.confirmDialog);
            SSHMiscDialogs.confirmDialog.setResizable(true);
        }
        SSHMiscDialogs.confirmDialog.remove(SSHMiscDialogs.confirmLabel);
        SSHMiscDialogs.confirmLabel.setText(message);
        SSHMiscDialogs.confirmDialog.add(SSHMiscDialogs.confirmLabel);
        SSHMiscDialogs.confirmDialog.pack();
        AWTConvenience.placeDialog(SSHMiscDialogs.confirmDialog);
        if (defAnswer) {
            SSHMiscDialogs.yesBut.requestFocus();
        }
        else {
            SSHMiscDialogs.noBut.requestFocus();
        }
        SSHMiscDialogs.confirmDialog.setVisible(true);
        return SSHMiscDialogs.confirmRet;
    }
    
    public static void notice(final String title, final String text, final int rows, final int cols, final boolean scrollbar, final Frame parent) {
        Dialog textDialog = null;
        textDialog = new Dialog(parent, title, true);
        final GridBagLayout grid = new GridBagLayout();
        final GridBagConstraints gridc = new GridBagConstraints();
        textDialog.setLayout(grid);
        gridc.fill = 0;
        gridc.gridwidth = 0;
        gridc.anchor = 10;
        gridc.insets = new Insets(4, 4, 4, 4);
        final TextArea textArea = new TextArea(text, rows, cols, scrollbar ? 1 : 3);
        grid.setConstraints(textArea, gridc);
        textDialog.add(textArea);
        textArea.setEditable(false);
        final Button okTextBut = new Button("OK");
        okTextBut.addActionListener(new AWTConvenience.CloseAction(textDialog));
        gridc.fill = 0;
        grid.setConstraints(okTextBut, gridc);
        textDialog.add(okTextBut);
        textDialog.addWindowListener(new AWTConvenience.CloseAdapter(okTextBut));
        AWTConvenience.setBackgroundOfChildren(textDialog);
        textDialog.setResizable(true);
        textDialog.pack();
        AWTConvenience.placeDialog(textDialog);
        okTextBut.requestFocus();
        textDialog.setVisible(true);
    }
    
    static {
        SSHMiscDialogs.alertDialog = null;
        SSHMiscDialogs.passwordDialog = null;
        SSHMiscDialogs.setPasswordDialog = null;
        SSHMiscDialogs.confirmDialog = null;
    }
}
