// 
// Decompiled by Procyon v0.5.30
// 

final class Class365
{
    private Class207 aClass207_3107;
    private Class79 aClass79_3108;
    static Class113 aClass113_3109;
    static boolean aBoolean3110;
    
    static final int method3937(final int n, final byte[] array, final int n2, final boolean b) {
        try {
            if (b) {
                Class365.aBoolean3110 = true;
            }
            int n3 = -1;
            for (int i = n2; i < n; ++i) {
                n3 = (Class287_Sub1.anIntArray3419[0xFF & (array[i] ^ n3)] ^ n3 >>> -26165528);
            }
            return ~n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.C(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + b + ')');
        }
    }
    
    final void method3938(final int n) {
        try {
            synchronized (this.aClass79_3108) {
                this.aClass79_3108.method794(55);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.E(" + n + ')');
        }
    }
    
    static final Class293 method3939(final int n, final Class293 class293) {
        try {
            Class293 class294 = client.method102(class293);
            if (class294 == null) {
                class294 = class293.aClass293_2219;
            }
            if (n != 4456) {
                method3939(54, null);
            }
            return class294;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.H(" + n + ',' + ((class293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class149 method3940(final byte b, final int n) {
        try {
            if (b != 31) {
                return null;
            }
            final Class149 class149;
            synchronized (this.aClass79_3108) {
                class149 = (Class149)this.aClass79_3108.method802(-128, n);
            }
            if (class149 != null) {
                return class149;
            }
            final byte[] method2745;
            synchronized (this.aClass207_3107) {
                method2745 = this.aClass207_3107.method2745(n, 11, false);
            }
            final Class149 class150 = new Class149();
            if (method2745 != null) {
                class150.method2431(new Class98_Sub22(method2745), -1);
            }
            synchronized (this.aClass79_3108) {
                this.aClass79_3108.method805(n, class150, (byte)(-80));
            }
            return class150;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.F(" + b + ',' + n + ')');
        }
    }
    
    static final void method3941(final Class207 aClass207_1681, final Class88 aClass88_1848, final int n) {
        try {
            Class242.aClass88_1848 = aClass88_1848;
            Class343.aString2863 = "";
            Class223.aClass207_1681 = aClass207_1681;
            if (Class19.aString3448.startsWith("win")) {
                Class343.aString2863 += "windows/";
            }
            else if (!Class19.aString3448.startsWith("linux")) {
                if (Class19.aString3448.startsWith("mac")) {
                    Class343.aString2863 += "macos/";
                }
            }
            else {
                Class343.aString2863 += "linux/";
            }
            if (n != -1) {
                method3937(63, null, -15, false);
            }
            if (Class242.aClass88_1848.aBoolean675) {
                Class343.aString2863 += "msjava/";
            }
            else if (!Class19.aString3442.startsWith("amd64") && !Class19.aString3442.startsWith("x86_64")) {
                if (!Class19.aString3442.startsWith("i386") && !Class19.aString3442.startsWith("i486") && !Class19.aString3442.startsWith("i586") && !Class19.aString3442.startsWith("x86")) {
                    if (Class19.aString3442.startsWith("ppc")) {
                        Class343.aString2863 += "ppc/";
                    }
                    else {
                        Class343.aString2863 += "universal/";
                    }
                }
                else {
                    Class343.aString2863 += "x86/";
                }
            }
            else {
                Class343.aString2863 += "x86_64/";
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.G(" + ((aClass207_1681 != null) ? "{...}" : "null") + ',' + ((aClass88_1848 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method3942(final int n) {
        try {
            Class365.aClass113_3109 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.B(" + n + ')');
        }
    }
    
    final void method3943(final int n, final boolean b) {
        try {
            if (b) {
                Class365.aBoolean3110 = false;
            }
            synchronized (this.aClass79_3108) {
                this.aClass79_3108.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.D(" + n + ',' + b + ')');
        }
    }
    
    final void method3944(final int n) {
        try {
            synchronized (this.aClass79_3108) {
                if (n == -1) {
                    this.aClass79_3108.method806((byte)48);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.A(" + n + ')');
        }
    }
    
    Class365(final Class279 class279, final int n, final Class207 aClass207_3107) {
        this.aClass79_3108 = new Class79(64);
        try {
            this.aClass207_3107 = aClass207_3107;
            if (this.aClass207_3107 != null) {
                this.aClass207_3107.method2761(0, 11);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wba.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_3107 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class365.aBoolean3110 = true;
        Class365.aClass113_3109 = new Class113(4, 1);
    }
}
