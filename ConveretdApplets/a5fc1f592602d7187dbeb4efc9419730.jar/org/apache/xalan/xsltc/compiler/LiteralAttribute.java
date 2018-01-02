// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xml.serializer.ElemDesc;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.Util;

final class LiteralAttribute extends Instruction
{
    private final String _name;
    private final AttributeValue _value;
    
    public LiteralAttribute(final String name, final String value, final Parser parser) {
        this._name = name;
        this._value = AttributeValue.create(this, value, parser);
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("LiteralAttribute name=" + this._name + " value=" + this._value);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._value.typeCheck(stable);
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    protected boolean contextDependent() {
        return this._value.contextDependent();
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(methodGen.loadHandler());
        il.append(new PUSH(cpg, this._name));
        this._value.translate(classGen, methodGen);
        final SyntaxTreeNode parent = this.getParent();
        if (parent instanceof LiteralElement && ((LiteralElement)parent).allAttributesUnique()) {
            int flags = 0;
            boolean isHTMLAttrEmpty = false;
            final ElemDesc elemDesc = ((LiteralElement)parent).getElemDesc();
            if (elemDesc != null) {
                if (elemDesc.isAttrFlagSet(this._name, 4)) {
                    flags |= 0x2;
                    isHTMLAttrEmpty = true;
                }
                else if (elemDesc.isAttrFlagSet(this._name, 2)) {
                    flags |= 0x4;
                }
            }
            if (this._value instanceof SimpleAttributeValue) {
                final String attrValue = ((SimpleAttributeValue)this._value).toString();
                if (!this.hasBadChars(attrValue) && !isHTMLAttrEmpty) {
                    flags |= 0x1;
                }
            }
            il.append(new PUSH(cpg, flags));
            il.append(methodGen.uniqueAttribute());
        }
        else {
            il.append(methodGen.attribute());
        }
    }
    
    private boolean hasBadChars(final String value) {
        for (final char ch : value.toCharArray()) {
            if (ch < ' ' || '~' < ch || ch == '<' || ch == '>' || ch == '&' || ch == '\"') {
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return this._name;
    }
    
    public AttributeValue getValue() {
        return this._value;
    }
}
