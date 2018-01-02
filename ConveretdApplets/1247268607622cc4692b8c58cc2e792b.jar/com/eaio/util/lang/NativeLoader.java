// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.util.lang;

import java.io.IOException;
import java.io.FilterInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import irc.main;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;

public class NativeLoader
{
    private static String osPrefix;
    private static String osSuffix;
    private final String name;
    private final String nativeName;
    private boolean attemptedLoading;
    private static boolean loadingSuccessful;
    
    private static void init(final String s, final String s2) {
        if (s.startsWith(Messages.getString("NativeLoader.2"))) {
            NativeLoader.osSuffix = Messages.getString("NativeLoader.3");
        }
        else if (s.equals(Messages.getString("NativeLoader.4"))) {
            NativeLoader.osPrefix = Messages.getString("NativeLoader.5");
            NativeLoader.osSuffix = Messages.getString("NativeLoader.6");
        }
        else {
            NativeLoader.osPrefix = Messages.getString("NativeLoader.7");
            if (s.equals(Messages.getString("NativeLoader.8"))) {
                if (s2.indexOf(Messages.getString("NativeLoader.9")) == -1) {
                    NativeLoader.osSuffix = Messages.getString("NativeLoader.10");
                }
                else {
                    NativeLoader.osSuffix = Messages.getString("NativeLoader.11");
                }
            }
            else if (s.equals(Messages.getString("NativeLoader.12")) || s.equals(Messages.getString("NativeLoader.13"))) {
                if (s2.indexOf(Messages.getString("NativeLoader.14")) > -1) {
                    NativeLoader.osSuffix = Messages.getString("NativeLoader.15");
                }
                else {
                    NativeLoader.osSuffix = Messages.getString("NativeLoader.16");
                }
            }
            else if (s.indexOf(Messages.getString("NativeLoader.17")) > -1) {
                NativeLoader.osSuffix = Messages.getString("NativeLoader.18");
            }
        }
    }
    
    public static boolean isload() {
        return NativeLoader.loadingSuccessful;
    }
    
    public NativeLoader(final String name) {
        this.name = name;
        final StringBuffer sb = new StringBuffer(name.length() + ((NativeLoader.osPrefix != null) ? NativeLoader.osPrefix.length() : 0) + NativeLoader.osSuffix.length());
        if (NativeLoader.osPrefix != null) {
            sb.append(NativeLoader.osPrefix);
        }
        sb.append(name);
        sb.append(NativeLoader.osSuffix);
        this.nativeName = sb.toString();
    }
    
    protected URL findURL(final ClassLoader classLoader) {
        URL url = classLoader.getResource(this.nativeName);
        final String string = new StringBuffer(Messages.getString("NativeLoader.19").length() + this.nativeName.length()).append(Messages.getString("NativeLoader.20")).append(this.nativeName).toString();
        final String string2 = new StringBuffer(Messages.getString("NativeLoader.21").length() + this.nativeName.length()).append(Messages.getString("NativeLoader.22")).append(this.nativeName).toString();
        if (url == null) {
            url = classLoader.getResource(string);
        }
        if (url == null) {
            url = classLoader.getResource(string2);
        }
        if (url == null) {
            final File file = new File(string);
            if (file.exists()) {
                try {
                    url = file.toURL();
                }
                catch (MalformedURLException ex) {}
            }
        }
        if (url == null) {
            final File file2 = new File(string2);
            if (file2.exists()) {
                try {
                    url = file2.toURL();
                }
                catch (MalformedURLException ex2) {}
            }
        }
        return url;
    }
    
    public final String getGeneratedName() {
        return this.nativeName;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final synchronized void load() throws SecurityException, UnsatisfiedLinkError, IOException {
        if (!this.attemptedLoading) {
            this.attemptedLoading = true;
            try {
                Runtime.getRuntime().loadLibrary(this.name);
                NativeLoader.loadingSuccessful = true;
                return;
            }
            catch (SecurityException ex) {}
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
            final String string = main.homeapp + Messages.getString("NativeLoader.23");
            if (new File(string).isFile()) {
                try {
                    Runtime.getRuntime().load(string);
                    NativeLoader.loadingSuccessful = true;
                    return;
                }
                catch (SecurityException ex2) {}
                catch (UnsatisfiedLinkError unsatisfiedLinkError2) {}
            }
            final URL url = this.findURL(this.getClass().getClassLoader());
            if (url != null) {
                final File file = new File(url.getFile());
                if (file.exists()) {
                    Runtime.getRuntime().load(file.getAbsolutePath());
                    NativeLoader.loadingSuccessful = true;
                    return;
                }
                FilterInputStream filterInputStream = null;
                BufferedOutputStream bufferedOutputStream = null;
                File tempFile = null;
                try {
                    tempFile = File.createTempFile(Messages.getString("NativeLoader.24"), NativeLoader.osSuffix, null);
                    tempFile.deleteOnExit();
                    filterInputStream = new BufferedInputStream(url.openStream(), 4096);
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile), 4096);
                    final byte[] array = new byte[4096];
                    int read;
                    while ((read = filterInputStream.read(array)) > 0) {
                        bufferedOutputStream.write(array, 0, read);
                    }
                }
                finally {
                    try {
                        ((BufferedInputStream)filterInputStream).close();
                    }
                    catch (Exception ex3) {}
                    try {
                        bufferedOutputStream.close();
                    }
                    catch (Exception ex4) {}
                }
                if (tempFile != null) {
                    Runtime.getRuntime().load(tempFile.getAbsolutePath());
                    NativeLoader.loadingSuccessful = true;
                    return;
                }
            }
            NativeLoader.loadingSuccessful = false;
            throw new UnsatisfiedLinkError(this.name);
        }
    }
    
    @Override
    public final String toString() {
        return this.toStringBuffer(null).toString();
    }
    
    public StringBuffer toStringBuffer(StringBuffer sb) {
        if (sb == null) {
            sb = new StringBuffer(64);
        }
        else {
            sb.ensureCapacity(sb.length() + 64);
        }
        sb.append(Messages.getString("NativeLoader.25"));
        sb.append(this.name);
        sb.append(Messages.getString("NativeLoader.26"));
        sb.append(this.nativeName);
        sb.append(Messages.getString("NativeLoader.27"));
        return sb;
    }
    
    public final synchronized boolean tryLoad() {
        if (!this.attemptedLoading) {
            try {
                this.load();
                return NativeLoader.loadingSuccessful;
            }
            catch (SecurityException ex) {}
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
            catch (IOException ex2) {}
            return false;
        }
        return NativeLoader.loadingSuccessful;
    }
    
    static {
        init(System.getProperty(Messages.getString("NativeLoader.0")), System.getProperty(Messages.getString("NativeLoader.1")));
    }
}
