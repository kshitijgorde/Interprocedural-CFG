// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.BranchHandle;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import com.ibm.xslt4j.bcel.generic.GOTO;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.IFNULL;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;

public final class ObjectType extends Type
{
    private String _javaClassName;
    private Class _clazz;
    static /* synthetic */ Class class$java$lang$Object;
    
    protected ObjectType(final String javaClassName) {
        this._javaClassName = "java.lang.Object";
        this._clazz = ((ObjectType.class$java$lang$Object == null) ? (ObjectType.class$java$lang$Object = class$("java.lang.Object")) : ObjectType.class$java$lang$Object);
        this._javaClassName = javaClassName;
        try {
            this._clazz = ObjectFactory.findProviderClass(javaClassName, ObjectFactory.findClassLoader(), true);
        }
        catch (ClassNotFoundException e) {
            this._clazz = null;
        }
    }
    
    protected ObjectType(final Class clazz) {
        this._javaClassName = "java.lang.Object";
        this._clazz = ((ObjectType.class$java$lang$Object == null) ? (ObjectType.class$java$lang$Object = class$("java.lang.Object")) : ObjectType.class$java$lang$Object);
        this._clazz = clazz;
        this._javaClassName = clazz.getName();
    }
    
    public int hashCode() {
        return ((ObjectType.class$java$lang$Object == null) ? (ObjectType.class$java$lang$Object = class$("java.lang.Object")) : ObjectType.class$java$lang$Object).hashCode();
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof ObjectType;
    }
    
    public String getJavaClassName() {
        return this._javaClassName;
    }
    
    public Class getJavaClass() {
        return this._clazz;
    }
    
    public String toString() {
        return this._javaClassName;
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        final StringBuffer result = new StringBuffer("L");
        result.append(this._javaClassName.replace('.', '/')).append(';');
        return result.toString();
    }
    
    public com.ibm.xslt4j.bcel.generic.Type toJCType() {
        return Util.getJCRefType(this.toSignature());
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.String) {
            this.translateTo(classGen, methodGen, (StringType)type);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(InstructionConstants.DUP);
        final BranchHandle ifNull = il.append(new IFNULL(null));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(this._javaClassName, "toString", "()Ljava/lang/String;")));
        final BranchHandle gotobh = il.append(new GOTO(null));
        ifNull.setTarget(il.append(InstructionConstants.POP));
        il.append(new PUSH(cpg, ""));
        gotobh.setTarget(il.append(InstructionConstants.NOP));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        if (clazz.isAssignableFrom(this._clazz)) {
            methodGen.getInstructionList().append(InstructionConstants.NOP);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getClass().toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateFrom(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public Instruction LOAD(final int slot) {
        return new ALOAD(slot);
    }
    
    public Instruction STORE(final int slot) {
        return new ASTORE(slot);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
