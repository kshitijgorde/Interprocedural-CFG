// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_cs extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_cs.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "Vnit\u0159n\u00ed b\u011bhov\u00e1 chyba v ''{0}''" }, { "RUN_TIME_COPY_ERR", "Vnit\u0159n\u00ed b\u011bhov\u00e1 chyba p\u0159i prov\u00e1d\u011bn\u00ed funkce <xsl:copy>." }, { "DATA_CONVERSION_ERR", "Neplatn\u00e1 konverze z ''{0}'' do ''{1}''." }, { "EXTERNAL_FUNC_ERR", "Extern\u00ed funkce ''{0}'' nen\u00ed podporov\u00e1na produktem SLTC." }, { "EQUALITY_EXPR_ERR", "Nezn\u00e1m\u00fd typ argumentu ve v\u00fdrazu rovnosti." }, { "INVALID_ARGUMENT_ERR", "Neplatn\u00fd typ argumentu ''{0}'' p\u0159i vol\u00e1n\u00ed ''{1}''" }, { "FORMAT_NUMBER_ERR", "Pokus form\u00e1tovat \u010d\u00edslo ''{0}'' pou\u017eit\u00edm vzorku ''{1}''." }, { "ITERATOR_CLONE_ERR", "Nelze klonovat iter\u00e1tor ''{0}''." }, { "AXIS_SUPPORT_ERR", "Iter\u00e1tor pro osu ''{0}'' nen\u00ed podporov\u00e1n." }, { "TYPED_AXIS_SUPPORT_ERR", "Iter\u00e1tor pro typizovanou osu ''{0}'' nen\u00ed podporov\u00e1n." }, { "STRAY_ATTRIBUTE_ERR", "Atribut ''{0}'' je vn\u011b prvku." }, { "STRAY_NAMESPACE_ERR", "Deklarace oboru n\u00e1zv\u016f ''{0}''=''{1}'' je vn\u011b prvku." }, { "NAMESPACE_PREFIX_ERR", "Obor n\u00e1zv\u016f pro p\u0159edponu ''{0}'' nebyl deklarov\u00e1n." }, { "DOM_ADAPTER_INIT_ERR", "DOMAdapter byl vytvo\u0159en s pou\u017eit\u00edm chybn\u00e9ho typu zdroje DOM." }, { "PARSER_DTD_SUPPORT_ERR", "Pou\u017eit\u00fd analyz\u00e1tor SAX nem\u016f\u017ee manipulovat s deklara\u010dn\u00edmi ud\u00e1lostmi DTD." }, { "NAMESPACES_SUPPORT_ERR", "Pou\u017eit\u00fd analyz\u00e1tor SAX nem\u016f\u017ee podporovat obory n\u00e1zv\u016f pro XML." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "Nelze p\u0159elo\u017eit odkazy URI ''{0}''." } };
    }
}
