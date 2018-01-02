// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Vector;
import com.ibm.xslt4j.java_cup.runtime.Symbol;
import java.util.Stack;
import com.ibm.xslt4j.java_cup.runtime.lr_parser;

class CUP$XPathParser$actions
{
    private final XPathParser parser;
    
    CUP$XPathParser$actions(final XPathParser parser) {
        this.parser = parser;
    }
    
    public final Symbol CUP$XPathParser$do_action(final int CUP$XPathParser$act_num, final lr_parser CUP$XPathParser$parser, final Stack CUP$XPathParser$stack, final int CUP$XPathParser$top) throws Exception {
        switch (CUP$XPathParser$act_num) {
            case 140: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("id");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 139: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("self");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 138: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("preceding-sibling");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 137: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("preceding");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 136: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("parent");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 135: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("namespace");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 134: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("following-sibling");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 133: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("following");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 132: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("decendant-or-self");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 131: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("decendant");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 130: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("child");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 129: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("attribute");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 128: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("ancestor-or-self");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 127: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("child");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 126: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("key");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 125: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("mod");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 124: {
                QName RESULT = null;
                RESULT = this.parser.getQNameIgnoreDefaultNs("div");
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 123: {
                QName RESULT = null;
                final int qnameleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int qnameright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final String qname = (String)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT = this.parser.getQNameIgnoreDefaultNs(qname);
                final Symbol CUP$XPathParser$result = new Symbol(37, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 122: {
                Object RESULT2 = null;
                final int qnleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int qnright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final QName qn = (QName)(RESULT2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value);
                final Symbol CUP$XPathParser$result = new Symbol(26, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 121: {
                Object RESULT2 = null;
                RESULT2 = null;
                final Symbol CUP$XPathParser$result = new Symbol(26, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 120: {
                Object RESULT2 = null;
                RESULT2 = new Integer(7);
                final Symbol CUP$XPathParser$result = new Symbol(25, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 119: {
                Object RESULT2 = null;
                final int lleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int lright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final String l = (String)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final QName name = this.parser.getQNameIgnoreDefaultNs("name");
                final Expression exp = new EqualityExpr(0, new NameCall(name), new LiteralExpr(l));
                final Vector predicates = new Vector();
                predicates.addElement(new Predicate(exp));
                RESULT2 = new Step(3, 7, predicates);
                final Symbol CUP$XPathParser$result = new Symbol(25, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 118: {
                Object RESULT2 = null;
                RESULT2 = new Integer(8);
                final Symbol CUP$XPathParser$result = new Symbol(25, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 117: {
                Object RESULT2 = null;
                RESULT2 = new Integer(3);
                final Symbol CUP$XPathParser$result = new Symbol(25, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 116: {
                Object RESULT2 = null;
                RESULT2 = new Integer(-1);
                final Symbol CUP$XPathParser$result = new Symbol(25, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 115: {
                Object RESULT2 = null;
                final int ntleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ntright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Object nt = RESULT2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(25, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 114: {
                Expression RESULT3 = null;
                final int exleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int exright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ex = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(3, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 113: {
                QName RESULT = null;
                final int vnameleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int vnameright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final QName vname = RESULT = (QName)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(39, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 112: {
                QName RESULT = null;
                final int fnameleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int fnameright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final QName fname = RESULT = (QName)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(38, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT);
                return CUP$XPathParser$result;
            }
            case 111: {
                Vector RESULT4 = null;
                final int argleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int argright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression arg = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int arglleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int arglright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector argl = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                argl.insertElementAt(arg, 0);
                RESULT4 = argl;
                final Symbol CUP$XPathParser$result = new Symbol(36, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT4);
                return CUP$XPathParser$result;
            }
            case 110: {
                Vector RESULT4 = null;
                final int argleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int argright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression arg = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Vector temp = new Vector();
                temp.addElement(arg);
                RESULT4 = temp;
                final Symbol CUP$XPathParser$result = new Symbol(36, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT4);
                return CUP$XPathParser$result;
            }
            case 109: {
                Expression RESULT3 = null;
                final int fnameleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).left;
                final int fnameright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).right;
                final QName fname = (QName)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).value;
                final int arglleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int arglright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Vector argl = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                if (fname == this.parser.getQNameIgnoreDefaultNs("concat")) {
                    RESULT3 = new ConcatCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("number")) {
                    RESULT3 = new NumberCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("document")) {
                    this.parser.setMultiDocument(true);
                    RESULT3 = new DocumentCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("string")) {
                    RESULT3 = new StringCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("boolean")) {
                    RESULT3 = new BooleanCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("name")) {
                    RESULT3 = new NameCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("generate-id")) {
                    RESULT3 = new GenerateIdCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("not")) {
                    RESULT3 = new NotCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("format-number")) {
                    RESULT3 = new FormatNumberCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("unparsed-entity-uri")) {
                    RESULT3 = new UnparsedEntityUriCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("key")) {
                    RESULT3 = new KeyCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("id")) {
                    RESULT3 = new KeyCall(fname, argl);
                    this.parser.setHasIdCall(true);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("ceiling")) {
                    RESULT3 = new CeilingCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("round")) {
                    RESULT3 = new RoundCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("floor")) {
                    RESULT3 = new FloorCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("contains")) {
                    RESULT3 = new ContainsCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("string-length")) {
                    RESULT3 = new StringLengthCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("starts-with")) {
                    RESULT3 = new StartsWithCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("function-available")) {
                    RESULT3 = new FunctionAvailableCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("element-available")) {
                    RESULT3 = new ElementAvailableCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("local-name")) {
                    RESULT3 = new LocalNameCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("lang")) {
                    RESULT3 = new LangCall(fname, argl);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("namespace-uri")) {
                    RESULT3 = new NamespaceUriCall(fname, argl);
                }
                else if (fname == this.parser.getQName("http://xml.apache.org/xalan/xsltc", "xsltc", "cast")) {
                    RESULT3 = new CastCall(fname, argl);
                }
                else if (fname.getLocalPart().equals("nodeset") || fname.getLocalPart().equals("node-set")) {
                    this.parser.setCallsNodeset(true);
                    RESULT3 = new FunctionCall(fname, argl);
                }
                else {
                    RESULT3 = new FunctionCall(fname, argl);
                }
                final Symbol CUP$XPathParser$result = new Symbol(16, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 108: {
                Expression RESULT3 = null;
                final int fnameleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int fnameright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final QName fname = (QName)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                if (fname == this.parser.getQNameIgnoreDefaultNs("current")) {
                    RESULT3 = new CurrentCall(fname);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("number")) {
                    RESULT3 = new NumberCall(fname, XPathParser.EmptyArgs);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("string")) {
                    RESULT3 = new StringCall(fname, XPathParser.EmptyArgs);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("concat")) {
                    RESULT3 = new ConcatCall(fname, XPathParser.EmptyArgs);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("true")) {
                    RESULT3 = new BooleanExpr(true);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("false")) {
                    RESULT3 = new BooleanExpr(false);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("name")) {
                    RESULT3 = new NameCall(fname);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("generate-id")) {
                    RESULT3 = new GenerateIdCall(fname, XPathParser.EmptyArgs);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("string-length")) {
                    RESULT3 = new StringLengthCall(fname, XPathParser.EmptyArgs);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("position")) {
                    RESULT3 = new PositionCall(fname);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("last")) {
                    RESULT3 = new LastCall(fname);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("local-name")) {
                    RESULT3 = new LocalNameCall(fname);
                }
                else if (fname == this.parser.getQNameIgnoreDefaultNs("namespace-uri")) {
                    RESULT3 = new NamespaceUriCall(fname);
                }
                else {
                    RESULT3 = new FunctionCall(fname, XPathParser.EmptyArgs);
                }
                final Symbol CUP$XPathParser$result = new Symbol(16, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 107: {
                Expression RESULT3 = null;
                final int varNameleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int varNameright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final QName varName = (QName)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final SyntaxTreeNode node = this.parser.lookupName(varName);
                if (node != null) {
                    if (node instanceof Variable) {
                        RESULT3 = new VariableRef((Variable)node);
                    }
                    else if (node instanceof Param) {
                        RESULT3 = new ParameterRef((Param)node);
                    }
                    else {
                        RESULT3 = new UnresolvedRef(varName);
                    }
                }
                if (node == null) {
                    RESULT3 = new UnresolvedRef(varName);
                }
                final Symbol CUP$XPathParser$result = new Symbol(15, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 106: {
                Expression RESULT3 = null;
                final int fcleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int fcright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression fc = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(17, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 105: {
                Expression RESULT3 = null;
                final int numleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int numright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Double num = (Double)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new RealExpr(num);
                final Symbol CUP$XPathParser$result = new Symbol(17, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 104: {
                Expression RESULT3 = null;
                final int numleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int numright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Long num2 = (Long)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final long value = num2;
                if (value < -2147483648L || value > 2147483647L) {
                    RESULT3 = new RealExpr(value);
                }
                else if (num2 == 0.0) {
                    RESULT3 = new RealExpr(num2);
                }
                else if ((int)(Object)num2 == 0) {
                    RESULT3 = new IntExpr((int)(Object)num2);
                }
                else if (num2 == 0.0) {
                    RESULT3 = new RealExpr(num2);
                }
                else {
                    RESULT3 = new IntExpr((int)(Object)num2);
                }
                final Symbol CUP$XPathParser$result = new Symbol(17, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 103: {
                Expression RESULT3 = null;
                final int stringleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int stringright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final String string = (String)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                String namespace = null;
                final int index = string.lastIndexOf(58);
                if (index > 0) {
                    final String prefix = string.substring(0, index);
                    namespace = this.parser._symbolTable.lookupNamespace(prefix);
                }
                RESULT3 = ((namespace == null) ? new LiteralExpr(string) : new LiteralExpr(string, namespace));
                final Symbol CUP$XPathParser$result = new Symbol(17, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 102: {
                Expression RESULT3 = null;
                final int exleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int exright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Expression ex = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final Symbol CUP$XPathParser$result = new Symbol(17, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 101: {
                Expression RESULT3 = null;
                final int vrleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int vrright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression vr = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(17, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 100: {
                Expression RESULT3 = null;
                final int primaryleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int primaryright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Expression primary = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new FilterExpr(primary, pp);
                final Symbol CUP$XPathParser$result = new Symbol(6, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 99: {
                Expression RESULT3 = null;
                final int primaryleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int primaryright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression primary = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(6, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 98: {
                Expression RESULT3 = null;
                RESULT3 = new Step(10, -1, null);
                final Symbol CUP$XPathParser$result = new Symbol(20, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 97: {
                Expression RESULT3 = null;
                RESULT3 = new Step(13, -1, null);
                final Symbol CUP$XPathParser$result = new Symbol(20, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 96: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(13);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 95: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(12);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 94: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(11);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 93: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(10);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 92: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(9);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 91: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(7);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 90: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(6);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 89: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(5);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 88: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(4);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 87: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(3);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 86: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(2);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 85: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(1);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 84: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(0);
                final Symbol CUP$XPathParser$result = new Symbol(40, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 83: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(2);
                final Symbol CUP$XPathParser$result = new Symbol(41, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 82: {
                Integer RESULT5 = null;
                final int anleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int anright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Integer an = RESULT5 = (Integer)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final Symbol CUP$XPathParser$result = new Symbol(41, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 81: {
                Expression RESULT3 = null;
                final int abbrevleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int abbrevright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression abbrev = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(7, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 80: {
                Expression RESULT3 = null;
                final int axisleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int axisright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Integer axis = (Integer)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ntestleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ntestright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Object ntest = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new Step(axis, this.parser.findNodeType(axis, ntest), null);
                final Symbol CUP$XPathParser$result = new Symbol(7, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 79: {
                Expression RESULT3 = null;
                final int axisleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int axisright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Integer axis = (Integer)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int ntestleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int ntestright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Object ntest = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp2 = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new Step(axis, this.parser.findNodeType(axis, ntest), pp2);
                final Symbol CUP$XPathParser$result = new Symbol(7, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 78: {
                Expression RESULT3 = null;
                final int ntestleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int ntestright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Object ntest2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                if (ntest2 instanceof Step) {
                    final Step step = (Step)ntest2;
                    step.addPredicates(pp);
                    RESULT3 = (Step)ntest2;
                }
                else {
                    RESULT3 = new Step(3, this.parser.findNodeType(3, ntest2), pp);
                }
                final Symbol CUP$XPathParser$result = new Symbol(7, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 77: {
                Expression RESULT3 = null;
                final int ntestleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ntestright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Object ntest2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                if (ntest2 instanceof Step) {
                    RESULT3 = (Step)ntest2;
                }
                else {
                    RESULT3 = new Step(3, this.parser.findNodeType(3, ntest2), null);
                }
                final Symbol CUP$XPathParser$result = new Symbol(7, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 76: {
                Expression RESULT3 = null;
                final int rlpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rlpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression rlp = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                int nodeType = -1;
                if (rlp instanceof Step && this.parser.isElementAxis(((Step)rlp).getAxis())) {
                    nodeType = 1;
                }
                final Step step2 = new Step(5, nodeType, null);
                RESULT3 = new AbsoluteLocationPath(this.parser.insertStep(step2, (RelativeLocationPath)rlp));
                final Symbol CUP$XPathParser$result = new Symbol(24, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 75: {
                Expression RESULT3 = null;
                final int rlpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int rlpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression rlp = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int stepleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int stepright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression step3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Step right = (Step)step3;
                final int axis2 = right.getAxis();
                final int type = right.getNodeType();
                final Vector predicates2 = right.getPredicates();
                if (axis2 == 3 && type != 2) {
                    if (predicates2 == null) {
                        right.setAxis(4);
                        if (rlp instanceof Step && ((Step)rlp).isAbbreviatedDot()) {
                            RESULT3 = right;
                        }
                        else {
                            final RelativeLocationPath left = (RelativeLocationPath)rlp;
                            RESULT3 = new ParentLocationPath(left, right);
                        }
                    }
                    else if (rlp instanceof Step && ((Step)rlp).isAbbreviatedDot()) {
                        final Step left2 = new Step(5, 1, null);
                        RESULT3 = new ParentLocationPath(left2, right);
                    }
                    else {
                        final RelativeLocationPath left = (RelativeLocationPath)rlp;
                        final Step mid = new Step(5, 1, null);
                        final ParentLocationPath ppl = new ParentLocationPath(mid, right);
                        RESULT3 = new ParentLocationPath(left, ppl);
                    }
                }
                else if (axis2 == 2 || type == 2) {
                    final RelativeLocationPath left = (RelativeLocationPath)rlp;
                    final Step middle = new Step(5, 1, null);
                    final ParentLocationPath ppl = new ParentLocationPath(middle, right);
                    RESULT3 = new ParentLocationPath(left, ppl);
                }
                else {
                    final RelativeLocationPath left = (RelativeLocationPath)rlp;
                    final Step middle = new Step(5, -1, null);
                    final ParentLocationPath ppl = new ParentLocationPath(middle, right);
                    RESULT3 = new ParentLocationPath(left, ppl);
                }
                final Symbol CUP$XPathParser$result = new Symbol(22, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 74: {
                Expression RESULT3 = null;
                final int aalpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aalpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression aalp = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(23, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 73: {
                Expression RESULT3 = null;
                final int rlpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rlpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression rlp = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new AbsoluteLocationPath(rlp);
                final Symbol CUP$XPathParser$result = new Symbol(23, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 72: {
                Expression RESULT3 = null;
                RESULT3 = new AbsoluteLocationPath();
                final Symbol CUP$XPathParser$result = new Symbol(23, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 71: {
                Expression RESULT3 = null;
                final int arlpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int arlpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression arlp = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(21, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 70: {
                Expression RESULT3 = null;
                final int rlpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int rlpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression rlp = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int stepleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int stepright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression step3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                if (rlp instanceof Step && ((Step)rlp).isAbbreviatedDot()) {
                    RESULT3 = step3;
                }
                else if (((Step)step3).isAbbreviatedDot()) {
                    RESULT3 = rlp;
                }
                else {
                    RESULT3 = new ParentLocationPath((RelativeLocationPath)rlp, step3);
                }
                final Symbol CUP$XPathParser$result = new Symbol(21, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 69: {
                Expression RESULT3 = null;
                final int stepleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int stepright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression step4 = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(21, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 68: {
                Expression RESULT3 = null;
                final int alpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int alpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression alp = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(4, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 67: {
                Expression RESULT3 = null;
                final int rlpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rlpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression rlp = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(4, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 66: {
                Expression RESULT3 = null;
                final int fexpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int fexpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression fexp = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int rlpleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rlpright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression rlp2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                int nodeType2 = -1;
                if (rlp2 instanceof Step && this.parser.isElementAxis(((Step)rlp2).getAxis())) {
                    nodeType2 = 1;
                }
                final Step step5 = new Step(5, nodeType2, null);
                FilterParentPath fpp = new FilterParentPath(fexp, step5);
                fpp = new FilterParentPath(fpp, rlp2);
                if (!(fexp instanceof KeyCall)) {
                    fpp.setDescendantAxis();
                }
                RESULT3 = fpp;
                final Symbol CUP$XPathParser$result = new Symbol(19, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 65: {
                Expression RESULT3 = null;
                final int fexpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int fexpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression fexp = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int rlpleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rlpright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression rlp2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new FilterParentPath(fexp, rlp2);
                final Symbol CUP$XPathParser$result = new Symbol(19, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 64: {
                Expression RESULT3 = null;
                final int fexpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int fexpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression fexp = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(19, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 63: {
                Expression RESULT3 = null;
                final int lpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int lpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression lp = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(19, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 62: {
                Expression RESULT3 = null;
                final int peleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int peright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression pe = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int restleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int restright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression rest = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new UnionPathExpr(pe, rest);
                final Symbol CUP$XPathParser$result = new Symbol(18, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 61: {
                Expression RESULT3 = null;
                final int peleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int peright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression pe = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(18, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 60: {
                Expression RESULT3 = null;
                final int ueleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ueright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ue = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new UnaryOpExpr(ue);
                final Symbol CUP$XPathParser$result = new Symbol(14, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 59: {
                Expression RESULT3 = null;
                final int ueleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ueright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ue = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(14, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 58: {
                Expression RESULT3 = null;
                final int meleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int meright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression me = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int ueleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ueright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ue2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new BinOpExpr(4, me, ue2);
                final Symbol CUP$XPathParser$result = new Symbol(13, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 57: {
                Expression RESULT3 = null;
                final int meleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int meright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression me = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int ueleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ueright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ue2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new BinOpExpr(3, me, ue2);
                final Symbol CUP$XPathParser$result = new Symbol(13, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 56: {
                Expression RESULT3 = null;
                final int meleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int meright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression me = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int ueleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ueright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ue2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new BinOpExpr(2, me, ue2);
                final Symbol CUP$XPathParser$result = new Symbol(13, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 55: {
                Expression RESULT3 = null;
                final int ueleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ueright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ue = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(13, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 54: {
                Expression RESULT3 = null;
                final int aeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int aeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression ae = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int meleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int meright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression me2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new BinOpExpr(1, ae, me2);
                final Symbol CUP$XPathParser$result = new Symbol(12, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 53: {
                Expression RESULT3 = null;
                final int aeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int aeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression ae = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int meleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int meright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression me2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new BinOpExpr(0, ae, me2);
                final Symbol CUP$XPathParser$result = new Symbol(12, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 52: {
                Expression RESULT3 = null;
                final int meleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int meright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression me = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(12, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 51: {
                Expression RESULT3 = null;
                final int releft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int reright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression re = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int aeleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aeright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ae2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new RelationalExpr(4, re, ae2);
                final Symbol CUP$XPathParser$result = new Symbol(11, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 50: {
                Expression RESULT3 = null;
                final int releft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int reright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression re = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int aeleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aeright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ae2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new RelationalExpr(5, re, ae2);
                final Symbol CUP$XPathParser$result = new Symbol(11, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 49: {
                Expression RESULT3 = null;
                final int releft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int reright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression re = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int aeleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aeright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ae2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new RelationalExpr(2, re, ae2);
                final Symbol CUP$XPathParser$result = new Symbol(11, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 48: {
                Expression RESULT3 = null;
                final int releft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int reright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression re = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int aeleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aeright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ae2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new RelationalExpr(3, re, ae2);
                final Symbol CUP$XPathParser$result = new Symbol(11, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 47: {
                Expression RESULT3 = null;
                final int aeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ae = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(11, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 46: {
                Expression RESULT3 = null;
                final int eeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int eeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression ee = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int releft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int reright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression re2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new EqualityExpr(1, ee, re2);
                final Symbol CUP$XPathParser$result = new Symbol(10, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 45: {
                Expression RESULT3 = null;
                final int eeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int eeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression ee = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int releft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int reright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression re2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new EqualityExpr(0, ee, re2);
                final Symbol CUP$XPathParser$result = new Symbol(10, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 44: {
                Expression RESULT3 = null;
                final int releft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int reright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression re = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(10, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 43: {
                Expression RESULT3 = null;
                final int aeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int aeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression ae = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int eeleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int eeright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ee2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new LogicalExpr(1, ae, ee2);
                final Symbol CUP$XPathParser$result = new Symbol(9, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 42: {
                Expression RESULT3 = null;
                final int eleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int eright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression e = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(9, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 41: {
                Expression RESULT3 = null;
                final int oeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int oeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Expression oe = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int aeleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aeright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ae2 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT3 = new LogicalExpr(0, oe, ae2);
                final Symbol CUP$XPathParser$result = new Symbol(8, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 40: {
                Expression RESULT3 = null;
                final int aeleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int aeright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ae = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(8, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 39: {
                Expression RESULT3 = null;
                final int exleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int exright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression ex = RESULT3 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(2, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 38: {
                Expression RESULT3 = null;
                final int eleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int eright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Expression e = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                RESULT3 = new Predicate(e);
                final Symbol CUP$XPathParser$result = new Symbol(5, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT3);
                return CUP$XPathParser$result;
            }
            case 37: {
                Vector RESULT4 = null;
                final int pleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int pright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Expression p = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                pp.insertElementAt(p, 0);
                RESULT4 = pp;
                final Symbol CUP$XPathParser$result = new Symbol(35, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT4);
                return CUP$XPathParser$result;
            }
            case 36: {
                Vector RESULT4 = null;
                final int pleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int pright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression p = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Vector temp = new Vector();
                temp.addElement(p);
                RESULT4 = temp;
                final Symbol CUP$XPathParser$result = new Symbol(35, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT4);
                return CUP$XPathParser$result;
            }
            case 35: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(2);
                final Symbol CUP$XPathParser$result = new Symbol(42, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 34: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(3);
                final Symbol CUP$XPathParser$result = new Symbol(42, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 33: {
                Integer RESULT5 = null;
                RESULT5 = new Integer(2);
                final Symbol CUP$XPathParser$result = new Symbol(42, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT5);
                return CUP$XPathParser$result;
            }
            case 32: {
                Object RESULT2 = null;
                final int qnleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int qnright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final QName qn = (QName)(RESULT2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value);
                final Symbol CUP$XPathParser$result = new Symbol(34, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 31: {
                Object RESULT2 = null;
                RESULT2 = null;
                final Symbol CUP$XPathParser$result = new Symbol(34, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 30: {
                Object RESULT2 = null;
                RESULT2 = new Integer(7);
                final Symbol CUP$XPathParser$result = new Symbol(33, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 29: {
                Object RESULT2 = null;
                RESULT2 = new Integer(8);
                final Symbol CUP$XPathParser$result = new Symbol(33, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 28: {
                Object RESULT2 = null;
                RESULT2 = new Integer(3);
                final Symbol CUP$XPathParser$result = new Symbol(33, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 27: {
                Object RESULT2 = null;
                RESULT2 = new Integer(-1);
                final Symbol CUP$XPathParser$result = new Symbol(33, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 26: {
                Object RESULT2 = null;
                final int ntleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ntright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Object nt = RESULT2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(33, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                return CUP$XPathParser$result;
            }
            case 25: {
                StepPattern RESULT6 = null;
                final int axisleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int axisright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Integer axis = (Integer)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int pipleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int pipright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final StepPattern pip = (StepPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp2 = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT6 = pip.setPredicates(pp2);
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 24: {
                StepPattern RESULT6 = null;
                final int axisleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int axisright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Integer axis = (Integer)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int pipleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int pipright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final StepPattern pip = RESULT6 = (StepPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 23: {
                StepPattern RESULT6 = null;
                final int axisleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int axisright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Integer axis = (Integer)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int ntleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int ntright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Object nt2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp2 = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT6 = this.parser.createStepPattern(axis, nt2, pp2);
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 22: {
                StepPattern RESULT6 = null;
                final int axisleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int axisright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Integer axis = (Integer)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ntleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ntright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Object nt2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT6 = this.parser.createStepPattern(axis, nt2, null);
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 21: {
                StepPattern RESULT6 = null;
                final int pipleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int pipright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final StepPattern pip2 = (StepPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT6 = pip2.setPredicates(pp);
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 20: {
                StepPattern RESULT6 = null;
                final int pipleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int pipright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final StepPattern pip2 = RESULT6 = (StepPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 19: {
                StepPattern RESULT6 = null;
                final int ntleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int ntright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final Object nt = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                final int ppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Vector pp = (Vector)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT6 = this.parser.createStepPattern(3, nt, pp);
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 18: {
                StepPattern RESULT6 = null;
                final int ntleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ntright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Object nt = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT6 = this.parser.createStepPattern(3, nt, null);
                final Symbol CUP$XPathParser$result = new Symbol(32, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 17: {
                RelativePathPattern RESULT7 = null;
                final int spleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int spright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final StepPattern sp = (StepPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int rppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final RelativePathPattern rpp = (RelativePathPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT7 = new AncestorPattern(sp, rpp);
                final Symbol CUP$XPathParser$result = new Symbol(31, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT7);
                return CUP$XPathParser$result;
            }
            case 16: {
                RelativePathPattern RESULT7 = null;
                final int spleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int spright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final StepPattern sp = (StepPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int rppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final RelativePathPattern rpp = (RelativePathPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT7 = new ParentPattern(sp, rpp);
                final Symbol CUP$XPathParser$result = new Symbol(31, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT7);
                return CUP$XPathParser$result;
            }
            case 15: {
                RelativePathPattern RESULT7 = null;
                final int spleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int spright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final StepPattern sp = (StepPattern)(RESULT7 = (StepPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value);
                final Symbol CUP$XPathParser$result = new Symbol(31, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT7);
                return CUP$XPathParser$result;
            }
            case 14: {
                StepPattern RESULT6 = null;
                final int lleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int lright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final String l = (String)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                RESULT6 = new ProcessingInstructionPattern(l);
                final Symbol CUP$XPathParser$result = new Symbol(30, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT6);
                return CUP$XPathParser$result;
            }
            case 13: {
                IdKeyPattern RESULT8 = null;
                final int l1left = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).left;
                final int l1right = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).right;
                final String l2 = (String)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).value;
                final int l2left = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int l2right = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final String l3 = (String)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                RESULT8 = new KeyPattern(l2, l3);
                final Symbol CUP$XPathParser$result = new Symbol(27, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 5).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT8);
                return CUP$XPathParser$result;
            }
            case 12: {
                IdKeyPattern RESULT8 = null;
                final int lleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int lright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final String l = (String)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value;
                RESULT8 = new IdPattern(l);
                this.parser.setHasIdCall(true);
                final Symbol CUP$XPathParser$result = new Symbol(27, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT8);
                return CUP$XPathParser$result;
            }
            case 11: {
                Pattern RESULT9 = null;
                final int rppleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rppright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final RelativePathPattern rpp2 = (RelativePathPattern)(RESULT9 = (RelativePathPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value);
                final Symbol CUP$XPathParser$result = new Symbol(29, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 10: {
                Pattern RESULT9 = null;
                final int rppleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rppright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final RelativePathPattern rpp2 = (RelativePathPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT9 = new AncestorPattern(rpp2);
                final Symbol CUP$XPathParser$result = new Symbol(29, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 9: {
                Pattern RESULT9 = null;
                final int ikpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int ikpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final IdKeyPattern ikp = (IdKeyPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int rppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final RelativePathPattern rpp = (RelativePathPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT9 = new AncestorPattern(ikp, rpp);
                final Symbol CUP$XPathParser$result = new Symbol(29, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 8: {
                Pattern RESULT9 = null;
                final int ikpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int ikpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final IdKeyPattern ikp = (IdKeyPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int rppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final RelativePathPattern rpp = (RelativePathPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT9 = new ParentPattern(ikp, rpp);
                final Symbol CUP$XPathParser$result = new Symbol(29, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 7: {
                Pattern RESULT9 = null;
                final int ikpleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int ikpright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final IdKeyPattern ikp = (IdKeyPattern)(RESULT9 = (IdKeyPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value);
                final Symbol CUP$XPathParser$result = new Symbol(29, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 6: {
                Pattern RESULT9 = null;
                final int rppleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int rppright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final RelativePathPattern rpp2 = (RelativePathPattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT9 = new AbsolutePathPattern(rpp2);
                final Symbol CUP$XPathParser$result = new Symbol(29, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 5: {
                Pattern RESULT9 = null;
                RESULT9 = new AbsolutePathPattern(null);
                final Symbol CUP$XPathParser$result = new Symbol(29, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 4: {
                Pattern RESULT9 = null;
                final int lppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left;
                final int lppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).right;
                final Pattern lpp = (Pattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).value;
                final int pleft2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int pright2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Pattern p2 = (Pattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                RESULT9 = new AlternativePattern(lpp, p2);
                final Symbol CUP$XPathParser$result = new Symbol(28, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 3: {
                Pattern RESULT9 = null;
                final int lppleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int lppright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Pattern lpp = RESULT9 = (Pattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value;
                final Symbol CUP$XPathParser$result = new Symbol(28, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT9);
                return CUP$XPathParser$result;
            }
            case 2: {
                SyntaxTreeNode RESULT10 = null;
                final int exprleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int exprright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Expression expr = (Expression)(RESULT10 = (Expression)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value);
                final Symbol CUP$XPathParser$result = new Symbol(1, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT10);
                return CUP$XPathParser$result;
            }
            case 1: {
                SyntaxTreeNode RESULT10 = null;
                final int patternleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).left;
                final int patternright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right;
                final Pattern pattern = (Pattern)(RESULT10 = (Pattern)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).value);
                final Symbol CUP$XPathParser$result = new Symbol(1, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT10);
                return CUP$XPathParser$result;
            }
            case 0: {
                Object RESULT2 = null;
                final int start_valleft = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left;
                final int start_valright = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).right;
                final SyntaxTreeNode start_val = (SyntaxTreeNode)(RESULT2 = CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).value);
                final Symbol CUP$XPathParser$result = new Symbol(0, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1).left, CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0).right, RESULT2);
                CUP$XPathParser$parser.done_parsing();
                return CUP$XPathParser$result;
            }
            default: {
                throw new Exception("Invalid action number found in internal parse table");
            }
        }
    }
}
