// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import ji.io.h;
import java.io.IOException;
import netscape.security.PrivilegeManager;
import ji.util.d;
import ji.util.e;
import ji.util.i;
import java.io.RandomAccessFile;
import java.io.DataInput;
import java.io.DataOutput;

public class au implements DataOutput, DataInput
{
    RandomAccessFile a;
    boolean b;
    boolean c;
    av d;
    String e;
    String f;
    boolean g;
    boolean h;
    
    private final void f() {
        if (!this.c) {
            this.b = (ji.sec.g.a() && !ji.sec.g.b());
        }
    }
    
    private final void a(final String s, final String s2, final String s3) throws Exception {
        try {
            if (!this.h && i.c(185) && i.c(198) && this.d == null && ji.util.e.u(s3) && ji.util.d.a0(s3)) {
                this.d = new av(s, s2, s3);
                if (!this.d.a()) {
                    this.d.h();
                    this.d = null;
                }
                else {
                    this.c = true;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.c = false;
            this.h = true;
            this.d = null;
        }
    }
    
    public final boolean a() {
        return this.c;
    }
    
    public au(final String f, final String s, final String s2, final boolean b) throws Exception {
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.f = f;
        if (b) {
            this.a(f, s, s2);
        }
        if (!this.c) {
            this.f();
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a = new RandomAccessFile(f, s);
        }
    }
    
    public final int b() throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return this.d.b();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.read();
    }
    
    public final int a(final int[] array, final int n, final int n2, final boolean b) throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return this.d.b(array, n, n2, b);
        }
        ji.io.h.d(this.e, "Ints reading not support directly by streamer");
        return 0;
    }
    
    public final int a(final byte[] array, final int n, final int n2) throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return this.d.a(array, n, n2);
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.read(array, n, n2);
    }
    
    public final int a(final byte[] array) throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return this.d.a(array, 0, array.length);
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.read(array);
    }
    
    public final void readFully(final byte[] array) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(array, 0, array.length);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.readFully(array);
        }
    }
    
    public final void readFully(final byte[] array, final int n, final int n2) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(array, n, n2);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.readFully(array, n, n2);
        }
    }
    
    public final int skipBytes(final int n) throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            final long g = this.d.g();
            this.d.b(g + n);
            return (int)(this.d.g() - g);
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.skipBytes(n);
    }
    
    public final void write(final int n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(n);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.write(n);
        }
    }
    
    public final void write(final byte[] array) throws IOException {
        if (this.g) {
            return;
        }
        this.a(array, false);
    }
    
    public final void a(final byte[] array, final boolean b) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(array, 0, array.length, b);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.write(array);
        }
    }
    
    public final void b(final int[] array, final int n, final int n2, final boolean b) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(array, n, n2, b);
        }
        else {
            ji.io.h.d(this.e, "Ints writing not support directly by streamer");
        }
    }
    
    public final void write(final byte[] array, final int n, final int n2) throws IOException {
        if (this.g) {
            return;
        }
        this.a(array, n, n2, false);
    }
    
    public final void a(final byte[] array, final int n, final int n2, final boolean b) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(array, n, n2, b);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.write(array, n, n2);
        }
    }
    
    public final long c() throws IOException {
        if (this.g) {
            return 0L;
        }
        if (this.c) {
            return this.d.g();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.getFilePointer();
    }
    
    public final void a(final long n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.b(n);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.seek(n);
        }
    }
    
    public final long d() throws IOException {
        if (this.g) {
            return 0L;
        }
        if (this.c) {
            return this.d.f();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.length();
    }
    
    public final void e() throws IOException {
        try {
            this.g = true;
            if (this.c && this.d != null) {
                this.d.h();
                this.f = null;
                this.e = null;
            }
            else if (this.a != null) {
                try {
                    if (this.b && !ji.sec.g.r) {
                        PrivilegeManager.enablePrivilege("Netcaster");
                    }
                }
                catch (Exception ex2) {
                    ji.sec.g.r = true;
                }
                this.a.close();
                this.a = null;
                this.f = null;
                this.e = null;
            }
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        finally {
            this.g = false;
        }
    }
    
    public final boolean readBoolean() throws IOException {
        if (this.g) {
            return false;
        }
        if (this.c) {
            return this.d.b() == 1;
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readBoolean();
    }
    
    public final byte readByte() throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return (byte)this.d.b();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readByte();
    }
    
    public final int readUnsignedByte() throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return this.d.b();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readUnsignedByte();
    }
    
    public final short readShort() throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return (short)this.d.c();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readShort();
    }
    
    public final int readUnsignedShort() throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return this.d.c();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readUnsignedShort();
    }
    
    public final char readChar() throws IOException {
        if (this.g) {
            return '\0';
        }
        if (this.c) {
            return (char)(this.d.b() << 8 | this.d.b());
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readChar();
    }
    
    public final int readInt() throws IOException {
        if (this.g) {
            return 0;
        }
        if (this.c) {
            return this.d.d();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readInt();
    }
    
    public final long readLong() throws IOException {
        if (this.g) {
            return 0L;
        }
        if (this.c) {
            return this.d.e();
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readLong();
    }
    
    public final float readFloat() throws IOException {
        if (this.g) {
            return 0.0f;
        }
        if (this.c) {
            return Float.intBitsToFloat(this.d.d());
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readFloat();
    }
    
    public final double readDouble() throws IOException {
        if (this.g) {
            return 0.0;
        }
        if (this.c) {
            return Double.longBitsToDouble(this.d.e());
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readDouble();
    }
    
    public final String readLine() throws IOException {
        if (this.g) {
            return null;
        }
        if (!this.c) {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            return this.a.readLine();
        }
        final StringBuffer sb = new StringBuffer();
        int unsignedByte = -1;
        int i = 0;
        while (i == 0) {
            switch (unsignedByte = this.readUnsignedByte()) {
                case -1:
                case 10: {
                    i = 1;
                    continue;
                }
                case 13: {
                    i = 1;
                    final long c = this.c();
                    if (this.readUnsignedByte() != 10) {
                        this.a(c);
                        continue;
                    }
                    continue;
                }
                default: {
                    sb.append((char)unsignedByte);
                    continue;
                }
            }
        }
        if (unsignedByte == -1 && sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }
    
    public final String readUTF() throws IOException {
        if (this.g) {
            return null;
        }
        if (this.c) {
            final int c = this.d.c();
            final byte[] array = new byte[c];
            this.d.a(array, 0, c);
            return new String(array, "UTF8");
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        return this.a.readUTF();
    }
    
    public final void writeBoolean(final boolean b) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            if (b) {
                this.d.a(1);
            }
            else {
                this.d.a(0);
            }
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeBoolean(b);
        }
    }
    
    public final void writeByte(final int n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(n);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeByte(n);
        }
    }
    
    public final void writeShort(final int n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.b(n);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeShort(n);
        }
    }
    
    public final void writeChar(final int n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.b(n);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeChar(n);
        }
    }
    
    public final void writeInt(final int n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.c(n);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeInt(n);
        }
    }
    
    public final void writeLong(final long n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(n);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeLong(n);
        }
    }
    
    public final void writeFloat(final float n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.c(Float.floatToIntBits(n));
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeFloat(n);
        }
    }
    
    public final void writeDouble(final double n) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            this.d.a(Double.doubleToLongBits(n));
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeDouble(n);
        }
    }
    
    public final void writeBytes(final String s) throws IOException {
        if (this.g) {
            return;
        }
        this.a(s, false);
    }
    
    public final void a(final String s, final boolean b) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            final byte[] bytes = s.getBytes(ji.util.d.bm(this.e));
            this.d.a(bytes, 0, bytes.length, b);
            return;
        }
        try {
            if (this.b && !ji.sec.g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            ji.sec.g.r = true;
        }
        this.a.writeBytes(s);
    }
    
    public final void writeChars(final String s) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                this.writeChar(charArray[i]);
            }
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeChars(s);
        }
    }
    
    public final void writeUTF(final String s) throws IOException {
        if (this.g) {
            return;
        }
        this.b(s, false);
    }
    
    public final void b(final String s, final boolean b) throws IOException {
        if (this.g) {
            return;
        }
        if (this.c) {
            final byte[] bytes = s.getBytes("UTF8");
            this.d.b(bytes.length);
            this.d.a(bytes, 0, bytes.length, b);
        }
        else {
            try {
                if (this.b && !ji.sec.g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                ji.sec.g.r = true;
            }
            this.a.writeUTF(s);
        }
    }
    
    protected void finalize() throws IOException {
        this.e();
    }
}
