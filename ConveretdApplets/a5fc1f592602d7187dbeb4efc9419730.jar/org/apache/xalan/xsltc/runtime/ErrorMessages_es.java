// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_es extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_es.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "Error interno de ejecuci\u00f3n en ''{0}''" }, { "RUN_TIME_COPY_ERR", "Error de ejecuci\u00f3n al ejecutar <xsl:copy>." }, { "DATA_CONVERSION_ERR", "Conversi\u00f3n no v\u00e1lida de ''{0}'' a ''{1}''." }, { "EXTERNAL_FUNC_ERR", "Funci\u00f3n externa ''{0}'' no soportada por XSLTC." }, { "EQUALITY_EXPR_ERR", "Tipo de argumento desconocido en expresi\u00f3n de igualdad." }, { "INVALID_ARGUMENT_ERR", "Tipo de argumento ''{0}'' no v\u00e1lido en llamada a ''{1}''" }, { "FORMAT_NUMBER_ERR", "Intento de formatear el n\u00famero ''{0}'' utilizando el patr\u00f3n ''{1}''." }, { "ITERATOR_CLONE_ERR", "No se puede replicar el iterador ''{0}''." }, { "AXIS_SUPPORT_ERR", "Iterador para el eje ''{0}'' no soportado." }, { "TYPED_AXIS_SUPPORT_ERR", "Iterador para el eje escrito ''{0}'' no soportado." }, { "STRAY_ATTRIBUTE_ERR", "Atributo ''{0}'' fuera del elemento." }, { "STRAY_NAMESPACE_ERR", "Declaraci\u00f3n del espacio de nombres ''{0}''=''{1}'' fuera del elemento." }, { "NAMESPACE_PREFIX_ERR", "No se ha declarado el espacio de nombres para el prefijo ''{0}''." }, { "DOM_ADAPTER_INIT_ERR", "DOMAdapter creado mediante un tipo incorrecto de DOM origen." }, { "PARSER_DTD_SUPPORT_ERR", "El analizador SAX utilizado no maneja sucesos de declaraci\u00f3n DTD." }, { "NAMESPACES_SUPPORT_ERR", "El analizador SAX utilizado no tiene soporte de espacios de nombres XML." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "No se ha podido resolver la referencia de URI ''{0}''." } };
    }
}
