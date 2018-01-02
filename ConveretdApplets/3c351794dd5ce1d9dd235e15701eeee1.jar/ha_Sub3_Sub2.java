import jaclib.memory.Stream;
import java.awt.Rectangle;
import java.awt.Canvas;
import jaggl.MapBuffer;
import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class ha_Sub3_Sub2 extends ha_Sub3
{
    private OpenGL anOpenGL6111;
    private Class148 aClass148_6112;
    private Class148 aClass148_6113;
    private Class148 aClass148_6114;
    private Class148 aClass148_6115;
    private Class148 aClass148_6116;
    private Class148 aClass148_6117;
    private Class148 aClass148_6118;
    private boolean aBoolean6119;
    private boolean aBoolean6120;
    private boolean aBoolean6121;
    private boolean aBoolean6122;
    private boolean aBoolean6123;
    private Class69_Sub2[] aClass69_Sub2Array6124;
    MapBuffer aMapBuffer6125;
    private int anInt6126;
    private long aLong6127;
    MapBuffer aMapBuffer6128;
    private int anInt6129;
    private boolean aBoolean6130;
    private String aString6131;
    private int anInt6132;
    boolean aBoolean6133;
    boolean aBoolean6134;
    int anInt6135;
    boolean aBoolean6136;
    boolean aBoolean6137;
    private boolean aBoolean6138;
    int[] anIntArray6139;
    private String aString6140;
    
    @Override
    final void method1994(final byte b, final int n) {
        try {
            if (b < 0) {
                this.aBoolean6133 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.BA(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void GA(final int n) {
        try {
            OpenGL.glClearColor((n & 0xFF0000) / 1.671168E7f, (n & 0xFF00) / 65280.0f, (0xFF & n) / 255.0f, (n >>> -58636872) / 255.0f);
            OpenGL.glClear(16384);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.GA(" + n + ')');
        }
    }
    
    @Override
    final void method2026(final int n, final boolean b, final byte b2, final Class65 class65, final boolean b3) {
        try {
            if (b2 != 27) {
                this.aString6140 = null;
            }
            OpenGL.glTexEnvi(8960, 34176 + n, Class98_Sub12.method1128(class65, 36));
            if (b) {
                OpenGL.glTexEnvi(8960, n + 34192, b3 ? 771 : 770);
            }
            else {
                OpenGL.glTexEnvi(8960, n + 34192, b3 ? 769 : 768);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.KB(" + n + ',' + b + ',' + b2 + ',' + ((class65 != null) ? "{...}" : "null") + ',' + b3 + ')');
        }
    }
    
    @Override
    final void method2037(final Class232 class232, final int n, final byte b, final int n2) {
        try {
            if (b > 8) {
                int n3;
                int n4;
                if (Class334.aClass232_3468 != class232) {
                    if (class232 == Class287_Sub1.aClass232_3420) {
                        n3 = 1 + n2;
                        n4 = 3;
                    }
                    else if (class232 == Class336.aClass232_2822) {
                        n4 = 4;
                        n3 = n2 * 3;
                    }
                    else if (class232 == Class98_Sub46_Sub15.aClass232_6043) {
                        n3 = n2 + 2;
                        n4 = 6;
                    }
                    else if (Class97.aClass232_806 == class232) {
                        n3 = n2 + 2;
                        n4 = 5;
                    }
                    else {
                        n3 = n2;
                        n4 = 0;
                    }
                }
                else {
                    n3 = 2 * n2;
                    n4 = 1;
                }
                OpenGL.glDrawArrays(n4, n, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.HB(" + ((class232 != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    private final void method2081(final byte b) {
        try {
            if (this.aBoolean6120) {
                OpenGL.glPopMatrix();
            }
            if (super.aClass204_4551.method2708(126)) {
                if (!this.aBoolean6122) {
                    OpenGL.glLoadMatrixf(super.aClass111_Sub3_4545.method2121((byte)(-81), Class98_Sub30.aFloatArray4096), 0);
                    this.aBoolean6122 = true;
                    this.method2065((byte)80);
                    this.method2035(-14713);
                }
                if (super.aBoolean4540) {
                    this.aBoolean6120 = false;
                }
                else {
                    OpenGL.glPushMatrix();
                    OpenGL.glMultMatrixf(super.aClass111_Sub3_4542.method2121((byte)(-80), Class98_Sub30.aFloatArray4096), 0);
                    this.aBoolean6120 = true;
                }
            }
            else if (!super.aBoolean4540) {
                OpenGL.glLoadMatrixf(super.aClass111_Sub3_4542.method2121((byte)(-57), Class98_Sub30.aFloatArray4096), 0);
                this.aBoolean6120 = false;
            }
            else {
                OpenGL.glLoadIdentity();
                this.aBoolean6120 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.N(" + b + ')');
        }
    }
    
    @Override
    final void method2025(final byte b) {
        try {
            if (!super.aBoolean4555) {
                OpenGL.glDisable(16384);
                OpenGL.glDisable(16385);
            }
            else {
                OpenGL.glEnable(16384);
                OpenGL.glEnable(16385);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.QA(" + b + ')');
        }
    }
    
    @Override
    final Class76 method2067(final int n, final byte b) {
        try {
            if (n != 3) {
                if (~n == 0xFFFFFFFB) {
                    return new Class76_Sub2(this, super.aClass207_4535, super.aClass195_4529);
                }
                if (~n != 0xFFFFFFF7) {
                    return super.method2067(n, (byte)(-112));
                }
                if (!client.aBoolean3553) {
                    return new Class76_Sub9(this, super.aClass207_4535, super.aClass195_4529);
                }
            }
            return new Class76_Sub8(this, super.aClass207_4535);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.CD(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method1746(final int n, final int n2, final int n3, final int n4) {
    }
    
    @Override
    final void method2009(final int n) {
        try {
            OpenGL.glScissor(super.anInt4578 + super.anInt4602, -super.anInt4638 + super.anInt4531 + super.anInt4622, super.anInt4575 - super.anInt4602, -super.anInt4558 + super.anInt4638);
            if (n != 28976) {
                this.method1974(-60);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.V(" + n + ')');
        }
    }
    
    @Override
    final void F(final int n, final int n2) {
    }
    
    @Override
    final void method2007(final boolean b) {
        try {
            super.aFloat4592 = -super.anInt4601 + super.anInt4605;
            super.aFloat4615 = -super.anInt4581 + super.aFloat4592;
            if (b) {
                this.method1972(58);
            }
            if (super.aFloat4615 < super.anInt4640) {
                super.aFloat4615 = super.anInt4640;
            }
            OpenGL.glFogf(2915, super.aFloat4615);
            OpenGL.glFogf(2916, super.aFloat4592);
            Class98_Sub39.aFloatArray4189[2] = Class202.method2702(super.anInt4636, 255) / 255.0f;
            Class98_Sub39.aFloatArray4189[0] = Class202.method2702(16711680, super.anInt4636) / 1.671168E7f;
            Class98_Sub39.aFloatArray4189[1] = Class202.method2702(65280, super.anInt4636) / 65280.0f;
            OpenGL.glFogfv(2918, Class98_Sub39.aFloatArray4189, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.S(" + b + ')');
        }
    }
    
    @Override
    final Object method2058(final Canvas canvas, final int n) {
        try {
            if (n != -8401) {
                this.anInt6126 = -114;
            }
            final long prepareSurface = this.anOpenGL6111.prepareSurface(canvas);
            if (prepareSurface == -1L) {
                throw new RuntimeException();
            }
            return new Long(prepareSurface);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.EB(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int I() {
        try {
            return this.anInt6132;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.I()");
        }
    }
    
    @Override
    final void b(final int n, final int n2, final int n3, final int n4, final double n5) {
    }
    
    final synchronized void method2082(final int n, final long aLong832) {
        try {
            if (n != 0) {
                this.aClass148_6112 = null;
            }
            final Class98 class98 = new Class98();
            class98.aLong832 = aLong832;
            this.aClass148_6118.method2419(class98, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.AA(" + n + ',' + aLong832 + ')');
        }
    }
    
    @Override
    final void method2030(final byte b) {
        try {
            Label_0028: {
                if (!super.aBoolean4672) {
                    OpenGL.glDisable(3089);
                    if (!client.aBoolean3553) {
                        break Label_0028;
                    }
                }
                OpenGL.glEnable(3089);
            }
            if (b != -122) {
                this.method2007(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.MC(" + b + ')');
        }
    }
    
    @Override
    final void method1999(final byte b) {
        try {
            Label_0045: {
                if (super.aBoolean4571 && super.aBoolean4562 && ~super.anInt4581 <= -1) {
                    OpenGL.glEnable(2912);
                    if (!client.aBoolean3553) {
                        break Label_0045;
                    }
                }
                OpenGL.glDisable(2912);
            }
            if (b != 112) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.WB(" + b + ')');
        }
    }
    
    @Override
    final void method2036(final int n) {
        try {
            if (n != -11155) {
                this.aBoolean6123 = false;
            }
            OpenGL.glViewport(super.anInt4578, super.anInt4622, super.anInt4527, super.anInt4531);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.JB(" + n + ')');
        }
    }
    
    @Override
    final void method2016(final byte b) {
        try {
            if (super.aClass126_4625 == Class101.aClass126_848) {
                OpenGL.glBlendFunc(770, 771);
            }
            else if (Class373_Sub1_Sub1.aClass126_6216 == super.aClass126_4625) {
                OpenGL.glBlendFunc(1, 1);
            }
            else if (Class83.aClass126_632 == super.aClass126_4625) {
                OpenGL.glBlendFunc(774, 1);
            }
            if (b >= -109) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.LA(" + b + ')');
        }
    }
    
    @Override
    final void method2059(final boolean b, final boolean b2) {
        try {
            Label_0023: {
                if (b) {
                    OpenGL.glEnable(32925);
                    if (!client.aBoolean3553) {
                        break Label_0023;
                    }
                }
                OpenGL.glDisable(32925);
            }
            if (b2) {
                this.method1806(86);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.FD(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final Class62 method1799() {
        try {
            int n = -1;
            if (this.aString6140.indexOf("nvidia") == -1) {
                if (~this.aString6140.indexOf("intel") != 0x0) {
                    n = 32902;
                }
                else if (~this.aString6140.indexOf("ati") != 0x0) {
                    n = 4098;
                }
            }
            else {
                n = 4318;
            }
            return new Class62(n, "OpenGL", this.anInt6129, this.aString6131, 0L);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.RB()");
        }
    }
    
    @Override
    final void method1764(final int n, final int n2) throws Exception_Sub1 {
        try {
            this.anOpenGL6111.swapBuffers();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.BB(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1791(final float n, final float n2, final float n3) {
    }
    
    @Override
    final void method2033(final int n) {
        try {
            if (super.aBoolean4560) {
                OpenGL.glEnable(3042);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            OpenGL.glDisable(3042);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.IA(" + n + ')');
        }
    }
    
    @Override
    final int[] na(final int n, final int n2, final int n3, final int n4) {
        try {
            final int[] array = new int[n3 * n4];
            for (int i = 0; i < n4; ++i) {
                OpenGL.glReadPixelsi(n, -i + super.anInt4531 + -n2 - 1, n3, 1, 32993, this.anInt6135, array, i * n3);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.na(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final synchronized void method1806(int n) {
        try {
            n &= Integer.MAX_VALUE;
            int n2 = 0;
            while (!this.aClass148_6113.method2420(-126)) {
                final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass148_6113.method2421(6494);
                Class294.anIntArray2408[n2++] = (int)class98_Sub34.aLong832;
                super.anInt4538 -= class98_Sub34.anInt4126;
                if (n2 == 1000) {
                    OpenGL.glDeleteBuffersARB(n2, Class294.anIntArray2408, 0);
                    n2 = 0;
                }
            }
            if (~n2 < -1) {
                OpenGL.glDeleteBuffersARB(n2, Class294.anIntArray2408, 0);
                n2 = 0;
            }
            while (!this.aClass148_6114.method2420(-125)) {
                final Class98_Sub34 class98_Sub35 = (Class98_Sub34)this.aClass148_6114.method2421(6494);
                Class294.anIntArray2408[n2++] = (int)class98_Sub35.aLong832;
                super.anInt4541 -= class98_Sub35.anInt4126;
                if (n2 == 1000) {
                    OpenGL.glDeleteTextures(n2, Class294.anIntArray2408, 0);
                    n2 = 0;
                }
            }
            if (n2 > 0) {
                OpenGL.glDeleteTextures(n2, Class294.anIntArray2408, 0);
                n2 = 0;
            }
            while (!this.aClass148_6115.method2420(-125)) {
                Class294.anIntArray2408[n2++] = ((Class98_Sub34)this.aClass148_6115.method2421(6494)).anInt4126;
                if (~n2 == 0xFFFFFC17) {
                    OpenGL.glDeleteFramebuffersEXT(n2, Class294.anIntArray2408, 0);
                    n2 = 0;
                }
            }
            if (n2 > 0) {
                OpenGL.glDeleteFramebuffersEXT(n2, Class294.anIntArray2408, 0);
                n2 = 0;
            }
            while (!this.aClass148_6116.method2420(-124)) {
                final Class98_Sub34 class98_Sub36 = (Class98_Sub34)this.aClass148_6116.method2421(6494);
                Class294.anIntArray2408[n2++] = (int)class98_Sub36.aLong832;
                super.anInt4539 -= class98_Sub36.anInt4126;
                if (n2 == 1000) {
                    OpenGL.glDeleteRenderbuffersEXT(n2, Class294.anIntArray2408, 0);
                    n2 = 0;
                }
            }
            if (~n2 < -1) {
                OpenGL.glDeleteRenderbuffersEXT(n2, Class294.anIntArray2408, 0);
            }
            while (!this.aClass148_6112.method2420(-127)) {
                final Class98_Sub34 class98_Sub37 = (Class98_Sub34)this.aClass148_6112.method2421(6494);
                OpenGL.glDeleteLists((int)class98_Sub37.aLong832, class98_Sub37.anInt4126);
            }
            while (!this.aClass148_6117.method2420(-128)) {
                OpenGL.glDeleteProgramARB((int)this.aClass148_6117.method2421(6494).aLong832);
            }
            while (!this.aClass148_6118.method2420(-125)) {
                OpenGL.glDeleteObjectARB(this.aClass148_6118.method2421(6494).aLong832);
            }
            while (!this.aClass148_6112.method2420(-125)) {
                final Class98_Sub34 class98_Sub38 = (Class98_Sub34)this.aClass148_6112.method2421(6494);
                OpenGL.glDeleteLists((int)class98_Sub38.aLong832, class98_Sub38.anInt4126);
            }
            if (this.E() > 100663296 && this.aLong6127 + 60000L < Class343.method3819(-47)) {
                System.gc();
                this.aLong6127 = Class343.method3819(-47);
            }
            super.method1806(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.UB(" + n + ')');
        }
    }
    
    @Override
    final void method1955(final int n) {
        try {
            Label_0035: {
                if (!super.aBoolean4643 || super.aBoolean4637) {
                    OpenGL.glDisable(2896);
                    if (!client.aBoolean3553) {
                        break Label_0035;
                    }
                }
                OpenGL.glEnable(2896);
            }
            if (n != -5668) {
                this.method2030((byte)(-122));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.AC(" + n + ')');
        }
    }
    
    @Override
    final void method1966(final byte b) {
        try {
            OpenGL.glMatrixMode(5889);
            OpenGL.glLoadMatrixf(super.aFloatArray4566, 0);
            OpenGL.glMatrixMode(5888);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.LC(" + b + ')');
        }
    }
    
    @Override
    final Interface2_Impl1 method2060(final boolean b, final int n) {
        try {
            if (n < 40) {
                return null;
            }
            return new Class69_Sub2(this, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.AD(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final Interface2_Impl2 method1990(final byte b, final boolean b2) {
        try {
            if (b != 84) {
                this.aClass69_Sub2Array6124 = null;
            }
            return new Class69_Sub1(this, Class162.aClass162_1267, b2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.KD(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method1825() {
        try {
            OpenGL.glFinish();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.TC()");
        }
    }
    
    @Override
    final void a(final Rectangle[] array, final int n, final int n2, final int n3) throws Exception_Sub1 {
        try {
            this.method1764(n2, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.DD(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method1761(final boolean b) {
    }
    
    @Override
    final Interface13 method1744(final int n, final int n2) {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.SB(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final boolean method1942(final int n, final Class164 class164, final Class162 class165) {
        try {
            if (n != 0) {
                this.method1988(null, null, 89);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.CC(" + n + ',' + ((class164 != null) ? "{...}" : "null") + ',' + ((class165 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1972(final int n) {
        try {
            OpenGL.glDepthMask(super.aBoolean4606 && super.aBoolean4620);
            if (n != 0) {
                this.aMapBuffer6125 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.DB(" + n + ')');
        }
    }
    
    final synchronized void method2083(final int n, final int n2, final int n3) {
        try {
            final Class98_Sub34 class98_Sub34 = new Class98_Sub34(n3);
            class98_Sub34.aLong832 = n2;
            if (n <= -4) {
                this.aClass148_6114.method2419(class98_Sub34, -20911);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.CA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method1983(final byte b) {
        try {
            this.method2081((byte)(-89));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.PB(" + b + ')');
        }
    }
    
    @Override
    final void method2047(final int n, final boolean b, final byte b2, final Class65 class65) {
        try {
            OpenGL.glTexEnvi(8960, n + 34184, Class98_Sub12.method1128(class65, 97));
            OpenGL.glTexEnvi(8960, 34200 - -n, b ? 771 : 770);
            if (b2 != -42) {
                this.aBoolean6138 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.B(" + n + ',' + b + ',' + b2 + ',' + ((class65 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final Interface4_Impl2 method2063(final int n, final byte b, final int[] array, final boolean b2, final int n2, final int n3, final int n4) {
        try {
            if (this.aBoolean6130 || (Class81.method815(n2, 0) && Class81.method815(n3, 0))) {
                return new Class21_Sub1(this, n2, n3, b2, array, n, n4);
            }
            if (!this.aBoolean6138) {
                final Class21_Sub1 class21_Sub1 = new Class21_Sub1(this, Class62.aClass164_486, Class162.aClass162_1266, Class48.method453(423660257, n2), Class48.method453(423660257, n3));
                class21_Sub1.method49(17779, 0, n3, array, 0, n, n4, n2);
                return class21_Sub1;
            }
            return new Class21_Sub3(this, n2, n3, array, n, n4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.QB(" + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + b2 + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method1776() {
    }
    
    @Override
    final void method2046(final int n) {
        try {
            Label_0028: {
                if (super.aBoolean4599) {
                    OpenGL.glEnable(3008);
                    if (!client.aBoolean3553) {
                        break Label_0028;
                    }
                }
                OpenGL.glDisable(3008);
            }
            if (n != 0) {
                this.aClass148_6117 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.TA(" + n + ')');
        }
    }
    
    @Override
    final Interface5 method1813(final int n, final int n2) {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.GD(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final Interface4_Impl2 method2032(final byte[] array, final boolean b, final Class164 class164, final int n, final int n2, final boolean b2, final int n3, final int n4) {
        try {
            if (b) {
                this.anInt6132 = -43;
            }
            if (this.aBoolean6130 || (Class81.method815(n4, 0) && Class81.method815(n2, 0))) {
                return new Class21_Sub1(this, class164, n4, n2, b2, array, n3, n);
            }
            if (!this.aBoolean6138) {
                final Class21_Sub1 class21_Sub1 = new Class21_Sub1(this, class164, Class162.aClass162_1266, Class48.method453(423660257, n4), Class48.method453(423660257, n2));
                class21_Sub1.method41(n, n3, n4, 0, n2, -26946, array, class164, 0);
                return class21_Sub1;
            }
            return new Class21_Sub3(this, class164, n4, n2, array, n3, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.FC(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + ((class164 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + b2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method1974(final int n) {
        try {
            if (n == 0) {
                if (!super.aBoolean4577) {
                    OpenGL.glDisable(2929);
                }
                else {
                    OpenGL.glEnable(2929);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.PA(" + n + ')');
        }
    }
    
    @Override
    final Interface4_Impl1 method2044(final int n, final Class164 class164, final byte[] array, final int n2, final int n3, final int n4) {
        try {
            return new Class21_Sub4(this, class164, n2, n3, n4, array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.OB(" + n + ',' + ((class164 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method1814() {
    }
    
    @Override
    final void method1964(final Class38 class38, final byte b) {
        try {
            if (b != 26) {
                this.aBoolean6130 = false;
            }
            if (class38 == r_Sub2.aClass38_6334) {
                OpenGL.glDisable(3168);
                OpenGL.glDisable(3169);
                OpenGL.glDisable(3170);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            final int method3678 = Class323.method3678((byte)115, class38);
            OpenGL.glTexGeni(8192, 9472, method3678);
            OpenGL.glEnable(3168);
            OpenGL.glTexGeni(8193, 9472, method3678);
            OpenGL.glEnable(3169);
            OpenGL.glTexGeni(8194, 9472, method3678);
            OpenGL.glEnable(3170);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.ND(" + ((class38 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method2042(final Class256 class256, final byte b) {
        try {
            final Class49[] aClass49Array5159 = ((Class256_Sub1)class256).aClass49Array5159;
            int n = 0;
            boolean aBoolean6119 = false;
            boolean aBoolean6120 = false;
            boolean aBoolean6121 = false;
            for (int n2 = 0; ~aClass49Array5159.length < ~n2; ++n2) {
                final Class49 class257 = aClass49Array5159[n2];
                final Class69_Sub2 class69_Sub2 = this.aClass69_Sub2Array6124[n2];
                int n3 = 0;
                final int method704 = class69_Sub2.method704((byte)(-22));
                final long method705 = class69_Sub2.method695(-30277);
                class69_Sub2.method698(18569);
                for (int n4 = 0; class257.method480((byte)(-99)) > n4; ++n4) {
                    final Class169 method706 = class257.method479(n4, (byte)(-122));
                    if (method706 != Class169.aClass169_1294) {
                        if (method706 == Class169.aClass169_1297) {
                            aBoolean6120 = true;
                            OpenGL.glNormalPointer(5126, method704, method705 - -n3);
                        }
                        else if (Class169.aClass169_1298 == method706) {
                            aBoolean6119 = true;
                            OpenGL.glColorPointer(4, 5121, method704, method705 + n3);
                        }
                        else if (Class169.aClass169_1299 == method706) {
                            OpenGL.glClientActiveTexture(n++ + 33984);
                            OpenGL.glTexCoordPointer(1, 5126, method704, method705 - -n3);
                        }
                        else if (Class169.aClass169_1301 == method706) {
                            OpenGL.glClientActiveTexture(33984 + n++);
                            OpenGL.glTexCoordPointer(2, 5126, method704, method705 - -n3);
                        }
                        else if (method706 != Class169.aClass169_1302) {
                            if (method706 == Class169.aClass169_1303) {
                                OpenGL.glClientActiveTexture(33984 + n++);
                                OpenGL.glTexCoordPointer(4, 5126, method704, n3 + method705);
                            }
                        }
                        else {
                            OpenGL.glClientActiveTexture(33984 - -(n++));
                            OpenGL.glTexCoordPointer(3, 5126, method704, method705 - -n3);
                        }
                    }
                    else {
                        aBoolean6121 = true;
                        OpenGL.glVertexPointer(3, 5126, method704, method705 + n3);
                    }
                    n3 += method706.anInt1295;
                }
            }
            if (aBoolean6121 != this.aBoolean6121) {
                if (!aBoolean6121) {
                    OpenGL.glDisableClientState(32884);
                }
                else {
                    OpenGL.glEnableClientState(32884);
                }
                this.aBoolean6121 = aBoolean6121;
            }
            if (!this.aBoolean6123 != !aBoolean6120) {
                if (aBoolean6120) {
                    OpenGL.glEnableClientState(32885);
                }
                else {
                    OpenGL.glDisableClientState(32885);
                }
                this.aBoolean6123 = aBoolean6120;
            }
            if (this.aBoolean6119 == !aBoolean6119) {
                if (!aBoolean6119) {
                    OpenGL.glDisableClientState(32886);
                }
                else {
                    OpenGL.glEnableClientState(32886);
                }
                this.aBoolean6119 = aBoolean6119;
            }
            if (~this.anInt6126 <= ~n) {
                if (this.anInt6126 > n) {
                    for (int i = n; i < this.anInt6126; ++i) {
                        OpenGL.glClientActiveTexture(33984 - -i);
                        OpenGL.glDisableClientState(32888);
                    }
                    this.anInt6126 = n;
                }
            }
            else {
                for (int anInt6126 = this.anInt6126; ~anInt6126 > ~n; ++anInt6126) {
                    OpenGL.glClientActiveTexture(anInt6126 + 33984);
                    OpenGL.glEnableClientState(32888);
                }
                this.anInt6126 = n;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.JD(" + ((class256 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method1958(final byte b) {
        try {
            OpenGL.glTexEnvi(8960, 34162, Class5.method178(super.aClass128Array4639[super.anInt4579], 0));
            if (b != -48) {
                this.method2065((byte)(-18));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.KC(" + b + ')');
        }
    }
    
    @Override
    final void method1817() {
        try {
            if (~super.anInt4527 < -1 || super.anInt4531 > 0) {
                final int anInt4602 = super.anInt4602;
                final int anInt4603 = super.anInt4575;
                final int anInt4604 = super.anInt4558;
                final int anInt4605 = super.anInt4638;
                this.la();
                OpenGL.glReadBuffer(1028);
                OpenGL.glDrawBuffer(1029);
                this.method1992((byte)125);
                this.method2028(false, (byte)(-101));
                this.method1979(false, -60);
                this.method2013(false, 2103);
                this.method1997(0, false);
                this.method2005(null, 127);
                this.method2039(false, 0, -2, false);
                this.method2015(1, (byte)(-120));
                this.method2001(0, 79);
                OpenGL.glMatrixMode(5889);
                OpenGL.glLoadIdentity();
                OpenGL.glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
                OpenGL.glMatrixMode(5888);
                OpenGL.glLoadIdentity();
                OpenGL.glRasterPos2i(0, 0);
                OpenGL.glCopyPixels(0, 0, super.anInt4527, super.anInt4531, 6144);
                OpenGL.glFlush();
                OpenGL.glReadBuffer(1029);
                OpenGL.glDrawBuffer(1029);
                this.KA(anInt4602, anInt4604, anInt4603, anInt4605);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.O()");
        }
    }
    
    @Override
    final void method1973(final Class232 class232, final int n, final int n2, final int n3, final Interface2_Impl2 interface2_Impl2, final int n4, final int n5) {
        try {
            if (n3 == 26810) {
                int n6;
                int n7;
                if (Class334.aClass232_3468 == class232) {
                    n6 = n5 * 2;
                    n7 = 1;
                }
                else if (Class287_Sub1.aClass232_3420 != class232) {
                    if (Class336.aClass232_2822 == class232) {
                        n7 = 4;
                        n6 = n5 * 3;
                    }
                    else if (Class98_Sub46_Sub15.aClass232_6043 == class232) {
                        n7 = 6;
                        n6 = 2 + n5;
                    }
                    else if (Class97.aClass232_806 == class232) {
                        n7 = 5;
                        n6 = 2 + n5;
                    }
                    else {
                        n6 = n5;
                        n7 = 0;
                    }
                }
                else {
                    n6 = n5 + 1;
                    n7 = 3;
                }
                final Class162 method77 = interface2_Impl2.method77(-15448);
                final Class69_Sub1 class69_Sub1 = (Class69_Sub1)interface2_Impl2;
                class69_Sub1.method698(18569);
                OpenGL.glDrawElements(n7, n6, Class98_Sub5_Sub1.method964(method77, (byte)105), class69_Sub1.method695(-30277) - -(n2 * method77.anInt1263));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.SD(" + ((class232 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + ((interface2_Impl2 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final Class48 method1769(final Class48 class48, final Class48 class49, final float n, final Class48 class50) {
        try {
            if (n >= 0.5f) {
                return class49;
            }
            return class48;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.UC(" + ((class48 != null) ? "{...}" : "null") + ',' + ((class49 != null) ? "{...}" : "null") + ',' + n + ',' + ((class50 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2011(final int n) {
        try {
            OpenGL.glTexEnvi(8960, 34161, Class5.method178(super.aClass128Array4585[super.anInt4579], 0));
            if (n != 2) {
                this.GA(-98);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.GB(" + n + ')');
        }
    }
    
    @Override
    final Interface4_Impl2 method1968(final int n, final int n2, final boolean b, final Class164 class164, final int n3, final int n4, final int n5, final float[] array) {
        try {
            if (this.aBoolean6130 || (Class81.method815(n2, 0) && Class81.method815(n4, 0))) {
                return new Class21_Sub1(this, class164, n2, n4, b, array, n, n5);
            }
            if (this.aBoolean6138) {
                return new Class21_Sub3(this, class164, n2, n4, array, n, n5);
            }
            final Class21_Sub1 class21_Sub1 = new Class21_Sub1(this, class164, Class162.aClass162_1270, Class48.method453(423660257, n2), Class48.method453(423660257, n4));
            class21_Sub1.method267(array, class164, n4, n2, (byte)111, 0, n5, 0, n);
            return class21_Sub1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.HC(" + n + ',' + n2 + ',' + b + ',' + ((class164 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2057(final int n) {
        try {
            Class98_Sub39.aFloatArray4189[2] = Class202.method2702(super.anInt4648, 255) / 255.0f;
            Class98_Sub39.aFloatArray4189[1] = Class202.method2702(65280, super.anInt4648) / 65280.0f;
            if (n != 12362) {
                this.aLong6127 = 41L;
            }
            Class98_Sub39.aFloatArray4189[0] = Class202.method2702(16711680, super.anInt4648) / 1.671168E7f;
            Class98_Sub39.aFloatArray4189[3] = (super.anInt4648 >>> 1318505624) / 255.0f;
            OpenGL.glTexEnvfv(8960, 8705, Class98_Sub39.aFloatArray4189, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.TB(" + n + ')');
        }
    }
    
    @Override
    final Class256 method1982(final Class49[] array, final int n) {
        try {
            if (n != 6) {
                this.method2036(59);
            }
            return new Class256_Sub1(array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.RC(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void method1950(final byte b) {
        try {
            OpenGL.glActiveTexture(super.anInt4579 + 33984);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.IB(" + b + ')');
        }
    }
    
    @Override
    final void method2004(final byte b) {
        try {
            Class98_Sub39.aFloatArray4189[1] = super.aFloat4549 * super.aFloat4630;
            Class98_Sub39.aFloatArray4189[3] = 1.0f;
            Class98_Sub39.aFloatArray4189[0] = super.aFloat4630 * super.aFloat4611;
            Class98_Sub39.aFloatArray4189[2] = super.aFloat4630 * super.aFloat4591;
            OpenGL.glLightfv(16384, 4609, Class98_Sub39.aFloatArray4189, 0);
            Class98_Sub39.aFloatArray4189[3] = 1.0f;
            Class98_Sub39.aFloatArray4189[1] = -super.aFloat4594 * super.aFloat4549;
            Class98_Sub39.aFloatArray4189[2] = -super.aFloat4594 * super.aFloat4591;
            Class98_Sub39.aFloatArray4189[0] = super.aFloat4611 * -super.aFloat4594;
            OpenGL.glLightfv(16385, 4609, Class98_Sub39.aFloatArray4189, 0);
            if (b > -98) {
                this.aBoolean6138 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.QC(" + b + ')');
        }
    }
    
    @Override
    final void method1936(final int n, final Object o, final Canvas canvas) {
        try {
            if (n != 0) {
                this.aBoolean6120 = false;
            }
            this.anOpenGL6111.releaseSurface(canvas, (long)o);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.JC(" + n + ',' + ((o != null) ? "{...}" : "null") + ',' + ((canvas != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2021(final int n) {
        try {
            if (n != 0) {
                this.method2021(-94);
            }
            this.aBoolean6122 = false;
            this.method2081((byte)119);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.G(" + n + ')');
        }
    }
    
    @Override
    final void method2065(final byte b) {
        try {
            OpenGL.glLightfv(16384, 4611, super.aFloatArray4596, 0);
            if (b == 80) {
                OpenGL.glLightfv(16385, 4611, super.aFloatArray4572, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.LB(" + b + ')');
        }
    }
    
    @Override
    final void method1959(final int n) {
        try {
            final int n2 = this.anIntArray6139[super.anInt4579];
            if (n2 != n) {
                OpenGL.glBindTexture(n2, this.anIntArray6139[super.anInt4579] = 0);
                OpenGL.glDisable(n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.MD(" + n + ')');
        }
    }
    
    @Override
    final void method1740(final Interface17 interface17) {
    }
    
    final synchronized void method2084(final int n, final int n2, final int n3) {
        try {
            if (n != 1) {
                this.method1746(-36, 50, 45, 78);
            }
            final Class98_Sub34 class98_Sub34 = new Class98_Sub34(n2);
            class98_Sub34.aLong832 = n3;
            this.aClass148_6113.method2419(class98_Sub34, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.FA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method1989(final int n) {
    }
    
    @Override
    final void ya() {
        try {
            this.method1997(0, true);
            OpenGL.glClear(256);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.ya()");
        }
    }
    
    ha_Sub3_Sub2(final OpenGL anOpenGL6111, final Canvas canvas, final long n, final d d, final Class207 class207, final int n2) {
        super(canvas, new Long(n), d, class207, n2, 1);
        this.aClass148_6112 = new Class148();
        this.aClass148_6113 = new Class148();
        this.aClass148_6114 = new Class148();
        this.aClass148_6115 = new Class148();
        this.aClass148_6116 = new Class148();
        this.aClass148_6117 = new Class148();
        this.aClass148_6118 = new Class148();
        this.aClass69_Sub2Array6124 = new Class69_Sub2[16];
        this.aMapBuffer6125 = new MapBuffer();
        this.aMapBuffer6128 = new MapBuffer();
        this.anInt6132 = 0;
        try {
            try {
                (this.anOpenGL6111 = anOpenGL6111).b();
                this.aString6140 = OpenGL.glGetString(7936).toLowerCase();
                this.aString6131 = OpenGL.glGetString(7937).toLowerCase();
                if (~this.aString6140.indexOf("microsoft") != 0x0 || this.aString6140.indexOf("brian paul") != -1 || this.aString6140.indexOf("mesa") != -1) {
                    throw new RuntimeException("");
                }
                final String[] method2142 = Class112.method2142(OpenGL.glGetString(7938).replace('.', ' '), ' ', false);
                if (~method2142.length <= -3) {
                    Label_0322: {
                        try {
                            this.anInt6129 = PacketSender.method3607(73, method2142[1]) + 10 * PacketSender.method3607(-52, method2142[0]);
                            break Label_0322;
                        }
                        catch (NumberFormatException ex2) {
                            throw new RuntimeException("");
                        }
                        throw new RuntimeException("");
                    }
                    if (~this.anInt6129 > -13) {
                        throw new RuntimeException("");
                    }
                    if (!this.anOpenGL6111.a("GL_ARB_multitexture")) {
                        throw new RuntimeException("");
                    }
                    if (!this.anOpenGL6111.a("GL_ARB_texture_env_combine")) {
                        throw new RuntimeException("");
                    }
                    final int[] array = { 0 };
                    OpenGL.glGetIntegerv(34018, array, 0);
                    super.anInt4608 = array[0];
                    if (super.anInt4608 < 2) {
                        throw new RuntimeException("");
                    }
                    super.anInt4565 = 8;
                    this.aBoolean6137 = this.anOpenGL6111.a("GL_ARB_vertex_buffer_object");
                    super.aBoolean4559 = this.anOpenGL6111.a("GL_ARB_multisample");
                    this.aBoolean6138 = this.anOpenGL6111.a("GL_ARB_texture_rectangle");
                    super.aBoolean4569 = this.anOpenGL6111.a("GL_ARB_texture_cube_map");
                    this.aBoolean6130 = this.anOpenGL6111.a("GL_ARB_texture_non_power_of_two");
                    super.aBoolean4588 = this.anOpenGL6111.a("GL_EXT_texture3D");
                    this.aBoolean6133 = this.anOpenGL6111.a("GL_ARB_vertex_shader");
                    this.aBoolean6134 = this.anOpenGL6111.a("GL_ARB_vertex_program");
                    this.aBoolean6136 = this.anOpenGL6111.a("GL_ARB_fragment_shader");
                    this.anOpenGL6111.a("GL_ARB_fragment_program");
                    this.anIntArray6139 = new int[super.anInt4608];
                    this.anInt6135 = (Stream.a() ? 33639 : 5121);
                    if (this.aString6131.indexOf("radeon") != -1) {
                        int method2143 = 0;
                        boolean b = false;
                        boolean b2 = false;
                        final String[] method2144 = Class112.method2142(this.aString6131.replace('/', ' '), ' ', false);
                        for (int n3 = 0; method2144.length > n3; ++n3) {
                            String s = method2144[n3];
                            try {
                                if (s.length() > 0) {
                                    if (s.charAt(0) == 'x' && ~s.length() <= -4 && Class77_Sub1.method781((byte)53, s.substring(1, 3))) {
                                        s = s.substring(1);
                                        b2 = true;
                                    }
                                    if (s.equals("hd")) {
                                        b = true;
                                    }
                                    else {
                                        if (s.startsWith("hd")) {
                                            s = s.substring(2);
                                            b = true;
                                        }
                                        if (~s.length() <= -5 && Class77_Sub1.method781((byte)53, s.substring(0, 4))) {
                                            method2143 = PacketSender.method3607(87, s.substring(0, 4));
                                            break;
                                        }
                                    }
                                }
                            }
                            catch (Exception ex3) {}
                        }
                        if (!b2 && !b) {
                            if (method2143 >= 7000 && method2143 <= 7999) {
                                this.aBoolean6137 = false;
                            }
                            if (method2143 >= 7000 && method2143 <= 9250) {
                                super.aBoolean4588 = false;
                            }
                        }
                        this.aBoolean6138 &= this.anOpenGL6111.a("GL_ARB_half_float_pixel");
                    }
                    this.aString6140.indexOf("intel");
                    if (this.aBoolean6137) {
                        try {
                            OpenGL.glGenBuffersARB(1, new int[1], 0);
                        }
                        catch (Throwable t2) {
                            throw new RuntimeException("");
                        }
                    }
                    return;
                }
                throw new RuntimeException("");
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.method1743(-1);
                throw new RuntimeException("");
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.<init>(" + ((anOpenGL6111 != null) ? "{...}" : "null") + ',' + ((canvas != null) ? "{...}" : "null") + ',' + n + ',' + ((d != null) ? "{...}" : "null") + ',' + ((class207 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    @Override
    final Interface17 method1815(final Interface5 interface5, final Interface13 interface6) {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.EC(" + ((interface5 != null) ? "{...}" : "null") + ',' + ((interface6 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method1977(final Class162 class162, final boolean b, final Class164 class163) {
        try {
            if (!b) {
                this.anInt6132 = -89;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.BD(" + ((class162 != null) ? "{...}" : "null") + ',' + b + ',' + ((class163 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method1802() {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.FB()");
        }
    }
    
    @Override
    final void method1991(final int n) {
        try {
            Class98_Sub39.aFloatArray4189[2] = super.aFloat4576 * super.aFloat4591;
            Class98_Sub39.aFloatArray4189[3] = 1.0f;
            Class98_Sub39.aFloatArray4189[1] = super.aFloat4549 * super.aFloat4576;
            Class98_Sub39.aFloatArray4189[0] = super.aFloat4611 * super.aFloat4576;
            if (n != -24391) {
                this.I();
            }
            OpenGL.glLightModelfv(2899, Class98_Sub39.aFloatArray4189, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.HD(" + n + ')');
        }
    }
    
    @Override
    final Interface4_Impl2 method2006(final int n, final int n2, final Class164 class164, final byte b, final Class162 class165) {
        try {
            if (b != 45) {
                return null;
            }
            if (this.aBoolean6130 || (Class81.method815(n2, 0) && Class81.method815(n, 0))) {
                return new Class21_Sub1(this, class164, class165, n2, n);
            }
            if (this.aBoolean6138) {
                return new Class21_Sub3(this, class164, class165, n2, n);
            }
            return new Class21_Sub1(this, class164, class165, Class48.method453(423660257, n2), Class48.method453(423660257, n));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.WC(" + n + ',' + n2 + ',' + ((class164 != null) ? "{...}" : "null") + ',' + b + ',' + ((class165 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1941(final int n) {
        try {
            for (int n2 = super.anInt4608 - 1; ~n2 <= -1; --n2) {
                OpenGL.glActiveTexture(n2 + 33984);
                OpenGL.glTexEnvi(8960, 8704, 34160);
                OpenGL.glTexEnvi(8960, 34161, 8448);
                OpenGL.glTexEnvi(8960, 34178, 34166);
                OpenGL.glTexEnvi(8960, 34162, 8448);
                OpenGL.glTexEnvi(8960, 34186, 34166);
            }
            OpenGL.glTexEnvi(8960, 34186, 34168);
            OpenGL.glShadeModel(7425);
            OpenGL.glClearDepth(1.0f);
            OpenGL.glDepthFunc(515);
            OpenGL.glPolygonMode(1028, 6914);
            OpenGL.glEnable(2884);
            OpenGL.glCullFace(1029);
            OpenGL.glAlphaFunc(516, 0.0f);
            OpenGL.glMatrixMode(5888);
            OpenGL.glLoadIdentity();
            OpenGL.glColorMaterial(1028, 5634);
            OpenGL.glEnable(2903);
            final float[] array = { 0.0f, 0.0f, 0.0f, 1.0f };
            for (int i = 0; i < 8; ++i) {
                final int n3 = i + 16384;
                OpenGL.glLightfv(n3, 4608, array, 0);
                OpenGL.glLightf(n3, 4615, 0.0f);
                OpenGL.glLightf(n3, 4616, 0.0f);
            }
            OpenGL.glFogf(2914, 0.95f);
            OpenGL.glFogi(2917, 9729);
            OpenGL.glHint(3156, 4353);
            this.anOpenGL6111.setSwapInterval(0);
            super.method1941(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.OC(" + n + ')');
        }
    }
    
    @Override
    final float method2050(final byte b) {
        try {
            if (b != 56) {
                return -1.1192888f;
            }
            return 0.0f;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.NC(" + b + ')');
        }
    }
    
    @Override
    final Interface4_Impl3 method1934(final int n, final boolean b, final int[][] array, final int n2) {
        try {
            if (n != 8) {
                this.aClass148_6115 = null;
            }
            return new Class21_Sub2(this, n2, b, array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.GC(" + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2035(final int n) {
        try {
            this.method1960(n ^ 0xFFFFF0F8);
            int n2;
            for (n2 = 0; super.anInt4619 > n2; ++n2) {
                final Class98_Sub5 class98_Sub5 = super.aClass98_Sub5Array4597[n2];
                final int method961 = class98_Sub5.method961((byte)(-78));
                final int n3 = 16386 - -n2;
                final float n4 = class98_Sub5.method956(false) / 255.0f;
                Class98_Sub39.aFloatArray4189[0] = class98_Sub5.method954(Class369.method3953(n, -8724));
                Class98_Sub39.aFloatArray4189[1] = class98_Sub5.method963((byte)84);
                Class98_Sub39.aFloatArray4189[2] = class98_Sub5.method962(n + 43412);
                Class98_Sub39.aFloatArray4189[3] = 1.0f;
                OpenGL.glLightfv(n3, 4611, Class98_Sub39.aFloatArray4189, 0);
                Class98_Sub39.aFloatArray4189[2] = n4 * Class202.method2702(255, method961);
                Class98_Sub39.aFloatArray4189[1] = n4 * Class202.method2702(method961 >> -989028888, 255);
                Class98_Sub39.aFloatArray4189[3] = 1.0f;
                Class98_Sub39.aFloatArray4189[0] = Class202.method2702(255, method961 >> 444887952) * n4;
                OpenGL.glLightfv(n3, 4609, Class98_Sub39.aFloatArray4189, 0);
                OpenGL.glLightf(n3, 4617, 1.0f / (class98_Sub5.method958(n + 14840) * class98_Sub5.method958(126)));
                OpenGL.glEnable(n3);
            }
            while (~super.anInt4628 < ~n2) {
                OpenGL.glDisable(16386 - -n2);
                ++n2;
            }
            super.method2035(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.BC(" + n + ')');
        }
    }
    
    @Override
    final void method1773() {
        try {
            super.method1773();
            if (this.anOpenGL6111 != null) {
                this.anOpenGL6111.a();
                this.anOpenGL6111.release();
                this.anOpenGL6111 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.AB()");
        }
    }
    
    @Override
    final void method1971(final int n, final boolean b, final Interface2_Impl1 interface2_Impl1) {
        try {
            if (b) {
                this.aClass69_Sub2Array6124[n] = (Class69_Sub2)interface2_Impl1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.R(" + n + ',' + b + ',' + ((interface2_Impl1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1756() {
    }
    
    @Override
    final void method1988(final Canvas canvas, final Object o, final int n) {
        try {
            if (n >= -40) {
                this.method2025((byte)(-19));
            }
            this.anOpenGL6111.surfaceResized((long)o);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.VC(" + ((canvas != null) ? "{...}" : "null") + ',' + ((o != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method1768() {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.CB()");
        }
    }
    
    final synchronized void method2085(final boolean b, final int n) {
        try {
            if (!b) {
                this.aBoolean6133 = false;
            }
            final Class98 class98 = new Class98();
            class98.aLong832 = n;
            this.aClass148_6117.method2419(class98, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.W(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2010(final int n) {
        try {
            OpenGL.glMatrixMode(5890);
            Label_0054: {
                if (Class98_Sub46_Sub19.aClass258_6062 == super.aClass258Array4644[super.anInt4579]) {
                    OpenGL.glLoadIdentity();
                    if (!client.aBoolean3553) {
                        break Label_0054;
                    }
                }
                OpenGL.glLoadMatrixf(super.aClass111_Sub3Array4609[super.anInt4579].method2121((byte)(-58), Class98_Sub30.aFloatArray4096), 0);
            }
            OpenGL.glMatrixMode(5888);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.MB(" + n + ')');
        }
    }
    
    @Override
    final void method1944(final Object o, final Canvas canvas, final byte b) {
        try {
            if (b == -34 && !this.anOpenGL6111.setSurface((long)o)) {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hl.NB(" + ((o != null) ? "{...}" : "null") + ',' + ((canvas != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
}
