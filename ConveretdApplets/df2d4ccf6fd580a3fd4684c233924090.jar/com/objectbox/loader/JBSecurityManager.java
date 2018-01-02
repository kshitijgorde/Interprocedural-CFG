// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.loader;

import com.objectbox.runner.util.JBLogger;
import java.io.FileDescriptor;
import java.net.InetAddress;
import com.objectbox.runner.model.SecurityManagerIF;
import com.objectbox.runner.gui.JBee;

public class JBSecurityManager extends SecurityManager
{
    protected transient SecurityViolationListener aSecurityViolationListener;
    
    public void addSecurityViolationListener(final SecurityViolationListener securityViolationListener) {
        this.aSecurityViolationListener = SecurityViolationEventMulticaster.add(this.aSecurityViolationListener, securityViolationListener);
    }
    
    public void checkAccept(final String s, final int n) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkAccept failed (faulty securityhandler)");
            }
            if (!securityHandler.checkAccept(s, n)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkAccept failed"));
                throw new SecurityException("checkAccept for " + s + ":" + n + " failed");
            }
        }
    }
    
    public void checkAccess(final Thread thread) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkAccess(thread)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkAccess failed"));
                throw new SecurityException("checkAccess failed");
            }
        }
    }
    
    public void checkAccess(final ThreadGroup threadGroup) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkAccess(threadGroup)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkAccess failed"));
                throw new SecurityException("checkAccess failed");
            }
        }
    }
    
    public void checkAwtEventQueueAccess() {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkAwtEventQueueAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkAwtEventQueueAccess()) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkAwtEventQueueAccess failed"));
                throw new SecurityException("checkAwtEventQueueAccess failed");
            }
        }
    }
    
    public void checkConnect(final String s, final int n) {
        try {
            final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
            super.inCheck = true;
            if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
                final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
                if (securityHandler == null) {
                    throw new SecurityException("checkConnect failed (faulty securityhandler)");
                }
                if (!securityHandler.checkConnect(s, n)) {
                    this.fireHandleSecurityViolation(new SecurityViolationEvent("checkConnect failed"));
                    throw new SecurityException("checkConnect failed");
                }
            }
        }
        finally {
            super.inCheck = false;
        }
        super.inCheck = false;
    }
    
    public void checkConnect(final String s, final int n, final Object o) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkConnect failed (faulty securityhandler)");
            }
            if (!securityHandler.checkConnect(s, n, o)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkConnect failed"));
                throw new SecurityException("checkConnect failed");
            }
        }
    }
    
    public void checkCreateClassLoader() {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkCreateClassLoader failed (faulty securityhandler)");
            }
            if (!securityHandler.checkCreateClassLoader()) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkCreateClassLoader failed"));
                throw new SecurityException("checkCreateClassLoader failed");
            }
        }
    }
    
    public void checkDelete(final String s) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkDelete failed (faulty securityhandler)");
            }
            if (!securityHandler.checkDelete(s)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkDelete failed"));
                throw new SecurityException("checkDelete failed");
            }
        }
    }
    
    public void checkExec(final String s) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkExec failed (faulty securityhandler)");
            }
            if (!securityHandler.checkExec(s)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkExec failed"));
                throw new SecurityException("checkExec failed");
            }
        }
    }
    
    public void checkExit(final int n) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkExit failed (faulty securityhandler)");
            }
            if (!securityHandler.checkExit(n)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkExit failed"));
                throw new SecurityException("checkExit failed");
            }
        }
    }
    
    public void checkLink(final String s) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkLink failed (faulty securityhandler)");
            }
            if (!securityHandler.checkLink(s)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkLink failed"));
                throw new SecurityException("checkLink failed");
            }
        }
    }
    
    public void checkListen(final int n) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkListen failed (faulty securityhandler)");
            }
            if (!securityHandler.checkListen(n)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkListen failed"));
                throw new SecurityException("checkListen failed");
            }
        }
    }
    
    public void checkMemberAccess(final Class clazz, final int n) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("No member access (faulty securityhandler)");
            }
            if (!securityHandler.checkMemberAccess(clazz, n)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkMemberAccess failed"));
                throw new SecurityException("No member access");
            }
        }
    }
    
    public void checkMulticast(final InetAddress inetAddress) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkMulticast failed (faulty securityhandler)");
            }
            if (!securityHandler.checkMulticast(inetAddress)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkMulticast failed"));
                throw new SecurityException("checkMulticast failed");
            }
        }
    }
    
    public void checkMulticast(final InetAddress inetAddress, final byte b) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkMulticast failed (faulty securityhandler)");
            }
            if (!securityHandler.checkMulticast(inetAddress, b)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkMulticast failed"));
                throw new SecurityException("checkMulticast failed");
            }
        }
    }
    
    public void checkPackageAccess(final String s) {
        if (!s.startsWith("com.objectbox.runner") || !s.startsWith("com.objectbox.loader")) {
            return;
        }
        throw new SecurityException("checkPackageAccess failed");
    }
    
    public void checkPackageDefinition(final String s) {
        if (!s.startsWith("java.")) {
            return;
        }
        if (!s.startsWith("com.objectbox.runner") || !s.startsWith("com.objectbox.loader")) {
            return;
        }
        throw new SecurityException("checkPackageDefinition failed");
    }
    
    public void checkPrintJobAccess() {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkPrintJobAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkPrintJobAccess()) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkPrintJobAccess failed"));
                throw new SecurityException("checkPrintJobAccess failed");
            }
        }
    }
    
    public void checkPropertiesAccess() {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkPropertiesAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkPropertiesAccess()) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkPropertiesAccess failed"));
                throw new SecurityException("checkPropertiesAccess failed");
            }
        }
    }
    
    public void checkPropertyAccess(final String s) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkPropertyAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkPropertyAccess(s)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkPropertyAccess failed"));
                throw new SecurityException("checkPropertyAccess failed");
            }
        }
    }
    
    public void checkPropertyAccess(final String s, final String s2) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkPropertyAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkPropertyAccess(s, s2)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkPropertyAccess failed"));
                throw new SecurityException("checkPropertyAccess failed");
            }
        }
    }
    
    public void checkRead(final FileDescriptor fileDescriptor) {
        if (this.inClass("com.objectbox.runner.applet.OBContext")) {
            return;
        }
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("No read access (faulty securityhandler)");
            }
            if (!securityHandler.checkRead(fileDescriptor)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkRead failed"));
                throw new SecurityException("No read access");
            }
        }
    }
    
    public void checkRead(final String s) {
        if (this.inClass("com.objectbox.runner.applet.OBContext")) {
            return;
        }
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("No read access (faulty securityhandler)");
            }
            if (!securityHandler.checkRead(s)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkRead failed"));
                throw new SecurityException("No read access");
            }
        }
    }
    
    public void checkRead(final String s, final Object o) {
        if (this.inClass("com.objectbox.runner.applet.OBContext")) {
            return;
        }
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("No read access (faulty securityhandler)");
            }
            if (!securityHandler.checkRead(s, o)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkRead failed"));
                throw new SecurityException("No read access");
            }
        }
    }
    
    public void checkSecurityAccess(final String s) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkSecurityAccess failed (faulty securityhandler)");
            }
            if (!securityHandler.checkSecurityAccess(s)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkSecurityAccess failed"));
                throw new SecurityException("checkSecurityAccess failed");
            }
        }
    }
    
    public void checkSetFactory() {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("checkSetFactory failed (faulty securityhandler)");
            }
            if (!securityHandler.checkSetFactory()) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkSetFactory failed"));
                throw new SecurityException("checkSetFactory failed");
            }
        }
    }
    
    public void checkSystemClipboardAccess() {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("No clipboard access(faulty securityhandler)");
            }
            if (!securityHandler.checkSystemClipboardAccess()) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkSystemClipboardAccess failed"));
                throw new SecurityException("No clipboard access");
            }
        }
    }
    
    public boolean checkTopLevelWindow(final Object o) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                System.err.println("Top level window not allowed");
                return false;
            }
            if (!securityHandler.checkTopLevelWindow(o)) {
                System.err.println("Top level window not allowed");
                return false;
            }
        }
        return true;
    }
    
    public void checkWrite(final FileDescriptor fileDescriptor) {
        if (this.inClass("com.objectbox.runner.applet.OBContext")) {
            return;
        }
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("No write access (faulty securityhandler)");
            }
            if (!securityHandler.checkWrite(fileDescriptor)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkWrite failed"));
                throw new SecurityException("No write access");
            }
        }
    }
    
    public void checkWrite(final String s) {
        if (this.inClass("com.objectbox.runner.applet.OBContext")) {
            return;
        }
        final JBURLClassloader jburlClassloader = (JBURLClassloader)this.currentClassLoader();
        this.classLoaderDepth();
        if (jburlClassloader != null && jburlClassloader.getRootclass().compareTo("com.objectbox.runner.gui.JBee") != 0) {
            final SecurityManagerIF securityHandler = JBee.getSecurityHandler(jburlClassloader.getSecurityID());
            if (securityHandler == null) {
                throw new SecurityException("No write access (faulty securityhandler)");
            }
            if (!securityHandler.checkWrite(s)) {
                this.fireHandleSecurityViolation(new SecurityViolationEvent("checkWrite failed"));
                throw new SecurityException("No write access");
            }
        }
    }
    
    protected void fireHandleSecurityViolation(final SecurityViolationEvent securityViolationEvent) {
        if (this.aSecurityViolationListener == null) {
            return;
        }
        this.aSecurityViolationListener.handleSecurityViolation(securityViolationEvent);
    }
    
    public void info() {
        JBLogger.log("--Context:" + this.getSecurityContext());
        final Class[] classContext = this.getClassContext();
        for (int i = 0; i < classContext.length; ++i) {
            JBLogger.log(String.valueOf(this.space(i)) + classContext[i]);
        }
        JBLogger.log((this.currentClassLoader() == null) ? "system classloader" : this.currentClassLoader().toString());
    }
    
    public JBURLClassloader preinit(final SecurityManagerIF securityManagerIF) {
        if (this.currentClassLoader() != null) {
            return (JBURLClassloader)this.currentClassLoader();
        }
        return null;
    }
    
    public void removeSecurityViolationListener(final SecurityViolationListener securityViolationListener) {
        this.aSecurityViolationListener = SecurityViolationEventMulticaster.remove(this.aSecurityViolationListener, securityViolationListener);
    }
    
    public String space(final int n) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    public JBSecurityManager() {
        this.aSecurityViolationListener = null;
    }
}
