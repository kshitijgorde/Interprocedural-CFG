// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_zh_TW extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_zh_TW.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "''{0}'' \u767c\u751f\u57f7\u884c\u6642\u671f\u5167\u90e8\u932f\u8aa4" }, { "RUN_TIME_COPY_ERR", "\u57f7\u884c <xsl:copy> \u6642\uff0c\u767c\u751f\u57f7\u884c\u6642\u671f\u932f\u8aa4\u3002" }, { "DATA_CONVERSION_ERR", "\u7121\u6cd5\u5c07 ''{0}'' \u8f49\u63db\u70ba ''{1}''\u3002" }, { "EXTERNAL_FUNC_ERR", "XSLTC \u4e0d\u652f\u63f4\u5916\u90e8\u51fd\u6578 ''{0}''\u3002" }, { "EQUALITY_EXPR_ERR", "\u76f8\u7b49\u8868\u793a\u5f0f\u4e2d\u5305\u542b\u4e0d\u660e\u7684\u5f15\u6578\u985e\u578b\u3002" }, { "INVALID_ARGUMENT_ERR", "\u5728\u547c\u53eb ''{1}'' \u4e2d\u7684\u5f15\u6578\u985e\u578b ''{0}'' \u7121\u6548" }, { "FORMAT_NUMBER_ERR", "\u5617\u8a66\u4f7f\u7528\u578b\u6a23 ''{1}'' \u683c\u5f0f\u5316\u6578\u5b57 ''{0}''\u3002" }, { "ITERATOR_CLONE_ERR", "\u7121\u6cd5\u8907\u88fd\u91cd\u8907\u9805\u76ee ''{0}''\u3002" }, { "AXIS_SUPPORT_ERR", "\u8ef8 ''{0}'' \u7684\u91cd\u8907\u9805\u76ee\u672a\u53d7\u652f\u63f4\u3002" }, { "TYPED_AXIS_SUPPORT_ERR", "\u8f38\u5165\u8ef8 ''{0}'' \u7684\u91cd\u8907\u9805\u76ee\u672a\u53d7\u652f\u63f4\u3002" }, { "STRAY_ATTRIBUTE_ERR", "\u5c6c\u6027 ''{0}'' \u8d85\u51fa\u5143\u7d20\u5916\u3002" }, { "STRAY_NAMESPACE_ERR", "\u540d\u7a31\u7a7a\u9593\u5ba3\u544a ''{0}''=''{1}'' \u8d85\u51fa\u5143\u7d20\u5916\u3002" }, { "NAMESPACE_PREFIX_ERR", "\u5b57\u9996 ''{0}'' \u7684\u540d\u7a31\u7a7a\u9593\u5c1a\u672a\u5ba3\u544a\u3002" }, { "DOM_ADAPTER_INIT_ERR", "\u5efa\u7acb DOMAdapter \u6642\u4f7f\u7528\u7684\u539f\u59cb\u6a94 DOM \u985e\u578b\u932f\u8aa4\u3002" }, { "PARSER_DTD_SUPPORT_ERR", "\u60a8\u4f7f\u7528\u7684 SAX \u5256\u6790\u5668\u7121\u6cd5\u8655\u7406 DTD \u5ba3\u544a\u4e8b\u4ef6\u3002" }, { "NAMESPACES_SUPPORT_ERR", "\u60a8\u4f7f\u7528\u7684 SAX \u5256\u6790\u5668\u4e0d\u652f\u63f4 XML \u540d\u7a31\u7a7a\u9593\u3002" }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "\u7121\u6cd5\u89e3\u6790 URI \u53c3\u7167 ''{0}''\u3002" } };
    }
}
