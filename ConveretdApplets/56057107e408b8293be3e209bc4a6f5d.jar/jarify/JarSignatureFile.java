// 
// Decompiled by Procyon v0.5.30
// 

package jarify;

import java.util.Enumeration;
import java.util.Vector;
import java.io.InputStream;
import java.util.Hashtable;

final class JarSignatureFile extends JarManifest
{
    private Hashtable manifestdigests;
    
    public JarSignatureFile(final String name, final long n, final InputStream inputStream) {
        super(n, inputStream);
        this.manifestdigests = new Hashtable();
        super.name = name;
        this.parseManifestDigests();
    }
    
    private void parseManifestDigests() {
        String s;
        int index;
        for (s = super.contentStrRaw.substring(0, super.contentStrRaw.indexOf(super.newLine + "Name: ")); (index = s.indexOf(super.newLine + " ")) != -1; s = s.substring(0, index) + s.substring(index + super.newLine.length() + 1, s.length())) {}
        int n = 0;
        final Vector<String> vector = new Vector<String>();
        int index2;
        while ((index2 = s.indexOf(super.newLine, n)) != -1) {
            vector.addElement(s.substring(n, index2));
            n = index2 + super.newLine.length();
        }
        for (int i = 0; i < vector.size(); ++i) {
            final String s2 = vector.elementAt(i);
            final int index3;
            if ((index3 = s2.indexOf("-Manifest: ")) != -1) {
                this.manifestdigests.put(s2.substring(0, index3), s2.substring(index3 + 11));
            }
        }
    }
    
    public String getManifestDigest(final String s) {
        return this.manifestdigests.get(s);
    }
    
    public Vector getManifestDigestList() {
        final Enumeration<Object> keys = this.manifestdigests.keys();
        final Vector<Object> vector = new Vector<Object>();
        while (keys.hasMoreElements()) {
            vector.addElement(keys.nextElement());
        }
        return vector;
    }
    
    public String getAlias() {
        if (super.name.indexOf(".") == -1) {
            return null;
        }
        return super.name.substring(super.name.lastIndexOf("/") + 1, super.name.lastIndexOf("."));
    }
}
