// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import java.util.Enumeration;
import anon.crypto.MyRSAPublicKey;
import java.util.Vector;
import anon.infoservice.ImmutableProxyInterface;
import anon.infoservice.IMutableProxyInterface;
import anon.crypto.PKCS12;
import anon.crypto.AsymmetricCryptoKeyPair;
import anon.crypto.JAPCertificate;
import anon.crypto.Validity;
import java.util.Calendar;
import anon.crypto.X509DistinguishedName;
import java.math.BigInteger;
import anon.crypto.IMyPublicKey;
import java.io.IOException;
import java.io.InterruptedIOException;
import logging.LogHolder;
import logging.LogType;
import anon.tor.cells.Cell;
import java.util.Random;
import java.security.SecureRandom;
import anon.crypto.RSAKeyPair;
import anon.crypto.MyRandom;
import java.util.Hashtable;
import java.io.OutputStream;
import java.io.InputStream;
import anon.tor.ordescription.ORDescriptor;
import anon.crypto.tinytls.TinyTLS;

public class FirstOnionRouterConnection implements Runnable
{
    private static String OP_NAME;
    private TinyTLS m_tinyTLS;
    private ORDescriptor m_description;
    private Thread m_readDataLoop;
    private InputStream m_istream;
    private OutputStream m_ostream;
    private Hashtable m_Circuits;
    private volatile boolean m_bRun;
    private boolean m_bIsClosed;
    private MyRandom m_rand;
    private Object m_oSendSync;
    private long m_inittimeout;
    private Tor m_Tor;
    private RSAKeyPair m_keypairIdentityKey;
    
    public FirstOnionRouterConnection(final ORDescriptor description, final Tor tor) {
        this.m_bIsClosed = true;
        this.m_inittimeout = 30000L;
        this.m_readDataLoop = null;
        this.m_bRun = false;
        this.m_bIsClosed = true;
        this.m_description = description;
        this.m_rand = new MyRandom(new SecureRandom());
        this.m_oSendSync = new Object();
        this.m_Tor = tor;
    }
    
    public ORDescriptor getORDescription() {
        return this.m_description;
    }
    
    public boolean isClosed() {
        return this.m_bIsClosed;
    }
    
    public void send(final Cell cell) throws IOException {
        synchronized (this.m_oSendSync) {
            while (true) {
                try {
                    this.m_ostream.write(cell.getCellData());
                    this.m_ostream.flush();
                    LogHolder.log(7, LogType.TOR, "OnionConnection " + this.m_description.getName() + " Send a cell");
                }
                catch (InterruptedIOException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    private boolean dispatchCell(final Cell cell) {
        try {
            final int circuitID = cell.getCircuitID();
            LogHolder.log(7, LogType.MISC, "OnionProxy read() Tor Cell - Circuit: " + circuitID + " Type: " + cell.getCommand());
            final Circuit circuit = this.m_Circuits.get(new Integer(circuitID));
            if (circuit != null) {
                circuit.dispatchCell(cell);
            }
            else {
                this.m_Circuits.remove(new Integer(circuitID));
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public synchronized void connect() throws Exception {
        final IMutableProxyInterface proxy = this.m_Tor.getProxy();
        ImmutableProxyInterface proxyInterface = null;
        if (proxy != null) {
            proxyInterface = proxy.getProxyInterface(false).getProxyInterface();
        }
        (this.m_tinyTLS = new FirstOnionRouterConnectionThread(this.m_description.getAddress(), this.m_description.getPort(), this.m_inittimeout, proxyInterface).getConnection()).setRootKey(this.m_description.getSigningKey());
        try {
            final RSAKeyPair instance = RSAKeyPair.getInstance(new BigInteger(new byte[] { 1, 0, 1 }), new SecureRandom(), 1024, 100);
            final JAPCertificate instance2 = JAPCertificate.getInstance(new X509DistinguishedName("CN=" + FirstOnionRouterConnection.OP_NAME), instance, new Validity(Calendar.getInstance(), 1));
            this.m_keypairIdentityKey = RSAKeyPair.getInstance(new BigInteger(new byte[] { 1, 0, 1 }), new SecureRandom(), 1024, 100);
            final PKCS12 pkcs12 = new PKCS12(new X509DistinguishedName("CN=" + FirstOnionRouterConnection.OP_NAME + " <identity>"), this.m_keypairIdentityKey, new Validity(Calendar.getInstance(), 1));
            this.m_tinyTLS.setClientCertificate(new JAPCertificate[] { instance2.sign(pkcs12), JAPCertificate.getInstance(pkcs12.getX509Certificate()) }, instance.getPrivate());
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.TOR, "Error while creating Certificates. Certificates are not used.");
        }
        this.m_tinyTLS.setSoTimeout(30000);
        this.m_tinyTLS.startHandshake();
        this.m_istream = this.m_tinyTLS.getInputStream();
        this.m_ostream = this.m_tinyTLS.getOutputStream();
        this.m_Circuits = new Hashtable();
        this.m_tinyTLS.setSoTimeout(1000);
        this.start();
        this.m_bIsClosed = false;
    }
    
    public synchronized Circuit createCircuit(final Vector vector) {
        int nextInt = 0;
        try {
            int n = 32768;
            if (this.m_description.getSigningKey().getModulus().compareTo(((MyRSAPublicKey)this.m_keypairIdentityKey.getPublic()).getModulus()) > 0) {
                n = 0;
            }
            do {
                nextInt = this.m_rand.nextInt(32767);
                nextInt |= n;
            } while (this.m_Circuits.containsKey(new Integer(nextInt)) && nextInt != 0);
            final Circuit circuit = new Circuit(nextInt, this, vector);
            this.m_Circuits.put(new Integer(nextInt), circuit);
            circuit.create();
            return circuit;
        }
        catch (Exception ex) {
            this.m_Circuits.remove(new Integer(nextInt));
            return null;
        }
    }
    
    private void start() {
        if (this.m_readDataLoop == null) {
            this.m_bRun = true;
            (this.m_readDataLoop = new Thread(this, "FirstOnionRouterConnection - " + this.m_description.getName())).setDaemon(true);
            this.m_readDataLoop.start();
        }
    }
    
    public void run() {
        final byte[] array = new byte[512];
        while (this.m_bRun) {
            int n = 0;
            while (n < 512 && this.m_bRun) {
                int read;
                try {
                    read = this.m_istream.read(array, n, 512 - n);
                }
                catch (InterruptedIOException ex) {
                    continue;
                }
                catch (IOException ex2) {
                    break;
                }
                if (read <= 0) {
                    break;
                }
                n += read;
            }
            if (n != 512) {
                this.closedByPeer();
                return;
            }
            LogHolder.log(7, LogType.TOR, "OnionConnection " + this.m_description.getName() + " received a Cell!");
            final Cell cell = Cell.createCell(array);
            if (cell == null) {
                LogHolder.log(0, LogType.TOR, "OnionConnection " + this.m_description.getName() + " dont know about this Cell!");
            }
            if (cell == null || !this.dispatchCell(cell)) {
                this.closedByPeer();
            }
        }
    }
    
    private void stop() throws IOException {
        if (this.m_readDataLoop != null && this.m_bRun) {
            try {
                this.m_bRun = false;
                this.m_readDataLoop.interrupt();
                this.m_readDataLoop.join();
            }
            catch (Throwable t) {}
        }
        this.m_readDataLoop = null;
    }
    
    public synchronized void close() {
        try {
            if (!this.m_bIsClosed) {
                this.m_bIsClosed = true;
                this.stop();
                this.m_tinyTLS.close();
                final Enumeration<Circuit> elements = this.m_Circuits.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().close();
                }
                this.m_Circuits.clear();
            }
        }
        catch (Throwable t) {}
    }
    
    private void closedByPeer() {
        if (this.m_bIsClosed) {
            return;
        }
        synchronized (this) {
            try {
                this.stop();
                this.m_tinyTLS.close();
                final Enumeration<Circuit> elements = this.m_Circuits.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().destroyedByPeer();
                }
                this.m_Circuits.clear();
            }
            catch (Throwable t) {}
            this.m_bIsClosed = true;
        }
    }
    
    protected void notifyCircuitClosed(final Circuit circuit) {
        this.m_Circuits.remove(new Integer(circuit.getCircID()));
    }
    
    static {
        FirstOnionRouterConnection.OP_NAME = "JAPClient";
    }
}
