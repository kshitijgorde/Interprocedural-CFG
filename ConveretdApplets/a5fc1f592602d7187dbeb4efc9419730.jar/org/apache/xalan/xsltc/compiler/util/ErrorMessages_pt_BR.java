// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import java.util.ListResourceBundle;

public class ErrorMessages_pt_BR extends ListResourceBundle
{
    private static final String[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_pt_BR.m_errorMessages;
    }
    
    static {
        m_errorMessages = new String[][] { { "MULTIPLE_STYLESHEET_ERR", "Mais de uma p\u00e1gina de estilo definida no mesmo arquivo. " }, { "TEMPLATE_REDEF_ERR", "O gabarito ''{0}'' j\u00e1 est\u00e1 definido nesta p\u00e1gina de estilo." }, { "TEMPLATE_UNDEF_ERR", "O gabarito ''{0}'' n\u00e3o est\u00e1 definido nesta p\u00e1gina de estilo. " }, { "VARIABLE_REDEF_ERR", "A vari\u00e1vel ''{0}'' tem sua multiplica\u00e7\u00e3o definida no mesmo escopo. " }, { "VARIABLE_UNDEF_ERR", "A vari\u00e1vel ou par\u00e2metro ''{0}'' n\u00e3o est\u00e1 definido. " }, { "CLASS_NOT_FOUND_ERR", "Imposs\u00edvel localizar a classe ''{0}''." }, { "METHOD_NOT_FOUND_ERR", "Imposs\u00edvel localizar m\u00e9todo externo ''{0}'' (deve ser p\u00fablico)." }, { "ARGUMENT_CONVERSION_ERR", "Imposs\u00edvel converter tipo de argumento/retorno na chamada do m\u00e9todo para o m\u00e9todo ''{0}''" }, { "FILE_NOT_FOUND_ERR", "Arquivo ou URI ''{0}'' n\u00e3o encontrado." }, { "INVALID_URI_ERR", "URI inv\u00e1lido ''{0}''." }, { "FILE_ACCESS_ERR", "Imposs\u00edvel abrir arquivo ou URI ''{0}''." }, { "MISSING_ROOT_ERR", "Esperado elemento <xsl:stylesheet> ou <xsl:transform>." }, { "NAMESPACE_UNDEF_ERR", "Prefixo de namespace ''{0}'' n\u00e3o foi declarado." }, { "FUNCTION_RESOLVE_ERR", "Imposs\u00edvel resolver chamada para a fun\u00e7\u00e3o ''{0}''." }, { "NEED_LITERAL_ERR", "O argumento para ''{0}'' deve ser uma cadeia literal." }, { "XPATH_PARSER_ERR", "Erro ao analisar a express\u00e3o XPath ''{0}''." }, { "REQUIRED_ATTR_ERR", "O atributo requerido ''{0}'' est\u00e1 ausente. " }, { "ILLEGAL_CHAR_ERR", "Caractere inv\u00e1lido ''{0}'' na express\u00e3o XPath. " }, { "ILLEGAL_PI_ERR", "Atributo inv\u00e1lido ''{0}'' para instru\u00e7\u00e3o de processamento. " }, { "STRAY_ATTRIBUTE_ERR", "Atributo ''{0}'' fora do elemento. " }, { "ILLEGAL_ATTRIBUTE_ERR", "Atributo inv\u00e1lido ''{0}''." }, { "CIRCULAR_INCLUDE_ERR", "Import/include circular. A p\u00e1gina de estilo ''{0}'' j\u00e1 foi carregada. " }, { "RESULT_TREE_SORT_ERR", "Os fragmentos da \u00e1rvore de resultados n\u00e3o podem ser ordenados (os elementos <xsl:sort> ser\u00e3o ignorados). Voc\u00ea deve ordenar os n\u00f3s quando criar a \u00e1rvore de resultados. " }, { "SYMBOLS_REDEF_ERR", "A formata\u00e7\u00e3o decimal ''{0}'' j\u00e1 est\u00e1 definida. " }, { "XSL_VERSION_ERR", "A vers\u00e3o de XSL ''{0}'' n\u00e3o \u00e9 suportada por XSLTC." }, { "CIRCULAR_VARIABLE_ERR", "Refer\u00eancia \u00e0 vari\u00e1vel/par\u00e2metro circular em ''{0}''." }, { "ILLEGAL_BINARY_OP_ERR", "Operador desconhecido para express\u00e3o bin\u00e1ria. " }, { "ILLEGAL_ARG_ERR", "Argumentos inv\u00e1lidos para chamada de fun\u00e7\u00e3o. " }, { "DOCUMENT_ARG_ERR", "O segundo argumento para a fun\u00e7\u00e3o document() deve ser um node-set." }, { "MISSING_WHEN_ERR", "Pelo menos um elemento <xsl:when> \u00e9 requerido em <xsl:choose>." }, { "MULTIPLE_OTHERWISE_ERR", "Apenas um elemento <xsl:otherwise> \u00e9 permitido em <xsl:choose>." }, { "STRAY_OTHERWISE_ERR", "<xsl:otherwise> somente pode ser utilizado em <xsl:choose>." }, { "STRAY_WHEN_ERR", "<xsl:when> somente pode ser utilizado em <xsl:choose>." }, { "WHEN_ELEMENT_ERR", "Apenas os elementos <xsl:when> e <xsl:otherwise> s\u00e3o permitidos em <xsl:choose>." }, { "UNNAMED_ATTRIBSET_ERR", "<xsl:attribute-set> n\u00e3o possui o atributo 'name'." }, { "ILLEGAL_CHILD_ERR", "Elemento filho inv\u00e1lido. " }, { "ILLEGAL_ELEM_NAME_ERR", "Imposs\u00edvel chamar um elemento ''{0}''" }, { "ILLEGAL_ATTR_NAME_ERR", "Imposs\u00edvel chamar um atributo ''{0}''" }, { "ILLEGAL_TEXT_NODE_ERR", "Dados de texto fora do elemento <xsl:stylesheet> de n\u00edvel superior. " }, { "SAX_PARSER_CONFIG_ERR", "Analisador JAXP n\u00e3o configurado corretamente " }, { "INTERNAL_ERR", "Erro interno de XSLTC irrecuper\u00e1vel: ''{0}''" }, { "UNSUPPORTED_XSL_ERR", "Elemento XSL n\u00e3o suportado ''{0}''." }, { "UNSUPPORTED_EXT_ERR", "Extens\u00e3o XSLTC n\u00e3o reconhecida ''{0}''." }, { "MISSING_XSLT_URI_ERR", "O documento de entrada n\u00e3o \u00e9 uma p\u00e1gina de estilo (o namespace XSL n\u00e3o est\u00e1 declarado no elemento root)." }, { "MISSING_XSLT_TARGET_ERR", "N\u00e3o foi poss\u00edvel localizar o destino da p\u00e1gina de estilo ''{0}''." }, { "NOT_IMPLEMENTED_ERR", "N\u00e3o implementado: ''{0}''." }, { "NOT_STYLESHEET_ERR", "O documento de entrada n\u00e3o cont\u00e9m uma p\u00e1gina de estilo XSL." }, { "ELEMENT_PARSE_ERR", "N\u00e3o foi poss\u00edvel analisar o elemento ''{0}''" }, { "KEY_USE_ATTR_ERR", "O atributo use de <key> deve ser node, node-set, string ou number." }, { "OUTPUT_VERSION_ERR", "A vers\u00e3o do documento XML de sa\u00edda deve ser 1.0" }, { "ILLEGAL_RELAT_OP_ERR", "Operador desconhecido para express\u00e3o relacional " }, { "ATTRIBSET_UNDEF_ERR", "Tentando utilizar um conjunto de atributos n\u00e3o existente ''{0}''." }, { "ATTR_VAL_TEMPLATE_ERR", "Imposs\u00edvel analisar gabarito de valor de atributo ''{0}''." }, { "UNKNOWN_SIG_TYPE_ERR", "Tipo de dados desconhecido na assinatura para a classe ''{0}''." }, { "DATA_CONVERSION_ERR", "Imposs\u00edvel converter tipo de dados ''{0}'' em ''{1}''." }, { "NO_TRANSLET_CLASS_ERR", "Este Gabarito n\u00e3o cont\u00e9m uma defini\u00e7\u00e3o de classe translet v\u00e1lida. " }, { "NO_MAIN_TRANSLET_ERR", "Este Gabarito n\u00e3o cont\u00e9m uma classe com o nome ''{0}''." }, { "TRANSLET_CLASS_ERR", "N\u00e3o foi poss\u00edvel carregar a classe translet ''{0}''." }, { "TRANSLET_OBJECT_ERR", "Classe translet carregada, mas \u00e9 imposs\u00edvel criar a inst\u00e2ncia de translet. " }, { "ERROR_LISTENER_NULL_ERR", "Tentando definir ErrorListener para ''{0}'' como nulo " }, { "JAXP_UNKNOWN_SOURCE_ERR", "Apenas StreamSource, SAXSource e DOMSource s\u00e3o suportados por XSLTC" }, { "JAXP_NO_SOURCE_ERR", "O objeto Source passado para ''{0}'' n\u00e3o possui conte\u00fado. " }, { "JAXP_COMPILE_ERR", "N\u00e3o foi poss\u00edvel compilar a p\u00e1gina de estilo " }, { "JAXP_INVALID_ATTR_ERR", "TransformerFactory n\u00e3o reconhece o atributo ''{0}''." }, { "JAXP_SET_RESULT_ERR", "setResult() deve ser chamado antes de startDocument()." }, { "JAXP_NO_TRANSLET_ERR", "Transformer n\u00e3o possui nenhum objeto translet encapsulado. " }, { "JAXP_NO_HANDLER_ERR", "Nenhuma rotina de tratamento de sa\u00edda definida para o resultado de transforma\u00e7\u00e3o. " }, { "JAXP_NO_RESULT_ERR", "O objeto Result passado para ''{0}'' \u00e9 inv\u00e1lido. " }, { "JAXP_UNKNOWN_PROP_ERR", "Tentando acessar a propriedade Transformer inv\u00e1lida ''{0}''." }, { "SAX2DOM_ADAPTER_ERR", "N\u00e3o foi poss\u00edvel criar o adaptador SAX2DOM: ''{0}''." }, { "XSLTC_SOURCE_ERR", "XSLTCSource.build() foi chamado sem systemId estar definido. " }, { "COMPILE_STDIN_ERR", "A op\u00e7\u00e3o -i deve ser utilizada com a op\u00e7\u00e3o -o." }, { "COMPILE_USAGE_STR", "RESUMO\n   java org.apache.xalan.xsltc.cmdline.Compile [-o <sa\u00edda>]\n      [-d <diret\u00f3rio>] [-j <arquivojar>] [-p <pacote>]\n      [-n] [-x] [-s] [-u] [-v] [-h] { <folha de estilo> | -i }\n\nOP\u00c7\u00d5ES\n   -o <sa\u00edda> atribui o nome <sa\u00edda> ao translet\n gerado. Por padr\u00e3o, o nome do translet\n\u00e9 obtido do nome <folha de estilo>. Esta op\u00e7\u00e3o\nser\u00e1 ignorada se estiverem sendo compiladas v\u00e1rias p\u00e1ginas de estilo.\n   -d <diret\u00f3rio> especifica um diret\u00f3rio de destino para translet\n   -j <arquivo jar>   empacota classes translet em um arquivo jar do\nnome especificado como <arquivo jar>\n   -p <pacote>  especifica um prefixo de nome de pacote para todas as\nclasses translet geradas.\n   -n ativa a seq\u00fc\u00eancia de gabaritos (melhor comportamento padr\u00e3o\nna m\u00e9dia).\n   -x ativa a sa\u00edda de mensagem de depura\u00e7\u00e3o adicional\n  -s desativa a chamada de System.exit\n   -u interpreta argumentos <stylesheet> como URLs\n   -i for\u00e7a o compilador a ler a p\u00e1gina de estilo de stdin\n   -v imprime a vers\u00e3o do compilador\n   -h imprime esta instru\u00e7\u00e3o de uso\n" }, { "TRANSFORM_USAGE_STR", "RESUMO \n   java org.apache.xalan.xsltc.cmdline.Transform [-j <arquivo jar>]\n      [-x] [-s] [-n <itera\u00e7\u00f5es>] {-u <document_url> | <document>}\n      <classe> [<param1>=<valor1> ...]\n\n   utiliza a <classe> translet para transformar um documento XML \n  especificado como <documento>. A <classe> translet no\n  CLASSPATH do usu\u00e1rio ou no <arquivo jar> opcionalmente especificado.\nOP\u00c7\u00d5ES\n   -j <arquivo jar> especifica um arquivo jar file a partir do qual ser\u00e1 carregado o translet\n   -x ativa a sa\u00edda de mensagem de depura\u00e7\u00e3o adicional\n   -s desativa a chamada de System.exit\n   -n <itera\u00e7\u00f5es> executa os hor\u00e1rios de transforma\u00e7\u00e3o <itera\u00e7\u00f5es> e\n exibe informa\u00e7\u00f5es sobre cria\u00e7\u00e3o de perfil\n   -u <url_de_documento> especifica o documento XML de entrada como uma URL\n" }, { "STRAY_SORT_ERR", "<xsl:sort> somente pode ser utilizado em <xsl:for-each> ou <xsl:apply-templates>." }, { "UNSUPPORTED_ENCODING", "A codifica\u00e7\u00e3o de sa\u00edda ''{0}'' n\u00e3o \u00e9 suportada nesta JVM." }, { "SYNTAX_ERR", "Erro de sintaxe em ''{0}''." }, { "CONSTRUCTOR_NOT_FOUND", "Imposs\u00edvel localizar o construtor externo ''{0}''." }, { "NO_JAVA_FUNCT_THIS_REF", "O primeiro argumento para a fun\u00e7\u00e3o Java n\u00e3o est\u00e1tica ''{0}'' n\u00e3o \u00e9 uma refer\u00eancia de objeto v\u00e1lida. " }, { "TYPE_CHECK_ERR", "Erro ao verificar o tipo de express\u00e3o ''{0}''." }, { "TYPE_CHECK_UNK_LOC_ERR", "Erro ao verificar tipo de express\u00e3o em uma localiza\u00e7\u00e3o desconhecida. " }, { "ILLEGAL_CMDLINE_OPTION_ERR", "A op\u00e7\u00e3o da linha de comando ''{0}'' n\u00e3o \u00e9 v\u00e1lida. " }, { "CMDLINE_OPT_MISSING_ARG_ERR", "A op\u00e7\u00e3o da linha de comando ''{0}'' n\u00e3o cont\u00e9m um argumento requerido. " }, { "WARNING_PLUS_WRAPPED_MSG", "AVISO:  ''{0}''\n       :{1}" }, { "WARNING_MSG", "AVISO:  ''{0}''" }, { "FATAL_ERR_PLUS_WRAPPED_MSG", "ERRO FATAL:  ''{0}''\n           :{1}" }, { "FATAL_ERR_MSG", "ERRO FATAL:  ''{0}''" }, { "ERROR_PLUS_WRAPPED_MSG", "ERRO:  ''{0}''\n     :{1}" }, { "ERROR_MSG", "ERRO:  ''{0}''" }, { "TRANSFORM_WITH_TRANSLET_STR", "Transformar utilizando translet ''{0}'' " }, { "TRANSFORM_WITH_JAR_STR", "Transformar utilizando translet ''{0}'' de arquivo jar ''{1}''" }, { "COULD_NOT_CREATE_TRANS_FACT", "N\u00e3o foi poss\u00edvel criar uma inst\u00e2ncia da classe TransformerFactory ''{0}''." }, { "COMPILER_ERROR_KEY", "Erros do compilador:" }, { "COMPILER_WARNING_KEY", "Avisos do compilador:" }, { "RUNTIME_ERROR_KEY", "Erros de translet:" } };
    }
}