import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

class Class98_Sub22 extends Class98
{
    int anInt3991;
    byte[] aByteArray3992;
    static IncomingOpcode aClass58_3993;
    static int anInt3994;
    static Class207 aClass207_3995;
    
    final void method1181(final String s, final int n) {
        try {
            final int index = s.indexOf(0);
            if (~index <= n) {
                throw new IllegalArgumentException("NUL character at " + index + " - cannot pjstr2");
            }
            this.aByteArray3992[this.anInt3991++] = 0;
            this.anInt3991 += Class200.method2694(s, 0, s.length(), this.anInt3991, this.aByteArray3992, -28439);
            this.aByteArray3992[this.anInt3991++] = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.GB(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void writeShortA(final int n, final byte b) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 2119028456);
            if (b != 126) {
                this.writeLEInt(75, -5);
            }
            this.aByteArray3992[this.anInt3991++] = (byte)(128 + n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.RA(" + n + ',' + b + ')');
        }
    }
    
    final int method1183(final int n) {
        try {
            this.anInt3991 += 2;
            int n2 = (this.aByteArray3992[-2 + this.anInt3991] & 0xFF) + (this.aByteArray3992[this.anInt3991 - 1] << 1553290568 & 0xFF00);
            if (~n2 < -32768) {
                n2 -= 65536;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.KC(" + n + ')');
        }
    }
    
    final byte method1184(final int n) {
        try {
            return (byte)(this.aByteArray3992[this.anInt3991++] - 128);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.GA(" + n + ')');
        }
    }
    
    final void writeInt(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> -1995948680);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 1547612048);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 1571862888);
            this.aByteArray3992[this.anInt3991++] = (byte)n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.FA(" + n + ',' + n2 + ')');
        }
    }
    
    final int method1186(final int n) {
        try {
            this.anInt3991 += 3;
            return (this.aByteArray3992[-1 + this.anInt3991] & 0xFF) + ((0xFF & this.aByteArray3992[-2 + this.anInt3991]) << 1155233704) + ((this.aByteArray3992[this.anInt3991 - 3] & 0xFF) << 251467472);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.MB(" + n + ')');
        }
    }
    
    final byte method1187(final byte b) {
        try {
            return (byte)(-this.aByteArray3992[this.anInt3991++]);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.WB(" + b + ')');
        }
    }
    
    final void method1188(final String s, final byte b) {
        try {
            if (b == 113) {
                final int index = s.indexOf(0);
                if (~index <= -1) {
                    throw new IllegalArgumentException("NUL character at " + index + " - cannot pjstr");
                }
                this.anInt3991 += Class200.method2694(s, 0, s.length(), this.anInt3991, this.aByteArray3992, -28439);
                this.aByteArray3992[this.anInt3991++] = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.BC(" + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final long method1189(final byte b) {
        try {
            return (this.method1202((byte)(-58)) & 0xFFFFFFFFL) + ((0xFFFFFFFFL & this.method1202((byte)(-68))) << 1898790944);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.FC(" + b + ')');
        }
    }
    
    final void method1190(final byte[] array, final boolean b, final int n, final int n2) {
        try {
            for (int i = n2; i < n2 + n; ++i) {
                array[i] = this.aByteArray3992[this.anInt3991++];
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.CA(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    final byte readSignedByte(final byte b) {
        try {
            return this.aByteArray3992[this.anInt3991++];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.NC(" + b + ')');
        }
    }
    
    final int method1192(final byte b) {
        try {
            this.anInt3991 += 3;
            return (0xFF & this.aByteArray3992[-3 + this.anInt3991]) + (((0xFF & this.aByteArray3992[-2 + this.anInt3991]) << -1177345560) + (0xFF0000 & this.aByteArray3992[this.anInt3991 - 1] << 587636880));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.BA(" + b + ')');
        }
    }
    
    final int readInt1(final boolean b) {
        try {
            this.anInt3991 += 4;
            return (this.aByteArray3992[-4 + this.anInt3991] << -1188328504 & 0xFF00) + ((0xFF0000 & this.aByteArray3992[this.anInt3991 - 1] << 340284016) + ((this.aByteArray3992[this.anInt3991 - 2] & 0xFF) << -2077360904)) - -(this.aByteArray3992[-3 + this.anInt3991] & 0xFF);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.M(" + b + ')');
        }
    }
    
    final void method1194(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.LC(" + n + ',' + n2 + ')');
        }
    }
    
    final void writeLEShort(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)n;
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> -1001534936);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.KA(" + n + ',' + n2 + ')');
        }
    }
    
    final int method1196(final int n, final byte b) {
        try {
            final int method3937 = Class365.method3937(this.anInt3991, this.aByteArray3992, n, false);
            this.writeInt(1571862888, method3937);
            return method3937;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.KB(" + n + ',' + b + ')');
        }
    }
    
    final int readSmart(final int n) {
        try {
            if ((0xFF & this.aByteArray3992[this.anInt3991]) < 128) {
                return this.readUnsignedByte((byte)(-115));
            }
            return -32768 + this.readShort((byte)127);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.R(" + n + ')');
        }
    }
    
    final int method1198(final int n) {
        try {
            this.anInt3991 += 2;
            int n2 = (-128 + this.aByteArray3992[this.anInt3991 - 2] & 0xFF) + (this.aByteArray3992[this.anInt3991 - 1] << -1508488440 & 0xFF00);
            if (~n2 < -32768) {
                n2 -= 65536;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.QA(" + n + ')');
        }
    }
    
    final void method1199(final int n, final boolean b) {
        try {
            if (b) {
                this.readInt1(true);
            }
            if ((0xFFFFFF80 & n) != 0x0) {
                if ((0xFFFFC000 & n) != 0x0) {
                    if (~(0xFFE00000 & n) != -1) {
                        if ((0xF0000000 & n) != 0x0) {
                            this.method1194(n >>> -342844612 | 0x80, 94);
                        }
                        this.method1194((n | 0x1001C695) >>> 651642005, -37);
                    }
                    this.method1194((0x201D9A | n) >>> -33880754, 107);
                }
                this.method1194(0x80 | n >>> -1419157497, -56);
            }
            this.method1194(n & 0x7F, 56);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.QC(" + n + ',' + b + ')');
        }
    }
    
    final void method1200(final byte b, final int n) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> -968007800);
            this.aByteArray3992[this.anInt3991++] = (byte)n;
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> -922569928);
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> -944199472);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.MA(" + b + ',' + n + ')');
        }
    }
    
    final void method1201(final int n) {
        try {
            if (this.aByteArray3992 != null) {
                Class129.method2228((byte)75, this.aByteArray3992);
            }
            this.aByteArray3992 = null;
            if (n != 0) {
                method1216(-7);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.VA(" + n + ')');
        }
    }
    
    final int method1202(final byte b) {
        try {
            this.anInt3991 += 4;
            if (b >= -45) {
                Class98_Sub22.anInt3994 = 37;
            }
            return (this.aByteArray3992[-3 + this.anInt3991] << -401730296 & 0xFF00) + ((this.aByteArray3992[this.anInt3991 - 2] & 0xFF) << 178467952) + (((0xFF & this.aByteArray3992[-1 + this.anInt3991]) << -688793032) + (0xFF & this.aByteArray3992[-4 + this.anInt3991]));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.SA(" + b + ')');
        }
    }
    
    final int readShort(final byte b) {
        try {
            if (b != 127) {
                Class98_Sub22.aClass58_3993 = null;
            }
            this.anInt3991 += 2;
            return (this.aByteArray3992[this.anInt3991 - 1] & 0xFF) + ((this.aByteArray3992[-2 + this.anInt3991] & 0xFF) << -1546530360);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.CB(" + b + ')');
        }
    }
    
    final String readString(final byte b) {
        try {
            final int anInt3991 = this.anInt3991;
            if (b != 84) {
                this.readInt1(true);
            }
            while (~this.aByteArray3992[this.anInt3991++] != -1) {}
            final int n = -anInt3991 + this.anInt3991 - 1;
            if (n == 0) {
                return "";
            }
            return Class98_Sub46_Sub6.method1546(n, anInt3991, (byte)(-64), this.aByteArray3992);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.TA(" + b + ')');
        }
    }
    
    final void method1205(final BigInteger bigInteger, final boolean b, final BigInteger bigInteger2) {
        try {
            final int anInt3991 = this.anInt3991;
            this.anInt3991 = 0;
            final byte[] array = new byte[anInt3991];
            this.method1190(array, b, anInt3991, 0);
            final byte[] byteArray = new BigInteger(array).toByteArray();
            this.anInt3991 = 0;
            if (b) {
                this.writeShort(byteArray.length, 1571862888);
                this.method1217(byteArray, byteArray.length, -1, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.IA(" + ((bigInteger != null) ? "{...}" : "null") + ',' + b + ',' + ((bigInteger2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void writeByteS(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(128 + -n);
            if (n2 > -16) {
                this.method1192((byte)(-121));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.HA(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1207(final byte b, final int n) {
        try {
            this.aByteArray3992[this.anInt3991 - n - 2] = (byte)(n >> 1804543080);
            if (b != 90) {
                this.readUnsignedByte((byte)(-108));
            }
            this.aByteArray3992[-1 + (-n + this.anInt3991)] = (byte)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.CC(" + b + ',' + n + ')');
        }
    }
    
    final int method1208(final int n) {
        try {
            int n2 = 0;
            if (n != 3893) {
                return 116;
            }
            int i;
            for (i = this.readSmart(n + 1689618819); i == 32767; i = this.readSmart(1689622712)) {
                n2 += 32767;
            }
            return n2 + i;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.P(" + n + ')');
        }
    }
    
    final int readInt2(final int n) {
        try {
            this.anInt3991 += 4;
            if (n >= -7) {
                this.anInt3991 = -81;
            }
            return (this.aByteArray3992[-2 + this.anInt3991] & 0xFF) + ((this.aByteArray3992[this.anInt3991 - 4] & 0xFF) << 1981066736) + (((this.aByteArray3992[-3 + this.anInt3991] & 0xFF) << -1310222600) + ((0xFF & this.aByteArray3992[this.anInt3991 - 1]) << 1074766536));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.UB(" + n + ')');
        }
    }
    
    final boolean method1210(final int n) {
        try {
            this.anInt3991 -= 4;
            return Class365.method3937(this.anInt3991, this.aByteArray3992, 0, false) == this.readInt(-2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.EB(" + n + ')');
        }
    }
    
    final void method1211(final byte b, final int n) {
        try {
            if (b > 79) {
                this.aByteArray3992[-n + (this.anInt3991 - 1)] = (byte)n;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.HB(" + b + ',' + n + ')');
        }
    }
    
    final int readShortA(final int n) {
        try {
            this.anInt3991 += 2;
            if (n <= 40) {
                return -92;
            }
            return (0xFF & this.aByteArray3992[this.anInt3991 - 1] - 128) + (0xFF00 & this.aByteArray3992[this.anInt3991 - 2] << -834637528);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.GC(" + n + ')');
        }
    }
    
    final void method1213(final int n, final long n2, int n3) {
        try {
            if (--n3 < 0 || n3 > 7) {
                throw new IllegalArgumentException();
            }
            if (n != 31498) {
                this.method1208(4);
            }
            for (int i = 8 * n3; i >= 0; i -= 8) {
                this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> i);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.S(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final int readUShort(final boolean b) {
        try {
            this.anInt3991 += 2;
            if (b) {
                this.method1244(45, (byte)31);
            }
            int n = (0xFF00 & this.aByteArray3992[this.anInt3991 - 2] << 1357336488) - -(this.aByteArray3992[this.anInt3991 - 1] & 0xFF);
            if (n > 32767) {
                n -= 65536;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.EA(" + b + ')');
        }
    }
    
    final void method1215(final int[] array, final int anInt3991, final int n, final byte b) {
        try {
            if (b != 30) {
                this.method1192((byte)(-61));
            }
            final int anInt3992 = this.anInt3991;
            this.anInt3991 = anInt3991;
            for (int n2 = (-anInt3991 + n) / 8, n3 = 0; ~n3 > ~n2; ++n3) {
                int int1 = this.readInt(b ^ 0xFFFFFFE0);
                int int2 = this.readInt(-2);
                int n4 = -957401312;
                final int n5 = -1640531527;
                int n6 = 32;
                while (~(n6--) < -1) {
                    int2 -= (array[n4 >>> 626734027 & 0x5A600003] + n4 ^ (int1 << 972126180 ^ int1 >>> -207185147) - -int1);
                    n4 -= n5;
                    int1 -= (n4 + array[n4 & 0x3] ^ int2 + (int2 << -1927182972 ^ int2 >>> -1816832155));
                }
                this.anInt3991 -= 8;
                this.writeInt(1571862888, int1);
                this.writeInt(b + 1571862858, int2);
            }
            this.anInt3991 = anInt3992;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.AB(" + ((array != null) ? "{...}" : "null") + ',' + anInt3991 + ',' + n + ',' + b + ')');
        }
    }
    
    static final void method1216(final int n) {
        try {
            if (n != -17470) {
                method1216(-14);
            }
            Class185.method2628(0, -42, 15).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.HC(" + n + ')');
        }
    }
    
    final void method1217(final byte[] array, final int n, final int n2, final int n3) {
        try {
            int n4 = n3;
            if (n2 != -1) {
                Class98_Sub22.anInt3994 = 121;
            }
            while (n + n3 > n4) {
                this.aByteArray3992[this.anInt3991++] = array[n4];
                ++n4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.DB(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method1218(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)n;
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 1489446952);
            if (n2 != 1489446952) {
                this.aByteArray3992 = null;
            }
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 182207856);
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> -43371176);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.MC(" + n + ',' + n2 + ')');
        }
    }
    
    final int readInt(final int n) {
        try {
            this.anInt3991 += 4;
            if (n != -2) {
                this.readShort1((byte)(-76));
            }
            return (0xFF & this.aByteArray3992[-1 + this.anInt3991]) + ((0xFF0000 & this.aByteArray3992[-3 + this.anInt3991] << 326096944) + ((0xFF & this.aByteArray3992[-4 + this.anInt3991]) << 1206151768)) + (this.aByteArray3992[-2 + this.anInt3991] << -1753478520 & 0xFF00);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.SB(" + n + ')');
        }
    }
    
    final int readLEShortA(final byte b) {
        try {
            this.anInt3991 += 2;
            return ((this.aByteArray3992[this.anInt3991 - 1] & 0xFF) << -1492087128) + (0xFF & this.aByteArray3992[-2 + this.anInt3991] - 128);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.FB(" + b + ')');
        }
    }
    
    final void method1221(final int n, final long n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 47442488);
            if (n > -49) {
                this.anInt3991 = -23;
            }
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 1602810288);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 140118760);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 1658586912);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> -1173783976);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> -1213337456);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 170858184);
            this.aByteArray3992[this.anInt3991++] = (byte)n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.JB(" + n + ',' + n2 + ')');
        }
    }
    
    final String method1222(final int n) {
        try {
            if (~this.aByteArray3992[this.anInt3991] == n) {
                ++this.anInt3991;
                return null;
            }
            return this.readString((byte)84);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.WA(" + n + ')');
        }
    }
    
    final String method1223(final int n) {
        try {
            if (~this.aByteArray3992[this.anInt3991++] != -1) {
                throw new IllegalStateException("Bad version number in gjstr2");
            }
            final int anInt3991 = this.anInt3991;
            while (~this.aByteArray3992[this.anInt3991++] != -1) {}
            if (n != -1) {
                return null;
            }
            final int n2 = -1 + (this.anInt3991 + -anInt3991);
            if (n2 == 0) {
                return "";
            }
            return Class98_Sub46_Sub6.method1546(n2, anInt3991, (byte)(-108), this.aByteArray3992);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.PB(" + n + ')');
        }
    }
    
    final void writeShort(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 737932552);
            this.aByteArray3992[this.anInt3991++] = (byte)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.QB(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1225(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 1668051632);
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 >> 831021000);
            this.aByteArray3992[this.anInt3991++] = (byte)n2;
            if (n == -24472) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.RB(" + n + ',' + n2 + ')');
        }
    }
    
    final int readByteC(final byte b) {
        try {
            return -this.aByteArray3992[this.anInt3991++] & 0xFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.UA(" + b + ')');
        }
    }
    
    final int method1227(final byte b) {
        try {
            if (b != -1) {
                Class98_Sub22.aClass58_3993 = null;
            }
            this.anInt3991 += 3;
            int n = ((this.aByteArray3992[-2 + this.anInt3991] & 0xFF) << -1482393368) + ((this.aByteArray3992[this.anInt3991 - 3] << 1099514736 & 0xFF0000) + (this.aByteArray3992[-1 + this.anInt3991] & 0xFF));
            if (~n < -8388608) {
                n -= 16777216;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.LA(" + b + ')');
        }
    }
    
    final int readShort1(final byte b) {
        try {
            this.anInt3991 += 2;
            return (0xFF & this.aByteArray3992[-2 + this.anInt3991]) + ((this.aByteArray3992[-1 + this.anInt3991] & 0xFF) << 850116168);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.PA(" + b + ')');
        }
    }
    
    final int readUnsignedByte(final byte b) {
        try {
            return this.aByteArray3992[this.anInt3991++] & 0xFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.OA(" + b + ')');
        }
    }
    
    final int readByteS(final int n) {
        try {
            return 128 + -this.aByteArray3992[this.anInt3991++] & 0xFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.OB(" + n + ')');
        }
    }
    
    final void method1231(final int n, final byte b) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(128 + n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.Q(" + n + ',' + b + ')');
        }
    }
    
    final void method1232(final int n, final byte b) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 1586241040);
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 1689622712);
            this.aByteArray3992[this.anInt3991++] = (byte)n;
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 1259248840);
            if (b <= 74) {
                Class98_Sub22.anInt3994 = 115;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.IB(" + n + ',' + b + ')');
        }
    }
    
    final void method1233(final byte b, final int n) {
        try {
            this.aByteArray3992[-4 + (-n + this.anInt3991)] = (byte)(n >> 749084632);
            if (b > -69) {
                this.method1190(null, false, -107, -119);
            }
            this.aByteArray3992[-3 + (-n + this.anInt3991)] = (byte)(n >> -1146031696);
            this.aByteArray3992[-n + this.anInt3991 - 2] = (byte)(n >> -2096973304);
            this.aByteArray3992[-n + (this.anInt3991 - 1)] = (byte)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.O(" + b + ',' + n + ')');
        }
    }
    
    final byte method1234(final int n) {
        try {
            return (byte)(-this.aByteArray3992[this.anInt3991++] + 128);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.PC(" + n + ')');
        }
    }
    
    final void method1235(final boolean b, final int[] array, final int anInt3991, final int n) {
        try {
            final int anInt3992 = this.anInt3991;
            this.anInt3991 = anInt3991;
            for (int n2 = (n - anInt3991) / 8, n3 = 0; ~n3 > ~n2; ++n3) {
                int int1 = this.readInt(-2);
                int int2 = this.readInt(-2);
                int n4 = 0;
                final int n5 = -1640531527;
                int n6 = 32;
                while (~(n6--) < -1) {
                    int1 += ((int2 << 2089946276 ^ int2 >>> 1651808389) - -int2 ^ array[n4 & 0x3] + n4);
                    n4 += n5;
                    int2 += (array[(0x1FD8 & n4) >>> -1054972021] + n4 ^ (int1 >>> -406550651 ^ int1 << 2098622308) + int1);
                }
                this.anInt3991 -= 8;
                this.writeInt(1571862888, int1);
                this.writeInt(1571862888, int2);
            }
            this.anInt3991 = anInt3992;
            if (!b) {
                Class98_Sub22.anInt3994 = -83;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.AC(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt3991 + ',' + n + ')');
        }
    }
    
    final int readByteA(final boolean b) {
        try {
            if (!b) {
                this.aByteArray3992 = null;
            }
            return 0xFF & this.aByteArray3992[this.anInt3991++] - 128;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.JA(" + b + ')');
        }
    }
    
    final void method1237(final int n, final int n2) {
        try {
            if (n >= 0 && ~n > -129) {
                this.method1194(n, -63);
            }
            else {
                if (n2 >= -117) {
                    this.method1194(-1, -2);
                }
                if (n < 0 || ~n <= -32769) {
                    throw new IllegalArgumentException();
                }
                this.writeShort(n + 32768, 1571862888);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.EC(" + n + ',' + n2 + ')');
        }
    }
    
    final void writeLEShortA(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)(n2 + n);
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 806931912);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.IC(" + n + ',' + n2 + ')');
        }
    }
    
    final int method1239(final int n) {
        try {
            if (~(0xFF & this.aByteArray3992[this.anInt3991]) > -129) {
                return -64 + this.readUnsignedByte((byte)31);
            }
            return this.readShort((byte)127) - 49152;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.LB(" + n + ')');
        }
    }
    
    final int method1240(final byte b) {
        try {
            if (b != -20) {
                return 50;
            }
            byte b2 = this.aByteArray3992[this.anInt3991++];
            int n = 0;
            while (b2 < 0) {
                n = ((b2 & 0x7F) | n) << 936736039;
                b2 = this.aByteArray3992[this.anInt3991++];
            }
            return b2 | n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.NB(" + b + ')');
        }
    }
    
    static final boolean method1241(final boolean b, final int n, final int n2) {
        try {
            return !b && (0x100 & n2) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.L(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    final int method1242(final int n) {
        try {
            this.anInt3991 += 2;
            int n2 = (this.aByteArray3992[-1 + this.anInt3991] - 128 & 0xFF) + (0xFF00 & this.aByteArray3992[-2 + this.anInt3991] << 365122312);
            if (~n2 < -32768) {
                n2 -= 65536;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.OC(" + n + ')');
        }
    }
    
    public static void method1243(final int n) {
        try {
            Class98_Sub22.aClass207_3995 = null;
            if (n <= 79) {
                Class98_Sub22.anInt3994 = -43;
            }
            Class98_Sub22.aClass58_3993 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.JC(" + n + ')');
        }
    }
    
    final void method1244(final int n, final byte b) {
        try {
            if (b != 112) {
                this.method1217(null, -122, -10, -57);
            }
            this.aByteArray3992[this.anInt3991++] = (byte)(-n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.VB(" + n + ',' + b + ')');
        }
    }
    
    final void writeLEInt(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)n;
            if (n2 != 1046032984) {
                this.readInt(13);
            }
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> -965600312);
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 534653328);
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 1046032984);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.TB(" + n + ',' + n2 + ')');
        }
    }
    
    final long method1246(final int n) {
        try {
            final long n2 = 0xFFFFFFFFL & this.readInt(-2);
            final long n3 = this.readInt(-2) & 0xFFFFFFFFL;
            if (n >= -87) {
                this.readShort1((byte)15);
            }
            return n3 + (n2 << 1722451168);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.N(" + n + ')');
        }
    }
    
    final void method1247(final int n, final int n2) {
        try {
            this.aByteArray3992[this.anInt3991++] = (byte)n;
            if (n2 != 4) {
                this.method1187((byte)12);
            }
            this.aByteArray3992[this.anInt3991++] = (byte)(n >> 1291747880);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.DA(" + n + ',' + n2 + ')');
        }
    }
    
    final long method1248(int n, final boolean b) {
        try {
            if (--n < 0 || ~n < -8) {
                throw new IllegalArgumentException();
            }
            if (b) {
                this.readIntReverse(false);
            }
            int i = 8 * n;
            long n2 = 0L;
            while (i >= 0) {
                n2 |= (this.aByteArray3992[this.anInt3991++] & 0xFFL) << i;
                i -= 8;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.BB(" + n + ',' + b + ')');
        }
    }
    
    final int readIntReverse(final boolean b) {
        try {
            this.anInt3991 += 4;
            return (this.aByteArray3992[-4 + this.anInt3991] & 0xFF) + ((0xFF & this.aByteArray3992[-2 + this.anInt3991]) << -1498293360) + (((this.aByteArray3992[-1 + this.anInt3991] & 0xFF) << 1899061624) - -((0xFF & this.aByteArray3992[-3 + this.anInt3991]) << 279902248));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.DC(" + b + ')');
        }
    }
    
    Class98_Sub22(final int n) {
        try {
            this.aByteArray3992 = Class129.method2225(false, n);
            this.anInt3991 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.<init>(" + n + ')');
        }
    }
    
    Class98_Sub22(final byte[] aByteArray3992) {
        try {
            this.anInt3991 = 0;
            this.aByteArray3992 = aByteArray3992;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.<init>(" + ((aByteArray3992 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1250(final int n, final int n2, final boolean b, final byte[] array) {
        try {
            for (int n3 = n2 + n - 1; ~n3 <= ~n; --n3) {
                array[n3] = (byte)(-128 + this.aByteArray3992[this.anInt3991++]);
            }
            if (b) {
                Class98_Sub22.anInt3994 = -120;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ji.NA(" + n + ',' + n2 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub22.anInt3994 = -1;
        Class98_Sub22.aClass58_3993 = new IncomingOpcode(114, 3);
    }
}
