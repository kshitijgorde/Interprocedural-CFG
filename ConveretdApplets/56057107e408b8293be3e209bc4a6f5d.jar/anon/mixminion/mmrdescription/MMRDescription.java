// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.mmrdescription;

import anon.util.Base64;
import java.io.LineNumberReader;
import logging.LogHolder;
import logging.LogType;
import java.security.SecureRandom;
import anon.mixminion.message.ExitInformation;
import anon.util.ByteArrayUtil;
import anon.mixminion.message.RoutingInformation;
import java.text.SimpleDateFormat;
import anon.crypto.MyRSAPublicKey;

public class MMRDescription
{
    private String m_address;
    private String m_name;
    private int m_port;
    private MyRSAPublicKey m_IdentityKey;
    private MyRSAPublicKey m_PacketKey;
    private byte[] m_digest;
    private byte[] m_keydigest;
    private boolean m_isExitNode;
    private boolean m_allowsFragmened;
    private String m_software;
    private SimpleDateFormat m_published;
    private static String m_time;
    
    public MMRDescription(final String address, final String name, final int port, final byte[] digest, final byte[] keydigest, final boolean isExitNode, final boolean allowsFragmened, final String software, final SimpleDateFormat published) {
        this.m_address = address;
        this.m_name = name;
        this.m_port = port;
        this.m_digest = digest;
        this.m_keydigest = keydigest;
        this.m_isExitNode = isExitNode;
        this.m_allowsFragmened = allowsFragmened;
        this.m_software = software;
        this.m_published = published;
    }
    
    public boolean setIdentityKey(final byte[] array) {
        this.m_IdentityKey = MyRSAPublicKey.getInstance(array);
        return this.m_IdentityKey != null;
    }
    
    public MyRSAPublicKey getIdentityKey() {
        return this.m_IdentityKey;
    }
    
    public SimpleDateFormat getPublished() {
        return this.m_published;
    }
    
    public boolean setPacketKey(final byte[] array) {
        this.m_PacketKey = MyRSAPublicKey.getInstance(array);
        return this.m_PacketKey != null;
    }
    
    public MyRSAPublicKey getPacketKey() {
        return this.m_PacketKey;
    }
    
    public byte[] getDigest() {
        return this.m_digest;
    }
    
    public byte[] getKeyDigest() {
        return this.m_keydigest;
    }
    
    public boolean isExitNode() {
        return this.m_isExitNode;
    }
    
    public boolean allowsFragmented() {
        return this.m_allowsFragmened;
    }
    
    public String getAddress() {
        return this.m_address;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public int getPort() {
        return this.m_port;
    }
    
    public RoutingInformation getRoutingInformation() {
        final RoutingInformation routingInformation = new RoutingInformation();
        routingInformation.m_Type = 3;
        routingInformation.m_Content = ByteArrayUtil.conc(ByteArrayUtil.inttobyte(this.m_port, 2), this.m_keydigest, this.m_address.getBytes());
        return routingInformation;
    }
    
    public String getSoftwareVersion() {
        return this.m_software;
    }
    
    public static ExitInformation getExitInformation(final String[] array, final byte[] array2) {
        final ExitInformation exitInformation = new ExitInformation();
        byte[] content;
        if (array2 == null) {
            final SecureRandom secureRandom = new SecureRandom();
            content = new byte[20];
            secureRandom.nextBytes(content);
            final byte[] array3 = content;
            final int n = 0;
            array3[n] &= 0x7F;
        }
        else {
            content = array2;
        }
        if (array.length < 1) {
            exitInformation.m_Type = 0;
            exitInformation.m_Content = content;
            LogHolder.log(3, LogType.MISC, "[Building ExitInformation]: no Recipients; Packet will be dropped! ");
            return exitInformation;
        }
        exitInformation.m_Type = 256;
        exitInformation.m_Content = ByteArrayUtil.conc(content, array[0].getBytes());
        return exitInformation;
    }
    
    public static MMRDescription parse(final LineNumberReader lineNumberReader) {
        try {
            lineNumberReader.readLine();
            final String substring = lineNumberReader.readLine().substring(10);
            final byte[] decode = Base64.decode(lineNumberReader.readLine().substring(10));
            final byte[] decode2 = Base64.decode(lineNumberReader.readLine().substring(8));
            Base64.decode(lineNumberReader.readLine().substring(11));
            final String s = MMRDescription.m_time = lineNumberReader.readLine().substring(11, 21);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.parse(s);
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            final byte[] decode3 = Base64.decode(lineNumberReader.readLine().substring(12));
            lineNumberReader.readLine();
            final String substring2 = lineNumberReader.readLine().substring(10);
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            final String substring3 = lineNumberReader.readLine().substring(10);
            final String substring4 = lineNumberReader.readLine().substring(6);
            if (substring4.startsWith("gest")) {
                return null;
            }
            final byte[] decode4 = Base64.decode(lineNumberReader.readLine().substring(12));
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            lineNumberReader.readLine();
            boolean b = false;
            boolean b2 = false;
            while (true) {
                final String line = lineNumberReader.readLine();
                if (line.startsWith("[Testing]")) {
                    break;
                }
                if (line.startsWith("[Delivery/SMTP]")) {
                    b = true;
                }
                if (line.startsWith("[Delivery/MBOX]")) {}
                if (!line.startsWith("[Delivery/Fragmented")) {
                    continue;
                }
                b2 = true;
            }
            final MMRDescription mmrDescription = new MMRDescription(substring3, substring, Integer.parseInt(substring4), decode2, decode4, b, b2, substring2, simpleDateFormat);
            if (!mmrDescription.setIdentityKey(decode) || !mmrDescription.setPacketKey(decode3)) {
                return null;
            }
            return mmrDescription;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    public String toString() {
        return "MMRRouter: " + this.m_name + " Exitnode:" + this.m_isExitNode + " FRAGS: " + this.allowsFragmented() + "Published: " + MMRDescription.m_time;
    }
}
