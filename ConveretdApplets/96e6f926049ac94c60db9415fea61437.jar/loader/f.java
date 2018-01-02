// 
// Decompiled by Procyon v0.5.30
// 

package loader;

class f implements Runnable
{
    private Object a;
    private volatile boolean b;
    private final Loader c;
    private static String z;
    
    private void a() {
        synchronized (this.a) {
            this.b = false;
        }
        // monitorexit(this.a)
    }
    
    public void run() {
        while (true) {
            synchronized (this.a) {
                if (!this.b) {
                    // monitorexit(this.a)
                    break;
                }
            }
            // monitorexit(this.a)
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            Loader.a(this.c, false);
        }
    }
    
    static void a(final f f) {
        f.a();
    }
    
    private f(final Loader c) {
        this.c = c;
        this.a = new Object();
        this.b = true;
        final Thread thread = new Thread(this, f.z);
        thread.setDaemon(true);
        thread.start();
    }
    
    f(final Loader loader, final d d) {
        this(loader);
    }
    
    static {
        final char[] charArray = "X)S4\u001bW+Ug\u0007V7W&\u0017".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '>';
                            break;
                        }
                        case 1: {
                            c2 = 'E';
                            break;
                        }
                        case 2: {
                            c2 = '2';
                            break;
                        }
                        case 3: {
                            c2 = 'G';
                            break;
                        }
                        default: {
                            c2 = 's';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                f.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
