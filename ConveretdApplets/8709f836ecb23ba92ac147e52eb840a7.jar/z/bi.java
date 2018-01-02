// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.Hashtable;

public class bi
{
    private static /* synthetic */ boolean a;
    
    public static void a(final byte[][] array, final int n, final int n2, final boolean b) {
        if (!bi.a && n <= 0) {
            throw new AssertionError();
        }
        if (!bi.a && n2 <= 0) {
            throw new AssertionError();
        }
        System.out.println("Fixing Exif (w,h)<=(" + n + "," + n2 + ")");
        final aH ah = new aH(array[0]);
        try {
            final Hashtable<Object, s> hashtable = new Hashtable<Object, s>();
            final boolean b2 = false;
            final aH ah2 = ah;
            final Hashtable<Object, s> hashtable2 = hashtable;
            final aH ah3 = ah2;
            final boolean b3 = b2;
            ah3.a("Exif\ufffd\ufffd\ufffd\ufffd");
            final int a = ah3.a();
            if (b3) {
                System.out.println("exif origin = " + String.format("%x", a));
            }
            ah3.a = (ah3.b() == 19789);
            if (ah3.b() != 42) {
                throw new DataFormatException("Missing 002a Exif marker.");
            }
            ah3.b(a + ah3.c());
            if (b3) {
                System.out.println("Parsing IFD0: ");
            }
            a(ah3, hashtable2, b3);
            if (hashtable2.containsKey(34665)) {
                ah3.b(a + hashtable2.get(34665).c);
                if (b3) {
                    System.out.println("Parsing EXIF IFD: ");
                }
                a(ah3, hashtable2, b3);
            }
            if (hashtable2.containsKey(40965)) {
                ah3.b(a + hashtable2.get(40965).c);
                if (b3) {
                    System.out.println("Parsing Interop IFD: ");
                }
                a(ah3, hashtable2, b3);
            }
            final Iterator<Map.Entry<Object, s>> iterator = hashtable.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<Object, s> entry;
                final int intValue = (entry = iterator.next()).getKey();
                final s s = entry.getValue();
                switch (intValue) {
                    case 256:
                    case 4097:
                    case 40962: {
                        ah.b(s.b);
                        if (s.a == 3 || s.a == 8) {
                            ah.c(n);
                            ah.a(2);
                            s.c = ah.b();
                            continue;
                        }
                        if (s.a == 4 || s.a == 9) {
                            ah.d(n);
                            ah.a(4);
                            s.c = ah.c();
                            continue;
                        }
                        throw new RuntimeException("Unexpected tag data format = " + s.a);
                    }
                    case 257:
                    case 4098:
                    case 40963: {
                        ah.b(s.b);
                        if (s.a == 3 || s.a == 8) {
                            ah.c(n2);
                            ah.a(2);
                            s.c = ah.b();
                            continue;
                        }
                        if (s.a == 4 || s.a == 9) {
                            ah.d(n2);
                            ah.a(4);
                            s.c = ah.c();
                            continue;
                        }
                        throw new RuntimeException("Unexpected tag data format = " + s.a);
                    }
                }
            }
        }
        catch (DataFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void a(final aH ah, final Hashtable hashtable, final boolean b) {
        if (!bi.a && ah == null) {
            throw new AssertionError();
        }
        for (int b2 = ah.b(), i = 0; i < b2; ++i) {
            final int b3 = ah.b();
            final int b4 = ah.b();
            final int c = ah.c();
            final int a = ah.a();
            final int c2 = ah.c();
            if (b) {
                System.out.println(String.format("%02d: T=%04x f=%04x #=%08x value=%08x", i, b3, b4, c, c2));
            }
            switch (b3) {
                case 256:
                case 257:
                case 4097:
                case 4098:
                case 34665:
                case 40961:
                case 40962:
                case 40963:
                case 40965: {
                    hashtable.put(b3, new s(b4, c, a, c2));
                    break;
                }
            }
        }
    }
    
    private static void a(final L l, final Hashtable hashtable, final boolean b) {
        for (int b2 = l.b(), i = 0; i < b2; ++i) {
            final int b3 = l.b();
            final int b4 = l.b();
            final int c = l.c();
            final int n = (int)l.a();
            final int c2 = l.c();
            if (b) {
                System.out.println(String.format("%02d: T=%04x f=%04x #=%08x value=%08x", i, b3, b4, c, c2));
            }
            switch (b3) {
                case 259:
                case 513:
                case 514: {
                    hashtable.put(b3, new s(b4, c, n, c2));
                    break;
                }
            }
        }
    }
    
    public static byte[] a(final RandomAccessFile randomAccessFile, final long n, final boolean b) {
        if (n < 0L || n > 2147483647L) {
            throw new RuntimeException("extractThumbnail: invalid exif offset");
        }
        try {
            final L l;
            (l = new L(randomAccessFile)).a((int)n);
            l.a("Exif\ufffd\ufffd\ufffd\ufffd");
            final int n2 = (int)l.a();
            l.a = (l.b() == 19789);
            if (l.b() != 42) {
                throw new DataFormatException("Missing 002a Exif marker.");
            }
            final Hashtable<Object, s> hashtable = new Hashtable<Object, s>();
            int c;
            while ((c = l.c()) != 0) {
                l.a(n2 + c);
                a(l, hashtable, false);
            }
            if (!hashtable.containsKey(514)) {
                return null;
            }
            if (!hashtable.containsKey(513)) {
                return null;
            }
            final byte[] array = new byte[hashtable.get(514).c];
            l.a(n2 + hashtable.get(513).c);
            l.a(array);
            return array;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static {
        bi.a = !bi.class.desiredAssertionStatus();
    }
}
