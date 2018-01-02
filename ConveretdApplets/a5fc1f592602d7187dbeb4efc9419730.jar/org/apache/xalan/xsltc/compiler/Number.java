// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.L2I;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.ASTORE;
import org.apache.xalan.xsltc.compiler.util.MatchGenerator;
import org.apache.xalan.xsltc.compiler.util.NodeCounterGenerator;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.ALOAD;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IFNONNULL;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Field;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.RealType;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.ArrayList;

final class Number extends Instruction implements Closure
{
    private static final int LEVEL_SINGLE = 0;
    private static final int LEVEL_MULTIPLE = 1;
    private static final int LEVEL_ANY = 2;
    private static final String[] ClassNames;
    private static final String[] FieldNames;
    private Pattern _from;
    private Pattern _count;
    private Expression _value;
    private AttributeValueTemplate _lang;
    private AttributeValueTemplate _format;
    private AttributeValueTemplate _letterValue;
    private AttributeValueTemplate _groupingSeparator;
    private AttributeValueTemplate _groupingSize;
    private int _level;
    private boolean _formatNeeded;
    private String _className;
    private ArrayList _closureVars;
    
    Number() {
        this._from = null;
        this._count = null;
        this._value = null;
        this._lang = null;
        this._format = null;
        this._letterValue = null;
        this._groupingSeparator = null;
        this._groupingSize = null;
        this._level = 0;
        this._formatNeeded = false;
        this._className = null;
        this._closureVars = null;
    }
    
    public boolean inInnerClass() {
        return this._className != null;
    }
    
    public Closure getParentClosure() {
        return null;
    }
    
    public String getInnerClassName() {
        return this._className;
    }
    
    public void addVariable(final VariableRefBase variableRef) {
        if (this._closureVars == null) {
            this._closureVars = new ArrayList();
        }
        if (!this._closureVars.contains(variableRef)) {
            this._closureVars.add(variableRef);
        }
    }
    
    public void parseContents(final Parser parser) {
        for (int count = super._attributes.getLength(), i = 0; i < count; ++i) {
            final String name = super._attributes.getQName(i);
            final String value = super._attributes.getValue(i);
            if (name.equals("value")) {
                this._value = parser.parseExpression(this, name, null);
            }
            else if (name.equals("count")) {
                this._count = parser.parsePattern(this, name, null);
            }
            else if (name.equals("from")) {
                this._from = parser.parsePattern(this, name, null);
            }
            else if (name.equals("level")) {
                if (value.equals("single")) {
                    this._level = 0;
                }
                else if (value.equals("multiple")) {
                    this._level = 1;
                }
                else if (value.equals("any")) {
                    this._level = 2;
                }
            }
            else if (name.equals("format")) {
                this._format = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            }
            else if (name.equals("lang")) {
                this._lang = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            }
            else if (name.equals("letter-value")) {
                this._letterValue = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            }
            else if (name.equals("grouping-separator")) {
                this._groupingSeparator = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            }
            else if (name.equals("grouping-size")) {
                this._groupingSize = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            }
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._value != null) {
            final Type tvalue = this._value.typeCheck(stable);
            if (!(tvalue instanceof RealType)) {
                this._value = new CastExpr(this._value, Type.Real);
            }
        }
        if (this._count != null) {
            this._count.typeCheck(stable);
        }
        if (this._from != null) {
            this._from.typeCheck(stable);
        }
        if (this._format != null) {
            this._format.typeCheck(stable);
        }
        if (this._lang != null) {
            this._lang.typeCheck(stable);
        }
        if (this._letterValue != null) {
            this._letterValue.typeCheck(stable);
        }
        if (this._groupingSeparator != null) {
            this._groupingSeparator.typeCheck(stable);
        }
        if (this._groupingSize != null) {
            this._groupingSize.typeCheck(stable);
        }
        return Type.Void;
    }
    
    public boolean hasValue() {
        return this._value != null;
    }
    
    public boolean isDefault() {
        return this._from == null && this._count == null;
    }
    
    private void compileDefault(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int[] fieldIndexes = this.getXSLTC().getNumberFieldIndexes();
        if (fieldIndexes[this._level] == -1) {
            final Field defaultNode = new Field(2, cpg.addUtf8(Number.FieldNames[this._level]), cpg.addUtf8("Lorg/apache/xalan/xsltc/dom/NodeCounter;"), null, cpg.getConstantPool());
            classGen.addField(defaultNode);
            fieldIndexes[this._level] = cpg.addFieldref(classGen.getClassName(), Number.FieldNames[this._level], "Lorg/apache/xalan/xsltc/dom/NodeCounter;");
        }
        il.append(classGen.loadTranslet());
        il.append(new GETFIELD(fieldIndexes[this._level]));
        final BranchHandle ifBlock1 = il.append(new IFNONNULL(null));
        final int index = cpg.addMethodref(Number.ClassNames[this._level], "getDefaultNodeCounter", "(Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/apache/xalan/xsltc/dom/NodeCounter;");
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadIterator());
        il.append(new INVOKESTATIC(index));
        il.append(InstructionConstants.DUP);
        il.append(classGen.loadTranslet());
        il.append(InstructionConstants.SWAP);
        il.append(new PUTFIELD(fieldIndexes[this._level]));
        final BranchHandle ifBlock2 = il.append(new GOTO(null));
        ifBlock1.setTarget(il.append(classGen.loadTranslet()));
        il.append(new GETFIELD(fieldIndexes[this._level]));
        ifBlock2.setTarget(il.append(InstructionConstants.NOP));
    }
    
    private void compileConstructor(final ClassGenerator classGen) {
        final InstructionList il = new InstructionList();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final MethodGenerator cons = new MethodGenerator(1, org.apache.bcel.generic.Type.VOID, new org.apache.bcel.generic.Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/Translet;"), Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;") }, new String[] { "dom", "translet", "iterator" }, "<init>", this._className, il, cpg);
        il.append(InstructionConstants.ALOAD_0);
        il.append(InstructionConstants.ALOAD_1);
        il.append(InstructionConstants.ALOAD_2);
        il.append(new ALOAD(3));
        final int index = cpg.addMethodref(Number.ClassNames[this._level], "<init>", "(Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
        il.append(new INVOKESPECIAL(index));
        il.append(InstructionConstants.RETURN);
        cons.stripAttributes(true);
        cons.setMaxLocals();
        cons.setMaxStack();
        classGen.addMethod(cons.getMethod());
    }
    
    private void compileLocals(final NodeCounterGenerator nodeCounterGen, final MatchGenerator matchGen, final InstructionList il) {
        final ConstantPoolGen cpg = nodeCounterGen.getConstantPool();
        LocalVariableGen local = matchGen.addLocalVariable("iterator", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), null, null);
        int field = cpg.addFieldref("org.apache.xalan.xsltc.dom.NodeCounter", "_iterator", "Lorg/apache/xml/dtm/DTMAxisIterator;");
        il.append(InstructionConstants.ALOAD_0);
        il.append(new GETFIELD(field));
        il.append(new ASTORE(local.getIndex()));
        matchGen.setIteratorIndex(local.getIndex());
        local = matchGen.addLocalVariable("translet", Util.getJCRefType("Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;"), null, null);
        field = cpg.addFieldref("org.apache.xalan.xsltc.dom.NodeCounter", "_translet", "Lorg/apache/xalan/xsltc/Translet;");
        il.append(InstructionConstants.ALOAD_0);
        il.append(new GETFIELD(field));
        il.append(new CHECKCAST(cpg.addClass("org.apache.xalan.xsltc.runtime.AbstractTranslet")));
        il.append(new ASTORE(local.getIndex()));
        nodeCounterGen.setTransletIndex(local.getIndex());
        local = matchGen.addLocalVariable("document", Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), null, null);
        field = cpg.addFieldref(this._className, "_document", "Lorg/apache/xalan/xsltc/DOM;");
        il.append(InstructionConstants.ALOAD_0);
        il.append(new GETFIELD(field));
        il.append(new ASTORE(local.getIndex()));
        matchGen.setDomIndex(local.getIndex());
    }
    
    private void compilePatterns(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this._className = this.getXSLTC().getHelperClassName();
        final NodeCounterGenerator nodeCounterGen = new NodeCounterGenerator(this._className, Number.ClassNames[this._level], this.toString(), 33, null, classGen.getStylesheet());
        InstructionList il = null;
        ConstantPoolGen cpg = nodeCounterGen.getConstantPool();
        final int closureLen = (this._closureVars == null) ? 0 : this._closureVars.size();
        for (int i = 0; i < closureLen; ++i) {
            final VariableBase var = this._closureVars.get(i).getVariable();
            nodeCounterGen.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
        }
        this.compileConstructor(nodeCounterGen);
        if (this._from != null) {
            il = new InstructionList();
            final MatchGenerator matchGen = new MatchGenerator(17, org.apache.bcel.generic.Type.BOOLEAN, new org.apache.bcel.generic.Type[] { org.apache.bcel.generic.Type.INT }, new String[] { "node" }, "matchesFrom", this._className, il, cpg);
            this.compileLocals(nodeCounterGen, matchGen, il);
            il.append(matchGen.loadContextNode());
            this._from.translate(nodeCounterGen, matchGen);
            this._from.synthesize(nodeCounterGen, matchGen);
            il.append(InstructionConstants.IRETURN);
            matchGen.stripAttributes(true);
            matchGen.setMaxLocals();
            matchGen.setMaxStack();
            matchGen.removeNOPs();
            nodeCounterGen.addMethod(matchGen.getMethod());
        }
        if (this._count != null) {
            il = new InstructionList();
            final MatchGenerator matchGen = new MatchGenerator(17, org.apache.bcel.generic.Type.BOOLEAN, new org.apache.bcel.generic.Type[] { org.apache.bcel.generic.Type.INT }, new String[] { "node" }, "matchesCount", this._className, il, cpg);
            this.compileLocals(nodeCounterGen, matchGen, il);
            il.append(matchGen.loadContextNode());
            this._count.translate(nodeCounterGen, matchGen);
            this._count.synthesize(nodeCounterGen, matchGen);
            il.append(InstructionConstants.IRETURN);
            matchGen.stripAttributes(true);
            matchGen.setMaxLocals();
            matchGen.setMaxStack();
            matchGen.removeNOPs();
            nodeCounterGen.addMethod(matchGen.getMethod());
        }
        this.getXSLTC().dumpClass(nodeCounterGen.getJavaClass());
        cpg = classGen.getConstantPool();
        il = methodGen.getInstructionList();
        final int index = cpg.addMethodref(this._className, "<init>", "(Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
        il.append(new NEW(cpg.addClass(this._className)));
        il.append(InstructionConstants.DUP);
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadIterator());
        il.append(new INVOKESPECIAL(index));
        for (int j = 0; j < closureLen; ++j) {
            final VariableRefBase varRef = this._closureVars.get(j);
            final VariableBase var2 = varRef.getVariable();
            final Type varType = var2.getType();
            il.append(InstructionConstants.DUP);
            il.append(var2.loadInstruction());
            il.append(new PUTFIELD(cpg.addFieldref(this._className, var2.getEscapedName(), varType.toSignature())));
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(classGen.loadTranslet());
        if (this.hasValue()) {
            this.compileDefault(classGen, methodGen);
            this._value.translate(classGen, methodGen);
            int index = cpg.addMethodref("java.lang.Math", "round", "(D)J");
            il.append(new INVOKESTATIC(index));
            il.append(new L2I());
            index = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "setValue", "(I)Lorg/apache/xalan/xsltc/dom/NodeCounter;");
            il.append(new INVOKEVIRTUAL(index));
        }
        else if (this.isDefault()) {
            this.compileDefault(classGen, methodGen);
        }
        else {
            this.compilePatterns(classGen, methodGen);
        }
        if (!this.hasValue()) {
            il.append(methodGen.loadContextNode());
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "setStartNode", "(I)Lorg/apache/xalan/xsltc/dom/NodeCounter;");
            il.append(new INVOKEVIRTUAL(index));
        }
        if (this._formatNeeded) {
            if (this._format != null) {
                this._format.translate(classGen, methodGen);
            }
            else {
                il.append(new PUSH(cpg, "1"));
            }
            if (this._lang != null) {
                this._lang.translate(classGen, methodGen);
            }
            else {
                il.append(new PUSH(cpg, "en"));
            }
            if (this._letterValue != null) {
                this._letterValue.translate(classGen, methodGen);
            }
            else {
                il.append(new PUSH(cpg, ""));
            }
            if (this._groupingSeparator != null) {
                this._groupingSeparator.translate(classGen, methodGen);
            }
            else {
                il.append(new PUSH(cpg, ""));
            }
            if (this._groupingSize != null) {
                this._groupingSize.translate(classGen, methodGen);
            }
            else {
                il.append(new PUSH(cpg, "0"));
            }
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "getCounter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
            il.append(new INVOKEVIRTUAL(index));
        }
        else {
            int index = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "setDefaultFormatting", "()Lorg/apache/xalan/xsltc/dom/NodeCounter;");
            il.append(new INVOKEVIRTUAL(index));
            index = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "getCounter", "()Ljava/lang/String;");
            il.append(new INVOKEVIRTUAL(index));
        }
        il.append(methodGen.loadHandler());
        int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "characters", "(Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;)V");
        il.append(new INVOKEVIRTUAL(index));
    }
    
    static {
        ClassNames = new String[] { "org.apache.xalan.xsltc.dom.SingleNodeCounter", "org.apache.xalan.xsltc.dom.MultipleNodeCounter", "org.apache.xalan.xsltc.dom.AnyNodeCounter" };
        FieldNames = new String[] { "___single_node_counter", "___multiple_node_counter", "___any_node_counter" };
    }
}
