// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Opcodes;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import java.util.HashMap;
import org.jruby.org.objectweb.asm.MethodVisitor;
import java.lang.reflect.Field;
import java.util.Map;
import org.jruby.org.objectweb.asm.MethodAdapter;

public class CheckMethodAdapter extends MethodAdapter
{
    public int version;
    private boolean startCode;
    private boolean endCode;
    private boolean endMethod;
    private final Map labels;
    private static final int[] TYPE;
    private static Field labelStatusField;
    static /* synthetic */ Class class$org$objectweb$asm$Label;
    
    public CheckMethodAdapter(final MethodVisitor methodVisitor) {
        this(methodVisitor, new HashMap());
    }
    
    public CheckMethodAdapter(final MethodVisitor methodVisitor, final Map labels) {
        super(methodVisitor);
        this.labels = labels;
    }
    
    public CheckMethodAdapter(final int n, final String s, final String s2, final MethodVisitor methodVisitor, final Map map) {
        this(new CheckMethodAdapter$1(n, s, s2, null, null, methodVisitor), map);
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.checkEndMethod();
        checkDesc(s, false);
        return new CheckAnnotationAdapter(this.mv.visitAnnotation(s, b));
    }
    
    public AnnotationVisitor visitAnnotationDefault() {
        this.checkEndMethod();
        return new CheckAnnotationAdapter(this.mv.visitAnnotationDefault(), false);
    }
    
    public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
        this.checkEndMethod();
        checkDesc(s, false);
        return new CheckAnnotationAdapter(this.mv.visitParameterAnnotation(n, s, b));
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.checkEndMethod();
        if (attribute == null) {
            throw new IllegalArgumentException("Invalid attribute (must not be null)");
        }
        this.mv.visitAttribute(attribute);
    }
    
    public void visitCode() {
        this.startCode = true;
        this.mv.visitCode();
    }
    
    public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
        int n4 = 0;
        int n5 = 0;
        switch (n) {
            case -1:
            case 0: {
                n4 = Integer.MAX_VALUE;
                n5 = Integer.MAX_VALUE;
                break;
            }
            case 3: {
                n4 = 0;
                n5 = 0;
                break;
            }
            case 4: {
                n4 = 0;
                n5 = 1;
                break;
            }
            case 1:
            case 2: {
                n4 = 3;
                n5 = 0;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid frame type " + n);
            }
        }
        if (n2 > n4) {
            throw new IllegalArgumentException("Invalid nLocal=" + n2 + " for frame type " + n);
        }
        if (n3 > n5) {
            throw new IllegalArgumentException("Invalid nStack=" + n3 + " for frame type " + n);
        }
        if (n != 2) {
            if (n2 > 0 && (array == null || array.length < n2)) {
                throw new IllegalArgumentException("Array local[] is shorter than nLocal");
            }
            for (int i = 0; i < n2; ++i) {
                checkFrameValue(array[i]);
            }
        }
        if (n3 > 0 && (array2 == null || array2.length < n3)) {
            throw new IllegalArgumentException("Array stack[] is shorter than nStack");
        }
        for (int j = 0; j < n3; ++j) {
            checkFrameValue(array2[j]);
        }
        this.mv.visitFrame(n, n2, array, n3, array2);
    }
    
    public void visitInsn(final int n) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 0);
        this.mv.visitInsn(n);
    }
    
    public void visitIntInsn(final int n, final int n2) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 1);
        switch (n) {
            case 16: {
                checkSignedByte(n2, "Invalid operand");
                break;
            }
            case 17: {
                checkSignedShort(n2, "Invalid operand");
                break;
            }
            default: {
                if (n2 < 4 || n2 > 11) {
                    throw new IllegalArgumentException("Invalid operand (must be an array type code T_...): " + n2);
                }
                break;
            }
        }
        this.mv.visitIntInsn(n, n2);
    }
    
    public void visitVarInsn(final int n, final int n2) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 2);
        checkUnsignedShort(n2, "Invalid variable index");
        this.mv.visitVarInsn(n, n2);
    }
    
    public void visitTypeInsn(final int n, final String s) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 3);
        checkInternalName(s, "type");
        if (n == 187 && s.charAt(0) == '[') {
            throw new IllegalArgumentException("NEW cannot be used to create arrays: " + s);
        }
        this.mv.visitTypeInsn(n, s);
    }
    
    public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 4);
        checkInternalName(s, "owner");
        checkUnqualifiedName(this.version, s2, "name");
        checkDesc(s3, false);
        this.mv.visitFieldInsn(n, s, s2, s3);
    }
    
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 5);
        checkMethodIdentifier(this.version, s2, "name");
        checkInternalName(s, "owner");
        checkMethodDesc(s3);
        if (n == 186 && s != "java/lang/dyn/Dynamic") {
            throw new IllegalArgumentException("INVOKEDYNAMIC cannot be used with another owner than INVOKEDYNAMIC_OWNER");
        }
        this.mv.visitMethodInsn(n, s, s2, s3);
    }
    
    public void visitJumpInsn(final int n, final Label label) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 6);
        this.checkLabel(label, false, "label");
        checkNonDebugLabel(label);
        this.mv.visitJumpInsn(n, label);
    }
    
    public void visitLabel(final Label label) {
        this.checkStartCode();
        this.checkEndCode();
        this.checkLabel(label, false, "label");
        if (this.labels.get(label) != null) {
            throw new IllegalArgumentException("Already visited label");
        }
        this.labels.put(label, new Integer(this.labels.size()));
        this.mv.visitLabel(label);
    }
    
    public void visitLdcInsn(final Object o) {
        this.checkStartCode();
        this.checkEndCode();
        if (!(o instanceof Type)) {
            checkConstant(o);
        }
        this.mv.visitLdcInsn(o);
    }
    
    public void visitIincInsn(final int n, final int n2) {
        this.checkStartCode();
        this.checkEndCode();
        checkUnsignedShort(n, "Invalid variable index");
        checkSignedShort(n2, "Invalid increment");
        this.mv.visitIincInsn(n, n2);
    }
    
    public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label[] array) {
        this.checkStartCode();
        this.checkEndCode();
        if (n2 < n) {
            throw new IllegalArgumentException("Max = " + n2 + " must be greater than or equal to min = " + n);
        }
        this.checkLabel(label, false, "default label");
        checkNonDebugLabel(label);
        if (array == null || array.length != n2 - n + 1) {
            throw new IllegalArgumentException("There must be max - min + 1 labels");
        }
        for (int i = 0; i < array.length; ++i) {
            this.checkLabel(array[i], false, "label at index " + i);
            checkNonDebugLabel(array[i]);
        }
        this.mv.visitTableSwitchInsn(n, n2, label, array);
    }
    
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
        this.checkEndCode();
        this.checkStartCode();
        this.checkLabel(label, false, "default label");
        checkNonDebugLabel(label);
        if (array == null || array2 == null || array.length != array2.length) {
            throw new IllegalArgumentException("There must be the same number of keys and labels");
        }
        for (int i = 0; i < array2.length; ++i) {
            this.checkLabel(array2[i], false, "label at index " + i);
            checkNonDebugLabel(array2[i]);
        }
        this.mv.visitLookupSwitchInsn(label, array, array2);
    }
    
    public void visitMultiANewArrayInsn(final String s, final int n) {
        this.checkStartCode();
        this.checkEndCode();
        checkDesc(s, false);
        if (s.charAt(0) != '[') {
            throw new IllegalArgumentException("Invalid descriptor (must be an array type descriptor): " + s);
        }
        if (n < 1) {
            throw new IllegalArgumentException("Invalid dimensions (must be greater than 0): " + n);
        }
        if (n > s.lastIndexOf(91) + 1) {
            throw new IllegalArgumentException("Invalid dimensions (must not be greater than dims(desc)): " + n);
        }
        this.mv.visitMultiANewArrayInsn(s, n);
    }
    
    public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
        this.checkStartCode();
        this.checkEndCode();
        this.checkLabel(label, false, "start label");
        this.checkLabel(label2, false, "end label");
        this.checkLabel(label3, false, "handler label");
        checkNonDebugLabel(label);
        checkNonDebugLabel(label2);
        checkNonDebugLabel(label3);
        if (this.labels.get(label) != null || this.labels.get(label2) != null || this.labels.get(label3) != null) {
            throw new IllegalStateException("Try catch blocks must be visited before their labels");
        }
        if (s != null) {
            checkInternalName(s, "type");
        }
        this.mv.visitTryCatchBlock(label, label2, label3, s);
    }
    
    public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
        this.checkStartCode();
        this.checkEndCode();
        checkUnqualifiedName(this.version, s, "name");
        checkDesc(s2, false);
        this.checkLabel(label, true, "start label");
        this.checkLabel(label2, true, "end label");
        checkUnsignedShort(n, "Invalid variable index");
        if (this.labels.get(label2) < this.labels.get(label)) {
            throw new IllegalArgumentException("Invalid start and end labels (end must be greater than start)");
        }
        this.mv.visitLocalVariable(s, s2, s3, label, label2, n);
    }
    
    public void visitLineNumber(final int n, final Label label) {
        this.checkStartCode();
        this.checkEndCode();
        checkUnsignedShort(n, "Invalid line number");
        this.checkLabel(label, true, "start label");
        this.mv.visitLineNumber(n, label);
    }
    
    public void visitMaxs(final int n, final int n2) {
        this.checkStartCode();
        this.checkEndCode();
        this.endCode = true;
        checkUnsignedShort(n, "Invalid max stack");
        checkUnsignedShort(n2, "Invalid max locals");
        this.mv.visitMaxs(n, n2);
    }
    
    public void visitEnd() {
        this.checkEndMethod();
        this.endMethod = true;
        this.mv.visitEnd();
    }
    
    void checkStartCode() {
        if (!this.startCode) {
            throw new IllegalStateException("Cannot visit instructions before visitCode has been called.");
        }
    }
    
    void checkEndCode() {
        if (this.endCode) {
            throw new IllegalStateException("Cannot visit instructions after visitMaxs has been called.");
        }
    }
    
    void checkEndMethod() {
        if (this.endMethod) {
            throw new IllegalStateException("Cannot visit elements after visitEnd has been called.");
        }
    }
    
    static void checkFrameValue(final Object o) {
        if (o == Opcodes.TOP || o == Opcodes.INTEGER || o == Opcodes.FLOAT || o == Opcodes.LONG || o == Opcodes.DOUBLE || o == Opcodes.NULL || o == Opcodes.UNINITIALIZED_THIS) {
            return;
        }
        if (o instanceof String) {
            checkInternalName((String)o, "Invalid stack frame value");
            return;
        }
        if (!(o instanceof Label)) {
            throw new IllegalArgumentException("Invalid stack frame value: " + o);
        }
    }
    
    static void checkOpcode(final int n, final int n2) {
        if (n < 0 || n > 199 || CheckMethodAdapter.TYPE[n] != n2) {
            throw new IllegalArgumentException("Invalid opcode: " + n);
        }
    }
    
    static void checkSignedByte(final int n, final String s) {
        if (n < -128 || n > 127) {
            throw new IllegalArgumentException(s + " (must be a signed byte): " + n);
        }
    }
    
    static void checkSignedShort(final int n, final String s) {
        if (n < -32768 || n > 32767) {
            throw new IllegalArgumentException(s + " (must be a signed short): " + n);
        }
    }
    
    static void checkUnsignedShort(final int n, final String s) {
        if (n < 0 || n > 65535) {
            throw new IllegalArgumentException(s + " (must be an unsigned short): " + n);
        }
    }
    
    static void checkConstant(final Object o) {
        if (!(o instanceof Integer) && !(o instanceof Float) && !(o instanceof Long) && !(o instanceof Double) && !(o instanceof String)) {
            throw new IllegalArgumentException("Invalid constant: " + o);
        }
    }
    
    static void checkUnqualifiedName(final int n, final String s, final String s2) {
        if ((n & 0xFFFF) < 49) {
            checkIdentifier(s, s2);
        }
        else {
            for (int i = 0; i < s.length(); ++i) {
                if (".;[/".indexOf(s.charAt(i)) != -1) {
                    throw new IllegalArgumentException("Invalid " + s2 + " (must be a valid unqualified name): " + s);
                }
            }
        }
    }
    
    static void checkIdentifier(final String s, final String s2) {
        checkIdentifier(s, 0, -1, s2);
    }
    
    static void checkIdentifier(final String s, final int n, final int n2, final String s2) {
        if (s != null) {
            if (n2 == -1) {
                if (s.length() <= n) {
                    throw new IllegalArgumentException("Invalid " + s2 + " (must not be null or empty)");
                }
            }
            else if (n2 <= n) {
                throw new IllegalArgumentException("Invalid " + s2 + " (must not be null or empty)");
            }
            if (!Character.isJavaIdentifierStart(s.charAt(n))) {
                throw new IllegalArgumentException("Invalid " + s2 + " (must be a valid Java identifier): " + s);
            }
            for (int n3 = (n2 == -1) ? s.length() : n2, i = n + 1; i < n3; ++i) {
                if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                    throw new IllegalArgumentException("Invalid " + s2 + " (must be a valid Java identifier): " + s);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Invalid " + s2 + " (must not be null or empty)");
    }
    
    static void checkMethodIdentifier(final int n, final String s, final String s2) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid " + s2 + " (must not be null or empty)");
        }
        if ("<init>".equals(s) || "<clinit>".equals(s)) {
            return;
        }
        if ((n & 0xFFFF) >= 49) {
            for (int i = 0; i < s.length(); ++i) {
                if (".;[/<>".indexOf(s.charAt(i)) != -1) {
                    throw new IllegalArgumentException("Invalid " + s2 + " (must be a valid unqualified name): " + s);
                }
            }
            return;
        }
        if (!Character.isJavaIdentifierStart(s.charAt(0))) {
            throw new IllegalArgumentException("Invalid " + s2 + " (must be a '<init>', '<clinit>' or a valid Java identifier): " + s);
        }
        for (int j = 1; j < s.length(); ++j) {
            if (!Character.isJavaIdentifierPart(s.charAt(j))) {
                throw new IllegalArgumentException("Invalid " + s2 + " (must be '<init>' or '<clinit>' or a valid Java identifier): " + s);
            }
        }
    }
    
    static void checkInternalName(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid " + s2 + " (must not be null or empty)");
        }
        if (s.charAt(0) == '[') {
            checkDesc(s, false);
        }
        else {
            checkInternalName(s, 0, -1, s2);
        }
    }
    
    static void checkInternalName(final String s, final int n, final int n2, final String s2) {
        final int n3 = (n2 == -1) ? s.length() : n2;
        try {
            int n4 = n;
            int i;
            do {
                i = s.indexOf(47, n4 + 1);
                if (i == -1 || i > n3) {
                    i = n3;
                }
                checkIdentifier(s, n4, i, null);
                n4 = i + 1;
            } while (i != n3);
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid " + s2 + " (must be a fully qualified class name in internal form): " + s);
        }
    }
    
    static void checkDesc(final String s, final boolean b) {
        if (checkDesc(s, 0, b) != s.length()) {
            throw new IllegalArgumentException("Invalid descriptor: " + s);
        }
    }
    
    static int checkDesc(final String s, final int n, final boolean b) {
        if (s == null || n >= s.length()) {
            throw new IllegalArgumentException("Invalid type descriptor (must not be null or empty)");
        }
        switch (s.charAt(n)) {
            case 'V': {
                if (b) {
                    return n + 1;
                }
                throw new IllegalArgumentException("Invalid descriptor: " + s);
            }
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z': {
                return n + 1;
            }
            case '[': {
                int n2;
                for (n2 = n + 1; n2 < s.length() && s.charAt(n2) == '['; ++n2) {}
                if (n2 < s.length()) {
                    return checkDesc(s, n2, false);
                }
                throw new IllegalArgumentException("Invalid descriptor: " + s);
            }
            case 'L': {
                final int index = s.indexOf(59, n);
                if (index == -1 || index - n < 2) {
                    throw new IllegalArgumentException("Invalid descriptor: " + s);
                }
                try {
                    checkInternalName(s, n + 1, index, null);
                }
                catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Invalid descriptor: " + s);
                }
                return index + 1;
            }
            default: {
                throw new IllegalArgumentException("Invalid descriptor: " + s);
            }
        }
    }
    
    static void checkMethodDesc(final String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid method descriptor (must not be null or empty)");
        }
        if (s.charAt(0) != '(' || s.length() < 3) {
            throw new IllegalArgumentException("Invalid descriptor: " + s);
        }
        int checkDesc = 1;
        Label_0143: {
            if (s.charAt(checkDesc) != ')') {
                while (s.charAt(checkDesc) != 'V') {
                    checkDesc = checkDesc(s, checkDesc, false);
                    if (checkDesc >= s.length() || s.charAt(checkDesc) == ')') {
                        break Label_0143;
                    }
                }
                throw new IllegalArgumentException("Invalid descriptor: " + s);
            }
        }
        if (checkDesc(s, checkDesc + 1, true) != s.length()) {
            throw new IllegalArgumentException("Invalid descriptor: " + s);
        }
    }
    
    static void checkClassSignature(final String s) {
        int checkFormalTypeParameters = 0;
        if (getChar(s, 0) == '<') {
            checkFormalTypeParameters = checkFormalTypeParameters(s, checkFormalTypeParameters);
        }
        int n;
        for (n = checkClassTypeSignature(s, checkFormalTypeParameters); getChar(s, n) == 'L'; n = checkClassTypeSignature(s, n)) {}
        if (n != s.length()) {
            throw new IllegalArgumentException(s + ": error at index " + n);
        }
    }
    
    static void checkMethodSignature(final String s) {
        int checkFormalTypeParameters = 0;
        if (getChar(s, 0) == '<') {
            checkFormalTypeParameters = checkFormalTypeParameters(s, checkFormalTypeParameters);
        }
        int n;
        for (n = checkChar('(', s, checkFormalTypeParameters); "ZCBSIFJDL[T".indexOf(getChar(s, n)) != -1; n = checkTypeSignature(s, n)) {}
        int n2 = checkChar(')', s, n);
        if (getChar(s, n2) == 'V') {
            ++n2;
        }
        else {
            n2 = checkTypeSignature(s, n2);
        }
        while (getChar(s, n2) == '^') {
            ++n2;
            if (getChar(s, n2) == 'L') {
                n2 = checkClassTypeSignature(s, n2);
            }
            else {
                n2 = checkTypeVariableSignature(s, n2);
            }
        }
        if (n2 != s.length()) {
            throw new IllegalArgumentException(s + ": error at index " + n2);
        }
    }
    
    static void checkFieldSignature(final String s) {
        final int checkFieldTypeSignature = checkFieldTypeSignature(s, 0);
        if (checkFieldTypeSignature != s.length()) {
            throw new IllegalArgumentException(s + ": error at index " + checkFieldTypeSignature);
        }
    }
    
    private static int checkFormalTypeParameters(final String s, int n) {
        for (n = checkChar('<', s, n), n = checkFormalTypeParameter(s, n); getChar(s, n) != '>'; n = checkFormalTypeParameter(s, n)) {}
        return n + 1;
    }
    
    private static int checkFormalTypeParameter(final String s, int n) {
        n = checkIdentifier(s, n);
        n = checkChar(':', s, n);
        if ("L[T".indexOf(getChar(s, n)) != -1) {
            n = checkFieldTypeSignature(s, n);
        }
        while (getChar(s, n) == ':') {
            n = checkFieldTypeSignature(s, n + 1);
        }
        return n;
    }
    
    private static int checkFieldTypeSignature(final String s, final int n) {
        switch (getChar(s, n)) {
            case 'L': {
                return checkClassTypeSignature(s, n);
            }
            case '[': {
                return checkTypeSignature(s, n + 1);
            }
            default: {
                return checkTypeVariableSignature(s, n);
            }
        }
    }
    
    private static int checkClassTypeSignature(final String s, int n) {
        for (n = checkChar('L', s, n), n = checkIdentifier(s, n); getChar(s, n) == '/'; n = checkIdentifier(s, n + 1)) {}
        if (getChar(s, n) == '<') {
            n = checkTypeArguments(s, n);
        }
        while (getChar(s, n) == '.') {
            n = checkIdentifier(s, n + 1);
            if (getChar(s, n) == '<') {
                n = checkTypeArguments(s, n);
            }
        }
        return checkChar(';', s, n);
    }
    
    private static int checkTypeArguments(final String s, int n) {
        for (n = checkChar('<', s, n), n = checkTypeArgument(s, n); getChar(s, n) != '>'; n = checkTypeArgument(s, n)) {}
        return n + 1;
    }
    
    private static int checkTypeArgument(final String s, int n) {
        final char char1 = getChar(s, n);
        if (char1 == '*') {
            return n + 1;
        }
        if (char1 == '+' || char1 == '-') {
            ++n;
        }
        return checkFieldTypeSignature(s, n);
    }
    
    private static int checkTypeVariableSignature(final String s, int n) {
        n = checkChar('T', s, n);
        n = checkIdentifier(s, n);
        return checkChar(';', s, n);
    }
    
    private static int checkTypeSignature(final String s, final int n) {
        switch (getChar(s, n)) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z': {
                return n + 1;
            }
            default: {
                return checkFieldTypeSignature(s, n);
            }
        }
    }
    
    private static int checkIdentifier(final String s, int n) {
        if (!Character.isJavaIdentifierStart(getChar(s, n))) {
            throw new IllegalArgumentException(s + ": identifier expected at index " + n);
        }
        ++n;
        while (Character.isJavaIdentifierPart(getChar(s, n))) {
            ++n;
        }
        return n;
    }
    
    private static int checkChar(final char c, final String s, final int n) {
        if (getChar(s, n) == c) {
            return n + 1;
        }
        throw new IllegalArgumentException(s + ": '" + c + "' expected at index " + n);
    }
    
    private static char getChar(final String s, final int n) {
        return (n < s.length()) ? s.charAt(n) : '\0';
    }
    
    void checkLabel(final Label label, final boolean b, final String s) {
        if (label == null) {
            throw new IllegalArgumentException("Invalid " + s + " (must not be null)");
        }
        if (b && this.labels.get(label) == null) {
            throw new IllegalArgumentException("Invalid " + s + " (must be visited first)");
        }
    }
    
    private static void checkNonDebugLabel(final Label label) {
        final Field labelStatusField = getLabelStatusField();
        int n;
        try {
            n = (int)((labelStatusField == null) ? 0 : labelStatusField.get(label));
        }
        catch (IllegalAccessException ex) {
            throw new Error("Internal error");
        }
        if ((n & 0x1) != 0x0) {
            throw new IllegalArgumentException("Labels used for debug info cannot be reused for control flow");
        }
    }
    
    private static Field getLabelStatusField() {
        if (CheckMethodAdapter.labelStatusField == null) {
            CheckMethodAdapter.labelStatusField = getLabelField("a");
            if (CheckMethodAdapter.labelStatusField == null) {
                CheckMethodAdapter.labelStatusField = getLabelField("status");
            }
        }
        return CheckMethodAdapter.labelStatusField;
    }
    
    private static Field getLabelField(final String s) {
        try {
            final Field declaredField = ((CheckMethodAdapter.class$org$objectweb$asm$Label == null) ? (CheckMethodAdapter.class$org$objectweb$asm$Label = class$("org.jruby.org.objectweb.asm.Label")) : CheckMethodAdapter.class$org$objectweb$asm$Label).getDeclaredField(s);
            declaredField.setAccessible(true);
            return declaredField;
        }
        catch (NoSuchFieldException ex) {
            return null;
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String s = "BBBBBBBBBBBBBBBBCCIAADDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBDDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBJBBBBBBBBBBBBBBBBBBBBHHHHHHHHHHHHHHHHDKLBBBBBBFFFFGGGGGECEBBEEBBAMHHAA";
        TYPE = new int[s.length()];
        for (int i = 0; i < CheckMethodAdapter.TYPE.length; ++i) {
            CheckMethodAdapter.TYPE[i] = s.charAt(i) - 'A' - '\u0001';
        }
    }
}
