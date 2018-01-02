// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import mindbright.security.RSAPublicKey;
import java.math.BigInteger;
import java.util.Enumeration;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Vector;

public class SSHRSAPublicKeyFile
{
    Vector pubKeyList;
    
    public SSHRSAPublicKeyFile(final InputStream fileIn, final String name, final boolean hostFile) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));
        this.pubKeyList = new Vector();
        String row;
        while ((row = reader.readLine()) != null) {
            row = row.trim();
            if (!row.equals("")) {
                if (row.charAt(0) == '#') {
                    continue;
                }
                String opts;
                if (hostFile) {
                    final int i = row.indexOf(32);
                    opts = row.substring(0, i);
                    row = row.substring(i);
                }
                else {
                    opts = "";
                }
                try {
                    final SSHRSAPublicKeyString pubKey = SSHRSAPublicKeyString.createKey(opts, row);
                    this.pubKeyList.addElement(pubKey);
                }
                catch (Exception e) {
                    throw new IOException("Corrupt public keys file: " + name);
                }
            }
        }
    }
    
    public static SSHRSAPublicKeyFile loadFromFile(final String name, final boolean hostFile) throws IOException {
        final FileInputStream fileIn = new FileInputStream(name);
        final SSHRSAPublicKeyFile keyFile = new SSHRSAPublicKeyFile(fileIn, name, hostFile);
        fileIn.close();
        return keyFile;
    }
    
    public void saveToFile(final String fileName) throws IOException {
        final FileWriter fileOut = new FileWriter(fileName);
        final BufferedWriter writer = new BufferedWriter(fileOut);
        SSHRSAPublicKeyString pk = null;
        final Enumeration elmts = this.elements();
        try {
            while (elmts.hasMoreElements()) {
                pk = elmts.nextElement();
                final String row = pk.toString();
                writer.write(row, 0, row.length());
                writer.newLine();
            }
        }
        catch (Exception e) {
            throw new IOException("Error while writing public-keys-file: " + fileName);
        }
        writer.flush();
        writer.close();
        fileOut.close();
    }
    
    public Enumeration elements() {
        return this.pubKeyList.elements();
    }
    
    public RSAPublicKey getPublic(final BigInteger n, final String user) {
        SSHRSAPublicKeyString pk = null;
        final Enumeration e = this.pubKeyList.elements();
        while (e.hasMoreElements()) {
            pk = e.nextElement();
            if (pk.getN().equals(n)) {
                break;
            }
            pk = null;
        }
        return pk;
    }
    
    public int checkPublic(final BigInteger n, final String host) {
        SSHRSAPublicKeyString pk = null;
        int hostCheck = 1;
        final Enumeration e = this.pubKeyList.elements();
        while (e.hasMoreElements()) {
            pk = e.nextElement();
            if (pk.getOpts().equals(host)) {
                if (pk.getN().equals(n)) {
                    hostCheck = 0;
                    break;
                }
                hostCheck = 2;
                break;
            }
        }
        return hostCheck;
    }
    
    public void addPublic(final String opts, final String user, final BigInteger e, final BigInteger n) {
        final SSHRSAPublicKeyString pubKey = new SSHRSAPublicKeyString(opts, user, e, n);
        this.pubKeyList.addElement(pubKey);
    }
    
    public void removePublic(final String host) {
        SSHRSAPublicKeyString pk = null;
        final Enumeration e = this.pubKeyList.elements();
        while (e.hasMoreElements()) {
            pk = e.nextElement();
            if (pk.getOpts().equals(host)) {
                this.pubKeyList.removeElement(pk);
                break;
            }
        }
    }
}
