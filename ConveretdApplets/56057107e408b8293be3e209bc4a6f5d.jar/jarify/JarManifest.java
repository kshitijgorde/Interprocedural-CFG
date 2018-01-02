// 
// Decompiled by Procyon v0.5.30
// 

package jarify;

import java.util.Enumeration;
import java.io.InputStream;
import java.util.Vector;
import java.util.Hashtable;

class JarManifest extends JarFileEntry
{
    private Hashtable entries;
    private Vector fileNameList;
    protected String contentStrRaw;
    protected String newLine;
    
    public JarManifest(final long n, final InputStream inputStream) {
        super("META-INF/MANIFEST.MF", n, inputStream);
        this.entries = new Hashtable();
        this.fileNameList = new Vector();
        this.init();
    }
    
    protected void init() {
        final byte[] content = this.getContent();
        if (content == null) {
            return;
        }
        this.contentStrRaw = new String(content);
        if (this.contentStrRaw.indexOf("\r\n") != -1) {
            this.newLine = "\r\n";
        }
        else if (this.contentStrRaw.indexOf("\r") != -1) {
            this.newLine = "\r";
        }
        else {
            this.newLine = "\n";
        }
        this.parse();
    }
    
    private void parse() {
        int n = 0;
        int n2 = 0;
        int index;
        while ((index = this.contentStrRaw.indexOf(this.newLine + "Name: ", n)) != -1) {
            final int n3 = index + this.newLine.length();
            final int index2 = this.contentStrRaw.indexOf(this.newLine + this.newLine, n3);
            String s = this.contentStrRaw.substring(n3, index2 + this.newLine.length() * 2);
            final EntryData entryData = new EntryData(s.getBytes());
            int index3;
            while ((index3 = s.indexOf(this.newLine + " ")) != -1) {
                s = s.substring(0, index3) + s.substring(index3 + this.newLine.length() + 1, s.length());
            }
            final String substring = s.substring("Name: ".length(), s.indexOf(this.newLine));
            this.fileNameList.addElement(substring);
            this.entries.put(substring, entryData);
            int n4 = s.indexOf(this.newLine);
            while ((n4 = s.indexOf("-Digest: ", n4 + 1)) != -1) {
                int index4 = 0;
                while ((index4 = s.indexOf(this.newLine, index4 + 1)) < n4) {
                    n2 = index4;
                }
                entryData.digests.put(s.substring(n2 + this.newLine.length(), n4 + 7), s.substring(n4 + 9, s.indexOf(this.newLine, n4)));
            }
            n = index2 - 3;
        }
    }
    
    public Vector getFileNames() {
        return this.fileNameList;
    }
    
    public String getDigest(final JarFileEntry jarFileEntry, final String s) {
        return this.getDigest(jarFileEntry.getName(), s);
    }
    
    public String getDigest(final String s, final String s2) {
        final EntryData entryData = this.entries.get(s);
        if (entryData == null) {
            return null;
        }
        return (String)entryData.digests.get(s2);
    }
    
    public byte[] getEntry(final String s) {
        final EntryData entryData = this.entries.get(s);
        if (entryData == null) {
            return null;
        }
        return entryData.raw;
    }
    
    public Vector getDigestList(final String s) {
        final EntryData entryData = this.entries.get(s);
        if (entryData == null) {
            return null;
        }
        final Enumeration keys = entryData.digests.keys();
        final Vector<Object> vector = new Vector<Object>();
        while (keys.hasMoreElements()) {
            vector.addElement(keys.nextElement());
        }
        return vector;
    }
    
    private class EntryData
    {
        byte[] raw;
        Hashtable digests;
        
        public EntryData(final byte[] raw) {
            this.raw = raw;
            this.digests = new Hashtable();
        }
    }
}
