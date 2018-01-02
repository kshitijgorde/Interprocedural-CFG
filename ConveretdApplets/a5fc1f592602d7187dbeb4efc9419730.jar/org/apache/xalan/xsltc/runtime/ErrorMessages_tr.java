// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_tr extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_tr.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "''{0}'' s\u0131n\u0131f\u0131nda \u00e7al\u0131\u015fma zaman\u0131 i\u00e7 hatas\u0131 " }, { "RUN_TIME_COPY_ERR", "<xsl:copy> y\u00fcr\u00fct\u00fcl\u00fcrken \u00e7al\u0131\u015fma zaman\u0131 hatas\u0131." }, { "DATA_CONVERSION_ERR", "''{0}'' tipinden ''{1}'' tipine d\u00f6n\u00fc\u015ft\u00fcrme ge\u00e7ersiz." }, { "EXTERNAL_FUNC_ERR", "''{0}'' d\u0131\u015f i\u015flevi XSLTC taraf\u0131ndan desteklenmiyor." }, { "EQUALITY_EXPR_ERR", "E\u015fitlik ifadesinde bilinmeyen ba\u011f\u0131ms\u0131z de\u011fi\u015fken tipi." }, { "INVALID_ARGUMENT_ERR", "''{1}'' i\u015flevi \u00e7a\u011fr\u0131s\u0131nda ''{0}'' ba\u011f\u0131ms\u0131z de\u011fi\u015fken tipi ge\u00e7ersiz " }, { "FORMAT_NUMBER_ERR", "''{0}'' say\u0131s\u0131n\u0131 ''{1}'' \u00f6r\u00fcnt\u00fcs\u00fcn\u00fc kullanarak bi\u00e7imleme giri\u015fimi." }, { "ITERATOR_CLONE_ERR", "''{0}'' yineleyicisinin e\u015fkopyas\u0131 yarat\u0131lam\u0131yor." }, { "AXIS_SUPPORT_ERR", "''{0}'' ekseni i\u00e7in yineleyici desteklenmiyor." }, { "TYPED_AXIS_SUPPORT_ERR", "Tip atanm\u0131\u015f ''{0}'' ekseni i\u00e7in yineleyici desteklenmiyor." }, { "STRAY_ATTRIBUTE_ERR", "''{0}'' \u00f6zniteli\u011fi \u00f6\u011fenin d\u0131\u015f\u0131nda." }, { "STRAY_NAMESPACE_ERR", "''{0}''=''{1}'' ad alan\u0131 bildirimi \u00f6\u011fenin d\u0131\u015f\u0131nda." }, { "NAMESPACE_PREFIX_ERR", "''{0}'' \u00f6nekine ili\u015fkin ad alan\u0131 bildirilmedi." }, { "DOM_ADAPTER_INIT_ERR", "DOMAdapter, yanl\u0131\u015f tipte kaynak DOM kullan\u0131larak yarat\u0131ld\u0131." }, { "PARSER_DTD_SUPPORT_ERR", "Kulland\u0131\u011f\u0131n\u0131z SAX ayr\u0131\u015ft\u0131r\u0131c\u0131s\u0131 DTD bildirim olaylar\u0131n\u0131 i\u015flemiyor." }, { "NAMESPACES_SUPPORT_ERR", "Kulland\u0131\u011f\u0131n\u0131z SAX ayr\u0131\u015ft\u0131r\u0131c\u0131s\u0131n\u0131n XML ad alanlar\u0131 deste\u011fi yok." }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "''{0}'' URI ba\u015fvurusu \u00e7\u00f6z\u00fclemedi." } };
    }
}
