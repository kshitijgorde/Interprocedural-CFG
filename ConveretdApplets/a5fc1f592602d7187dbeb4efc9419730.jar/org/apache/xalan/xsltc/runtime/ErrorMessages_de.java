// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_de extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_de.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "Interner Fehler bei der Ausf\u00fchrung in ''{0}''" }, { "RUN_TIME_COPY_ERR", "Fehler bei der Ausf\u00fchrung von <xsl:copy>." }, { "DATA_CONVERSION_ERR", "Ung\u00fcltige Konvertierung von ''{0}'' nach ''{1}''." }, { "EXTERNAL_FUNC_ERR", "Die externe Funktion ''{0}'' wird nicht von XSLTC unterst\u00fctzt." }, { "EQUALITY_EXPR_ERR", "Unbekannter Argumenttyp in Gleichheitsausdruck." }, { "INVALID_ARGUMENT_ERR", "Ung\u00fcltiger Argumenttyp ''{0}'' in Aufruf von ''{1}''" }, { "FORMAT_NUMBER_ERR", "Es wird versucht, die Zahl ''{0}'' mit Muster ''{1}'' zu formatieren." }, { "ITERATOR_CLONE_ERR", "Iterator ''{0}'' kann nicht geklont werden." }, { "AXIS_SUPPORT_ERR", "Iterator f\u00fcr Achse ''{0}'' wird nicht unterst\u00fctzt." }, { "TYPED_AXIS_SUPPORT_ERR", "Iterator f\u00fcr Achse ''{0}'' mit Typangabe wird nicht unterst\u00fctzt." }, { "STRAY_ATTRIBUTE_ERR", "Attribut ''{0}'' befindet sich nicht in einem Element." }, { "STRAY_NAMESPACE_ERR", "Namensbereichsdeklaration ''{0}''=''{1}'' befindet sich nicht in einem Element." }, { "NAMESPACE_PREFIX_ERR", "Der Namensbereich f\u00fcr Pr\u00e4fix ''{0}'' wurde nicht deklariert." }, { "DOM_ADAPTER_INIT_ERR", "DOMAdapter wurde mit dem falschen Typ f\u00fcr das Dokumentobjektmodell der Quelle erstellt." }, { "PARSER_DTD_SUPPORT_ERR", "Der von Ihnen verwendete SAX-Parser bearbeitet keine DTD-Deklarationsereignisse." }, { "NAMESPACES_SUPPORT_ERR", "Der von Ihnen verwendete SAX-Parser unterst\u00fctzt keine XML-Namensbereiche." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "Der URI-Verweis ''{0}'' konnte nicht aufgel\u00f6st werden." } };
    }
}
