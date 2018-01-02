// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_pt_BR extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_pt_BR.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "Erro interno de tempo de execu\u00e7\u00e3o em ''{0}''" }, { "RUN_TIME_COPY_ERR", "Erro de tempo de execu\u00e7\u00e3o ao executar <xsl:copy>." }, { "DATA_CONVERSION_ERR", "Convers\u00e3o inv\u00e1lida de ''{0}'' em ''{1}''." }, { "EXTERNAL_FUNC_ERR", "Fun\u00e7\u00e3o externa ''{0}'' n\u00e3o suportada por XSLTC." }, { "EQUALITY_EXPR_ERR", "Tipo de argumento desconhecido na express\u00e3o de igualdade. " }, { "INVALID_ARGUMENT_ERR", "Tipo de argumento inv\u00e1lido ''{0}'' na chamada para ''{1}''" }, { "FORMAT_NUMBER_ERR", "Tentando formatar o n\u00famero ''{0}'' utilizando o padr\u00e3o ''{1}''." }, { "ITERATOR_CLONE_ERR", "Imposs\u00edvel clonar iterador ''{0}''." }, { "AXIS_SUPPORT_ERR", "Iterador para eixo ''{0}'' n\u00e3o suportado. " }, { "TYPED_AXIS_SUPPORT_ERR", "Iterador para eixo digitado ''{0}'' n\u00e3o suportado. " }, { "STRAY_ATTRIBUTE_ERR", "Atributo ''{0}'' fora do elemento. " }, { "STRAY_NAMESPACE_ERR", "Declara\u00e7\u00e3o de namespace ''{0}''=''{1}'' fora do elemento. " }, { "NAMESPACE_PREFIX_ERR", "Namespace para prefixo ''{0}'' n\u00e3o foi declarado. " }, { "DOM_ADAPTER_INIT_ERR", "DOMAdapter criado utilizando tipo incorreto de DOM de origem." }, { "PARSER_DTD_SUPPORT_ERR", "O analisador SAX que est\u00e1 sendo utilizado n\u00e3o trata de eventos de declara\u00e7\u00e3o de DTD." }, { "NAMESPACES_SUPPORT_ERR", "O analisador SAX que est\u00e1 sendo utilizado n\u00e3o possui suporte para Namespaces XML." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "Imposs\u00edvel resolver a refer\u00eancia de URI ''{0}''." } };
    }
}
