// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.zip.ZipEntry;
import netcharts.util.zip.ZipURLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Component;

public class NFImageCache
{
    private Component a;
    private Applet b;
    private ImageObserver c;
    private static int d;
    private static Hashtable e;
    private static Vector f;
    private NFContext g;
    Hashtable h;
    
    public NFImageCache(final Component a) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.g = NFContext.getDefault();
        this.h = new Hashtable();
        this.a = a;
    }
    
    public NFImageCache(final Component a, final Applet b) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.g = NFContext.getDefault();
        this.h = new Hashtable();
        this.a = a;
        this.b = b;
    }
    
    public NFImageCache(final NFContext g) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.g = NFContext.getDefault();
        this.h = new Hashtable();
        this.g = g;
        this.b = g.getApplet();
        this.a = g.getTopLevel();
    }
    
    public void setContext(final NFContext g) {
        this.g = g;
    }
    
    public NFContext getContext() {
        return this.g;
    }
    
    public void addObserver(final ImageObserver c) {
        this.c = c;
    }
    
    private void a(final NFImageCacheEntry nfImageCacheEntry) {
        if (this.c == null) {
            return;
        }
        if ((this.a.checkImage(nfImageCacheEntry.im, this.c) & 0x20) == 0x0) {
            return;
        }
        this.c.imageUpdate(nfImageCacheEntry.im, 0x20 | 0x1 | 0x2, 0, 0, nfImageCacheEntry.im.getWidth(null), nfImageCacheEntry.im.getWidth(null));
    }
    
    public Image getImage(final URL url) {
        final String string = url.toString();
        synchronized (NFImageCache.e) {
            NFImageCacheEntry nfImageCacheEntry = NFImageCache.e.get(string);
            if (nfImageCacheEntry != null && this.b(nfImageCacheEntry)) {
                NFImageCache.e.remove(nfImageCacheEntry.filename);
                NFImageCache.f.removeElement(nfImageCacheEntry.filename);
                nfImageCacheEntry.im.flush();
                nfImageCacheEntry.im = null;
                nfImageCacheEntry = null;
            }
            if (nfImageCacheEntry != null) {
                this.a(nfImageCacheEntry);
                NFImageCache.f.removeElement(nfImageCacheEntry.filename);
                NFImageCache.f.addElement(nfImageCacheEntry.filename);
                return nfImageCacheEntry.im;
            }
            try {
                nfImageCacheEntry = new NFImageCacheEntry();
                nfImageCacheEntry.filename = string;
                nfImageCacheEntry.url = url;
                Label_1035: {
                    if (url.getProtocol().equalsIgnoreCase("http")) {
                        try {
                            if (NFContext.getUserAgentType() != 0) {
                                final NFHttpClient nfHttpClient = new NFHttpClient();
                                if (this.g != null) {
                                    nfHttpClient.setContext(this.g);
                                }
                                nfImageCacheEntry.im = this.getImage(NFHttpClient.readBytes(nfHttpClient.getInputStream(url, true), nfHttpClient.getContentLength()));
                                nfImageCacheEntry.ts = nfHttpClient.getLastModified();
                            }
                            else {
                                nfImageCacheEntry.im = this.getImage(NFHttpClient.readBytesFully(url.openStream()));
                                nfImageCacheEntry.ts = new Long(url.openConnection().getLastModified());
                            }
                            break Label_1035;
                        }
                        catch (Exception ex) {
                            NFDebug.print(65536L, "Unable to load " + nfImageCacheEntry.url + ", " + ex.getMessage());
                            return null;
                        }
                    }
                    if (url.getProtocol().equalsIgnoreCase("systemresource")) {
                        InputStream openStream = null;
                        try {
                            openStream = url.openStream();
                            break Label_1035;
                        }
                        catch (Exception ex2) {
                            NFDebug.print(65536L, "Unable to load " + nfImageCacheEntry.url + ", " + ex2.getMessage());
                            return null;
                        }
                        finally {
                            if (openStream == null) {
                                throw new Exception();
                            }
                            final Image image = Toolkit.getDefaultToolkit().getImage(url);
                            this.a.prepareImage(image, null);
                            while ((this.a.checkImage(image, null) & 0x20) == 0x0) {
                                try {
                                    Thread.sleep(100L);
                                }
                                catch (Exception ex4) {}
                            }
                            this.h.put(image.toString(), image.toString());
                            nfImageCacheEntry.im = image;
                            nfImageCacheEntry.ts = null;
                        }
                    }
                    if (!url.getProtocol().equalsIgnoreCase("zip")) {
                        nfImageCacheEntry.file = new File(string.substring(5));
                        if (nfImageCacheEntry.file.exists() && nfImageCacheEntry.file.canRead()) {
                            try {
                                final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(nfImageCacheEntry.file));
                                final byte[] bytes = NFHttpClient.readBytes(bufferedInputStream, (int)nfImageCacheEntry.file.length());
                                bufferedInputStream.close();
                                nfImageCacheEntry.im = this.getImage(bytes);
                                nfImageCacheEntry.ts = new Long(nfImageCacheEntry.file.lastModified());
                                break Label_1035;
                            }
                            catch (Exception ex5) {
                                return null;
                            }
                        }
                        return null;
                    }
                    try {
                        final ZipURLConnection zipURLConnection = (ZipURLConnection)url.openConnection();
                        final ZipEntry zipEntry = zipURLConnection.getZipEntry();
                        if (zipEntry != null) {
                            final InputStream inputStream = zipURLConnection.getInputStream();
                            final byte[] bytes2 = NFHttpClient.readBytes(new BufferedInputStream(inputStream), (int)zipEntry.getSize());
                            inputStream.close();
                            nfImageCacheEntry.im = this.getImage(bytes2);
                            nfImageCacheEntry.ts = new Long(zipEntry.getTime());
                        }
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
                this.a.prepareImage(nfImageCacheEntry.im, this.c);
            }
            catch (Exception ex6) {
                NFDebug.print(65536L, "Unable to load " + nfImageCacheEntry.filename);
                return null;
            }
            this.d(nfImageCacheEntry);
            return nfImageCacheEntry.im;
        }
    }
    
    private boolean b(final NFImageCacheEntry nfImageCacheEntry) {
        final String filename = nfImageCacheEntry.filename;
        if (!filename.startsWith("http") && !filename.startsWith("HTTP")) {
            return (filename.startsWith("file") || filename.startsWith("FILE")) && (nfImageCacheEntry.ts == null || (long)nfImageCacheEntry.ts < (long)this.c(nfImageCacheEntry));
        }
        if (NFContext.getUserAgentType() == 0) {
            try {
                return (long)nfImageCacheEntry.ts < new Long(nfImageCacheEntry.url.openConnection().getLastModified());
            }
            catch (Exception ex) {
                return false;
            }
        }
        if (nfImageCacheEntry.ts == null) {
            return true;
        }
        final NFHttpClient nfHttpClient = new NFHttpClient();
        if (this.g != null) {
            nfHttpClient.setContext(this.g);
        }
        return nfHttpClient.checkIfModifiedSince(nfImageCacheEntry.filename, (String)nfImageCacheEntry.ts);
    }
    
    private Object c(final NFImageCacheEntry nfImageCacheEntry) {
        final String filename = nfImageCacheEntry.filename;
        if (filename.startsWith("http") || filename.startsWith("HTTP")) {
            if (NFContext.getUserAgentType() == 0) {
                try {
                    return new Long(nfImageCacheEntry.url.openConnection().getLastModified());
                }
                catch (Exception ex) {
                    return null;
                }
            }
            final NFHttpClient nfHttpClient = new NFHttpClient();
            if (this.g != null) {
                nfHttpClient.setContext(this.g);
            }
            return nfHttpClient.getTimeStamp(nfImageCacheEntry.filename);
        }
        if (filename.startsWith("file") || filename.startsWith("FILE")) {
            if (nfImageCacheEntry.file == null) {
                nfImageCacheEntry.file = new File(filename.substring(5));
            }
            return new Long(nfImageCacheEntry.file.lastModified());
        }
        return null;
    }
    
    public Image getImage(String resolvePath) {
        resolvePath = NFFile.resolvePath(resolvePath);
        try {
            return this.getImage(NFUtil.getFileURL(resolvePath));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            NFDebug.print(65536L, "Unable to construct URL for " + resolvePath);
            return null;
        }
    }
    
    public void putImage(final String filename, final Image im) {
        final NFImageCacheEntry nfImageCacheEntry = new NFImageCacheEntry();
        nfImageCacheEntry.filename = filename;
        nfImageCacheEntry.im = im;
        this.d(nfImageCacheEntry);
    }
    
    private void d(final NFImageCacheEntry nfImageCacheEntry) {
        synchronized (NFImageCache.e) {
            final NFImageCacheEntry nfImageCacheEntry2 = NFImageCache.e.get(nfImageCacheEntry.filename);
            if (nfImageCacheEntry2 != null) {
                if (nfImageCacheEntry2.im != null) {
                    nfImageCacheEntry2.im.flush();
                }
                NFImageCache.e.put(nfImageCacheEntry.filename, nfImageCacheEntry);
                return;
            }
            while (NFImageCache.e.size() >= NFImageCache.d) {
                final String s = NFImageCache.f.elementAt(0);
                final NFImageCacheEntry nfImageCacheEntry3 = NFImageCache.e.get(s);
                if (nfImageCacheEntry3 != null && nfImageCacheEntry3.im != null) {
                    nfImageCacheEntry3.im.flush();
                }
                NFImageCache.e.remove(s);
                NFImageCache.f.removeElement(s);
            }
            NFImageCache.e.put(nfImageCacheEntry.filename, nfImageCacheEntry);
            NFImageCache.f.removeElement(nfImageCacheEntry.filename);
            NFImageCache.f.addElement(nfImageCacheEntry.filename);
        }
    }
    
    public Image getImage(final byte[] array) {
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        this.a.prepareImage(image, null);
        while ((this.a.checkImage(image, null) & 0x20) == 0x0) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        this.h.put(image.toString(), image.toString());
        return image;
    }
    
    public boolean waitForImage(final Image image, int n, final int n2) {
        if (image == null) {
            return true;
        }
        if (this.h.get(image.toString()) != null) {
            return true;
        }
        if (n <= 0) {
            n = 100;
        }
        int n3 = 0;
        while ((this.a.checkImage(image, null) & 0x20) == 0x0) {
            if (n2 > 0 && n3 >= n2) {
                return false;
            }
            try {
                Thread.sleep(n);
            }
            catch (Exception ex) {}
            ++n3;
        }
        this.h.put(image.toString(), image.toString());
        return true;
    }
    
    static {
        NFImageCache.d = 100;
        NFImageCache.e = new Hashtable();
        NFImageCache.f = new Vector();
    }
}
