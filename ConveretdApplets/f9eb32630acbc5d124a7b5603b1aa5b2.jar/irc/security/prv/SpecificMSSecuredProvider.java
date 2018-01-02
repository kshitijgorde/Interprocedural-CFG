// 
// Decompiled by Procyon v0.5.30
// 

package irc.security.prv;

import java.net.InetAddress;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.UnknownHostException;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.net.Socket;
import irc.security.SecuredProvider;

public class SpecificMSSecuredProvider implements SecuredProvider
{
    public Socket getSocket(final String s, final Integer n) throws UnknownHostException, IOException {
        try {
            PolicyEngine.assertPermission(PermissionID.NETIO);
            return new Socket(s, n);
        }
        catch (Throwable t) {
            return new Socket(s, n);
        }
    }
    
    public ServerSocket getServerSocket(final Integer n) throws IOException {
        try {
            PolicyEngine.assertPermission(PermissionID.NETIO);
            return new SpecificMSSecuredServerSocket(n);
        }
        catch (Throwable t) {
            return new SpecificMSSecuredServerSocket(n);
        }
    }
    
    public FileInputStream getFileInputStream(final File file) throws IOException {
        try {
            PolicyEngine.assertPermission(PermissionID.FILEIO);
            return new FileInputStream(file);
        }
        catch (Throwable t) {
            return new FileInputStream(file);
        }
    }
    
    public Integer getFileSize(final File file) {
        try {
            PolicyEngine.assertPermission(PermissionID.FILEIO);
            return new Integer((int)file.length());
        }
        catch (Throwable t) {
            return new Integer((int)file.length());
        }
    }
    
    public FileOutputStream getFileOutputStream(final File file) throws IOException {
        try {
            PolicyEngine.assertPermission(PermissionID.FILEIO);
            return new FileOutputStream(file);
        }
        catch (Throwable t) {
            return new FileOutputStream(file);
        }
    }
    
    public FileDialog getFileDialog(final Frame frame, final String s, final int n) {
        try {
            PolicyEngine.assertPermission(PermissionID.UI);
            return new FileDialog(frame, s, n);
        }
        catch (Throwable t) {
            return new FileDialog(frame, s, n);
        }
    }
    
    public File getLoadFile(final String s) {
        try {
            PolicyEngine.assertPermission(PermissionID.UI);
            final Frame frame = new Frame();
            final FileDialog fileDialog = new FileDialog(frame, s, 0);
            fileDialog.show();
            File file = null;
            if (fileDialog.getFile() != null) {
                file = new File(fileDialog.getDirectory() + fileDialog.getFile());
            }
            fileDialog.hide();
            fileDialog.dispose();
            frame.dispose();
            return file;
        }
        catch (Throwable t) {
            final Frame frame2 = new Frame();
            final FileDialog fileDialog2 = new FileDialog(frame2, s, 0);
            fileDialog2.show();
            File file2 = null;
            if (fileDialog2.getFile() != null) {
                file2 = new File(fileDialog2.getDirectory() + fileDialog2.getFile());
            }
            fileDialog2.hide();
            fileDialog2.dispose();
            frame2.dispose();
            return file2;
        }
    }
    
    public File getSaveFile(final String s) {
        try {
            PolicyEngine.assertPermission(PermissionID.UI);
            final Frame frame = new Frame();
            final FileDialog fileDialog = new FileDialog(frame, s, 1);
            fileDialog.show();
            final File file = new File(fileDialog.getDirectory() + fileDialog.getFile());
            fileDialog.hide();
            fileDialog.dispose();
            frame.dispose();
            return file;
        }
        catch (Throwable t) {
            final Frame frame2 = new Frame();
            final FileDialog fileDialog2 = new FileDialog(frame2, s, 1);
            fileDialog2.show();
            final File file2 = new File(fileDialog2.getDirectory() + fileDialog2.getFile());
            fileDialog2.hide();
            fileDialog2.dispose();
            frame2.dispose();
            return file2;
        }
    }
    
    public File getSaveFile(final String s, final String s2) {
        try {
            PolicyEngine.assertPermission(PermissionID.UI);
            final Frame frame = new Frame();
            final FileDialog fileDialog = new FileDialog(frame, s2, 1);
            fileDialog.setFile(s);
            fileDialog.show();
            final File file = new File(fileDialog.getDirectory() + fileDialog.getFile());
            fileDialog.hide();
            fileDialog.dispose();
            frame.dispose();
            return file;
        }
        catch (Throwable t) {
            final Frame frame2 = new Frame();
            final FileDialog fileDialog2 = new FileDialog(frame2, s2, 1);
            fileDialog2.setFile(s);
            fileDialog2.show();
            final File file2 = new File(fileDialog2.getDirectory() + fileDialog2.getFile());
            fileDialog2.hide();
            fileDialog2.dispose();
            frame2.dispose();
            return file2;
        }
    }
    
    public InetAddress getLocalHost() throws UnknownHostException {
        try {
            PolicyEngine.assertPermission(PermissionID.NETIO);
            final InetAddress[] allByName = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
            return allByName[allByName.length - 1];
        }
        catch (Throwable t) {
            final InetAddress[] allByName2 = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
            return allByName2[allByName2.length - 1];
        }
    }
    
    public String resolve(final InetAddress inetAddress) {
        try {
            PolicyEngine.assertPermission(PermissionID.NETIO);
            return inetAddress.getHostName();
        }
        catch (Throwable t) {
            return inetAddress.getHostName();
        }
    }
    
    public boolean tryProvider() {
        try {
            PolicyEngine.assertPermission(PermissionID.FILEIO);
            PolicyEngine.assertPermission(PermissionID.NETIO);
            PolicyEngine.assertPermission(PermissionID.UI);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public String getName() {
        return "Microsoft Internet Explorer Security Provider";
    }
}
