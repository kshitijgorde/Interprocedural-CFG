// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Iterator;
import com.ibm.xslt4j.bcel.generic.DUP;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.TargetLostException;
import com.ibm.xslt4j.bcel.util.InstructionFinder;
import com.ibm.xslt4j.bcel.generic.BranchHandle;
import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.IFLT;
import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.SWITCH;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.GOTO_W;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.NamedMethodGenerator;
import com.ibm.xslt4j.bcel.generic.Type;
import org.apache.xalan.xsltc.compiler.util.Util;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

final class Mode implements Constants
{
    private final QName _name;
    private final Stylesheet _stylesheet;
    private final String _methodName;
    private Vector _templates;
    private Vector _childNodeGroup;
    private TestSeq _childNodeTestSeq;
    private Vector _attribNodeGroup;
    private TestSeq _attribNodeTestSeq;
    private Vector _idxGroup;
    private TestSeq _idxTestSeq;
    private Vector[] _patternGroups;
    private TestSeq[] _testSeq;
    private Hashtable _neededTemplates;
    private Hashtable _namedTemplates;
    private Hashtable _templateIHs;
    private Hashtable _templateILs;
    private LocationPathPattern _rootPattern;
    private Hashtable _importLevels;
    private Hashtable _keys;
    private int _currentIndex;
    
    public Mode(final QName name, final Stylesheet stylesheet, final String suffix) {
        this._childNodeGroup = null;
        this._childNodeTestSeq = null;
        this._attribNodeGroup = null;
        this._attribNodeTestSeq = null;
        this._idxGroup = null;
        this._idxTestSeq = null;
        this._neededTemplates = new Hashtable();
        this._namedTemplates = new Hashtable();
        this._templateIHs = new Hashtable();
        this._templateILs = new Hashtable();
        this._rootPattern = null;
        this._importLevels = null;
        this._keys = null;
        this._name = name;
        this._stylesheet = stylesheet;
        this._methodName = "applyTemplates" + suffix;
        this._templates = new Vector();
        this._patternGroups = new Vector[32];
    }
    
    public String functionName() {
        return this._methodName;
    }
    
    public String functionName(final int min, final int max) {
        if (this._importLevels == null) {
            this._importLevels = new Hashtable();
        }
        this._importLevels.put(new Integer(max), new Integer(min));
        return this._methodName + '_' + max;
    }
    
    private String getClassName() {
        return this._stylesheet.getClassName();
    }
    
    public Stylesheet getStylesheet() {
        return this._stylesheet;
    }
    
    public void addTemplate(final Template template) {
        this._templates.addElement(template);
    }
    
    private Vector quicksort(final Vector templates, final int p, final int r) {
        if (p < r) {
            final int q = this.partition(templates, p, r);
            this.quicksort(templates, p, q);
            this.quicksort(templates, q + 1, r);
        }
        return templates;
    }
    
    private int partition(final Vector templates, final int p, final int r) {
        final Template x = templates.elementAt(p);
        int i = p - 1;
        int j = r + 1;
        while (true) {
            if (x.compareTo(templates.elementAt(--j)) <= 0) {
                while (x.compareTo(templates.elementAt(++i)) < 0) {}
                if (i >= j) {
                    break;
                }
                templates.set(j, templates.set(i, templates.elementAt(j)));
            }
        }
        return j;
    }
    
    public void processPatterns(final Hashtable keys) {
        this._keys = keys;
        this._templates = this.quicksort(this._templates, 0, this._templates.size() - 1);
        final Enumeration templates = this._templates.elements();
        while (templates.hasMoreElements()) {
            final Template template = templates.nextElement();
            if (template.isNamed() && !template.disabled()) {
                this._namedTemplates.put(template, this);
            }
            final Pattern pattern = template.getPattern();
            if (pattern != null) {
                this.flattenAlternative(pattern, template, keys);
            }
        }
        this.prepareTestSequences();
    }
    
    private void flattenAlternative(final Pattern pattern, final Template template, final Hashtable keys) {
        if (pattern instanceof IdKeyPattern) {
            final IdKeyPattern idkey = (IdKeyPattern)pattern;
            idkey.setTemplate(template);
            if (this._idxGroup == null) {
                this._idxGroup = new Vector();
            }
            this._idxGroup.add(pattern);
        }
        else if (pattern instanceof AlternativePattern) {
            final AlternativePattern alt = (AlternativePattern)pattern;
            this.flattenAlternative(alt.getLeft(), template, keys);
            this.flattenAlternative(alt.getRight(), template, keys);
        }
        else if (pattern instanceof LocationPathPattern) {
            final LocationPathPattern lpp = (LocationPathPattern)pattern;
            lpp.setTemplate(template);
            this.addPatternToGroup(lpp);
        }
    }
    
    private void addPatternToGroup(final LocationPathPattern lpp) {
        if (lpp instanceof IdKeyPattern) {
            this.addPattern(-1, lpp);
        }
        else {
            final StepPattern kernel = lpp.getKernelPattern();
            if (kernel != null) {
                this.addPattern(kernel.getNodeType(), lpp);
            }
            else if (this._rootPattern == null || lpp.noSmallerThan(this._rootPattern)) {
                this._rootPattern = lpp;
            }
        }
    }
    
    private void addPattern(final int kernelType, final LocationPathPattern pattern) {
        final int oldLength = this._patternGroups.length;
        if (kernelType >= oldLength) {
            final Vector[] newGroups = new Vector[kernelType * 2];
            System.arraycopy(this._patternGroups, 0, newGroups, 0, oldLength);
            this._patternGroups = newGroups;
        }
        Vector patterns;
        if (kernelType == -1) {
            if (pattern.getAxis() == 2) {
                patterns = ((this._attribNodeGroup == null) ? (this._attribNodeGroup = new Vector(2)) : this._attribNodeGroup);
            }
            else {
                patterns = ((this._childNodeGroup == null) ? (this._childNodeGroup = new Vector(2)) : this._childNodeGroup);
            }
        }
        else {
            patterns = ((this._patternGroups[kernelType] == null) ? (this._patternGroups[kernelType] = new Vector(2)) : this._patternGroups[kernelType]);
        }
        if (patterns.size() == 0) {
            patterns.addElement(pattern);
        }
        else {
            boolean inserted = false;
            for (int i = 0; i < patterns.size(); ++i) {
                final LocationPathPattern lppToCompare = patterns.elementAt(i);
                if (pattern.noSmallerThan(lppToCompare)) {
                    inserted = true;
                    patterns.insertElementAt(pattern, i);
                    break;
                }
            }
            if (!inserted) {
                patterns.addElement(pattern);
            }
        }
    }
    
    private void completeTestSequences(final int nodeType, final Vector patterns) {
        if (patterns != null) {
            if (this._patternGroups[nodeType] == null) {
                this._patternGroups[nodeType] = patterns;
            }
            else {
                for (int m = patterns.size(), j = 0; j < m; ++j) {
                    this.addPattern(nodeType, patterns.elementAt(j));
                }
            }
        }
    }
    
    private void prepareTestSequences() {
        final Vector starGroup = this._patternGroups[1];
        final Vector atStarGroup = this._patternGroups[2];
        this.completeTestSequences(3, this._childNodeGroup);
        this.completeTestSequences(1, this._childNodeGroup);
        this.completeTestSequences(7, this._childNodeGroup);
        this.completeTestSequences(8, this._childNodeGroup);
        this.completeTestSequences(2, this._attribNodeGroup);
        final Vector names = this._stylesheet.getXSLTC().getNamesIndex();
        if (starGroup != null || atStarGroup != null || this._childNodeGroup != null || this._attribNodeGroup != null) {
            for (int n = this._patternGroups.length, i = 14; i < n; ++i) {
                if (this._patternGroups[i] != null) {
                    final String name = names.elementAt(i - 14);
                    if (isAttributeName(name)) {
                        this.completeTestSequences(i, atStarGroup);
                        this.completeTestSequences(i, this._attribNodeGroup);
                    }
                    else {
                        this.completeTestSequences(i, starGroup);
                        this.completeTestSequences(i, this._childNodeGroup);
                    }
                }
            }
        }
        this._testSeq = new TestSeq[14 + names.size()];
        for (int n = this._patternGroups.length, i = 0; i < n; ++i) {
            final Vector patterns = this._patternGroups[i];
            if (patterns != null) {
                final TestSeq testSeq = new TestSeq(patterns, i, this);
                testSeq.reduce();
                (this._testSeq[i] = testSeq).findTemplates(this._neededTemplates);
            }
        }
        if (this._childNodeGroup != null && this._childNodeGroup.size() > 0) {
            (this._childNodeTestSeq = new TestSeq(this._childNodeGroup, -1, this)).reduce();
            this._childNodeTestSeq.findTemplates(this._neededTemplates);
        }
        if (this._idxGroup != null && this._idxGroup.size() > 0) {
            (this._idxTestSeq = new TestSeq(this._idxGroup, this)).reduce();
            this._idxTestSeq.findTemplates(this._neededTemplates);
        }
        if (this._rootPattern != null) {
            this._neededTemplates.put(this._rootPattern.getTemplate(), this);
        }
    }
    
    private void compileNamedTemplate(final Template template, final ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();
        final String methodName = Util.escape(template.getName().toString());
        int numParams = 0;
        if (template.isSimpleNamedTemplate()) {
            final Vector parameters = template.getParameters();
            numParams = parameters.size();
        }
        final Type[] types = new Type[4 + numParams];
        final String[] names = new String[4 + numParams];
        types[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
        types[1] = Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;");
        types[2] = Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;");
        types[3] = Type.INT;
        names[0] = "document";
        names[1] = "iterator";
        names[2] = "handler";
        names[3] = "node";
        for (int i = 4; i < 4 + numParams; ++i) {
            types[i] = Util.getJCRefType("Ljava/lang/Object;");
            names[i] = "param" + String.valueOf(i - 4);
        }
        final NamedMethodGenerator methodGen = new NamedMethodGenerator(1, Type.VOID, types, names, methodName, this.getClassName(), il, cpg);
        il.append(template.compile(classGen, methodGen));
        il.append(InstructionConstants.RETURN);
        methodGen.stripAttributes(true);
        methodGen.setMaxLocals();
        methodGen.setMaxStack();
        methodGen.removeNOPs();
        classGen.addMethod(methodGen.getMethod());
    }
    
    private void compileTemplates(final ClassGenerator classGen, final MethodGenerator methodGen, final InstructionHandle next) {
        Enumeration templates = this._namedTemplates.keys();
        while (templates.hasMoreElements()) {
            final Template template = templates.nextElement();
            this.compileNamedTemplate(template, classGen);
        }
        templates = this._neededTemplates.keys();
        while (templates.hasMoreElements()) {
            final Template template = templates.nextElement();
            if (template.hasContents()) {
                final InstructionList til = template.compile(classGen, methodGen);
                til.append(new GOTO_W(next));
                this._templateILs.put(template, til);
                this._templateIHs.put(template, til.getStart());
            }
            else {
                this._templateIHs.put(template, next);
            }
        }
    }
    
    private void appendTemplateCode(final InstructionList body) {
        final Enumeration templates = this._neededTemplates.keys();
        while (templates.hasMoreElements()) {
            final Object iList = this._templateILs.get(templates.nextElement());
            if (iList != null) {
                body.append((InstructionList)iList);
            }
        }
    }
    
    private void appendTestSequences(final InstructionList body) {
        for (int n = this._testSeq.length, i = 0; i < n; ++i) {
            final TestSeq testSeq = this._testSeq[i];
            if (testSeq != null) {
                final InstructionList il = testSeq.getInstructionList();
                if (il != null) {
                    body.append(il);
                }
            }
        }
    }
    
    public static void compileGetChildren(final ClassGenerator classGen, final MethodGenerator methodGen, final int node) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getChildren", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
        il.append(methodGen.loadDOM());
        il.append(new ILOAD(node));
        il.append(new INVOKEINTERFACE(git, 2));
    }
    
    private InstructionList compileDefaultRecursion(final ClassGenerator classGen, final MethodGenerator methodGen, final InstructionHandle next) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();
        final String applyTemplatesSig = classGen.getApplyTemplatesSig();
        final int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getChildren", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
        final int applyTemplates = cpg.addMethodref(this.getClassName(), this.functionName(), applyTemplatesSig);
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadDOM());
        il.append(new ILOAD(this._currentIndex));
        il.append(new INVOKEINTERFACE(git, 2));
        il.append(methodGen.loadHandler());
        il.append(new INVOKEVIRTUAL(applyTemplates));
        il.append(new GOTO_W(next));
        return il;
    }
    
    private InstructionList compileDefaultText(final ClassGenerator classGen, final MethodGenerator methodGen, final InstructionHandle next) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();
        final int chars = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "characters", "(ILorg/apache/xml/serializer/SerializationHandler;)V");
        il.append(methodGen.loadDOM());
        il.append(new ILOAD(this._currentIndex));
        il.append(methodGen.loadHandler());
        il.append(new INVOKEINTERFACE(chars, 3));
        il.append(new GOTO_W(next));
        return il;
    }
    
    private InstructionList compileNamespaces(final ClassGenerator classGen, final MethodGenerator methodGen, final boolean[] isNamespace, final boolean[] isAttribute, final boolean attrFlag, final InstructionHandle defaultTarget) {
        final XSLTC xsltc = classGen.getParser().getXSLTC();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final Vector namespaces = xsltc.getNamespaceIndex();
        final Vector names = xsltc.getNamesIndex();
        final int namespaceCount = namespaces.size() + 1;
        final int namesCount = names.size();
        final InstructionList il = new InstructionList();
        final int[] types = new int[namespaceCount];
        final InstructionHandle[] targets = new InstructionHandle[types.length];
        if (namespaceCount <= 0) {
            return null;
        }
        boolean compiled = false;
        for (int i = 0; i < namespaceCount; ++i) {
            targets[i] = defaultTarget;
            types[i] = i;
        }
        for (int j = 14; j < 14 + namesCount; ++j) {
            if (isNamespace[j] && isAttribute[j] == attrFlag) {
                final String name = names.elementAt(j - 14);
                final String namespace = name.substring(0, name.lastIndexOf(58));
                final int type = xsltc.registerNamespace(namespace);
                if (j < this._testSeq.length && this._testSeq[j] != null) {
                    targets[type] = this._testSeq[j].compile(classGen, methodGen, defaultTarget);
                    compiled = true;
                }
            }
        }
        if (!compiled) {
            return null;
        }
        final int getNS = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceType", "(I)I");
        il.append(methodGen.loadDOM());
        il.append(new ILOAD(this._currentIndex));
        il.append(new INVOKEINTERFACE(getNS, 2));
        il.append(new SWITCH(types, targets, defaultTarget));
        return il;
    }
    
    public void compileApplyTemplates(final ClassGenerator classGen) {
        final XSLTC xsltc = classGen.getParser().getXSLTC();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final Vector names = xsltc.getNamesIndex();
        final Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;") };
        final String[] argNames = { "document", "iterator", "handler" };
        final InstructionList mainIL = new InstructionList();
        final MethodGenerator methodGen = new MethodGenerator(17, Type.VOID, argTypes, argNames, this.functionName(), this.getClassName(), mainIL, classGen.getConstantPool());
        methodGen.addException("org.apache.xalan.xsltc.TransletException");
        final LocalVariableGen current = methodGen.addLocalVariable2("current", Type.INT, mainIL.getEnd());
        this._currentIndex = current.getIndex();
        final InstructionList body = new InstructionList();
        body.append(InstructionConstants.NOP);
        final InstructionList ilLoop = new InstructionList();
        ilLoop.append(methodGen.loadIterator());
        ilLoop.append(methodGen.nextNode());
        ilLoop.append(InstructionConstants.DUP);
        ilLoop.append(new ISTORE(this._currentIndex));
        final BranchHandle ifeq = ilLoop.append(new IFLT(null));
        final BranchHandle loop = ilLoop.append(new GOTO_W(null));
        ifeq.setTarget(ilLoop.append(InstructionConstants.RETURN));
        final InstructionHandle ihLoop = ilLoop.getStart();
        final InstructionList ilRecurse = this.compileDefaultRecursion(classGen, methodGen, ihLoop);
        final InstructionHandle ihRecurse = ilRecurse.getStart();
        final InstructionList ilText = this.compileDefaultText(classGen, methodGen, ihLoop);
        InstructionHandle ihText = ilText.getStart();
        final int[] types = new int[14 + names.size()];
        for (int i = 0; i < types.length; ++i) {
            types[i] = i;
        }
        final boolean[] isAttribute = new boolean[types.length];
        final boolean[] isNamespace = new boolean[types.length];
        for (int j = 0; j < names.size(); ++j) {
            final String name = names.elementAt(j);
            isAttribute[j + 14] = isAttributeName(name);
            isNamespace[j + 14] = isNamespaceName(name);
        }
        this.compileTemplates(classGen, methodGen, ihLoop);
        final TestSeq elemTest = this._testSeq[1];
        InstructionHandle ihElem = ihRecurse;
        if (elemTest != null) {
            ihElem = elemTest.compile(classGen, methodGen, ihRecurse);
        }
        final TestSeq attrTest = this._testSeq[2];
        InstructionHandle ihAttr = ihText;
        if (attrTest != null) {
            ihAttr = attrTest.compile(classGen, methodGen, ihAttr);
        }
        InstructionList ilKey = null;
        if (this._idxTestSeq != null) {
            loop.setTarget(this._idxTestSeq.compile(classGen, methodGen, body.getStart()));
            ilKey = this._idxTestSeq.getInstructionList();
        }
        else {
            loop.setTarget(body.getStart());
        }
        if (this._childNodeTestSeq != null) {
            final double nodePrio = this._childNodeTestSeq.getPriority();
            final int nodePos = this._childNodeTestSeq.getPosition();
            double elemPrio = -1.7976931348623157E308;
            int elemPos = Integer.MIN_VALUE;
            if (elemTest != null) {
                elemPrio = elemTest.getPriority();
                elemPos = elemTest.getPosition();
            }
            if (elemPrio == Double.NaN || elemPrio < nodePrio || (elemPrio == nodePrio && elemPos < nodePos)) {
                ihElem = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
            }
            final TestSeq textTest = this._testSeq[3];
            double textPrio = -1.7976931348623157E308;
            int textPos = Integer.MIN_VALUE;
            if (textTest != null) {
                textPrio = textTest.getPriority();
                textPos = textTest.getPosition();
            }
            if (textPrio == Double.NaN || textPrio < nodePrio || (textPrio == nodePrio && textPos < nodePos)) {
                ihText = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
                this._testSeq[3] = this._childNodeTestSeq;
            }
        }
        InstructionHandle elemNamespaceHandle = ihElem;
        final InstructionList nsElem = this.compileNamespaces(classGen, methodGen, isNamespace, isAttribute, false, ihElem);
        if (nsElem != null) {
            elemNamespaceHandle = nsElem.getStart();
        }
        InstructionHandle attrNamespaceHandle = ihAttr;
        final InstructionList nsAttr = this.compileNamespaces(classGen, methodGen, isNamespace, isAttribute, true, ihAttr);
        if (nsAttr != null) {
            attrNamespaceHandle = nsAttr.getStart();
        }
        final InstructionHandle[] targets = new InstructionHandle[types.length];
        for (int k = 14; k < targets.length; ++k) {
            final TestSeq testSeq = this._testSeq[k];
            if (isNamespace[k]) {
                if (isAttribute[k]) {
                    targets[k] = attrNamespaceHandle;
                }
                else {
                    targets[k] = elemNamespaceHandle;
                }
            }
            else if (testSeq != null) {
                if (isAttribute[k]) {
                    targets[k] = testSeq.compile(classGen, methodGen, attrNamespaceHandle);
                }
                else {
                    targets[k] = testSeq.compile(classGen, methodGen, elemNamespaceHandle);
                }
            }
            else {
                targets[k] = ihLoop;
            }
        }
        targets[0] = ((this._rootPattern != null) ? this.getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse);
        targets[9] = ((this._rootPattern != null) ? this.getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse);
        targets[3] = ((this._testSeq[3] != null) ? this._testSeq[3].compile(classGen, methodGen, ihText) : ihText);
        targets[13] = ihLoop;
        targets[1] = elemNamespaceHandle;
        targets[2] = attrNamespaceHandle;
        InstructionHandle ihPI = ihLoop;
        if (this._childNodeTestSeq != null) {
            ihPI = ihElem;
        }
        if (this._testSeq[7] != null) {
            targets[7] = this._testSeq[7].compile(classGen, methodGen, ihPI);
        }
        else {
            targets[7] = ihPI;
        }
        InstructionHandle ihComment = ihLoop;
        if (this._childNodeTestSeq != null) {
            ihComment = ihElem;
        }
        targets[8] = ((this._testSeq[8] != null) ? this._testSeq[8].compile(classGen, methodGen, ihComment) : ihComment);
        targets[11] = (targets[4] = ihLoop);
        targets[6] = (targets[10] = ihLoop);
        targets[12] = (targets[5] = ihLoop);
        for (int l = 14; l < targets.length; ++l) {
            final TestSeq testSeq2 = this._testSeq[l];
            if (testSeq2 == null || isNamespace[l]) {
                if (isAttribute[l]) {
                    targets[l] = attrNamespaceHandle;
                }
                else {
                    targets[l] = elemNamespaceHandle;
                }
            }
            else if (isAttribute[l]) {
                targets[l] = testSeq2.compile(classGen, methodGen, attrNamespaceHandle);
            }
            else {
                targets[l] = testSeq2.compile(classGen, methodGen, elemNamespaceHandle);
            }
        }
        if (ilKey != null) {
            body.insert(ilKey);
        }
        final int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
        body.append(methodGen.loadDOM());
        body.append(new ILOAD(this._currentIndex));
        body.append(new INVOKEINTERFACE(getType, 2));
        final InstructionHandle disp = body.append(new SWITCH(types, targets, ihLoop));
        this.appendTestSequences(body);
        this.appendTemplateCode(body);
        if (nsElem != null) {
            body.append(nsElem);
        }
        if (nsAttr != null) {
            body.append(nsAttr);
        }
        body.append(ilRecurse);
        body.append(ilText);
        mainIL.append(new GOTO_W(ihLoop));
        mainIL.append(body);
        mainIL.append(ilLoop);
        this.peepHoleOptimization(methodGen);
        methodGen.stripAttributes(true);
        methodGen.setMaxLocals();
        methodGen.setMaxStack();
        methodGen.removeNOPs();
        classGen.addMethod(methodGen.getMethod());
        if (this._importLevels != null) {
            final Enumeration levels = this._importLevels.keys();
            while (levels.hasMoreElements()) {
                final Integer max = levels.nextElement();
                final Integer min = this._importLevels.get(max);
                this.compileApplyImports(classGen, min, max);
            }
        }
    }
    
    private void compileTemplateCalls(final ClassGenerator classGen, final MethodGenerator methodGen, final InstructionHandle next, final int min, final int max) {
        final Enumeration templates = this._neededTemplates.keys();
        while (templates.hasMoreElements()) {
            final Template template = templates.nextElement();
            final int prec = template.getImportPrecedence();
            if (prec >= min && prec < max) {
                if (template.hasContents()) {
                    final InstructionList til = template.compile(classGen, methodGen);
                    til.append(new GOTO_W(next));
                    this._templateILs.put(template, til);
                    this._templateIHs.put(template, til.getStart());
                }
                else {
                    this._templateIHs.put(template, next);
                }
            }
        }
    }
    
    public void compileApplyImports(final ClassGenerator classGen, final int min, final int max) {
        final XSLTC xsltc = classGen.getParser().getXSLTC();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final Vector names = xsltc.getNamesIndex();
        this._namedTemplates = new Hashtable();
        this._neededTemplates = new Hashtable();
        this._templateIHs = new Hashtable();
        this._templateILs = new Hashtable();
        this._patternGroups = new Vector[32];
        this._rootPattern = null;
        final Vector oldTemplates = this._templates;
        this._templates = new Vector();
        final Enumeration templates = oldTemplates.elements();
        while (templates.hasMoreElements()) {
            final Template template = templates.nextElement();
            final int prec = template.getImportPrecedence();
            if (prec >= min && prec < max) {
                this.addTemplate(template);
            }
        }
        this.processPatterns(this._keys);
        final Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;"), Type.INT };
        final String[] argNames = { "document", "iterator", "handler", "node" };
        final InstructionList mainIL = new InstructionList();
        final MethodGenerator methodGen = new MethodGenerator(17, Type.VOID, argTypes, argNames, this.functionName() + '_' + max, this.getClassName(), mainIL, classGen.getConstantPool());
        methodGen.addException("org.apache.xalan.xsltc.TransletException");
        final LocalVariableGen current = methodGen.addLocalVariable2("current", Type.INT, mainIL.getEnd());
        this._currentIndex = current.getIndex();
        mainIL.append(new ILOAD(methodGen.getLocalIndex("node")));
        mainIL.append(new ISTORE(this._currentIndex));
        final InstructionList body = new InstructionList();
        body.append(InstructionConstants.NOP);
        final InstructionList ilLoop = new InstructionList();
        ilLoop.append(InstructionConstants.RETURN);
        final InstructionHandle ihLoop = ilLoop.getStart();
        final InstructionList ilRecurse = this.compileDefaultRecursion(classGen, methodGen, ihLoop);
        final InstructionHandle ihRecurse = ilRecurse.getStart();
        final InstructionList ilText = this.compileDefaultText(classGen, methodGen, ihLoop);
        InstructionHandle ihText = ilText.getStart();
        final int[] types = new int[14 + names.size()];
        for (int i = 0; i < types.length; ++i) {
            types[i] = i;
        }
        final boolean[] isAttribute = new boolean[types.length];
        final boolean[] isNamespace = new boolean[types.length];
        for (int j = 0; j < names.size(); ++j) {
            final String name = names.elementAt(j);
            isAttribute[j + 14] = isAttributeName(name);
            isNamespace[j + 14] = isNamespaceName(name);
        }
        this.compileTemplateCalls(classGen, methodGen, ihLoop, min, max);
        final TestSeq elemTest = this._testSeq[1];
        InstructionHandle ihElem = ihRecurse;
        if (elemTest != null) {
            ihElem = elemTest.compile(classGen, methodGen, ihLoop);
        }
        final TestSeq attrTest = this._testSeq[2];
        InstructionHandle ihAttr = ihLoop;
        if (attrTest != null) {
            ihAttr = attrTest.compile(classGen, methodGen, ihAttr);
        }
        InstructionList ilKey = null;
        if (this._idxTestSeq != null) {
            ilKey = this._idxTestSeq.getInstructionList();
        }
        if (this._childNodeTestSeq != null) {
            final double nodePrio = this._childNodeTestSeq.getPriority();
            final int nodePos = this._childNodeTestSeq.getPosition();
            double elemPrio = -1.7976931348623157E308;
            int elemPos = Integer.MIN_VALUE;
            if (elemTest != null) {
                elemPrio = elemTest.getPriority();
                elemPos = elemTest.getPosition();
            }
            if (elemPrio == Double.NaN || elemPrio < nodePrio || (elemPrio == nodePrio && elemPos < nodePos)) {
                ihElem = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
            }
            final TestSeq textTest = this._testSeq[3];
            double textPrio = -1.7976931348623157E308;
            int textPos = Integer.MIN_VALUE;
            if (textTest != null) {
                textPrio = textTest.getPriority();
                textPos = textTest.getPosition();
            }
            if (textPrio == Double.NaN || textPrio < nodePrio || (textPrio == nodePrio && textPos < nodePos)) {
                ihText = this._childNodeTestSeq.compile(classGen, methodGen, ihLoop);
                this._testSeq[3] = this._childNodeTestSeq;
            }
        }
        InstructionHandle elemNamespaceHandle = ihElem;
        final InstructionList nsElem = this.compileNamespaces(classGen, methodGen, isNamespace, isAttribute, false, ihElem);
        if (nsElem != null) {
            elemNamespaceHandle = nsElem.getStart();
        }
        final InstructionList nsAttr = this.compileNamespaces(classGen, methodGen, isNamespace, isAttribute, true, ihAttr);
        InstructionHandle attrNamespaceHandle = ihAttr;
        if (nsAttr != null) {
            attrNamespaceHandle = nsAttr.getStart();
        }
        final InstructionHandle[] targets = new InstructionHandle[types.length];
        for (int k = 14; k < targets.length; ++k) {
            final TestSeq testSeq = this._testSeq[k];
            if (isNamespace[k]) {
                if (isAttribute[k]) {
                    targets[k] = attrNamespaceHandle;
                }
                else {
                    targets[k] = elemNamespaceHandle;
                }
            }
            else if (testSeq != null) {
                if (isAttribute[k]) {
                    targets[k] = testSeq.compile(classGen, methodGen, attrNamespaceHandle);
                }
                else {
                    targets[k] = testSeq.compile(classGen, methodGen, elemNamespaceHandle);
                }
            }
            else {
                targets[k] = ihLoop;
            }
        }
        targets[0] = ((this._rootPattern != null) ? this.getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse);
        targets[9] = ((this._rootPattern != null) ? this.getTemplateInstructionHandle(this._rootPattern.getTemplate()) : ihRecurse);
        targets[3] = ((this._testSeq[3] != null) ? this._testSeq[3].compile(classGen, methodGen, ihText) : ihText);
        targets[13] = ihLoop;
        targets[1] = elemNamespaceHandle;
        targets[2] = attrNamespaceHandle;
        InstructionHandle ihPI = ihLoop;
        if (this._childNodeTestSeq != null) {
            ihPI = ihElem;
        }
        if (this._testSeq[7] != null) {
            targets[7] = this._testSeq[7].compile(classGen, methodGen, ihPI);
        }
        else {
            targets[7] = ihPI;
        }
        InstructionHandle ihComment = ihLoop;
        if (this._childNodeTestSeq != null) {
            ihComment = ihElem;
        }
        targets[8] = ((this._testSeq[8] != null) ? this._testSeq[8].compile(classGen, methodGen, ihComment) : ihComment);
        targets[11] = (targets[4] = ihLoop);
        targets[6] = (targets[10] = ihLoop);
        targets[12] = (targets[5] = ihLoop);
        for (int l = 14; l < targets.length; ++l) {
            final TestSeq testSeq2 = this._testSeq[l];
            if (testSeq2 == null || isNamespace[l]) {
                if (isAttribute[l]) {
                    targets[l] = attrNamespaceHandle;
                }
                else {
                    targets[l] = elemNamespaceHandle;
                }
            }
            else if (isAttribute[l]) {
                targets[l] = testSeq2.compile(classGen, methodGen, attrNamespaceHandle);
            }
            else {
                targets[l] = testSeq2.compile(classGen, methodGen, elemNamespaceHandle);
            }
        }
        if (ilKey != null) {
            body.insert(ilKey);
        }
        final int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
        body.append(methodGen.loadDOM());
        body.append(new ILOAD(this._currentIndex));
        body.append(new INVOKEINTERFACE(getType, 2));
        final InstructionHandle disp = body.append(new SWITCH(types, targets, ihLoop));
        this.appendTestSequences(body);
        this.appendTemplateCode(body);
        if (nsElem != null) {
            body.append(nsElem);
        }
        if (nsAttr != null) {
            body.append(nsAttr);
        }
        body.append(ilRecurse);
        body.append(ilText);
        mainIL.append(body);
        mainIL.append(ilLoop);
        this.peepHoleOptimization(methodGen);
        methodGen.stripAttributes(true);
        methodGen.setMaxLocals();
        methodGen.setMaxStack();
        methodGen.removeNOPs();
        classGen.addMethod(methodGen.getMethod());
        this._templates = oldTemplates;
    }
    
    private void peepHoleOptimization(final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        final InstructionFinder find = new InstructionFinder(il);
        String pattern = "LoadInstruction POP";
        final Iterator iter = find.search(pattern);
        while (iter.hasNext()) {
            final InstructionHandle[] match = iter.next();
            try {
                if (match[0].hasTargeters() || match[1].hasTargeters()) {
                    continue;
                }
                il.delete(match[0], match[1]);
            }
            catch (TargetLostException ex) {}
        }
        pattern = "ILOAD ILOAD SWAP ISTORE";
        final Iterator iter2 = find.search(pattern);
        while (iter2.hasNext()) {
            final InstructionHandle[] match2 = iter2.next();
            try {
                final ILOAD iload1 = (ILOAD)match2[0].getInstruction();
                final ILOAD iload2 = (ILOAD)match2[1].getInstruction();
                final ISTORE istore = (ISTORE)match2[3].getInstruction();
                if (match2[1].hasTargeters() || match2[2].hasTargeters() || match2[3].hasTargeters() || iload1.getIndex() != iload2.getIndex() || iload2.getIndex() != istore.getIndex()) {
                    continue;
                }
                il.delete(match2[1], match2[3]);
            }
            catch (TargetLostException ex2) {}
        }
        pattern = "LoadInstruction LoadInstruction SWAP";
        final Iterator iter3 = find.search(pattern);
        while (iter3.hasNext()) {
            final InstructionHandle[] match3 = iter3.next();
            try {
                if (match3[0].hasTargeters() || match3[1].hasTargeters() || match3[2].hasTargeters()) {
                    continue;
                }
                final Instruction load_m = match3[1].getInstruction();
                il.insert(match3[0], load_m);
                il.delete(match3[1], match3[2]);
            }
            catch (TargetLostException ex3) {}
        }
        pattern = "ALOAD ALOAD";
        final Iterator iter4 = find.search(pattern);
        while (iter4.hasNext()) {
            final InstructionHandle[] match4 = iter4.next();
            try {
                if (match4[1].hasTargeters()) {
                    continue;
                }
                final ALOAD aload1 = (ALOAD)match4[0].getInstruction();
                final ALOAD aload2 = (ALOAD)match4[1].getInstruction();
                if (aload1.getIndex() != aload2.getIndex()) {
                    continue;
                }
                il.insert(match4[1], new DUP());
                il.delete(match4[1]);
            }
            catch (TargetLostException ex4) {}
        }
    }
    
    public InstructionHandle getTemplateInstructionHandle(final Template template) {
        return this._templateIHs.get(template);
    }
    
    private static boolean isAttributeName(final String qname) {
        final int col = qname.lastIndexOf(58) + 1;
        return qname.charAt(col) == '@';
    }
    
    private static boolean isNamespaceName(final String qname) {
        final int col = qname.lastIndexOf(58);
        return col > -1 && qname.charAt(qname.length() - 1) == '*';
    }
}
