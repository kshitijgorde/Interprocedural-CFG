// 
// Decompiled by Procyon v0.5.30
// 

package jlog.io;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import jlog.$BI.$M4;
import jlog.$BI.$I4;
import java.net.MalformedURLException;
import java.net.URL;
import jlog.$T5.util.$U5;

public class $P4 extends $U5
{
    private URL $VC;
    public static String $X5;
    
    public synchronized void $JT(URL $u4) {
        final URL $vc = this.$VC;
        if ($U5.$RQ($vc, $u4)) {
            return;
        }
        if ($u4 != null) {
            $u4 = $U4($u4);
        }
        this.$VC = $u4;
        this.firePropertyChange($P4.$X5, $vc, $u4);
    }
    
    public static URL $SXC(URL $u4, final String s) throws MalformedURLException {
        if (s.indexOf(":") == 1) {
            return $U4("file", "", -1, s, null);
        }
        $u4 = $U4($u4);
        $u4 = new URL($u4, s);
        return $U4($u4);
    }
    
    public static String $T$C(URL $u4, URL $u5) throws MalformedURLException {
        $u4 = $U4($u4);
        $u5 = $U4($u5);
        if ($u4.getProtocol().equals($u5.getProtocol()) && $u4.getPort() == $u5.getPort() && $u4.getHost().equals($u5.getHost())) {
            return $JW.$BY($u4.getFile(), $u5.getFile());
        }
        return $u5.toExternalForm();
    }
    
    public static URL $U4(final String s) throws MalformedURLException {
        return $U4(null, s);
    }
    
    public static URL $U4(final String s, final String s2, final int n, final String s3, final String s4) throws MalformedURLException {
        return $U4(null, s, s2, n, s3, s4);
    }
    
    public static URL $U4(final URL url) {
        try {
            $I4.$QQ(url);
            return $U4(null, url.getProtocol(), url.getHost(), url.getPort(), url.getFile(), url.getRef());
        }
        catch (MalformedURLException ex) {
            $M4.print(ex);
            return url;
        }
    }
    
    public static URL $U4(final URL url, String s) throws MalformedURLException {
        String substring = "file";
        String substring2 = "";
        final int n = -1;
        final int index = s.indexOf(58);
        if (index != -1 && index >= 3) {
            substring = s.substring(0, index);
            s = s.substring(index + 1);
            if (s.startsWith("/")) {
                s = s.substring(1);
            }
            if (s.startsWith("/")) {
                s = s.substring(1);
            }
            final int index2 = s.indexOf(47);
            substring2 = s.substring(0, index2);
            s = s.substring(index2 + 1);
        }
        return $U4(url, substring, substring2, n, s, null);
    }
    
    public static URL $U4(final URL url, String protocol, String host, int port, String s, String substring) throws MalformedURLException {
        if ($U5.$LOD(protocol)) {
            if (url != null) {
                protocol = url.getProtocol();
            }
            if ($U5.$LOD(protocol)) {
                protocol = "file";
            }
        }
        if ($U5.$LOD(host)) {
            if (url != null) {
                host = url.getHost();
            }
            if ($U5.$LOD(host)) {
                host = "";
            }
        }
        if (port < 0) {
            if (url != null) {
                port = url.getPort();
            }
            if (port < 0) {
                port = -1;
            }
        }
        if ($U5.$LOD(s)) {
            if (url == null) {
                throw new MalformedURLException("missing file");
            }
            s = url.getFile();
        }
        else if (s.startsWith(".")) {
            if (url == null) {
                throw new MalformedURLException("missing base.file");
            }
            s = new URL(url, s).getFile();
        }
        if (substring == null) {
            final int index = s.indexOf(35);
            if (index != -1) {
                substring = s.substring(index + 1);
                s = s.substring(0, index);
            }
        }
        s = s.replace(File.separatorChar, '/');
        s = $JW.$AX(s);
        if (!$U5.$LOD(substring)) {
            s = String.valueOf(s) + "#" + substring;
        }
        return new URL(protocol, host, port, s);
    }
    
    static {
        $P4.$X5 = "PROP_URL";
    }
    
    public $P4() {
        this.setSource(this);
        this.$VC = null;
    }
    
    public $P4(final URL $vc) {
        this.setSource(this);
        this.$VC = $vc;
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener, final boolean b) {
        super.addPropertyChangeListener(propertyChangeListener, false);
        if (b) {
            propertyChangeListener.propertyChange(new PropertyChangeEvent(this, $P4.$X5, null, this.$VC));
        }
    }
    
    public static void copy(final URL url, final URL url2) throws MalformedURLException, IOException {
        if (url.sameFile(url2)) {
            return;
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
        try {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(getOutputStream(url2));
            try {
                $LB.copy(bufferedInputStream, bufferedOutputStream);
            }
            finally {
                bufferedOutputStream.close();
            }
        }
        finally {
            bufferedInputStream.close();
        }
    }
    
    public static File getFile(final URL url) {
        if (!url.getProtocol().equals("file")) {
            throw new RuntimeException("can't convert " + url + " to File: protocol " + url.getProtocol());
        }
        return new File(getLocalFilename(url));
    }
    
    public static InputStream getInputStream(final URL url) throws IOException {
        if (url.getProtocol().toLowerCase().equals("file")) {
            return new FileInputStream(getLocalFilename(url));
        }
        try {
            return $U4(url).openStream();
        }
        catch (SecurityException ex) {
            throw new IOException("SecurityException:" + ex.toString());
        }
    }
    
    public static String getLocalFilename(final URL url) {
        String s = url.getFile().replace('/', File.separatorChar);
        if (s.indexOf(58) != -1) {
            while (s.startsWith(String.valueOf(File.separatorChar))) {
                s = s.substring(1);
            }
        }
        return s;
    }
    
    public static OutputStream getOutputStream(URL $u4) throws IOException {
        $u4 = $U4($u4);
        final String protocol = $u4.getProtocol();
        if (!protocol.equals("file")) {
            throw new IOException(String.valueOf(String.valueOf($u4)) + ": can not write to protocol " + protocol);
        }
        try {
            return new FileOutputStream(getLocalFilename($u4));
        }
        catch (SecurityException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public URL getURL() {
        return this.$VC;
    }
}
