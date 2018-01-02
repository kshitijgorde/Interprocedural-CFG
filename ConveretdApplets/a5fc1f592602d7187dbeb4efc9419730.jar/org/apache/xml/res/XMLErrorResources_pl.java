// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.ListResourceBundle;

public class XMLErrorResources_pl extends ListResourceBundle
{
    public static final int MAX_CODE = 61;
    public static final int MAX_WARNING = 0;
    public static final int MAX_OTHERS = 4;
    public static final int MAX_MESSAGES = 62;
    public static final String ER_FUNCTION_NOT_SUPPORTED = "ER_FUNCTION_NOT_SUPPORTED";
    public static final String ER_CANNOT_OVERWRITE_CAUSE = "ER_CANNOT_OVERWRITE_CAUSE";
    public static final String ER_NO_DEFAULT_IMPL = "ER_NO_DEFAULT_IMPL";
    public static final String ER_CHUNKEDINTARRAY_NOT_SUPPORTED = "ER_CHUNKEDINTARRAY_NOT_SUPPORTED";
    public static final String ER_OFFSET_BIGGER_THAN_SLOT = "ER_OFFSET_BIGGER_THAN_SLOT";
    public static final String ER_COROUTINE_NOT_AVAIL = "ER_COROUTINE_NOT_AVAIL";
    public static final String ER_COROUTINE_CO_EXIT = "ER_COROUTINE_CO_EXIT";
    public static final String ER_COJOINROUTINESET_FAILED = "ER_COJOINROUTINESET_FAILED";
    public static final String ER_COROUTINE_PARAM = "ER_COROUTINE_PARAM";
    public static final String ER_PARSER_DOTERMINATE_ANSWERS = "ER_PARSER_DOTERMINATE_ANSWERS";
    public static final String ER_NO_PARSE_CALL_WHILE_PARSING = "ER_NO_PARSE_CALL_WHILE_PARSING";
    public static final String ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED = "ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED";
    public static final String ER_ITERATOR_AXIS_NOT_IMPLEMENTED = "ER_ITERATOR_AXIS_NOT_IMPLEMENTED";
    public static final String ER_ITERATOR_CLONE_NOT_SUPPORTED = "ER_ITERATOR_CLONE_NOT_SUPPORTED";
    public static final String ER_UNKNOWN_AXIS_TYPE = "ER_UNKNOWN_AXIS_TYPE";
    public static final String ER_AXIS_NOT_SUPPORTED = "ER_AXIS_NOT_SUPPORTED";
    public static final String ER_NO_DTMIDS_AVAIL = "ER_NO_DTMIDS_AVAIL";
    public static final String ER_NOT_SUPPORTED = "ER_NOT_SUPPORTED";
    public static final String ER_NODE_NON_NULL = "ER_NODE_NON_NULL";
    public static final String ER_COULD_NOT_RESOLVE_NODE = "ER_COULD_NOT_RESOLVE_NODE";
    public static final String ER_STARTPARSE_WHILE_PARSING = "ER_STARTPARSE_WHILE_PARSING";
    public static final String ER_STARTPARSE_NEEDS_SAXPARSER = "ER_STARTPARSE_NEEDS_SAXPARSER";
    public static final String ER_COULD_NOT_INIT_PARSER = "ER_COULD_NOT_INIT_PARSER";
    public static final String ER_EXCEPTION_CREATING_POOL = "ER_EXCEPTION_CREATING_POOL";
    public static final String ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE = "ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE";
    public static final String ER_SCHEME_REQUIRED = "ER_SCHEME_REQUIRED";
    public static final String ER_NO_SCHEME_IN_URI = "ER_NO_SCHEME_IN_URI";
    public static final String ER_NO_SCHEME_INURI = "ER_NO_SCHEME_INURI";
    public static final String ER_PATH_INVALID_CHAR = "ER_PATH_INVALID_CHAR";
    public static final String ER_SCHEME_FROM_NULL_STRING = "ER_SCHEME_FROM_NULL_STRING";
    public static final String ER_SCHEME_NOT_CONFORMANT = "ER_SCHEME_NOT_CONFORMANT";
    public static final String ER_HOST_ADDRESS_NOT_WELLFORMED = "ER_HOST_ADDRESS_NOT_WELLFORMED";
    public static final String ER_PORT_WHEN_HOST_NULL = "ER_PORT_WHEN_HOST_NULL";
    public static final String ER_INVALID_PORT = "ER_INVALID_PORT";
    public static final String ER_FRAG_FOR_GENERIC_URI = "ER_FRAG_FOR_GENERIC_URI";
    public static final String ER_FRAG_WHEN_PATH_NULL = "ER_FRAG_WHEN_PATH_NULL";
    public static final String ER_FRAG_INVALID_CHAR = "ER_FRAG_INVALID_CHAR";
    public static final String ER_PARSER_IN_USE = "ER_PARSER_IN_USE";
    public static final String ER_CANNOT_CHANGE_WHILE_PARSING = "ER_CANNOT_CHANGE_WHILE_PARSING";
    public static final String ER_SELF_CAUSATION_NOT_PERMITTED = "ER_SELF_CAUSATION_NOT_PERMITTED";
    public static final String ER_NO_USERINFO_IF_NO_HOST = "ER_NO_USERINFO_IF_NO_HOST";
    public static final String ER_NO_PORT_IF_NO_HOST = "ER_NO_PORT_IF_NO_HOST";
    public static final String ER_NO_QUERY_STRING_IN_PATH = "ER_NO_QUERY_STRING_IN_PATH";
    public static final String ER_NO_FRAGMENT_STRING_IN_PATH = "ER_NO_FRAGMENT_STRING_IN_PATH";
    public static final String ER_CANNOT_INIT_URI_EMPTY_PARMS = "ER_CANNOT_INIT_URI_EMPTY_PARMS";
    public static final String ER_METHOD_NOT_SUPPORTED = "ER_METHOD_NOT_SUPPORTED";
    public static final String ER_INCRSAXSRCFILTER_NOT_RESTARTABLE = "ER_INCRSAXSRCFILTER_NOT_RESTARTABLE";
    public static final String ER_XMLRDR_NOT_BEFORE_STARTPARSE = "ER_XMLRDR_NOT_BEFORE_STARTPARSE";
    public static final String ER_AXIS_TRAVERSER_NOT_SUPPORTED = "ER_AXIS_TRAVERSER_NOT_SUPPORTED";
    public static final String ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER = "ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER";
    public static final String ER_SYSTEMID_UNKNOWN = "ER_SYSTEMID_UNKNOWN";
    public static final String ER_LOCATION_UNKNOWN = "ER_LOCATION_UNKNOWN";
    public static final String ER_PREFIX_MUST_RESOLVE = "ER_PREFIX_MUST_RESOLVE";
    public static final String ER_CREATEDOCUMENT_NOT_SUPPORTED = "ER_CREATEDOCUMENT_NOT_SUPPORTED";
    public static final String ER_CHILD_HAS_NO_OWNER_DOCUMENT = "ER_CHILD_HAS_NO_OWNER_DOCUMENT";
    public static final String ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT = "ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT";
    public static final String ER_CANT_OUTPUT_TEXT_BEFORE_DOC = "ER_CANT_OUTPUT_TEXT_BEFORE_DOC";
    public static final String ER_CANT_HAVE_MORE_THAN_ONE_ROOT = "ER_CANT_HAVE_MORE_THAN_ONE_ROOT";
    public static final String ER_ARG_LOCALNAME_NULL = "ER_ARG_LOCALNAME_NULL";
    public static final String ER_ARG_LOCALNAME_INVALID = "ER_ARG_LOCALNAME_INVALID";
    public static final String ER_ARG_PREFIX_INVALID = "ER_ARG_PREFIX_INVALID";
    public static final String ER_RESOURCE_COULD_NOT_FIND = "ER_RESOURCE_COULD_NOT_FIND";
    public static final String ER_RESOURCE_COULD_NOT_LOAD = "ER_RESOURCE_COULD_NOT_LOAD";
    public static final String ER_BUFFER_SIZE_LESSTHAN_ZERO = "ER_BUFFER_SIZE_LESSTHAN_ZERO";
    public static final String ER_INVALID_UTF16_SURROGATE = "ER_INVALID_UTF16_SURROGATE";
    public static final String ER_OIERROR = "ER_OIERROR";
    public static final String ER_NAMESPACE_PREFIX = "ER_NAMESPACE_PREFIX";
    public static final String ER_STRAY_ATTRIBUTE = "ER_STRAY_ATTIRBUTE";
    public static final String ER_STRAY_NAMESPACE = "ER_STRAY_NAMESPACE";
    public static final String ER_COULD_NOT_LOAD_RESOURCE = "ER_COULD_NOT_LOAD_RESOURCE";
    public static final String ER_COULD_NOT_LOAD_METHOD_PROPERTY = "ER_COULD_NOT_LOAD_METHOD_PROPERTY";
    public static final String ER_SERIALIZER_NOT_CONTENTHANDLER = "ER_SERIALIZER_NOT_CONTENTHANDLER";
    public static final String ER_ILLEGAL_ATTRIBUTE_POSITION = "ER_ILLEGAL_ATTRIBUTE_POSITION";
    public static final Object[][] contents;
    
    public Object[][] getContents() {
        return XMLErrorResources_pl.contents;
    }
    
    public static final XMLErrorResources loadResourceBundle(final String className) throws MissingResourceException {
        final Locale locale = Locale.getDefault();
        final String suffix = getResourceSuffix(locale);
        try {
            return (XMLErrorResources)ResourceBundle.getBundle(className + suffix, locale);
        }
        catch (MissingResourceException e) {
            try {
                return (XMLErrorResources)ResourceBundle.getBundle(className, new Locale("pl", "PL"));
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
        contents = new Object[][] { { "ER0000", "{0}" }, { "ER_FUNCTION_NOT_SUPPORTED", "Nieobs\u0142ugiwana funkcja!" }, { "ER_CANNOT_OVERWRITE_CAUSE", "Nie mo\u017cna nadpisa\u0107 przyczyny" }, { "ER_NO_DEFAULT_IMPL", "Nie znaleziono domy\u015blnej implementacji" }, { "ER_CHUNKEDINTARRAY_NOT_SUPPORTED", "ChunkedIntArray({0}) nie jest obecnie obs\u0142ugiwane" }, { "ER_OFFSET_BIGGER_THAN_SLOT", "Przesuni\u0119cie wi\u0119ksze ni\u017c szczelina" }, { "ER_COROUTINE_NOT_AVAIL", "Koprocedura niedost\u0119pna, id={0}" }, { "ER_COROUTINE_CO_EXIT", "CoroutineManager otrzyma\u0142 \u017c\u0105danie co_exit()" }, { "ER_COJOINROUTINESET_FAILED", "co_joinCoroutineSet() nie powiod\u0142o si\u0119" }, { "ER_COROUTINE_PARAM", "B\u0142\u0105d parametru koprocedury ({0})" }, { "ER_PARSER_DOTERMINATE_ANSWERS", "\nNIEOCZEKIWANE: Analizator doTerminate odpowiada {0}" }, { "ER_NO_PARSE_CALL_WHILE_PARSING", "Nie mo\u017cna wywo\u0142a\u0107 parse podczas analizowania" }, { "ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED", "B\u0142\u0105d: iterator okre\u015blonego typu dla osi {0} nie jest zaimplementowany" }, { "ER_ITERATOR_AXIS_NOT_IMPLEMENTED", "B\u0142\u0105d: iterator dla osi {0} nie jest zaimplementowany" }, { "ER_ITERATOR_CLONE_NOT_SUPPORTED", "Kopia iteratora nie jest obs\u0142ugiwana" }, { "ER_UNKNOWN_AXIS_TYPE", "Nieznany typ przej\u015bcia osi {0}" }, { "ER_AXIS_NOT_SUPPORTED", "Nieobs\u0142ugiwane przej\u015bcie osi: {0}" }, { "ER_NO_DTMIDS_AVAIL", "Nie ma wi\u0119cej dost\u0119pnych identyfikator\u00f3w DTM" }, { "ER_NOT_SUPPORTED", "Nieobs\u0142ugiwane: {0}" }, { "ER_NODE_NON_NULL", "W\u0119ze\u0142 musi by\u0107 niepusty dla getDTMHandleFromNode" }, { "ER_COULD_NOT_RESOLVE_NODE", "Nie mo\u017cna przet\u0142umaczy\u0107 w\u0119z\u0142a na uchwyt" }, { "ER_STARTPARSE_WHILE_PARSING", "Nie mo\u017cna wywo\u0142a\u0107 startParse podczas analizowania" }, { "ER_STARTPARSE_NEEDS_SAXPARSER", "startParse potrzebuje niepustego SAXParser" }, { "ER_COULD_NOT_INIT_PARSER", "nie mo\u017cna zainicjowa\u0107 analizatora" }, { "ER_EXCEPTION_CREATING_POOL", "wyj\u0105tek podczas tworzenia nowej instancji dla puli" }, { "ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE", "\u015acie\u017cka zawiera niepoprawn\u0105 sekwencj\u0119 o zmienionym znaczeniu" }, { "ER_SCHEME_REQUIRED", "Schemat jest wymagany!" }, { "ER_NO_SCHEME_IN_URI", "Nie znaleziono schematu w URI {0}" }, { "ER_NO_SCHEME_INURI", "Nie znaleziono schematu w URI" }, { "ER_PATH_INVALID_CHAR", "\u015acie\u017cka zawiera niepoprawny znak {0}" }, { "ER_SCHEME_FROM_NULL_STRING", "Nie mo\u017cna ustawi\u0107 schematu z pustego ci\u0105gu znak\u00f3w" }, { "ER_SCHEME_NOT_CONFORMANT", "Schemat nie jest zgodny." }, { "ER_HOST_ADDRESS_NOT_WELLFORMED", "Host nie jest poprawnie skonstruowanym adresem" }, { "ER_PORT_WHEN_HOST_NULL", "Nie mo\u017cna ustawi\u0107 portu, kiedy host jest pusty" }, { "ER_INVALID_PORT", "Niepoprawny numer portu" }, { "ER_FRAG_FOR_GENERIC_URI", "Fragment mo\u017cna ustawi\u0107 tylko dla og\u00f3lnego URI" }, { "ER_FRAG_WHEN_PATH_NULL", "Nie mo\u017cna ustawi\u0107 fragmentu, kiedy \u015bcie\u017cka jest pusta" }, { "ER_FRAG_INVALID_CHAR", "Fragment zawiera niepoprawny znak" }, { "ER_PARSER_IN_USE", "Analizator jest ju\u017c u\u017cywany" }, { "ER_CANNOT_CHANGE_WHILE_PARSING", "Nie mo\u017cna zmieni\u0107 {0} {1} podczas analizowania" }, { "ER_SELF_CAUSATION_NOT_PERMITTED", "Powodowanie siebie jest niedozwolone" }, { "ER_NO_USERINFO_IF_NO_HOST", "Nie mo\u017cna poda\u0107 informacji o u\u017cytkowniku, je\u015bli nie podano hosta" }, { "ER_NO_PORT_IF_NO_HOST", "Nie mo\u017cna poda\u0107 portu, je\u015bli nie podano hosta" }, { "ER_NO_QUERY_STRING_IN_PATH", "Tekstu zapytania nie mo\u017cna poda\u0107 w tek\u015bcie \u015bcie\u017cki i zapytania" }, { "ER_NO_FRAGMENT_STRING_IN_PATH", "Nie mo\u017cna poda\u0107 fragmentu jednocze\u015bnie w \u015bcie\u017cce i fragmencie" }, { "ER_CANNOT_INIT_URI_EMPTY_PARMS", "Nie mo\u017cna zainicjowa\u0107 URI z pustymi parametrami" }, { "ER_METHOD_NOT_SUPPORTED", "Metoda nie jest jeszcze obs\u0142ugiwana" }, { "ER_INCRSAXSRCFILTER_NOT_RESTARTABLE", "IncrementalSAXSource_Filter nie jest obecnie mo\u017cliwy do ponownego uruchomienia" }, { "ER_XMLRDR_NOT_BEFORE_STARTPARSE", "XMLReader nie przed \u017c\u0105daniem startParse" }, { "ER_AXIS_TRAVERSER_NOT_SUPPORTED", "Nieobs\u0142ugiwane przej\u015bcie osi: {0}" }, { "ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER", "Utworzono ListingErrorHandler z pustym PrintWriter!" }, { "ER_SYSTEMID_UNKNOWN", "Nieznany identyfikator systemu" }, { "ER_LOCATION_UNKNOWN", "Po\u0142o\u017cenie b\u0142\u0119du jest nieznane" }, { "ER_PREFIX_MUST_RESOLVE", "Przedrostek musi da\u0107 si\u0119 przet\u0142umaczy\u0107 na przestrze\u0144 nazw: {0}" }, { "ER_CREATEDOCUMENT_NOT_SUPPORTED", "Funkcja createDocument() nie jest obs\u0142ugiwana w XPathContext!" }, { "ER_CHILD_HAS_NO_OWNER_DOCUMENT", "Potomek atrybutu nie ma dokumentu w\u0142a\u015bciciela!" }, { "ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT", "Potomek atrybutu nie ma elementu dokumentu w\u0142a\u015bciciela!" }, { "ER_CANT_OUTPUT_TEXT_BEFORE_DOC", "Ostrze\u017cenie: nie mo\u017cna wyprowadzi\u0107 tekstu przed elementem dokumentu!  Ignoruj\u0119..." }, { "ER_CANT_HAVE_MORE_THAN_ONE_ROOT", "Nie mo\u017cna mie\u0107 wi\u0119cej ni\u017c jeden element g\u0142\u00f3wny w DOM!" }, { "ER_ARG_LOCALNAME_NULL", "Argument 'localName' jest pusty" }, { "ER_ARG_LOCALNAME_INVALID", "Nazwa lokalna w QNAME powinna by\u0107 poprawn\u0105 nazw\u0105 NCName" }, { "ER_ARG_PREFIX_INVALID", "Przedrostek w QNAME powinien by\u0107 poprawn\u0105 nazw\u0105 NCName" }, { "BAD_CODE", "Parametr createMessage by\u0142 spoza zakresu" }, { "FORMAT_FAILED", "Podczas wywo\u0142ania messageFormat zg\u0142oszony zosta\u0142 wyj\u0105tek" }, { "line", "Wiersz: " }, { "column", "Kolumna " }, { "ER_SERIALIZER_NOT_CONTENTHANDLER", "Klasa szereguj\u0105ca ''{0}'' nie implementuje procedury obs\u0142ugi org.xml.sax.ContentHandler." }, { "ER_RESOURCE_COULD_NOT_FIND", "Nie mo\u017cna znale\u017a\u0107 zasobu [ {0} ].\n {1}" }, { "ER_RESOURCE_COULD_NOT_LOAD", "Zas\u00f3b [ {0} ] nie m\u00f3g\u0142 za\u0142adowa\u0107: {1} \n {2} \t {3}" }, { "ER_BUFFER_SIZE_LESSTHAN_ZERO", "Wielko\u015b\u0107 bufora <=0" }, { "ER_INVALID_UTF16_SURROGATE", "Wykryto niepoprawny surogat UTF-16: {0} ?" }, { "ER_OIERROR", "B\u0142\u0105d we/wy" }, { "ER_ILLEGAL_ATTRIBUTE_POSITION", "Nie mo\u017cna doda\u0107 atrybutu {0} po w\u0119z\u0142ach potomnych ani przed wyprodukowaniem elementu.  Atrybut zostanie zignorowany." }, { "ER_NAMESPACE_PREFIX", "Nie zadeklarowano przestrzeni nazw dla przedrostka ''{0}''." }, { "ER_STRAY_ATTIRBUTE", "Atrybut ''{0}'' poza elementem." }, { "ER_STRAY_NAMESPACE", "Deklaracja przestrzeni nazw ''{0}''=''{1}'' poza elementem." }, { "ER_COULD_NOT_LOAD_RESOURCE", "Nie mo\u017cna za\u0142adowa\u0107 ''{0}'' (sprawd\u017a CLASSPATH), u\u017cywane s\u0105 teraz warto\u015bci domy\u015blne" }, { "ER_COULD_NOT_LOAD_METHOD_PROPERTY", "Nie mo\u017cna za\u0142adowa\u0107 pliku w\u0142a\u015bciwo\u015bci ''{0}'' dla metody wyj\u015bciowej ''{1}'' (sprawd\u017a CLASSPATH)" } };
    }
}
