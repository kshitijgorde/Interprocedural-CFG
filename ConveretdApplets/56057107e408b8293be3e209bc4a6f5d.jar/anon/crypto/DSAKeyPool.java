// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.SecureRandom;
import logging.LogHolder;
import logging.LogType;
import anon.util.ClassUtil;
import java.util.Vector;

public class DSAKeyPool
{
    private Thread m_keyCreationThread;
    private Vector m_keys;
    private boolean m_bInterrupted;
    private int m_poolSize;
    private int m_certainty;
    private int m_keyLength;
    static /* synthetic */ Class class$anon$crypto$DSAKeyPool$KeyCreationThread;
    
    public DSAKeyPool(final int poolSize) {
        this.m_keys = new Vector();
        if (poolSize < 0) {
            this.m_poolSize = 0;
        }
        else if (poolSize > 1000) {
            this.m_poolSize = 1000;
        }
        this.m_poolSize = poolSize;
        this.m_certainty = 60;
        this.m_keyLength = 1024;
    }
    
    public DSAKeyPool() {
        this(1);
    }
    
    public void start() {
        synchronized (this.m_keys) {
            if (this.m_keyCreationThread == null) {
                this.m_bInterrupted = false;
                (this.m_keyCreationThread = new Thread(new KeyCreationThread(), ClassUtil.getShortClassName((DSAKeyPool.class$anon$crypto$DSAKeyPool$KeyCreationThread == null) ? (DSAKeyPool.class$anon$crypto$DSAKeyPool$KeyCreationThread = class$("anon.crypto.DSAKeyPool$KeyCreationThread")) : DSAKeyPool.class$anon$crypto$DSAKeyPool$KeyCreationThread))).setPriority(1);
                this.m_keyCreationThread.setDaemon(true);
                this.m_keyCreationThread.start();
            }
        }
    }
    
    public void stop() {
        synchronized (this.m_keys) {
            while (this.m_keyCreationThread != null && this.m_keyCreationThread.isAlive()) {
                this.m_bInterrupted = true;
                this.m_keyCreationThread.interrupt();
                this.m_keys.notifyAll();
                try {
                    this.m_keys.wait(100L);
                }
                catch (InterruptedException ex) {
                    break;
                }
            }
            this.m_keyCreationThread = null;
        }
    }
    
    public AsymmetricCryptoKeyPair popKeyPair() {
        AsymmetricCryptoKeyPair asymmetricCryptoKeyPair = null;
        synchronized (this.m_keys) {
            boolean b = false;
            if (this.m_keyCreationThread == null) {
                this.start();
            }
            if (this.m_poolSize == 0) {
                b = true;
                this.m_poolSize = 1;
            }
            while (this.m_keys.size() == 0 && this.m_keyCreationThread != null && !this.m_bInterrupted) {
                try {
                    this.m_keys.notify();
                    this.m_keys.wait(500L);
                }
                catch (InterruptedException ex) {
                    break;
                }
            }
            if (this.m_keys.size() > 0) {
                if (b) {
                    this.m_poolSize = 0;
                }
                asymmetricCryptoKeyPair = this.m_keys.firstElement();
                this.m_keys.removeElementAt(0);
                this.m_keys.notify();
            }
        }
        return asymmetricCryptoKeyPair;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private class KeyCreationThread implements Runnable
    {
        public void run() {
            LogHolder.log(5, LogType.CRYPTO, "Starting DSA key pool...");
            while (!DSAKeyPool.this.m_bInterrupted) {
                synchronized (DSAKeyPool.this.m_keys) {
                    if (DSAKeyPool.this.m_bInterrupted) {
                        return;
                    }
                    if (DSAKeyPool.this.m_keys.size() >= DSAKeyPool.this.m_poolSize) {
                        try {
                            DSAKeyPool.this.m_keys.wait();
                        }
                        catch (InterruptedException ex) {
                            return;
                        }
                    }
                    if (DSAKeyPool.this.m_keys.size() >= DSAKeyPool.this.m_poolSize) {
                        continue;
                    }
                }
                LogHolder.log(6, LogType.CRYPTO, "Creating DSA key pair " + (DSAKeyPool.this.m_keys.size() + 1) + " of " + DSAKeyPool.this.m_poolSize + "...");
                final DSAKeyPair instance = DSAKeyPair.getInstance(new SecureRandom(), DSAKeyPool.this.m_keyLength, DSAKeyPool.this.m_certainty);
                LogHolder.log(6, LogType.CRYPTO, "DSA key pair " + (DSAKeyPool.this.m_keys.size() + 1) + " was created.");
                DSAKeyPool.this.m_keys.addElement(instance);
                LogHolder.log(6, LogType.CRYPTO, "DSA key pair " + DSAKeyPool.this.m_keys.size() + " was added to the pool of currently " + DSAKeyPool.this.m_keys.size() + ".");
            }
        }
    }
}
