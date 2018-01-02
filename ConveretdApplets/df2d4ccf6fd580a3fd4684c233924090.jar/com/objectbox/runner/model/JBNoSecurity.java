// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.io.FileDescriptor;
import java.net.InetAddress;
import com.objectbox.loader.JBSecurityManager;

public class JBNoSecurity implements SecurityManagerIF
{
    transient JBSecurityManager sm;
    static final long serialVersionUID = -123456789L;
    
    public JBNoSecurity(final JBSecurityManager sm) {
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
        return true;
    }
    
    public boolean checkConnect(final String s, final int n) {
        return true;
    }
    
    public boolean checkConnect(final String s, final int n, final Object o) {
        return true;
    }
    
    public boolean checkCreateClassLoader() {
        return true;
    }
    
    public boolean checkDelete(final String s) {
        return true;
    }
    
    public boolean checkExec(final String s) {
        return true;
    }
    
    public boolean checkExit(final int n) {
        return true;
    }
    
    public boolean checkLink(final String s) {
        return true;
    }
    
    public boolean checkListen(final int n) {
        return true;
    }
    
    public boolean checkMemberAccess(final Class clazz, final int n) {
        return true;
    }
    
    public boolean checkMulticast(final InetAddress inetAddress) {
        return true;
    }
    
    public boolean checkMulticast(final InetAddress inetAddress, final byte b) {
        return true;
    }
    
    public boolean checkPackageAccess(final String s) {
        return true;
    }
    
    public boolean checkPackageDefinition(final String s) {
        return true;
    }
    
    public boolean checkPrintJobAccess() {
        return true;
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
        return true;
    }
    
    public boolean checkRead(final String s, final Object o) {
        return true;
    }
    
    public boolean checkSecurityAccess(final String s) {
        return true;
    }
    
    public boolean checkSetFactory() {
        return true;
    }
    
    public boolean checkSystemClipboardAccess() {
        return true;
    }
    
    public boolean checkTopLevelWindow(final Object o) {
        return true;
    }
    
    public boolean checkWrite(final FileDescriptor fileDescriptor) {
        return true;
    }
    
    public boolean checkWrite(final String s) {
        return true;
    }
    
    public int getID() {
        return 3;
    }
    
    public void initstate() {
    }
}
