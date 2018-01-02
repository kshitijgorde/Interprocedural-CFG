// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.text.DecimalFormat;
import org.apache.xml.utils.res.XResourceBundleBase;

public class XSLTErrorResources extends XResourceBundleBase
{
    public static final String ERROR_SUFFIX = "ER";
    public static final String WARNING_SUFFIX = "WR";
    public static final int MAX_CODE = 108;
    public static final int MAX_WARNING = 26;
    public static final int MAX_OTHERS = 41;
    public static final int MAX_MESSAGES = 135;
    static final Object[][] contents;
    public static final int ERROR0000 = 0;
    public static final int ER_NO_CURLYBRACE = 1;
    public static final int ER_ILLEGAL_ATTRIBUTE = 2;
    public static final int ER_NULL_SOURCENODE_APPLYIMPORTS = 3;
    public static final int ER_CANNOT_ADD = 4;
    public static final int ER_NULL_SOURCENODE_HANDLEAPPLYTEMPLATES = 5;
    public static final int ER_NO_NAME_ATTRIB = 6;
    public static final int ER_TEMPLATE_NOT_FOUND = 7;
    public static final int ER_CANT_RESOLVE_NAME_AVT = 8;
    public static final int ER_REQUIRES_ATTRIB = 9;
    public static final int ER_MUST_HAVE_TEST_ATTRIB = 10;
    public static final int ER_BAD_VAL_ON_LEVEL_ATTRIB = 11;
    public static final int ER_PROCESSINGINSTRUCTION_NAME_CANT_BE_XML = 12;
    public static final int ER_PROCESSINGINSTRUCTION_NOTVALID_NCNAME = 13;
    public static final int ER_NEED_MATCH_ATTRIB = 14;
    public static final int ER_NEED_NAME_OR_MATCH_ATTRIB = 15;
    public static final int ER_CANT_RESOLVE_NSPREFIX = 16;
    public static final int ER_ILLEGAL_VALUE = 17;
    public static final int ER_NO_OWNERDOC = 18;
    public static final int ER_ELEMTEMPLATEELEM_ERR = 19;
    public static final int ER_NULL_CHILD = 20;
    public static final int ER_NEED_SELECT_ATTRIB = 21;
    public static final int ER_NEED_TEST_ATTRIB = 22;
    public static final int ER_NEED_NAME_ATTRIB = 23;
    public static final int ER_NO_CONTEXT_OWNERDOC = 24;
    public static final int ER_COULD_NOT_CREATE_XML_PROC_LIAISON = 25;
    public static final int ER_PROCESS_NOT_SUCCESSFUL = 26;
    public static final int ER_NOT_SUCCESSFUL = 27;
    public static final int ER_ENCODING_NOT_SUPPORTED = 28;
    public static final int ER_COULD_NOT_CREATE_TRACELISTENER = 29;
    public static final int ER_KEY_REQUIRES_NAME_ATTRIB = 30;
    public static final int ER_KEY_REQUIRES_MATCH_ATTRIB = 31;
    public static final int ER_KEY_REQUIRES_USE_ATTRIB = 32;
    public static final int ER_REQUIRES_ELEMENTS_ATTRIB = 33;
    public static final int ER_MISSING_PREFIX_ATTRIB = 34;
    public static final int ER_BAD_STYLESHEET_URL = 35;
    public static final int ER_FILE_NOT_FOUND = 36;
    public static final int ER_IOEXCEPTION = 37;
    public static final int ER_NO_HREF_ATTRIB = 38;
    public static final int ER_STYLESHEET_INCLUDES_ITSELF = 39;
    public static final int ER_PROCESSINCLUDE_ERROR = 40;
    public static final int ER_MISSING_LANG_ATTRIB = 41;
    public static final int ER_MISSING_CONTAINER_ELEMENT_COMPONENT = 42;
    public static final int ER_CAN_ONLY_OUTPUT_TO_ELEMENT = 43;
    public static final int ER_PROCESS_ERROR = 44;
    public static final int ER_UNIMPLNODE_ERROR = 45;
    public static final int ER_NO_SELECT_EXPRESSION = 46;
    public static final int ER_CANNOT_SERIALIZE_XSLPROCESSOR = 47;
    public static final int ER_NO_INPUT_STYLESHEET = 48;
    public static final int ER_FAILED_PROCESS_STYLESHEET = 49;
    public static final int ER_COULDNT_PARSE_DOC = 50;
    public static final int ER_COULDNT_FIND_FRAGMENT = 51;
    public static final int ER_NODE_NOT_ELEMENT = 52;
    public static final int ER_FOREACH_NEED_MATCH_OR_NAME_ATTRIB = 53;
    public static final int ER_TEMPLATES_NEED_MATCH_OR_NAME_ATTRIB = 54;
    public static final int ER_NO_CLONE_OF_DOCUMENT_FRAG = 55;
    public static final int ER_CANT_CREATE_ITEM = 56;
    public static final int ER_XMLSPACE_ILLEGAL_VALUE = 57;
    public static final int ER_NO_XSLKEY_DECLARATION = 58;
    public static final int ER_CANT_CREATE_URL = 59;
    public static final int ER_XSLFUNCTIONS_UNSUPPORTED = 60;
    public static final int ER_PROCESSOR_ERROR = 61;
    public static final int ER_NOT_ALLOWED_INSIDE_STYLESHEET = 62;
    public static final int ER_RESULTNS_NOT_SUPPORTED = 63;
    public static final int ER_DEFAULTSPACE_NOT_SUPPORTED = 64;
    public static final int ER_INDENTRESULT_NOT_SUPPORTED = 65;
    public static final int ER_ILLEGAL_ATTRIB = 66;
    public static final int ER_UNKNOWN_XSL_ELEM = 67;
    public static final int ER_BAD_XSLSORT_USE = 68;
    public static final int ER_MISPLACED_XSLWHEN = 69;
    public static final int ER_XSLWHEN_NOT_PARENTED_BY_XSLCHOOSE = 70;
    public static final int ER_MISPLACED_XSLOTHERWISE = 71;
    public static final int ER_XSLOTHERWISE_NOT_PARENTED_BY_XSLCHOOSE = 72;
    public static final int ER_NOT_ALLOWED_INSIDE_TEMPLATE = 73;
    public static final int ER_UNKNOWN_EXT_NS_PREFIX = 74;
    public static final int ER_IMPORTS_AS_FIRST_ELEM = 75;
    public static final int ER_IMPORTING_ITSELF = 76;
    public static final int ER_XMLSPACE_ILLEGAL_VAL = 77;
    public static final int ER_PROCESSSTYLESHEET_NOT_SUCCESSFUL = 78;
    public static final int ER_SAX_EXCEPTION = 79;
    public static final int ER_FUNCTION_NOT_SUPPORTED = 80;
    public static final int ER_XSLT_ERROR = 81;
    public static final int ER_CURRENCY_SIGN_ILLEGAL = 82;
    public static final int ER_DOCUMENT_FUNCTION_INVALID_IN_STYLESHEET_DOM = 83;
    public static final int ER_CANT_RESOLVE_PREFIX_OF_NON_PREFIX_RESOLVER = 84;
    public static final int ER_REDIRECT_COULDNT_GET_FILENAME = 85;
    public static final int ER_CANNOT_BUILD_FORMATTERLISTENER_IN_REDIRECT = 86;
    public static final int ER_INVALID_PREFIX_IN_EXCLUDERESULTPREFIX = 87;
    public static final int ER_MISSING_NS_URI = 88;
    public static final int ER_MISSING_ARG_FOR_OPTION = 89;
    public static final int ER_INVALID_OPTION = 90;
    public static final int ER_MALFORMED_FORMAT_STRING = 91;
    public static final int ER_STYLESHEET_REQUIRES_VERSION_ATTRIB = 92;
    public static final int ER_ILLEGAL_ATTRIBUTE_VALUE = 93;
    public static final int ER_CHOOSE_REQUIRES_WHEN = 94;
    public static final int ER_NO_APPLY_IMPORT_IN_FOR_EACH = 95;
    public static final int ER_CANT_USE_DTM_FOR_OUTPUT = 96;
    public static final int ER_CANT_USE_DTM_FOR_INPUT = 97;
    public static final int ER_CALL_TO_EXT_FAILED = 98;
    public static final int ER_PREFIX_MUST_RESOLVE = 99;
    public static final int ER_INVALID_UTF16_SURROGATE = 100;
    public static final int ER_XSLATTRSET_USED_ITSELF = 101;
    public static final int ER_CANNOT_MIX_XERCESDOM = 102;
    public static final int ER_TOO_MANY_LISTENERS = 103;
    public static final int ER_IN_ELEMTEMPLATEELEM_READOBJECT = 104;
    public static final int ER_DUPLICATE_NAMED_TEMPLATE = 105;
    public static final int ER_INVALID_KEY_CALL = 106;
    public static final int ER_REFERENCING_ITSELF = 107;
    public static final int ER_ILLEGAL_DOMSOURCE_INPUT = 108;
    public static final int WG_FOUND_CURLYBRACE = 1;
    public static final int WG_COUNT_ATTRIB_MATCHES_NO_ANCESTOR = 2;
    public static final int WG_EXPR_ATTRIB_CHANGED_TO_SELECT = 3;
    public static final int WG_NO_LOCALE_IN_FORMATNUMBER = 4;
    public static final int WG_LOCALE_NOT_FOUND = 5;
    public static final int WG_CANNOT_MAKE_URL_FROM = 6;
    public static final int WG_CANNOT_LOAD_REQUESTED_DOC = 7;
    public static final int WG_CANNOT_FIND_COLLATOR = 8;
    public static final int WG_FUNCTIONS_SHOULD_USE_URL = 9;
    public static final int WG_ENCODING_NOT_SUPPORTED_USING_UTF8 = 10;
    public static final int WG_ENCODING_NOT_SUPPORTED_USING_JAVA = 11;
    public static final int WG_SPECIFICITY_CONFLICTS = 12;
    public static final int WG_PARSING_AND_PREPARING = 13;
    public static final int WG_ATTR_TEMPLATE = 14;
    public static final int WG_CONFLICT_BETWEEN_XSLSTRIPSPACE_AND_XSLPRESERVESPACE = 15;
    public static final int WG_ATTRIB_NOT_HANDLED = 16;
    public static final int WG_NO_DECIMALFORMAT_DECLARATION = 17;
    public static final int WG_OLD_XSLT_NS = 18;
    public static final int WG_ONE_DEFAULT_XSLDECIMALFORMAT_ALLOWED = 19;
    public static final int WG_XSLDECIMALFORMAT_NAMES_MUST_BE_UNIQUE = 20;
    public static final int WG_ILLEGAL_ATTRIBUTE = 21;
    public static final int WG_COULD_NOT_RESOLVE_PREFIX = 22;
    public static final int WG_STYLESHEET_REQUIRES_VERSION_ATTRIB = 23;
    public static final int WG_ILLEGAL_ATTRIBUTE_NAME = 24;
    public static final int WG_ILLEGAL_ATTRIBUTE_VALUE = 25;
    public static final int WG_EMPTY_SECOND_ARG = 26;
    public static final String BAD_CODE = "BAD_CODE";
    public static final String FORMAT_FAILED = "FORMAT_FAILED";
    public static final String ERROR_STRING = "#error";
    public static final String ERROR_HEADER = "Error: ";
    public static final String WARNING_HEADER = "Warning: ";
    public static final String XSL_HEADER = "XSLT ";
    public static final String XML_HEADER = "XML ";
    public static final String QUERY_HEADER = "PATTERN ";
    
    static {
        contents = new Object[177][2];
        for (int i = 0; i < 109; ++i) {
            XSLTErrorResources.contents[i][0] = getMKey(i);
        }
        for (int j = 1; j < 27; ++j) {
            XSLTErrorResources.contents[j + 108][0] = getWKey(j);
        }
        XSLTErrorResources.contents[0][1] = "{0}";
        XSLTErrorResources.contents[1][1] = "Error: Can not have '{' within expression";
        XSLTErrorResources.contents[2][1] = "{0} has an illegal attribute: {1}";
        XSLTErrorResources.contents[3][1] = "sourceNode is null in xsl:apply-imports!";
        XSLTErrorResources.contents[4][1] = "Can not add {0} to {1}";
        XSLTErrorResources.contents[5][1] = "sourceNode is null in handleApplyTemplatesInstruction!";
        XSLTErrorResources.contents[6][1] = "{0} must have a name attribute.";
        XSLTErrorResources.contents[7][1] = "Could not find template named: {0}";
        XSLTErrorResources.contents[8][1] = "Could not resolve name AVT in xsl:call-template.";
        XSLTErrorResources.contents[9][1] = "{0} requires attribute: {1}";
        XSLTErrorResources.contents[10][1] = "{0} must have a 'test' attribute.";
        XSLTErrorResources.contents[11][1] = "Bad value on level attribute: {0}";
        XSLTErrorResources.contents[12][1] = "processing-instruction name can not be 'xml'";
        XSLTErrorResources.contents[13][1] = "processing-instruction name must be a valid NCName: {0}";
        XSLTErrorResources.contents[14][1] = "{0} must have a match attribute if it has a mode.";
        XSLTErrorResources.contents[15][1] = "{0} requires either a name or a match attribute.";
        XSLTErrorResources.contents[16][1] = "Can not resolve namespace prefix: {0}";
        XSLTErrorResources.contents[17][1] = "xml:space has an illegal value: {0}";
        XSLTErrorResources.contents[18][1] = "Child node does not have an owner document!";
        XSLTErrorResources.contents[19][1] = "ElemTemplateElement error: {0}";
        XSLTErrorResources.contents[20][1] = "Trying to add a null child!";
        XSLTErrorResources.contents[21][1] = "{0} requires a select attribute.";
        XSLTErrorResources.contents[22][1] = "xsl:when must have a 'test' attribute.";
        XSLTErrorResources.contents[23][1] = "xsl:with-param must have a 'name' attribute.";
        XSLTErrorResources.contents[24][1] = "context does not have an owner document!";
        XSLTErrorResources.contents[25][1] = "Could not create XML TransformerFactory Liaison: {0}";
        XSLTErrorResources.contents[26][1] = "Xalan: Process was not successful.";
        XSLTErrorResources.contents[27][1] = "Xalan: was not successful.";
        XSLTErrorResources.contents[28][1] = "Encoding not supported: {0}";
        XSLTErrorResources.contents[29][1] = "Could not create TraceListener: {0}";
        XSLTErrorResources.contents[30][1] = "xsl:key requires a 'name' attribute!";
        XSLTErrorResources.contents[31][1] = "xsl:key requires a 'match' attribute!";
        XSLTErrorResources.contents[32][1] = "xsl:key requires a 'use' attribute!";
        XSLTErrorResources.contents[33][1] = "(StylesheetHandler) {0} requires an 'elements' attribute!";
        XSLTErrorResources.contents[34][1] = "(StylesheetHandler) {0} attribute 'prefix' is missing";
        XSLTErrorResources.contents[35][1] = "Stylesheet URL is bad: {0}";
        XSLTErrorResources.contents[36][1] = "Stylesheet file was not found: {0}";
        XSLTErrorResources.contents[37][1] = "Had IO Exception with stylesheet file: {0}";
        XSLTErrorResources.contents[38][1] = "(StylesheetHandler) Could not find href attribute for {0}";
        XSLTErrorResources.contents[39][1] = "(StylesheetHandler) {0} is directly or indirectly including itself!";
        XSLTErrorResources.contents[40][1] = "StylesheetHandler.processInclude error, {0}";
        XSLTErrorResources.contents[41][1] = "(StylesheetHandler) {0} attribute 'lang' is missing";
        XSLTErrorResources.contents[42][1] = "(StylesheetHandler) misplaced {0} element?? Missing container element 'component'";
        XSLTErrorResources.contents[43][1] = "Can only output to an Element, DocumentFragment, Document, or PrintWriter.";
        XSLTErrorResources.contents[44][1] = "StylesheetRoot.process error";
        XSLTErrorResources.contents[45][1] = "UnImplNode error: {0}";
        XSLTErrorResources.contents[46][1] = "Error! Did not find xpath select expression (-select).";
        XSLTErrorResources.contents[47][1] = "Can not serialize an XSLProcessor!";
        XSLTErrorResources.contents[48][1] = "Stylesheet input was not specified!";
        XSLTErrorResources.contents[49][1] = "Failed to process stylesheet!";
        XSLTErrorResources.contents[50][1] = "Could not parse {0} document!";
        XSLTErrorResources.contents[51][1] = "Could not find fragment: {0}";
        XSLTErrorResources.contents[52][1] = "Node pointed to by fragment identifier was not an element: {0}";
        XSLTErrorResources.contents[53][1] = "for-each must have either a match or name attribute";
        XSLTErrorResources.contents[54][1] = "templates must have either a match or name attribute";
        XSLTErrorResources.contents[55][1] = "No clone of a document fragment!";
        XSLTErrorResources.contents[56][1] = "Can not create item in result tree: {0}";
        XSLTErrorResources.contents[57][1] = "xml:space in the source XML has an illegal value: {0}";
        XSLTErrorResources.contents[58][1] = "There is no xsl:key declaration for {0}!";
        XSLTErrorResources.contents[59][1] = "Error! Cannot create url for: {0}";
        XSLTErrorResources.contents[60][1] = "xsl:functions is unsupported";
        XSLTErrorResources.contents[61][1] = "XSLT TransformerFactory Error";
        XSLTErrorResources.contents[62][1] = "(StylesheetHandler) {0} not allowed inside a stylesheet!";
        XSLTErrorResources.contents[63][1] = "result-ns no longer supported!  Use xsl:output instead.";
        XSLTErrorResources.contents[64][1] = "default-space no longer supported!  Use xsl:strip-space or xsl:preserve-space instead.";
        XSLTErrorResources.contents[65][1] = "indent-result no longer supported!  Use xsl:output instead.";
        XSLTErrorResources.contents[66][1] = "(StylesheetHandler) {0} has an illegal attribute: {1}";
        XSLTErrorResources.contents[67][1] = "Unknown XSL element: {0}";
        XSLTErrorResources.contents[68][1] = "(StylesheetHandler) xsl:sort can only be used with xsl:apply-templates or xsl:for-each.";
        XSLTErrorResources.contents[69][1] = "(StylesheetHandler) misplaced xsl:when!";
        XSLTErrorResources.contents[70][1] = "(StylesheetHandler) xsl:when not parented by xsl:choose!";
        XSLTErrorResources.contents[71][1] = "(StylesheetHandler) misplaced xsl:otherwise!";
        XSLTErrorResources.contents[72][1] = "(StylesheetHandler) xsl:otherwise not parented by xsl:choose!";
        XSLTErrorResources.contents[73][1] = "(StylesheetHandler) {0} is not allowed inside a template!";
        XSLTErrorResources.contents[74][1] = "(StylesheetHandler) {0} extension namespace prefix {1} unknown";
        XSLTErrorResources.contents[75][1] = "(StylesheetHandler) Imports can only occur as the first elements in the stylesheet!";
        XSLTErrorResources.contents[76][1] = "(StylesheetHandler) {0} is directly or indirectly importing itself!";
        XSLTErrorResources.contents[77][1] = "(StylesheetHandler) xml:space has an illegal value: {0}";
        XSLTErrorResources.contents[78][1] = "processStylesheet not succesfull!";
        XSLTErrorResources.contents[79][1] = "SAX Exception";
        XSLTErrorResources.contents[80][1] = "Function not supported!";
        XSLTErrorResources.contents[81][1] = "XSLT Error";
        XSLTErrorResources.contents[82][1] = "currency sign is not allowed in format pattern string";
        XSLTErrorResources.contents[83][1] = "Document function not supported in Stylesheet DOM!";
        XSLTErrorResources.contents[84][1] = "Can't resolve prefix of non-Prefix resolver!";
        XSLTErrorResources.contents[85][1] = "Redirect extension: Could not get filename - file or select attribute must return vald string.";
        XSLTErrorResources.contents[86][1] = "Can not build FormatterListener in Redirect extension!";
        XSLTErrorResources.contents[87][1] = "Prefix in exclude-result-prefixes is not valid: {0}";
        XSLTErrorResources.contents[88][1] = "Missing namespace URI for specified prefix";
        XSLTErrorResources.contents[89][1] = "Missing argument for option: {0}";
        XSLTErrorResources.contents[90][1] = "Invalid option: {0}";
        XSLTErrorResources.contents[91][1] = "Malformed format string: {0}";
        XSLTErrorResources.contents[92][1] = "xsl:stylesheet requires a 'version' attribute!";
        XSLTErrorResources.contents[93][1] = "Attribute: {0} has an illegal value: {1}";
        XSLTErrorResources.contents[94][1] = "xsl:choose requires an xsl:when";
        XSLTErrorResources.contents[95][1] = "xsl:apply-imports not allowed in a xsl:for-each";
        XSLTErrorResources.contents[96][1] = "Cannot use a DTMLiaison for an output DOM node... pass a org.apache.xpath.DOM2Helper instead!";
        XSLTErrorResources.contents[97][1] = "Cannot use a DTMLiaison for a input DOM node... pass a org.apache.xpath.DOM2Helper instead!";
        XSLTErrorResources.contents[98][1] = "Call to extension element failed: {0}";
        XSLTErrorResources.contents[99][1] = "Prefix must resolve to a namespace: {0}";
        XSLTErrorResources.contents[100][1] = "Invalid UTF-16 surrogate detected: {0} ?";
        XSLTErrorResources.contents[101][1] = "xsl:attribute-set {0} used itself, which will cause an infinite loop.";
        XSLTErrorResources.contents[102][1] = "Can not mix non Xerces-DOM input with Xerces-DOM output!";
        XSLTErrorResources.contents[103][1] = "addTraceListenersToStylesheet - TooManyListenersException";
        XSLTErrorResources.contents[104][1] = "In ElemTemplateElement.readObject: {0}";
        XSLTErrorResources.contents[105][1] = "Found more than one template named: {0}";
        XSLTErrorResources.contents[106][1] = "Invalid function call: recursive key() calls are not allowed";
        XSLTErrorResources.contents[107][1] = "Variable {0} is directly or indirectly referencing itself!";
        XSLTErrorResources.contents[108][1] = "The input node can not be null for a DOMSource for newTemplates!";
        XSLTErrorResources.contents[109][1] = "Found '}' but no attribute template open!";
        XSLTErrorResources.contents[110][1] = "Warning: count attribute does not match an ancestor in xsl:number! Target = {0}";
        XSLTErrorResources.contents[111][1] = "Old syntax: The name of the 'expr' attribute has been changed to 'select'.";
        XSLTErrorResources.contents[112][1] = "Xalan doesn't yet handle the locale name in the format-number function.";
        XSLTErrorResources.contents[113][1] = "Warning: Could not find locale for xml:lang={0}";
        XSLTErrorResources.contents[114][1] = "Can not make URL from: {0}";
        XSLTErrorResources.contents[115][1] = "Can not load requested doc: {0}";
        XSLTErrorResources.contents[116][1] = "Could not find Collator for <sort xml:lang={0}";
        XSLTErrorResources.contents[117][1] = "Old syntax: the functions instruction should use a url of {0}";
        XSLTErrorResources.contents[118][1] = "encoding not supported: {0}, using UTF-8";
        XSLTErrorResources.contents[119][1] = "encoding not supported: {0}, using Java {1}";
        XSLTErrorResources.contents[120][1] = "Specificity conflicts found: {0} Last found in stylesheet will be used.";
        XSLTErrorResources.contents[121][1] = "========= Parsing and preparing {0} ==========";
        XSLTErrorResources.contents[122][1] = "Attr Template, {0}";
        XSLTErrorResources.contents[123][1] = "Match conflict between xsl:strip-space and xsl:preserve-space";
        XSLTErrorResources.contents[124][1] = "Xalan does not yet handle the {0} attribute!";
        XSLTErrorResources.contents[125][1] = "No declaration found for decimal format: {0}";
        XSLTErrorResources.contents[126][1] = "Old XSLT Namespace: {0}";
        XSLTErrorResources.contents[127][1] = "Only one default xsl:decimal-format declaration is allowed.";
        XSLTErrorResources.contents[128][1] = "xsl:decimal-format names must be unique. Name \"{0}\" has been duplicated.";
        XSLTErrorResources.contents[129][1] = "{0} has an illegal attribute: {1}";
        XSLTErrorResources.contents[130][1] = "Could not resolve namespace prefix: {0}. The node will be ignored.";
        XSLTErrorResources.contents[131][1] = "xsl:stylesheet requires a 'version' attribute!";
        XSLTErrorResources.contents[132][1] = "Illegal attribute name: {0}";
        XSLTErrorResources.contents[133][1] = "Illegal value used for attribute {0}: {1}";
        XSLTErrorResources.contents[134][1] = "Resulting nodeset from second argument of document function is empty. The first agument will be used.";
        XSLTErrorResources.contents[135][0] = "ui_language";
        XSLTErrorResources.contents[135][1] = "en";
        XSLTErrorResources.contents[136][0] = "help_language";
        XSLTErrorResources.contents[136][1] = "en";
        XSLTErrorResources.contents[137][0] = "language";
        XSLTErrorResources.contents[137][1] = "en";
        XSLTErrorResources.contents[138][0] = "BAD_CODE";
        XSLTErrorResources.contents[138][1] = "Parameter to createMessage was out of bounds";
        XSLTErrorResources.contents[139][0] = "FORMAT_FAILED";
        XSLTErrorResources.contents[139][1] = "Exception thrown during messageFormat call";
        XSLTErrorResources.contents[140][0] = "version";
        XSLTErrorResources.contents[140][1] = ">>>>>>> Xalan Version ";
        XSLTErrorResources.contents[141][0] = "version2";
        XSLTErrorResources.contents[141][1] = "<<<<<<<";
        XSLTErrorResources.contents[142][0] = "yes";
        XSLTErrorResources.contents[142][1] = "yes";
        XSLTErrorResources.contents[143][0] = "line";
        XSLTErrorResources.contents[143][1] = "Line #";
        XSLTErrorResources.contents[144][0] = "column";
        XSLTErrorResources.contents[144][1] = "Column #";
        XSLTErrorResources.contents[145][0] = "xsldone";
        XSLTErrorResources.contents[145][1] = "XSLProcessor: done";
        XSLTErrorResources.contents[146][0] = "xslProc_option";
        XSLTErrorResources.contents[146][1] = "=xslproc options:";
        XSLTErrorResources.contents[147][0] = "optionIN";
        XSLTErrorResources.contents[147][1] = "    -IN inputXMLURL";
        XSLTErrorResources.contents[148][0] = "optionXSL";
        XSLTErrorResources.contents[148][1] = "   [-XSL XSLTransformationURL]";
        XSLTErrorResources.contents[149][0] = "optionOUT";
        XSLTErrorResources.contents[149][1] = "   [-XSL XSLTransformationURL]";
        XSLTErrorResources.contents[150][0] = "optionLXCIN";
        XSLTErrorResources.contents[150][1] = "   [-LXCIN compiledStylesheetFileNameIn]";
        XSLTErrorResources.contents[151][0] = "optionLXCOUT";
        XSLTErrorResources.contents[151][1] = "   [-LXCOUT compiledStylesheetFileNameOutOut]";
        XSLTErrorResources.contents[152][0] = "optionPARSER";
        XSLTErrorResources.contents[152][1] = "   [-PARSER fully qualified class name of parser liaison]";
        XSLTErrorResources.contents[153][0] = "optionE";
        XSLTErrorResources.contents[153][1] = "   [-E (Do not expand entity refs)]";
        XSLTErrorResources.contents[154][0] = "optionV";
        XSLTErrorResources.contents[154][1] = "   [-E (Do not expand entity refs)]";
        XSLTErrorResources.contents[155][0] = "optionQC";
        XSLTErrorResources.contents[155][1] = "   [-QC (Quiet Pattern Conflicts Warnings)]";
        XSLTErrorResources.contents[156][0] = "optionQ";
        XSLTErrorResources.contents[156][1] = "   [-Q  (Quiet Mode)]";
        XSLTErrorResources.contents[157][0] = "optionLF";
        XSLTErrorResources.contents[157][1] = "   [-LF (Use linefeeds only on output {default is CR/LF})]";
        XSLTErrorResources.contents[158][0] = "optionCR";
        XSLTErrorResources.contents[158][1] = "   [-CR (Use carriage returns only on output {default is CR/LF})]";
        XSLTErrorResources.contents[159][0] = "optionESCAPE";
        XSLTErrorResources.contents[159][1] = "   [-ESCAPE (Which characters to escape {default is <>&\"'\\r\\n}]";
        XSLTErrorResources.contents[160][0] = "optionINDENT";
        XSLTErrorResources.contents[160][1] = "   [-INDENT (Control how many spaces to indent {default is 0})]";
        XSLTErrorResources.contents[161][0] = "optionTT";
        XSLTErrorResources.contents[161][1] = "   [-TT (Trace the templates as they are being called.)]";
        XSLTErrorResources.contents[162][0] = "optionTG";
        XSLTErrorResources.contents[162][1] = "   [-TG (Trace each generation event.)]";
        XSLTErrorResources.contents[163][0] = "optionTS";
        XSLTErrorResources.contents[163][1] = "   [-TS (Trace each selection event.)]";
        XSLTErrorResources.contents[164][0] = "optionTTC";
        XSLTErrorResources.contents[164][1] = "   [-TTC (Trace the template children as they are being processed.)]";
        XSLTErrorResources.contents[165][0] = "optionTCLASS";
        XSLTErrorResources.contents[165][1] = "   [-TCLASS (TraceListener class for trace extensions.)]";
        XSLTErrorResources.contents[166][0] = "optionVALIDATE";
        XSLTErrorResources.contents[166][1] = "   [-VALIDATE (Set whether validation occurs.  Validation is off by default.)]";
        XSLTErrorResources.contents[167][0] = "optionEDUMP";
        XSLTErrorResources.contents[167][1] = "   [-EDUMP {optional filename} (Do stackdump on error.)]";
        XSLTErrorResources.contents[168][0] = "optionXML";
        XSLTErrorResources.contents[168][1] = "   [-XML (Use XML formatter and add XML header.)]";
        XSLTErrorResources.contents[169][0] = "optionTEXT";
        XSLTErrorResources.contents[169][1] = "   [-TEXT (Use simple Text formatter.)]";
        XSLTErrorResources.contents[170][0] = "optionHTML";
        XSLTErrorResources.contents[170][1] = "   [-HTML (Use HTML formatter.)]";
        XSLTErrorResources.contents[171][0] = "optionPARAM";
        XSLTErrorResources.contents[171][1] = "   [-PARAM name expression (Set a stylesheet parameter)]";
        XSLTErrorResources.contents[172][0] = "noParsermsg1";
        XSLTErrorResources.contents[172][1] = "XSL Process was not successful.";
        XSLTErrorResources.contents[173][0] = "noParsermsg2";
        XSLTErrorResources.contents[173][1] = "** Could not find parser **";
        XSLTErrorResources.contents[174][0] = "noParsermsg3";
        XSLTErrorResources.contents[174][1] = "Please check your classpath.";
        XSLTErrorResources.contents[175][0] = "noParsermsg4";
        XSLTErrorResources.contents[175][1] = "If you don't have IBM's XML Parser for Java, you can download it from";
        XSLTErrorResources.contents[176][0] = "noParsermsg5";
        XSLTErrorResources.contents[176][1] = "IBM's AlphaWorks: http://www.alphaworks.ibm.com/formula/xml";
    }
    
    public Object[][] getContents() {
        return XSLTErrorResources.contents;
    }
    
    public static String getMKey(final int errorCode) {
        if (errorCode > 108) {
            return null;
        }
        final DecimalFormat df = new DecimalFormat("0000");
        return "ER" + df.format(errorCode);
    }
    
    public String getMessageKey(final int errorCode) {
        if (errorCode > 108) {
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
        if (errorCode > 26) {
            return null;
        }
        final DecimalFormat df = new DecimalFormat("0000");
        return "WR" + df.format(errorCode);
    }
    
    public String getWarningKey(final int errorCode) {
        if (errorCode > 26) {
            return null;
        }
        final DecimalFormat df = new DecimalFormat("0000");
        return "WR" + df.format(errorCode);
    }
    
    public static final XSLTErrorResources loadResourceBundle(final String className) throws MissingResourceException {
        final Locale locale = Locale.getDefault();
        final String suffix = getResourceSuffix(locale);
        try {
            return (XSLTErrorResources)ResourceBundle.getBundle(String.valueOf(className) + suffix, locale);
        }
        catch (MissingResourceException ex) {
            try {
                return (XSLTErrorResources)ResourceBundle.getBundle(className, new Locale("en", "US"));
            }
            catch (MissingResourceException ex2) {
                throw new MissingResourceException("Could not load any resource bundles.", className, "");
            }
        }
    }
}
