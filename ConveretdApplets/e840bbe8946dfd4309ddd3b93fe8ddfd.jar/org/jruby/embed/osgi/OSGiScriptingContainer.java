// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi;

import org.jruby.embed.EmbedEvalUnit;
import java.io.InputStream;
import java.net.URL;
import org.jruby.embed.EvalFailedException;
import java.io.BufferedInputStream;
import java.io.IOException;
import org.jruby.embed.osgi.utils.OSGiFileLocator;
import org.jruby.embed.osgi.internal.OSGiLoadService;
import org.jruby.embed.osgi.internal.JRubyOSGiBundleClassLoader;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.LocalContextScope;
import org.osgi.framework.Bundle;
import org.jruby.embed.ScriptingContainer;

public class OSGiScriptingContainer extends ScriptingContainer
{
    public OSGiScriptingContainer(final Bundle creator) {
        this(creator, LocalContextScope.SINGLETHREAD, LocalVariableBehavior.TRANSIENT);
    }
    
    public OSGiScriptingContainer(final Bundle creator, final LocalContextScope scope, final LocalVariableBehavior behavior) {
        super(scope, behavior);
        if (creator != null) {
            super.setClassLoader(new JRubyOSGiBundleClassLoader(creator));
        }
        else {
            super.setClassLoader(new JRubyOSGiBundleClassLoader());
        }
        super.setLoadServiceCreator(OSGiLoadService.OSGI_DEFAULT);
        try {
            super.setHomeDirectory(OSGiFileLocator.getJRubyHomeFolder().getAbsolutePath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Object runScriptlet(final String bundleSymbolicName, final String path) {
        final Bundle bundle = OSGiFileLocator.getBundle(bundleSymbolicName);
        if (bundle == null) {
            throw new IllegalArgumentException("Unable to find the bundle '" + bundleSymbolicName + "'");
        }
        return this.runScriptlet(bundle, path);
    }
    
    public Object runScriptlet(final Bundle bundle, final String path) {
        final URL url = bundle.getEntry(path);
        if (url == null) {
            throw new IllegalArgumentException("Unable to find the entry '" + path + "' in the bundle " + bundle.getSymbolicName());
        }
        this.addToClassPath(bundle);
        InputStream istream = null;
        try {
            istream = new BufferedInputStream(url.openStream());
            return this.runScriptlet(istream, this.getFilename(bundle, path));
        }
        catch (IOException ioe) {
            throw new EvalFailedException(ioe);
        }
        finally {
            if (istream != null) {
                try {
                    istream.close();
                }
                catch (IOException ex) {}
            }
        }
    }
    
    public EmbedEvalUnit parse(final Bundle bundle, final String path, final int... lines) throws IOException {
        final URL url = bundle.getEntry(path);
        InputStream istream = null;
        try {
            istream = new BufferedInputStream(url.openStream());
            return super.parse(istream, this.getFilename(bundle, path), new int[0]);
        }
        catch (IOException ioe) {
            throw new EvalFailedException(ioe);
        }
        finally {
            if (istream != null) {
                try {
                    istream.close();
                }
                catch (IOException ex) {}
            }
        }
    }
    
    private String getFilename(final Bundle bundle, final String path) {
        return "bundle:/" + bundle.getSymbolicName() + ((path.charAt(0) == '/') ? path : ("/" + path));
    }
    
    public void addToClassPath(final Bundle bundle) {
        this.getOSGiBundleClassLoader().addBundle(bundle);
    }
    
    public JRubyOSGiBundleClassLoader getOSGiBundleClassLoader() {
        return (JRubyOSGiBundleClassLoader)super.getClassLoader();
    }
}
