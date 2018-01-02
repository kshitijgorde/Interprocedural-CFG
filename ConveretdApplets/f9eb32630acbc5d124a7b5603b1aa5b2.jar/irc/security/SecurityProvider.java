// 
// Decompiled by Procyon v0.5.30
// 

package irc.security;

import java.net.InetAddress;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.IOException;
import irc.EventDispatcher;
import java.net.Socket;
import java.awt.event.ActionListener;

public class SecurityProvider implements ActionListener
{
    private SecuredProvider _provider;
    private boolean _answer;
    
    public SecurityProvider() {
        if (this.tryProvider("MS")) {
            return;
        }
        this._provider = new DefaultSecuredProvider();
    }
    
    private boolean tryProvider(final String s) {
        final SecuredProvider provider = this._provider;
        try {
            this._provider = (SecuredProvider)Class.forName("irc.security.prv.Specific" + s + "SecuredProvider").newInstance();
            if (!this._provider.tryProvider()) {
                throw new Exception();
            }
            return true;
        }
        catch (Exception ex) {
            this._provider = provider;
            return false;
        }
    }
    
    public String getProviderName() {
        return this._provider.getName();
    }
    
    public Socket getSocket(final String s, final int n) throws UnknownHostException, IOException {
        try {
            return (Socket)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getSocket", new Object[] { s, new Integer(n) });
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted");
        }
        catch (Throwable t) {
            throw new IOException(t.getClass().getName() + " : " + t.getMessage());
        }
    }
    
    public ServerSocket getServerSocket(final int n) throws IOException {
        try {
            return (ServerSocket)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getServerSocket", new Object[] { new Integer(n) });
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted");
        }
        catch (Throwable t) {
            throw new IOException(t.getClass().getName() + " : " + t.getMessage());
        }
    }
    
    public boolean confirm(final Frame frame, final String s, final String s2) {
        Frame frame2 = null;
        Dialog dialog;
        if (frame == null) {
            frame2 = new Frame();
            dialog = new Dialog(frame2, s, true);
        }
        else {
            dialog = new Dialog(frame, s, true);
        }
        dialog.setLayout(new BorderLayout());
        dialog.add(new Label(s2), "Center");
        final Button button = new Button("Yes");
        final Button button2 = new Button("No");
        final Panel panel = new Panel();
        dialog.setResizable(false);
        dialog.add(panel, "South");
        panel.add(button);
        panel.add(button2);
        button.addActionListener(this);
        button2.addActionListener(this);
        dialog.pack();
        this._answer = false;
        dialog.show();
        button.removeActionListener(this);
        button2.removeActionListener(this);
        dialog.hide();
        dialog.dispose();
        if (frame2 != null) {
            frame2.hide();
            frame2.dispose();
        }
        return this._answer;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Button button = (Button)actionEvent.getSource();
        this._answer = false;
        if (button.getLabel().equals("Yes")) {
            this._answer = true;
        }
        ((Window)button.getParent().getParent()).hide();
    }
    
    public FileInputStream getFileInputStream(final File file) throws IOException {
        if (!this.confirm(null, "Security warning", "Authorize file read action on " + file + "?")) {
            throw new IOException("User denied access");
        }
        try {
            return (FileInputStream)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getFileInputStream", new Object[] { file });
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted");
        }
        catch (Throwable t) {
            throw new IOException(t.getMessage());
        }
    }
    
    public FileOutputStream getFileOutputStream(final File file) throws IOException {
        if (!this.confirm(null, "Security warning", "Authorize file write action on " + file + "?")) {
            throw new IOException("User denied access");
        }
        try {
            return (FileOutputStream)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getFileOutputStream", new Object[] { file });
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted");
        }
        catch (Throwable t) {
            throw new IOException(t.getMessage());
        }
    }
    
    public int getFileSize(final File file) {
        try {
            return (int)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getFileSize", new Object[] { file });
        }
        catch (Throwable t) {
            return -1;
        }
    }
    
    public File getLoadFile(final String s) {
        try {
            return (File)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getLoadFile", new Object[] { s });
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public File getSaveFile(final String s) {
        try {
            return (File)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getSaveFile", new Object[] { s });
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public File getSaveFile(final String s, final String s2) {
        try {
            return (File)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getSaveFile", new Object[] { s, s2 });
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public InetAddress getLocalHost() throws UnknownHostException {
        try {
            return (InetAddress)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "getLocalHost", new Object[0]);
        }
        catch (InterruptedException ex) {
            throw new UnknownHostException("Unable to resolve");
        }
        catch (Throwable t) {
            throw new UnknownHostException(t.getMessage());
        }
    }
    
    public String resolve(final InetAddress inetAddress) {
        try {
            return (String)EventDispatcher.dispatchEventAsyncAndWaitExSecurity(this._provider, "resolve", new Object[] { inetAddress });
        }
        catch (Throwable t) {
            return null;
        }
    }
}
