// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_hu extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_hu.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "Fut\u00e1sidej\u0171 bels\u0151 hiba; helye: ''{0}''" }, { "RUN_TIME_COPY_ERR", "Fut\u00e1sidej\u0171 bels\u0151 hiba <xsl:copy> v\u00e9grehajt\u00e1sakor." }, { "DATA_CONVERSION_ERR", "\u00c9rv\u00e9nytelen konverzi\u00f3: ''{0}'' t\u00edpusr\u00f3l ''{1}'' t\u00edpusra." }, { "EXTERNAL_FUNC_ERR", "A(z) ''{0}'' k\u00fcls\u0151 f\u00fcggv\u00e9nyt nem t\u00e1mogatja az XSLTC." }, { "EQUALITY_EXPR_ERR", "Ismeretlen argumentumt\u00edpus tal\u00e1lhat\u00f3 az egyenl\u0151s\u00e9gi kifejez\u00e9sben." }, { "INVALID_ARGUMENT_ERR", "\u00c9rv\u00e9nytelen argumentumt\u00edpust (''{0}'') haszn\u00e1lt ''{1}'' h\u00edv\u00e1s\u00e1ban." }, { "FORMAT_NUMBER_ERR", "A(z) ''{0}'' sz\u00e1mot ''{1}'' minta alapj\u00e1n akarta form\u00e1zni." }, { "ITERATOR_CLONE_ERR", "Nem lehet kl\u00f3nozni a(z) ''{0}'' iter\u00e1tort." }, { "AXIS_SUPPORT_ERR", "A(z) ''{0}'' tengely iter\u00e1tora nem t\u00e1mogatott." }, { "TYPED_AXIS_SUPPORT_ERR", "A tipiz\u00e1lt ''{0}'' tengelyre iter\u00e1tor nem t\u00e1mogatott." }, { "STRAY_ATTRIBUTE_ERR", "A(z) ''{0}'' attrib\u00fatum k\u00edv\u00fcl van az elemen." }, { "STRAY_NAMESPACE_ERR", "A(z) ''{0}''=''{1}'' n\u00e9vt\u00e9r-deklar\u00e1ci\u00f3 k\u00edv\u00fcl esik az elemen." }, { "NAMESPACE_PREFIX_ERR", "A(z) ''{0}'' el\u0151tag n\u00e9vtere nem defini\u00e1lt." }, { "DOM_ADAPTER_INIT_ERR", "Nem megfelel\u0151 t\u00edpus\u00fa forr\u00e1s DOM haszn\u00e1lat\u00e1val j\u00f6tt l\u00e9tre a DOMAdapter." }, { "PARSER_DTD_SUPPORT_ERR", "Az \u00d6n \u00e1ltal haszn\u00e1lt SAX elemz\u0151 nem kezeli a DTD-deklar\u00e1ci\u00f3s esem\u00e9nyeket." }, { "NAMESPACES_SUPPORT_ERR", "Az \u00d6n \u00e1ltal haszn\u00e1lt SAX elemz\u0151 nem t\u00e1mogatja az XML n\u00e9vtereket." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "Nem lehet feloldani a(z) ''{0}'' URI hivatkoz\u00e1st." } };
    }
}
