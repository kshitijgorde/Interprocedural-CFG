// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import com.objectbox.loader.JBSecurityManager;
import java.io.FileDescriptor;
import java.net.InetAddress;

public final class JBSecurityModel implements SecurityManagerIF
{
    private SecurityManagerIF managerobject;
    private static JBSecurityModel low;
    private static JBSecurityModel medium;
    private static JBSecurityModel high;
    
    static {
        JBSecurityModel.low = null;
        JBSecurityModel.medium = null;
        JBSecurityModel.high = null;
    }
    
    private JBSecurityModel() {
        this.managerobject = null;
    }
    
    public boolean checkAccept(final String s, final int n) {
        return this.managerobject != null && this.managerobject.checkAccept(s, n);
    }
    
    public boolean checkAccess(final Thread thread) {
        return this.managerobject != null && this.managerobject.checkAccess(thread);
    }
    
    public boolean checkAccess(final ThreadGroup threadGroup) {
        return this.managerobject != null && this.managerobject.checkAccess(threadGroup);
    }
    
    public boolean checkAwtEventQueueAccess() {
        return this.managerobject != null && this.managerobject.checkAwtEventQueueAccess();
    }
    
    public boolean checkConnect(final String s, final int n) {
        return this.managerobject != null && this.managerobject.checkConnect(s, n);
    }
    
    public boolean checkConnect(final String s, final int n, final Object o) {
        return this.managerobject != null && this.managerobject.checkConnect(s, n, o);
    }
    
    public boolean checkCreateClassLoader() {
        return this.managerobject != null && this.managerobject.checkCreateClassLoader();
    }
    
    public boolean checkDelete(final String s) {
        return this.managerobject != null && this.managerobject.checkDelete(s);
    }
    
    public boolean checkExec(final String s) {
        return this.managerobject != null && this.managerobject.checkExec(s);
    }
    
    public boolean checkExit(final int n) {
        return this.managerobject != null && this.managerobject.checkExit(n);
    }
    
    public boolean checkLink(final String s) {
        return this.managerobject != null && this.managerobject.checkLink(s);
    }
    
    public boolean checkListen(final int n) {
        return this.managerobject != null && this.managerobject.checkListen(n);
    }
    
    public boolean checkMemberAccess(final Class clazz, final int n) {
        return this.managerobject != null && this.managerobject.checkMemberAccess(clazz, n);
    }
    
    public boolean checkMulticast(final InetAddress inetAddress) {
        return this.managerobject != null && this.managerobject.checkMulticast(inetAddress);
    }
    
    public boolean checkMulticast(final InetAddress inetAddress, final byte b) {
        return this.managerobject != null && this.managerobject.checkMulticast(inetAddress, b);
    }
    
    public boolean checkPackageAccess(final String s) {
        return this.managerobject != null && this.managerobject.checkPackageAccess(s);
    }
    
    public boolean checkPackageDefinition(final String s) {
        return this.managerobject != null && this.managerobject.checkPackageDefinition(s);
    }
    
    public boolean checkPrintJobAccess() {
        return this.managerobject != null && this.managerobject.checkPrintJobAccess();
    }
    
    public boolean checkPropertiesAccess() {
        return this.managerobject != null && this.managerobject.checkPropertiesAccess();
    }
    
    public boolean checkPropertyAccess(final String s) {
        return this.managerobject != null && this.managerobject.checkPropertyAccess(s);
    }
    
    public boolean checkPropertyAccess(final String s, final String s2) {
        return this.managerobject != null && this.managerobject.checkPropertyAccess(s, s2);
    }
    
    public boolean checkRead(final FileDescriptor fileDescriptor) {
        return this.managerobject != null && this.managerobject.checkRead(fileDescriptor);
    }
    
    public boolean checkRead(final String s) {
        return this.managerobject != null && this.managerobject.checkRead(s);
    }
    
    public boolean checkRead(final String s, final Object o) {
        return this.managerobject != null && this.managerobject.checkRead(s, o);
    }
    
    public boolean checkSecurityAccess(final String s) {
        return this.managerobject != null && this.managerobject.checkSecurityAccess(s);
    }
    
    public boolean checkSetFactory() {
        return this.managerobject != null && this.managerobject.checkSetFactory();
    }
    
    public boolean checkSystemClipboardAccess() {
        return this.managerobject != null && this.managerobject.checkSystemClipboardAccess();
    }
    
    public boolean checkTopLevelWindow(final Object o) {
        return this.managerobject != null && this.managerobject.checkTopLevelWindow(o);
    }
    
    public boolean checkWrite(final FileDescriptor fileDescriptor) {
        return this.managerobject != null && this.managerobject.checkWrite(fileDescriptor);
    }
    
    public boolean checkWrite(final String s) {
        return this.managerobject != null && this.managerobject.checkWrite(s);
    }
    
    private static JBSecurityModel getHighSecurityModel() {
        if (JBSecurityModel.high == null) {
            (JBSecurityModel.high = new JBSecurityModel()).setHandler(new JBHighSecurity((JBSecurityManager)System.getSecurityManager()));
        }
        return JBSecurityModel.high;
    }
    
    public int getID() {
        if (this.managerobject != null) {
            return this.managerobject.getID();
        }
        return -1;
    }
    
    private static JBSecurityModel getLowSecurityModel() {
        if (JBSecurityModel.low == null) {
            (JBSecurityModel.low = new JBSecurityModel()).setHandler(new JBNoSecurity((JBSecurityManager)System.getSecurityManager()));
        }
        return JBSecurityModel.low;
    }
    
    private static JBSecurityModel getMediumSecurityModel() {
        if (JBSecurityModel.medium == null) {
            (JBSecurityModel.medium = new JBSecurityModel()).setHandler(new JBMediumSecurity((JBSecurityManager)System.getSecurityManager()));
        }
        return JBSecurityModel.medium;
    }
    
    public static JBSecurityModel getSecurityModel(final int n) {
        JBSecurityModel jbSecurityModel = null;
        switch (n) {
            case 1: {
                jbSecurityModel = getHighSecurityModel();
                break;
            }
            case 2: {
                jbSecurityModel = getMediumSecurityModel();
                break;
            }
            case 3: {
                jbSecurityModel = getLowSecurityModel();
                break;
            }
            default: {
                jbSecurityModel = getHighSecurityModel();
                break;
            }
        }
        return jbSecurityModel;
    }
    
    private void setHandler(final SecurityManagerIF managerobject) {
        this.managerobject = managerobject;
    }
}
