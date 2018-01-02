// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.ListResourceBundle;

public class XPATHErrorResources_sk extends ListResourceBundle
{
    public static final int MAX_CODE = 108;
    public static final int MAX_WARNING = 11;
    public static final int MAX_OTHERS = 20;
    public static final int MAX_MESSAGES = 120;
    public static final String ERROR0000 = "ERROR0000";
    public static final String ER_CURRENT_NOT_ALLOWED_IN_MATCH = "ER_CURRENT_NOT_ALLOWED_IN_MATCH";
    public static final String ER_CURRENT_TAKES_NO_ARGS = "ER_CURRENT_TAKES_NO_ARGS";
    public static final String ER_DOCUMENT_REPLACED = "ER_DOCUMENT_REPLACED";
    public static final String ER_CONTEXT_HAS_NO_OWNERDOC = "ER_CONTEXT_HAS_NO_OWNERDOC";
    public static final String ER_LOCALNAME_HAS_TOO_MANY_ARGS = "ER_LOCALNAME_HAS_TOO_MANY_ARGS";
    public static final String ER_NAMESPACEURI_HAS_TOO_MANY_ARGS = "ER_NAMESPACEURI_HAS_TOO_MANY_ARGS";
    public static final String ER_NORMALIZESPACE_HAS_TOO_MANY_ARGS = "ER_NORMALIZESPACE_HAS_TOO_MANY_ARGS";
    public static final String ER_NUMBER_HAS_TOO_MANY_ARGS = "ER_NUMBER_HAS_TOO_MANY_ARGS";
    public static final String ER_NAME_HAS_TOO_MANY_ARGS = "ER_NAME_HAS_TOO_MANY_ARGS";
    public static final String ER_STRING_HAS_TOO_MANY_ARGS = "ER_STRING_HAS_TOO_MANY_ARGS";
    public static final String ER_STRINGLENGTH_HAS_TOO_MANY_ARGS = "ER_STRINGLENGTH_HAS_TOO_MANY_ARGS";
    public static final String ER_TRANSLATE_TAKES_3_ARGS = "ER_TRANSLATE_TAKES_3_ARGS";
    public static final String ER_UNPARSEDENTITYURI_TAKES_1_ARG = "ER_UNPARSEDENTITYURI_TAKES_1_ARG";
    public static final String ER_NAMESPACEAXIS_NOT_IMPLEMENTED = "ER_NAMESPACEAXIS_NOT_IMPLEMENTED";
    public static final String ER_UNKNOWN_AXIS = "ER_UNKNOWN_AXIS";
    public static final String ER_UNKNOWN_MATCH_OPERATION = "ER_UNKNOWN_MATCH_OPERATION";
    public static final String ER_INCORRECT_ARG_LENGTH = "ER_INCORRECT_ARG_LENGTH";
    public static final String ER_CANT_CONVERT_TO_NUMBER = "ER_CANT_CONVERT_TO_NUMBER";
    public static final String ER_CANT_CONVERT_TO_NODELIST = "ER_CANT_CONVERT_TO_NODELIST";
    public static final String ER_CANT_CONVERT_TO_MUTABLENODELIST = "ER_CANT_CONVERT_TO_MUTABLENODELIST";
    public static final String ER_CANT_CONVERT_TO_TYPE = "ER_CANT_CONVERT_TO_TYPE";
    public static final String ER_EXPECTED_MATCH_PATTERN = "ER_EXPECTED_MATCH_PATTERN";
    public static final String ER_COULDNOT_GET_VAR_NAMED = "ER_COULDNOT_GET_VAR_NAMED";
    public static final String ER_UNKNOWN_OPCODE = "ER_UNKNOWN_OPCODE";
    public static final String ER_EXTRA_ILLEGAL_TOKENS = "ER_EXTRA_ILLEGAL_TOKENS";
    public static final String ER_EXPECTED_DOUBLE_QUOTE = "ER_EXPECTED_DOUBLE_QUOTE";
    public static final String ER_EXPECTED_SINGLE_QUOTE = "ER_EXPECTED_SINGLE_QUOTE";
    public static final String ER_EMPTY_EXPRESSION = "ER_EMPTY_EXPRESSION";
    public static final String ER_EXPECTED_BUT_FOUND = "ER_EXPECTED_BUT_FOUND";
    public static final String ER_INCORRECT_PROGRAMMER_ASSERTION = "ER_INCORRECT_PROGRAMMER_ASSERTION";
    public static final String ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL = "ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL";
    public static final String ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG = "ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG";
    public static final String ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG = "ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG";
    public static final String ER_PREDICATE_ILLEGAL_SYNTAX = "ER_PREDICATE_ILLEGAL_SYNTAX";
    public static final String ER_ILLEGAL_AXIS_NAME = "ER_ILLEGAL_AXIS_NAME";
    public static final String ER_UNKNOWN_NODETYPE = "ER_UNKNOWN_NODETYPE";
    public static final String ER_PATTERN_LITERAL_NEEDS_BE_QUOTED = "ER_PATTERN_LITERAL_NEEDS_BE_QUOTED";
    public static final String ER_COULDNOT_BE_FORMATTED_TO_NUMBER = "ER_COULDNOT_BE_FORMATTED_TO_NUMBER";
    public static final String ER_COULDNOT_CREATE_XMLPROCESSORLIAISON = "ER_COULDNOT_CREATE_XMLPROCESSORLIAISON";
    public static final String ER_DIDNOT_FIND_XPATH_SELECT_EXP = "ER_DIDNOT_FIND_XPATH_SELECT_EXP";
    public static final String ER_COULDNOT_FIND_ENDOP_AFTER_OPLOCATIONPATH = "ER_COULDNOT_FIND_ENDOP_AFTER_OPLOCATIONPATH";
    public static final String ER_ERROR_OCCURED = "ER_ERROR_OCCURED";
    public static final String ER_ILLEGAL_VARIABLE_REFERENCE = "ER_ILLEGAL_VARIABLE_REFERENCE";
    public static final String ER_AXES_NOT_ALLOWED = "ER_AXES_NOT_ALLOWED";
    public static final String ER_KEY_HAS_TOO_MANY_ARGS = "ER_KEY_HAS_TOO_MANY_ARGS";
    public static final String ER_COUNT_TAKES_1_ARG = "ER_COUNT_TAKES_1_ARG";
    public static final String ER_COULDNOT_FIND_FUNCTION = "ER_COULDNOT_FIND_FUNCTION";
    public static final String ER_UNSUPPORTED_ENCODING = "ER_UNSUPPORTED_ENCODING";
    public static final String ER_PROBLEM_IN_DTM_NEXTSIBLING = "ER_PROBLEM_IN_DTM_NEXTSIBLING";
    public static final String ER_CANNOT_WRITE_TO_EMPTYNODELISTIMPL = "ER_CANNOT_WRITE_TO_EMPTYNODELISTIMPL";
    public static final String ER_SETDOMFACTORY_NOT_SUPPORTED = "ER_SETDOMFACTORY_NOT_SUPPORTED";
    public static final String ER_PREFIX_MUST_RESOLVE = "ER_PREFIX_MUST_RESOLVE";
    public static final String ER_PARSE_NOT_SUPPORTED = "ER_PARSE_NOT_SUPPORTED";
    public static final String ER_SAX_API_NOT_HANDLED = "ER_SAX_API_NOT_HANDLED";
    public static final String ER_IGNORABLE_WHITESPACE_NOT_HANDLED = "ER_IGNORABLE_WHITESPACE_NOT_HANDLED";
    public static final String ER_DTM_CANNOT_HANDLE_NODES = "ER_DTM_CANNOT_HANDLE_NODES";
    public static final String ER_XERCES_CANNOT_HANDLE_NODES = "ER_XERCES_CANNOT_HANDLE_NODES";
    public static final String ER_XERCES_PARSE_ERROR_DETAILS = "ER_XERCES_PARSE_ERROR_DETAILS";
    public static final String ER_XERCES_PARSE_ERROR = "ER_XERCES_PARSE_ERROR";
    public static final String ER_INVALID_UTF16_SURROGATE = "ER_INVALID_UTF16_SURROGATE";
    public static final String ER_OIERROR = "ER_OIERROR";
    public static final String ER_CANNOT_CREATE_URL = "ER_CANNOT_CREATE_URL";
    public static final String ER_XPATH_READOBJECT = "ER_XPATH_READOBJECT";
    public static final String ER_FUNCTION_TOKEN_NOT_FOUND = "ER_FUNCTION_TOKEN_NOT_FOUND";
    public static final String ER_CANNOT_DEAL_XPATH_TYPE = "ER_CANNOT_DEAL_XPATH_TYPE";
    public static final String ER_NODESET_NOT_MUTABLE = "ER_NODESET_NOT_MUTABLE";
    public static final String ER_NODESETDTM_NOT_MUTABLE = "ER_NODESETDTM_NOT_MUTABLE";
    public static final String ER_VAR_NOT_RESOLVABLE = "ER_VAR_NOT_RESOLVABLE";
    public static final String ER_NULL_ERROR_HANDLER = "ER_NULL_ERROR_HANDLER";
    public static final String ER_PROG_ASSERT_UNKNOWN_OPCODE = "ER_PROG_ASSERT_UNKNOWN_OPCODE";
    public static final String ER_ZERO_OR_ONE = "ER_ZERO_OR_ONE";
    public static final String ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER = "ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER";
    public static final String ER_ASNODEITERATOR_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER = "ER_ASNODEITERATOR_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER";
    public static final String ER_FSB_NOT_SUPPORTED_XSTRINGFORCHARS = "ER_FSB_NOT_SUPPORTED_XSTRINGFORCHARS";
    public static final String ER_COULD_NOT_FIND_VAR = "ER_COULD_NOT_FIND_VAR";
    public static final String ER_XSTRINGFORCHARS_CANNOT_TAKE_STRING = "ER_XSTRINGFORCHARS_CANNOT_TAKE_STRING";
    public static final String ER_FASTSTRINGBUFFER_CANNOT_BE_NULL = "ER_FASTSTRINGBUFFER_CANNOT_BE_NULL";
    public static final String ER_TWO_OR_THREE = "ER_TWO_OR_THREE";
    public static final String ER_VARIABLE_ACCESSED_BEFORE_BIND = "ER_VARIABLE_ACCESSED_BEFORE_BIND";
    public static final String ER_FSB_CANNOT_TAKE_STRING = "ER_FSB_CANNOT_TAKE_STRING";
    public static final String ER_SETTING_WALKER_ROOT_TO_NULL = "ER_SETTING_WALKER_ROOT_TO_NULL";
    public static final String ER_NODESETDTM_CANNOT_ITERATE = "ER_NODESETDTM_CANNOT_ITERATE";
    public static final String ER_NODESET_CANNOT_ITERATE = "ER_NODESET_CANNOT_ITERATE";
    public static final String ER_NODESETDTM_CANNOT_INDEX = "ER_NODESETDTM_CANNOT_INDEX";
    public static final String ER_NODESET_CANNOT_INDEX = "ER_NODESET_CANNOT_INDEX";
    public static final String ER_CANNOT_CALL_SETSHOULDCACHENODE = "ER_CANNOT_CALL_SETSHOULDCACHENODE";
    public static final String ER_ONLY_ALLOWS = "ER_ONLY_ALLOWS";
    public static final String ER_UNKNOWN_STEP = "ER_UNKNOWN_STEP";
    public static final String ER_EXPECTED_REL_LOC_PATH = "ER_EXPECTED_REL_LOC_PATH";
    public static final String ER_EXPECTED_LOC_PATH = "ER_EXPECTED_LOC_PATH";
    public static final String ER_EXPECTED_LOC_STEP = "ER_EXPECTED_LOC_STEP";
    public static final String ER_EXPECTED_NODE_TEST = "ER_EXPECTED_NODE_TEST";
    public static final String ER_EXPECTED_STEP_PATTERN = "ER_EXPECTED_STEP_PATTERN";
    public static final String ER_EXPECTED_REL_PATH_PATTERN = "ER_EXPECTED_REL_PATH_PATTERN";
    public static final String ER_CANT_CONVERT_TO_BOOLEAN = "ER_CANT_CONVERT_TO_BOOLEAN";
    public static final String ER_CANT_CONVERT_TO_SINGLENODE = "ER_CANT_CONVERT_TO_SINGLENODE";
    public static final String ER_CANT_GET_SNAPSHOT_LENGTH = "ER_CANT_GET_SNAPSHOT_LENGTH";
    public static final String ER_NON_ITERATOR_TYPE = "ER_NON_ITERATOR_TYPE";
    public static final String ER_DOC_MUTATED = "ER_DOC_MUTATED";
    public static final String ER_INVALID_XPATH_TYPE = "ER_INVALID_XPATH_TYPE";
    public static final String ER_EMPTY_XPATH_RESULT = "ER_EMPTY_XPATH_RESULT";
    public static final String ER_INCOMPATIBLE_TYPES = "ER_INCOMPATIBLE_TYPES";
    public static final String ER_NULL_RESOLVER = "ER_NULL_RESOLVER";
    public static final String ER_CANT_CONVERT_TO_STRING = "ER_CANT_CONVERT_TO_STRING";
    public static final String ER_NON_SNAPSHOT_TYPE = "ER_NON_SNAPSHOT_TYPE";
    public static final String ER_WRONG_DOCUMENT = "ER_WRONG_DOCUMENT";
    public static final String ER_WRONG_NODETYPE = "ER_WRONG_NODETYPE";
    public static final String ER_XPATH_ERROR = "ER_XPATH_ERROR";
    public static final String WG_LOCALE_NAME_NOT_HANDLED = "WG_LOCALE_NAME_NOT_HANDLED";
    public static final String WG_PROPERTY_NOT_SUPPORTED = "WG_PROPERTY_NOT_SUPPORTED";
    public static final String WG_DONT_DO_ANYTHING_WITH_NS = "WG_DONT_DO_ANYTHING_WITH_NS";
    public static final String WG_SECURITY_EXCEPTION = "WG_SECURITY_EXCEPTION";
    public static final String WG_QUO_NO_LONGER_DEFINED = "WG_QUO_NO_LONGER_DEFINED";
    public static final String WG_NEED_DERIVED_OBJECT_TO_IMPLEMENT_NODETEST = "WG_NEED_DERIVED_OBJECT_TO_IMPLEMENT_NODETEST";
    public static final String WG_FUNCTION_TOKEN_NOT_FOUND = "WG_FUNCTION_TOKEN_NOT_FOUND";
    public static final String WG_COULDNOT_FIND_FUNCTION = "WG_COULDNOT_FIND_FUNCTION";
    public static final String WG_CANNOT_MAKE_URL_FROM = "WG_CANNOT_MAKE_URL_FROM";
    public static final String WG_EXPAND_ENTITIES_NOT_SUPPORTED = "WG_EXPAND_ENTITIES_NOT_SUPPORTED";
    public static final String WG_ILLEGAL_VARIABLE_REFERENCE = "WG_ILLEGAL_VARIABLE_REFERENCE";
    public static final String WG_UNSUPPORTED_ENCODING = "WG_UNSUPPORTED_ENCODING";
    public static final Object[][] contents;
    public static final String BAD_CODE = "BAD_CODE";
    public static final String FORMAT_FAILED = "FORMAT_FAILED";
    public static final String ERROR_RESOURCES = "org.apache.xpath.res.XPATHErrorResources";
    public static final String ERROR_STRING = "#error";
    public static final String ERROR_HEADER = "Chyba: ";
    public static final String WARNING_HEADER = "Upozornenie: ";
    public static final String XSL_HEADER = "XSL ";
    public static final String XML_HEADER = "XML ";
    public static final String QUERY_HEADER = "PATTERN ";
    
    public Object[][] getContents() {
        return XPATHErrorResources_sk.contents;
    }
    
    public static final XPATHErrorResources loadResourceBundle(final String className) throws MissingResourceException {
        final Locale locale = Locale.getDefault();
        final String suffix = getResourceSuffix(locale);
        try {
            return (XPATHErrorResources)ResourceBundle.getBundle(className + suffix, locale);
        }
        catch (MissingResourceException e) {
            try {
                return (XPATHErrorResources)ResourceBundle.getBundle(className, new Locale("en", "US"));
            }
            catch (MissingResourceException e2) {
                throw new MissingResourceException("Could not load any resource bundles.", className, "");
            }
        }
    }
    
    private static final String getResourceSuffix(final Locale locale) {
        String suffix = "_" + locale.getLanguage();
        final String country = locale.getCountry();
        if (country.equals("TW")) {
            suffix = suffix + "_" + country;
        }
        return suffix;
    }
    
    static {
        contents = new Object[][] { { "ERROR0000", "{0}" }, { "ER_CURRENT_NOT_ALLOWED_IN_MATCH", "Funkcia current () nie je povolen\u00e1 v porovn\u00e1vacom vzore!" }, { "ER_CURRENT_TAKES_NO_ARGS", "Funkcia current () nepr\u00edjma argumenty!" }, { "ER_DOCUMENT_REPLACED", "Implement\u00e1cia funkcie document() bola nahraden\u00e1 org.apache.xalan.xslt.FuncDocument!" }, { "ER_CONTEXT_HAS_NO_OWNERDOC", "kontext nem\u00e1 dokument vlastn\u00edka!" }, { "ER_LOCALNAME_HAS_TOO_MANY_ARGS", "local-name() m\u00e1 prive\u013ea argumentov." }, { "ER_NAMESPACEURI_HAS_TOO_MANY_ARGS", "namespace-uri() m\u00e1 prive\u013ea argumentov." }, { "ER_NORMALIZESPACE_HAS_TOO_MANY_ARGS", "normalize-space() m\u00e1 prive\u013ea argumentov." }, { "ER_NUMBER_HAS_TOO_MANY_ARGS", "number() m\u00e1 prive\u013ea argumentov." }, { "ER_NAME_HAS_TOO_MANY_ARGS", "name() m\u00e1 prive\u013ea argumentov." }, { "ER_STRING_HAS_TOO_MANY_ARGS", "string() m\u00e1 prive\u013ea argumentov" }, { "ER_STRINGLENGTH_HAS_TOO_MANY_ARGS", "string-length() m\u00e1 prive\u013ea argumentov" }, { "ER_TRANSLATE_TAKES_3_ARGS", "Funkcia translate() pr\u00edjma tri argumenty!" }, { "ER_UNPARSEDENTITYURI_TAKES_1_ARG", "Funkcia unparsed-entity-uri by mala prija\u0165 jeden argument!" }, { "ER_NAMESPACEAXIS_NOT_IMPLEMENTED", "osi n\u00e1zvov\u00fdch priestorov e\u0161te nie s\u00fa implementovan\u00e9!" }, { "ER_UNKNOWN_AXIS", "nezn\u00e1ma os: {0}" }, { "ER_UNKNOWN_MATCH_OPERATION", "nezn\u00e1ma porovn\u00e1vacia oper\u00e1cia!" }, { "ER_INCORRECT_ARG_LENGTH", "Testovanie uzla arg length of processing-instruction() je nespr\u00e1vne!" }, { "ER_CANT_CONVERT_TO_NUMBER", "Nie je mo\u017en\u00e9 konvertova\u0165 {0} na \u010d\u00edslo" }, { "ER_CANT_CONVERT_TO_NODELIST", "Nie je mo\u017en\u00e9 konvertova\u0165 {0} na NodeList!" }, { "ER_CANT_CONVERT_TO_MUTABLENODELIST", "Nie je mo\u017en\u00e9 konvertova\u0165 {0} na NodeSetDTM!" }, { "ER_CANT_CONVERT_TO_TYPE", "Nie je mo\u017en\u00e1 konverzia {0} na typ#{1}" }, { "ER_EXPECTED_MATCH_PATTERN", "O\u010dak\u00e1van\u00fd porovn\u00e1vac\u00ed vzor v getMatchScore!" }, { "ER_COULDNOT_GET_VAR_NAMED", "Nie je mo\u017en\u00e9 dosiahnu\u0165 premenn\u00fa s n\u00e1zvom {0}" }, { "ER_UNKNOWN_OPCODE", "CHYBA! Nezn\u00e1my k\u00f3d op: {0}" }, { "ER_EXTRA_ILLEGAL_TOKENS", "Nadbyto\u010dn\u00e9 neplatn\u00e9 symboly: {0}" }, { "ER_EXPECTED_DOUBLE_QUOTE", "Nespr\u00e1vny liter\u00e1l... o\u010dak\u00e1van\u00e1 dvojit\u00e1 cit\u00e1cia!" }, { "ER_EXPECTED_SINGLE_QUOTE", "Nespr\u00e1vny liter\u00e1l... o\u010dak\u00e1van\u00e1 jedin\u00e1 cit\u00e1cia!" }, { "ER_EMPTY_EXPRESSION", "Pr\u00e1zdny v\u00fdraz!" }, { "ER_EXPECTED_BUT_FOUND", "O\u010dak\u00e1vala sa {0}, ale bola n\u00e1jden\u00e1: {1}" }, { "ER_INCORRECT_PROGRAMMER_ASSERTION", "Program\u00e1torsk\u00e9 vyjadrenie je nespr\u00e1vne! - {0}" }, { "ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL", "argument boolean(...) u\u017e nie je volite\u013en\u00fd s konceptom 19990709 XPath." }, { "ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG", "N\u00e1jdene ',' ale \u017eiaden predch\u00e1dzaj\u00faci argument!" }, { "ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG", "N\u00e1jden\u00e9 ',' ale \u017eiaden nasleduj\u00faci argument!" }, { "ER_PREDICATE_ILLEGAL_SYNTAX", "'..[predicate]' alebo '.[predicate]' je nespr\u00e1vna syntax.  Pou\u017eite namiesto toho 'self::node()[predicate]'." }, { "ER_ILLEGAL_AXIS_NAME", "Neplatn\u00fd n\u00e1zov osi: {0}" }, { "ER_UNKNOWN_NODETYPE", "Nezn\u00e1my typ uzla: {0}" }, { "ER_PATTERN_LITERAL_NEEDS_BE_QUOTED", "Vzorov\u00fd liter\u00e1l ({0}) potrebuje by\u0165 citovan\u00fd!" }, { "ER_COULDNOT_BE_FORMATTED_TO_NUMBER", "{0} nem\u00f4\u017ee by\u0165 form\u00e1tovan\u00e9 na \u010d\u00edslo!" }, { "ER_COULDNOT_CREATE_XMLPROCESSORLIAISON", "Nebolo mo\u017en\u00e9 vytvori\u0165 vz\u0165ah XML TransformerFactory: {0}" }, { "ER_DIDNOT_FIND_XPATH_SELECT_EXP", "Chyba! Nena\u0161iel sa v\u00fdraz v\u00fdberu xpath (-select)." }, { "ER_COULDNOT_FIND_ENDOP_AFTER_OPLOCATIONPATH", "CHYBA! Nebolo mo\u017en\u00e9 n\u00e1js\u0165 ENDOP po OP_LOCATIONPATH" }, { "ER_ERROR_OCCURED", "Vyskytla sa chyba!" }, { "ER_ILLEGAL_VARIABLE_REFERENCE", "VariableReference bol dan\u00fd pre premenn\u00fa mimo kontext, alebo bez defin\u00edcie!  N\u00e1zov = {0}" }, { "ER_AXES_NOT_ALLOWED", "Len potomok:: atrib\u00fat:: osi s\u00fa povolen\u00e9 v zhodn\u00fdch vzoroch!  Chybn\u00e9 osi = {0}" }, { "ER_KEY_HAS_TOO_MANY_ARGS", "key() m\u00e1 nespr\u00e1vny po\u010det argumentov." }, { "ER_COUNT_TAKES_1_ARG", "Funkcia count by mala prija\u0165 jeden argument!" }, { "ER_COULDNOT_FIND_FUNCTION", "Nebolo mo\u017en\u00e9 n\u00e1js\u0165 funkciu: {0}" }, { "ER_UNSUPPORTED_ENCODING", "Nepodporovan\u00e9 k\u00f3dovanie: {0}" }, { "ER_PROBLEM_IN_DTM_NEXTSIBLING", "Vyskytol sa probl\u00e9m v DTM v getNextSibling... pokus o obnovu" }, { "ER_CANNOT_WRITE_TO_EMPTYNODELISTIMPL", "Chyba program\u00e1tora: EmptyNodeList nebolo mo\u017en\u00e9 zap\u00edsa\u0165." }, { "ER_SETDOMFACTORY_NOT_SUPPORTED", "setDOMFactory nie je podporovan\u00e9 XPathContext!" }, { "ER_PREFIX_MUST_RESOLVE", "Predpona sa mus\u00ed rozl\u00ed\u0161i\u0165 do n\u00e1zvov\u00e9ho priestoru: {0}" }, { "ER_PARSE_NOT_SUPPORTED", "anal\u00fdza (InputSource source) nie je podporovan\u00e1 XPathContext! Nie je mo\u017en\u00e9 otvori\u0165 {0}" }, { "ER_SAX_API_NOT_HANDLED", "SAX API znaky(char ch[]... nie s\u00fa spracovan\u00e9 DTM!" }, { "ER_IGNORABLE_WHITESPACE_NOT_HANDLED", "ignorableWhitespace(char ch[]... nie s\u00fa spracovan\u00e9 DTM!" }, { "ER_DTM_CANNOT_HANDLE_NODES", "DTMLiaison nem\u00f4\u017ee spracova\u0165 uzly typu {0}" }, { "ER_XERCES_CANNOT_HANDLE_NODES", "DOM2Helper nem\u00f4\u017ee spracova\u0165 uzly typu {0}" }, { "ER_XERCES_PARSE_ERROR_DETAILS", "Chyba DOM2Helper.parse: SystemID - {0} riadok - {1}" }, { "ER_XERCES_PARSE_ERROR", "chyba DOM2Helper.parse" }, { "ER_INVALID_UTF16_SURROGATE", "Bolo zisten\u00e9 neplatn\u00e9 nahradenie UTF-16: {0} ?" }, { "ER_OIERROR", "chyba IO" }, { "ER_CANNOT_CREATE_URL", "Nie je mo\u017en\u00e9 vytvori\u0165 url pre: {0}" }, { "ER_XPATH_READOBJECT", "V XPath.readObject: {0}" }, { "ER_FUNCTION_TOKEN_NOT_FOUND", "nebol n\u00e1jden\u00fd symbol funkcie." }, { "ER_CANNOT_DEAL_XPATH_TYPE", "Nie je mo\u017en\u00e9 pracova\u0165 s typom XPath: {0}" }, { "ER_NODESET_NOT_MUTABLE", "Tento NodeSet je nest\u00e1ly" }, { "ER_NODESETDTM_NOT_MUTABLE", "Tento NodeSetDTM nie je nest\u00e1ly" }, { "ER_VAR_NOT_RESOLVABLE", "Premenn\u00fa nie je mo\u017en\u00e9 rozl\u00ed\u0161i\u0165: {0}" }, { "ER_NULL_ERROR_HANDLER", "Nulov\u00fd chybov\u00fd manipula\u010dn\u00fd program" }, { "ER_PROG_ASSERT_UNKNOWN_OPCODE", "Tvrdenie program\u00e1tora: nezn\u00e1my opera\u010dn\u00fd k\u00f3d: {0}" }, { "ER_ZERO_OR_ONE", "0 alebo 1" }, { "ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", "rtf() nie je podporovan\u00fd XRTreeFragSelectWrapper" }, { "ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", "asNodeIterator() nie je podporovan\u00fd XRTreeFragSelectWrapper" }, { "ER_FSB_NOT_SUPPORTED_XSTRINGFORCHARS", "fsb() nie je podporovan\u00fd pre XStringForChars" }, { "ER_COULD_NOT_FIND_VAR", "Nebolo mo\u017en\u00e9 n\u00e1js\u0165 premenn\u00fa s n\u00e1zvom {0}" }, { "ER_XSTRINGFORCHARS_CANNOT_TAKE_STRING", "XStringForChars nem\u00f4\u017ee ako argument prija\u0165 re\u0165azec" }, { "ER_FASTSTRINGBUFFER_CANNOT_BE_NULL", "Argument FastStringBuffer nem\u00f4\u017ee by\u0165 null" }, { "ER_TWO_OR_THREE", "2 alebo 3" }, { "ER_VARIABLE_ACCESSED_BEFORE_BIND", "Premenn\u00e1 bola z\u00edskan\u00e1 sk\u00f4r, ne\u017e bola viazan\u00e1!" }, { "ER_FSB_CANNOT_TAKE_STRING", "XStringForFSB nem\u00f4\u017ee pova\u017eova\u0165 re\u0165azec za argument!" }, { "ER_SETTING_WALKER_ROOT_TO_NULL", "\n !!!! Chyba! Nastavenie root of a walker na null!!!" }, { "ER_NODESETDTM_CANNOT_ITERATE", "Tento NodeSetDTM sa nem\u00f4\u017ee iterova\u0165 na predch\u00e1dzaj\u00faci uzol!" }, { "ER_NODESET_CANNOT_ITERATE", "Tento NodeSet sa nem\u00f4\u017ee iterova\u0165 na predch\u00e1dzaj\u00faci uzol!" }, { "ER_NODESETDTM_CANNOT_INDEX", "Tento NodeSetDTM nem\u00f4\u017ee vykon\u00e1va\u0165 funkcie indexovania alebo po\u010d\u00edtania!" }, { "ER_NODESET_CANNOT_INDEX", "Tento NodeSet nem\u00f4\u017ee vykon\u00e1va\u0165 funkcie indexovania alebo po\u010d\u00edtania!" }, { "ER_CANNOT_CALL_SETSHOULDCACHENODE", "Nie je mo\u017en\u00e9 vola\u0165 setShouldCacheNodes po volan\u00ed nextNode!" }, { "ER_ONLY_ALLOWS", "{0} povo\u013euje iba {1} argumentov" }, { "ER_UNKNOWN_STEP", "Tvrdenie program\u00e1tora v getNextStepPos: nezn\u00e1me stepType: {0}" }, { "ER_EXPECTED_REL_LOC_PATH", "Po symbole '/' alebo '//' sa o\u010dak\u00e1vala cesta relat\u00edvneho umiestnenia." }, { "ER_EXPECTED_LOC_PATH", "O\u010dak\u00e1vala sa cesta umiestnenia, ale na\u0161iel sa tento symbol : {0}" }, { "ER_EXPECTED_LOC_STEP", "Po symbole '/' alebo '//' sa o\u010dak\u00e1val krok umiestnenia." }, { "ER_EXPECTED_NODE_TEST", "O\u010dak\u00e1val sa test uzlov, ktor\u00fd sa zhoduje bu\u010f s NCName:* alebo s QName." }, { "ER_EXPECTED_STEP_PATTERN", "O\u010dak\u00e1val sa vzor kroku, ale bol zaznamenan\u00fd '/'." }, { "ER_EXPECTED_REL_PATH_PATTERN", "O\u010dak\u00e1val sa vzor relat\u00edvnej cesty." }, { "ER_CANT_CONVERT_TO_BOOLEAN", "Nie je mo\u017en\u00e1 konverzia {0} na boolovsk\u00fd." }, { "ER_CANT_CONVERT_TO_SINGLENODE", "Nie je mo\u017en\u00e1 konverzia {0} na jeden uzol. Tento sa sk\u00f4r aplikuje na typy ANY_UNORDERED_NODE_TYPE a FIRST_ORDERED_NODE_TYPE." }, { "ER_CANT_GET_SNAPSHOT_LENGTH", "Nie je mo\u017en\u00e9 z\u00edska\u0165 d\u013a\u017eku sn\u00edmky na type: {0}. T\u00e1to sa sk\u00f4r aplikuje na typy UNORDERED_NODE_SNAPSHOT_TYPE a ORDERED_NODE_SNAPSHOT_TYPE." }, { "ER_NON_ITERATOR_TYPE", "Nie je mo\u017en\u00e9 iterova\u0165 cez neiter\u00e1torsk\u00fd typ: {0}" }, { "ER_DOC_MUTATED", "Dokument sa od vr\u00e1tenia v\u00fdsledku zmenil. Iter\u00e1tor je neplatn\u00fd." }, { "ER_INVALID_XPATH_TYPE", "Neplatn\u00fd argument typu XPath: {0}" }, { "ER_EMPTY_XPATH_RESULT", "Pr\u00e1zdny objekt v\u00fdsledku XPath" }, { "ER_INCOMPATIBLE_TYPES", "Vr\u00e1ten\u00fd typ: {0} nemo\u017eno prin\u00fati\u0165 do uveden\u00e9ho typu: {1}" }, { "ER_NULL_RESOLVER", "Nie je mo\u017en\u00e9 rozl\u00ed\u0161i\u0165 predponu s rozli\u0161ova\u010dom nulovej predpony." }, { "ER_CANT_CONVERT_TO_STRING", "Nie je mo\u017en\u00e1 konverzia {0} na re\u0165azec." }, { "ER_NON_SNAPSHOT_TYPE", "Nie je mo\u017en\u00e9 vola\u0165 snapshotItem na type: {0}. T\u00e1to met\u00f3da sa aplikuje na typy UNORDERED_NODE_SNAPSHOT_TYPE a ORDERED_NODE_SNAPSHOT_TYPE." }, { "ER_WRONG_DOCUMENT", "Uzol kontextu nepatr\u00ed k dokumentu, ktor\u00fd je viazan\u00fd na tento XPathEvaluator." }, { "ER_WRONG_NODETYPE", "Typ uzla kontextu nie je podporovan\u00fd." }, { "ER_XPATH_ERROR", "Nezn\u00e1ma chyba v XPath." }, { "WG_LOCALE_NAME_NOT_HANDLED", "n\u00e1zov umiestnenia vo funkcii format-number e\u0161te nebol spracovan\u00fd!" }, { "WG_PROPERTY_NOT_SUPPORTED", "Vlastn\u00edctvo XSL nie je podporovan\u00e9: {0}" }, { "WG_DONT_DO_ANYTHING_WITH_NS", "Nerobte moment\u00e1lne ni\u010d s n\u00e1zvov\u00fdm priestorom {0} vo vlastn\u00edctve: {1}" }, { "WG_SECURITY_EXCEPTION", "SecurityException po\u010das pokusu o pr\u00edstup do syst\u00e9mov\u00e9ho vlastn\u00edctva XSL: {0}" }, { "WG_QUO_NO_LONGER_DEFINED", "Star\u00e1 syntax: quo(...) u\u017e nie je v XPath definovan\u00e9." }, { "WG_NEED_DERIVED_OBJECT_TO_IMPLEMENT_NODETEST", "XPath potrebuje odvoden\u00fd objekt na implement\u00e1ciu nodeTest!" }, { "WG_FUNCTION_TOKEN_NOT_FOUND", "nebol n\u00e1jden\u00fd symbol funkcie." }, { "WG_COULDNOT_FIND_FUNCTION", "Nebolo mo\u017en\u00e9 n\u00e1js\u0165 funkciu: {0}" }, { "WG_CANNOT_MAKE_URL_FROM", "Nie je mo\u017en\u00e9 vytvori\u0165 URL z: {0}" }, { "WG_EXPAND_ENTITIES_NOT_SUPPORTED", "-E vo\u013eba nie je podporovan\u00e1 syntaktick\u00fdm analyz\u00e1torom DTM" }, { "WG_ILLEGAL_VARIABLE_REFERENCE", "VariableReference bol dan\u00fd pre premenn\u00fa mimo kontext, alebo bez defin\u00edcie!  N\u00e1zov = {0}" }, { "WG_UNSUPPORTED_ENCODING", "Nepodporovan\u00e9 k\u00f3dovanie: {0}" }, { "ui_language", "en" }, { "help_language", "en" }, { "language", "en" }, { "BAD_CODE", "Parameter na createMessage bol mimo ohrani\u010denia" }, { "FORMAT_FAILED", "V\u00fdnimka po\u010das volania messageFormat" }, { "version", ">>>>>>> Verzia Xalan " }, { "version2", "<<<<<<<" }, { "yes", "\u00e1no" }, { "line", "Riadok #" }, { "column", "St\u013apec #" }, { "xsldone", "XSLProcessor: vykonan\u00e9" }, { "xpath_option", "vo\u013eby xpath: " }, { "optionIN", "   [-in inputXMLURL]" }, { "optionSelect", "   [-select xpath v\u00fdraz]" }, { "optionMatch", "   [-match porovn\u00e1vac\u00ed vzor (pre diagnostiku zhody)]" }, { "optionAnyExpr", "Alebo len v\u00fdraz xpath vykon\u00e1 v\u00fdpis pam\u00e4te diagnostiky" }, { "noParsermsg1", "Proces XSL nebol \u00faspe\u0161n\u00fd." }, { "noParsermsg2", "** Nebolo mo\u017en\u00e9 n\u00e1js\u0165 syntaktick\u00fd analyz\u00e1tor **" }, { "noParsermsg3", "Skontroluje, pros\u00edm, svoju classpath." }, { "noParsermsg4", "Ak nem\u00e1te Syntaktick\u00fd analyz\u00e1tor XML pre jazyk Java od firmy IBM, m\u00f4\u017eete si ho stiahnu\u0165 z" }, { "noParsermsg5", "IBM's AlphaWorks: http://www.alphaworks.ibm.com/formula/xml" }, { "gtone", ">1" }, { "zero", "0" }, { "one", "1" }, { "two", "2" }, { "three", "3" } };
    }
}
