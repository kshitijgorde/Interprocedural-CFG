// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm;

import java.io.IOException;
import java.io.InputStream;

public class ClassReader
{
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    public final byte[] b;
    private final int[] a;
    private final String[] c;
    private final int d;
    public final int header;
    
    public ClassReader(final byte[] array) {
        this(array, 0, array.length);
    }
    
    public ClassReader(final byte[] b, final int n, final int n2) {
        this.b = b;
        this.a = new int[this.readUnsignedShort(n + 8)];
        final int length = this.a.length;
        this.c = new String[length];
        int d = 0;
        int header = n + 10;
        for (int i = 1; i < length; ++i) {
            this.a[i] = header + 1;
            int n3 = 0;
            switch (b[header]) {
                case 3:
                case 4:
                case 9:
                case 10:
                case 11:
                case 12: {
                    n3 = 5;
                    break;
                }
                case 5:
                case 6: {
                    n3 = 9;
                    ++i;
                    break;
                }
                case 1: {
                    n3 = 3 + this.readUnsignedShort(header + 1);
                    if (n3 > d) {
                        d = n3;
                        break;
                    }
                    break;
                }
                default: {
                    n3 = 3;
                    break;
                }
            }
            header += n3;
        }
        this.d = d;
        this.header = header;
    }
    
    public int getAccess() {
        return this.readUnsignedShort(this.header);
    }
    
    public String getClassName() {
        return this.readClass(this.header + 2, new char[this.d]);
    }
    
    public String getSuperName() {
        final int n = this.a[this.readUnsignedShort(this.header + 4)];
        return (n == 0) ? null : this.readUTF8(n, new char[this.d]);
    }
    
    public String[] getInterfaces() {
        int n = this.header + 6;
        final int unsignedShort = this.readUnsignedShort(n);
        final String[] array = new String[unsignedShort];
        if (unsignedShort > 0) {
            final char[] array2 = new char[this.d];
            for (int i = 0; i < unsignedShort; ++i) {
                n += 2;
                array[i] = this.readClass(n, array2);
            }
        }
        return array;
    }
    
    void a(final ClassWriter classWriter) {
        final char[] array = new char[this.d];
        final int length = this.a.length;
        final Item[] e = new Item[length];
        for (int i = 1; i < length; ++i) {
            final int n = this.a[i];
            final byte b = this.b[n - 1];
            final Item item = new Item(i);
            switch (b) {
                case 9:
                case 10:
                case 11: {
                    final int n2 = this.a[this.readUnsignedShort(n + 2)];
                    item.a(b, this.readClass(n, array), this.readUTF8(n2, array), this.readUTF8(n2 + 2, array));
                    break;
                }
                case 3: {
                    item.a(this.readInt(n));
                    break;
                }
                case 4: {
                    item.a(Float.intBitsToFloat(this.readInt(n)));
                    break;
                }
                case 12: {
                    item.a(b, this.readUTF8(n, array), this.readUTF8(n + 2, array), null);
                    break;
                }
                case 5: {
                    item.a(this.readLong(n));
                    ++i;
                    break;
                }
                case 6: {
                    item.a(Double.longBitsToDouble(this.readLong(n)));
                    ++i;
                    break;
                }
                case 1: {
                    String s = this.c[i];
                    if (s == null) {
                        final int n3 = this.a[i];
                        final String[] c = this.c;
                        final int n4 = i;
                        final String a = this.a(n3 + 2, this.readUnsignedShort(n3), array);
                        c[n4] = a;
                        s = a;
                    }
                    item.a(b, s, null, null);
                    break;
                }
                default: {
                    item.a(b, this.readUTF8(n, array), null, null);
                    break;
                }
            }
            final int n5 = item.j % e.length;
            item.k = e[n5];
            e[n5] = item;
        }
        final int n6 = this.a[1] - 1;
        classWriter.d.putByteArray(this.b, n6, this.header - n6);
        classWriter.e = e;
        classWriter.f = (int)(0.75 * length);
        classWriter.c = length;
    }
    
    public ClassReader(final InputStream inputStream) throws IOException {
        this(a(inputStream));
    }
    
    public ClassReader(final String s) throws IOException {
        this(ClassLoader.getSystemResourceAsStream(s.replace('.', '/') + ".class"));
    }
    
    private static byte[] a(final InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        byte[] array = new byte[inputStream.available()];
        int n = 0;
        while (true) {
            final int read = inputStream.read(array, n, array.length - n);
            if (read == -1) {
                if (n < array.length) {
                    final byte[] array2 = new byte[n];
                    System.arraycopy(array, 0, array2, 0, n);
                    array = array2;
                }
                return array;
            }
            n += read;
            if (n != array.length) {
                continue;
            }
            final int read2 = inputStream.read();
            if (read2 < 0) {
                return array;
            }
            final byte[] array3 = new byte[array.length + 1000];
            System.arraycopy(array, 0, array3, 0, n);
            array3[n++] = (byte)read2;
            array = array3;
        }
    }
    
    public void accept(final ClassVisitor classVisitor, final int n) {
        this.accept(classVisitor, new Attribute[0], n);
    }
    
    public void accept(final ClassVisitor classVisitor, final Attribute[] array, final int n) {
        final byte[] b = this.b;
        final char[] array2 = new char[this.d];
        int n2 = 0;
        int n3 = 0;
        Attribute a = null;
        int header = this.header;
        int unsignedShort = this.readUnsignedShort(header);
        final String class1 = this.readClass(header + 2, array2);
        final int n4 = this.a[this.readUnsignedShort(header + 4)];
        final String s = (n4 == 0) ? null : this.readUTF8(n4, array2);
        final String[] array3 = new String[this.readUnsignedShort(header + 6)];
        int n5 = 0;
        header += 8;
        for (int i = 0; i < array3.length; ++i) {
            array3[i] = this.readClass(header, array2);
            header += 2;
        }
        final boolean b2 = (n & 0x1) != 0x0;
        final boolean b3 = (n & 0x2) != 0x0;
        final int n6 = ((n & 0x8) != 0x0) ? 1 : 0;
        int n7 = header;
        int j = this.readUnsignedShort(n7);
        n7 += 2;
        while (j > 0) {
            int k = this.readUnsignedShort(n7 + 6);
            n7 += 8;
            while (k > 0) {
                n7 += 6 + this.readInt(n7 + 2);
                --k;
            }
            --j;
        }
        int l = this.readUnsignedShort(n7);
        n7 += 2;
        while (l > 0) {
            int unsignedShort2 = this.readUnsignedShort(n7 + 6);
            n7 += 8;
            while (unsignedShort2 > 0) {
                n7 += 6 + this.readInt(n7 + 2);
                --unsignedShort2;
            }
            --l;
        }
        String utf8 = null;
        String utf9 = null;
        String a2 = null;
        String class2 = null;
        String utf10 = null;
        String utf11 = null;
        int unsignedShort3 = this.readUnsignedShort(n7);
        n7 += 2;
        while (unsignedShort3 > 0) {
            final String utf12 = this.readUTF8(n7, array2);
            if ("SourceFile".equals(utf12)) {
                utf9 = this.readUTF8(n7 + 6, array2);
            }
            else if ("InnerClasses".equals(utf12)) {
                n5 = n7 + 6;
            }
            else if ("EnclosingMethod".equals(utf12)) {
                class2 = this.readClass(n7 + 6, array2);
                final int unsignedShort4 = this.readUnsignedShort(n7 + 8);
                if (unsignedShort4 != 0) {
                    utf10 = this.readUTF8(this.a[unsignedShort4], array2);
                    utf11 = this.readUTF8(this.a[unsignedShort4] + 2, array2);
                }
            }
            else if ("Signature".equals(utf12)) {
                utf8 = this.readUTF8(n7 + 6, array2);
            }
            else if ("RuntimeVisibleAnnotations".equals(utf12)) {
                n2 = n7 + 6;
            }
            else if ("Deprecated".equals(utf12)) {
                unsignedShort |= 0x20000;
            }
            else if ("Synthetic".equals(utf12)) {
                unsignedShort |= 0x41000;
            }
            else if ("SourceDebugExtension".equals(utf12)) {
                final int int1 = this.readInt(n7 + 2);
                a2 = this.a(n7 + 6, int1, new char[int1]);
            }
            else if ("RuntimeInvisibleAnnotations".equals(utf12)) {
                n3 = n7 + 6;
            }
            else {
                final Attribute a3 = this.a(array, utf12, n7 + 6, this.readInt(n7 + 2), array2, -1, null);
                if (a3 != null) {
                    a3.a = a;
                    a = a3;
                }
            }
            n7 += 6 + this.readInt(n7 + 2);
            --unsignedShort3;
        }
        classVisitor.visit(this.readInt(4), unsignedShort, class1, utf8, s, array3);
        if (!b3 && (utf9 != null || a2 != null)) {
            classVisitor.visitSource(utf9, a2);
        }
        if (class2 != null) {
            classVisitor.visitOuterClass(class2, utf10, utf11);
        }
        for (int n8 = 1; n8 >= 0; --n8) {
            int a4 = (n8 == 0) ? n3 : n2;
            if (a4 != 0) {
                int unsignedShort5 = this.readUnsignedShort(a4);
                a4 += 2;
                while (unsignedShort5 > 0) {
                    a4 = this.a(a4 + 2, array2, true, classVisitor.visitAnnotation(this.readUTF8(a4, array2), n8 != 0));
                    --unsignedShort5;
                }
            }
        }
        while (a != null) {
            final Attribute a5 = a.a;
            a.a = null;
            classVisitor.visitAttribute(a);
            a = a5;
        }
        if (n5 != 0) {
            int unsignedShort6 = this.readUnsignedShort(n5);
            n5 += 2;
            while (unsignedShort6 > 0) {
                classVisitor.visitInnerClass((this.readUnsignedShort(n5) == 0) ? null : this.readClass(n5, array2), (this.readUnsignedShort(n5 + 2) == 0) ? null : this.readClass(n5 + 2, array2), (this.readUnsignedShort(n5 + 4) == 0) ? null : this.readUTF8(n5 + 4, array2), this.readUnsignedShort(n5 + 6));
                n5 += 8;
                --unsignedShort6;
            }
        }
        int unsignedShort7 = this.readUnsignedShort(header);
        header += 2;
        while (unsignedShort7 > 0) {
            int unsignedShort8 = this.readUnsignedShort(header);
            final String utf13 = this.readUTF8(header + 2, array2);
            final String utf14 = this.readUTF8(header + 4, array2);
            int unsignedShort9 = 0;
            String utf15 = null;
            int n9 = 0;
            int n10 = 0;
            Attribute a6 = null;
            int unsignedShort10 = this.readUnsignedShort(header + 6);
            header += 8;
            while (unsignedShort10 > 0) {
                final String utf16 = this.readUTF8(header, array2);
                if ("ConstantValue".equals(utf16)) {
                    unsignedShort9 = this.readUnsignedShort(header + 6);
                }
                else if ("Signature".equals(utf16)) {
                    utf15 = this.readUTF8(header + 6, array2);
                }
                else if ("Deprecated".equals(utf16)) {
                    unsignedShort8 |= 0x20000;
                }
                else if ("Synthetic".equals(utf16)) {
                    unsignedShort8 |= 0x41000;
                }
                else if ("RuntimeVisibleAnnotations".equals(utf16)) {
                    n9 = header + 6;
                }
                else if ("RuntimeInvisibleAnnotations".equals(utf16)) {
                    n10 = header + 6;
                }
                else {
                    final Attribute a7 = this.a(array, utf16, header + 6, this.readInt(header + 2), array2, -1, null);
                    if (a7 != null) {
                        a7.a = a6;
                        a6 = a7;
                    }
                }
                header += 6 + this.readInt(header + 2);
                --unsignedShort10;
            }
            final FieldVisitor visitField = classVisitor.visitField(unsignedShort8, utf13, utf14, utf15, (unsignedShort9 == 0) ? null : this.readConst(unsignedShort9, array2));
            if (visitField != null) {
                for (int n11 = 1; n11 >= 0; --n11) {
                    int a8 = (n11 == 0) ? n10 : n9;
                    if (a8 != 0) {
                        int unsignedShort11 = this.readUnsignedShort(a8);
                        a8 += 2;
                        while (unsignedShort11 > 0) {
                            a8 = this.a(a8 + 2, array2, true, visitField.visitAnnotation(this.readUTF8(a8, array2), n11 != 0));
                            --unsignedShort11;
                        }
                    }
                }
                while (a6 != null) {
                    final Attribute a9 = a6.a;
                    a6.a = null;
                    visitField.visitAttribute(a6);
                    a6 = a9;
                }
                visitField.visitEnd();
            }
            --unsignedShort7;
        }
        int unsignedShort12 = this.readUnsignedShort(header);
        header += 2;
        while (unsignedShort12 > 0) {
            final int h = header + 6;
            int unsignedShort13 = this.readUnsignedShort(header);
            final String utf17 = this.readUTF8(header + 2, array2);
            final String utf18 = this.readUTF8(header + 4, array2);
            String utf19 = null;
            int n12 = 0;
            int n13 = 0;
            int n14 = 0;
            int n15 = 0;
            int n16 = 0;
            Attribute a10 = null;
            int n17 = 0;
            int n18 = 0;
            int unsignedShort14 = this.readUnsignedShort(header + 6);
            header += 8;
            while (unsignedShort14 > 0) {
                final String utf20 = this.readUTF8(header, array2);
                final int int2 = this.readInt(header + 2);
                header += 6;
                if ("Code".equals(utf20)) {
                    if (!b2) {
                        n17 = header;
                    }
                }
                else if ("Exceptions".equals(utf20)) {
                    n18 = header;
                }
                else if ("Signature".equals(utf20)) {
                    utf19 = this.readUTF8(header, array2);
                }
                else if ("Deprecated".equals(utf20)) {
                    unsignedShort13 |= 0x20000;
                }
                else if ("RuntimeVisibleAnnotations".equals(utf20)) {
                    n12 = header;
                }
                else if ("AnnotationDefault".equals(utf20)) {
                    n14 = header;
                }
                else if ("Synthetic".equals(utf20)) {
                    unsignedShort13 |= 0x41000;
                }
                else if ("RuntimeInvisibleAnnotations".equals(utf20)) {
                    n13 = header;
                }
                else if ("RuntimeVisibleParameterAnnotations".equals(utf20)) {
                    n15 = header;
                }
                else if ("RuntimeInvisibleParameterAnnotations".equals(utf20)) {
                    n16 = header;
                }
                else {
                    final Attribute a11 = this.a(array, utf20, header, int2, array2, -1, null);
                    if (a11 != null) {
                        a11.a = a10;
                        a10 = a11;
                    }
                }
                header += int2;
                --unsignedShort14;
            }
            String[] array4;
            if (n18 == 0) {
                array4 = null;
            }
            else {
                array4 = new String[this.readUnsignedShort(n18)];
                n18 += 2;
                for (int n19 = 0; n19 < array4.length; ++n19) {
                    array4[n19] = this.readClass(n18, array2);
                    n18 += 2;
                }
            }
            final MethodVisitor visitMethod = classVisitor.visitMethod(unsignedShort13, utf17, utf18, utf19, array4);
            Label_5612: {
                if (visitMethod != null) {
                    if (visitMethod instanceof MethodWriter) {
                        final MethodWriter methodWriter = (MethodWriter)visitMethod;
                        if (methodWriter.b.J == this && utf19 == methodWriter.g) {
                            int n20 = 0;
                            if (array4 == null) {
                                n20 = ((methodWriter.j == 0) ? 1 : 0);
                            }
                            else if (array4.length == methodWriter.j) {
                                n20 = 1;
                                for (int n21 = array4.length - 1; n21 >= 0; --n21) {
                                    n18 -= 2;
                                    if (methodWriter.k[n21] != this.readUnsignedShort(n18)) {
                                        n20 = 0;
                                        break;
                                    }
                                }
                            }
                            if (n20 != 0) {
                                methodWriter.h = h;
                                methodWriter.i = header - h;
                                break Label_5612;
                            }
                        }
                    }
                    if (n14 != 0) {
                        final AnnotationVisitor visitAnnotationDefault = visitMethod.visitAnnotationDefault();
                        this.a(n14, array2, null, visitAnnotationDefault);
                        if (visitAnnotationDefault != null) {
                            visitAnnotationDefault.visitEnd();
                        }
                    }
                    for (int n22 = 1; n22 >= 0; --n22) {
                        int a12 = (n22 == 0) ? n13 : n12;
                        if (a12 != 0) {
                            int unsignedShort15 = this.readUnsignedShort(a12);
                            a12 += 2;
                            while (unsignedShort15 > 0) {
                                a12 = this.a(a12 + 2, array2, true, visitMethod.visitAnnotation(this.readUTF8(a12, array2), n22 != 0));
                                --unsignedShort15;
                            }
                        }
                    }
                    if (n15 != 0) {
                        this.a(n15, utf18, array2, true, visitMethod);
                    }
                    if (n16 != 0) {
                        this.a(n16, utf18, array2, false, visitMethod);
                    }
                    while (a10 != null) {
                        final Attribute a13 = a10.a;
                        a10.a = null;
                        visitMethod.visitAttribute(a10);
                        a10 = a13;
                    }
                }
                if (visitMethod != null && n17 != 0) {
                    final int unsignedShort16 = this.readUnsignedShort(n17);
                    final int unsignedShort17 = this.readUnsignedShort(n17 + 2);
                    final int int3 = this.readInt(n17 + 4);
                    n17 += 8;
                    final int n23 = n17;
                    final int n24 = n17 + int3;
                    visitMethod.visitCode();
                    final Label[] array5 = new Label[int3 + 2];
                    this.readLabel(int3 + 1, array5);
                    while (n17 < n24) {
                        final int n25 = n17 - n23;
                        switch (ClassWriter.a[b[n17] & 0xFF]) {
                            case 0:
                            case 4: {
                                ++n17;
                                continue;
                            }
                            case 8: {
                                this.readLabel(n25 + this.readShort(n17 + 1), array5);
                                n17 += 3;
                                continue;
                            }
                            case 9: {
                                this.readLabel(n25 + this.readInt(n17 + 1), array5);
                                n17 += 5;
                                continue;
                            }
                            case 16: {
                                if ((b[n17 + 1] & 0xFF) == 0x84) {
                                    n17 += 6;
                                    continue;
                                }
                                n17 += 4;
                                continue;
                            }
                            case 13: {
                                n17 = n17 + 4 - (n25 & 0x3);
                                this.readLabel(n25 + this.readInt(n17), array5);
                                int n26 = this.readInt(n17 + 8) - this.readInt(n17 + 4) + 1;
                                n17 += 12;
                                while (n26 > 0) {
                                    this.readLabel(n25 + this.readInt(n17), array5);
                                    n17 += 4;
                                    --n26;
                                }
                                continue;
                            }
                            case 14: {
                                n17 = n17 + 4 - (n25 & 0x3);
                                this.readLabel(n25 + this.readInt(n17), array5);
                                int int4 = this.readInt(n17 + 4);
                                n17 += 8;
                                while (int4 > 0) {
                                    this.readLabel(n25 + this.readInt(n17 + 4), array5);
                                    n17 += 8;
                                    --int4;
                                }
                                continue;
                            }
                            case 1:
                            case 3:
                            case 10: {
                                n17 += 2;
                                continue;
                            }
                            case 2:
                            case 5:
                            case 6:
                            case 11:
                            case 12: {
                                n17 += 3;
                                continue;
                            }
                            case 7: {
                                n17 += 5;
                                continue;
                            }
                            default: {
                                n17 += 4;
                                continue;
                            }
                        }
                    }
                    int unsignedShort18 = this.readUnsignedShort(n17);
                    n17 += 2;
                    while (unsignedShort18 > 0) {
                        final Label label = this.readLabel(this.readUnsignedShort(n17), array5);
                        final Label label2 = this.readLabel(this.readUnsignedShort(n17 + 2), array5);
                        final Label label3 = this.readLabel(this.readUnsignedShort(n17 + 4), array5);
                        final int unsignedShort19 = this.readUnsignedShort(n17 + 6);
                        if (unsignedShort19 == 0) {
                            visitMethod.visitTryCatchBlock(label, label2, label3, null);
                        }
                        else {
                            visitMethod.visitTryCatchBlock(label, label2, label3, this.readUTF8(this.a[unsignedShort19], array2));
                        }
                        n17 += 8;
                        --unsignedShort18;
                    }
                    int n27 = 0;
                    int n28 = 0;
                    int n29 = 0;
                    int n30 = 0;
                    int n31 = 0;
                    int n32 = 0;
                    int n33 = 0;
                    int unsignedShort20 = 0;
                    int n34 = 0;
                    int n35 = 0;
                    Object[] array6 = null;
                    Object[] array7 = null;
                    boolean b4 = true;
                    Attribute a14 = null;
                    int unsignedShort21 = this.readUnsignedShort(n17);
                    n17 += 2;
                    while (unsignedShort21 > 0) {
                        final String utf21 = this.readUTF8(n17, array2);
                        if ("LocalVariableTable".equals(utf21)) {
                            if (!b3) {
                                n27 = n17 + 6;
                                int unsignedShort22 = this.readUnsignedShort(n17 + 6);
                                int n36 = n17 + 8;
                                while (unsignedShort22 > 0) {
                                    final int unsignedShort23 = this.readUnsignedShort(n36);
                                    if (array5[unsignedShort23] == null) {
                                        final Label label4 = this.readLabel(unsignedShort23, array5);
                                        label4.a |= 0x1;
                                    }
                                    final int n37 = unsignedShort23 + this.readUnsignedShort(n36 + 2);
                                    if (array5[n37] == null) {
                                        final Label label5 = this.readLabel(n37, array5);
                                        label5.a |= 0x1;
                                    }
                                    n36 += 10;
                                    --unsignedShort22;
                                }
                            }
                        }
                        else if ("LocalVariableTypeTable".equals(utf21)) {
                            n28 = n17 + 6;
                        }
                        else if ("LineNumberTable".equals(utf21)) {
                            if (!b3) {
                                int unsignedShort24 = this.readUnsignedShort(n17 + 6);
                                int n38 = n17 + 8;
                                while (unsignedShort24 > 0) {
                                    final int unsignedShort25 = this.readUnsignedShort(n38);
                                    if (array5[unsignedShort25] == null) {
                                        final Label label6 = this.readLabel(unsignedShort25, array5);
                                        label6.a |= 0x1;
                                    }
                                    array5[unsignedShort25].b = this.readUnsignedShort(n38 + 2);
                                    n38 += 4;
                                    --unsignedShort24;
                                }
                            }
                        }
                        else if ("StackMapTable".equals(utf21)) {
                            if ((n & 0x4) == 0x0) {
                                n29 = n17 + 8;
                                n30 = this.readInt(n17 + 2);
                                n31 = this.readUnsignedShort(n17 + 6);
                            }
                        }
                        else if ("StackMap".equals(utf21)) {
                            if ((n & 0x4) == 0x0) {
                                n29 = n17 + 8;
                                n30 = this.readInt(n17 + 2);
                                n31 = this.readUnsignedShort(n17 + 6);
                                b4 = false;
                            }
                        }
                        else {
                            for (int n39 = 0; n39 < array.length; ++n39) {
                                if (array[n39].type.equals(utf21)) {
                                    final Attribute read = array[n39].read(this, n17 + 6, this.readInt(n17 + 2), array2, n23 - 8, array5);
                                    if (read != null) {
                                        read.a = a14;
                                        a14 = read;
                                    }
                                }
                            }
                        }
                        n17 += 6 + this.readInt(n17 + 2);
                        --unsignedShort21;
                    }
                    if (n29 != 0) {
                        array6 = new Object[unsignedShort17];
                        array7 = new Object[unsignedShort16];
                        Label_3736: {
                            if (n6 != 0) {
                                int n40 = 0;
                                if ((unsignedShort13 & 0x8) == 0x0) {
                                    if ("<init>".equals(utf17)) {
                                        array6[n40++] = Opcodes.UNINITIALIZED_THIS;
                                    }
                                    else {
                                        array6[n40++] = this.readClass(this.header + 2, array2);
                                    }
                                }
                                int n41 = 1;
                                while (true) {
                                    final int n42 = n41;
                                    switch (utf18.charAt(n41++)) {
                                        case 'B':
                                        case 'C':
                                        case 'I':
                                        case 'S':
                                        case 'Z': {
                                            array6[n40++] = Opcodes.INTEGER;
                                            continue;
                                        }
                                        case 'F': {
                                            array6[n40++] = Opcodes.FLOAT;
                                            continue;
                                        }
                                        case 'J': {
                                            array6[n40++] = Opcodes.LONG;
                                            continue;
                                        }
                                        case 'D': {
                                            array6[n40++] = Opcodes.DOUBLE;
                                            continue;
                                        }
                                        case '[': {
                                            while (utf18.charAt(n41) == '[') {
                                                ++n41;
                                            }
                                            if (utf18.charAt(n41) == 'L') {
                                                ++n41;
                                                while (utf18.charAt(n41) != ';') {
                                                    ++n41;
                                                }
                                            }
                                            array6[n40++] = utf18.substring(n42, ++n41);
                                            continue;
                                        }
                                        case 'L': {
                                            while (utf18.charAt(n41) != ';') {
                                                ++n41;
                                            }
                                            array6[n40++] = utf18.substring(n42 + 1, n41++);
                                            continue;
                                        }
                                        default: {
                                            unsignedShort20 = n40;
                                            break Label_3736;
                                        }
                                    }
                                }
                            }
                        }
                        n33 = -1;
                        for (int n43 = n29; n43 < n29 + n30 - 2; ++n43) {
                            if (b[n43] == 8) {
                                final int unsignedShort26 = this.readUnsignedShort(n43 + 1);
                                if (unsignedShort26 >= 0 && unsignedShort26 < int3 && (b[n23 + unsignedShort26] & 0xFF) == 0xBB) {
                                    this.readLabel(unsignedShort26, array5);
                                }
                            }
                        }
                    }
                    int n44 = n23;
                    while (n44 < n24) {
                        final int n45 = n44 - n23;
                        final Label label7 = array5[n45];
                        if (label7 != null) {
                            visitMethod.visitLabel(label7);
                            if (!b3 && label7.b > 0) {
                                visitMethod.visitLineNumber(label7.b, label7);
                            }
                        }
                        while (array6 != null && (n33 == n45 || n33 == -1)) {
                            if (!b4 || n6) {
                                visitMethod.visitFrame(-1, unsignedShort20, array6, n35, array7);
                            }
                            else if (n33 != -1) {
                                visitMethod.visitFrame(n32, n34, array6, n35, array7);
                            }
                            if (n31 > 0) {
                                int n46;
                                if (b4) {
                                    n46 = (b[n29++] & 0xFF);
                                }
                                else {
                                    n46 = 255;
                                    n33 = -1;
                                }
                                n34 = 0;
                                int unsignedShort27;
                                if (n46 < 64) {
                                    unsignedShort27 = n46;
                                    n32 = 3;
                                    n35 = 0;
                                }
                                else if (n46 < 128) {
                                    unsignedShort27 = n46 - 64;
                                    n29 = this.a(array7, 0, n29, array2, array5);
                                    n32 = 4;
                                    n35 = 1;
                                }
                                else {
                                    unsignedShort27 = this.readUnsignedShort(n29);
                                    n29 += 2;
                                    if (n46 == 247) {
                                        n29 = this.a(array7, 0, n29, array2, array5);
                                        n32 = 4;
                                        n35 = 1;
                                    }
                                    else if (n46 >= 248 && n46 < 251) {
                                        n32 = 2;
                                        n34 = 251 - n46;
                                        unsignedShort20 -= n34;
                                        n35 = 0;
                                    }
                                    else if (n46 == 251) {
                                        n32 = 3;
                                        n35 = 0;
                                    }
                                    else if (n46 < 255) {
                                        int n47 = (n6 != 0) ? unsignedShort20 : 0;
                                        for (int n48 = n46 - 251; n48 > 0; --n48) {
                                            n29 = this.a(array6, n47++, n29, array2, array5);
                                        }
                                        n32 = 1;
                                        n34 = n46 - 251;
                                        unsignedShort20 += n34;
                                        n35 = 0;
                                    }
                                    else {
                                        n32 = 0;
                                        int n49;
                                        n34 = (n49 = (unsignedShort20 = this.readUnsignedShort(n29)));
                                        n29 += 2;
                                        int n50 = 0;
                                        while (n49 > 0) {
                                            n29 = this.a(array6, n50++, n29, array2, array5);
                                            --n49;
                                        }
                                        int unsignedShort28;
                                        n35 = (unsignedShort28 = this.readUnsignedShort(n29));
                                        n29 += 2;
                                        int n51 = 0;
                                        while (unsignedShort28 > 0) {
                                            n29 = this.a(array7, n51++, n29, array2, array5);
                                            --unsignedShort28;
                                        }
                                    }
                                }
                                n33 += unsignedShort27 + 1;
                                this.readLabel(n33, array5);
                                --n31;
                            }
                            else {
                                array6 = null;
                            }
                        }
                        int n52 = b[n44] & 0xFF;
                        switch (ClassWriter.a[n52]) {
                            case 0: {
                                visitMethod.visitInsn(n52);
                                ++n44;
                                continue;
                            }
                            case 4: {
                                if (n52 > 54) {
                                    n52 -= 59;
                                    visitMethod.visitVarInsn(54 + (n52 >> 2), n52 & 0x3);
                                }
                                else {
                                    n52 -= 26;
                                    visitMethod.visitVarInsn(21 + (n52 >> 2), n52 & 0x3);
                                }
                                ++n44;
                                continue;
                            }
                            case 8: {
                                visitMethod.visitJumpInsn(n52, array5[n45 + this.readShort(n44 + 1)]);
                                n44 += 3;
                                continue;
                            }
                            case 9: {
                                visitMethod.visitJumpInsn(n52 - 33, array5[n45 + this.readInt(n44 + 1)]);
                                n44 += 5;
                                continue;
                            }
                            case 16: {
                                final int n53 = b[n44 + 1] & 0xFF;
                                if (n53 == 132) {
                                    visitMethod.visitIincInsn(this.readUnsignedShort(n44 + 2), this.readShort(n44 + 4));
                                    n44 += 6;
                                    continue;
                                }
                                visitMethod.visitVarInsn(n53, this.readUnsignedShort(n44 + 2));
                                n44 += 4;
                                continue;
                            }
                            case 13: {
                                n44 = n44 + 4 - (n45 & 0x3);
                                final int n54 = n45 + this.readInt(n44);
                                final int int5 = this.readInt(n44 + 4);
                                final int int6 = this.readInt(n44 + 8);
                                n44 += 12;
                                final Label[] array8 = new Label[int6 - int5 + 1];
                                for (int n55 = 0; n55 < array8.length; ++n55) {
                                    array8[n55] = array5[n45 + this.readInt(n44)];
                                    n44 += 4;
                                }
                                visitMethod.visitTableSwitchInsn(int5, int6, array5[n54], array8);
                                continue;
                            }
                            case 14: {
                                n44 = n44 + 4 - (n45 & 0x3);
                                final int n56 = n45 + this.readInt(n44);
                                final int int7 = this.readInt(n44 + 4);
                                n44 += 8;
                                final int[] array9 = new int[int7];
                                final Label[] array10 = new Label[int7];
                                for (int n57 = 0; n57 < array9.length; ++n57) {
                                    array9[n57] = this.readInt(n44);
                                    array10[n57] = array5[n45 + this.readInt(n44 + 4)];
                                    n44 += 8;
                                }
                                visitMethod.visitLookupSwitchInsn(array5[n56], array9, array10);
                                continue;
                            }
                            case 3: {
                                visitMethod.visitVarInsn(n52, b[n44 + 1] & 0xFF);
                                n44 += 2;
                                continue;
                            }
                            case 1: {
                                visitMethod.visitIntInsn(n52, b[n44 + 1]);
                                n44 += 2;
                                continue;
                            }
                            case 2: {
                                visitMethod.visitIntInsn(n52, this.readShort(n44 + 1));
                                n44 += 3;
                                continue;
                            }
                            case 10: {
                                visitMethod.visitLdcInsn(this.readConst(b[n44 + 1] & 0xFF, array2));
                                n44 += 2;
                                continue;
                            }
                            case 11: {
                                visitMethod.visitLdcInsn(this.readConst(this.readUnsignedShort(n44 + 1), array2));
                                n44 += 3;
                                continue;
                            }
                            case 6:
                            case 7: {
                                int n58 = this.a[this.readUnsignedShort(n44 + 1)];
                                String class3;
                                if (n52 == 186) {
                                    class3 = "java/lang/dyn/Dynamic";
                                }
                                else {
                                    class3 = this.readClass(n58, array2);
                                    n58 = this.a[this.readUnsignedShort(n58 + 2)];
                                }
                                final String utf22 = this.readUTF8(n58, array2);
                                final String utf23 = this.readUTF8(n58 + 2, array2);
                                if (n52 < 182) {
                                    visitMethod.visitFieldInsn(n52, class3, utf22, utf23);
                                }
                                else {
                                    visitMethod.visitMethodInsn(n52, class3, utf22, utf23);
                                }
                                if (n52 == 185 || n52 == 186) {
                                    n44 += 5;
                                    continue;
                                }
                                n44 += 3;
                                continue;
                            }
                            case 5: {
                                visitMethod.visitTypeInsn(n52, this.readClass(n44 + 1, array2));
                                n44 += 3;
                                continue;
                            }
                            case 12: {
                                visitMethod.visitIincInsn(b[n44 + 1] & 0xFF, b[n44 + 2]);
                                n44 += 3;
                                continue;
                            }
                            default: {
                                visitMethod.visitMultiANewArrayInsn(this.readClass(n44 + 1, array2), b[n44 + 3] & 0xFF);
                                n44 += 4;
                                continue;
                            }
                        }
                    }
                    final Label label8 = array5[n24 - n23];
                    if (label8 != null) {
                        visitMethod.visitLabel(label8);
                    }
                    if (!b3 && n27 != 0) {
                        int[] array11 = null;
                        if (n28 != 0) {
                            int n59;
                            int n60;
                            for (n59 = this.readUnsignedShort(n28) * 3, n60 = n28 + 2, array11 = new int[n59]; n59 > 0; array11[--n59] = n60 + 6, array11[--n59] = this.readUnsignedShort(n60 + 8), array11[--n59] = this.readUnsignedShort(n60), n60 += 10) {}
                        }
                        int unsignedShort29 = this.readUnsignedShort(n27);
                        int n61 = n27 + 2;
                        while (unsignedShort29 > 0) {
                            final int unsignedShort30 = this.readUnsignedShort(n61);
                            final int unsignedShort31 = this.readUnsignedShort(n61 + 2);
                            final int unsignedShort32 = this.readUnsignedShort(n61 + 8);
                            String utf24 = null;
                            if (array11 != null) {
                                for (int n62 = 0; n62 < array11.length; n62 += 3) {
                                    if (array11[n62] == unsignedShort30 && array11[n62 + 1] == unsignedShort32) {
                                        utf24 = this.readUTF8(array11[n62 + 2], array2);
                                        break;
                                    }
                                }
                            }
                            visitMethod.visitLocalVariable(this.readUTF8(n61 + 4, array2), this.readUTF8(n61 + 6, array2), utf24, array5[unsignedShort30], array5[unsignedShort30 + unsignedShort31], unsignedShort32);
                            n61 += 10;
                            --unsignedShort29;
                        }
                    }
                    while (a14 != null) {
                        final Attribute a15 = a14.a;
                        a14.a = null;
                        visitMethod.visitAttribute(a14);
                        a14 = a15;
                    }
                    visitMethod.visitMaxs(unsignedShort16, unsignedShort17);
                }
                if (visitMethod != null) {
                    visitMethod.visitEnd();
                }
            }
            --unsignedShort12;
        }
        classVisitor.visitEnd();
    }
    
    private void a(int a, final String s, final char[] array, final boolean b, final MethodVisitor methodVisitor) {
        final int n = this.b[a++] & 0xFF;
        int n2;
        int i;
        for (n2 = Type.getArgumentTypes(s).length - n, i = 0; i < n2; ++i) {
            final AnnotationVisitor visitParameterAnnotation = methodVisitor.visitParameterAnnotation(i, "Ljava/lang/Synthetic;", false);
            if (visitParameterAnnotation != null) {
                visitParameterAnnotation.visitEnd();
            }
        }
        while (i < n + n2) {
            int j = this.readUnsignedShort(a);
            a += 2;
            while (j > 0) {
                a = this.a(a + 2, array, true, methodVisitor.visitParameterAnnotation(i, this.readUTF8(a, array), b));
                --j;
            }
            ++i;
        }
    }
    
    private int a(int n, final char[] array, final boolean b, final AnnotationVisitor annotationVisitor) {
        int i = this.readUnsignedShort(n);
        n += 2;
        if (b) {
            while (i > 0) {
                n = this.a(n + 2, array, this.readUTF8(n, array), annotationVisitor);
                --i;
            }
        }
        else {
            while (i > 0) {
                n = this.a(n, array, null, annotationVisitor);
                --i;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return n;
    }
    
    private int a(int n, final char[] array, final String s, final AnnotationVisitor annotationVisitor) {
        if (annotationVisitor != null) {
            Label_1259: {
                switch (this.b[n++] & 0xFF) {
                    case 68:
                    case 70:
                    case 73:
                    case 74: {
                        annotationVisitor.visit(s, this.readConst(this.readUnsignedShort(n), array));
                        n += 2;
                        break;
                    }
                    case 66: {
                        annotationVisitor.visit(s, new Byte((byte)this.readInt(this.a[this.readUnsignedShort(n)])));
                        n += 2;
                        break;
                    }
                    case 90: {
                        annotationVisitor.visit(s, (this.readInt(this.a[this.readUnsignedShort(n)]) == 0) ? Boolean.FALSE : Boolean.TRUE);
                        n += 2;
                        break;
                    }
                    case 83: {
                        annotationVisitor.visit(s, new Short((short)this.readInt(this.a[this.readUnsignedShort(n)])));
                        n += 2;
                        break;
                    }
                    case 67: {
                        annotationVisitor.visit(s, new Character((char)this.readInt(this.a[this.readUnsignedShort(n)])));
                        n += 2;
                        break;
                    }
                    case 115: {
                        annotationVisitor.visit(s, this.readUTF8(n, array));
                        n += 2;
                        break;
                    }
                    case 101: {
                        annotationVisitor.visitEnum(s, this.readUTF8(n, array), this.readUTF8(n + 2, array));
                        n += 4;
                        break;
                    }
                    case 99: {
                        annotationVisitor.visit(s, Type.getType(this.readUTF8(n, array)));
                        n += 2;
                        break;
                    }
                    case 64: {
                        n = this.a(n + 2, array, true, annotationVisitor.visitAnnotation(s, this.readUTF8(n, array)));
                        break;
                    }
                    case 91: {
                        final int unsignedShort = this.readUnsignedShort(n);
                        n += 2;
                        if (unsignedShort == 0) {
                            return this.a(n - 2, array, false, annotationVisitor.visitArray(s));
                        }
                        switch (this.b[n++] & 0xFF) {
                            case 66: {
                                final byte[] array2 = new byte[unsignedShort];
                                for (int i = 0; i < unsignedShort; ++i) {
                                    array2[i] = (byte)this.readInt(this.a[this.readUnsignedShort(n)]);
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array2);
                                --n;
                                break Label_1259;
                            }
                            case 90: {
                                final boolean[] array3 = new boolean[unsignedShort];
                                for (int j = 0; j < unsignedShort; ++j) {
                                    array3[j] = (this.readInt(this.a[this.readUnsignedShort(n)]) != 0);
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array3);
                                --n;
                                break Label_1259;
                            }
                            case 83: {
                                final short[] array4 = new short[unsignedShort];
                                for (int k = 0; k < unsignedShort; ++k) {
                                    array4[k] = (short)this.readInt(this.a[this.readUnsignedShort(n)]);
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array4);
                                --n;
                                break Label_1259;
                            }
                            case 67: {
                                final char[] array5 = new char[unsignedShort];
                                for (int l = 0; l < unsignedShort; ++l) {
                                    array5[l] = (char)this.readInt(this.a[this.readUnsignedShort(n)]);
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array5);
                                --n;
                                break Label_1259;
                            }
                            case 73: {
                                final int[] array6 = new int[unsignedShort];
                                for (int n2 = 0; n2 < unsignedShort; ++n2) {
                                    array6[n2] = this.readInt(this.a[this.readUnsignedShort(n)]);
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array6);
                                --n;
                                break Label_1259;
                            }
                            case 74: {
                                final long[] array7 = new long[unsignedShort];
                                for (int n3 = 0; n3 < unsignedShort; ++n3) {
                                    array7[n3] = this.readLong(this.a[this.readUnsignedShort(n)]);
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array7);
                                --n;
                                break Label_1259;
                            }
                            case 70: {
                                final float[] array8 = new float[unsignedShort];
                                for (int n4 = 0; n4 < unsignedShort; ++n4) {
                                    array8[n4] = Float.intBitsToFloat(this.readInt(this.a[this.readUnsignedShort(n)]));
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array8);
                                --n;
                                break Label_1259;
                            }
                            case 68: {
                                final double[] array9 = new double[unsignedShort];
                                for (int n5 = 0; n5 < unsignedShort; ++n5) {
                                    array9[n5] = Double.longBitsToDouble(this.readLong(this.a[this.readUnsignedShort(n)]));
                                    n += 3;
                                }
                                annotationVisitor.visit(s, array9);
                                --n;
                                break Label_1259;
                            }
                            default: {
                                n = this.a(n - 3, array, false, annotationVisitor.visitArray(s));
                                break Label_1259;
                            }
                        }
                        break;
                    }
                }
            }
            return n;
        }
        switch (this.b[n] & 0xFF) {
            case 101: {
                return n + 5;
            }
            case 64: {
                return this.a(n + 3, array, true, null);
            }
            case 91: {
                return this.a(n + 1, array, false, null);
            }
            default: {
                return n + 3;
            }
        }
    }
    
    private int a(final Object[] array, final int n, int n2, final char[] array2, final Label[] array3) {
        switch (this.b[n2++] & 0xFF) {
            case 0: {
                array[n] = Opcodes.TOP;
                break;
            }
            case 1: {
                array[n] = Opcodes.INTEGER;
                break;
            }
            case 2: {
                array[n] = Opcodes.FLOAT;
                break;
            }
            case 3: {
                array[n] = Opcodes.DOUBLE;
                break;
            }
            case 4: {
                array[n] = Opcodes.LONG;
                break;
            }
            case 5: {
                array[n] = Opcodes.NULL;
                break;
            }
            case 6: {
                array[n] = Opcodes.UNINITIALIZED_THIS;
                break;
            }
            case 7: {
                array[n] = this.readClass(n2, array2);
                n2 += 2;
                break;
            }
            default: {
                array[n] = this.readLabel(this.readUnsignedShort(n2), array3);
                n2 += 2;
                break;
            }
        }
        return n2;
    }
    
    protected Label readLabel(final int n, final Label[] array) {
        if (array[n] == null) {
            array[n] = new Label();
        }
        return array[n];
    }
    
    private Attribute a(final Attribute[] array, final String s, final int n, final int n2, final char[] array2, final int n3, final Label[] array3) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].type.equals(s)) {
                return array[i].read(this, n, n2, array2, n3, array3);
            }
        }
        return new Attribute(s).read(this, n, n2, null, -1, null);
    }
    
    public int getItem(final int n) {
        return this.a[n];
    }
    
    public int readByte(final int n) {
        return this.b[n] & 0xFF;
    }
    
    public int readUnsignedShort(final int n) {
        final byte[] b = this.b;
        return (b[n] & 0xFF) << 8 | (b[n + 1] & 0xFF);
    }
    
    public short readShort(final int n) {
        final byte[] b = this.b;
        return (short)((b[n] & 0xFF) << 8 | (b[n + 1] & 0xFF));
    }
    
    public int readInt(final int n) {
        final byte[] b = this.b;
        return (b[n] & 0xFF) << 24 | (b[n + 1] & 0xFF) << 16 | (b[n + 2] & 0xFF) << 8 | (b[n + 3] & 0xFF);
    }
    
    public long readLong(final int n) {
        return this.readInt(n) << 32 | (this.readInt(n + 4) & 0xFFFFFFFFL);
    }
    
    public String readUTF8(int n, final char[] array) {
        final int unsignedShort = this.readUnsignedShort(n);
        final String s = this.c[unsignedShort];
        if (s != null) {
            return s;
        }
        n = this.a[unsignedShort];
        return this.c[unsignedShort] = this.a(n + 2, this.readUnsignedShort(n), array);
    }
    
    private String a(int i, final int n, final char[] array) {
        final int n2 = i + n;
        final byte[] b = this.b;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        while (i < n2) {
            final byte b2 = b[i++];
            switch (n4) {
                case 0: {
                    final int n6 = b2 & 0xFF;
                    if (n6 < 128) {
                        array[n3++] = (char)n6;
                        continue;
                    }
                    if (n6 < 224 && n6 > 191) {
                        n5 = (char)(n6 & 0x1F);
                        n4 = 1;
                        continue;
                    }
                    n5 = (char)(n6 & 0xF);
                    n4 = 2;
                    continue;
                }
                case 1: {
                    array[n3++] = (char)(n5 << 6 | (b2 & 0x3F));
                    n4 = 0;
                    continue;
                }
                case 2: {
                    n5 = (char)(n5 << 6 | (b2 & 0x3F));
                    n4 = 1;
                    continue;
                }
            }
        }
        return new String(array, 0, n3);
    }
    
    public String readClass(final int n, final char[] array) {
        return this.readUTF8(this.a[this.readUnsignedShort(n)], array);
    }
    
    public Object readConst(final int n, final char[] array) {
        final int n2 = this.a[n];
        switch (this.b[n2 - 1]) {
            case 3: {
                return new Integer(this.readInt(n2));
            }
            case 4: {
                return new Float(Float.intBitsToFloat(this.readInt(n2)));
            }
            case 5: {
                return new Long(this.readLong(n2));
            }
            case 6: {
                return new Double(Double.longBitsToDouble(this.readLong(n2)));
            }
            case 7: {
                return Type.getObjectType(this.readUTF8(n2, array));
            }
            default: {
                return this.readUTF8(n2, array);
            }
        }
    }
}
