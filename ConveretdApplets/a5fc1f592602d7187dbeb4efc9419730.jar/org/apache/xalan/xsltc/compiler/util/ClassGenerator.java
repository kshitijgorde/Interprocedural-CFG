// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.Instruction;
import org.apache.xalan.xsltc.compiler.Parser;
import org.apache.xalan.xsltc.compiler.Stylesheet;
import org.apache.bcel.generic.ClassGen;

public class ClassGenerator extends ClassGen
{
    protected static int TRANSLET_INDEX;
    protected static int INVALID_INDEX;
    private Stylesheet _stylesheet;
    private final Parser _parser;
    private final Instruction _aloadTranslet;
    private final String _domClass;
    private final String _domClassSig;
    private final String _applyTemplatesSig;
    
    public ClassGenerator(final String class_name, final String super_class_name, final String file_name, final int access_flags, final String[] interfaces, final Stylesheet stylesheet) {
        super(class_name, super_class_name, file_name, access_flags, interfaces);
        this._stylesheet = stylesheet;
        this._parser = stylesheet.getParser();
        this._aloadTranslet = new ALOAD(ClassGenerator.TRANSLET_INDEX);
        if (stylesheet.isMultiDocument()) {
            this._domClass = "org.apache.xalan.xsltc.dom.MultiDOM";
            this._domClassSig = "Lorg/apache/xalan/xsltc/dom/MultiDOM;";
        }
        else {
            this._domClass = "org.apache.xalan.xsltc.dom.DOMAdapter";
            this._domClassSig = "Lorg/apache/xalan/xsltc/dom/DOMAdapter;";
        }
        this._applyTemplatesSig = "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V";
    }
    
    public final Parser getParser() {
        return this._parser;
    }
    
    public final Stylesheet getStylesheet() {
        return this._stylesheet;
    }
    
    public final String getClassName() {
        return this._stylesheet.getClassName();
    }
    
    public Instruction loadTranslet() {
        return this._aloadTranslet;
    }
    
    public final String getDOMClass() {
        return this._domClass;
    }
    
    public final String getDOMClassSig() {
        return this._domClassSig;
    }
    
    public final String getApplyTemplatesSig() {
        return this._applyTemplatesSig;
    }
    
    public boolean isExternal() {
        return false;
    }
    
    static {
        ClassGenerator.TRANSLET_INDEX = 0;
        ClassGenerator.INVALID_INDEX = -1;
    }
}
