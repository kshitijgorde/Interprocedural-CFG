// 
// Decompiled by Procyon v0.5.30
// 

package jarify;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.util.zip.ZipException;
import java.io.File;
import java.util.zip.ZipFile;

final class JarFile
{
    private String m_FileName;
    private ZipFile m_ZipFile;
    private JarManifest m_Manifest;
    
    public JarFile(final File file) throws ZipException, IOException, SecurityException {
        this.m_ZipFile = new ZipFile(file);
        this.m_FileName = this.m_ZipFile.getName();
        this.init();
    }
    
    private void init() throws IOException {
        final ZipEntry entry = this.m_ZipFile.getEntry("META-INF/MANIFEST.MF");
        if (entry != null) {
            this.m_Manifest = new JarManifest(entry.getSize(), this.m_ZipFile.getInputStream(entry));
        }
    }
    
    public JarManifest getManifest() {
        return this.m_Manifest;
    }
    
    public boolean fileExists(final String s) {
        return this.m_ZipFile.getEntry(s) != null;
    }
    
    public JarSignatureFile getSignatureFile(final String s) {
        final ZipEntry entry = this.m_ZipFile.getEntry("META-INF/" + s + ".SF");
        try {
            if (entry != null) {
                return new JarSignatureFile(entry.getName(), entry.getSize(), this.m_ZipFile.getInputStream(entry));
            }
        }
        catch (IOException ex) {}
        return null;
    }
    
    public JarFileEntry getSignatureBlockFile(String upperCase) {
        final Enumeration<? extends ZipEntry> entries = this.m_ZipFile.entries();
        upperCase = upperCase.toUpperCase();
        while (entries.hasMoreElements()) {
            final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
            try {
                if (zipEntry.getName().startsWith("META-INF/" + upperCase) && (zipEntry.getName().toUpperCase().endsWith(upperCase + ".DSA") || zipEntry.getName().toUpperCase().endsWith(upperCase + ".RSA"))) {
                    return new JarFileEntry(zipEntry.getName(), zipEntry.getSize(), this.m_ZipFile.getInputStream(zipEntry));
                }
                continue;
            }
            catch (IOException ex) {}
        }
        return null;
    }
    
    public Vector getSignatureBlockFiles(final String s) {
        final Vector<JarFileEntry> vector = new Vector<JarFileEntry>();
        final Enumeration<? extends ZipEntry> entries = this.m_ZipFile.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
            try {
                if (!zipEntry.getName().startsWith("META-INF/" + s) || zipEntry.getName().toLowerCase().endsWith(".sf")) {
                    continue;
                }
                vector.addElement(new JarFileEntry(zipEntry.getName(), zipEntry.getSize(), this.m_ZipFile.getInputStream(zipEntry)));
            }
            catch (IOException ex) {}
        }
        return vector;
    }
    
    public JarFileEntry getFileByName(final String s) {
        ZipEntry entry = null;
        try {
            entry = this.m_ZipFile.getEntry(s);
        }
        catch (Exception ex) {}
        if (entry == null) {
            URL url;
            try {
                url = new URL(s);
            }
            catch (MalformedURLException ex2) {
                return null;
            }
            catch (Exception ex3) {
                return null;
            }
            try {
                return new JarFileEntry(url.getFile(), url.openConnection().getContentLength(), url.openStream());
            }
            catch (Exception ex4) {
                return null;
            }
        }
        try {
            if (entry != null) {
                return new JarFileEntry(entry.getName(), entry.getSize(), this.m_ZipFile.getInputStream(entry));
            }
        }
        catch (IOException ex5) {}
        return null;
    }
    
    public String getName() {
        return this.m_FileName;
    }
    
    public Vector getAliasList() {
        final Vector<String> vector = new Vector<String>();
        final Enumeration<? extends ZipEntry> entries = this.m_ZipFile.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
            final String replace = zipEntry.getName().replace('\\', '/');
            final int lastIndex = zipEntry.getName().lastIndexOf("/");
            if (lastIndex != -1 && replace.substring(0, lastIndex).equals("META-INF") && replace.toLowerCase().endsWith(".sf")) {
                vector.addElement(replace.substring(lastIndex + 1, replace.length() - 3));
            }
        }
        return vector;
    }
    
    public boolean close() {
        try {
            this.m_ZipFile.close();
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
}
