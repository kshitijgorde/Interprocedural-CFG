// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls;

import java.util.Random;
import java.io.DataOutputStream;
import java.math.BigInteger;
import anon.util.ByteArrayUtil;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.io.DataInputStream;
import java.net.SocketException;
import java.io.OutputStream;
import java.io.InputStream;
import anon.crypto.tinytls.ciphersuites.DHE_RSA_WITH_DES_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_RSA_WITH_AES_128_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_RSA_WITH_3DES_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_DSS_WITH_DES_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_DSS_WITH_AES_128_CBC_SHA;
import anon.crypto.tinytls.ciphersuites.DHE_DSS_WITH_3DES_CBC_SHA;
import anon.crypto.tinytls.keyexchange.DHE_RSA_Key_Exchange;
import anon.crypto.tinytls.keyexchange.DHE_DSS_Key_Exchange;
import logging.LogHolder;
import logging.LogType;
import java.io.IOException;
import java.net.InetAddress;
import anon.crypto.MyRSAPrivateKey;
import anon.crypto.MyDSAPrivateKey;
import anon.crypto.IMyPrivateKey;
import anon.crypto.JAPCertificate;
import anon.crypto.tinytls.ciphersuites.CipherSuite;
import java.util.Vector;
import java.net.Socket;

public class TinyTLSServerSocket extends Socket
{
    public static byte[] PROTOCOLVERSION;
    private static int PROTOCOLVERSION_SHORT;
    private Vector m_supportedciphersuites;
    private CipherSuite m_selectedciphersuite;
    private Thread m_threadCloseGuard;
    private Object SYNC_CLOSE;
    private Socket m_Socket;
    private TLSInputStream m_istream;
    private TLSOutputStream m_ostream;
    private boolean m_handshakecompleted;
    private byte[] m_clientrandom;
    private byte[] m_serverrandom;
    private JAPCertificate m_servercertificate;
    private IMyPrivateKey m_privatekey;
    private MyDSAPrivateKey m_DSSKey;
    private MyRSAPrivateKey m_RSAKey;
    private JAPCertificate m_DSSCertificate;
    private JAPCertificate m_RSACertificate;
    private byte[] m_handshakemessages;
    private boolean m_encrypt;
    static /* synthetic */ Class class$java$net$Socket;
    
    public InetAddress getInetAddress() {
        return this.m_Socket.getInetAddress();
    }
    
    public TinyTLSServerSocket(final Socket socket) throws IOException {
        this(socket, 0L);
    }
    
    public TinyTLSServerSocket(final Socket socket, final long n) throws IOException {
        this.m_selectedciphersuite = null;
        this.m_threadCloseGuard = null;
        this.SYNC_CLOSE = new Object();
        this.m_Socket = socket;
        if (n > 0L) {
            (this.m_threadCloseGuard = new Thread(new Runnable() {
                public void run() {
                    synchronized (TinyTLSServerSocket.this.SYNC_CLOSE) {
                        if (TinyTLSServerSocket.this.m_threadCloseGuard != null) {
                            try {
                                TinyTLSServerSocket.this.SYNC_CLOSE.wait(n);
                            }
                            catch (InterruptedException ex2) {}
                            if (!isClosed(TinyTLSServerSocket.this.m_Socket)) {
                                LogHolder.log(1, LogType.NET, "CloseGuard: Closing TLS socket after " + n + " milliseconds!");
                                try {
                                    TinyTLSServerSocket.this.close();
                                }
                                catch (IOException ex) {
                                    LogHolder.log(1, LogType.NET, ex);
                                }
                            }
                        }
                    }
                }
            })).start();
        }
        this.m_handshakecompleted = false;
        this.m_encrypt = false;
        this.m_supportedciphersuites = new Vector();
        this.m_istream = new TLSInputStream(socket.getInputStream());
        this.m_ostream = new TLSOutputStream(socket.getOutputStream());
        this.m_DSSCertificate = null;
        this.m_DSSKey = null;
        this.m_RSACertificate = null;
        this.m_RSAKey = null;
    }
    
    public void addCipherSuite(final CipherSuite cipherSuite) {
        if (!this.m_supportedciphersuites.contains(cipherSuite)) {
            if ((cipherSuite.getKeyExchangeAlgorithm() instanceof DHE_DSS_Key_Exchange && this.m_DSSKey != null && this.m_DSSCertificate != null) || (cipherSuite.getKeyExchangeAlgorithm() instanceof DHE_RSA_Key_Exchange && this.m_RSAKey != null && this.m_RSACertificate != null)) {
                this.m_supportedciphersuites.addElement(cipherSuite);
            }
            else {
                LogHolder.log(7, LogType.MISC, "[CIPHERSUITE NOT ADDED] : Please check if you've set the Certificate and the Private Key");
            }
        }
    }
    
    public void startHandshake() throws IOException {
        if (this.m_supportedciphersuites.isEmpty()) {
            if (this.m_DSSKey != null && this.m_DSSCertificate != null) {
                this.addCipherSuite(new DHE_DSS_WITH_3DES_CBC_SHA());
                this.addCipherSuite(new DHE_DSS_WITH_AES_128_CBC_SHA());
                this.addCipherSuite(new DHE_DSS_WITH_DES_CBC_SHA());
            }
            if (this.m_RSAKey != null && this.m_RSACertificate != null) {
                this.addCipherSuite(new DHE_RSA_WITH_3DES_CBC_SHA());
                this.addCipherSuite(new DHE_RSA_WITH_AES_128_CBC_SHA());
                this.addCipherSuite(new DHE_RSA_WITH_DES_CBC_SHA());
            }
        }
        this.m_handshakemessages = new byte[0];
        try {
            this.m_istream.readClientHello();
            this.m_ostream.sendServerHandshakes();
            this.m_istream.readClientKeyExchange();
            this.m_istream.readClientFinished();
            this.m_ostream.sendChangeCipherSpec();
            this.m_ostream.sendServerFinished();
        }
        catch (TLSException ex) {
            if (ex.Alert()) {
                this.m_ostream.send(21, new byte[] { ex.getAlertLevel(), ex.getAlertDescription() }, 0, 2);
            }
            throw ex;
        }
        this.m_handshakecompleted = true;
    }
    
    public void setDSSParameters(final JAPCertificate dssCertificate, final MyDSAPrivateKey dssKey) {
        this.m_DSSCertificate = dssCertificate;
        this.m_DSSKey = dssKey;
    }
    
    public void setRSAParameters(final JAPCertificate rsaCertificate, final MyRSAPrivateKey rsaKey) {
        this.m_RSACertificate = rsaCertificate;
        this.m_RSAKey = rsaKey;
    }
    
    public InputStream getInputStream() {
        return this.m_istream;
    }
    
    public OutputStream getOutputStream() {
        return this.m_ostream;
    }
    
    public void close() throws IOException {
        IOException ex = null;
        try {
            if (this.m_ostream != null) {
                this.m_ostream.send(21, new byte[] { 1, 0 }, 0, 2);
            }
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        try {
            if (this.m_ostream != null) {
                this.m_ostream.close();
            }
        }
        catch (IOException ex3) {
            if (ex == null) {
                ex = ex3;
            }
        }
        try {
            if (this.m_istream != null) {
                this.m_istream.close();
            }
        }
        catch (IOException ex4) {
            if (ex == null) {
                ex = ex4;
            }
        }
        if (this.m_Socket != null) {
            this.m_Socket.close();
        }
        if (this.m_threadCloseGuard != null) {
            synchronized (this.SYNC_CLOSE) {
                this.SYNC_CLOSE.notify();
                this.m_threadCloseGuard = null;
            }
        }
        if (ex != null) {
            throw ex;
        }
    }
    
    private static boolean isClosed(final Socket socket) {
        if (socket != null) {
            try {
                return (boolean)((TinyTLSServerSocket.class$java$net$Socket == null) ? (TinyTLSServerSocket.class$java$net$Socket = class$("java.net.Socket")) : TinyTLSServerSocket.class$java$net$Socket).getMethod("isClosed", (Class[])new Class[0]).invoke(socket, new Object[0]);
            }
            catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isClosed() {
        return isClosed(this.m_Socket);
    }
    
    public void setSoTimeout(final int soTimeout) throws SocketException {
        if (this.m_Socket != null) {
            this.m_Socket.setSoTimeout(soTimeout);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        TinyTLSServerSocket.PROTOCOLVERSION = new byte[] { 3, 1 };
        TinyTLSServerSocket.PROTOCOLVERSION_SHORT = 769;
    }
    
    class TLSInputStream extends InputStream
    {
        private DataInputStream m_stream;
        private int m_aktPendOffset;
        private int m_aktPendLen;
        private TLSPlaintextRecord m_aktTLSRecord;
        private int m_ReadRecordState;
        private static final int STATE_START = 0;
        private static final int STATE_VERSION = 1;
        private static final int STATE_LENGTH = 2;
        private static final int STATE_PAYLOAD = 3;
        
        public TLSInputStream(final InputStream inputStream) {
            this.m_aktTLSRecord = new TLSPlaintextRecord();
            this.m_stream = new DataInputStream(inputStream);
            this.m_aktPendOffset = 0;
            this.m_aktPendLen = 0;
            this.m_ReadRecordState = 0;
        }
        
        private synchronized void readRecord() throws IOException {
            if (this.m_ReadRecordState == 0) {
                byte byte1;
                try {
                    byte1 = this.m_stream.readByte();
                }
                catch (InterruptedIOException ex) {
                    ex.bytesTransferred = 0;
                    throw ex;
                }
                catch (SocketException ex2) {
                    throw new TLSException(ex2.getMessage(), 2, 0);
                }
                catch (EOFException ex6) {
                    throw new TLSException("EOF", 2, 0);
                }
                if (byte1 < 20 || byte1 > 23) {
                    throw new TLSException("SSL content type protocol not supported: " + byte1, 2, 10);
                }
                this.m_aktTLSRecord.setType(byte1);
                this.m_ReadRecordState = 1;
            }
            if (this.m_ReadRecordState == 1) {
                short short1;
                try {
                    short1 = this.m_stream.readShort();
                }
                catch (InterruptedIOException ex3) {
                    ex3.bytesTransferred = 0;
                    throw ex3;
                }
                if (short1 != TinyTLSServerSocket.PROTOCOLVERSION_SHORT) {
                    throw new TLSException("Protocol version not supported" + short1, 2, 70);
                }
                this.m_ReadRecordState = 2;
            }
            if (this.m_ReadRecordState == 2) {
                short short2;
                try {
                    short2 = this.m_stream.readShort();
                }
                catch (InterruptedIOException ex4) {
                    ex4.bytesTransferred = 0;
                    throw ex4;
                }
                if (short2 < 0) {
                    throw new TLSException("Wrong record len", 2, 70);
                }
                this.m_aktTLSRecord.setLength(short2);
                this.m_ReadRecordState = 3;
                this.m_aktPendOffset = 0;
            }
            if (this.m_ReadRecordState == 3) {
                int i = this.m_aktTLSRecord.getLength() - this.m_aktPendOffset;
                final byte[] data = this.m_aktTLSRecord.getData();
                while (i > 0) {
                    try {
                        final int read = this.m_stream.read(data, this.m_aktPendOffset, i);
                        if (read < 0) {
                            throw new TLSException("EOF", 2, 0);
                        }
                        i -= read;
                        this.m_aktPendOffset += read;
                    }
                    catch (InterruptedIOException ex5) {
                        this.m_aktPendOffset += ex5.bytesTransferred;
                        ex5.bytesTransferred = 0;
                        throw ex5;
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
                        case 21: {
                            this.handleAlert();
                        }
                        case 23: {
                            TinyTLSServerSocket.this.m_selectedciphersuite.decode(this.m_aktTLSRecord);
                            this.m_aktPendOffset = 0;
                            this.m_aktPendLen = this.m_aktTLSRecord.getLength();
                            continue;
                        }
                        default: {
                            throw new TLSException("Error while decoding application data", 2, 10);
                        }
                    }
                }
                catch (Throwable t) {
                    throw new TLSException("Exception by reading next TSL record: " + t.getMessage(), 2, 80);
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
        
        private void handleAlert() throws IOException {
            LogHolder.log(7, LogType.MISC, "[TLS] ALERT!");
            if (TinyTLSServerSocket.this.m_handshakecompleted) {
                TinyTLSServerSocket.this.m_selectedciphersuite.decode(this.m_aktTLSRecord);
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
        
        public void readClientHello() throws IOException {
            this.readRecord();
            final byte[] data = this.m_aktTLSRecord.getData();
            if (this.m_aktTLSRecord.getType() == 22 && data[0] == 1) {
                if ((data[4] << 8 | data[5]) == TinyTLSServerSocket.PROTOCOLVERSION_SHORT) {
                    TinyTLSServerSocket.this.m_clientrandom = new byte[32];
                    System.arraycopy(data, 6, TinyTLSServerSocket.this.m_clientrandom, 0, 32);
                    if (data[38] != 0) {
                        throw new TLSException("Client wants to reuse another session, but this is not supportet yet", 2, 40);
                    }
                    try {
                        final int n = (data[39] & 0xFF) << 8 | (data[40] & 0xFF);
                        for (int n2 = 41; n + 41 > n2 && TinyTLSServerSocket.this.m_selectedciphersuite == null; n2 += 2) {
                            int i = 0;
                            while (i < TinyTLSServerSocket.this.m_supportedciphersuites.size()) {
                                final CipherSuite cipherSuite = TinyTLSServerSocket.this.m_supportedciphersuites.elementAt(i);
                                final byte[] cipherSuiteCode = cipherSuite.getCipherSuiteCode();
                                if (data[n2] == cipherSuiteCode[0] && data[n2 + 1] == cipherSuiteCode[1]) {
                                    TinyTLSServerSocket.this.m_selectedciphersuite = cipherSuite;
                                    if (cipherSuite.getKeyExchangeAlgorithm() instanceof DHE_DSS_Key_Exchange) {
                                        TinyTLSServerSocket.this.m_servercertificate = TinyTLSServerSocket.this.m_DSSCertificate;
                                        TinyTLSServerSocket.this.m_privatekey = TinyTLSServerSocket.this.m_DSSKey;
                                        break;
                                    }
                                    if (cipherSuite.getKeyExchangeAlgorithm() instanceof DHE_RSA_Key_Exchange) {
                                        TinyTLSServerSocket.this.m_servercertificate = TinyTLSServerSocket.this.m_RSACertificate;
                                        TinyTLSServerSocket.this.m_privatekey = TinyTLSServerSocket.this.m_RSAKey;
                                        break;
                                    }
                                    LogHolder.log(7, LogType.MISC, "[ERROR!!!] : KeyExchangeAlgorithm not supported yet.(should never happen)");
                                    break;
                                }
                                else {
                                    ++i;
                                }
                            }
                        }
                        if (TinyTLSServerSocket.this.m_selectedciphersuite == null) {
                            throw new TLSException("no supported ciphersuite found", 2, 40);
                        }
                        int n3 = n + 41;
                        int j = data[n3];
                        if (j == 0) {
                            throw new TLSException("no compressionalgorithm defined. you need at least one (for example no_compression)", 2, 50);
                        }
                        while (j != 0) {
                            ++n3;
                            if (data[n3] == 0) {
                                TinyTLSServerSocket.this.m_handshakemessages = ByteArrayUtil.conc(TinyTLSServerSocket.this.m_handshakemessages, data, this.m_aktTLSRecord.getLength());
                                return;
                            }
                            --j;
                        }
                        throw new TLSException("no supportet compressionalgorithm found", 2, 40);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        throw new TLSException("client hello is not long enough", 2, 50);
                    }
                }
                throw new TLSException("this Protocol is not supported", 2, 70);
            }
            throw new TLSException("Client hello expected but another message was received", 2, 10);
        }
        
        public void readClientKeyExchange() throws IOException {
            this.readRecord();
            final byte[] data = this.m_aktTLSRecord.getData();
            try {
                if (data[0] == 16) {
                    final int n = (data[4] & 0xFF) << 8 | data[5];
                    TinyTLSServerSocket.this.m_selectedciphersuite.processClientKeyExchange(new BigInteger(ByteArrayUtil.conc(new byte[] { 0 }, ByteArrayUtil.copy(data, 6, this.m_aktTLSRecord.getLength() - 6))));
                    TinyTLSServerSocket.this.m_handshakemessages = ByteArrayUtil.conc(TinyTLSServerSocket.this.m_handshakemessages, data, this.m_aktTLSRecord.getLength());
                    return;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new TLSException(ex.getLocalizedMessage(), 2, 50);
            }
            throw new TLSException("Client Key Exchange expected, but another messagetype was recieved", 2, 10);
        }
        
        public void readClientFinished() throws IOException {
            this.readRecord();
            final byte[] data = this.m_aktTLSRecord.getData();
            if (this.m_aktTLSRecord.getType() == 20 && this.m_aktTLSRecord.getLength() == 1 && data[0] == 1) {
                TinyTLSServerSocket.this.m_encrypt = true;
                this.readRecord();
                TinyTLSServerSocket.this.m_selectedciphersuite.decode(this.m_aktTLSRecord);
                try {
                    if (data[0] == 20) {
                        TinyTLSServerSocket.this.m_selectedciphersuite.getKeyExchangeAlgorithm().processClientFinished(ByteArrayUtil.copy(data, 4, 12), TinyTLSServerSocket.this.m_handshakemessages);
                        TinyTLSServerSocket.this.m_handshakemessages = ByteArrayUtil.conc(TinyTLSServerSocket.this.m_handshakemessages, data, this.m_aktTLSRecord.getLength());
                        return;
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    throw new TLSException(ex.getLocalizedMessage(), 2, 50);
                }
                throw new TLSException("Client Finish message expected, but another message was recieved", 2, 10);
            }
            throw new TLSException("Change Cipher Spec expected", 2, 10);
        }
    }
    
    class TLSOutputStream extends OutputStream
    {
        private DataOutputStream m_stream;
        private TLSPlaintextRecord m_aktTLSRecord;
        
        public TLSOutputStream(final OutputStream outputStream) {
            this.m_aktTLSRecord = new TLSPlaintextRecord();
            this.m_stream = new DataOutputStream(outputStream);
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
        
        public void flush() throws IOException {
            this.m_stream.flush();
        }
        
        private synchronized void send(final int type, final byte[] array, final int n, final int length) throws IOException {
            final byte[] data = this.m_aktTLSRecord.getData();
            System.arraycopy(array, n, data, 0, length);
            this.m_aktTLSRecord.setLength(length);
            this.m_aktTLSRecord.setType(type);
            if (TinyTLSServerSocket.this.m_encrypt) {
                TinyTLSServerSocket.this.m_selectedciphersuite.encode(this.m_aktTLSRecord);
            }
            try {
                this.m_stream.write(this.m_aktTLSRecord.getHeader());
            }
            catch (SocketException ex) {
                throw new TLSException(ex.getMessage(), 2, 0);
            }
            this.m_stream.write(data, 0, this.m_aktTLSRecord.getLength());
            this.m_stream.flush();
        }
        
        public void sendHandshake(final int n, final byte[] array) throws IOException {
            final byte[] conc = ByteArrayUtil.conc(new byte[] { (byte)n }, ByteArrayUtil.inttobyte(array.length, 3), array);
            this.send(22, conc, 0, conc.length);
            TinyTLSServerSocket.this.m_handshakemessages = ByteArrayUtil.conc(TinyTLSServerSocket.this.m_handshakemessages, conc);
        }
        
        public void sendServerHello() throws IOException {
            final byte[] array = new byte[28];
            final byte[] array2 = { 0 };
            final byte[] cipherSuiteCode = TinyTLSServerSocket.this.m_selectedciphersuite.getCipherSuiteCode();
            final byte[] array3 = { 0 };
            final byte[] inttobyte = ByteArrayUtil.inttobyte(System.currentTimeMillis() / 1000L, 4);
            new Random(System.currentTimeMillis()).nextBytes(array);
            TinyTLSServerSocket.this.m_serverrandom = ByteArrayUtil.conc(inttobyte, array);
            this.sendHandshake(2, ByteArrayUtil.conc(TinyTLSServerSocket.PROTOCOLVERSION, TinyTLSServerSocket.this.m_serverrandom, array2, cipherSuiteCode, array3));
        }
        
        public void sendServerCertificate() throws IOException {
            final byte[] byteArray = TinyTLSServerSocket.this.m_servercertificate.toByteArray();
            final byte[] conc = ByteArrayUtil.conc(ByteArrayUtil.inttobyte(byteArray.length, 3), byteArray);
            this.sendHandshake(11, ByteArrayUtil.conc(ByteArrayUtil.inttobyte(conc.length, 3), conc));
        }
        
        public void sendServerKeyExchange() throws IOException {
            this.sendHandshake(12, TinyTLSServerSocket.this.m_selectedciphersuite.getKeyExchangeAlgorithm().generateServerKeyExchange(TinyTLSServerSocket.this.m_privatekey, TinyTLSServerSocket.this.m_clientrandom, TinyTLSServerSocket.this.m_serverrandom));
        }
        
        public void sendServerHelloDone() throws IOException {
            this.sendHandshake(14, new byte[0]);
        }
        
        public void sendServerHandshakes() throws IOException {
            this.sendServerHello();
            this.sendServerCertificate();
            this.sendServerKeyExchange();
            this.sendServerHelloDone();
        }
        
        public void sendChangeCipherSpec() throws IOException {
            TinyTLSServerSocket.this.m_encrypt = false;
            this.send(20, new byte[] { 1 }, 0, 1);
            TinyTLSServerSocket.this.m_encrypt = true;
        }
        
        public void sendServerFinished() throws IOException {
            this.sendHandshake(20, TinyTLSServerSocket.this.m_selectedciphersuite.getKeyExchangeAlgorithm().calculateServerFinished(TinyTLSServerSocket.this.m_handshakemessages));
        }
    }
}
