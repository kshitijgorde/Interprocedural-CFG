// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import java.io.IOException;
import java.io.FileOutputStream;
import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.org.objectweb.asm.ClassWriter;
import org.joni.constants.AsmConstants;
import org.jruby.org.objectweb.asm.Opcodes;

abstract class AsmCompilerSupport extends Compiler implements Opcodes, AsmConstants
{
    protected ClassWriter factory;
    protected MethodVisitor factoryInit;
    protected String factoryName;
    protected ClassWriter machine;
    protected MethodVisitor machineInit;
    protected MethodVisitor match;
    protected String machineName;
    protected int maxStack;
    protected int maxVars;
    protected int bitsets;
    protected int ranges;
    protected int templates;
    static int REG_NUM;
    private static final DummyClassLoader loader;
    
    AsmCompilerSupport(final Analyser analyser) {
        super(analyser);
        this.maxStack = 1;
        this.maxVars = 6;
    }
    
    protected final void prepareFactory() {
        this.factory = new ClassWriter(1);
        this.factoryName = "org/joni/MatcherFactory" + AsmCompilerSupport.REG_NUM;
        this.factory.visit(48, 17, this.factoryName, null, "org/joni/MatcherFactory", null);
        final MethodVisitor create = this.factory.visitMethod(4096, "create", "(Lorg/joni/Regex;[BII)Lorg/joni/Matcher;", null, null);
        create.visitTypeInsn(187, this.machineName);
        create.visitInsn(89);
        create.visitVarInsn(25, 1);
        create.visitVarInsn(25, 2);
        create.visitVarInsn(21, 3);
        create.visitVarInsn(21, 4);
        create.visitMethodInsn(183, this.machineName, "<init>", "(Lorg/joni/Regex;[BII)V");
        create.visitInsn(176);
        create.visitMaxs(0, 0);
        create.visitEnd();
    }
    
    protected final void prepareFactoryInit() {
        (this.factoryInit = this.factory.visitMethod(1, "<init>", "()V", null, null)).visitVarInsn(25, 0);
        this.factoryInit.visitMethodInsn(183, "org/joni/MatcherFactory", "<init>", "()V");
    }
    
    protected final void setupFactoryInit() {
        this.factoryInit.visitInsn(177);
        this.factoryInit.visitMaxs(0, 0);
        this.factoryInit.visitEnd();
    }
    
    protected final void prepareMachine() {
        this.machine = new ClassWriter(1);
        this.machineName = "org/joni/NativeMachine" + AsmCompilerSupport.REG_NUM;
    }
    
    protected final void prepareMachineInit() {
        this.machine.visit(48, 17, this.machineName, null, "org/joni/NativeMachine", null);
        (this.machineInit = this.machine.visitMethod(4, "<init>", "(Lorg/joni/Regex;[BII)V", null, null)).visitVarInsn(25, 0);
        this.machineInit.visitVarInsn(25, 1);
        this.machineInit.visitVarInsn(25, 2);
        this.machineInit.visitVarInsn(21, 3);
        this.machineInit.visitVarInsn(21, 4);
        this.machineInit.visitMethodInsn(183, "org/joni/NativeMachine", "<init>", "(Lorg/joni/Regex;[BII)V");
    }
    
    protected final void setupMachineInit() {
        if (this.bitsets + this.ranges + this.templates > 0) {
            this.machine.visitField(18, "factory", "L" + this.factoryName + ";", null, null);
            this.machineInit.visitVarInsn(25, 0);
            this.machineInit.visitVarInsn(25, 1);
            this.machineInit.visitFieldInsn(180, "org/joni/Regex", "factory", "Lorg/joni/MatcherFactory;");
            this.machineInit.visitTypeInsn(192, this.factoryName);
            this.machineInit.visitFieldInsn(181, this.machineName, "factory", "L" + this.factoryName + ";");
        }
        this.machineInit.visitInsn(177);
        this.machineInit.visitMaxs(0, 0);
        this.machineInit.visitEnd();
    }
    
    protected final void prepareMachineMatch() {
        this.match = this.machine.visitMethod(4096, "matchAt", "(III)I", null, null);
        this.move(4, 2);
        this.load("bytes", "[B");
        this.astore(5);
    }
    
    protected final void setupMachineMatch() {
        this.match.visitInsn(2);
        this.match.visitInsn(172);
        this.match.visitMaxs(this.maxStack, this.maxVars);
        this.match.visitEnd();
    }
    
    protected final void setupClasses() {
        final byte[] factoryCode = this.factory.toByteArray();
        final byte[] machineCode = this.machine.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(this.factoryName.substring(this.factoryName.lastIndexOf(47) + 1) + ".class");
            fos.write(factoryCode);
            fos.close();
            fos = new FileOutputStream(this.machineName.substring(this.machineName.lastIndexOf(47) + 1) + ".class");
            fos.write(machineCode);
            fos.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace(Config.err);
        }
        AsmCompilerSupport.loader.defineClass(this.machineName.replace('/', '.'), machineCode);
        final Class<?> cls = AsmCompilerSupport.loader.defineClass(this.factoryName.replace('/', '.'), factoryCode);
        try {
            this.regex.factory = (MatcherFactory)cls.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(Config.err);
        }
    }
    
    protected final void aload(final int var) {
        this.match.visitVarInsn(25, var);
    }
    
    protected final void astore(final int var) {
        this.match.visitVarInsn(58, var);
    }
    
    protected final void loadThis() {
        this.match.visitVarInsn(25, 0);
    }
    
    protected final void load(final int var) {
        this.match.visitVarInsn(21, var);
    }
    
    protected final void store(final int var) {
        this.match.visitVarInsn(54, var);
    }
    
    protected final void move(final int to, final int from) {
        this.load(from);
        this.store(to);
    }
    
    protected final void load(final String field, final String singature) {
        this.loadThis();
        this.match.visitFieldInsn(180, this.machineName, field, singature);
    }
    
    protected final void load(final String field) {
        this.load(field, "I");
    }
    
    protected final void store(final String field, final String singature) {
        this.loadThis();
        this.match.visitFieldInsn(181, this.machineName, field, singature);
    }
    
    protected final void store(final String field) {
        this.store(field, "I");
    }
    
    protected final String installTemplate(final byte[] arr, final int p, final int length) {
        final String templateName = "template" + ++this.templates;
        this.installArray(templateName, arr, p, length);
        return templateName;
    }
    
    protected final String installCodeRange(final int[] arr) {
        final String coreRangeName = "range" + ++this.ranges;
        this.installArray(coreRangeName, arr);
        return coreRangeName;
    }
    
    protected final String installBitSet(final int[] arr) {
        final String bitsetName = "bitset" + ++this.bitsets;
        this.installArray(bitsetName, arr);
        return bitsetName;
    }
    
    private void installArray(final String name, final int[] arr) {
        this.factory.visitField(18, name, "[I", null, null);
        this.factoryInit.visitVarInsn(25, 0);
        this.loadInt(this.factoryInit, arr.length);
        this.factoryInit.visitIntInsn(188, 10);
        for (int i = 0; i < arr.length; ++i) {
            this.buildArray(i, arr[i], 79);
        }
        this.factoryInit.visitFieldInsn(181, this.factoryName, name, "[I");
    }
    
    private void installArray(final String name, final byte[] arr, final int p, final int length) {
        this.factory.visitField(18, name, "[B", null, null);
        this.factoryInit.visitVarInsn(25, 0);
        this.loadInt(this.factoryInit, arr.length);
        this.factoryInit.visitIntInsn(188, 8);
        for (int i = p, j = 0; i < p + length; ++i, ++j) {
            this.buildArray(j, arr[i] & 0xFF, 84);
        }
        this.factoryInit.visitFieldInsn(181, this.factoryName, name, "[B");
    }
    
    private void buildArray(final int index, final int value, final int type) {
        this.factoryInit.visitInsn(89);
        this.loadInt(this.factoryInit, index);
        this.loadInt(this.factoryInit, value);
        this.factoryInit.visitInsn(type);
    }
    
    private void loadInt(final MethodVisitor mv, final int value) {
        if (value >= -1 && value <= 5) {
            mv.visitInsn(value + 3);
        }
        else if ((value >= 6 && value <= 127) || (value >= -128 && value <= -2)) {
            mv.visitIntInsn(16, value);
        }
        else if ((value >= 128 && value <= 32767) || (value >= -32768 && value <= -129)) {
            mv.visitIntInsn(17, value);
        }
        else {
            mv.visitLdcInsn(new Integer(value));
        }
    }
    
    static {
        AsmCompilerSupport.REG_NUM = 0;
        loader = new DummyClassLoader();
    }
    
    private static final class DummyClassLoader extends ClassLoader
    {
        public Class<?> defineClass(final String name, final byte[] bytes) {
            return super.defineClass(name, bytes, 0, bytes.length);
        }
    }
}
