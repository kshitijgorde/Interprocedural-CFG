// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.xalan.xsltc.compiler.Pattern;
import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.ICONST;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Type;
import java.util.Hashtable;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.Constants;
import com.ibm.xslt4j.bcel.generic.MethodGen;

public class MethodGenerator extends MethodGen implements Constants
{
    protected static final int INVALID_INDEX = -1;
    private static final String START_ELEMENT_SIG = "(Ljava/lang/String;)V";
    private static final String END_ELEMENT_SIG = "(Ljava/lang/String;)V";
    private InstructionList _mapTypeSub;
    private static final int DOM_INDEX = 1;
    private static final int ITERATOR_INDEX = 2;
    private static final int HANDLER_INDEX = 3;
    private Instruction _iloadCurrent;
    private Instruction _istoreCurrent;
    private final Instruction _astoreHandler;
    private final Instruction _aloadHandler;
    private final Instruction _astoreIterator;
    private final Instruction _aloadIterator;
    private final Instruction _aloadDom;
    private final Instruction _astoreDom;
    private final Instruction _startElement;
    private final Instruction _endElement;
    private final Instruction _startDocument;
    private final Instruction _endDocument;
    private final Instruction _attribute;
    private final Instruction _uniqueAttribute;
    private final Instruction _namespace;
    private final Instruction _setStartNode;
    private final Instruction _reset;
    private final Instruction _nextNode;
    private SlotAllocator _slotAllocator;
    private boolean _allocatorInit;
    private Hashtable _preCompiled;
    
    public MethodGenerator(final int access_flags, final Type return_type, final Type[] arg_types, final String[] arg_names, final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cpg) {
        super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cpg);
        this._allocatorInit = false;
        this._preCompiled = new Hashtable();
        this._astoreHandler = new ASTORE(3);
        this._aloadHandler = new ALOAD(3);
        this._astoreIterator = new ASTORE(2);
        this._aloadIterator = new ALOAD(2);
        this._aloadDom = new ALOAD(1);
        this._astoreDom = new ASTORE(1);
        final int startElement = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "startElement", "(Ljava/lang/String;)V");
        this._startElement = new INVOKEINTERFACE(startElement, 2);
        final int endElement = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "endElement", "(Ljava/lang/String;)V");
        this._endElement = new INVOKEINTERFACE(endElement, 2);
        final int attribute = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "addAttribute", "(Ljava/lang/String;Ljava/lang/String;)V");
        this._attribute = new INVOKEINTERFACE(attribute, 3);
        final int uniqueAttribute = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "addUniqueAttribute", "(Ljava/lang/String;Ljava/lang/String;I)V");
        this._uniqueAttribute = new INVOKEINTERFACE(uniqueAttribute, 4);
        final int namespace = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "namespaceAfterStartElement", "(Ljava/lang/String;Ljava/lang/String;)V");
        this._namespace = new INVOKEINTERFACE(namespace, 3);
        int index = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "startDocument", "()V");
        this._startDocument = new INVOKEINTERFACE(index, 1);
        index = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "endDocument", "()V");
        this._endDocument = new INVOKEINTERFACE(index, 1);
        index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "setStartNode", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
        this._setStartNode = new INVOKEINTERFACE(index, 2);
        index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "reset", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
        this._reset = new INVOKEINTERFACE(index, 1);
        index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "next", "()I");
        this._nextNode = new INVOKEINTERFACE(index, 1);
        (this._slotAllocator = new SlotAllocator()).initialize(this.getLocalVariables());
        this._allocatorInit = true;
    }
    
    public LocalVariableGen addLocalVariable(final String name, final Type type, final InstructionHandle start, final InstructionHandle end) {
        return this._allocatorInit ? this.addLocalVariable2(name, type, start) : super.addLocalVariable(name, type, start, end);
    }
    
    public LocalVariableGen addLocalVariable2(final String name, final Type type, final InstructionHandle start) {
        return super.addLocalVariable(name, type, this._slotAllocator.allocateSlot(type), start, null);
    }
    
    public void removeLocalVariable(final LocalVariableGen lvg) {
        this._slotAllocator.releaseSlot(lvg);
        super.removeLocalVariable(lvg);
    }
    
    public Instruction loadDOM() {
        return this._aloadDom;
    }
    
    public Instruction storeDOM() {
        return this._astoreDom;
    }
    
    public Instruction storeHandler() {
        return this._astoreHandler;
    }
    
    public Instruction loadHandler() {
        return this._aloadHandler;
    }
    
    public Instruction storeIterator() {
        return this._astoreIterator;
    }
    
    public Instruction loadIterator() {
        return this._aloadIterator;
    }
    
    public final Instruction setStartNode() {
        return this._setStartNode;
    }
    
    public final Instruction reset() {
        return this._reset;
    }
    
    public final Instruction nextNode() {
        return this._nextNode;
    }
    
    public final Instruction startElement() {
        return this._startElement;
    }
    
    public final Instruction endElement() {
        return this._endElement;
    }
    
    public final Instruction startDocument() {
        return this._startDocument;
    }
    
    public final Instruction endDocument() {
        return this._endDocument;
    }
    
    public final Instruction attribute() {
        return this._attribute;
    }
    
    public final Instruction uniqueAttribute() {
        return this._uniqueAttribute;
    }
    
    public final Instruction namespace() {
        return this._namespace;
    }
    
    public Instruction loadCurrentNode() {
        if (this._iloadCurrent == null) {
            final int idx = this.getLocalIndex("current");
            if (idx > 0) {
                this._iloadCurrent = new ILOAD(idx);
            }
            else {
                this._iloadCurrent = new ICONST(0);
            }
        }
        return this._iloadCurrent;
    }
    
    public Instruction storeCurrentNode() {
        return (this._istoreCurrent != null) ? this._istoreCurrent : (this._istoreCurrent = new ISTORE(this.getLocalIndex("current")));
    }
    
    public Instruction loadContextNode() {
        return this.loadCurrentNode();
    }
    
    public Instruction storeContextNode() {
        return this.storeCurrentNode();
    }
    
    public int getLocalIndex(final String name) {
        return this.getLocalVariable(name).getIndex();
    }
    
    public LocalVariableGen getLocalVariable(final String name) {
        final LocalVariableGen[] vars = this.getLocalVariables();
        for (int i = 0; i < vars.length; ++i) {
            if (vars[i].getName().equals(name)) {
                return vars[i];
            }
        }
        return null;
    }
    
    public void setMaxLocals() {
        final int prevLocals;
        int maxLocals = prevLocals = super.getMaxLocals();
        final LocalVariableGen[] localVars = super.getLocalVariables();
        if (localVars != null && localVars.length > maxLocals) {
            maxLocals = localVars.length;
        }
        if (maxLocals < 5) {
            maxLocals = 5;
        }
        super.setMaxLocals(maxLocals);
    }
    
    public void addInstructionList(final Pattern pattern, final InstructionList ilist) {
        this._preCompiled.put(pattern, ilist);
    }
    
    public InstructionList getInstructionList(final Pattern pattern) {
        return this._preCompiled.get(pattern);
    }
}
