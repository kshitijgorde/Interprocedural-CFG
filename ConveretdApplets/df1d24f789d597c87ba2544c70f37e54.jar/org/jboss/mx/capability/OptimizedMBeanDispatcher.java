// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.capability;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.bcel.generic.DLOAD;
import org.apache.bcel.generic.DSTORE;
import org.apache.bcel.generic.FLOAD;
import org.apache.bcel.generic.FSTORE;
import org.apache.bcel.generic.LLOAD;
import org.apache.bcel.generic.LSTORE;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.ISTORE;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanException;
import javax.management.ReflectionException;
import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.ACONST_NULL;
import org.apache.bcel.generic.ARETURN;
import org.apache.bcel.generic.AALOAD;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IFNULL;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.RETURN;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import javax.management.MBeanParameterInfo;
import java.lang.reflect.Constructor;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ConstantPoolGen;
import org.jboss.mx.metadata.AttributeOperationResolver;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import org.apache.bcel.generic.ClassGen;
import javax.management.MBeanInfo;
import org.jboss.mx.server.ServerConstants;

public class OptimizedMBeanDispatcher implements ServerConstants
{
    static final Class SUPER_CLASS;
    
    public static ReflectedMBeanDispatcher create(final MBeanInfo info, final Object resource) {
        try {
            final String className = resource.getClass().getName().replace('.', '_') + "_Dispatcher";
            final String superClass = OptimizedMBeanDispatcher.SUPER_CLASS.getName();
            final String fileName = className + ".class";
            final int modifiers = 1;
            final String[] interfaces = new String[0];
            final ClassGen clazz = new ClassGen(className, superClass, fileName, modifiers, interfaces);
            final ConstantPoolGen cp = clazz.getConstantPool();
            clazz.addMethod(createConstructor(cp, className).getMethod());
            clazz.addMethod(createInvoke(cp, info, className, resource.getClass().getName()).getMethod());
            clazz.update();
            final JavaClass c = clazz.getJavaClass();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream(2000);
            final BufferedOutputStream bos = new BufferedOutputStream(baos);
            c.dump((OutputStream)bos);
            final ClassLoader ocl = new DispatchClassLoader(resource.getClass().getClassLoader(), className, baos.toByteArray());
            final Class dispatcherClass = ocl.loadClass(className);
            final Constructor constr = dispatcherClass.getConstructor(MBeanInfo.class, AttributeOperationResolver.class, Object.class);
            final Object o = constr.newInstance(info, new AttributeOperationResolver(info), resource);
            return (ReflectedMBeanDispatcher)o;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Error();
        }
    }
    
    public static String getMethodDescriptor(final MBeanParameterInfo[] signature, final String returnType) {
        final StringBuffer sign = new StringBuffer(256);
        sign.append("(");
        for (int i = 0; i < signature.length; ++i) {
            sign.append(getDescriptorForType(signature[i].getName()));
        }
        sign.append(")" + getDescriptorForType(returnType));
        return sign.toString();
    }
    
    public static String getDescriptorForType(final String name) {
        if (name.equals(Byte.TYPE.getName())) {
            return "B";
        }
        if (name.equals(Character.TYPE.getName())) {
            return "C";
        }
        if (name.equals(Double.TYPE.getName())) {
            return "D";
        }
        if (name.equals(Float.TYPE.getName())) {
            return "F";
        }
        if (name.equals(Integer.TYPE.getName())) {
            return "I";
        }
        if (name.equals(Long.TYPE.getName())) {
            return "J";
        }
        if (name.equals(Short.TYPE.getName())) {
            return "S";
        }
        if (name.equals(Boolean.TYPE.getName())) {
            return "Z";
        }
        if (name.equals(Void.TYPE.getName())) {
            return "V";
        }
        if (name.startsWith("[")) {
            return name.replace('.', '/');
        }
        return "L" + name.replace('.', '/') + ";";
    }
    
    public static boolean isPrimitive(final String name) {
        return name.equals(Byte.TYPE.getName()) || name.equals(Character.TYPE.getName()) || name.equals(Double.TYPE.getName()) || name.equals(Float.TYPE.getName()) || name.equals(Integer.TYPE.getName()) || name.equals(Long.TYPE.getName()) || name.equals(Short.TYPE.getName()) || name.equals(Boolean.TYPE.getName());
    }
    
    protected static MethodGen createConstructor(final ConstantPoolGen cp, final String className) {
        final InstructionList constrInstructions = new InstructionList();
        final int constrRefIndex = cp.addMethodref(OptimizedMBeanDispatcher.SUPER_CLASS.getName(), "<init>", "(" + getDescriptorForType(MBeanInfo.class.getName()) + getDescriptorForType(AttributeOperationResolver.class.getName()) + getDescriptorForType(Object.class.getName()) + ")V");
        constrInstructions.append((Instruction)new ALOAD(0));
        constrInstructions.append((Instruction)new ALOAD(1));
        constrInstructions.append((Instruction)new ALOAD(2));
        constrInstructions.append((Instruction)new ALOAD(3));
        constrInstructions.append((Instruction)new INVOKESPECIAL(constrRefIndex));
        constrInstructions.append((Instruction)new RETURN());
        final MethodGen constrMethod = new MethodGen(1, (Type)Type.VOID, new Type[] { new ObjectType(MBeanInfo.class.getName()), new ObjectType(AttributeOperationResolver.class.getName()), new ObjectType(Object.class.getName()) }, new String[] { "info", "resolver", "resource" }, "<init>", className, constrInstructions, cp);
        constrMethod.setMaxStack(4);
        return constrMethod;
    }
    
    protected static MethodGen createInvoke(final ConstantPoolGen cp, final MBeanInfo info, final String className, final String resourceClassName) {
        final InstructionList invokeInstructions = new InstructionList();
        final MethodEntry[] operations = getOperations(info);
        for (int i = 0; i < operations.length; ++i) {
            operations[i].nameIndexInCP = cp.addString(operations[i].getName());
            operations[i].methodIndexInCP = cp.addMethodref(resourceClassName, operations[i].getName(), operations[i].methodDescriptor);
        }
        final int invokeIndex = cp.addMethodref(OptimizedMBeanDispatcher.SUPER_CLASS.getName(), "invoke", "(" + getDescriptorForType(String.class.getName()) + getDescriptorForType(Object[].class.getName()) + getDescriptorForType(String[].class.getName()) + ")" + getDescriptorForType(Object.class.getName()));
        final int getResourceObjectIndex = cp.addMethodref(OptimizedMBeanDispatcher.SUPER_CLASS.getName(), "getResourceObject", "()Ljava/lang/Object;");
        final int strEqualsIndex = cp.addMethodref(String.class.getName(), "equals", "(Ljava/lang/Object;)Z");
        InstructionHandle beginTryBlock = null;
        InstructionHandle endTryBlock = null;
        final IFNULL ifOperationEqualsNull = new IFNULL((InstructionHandle)null);
        IFEQ operationElseIfBranch = null;
        if (operations.length > 0) {
            invokeInstructions.append((Instruction)new ALOAD(1));
            beginTryBlock = (InstructionHandle)invokeInstructions.append((BranchInstruction)ifOperationEqualsNull);
            for (int j = 0; j < operations.length; ++j) {
                final InstructionHandle jumpToNextElse = invokeInstructions.append((Instruction)new ALOAD(1));
                invokeInstructions.append((Instruction)new LDC(operations[j].nameIndexInCP));
                invokeInstructions.append((Instruction)new INVOKEVIRTUAL(strEqualsIndex));
                if (operationElseIfBranch != null) {
                    operationElseIfBranch.setTarget(jumpToNextElse);
                }
                operationElseIfBranch = new IFEQ((InstructionHandle)null);
                invokeInstructions.append((BranchInstruction)operationElseIfBranch);
                invokeInstructions.append((Instruction)new ALOAD(0));
                invokeInstructions.append((Instruction)new INVOKEVIRTUAL(getResourceObjectIndex));
                int x = cp.addClass(resourceClassName);
                invokeInstructions.append((Instruction)new CHECKCAST(x));
                if (operations[j].getSignature().length > 0) {
                    for (int arrayIndex = 0; arrayIndex < operations[j].getSignature().length; ++arrayIndex) {
                        invokeInstructions.append((Instruction)new ALOAD(2));
                        invokeInstructions.append((CompoundInstruction)new PUSH(cp, arrayIndex));
                        invokeInstructions.append((Instruction)new AALOAD());
                        final String type = operations[j].getSignature()[arrayIndex].getName();
                        if (isPrimitive(type)) {
                            invokeInstructions.append(convertObjectToPrimitive(cp, type));
                        }
                        else {
                            x = cp.addClass(type);
                            invokeInstructions.append((Instruction)new CHECKCAST(x));
                        }
                    }
                }
                x = operations[j].methodIndexInCP;
                invokeInstructions.append((Instruction)new INVOKEVIRTUAL(x));
                final String type2 = operations[j].getReturnType();
                if (isPrimitive(type2)) {
                    invokeInstructions.append(convertPrimitiveToObject(cp, type2));
                    invokeInstructions.append((Instruction)new ARETURN());
                }
                else if (type2.equals(Void.TYPE.getName())) {
                    invokeInstructions.append((Instruction)new ACONST_NULL());
                    invokeInstructions.append((Instruction)new ARETURN());
                }
                else {
                    invokeInstructions.append((Instruction)new ARETURN());
                }
            }
        }
        final InstructionHandle jumpToSuperInvoke = invokeInstructions.append((Instruction)new ALOAD(0));
        invokeInstructions.append((Instruction)new ALOAD(1));
        invokeInstructions.append((Instruction)new ALOAD(2));
        invokeInstructions.append((Instruction)new ALOAD(3));
        invokeInstructions.append((Instruction)new INVOKESPECIAL(invokeIndex));
        invokeInstructions.append((Instruction)new ARETURN());
        ifOperationEqualsNull.setTarget(jumpToSuperInvoke);
        if (operations.length > 0) {
            if (operationElseIfBranch != null) {
                operationElseIfBranch.setTarget(jumpToSuperInvoke);
            }
            beginTryBlock = beginTryBlock.getNext();
            endTryBlock = jumpToSuperInvoke.getPrev();
        }
        final InstructionHandle exceptionHandlerCode = invokeInstructions.append((Instruction)new ALOAD(0));
        invokeInstructions.append((Instruction)new ALOAD(1));
        invokeInstructions.append((Instruction)new ALOAD(2));
        invokeInstructions.append((Instruction)new ALOAD(3));
        invokeInstructions.append((Instruction)new INVOKESPECIAL(invokeIndex));
        invokeInstructions.append((Instruction)new ARETURN());
        final MethodGen invokeMethod = new MethodGen(1, (Type)Type.OBJECT, new Type[] { Type.STRING, new ArrayType(Object.class.getName(), 1), new ArrayType(String.class.getName(), 1) }, new String[] { "operationName", "args", "signature" }, "invoke", className, invokeInstructions, cp);
        invokeMethod.setMaxLocals(7);
        invokeMethod.setMaxStack(calculateMaxStackSize(info));
        invokeMethod.addException(ReflectionException.class.getName());
        invokeMethod.addException(MBeanException.class.getName());
        if (operations.length > 0) {
            invokeMethod.addExceptionHandler(beginTryBlock, endTryBlock, exceptionHandlerCode, new ObjectType("java.lang.Throwable"));
        }
        return invokeMethod;
    }
    
    private static int calculateMaxStackSize(final MBeanInfo info) {
        final MBeanOperationInfo[] operations = info.getOperations();
        int maxSize = 7;
        for (int i = 0; i < operations.length; ++i) {
            if (operations[i].getSignature().length > maxSize + 2) {
                maxSize = operations[i].getSignature().length + 2;
            }
        }
        return maxSize;
    }
    
    protected static InstructionList convertObjectToPrimitive(final ConstantPoolGen cp, final String type) {
        final InstructionList il = new InstructionList();
        final int intValueIndex = cp.addMethodref(Integer.class.getName(), "intValue", "()I");
        final int byteValueIndex = cp.addMethodref(Byte.class.getName(), "byteValue", "()B");
        final int charValueIndex = cp.addMethodref(Character.class.getName(), "charValue", "()C");
        final int doubleValueIndex = cp.addMethodref(Double.class.getName(), "doubleValue", "()D");
        final int floatValueIndex = cp.addMethodref(Float.class.getName(), "floatValue", "()F");
        final int longValueIndex = cp.addMethodref(Long.class.getName(), "longValue", "()J");
        final int shortValueIndex = cp.addMethodref(Short.class.getName(), "shortValue", "()S");
        final int booleanValueIndex = cp.addMethodref(Boolean.class.getName(), "booleanValue", "()Z");
        if (type.equals(Integer.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Integer");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(intValueIndex));
        }
        else if (type.equals(Byte.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Byte");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(byteValueIndex));
        }
        else if (type.equals(Character.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Character");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(charValueIndex));
        }
        else if (type.equals(Double.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Double");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(doubleValueIndex));
        }
        else if (type.equals(Float.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Float");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(floatValueIndex));
        }
        else if (type.equals(Long.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Long");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(longValueIndex));
        }
        else if (type.equals(Short.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Short");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(shortValueIndex));
        }
        else if (type.equals(Boolean.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Boolean");
            il.append((Instruction)new CHECKCAST(x));
            il.append((Instruction)new INVOKEVIRTUAL(booleanValueIndex));
        }
        return il;
    }
    
    protected static InstructionList convertPrimitiveToObject(final ConstantPoolGen cp, final String type) {
        final InstructionList il = new InstructionList();
        if (type.equals(Boolean.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Boolean");
            final int constrIndex = cp.addMethodref("java.lang.Boolean", "<init>", "(B)V");
            il.append((Instruction)new ISTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(5));
            il.append((Instruction)new ALOAD(5));
            il.append((Instruction)new ILOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(5));
        }
        else if (type.equals(Short.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Short");
            final int constrIndex = cp.addMethodref("java.lang.Short", "<init>", "(S)V");
            il.append((Instruction)new ISTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(5));
            il.append((Instruction)new ALOAD(5));
            il.append((Instruction)new ILOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(5));
        }
        else if (type.equals(Long.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Long");
            final int constrIndex = cp.addMethodref("java.lang.Long", "<init>", "(J)V");
            il.append((Instruction)new LSTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(6));
            il.append((Instruction)new ALOAD(6));
            il.append((Instruction)new LLOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(6));
        }
        else if (type.equals(Integer.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Integer");
            final int constrIndex = cp.addMethodref("java.lang.Integer", "<init>", "(I)V");
            il.append((Instruction)new ISTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(5));
            il.append((Instruction)new ALOAD(5));
            il.append((Instruction)new ILOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(5));
        }
        else if (type.equals(Float.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Float");
            final int constrIndex = cp.addMethodref("java.lang.Float", "<init>", "(F)V");
            il.append((Instruction)new FSTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(5));
            il.append((Instruction)new ALOAD(5));
            il.append((Instruction)new FLOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(5));
        }
        else if (type.equals(Double.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Double");
            final int constrIndex = cp.addMethodref("java.lang.Double", "<init>", "(D)V");
            il.append((Instruction)new DSTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(6));
            il.append((Instruction)new ALOAD(6));
            il.append((Instruction)new DLOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(6));
        }
        else if (type.equals(Character.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Character");
            final int constrIndex = cp.addMethodref("java.lang.Character", "<init>", "(C)V");
            il.append((Instruction)new ISTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(5));
            il.append((Instruction)new ALOAD(5));
            il.append((Instruction)new ILOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(5));
        }
        else if (type.equals(Byte.TYPE.getName())) {
            final int x = cp.addClass("java.lang.Byte");
            final int constrIndex = cp.addMethodref("java.lang.Byte", "<init>", "(B)V");
            il.append((Instruction)new ISTORE(4));
            il.append((Instruction)new NEW(x));
            il.append((Instruction)new ASTORE(5));
            il.append((Instruction)new ALOAD(5));
            il.append((Instruction)new ILOAD(4));
            il.append((Instruction)new INVOKESPECIAL(constrIndex));
            il.append((Instruction)new ALOAD(5));
        }
        return il;
    }
    
    protected static MethodEntry[] getOperations(final MBeanInfo info) {
        final HashMap operationMap = new HashMap();
        final ArrayList overloadList = new ArrayList();
        final MBeanOperationInfo[] operations = info.getOperations();
        for (int i = 0; i < operations.length; ++i) {
            final String methodName = operations[i].getName();
            if (operationMap.containsKey(methodName)) {
                overloadList.add(methodName);
            }
            else {
                operationMap.put(methodName, new MethodEntry(operations[i]));
            }
        }
        final Iterator it = overloadList.iterator();
        while (it.hasNext()) {
            operationMap.remove(it.next());
        }
        return (MethodEntry[])operationMap.values().toArray(new MethodEntry[0]);
    }
    
    static {
        SUPER_CLASS = ReflectedMBeanDispatcher.class;
    }
    
    private static class MethodEntry extends MBeanOperationInfo
    {
        private static final long serialVersionUID = 1792631947840418314L;
        String methodDescriptor;
        int nameIndexInCP;
        int methodIndexInCP;
        
        public MethodEntry(final MBeanOperationInfo info) {
            super(info.getName(), info.getDescription(), info.getSignature(), info.getReturnType(), info.getImpact());
            this.methodDescriptor = null;
            this.nameIndexInCP = -1;
            this.methodIndexInCP = -1;
            this.methodDescriptor = OptimizedMBeanDispatcher.getMethodDescriptor(info.getSignature(), info.getReturnType());
        }
    }
}
