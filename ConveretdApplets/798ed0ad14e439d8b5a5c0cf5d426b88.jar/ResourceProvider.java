import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import java.io.InputStream;
import java.applet.AppletStub;

// 
// Decompiled by Procyon v0.5.30
// 

public class ResourceProvider
{
    private Class resourceClass;
    private AppletStub applet;
    private String resourcePrefix;
    
    public ResourceProvider() {
        this(null);
    }
    
    public ResourceProvider(final AppletStub appletStub) {
        this(null, appletStub);
    }
    
    public ResourceProvider(final Class resourceClass, final AppletStub context) {
        this.setContext(context);
        this.setResourceClass(resourceClass);
    }
    
    public final void setResourceClass(Class class1) {
        if (class1 == null) {
            class1 = this.getClass();
        }
        this.resourceClass = class1;
        this.resourcePrefix = "/";
        final String replace = class1.getName().replace('.', '/');
        final int lastIndex = replace.lastIndexOf(47);
        if (lastIndex > 0) {
            final int lastIndex2 = replace.lastIndexOf(47, lastIndex - 1);
            if (lastIndex2 >= 0) {
                this.resourcePrefix = this.resourcePrefix + replace.substring(0, lastIndex2) + '/';
            }
        }
    }
    
    public final void setContext(final AppletStub applet) {
        this.applet = applet;
    }
    
    public final InputStream getInternalResourceStream(final String s) {
        if (s == null) {
            return null;
        }
        final URL internalResourceURL = this.getInternalResourceURL(s);
        if (internalResourceURL != null) {
            try {
                return internalResourceURL.openStream();
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public final URL getInternalResourceURL(final String s) {
        if (s == null) {
            return null;
        }
        final String string = this.resourcePrefix + s;
        System.out.println("Opening internal resource: " + string);
        URL resource = this.resourceClass.getResource(string);
        if (resource == null && this.applet != null) {
            final URL codeBase = this.applet.getCodeBase();
            try {
                resource = new URL(codeBase, s);
            }
            catch (Exception ex) {}
        }
        return resource;
    }
    
    public final URL getExternalResourceURL(final String s, final boolean b) {
        if (s == null) {
            return null;
        }
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (Exception ex) {}
        if (url == null && this.applet != null) {
            URL url2;
            if (b) {
                url2 = this.applet.getDocumentBase();
            }
            else {
                url2 = this.applet.getCodeBase();
            }
            try {
                url = new URL(url2, s);
            }
            catch (Exception ex2) {}
        }
        return url;
    }
    
    public final Image getInternalImage(final String s) {
        final InputStream internalResourceStream = this.getInternalResourceStream(s);
        if (internalResourceStream != null) {
            return this.getImage(internalResourceStream);
        }
        return null;
    }
    
    public final Image getExternalImage(final String s, final boolean b) {
        System.out.println("Loading image: " + s);
        final URL externalResourceURL = this.getExternalResourceURL(s, b);
        if (externalResourceURL == null) {
            return null;
        }
        try {
            final InputStream openStream = externalResourceURL.openStream();
            if (openStream != null) {
                openStream.close();
                return Toolkit.getDefaultToolkit().createImage(externalResourceURL);
            }
            return null;
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    private Image getImage(final InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[1024];
            for (int i = inputStream.read(array); i >= 0; i = inputStream.read(array)) {
                byteArrayOutputStream.write(array, 0, i);
            }
            return Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
