// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.IF_ICMPEQ;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.Util;
import com.ibm.xslt4j.bcel.generic.Type;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.BranchHandle;
import java.util.Vector;
import java.util.StringTokenizer;

final class Whitespace extends TopLevelElement
{
    public static final int USE_PREDICATE = 0;
    public static final int STRIP_SPACE = 1;
    public static final int PRESERVE_SPACE = 2;
    public static final int RULE_NONE = 0;
    public static final int RULE_ELEMENT = 1;
    public static final int RULE_NAMESPACE = 2;
    public static final int RULE_ALL = 3;
    private String _elementList;
    private int _action;
    private int _importPrecedence;
    
    public void parseContents(final Parser parser) {
        this._action = (super._qname.getLocalPart().endsWith("strip-space") ? 1 : 2);
        this._importPrecedence = parser.getCurrentImportPrecedence();
        this._elementList = this.getAttribute("elements");
        if (this._elementList == null || this._elementList.length() == 0) {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "elements");
            return;
        }
        final SymbolTable stable = parser.getSymbolTable();
        final StringTokenizer list = new StringTokenizer(this._elementList);
        final StringBuffer elements = new StringBuffer("");
        while (list.hasMoreElements()) {
            final String token = list.nextToken();
            final int col = token.indexOf(58);
            if (col != -1) {
                final String namespace = this.lookupNamespace(token.substring(0, col));
                if (namespace != null) {
                    elements.append(namespace + ":" + token.substring(col + 1, token.length()));
                }
                else {
                    elements.append(token);
                }
            }
            else {
                elements.append(token);
            }
            if (list.hasMoreElements()) {
                elements.append(" ");
            }
        }
        this._elementList = elements.toString();
    }
    
    public Vector getRules() {
        final Vector rules = new Vector();
        final StringTokenizer list = new StringTokenizer(this._elementList);
        while (list.hasMoreElements()) {
            rules.add(new WhitespaceRule(this._action, list.nextToken(), this._importPrecedence));
        }
        return rules;
    }
    
    private static WhitespaceRule findContradictingRule(final Vector rules, final WhitespaceRule rule) {
        for (int i = 0; i < rules.size(); ++i) {
            final WhitespaceRule currentRule = rules.elementAt(i);
            if (currentRule == rule) {
                return null;
            }
            switch (currentRule.getStrength()) {
                case 3: {
                    return currentRule;
                }
                case 1: {
                    if (!rule.getElement().equals(currentRule.getElement())) {
                        break;
                    }
                }
                case 2: {
                    if (rule.getNamespace().equals(currentRule.getNamespace())) {
                        return currentRule;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    private static int prioritizeRules(final Vector rules) {
        int defaultAction = 2;
        quicksort(rules, 0, rules.size() - 1);
        boolean strip = false;
        for (int i = 0; i < rules.size(); ++i) {
            final WhitespaceRule currentRule = rules.elementAt(i);
            if (currentRule.getAction() == 1) {
                strip = true;
            }
        }
        if (!strip) {
            rules.removeAllElements();
            return 2;
        }
        int idx = 0;
        while (idx < rules.size()) {
            final WhitespaceRule currentRule = rules.elementAt(idx);
            if (findContradictingRule(rules, currentRule) != null) {
                rules.remove(idx);
            }
            else {
                if (currentRule.getStrength() == 3) {
                    defaultAction = currentRule.getAction();
                    for (int j = idx; j < rules.size(); ++j) {
                        rules.removeElementAt(j);
                    }
                }
                ++idx;
            }
        }
        if (rules.size() == 0) {
            return defaultAction;
        }
        do {
            final WhitespaceRule currentRule = rules.lastElement();
            if (currentRule.getAction() != defaultAction) {
                break;
            }
            rules.removeElementAt(rules.size() - 1);
        } while (rules.size() > 0);
        return defaultAction;
    }
    
    public static void compileStripSpace(final BranchHandle[] strip, final int sCount, final InstructionList il) {
        final InstructionHandle target = il.append(InstructionConstants.ICONST_1);
        il.append(InstructionConstants.IRETURN);
        for (int i = 0; i < sCount; ++i) {
            strip[i].setTarget(target);
        }
    }
    
    public static void compilePreserveSpace(final BranchHandle[] preserve, final int pCount, final InstructionList il) {
        final InstructionHandle target = il.append(InstructionConstants.ICONST_0);
        il.append(InstructionConstants.IRETURN);
        for (int i = 0; i < pCount; ++i) {
            preserve[i].setTarget(target);
        }
    }
    
    private static void compilePredicate(final Vector rules, final int defaultAction, final ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();
        final XSLTC xsltc = classGen.getParser().getXSLTC();
        final MethodGenerator stripSpace = new MethodGenerator(17, Type.BOOLEAN, new Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Type.INT, Type.INT }, new String[] { "dom", "node", "type" }, "stripSpace", classGen.getClassName(), il, cpg);
        classGen.addInterface("org/apache/xalan/xsltc/StripFilter");
        final int paramDom = stripSpace.getLocalIndex("dom");
        final int paramCurrent = stripSpace.getLocalIndex("node");
        final int paramType = stripSpace.getLocalIndex("type");
        final BranchHandle[] strip = new BranchHandle[rules.size()];
        final BranchHandle[] preserve = new BranchHandle[rules.size()];
        int sCount = 0;
        int pCount = 0;
        for (int i = 0; i < rules.size(); ++i) {
            final WhitespaceRule rule = rules.elementAt(i);
            final int gns = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceName", "(I)Ljava/lang/String;");
            final int strcmp = cpg.addMethodref("java/lang/String", "compareTo", "(Ljava/lang/String;)I");
            if (rule.getStrength() == 2) {
                il.append(new ALOAD(paramDom));
                il.append(new ILOAD(paramCurrent));
                il.append(new INVOKEINTERFACE(gns, 2));
                il.append(new PUSH(cpg, rule.getNamespace()));
                il.append(new INVOKEVIRTUAL(strcmp));
                il.append(InstructionConstants.ICONST_0);
                if (rule.getAction() == 1) {
                    strip[sCount++] = il.append(new IF_ICMPEQ(null));
                }
                else {
                    preserve[pCount++] = il.append(new IF_ICMPEQ(null));
                }
            }
            else if (rule.getStrength() == 1) {
                final Parser parser = classGen.getParser();
                QName qname;
                if (rule.getNamespace() != "") {
                    qname = parser.getQName(rule.getNamespace(), null, rule.getElement());
                }
                else {
                    qname = parser.getQName(rule.getElement());
                }
                final int elementType = xsltc.registerElement(qname);
                il.append(new ILOAD(paramType));
                il.append(new PUSH(cpg, elementType));
                if (rule.getAction() == 1) {
                    strip[sCount++] = il.append(new IF_ICMPEQ(null));
                }
                else {
                    preserve[pCount++] = il.append(new IF_ICMPEQ(null));
                }
            }
        }
        if (defaultAction == 1) {
            compileStripSpace(strip, sCount, il);
            compilePreserveSpace(preserve, pCount, il);
        }
        else {
            compilePreserveSpace(preserve, pCount, il);
            compileStripSpace(strip, sCount, il);
        }
        stripSpace.stripAttributes(true);
        stripSpace.setMaxLocals();
        stripSpace.setMaxStack();
        stripSpace.removeNOPs();
        classGen.addMethod(stripSpace.getMethod());
    }
    
    private static void compileDefault(final int defaultAction, final ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();
        final XSLTC xsltc = classGen.getParser().getXSLTC();
        final MethodGenerator stripSpace = new MethodGenerator(17, Type.BOOLEAN, new Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Type.INT, Type.INT }, new String[] { "dom", "node", "type" }, "stripSpace", classGen.getClassName(), il, cpg);
        classGen.addInterface("org/apache/xalan/xsltc/StripFilter");
        if (defaultAction == 1) {
            il.append(InstructionConstants.ICONST_1);
        }
        else {
            il.append(InstructionConstants.ICONST_0);
        }
        il.append(InstructionConstants.IRETURN);
        stripSpace.stripAttributes(true);
        stripSpace.setMaxLocals();
        stripSpace.setMaxStack();
        stripSpace.removeNOPs();
        classGen.addMethod(stripSpace.getMethod());
    }
    
    public static int translateRules(final Vector rules, final ClassGenerator classGen) {
        final int defaultAction = prioritizeRules(rules);
        if (rules.size() == 0) {
            compileDefault(defaultAction, classGen);
            return defaultAction;
        }
        compilePredicate(rules, defaultAction, classGen);
        return 0;
    }
    
    private static void quicksort(final Vector rules, int p, final int r) {
        while (p < r) {
            final int q = partition(rules, p, r);
            quicksort(rules, p, q);
            p = q + 1;
        }
    }
    
    private static int partition(final Vector rules, final int p, final int r) {
        final WhitespaceRule x = rules.elementAt(p + r >>> 1);
        int i = p - 1;
        int j = r + 1;
        while (true) {
            if (x.compareTo(rules.elementAt(--j)) >= 0) {
                while (x.compareTo(rules.elementAt(++i)) > 0) {}
                if (i >= j) {
                    break;
                }
                final WhitespaceRule tmp = rules.elementAt(i);
                rules.setElementAt(rules.elementAt(j), i);
                rules.setElementAt(tmp, j);
            }
        }
        return j;
    }
    
    public org.apache.xalan.xsltc.compiler.util.Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return org.apache.xalan.xsltc.compiler.util.Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
    }
    
    private static final class WhitespaceRule
    {
        private final int _action;
        private String _namespace;
        private String _element;
        private int _type;
        private int _priority;
        
        public WhitespaceRule(final int action, final String element, final int precedence) {
            this._action = action;
            final int colon = element.lastIndexOf(58);
            if (colon >= 0) {
                this._namespace = element.substring(0, colon);
                this._element = element.substring(colon + 1, element.length());
            }
            else {
                this._namespace = "";
                this._element = element;
            }
            this._priority = precedence << 2;
            if (this._element.equals("*")) {
                if (this._namespace == "") {
                    this._type = 3;
                    this._priority += 2;
                }
                else {
                    this._type = 2;
                    ++this._priority;
                }
            }
            else {
                this._type = 1;
            }
        }
        
        public int compareTo(final WhitespaceRule other) {
            return (this._priority < other._priority) ? -1 : ((this._priority > other._priority) ? 1 : 0);
        }
        
        public int getAction() {
            return this._action;
        }
        
        public int getStrength() {
            return this._type;
        }
        
        public int getPriority() {
            return this._priority;
        }
        
        public String getElement() {
            return this._element;
        }
        
        public String getNamespace() {
            return this._namespace;
        }
    }
}
