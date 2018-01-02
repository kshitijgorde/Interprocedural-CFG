// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import java.io.IOException;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringReader;
import anon.util.Base64;
import java.util.Date;
import java.util.Calendar;
import anon.mixminion.mmrdescription.MMRDescription;
import anon.util.ByteArrayUtil;
import java.util.Vector;

public class ReplyBlock
{
    static final int KEY_LEN = 16;
    static final long KEY_LIFETIME = 7776000L;
    private byte[] m_sharedSecret;
    private Vector m_path;
    private byte[] m_longterm_secret;
    private RoutingInformation m_myrouting;
    private byte[] m_headerbytes;
    private String m_myaddress;
    private long m_timetolive;
    
    public ReplyBlock(final String myaddress, final Vector path, final byte[] longterm_secret) {
        this.m_myaddress = myaddress;
        this.m_path = path;
        this.m_longterm_secret = longterm_secret;
        this.m_myrouting = new RoutingInformation();
        this.m_headerbytes = null;
    }
    
    public ReplyBlock(final RoutingInformation myrouting, final byte[] headerbytes, final byte[] sharedSecret, final long timetolive) {
        this.m_myrouting = myrouting;
        this.m_headerbytes = headerbytes;
        this.m_sharedSecret = sharedSecret;
        this.m_timetolive = timetolive;
    }
    
    public void buildBlock() {
        System.out.println("Baue ReplyBlock an: " + this.m_myaddress);
        final int size = this.m_path.size();
        byte[] array;
        do {
            final byte[] randomArray;
            array = (randomArray = MixMinionCryptoUtil.randomArray(20));
            final int n = 0;
            randomArray[n] &= 0x7F;
        } while (MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array, this.m_longterm_secret, "Validate".getBytes()))[19] != 0);
        final byte[] prng = MixMinionCryptoUtil.createPRNG(ByteArrayUtil.copy(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array, this.m_longterm_secret, "Generate".getBytes())), 0, 16), 16 * (size + 1));
        this.m_sharedSecret = ByteArrayUtil.copy(prng, size * 16, 16);
        final Vector<byte[]> vector = new Vector<byte[]>();
        for (int i = 1; i <= size; ++i) {
            vector.addElement(ByteArrayUtil.copy(prng, (size - i) * 16, 16));
        }
        final ExitInformation exitInformation = new ExitInformation();
        this.m_headerbytes = new Header(this.m_path, vector, MMRDescription.getExitInformation(new String[] { this.m_myaddress }, array)).getAsByteArray();
        this.m_myrouting.m_Type = 4;
        this.m_myrouting.m_Content = this.m_path.elementAt(0).getRoutingInformation().m_Content;
        final Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        this.m_timetolive = ((instance.get(1) - 1970 - 1) * 365 + instance.get(6)) * 24 * 60 * 60 + 7776000L;
    }
    
    public byte[] getHeaderBytes() {
        return this.m_headerbytes;
    }
    
    public byte[] getSharedSecret() {
        return this.m_sharedSecret;
    }
    
    public byte[] getReplyBlockasBytes() {
        return ByteArrayUtil.conc(new byte[] { 83, 85, 82, 66, 1, 0 }, ByteArrayUtil.inttobyte(this.m_timetolive, 4), this.m_headerbytes, ByteArrayUtil.inttobyte(this.m_myrouting.m_Content.length, 2), ByteArrayUtil.inttobyte(4L, 2), ByteArrayUtil.conc(this.m_sharedSecret, this.m_myrouting.m_Content));
    }
    
    public String getReplyBlockasString() {
        return "\n\n:-----BEGIN TYPE III REPLY BLOCK-----\nVERSION: 0.2\n" + Base64.encodeBytes(this.getReplyBlockasBytes()) + "\n:-----END TYPE III REPLY BLOCK-----";
    }
    
    public RoutingInformation getRouting() {
        return this.m_myrouting;
    }
    
    public static Vector parseReplyBlocks(String string, final byte[] array) throws IOException {
        final Vector<ReplyBlock> vector = new Vector<ReplyBlock>();
        string += "\n-----END OF PLAINTEXT MESSAGE-----";
        final LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(string));
        String s = lineNumberReader.readLine();
        while (true) {
            if (s.endsWith("-----BEGIN TYPE III REPLY BLOCK-----")) {
                if (!lineNumberReader.readLine().startsWith(">")) {
                    s = lineNumberReader.readLine();
                    String string2 = "";
                    while (!s.trim().endsWith("-----END TYPE III REPLY BLOCK-----")) {
                        string2 = string2 + s + "\n";
                        s = lineNumberReader.readLine();
                    }
                    final byte[] decode = Base64.decode(string2.substring(0, string2.length() - 1));
                    final byte[] array2 = new byte[4];
                    for (int i = 0; i < 4; ++i) {
                        array2[i] = decode[6 + i];
                    }
                    final long n = byteToInt(array2, 0);
                    final int byteToInt = byteToInt(new byte[] { decode[2058], decode[2059] }, 0);
                    final RoutingInformation routingInformation = new RoutingInformation();
                    routingInformation.m_Type = 4;
                    final byte[] content = new byte[byteToInt];
                    for (int j = 2078; j < 2078 + byteToInt; ++j) {
                        content[j - 2078] = decode[j];
                    }
                    routingInformation.m_Content = content;
                    final byte[] array3 = new byte[2048];
                    for (int k = 0; k < 2048; ++k) {
                        array3[k] = decode[k + 10];
                    }
                    final byte[] array4 = new byte[16];
                    for (int l = 0; l < 16; ++l) {
                        array4[l] = decode[2062 + l];
                    }
                    vector.addElement(new ReplyBlock(routingInformation, array3, array4, n));
                }
                else {
                    s = lineNumberReader.readLine();
                }
            }
            else {
                s = lineNumberReader.readLine();
                if (s.startsWith("-----END OF PLAINTEXT MESSAGE-----")) {
                    break;
                }
                continue;
            }
        }
        return vector;
    }
    
    public static String removeRepyBlocks(final String s) throws IOException {
        final LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(s));
        String s2 = lineNumberReader.readLine();
        String string = "";
        int n = 0;
        while (s2 != null) {
            if (s2.trim().endsWith("-----BEGIN TYPE III REPLY BLOCK-----")) {
                n = 1;
            }
            if (n == 0) {
                string = string + "\n" + s2;
            }
            if (s2.trim().endsWith("-----END TYPE III REPLY BLOCK-----")) {
                n = 0;
            }
            s2 = lineNumberReader.readLine();
        }
        return string;
    }
    
    public boolean timetoliveIsOK() {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        return ((instance.get(1) - 1970 - 1) * 365 + instance.get(6)) * 24 * 60 * 60 < this.m_timetolive;
    }
    
    private static int byteToInt(final byte[] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            n2 += (array[i + n] & 0xFF) << (array.length - 1 - i) * 8;
        }
        return n2;
    }
}
