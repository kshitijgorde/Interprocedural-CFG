// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.util;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.ByteArrayInputStream;
import mindbright.security.AccessDeniedException;
import java.io.InputStream;
import mindbright.security.MessageDigest;
import java.io.IOException;
import mindbright.security.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class EncryptedProperties extends Properties
{
    public static final String HASH_KEY = "EncryptedProperties.hash";
    public static final String CIPHER_KEY = "EncryptedProperties.cipher";
    public static final String CONTENTS_KEY = "EncryptedProperties.contents";
    public static final String SIZE_KEY = "EncryptedProperties.size";
    public static final String PROPS_HEADER = "Sealed with mindbright.util.EncryptedProperties(ver. $Name:  $$Date: 2001/04/06 17:39:35 $)";
    private boolean isNormalPropsFile;
    
    public EncryptedProperties() {
        this.isNormalPropsFile = false;
    }
    
    public EncryptedProperties(final Properties defaultProperties) {
        super(defaultProperties);
        this.isNormalPropsFile = false;
    }
    
    public boolean isNormalPropsFile() {
        return this.isNormalPropsFile;
    }
    
    public synchronized void save(final OutputStream out, final String header, final String password, final String cipherName) throws IOException {
        final ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        final Properties encProps = new Properties();
        final Cipher cipher = Cipher.getInstance(cipherName);
        if (cipher == null) {
            throw new IOException("Unknown cipher '" + cipherName + "'");
        }
        this.save(bytesOut, header);
        byte[] contents = bytesOut.toByteArray();
        final int size = contents.length;
        byte[] hash;
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(contents);
            hash = md5.digest();
        }
        catch (Exception e) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
        hash = Base64.encode(hash);
        final String hashStr = new String(hash);
        final byte[] tmp = new byte[contents.length + (8 - contents.length % 8)];
        System.arraycopy(contents, 0, tmp, 0, contents.length);
        contents = new byte[tmp.length];
        cipher.setKey(hashStr + password);
        cipher.encrypt(tmp, 0, contents, 0, contents.length);
        contents = Base64.encode(contents);
        ((Hashtable<String, String>)encProps).put("EncryptedProperties.hash", new String(hash));
        ((Hashtable<String, String>)encProps).put("EncryptedProperties.cipher", cipherName);
        ((Hashtable<String, String>)encProps).put("EncryptedProperties.contents", new String(contents));
        ((Hashtable<String, String>)encProps).put("EncryptedProperties.size", String.valueOf(size));
        encProps.save(out, "Sealed with mindbright.util.EncryptedProperties(ver. $Name:  $$Date: 2001/04/06 17:39:35 $)");
        out.flush();
    }
    
    public synchronized void load(final InputStream in, final String password) throws IOException, AccessDeniedException {
        final Properties encProps = new Properties();
        encProps.load(in);
        final String hashStr = encProps.getProperty("EncryptedProperties.hash");
        final String cipherName = encProps.getProperty("EncryptedProperties.cipher");
        final String contentsStr = encProps.getProperty("EncryptedProperties.contents");
        final String sizeStr = encProps.getProperty("EncryptedProperties.size");
        if (hashStr == null && cipherName == null && contentsStr == null && sizeStr == null) {
            this.isNormalPropsFile = true;
            final Enumeration keys = encProps.keys();
            while (keys.hasMoreElements()) {
                final String key = keys.nextElement();
                ((Hashtable<String, String>)this).put(key, encProps.getProperty(key));
            }
            return;
        }
        final int size = Integer.parseInt(sizeStr);
        final byte[] hash = Base64.decode(hashStr.getBytes());
        byte[] contents = Base64.decode(contentsStr.getBytes());
        final Cipher cipher = Cipher.getInstance(cipherName);
        if (cipher == null) {
            throw new IOException("Unknown cipher '" + cipherName + "'");
        }
        cipher.setKey(hashStr + password);
        cipher.decrypt(contents, 0, contents, 0, contents.length);
        final byte[] tmp = new byte[size];
        System.arraycopy(contents, 0, tmp, 0, size);
        contents = tmp;
        byte[] hashCalc;
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(contents);
            hashCalc = md5.digest();
        }
        catch (Exception e) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
        for (int i = 0; i < hash.length; ++i) {
            if (hash[i] != hashCalc[i]) {
                throw new AccessDeniedException("Access denied");
            }
        }
        final ByteArrayInputStream bytesIn = new ByteArrayInputStream(contents);
        this.load(bytesIn);
    }
}
