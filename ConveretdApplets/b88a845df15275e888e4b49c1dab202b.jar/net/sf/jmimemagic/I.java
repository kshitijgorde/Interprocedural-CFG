// 
// Decompiled by Procyon v0.5.30
// 

package net.sf.jmimemagic;

import jmaster.util.log.B;
import java.util.Iterator;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.util.Collection;
import java.util.ArrayList;
import jmaster.util.log.A;

public class I implements Cloneable
{
    private static A C;
    private ArrayList B;
    private F A;
    static /* synthetic */ Class class$net$sf$jmimemagic$I;
    
    public I() {
        this.B = new ArrayList(0);
        this.A = null;
        I.C.D("instantiated");
    }
    
    public void A(final F a) {
        I.C.D("setMatch()");
        this.A = a;
    }
    
    public F B() {
        I.C.D("getMatch()");
        return this.A;
    }
    
    public boolean A() {
        I.C.D("isValid()");
        if (this.A == null || this.A.I() == null) {
            return false;
        }
        final String s = new String(this.A.I().array());
        final char e = this.A.E();
        final String k = this.A.K();
        final String s2 = new String(this.A.I().array());
        return s != null && !s.equals("") && e != '\0' && (e == '=' || e == '!' || e == '>' || e == '<') && k != null && !k.equals("") && s2 != null && !s2.equals("");
    }
    
    public void A(final I i) {
        I.C.D("addSubMatcher()");
        this.B.add(i);
    }
    
    public void A(final Collection collection) {
        I.C.D("setSubMatchers(): for match '" + this.A.K() + "'");
        this.B.clear();
        this.B.addAll(collection);
    }
    
    public Collection C() {
        I.C.D("getSubMatchers()");
        return this.B;
    }
    
    public F A(final File file, final boolean b) throws IOException, G {
        I.C.D("test(File)");
        final int g = this.A.G();
        final String k = this.A.K();
        final String f = this.A.F();
        this.A.H();
        I.C.D("test(File): testing '" + file.getName() + "' for '" + k + "'");
        I.C.D("test(File): \n=== BEGIN MATCH INFO ==");
        I.C.D(this.A.C());
        I.C.D("test(File): \n=== END MATCH INFO ====\n");
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            int capacity;
            if (f.equals("byte")) {
                capacity = 1;
            }
            else if (f.equals("short") || f.equals("leshort") || f.equals("beshort")) {
                capacity = 4;
            }
            else if (f.equals("long") || f.equals("lelong") || f.equals("belong")) {
                capacity = 8;
            }
            else if (f.equals("string")) {
                capacity = this.A.I().capacity();
            }
            else if (f.equals("regex")) {
                capacity = (int)randomAccessFile.length() - g;
                if (capacity < 0) {
                    capacity = 0;
                }
            }
            else {
                if (!f.equals("detector")) {
                    throw new G("unsupported test type '" + f + "'");
                }
                capacity = (int)randomAccessFile.length() - g;
                if (capacity < 0) {
                    capacity = 0;
                }
            }
            if (capacity > randomAccessFile.length() - g) {
                return null;
            }
            final byte[] array = new byte[capacity];
            randomAccessFile.seek(g);
            int n = 0;
            int i = 0;
            while (i == 0) {
                final int read = randomAccessFile.read(array, 0, capacity - n);
                if (read == -1) {
                    throw new IOException("reached end of file before all bytes were read");
                }
                n += read;
                if (n != capacity) {
                    continue;
                }
                i = 1;
            }
            I.C.D("test(File): stream size is '" + array.length + "'");
            F b2 = null;
            if (this.A(array)) {
                b2 = this.B();
                I.C.D("test(File): testing matched '" + k + "'");
                if (!b && this.B != null && this.B.size() > 0) {
                    I.C.D("test(File): testing " + this.B.size() + " submatches for '" + k + "'");
                    for (int j = 0; j < this.B.size(); ++j) {
                        I.C.D("test(File): testing submatch " + j);
                        final F a;
                        if ((a = this.B.get(j).A(file, false)) != null) {
                            I.C.D("test(File): submatch " + j + " matched with '" + a.K() + "'");
                            b2.A(a);
                        }
                        else {
                            I.C.D("test(File): submatch " + j + " doesn't match");
                        }
                    }
                }
            }
            return b2;
        }
        finally {
            try {
                randomAccessFile.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public F A(final byte[] array, final boolean b) throws IOException, G {
        I.C.D("test(byte[])");
        final int g = this.A.G();
        final String k = this.A.K();
        final String f = this.A.F();
        final String s = new String(this.A.I().array());
        this.A.H();
        I.C.D("test(byte[]): testing byte[] data for '" + k + "'");
        I.C.D("test(byte[]): \n=== BEGIN MATCH INFO ==");
        I.C.D(this.A.C());
        I.C.D("test(byte[]): \n=== END MATCH INFO ====\n");
        int capacity;
        if (f.equals("byte")) {
            capacity = 1;
        }
        else if (f.equals("short") || f.equals("leshort") || f.equals("beshort")) {
            capacity = 4;
        }
        else if (f.equals("long") || f.equals("lelong") || f.equals("belong")) {
            capacity = 8;
        }
        else if (f.equals("string")) {
            capacity = this.A.I().capacity();
        }
        else if (f.equals("regex")) {
            capacity = array.length - g - 1;
            if (capacity < 0) {
                capacity = 0;
            }
        }
        else {
            if (!f.equals("detector")) {
                throw new G("unsupported test type " + f);
            }
            capacity = array.length - g - 1;
            if (capacity < 0) {
                capacity = 0;
            }
        }
        final byte[] array2 = new byte[capacity];
        I.C.D("test(byte[]): offset=" + g + ",length=" + capacity + ",data length=" + array.length);
        if (g + capacity < array.length) {
            System.arraycopy(array, g, array2, 0, capacity);
            I.C.D("test(byte[]): stream size is '" + array2.length + "'");
            F b2 = null;
            if (this.A(array2)) {
                b2 = this.B();
                I.C.D("test(byte[]): testing matched '" + k + "'");
                if (!b && this.B != null && this.B.size() > 0) {
                    I.C.D("test(byte[]): testing " + this.B.size() + " submatches for '" + k + "'");
                    for (int i = 0; i < this.B.size(); ++i) {
                        I.C.D("test(byte[]): testing submatch " + i);
                        final F a;
                        if ((a = this.B.get(i).A(array, false)) != null) {
                            I.C.D("test(byte[]): submatch " + i + " matched with '" + a.K() + "'");
                            b2.A(a);
                        }
                        else {
                            I.C.D("test(byte[]): submatch " + i + " doesn't match");
                        }
                    }
                }
            }
            return b2;
        }
        return null;
    }
    
    private boolean A(final byte[] array) {
        I.C.D("testInternal(byte[])");
        if (array.length == 0) {
            return false;
        }
        final String f = this.A.F();
        final String s = new String(this.A.I().array());
        final String h = this.A.H();
        final String k = this.A.K();
        final ByteBuffer allocate = ByteBuffer.allocate(array.length);
        if (f != null && s != null && s.length() > 0) {
            if (f.equals("string")) {
                return this.D(allocate.put(array));
            }
            if (f.equals("byte")) {
                return this.B(allocate.put(array));
            }
            if (f.equals("short")) {
                return this.E(allocate.put(array));
            }
            if (f.equals("leshort")) {
                final ByteBuffer put = allocate.put(array);
                put.order(ByteOrder.LITTLE_ENDIAN);
                return this.E(put);
            }
            if (f.equals("beshort")) {
                final ByteBuffer put2 = allocate.put(array);
                put2.order(ByteOrder.BIG_ENDIAN);
                return this.E(put2);
            }
            if (f.equals("long")) {
                return this.C(allocate.put(array));
            }
            if (f.equals("lelong")) {
                final ByteBuffer put3 = allocate.put(array);
                put3.order(ByteOrder.LITTLE_ENDIAN);
                return this.C(put3);
            }
            if (f.equals("belong")) {
                final ByteBuffer put4 = allocate.put(array);
                put4.order(ByteOrder.BIG_ENDIAN);
                return this.C(put4);
            }
            if (f.equals("regex")) {
                return this.A(new String(array));
            }
            if (f.equals("detector")) {
                return this.A(allocate.put(array));
            }
            I.C.E("testInternal(byte[]): invalid test type '" + f + "'");
        }
        else {
            I.C.E("testInternal(byte[]): type or test is empty for '" + h + " - " + k + "'");
        }
        return false;
    }
    
    private boolean B(final ByteBuffer byteBuffer) {
        I.C.D("testByte()");
        final String s = new String(this.A.I().array());
        final char e = this.A.E();
        final long d = this.A.D();
        final byte b = (byte)(byteBuffer.get(0) & d);
        I.C.D("testByte(): decoding '" + s + "' to byte");
        final byte byteValue = (byte)(Object)Integer.decode(s);
        final byte b2 = (byte)(byteValue & 0xFF);
        I.C.D("testByte(): applying bitmask '" + d + "' to '" + byteValue + "', result is '" + b2 + "'");
        I.C.D("testByte(): comparing byte '" + b + "' to '" + b2 + "'");
        switch (e) {
            case 61: {
                return b2 == b;
            }
            case 33: {
                return b2 != b;
            }
            case 62: {
                return b2 > b;
            }
            case 60: {
                return b2 < b;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean D(final ByteBuffer byteBuffer) {
        I.C.D("testString()");
        final ByteBuffer i = this.A.I();
        final char e = this.A.E();
        final byte[] array = byteBuffer.array();
        final byte[] array2 = i.array();
        boolean b = false;
        int j;
        for (j = 0; j < array2.length; ++j) {
            I.C.D("testing byte '" + array[j] + "' from '" + new String(byteBuffer.array()) + "' against byte '" + array2[j] + "' from '" + new String(i.array()) + "'");
            if (array2[j] != array[j]) {
                b = true;
                break;
            }
        }
        switch (e) {
            case 61: {
                return !b;
            }
            case 33: {
                return b;
            }
            case 62: {
                return array2[j] > array[j];
            }
            case 60: {
                return array2[j] < array[j];
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean E(final ByteBuffer byteBuffer) {
        I.C.D("testShort()");
        final String s = new String(this.A.I().array());
        final char e = this.A.E();
        final short n = (short)(this.F(byteBuffer) & (short)this.A.D());
        short shortValue;
        try {
            shortValue = (short)(Object)Integer.decode(s);
        }
        catch (NumberFormatException ex) {
            I.C.E("testShort(): " + ex);
            return false;
        }
        I.C.D("testShort(): testing '" + Long.toHexString(n) + "' against '" + Long.toHexString(shortValue) + "'");
        switch (e) {
            case 61: {
                return n == shortValue;
            }
            case 33: {
                return n != shortValue;
            }
            case 62: {
                return n > shortValue;
            }
            case 60: {
                return n < shortValue;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean C(final ByteBuffer byteBuffer) {
        I.C.D("testLong()");
        final String s = new String(this.A.I().array());
        final char e = this.A.E();
        final long n = this.G(byteBuffer) & this.A.D();
        final long longValue = Long.decode(s);
        I.C.D("testLong(): testing '" + Long.toHexString(n) + "' against '" + s + "' => '" + Long.toHexString(longValue) + "'");
        switch (e) {
            case 61: {
                return n == longValue;
            }
            case 33: {
                return n != longValue;
            }
            case 62: {
                return n > longValue;
            }
            case 60: {
                return n < longValue;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean A(final String s) {
        I.C.D("testRegex()");
        final String s2 = new String(this.A.I().array());
        final char e = this.A.E();
        final z.C.A.A.B.B.A a = new z.C.A.A.B.B.A();
        I.C.D("testRegex(): searching for '" + s2 + "'");
        if (e == '=') {
            return a.C(s2, s);
        }
        return e == '!' && !a.C(s2, s);
    }
    
    private boolean A(final ByteBuffer byteBuffer) {
        I.C.D("testDetector()");
        final String s = new String(this.A.I().array());
        try {
            I.C.D("loading class: " + s);
            final String[] process = ((net.sf.jmimemagic.A)Class.forName(s).newInstance()).process(byteBuffer.array(), this.A.G(), this.A.B(), this.A.D(), this.A.E(), this.A.H(), this.A.L());
            if (process != null && process.length > 0) {
                this.A.B(process[0]);
                return true;
            }
        }
        catch (ClassNotFoundException ex) {
            I.C.E("failed to load detector: " + s, ex);
        }
        catch (InstantiationException ex2) {
            I.C.E("specified class is not a valid detector class: " + s, ex2);
        }
        catch (IllegalAccessException ex3) {
            I.C.E("specified class cannot be accessed: " + s, ex3);
        }
        return false;
    }
    
    public String[] D() {
        I.C.D("testDetector()");
        final String s = new String(this.A.I().array());
        try {
            I.C.D("loading class: " + s);
            return ((net.sf.jmimemagic.A)Class.forName(s).newInstance()).getHandledTypes();
        }
        catch (ClassNotFoundException ex) {
            I.C.E("failed to load detector: " + s, ex);
        }
        catch (InstantiationException ex2) {
            I.C.E("specified class is not a valid detector class: " + s, ex2);
        }
        catch (IllegalAccessException ex3) {
            I.C.E("specified class cannot be accessed: " + s, ex3);
        }
        return new String[0];
    }
    
    private String A(final byte b) {
        return String.valueOf(b / 32 & 0x7) + String.valueOf(b / 8 & 0x7) + String.valueOf(b & 0x7);
    }
    
    private short F(final ByteBuffer byteBuffer) {
        return byteBuffer.getShort(0);
    }
    
    private long G(final ByteBuffer byteBuffer) {
        return byteBuffer.getInt(0);
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final I i = new I();
        i.A((F)this.A.clone());
        final Iterator<I> iterator = this.B.iterator();
        final ArrayList<Object> list = new ArrayList<Object>();
        while (iterator.hasNext()) {
            list.add(iterator.next().clone());
        }
        i.A(list);
        return i;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        I.C = B.getInstance().getLog((I.class$net$sf$jmimemagic$I == null) ? (I.class$net$sf$jmimemagic$I = class$("net.sf.jmimemagic.I")) : I.class$net$sf$jmimemagic$I);
    }
}
