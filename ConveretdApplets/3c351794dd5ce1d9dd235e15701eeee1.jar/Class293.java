import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class293
{
    byte aByte2207;
    int anInt2208;
    boolean aBoolean2209;
    int anInt2210;
    int anInt2211;
    Object[] anObjectArray2212;
    int anInt2213;
    String aString2214;
    Object[] anObjectArray2215;
    int anInt2216;
    int[] anIntArray2217;
    int anInt2218;
    Class293 aClass293_2219;
    Object[] anObjectArray2220;
    int[] anIntArray2221;
    boolean aBoolean2222;
    int anInt2223;
    String aString2224;
    String aString2225;
    int anInt2226;
    Object[] anObjectArray2227;
    int anInt2228;
    int anInt2229;
    Object[] anObjectArray2230;
    String aString2231;
    int anInt2232;
    int anInt2233;
    int anInt2234;
    int anInt2235;
    int anInt2236;
    int anInt2237;
    int anInt2238;
    Object[] anObjectArray2239;
    byte aByte2240;
    boolean aBoolean2241;
    int anInt2242;
    byte aByte2243;
    int anInt2244;
    byte aByte2245;
    int anInt2246;
    int anInt2247;
    int anInt2248;
    int[] anIntArray2249;
    int anInt2250;
    int anInt2251;
    Object[] anObjectArray2252;
    Object[] anObjectArray2253;
    int anInt2254;
    int anInt2255;
    boolean aBoolean2256;
    Object[] anObjectArray2257;
    int anInt2258;
    int anInt2259;
    int anInt2260;
    int anInt2261;
    boolean aBoolean2262;
    boolean aBoolean2263;
    int anInt2264;
    boolean aBoolean2265;
    Object[] anObjectArray2266;
    int anInt2267;
    int anInt2268;
    Object[] anObjectArray2269;
    Object[] anObjectArray2270;
    int[] anIntArray2271;
    Object[] anObjectArray2272;
    int anInt2273;
    Object[] anObjectArray2274;
    int[] anIntArray2275;
    int anInt2276;
    Object[] anObjectArray2277;
    Object[] anObjectArray2278;
    boolean aBoolean2279;
    boolean aBoolean2280;
    boolean aBoolean2281;
    int anInt2282;
    int anInt2283;
    int[] anIntArray2284;
    int anInt2285;
    boolean aBoolean2286;
    int anInt2287;
    boolean aBoolean2288;
    int anInt2289;
    int anInt2290;
    Object[] anObjectArray2291;
    Object[] anObjectArray2292;
    int anInt2293;
    Object[] anObjectArray2294;
    boolean aBoolean2295;
    int anInt2296;
    int[] anIntArray2297;
    int[] anIntArray2298;
    int anInt2299;
    int anInt2300;
    Class246_Sub5 aClass246_Sub5_2301;
    int anInt2302;
    int anInt2303;
    int anInt2304;
    int anInt2305;
    int anInt2306;
    int anInt2307;
    int anInt2308;
    int anInt2309;
    int anInt2310;
    int anInt2311;
    int anInt2312;
    Object[] anObjectArray2313;
    Object[] anObjectArray2314;
    boolean aBoolean2315;
    Object[] anObjectArray2316;
    int anInt2317;
    int anInt2318;
    Object[] anObjectArray2319;
    Object[] anObjectArray2320;
    int anInt2321;
    boolean aBoolean2322;
    int anInt2323;
    Object[] anObjectArray2324;
    boolean aBoolean2325;
    int[] anIntArray2326;
    boolean aBoolean2327;
    int anInt2328;
    Object[] anObjectArray2329;
    Object[] anObjectArray2330;
    byte[] aByteArray2331;
    Object[] anObjectArray2332;
    String aString2333;
    int anInt2334;
    Object[] anObjectArray2335;
    int anInt2336;
    private Class377 aClass377_2337;
    int anInt2338;
    Class293[] aClass293Array2339;
    Object[] anObjectArray2340;
    int anInt2341;
    int[] anIntArray2342;
    int anInt2343;
    int anInt2344;
    byte[] aByteArray2345;
    int anInt2346;
    int anInt2347;
    Class98_Sub49 aClass98_Sub49_2348;
    int anInt2349;
    int anInt2350;
    String[] aStringArray2351;
    int anInt2352;
    int anInt2353;
    int anInt2354;
    int anInt2355;
    Object[] anObjectArray2356;
    
    final void method3455(final int n, final int anInt4126, final int n2) {
        try {
            if (this.aClass377_2337 == null) {
                (this.aClass377_2337 = new Class377(16)).method3996(new Class98_Sub34(anInt4126), n, -1);
            }
            else if (n2 == 16) {
                final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_2337.method3990(n, -1);
                if (class98_Sub34 != null) {
                    class98_Sub34.anInt4126 = anInt4126;
                }
                else {
                    this.aClass377_2337.method3996(new Class98_Sub34(anInt4126), n, n2 ^ 0xFFFFFFEF);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.B(" + n + ',' + anInt4126 + ',' + n2 + ')');
        }
    }
    
    final Class332 method3456(final ha ha, final int n) {
        try {
            Class93.aBoolean3503 = false;
            final long n2 = (this.anInt2355 << 479438120) + (((this.aBoolean2281 ? 1 : 0) << -1234042329) + ((this.aBoolean2279 ? 1 : 0) << 273433763)) + (this.anInt2237 - (-(this.anInt2304 << 2008896804) - ((this.aBoolean2327 ? 1 : 0) << -445924378)));
            if (n != -1234042329) {
                this.method3457(null, -103);
            }
            final Class332 class332 = (Class332)Class69_Sub2.aClass79_5334.method802(n + 1234042209, n2);
            if (class332 != null) {
                return class332;
            }
            final Class324 method3685 = Class324.method3685(Class166.aClass207_1280, this.anInt2237, 0);
            if (method3685 == null) {
                Class93.aBoolean3503 = true;
                return null;
            }
            if (this.aBoolean2327) {
                method3685.method3682();
            }
            if (this.aBoolean2281) {
                method3685.method3691();
            }
            Label_0206: {
                if (~this.anInt2304 >= -1) {
                    if (this.anInt2355 == 0) {
                        break Label_0206;
                    }
                    method3685.method3688(1);
                    if (!client.aBoolean3553) {
                        break Label_0206;
                    }
                }
                method3685.method3688(this.anInt2304);
            }
            if (~this.anInt2304 <= -2) {
                method3685.method3694(1);
            }
            if (this.anInt2304 >= 2) {
                method3685.method3694(16777215);
            }
            if (this.anInt2355 != 0) {
                method3685.method3693(this.anInt2355 | 0xFF000000);
            }
            final Class332 method3686 = ha.method1758(method3685, true);
            Class69_Sub2.aClass79_5334.method807(13436, n2, 4 * (method3686.method3734() * method3686.method3731()), method3686);
            return method3686;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.N(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method3457(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            int unsignedByte = class98_Sub22.readUnsignedByte((byte)57);
            if (unsignedByte == 255) {
                unsignedByte = -1;
            }
            this.anInt2354 = class98_Sub22.readUnsignedByte((byte)90);
            if (~(0x80 & this.anInt2354) != -1) {
                this.anInt2354 &= 0x7F;
                this.aString2231 = class98_Sub22.readString((byte)84);
            }
            this.anInt2307 = class98_Sub22.readShort((byte)127);
            this.anInt2283 = class98_Sub22.readUShort(false);
            this.anInt2229 = class98_Sub22.readUShort(false);
            this.anInt2235 = class98_Sub22.readShort((byte)127);
            this.anInt2242 = class98_Sub22.readShort((byte)127);
            this.aByte2243 = class98_Sub22.readSignedByte((byte)(-19));
            this.aByte2207 = class98_Sub22.readSignedByte((byte)(-19));
            this.aByte2240 = class98_Sub22.readSignedByte((byte)(-19));
            this.aByte2245 = class98_Sub22.readSignedByte((byte)(-19));
            this.anInt2234 = class98_Sub22.readShort((byte)127);
            if (this.anInt2234 != 65535) {
                this.anInt2234 += (this.anInt2248 & 0xFFFF0000);
            }
            else {
                this.anInt2234 = -1;
            }
            final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)11);
            if (unsignedByte >= 0) {
                this.aBoolean2286 = (~(0x2 & unsignedByte2) != -1);
            }
            this.aBoolean2295 = (~(unsignedByte2 & 0x1) != -1);
            if (~this.anInt2354 == -1) {
                this.anInt2290 = class98_Sub22.readShort((byte)127);
                this.anInt2228 = class98_Sub22.readShort((byte)127);
                if (unsignedByte < 0) {
                    this.aBoolean2286 = (~class98_Sub22.readUnsignedByte((byte)113) == 0xFFFFFFFE);
                }
            }
            if (this.anInt2354 == 5) {
                this.anInt2237 = class98_Sub22.readInt(-2);
                this.anInt2255 = class98_Sub22.readShort((byte)127);
                final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)(-102));
                this.aBoolean2288 = (~(0x1 & unsignedByte3) != -1);
                this.aBoolean2279 = ((unsignedByte3 & 0x2) != 0x0);
                this.anInt2285 = class98_Sub22.readUnsignedByte((byte)2);
                this.anInt2304 = class98_Sub22.readUnsignedByte((byte)(-98));
                this.anInt2355 = class98_Sub22.readInt(-2);
                this.aBoolean2327 = (class98_Sub22.readUnsignedByte((byte)115) == 1);
                this.aBoolean2281 = (class98_Sub22.readUnsignedByte((byte)(-120)) == 1);
                this.anInt2236 = class98_Sub22.readInt(-2);
            }
            if (this.anInt2354 == 6) {
                this.anInt2233 = 1;
                this.anInt2343 = class98_Sub22.readShort((byte)127);
                if (this.anInt2343 == 65535) {
                    this.anInt2343 = -1;
                }
                final int unsignedByte4 = class98_Sub22.readUnsignedByte((byte)(-115));
                this.aBoolean2280 = (~(0x2 & unsignedByte4) == 0xFFFFFFFD);
                this.aBoolean2265 = (~(unsignedByte4 & 0x4) == 0xFFFFFFFB);
                this.aBoolean2325 = ((0x8 & unsignedByte4) == 0x8);
                if ((0x1 & unsignedByte4) != 0x0) {
                    this.anInt2336 = class98_Sub22.readUShort(false);
                    this.anInt2344 = class98_Sub22.readUShort(false);
                    this.anInt2310 = class98_Sub22.readShort((byte)127);
                    this.anInt2218 = class98_Sub22.readShort((byte)127);
                    this.anInt2346 = class98_Sub22.readShort((byte)127);
                    this.anInt2251 = class98_Sub22.readShort((byte)127);
                }
                else if (this.aBoolean2280) {
                    this.anInt2336 = class98_Sub22.readUShort(false);
                    this.anInt2344 = class98_Sub22.readUShort(false);
                    this.anInt2352 = class98_Sub22.readUShort(false);
                    this.anInt2310 = class98_Sub22.readShort((byte)127);
                    this.anInt2218 = class98_Sub22.readShort((byte)127);
                    this.anInt2346 = class98_Sub22.readShort((byte)127);
                    this.anInt2251 = class98_Sub22.readUShort(false);
                }
                this.anInt2208 = class98_Sub22.readShort((byte)127);
                if (~this.anInt2208 == 0xFFFF0000) {
                    this.anInt2208 = -1;
                }
                if (~this.aByte2243 != -1) {
                    this.anInt2232 = class98_Sub22.readShort((byte)127);
                }
                if (~this.aByte2207 != -1) {
                    this.anInt2226 = class98_Sub22.readShort((byte)127);
                }
            }
            if (~this.anInt2354 == 0xFFFFFFFB) {
                this.anInt2264 = class98_Sub22.readShort((byte)127);
                if (this.anInt2264 == 65535) {
                    this.anInt2264 = -1;
                }
                this.aString2225 = class98_Sub22.readString((byte)84);
                this.anInt2244 = class98_Sub22.readUnsignedByte((byte)38);
                this.anInt2341 = class98_Sub22.readUnsignedByte((byte)55);
                this.anInt2296 = class98_Sub22.readUnsignedByte((byte)(-127));
                this.aBoolean2315 = (class98_Sub22.readUnsignedByte((byte)(-103)) == 1);
                this.anInt2236 = class98_Sub22.readInt(n ^ 0x3B3);
                this.anInt2285 = class98_Sub22.readUnsignedByte((byte)26);
                if (~unsignedByte <= -1) {
                    this.anInt2350 = class98_Sub22.readUnsignedByte((byte)36);
                }
            }
            if (~this.anInt2354 == 0xFFFFFFFC) {
                this.anInt2236 = class98_Sub22.readInt(-2);
                this.aBoolean2263 = (class98_Sub22.readUnsignedByte((byte)48) == 1);
                this.anInt2285 = class98_Sub22.readUnsignedByte((byte)19);
            }
            if (~this.anInt2354 == 0xFFFFFFF6) {
                this.anInt2293 = class98_Sub22.readUnsignedByte((byte)21);
                this.anInt2236 = class98_Sub22.readInt(-2);
                this.aBoolean2256 = (~class98_Sub22.readUnsignedByte((byte)(-111)) == 0xFFFFFFFE);
            }
            final int method1186 = class98_Sub22.method1186(-125);
            int n2 = class98_Sub22.readUnsignedByte((byte)(-109));
            if (n2 != 0) {
                this.aByteArray2345 = new byte[11];
                this.anIntArray2275 = new int[11];
                this.aByteArray2331 = new byte[11];
                while (~n2 != -1) {
                    final int n3 = -1 + (n2 >> -1687058556);
                    int n4 = (class98_Sub22.readUnsignedByte((byte)23) | n2 << -1011278808) & 0xFFF;
                    if (n4 == 4095) {
                        n4 = -1;
                    }
                    final byte signedByte = class98_Sub22.readSignedByte((byte)(-19));
                    if (signedByte != 0) {
                        this.aBoolean2222 = true;
                    }
                    final byte signedByte2 = class98_Sub22.readSignedByte((byte)(-19));
                    this.anIntArray2275[n3] = n4;
                    this.aByteArray2345[n3] = signedByte;
                    this.aByteArray2331[n3] = signedByte2;
                    n2 = class98_Sub22.readUnsignedByte((byte)(-122));
                }
            }
            this.aString2224 = class98_Sub22.readString((byte)84);
            final int unsignedByte5 = class98_Sub22.readUnsignedByte((byte)(-100));
            final int i = 0xF & unsignedByte5;
            final int n5 = unsignedByte5 >> -1266982652;
            if (i > 0) {
                this.aStringArray2351 = new String[i];
                for (int n6 = 0; i > n6; ++n6) {
                    this.aStringArray2351[n6] = class98_Sub22.readString((byte)84);
                }
            }
            if (~n5 < -1) {
                final int unsignedByte6 = class98_Sub22.readUnsignedByte((byte)50);
                this.anIntArray2326 = new int[1 + unsignedByte6];
                for (int n7 = 0; ~this.anIntArray2326.length < ~n7; ++n7) {
                    this.anIntArray2326[n7] = -1;
                }
                this.anIntArray2326[unsignedByte6] = class98_Sub22.readShort((byte)127);
            }
            if (n5 > 1) {
                this.anIntArray2326[class98_Sub22.readUnsignedByte((byte)(-115))] = class98_Sub22.readShort((byte)127);
            }
            this.aString2333 = class98_Sub22.readString((byte)84);
            if (this.aString2333.equals("")) {
                this.aString2333 = null;
            }
            this.anInt2308 = class98_Sub22.readUnsignedByte((byte)(-109));
            this.anInt2353 = class98_Sub22.readUnsignedByte((byte)23);
            this.anInt2289 = class98_Sub22.readUnsignedByte((byte)33);
            this.aString2214 = class98_Sub22.readString((byte)84);
            int short1 = -1;
            if (~aa_Sub3.method157(method1186, (byte)64) != -1) {
                short1 = class98_Sub22.readShort((byte)127);
                if (short1 == 65535) {
                    short1 = -1;
                }
                this.anInt2309 = class98_Sub22.readShort((byte)127);
                if (~this.anInt2309 == 0xFFFF0000) {
                    this.anInt2309 = -1;
                }
                this.anInt2318 = class98_Sub22.readShort((byte)127);
                if (this.anInt2318 == 65535) {
                    this.anInt2318 = -1;
                }
            }
            if (~unsignedByte <= -1) {
                this.anInt2317 = class98_Sub22.readShort((byte)127);
                if (~this.anInt2317 == 0xFFFF0000) {
                    this.anInt2317 = -1;
                }
            }
            this.aClass98_Sub49_2348 = new Class98_Sub49(method1186, short1);
            if (~unsignedByte <= -1) {
                for (int j = class98_Sub22.readUnsignedByte((byte)(-114)), n8 = 0; j > n8; ++n8) {
                    this.aClass377_2337.method3996(new Class98_Sub34(class98_Sub22.readInt(-2)), class98_Sub22.method1186(-124), -1);
                }
                for (int unsignedByte7 = class98_Sub22.readUnsignedByte((byte)68), n9 = 0; ~n9 > ~unsignedByte7; ++n9) {
                    this.aClass377_2337.method3996(new Class98_Sub15(class98_Sub22.method1223(-1)), class98_Sub22.method1186(-123), -1);
                }
            }
            this.anObjectArray2332 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2227 = this.method3462(class98_Sub22, n ^ 0xFFFFFC4D);
            this.anObjectArray2272 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2324 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2257 = this.method3462(class98_Sub22, n + 947);
            this.anObjectArray2269 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2252 = this.method3462(class98_Sub22, n ^ n);
            this.anObjectArray2278 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2270 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2329 = this.method3462(class98_Sub22, n + 947);
            if (~unsignedByte <= -1) {
                this.anObjectArray2253 = this.method3462(class98_Sub22, 0);
            }
            this.anObjectArray2314 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2291 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2335 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2356 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2230 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2316 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2313 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2277 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2212 = this.method3462(class98_Sub22, 0);
            this.anObjectArray2320 = this.method3462(class98_Sub22, 0);
            this.anIntArray2284 = this.method3473(class98_Sub22, 0);
            this.anIntArray2249 = this.method3473(class98_Sub22, 0);
            this.anIntArray2271 = this.method3473(class98_Sub22, 0);
            this.anIntArray2297 = this.method3473(class98_Sub22, n + 947);
            this.anIntArray2342 = this.method3473(class98_Sub22, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.T(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method3458(final String aString3917, final int n, final int n2) {
        try {
            if (n != 16) {
                this.method3464(true, null, 18, null, null);
            }
            if (this.aClass377_2337 == null) {
                (this.aClass377_2337 = new Class377(16)).method3996(new Class98_Sub15(aString3917), n2, n ^ 0xFFFFFFEF);
            }
            else {
                final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_2337.method3990(n2, n ^ 0xFFFFFFEF);
                if (class98_Sub15 == null) {
                    this.aClass377_2337.method3996(new Class98_Sub15(aString3917), n2, n ^ 0xFFFFFFEF);
                }
                else {
                    class98_Sub15.aString3917 = aString3917;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.H(" + ((aString3917 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method3459(final byte b, final int n) {
        try {
            if (this.aClass377_2337 != null) {
                final Class98 method3990 = this.aClass377_2337.method3990(n, -1);
                if (method3990 != null) {
                    method3990.method942(120);
                }
            }
            if (b < 36) {
                this.method3458(null, 44, -60);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.L(" + b + ',' + n + ')');
        }
    }
    
    final Class43 method3460(final int n, final ha ha) {
        try {
            final Class43 method945 = Class98_Sub1.method945(this.anInt2264, ha, (byte)99, false);
            Class93.aBoolean3503 = (method945 == null);
            if (n > -37) {
                this.anInt2347 = -28;
            }
            return method945;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.D(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class146 method3461(final Interface6 interface6, final Class183 class183, final Class301 class184, final Class83 class185, final ha ha, final Class257 class186, final Class40 class187, final byte b, final int n, int method1777, final int n2, final int n3, final Class97 class188, final Class313 class189, final Class205 class190) {
        try {
            Class93.aBoolean3503 = false;
            if (~this.anInt2233 == -1) {
                return null;
            }
            if (~this.anInt2233 == 0xFFFFFFFE && this.anInt2343 == -1) {
                return null;
            }
            if (~this.anInt2233 == 0xFFFFFFFE) {
                final int n4 = method1777;
                if (class188 != null) {
                    method1777 |= class188.method932(true, n2, true, n);
                }
                final long n5 = (this.anInt2233 << 994290256) + (ha.anInt937 << -395944227) - -this.anInt2343;
                Class146 class191 = (Class146)Class64_Sub5.aClass79_3650.method802(-125, n5);
                if (class191 == null || ~ha.c(class191.ua(), method1777) != -1) {
                    if (class191 != null) {
                        method1777 = ha.method1777(method1777, class191.ua());
                    }
                    final Class178 method1778 = Class98_Sub6.method981(0, -9252, Class340.aClass207_2847, this.anInt2343);
                    if (method1778 == null) {
                        Class93.aBoolean3503 = true;
                        return null;
                    }
                    if (method1778.anInt1387 < 13) {
                        method1778.method2592(13746, 2);
                    }
                    class191 = ha.method1790(method1778, method1777, Class76_Sub10.anInt3794, 64, 768);
                    Class64_Sub5.aClass79_3650.method805(n5, class191, (byte)(-80));
                }
                if (class188 != null) {
                    class191 = class188.method937(n, n3, method1777, 121, class191, n2);
                }
                class191.s(n4);
                return class191;
            }
            if (~this.anInt2233 == 0xFFFFFFFD) {
                final Class146 method1779 = class184.method3538(5, this.anInt2343).method2299(class188, false, interface6, n3, n2, class183, method1777, ha, class187, n);
                if (method1779 == null) {
                    Class93.aBoolean3503 = true;
                    return null;
                }
                return method1779;
            }
            else if (this.anInt2233 == 3) {
                if (class189 == null) {
                    return null;
                }
                final Class146 method1780 = class189.method3624((byte)107, n3, class185, class190, ha, n2, interface6, class188, class184, n, class183, method1777);
                if (method1780 == null) {
                    Class93.aBoolean3503 = true;
                    return null;
                }
                return method1780;
            }
            else if (this.anInt2233 == 4) {
                final Class146 method1781 = class190.method2714(this.anInt2343, (byte)(-117)).method3501(n3, method1777, n2, class188, n, ha, 10, 128, class189);
                if (method1781 == null) {
                    Class93.aBoolean3503 = true;
                    return null;
                }
                return method1781;
            }
            else if (this.anInt2233 == 6) {
                final Class146 method1782 = class184.method3538(5, this.anInt2343).method2301(n2, 0, null, 0, (byte)120, class188, null, ha, 0, n, interface6, class187, n3, class186, method1777, 0, class183, null);
                if (method1782 == null) {
                    Class93.aBoolean3503 = true;
                    return null;
                }
                return method1782;
            }
            else {
                if (this.anInt2233 != 7) {
                    return null;
                }
                if (class189 == null) {
                    return null;
                }
                final Class146 method1783 = class189.method3626(n2, class188, n3, this.anInt2210, this.anInt2343 >>> -1684107024, this.anInt2343 & 0xFFFF, n, method1777, class185, ha, 256, class183);
                if (method1783 == null) {
                    Class93.aBoolean3503 = true;
                    return null;
                }
                return method1783;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.P(" + ((interface6 != null) ? "{...}" : "null") + ',' + ((class183 != null) ? "{...}" : "null") + ',' + ((class184 != null) ? "{...}" : "null") + ',' + ((class185 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class186 != null) ? "{...}" : "null") + ',' + ((class187 != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + method1777 + ',' + n2 + ',' + n3 + ',' + ((class188 != null) ? "{...}" : "null") + ',' + ((class189 != null) ? "{...}" : "null") + ',' + ((class190 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Object[] method3462(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-123));
            if (~unsignedByte == -1) {
                return null;
            }
            final Object[] array = new Object[unsignedByte];
            for (int n2 = n; ~n2 > ~unsignedByte; ++n2) {
                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-125));
                if (unsignedByte2 != 0) {
                    if (unsignedByte2 == 1) {
                        array[n2] = class98_Sub22.readString((byte)84);
                    }
                }
                else {
                    array[n2] = new Integer(class98_Sub22.readInt(-2));
                }
            }
            this.aBoolean2209 = true;
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.M(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final String method3463(final int n, final int n2, final String s) {
        try {
            if (n2 != 700) {
                this.aBoolean2263 = false;
            }
            if (this.aClass377_2337 == null) {
                return s;
            }
            final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_2337.method3990(n, -1);
            if (class98_Sub15 == null) {
                return s;
            }
            return class98_Sub15.aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.R(" + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3464(final boolean b, final ha ha, final int n, final Class146 class146, final Class111 class147) {
        try {
            class146.method2343(class147);
            final Class87[] method2320 = class146.method2320();
            if (b) {
                final Class35[] method2321 = class146.method2322();
                if ((this.aClass246_Sub5_2301 == null || this.aClass246_Sub5_2301.aBoolean5099) && (method2320 != null || method2321 != null)) {
                    this.aClass246_Sub5_2301 = Class246_Sub5.method3117(n, false);
                }
                if (this.aClass246_Sub5_2301 != null) {
                    this.aClass246_Sub5_2301.method3120(ha, n, method2320, method2321, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.A(" + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + ((class146 != null) ? "{...}" : "null") + ',' + ((class147 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3465(final int n) {
        try {
            this.anObjectArray2316 = null;
            this.anObjectArray2215 = null;
            this.anObjectArray2292 = null;
            this.anObjectArray2272 = null;
            this.anObjectArray2252 = null;
            this.anObjectArray2340 = null;
            if (n != -1) {
                this.anIntArray2271 = null;
            }
            this.anIntArray2297 = null;
            this.anIntArray2249 = null;
            this.anObjectArray2320 = null;
            this.anObjectArray2277 = null;
            this.anObjectArray2266 = null;
            this.anObjectArray2291 = null;
            this.anObjectArray2220 = null;
            this.anObjectArray2274 = null;
            this.anObjectArray2278 = null;
            this.anObjectArray2313 = null;
            this.anObjectArray2324 = null;
            this.anObjectArray2335 = null;
            this.anObjectArray2356 = null;
            this.anObjectArray2253 = null;
            this.anIntArray2342 = null;
            this.anObjectArray2314 = null;
            this.anIntArray2271 = null;
            this.anObjectArray2294 = null;
            this.anObjectArray2239 = null;
            this.anIntArray2284 = null;
            this.anObjectArray2269 = null;
            this.anObjectArray2270 = null;
            this.anObjectArray2332 = null;
            this.anObjectArray2329 = null;
            this.anObjectArray2257 = null;
            this.anObjectArray2330 = null;
            this.anObjectArray2227 = null;
            this.anObjectArray2230 = null;
            this.anObjectArray2212 = null;
            this.anObjectArray2319 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.K(" + n + ')');
        }
    }
    
    static final void method3466(final byte b, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2) {
        try {
            boolean b2 = false;
            Label_0088: {
                if (~Class215.anInt1614 != ~class246_Sub3_Sub4_Sub2.anInt6424 && class246_Sub3_Sub4_Sub2.anInt6413 != -1 && class246_Sub3_Sub4_Sub2.anInt6400 == 0) {
                    final Class97 method2623 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2.anInt6413, b + 16363);
                    if (!method2623.aBoolean825 && 1 + class246_Sub3_Sub4_Sub2.anInt6366 <= method2623.anIntArray811[class246_Sub3_Sub4_Sub2.anInt6393]) {
                        break Label_0088;
                    }
                    b2 = true;
                    if (!client.aBoolean3553) {
                        break Label_0088;
                    }
                }
                b2 = true;
            }
            if (b2) {
                final int n = class246_Sub3_Sub4_Sub2.anInt6424 + -class246_Sub3_Sub4_Sub2.anInt6390;
                final int n2 = Class215.anInt1614 + -class246_Sub3_Sub4_Sub2.anInt6390;
                final int n3 = class246_Sub3_Sub4_Sub2.anInt6378 * 512 - -(256 * class246_Sub3_Sub4_Sub2.method3034(0));
                final int n4 = class246_Sub3_Sub4_Sub2.anInt6347 * 512 + class246_Sub3_Sub4_Sub2.method3034(0) * 256;
                final int n5 = class246_Sub3_Sub4_Sub2.anInt6362 * 512 - -(256 * class246_Sub3_Sub4_Sub2.method3034(0));
                final int n6 = 512 * class246_Sub3_Sub4_Sub2.anInt6392 - -(class246_Sub3_Sub4_Sub2.method3034(0) * 256);
                class246_Sub3_Sub4_Sub2.anInt5084 = ((-n2 + n) * n3 - -(n5 * n2)) / n;
                class246_Sub3_Sub4_Sub2.anInt5079 = (n2 * n6 + n4 * (n - n2)) / n;
            }
            class246_Sub3_Sub4_Sub2.anInt6433 = 0;
            if (class246_Sub3_Sub4_Sub2.anInt6407 == 0) {
                class246_Sub3_Sub4_Sub2.method3047(8192, false, b + 11);
            }
            if (b == 20) {
                if (class246_Sub3_Sub4_Sub2.anInt6407 == 1) {
                    class246_Sub3_Sub4_Sub2.method3047(12288, false, 107);
                }
                if (class246_Sub3_Sub4_Sub2.anInt6407 == 2) {
                    class246_Sub3_Sub4_Sub2.method3047(0, false, 65);
                }
                if (~class246_Sub3_Sub4_Sub2.anInt6407 == 0xFFFFFFFC) {
                    class246_Sub3_Sub4_Sub2.method3047(4096, false, 88);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.S(" + b + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class346 method3467(final int n, final Class115 class115, final Class59 class116) {
        try {
            if (n == ~this.anInt2267) {
                return null;
            }
            final long n2 = (0xFFFFL & this.anInt2306) << -1299272208 | (this.anInt2260 << 1934691488 & 0xFFFF00000000L) | (0xFFFF0000L & this.anInt2334 << 1170488080) | (this.anInt2267 & 0xFFFFL);
            Class346 method528 = (Class346)Class151_Sub7.aClass79_5004.method802(n - 123, n2);
            if (method528 == null) {
                method528 = class116.method528(0, this.anInt2267, this.anInt2260, this.anInt2306, class115, this.anInt2334);
                Class151_Sub7.aClass79_5004.method805(n2, method528, (byte)(-80));
            }
            return method528;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.F(" + n + ',' + ((class115 != null) ? "{...}" : "null") + ',' + ((class116 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3468(final String s, final int n, final int n2) {
        try {
            if (this.aStringArray2351 == null || n >= this.aStringArray2351.length) {
                final String[] aStringArray2351 = new String[1 + n];
                if (this.aStringArray2351 != null) {
                    for (int n3 = 0; ~n3 > ~this.aStringArray2351.length; ++n3) {
                        aStringArray2351[n3] = this.aStringArray2351[n3];
                    }
                }
                this.aStringArray2351 = aStringArray2351;
            }
            if (n2 != 1) {
                this.anIntArray2297 = null;
            }
            this.aStringArray2351[n] = s;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.Q(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final aa method3469(final ha ha, final int n) {
        try {
            final aa aa = (aa)Class76_Sub11.aClass79_3797.method802(-128, this.anInt2248);
            if (aa != null) {
                return aa;
            }
            final Class324 method3685 = Class324.method3685(Class166.aClass207_1280, this.anInt2237, 0);
            if (method3685 == null) {
                return null;
            }
            if (n < 91) {
                this.method3468(null, 115, 17);
            }
            final int n2 = method3685.anInt2719 + (method3685.anInt2722 + method3685.anInt2725);
            final int n3 = method3685.anInt2720 - (-method3685.anInt2721 - method3685.anInt2724);
            this.anIntArray2217 = new int[n3];
            this.anIntArray2298 = new int[n3];
            for (int n4 = 0; method3685.anInt2720 > n4; ++n4) {
                int n5 = 0;
                for (int n6 = 0; ~n6 > ~method3685.anInt2722; ++n6) {
                    if (method3685.aByteArray2717[n6 + method3685.anInt2722 * n4] != 0) {
                        n5 = n6;
                        break;
                    }
                }
                int n7 = n2;
                for (int i = n5; i < method3685.anInt2722; ++i) {
                    if (~method3685.aByteArray2717[i - -(n4 * method3685.anInt2722)] == -1) {
                        n7 = i;
                        break;
                    }
                }
                this.anIntArray2217[method3685.anInt2721 + n4] = method3685.anInt2725 + n5;
                this.anIntArray2298[method3685.anInt2721 + n4] = n7 + -n5;
            }
            final aa method3686 = ha.method1772(n2, n3, this.anIntArray2217, this.anIntArray2298);
            Class76_Sub11.aClass79_3797.method805(this.anInt2248, method3686, (byte)(-80));
            return method3686;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.I(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method3470(final boolean b, final boolean b2, final long n, int n2, final int n3, final String s, final boolean b3, final int n4, final int n5, final long n6, final int n7, final boolean b4, final String s2) {
        try {
            if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && Class359.anInt3058 < 500) {
                n2 = ((n2 == -1) ? Class21_Sub2.anInt5387 : n2);
                if (!b) {
                    Class157.method2506(126, new Class98_Sub46_Sub8(s2, s, n2, n5, n7, n, n3, n4, b2, b4, n6, b3));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.G(" + b + ',' + b2 + ',' + n + ',' + n2 + ',' + n3 + ',' + ((s != null) ? "{...}" : "null") + ',' + b3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + b4 + ',' + ((s2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3471(final Canvas canvas, final int n) {
        try {
            final Dimension size = canvas.getSize();
            Class287_Sub2.method3391(size.height, size.width, n + 2);
            if (n != 0) {
                method3470(false, false, 67L, 36, 81, null, false, -96, 43, 34L, 85, false, null);
            }
            if (Class98_Sub46.anInt4261 == 1) {
                Class154.aHa1231.method1782(canvas, aa_Sub1.anInt3556, Class48_Sub1_Sub2.anInt5519);
            }
            else {
                Class154.aHa1231.method1782(canvas, Class151_Sub7.anInt5005, Class149.anInt1208);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.C(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final int method3472(final int n, final int n2, final int n3) {
        try {
            if (n != 22241) {
                return -31;
            }
            if (this.aClass377_2337 == null) {
                return n2;
            }
            final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_2337.method3990(n3, -1);
            if (class98_Sub34 == null) {
                return n2;
            }
            return class98_Sub34.anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    private final int[] method3473(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-104));
            if (unsignedByte == 0) {
                return null;
            }
            final int[] array = new int[unsignedByte];
            for (int i = n; i < unsignedByte; ++i) {
                array[i] = class98_Sub22.readInt(-2);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.J(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method3474(final int n, final int n2, final int n3) {
        try {
            if (this.anIntArray2326 == null || ~n2 <= ~this.anIntArray2326.length) {
                final int[] anIntArray2326 = new int[n2 + 1];
                if (this.anIntArray2326 != null) {
                    for (int n4 = 0; this.anIntArray2326.length > n4; ++n4) {
                        anIntArray2326[n4] = this.anIntArray2326[n4];
                    }
                    for (int length = this.anIntArray2326.length; ~length > ~n2; ++length) {
                        anIntArray2326[length] = -1;
                    }
                }
                this.anIntArray2326 = anIntArray2326;
            }
            if (n != -17972) {
                this.aBoolean2288 = false;
            }
            this.anIntArray2326[n2] = n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sba.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public Class293() {
        this.aBoolean2209 = false;
        this.aBoolean2222 = false;
        this.aClass293_2219 = null;
        this.anInt2234 = -1;
        this.aByte2207 = 0;
        this.anInt2238 = -1;
        this.anInt2211 = -1;
        this.anInt2244 = 0;
        this.aBoolean2262 = false;
        this.anInt2246 = 0;
        this.anInt2258 = 0;
        this.aString2225 = "";
        this.anInt2237 = -1;
        this.anInt2218 = 0;
        this.anInt2255 = 0;
        this.anInt2268 = 0;
        this.anInt2228 = 0;
        this.anInt2273 = 0;
        this.anInt2251 = 100;
        this.anInt2289 = Class369.anInt3129;
        this.anInt2254 = -1;
        this.aBoolean2265 = false;
        this.aString2224 = "";
        this.anInt2264 = -1;
        this.anInt2208 = -1;
        this.anInt2276 = 0;
        this.aByte2240 = 0;
        this.anInt2248 = -1;
        this.aString2214 = "";
        this.anInt2235 = 0;
        this.aBoolean2295 = false;
        this.anInt2232 = 0;
        this.anInt2213 = 0;
        this.anInt2300 = -1;
        this.aBoolean2263 = false;
        this.anInt2308 = 0;
        this.aBoolean2315 = false;
        this.anInt2285 = 0;
        this.anInt2311 = 0;
        this.aBoolean2286 = false;
        this.anInt2210 = -1;
        this.anInt2267 = -1;
        this.aBoolean2241 = false;
        this.anInt2305 = 2;
        this.anInt2303 = 0;
        this.anInt2229 = 0;
        this.anInt2250 = -1;
        this.anInt2323 = 0;
        this.aByte2245 = 0;
        this.anInt2312 = 0;
        this.aBoolean2279 = false;
        this.anInt2233 = 1;
        this.anInt2223 = 0;
        this.anInt2321 = 1;
        this.anInt2302 = -1;
        this.anInt2247 = -1;
        this.aByte2243 = 0;
        this.anInt2309 = -1;
        this.anInt2317 = -1;
        this.aBoolean2325 = false;
        this.anInt2282 = 0;
        this.aBoolean2322 = false;
        this.anInt2236 = 0;
        this.anInt2299 = 0;
        this.anInt2310 = 0;
        this.anInt2283 = 0;
        this.anInt2290 = 0;
        this.anInt2328 = 0;
        this.anInt2336 = 0;
        this.anInt2293 = 1;
        this.anInt2242 = 0;
        this.anInt2304 = 0;
        this.anInt2226 = 0;
        this.aBoolean2288 = false;
        this.anInt2318 = -1;
        this.anInt2338 = 1;
        this.aClass98_Sub49_2348 = Class265.aClass98_Sub49_1982;
        this.anInt2346 = 0;
        this.anInt2341 = 0;
        this.anInt2347 = 0;
        this.anInt2352 = 0;
        this.anInt2349 = 0;
        this.anInt2350 = 0;
        this.anInt2344 = 0;
        this.anInt2353 = 0;
        this.anInt2287 = 1;
        this.anInt2307 = 0;
        this.anInt2296 = 0;
        this.aBoolean2256 = false;
        this.anInt2355 = 0;
    }
}
