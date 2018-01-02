// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema.identity;

import java.util.Hashtable;
import org.apache.xerces.utils.QName;
import java.util.Vector;
import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.utils.StringPool;

public class XPath
{
    private static final boolean DEBUG_ALL = false;
    private static final boolean DEBUG_XPATH_PARSE = false;
    private static final boolean DEBUG_ANY = false;
    protected String fExpression;
    protected LocationPath fLocationPath;
    protected StringPool fStringPool;
    
    public XPath(final String fExpression, final StringPool fStringPool, final NamespacesScope namespacesScope) throws XPathException {
        this.fExpression = fExpression;
        this.fStringPool = fStringPool;
        this.parseExpression(namespacesScope);
    }
    
    public LocationPath getLocationPath() {
        return (LocationPath)this.fLocationPath.clone();
    }
    
    public String toString() {
        return this.fLocationPath.toString();
    }
    
    private void parseExpression(final NamespacesScope namespacesScope) throws XPathException {
        final Tokens tokens = new Tokens(this.fStringPool);
        new Scanner(this.fStringPool) {
            protected void addToken(final Tokens tokens, final int n) throws XPathException {
                if (n == -1006 || n == -1035 || n == -1036 || n == -1008 || n == -1011 || n == -1021 || n == -1004) {
                    super.addToken(tokens, n);
                    return;
                }
                final StringBuffer sb = new StringBuffer();
                sb.append("token not supported: ");
                final String tokenName = tokens.getTokenName(n);
                if (tokenName != null) {
                    sb.append('\"');
                    sb.append(tokenName);
                    sb.append('\"');
                }
                else {
                    sb.append('(');
                    sb.append(n);
                    sb.append(')');
                }
                throw new XPathException(sb.toString());
            }
        }.scanExpr(this.fStringPool, tokens, this.fExpression, 0, this.fExpression.length());
        final Vector vector = new Vector<Step>();
        for (int tokenCount = tokens.getTokenCount(), i = 0; i < tokenCount; ++i) {
            switch (tokens.getToken(i)) {
                case -1035: {
                    ++i;
                }
                case -1006: {
                    if (i == tokenCount - 1) {
                        throw new XPathException("missing attribute name");
                    }
                    final int token = tokens.getToken(++i);
                    if (token != -1011) {
                        throw new XPathException("expected " + tokens.getTokenName(-1011) + ", found " + tokens.getTokenName(token));
                    }
                    final int tokenString = tokens.getTokenString(tokens.getToken(++i));
                    int namespaceForPrefix = 0;
                    if (namespacesScope != null && tokenString != -1) {
                        namespaceForPrefix = namespacesScope.getNamespaceForPrefix(tokenString);
                    }
                    if (tokenString != -1 && namespacesScope != null && namespaceForPrefix == 0) {
                        throw new XPathException("prefix " + this.fStringPool.toString(tokenString) + " not bound to namespace URI");
                    }
                    final int tokenString2 = tokens.getTokenString(tokens.getToken(++i));
                    vector.addElement(new Step(new Axis((short)2), new NodeTest(this.fStringPool, new QName(tokenString, tokenString2, (tokenString != -1) ? this.fStringPool.addSymbol(this.fStringPool.toString(tokenString) + ':' + this.fStringPool.toString(tokenString2)) : tokenString2, namespaceForPrefix))));
                    break;
                }
                case -1008: {
                    throw new XPathException("Not allowed to have double colon here");
                }
                case -1036: {
                    ++i;
                }
                case -1011: {
                    final int tokenString3 = tokens.getTokenString(tokens.getToken(++i));
                    int namespaceForPrefix2 = 0;
                    if (namespacesScope != null && tokenString3 != -1) {
                        namespaceForPrefix2 = namespacesScope.getNamespaceForPrefix(tokenString3);
                    }
                    if (tokenString3 != -1 && namespacesScope != null && namespaceForPrefix2 == 0) {
                        throw new XPathException("prefix " + this.fStringPool.toString(tokenString3) + " not bound to namespace URI");
                    }
                    final int tokenString4 = tokens.getTokenString(tokens.getToken(++i));
                    vector.addElement(new Step(new Axis((short)1), new NodeTest(this.fStringPool, new QName(tokenString3, tokenString4, (tokenString3 != -1) ? this.fStringPool.addSymbol(this.fStringPool.toString(tokenString3) + ':' + this.fStringPool.toString(tokenString4)) : tokenString4, namespaceForPrefix2))));
                    break;
                }
                case -1004: {
                    vector.addElement(new Step(new Axis((short)3), new NodeTest((short)3)));
                    break;
                }
                case -1021: {
                    if (i == 0) {
                        throw new XPathException("not allowed to select the root");
                    }
                    if (i == tokenCount - 1) {
                        throw new XPathException("expected step following '/'");
                    }
                    break;
                }
            }
        }
        final int size = vector.size();
        if (size == 0) {
            throw new XPathException("empty xpath expression");
        }
        final Step[] array = new Step[size];
        vector.copyInto(array);
        this.fLocationPath = new LocationPath(array);
    }
    
    public static void main(final String[] array) throws Exception {
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            System.out.println("# XPath expression: \"" + s + '\"');
            try {
                System.out.println("expanded xpath: \"" + new XPath(s, new StringPool(), null).toString() + '\"');
            }
            catch (XPathException ex) {
                System.out.println("error: " + ex.getMessage());
            }
        }
    }
    
    public static class LocationPath implements Cloneable
    {
        public Step[] steps;
        
        public LocationPath(final Step[] steps) {
            this.steps = steps;
        }
        
        protected LocationPath(final LocationPath locationPath) {
            this.steps = new Step[locationPath.steps.length];
            for (int i = 0; i < this.steps.length; ++i) {
                this.steps[i] = (Step)locationPath.steps[i].clone();
            }
        }
        
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < this.steps.length; ++i) {
                if (i > 0) {
                    sb.append('/');
                }
                sb.append(this.steps[i].toString());
            }
            return sb.toString();
        }
        
        public Object clone() {
            return new LocationPath(this);
        }
    }
    
    public static class Step implements Cloneable
    {
        public Axis axis;
        public NodeTest nodeTest;
        
        public Step(final Axis axis, final NodeTest nodeTest) {
            this.axis = axis;
            this.nodeTest = nodeTest;
        }
        
        protected Step(final Step step) {
            this.axis = (Axis)step.axis.clone();
            this.nodeTest = (NodeTest)step.nodeTest.clone();
        }
        
        public String toString() {
            if (this.axis.type == 3) {
                return ".";
            }
            if (this.axis.type == 2) {
                return "@" + this.nodeTest.toString();
            }
            if (this.axis.type == 1) {
                return this.nodeTest.toString();
            }
            return "??? (" + this.axis.type + ')';
        }
        
        public Object clone() {
            return new Step(this);
        }
    }
    
    public static class Axis implements Cloneable
    {
        public static final short CHILD = 1;
        public static final short ATTRIBUTE = 2;
        public static final short SELF = 3;
        public short type;
        
        public Axis(final short type) {
            this.type = type;
        }
        
        protected Axis(final Axis axis) {
            this.type = axis.type;
        }
        
        public String toString() {
            switch (this.type) {
                case 1: {
                    return "child";
                }
                case 2: {
                    return "attribute";
                }
                case 3: {
                    return "self";
                }
                default: {
                    return "???";
                }
            }
        }
        
        public Object clone() {
            return new Axis(this);
        }
    }
    
    public static class NodeTest implements Cloneable
    {
        public static final short QNAME = 1;
        public static final short WILDCARD = 2;
        public static final short NODE = 3;
        protected StringPool fStringPool;
        public short type;
        public final QName name;
        
        public NodeTest(final short type) {
            this.name = new QName();
            this.type = type;
        }
        
        public NodeTest(final StringPool fStringPool, final QName values) {
            this.name = new QName();
            this.fStringPool = fStringPool;
            this.type = 1;
            this.name.setValues(values);
        }
        
        public NodeTest(final NodeTest nodeTest) {
            this.name = new QName();
            this.fStringPool = nodeTest.fStringPool;
            this.type = nodeTest.type;
            this.name.setValues(nodeTest.name);
        }
        
        public String toString() {
            switch (this.type) {
                case 1: {
                    if (this.name.prefix == -1) {
                        return this.fStringPool.toString(this.name.localpart);
                    }
                    if (this.name.uri == 0) {
                        return this.fStringPool.toString(this.name.prefix) + ':' + this.fStringPool.toString(this.name.localpart);
                    }
                    return "{" + this.fStringPool.toString(this.name.uri) + '}' + this.fStringPool.toString(this.name.prefix) + ':' + this.fStringPool.toString(this.name.localpart);
                }
                case 2: {
                    return "*";
                }
                case 3: {
                    return "node()";
                }
                default: {
                    return "???";
                }
            }
        }
        
        public Object clone() {
            return new NodeTest(this);
        }
    }
    
    private static final class Tokens
    {
        static final boolean DUMP_TOKENS = false;
        public static final int EXPRTOKEN_OPEN_PAREN = -1000;
        public static final int EXPRTOKEN_CLOSE_PAREN = -1001;
        public static final int EXPRTOKEN_OPEN_BRACKET = -1002;
        public static final int EXPRTOKEN_CLOSE_BRACKET = -1003;
        public static final int EXPRTOKEN_PERIOD = -1004;
        public static final int EXPRTOKEN_DOUBLE_PERIOD = -1005;
        public static final int EXPRTOKEN_ATSIGN = -1006;
        public static final int EXPRTOKEN_COMMA = -1007;
        public static final int EXPRTOKEN_DOUBLE_COLON = -1008;
        public static final int EXPRTOKEN_NAMETEST_ANY = -1009;
        public static final int EXPRTOKEN_NAMETEST_NAMESPACE = -1010;
        public static final int EXPRTOKEN_NAMETEST_QNAME = -1011;
        public static final int EXPRTOKEN_NODETYPE_COMMENT = -1012;
        public static final int EXPRTOKEN_NODETYPE_TEXT = -1013;
        public static final int EXPRTOKEN_NODETYPE_PI = -1014;
        public static final int EXPRTOKEN_NODETYPE_NODE = -1015;
        public static final int EXPRTOKEN_OPERATOR_AND = -1016;
        public static final int EXPRTOKEN_OPERATOR_OR = -1017;
        public static final int EXPRTOKEN_OPERATOR_MOD = -1018;
        public static final int EXPRTOKEN_OPERATOR_DIV = -1019;
        public static final int EXPRTOKEN_OPERATOR_MULT = -1020;
        public static final int EXPRTOKEN_OPERATOR_SLASH = -1021;
        public static final int EXPRTOKEN_OPERATOR_DOUBLE_SLASH = -1022;
        public static final int EXPRTOKEN_OPERATOR_UNION = -1023;
        public static final int EXPRTOKEN_OPERATOR_PLUS = -1024;
        public static final int EXPRTOKEN_OPERATOR_MINUS = -1025;
        public static final int EXPRTOKEN_OPERATOR_EQUAL = -1026;
        public static final int EXPRTOKEN_OPERATOR_NOT_EQUAL = -1027;
        public static final int EXPRTOKEN_OPERATOR_LESS = -1028;
        public static final int EXPRTOKEN_OPERATOR_LESS_EQUAL = -1029;
        public static final int EXPRTOKEN_OPERATOR_GREATER = -1030;
        public static final int EXPRTOKEN_OPERATOR_GREATER_EQUAL = -1031;
        public static final int EXPRTOKEN_FIRST_OPERATOR = -1016;
        public static final int EXPRTOKEN_LAST_OPERATOR = -1031;
        public static final int EXPRTOKEN_FUNCTION_NAME = -1032;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR = -1033;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF = -1034;
        public static final int EXPRTOKEN_AXISNAME_ATTRIBUTE = -1035;
        public static final int EXPRTOKEN_AXISNAME_CHILD = -1036;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT = -1037;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF = -1038;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING = -1039;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING = -1040;
        public static final int EXPRTOKEN_AXISNAME_NAMESPACE = -1041;
        public static final int EXPRTOKEN_AXISNAME_PARENT = -1042;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING = -1043;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING_SIBLING = -1044;
        public static final int EXPRTOKEN_AXISNAME_SELF = -1045;
        public static final int EXPRTOKEN_LITERAL = -1046;
        public static final int EXPRTOKEN_NUMBER = -1047;
        public static final int EXPRTOKEN_VARIABLE_REFERENCE = -1048;
        private static final int INITIAL_TOKEN_COUNT = 256;
        private int[] fTokens;
        private int fTokenCount;
        private StringPool fStringPool;
        private Hashtable fSymbolMapping;
        private Hashtable fTokenNames;
        
        public Tokens(final StringPool fStringPool) {
            this.fTokens = new int[256];
            this.fTokenCount = 0;
            this.fSymbolMapping = new Hashtable();
            this.fTokenNames = new Hashtable();
            this.fStringPool = fStringPool;
            this.fTokenNames.put(new Integer(-1000), "EXPRTOKEN_OPEN_PAREN");
            this.fTokenNames.put(new Integer(-1001), "EXPRTOKEN_CLOSE_PAREN");
            this.fTokenNames.put(new Integer(-1002), "EXPRTOKEN_OPEN_BRACKET");
            this.fTokenNames.put(new Integer(-1003), "EXPRTOKEN_CLOSE_BRACKET");
            this.fTokenNames.put(new Integer(-1004), "EXPRTOKEN_PERIOD");
            this.fTokenNames.put(new Integer(-1005), "EXPRTOKEN_DOUBLE_PERIOD");
            this.fTokenNames.put(new Integer(-1006), "EXPRTOKEN_ATSIGN");
            this.fTokenNames.put(new Integer(-1007), "EXPRTOKEN_COMMA");
            this.fTokenNames.put(new Integer(-1008), "EXPRTOKEN_DOUBLE_COLON");
            this.fTokenNames.put(new Integer(-1009), "EXPRTOKEN_NAMETEST_ANY");
            this.fTokenNames.put(new Integer(-1010), "EXPRTOKEN_NAMETEST_NAMESPACE");
            this.fTokenNames.put(new Integer(-1011), "EXPRTOKEN_NAMETEST_QNAME");
            this.fTokenNames.put(new Integer(-1012), "EXPRTOKEN_NODETYPE_COMMENT");
            this.fTokenNames.put(new Integer(-1013), "EXPRTOKEN_NODETYPE_TEXT");
            this.fTokenNames.put(new Integer(-1014), "EXPRTOKEN_NODETYPE_PI");
            this.fTokenNames.put(new Integer(-1015), "EXPRTOKEN_NODETYPE_NODE");
            this.fTokenNames.put(new Integer(-1016), "EXPRTOKEN_OPERATOR_AND");
            this.fTokenNames.put(new Integer(-1017), "EXPRTOKEN_OPERATOR_OR");
            this.fTokenNames.put(new Integer(-1018), "EXPRTOKEN_OPERATOR_MOD");
            this.fTokenNames.put(new Integer(-1019), "EXPRTOKEN_OPERATOR_DIV");
            this.fTokenNames.put(new Integer(-1020), "EXPRTOKEN_OPERATOR_MULT");
            this.fTokenNames.put(new Integer(-1021), "EXPRTOKEN_OPERATOR_SLASH");
            this.fTokenNames.put(new Integer(-1022), "EXPRTOKEN_OPERATOR_DOUBLE_SLASH");
            this.fTokenNames.put(new Integer(-1023), "EXPRTOKEN_OPERATOR_UNION");
            this.fTokenNames.put(new Integer(-1024), "EXPRTOKEN_OPERATOR_PLUS");
            this.fTokenNames.put(new Integer(-1025), "EXPRTOKEN_OPERATOR_MINUS");
            this.fTokenNames.put(new Integer(-1026), "EXPRTOKEN_OPERATOR_EQUAL");
            this.fTokenNames.put(new Integer(-1027), "EXPRTOKEN_OPERATOR_NOT_EQUAL");
            this.fTokenNames.put(new Integer(-1028), "EXPRTOKEN_OPERATOR_LESS");
            this.fTokenNames.put(new Integer(-1029), "EXPRTOKEN_OPERATOR_LESS_EQUAL");
            this.fTokenNames.put(new Integer(-1030), "EXPRTOKEN_OPERATOR_GREATER");
            this.fTokenNames.put(new Integer(-1031), "EXPRTOKEN_OPERATOR_GREATER_EQUAL");
            this.fTokenNames.put(new Integer(-1032), "EXPRTOKEN_FUNCTION_NAME");
            this.fTokenNames.put(new Integer(-1033), "EXPRTOKEN_AXISNAME_ANCESTOR");
            this.fTokenNames.put(new Integer(-1034), "EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF");
            this.fTokenNames.put(new Integer(-1035), "EXPRTOKEN_AXISNAME_ATTRIBUTE");
            this.fTokenNames.put(new Integer(-1036), "EXPRTOKEN_AXISNAME_CHILD");
            this.fTokenNames.put(new Integer(-1037), "EXPRTOKEN_AXISNAME_DESCENDANT");
            this.fTokenNames.put(new Integer(-1038), "EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF");
            this.fTokenNames.put(new Integer(-1039), "EXPRTOKEN_AXISNAME_FOLLOWING");
            this.fTokenNames.put(new Integer(-1040), "EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING");
            this.fTokenNames.put(new Integer(-1041), "EXPRTOKEN_AXISNAME_NAMESPACE");
            this.fTokenNames.put(new Integer(-1042), "EXPRTOKEN_AXISNAME_PARENT");
            this.fTokenNames.put(new Integer(-1043), "EXPRTOKEN_AXISNAME_PRECEDING");
            this.fTokenNames.put(new Integer(-1044), "EXPRTOKEN_AXISNAME_PRECEDING_SIBLING");
            this.fTokenNames.put(new Integer(-1045), "EXPRTOKEN_AXISNAME_SELF");
            this.fTokenNames.put(new Integer(-1046), "EXPRTOKEN_LITERAL");
            this.fTokenNames.put(new Integer(-1047), "EXPRTOKEN_NUMBER");
            this.fTokenNames.put(new Integer(-1048), "EXPRTOKEN_VARIABLE_REFERENCE");
        }
        
        public String getTokenName(final int n) {
            return this.fTokenNames.get(new Integer(n));
        }
        
        public int getTokenString(final int n) {
            return n;
        }
        
        public void addToken(final int n) {
            try {
                this.fTokens[this.fTokenCount] = n;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                System.arraycopy(this.fTokens, 0, this.fTokens = new int[this.fTokenCount << 1], 0, this.fTokenCount);
                this.fTokens[this.fTokenCount] = n;
            }
            ++this.fTokenCount;
        }
        
        public int getTokenCount() {
            return this.fTokenCount;
        }
        
        public int getToken(final int n) {
            return this.fTokens[n];
        }
        
        public void dumpTokens() {
            for (int i = 0; i < this.fTokenCount; ++i) {
                switch (this.fTokens[i]) {
                    case -1000: {
                        System.out.print("<OPEN_PAREN/>");
                        break;
                    }
                    case -1001: {
                        System.out.print("<CLOSE_PAREN/>");
                        break;
                    }
                    case -1002: {
                        System.out.print("<OPEN_BRACKET/>");
                        break;
                    }
                    case -1003: {
                        System.out.print("<CLOSE_BRACKET/>");
                        break;
                    }
                    case -1004: {
                        System.out.print("<PERIOD/>");
                        break;
                    }
                    case -1005: {
                        System.out.print("<DOUBLE_PERIOD/>");
                        break;
                    }
                    case -1006: {
                        System.out.print("<ATSIGN/>");
                        break;
                    }
                    case -1007: {
                        System.out.print("<COMMA/>");
                        break;
                    }
                    case -1008: {
                        System.out.print("<DOUBLE_COLON/>");
                        break;
                    }
                    case -1009: {
                        System.out.print("<NAMETEST_ANY/>");
                        break;
                    }
                    case -1010: {
                        System.out.print("<NAMETEST_NAMESPACE");
                        System.out.print(" prefix=\"" + this.getTokenString(this.fTokens[++i]) + "\"");
                        System.out.print("/>");
                        break;
                    }
                    case -1011: {
                        System.out.print("<NAMETEST_QNAME");
                        if (this.fTokens[++i] != -1) {
                            System.out.print(" prefix=\"" + this.getTokenString(this.fTokens[i]) + "\"");
                        }
                        System.out.print(" localpart=\"" + this.getTokenString(this.fTokens[++i]) + "\"");
                        System.out.print("/>");
                        break;
                    }
                    case -1012: {
                        System.out.print("<NODETYPE_COMMENT/>");
                        break;
                    }
                    case -1013: {
                        System.out.print("<NODETYPE_TEXT/>");
                        break;
                    }
                    case -1014: {
                        System.out.print("<NODETYPE_PI/>");
                        break;
                    }
                    case -1015: {
                        System.out.print("<NODETYPE_NODE/>");
                        break;
                    }
                    case -1016: {
                        System.out.print("<OPERATOR_AND/>");
                        break;
                    }
                    case -1017: {
                        System.out.print("<OPERATOR_OR/>");
                        break;
                    }
                    case -1018: {
                        System.out.print("<OPERATOR_MOD/>");
                        break;
                    }
                    case -1019: {
                        System.out.print("<OPERATOR_DIV/>");
                        break;
                    }
                    case -1020: {
                        System.out.print("<OPERATOR_MULT/>");
                        break;
                    }
                    case -1021: {
                        System.out.print("<OPERATOR_SLASH/>");
                        if (i + 1 < this.fTokenCount) {
                            System.out.println();
                            System.out.print("  ");
                            break;
                        }
                        break;
                    }
                    case -1022: {
                        System.out.print("<OPERATOR_DOUBLE_SLASH/>");
                        break;
                    }
                    case -1023: {
                        System.out.print("<OPERATOR_UNION/>");
                        break;
                    }
                    case -1024: {
                        System.out.print("<OPERATOR_PLUS/>");
                        break;
                    }
                    case -1025: {
                        System.out.print("<OPERATOR_MINUS/>");
                        break;
                    }
                    case -1026: {
                        System.out.print("<OPERATOR_EQUAL/>");
                        break;
                    }
                    case -1027: {
                        System.out.print("<OPERATOR_NOT_EQUAL/>");
                        break;
                    }
                    case -1028: {
                        System.out.print("<OPERATOR_LESS/>");
                        break;
                    }
                    case -1029: {
                        System.out.print("<OPERATOR_LESS_EQUAL/>");
                        break;
                    }
                    case -1030: {
                        System.out.print("<OPERATOR_GREATER/>");
                        break;
                    }
                    case -1031: {
                        System.out.print("<OPERATOR_GREATER_EQUAL/>");
                        break;
                    }
                    case -1032: {
                        System.out.print("<FUNCTION_NAME");
                        if (this.fTokens[++i] != -1) {
                            System.out.print(" prefix=\"" + this.getTokenString(this.fTokens[i]) + "\"");
                        }
                        System.out.print(" localpart=\"" + this.getTokenString(this.fTokens[++i]) + "\"");
                        System.out.print("/>");
                        break;
                    }
                    case -1033: {
                        System.out.print("<AXISNAME_ANCESTOR/>");
                        break;
                    }
                    case -1034: {
                        System.out.print("<AXISNAME_ANCESTOR_OR_SELF/>");
                        break;
                    }
                    case -1035: {
                        System.out.print("<AXISNAME_ATTRIBUTE/>");
                        break;
                    }
                    case -1036: {
                        System.out.print("<AXISNAME_CHILD/>");
                        break;
                    }
                    case -1037: {
                        System.out.print("<AXISNAME_DESCENDANT/>");
                        break;
                    }
                    case -1038: {
                        System.out.print("<AXISNAME_DESCENDANT_OR_SELF/>");
                        break;
                    }
                    case -1039: {
                        System.out.print("<AXISNAME_FOLLOWING/>");
                        break;
                    }
                    case -1040: {
                        System.out.print("<AXISNAME_FOLLOWING_SIBLING/>");
                        break;
                    }
                    case -1041: {
                        System.out.print("<AXISNAME_NAMESPACE/>");
                        break;
                    }
                    case -1042: {
                        System.out.print("<AXISNAME_PARENT/>");
                        break;
                    }
                    case -1043: {
                        System.out.print("<AXISNAME_PRECEDING/>");
                        break;
                    }
                    case -1044: {
                        System.out.print("<AXISNAME_PRECEDING_SIBLING/>");
                        break;
                    }
                    case -1045: {
                        System.out.print("<AXISNAME_SELF/>");
                        break;
                    }
                    case -1046: {
                        System.out.print("<LITERAL");
                        System.out.print(" value=\"" + this.getTokenString(this.fTokens[++i]) + "\"");
                        System.out.print("/>");
                        break;
                    }
                    case -1047: {
                        System.out.print("<NUMBER");
                        System.out.print(" whole=\"" + this.getTokenString(this.fTokens[++i]) + "\"");
                        System.out.print(" part=\"" + this.getTokenString(this.fTokens[++i]) + "\"");
                        System.out.print("/>");
                        break;
                    }
                    case -1048: {
                        System.out.print("<VARIABLE_REFERENCE");
                        if (this.fTokens[++i] != -1) {
                            System.out.print(" prefix=\"" + this.getTokenString(this.fTokens[i]) + "\"");
                        }
                        System.out.print(" localpart=\"" + this.getTokenString(this.fTokens[++i]) + "\"");
                        System.out.print("/>");
                        break;
                    }
                    default: {
                        System.out.println("<???/>");
                        break;
                    }
                }
            }
            System.out.println();
        }
    }
    
    private static class Scanner
    {
        private static final byte CHARTYPE_INVALID = 0;
        private static final byte CHARTYPE_OTHER = 1;
        private static final byte CHARTYPE_WHITESPACE = 2;
        private static final byte CHARTYPE_EXCLAMATION = 3;
        private static final byte CHARTYPE_QUOTE = 4;
        private static final byte CHARTYPE_DOLLAR = 5;
        private static final byte CHARTYPE_OPEN_PAREN = 6;
        private static final byte CHARTYPE_CLOSE_PAREN = 7;
        private static final byte CHARTYPE_STAR = 8;
        private static final byte CHARTYPE_PLUS = 9;
        private static final byte CHARTYPE_COMMA = 10;
        private static final byte CHARTYPE_MINUS = 11;
        private static final byte CHARTYPE_PERIOD = 12;
        private static final byte CHARTYPE_SLASH = 13;
        private static final byte CHARTYPE_DIGIT = 14;
        private static final byte CHARTYPE_COLON = 15;
        private static final byte CHARTYPE_LESS = 16;
        private static final byte CHARTYPE_EQUAL = 17;
        private static final byte CHARTYPE_GREATER = 18;
        private static final byte CHARTYPE_ATSIGN = 19;
        private static final byte CHARTYPE_LETTER = 20;
        private static final byte CHARTYPE_OPEN_BRACKET = 21;
        private static final byte CHARTYPE_CLOSE_BRACKET = 22;
        private static final byte CHARTYPE_UNDERSCORE = 23;
        private static final byte CHARTYPE_UNION = 24;
        private static final byte CHARTYPE_NONASCII = 25;
        private static byte[] fASCIICharMap;
        private StringPool fStringPool;
        private int fAndSymbol;
        private int fOrSymbol;
        private int fModSymbol;
        private int fDivSymbol;
        private int fCommentSymbol;
        private int fTextSymbol;
        private int fPISymbol;
        private int fNodeSymbol;
        private int fAncestorSymbol;
        private int fAncestorOrSelfSymbol;
        private int fAttributeSymbol;
        private int fChildSymbol;
        private int fDescendantSymbol;
        private int fDescendantOrSelfSymbol;
        private int fFollowingSymbol;
        private int fFollowingSiblingSymbol;
        private int fNamespaceSymbol;
        private int fParentSymbol;
        private int fPrecedingSymbol;
        private int fPrecedingSiblingSymbol;
        private int fSelfSymbol;
        
        public Scanner(final StringPool fStringPool) {
            this.fStringPool = fStringPool;
            this.fAndSymbol = this.fStringPool.addSymbol("and");
            this.fOrSymbol = this.fStringPool.addSymbol("or");
            this.fModSymbol = this.fStringPool.addSymbol("mod");
            this.fDivSymbol = this.fStringPool.addSymbol("div");
            this.fCommentSymbol = this.fStringPool.addSymbol("comment");
            this.fTextSymbol = this.fStringPool.addSymbol("text");
            this.fPISymbol = this.fStringPool.addSymbol("processing-instruction");
            this.fNodeSymbol = this.fStringPool.addSymbol("node");
            this.fAncestorSymbol = this.fStringPool.addSymbol("ancestor");
            this.fAncestorOrSelfSymbol = this.fStringPool.addSymbol("ancestor-or-self");
            this.fAttributeSymbol = this.fStringPool.addSymbol("attribute");
            this.fChildSymbol = this.fStringPool.addSymbol("child");
            this.fDescendantSymbol = this.fStringPool.addSymbol("descendant");
            this.fDescendantOrSelfSymbol = this.fStringPool.addSymbol("descendant-or-self");
            this.fFollowingSymbol = this.fStringPool.addSymbol("following");
            this.fFollowingSiblingSymbol = this.fStringPool.addSymbol("following-sibling");
            this.fNamespaceSymbol = this.fStringPool.addSymbol("namespace");
            this.fParentSymbol = this.fStringPool.addSymbol("parent");
            this.fPrecedingSymbol = this.fStringPool.addSymbol("preceding");
            this.fPrecedingSiblingSymbol = this.fStringPool.addSymbol("preceding-sibling");
            this.fSelfSymbol = this.fStringPool.addSymbol("self");
        }
        
        public boolean scanExpr(final StringPool stringPool, final Tokens tokens, final String s, int i, final int n) throws XPathException {
            int n2 = 0;
            char c;
            char char1;
            char c2;
            char c3;
            int n3;
            int n4;
            char c4;
            int n5;
            byte b;
            byte b2;
            int n6;
            int n7;
            char char2;
            int n8;
            byte b3;
            char char3;
            byte b4;
            int n9;
            byte b5;
            int n10;
            boolean b6;
            boolean b7;
            int n11;
            int n12;
            byte b8;
            byte b9;
            Label_1643_Outer:Label_2646:
            while (i != n) {
                for (c = s.charAt(i); (c == ' ' || c == '\n' || c == '\t' || c == '\r') && ++i != n; c = s.charAt(i)) {}
                if (i == n) {
                    return true;
                }
                switch ((c >= '\u0080') ? 25 : Scanner.fASCIICharMap[c]) {
                    case 6: {
                        this.addToken(tokens, -1000);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 7: {
                        this.addToken(tokens, -1001);
                        n2 = 1;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 21: {
                        this.addToken(tokens, -1002);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 22: {
                        this.addToken(tokens, -1003);
                        n2 = 1;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 12: {
                        if (i + 1 == n) {
                            this.addToken(tokens, -1004);
                            n2 = 1;
                            ++i;
                            continue;
                        }
                        char1 = s.charAt(i + 1);
                        if (char1 == '.') {
                            this.addToken(tokens, -1005);
                            n2 = 1;
                            i += 2;
                        }
                        else if (char1 >= '0' && char1 <= '9') {
                            this.addToken(tokens, -1047);
                            n2 = 1;
                            i = this.scanNumber(tokens, s, n, i);
                        }
                        else {
                            this.addToken(tokens, -1004);
                            n2 = 1;
                            ++i;
                        }
                        if (i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 19: {
                        this.addToken(tokens, -1006);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 10: {
                        this.addToken(tokens, -1007);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 15: {
                        if (++i == n) {
                            System.out.println("abort 1a");
                            return false;
                        }
                        if (s.charAt(i) != ':') {
                            System.out.println("abort 1b");
                            return false;
                        }
                        this.addToken(tokens, -1008);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 13: {
                        if (++i == n) {
                            this.addToken(tokens, -1021);
                            n2 = 0;
                            continue;
                        }
                        if (s.charAt(i) != '/') {
                            this.addToken(tokens, -1021);
                            n2 = 0;
                            continue;
                        }
                        this.addToken(tokens, -1022);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 24: {
                        this.addToken(tokens, -1023);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 9: {
                        this.addToken(tokens, -1024);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 11: {
                        this.addToken(tokens, -1025);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 17: {
                        this.addToken(tokens, -1026);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 3: {
                        if (++i == n) {
                            System.out.println("abort 2a");
                            return false;
                        }
                        if (s.charAt(i) != '=') {
                            System.out.println("abort 2b");
                            return false;
                        }
                        this.addToken(tokens, -1027);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 16: {
                        if (++i == n) {
                            this.addToken(tokens, -1028);
                            n2 = 0;
                            continue;
                        }
                        if (s.charAt(i) != '=') {
                            this.addToken(tokens, -1028);
                            n2 = 0;
                            continue;
                        }
                        this.addToken(tokens, -1029);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 18: {
                        if (++i == n) {
                            this.addToken(tokens, -1030);
                            n2 = 0;
                            continue;
                        }
                        if (s.charAt(i) != '=') {
                            this.addToken(tokens, -1030);
                            n2 = 0;
                            continue;
                        }
                        this.addToken(tokens, -1031);
                        n2 = 0;
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 4: {
                        c2 = c;
                        if (++i == n) {
                            System.out.println("abort 2c");
                            return false;
                        }
                        c3 = s.charAt(i);
                        n3 = i;
                        while (c3 != c2) {
                            if (++i == n) {
                                System.out.println("abort 2d");
                                return false;
                            }
                            c3 = s.charAt(i);
                        }
                        n4 = i - n3;
                        this.addToken(tokens, -1046);
                        n2 = 1;
                        tokens.addToken(stringPool.addSymbol(s.substring(n3, n3 + n4)));
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 14: {
                        this.addToken(tokens, -1047);
                        n2 = 1;
                        i = this.scanNumber(tokens, s, n, i);
                        continue;
                    }
                    case 5: {
                        if (++i == n) {
                            System.out.println("abort 3a");
                            return false;
                        }
                        c4 = s.charAt(i);
                        n5 = i;
                        if (c4 >= '\u0080') {
                            throw new RuntimeException("need encoding support");
                        }
                        b = Scanner.fASCIICharMap[c4];
                        if (b != 20 && b != 23) {
                            System.out.println("abort 3b");
                            return false;
                        }
                        while (++i < n) {
                            c4 = s.charAt(i);
                            if (c4 >= '\u0080') {
                                throw new RuntimeException("need encoding support");
                            }
                            b2 = Scanner.fASCIICharMap[c4];
                            if (b2 != 20 && b2 != 14 && b2 != 12 && b2 != 11 && b2 != 23) {
                                break;
                            }
                        }
                        n6 = stringPool.addSymbol(s.substring(n5, i));
                        if (c4 != ':') {
                            n7 = -1;
                        }
                        else {
                            n7 = n6;
                            if (++i == n) {
                                System.out.println("abort 4a");
                                return false;
                            }
                            char2 = s.charAt(i);
                            n8 = i;
                            if (char2 >= '\u0080') {
                                throw new RuntimeException("need encoding support");
                            }
                            b3 = Scanner.fASCIICharMap[char2];
                            if (b3 != 20 && b3 != 23) {
                                System.out.println("abort 4b");
                                return false;
                            }
                            while (++i < n) {
                                char3 = s.charAt(i);
                                if (char3 >= '\u0080') {
                                    throw new RuntimeException("need encoding support");
                                }
                                b4 = Scanner.fASCIICharMap[char3];
                                if (b4 != 20 && b4 != 14 && b4 != 12 && b4 != 11 && b4 != 23) {
                                    break;
                                }
                            }
                            n6 = stringPool.addSymbol(s.substring(n8, i));
                        }
                        this.addToken(tokens, -1048);
                        n2 = 1;
                        tokens.addToken(n7);
                        tokens.addToken(n6);
                        continue;
                    }
                    case 8: {
                        if (n2 != 0) {
                            this.addToken(tokens, -1020);
                            n2 = 0;
                        }
                        else {
                            this.addToken(tokens, -1009);
                            n2 = 1;
                        }
                        if (++i == n) {
                            continue;
                        }
                        continue;
                    }
                    case 25: {
                        throw new RuntimeException("need encoding support");
                    }
                    case 20:
                    case 23: {
                        n9 = i;
                        while (true) {
                            while (++i != n) {
                                c = s.charAt(i);
                                if (c >= '\u0080') {
                                    throw new RuntimeException("need encoding support");
                                }
                                b5 = Scanner.fASCIICharMap[c];
                                if (b5 == 20 || b5 == 14 || b5 == 12 || b5 == 11 || b5 == 23) {
                                    continue Label_1643_Outer;
                                }
                                n10 = stringPool.addSymbol(s.substring(n9, i));
                                b6 = false;
                                b7 = false;
                                n11 = -1;
                                if (c == ':') {
                                    if (++i == n) {
                                        System.out.println("abort 5");
                                        return false;
                                    }
                                    c = s.charAt(i);
                                    if (c == '*') {
                                        if (++i < n) {
                                            c = s.charAt(i);
                                        }
                                        b6 = true;
                                    }
                                    else if (c == ':') {
                                        if (++i < n) {
                                            c = s.charAt(i);
                                        }
                                        b7 = true;
                                    }
                                    else {
                                        n11 = n10;
                                        n12 = i;
                                        if (c >= '\u0080') {
                                            throw new RuntimeException("need encoding support");
                                        }
                                        b8 = Scanner.fASCIICharMap[c];
                                        if (b8 != 20 && b8 != 23) {
                                            System.out.println("abort 5b");
                                            return false;
                                        }
                                        while (++i < n) {
                                            c = s.charAt(i);
                                            if (c >= '\u0080') {
                                                throw new RuntimeException("need encoding support");
                                            }
                                            b9 = Scanner.fASCIICharMap[c];
                                            if (b9 != 20 && b9 != 14 && b9 != 12 && b9 != 11 && b9 != 23) {
                                                break;
                                            }
                                        }
                                        n10 = stringPool.addSymbol(s.substring(n12, i));
                                    }
                                }
                                while ((c == ' ' || c == '\n' || c == '\t' || c == '\r') && ++i != n) {
                                    c = s.charAt(i);
                                }
                                if (n2 != 0) {
                                    if (n10 == this.fAndSymbol) {
                                        this.addToken(tokens, -1016);
                                        n2 = 0;
                                    }
                                    else if (n10 == this.fOrSymbol) {
                                        this.addToken(tokens, -1017);
                                        n2 = 0;
                                    }
                                    else if (n10 == this.fModSymbol) {
                                        this.addToken(tokens, -1018);
                                        n2 = 0;
                                    }
                                    else {
                                        if (n10 != this.fDivSymbol) {
                                            System.out.println("abort 6");
                                            return false;
                                        }
                                        this.addToken(tokens, -1019);
                                        n2 = 0;
                                    }
                                    if (b6) {
                                        System.out.println("abort 7");
                                        return false;
                                    }
                                    if (b7) {
                                        System.out.println("abort 8");
                                        return false;
                                    }
                                    continue Label_2646;
                                }
                                else if (c == '(' && !b6 && !b7) {
                                    if (n10 == this.fCommentSymbol) {
                                        this.addToken(tokens, -1012);
                                    }
                                    else if (n10 == this.fTextSymbol) {
                                        this.addToken(tokens, -1013);
                                    }
                                    else if (n10 == this.fPISymbol) {
                                        this.addToken(tokens, -1014);
                                    }
                                    else if (n10 == this.fNodeSymbol) {
                                        this.addToken(tokens, -1015);
                                    }
                                    else {
                                        this.addToken(tokens, -1032);
                                        tokens.addToken(n11);
                                        tokens.addToken(n10);
                                    }
                                    this.addToken(tokens, -1000);
                                    n2 = 0;
                                    if (++i == n) {
                                        continue Label_2646;
                                    }
                                    continue Label_2646;
                                }
                                else if (b7 || (c == ':' && i + 1 < n && s.charAt(i + 1) == ':')) {
                                    if (n10 == this.fAncestorSymbol) {
                                        this.addToken(tokens, -1033);
                                    }
                                    else if (n10 == this.fAncestorOrSelfSymbol) {
                                        this.addToken(tokens, -1034);
                                    }
                                    else if (n10 == this.fAttributeSymbol) {
                                        this.addToken(tokens, -1035);
                                    }
                                    else if (n10 == this.fChildSymbol) {
                                        this.addToken(tokens, -1036);
                                    }
                                    else if (n10 == this.fDescendantSymbol) {
                                        this.addToken(tokens, -1037);
                                    }
                                    else if (n10 == this.fDescendantOrSelfSymbol) {
                                        this.addToken(tokens, -1038);
                                    }
                                    else if (n10 == this.fFollowingSymbol) {
                                        this.addToken(tokens, -1039);
                                    }
                                    else if (n10 == this.fFollowingSiblingSymbol) {
                                        this.addToken(tokens, -1040);
                                    }
                                    else if (n10 == this.fNamespaceSymbol) {
                                        this.addToken(tokens, -1041);
                                    }
                                    else if (n10 == this.fParentSymbol) {
                                        this.addToken(tokens, -1042);
                                    }
                                    else if (n10 == this.fPrecedingSymbol) {
                                        this.addToken(tokens, -1043);
                                    }
                                    else if (n10 == this.fPrecedingSiblingSymbol) {
                                        this.addToken(tokens, -1044);
                                    }
                                    else {
                                        if (n10 != this.fSelfSymbol) {
                                            System.out.println("abort 9");
                                            return false;
                                        }
                                        this.addToken(tokens, -1045);
                                    }
                                    if (b6) {
                                        System.out.println("abort 10");
                                        return false;
                                    }
                                    this.addToken(tokens, -1008);
                                    n2 = 0;
                                    if (b7) {
                                        continue Label_2646;
                                    }
                                    ++i;
                                    if (++i == n) {
                                        continue Label_2646;
                                    }
                                    continue Label_2646;
                                }
                                else {
                                    if (b6) {
                                        this.addToken(tokens, -1010);
                                        n2 = 1;
                                        tokens.addToken(n10);
                                        continue Label_2646;
                                    }
                                    this.addToken(tokens, -1011);
                                    n2 = 1;
                                    tokens.addToken(n11);
                                    tokens.addToken(n10);
                                    continue Label_2646;
                                }
                            }
                            continue;
                        }
                    }
                }
            }
            return true;
        }
        
        private int scanNumber(final Tokens tokens, final String s, final int n, int n2) {
            char c = s.charAt(n2);
            int n3 = 0;
            int n4 = 0;
            while (c >= '0' && c <= '9') {
                n3 = n3 * 10 + (c - '0');
                if (++n2 == n) {
                    break;
                }
                c = s.charAt(n2);
            }
            if (c == '.' && ++n2 < n) {
                for (char c2 = s.charAt(n2); c2 >= '0' && c2 <= '9'; c2 = s.charAt(n2)) {
                    n4 = n4 * 10 + (c2 - '0');
                    if (++n2 == n) {
                        break;
                    }
                }
                if (n4 != 0) {
                    throw new RuntimeException("find a solution!");
                }
            }
            tokens.addToken(n3);
            tokens.addToken(n4);
            return n2;
        }
        
        protected void addToken(final Tokens tokens, final int n) throws XPathException {
            tokens.addToken(n);
        }
        
        static {
            Scanner.fASCIICharMap = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 1, 5, 1, 1, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 1, 16, 17, 18, 1, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 1, 22, 1, 23, 1, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 1, 24, 1, 1, 1 };
        }
    }
}
