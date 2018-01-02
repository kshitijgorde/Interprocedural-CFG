// 
// Decompiled by Procyon v0.5.30
// 

package irc.security;

import java.net.InetAddress;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;

public class DefaultSecuredProvider implements SecuredProvider
{
    public Socket getSocket(final String s, final Integer n) throws UnknownHostException, IOException {
        return new Socket(s, n);
    }
    
    public ServerSocket getServerSocket(final Integer n) throws IOException {
        return new ServerSocket(n);
    }
    
    public FileInputStream getFileInputStream(final File file) throws IOException {
        return new FileInputStream(file);
    }
    
    public FileOutputStream getFileOutputStream(final File file) throws IOException {
        return new FileOutputStream(file);
    }
    
    public Integer getFileSize(final File file) {
        return new Integer((int)file.length());
    }
    
    public File getLoadFile(final String s) {
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
    
    public File getSaveFile(final String s) {
        final Frame frame = new Frame();
        final FileDialog fileDialog = new FileDialog(frame, s, 1);
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
    
    public File getSaveFile(final String file, final String s) {
        final Frame frame = new Frame();
        final FileDialog fileDialog = new FileDialog(frame, s, 1);
        fileDialog.setFile(file);
        fileDialog.show();
        File file2 = null;
        if (fileDialog.getFile() != null) {
            file2 = new File(fileDialog.getDirectory() + fileDialog.getFile());
        }
        fileDialog.hide();
        fileDialog.dispose();
        frame.dispose();
        return file2;
    }
    
    public InetAddress getLocalHost() throws UnknownHostException {
        final InetAddress[] allByName = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
        return allByName[allByName.length - 1];
    }
    
    public String resolve(final InetAddress inetAddress) {
        return inetAddress.getHostName();
    }
    
    public boolean tryProvider() {
        return true;
    }
    
    public String getName() {
        return "Default Security Provider";
    }
}
