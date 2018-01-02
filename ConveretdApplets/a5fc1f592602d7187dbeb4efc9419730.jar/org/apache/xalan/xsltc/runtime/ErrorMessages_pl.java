// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_pl extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_pl.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "Wewn\u0119trzny b\u0142\u0105d czasu wykonania w ''{0}''" }, { "RUN_TIME_COPY_ERR", "B\u0142\u0105d czasu wykonania podczas wykonywania <xsl:copy>." }, { "DATA_CONVERSION_ERR", "Niepoprawna konwersja z ''{0}'' do ''{1}''." }, { "EXTERNAL_FUNC_ERR", "Funkcja zewn\u0119trzna ''{0}'' nieobs\u0142ugiwana przez XSLTC." }, { "EQUALITY_EXPR_ERR", "Nieznany typ argumentu w wyra\u017ceniu r\u00f3wno\u015bci." }, { "INVALID_ARGUMENT_ERR", "Niepoprawny typ argumentu ''{0}'' w wywo\u0142aniu ''{1}''" }, { "FORMAT_NUMBER_ERR", "Pr\u00f3ba sformatowania liczby ''{0}'' za pomoc\u0105 wzorca ''{1}''." }, { "ITERATOR_CLONE_ERR", "Nie mo\u017cna utworzy\u0107 kopii iteratora ''{0}''." }, { "AXIS_SUPPORT_ERR", "Iterator osi ''{0}'' nie jest obs\u0142ugiwany." }, { "TYPED_AXIS_SUPPORT_ERR", "Iterator osi ''{0}'' okre\u015blonego typu nie jest obs\u0142ugiwany." }, { "STRAY_ATTRIBUTE_ERR", "Atrybut ''{0}'' poza elementem." }, { "STRAY_NAMESPACE_ERR", "Deklaracja przestrzeni nazw ''{0}''=''{1}'' poza elementem." }, { "NAMESPACE_PREFIX_ERR", "Nie zadeklarowano przestrzeni nazw dla przedrostka ''{0}''." }, { "DOM_ADAPTER_INIT_ERR", "Utworzono DOMAdapter za pomoc\u0105 \u017ar\u00f3d\u0142owego DOM o b\u0142\u0119dnym typie." }, { "PARSER_DTD_SUPPORT_ERR", "U\u017cywany analizator sk\u0142adni SAX nie obs\u0142uguje zdarze\u0144 deklaracji DTD." }, { "NAMESPACES_SUPPORT_ERR", "U\u017cywany analizator sk\u0142adni SAX nie obs\u0142uguje przestrzeni nazw XML." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "Nie mo\u017cna przet\u0142umaczy\u0107 odniesienia do URI ''{0}''." } };
    }
}
