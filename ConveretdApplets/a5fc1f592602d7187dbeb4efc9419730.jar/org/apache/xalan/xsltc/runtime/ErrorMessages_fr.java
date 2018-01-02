// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_fr extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_fr.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "Erreur interne d''ex\u00e9cution dans ''{0}''" }, { "RUN_TIME_COPY_ERR", "Erreur d'ex\u00e9cution de <xsl:copy>." }, { "DATA_CONVERSION_ERR", "Conversion incorrecte de ''{0}'' en ''{1}''." }, { "EXTERNAL_FUNC_ERR", "Fonction externe ''{0}'' non prise en charge par XSLTC." }, { "EQUALITY_EXPR_ERR", "Type d'argument inconnu dans l'expression d'\u00e9galit\u00e9." }, { "INVALID_ARGUMENT_ERR", "Type d''argument inconnu ''{0}'' dans l''appel \u00e0 ''{1}''" }, { "FORMAT_NUMBER_ERR", "Tentative de formatage du nombre ''{0}'' avec le mod\u00e8le ''{1}''." }, { "ITERATOR_CLONE_ERR", "Clonage impossible de l''it\u00e9rateur ''{0}''." }, { "AXIS_SUPPORT_ERR", "It\u00e9rateur non pris en charge pour l''axe ''{0}''." }, { "TYPED_AXIS_SUPPORT_ERR", "It\u00e9rateur non pris en charge pour l''axe indiqu\u00e9 ''{0}''." }, { "STRAY_ATTRIBUTE_ERR", "L''attribut ''{0}'' est \u00e0 l''ext\u00e9rieur de l''\u00e9l\u00e9ment." }, { "STRAY_NAMESPACE_ERR", "La d\u00e9claration d''espace de noms ''{0}''=''{1}'' est \u00e0 l''ext\u00e9rieur de l''\u00e9l\u00e9ment." }, { "NAMESPACE_PREFIX_ERR", "L''espace de noms du pr\u00e9fixe ''{0}'' n''a pas \u00e9t\u00e9 d\u00e9clar\u00e9." }, { "DOM_ADAPTER_INIT_ERR", "DOMAdapter a \u00e9t\u00e9 cr\u00e9\u00e9 avec un type incorrect de source de DOM." }, { "PARSER_DTD_SUPPORT_ERR", "L''analyseur SAX que vous utilisez ne traite pas les \u00e9v\u00e9nements de d\u00e9claration DTD." }, { "NAMESPACES_SUPPORT_ERR", "L'analyseur SAX que vous utilisez ne prend pas en charge les espaces de nom XML." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "R\u00e9solution impossible de la r\u00e9f\u00e9rence \u00e0 l''URI ''{0}''." } };
    }
}
