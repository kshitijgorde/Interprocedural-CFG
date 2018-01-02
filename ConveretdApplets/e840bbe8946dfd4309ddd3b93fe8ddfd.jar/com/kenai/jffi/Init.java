// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.util.Arrays;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

final class Init
{
    private static final String bootPropertyFilename = "boot.properties";
    private static final String bootLibraryPropertyName = "jffi.boot.library.path";
    private static final String stubLibraryName;
    private static volatile boolean loaded;
    
    static final void load() {
        if (Init.loaded) {
            return;
        }
        final String libName = getStubLibraryName();
        final String bootPath = getBootPath();
        if (bootPath != null && loadFromBootPath(libName, bootPath)) {
            return;
        }
        try {
            System.loadLibrary(libName);
        }
        catch (UnsatisfiedLinkError ex) {
            loadFromJar();
        }
    }
    
    private static final String getBootPath() {
        final String bootPath = System.getProperty("jffi.boot.library.path");
        if (bootPath != null) {
            return bootPath;
        }
        final InputStream is = Init.class.getResourceAsStream("boot.properties");
        if (is != null) {
            final Properties p = new Properties();
            try {
                p.load(is);
                return p.getProperty("jffi.boot.library.path");
            }
            catch (IOException ex) {
                try {
                    is.close();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            finally {
                try {
                    is.close();
                }
                catch (IOException ex2) {
                    throw new RuntimeException(ex2);
                }
            }
        }
        return null;
    }
    
    private static final boolean loadFromBootPath(final String libName, final String bootPath) {
        final String[] dirs = bootPath.split(File.pathSeparator);
        int i = 0;
        while (i < dirs.length) {
            final String path = new File(new File(dirs[i]), System.mapLibraryName(libName)).getAbsolutePath();
            try {
                System.load(path);
                return true;
            }
            catch (UnsatisfiedLinkError ex) {
                if (Platform.getPlatform().getOS() == Platform.OS.DARWIN) {
                    String orig;
                    String ext;
                    if (path.endsWith("dylib")) {
                        orig = "dylib";
                        ext = "jnilib";
                    }
                    else {
                        orig = "jnilib";
                        ext = "dylib";
                    }
                    try {
                        System.load(path.substring(0, path.lastIndexOf(orig)) + ext);
                        return true;
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
                }
                ++i;
                continue;
            }
            break;
        }
        return false;
    }
    
    private static final void loadFromJar() {
        final InputStream is = getStubLibraryStream();
        File dstFile = null;
        FileOutputStream os = null;
        try {
            dstFile = File.createTempFile("jffi", null);
            dstFile.deleteOnExit();
            os = new FileOutputStream(dstFile);
            final ReadableByteChannel srcChannel = Channels.newChannel(is);
            long pos = 0L;
            while (is.available() > 0) {
                pos += os.getChannel().transferFrom(srcChannel, pos, Math.max(4096, is.available()));
            }
        }
        catch (IOException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
        finally {
            try {
                if (os != null) {
                    os.close();
                }
                is.close();
            }
            catch (IOException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        System.load(dstFile.getAbsolutePath());
    }
    
    private static final InputStream getStubLibraryStream() {
        final String stubPath = getStubLibraryPath();
        final ClassLoader[] cls = { Init.class.getClassLoader(), Thread.currentThread().getContextClassLoader() };
        InputStream is = null;
        final String[] paths = { stubPath, "/" + stubPath };
    Label_0175:
        for (final ClassLoader cl : cls) {
            if (cl != null) {
                for (final String path : paths) {
                    is = cl.getResourceAsStream(path);
                    if (is == null && Platform.getPlatform().getOS() == Platform.OS.DARWIN) {
                        is = cl.getResourceAsStream(path.replaceAll("dylib", "jnilib"));
                    }
                    if (is != null) {
                        break Label_0175;
                    }
                }
            }
        }
        if (is == null) {
            throw new UnsatisfiedLinkError("Could not locate stub library in jar file.  Tried " + Arrays.deepToString(paths));
        }
        return is;
    }
    
    private static final String getStubLibraryName() {
        return Init.stubLibraryName;
    }
    
    private static final String getStubLibraryPath() {
        return "jni/" + Platform.getPlatform().getName() + "/" + System.mapLibraryName(Init.stubLibraryName);
    }
    
    static {
        stubLibraryName = String.format("jffi-%d.%d", Foreign.VERSION_MAJOR, Foreign.VERSION_MINOR);
        Init.loaded = false;
    }
}
