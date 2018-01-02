// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import java.io.IOException;
import java.util.Vector;
import anon.util.ByteArrayUtil;
import anon.util.Base64;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringReader;

public class Decoder
{
    private final int KEY_LEN = 16;
    private final int MAXHOPS = 20;
    private final int PACKETSIZE = 28625;
    private String m_message;
    private String m_password;
    
    public Decoder(final String message, final String password) {
        this.m_message = message;
        this.m_password = password;
    }
    
    public String decode() throws IOException {
        final Vector userSecrets = new Keyring(this.m_password).getUserSecrets();
        final byte[] array = new byte[0];
        String string = "";
        String trytoReassemble = "";
        boolean b = false;
        boolean b2 = false;
        final LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(this.m_message));
        String s = lineNumberReader.readLine();
        while (!s.startsWith("-----BEGIN TYPE III ANONYMOUS MESSAGE-----")) {
            s = lineNumberReader.readLine();
            if (s.intern() == ".") {
                return null;
            }
        }
        if (!lineNumberReader.readLine().substring(14).equals("encrypted")) {
            return null;
        }
        final byte[] decode = Base64.decode(lineNumberReader.readLine().substring(17));
        lineNumberReader.readLine();
        for (String s2 = lineNumberReader.readLine(); !s2.startsWith("-----END TYPE III ANONYMOUS MESSAGE-----"); s2 = lineNumberReader.readLine()) {
            string = string + s2 + "\n";
        }
        final byte[] decode2 = Base64.decode(string);
        for (int i = 0; i < userSecrets.size(); ++i) {
            final byte[] array2 = userSecrets.elementAt(i);
            if (MixMinionCryptoUtil.hash(ByteArrayUtil.conc(decode, array2, "Validate".getBytes()))[19] == 0) {
                final byte[] prng = MixMinionCryptoUtil.createPRNG(ByteArrayUtil.copy(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(decode, array2, "Generate".getBytes())), 0, 16), 320);
                byte[] sprp_Encrypt = decode2;
                for (int j = 0; j < 20; ++j) {
                    sprp_Encrypt = MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(ByteArrayUtil.copy(prng, j * 16, 16), "PAYLOAD ENCRYPT".getBytes())), sprp_Encrypt);
                    if (this.testPayload(sprp_Encrypt) == 1) {
                        trytoReassemble = new String(MixMinionCryptoUtil.decompressData(ByteArrayUtil.copy(sprp_Encrypt, 22, this.byteToInt(ByteArrayUtil.copy(sprp_Encrypt, 0, 2), 0))));
                        b = true;
                        break;
                    }
                    if (this.testPayload(sprp_Encrypt) == 2) {
                        System.out.println("Fragment");
                        b2 = true;
                        trytoReassemble = this.trytoReassemble(sprp_Encrypt);
                        if (trytoReassemble != null) {
                            b = true;
                        }
                    }
                }
            }
        }
        String s3;
        if (!b) {
            if (b2) {
                s3 = "From: JAP-Decoder\nTo: local user\nSubject: Fragment\n\nFuer die Decodierung der Nachricht werden weitere Fragmente benoetigt.\n\n";
            }
            else {
                s3 = "From: JAP-Decoder\nTo: local user\nSubject: Fehler\n\nLeider konnte nichts decodiert werden.\n";
            }
        }
        else {
            String string2 = "From: JAP-Decoder\n";
            final LineNumberReader lineNumberReader2 = new LineNumberReader(new StringReader(trytoReassemble));
            String s4 = lineNumberReader2.readLine();
            if (s4.startsWith("MIME")) {
                string2 = string2 + s4 + "\n";
                s4 = lineNumberReader2.readLine();
            }
            String s5 = string2 + "To: local user\n";
            for (String s6 = "Subject: " + s4.substring(7); s6 != null; s6 = lineNumberReader2.readLine()) {
                s5 = s5 + s6 + "\n";
            }
            s3 = s5;
        }
        return s3;
    }
    
    private int testPayload(final byte[] array) {
        final byte[] copy = ByteArrayUtil.copy(array, 2, 20);
        final byte[] hash = MixMinionCryptoUtil.hash(ByteArrayUtil.copy(array, 22, array.length - 22));
        this.byteToInt(ByteArrayUtil.copy(array, 0, 2), 0);
        if (ByteArrayUtil.equal(copy, hash)) {
            return 1;
        }
        if (ByteArrayUtil.equal(MixMinionCryptoUtil.hash(ByteArrayUtil.copy(array, 23, array.length - 23)), ByteArrayUtil.copy(array, 3, 20))) {
            return 2;
        }
        return 0;
    }
    
    private String trytoReassemble(byte[] copy) {
        String s = null;
        Vector<FragmentContainer> vector = null;
        if (vector == null) {
            vector = new Vector<FragmentContainer>();
        }
        final byte[] copy2 = ByteArrayUtil.copy(copy, 23, 20);
        final int byteToInt = this.byteToInt(ByteArrayUtil.copy(copy, 1, 2), 0);
        final double n = this.byteToInt(ByteArrayUtil.copy(copy, 43, 4), 0);
        System.out.println("MessageSize:" + n + " index: " + byteToInt);
        copy = ByteArrayUtil.copy(copy, 47, 28625);
        FragmentContainer fragmentContainer = null;
        int n2 = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final FragmentContainer fragmentContainer2 = vector.elementAt(i);
            if (ByteArrayUtil.equal(copy2, fragmentContainer2.getID())) {
                fragmentContainer = fragmentContainer2;
                n2 = i;
                break;
            }
        }
        if (n2 == -1) {
            final int n3 = (int)Math.ceil(n / 28625.0);
            System.out.println("Numberof: " + n3);
            fragmentContainer = new FragmentContainer(copy2, n3);
        }
        if (fragmentContainer.addFragment(copy, byteToInt)) {
            final byte[] unwhiten = this.unwhiten(ByteArrayUtil.copy(fragmentContainer.reassembleMessage(), 0, (int)n));
            final int byteToInt2 = this.byteToInt(ByteArrayUtil.copy(unwhiten, 3, 1), 0);
            s = new String(MixMinionCryptoUtil.decompressData(ByteArrayUtil.copy(unwhiten, 4 + byteToInt2, unwhiten.length - 4 - byteToInt2)));
        }
        if (n2 == -1) {
            vector.addElement(fragmentContainer);
        }
        else {
            vector.setElementAt(fragmentContainer, n2);
        }
        return s;
    }
    
    private int byteToInt(final byte[] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            n2 += (array[i + n] & 0xFF) << (array.length - 1 - i) * 8;
        }
        return n2;
    }
    
    private byte[] unwhiten(final byte[] array) {
        return MixMinionCryptoUtil.SPRP_Decrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(new byte[] { 87, 72, 73, 84, 69, 78 }, "WHITEN".getBytes())), array);
    }
}
