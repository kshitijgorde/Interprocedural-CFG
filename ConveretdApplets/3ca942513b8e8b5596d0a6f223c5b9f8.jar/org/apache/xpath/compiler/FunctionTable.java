// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.compiler;

import java.util.Iterator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.functions.Function;
import javax.xml.xpath.XPathFunction;
import java.util.HashMap;

public class FunctionTable
{
    public static final int FUNC_CURRENT = 0;
    public static final int FUNC_LAST = 1;
    public static final int FUNC_POSITION = 2;
    public static final int FUNC_COUNT = 3;
    public static final int FUNC_ID = 4;
    public static final int FUNC_KEY = 5;
    public static final int FUNC_LOCAL_PART = 7;
    public static final int FUNC_NAMESPACE = 8;
    public static final int FUNC_QNAME = 9;
    public static final int FUNC_GENERATE_ID = 10;
    public static final int FUNC_NOT = 11;
    public static final int FUNC_TRUE = 12;
    public static final int FUNC_FALSE = 13;
    public static final int FUNC_BOOLEAN = 14;
    public static final int FUNC_NUMBER = 15;
    public static final int FUNC_FLOOR = 16;
    public static final int FUNC_CEILING = 17;
    public static final int FUNC_ROUND = 18;
    public static final int FUNC_SUM = 19;
    public static final int FUNC_STRING = 20;
    public static final int FUNC_STARTS_WITH = 21;
    public static final int FUNC_CONTAINS = 22;
    public static final int FUNC_SUBSTRING_BEFORE = 23;
    public static final int FUNC_SUBSTRING_AFTER = 24;
    public static final int FUNC_NORMALIZE_SPACE = 25;
    public static final int FUNC_TRANSLATE = 26;
    public static final int FUNC_CONCAT = 27;
    public static final int FUNC_SUBSTRING = 29;
    public static final int FUNC_STRING_LENGTH = 30;
    public static final int FUNC_SYSTEM_PROPERTY = 31;
    public static final int FUNC_LANG = 32;
    public static final int FUNC_EXT_FUNCTION_AVAILABLE = 33;
    public static final int FUNC_EXT_ELEM_AVAILABLE = 34;
    public static final int FUNC_UNPARSED_ENTITY_URI = 36;
    public static final int FUNC_DOCLOCATION = 35;
    private static Class[] m_functions;
    private static HashMap m_functionID;
    private Class[] m_functions_customer;
    private HashMap m_functionID_customer;
    private XPathFunction[] m_functions_JAXPcustomer;
    private HashMap m_functions_JAXPoverrides;
    private static final int NUM_BUILT_IN_FUNCS = 37;
    private static final int NUM_ALLOWABLE_ADDINS = 30;
    private int m_funcNextFreeIndex;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncCurrent;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncLast;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncPosition;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncCount;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncId;
    static /* synthetic */ Class class$org$apache$xalan$templates$FuncKey;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncLocalPart;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncNamespace;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncQname;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncGenerateId;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncNot;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncTrue;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncFalse;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncBoolean;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncLang;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncNumber;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncFloor;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncCeiling;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncRound;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncSum;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncString;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncStartsWith;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncContains;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncSubstringBefore;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncSubstringAfter;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncNormalizeSpace;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncTranslate;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncConcat;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncSystemProperty;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncExtFunctionAvailable;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncExtElementAvailable;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncSubstring;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncStringLength;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncDoclocation;
    static /* synthetic */ Class class$org$apache$xpath$functions$FuncUnparsedEntityURI;
    
    public FunctionTable() {
        this.m_functions_customer = new Class[30];
        this.m_functionID_customer = new HashMap();
        this.m_functions_JAXPcustomer = new XPathFunction[30];
        this.m_functions_JAXPoverrides = new HashMap();
        this.m_funcNextFreeIndex = 37;
    }
    
    String getFunctionName(final int funcID) {
        if (funcID < 37) {
            return FunctionTable.m_functions[funcID].getName();
        }
        return this.m_functions_customer[funcID - 37].getName();
    }
    
    Function getFunction(final int which) throws TransformerException {
        try {
            if (which < 37) {
                return FunctionTable.m_functions[which].newInstance();
            }
            return this.m_functions_customer[which - 37].newInstance();
        }
        catch (IllegalAccessException ex) {
            throw new TransformerException(ex.getMessage());
        }
        catch (InstantiationException ex2) {
            throw new TransformerException(ex2.getMessage());
        }
    }
    
    XPathFunction getJAXPFunction(final int which) {
        if (which < 37) {
            return this.m_functions_JAXPoverrides.get(new Integer(which));
        }
        return this.m_functions_JAXPcustomer[which - 37];
    }
    
    Object getFunctionID(final String key) {
        Object id = this.m_functionID_customer.get(key);
        if (null == id) {
            id = FunctionTable.m_functionID.get(key);
        }
        return id;
    }
    
    public int installFunction(final String name, final Class func) {
        final Object funcIndexObj = this.getFunctionID(name);
        int funcIndex;
        if (null != funcIndexObj) {
            funcIndex = (int)funcIndexObj;
            if (funcIndex < 37) {
                funcIndex = this.m_funcNextFreeIndex++;
                this.m_functionID_customer.put(name, new Integer(funcIndex));
            }
            this.m_functions_customer[funcIndex - 37] = func;
        }
        else {
            funcIndex = this.m_funcNextFreeIndex++;
            this.m_functions_customer[funcIndex - 37] = func;
            this.m_functionID_customer.put(name, new Integer(funcIndex));
        }
        return funcIndex;
    }
    
    public int installFunction(final String name, final XPathFunction func) {
        final Object funcIndexObj = this.getFunctionID(name);
        int funcIndex;
        if (null != funcIndexObj) {
            funcIndex = (int)funcIndexObj;
            if (funcIndex < 37) {
                this.m_functions_JAXPoverrides.put(new Integer(funcIndex), func);
            }
            else {
                this.m_functions_JAXPcustomer[funcIndex - 37] = func;
            }
        }
        else {
            funcIndex = this.m_funcNextFreeIndex++;
            this.m_functions_JAXPcustomer[funcIndex - 37] = func;
            this.m_functionID_customer.put(name, new Integer(funcIndex));
        }
        return funcIndex;
    }
    
    public boolean functionAvailable(final String methName) {
        final Object tblEntry = FunctionTable.m_functionID.get(methName);
        return null != tblEntry || this.customFunctionAvailable(methName);
    }
    
    public boolean customFunctionAvailable(final String methName) {
        return this.m_functionID_customer.get(methName) != null;
    }
    
    boolean overrideKeyExists(final int funcId) {
        return this.m_functions_JAXPoverrides.containsKey(new Integer(funcId));
    }
    
    public String getOrigFunctionName(final int funcId) {
        for (final String name : FunctionTable.m_functionID.keySet()) {
            if (FunctionTable.m_functionID.get(name) == funcId) {
                return name;
            }
        }
        return null;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        FunctionTable.m_functionID = new HashMap();
        (FunctionTable.m_functions = new Class[37])[0] = ((FunctionTable.class$org$apache$xpath$functions$FuncCurrent == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncCurrent = class$("org.apache.xpath.functions.FuncCurrent")) : FunctionTable.class$org$apache$xpath$functions$FuncCurrent);
        FunctionTable.m_functions[1] = ((FunctionTable.class$org$apache$xpath$functions$FuncLast == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncLast = class$("org.apache.xpath.functions.FuncLast")) : FunctionTable.class$org$apache$xpath$functions$FuncLast);
        FunctionTable.m_functions[2] = ((FunctionTable.class$org$apache$xpath$functions$FuncPosition == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncPosition = class$("org.apache.xpath.functions.FuncPosition")) : FunctionTable.class$org$apache$xpath$functions$FuncPosition);
        FunctionTable.m_functions[3] = ((FunctionTable.class$org$apache$xpath$functions$FuncCount == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncCount = class$("org.apache.xpath.functions.FuncCount")) : FunctionTable.class$org$apache$xpath$functions$FuncCount);
        FunctionTable.m_functions[4] = ((FunctionTable.class$org$apache$xpath$functions$FuncId == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncId = class$("org.apache.xpath.functions.FuncId")) : FunctionTable.class$org$apache$xpath$functions$FuncId);
        FunctionTable.m_functions[5] = ((FunctionTable.class$org$apache$xalan$templates$FuncKey == null) ? (FunctionTable.class$org$apache$xalan$templates$FuncKey = class$("org.apache.xalan.templates.FuncKey")) : FunctionTable.class$org$apache$xalan$templates$FuncKey);
        FunctionTable.m_functions[7] = ((FunctionTable.class$org$apache$xpath$functions$FuncLocalPart == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncLocalPart = class$("org.apache.xpath.functions.FuncLocalPart")) : FunctionTable.class$org$apache$xpath$functions$FuncLocalPart);
        FunctionTable.m_functions[8] = ((FunctionTable.class$org$apache$xpath$functions$FuncNamespace == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncNamespace = class$("org.apache.xpath.functions.FuncNamespace")) : FunctionTable.class$org$apache$xpath$functions$FuncNamespace);
        FunctionTable.m_functions[9] = ((FunctionTable.class$org$apache$xpath$functions$FuncQname == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncQname = class$("org.apache.xpath.functions.FuncQname")) : FunctionTable.class$org$apache$xpath$functions$FuncQname);
        FunctionTable.m_functions[10] = ((FunctionTable.class$org$apache$xpath$functions$FuncGenerateId == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncGenerateId = class$("org.apache.xpath.functions.FuncGenerateId")) : FunctionTable.class$org$apache$xpath$functions$FuncGenerateId);
        FunctionTable.m_functions[11] = ((FunctionTable.class$org$apache$xpath$functions$FuncNot == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncNot = class$("org.apache.xpath.functions.FuncNot")) : FunctionTable.class$org$apache$xpath$functions$FuncNot);
        FunctionTable.m_functions[12] = ((FunctionTable.class$org$apache$xpath$functions$FuncTrue == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncTrue = class$("org.apache.xpath.functions.FuncTrue")) : FunctionTable.class$org$apache$xpath$functions$FuncTrue);
        FunctionTable.m_functions[13] = ((FunctionTable.class$org$apache$xpath$functions$FuncFalse == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncFalse = class$("org.apache.xpath.functions.FuncFalse")) : FunctionTable.class$org$apache$xpath$functions$FuncFalse);
        FunctionTable.m_functions[14] = ((FunctionTable.class$org$apache$xpath$functions$FuncBoolean == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncBoolean = class$("org.apache.xpath.functions.FuncBoolean")) : FunctionTable.class$org$apache$xpath$functions$FuncBoolean);
        FunctionTable.m_functions[32] = ((FunctionTable.class$org$apache$xpath$functions$FuncLang == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncLang = class$("org.apache.xpath.functions.FuncLang")) : FunctionTable.class$org$apache$xpath$functions$FuncLang);
        FunctionTable.m_functions[15] = ((FunctionTable.class$org$apache$xpath$functions$FuncNumber == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncNumber = class$("org.apache.xpath.functions.FuncNumber")) : FunctionTable.class$org$apache$xpath$functions$FuncNumber);
        FunctionTable.m_functions[16] = ((FunctionTable.class$org$apache$xpath$functions$FuncFloor == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncFloor = class$("org.apache.xpath.functions.FuncFloor")) : FunctionTable.class$org$apache$xpath$functions$FuncFloor);
        FunctionTable.m_functions[17] = ((FunctionTable.class$org$apache$xpath$functions$FuncCeiling == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncCeiling = class$("org.apache.xpath.functions.FuncCeiling")) : FunctionTable.class$org$apache$xpath$functions$FuncCeiling);
        FunctionTable.m_functions[18] = ((FunctionTable.class$org$apache$xpath$functions$FuncRound == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncRound = class$("org.apache.xpath.functions.FuncRound")) : FunctionTable.class$org$apache$xpath$functions$FuncRound);
        FunctionTable.m_functions[19] = ((FunctionTable.class$org$apache$xpath$functions$FuncSum == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncSum = class$("org.apache.xpath.functions.FuncSum")) : FunctionTable.class$org$apache$xpath$functions$FuncSum);
        FunctionTable.m_functions[20] = ((FunctionTable.class$org$apache$xpath$functions$FuncString == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncString = class$("org.apache.xpath.functions.FuncString")) : FunctionTable.class$org$apache$xpath$functions$FuncString);
        FunctionTable.m_functions[21] = ((FunctionTable.class$org$apache$xpath$functions$FuncStartsWith == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncStartsWith = class$("org.apache.xpath.functions.FuncStartsWith")) : FunctionTable.class$org$apache$xpath$functions$FuncStartsWith);
        FunctionTable.m_functions[22] = ((FunctionTable.class$org$apache$xpath$functions$FuncContains == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncContains = class$("org.apache.xpath.functions.FuncContains")) : FunctionTable.class$org$apache$xpath$functions$FuncContains);
        FunctionTable.m_functions[23] = ((FunctionTable.class$org$apache$xpath$functions$FuncSubstringBefore == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncSubstringBefore = class$("org.apache.xpath.functions.FuncSubstringBefore")) : FunctionTable.class$org$apache$xpath$functions$FuncSubstringBefore);
        FunctionTable.m_functions[24] = ((FunctionTable.class$org$apache$xpath$functions$FuncSubstringAfter == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncSubstringAfter = class$("org.apache.xpath.functions.FuncSubstringAfter")) : FunctionTable.class$org$apache$xpath$functions$FuncSubstringAfter);
        FunctionTable.m_functions[25] = ((FunctionTable.class$org$apache$xpath$functions$FuncNormalizeSpace == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncNormalizeSpace = class$("org.apache.xpath.functions.FuncNormalizeSpace")) : FunctionTable.class$org$apache$xpath$functions$FuncNormalizeSpace);
        FunctionTable.m_functions[26] = ((FunctionTable.class$org$apache$xpath$functions$FuncTranslate == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncTranslate = class$("org.apache.xpath.functions.FuncTranslate")) : FunctionTable.class$org$apache$xpath$functions$FuncTranslate);
        FunctionTable.m_functions[27] = ((FunctionTable.class$org$apache$xpath$functions$FuncConcat == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncConcat = class$("org.apache.xpath.functions.FuncConcat")) : FunctionTable.class$org$apache$xpath$functions$FuncConcat);
        FunctionTable.m_functions[31] = ((FunctionTable.class$org$apache$xpath$functions$FuncSystemProperty == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncSystemProperty = class$("org.apache.xpath.functions.FuncSystemProperty")) : FunctionTable.class$org$apache$xpath$functions$FuncSystemProperty);
        FunctionTable.m_functions[33] = ((FunctionTable.class$org$apache$xpath$functions$FuncExtFunctionAvailable == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncExtFunctionAvailable = class$("org.apache.xpath.functions.FuncExtFunctionAvailable")) : FunctionTable.class$org$apache$xpath$functions$FuncExtFunctionAvailable);
        FunctionTable.m_functions[34] = ((FunctionTable.class$org$apache$xpath$functions$FuncExtElementAvailable == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncExtElementAvailable = class$("org.apache.xpath.functions.FuncExtElementAvailable")) : FunctionTable.class$org$apache$xpath$functions$FuncExtElementAvailable);
        FunctionTable.m_functions[29] = ((FunctionTable.class$org$apache$xpath$functions$FuncSubstring == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncSubstring = class$("org.apache.xpath.functions.FuncSubstring")) : FunctionTable.class$org$apache$xpath$functions$FuncSubstring);
        FunctionTable.m_functions[30] = ((FunctionTable.class$org$apache$xpath$functions$FuncStringLength == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncStringLength = class$("org.apache.xpath.functions.FuncStringLength")) : FunctionTable.class$org$apache$xpath$functions$FuncStringLength);
        FunctionTable.m_functions[35] = ((FunctionTable.class$org$apache$xpath$functions$FuncDoclocation == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncDoclocation = class$("org.apache.xpath.functions.FuncDoclocation")) : FunctionTable.class$org$apache$xpath$functions$FuncDoclocation);
        FunctionTable.m_functions[36] = ((FunctionTable.class$org$apache$xpath$functions$FuncUnparsedEntityURI == null) ? (FunctionTable.class$org$apache$xpath$functions$FuncUnparsedEntityURI = class$("org.apache.xpath.functions.FuncUnparsedEntityURI")) : FunctionTable.class$org$apache$xpath$functions$FuncUnparsedEntityURI);
        FunctionTable.m_functionID.put("current", new Integer(0));
        FunctionTable.m_functionID.put("last", new Integer(1));
        FunctionTable.m_functionID.put("position", new Integer(2));
        FunctionTable.m_functionID.put("count", new Integer(3));
        FunctionTable.m_functionID.put("id", new Integer(4));
        FunctionTable.m_functionID.put("key", new Integer(5));
        FunctionTable.m_functionID.put("local-name", new Integer(7));
        FunctionTable.m_functionID.put("namespace-uri", new Integer(8));
        FunctionTable.m_functionID.put("name", new Integer(9));
        FunctionTable.m_functionID.put("generate-id", new Integer(10));
        FunctionTable.m_functionID.put("not", new Integer(11));
        FunctionTable.m_functionID.put("true", new Integer(12));
        FunctionTable.m_functionID.put("false", new Integer(13));
        FunctionTable.m_functionID.put("boolean", new Integer(14));
        FunctionTable.m_functionID.put("lang", new Integer(32));
        FunctionTable.m_functionID.put("number", new Integer(15));
        FunctionTable.m_functionID.put("floor", new Integer(16));
        FunctionTable.m_functionID.put("ceiling", new Integer(17));
        FunctionTable.m_functionID.put("round", new Integer(18));
        FunctionTable.m_functionID.put("sum", new Integer(19));
        FunctionTable.m_functionID.put("string", new Integer(20));
        FunctionTable.m_functionID.put("starts-with", new Integer(21));
        FunctionTable.m_functionID.put("contains", new Integer(22));
        FunctionTable.m_functionID.put("substring-before", new Integer(23));
        FunctionTable.m_functionID.put("substring-after", new Integer(24));
        FunctionTable.m_functionID.put("normalize-space", new Integer(25));
        FunctionTable.m_functionID.put("translate", new Integer(26));
        FunctionTable.m_functionID.put("concat", new Integer(27));
        FunctionTable.m_functionID.put("system-property", new Integer(31));
        FunctionTable.m_functionID.put("function-available", new Integer(33));
        FunctionTable.m_functionID.put("element-available", new Integer(34));
        FunctionTable.m_functionID.put("substring", new Integer(29));
        FunctionTable.m_functionID.put("string-length", new Integer(30));
        FunctionTable.m_functionID.put("unparsed-entity-uri", new Integer(36));
        FunctionTable.m_functionID.put("document-location", new Integer(35));
    }
}
