// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.util.Hashtable;
import com.objectbox.runner.gui.JBee;
import java.io.FileDescriptor;
import java.net.UnknownHostException;
import java.net.InetAddress;
import com.objectbox.loader.JBSecurityManager;
import java.util.Properties;

public class JBHighSecurity implements SecurityManagerIF
{
    transient Properties securitysettings;
    transient JBSecurityManager sm;
    static final long serialVersionUID = -123456789L;
    
    public JBHighSecurity(final JBSecurityManager sm) {
        this.securitysettings = new Properties();
        this.sm = null;
        this.sm = sm;
        this.initstate();
    }
    
    public boolean checkAccept(final String s, final int n) {
        return true;
    }
    
    public boolean checkAccess(final Thread thread) {
        return true;
    }
    
    public boolean checkAccess(final ThreadGroup threadGroup) {
        return true;
    }
    
    public boolean checkAwtEventQueueAccess() {
        return false;
    }
    
    public boolean checkConnect(final String s, final int n) {
        final String server = this.sm.preinit(this).getServer();
        if (s.equals(server)) {
            return true;
        }
        try {
            if (InetAddress.getByName(s).equals(InetAddress.getByName(server))) {
                return true;
            }
        }
        catch (UnknownHostException ex) {}
        return false;
    }
    
    public boolean checkConnect(final String s, final int n, final Object o) {
        final String server = this.sm.preinit(this).getServer();
        if (s.equals(server)) {
            return true;
        }
        try {
            if (InetAddress.getByName(s).equals(InetAddress.getByName(server))) {
                return true;
            }
        }
        catch (UnknownHostException ex) {}
        return false;
    }
    
    public boolean checkCreateClassLoader() {
        return false;
    }
    
    public boolean checkDelete(final String s) {
        return false;
    }
    
    public boolean checkExec(final String s) {
        return false;
    }
    
    public boolean checkExit(final int n) {
        return false;
    }
    
    public boolean checkLink(final String s) {
        return s.equals("mmedia");
    }
    
    public boolean checkListen(final int n) {
        return false;
    }
    
    public boolean checkMemberAccess(final Class clazz, final int n) {
        return true;
    }
    
    public boolean checkMulticast(final InetAddress inetAddress) {
        return false;
    }
    
    public boolean checkMulticast(final InetAddress inetAddress, final byte b) {
        return false;
    }
    
    public boolean checkPackageAccess(final String s) {
        return false;
    }
    
    public boolean checkPackageDefinition(final String s) {
        return false;
    }
    
    public boolean checkPrintJobAccess() {
        return false;
    }
    
    public boolean checkPropertiesAccess() {
        return true;
    }
    
    public boolean checkPropertyAccess(final String s) {
        return true;
    }
    
    public boolean checkPropertyAccess(final String s, final String s2) {
        return true;
    }
    
    public boolean checkRead(final FileDescriptor fileDescriptor) {
        return true;
    }
    
    public boolean checkRead(final String s) {
        return false;
    }
    
    public boolean checkRead(final String s, final Object o) {
        return false;
    }
    
    public boolean checkSecurityAccess(final String s) {
        return false;
    }
    
    public boolean checkSetFactory() {
        return false;
    }
    
    public boolean checkSystemClipboardAccess() {
        return false;
    }
    
    public boolean checkTopLevelWindow(final Object o) {
        return true;
    }
    
    public boolean checkWrite(final FileDescriptor fileDescriptor) {
        return true;
    }
    
    public boolean checkWrite(final String s) {
        return false;
    }
    
    public int getID() {
        return 1;
    }
    
    public void initstate() {
        ((Hashtable<String, String>)this.securitysettings).put("AcceptHosts", "*");
        ((Hashtable<String, String>)this.securitysettings).put("ThreadAccess", "*");
        ((Hashtable<String, String>)this.securitysettings).put("AwtEventQueueAccess", "Yes");
        ((Hashtable<String, String>)this.securitysettings).put("Connect", "");
        ((Hashtable<String, String>)this.securitysettings).put("CreateClassLoader", "No");
        final String preference = JBee.getPreference("javabee_home");
        ((Hashtable<String, String>)this.securitysettings).put("SafeDirectories", (preference == null) ? "" : preference);
        ((Hashtable<String, String>)this.securitysettings).put("Exit", "");
        ((Hashtable<String, String>)this.securitysettings).put("Link", "");
        ((Hashtable<String, String>)this.securitysettings).put("Listen", "");
        ((Hashtable<String, String>)this.securitysettings).put("MemberAccess", "");
        ((Hashtable<String, String>)this.securitysettings).put("Multicast", "");
        ((Hashtable<String, String>)this.securitysettings).put("PackageAccess", "");
        ((Hashtable<String, String>)this.securitysettings).put("PackageDefinition", "");
        ((Hashtable<String, String>)this.securitysettings).put("PrintJobAccess", "");
        ((Hashtable<String, String>)this.securitysettings).put("PropertiesAccess", "");
        ((Hashtable<String, String>)this.securitysettings).put("PropertyAccess", "");
        ((Hashtable<String, String>)this.securitysettings).put("Read", "");
        ((Hashtable<String, String>)this.securitysettings).put("SecurityAccess", "");
        ((Hashtable<String, String>)this.securitysettings).put("SetFactory", "");
        ((Hashtable<String, String>)this.securitysettings).put("SystemClipboardAccess", "");
        ((Hashtable<String, String>)this.securitysettings).put("TopLevelWindow", "");
        ((Hashtable<String, String>)this.securitysettings).put("Write", "");
    }
}
