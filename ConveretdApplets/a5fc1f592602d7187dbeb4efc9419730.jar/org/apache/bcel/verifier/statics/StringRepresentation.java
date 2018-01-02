// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.statics;

import org.apache.bcel.classfile.Unknown;
import org.apache.bcel.classfile.Synthetic;
import org.apache.bcel.classfile.SourceFile;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.LineNumber;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.InnerClasses;
import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.ExceptionTable;
import org.apache.bcel.classfile.Deprecated;
import org.apache.bcel.classfile.ConstantValue;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.ConstantString;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantLong;
import org.apache.bcel.classfile.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.ConstantInteger;
import org.apache.bcel.classfile.ConstantFloat;
import org.apache.bcel.classfile.ConstantFieldref;
import org.apache.bcel.classfile.ConstantDouble;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.Node;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.EmptyVisitor;

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
