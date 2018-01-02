// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.ListResourceBundle;

public class XPATHErrorResources_pt_BR extends ListResourceBundle
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
    public static final String ERROR_HEADER = "Erro: ";
    public static final String WARNING_HEADER = "Aviso: ";
    public static final String XSL_HEADER = "XSL ";
    public static final String XML_HEADER = "XML ";
    public static final String QUERY_HEADER = "PADR\u00c3O ";
    
    public Object[][] getContents() {
        return XPATHErrorResources_pt_BR.contents;
    }
    
    public static final XPATHErrorResources loadResourceBundle(final String className) throws MissingResourceException {
        final Locale locale = Locale.getDefault();
        final String suffix = getResourceSuffix(locale);
        try {
            return (XPATHErrorResources)ResourceBundle.getBundle(className + suffix, locale);
        }
        catch (MissingResourceException e) {
            try {
                return (XPATHErrorResources)ResourceBundle.getBundle(className, new Locale("pt", "BR"));
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
        contents = new Object[][] { { "ERROR0000", "{0}" }, { "ER_CURRENT_NOT_ALLOWED_IN_MATCH", "A fun\u00e7\u00e3o current() n\u00e3o \u00e9 permitida em um padr\u00e3o de correspond\u00eancia!" }, { "ER_CURRENT_TAKES_NO_ARGS", "A fun\u00e7\u00e3o current() n\u00e3o aceita argumentos!" }, { "ER_DOCUMENT_REPLACED", "A implementa\u00e7\u00e3o da fun\u00e7\u00e3o document() foi substitu\u00edda por org.apache.xalan.xslt.FuncDocument!" }, { "ER_CONTEXT_HAS_NO_OWNERDOC", "context n\u00e3o possui um documento do propriet\u00e1rio!" }, { "ER_LOCALNAME_HAS_TOO_MANY_ARGS", "local-name() possui argumentos em excesso." }, { "ER_NAMESPACEURI_HAS_TOO_MANY_ARGS", "namespace-uri() possui argumentos em excesso." }, { "ER_NORMALIZESPACE_HAS_TOO_MANY_ARGS", "normalize-space() possui argumentos em excesso." }, { "ER_NUMBER_HAS_TOO_MANY_ARGS", "number() possui argumentos em excesso." }, { "ER_NAME_HAS_TOO_MANY_ARGS", "name() possui argumentos em excesso." }, { "ER_STRING_HAS_TOO_MANY_ARGS", "string() possui argumentos em excesso." }, { "ER_STRINGLENGTH_HAS_TOO_MANY_ARGS", "string-length() possui argumentos em excesso." }, { "ER_TRANSLATE_TAKES_3_ARGS", "A fun\u00e7\u00e3o translate() tem tr\u00eas argumentos!" }, { "ER_UNPARSEDENTITYURI_TAKES_1_ARG", "A fun\u00e7\u00e3o unparsed-entity-uri deve ter um argumento!" }, { "ER_NAMESPACEAXIS_NOT_IMPLEMENTED", "namespace axis ainda n\u00e3o implementado!" }, { "ER_UNKNOWN_AXIS", "eixo desconhecido: {0}" }, { "ER_UNKNOWN_MATCH_OPERATION", "opera\u00e7\u00e3o de correspond\u00eancia desconhecida!" }, { "ER_INCORRECT_ARG_LENGTH", "O comprimento de arg do teste de n\u00f3 de processing-instruction() est\u00e1 incorreto! " }, { "ER_CANT_CONVERT_TO_NUMBER", "Imposs\u00edvel converter {0} em um n\u00famero" }, { "ER_CANT_CONVERT_TO_NODELIST", "Imposs\u00edvel converter {0} em um NodeList!" }, { "ER_CANT_CONVERT_TO_MUTABLENODELIST", "Imposs\u00edvel converter {0} em um NodeSetDTM!" }, { "ER_CANT_CONVERT_TO_TYPE", "Imposs\u00edvel converter {0} em um tipo {1}" }, { "ER_EXPECTED_MATCH_PATTERN", "Padr\u00e3o de correspond\u00eancia esperado em getMatchScore!" }, { "ER_COULDNOT_GET_VAR_NAMED", "N\u00e3o foi poss\u00edvel obter a vari\u00e1vel {0}" }, { "ER_UNKNOWN_OPCODE", "ERRO! C\u00f3digo op desconhecido: {0}" }, { "ER_EXTRA_ILLEGAL_TOKENS", "Tokens inv\u00e1lidos extras: {0}" }, { "ER_EXPECTED_DOUBLE_QUOTE", "literal com aspa incorreta... era esperada aspa dupla!" }, { "ER_EXPECTED_SINGLE_QUOTE", "literal com aspa incorreta... era esperada aspa simples!" }, { "ER_EMPTY_EXPRESSION", "Express\u00e3o vazia!" }, { "ER_EXPECTED_BUT_FOUND", "Esperado {0}, mas encontrado: {1}" }, { "ER_INCORRECT_PROGRAMMER_ASSERTION", "A declara\u00e7\u00e3o do programador est\u00e1 incorreta! - {0}" }, { "ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL", "O argumento boolean(...) n\u00e3o \u00e9 mais opcional com o rascunho 19990709 XPath." }, { "ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG", "Encontrado ',' mas sem argumento precedente!" }, { "ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG", "Encontrado ',' mas sem argumento seguinte!" }, { "ER_PREDICATE_ILLEGAL_SYNTAX", "'..[predicate]' ou '.[predicate]' \u00e9 sintaxe inv\u00e1lida.  Utilize ent\u00e3o 'self::node()[predicate]'." }, { "ER_ILLEGAL_AXIS_NAME", "nome de eixo inv\u00e1lido: {0}" }, { "ER_UNKNOWN_NODETYPE", "Tipo de n\u00f3 desconhecido: {0}" }, { "ER_PATTERN_LITERAL_NEEDS_BE_QUOTED", "O literal de padr\u00e3o ({0}) precisa ser colocado entre aspas!" }, { "ER_COULDNOT_BE_FORMATTED_TO_NUMBER", "{0} n\u00e3o p\u00f4de ser formatado para um n\u00famero!" }, { "ER_COULDNOT_CREATE_XMLPROCESSORLIAISON", "N\u00e3o foi poss\u00edvel criar XML TransformerFactory Liaison: {0}" }, { "ER_DIDNOT_FIND_XPATH_SELECT_EXP", "Erro! N\u00e3o encontrada a express\u00e3o xpath select (-select)." }, { "ER_COULDNOT_FIND_ENDOP_AFTER_OPLOCATIONPATH", "ERRO! N\u00e3o foi poss\u00edvel encontrar ENDOP ap\u00f3s OP_LOCATIONPATH" }, { "ER_ERROR_OCCURED", "Ocorreu um erro!" }, { "ER_ILLEGAL_VARIABLE_REFERENCE", "VariableReference fornecido para a vari\u00e1vel fora de contexto ou sem defini\u00e7\u00e3o!  Nome = {0}" }, { "ER_AXES_NOT_ALLOWED", "Apenas os eixos child:: e attribute:: s\u00e3o permitidos em padr\u00f5es de correspond\u00eancia! Eixos transgredidos = {0}" }, { "ER_KEY_HAS_TOO_MANY_ARGS", "key() possui um n\u00famero incorreto de argumentos." }, { "ER_COUNT_TAKES_1_ARG", "A fun\u00e7\u00e3o count deve ter um argumento!" }, { "ER_COULDNOT_FIND_FUNCTION", "N\u00e3o foi poss\u00edvel localizar a fun\u00e7\u00e3o: {0}" }, { "ER_UNSUPPORTED_ENCODING", "Codifica\u00e7\u00e3o n\u00e3o suportada: {0}" }, { "ER_PROBLEM_IN_DTM_NEXTSIBLING", "Ocorreu um problema no DTM em getNextSibling... tentando recuperar" }, { "ER_CANNOT_WRITE_TO_EMPTYNODELISTIMPL", "Erro do programador: EmptyNodeList n\u00e3o pode ser gravado." }, { "ER_SETDOMFACTORY_NOT_SUPPORTED", "setDOMFactory n\u00e3o \u00e9 suportado por XPathContext!" }, { "ER_PREFIX_MUST_RESOLVE", "O prefixo deve ser resolvido para um namespace: {0}" }, { "ER_PARSE_NOT_SUPPORTED", "parse (origem InputSource) n\u00e3o suportada no XPathContext! Imposs\u00edvel abrir {0}" }, { "ER_SAX_API_NOT_HANDLED", "SAX API characters(char ch[]... n\u00e3o tratado pelo DTM!" }, { "ER_IGNORABLE_WHITESPACE_NOT_HANDLED", "ignorableWhitespace(char ch[]... n\u00e3o tratado pelo DTM!" }, { "ER_DTM_CANNOT_HANDLE_NODES", "DTMLiaison n\u00e3o pode tratar n\u00f3s do tipo {0}" }, { "ER_XERCES_CANNOT_HANDLE_NODES", "DOM2Helper n\u00e3o pode tratar n\u00f3s do tipo {0}" }, { "ER_XERCES_PARSE_ERROR_DETAILS", "DOM2Helper.parse error: SystemID - {0} linha - {1}" }, { "ER_XERCES_PARSE_ERROR", "Erro de DOM2Helper.parse" }, { "ER_INVALID_UTF16_SURROGATE", "Detectado substituto UTF-16 inv\u00e1lido: {0} ?" }, { "ER_OIERROR", "Erro de E/S" }, { "ER_CANNOT_CREATE_URL", "Imposs\u00edvel criar url para: {0}" }, { "ER_XPATH_READOBJECT", "Em XPath.readObject: {0}" }, { "ER_FUNCTION_TOKEN_NOT_FOUND", "Token function n\u00e3o encontrado." }, { "ER_CANNOT_DEAL_XPATH_TYPE", "Imposs\u00edvel lidar com o tipo XPath: {0}" }, { "ER_NODESET_NOT_MUTABLE", "Este NodeSet n\u00e3o \u00e9 mut\u00e1vel" }, { "ER_NODESETDTM_NOT_MUTABLE", "Este NodeSetDTM n\u00e3o \u00e9 mut\u00e1vel" }, { "ER_VAR_NOT_RESOLVABLE", "A vari\u00e1vel n\u00e3o pode ser resolvida: {0}" }, { "ER_NULL_ERROR_HANDLER", "Rotina de tratamento de erros nula" }, { "ER_PROG_ASSERT_UNKNOWN_OPCODE", "Declara\u00e7\u00e3o do programador: c\u00f3digo de op\u00e7\u00e3o desconhecido: {0}" }, { "ER_ZERO_OR_ONE", "0 ou 1" }, { "ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", "rtf() n\u00e3o suportado por XRTreeFragSelectWrapper" }, { "ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", "asNodeIterator() n\u00e3o suportado por XRTreeFragSelectWrapper" }, { "ER_FSB_NOT_SUPPORTED_XSTRINGFORCHARS", "fsb() n\u00e3o suportado para XStringForChars" }, { "ER_COULD_NOT_FIND_VAR", "N\u00e3o foi poss\u00edvel encontrar a vari\u00e1vel com o nome {0}" }, { "ER_XSTRINGFORCHARS_CANNOT_TAKE_STRING", "XStringForChars n\u00e3o pode obter uma cadeia para um argumento" }, { "ER_FASTSTRINGBUFFER_CANNOT_BE_NULL", "O argumento FastStringBuffer n\u00e3o pode ser nulo" }, { "ER_TWO_OR_THREE", "2 ou 3" }, { "ER_VARIABLE_ACCESSED_BEFORE_BIND", "Vari\u00e1vel acessada antes de ser ligada!" }, { "ER_FSB_CANNOT_TAKE_STRING", "XStringForFSB n\u00e3o pode obter uma cadeia para um argumento!" }, { "ER_SETTING_WALKER_ROOT_TO_NULL", "\n !!!! Erro! Definindo a raiz de um transmissor como nula!!!" }, { "ER_NODESETDTM_CANNOT_ITERATE", "Este NodeSetDTM n\u00e3o pode iterar em um n\u00f3 anterior!" }, { "ER_NODESET_CANNOT_ITERATE", "Este NodeSet n\u00e3o pode iterar em um n\u00f3 anterior!" }, { "ER_NODESETDTM_CANNOT_INDEX", "Este NodeSetDTM n\u00e3o pode executar fun\u00e7\u00f5es de indexa\u00e7\u00e3o ou de contagem!" }, { "ER_NODESET_CANNOT_INDEX", "Este NodeSet n\u00e3o pode executar fun\u00e7\u00f5es de indexa\u00e7\u00e3o ou de contagem!" }, { "ER_CANNOT_CALL_SETSHOULDCACHENODE", "Imposs\u00edvel chamar setShouldCacheNodes depois de nextNode ter sido chamado!" }, { "ER_ONLY_ALLOWS", "{0} permite apenas {1} argumento(s)" }, { "ER_UNKNOWN_STEP", "Declara\u00e7\u00e3o do programador em getNextStepPos: stepType desconhecido: {0}" }, { "ER_EXPECTED_REL_LOC_PATH", "Era esperado um caminho de localiza\u00e7\u00e3o relativo ap\u00f3s o token '/' ou '//'." }, { "ER_EXPECTED_LOC_PATH", "Era esperado um caminho de localiza\u00e7\u00e3o, mas o seguinte token foi encontrado:  {0}" }, { "ER_EXPECTED_LOC_STEP", "Era esperada uma etapa de localiza\u00e7\u00e3o ap\u00f3s o token '/' ou '//'." }, { "ER_EXPECTED_NODE_TEST", "Era esperado um n\u00f3 correspondente a NCName:* ou QName." }, { "ER_EXPECTED_STEP_PATTERN", "Era esperado um padr\u00e3o de etapa, mas foi encontrado '/'." }, { "ER_EXPECTED_REL_PATH_PATTERN", "Era esperado um padr\u00e3o de caminho relativo." }, { "ER_CANT_CONVERT_TO_BOOLEAN", "Imposs\u00edvel converter {0} em um boolean." }, { "ER_CANT_CONVERT_TO_SINGLENODE", "Imposs\u00edvel converter {0} em um \u00fanico n\u00f3. Este getter se aplica aos tipos ANY_UNORDERED_NODE_TYPE e FIRST_ORDERED_NODE_TYPE." }, { "ER_CANT_GET_SNAPSHOT_LENGTH", "Imposs\u00edvel obter comprimento de instant\u00e2neo no tipo: {0}. Este getter se aplica aos tipos UNORDERED_NODE_SNAPSHOT_TYPE e ORDERED_NODE_SNAPSHOT_TYPE." }, { "ER_NON_ITERATOR_TYPE", "Imposs\u00edvel iterar em tipo de n\u00e3o iterador: {0}" }, { "ER_DOC_MUTATED", "Documento alterado desde o retorno do resultado. O iterador \u00e9 inv\u00e1lido." }, { "ER_INVALID_XPATH_TYPE", "Argumento de tipo XPath inv\u00e1lido: {0}" }, { "ER_EMPTY_XPATH_RESULT", "Objeto de resultado XPath vazio" }, { "ER_INCOMPATIBLE_TYPES", "O tipo retornado: {0} n\u00e3o pode ser for\u00e7ado no tipo especificado: {1}" }, { "ER_NULL_RESOLVER", "N\u00e3o foi poss\u00edvel resolver o prefixo com um resolvedor de prefixo nulo." }, { "ER_CANT_CONVERT_TO_STRING", "Imposs\u00edvel converter {0} em uma cadeia." }, { "ER_NON_SNAPSHOT_TYPE", "Imposs\u00edvel chamar snapshotItem no tipo: {0}. Este m\u00e9todo se aplica aos tipos UNORDERED_NODE_SNAPSHOT_TYPE e ORDERED_NODE_SNAPSHOT_TYPE." }, { "ER_WRONG_DOCUMENT", "O n\u00f3 do contexto n\u00e3o pertence ao documento que est\u00e1 ligado a este XPathEvaluator." }, { "ER_WRONG_NODETYPE", "O tipo de n\u00f3 de contexto n\u00e3o \u00e9 suportado." }, { "ER_XPATH_ERROR", "Erro desconhecido em XPath." }, { "WG_LOCALE_NAME_NOT_HANDLED", "nome de locale na fun\u00e7\u00e3o format-number ainda n\u00e3o tratado!" }, { "WG_PROPERTY_NOT_SUPPORTED", "Propriedade XSL n\u00e3o suportada: {0}" }, { "WG_DONT_DO_ANYTHING_WITH_NS", "N\u00e3o fazer nada no momento com o namespace {0} na propriedade {1}" }, { "WG_SECURITY_EXCEPTION", "SecurityException ao tentar acessar a propriedade do sistema XSL: {0}" }, { "WG_QUO_NO_LONGER_DEFINED", "Sintaxe antiga: quo(...) n\u00e3o est\u00e1 mais definida no XPath." }, { "WG_NEED_DERIVED_OBJECT_TO_IMPLEMENT_NODETEST", "XPath precisa de um objeto derivado para implementar nodeTest!" }, { "WG_FUNCTION_TOKEN_NOT_FOUND", "Token function n\u00e3o encontrado." }, { "WG_COULDNOT_FIND_FUNCTION", "N\u00e3o foi poss\u00edvel localizar a fun\u00e7\u00e3o: {0}" }, { "WG_CANNOT_MAKE_URL_FROM", "Imposs\u00edvel criar URL a partir de: {0}" }, { "WG_EXPAND_ENTITIES_NOT_SUPPORTED", "A op\u00e7\u00e3o -E n\u00e3o \u00e9 suportada pelo analisador do DTM" }, { "WG_ILLEGAL_VARIABLE_REFERENCE", "VariableReference fornecido para a vari\u00e1vel fora de contexto ou sem defini\u00e7\u00e3o!  Nome = {0}" }, { "WG_UNSUPPORTED_ENCODING", "Codifica\u00e7\u00e3o n\u00e3o suportada: {0}" }, { "ui_language", "pt" }, { "help_language", "pt" }, { "language", "pt" }, { "BAD_CODE", "O par\u00e2metro para createMessage estava fora dos limites" }, { "FORMAT_FAILED", "Exce\u00e7\u00e3o emitida durante chamada messageFormat" }, { "version", ">>>>>>> Vers\u00e3o Xalan" }, { "version2", "<<<<<<<" }, { "yes", "sim" }, { "line", "Linha n°" }, { "column", "Coluna n°" }, { "xsldone", "XSLProcessor: conclu\u00eddo" }, { "xpath_option", "op\u00e7\u00f5es xpath:" }, { "optionIN", "   [-in inputXMLURL]" }, { "optionSelect", "   [-select xpath expression]" }, { "optionMatch", "[-match match pattern (para corresponder diagn\u00f3sticos)]" }, { "optionAnyExpr", "Ou apenas uma express\u00e3o xpath executar\u00e1 um dump de diagn\u00f3stico" }, { "noParsermsg1", "O Processo XSL n\u00e3o obteve \u00eaxito." }, { "noParsermsg2", "** N\u00e3o foi poss\u00edvel encontrar o analisador **" }, { "noParsermsg3", "Verifique seu classpath." }, { "noParsermsg4", "Se voc\u00ea n\u00e3o tiver o XML Parser para Java da IBM, poder\u00e1 fazer o download dele a partir de" }, { "noParsermsg5", "IBM's AlphaWorks: http://www.alphaworks.ibm.com/formula/xml" }, { "gtone", ">1" }, { "zero", "0" }, { "one", "1" }, { "two", "2" }, { "three", "3" } };
    }
}
