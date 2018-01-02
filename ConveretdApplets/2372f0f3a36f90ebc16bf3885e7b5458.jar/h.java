import com.ms.dll.Root;
import com.ms.dll.DllLib;
import com.ms.win32.WAVEHDR;
import com.ms.win32.User32;
import com.ms.win32.MSG;
import com.ms.win32.Kernel32;
import com.ms.win32.Winmm;
import com.ms.win32.WAVEFORMATEX;

// 
// Decompiled by Procyon v0.5.30
// 

public class h implements Runnable
{
    public boolean a;
    public boolean b;
    public int c;
    public int d;
    public volatile int e;
    public Thread f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int[] k;
    public int[] l;
    public int m;
    public int n;
    public int o;
    public static /* synthetic */ Class p;
    
    public static boolean h() {
        try {
            final WAVEFORMATEX waveformatex = new WAVEFORMATEX();
        }
        catch (IllegalAccessError illegalAccessError) {
            return false;
        }
        return true;
    }
    
    public h(final boolean b, final int d, final int n, final int i) {
        this.a = false;
        this.b = false;
        this.c = 0;
        this.d = 5;
        this.e = 0;
        this.f = null;
        this.g = 0;
        this.h = 8000;
        this.i = 1;
        this.j = 32;
        this.k = new int[this.j];
        this.l = new int[this.j];
        this.m = 0;
        this.n = 0;
        this.o = 0;
        final int[] array = { 99 };
        (this.f = new Thread(this)).start();
        final WAVEFORMATEX waveformatex = new WAVEFORMATEX();
        this.d = d;
        this.h = n;
        this.i = i;
        waveformatex.wFormatTag = 1;
        waveformatex.nChannels = (short)i;
        waveformatex.nSamplesPerSec = n;
        waveformatex.nAvgBytesPerSec = n * i * 2;
        waveformatex.nBlockAlign = (short)(i * 2);
        waveformatex.wBitsPerSample = 16;
        waveformatex.cbSize = 0;
        while (this.g == 0) {
            try {
                Thread.currentThread();
                Thread.sleep(5L);
            }
            catch (Exception ex) {}
        }
        try {
            if (Winmm.waveOutOpen(array, -1, waveformatex, this.g, 0, 131072) == 0) {
                this.c = array[0];
            }
            else {
                this.b = true;
            }
        }
        catch (IllegalAccessError illegalAccessError) {
            this.b = true;
        }
    }
    
    public void run() {
        this.g = Kernel32.GetCurrentThreadId();
        int n = 0;
        while (!this.a) {
            final MSG msg = new MSG();
            while (User32.PeekMessage(msg, -1, 0, 0, 1)) {
                switch (msg.message) {
                    case 955: {
                        this.b(msg.wParam, msg.lParam);
                        continue;
                    }
                    case 957: {
                        this.a(msg.wParam, msg.lParam);
                        continue;
                    }
                    case 956: {
                        this.c(msg.wParam, msg.lParam);
                        continue;
                    }
                    default: {
                        msg.hwnd = 0;
                        continue;
                    }
                }
            }
            try {
                Thread.currentThread();
                Thread.sleep(5L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.a) {
                if (++n <= 300) {
                    continue;
                }
                final int c = this.c;
                this.c = 0;
                try {
                    Winmm.waveOutPause(c);
                    Winmm.waveOutReset(c);
                    Winmm.waveOutClose(c);
                    Thread.currentThread();
                    Thread.sleep(5L);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                n = 0;
            }
            else {
                n = 0;
            }
        }
        this.f = null;
    }
    
    public synchronized void a(final int n, final int n2) {
        try {
            final WAVEHDR wavehdr = (WAVEHDR)DllLib.ptrToStruct((h.p == null) ? (h.p = class$(zkmToString("y\u0017.\b\u0000iV4O\u0003)Jmq,L=\u000bb?"))) : h.p, n2);
            Winmm.waveOutUnprepareHeader(this.c, wavehdr, 32);
            wavehdr.lpData = 0;
            Root.free(this.k[wavehdr.dwUser]);
            DllLib.freePinnedHandle(this.l[wavehdr.dwUser]);
            this.n -= wavehdr.dwBufferLength;
            --this.e;
        }
        catch (Exception ex) {}
    }
    
    public synchronized void b(final int n, final int n2) {
    }
    
    public synchronized void c(final int n, final int n2) {
        System.out.println(zkmToString("U\u0016\u0014G\u001b\u007f;/I\u001e\u007f"));
        this.a = true;
    }
    
    public synchronized int a() {
        if (this.e < this.j - 1) {
            return this.h * this.i / 4;
        }
        return 0;
    }
    
    public synchronized int b() {
        return this.n / 2;
    }
    
    public synchronized int a(final byte[] array, final int n, final int dwBufferLength) {
        int n2 = 0;
        final WAVEHDR wavehdr = new WAVEHDR();
        final byte[] array2 = new byte[dwBufferLength];
        System.arraycopy(array, n, array2, 0, dwBufferLength);
        this.k[this.m] = Root.alloc((Object)wavehdr);
        this.l[this.m] = DllLib.getPinnedHandle((Object)array2);
        wavehdr.lpData = DllLib.addrOfPinnedObject(this.l[this.m]);
        wavehdr.dwBufferLength = dwBufferLength;
        wavehdr.dwBytesRecorded = 0;
        wavehdr.dwUser = this.m;
        wavehdr.dwFlags = 0;
        wavehdr.dwLoops = 0;
        wavehdr.lpNext = 0;
        wavehdr.reserved = 0;
        try {
            n2 = Winmm.waveOutPrepareHeader(this.c, wavehdr, DllLib.sizeOf((Object)wavehdr));
            n2 = Winmm.waveOutWrite(this.c, wavehdr, DllLib.sizeOf((Object)wavehdr));
        }
        catch (Exception ex) {}
        if (n2 != 0) {
            System.out.println(n + "-" + dwBufferLength + zkmToString(":Ec") + n2);
        }
        else {
            this.n += dwBufferLength;
            this.o += dwBufferLength;
            ++this.e;
            ++this.m;
            if (this.m >= this.j) {
                this.m = 0;
            }
        }
        return dwBufferLength;
    }
    
    public synchronized void c() {
        this.a = true;
    }
    
    public synchronized void d() {
        if (this.c != 0) {
            try {
                Winmm.waveOutRestart(this.c);
            }
            catch (Exception ex) {}
        }
    }
    
    public synchronized void e() {
        if (this.c != 0) {
            try {
                Winmm.waveOutPause(this.c);
                Winmm.waveOutReset(this.c);
                Winmm.waveOutClose(this.c);
            }
            catch (Exception ex) {}
            this.c = 0;
        }
    }
    
    public synchronized void f() {
        if (this.c != 0) {
            try {
                Winmm.waveOutPause(this.c);
            }
            catch (Exception ex) {}
        }
    }
    
    public synchronized long g() {
        if (this.c != 0) {
            return (this.o - this.n) / 2 / this.i;
        }
        return -1L;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u001a';
                    break;
                }
                case 1: {
                    c2 = 'x';
                    break;
                }
                case 2: {
                    c2 = 'C';
                    break;
                }
                case 3: {
                    c2 = '&';
                    break;
                }
                default: {
                    c2 = 'm';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
