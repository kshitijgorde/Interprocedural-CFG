// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import logging.LogHolder;
import logging.LogType;
import java.security.SecureRandom;

public final class KeyPool implements Runnable
{
    private SecureRandom sr;
    private KeyList pool;
    private KeyList aktKey;
    private int keySize;
    private int poolSize;
    private Object l1;
    private Object l2;
    private boolean runflag;
    private static KeyPool m_KeyPool;
    private Thread m_KeyPoolThread;
    
    private KeyPool(final int poolSize, final int keySize) {
        this.m_KeyPoolThread = null;
        this.keySize = keySize;
        this.poolSize = poolSize;
        this.pool = null;
        this.aktKey = null;
        this.l1 = new Object();
        this.l2 = new Object();
        (this.m_KeyPoolThread = new Thread(this, "JAP - KeyPool")).setDaemon(true);
        this.m_KeyPoolThread.setPriority(1);
        this.m_KeyPoolThread.start();
    }
    
    public static synchronized KeyPool start() {
        if (KeyPool.m_KeyPool == null) {
            KeyPool.m_KeyPool = new KeyPool(20, 16);
        }
        return KeyPool.m_KeyPool;
    }
    
    public void run() {
        try {
            this.sr = new SecureRandom(SecureRandom.getSeed(20));
        }
        catch (Throwable t) {
            this.sr = new SecureRandom();
        }
        this.pool = new KeyList(this.keySize);
        for (int i = 1; i < this.poolSize; ++i) {
            final KeyList pool = new KeyList(this.keySize);
            pool.next = this.pool;
            this.pool = pool;
        }
        this.aktKey = null;
        this.runflag = true;
        while (this.runflag) {
            if (this.pool != null) {
                synchronized (this) {
                    this.sr.nextBytes(this.pool.key);
                    final KeyList pool2 = this.pool;
                    this.pool = this.pool.next;
                    pool2.next = this.aktKey;
                    this.aktKey = pool2;
                    synchronized (this.l2) {
                        this.l2.notify();
                    }
                    continue;
                }
            }
            try {
                synchronized (this.l1) {
                    this.l1.wait();
                }
            }
            catch (InterruptedException ex) {
                LogHolder.log(7, LogType.MISC, "JAPKeyPool:run() waiting interrupted!");
            }
        }
    }
    
    public static int getKey(final byte[] array) {
        return getKey(array, 0);
    }
    
    public static int getKey(final byte[] array, final int n) {
        if (array == null || array.length - n < KeyPool.m_KeyPool.keySize) {
            return -1;
        }
        if (KeyPool.m_KeyPool.aktKey == null) {
            try {
                synchronized (KeyPool.m_KeyPool.l2) {
                    KeyPool.m_KeyPool.l2.wait();
                }
            }
            catch (InterruptedException ex) {
                LogHolder.log(7, LogType.MISC, "JAPKeyPool:getKey() waiting interrupted!");
            }
        }
        synchronized (KeyPool.m_KeyPool) {
            System.arraycopy(KeyPool.m_KeyPool.aktKey.key, 0, array, n, KeyPool.m_KeyPool.keySize);
            final KeyList aktKey = KeyPool.m_KeyPool.aktKey;
            KeyPool.m_KeyPool.aktKey = KeyPool.m_KeyPool.aktKey.next;
            aktKey.next = KeyPool.m_KeyPool.pool;
            KeyPool.m_KeyPool.pool = aktKey;
        }
        synchronized (KeyPool.m_KeyPool.l1) {
            KeyPool.m_KeyPool.l1.notify();
        }
        return 0;
    }
    
    static {
        KeyPool.m_KeyPool = null;
    }
    
    private final class KeyList
    {
        public byte[] key;
        public KeyList next;
        
        public KeyList(final int n) {
            this.key = new byte[n];
            this.next = null;
        }
    }
}
