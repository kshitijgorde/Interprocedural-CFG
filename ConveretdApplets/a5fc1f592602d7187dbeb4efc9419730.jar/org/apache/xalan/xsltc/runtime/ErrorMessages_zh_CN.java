// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.ListResourceBundle;

public class ErrorMessages_zh_CN extends ListResourceBundle
{
    private static final Object[][] m_errorMessages;
    
    public Object[][] getContents() {
        return ErrorMessages_zh_CN.m_errorMessages;
    }
    
    static {
        m_errorMessages = new Object[][] { { "RUN_TIME_INTERNAL_ERR", "\u201c{0}\u201d\u4e2d\u5b58\u5728\u8fd0\u884c\u65f6\u5185\u90e8\u9519\u8bef" }, { "RUN_TIME_COPY_ERR", "\u5728\u6267\u884c <xsl:copy> \u65f6\u53d1\u751f\u8fd0\u884c\u65f6\u9519\u8bef\u3002" }, { "DATA_CONVERSION_ERR", "\u4ece\u201c{0}\u201d\u5230\u201c{1}\u201d\u7684\u8f6c\u6362\u65e0\u6548\u3002" }, { "EXTERNAL_FUNC_ERR", "XSLTC \u4e0d\u652f\u6301\u5916\u90e8\u51fd\u6570\u201c{0}\u201d\u3002" }, { "EQUALITY_EXPR_ERR", "\u7b49\u5f0f\u8868\u8fbe\u5f0f\u4e2d\u7684\u81ea\u53d8\u91cf\u7c7b\u578b\u672a\u77e5\u3002" }, { "INVALID_ARGUMENT_ERR", "\u5728\u5bf9\u201c{1}\u201d\u7684\u8c03\u7528\u4e2d\u7684\u81ea\u53d8\u91cf\u7c7b\u578b\u201c{0}\u201d\u65e0\u6548" }, { "FORMAT_NUMBER_ERR", "\u8bd5\u56fe\u4f7f\u7528\u6a21\u5f0f\u201c{1}\u201d\u683c\u5f0f\u5316\u6570\u503c\u201c{0}\u201d\u3002" }, { "ITERATOR_CLONE_ERR", "\u65e0\u6cd5\u514b\u9686\u8fed\u4ee3\u5668\u201c{0}\u201d\u3002" }, { "AXIS_SUPPORT_ERR", "\u4e0d\u652f\u6301\u8f74\u201c{0}\u201d\u7684\u8fed\u4ee3\u5668\u3002" }, { "TYPED_AXIS_SUPPORT_ERR", "\u4e0d\u652f\u6301\u8f93\u5165\u7684\u8f74\u201c{0}\u201d\u7684\u8fed\u4ee3\u5668\u3002" }, { "STRAY_ATTRIBUTE_ERR", "\u5c5e\u6027\u201c{0}\u201d\u5728\u5143\u7d20\u5916\u3002" }, { "STRAY_NAMESPACE_ERR", "\u540d\u79f0\u7a7a\u95f4\u8bf4\u660e\u201c{0}\u201d=\u201c{1}\u201d\u5728\u5143\u7d20\u5916\u3002" }, { "NAMESPACE_PREFIX_ERR", "\u6ca1\u6709\u8bf4\u660e\u540d\u79f0\u7a7a\u95f4\u524d\u7f00\u201c{0}\u201d\u3002" }, { "DOM_ADAPTER_INIT_ERR", "\u4f7f\u7528\u9519\u8bef\u7c7b\u578b\u7684\u6e90 DOM \u521b\u5efa\u4e86 DOMAdapter\u3002" }, { "PARSER_DTD_SUPPORT_ERR", "\u6b63\u5728\u4f7f\u7528\u7684 SAX \u89e3\u6790\u5668\u4e0d\u5904\u7406 DTD \u8bf4\u660e\u4e8b\u4ef6\u3002" }, { "NAMESPACES_SUPPORT_ERR", "\u6b63\u5728\u4f7f\u7528\u7684 SAX \u89e3\u6790\u5668\u4e0d\u652f\u6301 XML \u540d\u79f0\u7a7a\u95f4\u3002" }, { "CANT_RESOLVE_RELATIVE_URI_ERR", "\u65e0\u6cd5\u89e3\u6790 URI \u5f15\u7528\u201c{0}\u201d\u3002" } };
    }
}
