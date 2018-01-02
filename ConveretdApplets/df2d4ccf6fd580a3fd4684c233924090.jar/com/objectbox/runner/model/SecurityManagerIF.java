// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.io.Serializable;

public interface SecurityManagerIF extends Serializable
{
    boolean checkAccept(final String p0, final int p1);
    
    boolean checkAccess(final Thread p0);
    
    boolean checkAccess(final ThreadGroup p0);
    
    boolean checkAwtEventQueueAccess();
    
    boolean checkConnect(final String p0, final int p1);
    
    boolean checkConnect(final String p0, final int p1, final Object p2);
    
    boolean checkCreateClassLoader();
    
    boolean checkDelete(final String p0);
    
    boolean checkExec(final String p0);
    
    boolean checkExit(final int p0);
    
    boolean checkLink(final String p0);
    
    boolean checkListen(final int p0);
    
    boolean checkMemberAccess(final Class p0, final int p1);
    
    boolean checkMulticast(final InetAddress p0);
    
    boolean checkMulticast(final InetAddress p0, final byte p1);
    
    boolean checkPackageAccess(final String p0);
    
    boolean checkPackageDefinition(final String p0);
    
    boolean checkPrintJobAccess();
    
    boolean checkPropertiesAccess();
    
    boolean checkPropertyAccess(final String p0);
    
    boolean checkPropertyAccess(final String p0, final String p1);
    
    boolean checkRead(final FileDescriptor p0);
    
    boolean checkRead(final String p0);
    
    boolean checkRead(final String p0, final Object p1);
    
    boolean checkSecurityAccess(final String p0);
    
    boolean checkSetFactory();
    
    boolean checkSystemClipboardAccess();
    
    boolean checkTopLevelWindow(final Object p0);
    
    boolean checkWrite(final FileDescriptor p0);
    
    boolean checkWrite(final String p0);
    
    int getID();
}
