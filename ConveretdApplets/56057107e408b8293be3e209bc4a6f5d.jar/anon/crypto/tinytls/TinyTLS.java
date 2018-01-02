// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls;

import anon.crypto.MyDSASignature;
import anon.crypto.MyDSAPrivateKey;
import java.security.InvalidKeyException;
import anon.crypto.MyRSASignature;
import anon.crypto.tinytls.util.hash;
import anon.crypto.MyRSAPrivateKey;
import java.util.Random;
import anon.util.ByteArrayUtil;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.io.DataInputStream;
import java.net.SocketException;
import java.io.OutputStream;
import java.io.InputStream;
import anon.crypto.tinytls.ciphersuites.DHE_DSS_WITH_DES_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_RSA_WITH_DES_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_DSS_WITH_3DES_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_RSA_WITH_3DES_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_DSS_WITH_AES_128_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_RSA_WITH_AES_128_CBC_SHA;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.ListenerInterface;
import anon.infoservice.HTTPConnectionFactory;
import java.io.IOException;
import java.net.UnknownHostException;
import anon.infoservice.ImmutableProxyInterface;
import anon.shared.ProxyConnection;
import anon.crypto.IMyPrivateKey;
import anon.crypto.IMyPublicKey;
import anon.crypto.JAPCertificate;
import anon.crypto.tinytls.ciphersuites.CipherSuite;
import java.util.Vector;
import java.net.Socket;

public class TinyTLS extends Socket
{
    public static byte[] PROTOCOLVERSION;
    private static int PROTOCOLVERSION_SHORT;
    private Vector m_supportedciphersuites;
    private CipherSuite m_selectedciphersuite;
    private TLSInputStream m_istream;
    private TLSOutputStream m_ostream;
    private boolean m_handshakecompleted;
    private boolean m_serverhellodone;
    private boolean m_certificaterequested;
    private JAPCertificate m_servercertificate;
    private IMyPublicKey m_trustedRoot;
    private boolean m_checkTrustedRoot;
    private byte[] m_clientrandom;
    private byte[] m_serverrandom;
    private byte[] m_handshakemessages;
    private byte[] m_clientcertificatetypes;
    private IMyPrivateKey m_clientprivatekey;
    private JAPCertificate[] m_clientcertificates;
    private boolean m_certificateverify;
    private boolean m_encrypt;
    private ProxyConnection m_ProxyConnection;
    
    public TinyTLS(final String s, final int n) throws UnknownHostException, IOException, Exception {
        this(s, n, null);
    }
    
    public TinyTLS(final String s, final int n, final ImmutableProxyInterface immutableProxyInterface) throws UnknownHostException, IOException, Exception {
        this.m_selectedciphersuite = null;
        this.m_ProxyConnection = new ProxyConnection(HTTPConnectionFactory.getInstance().createHTTPConnection(new ListenerInterface(s, n), immutableProxyInterface).Connect());
        this.m_handshakecompleted = false;
        this.m_serverhellodone = false;
        this.m_encrypt = false;
        this.m_certificaterequested = false;
        this.m_certificateverify = false;
        this.m_supportedciphersuites = new Vector();
        this.m_istream = new TLSInputStream(this.m_ProxyConnection.getInputStream());
        this.m_ostream = new TLSOutputStream(this.m_ProxyConnection.getOutputStream());
        this.m_trustedRoot = null;
        this.m_checkTrustedRoot = true;
        this.m_clientcertificatetypes = null;
        this.m_clientcertificates = null;
        this.m_clientprivatekey = null;
    }
    
    public void addCipherSuite(final CipherSuite cipherSuite) {
        if (!this.m_supportedciphersuites.contains(cipherSuite)) {
            this.m_supportedciphersuites.addElement(cipherSuite);
            LogHolder.log(7, LogType.MISC, "[CIPHERSUITE_ADDED] : " + cipherSuite.toString());
        }
    }
    
    public void startHandshake() throws IOException {
        if (this.m_supportedciphersuites.isEmpty()) {
            LogHolder.log(7, LogType.MISC, "[NO_CIPHERSUITE_DEFINED] : using predefined");
            this.addCipherSuite(new DHE_RSA_WITH_AES_128_CBC_SHA());
            this.addCipherSuite(new DHE_DSS_WITH_AES_128_CBC_SHA());
            this.addCipherSuite(new DHE_RSA_WITH_3DES_CBC_SHA());
            this.addCipherSuite(new DHE_DSS_WITH_3DES_CBC_SHA());
            this.addCipherSuite(new DHE_RSA_WITH_DES_CBC_SHA());
            this.addCipherSuite(new DHE_DSS_WITH_DES_CBC_SHA());
        }
        if (!this.m_checkTrustedRoot) {
            LogHolder.log(7, LogType.MISC, "[CHECK_TRUSTED_ROOT_DEACTIVATED] : all certificates are accepted");
        }
        else if (this.m_trustedRoot == null) {
            LogHolder.log(7, LogType.MISC, "[TRUSTED_CERTIFICATES_NOT_SET] : cannot verify Certificates");
            throw new TLSException("Please set Trusted Root");
        }
        this.m_handshakemessages = new byte[0];
        this.m_ostream.sendClientHello();
        this.m_istream.readServerHandshakes();
        this.m_ostream.sendClientCertificate();
        this.m_ostream.sendClientKeyExchange();
        this.m_ostream.sendCertificateVerify();
        this.m_ostream.sendChangeCipherSpec();
        this.m_ostream.sendClientFinished();
        this.m_istream.readServerFinished();
        this.m_handshakecompleted = true;
    }
    
    public void setRootKey(final IMyPublicKey trustedRoot) {
        this.m_trustedRoot = trustedRoot;
    }
    
    public void checkRootCertificate(final boolean checkTrustedRoot) {
        this.m_checkTrustedRoot = checkTrustedRoot;
    }
    
    public InputStream getInputStream() {
        return this.m_istream;
    }
    
    public OutputStream getOutputStream() {
        return this.m_ostream;
    }
    
    public void setSoTimeout(final int soTimeout) throws SocketException {
        this.m_ProxyConnection.setSoTimeout(soTimeout);
    }
    
    public void setClientCertificate(final JAPCertificate japCertificate, final IMyPrivateKey myPrivateKey) throws IOException {
        this.setClientCertificate(new JAPCertificate[] { japCertificate }, myPrivateKey);
    }
    
    public void setClientCertificate(final JAPCertificate[] clientcertificates, final IMyPrivateKey clientprivatekey) throws IOException {
        if (clientcertificates != null) {
            JAPCertificate japCertificate = clientcertificates[0];
            LogHolder.log(7, LogType.MISC, "[CLIENT_CERTIFICATE] " + japCertificate.getIssuer().toString());
            LogHolder.log(7, LogType.MISC, "[CLIENT_CERTIFICATE] " + japCertificate.getSubject().toString());
            for (int i = 1; i < clientcertificates.length; ++i) {
                final JAPCertificate japCertificate2 = clientcertificates[i];
                if (!japCertificate.verify(japCertificate2.getPublicKey())) {
                    throw new IOException("TLS Server Certs could not be verified!");
                }
                japCertificate = japCertificate2;
                LogHolder.log(7, LogType.MISC, "[CLIENT_CERTIFICATE] " + japCertificate.getIssuer().toString());
                LogHolder.log(7, LogType.MISC, "[CLIENT_CERTIFICATE] " + japCertificate.getSubject().toString());
            }
        }
        this.m_clientcertificates = clientcertificates;
        this.m_clientprivatekey = clientprivatekey;
    }
    
    public void close() {
        try {
            if (this.m_ostream != null) {
                this.m_ostream.close();
            }
        }
        catch (IOException ex) {}
        try {
            if (this.m_istream != null) {
                this.m_istream.close();
            }
        }
        catch (IOException ex2) {}
        this.m_ProxyConnection.close();
    }
    
    public Socket getSocket() {
        return this.m_ProxyConnection.getSocket();
    }
    
    static {
        TinyTLS.PROTOCOLVERSION = new byte[] { 3, 1 };
        TinyTLS.PROTOCOLVERSION_SHORT = 769;
    }
    
    class TLSInputStream extends InputStream implements ITLSConstants
    {
        private DataInputStream m_stream;
        private int m_aktPendOffset;
        private int m_aktPendLen;
        private TLSPlaintextRecord m_aktTLSRecord;
        private int m_ReadRecordState;
        
        public TLSInputStream(final InputStream inputStream) {
            this.m_aktTLSRecord = new TLSPlaintextRecord();
            this.m_stream = new DataInputStream(inputStream);
            this.m_aktPendOffset = 0;
            this.m_aktPendLen = 0;
            this.m_ReadRecordState = 0;
        }
        
        private synchronized void readRecord() throws IOException {
            if (this.m_ReadRecordState == 0) {
                this.m_aktTLSRecord.clean();
                byte byte1;
                try {
                    byte1 = this.m_stream.readByte();
                }
                catch (InterruptedIOException ex) {
                    ex.bytesTransferred = 0;
                    throw ex;
                }
                if (byte1 < 20 || byte1 > 23) {
                    throw new TLSException("SSL Content typeProtocoll not supported: " + byte1);
                }
                this.m_aktTLSRecord.setType(byte1);
                this.m_ReadRecordState = 1;
            }
            if (this.m_ReadRecordState == 1) {
                short short1;
                try {
                    short1 = this.m_stream.readShort();
                }
                catch (InterruptedIOException ex2) {
                    ex2.bytesTransferred = 0;
                    throw ex2;
                }
                if (short1 != TinyTLS.PROTOCOLVERSION_SHORT) {
                    throw new TLSException("Protocol version not supported: " + short1);
                }
                this.m_ReadRecordState = 2;
            }
            if (this.m_ReadRecordState == 2) {
                short short2;
                try {
                    short2 = this.m_stream.readShort();
                }
                catch (InterruptedIOException ex3) {
                    ex3.bytesTransferred = 0;
                    throw ex3;
                }
                if (short2 > 16384) {
                    throw new TLSException("Given size of TLSPlaintex record payload exceeds TLSPlaintextRecord.MAX_PAYLOAD_SIZE!");
                }
                this.m_aktTLSRecord.setLength(short2);
                this.m_ReadRecordState = 3;
                this.m_aktPendOffset = 0;
            }
            if (this.m_ReadRecordState == 3) {
                int i = this.m_aktTLSRecord.getLength() - this.m_aktPendOffset;
                while (i > 0) {
                    try {
                        final int read = this.m_stream.read(this.m_aktTLSRecord.getData(), this.m_aktPendOffset, i);
                        if (read < 0) {
                            throw new EOFException();
                        }
                        i -= read;
                        this.m_aktPendOffset += read;
                    }
                    catch (InterruptedIOException ex4) {
                        this.m_aktPendOffset += ex4.bytesTransferred;
                        ex4.bytesTransferred = 0;
                        throw ex4;
                    }
                }
                this.m_ReadRecordState = 0;
                this.m_aktPendOffset = 0;
            }
        }
        
        public int read() throws IOException {
            final byte[] array = { 0 };
            if (this.read(array, 0, 1) < 1) {
                return -1;
            }
            return array[0] & 0xFF;
        }
        
        public int read(final byte[] array) throws IOException {
            return this.read(array, 0, array.length);
        }
        
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            while (this.m_aktPendLen < 1) {
                this.readRecord();
                try {
                    switch (this.m_aktTLSRecord.getType()) {
                        case 23: {
                            TinyTLS.this.m_selectedciphersuite.decode(this.m_aktTLSRecord);
                            this.m_aktPendOffset = 0;
                            this.m_aktPendLen = this.m_aktTLSRecord.getLength();
                            continue;
                        }
                        case 21: {
                            this.handleAlert();
                            continue;
                        }
                        default: {
                            throw new IOException("Error while decoding application data");
                        }
                    }
                }
                catch (Throwable t) {
                    throw new IOException("Exception by reading next TSL record: " + t.getMessage());
                }
            }
            final int min = Math.min(this.m_aktPendLen, n2);
            System.arraycopy(this.m_aktTLSRecord.getData(), this.m_aktPendOffset, array, n, min);
            this.m_aktPendOffset += min;
            this.m_aktPendLen -= min;
            return min;
        }
        
        public int available() {
            return this.m_aktPendLen;
        }
        
        private void gotServerHello(final TLSHandshakeRecord tlsHandshakeRecord) throws IOException {
            final int n = 0;
            final byte[] data = tlsHandshakeRecord.getData();
            LogHolder.log(7, LogType.MISC, "[SERVER_HELLO] SSLVERSION :" + data[n] + "." + data[n + 1]);
            if (data[n] != TinyTLS.PROTOCOLVERSION[0] || data[n + 1] != TinyTLS.PROTOCOLVERSION[1]) {
                throw new TLSException("Server replies with wrong protocoll");
            }
            TinyTLS.this.m_serverrandom = ByteArrayUtil.copy(data, n + 2, 32);
            final byte[] array = new byte[0];
            final byte b = data[n + 34];
            if (b > 0) {
                ByteArrayUtil.copy(data, n + 35, b);
            }
            LogHolder.log(7, LogType.MISC, "[SERVER_HELLO] Laenge der SessionID : " + b);
            final byte[] copy = ByteArrayUtil.copy(data, n + 35 + b, 2);
            LogHolder.log(7, LogType.MISC, "[SERVER_HELLO] Ciphersuite : " + copy[0] + " " + copy[1]);
            LogHolder.log(7, LogType.MISC, "[SERVER_HELLO] Kompression : " + ByteArrayUtil.copy(data, n + 37 + b, 1)[0]);
            CipherSuite cipherSuite = null;
            for (int i = 0; i < TinyTLS.this.m_supportedciphersuites.size(); ++i) {
                cipherSuite = (CipherSuite)TinyTLS.this.m_supportedciphersuites.elementAt(i);
                final byte[] cipherSuiteCode = cipherSuite.getCipherSuiteCode();
                if (cipherSuiteCode[0] == copy[0] && cipherSuiteCode[1] == copy[1]) {
                    break;
                }
                cipherSuite = null;
            }
            if (cipherSuite == null) {
                throw new TLSException("Unsupported Ciphersuite selected");
            }
            TinyTLS.this.m_selectedciphersuite = cipherSuite;
            TinyTLS.this.m_supportedciphersuites = null;
        }
        
        private void gotCertificate(final TLSHandshakeRecord tlsHandshakeRecord) throws IOException {
            final byte[] data = tlsHandshakeRecord.getData();
            final int n = 0;
            tlsHandshakeRecord.getLength();
            final Vector vector = new Vector<JAPCertificate>();
            final byte[] copy = ByteArrayUtil.copy(data, n, 3);
            final int n2 = (copy[0] & 0xFF) << 16 | (copy[1] & 0xFF) << 8 | (copy[2] & 0xFF);
            int n3 = n + 3;
            final byte[] copy2 = ByteArrayUtil.copy(data, n3, 3);
            n3 += 3;
            final int n4 = (copy2[0] & 0xFF) << 16 | (copy2[1] & 0xFF) << 8 | (copy2[2] & 0xFF);
            final byte[] copy3 = ByteArrayUtil.copy(data, n3, n4);
            int n5 = n3 + n4;
            final JAPCertificate instance = JAPCertificate.getInstance(copy3);
            LogHolder.log(7, LogType.MISC, "[SERVER_CERTIFICATE] " + instance.getIssuer().toString());
            LogHolder.log(7, LogType.MISC, "[SERVER_CERTIFICATE] " + instance.getSubject().toString());
            TinyTLS.this.m_servercertificate = instance;
            TinyTLS.this.m_selectedciphersuite.setServerCertificate(instance);
            while (n5 - n < n2) {
                final byte[] copy4 = ByteArrayUtil.copy(data, n5, 3);
                n5 += 3;
                final int n6 = (copy4[0] & 0xFF) << 16 | (copy4[1] & 0xFF) << 8 | (copy4[2] & 0xFF);
                final byte[] copy5 = ByteArrayUtil.copy(data, n5, n6);
                n5 += n6;
                final JAPCertificate instance2 = JAPCertificate.getInstance(copy5);
                LogHolder.log(7, LogType.MISC, "[NEXT_CERTIFICATE] " + instance2.getIssuer().toString());
                LogHolder.log(7, LogType.MISC, "[NEXT_CERTIFICATE] " + instance2.getSubject().toString());
                vector.addElement(instance2);
            }
            JAPCertificate access$400 = TinyTLS.this.m_servercertificate;
            for (int i = 0; i < vector.size(); ++i) {
                final JAPCertificate japCertificate = vector.elementAt(i);
                if (!access$400.verify(japCertificate.getPublicKey())) {
                    throw new IOException("TLS Server Certs could not be verified!");
                }
                access$400 = japCertificate;
            }
            if (TinyTLS.this.m_checkTrustedRoot && !access$400.verify(TinyTLS.this.m_trustedRoot)) {
                throw new IOException("TLS Server Cert could not be verified to be trusted!");
            }
        }
        
        private void gotServerKeyExchange(final TLSHandshakeRecord tlsHandshakeRecord) throws IOException {
            TinyTLS.this.m_selectedciphersuite.getKeyExchangeAlgorithm().processServerKeyExchange(tlsHandshakeRecord.getData(), 0, tlsHandshakeRecord.getLength(), TinyTLS.this.m_clientrandom, TinyTLS.this.m_serverrandom, TinyTLS.this.m_servercertificate);
        }
        
        private void gotCertificateRequest(final TLSHandshakeRecord tlsHandshakeRecord) {
            final byte[] data = tlsHandshakeRecord.getData();
            final int n = 0;
            tlsHandshakeRecord.getLength();
            TinyTLS.this.m_certificaterequested = true;
            LogHolder.log(7, LogType.MISC, "[SERVER_CERTIFICATE_REQUEST]");
            final byte b = data[n];
            if (b > 0) {
                TinyTLS.this.m_clientcertificatetypes = ByteArrayUtil.copy(data, n + 1, b);
            }
        }
        
        private void gotServerHelloDone() {
            TinyTLS.this.m_serverhellodone = true;
            LogHolder.log(7, LogType.MISC, "[SERVER_HELLO_DONE]");
        }
        
        private void handleAlert() throws IOException {
            LogHolder.log(7, LogType.MISC, "[TLS] ALERT!");
            if (TinyTLS.this.m_handshakecompleted) {
                TinyTLS.this.m_selectedciphersuite.decode(this.m_aktTLSRecord);
            }
            final byte[] data = this.m_aktTLSRecord.getData();
            switch (data[0]) {
                case 1: {
                    switch (data[1]) {
                        case 0: {
                            LogHolder.log(7, LogType.MISC, "[RECIEVED-ALERT] TYPE=WARNING ; MESSAGE=CLOSE NOTIFY");
                            return;
                        }
                        default: {
                            throw new TLSException("TLSAlert detected!! Level : Warning - Description :" + data[1]);
                        }
                    }
                    break;
                }
                case 2: {
                    throw new TLSException("TLSAlert detected!! Level : Fatal - Description :" + data[1]);
                }
                default: {
                    throw new TLSException("Unknown TLSAlert detected!!");
                }
            }
        }
        
        protected void readServerHandshakes() throws IOException {
            while (!TinyTLS.this.m_serverhellodone) {
                if (!this.m_aktTLSRecord.hasMoreHandshakeRecords()) {
                    this.readRecord();
                    switch (this.m_aktTLSRecord.getType()) {
                        case 21: {
                            this.handleAlert();
                            break;
                        }
                        case 22: {
                            break;
                        }
                        default: {
                            throw new TLSException("Error while shaking hands");
                        }
                    }
                }
                final TLSHandshakeRecord nextHandshakeRecord = this.m_aktTLSRecord.getNextHandshakeRecord();
                final byte[] data = nextHandshakeRecord.getData();
                final int type = nextHandshakeRecord.getType();
                final int length = nextHandshakeRecord.getLength();
                TinyTLS.this.m_handshakemessages = ByteArrayUtil.conc(TinyTLS.this.m_handshakemessages, nextHandshakeRecord.getHeader(), 4);
                TinyTLS.this.m_handshakemessages = ByteArrayUtil.conc(TinyTLS.this.m_handshakemessages, data, length);
                switch (type) {
                    case 2: {
                        this.gotServerHello(nextHandshakeRecord);
                        continue;
                    }
                    case 11: {
                        this.gotCertificate(nextHandshakeRecord);
                        continue;
                    }
                    case 12: {
                        this.gotServerKeyExchange(nextHandshakeRecord);
                        continue;
                    }
                    case 13: {
                        this.gotCertificateRequest(nextHandshakeRecord);
                        continue;
                    }
                    case 14: {
                        this.gotServerHelloDone();
                        continue;
                    }
                    default: {
                        throw new TLSException("Unexpected Handshake type: " + type);
                    }
                }
            }
        }
        
        protected void readServerFinished() throws IOException {
            this.readRecord();
            switch (this.m_aktTLSRecord.getType()) {
                case 20: {
                    if (this.m_aktTLSRecord.getLength() == 1 && this.m_aktTLSRecord.getData()[0] == 1) {
                        LogHolder.log(7, LogType.MISC, "[SERVER_CHANGE_CIPHER_SPEC]");
                        break;
                    }
                    break;
                }
                case 21: {
                    this.handleAlert();
                    break;
                }
                default: {
                    throw new TLSException("Error while shaking hands");
                }
            }
            this.readRecord();
            switch (this.m_aktTLSRecord.getType()) {
                case 22: {
                    LogHolder.log(7, LogType.MISC, "[SERVER_FINISHED]");
                    TinyTLS.this.m_selectedciphersuite.processServerFinished(this.m_aktTLSRecord, TinyTLS.this.m_handshakemessages);
                    break;
                }
                case 21: {
                    this.handleAlert();
                    break;
                }
                default: {
                    throw new TLSException("Error while shaking hands");
                }
            }
        }
    }
    
    class TLSOutputStream extends OutputStream
    {
        private OutputStream m_stream;
        private TLSPlaintextRecord m_aktTLSRecord;
        
        public TLSOutputStream(final OutputStream stream) {
            this.m_aktTLSRecord = new TLSPlaintextRecord();
            this.m_stream = stream;
        }
        
        public void write(final byte[] array) throws IOException {
            this.send(23, array, 0, array.length);
        }
        
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            this.send(23, array, n, n2);
        }
        
        public void write(final int n) throws IOException {
            this.write(new byte[] { (byte)n });
        }
        
        public void close() throws IOException {
            this.sendCloseNotify();
            this.m_stream.close();
        }
        
        public void flush() throws IOException {
            this.m_stream.flush();
        }
        
        private synchronized void send(final int type, final byte[] array, final int n, final int length) throws IOException {
            final byte[] data = this.m_aktTLSRecord.getData();
            System.arraycopy(array, n, data, 0, length);
            this.m_aktTLSRecord.setLength(length);
            this.m_aktTLSRecord.setType(type);
            if (TinyTLS.this.m_encrypt) {
                TinyTLS.this.m_selectedciphersuite.encode(this.m_aktTLSRecord);
            }
            this.m_stream.write(this.m_aktTLSRecord.getHeader());
            this.m_stream.write(data, 0, this.m_aktTLSRecord.getLength());
            this.m_stream.flush();
        }
        
        public void sendHandshake(final int n, final byte[] array) throws IOException {
            final byte[] conc = ByteArrayUtil.conc(new byte[] { (byte)n }, ByteArrayUtil.inttobyte(array.length, 3), array);
            this.send(22, conc, 0, conc.length);
            TinyTLS.this.m_handshakemessages = ByteArrayUtil.conc(TinyTLS.this.m_handshakemessages, conc);
        }
        
        public void sendClientHello() throws IOException {
            final byte[] array = new byte[28];
            final byte[] array2 = { 0 };
            final byte[] array3 = new byte[TinyTLS.this.m_supportedciphersuites.size() * 2];
            int n = 0;
            for (int i = 0; i < TinyTLS.this.m_supportedciphersuites.size(); ++i) {
                final CipherSuite cipherSuite = TinyTLS.this.m_supportedciphersuites.elementAt(i);
                array3[n] = cipherSuite.getCipherSuiteCode()[0];
                ++n;
                array3[n] = cipherSuite.getCipherSuiteCode()[1];
                ++n;
            }
            final byte[] conc = ByteArrayUtil.conc(ByteArrayUtil.inttobyte(TinyTLS.this.m_supportedciphersuites.size() * 2, 2), array3);
            final byte[] array4 = { 1, 0 };
            final byte[] inttobyte = ByteArrayUtil.inttobyte(System.currentTimeMillis() / 1000L, 4);
            new Random(System.currentTimeMillis()).nextBytes(array);
            this.sendHandshake(1, ByteArrayUtil.conc(TinyTLS.PROTOCOLVERSION, inttobyte, array, array2, conc, array4));
            TinyTLS.this.m_clientrandom = ByteArrayUtil.conc(inttobyte, array);
            LogHolder.log(7, LogType.MISC, "[CLIENT_HELLO]");
        }
        
        public void sendClientCertificate() throws IOException {
            LogHolder.log(7, LogType.MISC, "[CLIENT_CERTIFICATE]");
            if (TinyTLS.this.m_certificaterequested) {
                if (TinyTLS.this.m_clientcertificatetypes != null && TinyTLS.this.m_clientcertificates != null) {
                    for (int i = 0; i < TinyTLS.this.m_clientcertificatetypes.length; ++i) {
                        switch (TinyTLS.this.m_clientcertificatetypes[i]) {
                            case 1: {
                                byte[] conc = new byte[0];
                                for (int j = 0; j < TinyTLS.this.m_clientcertificates.length; ++j) {
                                    final byte[] byteArray = TinyTLS.this.m_clientcertificates[j].toByteArray(false);
                                    conc = ByteArrayUtil.conc(conc, ByteArrayUtil.inttobyte(byteArray.length, 3), byteArray);
                                }
                                this.sendHandshake(11, ByteArrayUtil.conc(ByteArrayUtil.inttobyte(conc.length, 3), conc));
                                TinyTLS.this.m_certificateverify = true;
                                return;
                            }
                            case 2: {
                                byte[] conc2 = new byte[0];
                                for (int k = 0; k < TinyTLS.this.m_clientcertificates.length; ++k) {
                                    final byte[] byteArray2 = TinyTLS.this.m_clientcertificates[k].toByteArray(false);
                                    conc2 = ByteArrayUtil.conc(conc2, ByteArrayUtil.inttobyte(byteArray2.length, 3), byteArray2);
                                }
                                this.sendHandshake(11, ByteArrayUtil.conc(ByteArrayUtil.inttobyte(conc2.length, 3), conc2));
                                TinyTLS.this.m_certificateverify = true;
                                return;
                            }
                        }
                    }
                }
                else {
                    this.sendHandshake(11, new byte[] { 0, 0, 0 });
                }
            }
        }
        
        public void sendClientKeyExchange() throws IOException {
            final byte[] calculateClientKeyExchange = TinyTLS.this.m_selectedciphersuite.calculateClientKeyExchange();
            this.sendHandshake(16, ByteArrayUtil.conc(ByteArrayUtil.inttobyte(calculateClientKeyExchange.length, 2), calculateClientKeyExchange));
            LogHolder.log(7, LogType.MISC, "[CLIENT_KEY_EXCHANGE]");
        }
        
        public void sendCertificateVerify() throws IOException {
            if (TinyTLS.this.m_certificateverify) {
                if (TinyTLS.this.m_clientprivatekey instanceof MyRSAPrivateKey) {
                    final byte[] conc = ByteArrayUtil.conc(hash.md5(TinyTLS.this.m_handshakemessages), hash.sha(TinyTLS.this.m_handshakemessages));
                    final MyRSASignature myRSASignature = new MyRSASignature();
                    try {
                        myRSASignature.initSign(TinyTLS.this.m_clientprivatekey);
                    }
                    catch (InvalidKeyException ex) {
                        throw new TLSException("cannot encrypt signature", 2, 80);
                    }
                    final byte[] signPlain = myRSASignature.signPlain(conc);
                    this.sendHandshake(15, ByteArrayUtil.conc(ByteArrayUtil.inttobyte(signPlain.length, 2), signPlain));
                    LogHolder.log(7, LogType.MISC, "[CLIENT_CERTIFICATE_VERIFY_RSA]");
                }
                else if (TinyTLS.this.m_clientprivatekey instanceof MyDSAPrivateKey) {
                    final MyDSASignature myDSASignature = new MyDSASignature();
                    try {
                        myDSASignature.initSign(TinyTLS.this.m_clientprivatekey);
                    }
                    catch (InvalidKeyException ex2) {}
                    final byte[] sign = myDSASignature.sign(TinyTLS.this.m_handshakemessages);
                    this.sendHandshake(15, ByteArrayUtil.conc(ByteArrayUtil.inttobyte(sign.length, 2), sign));
                    LogHolder.log(7, LogType.MISC, "[CLIENT_CERTIFICATE_VERIFY_DSA]");
                }
            }
        }
        
        public void sendChangeCipherSpec() throws IOException {
            this.send(20, new byte[] { 1 }, 0, 1);
            TinyTLS.this.m_encrypt = true;
            LogHolder.log(7, LogType.MISC, "[CLIENT_CHANGE_CIPHER_SPEC]");
        }
        
        public void sendCloseNotify() throws IOException {
            this.send(21, new byte[] { 1, 0 }, 0, 2);
            LogHolder.log(7, LogType.MISC, "[CLIENT_CLOSE_NOTIFY]");
        }
        
        public void sendClientFinished() throws IOException {
            this.sendHandshake(20, TinyTLS.this.m_selectedciphersuite.getKeyExchangeAlgorithm().calculateClientFinished(TinyTLS.this.m_handshakemessages));
            LogHolder.log(7, LogType.MISC, "[CLIENT_FINISHED]");
        }
    }
    
    private interface ITLSConstants
    {
        public static final int STATE_START = 0;
        public static final int STATE_VERSION = 1;
        public static final int STATE_LENGTH = 2;
        public static final int STATE_PAYLOAD = 3;
    }
}
