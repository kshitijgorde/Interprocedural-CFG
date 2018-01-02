// 
// Decompiled by Procyon v0.5.30
// 

package java.lang;

import java.net.URL;
import java.net.InetAddress;
import java.io.FileDescriptor;

public abstract class SecurityManager
{
    public void checkWrite(final String s) {
    }
    
    public Object getSecurityContext() {
        return null;
    }
    
    public void checkWrite(final FileDescriptor fileDescriptor) {
    }
    
    public void checkExit(final int n) {
    }
    
    public static void resetScopePermission() {
    }
    
    public ThreadGroup getThreadGroup() {
        return null;
    }
    
    public void checkSystemClipboardAccess() {
    }
    
    public synchronized boolean getInCheck() {
        return false;
    }
    
    public void checkExec(final String s) {
    }
    
    public void checkMulticast(final InetAddress inetAddress, final byte b) {
    }
    
    public void checkPropertiesAccess() {
    }
    
    public static void setScopePermission() {
    }
    
    public void checkConnect(final String s, final int n, final Object o) {
    }
    
    public void checkPackageDefinition(final String s) {
    }
    
    public void checkPackageAccess(final String s) {
    }
    
    public static void setSecurityManager() {
    }
    
    public void checkSetFactory() {
    }
    
    public void checkSecurityAccess(final String s) {
    }
    
    public static boolean checkScopePermission(final int n) {
        return false;
    }
    
    public void checkRead(final String s) {
    }
    
    public void checkAwtEventQueueAccess() {
    }
    
    public static boolean isPrivilegeEnabled(final String s) {
        return false;
    }
    
    public void checkAccess(final Thread thread, final Throwable t) {
    }
    
    public void checkURLConnect(final URL url) {
    }
    
    public static void enablePrivilege(final String s) {
    }
    
    public void checkRead(final FileDescriptor fileDescriptor) {
    }
    
    public void checkListen(final int n) {
    }
    
    public static boolean checksMatchPrincipalAlways(final int n) {
        return false;
    }
    
    public static void revertPrivilege() {
    }
    
    public boolean checkTopLevelWindow(final Object o) {
        return false;
    }
    
    public void checkCreateClassLoader() {
    }
    
    public void checkPrintJobAccess() {
    }
    
    public boolean checkMatchPrincipalAlways(final int n) {
        return false;
    }
    
    public void checkCreateSecurityManagerAccess() {
    }
    
    public void checkAccess(final ThreadGroup threadGroup) {
    }
    
    public void checkAccept(final String s, final int n) {
    }
    
    public void checkDelete(final String s) {
    }
    
    public void checkLink(final String s) {
    }
    
    public void checkRead(final String s, final Object o) {
    }
    
    public void checkURLConnect(final URL url, final URL url2) {
    }
    
    public void checkConnect(final String s, final int n) {
    }
    
    public void checkMemberAccess(final Class clazz, final int n) {
    }
    
    public void checkAccess(final Thread thread) {
    }
    
    public void checkPropertyAccess(final String s) {
    }
    
    public void checkMulticast(final InetAddress inetAddress) {
    }
}
