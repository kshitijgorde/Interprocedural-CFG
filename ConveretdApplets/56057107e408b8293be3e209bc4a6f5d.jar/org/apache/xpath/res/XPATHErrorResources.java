// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.text.DecimalFormat;
import org.apache.xml.utils.res.XResourceBundleBase;

public class XPATHErrorResources extends XResourceBundleBase
{
    public static final String ERROR_SUFFIX = "ER";
    public static final String WARNING_SUFFIX = "WR";
    public static final int MAX_CODE = 69;
    public static final int MAX_WARNING = 11;
    public static final int MAX_OTHERS = 20;
    public static final int MAX_MESSAGES = 81;
    static final Object[][] contents;
    public static final int ERROR0000 = 0;
    public static final int ER_CURRENT_NOT_ALLOWED_IN_MATCH = 1;
    public static final int ER_CURRENT_TAKES_NO_ARGS = 2;
    public static final int ER_DOCUMENT_REPLACED = 3;
    public static final int ER_CONTEXT_HAS_NO_OWNERDOC = 4;
    public static final int ER_LOCALNAME_HAS_TOO_MANY_ARGS = 5;
    public static final int ER_NAMESPACEURI_HAS_TOO_MANY_ARGS = 6;
    public static final int ER_NORMALIZESPACE_HAS_TOO_MANY_ARGS = 7;
    public static final int ER_NUMBER_HAS_TOO_MANY_ARGS = 8;
    public static final int ER_NAME_HAS_TOO_MANY_ARGS = 9;
    public static final int ER_STRING_HAS_TOO_MANY_ARGS = 10;
    public static final int ER_STRINGLENGTH_HAS_TOO_MANY_ARGS = 11;
    public static final int ER_TRANSLATE_TAKES_3_ARGS = 12;
    public static final int ER_UNPARSEDENTITYURI_TAKES_1_ARG = 13;
    public static final int ER_NAMESPACEAXIS_NOT_IMPLEMENTED = 14;
    public static final int ER_UNKNOWN_AXIS = 15;
    public static final int ER_UNKNOWN_MATCH_OPERATION = 16;
    public static final int ER_INCORRECT_ARG_LENGTH = 17;
    public static final int ER_CANT_CONVERT_TO_NUMBER = 18;
    public static final int ER_CANT_CONVERT_TO_NODELIST = 19;
    public static final int ER_CANT_CONVERT_TO_MUTABLENODELIST = 20;
    public static final int ER_CANT_CONVERT_TO_TYPE = 21;
    public static final int ER_EXPECTED_MATCH_PATTERN = 22;
    public static final int ER_COULDNOT_GET_VAR_NAMED = 23;
    public static final int ER_UNKNOWN_OPCODE = 24;
    public static final int ER_EXTRA_ILLEGAL_TOKENS = 25;
    public static final int ER_EXPECTED_DOUBLE_QUOTE = 26;
    public static final int ER_EXPECTED_SINGLE_QUOTE = 27;
    public static final int ER_EMPTY_EXPRESSION = 28;
    public static final int ER_EXPECTED_BUT_FOUND = 29;
    public static final int ER_INCORRECT_PROGRAMMER_ASSERTION = 30;
    public static final int ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL = 31;
    public static final int ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG = 32;
    public static final int ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG = 33;
    public static final int ER_PREDICATE_ILLEGAL_SYNTAX = 34;
    public static final int ER_ILLEGAL_AXIS_NAME = 35;
    public static final int ER_UNKNOWN_NODETYPE = 36;
    public static final int ER_PATTERN_LITERAL_NEEDS_BE_QUOTED = 37;
    public static final int ER_COULDNOT_BE_FORMATTED_TO_NUMBER = 38;
    public static final int ER_COULDNOT_CREATE_XMLPROCESSORLIAISON = 39;
    public static final int ER_DIDNOT_FIND_XPATH_SELECT_EXP = 40;
    public static final int ER_COULDNOT_FIND_ENDOP_AFTER_OPLOCATIONPATH = 41;
    public static final int ER_ERROR_OCCURED = 42;
    public static final int ER_ILLEGAL_VARIABLE_REFERENCE = 43;
    public static final int ER_AXES_NOT_ALLOWED = 44;
    public static final int ER_KEY_HAS_TOO_MANY_ARGS = 45;
    public static final int ER_COUNT_TAKES_1_ARG = 46;
    public static final int ER_COULDNOT_FIND_FUNCTION = 47;
    public static final int ER_UNSUPPORTED_ENCODING = 48;
    public static final int ER_PROBLEM_IN_DTM_NEXTSIBLING = 49;
    public static final int ER_CANNOT_WRITE_TO_EMPTYNODELISTIMPL = 50;
    public static final int ER_SETDOMFACTORY_NOT_SUPPORTED = 51;
    public static final int ER_PREFIX_MUST_RESOLVE = 52;
    public static final int ER_PARSE_NOT_SUPPORTED = 53;
    public static final int ER_CREATEDOCUMENT_NOT_SUPPORTED = 54;
    public static final int ER_CHILD_HAS_NO_OWNER_DOCUMENT = 55;
    public static final int ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT = 56;
    public static final int ER_SAX_API_NOT_HANDLED = 57;
    public static final int ER_IGNORABLE_WHITESPACE_NOT_HANDLED = 58;
    public static final int ER_DTM_CANNOT_HANDLE_NODES = 59;
    public static final int ER_XERCES_CANNOT_HANDLE_NODES = 60;
    public static final int ER_XERCES_PARSE_ERROR_DETAILS = 61;
    public static final int ER_XERCES_PARSE_ERROR = 62;
    public static final int ER_CANT_OUTPUT_TEXT_BEFORE_DOC = 63;
    public static final int ER_CANT_HAVE_MORE_THAN_ONE_ROOT = 64;
    public static final int ER_INVALID_UTF16_SURROGATE = 65;
    public static final int ER_OIERROR = 66;
    public static final int ER_CANNOT_CREATE_URL = 67;
    public static final int ER_XPATH_READOBJECT = 68;
    public static final int ER_FUNCTION_TOKEN_NOT_FOUND = 69;
    public static final int WG_LOCALE_NAME_NOT_HANDLED = 1;
    public static final int WG_PROPERTY_NOT_SUPPORTED = 2;
    public static final int WG_DONT_DO_ANYTHING_WITH_NS = 3;
    public static final int WG_SECURITY_EXCEPTION = 4;
    public static final int WG_QUO_NO_LONGER_DEFINED = 5;
    public static final int WG_NEED_DERIVED_OBJECT_TO_IMPLEMENT_NODETEST = 6;
    public static final int WG_FUNCTION_TOKEN_NOT_FOUND = 7;
    public static final int WG_COULDNOT_FIND_FUNCTION = 8;
    public static final int WG_CANNOT_MAKE_URL_FROM = 9;
    public static final int WG_EXPAND_ENTITIES_NOT_SUPPORTED = 10;
    public static final int WG_ILLEGAL_VARIABLE_REFERENCE = 11;
    public static final int WG_UNSUPPORTED_ENCODING = 12;
    public static final String BAD_CODE = "BAD_CODE";
    public static final String FORMAT_FAILED = "FORMAT_FAILED";
    public static final String ERROR_RESOURCES = "org.apache.xpath.res.XPATHErrorResources";
    public static final String ERROR_STRING = "#error";
    public static final String ERROR_HEADER = "Error: ";
    public static final String WARNING_HEADER = "Warning: ";
    public static final String XSL_HEADER = "XSL ";
    public static final String XML_HEADER = "XML ";
    public static final String QUERY_HEADER = "PATTERN ";
    
    static {
        contents = new Object[102][2];
        for (int i = 0; i < 70; ++i) {
            XPATHErrorResources.contents[i][0] = getMKey(i);
        }
        for (int j = 1; j < 12; ++j) {
            XPATHErrorResources.contents[j + 69][0] = getWKey(j);
        }
        XPATHErrorResources.contents[0][1] = "{0}";
        XPATHErrorResources.contents[1][1] = "The current() function is not allowed in a match pattern!";
        XPATHErrorResources.contents[2][1] = "The current() function does not accept arguments!";
        XPATHErrorResources.contents[3][1] = "document() function implementation has been replaced by org.apache.xalan.xslt.FuncDocument!";
        XPATHErrorResources.contents[4][1] = "context does not have an owner document!";
        XPATHErrorResources.contents[5][1] = "local-name() has too many arguments.";
        XPATHErrorResources.contents[6][1] = "namespace-uri() has too many arguments.";
        XPATHErrorResources.contents[7][1] = "normalize-space() has too many arguments.";
        XPATHErrorResources.contents[8][1] = "number() has too many arguments.";
        XPATHErrorResources.contents[9][1] = "name() has too many arguments.";
        XPATHErrorResources.contents[10][1] = "string() has too many arguments.";
        XPATHErrorResources.contents[11][1] = "string-length() has too many arguments.";
        XPATHErrorResources.contents[12][1] = "The translate() function takes three arguments!";
        XPATHErrorResources.contents[13][1] = "The unparsed-entity-uri function should take one argument!";
        XPATHErrorResources.contents[14][1] = "namespace axis not implemented yet!";
        XPATHErrorResources.contents[15][1] = "unknown axis: {0}";
        XPATHErrorResources.contents[16][1] = "unknown match operation!";
        XPATHErrorResources.contents[17][1] = "Arg length of processing-instruction() node test is incorrect!";
        XPATHErrorResources.contents[18][1] = "Can not convert {0} to a number";
        XPATHErrorResources.contents[19][1] = "Can not convert {0} to a NodeList!";
        XPATHErrorResources.contents[20][1] = "Can not convert {0} to a NodeSet!";
        XPATHErrorResources.contents[21][1] = "Can not convert {0} to a type#{1}";
        XPATHErrorResources.contents[22][1] = "Expected match pattern in getMatchScore!";
        XPATHErrorResources.contents[23][1] = "Could not get variable named {0}";
        XPATHErrorResources.contents[24][1] = "ERROR! Unknown op code: {0}";
        XPATHErrorResources.contents[25][1] = "Extra illegal tokens: {0}";
        XPATHErrorResources.contents[26][1] = "misquoted literal... expected double quote!";
        XPATHErrorResources.contents[27][1] = "misquoted literal... expected single quote!";
        XPATHErrorResources.contents[28][1] = "Empty expression!";
        XPATHErrorResources.contents[29][1] = "Expected {0}, but found: {1}";
        XPATHErrorResources.contents[30][1] = "Programmer assertion is incorrect! - {0}";
        XPATHErrorResources.contents[31][1] = "boolean(...) argument is no longer optional with 19990709 XPath draft.";
        XPATHErrorResources.contents[32][1] = "Found ',' but no preceding argument!";
        XPATHErrorResources.contents[33][1] = "Found ',' but no following argument!";
        XPATHErrorResources.contents[34][1] = "'..[predicate]' or '.[predicate]' is illegal syntax.  Use 'self::node()[predicate]' instead.";
        XPATHErrorResources.contents[35][1] = "illegal axis name: {0}";
        XPATHErrorResources.contents[36][1] = "Unknown nodetype: {0}";
        XPATHErrorResources.contents[37][1] = "Pattern literal ({0}) needs to be quoted!";
        XPATHErrorResources.contents[38][1] = "{0} could not be formatted to a number!";
        XPATHErrorResources.contents[39][1] = "Could not create XML TransformerFactory Liaison: {0}";
        XPATHErrorResources.contents[40][1] = "Error! Did not find xpath select expression (-select).";
        XPATHErrorResources.contents[41][1] = "ERROR! Could not find ENDOP after OP_LOCATIONPATH";
        XPATHErrorResources.contents[42][1] = "Error occured!";
        XPATHErrorResources.contents[43][1] = "VariableReference given for variable out of context or without definition!  Name = {0}";
        XPATHErrorResources.contents[44][1] = "Only child:: and attribute:: axes are allowed in match patterns!  Offending axes = {0}";
        XPATHErrorResources.contents[45][1] = "key() has an incorrect number of arguments.";
        XPATHErrorResources.contents[46][1] = "The count function should take one argument!";
        XPATHErrorResources.contents[47][1] = "Could not find function: {0}";
        XPATHErrorResources.contents[48][1] = "Unsupported encoding: {0}";
        XPATHErrorResources.contents[49][1] = "Problem occured in DTM in getNextSibling... trying to recover";
        XPATHErrorResources.contents[50][1] = "Programmer error: EmptyNodeList can not be written to.";
        XPATHErrorResources.contents[51][1] = "setDOMFactory is not supported by XPathContext!";
        XPATHErrorResources.contents[52][1] = "Prefix must resolve to a namespace: {0}";
        XPATHErrorResources.contents[53][1] = "parse (InputSource source) not supported in XPathContext! Can not open {0}";
        XPATHErrorResources.contents[54][1] = "createDocument() not supported in XPathContext!";
        XPATHErrorResources.contents[55][1] = "Attribute child does not have an owner document!";
        XPATHErrorResources.contents[56][1] = "Attribute child does not have an owner document element!";
        XPATHErrorResources.contents[57][1] = "SAX API characters(char ch[]... not handled by the DTM!";
        XPATHErrorResources.contents[58][1] = "ignorableWhitespace(char ch[]... not handled by the DTM!";
        XPATHErrorResources.contents[59][1] = "DTMLiaison can not handle nodes of type {0}";
        XPATHErrorResources.contents[60][1] = "DOM2Helper can not handle nodes of type {0}";
        XPATHErrorResources.contents[61][1] = "DOM2Helper.parse error: SystemID - {0} line - {1}";
        XPATHErrorResources.contents[62][1] = "DOM2Helper.parse error";
        XPATHErrorResources.contents[63][1] = "Warning: can't output text before document element!  Ignoring...";
        XPATHErrorResources.contents[64][1] = "Can't have more than one root on a DOM!";
        XPATHErrorResources.contents[65][1] = "Invalid UTF-16 surrogate detected: {0} ?";
        XPATHErrorResources.contents[66][1] = "IO error";
        XPATHErrorResources.contents[67][1] = "Cannot create url for: {0}";
        XPATHErrorResources.contents[68][1] = "In XPath.readObject: {0}";
        XPATHErrorResources.contents[69][1] = "function token not found.";
        XPATHErrorResources.contents[70][1] = "locale name in the format-number function not yet handled!";
        XPATHErrorResources.contents[71][1] = "XSL Property not supported: {0}";
        XPATHErrorResources.contents[72][1] = "Do not currently do anything with namespace {0} in property: {1}";
        XPATHErrorResources.contents[73][1] = "SecurityException when trying to access XSL system property: {0}";
        XPATHErrorResources.contents[74][1] = "Old syntax: quo(...) is no longer defined in XPath.";
        XPATHErrorResources.contents[75][1] = "XPath needs a derived object to implement nodeTest!";
        XPATHErrorResources.contents[76][1] = "function token not found.";
        XPATHErrorResources.contents[77][1] = "Could not find function: {0}";
        XPATHErrorResources.contents[78][1] = "Can not make URL from: {0}";
        XPATHErrorResources.contents[79][1] = "-E option not supported for DTM parser";
        XPATHErrorResources.contents[80][1] = "VariableReference given for variable out of context or without definition!  Name = {0}";
        XPATHErrorResources.contents[48][1] = "Unsupported encoding: {0}";
        XPATHErrorResources.contents[81][0] = "ui_language";
        XPATHErrorResources.contents[81][1] = "en";
        XPATHErrorResources.contents[82][0] = "help_language";
        XPATHErrorResources.contents[82][1] = "en";
        XPATHErrorResources.contents[83][0] = "language";
        XPATHErrorResources.contents[83][1] = "en";
        XPATHErrorResources.contents[84][0] = "BAD_CODE";
        XPATHErrorResources.contents[84][1] = "Parameter to createMessage was out of bounds";
        XPATHErrorResources.contents[85][0] = "FORMAT_FAILED";
        XPATHErrorResources.contents[85][1] = "Exception thrown during messageFormat call";
        XPATHErrorResources.contents[86][0] = "version";
        XPATHErrorResources.contents[86][1] = ">>>>>>> Xalan Version ";
        XPATHErrorResources.contents[87][0] = "version2";
        XPATHErrorResources.contents[87][1] = "<<<<<<<";
        XPATHErrorResources.contents[88][0] = "yes";
        XPATHErrorResources.contents[88][1] = "yes";
        XPATHErrorResources.contents[89][0] = "line";
        XPATHErrorResources.contents[89][1] = "Line #";
        XPATHErrorResources.contents[90][0] = "column";
        XPATHErrorResources.contents[90][1] = "Column #";
        XPATHErrorResources.contents[91][0] = "xsldone";
        XPATHErrorResources.contents[91][1] = "XSLProcessor: done";
        XPATHErrorResources.contents[92][0] = "xpath_option";
        XPATHErrorResources.contents[92][1] = "xpath options: ";
        XPATHErrorResources.contents[93][0] = "optionIN";
        XPATHErrorResources.contents[93][1] = "   [-in inputXMLURL]";
        XPATHErrorResources.contents[94][0] = "optionSelect";
        XPATHErrorResources.contents[94][1] = "   [-select xpath expression]";
        XPATHErrorResources.contents[95][0] = "optionMatch";
        XPATHErrorResources.contents[95][1] = "   [-match match pattern (for match diagnostics)]";
        XPATHErrorResources.contents[96][0] = "optionAnyExpr";
        XPATHErrorResources.contents[96][1] = "Or just an xpath expression will do a diagnostic dump";
        XPATHErrorResources.contents[97][0] = "noParsermsg1";
        XPATHErrorResources.contents[97][1] = "XSL Process was not successful.";
        XPATHErrorResources.contents[98][0] = "noParsermsg2";
        XPATHErrorResources.contents[98][1] = "** Could not find parser **";
        XPATHErrorResources.contents[99][0] = "noParsermsg3";
        XPATHErrorResources.contents[99][1] = "Please check your classpath.";
        XPATHErrorResources.contents[100][0] = "noParsermsg4";
        XPATHErrorResources.contents[100][1] = "If you don't have IBM's XML Parser for Java, you can download it from";
        XPATHErrorResources.contents[101][0] = "noParsermsg5";
        XPATHErrorResources.contents[101][1] = "IBM's AlphaWorks: http://www.alphaworks.ibm.com/formula/xml";
    }
    
    public Object[][] getContents() {
        return XPATHErrorResources.contents;
    }
    
    public static String getMKey(final int errorCode) {
        if (errorCode > 69) {
            return null;
        }
        final DecimalFormat df = new DecimalFormat("0000");
        return "ER" + df.format(errorCode);
    }
    
    public String getMessageKey(final int errorCode) {
        if (errorCode > 69) {
            return null;
        }
        final DecimalFormat df = new DecimalFormat("0000");
        return "ER" + df.format(errorCode);
    }
    
    private static final String getResourceSuffix(final Locale locale) {
        String suffix = "_" + locale.getLanguage();
        final String country = locale.getCountry();
        if (country.equals("TW")) {
            suffix = String.valueOf(suffix) + "_" + country;
        }
        return suffix;
    }
    
    public static String getWKey(final int errorCode) {
        if (errorCode > 11) {
            return null;
        }
        final DecimalFormat df = new DecimalFormat("0000");
        return "WR" + df.format(errorCode);
    }
    
    public String getWarningKey(final int errorCode) {
        if (errorCode > 11) {
            return null;
        }
        final DecimalFormat df = new DecimalFormat("0000");
        return "WR" + df.format(errorCode);
    }
    
    public static final XPATHErrorResources loadResourceBundle(final String className) throws MissingResourceException {
        final Locale locale = Locale.getDefault();
        final String suffix = getResourceSuffix(locale);
        try {
            return (XPATHErrorResources)ResourceBundle.getBundle(String.valueOf(className) + suffix, locale);
        }
        catch (MissingResourceException ex) {
            try {
                return (XPATHErrorResources)ResourceBundle.getBundle(className, new Locale("en", "US"));
            }
            catch (MissingResourceException ex2) {
                throw new MissingResourceException("Could not load any resource bundles.", className, "");
            }
        }
    }
}
