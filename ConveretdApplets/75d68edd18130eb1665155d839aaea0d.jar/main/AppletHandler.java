// 
// Decompiled by Procyon v0.5.30
// 

package main;

import java.security.Permission;
import java.security.PrivilegedAction;
import javax.swing.SwingUtilities;
import ui.FileDialogThread;
import org.json.JSONException;
import java.security.PrivilegedActionException;
import jsonutil.JSONUtil;
import java.security.PrivilegedExceptionAction;
import java.security.AccessController;
import action.FileStationHandler;
import netscape.javascript.JSObject;
import ui.FileDialog;
import java.io.File;
import action.upload.UploadTaskManeger;
import java.applet.Applet;

public class AppletHandler extends Applet
{
    private UploadTaskManeger _UTaskManeger;
    private File _LastSelectedFile;
    
    public AppletHandler() {
        this._UTaskManeger = null;
        this._LastSelectedFile = null;
    }
    
    public void init() {
    }
    
    public void start() {
        FileDialog.initDialog(this);
        this.runApplet();
    }
    
    private void runApplet() {
        try {
            final boolean checkPermission = this.checkPermission();
            if (checkPermission) {
                this._UTaskManeger = new UploadTaskManeger(this);
            }
            JSObject.getWindow((Applet)this).eval("AppletProgram.setJavaPermission(" + checkPermission + ");");
        }
        catch (Exception ex) {
            FileStationHandler.log(ex);
        }
    }
    
    public void destroy() {
    }
    
    public void stop() {
    }
    
    public Object action(final String s) throws JSONException {
        final FileStationHandler fileStationHandler = new FileStationHandler(s, this._UTaskManeger);
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<Object>)fileStationHandler);
        }
        catch (PrivilegedActionException ex) {
            FileStationHandler.log(ex);
            return JSONUtil.setError("error", "error_privilege_not_enough");
        }
        catch (Exception ex2) {
            FileStationHandler.log(ex2);
            return JSONUtil.setError("error", "error_system_busy");
        }
    }
    
    public void showFileDialog(final String s, final boolean b) {
        SwingUtilities.invokeLater(new FileDialogThread(this, s, b));
    }
    
    public File getLastSelectedFile() {
        return this._LastSelectedFile;
    }
    
    public void setLastSelectedFile(final File lastSelectedFile) {
        this._LastSelectedFile = lastSelectedFile;
    }
    
    public boolean checkPermission() {
        final Boolean b = new Boolean("false");
        Boolean b2;
        try {
            b2 = AccessController.doPrivileged((PrivilegedAction<Boolean>)new PrivilegedAction<Boolean>() {
                public Boolean run() {
                    try {
                        final SecurityManager securityManager = System.getSecurityManager();
                        if (securityManager != null) {
                            securityManager.checkRead("<<ALL FILES>>");
                            securityManager.checkPermission(new RuntimePermission("getFileSystemAttributes"));
                        }
                        if (System.getProperty("java.version").indexOf("1.4") != -1) {
                            return false;
                        }
                        System.setProperty("java.net.useSystemProxies", "true");
                    }
                    catch (SecurityException ex) {
                        return false;
                    }
                    return true;
                }
            });
        }
        catch (Exception ex) {
            FileStationHandler.log(ex);
            return false;
        }
        return b2;
    }
    
    public boolean isRunning() {
        return true;
    }
}
