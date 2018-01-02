// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import com.kenai.constantine.platform.Errno;
import org.jruby.ext.posix.util.ExecIt;
import java.io.File;
import org.jruby.ext.posix.util.Chmod;
import org.jruby.ext.posix.util.FieldAccess;
import java.io.FileDescriptor;
import java.lang.reflect.Field;

public class JavaLibCHelper
{
    public static final int STDIN = 0;
    public static final int STDOUT = 1;
    public static final int STDERR = 2;
    private final POSIXHandler handler;
    private final Field fdField;
    private final Field handleField;
    ThreadLocal<Integer> pwIndex;
    
    public JavaLibCHelper(final POSIXHandler handler) {
        this.pwIndex = new ThreadLocal<Integer>() {
            protected Integer initialValue() {
                return 0;
            }
        };
        this.handler = handler;
        this.handleField = FieldAccess.getProtectedField(FileDescriptor.class, "handle");
        this.fdField = FieldAccess.getProtectedField(FileDescriptor.class, "fd");
    }
    
    public int chmod(final String filename, final int mode) {
        return Chmod.chmod(new JavaSecuredFile(filename), Integer.toOctalString(mode));
    }
    
    public int chown(final String filename, final int user, final int group) {
        final ExecIt launcher = new ExecIt(this.handler);
        int chownResult = -1;
        int chgrpResult = -1;
        try {
            if (user != -1) {
                chownResult = launcher.runAndWait("chown", "" + user, filename);
            }
            if (group != -1) {
                chgrpResult = launcher.runAndWait("chgrp ", "" + user, filename);
            }
        }
        catch (Exception ex) {}
        return (chownResult == -1 || chgrpResult == -1) ? 1 : 0;
    }
    
    public int getfd(final FileDescriptor descriptor) {
        if (descriptor == null || this.fdField == null) {
            return -1;
        }
        try {
            return this.fdField.getInt(descriptor);
        }
        catch (SecurityException e) {}
        catch (IllegalArgumentException e2) {}
        catch (IllegalAccessException ex) {}
        return -1;
    }
    
    public long gethandle(final FileDescriptor descriptor) {
        if (descriptor == null || this.handleField == null) {
            return -1L;
        }
        try {
            return this.handleField.getLong(descriptor);
        }
        catch (SecurityException e) {}
        catch (IllegalArgumentException e2) {}
        catch (IllegalAccessException ex) {}
        return -1L;
    }
    
    public String getlogin() {
        return System.getProperty("user.name");
    }
    
    public int getpid() {
        return this.handler.getPID();
    }
    
    public Passwd getpwent() {
        final Passwd retVal = (this.pwIndex.get() == 0) ? new JavaPasswd(this.handler) : null;
        this.pwIndex.set(this.pwIndex.get() + 1);
        return retVal;
    }
    
    public int setpwent() {
        return 0;
    }
    
    public int endpwent() {
        this.pwIndex.set(0);
        return 0;
    }
    
    public Passwd getpwuid(final int which) {
        return (which == JavaPOSIX.LoginInfo.UID) ? new JavaPasswd(this.handler) : null;
    }
    
    public int isatty(final int fd) {
        return (fd == 1 || fd == 0 || fd == 2) ? 1 : 0;
    }
    
    public int link(final String oldpath, final String newpath) {
        try {
            return new ExecIt(this.handler).runAndWait("ln", oldpath, newpath);
        }
        catch (Exception e) {
            return -1;
        }
    }
    
    public int lstat(final String path, final FileStat stat) {
        final File file = new JavaSecuredFile(path);
        if (!file.exists()) {
            this.handler.error(Errno.ENOENT, path);
        }
        final JavaFileStat jstat = (JavaFileStat)stat;
        jstat.setup(path);
        return 0;
    }
    
    public int mkdir(final String path, final int mode) {
        final File dir = new JavaSecuredFile(path);
        if (!dir.mkdir()) {
            return -1;
        }
        this.chmod(path, mode);
        return 0;
    }
    
    public int stat(final String path, final FileStat stat) {
        final JavaFileStat jstat = (JavaFileStat)stat;
        try {
            final File file = new JavaSecuredFile(path);
            if (!file.exists()) {
                this.handler.error(Errno.ENOENT, path);
            }
            jstat.setup(file.getCanonicalPath());
        }
        catch (IOException ex) {}
        return 0;
    }
    
    public int symlink(final String oldpath, final String newpath) {
        try {
            return new ExecIt(this.handler).runAndWait("ln", "-s", oldpath, newpath);
        }
        catch (Exception e) {
            return -1;
        }
    }
    
    public int readlink(final String oldpath, final ByteBuffer buffer, final int length) throws IOException {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new ExecIt(this.handler).runAndWait(baos, "readlink", oldpath);
            final byte[] bytes = baos.toByteArray();
            if (bytes.length > length || bytes.length == 0) {
                return -1;
            }
            buffer.put(bytes, 0, bytes.length - 1);
            return buffer.position();
        }
        catch (InterruptedException e) {
            return -1;
        }
    }
}
