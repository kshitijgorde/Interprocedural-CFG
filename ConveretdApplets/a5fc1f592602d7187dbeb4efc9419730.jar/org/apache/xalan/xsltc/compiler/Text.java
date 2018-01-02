// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.Util;

final class Text extends Instruction
{
    private String _text;
    private boolean _escaping;
    private boolean _ignore;
    private boolean _textElement;
    
    public Text() {
        this._escaping = true;
        this._ignore = false;
        this._textElement = false;
        this._textElement = true;
    }
    
    public Text(final String text) {
        this._escaping = true;
        this._ignore = false;
        this._textElement = false;
        this._text = text;
    }
    
    protected String getText() {
        return this._text;
    }
    
    protected void setText(final String text) {
        if (this._text == null) {
            this._text = text;
        }
        else {
            this._text += text;
        }
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("Text");
        this.indent(indent + 4);
        Util.println(this._text);
    }
    
    public void parseContents(final Parser parser) {
        final String str = this.getAttribute("disable-output-escaping");
        if (str != null && str.equals("yes")) {
            this._escaping = false;
        }
        this.parseChildren(parser);
        if (this._text == null) {
            if (this._textElement) {
                this._text = "";
            }
            else {
                this._ignore = true;
            }
        }
        else if (this._textElement) {
            if (this._text.length() == 0) {
                this._ignore = true;
            }
        }
        else if (this.getParent() instanceof LiteralElement) {
            final LiteralElement element = (LiteralElement)this.getParent();
            final String space = element.getAttribute("xml:space");
            if ((space == null || !space.equals("preserve")) && this._text.trim().length() == 0) {
                this._ignore = true;
            }
        }
        else if (this._text.trim().length() == 0) {
            this._ignore = true;
        }
    }
    
    public void ignore() {
        this._ignore = true;
    }
    
    public boolean isIgnore() {
        return this._ignore;
    }
    
    public boolean isTextElement() {
        return this._textElement;
    }
    
    protected boolean contextDependent() {
        return false;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (!this._ignore) {
            final int esc = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "setEscaping", "(Z)Z");
            if (!this._escaping) {
                il.append(methodGen.loadHandler());
                il.append(new PUSH(cpg, false));
                il.append(new INVOKEINTERFACE(esc, 2));
            }
            il.append(methodGen.loadHandler());
            if (!this.canLoadAsArrayOffsetLength()) {
                final int characters = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "characters", "(Ljava/lang/String;)V");
                il.append(new PUSH(cpg, this._text));
                il.append(new INVOKEINTERFACE(characters, 2));
            }
            else {
                final int characters = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "characters", "([CII)V");
                this.loadAsArrayOffsetLength(classGen, methodGen);
                il.append(new INVOKEINTERFACE(characters, 4));
            }
            if (!this._escaping) {
                il.append(methodGen.loadHandler());
                il.append(InstructionConstants.SWAP);
                il.append(new INVOKEINTERFACE(esc, 2));
                il.append(InstructionConstants.POP);
            }
        }
        this.translateContents(classGen, methodGen);
    }
    
    public boolean canLoadAsArrayOffsetLength() {
        return this._text.length() <= 21845;
    }
    
    public void loadAsArrayOffsetLength(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final XSLTC xsltc = classGen.getParser().getXSLTC();
        final int offset = xsltc.addCharacterData(this._text);
        final int length = this._text.length();
        final String charDataFieldName = "_scharData" + (xsltc.getCharacterDataCount() - 1);
        il.append(new GETSTATIC(cpg.addFieldref(xsltc.getClassName(), charDataFieldName, "[C")));
        il.append(new PUSH(cpg, offset));
        il.append(new PUSH(cpg, this._text.length()));
    }
}
