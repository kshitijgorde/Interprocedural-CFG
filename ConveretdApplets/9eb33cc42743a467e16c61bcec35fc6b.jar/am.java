import java.math.BigInteger;
import java.util.Enumeration;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class am
{
    public Vector g2;
    
    public am(final InputStream inputStream, final String s, final boolean b) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.g2 = new Vector();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String s2 = line.trim();
            if (!s2.equals("")) {
                if (s2.charAt(0) == '#') {
                    continue;
                }
                String substring;
                if (b) {
                    final int index = s2.indexOf(32);
                    substring = s2.substring(0, index);
                    s2 = s2.substring(index);
                }
                else {
                    substring = "";
                }
                try {
                    this.g2.addElement(al.fl(substring, s2));
                }
                catch (Exception ex) {
                    throw new IOException("Corrupt public keys file: " + s);
                }
            }
        }
    }
    
    public final void fq(final String s) throws IOException {
        final FileWriter fileWriter = new FileWriter(s);
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        final Enumeration fp = this.fp();
        try {
            while (fp.hasMoreElements()) {
                final String string = fp.nextElement().toString();
                bufferedWriter.write(string, 0, string.length());
                bufferedWriter.newLine();
            }
        }
        catch (Exception ex) {
            throw new IOException("Error while writing public-keys-file: " + s);
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
    }
    
    public final Enumeration fp() {
        return this.g2.elements();
    }
    
    public final int fo(final BigInteger bigInteger, final String s) {
        int n = 1;
        final Enumeration<al> elements = (Enumeration<al>)this.g2.elements();
        while (elements.hasMoreElements()) {
            final al al = elements.nextElement();
            if (al.fk().equals(s)) {
                if (al.m2().equals(bigInteger)) {
                    n = 0;
                    break;
                }
                n = 2;
                break;
            }
        }
        return n;
    }
    
    public final void fn(final String s, final String s2, final BigInteger bigInteger, final BigInteger bigInteger2) {
        this.g2.addElement(new al(s, s2, bigInteger, bigInteger2));
    }
    
    public final void fm(final String s) {
        final Enumeration<al> elements = this.g2.elements();
        while (elements.hasMoreElements()) {
            final al al = elements.nextElement();
            if (al.fk().equals(s)) {
                this.g2.removeElement(al);
                break;
            }
        }
    }
}
