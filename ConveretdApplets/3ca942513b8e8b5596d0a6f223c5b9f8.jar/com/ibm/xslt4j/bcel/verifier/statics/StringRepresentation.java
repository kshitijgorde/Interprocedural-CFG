// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.statics;

import com.ibm.xslt4j.bcel.classfile.Unknown;
import com.ibm.xslt4j.bcel.classfile.Synthetic;
import com.ibm.xslt4j.bcel.classfile.SourceFile;
import com.ibm.xslt4j.bcel.classfile.Method;
import com.ibm.xslt4j.bcel.classfile.LocalVariableTable;
import com.ibm.xslt4j.bcel.classfile.LocalVariable;
import com.ibm.xslt4j.bcel.classfile.LineNumberTable;
import com.ibm.xslt4j.bcel.classfile.LineNumber;
import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.classfile.InnerClasses;
import com.ibm.xslt4j.bcel.classfile.InnerClass;
import com.ibm.xslt4j.bcel.classfile.Field;
import com.ibm.xslt4j.bcel.classfile.ExceptionTable;
import com.ibm.xslt4j.bcel.classfile.Deprecated;
import com.ibm.xslt4j.bcel.classfile.ConstantValue;
import com.ibm.xslt4j.bcel.classfile.ConstantUtf8;
import com.ibm.xslt4j.bcel.classfile.ConstantString;
import com.ibm.xslt4j.bcel.classfile.ConstantPool;
import com.ibm.xslt4j.bcel.classfile.ConstantNameAndType;
import com.ibm.xslt4j.bcel.classfile.ConstantMethodref;
import com.ibm.xslt4j.bcel.classfile.ConstantLong;
import com.ibm.xslt4j.bcel.classfile.ConstantInterfaceMethodref;
import com.ibm.xslt4j.bcel.classfile.ConstantInteger;
import com.ibm.xslt4j.bcel.classfile.ConstantFloat;
import com.ibm.xslt4j.bcel.classfile.ConstantFieldref;
import com.ibm.xslt4j.bcel.classfile.ConstantDouble;
import com.ibm.xslt4j.bcel.classfile.ConstantClass;
import com.ibm.xslt4j.bcel.classfile.CodeException;
import com.ibm.xslt4j.bcel.classfile.Code;
import com.ibm.xslt4j.bcel.classfile.Node;
import com.ibm.xslt4j.bcel.classfile.Visitor;
import com.ibm.xslt4j.bcel.classfile.EmptyVisitor;

public class StringRepresentation extends EmptyVisitor implements Visitor
{
    private String tostring;
    
    public StringRepresentation(final Node n) {
        n.accept(this);
    }
    
    public String toString() {
        return this.tostring;
    }
    
    private String toString(final Node obj) {
        String ret;
        try {
            ret = obj.toString();
        }
        catch (RuntimeException e) {
            String s = obj.getClass().getName();
            s = s.substring(s.lastIndexOf(".") + 1);
            ret = "<<" + s + ">>";
        }
        return ret;
    }
    
    public void visitCode(final Code obj) {
        this.tostring = "<CODE>";
    }
    
    public void visitCodeException(final CodeException obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantClass(final ConstantClass obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantDouble(final ConstantDouble obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantFieldref(final ConstantFieldref obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantFloat(final ConstantFloat obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantInteger(final ConstantInteger obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantInterfaceMethodref(final ConstantInterfaceMethodref obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantLong(final ConstantLong obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantMethodref(final ConstantMethodref obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantNameAndType(final ConstantNameAndType obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantPool(final ConstantPool obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantString(final ConstantString obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantUtf8(final ConstantUtf8 obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitConstantValue(final ConstantValue obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitDeprecated(final Deprecated obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitExceptionTable(final ExceptionTable obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitField(final Field obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitInnerClass(final InnerClass obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitInnerClasses(final InnerClasses obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitJavaClass(final JavaClass obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitLineNumber(final LineNumber obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitLineNumberTable(final LineNumberTable obj) {
        this.tostring = "<LineNumberTable: " + this.toString(obj) + ">";
    }
    
    public void visitLocalVariable(final LocalVariable obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitLocalVariableTable(final LocalVariableTable obj) {
        this.tostring = "<LocalVariableTable: " + this.toString(obj) + ">";
    }
    
    public void visitMethod(final Method obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitSourceFile(final SourceFile obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitSynthetic(final Synthetic obj) {
        this.tostring = this.toString(obj);
    }
    
    public void visitUnknown(final Unknown obj) {
        this.tostring = this.toString(obj);
    }
}
