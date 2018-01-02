// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.awt.Container;
import mindbright.util.AWTConvenience;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.FileDialog;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Dialog;

public class SSHSCPDialog
{
    private static final String TXT_LOCAL_IS_SOURCE = "Copy from local files/directories:";
    private static final String TXT_REMOTE_IS_TARGET = "To directory/file on server:";
    private static final String TXT_LOCAL_IS_TARGET = "Copy to local directory/file:";
    private static final String TXT_REMOTE_IS_SOURCE = "From files/directories on server:";
    static Dialog scpDialog;
    static TextField localFile;
    static TextField remoteFile;
    static Label localPos;
    static Label remotePos;
    static Checkbox cbRecursive;
    static Checkbox cbBackground;
    static FileDialog scpLocFD;
    static boolean toRemote;
    static SSHPropertyHandler propsHandler;
    static SSHInteractor interactor;
    static Frame parent;
    
    public static void show(final String title, final Frame p, final SSHPropertyHandler props, final SSHInteractor itor) {
        SSHSCPDialog.parent = p;
        SSHSCPDialog.propsHandler = props;
        SSHSCPDialog.interactor = itor;
        if (SSHSCPDialog.scpDialog == null) {
            SSHSCPDialog.scpDialog = new Dialog(SSHSCPDialog.parent, title, false);
            final GridBagLayout grid = new GridBagLayout();
            final GridBagConstraints gridc = new GridBagConstraints();
            (SSHSCPDialog.scpLocFD = new FileDialog(SSHSCPDialog.parent, "MindTerm - Select file to copy", 0)).setDirectory(SSHSCPDialog.propsHandler.getSSHHomeDir());
            SSHSCPDialog.scpDialog.setLayout(grid);
            gridc.fill = 2;
            gridc.anchor = 17;
            gridc.gridwidth = 2;
            gridc.gridy = 0;
            gridc.insets = new Insets(8, 4, 4, 8);
            grid.setConstraints(SSHSCPDialog.localPos = new Label("Copy from local files/directories:"), gridc);
            SSHSCPDialog.scpDialog.add(SSHSCPDialog.localPos);
            gridc.gridy = 1;
            gridc.gridwidth = 3;
            grid.setConstraints(SSHSCPDialog.localFile = new TextField("", 38), gridc);
            SSHSCPDialog.scpDialog.add(SSHSCPDialog.localFile);
            SSHSCPDialog.localFile.setText(SSHSCPDialog.scpLocFD.getDirectory());
            gridc.gridwidth = 1;
            Button b = new Button("...");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    SSHSCPDialog.scpLocFD.setVisible(true);
                    if (SSHSCPDialog.scpLocFD.getFile() != null && SSHSCPDialog.scpLocFD.getFile().length() > 0) {
                        String file = SSHSCPDialog.scpLocFD.getDirectory() + SSHSCPDialog.scpLocFD.getFile();
                        if (file.indexOf(32) != -1) {
                            file = "\"" + file + "\"";
                        }
                        SSHSCPDialog.localFile.setText(file);
                    }
                }
            });
            gridc.fill = 0;
            grid.setConstraints(b, gridc);
            SSHSCPDialog.scpDialog.add(b);
            SSHSCPDialog.toRemote = true;
            gridc.gridy = 2;
            b = new Button("Change Direction");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    SSHSCPDialog.toRemote = !SSHSCPDialog.toRemote;
                    if (SSHSCPDialog.toRemote) {
                        SSHSCPDialog.localPos.setText("Copy from local files/directories:");
                        SSHSCPDialog.remotePos.setText("To directory/file on server:");
                    }
                    else {
                        SSHSCPDialog.localPos.setText("Copy to local directory/file:");
                        SSHSCPDialog.remotePos.setText("From files/directories on server:");
                    }
                }
            });
            gridc.gridwidth = 0;
            gridc.fill = 0;
            gridc.anchor = 10;
            grid.setConstraints(b, gridc);
            SSHSCPDialog.scpDialog.add(b);
            gridc.gridy = 3;
            gridc.anchor = 17;
            gridc.gridwidth = 2;
            gridc.fill = 2;
            grid.setConstraints(SSHSCPDialog.remotePos = new Label("To directory/file on server:"), gridc);
            SSHSCPDialog.scpDialog.add(SSHSCPDialog.remotePos);
            gridc.gridy = 4;
            gridc.gridwidth = 3;
            grid.setConstraints(SSHSCPDialog.remoteFile = new TextField("", 38), gridc);
            SSHSCPDialog.scpDialog.add(SSHSCPDialog.remoteFile);
            gridc.gridy = 5;
            gridc.gridwidth = 1;
            grid.setConstraints(SSHSCPDialog.cbRecursive = new Checkbox("Recursive copy", false), gridc);
            SSHSCPDialog.scpDialog.add(SSHSCPDialog.cbRecursive);
            grid.setConstraints(SSHSCPDialog.cbBackground = new Checkbox("Low priority", false), gridc);
            SSHSCPDialog.scpDialog.add(SSHSCPDialog.cbBackground);
            gridc.gridy = 6;
            gridc.gridwidth = 2;
            final Panel bp = new Panel(new FlowLayout());
            final Button okBut;
            bp.add(okBut = new Button("Start Copy"));
            okBut.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    try {
                        final String srvHost = SSHSCPDialog.propsHandler.getSrvHost();
                        final int srvPort = SSHSCPDialog.propsHandler.getSrvPort();
                        final String localFN = SSHSCPDialog.localFile.getText();
                        final String remoteFN = SSHSCPDialog.remoteFile.getText();
                        final String curDir = SSHSCPDialog.scpLocFD.getDirectory();
                        final boolean recursive = SSHSCPDialog.cbRecursive.getState();
                        final boolean background = SSHSCPDialog.cbBackground.getState();
                        final SSHSCPGUIThread progress = new SSHSCPGUIThread(srvHost, srvPort, SSHSCPDialog.propsHandler, SSHSCPDialog.propsHandler, SSHSCPDialog.interactor, SSHSCPDialog.parent, curDir, localFN, remoteFN, recursive, background, SSHSCPDialog.toRemote);
                    }
                    catch (Exception ee) {
                        SSHMiscDialogs.alert("MindTerm - Alert", "Error starting SCP-tread: " + ee.getMessage(), SSHSCPDialog.parent);
                    }
                }
            });
            final Button cancBut;
            bp.add(cancBut = new Button("Close Dialog"));
            cancBut.addActionListener(new AWTConvenience.CloseAction(SSHSCPDialog.scpDialog));
            AWTConvenience.setKeyListenerOfChildren(SSHSCPDialog.scpDialog, new AWTConvenience.OKCancelAdapter(okBut, cancBut), null);
            gridc.gridwidth = 0;
            gridc.anchor = 10;
            grid.setConstraints(bp, gridc);
            SSHSCPDialog.scpDialog.add(bp);
            SSHSCPDialog.scpDialog.addWindowListener(new AWTConvenience.CloseAdapter(cancBut));
            AWTConvenience.setBackgroundOfChildren(SSHSCPDialog.scpDialog);
            SSHSCPDialog.scpDialog.setResizable(true);
            SSHSCPDialog.scpDialog.pack();
        }
        SSHSCPDialog.scpDialog.setTitle(title);
        AWTConvenience.placeDialog(SSHSCPDialog.scpDialog);
        SSHSCPDialog.scpDialog.setVisible(true);
    }
}
