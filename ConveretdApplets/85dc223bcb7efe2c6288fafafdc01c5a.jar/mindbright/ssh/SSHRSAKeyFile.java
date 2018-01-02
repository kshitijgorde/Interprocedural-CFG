// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import mindbright.security.Cipher;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import mindbright.security.RSAPrivateKey;
import mindbright.security.KeyPair;
import mindbright.security.RSAPublicKey;

public class SSHRSAKeyFile
{
    int cipherType;
    RSAPublicKey pubKey;
    String fileComment;
    byte[] encrypted;
    static final String privFileId = "SSH PRIVATE KEY FILE FORMAT 1.1\n";
    
    public static void createKeyFile(final KeyPair kp, final String passwd, final String name, final String comment) throws IOException {
        final RSAPrivateKey privKey = (RSAPrivateKey)kp.getPrivate();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(8192);
        SSHDataOutputStream dataOut = new SSHDataOutputStream(baos);
        byte[] c = new byte[2];
        SSH.secureRandom().nextBytes(c);
        dataOut.writeByte(c[0]);
        dataOut.writeByte(c[1]);
        dataOut.writeByte(c[0]);
        dataOut.writeByte(c[1]);
        dataOut.writeBigInteger(privKey.getD());
        dataOut.writeBigInteger(privKey.getU());
        dataOut.writeBigInteger(privKey.getP());
        dataOut.writeBigInteger(privKey.getQ());
        byte[] encrypted = baos.toByteArray();
        c = new byte[8 - encrypted.length % 8 + encrypted.length];
        System.arraycopy(encrypted, 0, c, 0, encrypted.length);
        encrypted = c;
        final int cipherType = 3;
        final Cipher cipher = Cipher.getInstance(SSH.cipherClasses[cipherType][0]);
        cipher.setKey(passwd);
        encrypted = cipher.encrypt(encrypted);
        final FileOutputStream fileOut = new FileOutputStream(name);
        dataOut = new SSHDataOutputStream(fileOut);
        dataOut.writeBytes("SSH PRIVATE KEY FILE FORMAT 1.1\n");
        dataOut.writeByte(0);
        dataOut.writeByte(cipherType);
        dataOut.writeInt(0);
        dataOut.writeInt(0);
        dataOut.writeBigInteger(((RSAPublicKey)kp.getPublic()).getN());
        dataOut.writeBigInteger(((RSAPublicKey)kp.getPublic()).getE());
        dataOut.writeString(comment);
        dataOut.write(encrypted, 0, encrypted.length);
        dataOut.close();
    }
    
    public SSHRSAKeyFile(final String name) throws IOException {
        final FileInputStream fileIn = new FileInputStream(name);
        final SSHDataInputStream dataIn = new SSHDataInputStream(fileIn);
        final byte[] id = new byte["SSH PRIVATE KEY FILE FORMAT 1.1\n".length()];
        dataIn.readFully(id);
        final String idStr = new String(id);
        dataIn.readByte();
        if (!idStr.equals("SSH PRIVATE KEY FILE FORMAT 1.1\n")) {
            throw new IOException("RSA key file corrupt");
        }
        this.cipherType = dataIn.readByte();
        if (SSH.cipherClasses[this.cipherType][0] == null) {
            throw new IOException("Ciphertype " + this.cipherType + " in key-file not supported");
        }
        dataIn.readInt();
        dataIn.readInt();
        final BigInteger n = dataIn.readBigInteger();
        final BigInteger e = dataIn.readBigInteger();
        this.pubKey = new RSAPublicKey(e, n);
        this.fileComment = dataIn.readString();
        final byte[] rest = new byte[8192];
        final int len = dataIn.read(rest);
        dataIn.close();
        System.arraycopy(rest, 0, this.encrypted = new byte[len], 0, len);
    }
    
    public String getComment() {
        return this.fileComment;
    }
    
    public RSAPublicKey getPublic() {
        return this.pubKey;
    }
    
    public RSAPrivateKey getPrivate(final String passwd) {
        RSAPrivateKey privKey = null;
        final Cipher cipher = Cipher.getInstance(SSH.cipherClasses[this.cipherType][0]);
        cipher.setKey(passwd);
        final byte[] decrypted = cipher.decrypt(this.encrypted);
        final SSHDataInputStream dataIn = new SSHDataInputStream(new ByteArrayInputStream(decrypted));
        try {
            final byte c1 = dataIn.readByte();
            final byte c2 = dataIn.readByte();
            final byte c3 = dataIn.readByte();
            final byte c4 = dataIn.readByte();
            if (c1 != c3 || c2 != c4) {
                return null;
            }
            final BigInteger d = dataIn.readBigInteger();
            final BigInteger u = dataIn.readBigInteger();
            final BigInteger p = dataIn.readBigInteger();
            final BigInteger q = dataIn.readBigInteger();
            dataIn.close();
            privKey = new RSAPrivateKey(this.pubKey.getE(), this.pubKey.getN(), d, u, p, q);
        }
        catch (IOException e) {
            privKey = null;
        }
        return privKey;
    }
}
