// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringReader;
import anon.util.Base64;
import anon.util.ByteArrayUtil;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import java.util.Vector;

public class Keyring
{
    final int KEY_LEN = 16;
    static final long KEY_LIFETIME = 7776000L;
    private Vector m_mykeys;
    private Vector m_expiring;
    private String m_password;
    private int m_today;
    
    public Keyring(final String password) {
        this.m_mykeys = new Vector();
        this.m_expiring = new Vector();
        this.m_password = password;
        final Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        this.m_today = ((instance.get(1) - 1970 - 1) * 365 + instance.get(6)) * 24 * 60 * 60;
        final String s = null;
        if (s != null) {
            try {
                this.unpackKeyRing(s);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public Vector getUserSecrets() {
        return this.m_mykeys;
    }
    
    public byte[] getNewSecret() {
        return this.makeNewKey();
    }
    
    private String packKeyring() {
        byte[] conc = new byte[0];
        for (int i = 0; i < this.m_mykeys.size(); ++i) {
            final byte[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
            final byte[] array2 = this.m_expiring.elementAt(i);
            if (this.byteToInt(array2, 0) + 7776000L > this.m_today) {
                final byte[] conc2 = ByteArrayUtil.conc(array2, array, this.m_mykeys.elementAt(i));
                conc = ByteArrayUtil.conc(conc, ByteArrayUtil.conc(new byte[1], ByteArrayUtil.inttobyte(conc2.length, 2), conc2));
            }
        }
        final byte[] bytes = "KEYRING2".getBytes();
        final byte[] array3 = { 18, 8, 32, 16, 52, 86, 7, 19 };
        final byte[] conc3 = ByteArrayUtil.conc(ByteArrayUtil.inttobyte(conc.length, 4), conc, MixMinionCryptoUtil.randomArray(1024 * this.myceil(conc.length, 1024.0) - conc.length));
        return "-----BEGIN TYPE III KEYRING-----\nVersion: 0.1\n\n" + Base64.encodeBytes(MixMinionCryptoUtil.Encrypt(ByteArrayUtil.copy(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array3, this.m_password.getBytes(), array3)), 0, 16), ByteArrayUtil.conc(conc3, MixMinionCryptoUtil.hash(ByteArrayUtil.conc(conc3, array3, bytes))))) + "\n-----END TYPE III KEYRING-----";
    }
    
    private void unpackKeyRing(final String s) throws IOException {
        String string = "";
        final LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(s));
        lineNumberReader.readLine();
        lineNumberReader.readLine();
        lineNumberReader.readLine();
        for (String s2 = lineNumberReader.readLine(); !s2.startsWith("-----END"); s2 = lineNumberReader.readLine()) {
            string += s2;
        }
        final byte[] decode = Base64.decode(string);
        final byte[] array = { 18, 8, 32, 16, 52, 86, 7, 19 };
        final byte[] bytes = "KEYRING2".getBytes();
        final byte[] encrypt = MixMinionCryptoUtil.Encrypt(ByteArrayUtil.copy(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array, this.m_password.getBytes(), array)), 0, 16), decode);
        final byte[] copy = ByteArrayUtil.copy(encrypt, encrypt.length - 20, 20);
        final byte[] copy2 = ByteArrayUtil.copy(encrypt, 0, encrypt.length - 20);
        if (!ByteArrayUtil.equal(copy, MixMinionCryptoUtil.hash(ByteArrayUtil.conc(copy2, array, bytes)))) {
            System.out.println("falsches Passwort!");
        }
        final byte[] copy3 = ByteArrayUtil.copy(copy2, 4, this.byteToInt(ByteArrayUtil.copy(copy2, 0, 4), 0));
        for (int i = 0; i < copy3.length; i += 37) {
            if (copy3[i] == 0) {
                this.m_expiring.addElement(ByteArrayUtil.copy(copy3, i + 3, 4));
                this.m_mykeys.addElement(ByteArrayUtil.copy(copy3, i + 17, 20));
            }
        }
    }
    
    private byte[] makeNewKey() {
        final byte[] randomArray = MixMinionCryptoUtil.randomArray(20);
        final byte[] inttobyte = ByteArrayUtil.inttobyte(this.m_today + 7776000L, 4);
        this.m_mykeys.addElement(randomArray);
        this.m_expiring.addElement(inttobyte);
        this.saveKeyRing();
        return randomArray;
    }
    
    public void changeKeyringPW(final String password) {
        this.m_password = password;
        this.saveKeyRing();
    }
    
    private void saveKeyRing() {
    }
    
    private int byteToInt(final byte[] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            n2 += (array[i + n] & 0xFF) << (array.length - 1 - i) * 8;
        }
        return n2;
    }
    
    private int myceil(final double n, final double n2) {
        final int n3 = (int)Math.ceil(n / n2);
        if (n3 == 0) {
            return 1;
        }
        return n3;
    }
}
