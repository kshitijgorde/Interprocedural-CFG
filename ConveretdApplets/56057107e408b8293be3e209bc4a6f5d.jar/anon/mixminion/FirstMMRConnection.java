// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion;

import anon.crypto.PKCS12;
import anon.crypto.AsymmetricCryptoKeyPair;
import anon.crypto.JAPCertificate;
import anon.crypto.Validity;
import java.util.Calendar;
import anon.crypto.X509DistinguishedName;
import anon.crypto.RSAKeyPair;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.io.BufferedInputStream;
import java.util.Random;
import java.io.IOException;
import java.io.InterruptedIOException;
import logging.LogHolder;
import logging.LogType;
import anon.mixminion.message.MixMinionCryptoUtil;
import anon.util.ByteArrayUtil;
import java.io.OutputStream;
import java.io.InputStream;
import anon.mixminion.mmrdescription.MMRDescription;
import anon.crypto.tinytls.TinyTLS;

public class FirstMMRConnection
{
    private static String OP_NAME;
    private TinyTLS m_tinyTLS;
    private MMRDescription m_description;
    private InputStream m_istream;
    private OutputStream m_ostream;
    private String m_protocol;
    private boolean m_bIsClosed;
    private long m_inittimeout;
    private Mixminion m_Mixminion;
    private int m_blocksize;
    
    public FirstMMRConnection(final MMRDescription description, final Mixminion mixminion) {
        this.m_bIsClosed = true;
        this.m_inittimeout = 30000L;
        this.m_protocol = "MMTP 0.3";
        this.m_blocksize = 1024;
        this.m_bIsClosed = true;
        this.m_description = description;
        this.m_Mixminion = mixminion;
    }
    
    public MMRDescription getMMRDescription() {
        return this.m_description;
    }
    
    public boolean isClosed() {
        return this.m_bIsClosed;
    }
    
    private boolean sending(final byte[] array, final String s) throws IOException {
        try {
            final String string = s + "\r\n";
            final byte[] array2 = new byte[6];
            final byte[] bytes = string.getBytes();
            if (array.length != 32768) {
                return false;
            }
            final byte[] hash = MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array, s.getBytes()));
            this.m_ostream.write(bytes);
            for (int i = 0; i < array.length; i += this.m_blocksize) {
                this.m_ostream.write(array, i, this.m_blocksize);
            }
            this.m_ostream.write(hash);
            this.m_ostream.flush();
            LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Send a packet");
            if (s.equals("SEND")) {
                return this.receive(array, "RECEIVED");
            }
            return s.equals("JUNK") && this.receive(array, "RECEIVED JUNK");
        }
        catch (InterruptedIOException ex) {
            return false;
        }
    }
    
    public boolean send(final byte[] array) throws IOException {
        try {
            this.connect();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final boolean sending = this.sending(array, "SEND");
        this.close();
        return sending;
    }
    
    public boolean sendMessage(final byte[] array) throws IOException {
        return this.sending(array, "SEND");
    }
    
    public boolean sendJunk() throws IOException {
        final String s = "JUNK";
        final byte[] array = new byte[32768];
        new Random().nextBytes(array);
        return this.sending(array, s);
    }
    
    private boolean receive(final byte[] array, final String s) {
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.m_istream);
            final byte[] array2 = new byte[10];
            bufferedInputStream.read(array2, 0, 10);
            final String s2 = new String(array2, 0, 8);
            final byte[] array3 = new byte[20];
            bufferedInputStream.read(array3, 0, 20);
            if (s2.equals("RECEIVED")) {
                final byte[] array4 = new byte[20];
                if (ByteArrayUtil.equal(array3, MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array, s.getBytes())))) {
                    LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Packet Transmission succeeded. Valid checksum.");
                    return true;
                }
                LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Packet Transmission failed. Invalid checksum.");
                System.out.println("Hash nicht korrekt!");
                return false;
            }
            else {
                if (!s2.equals("REJECTED")) {
                    LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Packet Transmission failed. Invalid server answer.");
                    return false;
                }
                final byte[] array5 = new byte[20];
                if (ByteArrayUtil.equal(array3, MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array, "REJECTED".getBytes())))) {
                    LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Packet Transmission rejected. Valid checksum.");
                    return false;
                }
                LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Packet Transmission rejected. Invalid checksum.");
                return false;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private void createClientCert() {
        try {
            final RSAKeyPair instance = RSAKeyPair.getInstance(new BigInteger(new byte[] { 1, 0, 1 }), new SecureRandom(), 1024, 100);
            final JAPCertificate instance2 = JAPCertificate.getInstance(new X509DistinguishedName("CN=" + FirstMMRConnection.OP_NAME), instance, new Validity(Calendar.getInstance(), 1));
            final PKCS12 pkcs12 = new PKCS12(new X509DistinguishedName("CN=" + FirstMMRConnection.OP_NAME + " <identity>"), RSAKeyPair.getInstance(new BigInteger(new byte[] { 1, 0, 1 }), new SecureRandom(), 1024, 100), new Validity(Calendar.getInstance(), 1));
            this.m_tinyTLS.setClientCertificate(new JAPCertificate[] { instance2.sign(pkcs12), JAPCertificate.getInstance(pkcs12.getX509Certificate()) }, instance.getPrivate());
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.TOR, "Error while creating Certificates. Certificates are not used.");
        }
    }
    
    public void connect() throws Exception {
        (this.m_tinyTLS = new FirstMMRConnectionThread(this.m_description.getAddress(), this.m_description.getPort(), this.m_inittimeout, this.m_Mixminion.getProxy().getProxyInterface(false).getProxyInterface()).getConnection()).checkRootCertificate(false);
        this.createClientCert();
        this.m_tinyTLS.startHandshake();
        this.m_ostream = this.m_tinyTLS.getOutputStream();
        this.m_istream = this.m_tinyTLS.getInputStream();
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.m_istream);
        this.m_tinyTLS.setSoTimeout(30000);
        this.m_ostream.write(this.m_protocol.concat("\r\n").getBytes());
        final byte[] array = new byte[10];
        bufferedInputStream.read(array, 0, 10);
        if (new String(array, 0, 8).equals(this.m_protocol)) {
            LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Protocol supported: " + this.m_protocol);
            this.m_bIsClosed = false;
        }
        else {
            LogHolder.log(7, LogType.MISC, "MMRConnection " + this.m_description.getName() + " - Protocol not supported: " + this.m_protocol);
            this.close();
            this.m_bIsClosed = true;
        }
    }
    
    public void close() {
        try {
            if (!this.m_bIsClosed) {
                this.m_bIsClosed = true;
                this.m_tinyTLS.close();
            }
        }
        catch (Throwable t) {}
    }
    
    static {
        FirstMMRConnection.OP_NAME = "JAPClient";
    }
}
