import java.io.FileOutputStream;
import java.io.File;
import java.net.Socket;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Point;
import java.awt.Component;
import java.net.InetAddress;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.Frame;
import java.awt.EventQueue;
import java.lang.reflect.Method;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class88 implements Runnable
{
    boolean aBoolean675;
    static String aString676;
    private Callback_Sub1 aCallback_Sub1_677;
    static Method aMethod678;
    private static String aString679;
    Class356 aClass356_680;
    private boolean aBoolean681;
    boolean aBoolean682;
    private Object anObject683;
    private Class20 aClass20_684;
    Class356 aClass356_685;
    private Thread aThread686;
    private Object anObject687;
    private static String aString688;
    private Class143 aClass143_689;
    static String aString690;
    private static int anInt691;
    static String aString692;
    private Object anObject693;
    private static String aString694;
    Class356[] aClass356Array695;
    static String aString696;
    private Class143 aClass143_697;
    EventQueue anEventQueue698;
    static String aString699;
    static Method aMethod700;
    private static volatile long aLong701;
    Class356 aClass356_702;
    static Class aClass703;
    static Class aClass704;
    static Class aClass705;
    static Class aClass706;
    
    final Class143 method858(final int n, final Runnable runnable, final int n2) {
        try {
            if (n2 != 1) {
                this.method874(-5);
            }
            return this.method877(0, -115, n, 2, runnable);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method859(final int n, final String s) {
        try {
            if (n != -14) {
                this.aClass20_684 = null;
            }
            return this.method877(0, -126, 0, 16, s);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final boolean method860(final byte b) {
        try {
            if (!this.aBoolean682) {
                return false;
            }
            if (!this.aBoolean675) {
                return this.anObject693 != null;
            }
            return this.aClass20_684 != null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method861(final int n) {
        try {
            Class88.aLong701 = 5000L + Class343.method3819(-47);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method862(final int n, final Frame frame) {
        try {
            if (n != -3470) {
                Class88.aString692 = null;
            }
            return this.method877(0, -66, 0, 7, frame);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void run() {
        try {
            while (true) {
                Class143 aClass143_697 = null;
                Label_0075: {
                    synchronized (this) {
                        while (!this.aBoolean681) {
                            if (this.aClass143_697 != null) {
                                aClass143_697 = this.aClass143_697;
                                this.aClass143_697 = this.aClass143_697.aClass143_1167;
                                if (this.aClass143_697 == null) {
                                    this.aClass143_689 = null;
                                }
                                break Label_0075;
                            }
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex2) {}
                        }
                        return;
                    }
                    try {
                        final int anInt1164 = aClass143_697.anInt1164;
                        Label_1757: {
                            if (~anInt1164 != 0xFFFFFFFE) {
                                if (anInt1164 == 22) {
                                    if (~Class88.aLong701 < ~Class343.method3819(-47)) {
                                        throw new IOException();
                                    }
                                    try {
                                        aClass143_697.anObject1162 = Class246_Sub3_Sub2_Sub1.method3006((String)aClass143_697.anObject1161, 0, aClass143_697.anInt1166).method305(-2);
                                        break Label_1757;
                                    }
                                    catch (IOException_Sub1 ioException_Sub1) {
                                        aClass143_697.anObject1162 = ioException_Sub1.getMessage();
                                        throw ioException_Sub1;
                                    }
                                }
                                if (anInt1164 == 2) {
                                    final Thread anObject1162 = new Thread((Runnable)aClass143_697.anObject1161);
                                    anObject1162.setDaemon(true);
                                    anObject1162.start();
                                    anObject1162.setPriority(aClass143_697.anInt1166);
                                    aClass143_697.anObject1162 = anObject1162;
                                }
                                else if (~anInt1164 != 0xFFFFFFFB) {
                                    if (~anInt1164 != 0xFFFFFFF7) {
                                        if (~anInt1164 == 0xFFFFFFF6) {
                                            final Object[] array = (Object[])aClass143_697.anObject1161;
                                            if (this.aBoolean682 && ((Class)array[0]).getClassLoader() == null) {
                                                throw new SecurityException();
                                            }
                                            aClass143_697.anObject1162 = ((Class)array[0]).getDeclaredField((String)array[1]);
                                        }
                                        else if (anInt1164 == 18) {
                                            aClass143_697.anObject1162 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                                        }
                                        else if (anInt1164 != 19) {
                                            if (!this.aBoolean682) {
                                                throw new Exception("");
                                            }
                                            if (anInt1164 == 3) {
                                                if (Class88.aLong701 > Class343.method3819(-47)) {
                                                    throw new IOException();
                                                }
                                                aClass143_697.anObject1162 = InetAddress.getByName(String.valueOf(0xFF & aClass143_697.anInt1166 >> 443133112) + "." + (0xFF & aClass143_697.anInt1166 >> -1933975568) + "." + ((aClass143_697.anInt1166 & 0xFFFC) >> -963801848) + "." + (0xFF & aClass143_697.anInt1166)).getHostName();
                                            }
                                            else if (~anInt1164 != 0xFFFFFFEA) {
                                                if (anInt1164 != 5) {
                                                    if (~anInt1164 == 0xFFFFFFF9) {
                                                        final Frame anObject1163 = new Frame("Jagex Full Screen");
                                                        ((Frame)(aClass143_697.anObject1162 = anObject1163)).setResizable(false);
                                                        if (!this.aBoolean675) {
                                                            Class.forName("Class68").getMethod("method692", (Class88.aClass703 != null) ? Class88.aClass703 : (Class88.aClass703 = method878("java.awt.Frame")), Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(this.anObject693, anObject1163, new Integer(aClass143_697.anInt1166 >>> -367960272), new Integer(aClass143_697.anInt1166 & 0xFFFF), new Integer(aClass143_697.anInt1165 >> 753354768), new Integer(aClass143_697.anInt1165 & 0xFFFF));
                                                        }
                                                        else {
                                                            this.aClass20_684.method255(aClass143_697.anInt1165 >> 178448656, Integer.MIN_VALUE, aClass143_697.anInt1166 >>> 1019450064, aClass143_697.anInt1165 & 0xFFFF, 0xFFFF & aClass143_697.anInt1166, anObject1163);
                                                        }
                                                    }
                                                    else if (~anInt1164 == 0xFFFFFFF8) {
                                                        if (!this.aBoolean675) {
                                                            Class.forName("Class68").getMethod("method690", (Class<?>[])new Class[0]).invoke(this.anObject693, new Object[0]);
                                                        }
                                                        else {
                                                            this.aClass20_684.method253(83, (Frame)aClass143_697.anObject1161);
                                                        }
                                                    }
                                                    else if (anInt1164 == 12) {
                                                        aClass143_697.anObject1162 = method871(Class88.aString694, (String)aClass143_697.anObject1161, Class88.anInt691, (byte)(-121));
                                                    }
                                                    else if (~anInt1164 != 0xFFFFFFF2) {
                                                        if (this.aBoolean682 && ~anInt1164 == 0xFFFFFFF1) {
                                                            final int anInt1165 = aClass143_697.anInt1166;
                                                            final int anInt1166 = aClass143_697.anInt1165;
                                                            if (this.aBoolean675) {
                                                                this.aCallback_Sub1_677.method356(1, anInt1166, anInt1165);
                                                            }
                                                            else {
                                                                Class.forName("Class321").getDeclaredMethod("method3668", Integer.TYPE, Integer.TYPE).invoke(this.anObject687, new Integer(anInt1165), new Integer(anInt1166));
                                                            }
                                                        }
                                                        else if (this.aBoolean682 && ~anInt1164 == 0xFFFFFFF0) {
                                                            final boolean b = aClass143_697.anInt1166 != 0;
                                                            final Component component = (Component)aClass143_697.anObject1161;
                                                            if (this.aBoolean675) {
                                                                this.aCallback_Sub1_677.method358(b, component, (byte)(-104));
                                                            }
                                                            else {
                                                                Class.forName("Class321").getDeclaredMethod("method3667", (Class88.aClass704 != null) ? Class88.aClass704 : (Class88.aClass704 = method878("java.awt.Component")), Boolean.TYPE).invoke(this.anObject687, component, new Boolean(b));
                                                            }
                                                        }
                                                        else {
                                                            if (this.aBoolean675 || anInt1164 != 17) {
                                                                if (anInt1164 != 16) {
                                                                    throw new Exception("");
                                                                }
                                                                try {
                                                                    if (!Class88.aString699.startsWith("win")) {
                                                                        throw new Exception();
                                                                    }
                                                                    final String s = (String)aClass143_697.anObject1161;
                                                                    if (!s.startsWith("http://") && !s.startsWith("https://")) {
                                                                        throw new Exception();
                                                                    }
                                                                    final String s2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?&=,.%+-_#:/*";
                                                                    for (int n = 0; ~n > ~s.length(); ++n) {
                                                                        if (s2.indexOf(s.charAt(n)) == -1) {
                                                                            throw new Exception();
                                                                        }
                                                                    }
                                                                    Runtime.getRuntime().exec("cmd /c start \"j\" \"" + s + "\"");
                                                                    aClass143_697.anObject1162 = null;
                                                                    break Label_1757;
                                                                }
                                                                catch (Exception anObject1164) {
                                                                    throw aClass143_697.anObject1162 = anObject1164;
                                                                }
                                                            }
                                                            final Object[] array2 = (Object[])aClass143_697.anObject1161;
                                                            Class.forName("Class321").getDeclaredMethod("method3666", (Class88.aClass704 != null) ? Class88.aClass704 : (Class88.aClass704 = method878("java.awt.Component")), (Class88.aClass705 != null) ? Class88.aClass705 : (Class88.aClass705 = method878("[I")), Integer.TYPE, Integer.TYPE, (Class88.aClass706 != null) ? Class88.aClass706 : (Class88.aClass706 = method878("java.awt.Point"))).invoke(this.anObject687, (Component)array2[0], (int[])array2[1], new Integer(aClass143_697.anInt1166), new Integer(aClass143_697.anInt1165), (Point)array2[2]);
                                                        }
                                                    }
                                                    else {
                                                        aClass143_697.anObject1162 = method871("", (String)aClass143_697.anObject1161, Class88.anInt691, (byte)(-110));
                                                    }
                                                }
                                                else if (this.aBoolean675) {
                                                    aClass143_697.anObject1162 = this.aClass20_684.method252(false);
                                                }
                                                else {
                                                    aClass143_697.anObject1162 = Class.forName("Class68").getMethod("method693", (Class<?>[])new Class[0]).invoke(this.anObject693, new Object[0]);
                                                }
                                            }
                                            else {
                                                if (~Class88.aLong701 < ~Class343.method3819(-47)) {
                                                    throw new IOException();
                                                }
                                                aClass143_697.anObject1162 = InetAddress.getByName((String)aClass143_697.anObject1161).getAddress();
                                            }
                                        }
                                        else {
                                            Toolkit.getDefaultToolkit().getSystemClipboard().setContents((Transferable)aClass143_697.anObject1161, null);
                                        }
                                    }
                                    else {
                                        final Object[] array3 = (Object[])aClass143_697.anObject1161;
                                        if (this.aBoolean682 && ((Class)array3[0]).getClassLoader() == null) {
                                            throw new SecurityException();
                                        }
                                        aClass143_697.anObject1162 = ((Class)array3[0]).getDeclaredMethod((String)array3[1], (Class[])array3[2]);
                                    }
                                }
                                else {
                                    if (Class88.aLong701 > Class343.method3819(-47)) {
                                        throw new IOException();
                                    }
                                    aClass143_697.anObject1162 = new DataInputStream(((URL)aClass143_697.anObject1161).openStream());
                                }
                            }
                            else {
                                if (~Class88.aLong701 < ~Class343.method3819(-47)) {
                                    throw new IOException();
                                }
                                aClass143_697.anObject1162 = new Socket(InetAddress.getByName((String)aClass143_697.anObject1161), aClass143_697.anInt1166);
                            }
                        }
                        aClass143_697.anInt1163 = 1;
                    }
                    catch (ThreadDeath threadDeath) {
                        throw threadDeath;
                    }
                    catch (Throwable t) {
                        aClass143_697.anInt1163 = 2;
                    }
                }
                synchronized (aClass143_697) {
                    aClass143_697.notify();
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method863(final String s, final boolean b, final int n, final boolean b2) {
        try {
            if (b) {
                this.aCallback_Sub1_677 = null;
            }
            return this.method877(0, -93, n, b2 ? 22 : 1, s);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method864(final int n) {
        try {
            if (n < 36) {
                this.method861(-64);
            }
            return this.method877(0, -114, 0, 5, null);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final Class356 method865(final int n, final String s) {
        try {
            if (n > -64) {
                return null;
            }
            return method871(Class88.aString694, s, Class88.anInt691, (byte)(-106));
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method866(final int n, final URL url) {
        try {
            if (n >= -96) {
                this.method875(null, false, -55);
            }
            return this.method877(0, -102, 0, 4, url);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Object method867(final boolean b) {
        try {
            if (b) {
                Class88.aString688 = null;
            }
            return this.anObject683;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method868(final int n, final int n2) {
        try {
            if (n2 < 91) {
                this.aBoolean682 = true;
            }
            return this.method877(0, -77, n, 3, null);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method869(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n != -21605) {
                return null;
            }
            return this.method877((n2 << 1082083888) - -n4, n + 21511, n5 + (n3 << 2029886704), 6, null);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method870(final String s, final Class clazz, final int n, final Class[] array) {
        try {
            if (n != 0) {
                this.method876(null, false, null);
            }
            return this.method877(0, -66, 0, 8, new Object[] { clazz, s, array });
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private static final Class356 method871(final String s, final String s2, final int n, final byte b) {
        try {
            String s3;
            if (n != 33) {
                if (n != 34) {
                    s3 = "dementhium_" + s + "_preferences" + s2 + ".dat";
                }
                else {
                    s3 = "dementhium_" + s + "_preferences" + s2 + "_wip.dat";
                }
            }
            else {
                s3 = "dementhium_" + s + "_preferences" + s2 + "_rc.dat";
            }
            final String[] array = { "c:/rscache/", "/rscache/", Class88.aString679, "c:/windows/", "c:/winnt/", "c:/", "/tmp/", "" };
            for (int n2 = 0; ~n2 > ~array.length; ++n2) {
                final String s4 = array[n2];
                if (s4.length() > 0) {
                    if (!new File(s4).exists()) {
                        continue;
                    }
                }
                try {
                    return new Class356(new File(s4, s3), "rw", 10000L);
                }
                catch (Exception ex2) {}
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method872(final int[] array, final Point point, final int n, final byte b, final int n2, final Component component) {
        try {
            if (b < 56) {
                this.method861(16);
            }
            return this.method877(n, -101, n2, 17, new Object[] { component, array, point });
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method873(final String s, final Class clazz, final int n) {
        try {
            if (n != -27303) {
                return null;
            }
            return this.method877(0, -72, 0, 9, new Object[] { clazz, s });
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method874(final int n) {
        try {
            synchronized (this) {
                this.aBoolean681 = true;
                this.notifyAll();
            }
            try {
                this.aThread686.join();
            }
            catch (InterruptedException ex2) {}
            if (this.aClass356_702 != null) {
                try {
                    this.aClass356_702.method3880(true);
                }
                catch (IOException ex3) {}
            }
            if (n >= 54) {
                if (this.aClass356_680 != null) {
                    try {
                        this.aClass356_680.method3880(true);
                    }
                    catch (IOException ex4) {}
                }
                if (this.aClass356Array695 != null) {
                    for (int n2 = 0; ~n2 > ~this.aClass356Array695.length; ++n2) {
                        if (this.aClass356Array695[n2] != null) {
                            try {
                                this.aClass356Array695[n2].method3880(true);
                            }
                            catch (IOException ex5) {}
                        }
                    }
                }
                if (this.aClass356_685 != null) {
                    try {
                        this.aClass356_685.method3880(true);
                    }
                    catch (IOException ex6) {}
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final Class143 method875(final String s, final boolean b, final int n) {
        try {
            if (n != 21516) {
                return null;
            }
            if (!b) {
                return this.method877(0, n - 21585, 0, 13, s);
            }
            return this.method877(0, -119, 0, 12, s);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final boolean method876(final byte[] array, final boolean b, final File file) {
        try {
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(array, 0, array.length);
                fileOutputStream.close();
                return b;
            }
            catch (IOException ex2) {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final Class143 method877(final int anInt1165, final int n, final int anInt1166, final int anInt1167, final Object anObject1161) {
        try {
            if (n > -61) {
                this.aThread686 = null;
            }
            final Class143 class143 = new Class143();
            class143.anObject1161 = anObject1161;
            class143.anInt1164 = anInt1167;
            class143.anInt1166 = anInt1166;
            class143.anInt1165 = anInt1165;
            synchronized (this) {
                if (this.aClass143_689 != null) {
                    this.aClass143_689.aClass143_1167 = class143;
                    this.aClass143_689 = class143;
                }
                else {
                    final Class143 class144 = class143;
                    this.aClass143_697 = class144;
                    this.aClass143_689 = class144;
                }
                this.notify();
            }
            return class143;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class88(final int anInt691, final String aString694, final int n, final boolean aBoolean682) throws Exception {
        this.aClass143_689 = null;
        this.aClass356_680 = null;
        this.aClass356_685 = null;
        this.aBoolean681 = false;
        this.aClass143_697 = null;
        this.aBoolean682 = false;
        this.aBoolean675 = false;
        this.aClass356_702 = null;
        try {
            Class88.aString692 = "1.1";
            Class88.aString694 = aString694;
            this.aBoolean682 = aBoolean682;
            Class88.aString696 = "Unknown";
            Class88.anInt691 = anInt691;
            try {
                Class88.aString696 = System.getProperty("java.vendor");
                Class88.aString692 = System.getProperty("java.version");
            }
            catch (Exception ex2) {}
            if (~Class88.aString696.toLowerCase().indexOf("microsoft") != 0x0) {
                this.aBoolean675 = true;
            }
            try {
                Class88.aString688 = System.getProperty("os.name");
            }
            catch (Exception ex3) {
                Class88.aString688 = "Unknown";
            }
            Class88.aString699 = Class88.aString688.toLowerCase();
            try {
                Class88.aString690 = System.getProperty("os.arch").toLowerCase();
            }
            catch (Exception ex4) {
                Class88.aString690 = "";
            }
            try {
                Class88.aString676 = System.getProperty("os.version").toLowerCase();
            }
            catch (Exception ex5) {
                Class88.aString676 = "";
            }
            try {
                Class88.aString679 = System.getProperty("user.home");
                if (Class88.aString679 != null) {
                    Class88.aString679 += "/";
                }
            }
            catch (Exception ex6) {}
            if (Class88.aString679 == null) {
                Class88.aString679 = "~/";
            }
            try {
                this.anEventQueue698 = Toolkit.getDefaultToolkit().getSystemEventQueue();
            }
            catch (Throwable t) {}
            if (!this.aBoolean675) {
                try {
                    Class88.aMethod700 = Class.forName("java.awt.Component").getDeclaredMethod("setFocusTraversalKeysEnabled", Boolean.TYPE);
                }
                catch (Exception ex7) {}
                try {
                    Class88.aMethod678 = Class.forName("java.awt.Container").getDeclaredMethod("setFocusCycleRoot", Boolean.TYPE);
                }
                catch (Exception ex8) {}
            }
            Class316.method3650(false, Class88.anInt691, Class88.aString694);
            if (this.aBoolean682) {
                this.aClass356_685 = new Class356(Class316.method3648(Class88.anInt691, 0, "random.dat", null), "rw", 25L);
                this.aClass356_702 = new Class356(Class316.method3649("main_file_cache.dat2", 13), "rw", 209715200L);
                this.aClass356_680 = new Class356(Class316.method3649("main_file_cache.idx255", 61), "rw", 1048576L);
                this.aClass356Array695 = new Class356[n];
                for (int n2 = 0; ~n < ~n2; ++n2) {
                    this.aClass356Array695[n2] = new Class356(Class316.method3649("main_file_cache.idx" + n2, -128), "rw", 1048576L);
                }
                if (this.aBoolean675) {
                    try {
                        this.anObject683 = Class.forName("Class158").newInstance();
                    }
                    catch (Throwable t2) {}
                }
                try {
                    if (!this.aBoolean675) {
                        this.anObject693 = Class.forName("Class68").newInstance();
                    }
                    else {
                        this.aClass20_684 = new Class20();
                    }
                }
                catch (Throwable t3) {}
                try {
                    if (!this.aBoolean675) {
                        this.anObject687 = Class.forName("Class321").newInstance();
                    }
                    else {
                        this.aCallback_Sub1_677 = new Callback_Sub1();
                    }
                }
                catch (Throwable t4) {}
            }
            if (this.aBoolean682 && !this.aBoolean675) {
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                for (ThreadGroup threadGroup2 = threadGroup.getParent(); threadGroup2 != null; threadGroup2 = threadGroup.getParent()) {
                    threadGroup = threadGroup2;
                }
                final Thread[] array = new Thread[1000];
                threadGroup.enumerate(array);
                for (int n3 = 0; array.length > n3; ++n3) {
                    if (array[n3] != null && array[n3].getName().startsWith("AWT")) {
                        array[n3].setPriority(1);
                    }
                }
            }
            this.aBoolean681 = false;
            (this.aThread686 = new Thread(this)).setPriority(10);
            this.aThread686.setDaemon(true);
            this.aThread686.start();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static Class method878(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class88.aLong701 = 0L;
    }
}
