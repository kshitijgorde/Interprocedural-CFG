// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.TABLESWITCH;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.xalan.xsltc.compiler.util.CompareGenerator;
import org.apache.bcel.classfile.Method;
import org.apache.xalan.xsltc.compiler.util.NodeSortRecordGenerator;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.ALOAD;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Field;
import org.apache.xalan.xsltc.compiler.util.NodeSortRecordFactGenerator;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.ANEWARRAY;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.NEW;
import java.util.Vector;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.StringType;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.IntType;
import java.util.ArrayList;

final class Sort extends Instruction implements Closure
{
    private Expression _select;
    private AttributeValue _order;
    private AttributeValue _caseOrder;
    private AttributeValue _dataType;
    private String _lang;
    private String _data;
    private String _className;
    private ArrayList _closureVars;
    private boolean _needsSortRecordFactory;
    
    Sort() {
        this._data = null;
        this._className = null;
        this._closureVars = null;
        this._needsSortRecordFactory = false;
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
            this._needsSortRecordFactory = true;
        }
    }
    
    private void setInnerClassName(final String className) {
        this._className = className;
    }
    
    public void parseContents(final Parser parser) {
        final SyntaxTreeNode parent = this.getParent();
        if (!(parent instanceof ApplyTemplates) && !(parent instanceof ForEach)) {
            this.reportError(this, parser, "STRAY_SORT_ERR", null);
            return;
        }
        this._select = parser.parseExpression(this, "select", "string(.)");
        String val = this.getAttribute("order");
        if (val.length() == 0) {
            val = "ascending";
        }
        this._order = AttributeValue.create(this, val, parser);
        val = this.getAttribute("data-type");
        if (val.length() == 0) {
            try {
                final Type type = this._select.typeCheck(parser.getSymbolTable());
                if (type instanceof IntType) {
                    val = "number";
                }
                else {
                    val = "text";
                }
            }
            catch (TypeCheckError e) {
                val = "text";
            }
        }
        this._dataType = AttributeValue.create(this, val, parser);
        this._lang = this.getAttribute("lang");
        val = this.getAttribute("case-order");
        this._caseOrder = AttributeValue.create(this, val, parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type tselect = this._select.typeCheck(stable);
        if (!(tselect instanceof StringType)) {
            this._select = new CastExpr(this._select, Type.String);
        }
        this._order.typeCheck(stable);
        this._caseOrder.typeCheck(stable);
        this._dataType.typeCheck(stable);
        return Type.Void;
    }
    
    public void translateSortType(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this._dataType.translate(classGen, methodGen);
    }
    
    public void translateSortOrder(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this._order.translate(classGen, methodGen);
    }
    
    public void translateCaseOrder(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this._caseOrder.translate(classGen, methodGen);
    }
    
    public void translateLang(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new PUSH(cpg, this._lang));
    }
    
    public void translateSelect(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this._select.translate(classGen, methodGen);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
    }
    
    public static void translateSortIterator(final ClassGenerator classGen, final MethodGenerator methodGen, final Expression nodeSet, final Vector sortObjects) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SortingIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xalan/xsltc/dom/NodeSortRecordFactory;)V");
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SortingIterator")));
        il.append(InstructionConstants.DUP);
        if (nodeSet == null) {
            final int children = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getAxisIterator", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(methodGen.loadDOM());
            il.append(new PUSH(cpg, 3));
            il.append(new INVOKEINTERFACE(children, 2));
        }
        else {
            nodeSet.translate(classGen, methodGen);
        }
        compileSortRecordFactory(sortObjects, classGen, methodGen);
        il.append(new INVOKESPECIAL(init));
    }
    
    public static void compileSortRecordFactory(final Vector sortObjects, final ClassGenerator classGen, final MethodGenerator methodGen) {
        final String sortRecordClass = compileSortRecord(sortObjects, classGen, methodGen);
        boolean needsSortRecordFactory = false;
        final int nsorts = sortObjects.size();
        for (int i = 0; i < nsorts; ++i) {
            final Sort sort = sortObjects.elementAt(i);
            needsSortRecordFactory |= sort._needsSortRecordFactory;
        }
        String sortRecordFactoryClass = "org/apache/xalan/xsltc/dom/NodeSortRecordFactory";
        if (needsSortRecordFactory) {
            sortRecordFactoryClass = compileSortRecordFactory(sortObjects, classGen, methodGen, sortRecordClass);
        }
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new NEW(cpg.addClass(sortRecordFactoryClass)));
        il.append(InstructionConstants.DUP);
        il.append(methodGen.loadDOM());
        il.append(new PUSH(cpg, sortRecordClass));
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, nsorts));
        il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
        for (int level = 0; level < nsorts; ++level) {
            final Sort sort2 = sortObjects.elementAt(level);
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, level));
            sort2.translateSortOrder(classGen, methodGen);
            il.append(InstructionConstants.AASTORE);
        }
        il.append(new PUSH(cpg, nsorts));
        il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
        for (int level2 = 0; level2 < nsorts; ++level2) {
            final Sort sort3 = sortObjects.elementAt(level2);
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, level2));
            sort3.translateSortType(classGen, methodGen);
            il.append(InstructionConstants.AASTORE);
        }
        il.append(new PUSH(cpg, nsorts));
        il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
        for (int level3 = 0; level3 < nsorts; ++level3) {
            final Sort sort4 = sortObjects.elementAt(level3);
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, level3));
            sort4.translateLang(classGen, methodGen);
            il.append(InstructionConstants.AASTORE);
        }
        il.append(new PUSH(cpg, nsorts));
        il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
        for (int level4 = 0; level4 < nsorts; ++level4) {
            final Sort sort5 = sortObjects.elementAt(level4);
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, level4));
            sort5.translateCaseOrder(classGen, methodGen);
            il.append(InstructionConstants.AASTORE);
        }
        il.append(new INVOKESPECIAL(cpg.addMethodref(sortRecordFactoryClass, "<init>", "(Lorg/apache/xalan/xsltc/DOM;Ljava/lang/String;Lorg/apache/xalan/xsltc/Translet;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V")));
        final ArrayList dups = new ArrayList();
        for (int j = 0; j < nsorts; ++j) {
            final Sort sort6 = sortObjects.get(j);
            for (int length = (sort6._closureVars == null) ? 0 : sort6._closureVars.size(), k = 0; k < length; ++k) {
                final VariableRefBase varRef = sort6._closureVars.get(k);
                if (!dups.contains(varRef)) {
                    final VariableBase var = varRef.getVariable();
                    il.append(InstructionConstants.DUP);
                    il.append(var.loadInstruction());
                    il.append(new PUTFIELD(cpg.addFieldref(sortRecordFactoryClass, var.getEscapedName(), var.getType().toSignature())));
                    dups.add(varRef);
                }
            }
        }
    }
    
    public static String compileSortRecordFactory(final Vector sortObjects, final ClassGenerator classGen, final MethodGenerator methodGen, final String sortRecordClass) {
        final XSLTC xsltc = sortObjects.firstElement().getXSLTC();
        final String className = xsltc.getHelperClassName();
        final NodeSortRecordFactGenerator sortRecordFactory = new NodeSortRecordFactGenerator(className, "org/apache/xalan/xsltc/dom/NodeSortRecordFactory", className + ".java", 49, new String[0], classGen.getStylesheet());
        final ConstantPoolGen cpg = sortRecordFactory.getConstantPool();
        final int nsorts = sortObjects.size();
        final ArrayList dups = new ArrayList();
        for (int j = 0; j < nsorts; ++j) {
            final Sort sort = sortObjects.get(j);
            for (int length = (sort._closureVars == null) ? 0 : sort._closureVars.size(), i = 0; i < length; ++i) {
                final VariableRefBase varRef = sort._closureVars.get(i);
                if (!dups.contains(varRef)) {
                    final VariableBase var = varRef.getVariable();
                    sortRecordFactory.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
                    dups.add(varRef);
                }
            }
        }
        final org.apache.bcel.generic.Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Ljava/lang/String;"), Util.getJCRefType("Lorg/apache/xalan/xsltc/Translet;"), Util.getJCRefType("[Ljava/lang/String;"), Util.getJCRefType("[Ljava/lang/String;"), Util.getJCRefType("[Ljava/lang/String;"), Util.getJCRefType("[Ljava/lang/String;") };
        final String[] argNames = { "document", "className", "translet", "order", "type", "lang", "case_order" };
        InstructionList il = new InstructionList();
        final MethodGenerator constructor = new MethodGenerator(1, org.apache.bcel.generic.Type.VOID, argTypes, argNames, "<init>", className, il, cpg);
        il.append(InstructionConstants.ALOAD_0);
        il.append(InstructionConstants.ALOAD_1);
        il.append(InstructionConstants.ALOAD_2);
        il.append(new ALOAD(3));
        il.append(new ALOAD(4));
        il.append(new ALOAD(5));
        il.append(new ALOAD(6));
        il.append(new ALOAD(7));
        il.append(new INVOKESPECIAL(cpg.addMethodref("org/apache/xalan/xsltc/dom/NodeSortRecordFactory", "<init>", "(Lorg/apache/xalan/xsltc/DOM;Ljava/lang/String;Lorg/apache/xalan/xsltc/Translet;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V")));
        il.append(InstructionConstants.RETURN);
        il = new InstructionList();
        final MethodGenerator makeNodeSortRecord = new MethodGenerator(1, Util.getJCRefType("Lorg/apache/xalan/xsltc/dom/NodeSortRecord;"), new org.apache.bcel.generic.Type[] { org.apache.bcel.generic.Type.INT, org.apache.bcel.generic.Type.INT }, new String[] { "node", "last" }, "makeNodeSortRecord", className, il, cpg);
        il.append(InstructionConstants.ALOAD_0);
        il.append(InstructionConstants.ILOAD_1);
        il.append(InstructionConstants.ILOAD_2);
        il.append(new INVOKESPECIAL(cpg.addMethodref("org/apache/xalan/xsltc/dom/NodeSortRecordFactory", "makeNodeSortRecord", "(II)Lorg/apache/xalan/xsltc/dom/NodeSortRecord;")));
        il.append(InstructionConstants.DUP);
        il.append(new CHECKCAST(cpg.addClass(sortRecordClass)));
        for (int ndups = dups.size(), k = 0; k < ndups; ++k) {
            final VariableRefBase varRef2 = dups.get(k);
            final VariableBase var2 = varRef2.getVariable();
            final Type varType = var2.getType();
            il.append(InstructionConstants.DUP);
            il.append(InstructionConstants.ALOAD_0);
            il.append(new GETFIELD(cpg.addFieldref(className, var2.getEscapedName(), varType.toSignature())));
            il.append(new PUTFIELD(cpg.addFieldref(sortRecordClass, var2.getEscapedName(), varType.toSignature())));
        }
        il.append(InstructionConstants.POP);
        il.append(InstructionConstants.ARETURN);
        constructor.setMaxLocals();
        constructor.setMaxStack();
        sortRecordFactory.addMethod(constructor.getMethod());
        makeNodeSortRecord.setMaxLocals();
        makeNodeSortRecord.setMaxStack();
        sortRecordFactory.addMethod(makeNodeSortRecord.getMethod());
        xsltc.dumpClass(sortRecordFactory.getJavaClass());
        return className;
    }
    
    private static String compileSortRecord(final Vector sortObjects, final ClassGenerator classGen, final MethodGenerator methodGen) {
        final XSLTC xsltc = sortObjects.firstElement().getXSLTC();
        final String className = xsltc.getHelperClassName();
        final NodeSortRecordGenerator sortRecord = new NodeSortRecordGenerator(className, "org.apache.xalan.xsltc.dom.NodeSortRecord", "sort$0.java", 49, new String[0], classGen.getStylesheet());
        final ConstantPoolGen cpg = sortRecord.getConstantPool();
        final int nsorts = sortObjects.size();
        final ArrayList dups = new ArrayList();
        for (int j = 0; j < nsorts; ++j) {
            final Sort sort = sortObjects.get(j);
            sort.setInnerClassName(className);
            for (int length = (sort._closureVars == null) ? 0 : sort._closureVars.size(), i = 0; i < length; ++i) {
                final VariableRefBase varRef = sort._closureVars.get(i);
                if (!dups.contains(varRef)) {
                    final VariableBase var = varRef.getVariable();
                    sortRecord.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
                    dups.add(varRef);
                }
            }
        }
        final Method init = compileInit(sortObjects, sortRecord, cpg, className);
        final Method extract = compileExtract(sortObjects, sortRecord, cpg, className);
        sortRecord.addMethod(init);
        sortRecord.addMethod(extract);
        xsltc.dumpClass(sortRecord.getJavaClass());
        return className;
    }
    
    private static Method compileInit(final Vector sortObjects, final NodeSortRecordGenerator sortRecord, final ConstantPoolGen cpg, final String className) {
        final InstructionList il = new InstructionList();
        final MethodGenerator init = new MethodGenerator(1, org.apache.bcel.generic.Type.VOID, null, null, "<init>", className, il, cpg);
        il.append(InstructionConstants.ALOAD_0);
        il.append(new INVOKESPECIAL(cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeSortRecord", "<init>", "()V")));
        il.append(InstructionConstants.RETURN);
        init.stripAttributes(true);
        init.setMaxLocals();
        init.setMaxStack();
        return init.getMethod();
    }
    
    private static Method compileExtract(final Vector sortObjects, final NodeSortRecordGenerator sortRecord, final ConstantPoolGen cpg, final String className) {
        final InstructionList il = new InstructionList();
        final CompareGenerator extractMethod = new CompareGenerator(17, org.apache.bcel.generic.Type.STRING, new org.apache.bcel.generic.Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), org.apache.bcel.generic.Type.INT, org.apache.bcel.generic.Type.INT, Util.getJCRefType("Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;"), org.apache.bcel.generic.Type.INT }, new String[] { "dom", "current", "level", "translet", "last" }, "extractValueFromDOM", className, il, cpg);
        final int levels = sortObjects.size();
        final int[] match = new int[levels];
        final InstructionHandle[] target = new InstructionHandle[levels];
        InstructionHandle tblswitch = null;
        if (levels > 1) {
            il.append(new ILOAD(extractMethod.getLocalIndex("level")));
            tblswitch = il.append(new NOP());
        }
        for (int level = 0; level < levels; ++level) {
            match[level] = level;
            final Sort sort = sortObjects.elementAt(level);
            target[level] = il.append(InstructionConstants.NOP);
            sort.translateSelect(sortRecord, extractMethod);
            il.append(InstructionConstants.ARETURN);
        }
        if (levels > 1) {
            final InstructionHandle defaultTarget = il.append(new PUSH(cpg, ""));
            il.insert(tblswitch, new TABLESWITCH(match, target, defaultTarget));
            il.append(InstructionConstants.ARETURN);
        }
        extractMethod.stripAttributes(true);
        extractMethod.setMaxLocals();
        extractMethod.setMaxStack();
        extractMethod.removeNOPs();
        return extractMethod.getMethod();
    }
}
