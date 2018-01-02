// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xml.utils.QName;
import java.util.Hashtable;

public class XSLTSchema extends XSLTElementDef
{
    private Hashtable m_availElems;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemTextLiteral;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemLiteralResult;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemUnknown;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemValueOf;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemCopyOf;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemNumber;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemSort;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemWithParam;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemApplyTemplates;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemApplyImport;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemForEach;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemIf;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemWhen;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemOtherwise;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemChoose;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemAttribute;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemCallTemplate;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemVariable;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemParam;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemText;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemPI;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemElement;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemComment;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemCopy;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemMessage;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemFallback;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExsltFunction;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExsltFuncResult;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemTemplate;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExtensionScript;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExtensionDecl;
    
    XSLTSchema() {
        this.m_availElems = new Hashtable();
        this.build();
    }
    
    void build() {
        final XSLTAttributeDef hrefAttr = new XSLTAttributeDef(null, "href", 2, true, false, 1);
        final XSLTAttributeDef elementsAttr = new XSLTAttributeDef(null, "elements", 12, true, false, 1);
        final XSLTAttributeDef methodAttr = new XSLTAttributeDef(null, "method", 9, false, false, 1);
        final XSLTAttributeDef versionAttr = new XSLTAttributeDef(null, "version", 13, false, false, 1);
        final XSLTAttributeDef encodingAttr = new XSLTAttributeDef(null, "encoding", 1, false, false, 1);
        final XSLTAttributeDef omitXmlDeclarationAttr = new XSLTAttributeDef(null, "omit-xml-declaration", 8, false, false, 1);
        final XSLTAttributeDef standaloneAttr = new XSLTAttributeDef(null, "standalone", 8, false, false, 1);
        final XSLTAttributeDef doctypePublicAttr = new XSLTAttributeDef(null, "doctype-public", 1, false, false, 1);
        final XSLTAttributeDef doctypeSystemAttr = new XSLTAttributeDef(null, "doctype-system", 1, false, false, 1);
        final XSLTAttributeDef cdataSectionElementsAttr = new XSLTAttributeDef(null, "cdata-section-elements", 19, false, false, 1);
        final XSLTAttributeDef indentAttr = new XSLTAttributeDef(null, "indent", 8, false, false, 1);
        final XSLTAttributeDef mediaTypeAttr = new XSLTAttributeDef(null, "media-type", 1, false, false, 1);
        final XSLTAttributeDef nameAttrRequired = new XSLTAttributeDef(null, "name", 9, true, false, 1);
        final XSLTAttributeDef nameAVTRequired = new XSLTAttributeDef(null, "name", 18, true, true, 2);
        final XSLTAttributeDef nameAVT_NCNAMERequired = new XSLTAttributeDef(null, "name", 17, true, true, 2);
        final XSLTAttributeDef nameAttrOpt_ERROR = new XSLTAttributeDef(null, "name", 9, false, false, 1);
        final XSLTAttributeDef useAttr = new XSLTAttributeDef(null, "use", 5, true, false, 1);
        final XSLTAttributeDef namespaceAVTOpt = new XSLTAttributeDef(null, "namespace", 2, false, true, 2);
        final XSLTAttributeDef decimalSeparatorAttr = new XSLTAttributeDef(null, "decimal-separator", 6, false, 1, ".");
        final XSLTAttributeDef infinityAttr = new XSLTAttributeDef(null, "infinity", 1, false, 1, "Infinity");
        final XSLTAttributeDef minusSignAttr = new XSLTAttributeDef(null, "minus-sign", 6, false, 1, "-");
        final XSLTAttributeDef NaNAttr = new XSLTAttributeDef(null, "NaN", 1, false, 1, "NaN");
        final XSLTAttributeDef percentAttr = new XSLTAttributeDef(null, "percent", 6, false, 1, "%");
        final XSLTAttributeDef perMilleAttr = new XSLTAttributeDef(null, "per-mille", 6, false, false, 1);
        final XSLTAttributeDef zeroDigitAttr = new XSLTAttributeDef(null, "zero-digit", 6, false, 1, "0");
        final XSLTAttributeDef digitAttr = new XSLTAttributeDef(null, "digit", 6, false, 1, "#");
        final XSLTAttributeDef patternSeparatorAttr = new XSLTAttributeDef(null, "pattern-separator", 6, false, 1, ";");
        final XSLTAttributeDef groupingSeparatorAttr = new XSLTAttributeDef(null, "grouping-separator", 6, false, 1, ",");
        final XSLTAttributeDef useAttributeSetsAttr = new XSLTAttributeDef(null, "use-attribute-sets", 10, false, false, 1);
        final XSLTAttributeDef testAttrRequired = new XSLTAttributeDef(null, "test", 5, true, false, 1);
        final XSLTAttributeDef selectAttrRequired = new XSLTAttributeDef(null, "select", 5, true, false, 1);
        final XSLTAttributeDef selectAttrOpt = new XSLTAttributeDef(null, "select", 5, false, false, 1);
        final XSLTAttributeDef selectAttrDefNode = new XSLTAttributeDef(null, "select", 5, false, 1, "node()");
        final XSLTAttributeDef selectAttrDefDot = new XSLTAttributeDef(null, "select", 5, false, 1, ".");
        final XSLTAttributeDef matchAttrRequired = new XSLTAttributeDef(null, "match", 4, true, false, 1);
        final XSLTAttributeDef matchAttrOpt = new XSLTAttributeDef(null, "match", 4, false, false, 1);
        final XSLTAttributeDef priorityAttr = new XSLTAttributeDef(null, "priority", 7, false, false, 1);
        final XSLTAttributeDef modeAttr = new XSLTAttributeDef(null, "mode", 9, false, false, 1);
        final XSLTAttributeDef spaceAttr = new XSLTAttributeDef("http://www.w3.org/XML/1998/namespace", "space", false, false, false, 2, "default", 2, "preserve", 1);
        final XSLTAttributeDef spaceAttrLiteral = new XSLTAttributeDef("http://www.w3.org/XML/1998/namespace", "space", 2, false, true, 1);
        final XSLTAttributeDef stylesheetPrefixAttr = new XSLTAttributeDef(null, "stylesheet-prefix", 1, true, false, 1);
        final XSLTAttributeDef resultPrefixAttr = new XSLTAttributeDef(null, "result-prefix", 1, true, false, 1);
        final XSLTAttributeDef disableOutputEscapingAttr = new XSLTAttributeDef(null, "disable-output-escaping", 8, false, false, 1);
        final XSLTAttributeDef levelAttr = new XSLTAttributeDef(null, "level", false, false, false, 1, "single", 1, "multiple", 2, "any", 3);
        levelAttr.setDefault("single");
        final XSLTAttributeDef countAttr = new XSLTAttributeDef(null, "count", 4, false, false, 1);
        final XSLTAttributeDef fromAttr = new XSLTAttributeDef(null, "from", 4, false, false, 1);
        final XSLTAttributeDef valueAttr = new XSLTAttributeDef(null, "value", 5, false, false, 1);
        final XSLTAttributeDef formatAttr = new XSLTAttributeDef(null, "format", 1, false, true, 1);
        formatAttr.setDefault("1");
        final XSLTAttributeDef langAttr = new XSLTAttributeDef(null, "lang", 13, false, true, 1);
        final XSLTAttributeDef letterValueAttr = new XSLTAttributeDef(null, "letter-value", false, true, false, 1, "alphabetic", 1, "traditional", 2);
        final XSLTAttributeDef groupingSeparatorAVT = new XSLTAttributeDef(null, "grouping-separator", 6, false, true, 1);
        final XSLTAttributeDef groupingSizeAttr = new XSLTAttributeDef(null, "grouping-size", 7, false, true, 1);
        final XSLTAttributeDef dataTypeAttr = new XSLTAttributeDef(null, "data-type", false, true, true, 1, "text", 1, "number", 1);
        dataTypeAttr.setDefault("text");
        final XSLTAttributeDef orderAttr = new XSLTAttributeDef(null, "order", false, true, false, 1, "ascending", 1, "descending", 2);
        orderAttr.setDefault("ascending");
        final XSLTAttributeDef caseOrderAttr = new XSLTAttributeDef(null, "case-order", false, true, false, 1, "upper-first", 1, "lower-first", 2);
        final XSLTAttributeDef terminateAttr = new XSLTAttributeDef(null, "terminate", 8, false, false, 1);
        terminateAttr.setDefault("no");
        final XSLTAttributeDef xslExcludeResultPrefixesAttr = new XSLTAttributeDef("http://www.w3.org/1999/XSL/Transform", "exclude-result-prefixes", 20, false, false, 1);
        final XSLTAttributeDef xslExtensionElementPrefixesAttr = new XSLTAttributeDef("http://www.w3.org/1999/XSL/Transform", "extension-element-prefixes", 15, false, false, 1);
        final XSLTAttributeDef xslUseAttributeSetsAttr = new XSLTAttributeDef("http://www.w3.org/1999/XSL/Transform", "use-attribute-sets", 10, false, false, 1);
        final XSLTAttributeDef xslVersionAttr = new XSLTAttributeDef("http://www.w3.org/1999/XSL/Transform", "version", 13, false, false, 1);
        final XSLTElementDef charData = new XSLTElementDef(this, null, "text()", null, null, null, new ProcessorCharacters(), (XSLTSchema.class$org$apache$xalan$templates$ElemTextLiteral == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemTextLiteral = class$("org.apache.xalan.templates.ElemTextLiteral")) : XSLTSchema.class$org$apache$xalan$templates$ElemTextLiteral);
        charData.setType(2);
        final XSLTElementDef whiteSpaceOnly = new XSLTElementDef(this, null, "text()", null, null, null, null, (XSLTSchema.class$org$apache$xalan$templates$ElemTextLiteral == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemTextLiteral = class$("org.apache.xalan.templates.ElemTextLiteral")) : XSLTSchema.class$org$apache$xalan$templates$ElemTextLiteral);
        charData.setType(2);
        final XSLTAttributeDef resultAttr = new XSLTAttributeDef(null, "*", 3, false, true, 2);
        final XSLTAttributeDef xslResultAttr = new XSLTAttributeDef("http://www.w3.org/1999/XSL/Transform", "*", 1, false, false, 2);
        final XSLTElementDef[] templateElements = new XSLTElementDef[23];
        final XSLTElementDef[] templateElementsAndParams = new XSLTElementDef[24];
        final XSLTElementDef[] templateElementsAndSort = new XSLTElementDef[24];
        final XSLTElementDef[] exsltFunctionElements = new XSLTElementDef[24];
        final XSLTElementDef[] charTemplateElements = new XSLTElementDef[15];
        final XSLTElementDef resultElement = new XSLTElementDef(this, null, "*", null, templateElements, new XSLTAttributeDef[] { spaceAttrLiteral, xslExcludeResultPrefixesAttr, xslExtensionElementPrefixesAttr, xslUseAttributeSetsAttr, xslVersionAttr, xslResultAttr, resultAttr }, new ProcessorLRE(), (XSLTSchema.class$org$apache$xalan$templates$ElemLiteralResult == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemLiteralResult = class$("org.apache.xalan.templates.ElemLiteralResult")) : XSLTSchema.class$org$apache$xalan$templates$ElemLiteralResult, 20, true);
        final XSLTElementDef unknownElement = new XSLTElementDef(this, "*", "unknown", null, templateElementsAndParams, new XSLTAttributeDef[] { xslExcludeResultPrefixesAttr, xslExtensionElementPrefixesAttr, xslUseAttributeSetsAttr, xslVersionAttr, xslResultAttr, resultAttr }, new ProcessorUnknown(), (XSLTSchema.class$org$apache$xalan$templates$ElemUnknown == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemUnknown = class$("org.apache.xalan.templates.ElemUnknown")) : XSLTSchema.class$org$apache$xalan$templates$ElemUnknown, 20, true);
        final XSLTElementDef xslValueOf = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "value-of", null, null, new XSLTAttributeDef[] { selectAttrRequired, disableOutputEscapingAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemValueOf == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemValueOf = class$("org.apache.xalan.templates.ElemValueOf")) : XSLTSchema.class$org$apache$xalan$templates$ElemValueOf, 20, true);
        final XSLTElementDef xslCopyOf = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "copy-of", null, null, new XSLTAttributeDef[] { selectAttrRequired }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemCopyOf == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemCopyOf = class$("org.apache.xalan.templates.ElemCopyOf")) : XSLTSchema.class$org$apache$xalan$templates$ElemCopyOf, 20, true);
        final XSLTElementDef xslNumber = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "number", null, null, new XSLTAttributeDef[] { levelAttr, countAttr, fromAttr, valueAttr, formatAttr, langAttr, letterValueAttr, groupingSeparatorAVT, groupingSizeAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemNumber == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemNumber = class$("org.apache.xalan.templates.ElemNumber")) : XSLTSchema.class$org$apache$xalan$templates$ElemNumber, 20, true);
        final XSLTElementDef xslSort = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "sort", null, null, new XSLTAttributeDef[] { selectAttrDefDot, langAttr, dataTypeAttr, orderAttr, caseOrderAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemSort == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemSort = class$("org.apache.xalan.templates.ElemSort")) : XSLTSchema.class$org$apache$xalan$templates$ElemSort, 19, true);
        final XSLTElementDef xslWithParam = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "with-param", null, templateElements, new XSLTAttributeDef[] { nameAttrRequired, selectAttrOpt }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemWithParam == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemWithParam = class$("org.apache.xalan.templates.ElemWithParam")) : XSLTSchema.class$org$apache$xalan$templates$ElemWithParam, 19, true);
        final XSLTElementDef xslApplyTemplates = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "apply-templates", null, new XSLTElementDef[] { xslSort, xslWithParam }, new XSLTAttributeDef[] { selectAttrDefNode, modeAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemApplyTemplates == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemApplyTemplates = class$("org.apache.xalan.templates.ElemApplyTemplates")) : XSLTSchema.class$org$apache$xalan$templates$ElemApplyTemplates, 20, true);
        final XSLTElementDef xslApplyImports = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "apply-imports", null, null, new XSLTAttributeDef[0], new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemApplyImport == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemApplyImport = class$("org.apache.xalan.templates.ElemApplyImport")) : XSLTSchema.class$org$apache$xalan$templates$ElemApplyImport);
        final XSLTElementDef xslForEach = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "for-each", null, templateElementsAndSort, new XSLTAttributeDef[] { selectAttrRequired, spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemForEach == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemForEach = class$("org.apache.xalan.templates.ElemForEach")) : XSLTSchema.class$org$apache$xalan$templates$ElemForEach, true, false, true, 20, true);
        final XSLTElementDef xslIf = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "if", null, templateElements, new XSLTAttributeDef[] { testAttrRequired, spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemIf == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemIf = class$("org.apache.xalan.templates.ElemIf")) : XSLTSchema.class$org$apache$xalan$templates$ElemIf, 20, true);
        final XSLTElementDef xslWhen = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "when", null, templateElements, new XSLTAttributeDef[] { testAttrRequired, spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemWhen == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemWhen = class$("org.apache.xalan.templates.ElemWhen")) : XSLTSchema.class$org$apache$xalan$templates$ElemWhen, false, true, 1, true);
        final XSLTElementDef xslOtherwise = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "otherwise", null, templateElements, new XSLTAttributeDef[] { spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemOtherwise == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemOtherwise = class$("org.apache.xalan.templates.ElemOtherwise")) : XSLTSchema.class$org$apache$xalan$templates$ElemOtherwise, false, false, 2, false);
        final XSLTElementDef xslChoose = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "choose", null, new XSLTElementDef[] { xslWhen, xslOtherwise }, new XSLTAttributeDef[] { spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemChoose == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemChoose = class$("org.apache.xalan.templates.ElemChoose")) : XSLTSchema.class$org$apache$xalan$templates$ElemChoose, true, false, true, 20, true);
        final XSLTElementDef xslAttribute = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "attribute", null, charTemplateElements, new XSLTAttributeDef[] { nameAVTRequired, namespaceAVTOpt, spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemAttribute == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemAttribute = class$("org.apache.xalan.templates.ElemAttribute")) : XSLTSchema.class$org$apache$xalan$templates$ElemAttribute, 20, true);
        final XSLTElementDef xslCallTemplate = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "call-template", null, new XSLTElementDef[] { xslWithParam }, new XSLTAttributeDef[] { nameAttrRequired }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemCallTemplate == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemCallTemplate = class$("org.apache.xalan.templates.ElemCallTemplate")) : XSLTSchema.class$org$apache$xalan$templates$ElemCallTemplate, 20, true);
        final XSLTElementDef xslVariable = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "variable", null, templateElements, new XSLTAttributeDef[] { nameAttrRequired, selectAttrOpt }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemVariable == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemVariable = class$("org.apache.xalan.templates.ElemVariable")) : XSLTSchema.class$org$apache$xalan$templates$ElemVariable, 20, true);
        final XSLTElementDef xslParam = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "param", null, templateElements, new XSLTAttributeDef[] { nameAttrRequired, selectAttrOpt }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemParam == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemParam = class$("org.apache.xalan.templates.ElemParam")) : XSLTSchema.class$org$apache$xalan$templates$ElemParam, 19, true);
        final XSLTElementDef xslText = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "text", null, new XSLTElementDef[] { charData }, new XSLTAttributeDef[] { disableOutputEscapingAttr }, new ProcessorText(), (XSLTSchema.class$org$apache$xalan$templates$ElemText == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemText = class$("org.apache.xalan.templates.ElemText")) : XSLTSchema.class$org$apache$xalan$templates$ElemText, 20, true);
        final XSLTElementDef xslProcessingInstruction = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "processing-instruction", null, charTemplateElements, new XSLTAttributeDef[] { nameAVT_NCNAMERequired, spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemPI == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemPI = class$("org.apache.xalan.templates.ElemPI")) : XSLTSchema.class$org$apache$xalan$templates$ElemPI, 20, true);
        final XSLTElementDef xslElement = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "element", null, templateElements, new XSLTAttributeDef[] { nameAVTRequired, namespaceAVTOpt, useAttributeSetsAttr, spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemElement == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemElement = class$("org.apache.xalan.templates.ElemElement")) : XSLTSchema.class$org$apache$xalan$templates$ElemElement, 20, true);
        final XSLTElementDef xslComment = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "comment", null, charTemplateElements, new XSLTAttributeDef[] { spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemComment == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemComment = class$("org.apache.xalan.templates.ElemComment")) : XSLTSchema.class$org$apache$xalan$templates$ElemComment, 20, true);
        final XSLTElementDef xslCopy = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "copy", null, templateElements, new XSLTAttributeDef[] { spaceAttr, useAttributeSetsAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemCopy == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemCopy = class$("org.apache.xalan.templates.ElemCopy")) : XSLTSchema.class$org$apache$xalan$templates$ElemCopy, 20, true);
        final XSLTElementDef xslMessage = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "message", null, templateElements, new XSLTAttributeDef[] { terminateAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemMessage == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemMessage = class$("org.apache.xalan.templates.ElemMessage")) : XSLTSchema.class$org$apache$xalan$templates$ElemMessage, 20, true);
        final XSLTElementDef xslFallback = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "fallback", null, templateElements, new XSLTAttributeDef[] { spaceAttr }, new ProcessorTemplateElem(), (XSLTSchema.class$org$apache$xalan$templates$ElemFallback == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemFallback = class$("org.apache.xalan.templates.ElemFallback")) : XSLTSchema.class$org$apache$xalan$templates$ElemFallback, 20, true);
        final XSLTElementDef exsltFunction = new XSLTElementDef(this, "http://exslt.org/functions", "function", null, exsltFunctionElements, new XSLTAttributeDef[] { nameAttrRequired }, new ProcessorExsltFunction(), (XSLTSchema.class$org$apache$xalan$templates$ElemExsltFunction == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemExsltFunction = class$("org.apache.xalan.templates.ElemExsltFunction")) : XSLTSchema.class$org$apache$xalan$templates$ElemExsltFunction);
        final XSLTElementDef exsltResult = new XSLTElementDef(this, "http://exslt.org/functions", "result", null, templateElements, new XSLTAttributeDef[] { selectAttrOpt }, new ProcessorExsltFuncResult(), (XSLTSchema.class$org$apache$xalan$templates$ElemExsltFuncResult == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemExsltFuncResult = class$("org.apache.xalan.templates.ElemExsltFuncResult")) : XSLTSchema.class$org$apache$xalan$templates$ElemExsltFuncResult);
        int i = 0;
        templateElements[i++] = charData;
        templateElements[i++] = xslApplyTemplates;
        templateElements[i++] = xslCallTemplate;
        templateElements[i++] = xslApplyImports;
        templateElements[i++] = xslForEach;
        templateElements[i++] = xslValueOf;
        templateElements[i++] = xslCopyOf;
        templateElements[i++] = xslNumber;
        templateElements[i++] = xslChoose;
        templateElements[i++] = xslIf;
        templateElements[i++] = xslText;
        templateElements[i++] = xslCopy;
        templateElements[i++] = xslVariable;
        templateElements[i++] = xslMessage;
        templateElements[i++] = xslFallback;
        templateElements[i++] = xslProcessingInstruction;
        templateElements[i++] = xslComment;
        templateElements[i++] = xslElement;
        templateElements[i++] = xslAttribute;
        templateElements[i++] = resultElement;
        templateElements[i++] = unknownElement;
        templateElements[i++] = exsltFunction;
        templateElements[i++] = exsltResult;
        int k;
        for (k = 0; k < i; ++k) {
            templateElementsAndParams[k] = templateElements[k];
            templateElementsAndSort[k] = templateElements[k];
            exsltFunctionElements[k] = templateElements[k];
        }
        templateElementsAndParams[k] = xslParam;
        templateElementsAndSort[k] = xslSort;
        exsltFunctionElements[k] = xslParam;
        i = 0;
        charTemplateElements[i++] = charData;
        charTemplateElements[i++] = xslApplyTemplates;
        charTemplateElements[i++] = xslCallTemplate;
        charTemplateElements[i++] = xslApplyImports;
        charTemplateElements[i++] = xslForEach;
        charTemplateElements[i++] = xslValueOf;
        charTemplateElements[i++] = xslCopyOf;
        charTemplateElements[i++] = xslNumber;
        charTemplateElements[i++] = xslChoose;
        charTemplateElements[i++] = xslIf;
        charTemplateElements[i++] = xslText;
        charTemplateElements[i++] = xslCopy;
        charTemplateElements[i++] = xslVariable;
        charTemplateElements[i++] = xslMessage;
        charTemplateElements[i++] = xslFallback;
        final XSLTElementDef importDef = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "import", null, null, new XSLTAttributeDef[] { hrefAttr }, new ProcessorImport(), null, 1, true);
        final XSLTElementDef includeDef = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "include", null, null, new XSLTAttributeDef[] { hrefAttr }, new ProcessorInclude(), null, 20, true);
        final XSLTAttributeDef[] scriptAttrs = { new XSLTAttributeDef(null, "lang", 13, true, false, 2), new XSLTAttributeDef(null, "src", 2, false, false, 2) };
        final XSLTAttributeDef[] componentAttrs = { new XSLTAttributeDef(null, "prefix", 13, true, false, 2), new XSLTAttributeDef(null, "elements", 14, false, false, 2), new XSLTAttributeDef(null, "functions", 14, false, false, 2) };
        final XSLTElementDef[] topLevelElements = { includeDef, importDef, whiteSpaceOnly, unknownElement, new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "strip-space", null, null, new XSLTAttributeDef[] { elementsAttr }, new ProcessorStripSpace(), null, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "preserve-space", null, null, new XSLTAttributeDef[] { elementsAttr }, new ProcessorPreserveSpace(), null, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "output", null, null, new XSLTAttributeDef[] { methodAttr, versionAttr, encodingAttr, omitXmlDeclarationAttr, standaloneAttr, doctypePublicAttr, doctypeSystemAttr, cdataSectionElementsAttr, indentAttr, mediaTypeAttr, XSLTAttributeDef.m_foreignAttr }, new ProcessorOutputElem(), null, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "key", null, null, new XSLTAttributeDef[] { nameAttrRequired, matchAttrRequired, useAttr }, new ProcessorKey(), null, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "decimal-format", null, null, new XSLTAttributeDef[] { nameAttrOpt_ERROR, decimalSeparatorAttr, groupingSeparatorAttr, infinityAttr, minusSignAttr, NaNAttr, percentAttr, perMilleAttr, zeroDigitAttr, digitAttr, patternSeparatorAttr }, new ProcessorDecimalFormat(), null, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "attribute-set", null, new XSLTElementDef[] { xslAttribute }, new XSLTAttributeDef[] { nameAttrRequired, useAttributeSetsAttr }, new ProcessorAttributeSet(), null, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "variable", null, templateElements, new XSLTAttributeDef[] { nameAttrRequired, selectAttrOpt }, new ProcessorGlobalVariableDecl(), (XSLTSchema.class$org$apache$xalan$templates$ElemVariable == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemVariable = class$("org.apache.xalan.templates.ElemVariable")) : XSLTSchema.class$org$apache$xalan$templates$ElemVariable, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "param", null, templateElements, new XSLTAttributeDef[] { nameAttrRequired, selectAttrOpt }, new ProcessorGlobalParamDecl(), (XSLTSchema.class$org$apache$xalan$templates$ElemParam == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemParam = class$("org.apache.xalan.templates.ElemParam")) : XSLTSchema.class$org$apache$xalan$templates$ElemParam, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "template", null, templateElementsAndParams, new XSLTAttributeDef[] { matchAttrOpt, nameAttrOpt_ERROR, priorityAttr, modeAttr, spaceAttr }, new ProcessorTemplate(), (XSLTSchema.class$org$apache$xalan$templates$ElemTemplate == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemTemplate = class$("org.apache.xalan.templates.ElemTemplate")) : XSLTSchema.class$org$apache$xalan$templates$ElemTemplate, true, 20, true), new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "namespace-alias", null, null, new XSLTAttributeDef[] { stylesheetPrefixAttr, resultPrefixAttr }, new ProcessorNamespaceAlias(), null, 20, true), new XSLTElementDef(this, "http://xml.apache.org/xalan", "component", null, new XSLTElementDef[] { new XSLTElementDef(this, "http://xml.apache.org/xalan", "script", null, new XSLTElementDef[] { charData }, scriptAttrs, new ProcessorLRE(), (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionScript == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionScript = class$("org.apache.xalan.templates.ElemExtensionScript")) : XSLTSchema.class$org$apache$xalan$templates$ElemExtensionScript, 20, true) }, componentAttrs, new ProcessorLRE(), (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionDecl == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionDecl = class$("org.apache.xalan.templates.ElemExtensionDecl")) : XSLTSchema.class$org$apache$xalan$templates$ElemExtensionDecl), new XSLTElementDef(this, "http://xml.apache.org/xslt", "component", null, new XSLTElementDef[] { new XSLTElementDef(this, "http://xml.apache.org/xslt", "script", null, new XSLTElementDef[] { charData }, scriptAttrs, new ProcessorLRE(), (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionScript == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionScript = class$("org.apache.xalan.templates.ElemExtensionScript")) : XSLTSchema.class$org$apache$xalan$templates$ElemExtensionScript, 20, true) }, componentAttrs, new ProcessorLRE(), (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionDecl == null) ? (XSLTSchema.class$org$apache$xalan$templates$ElemExtensionDecl = class$("org.apache.xalan.templates.ElemExtensionDecl")) : XSLTSchema.class$org$apache$xalan$templates$ElemExtensionDecl), exsltFunction };
        final XSLTAttributeDef excludeResultPrefixesAttr = new XSLTAttributeDef(null, "exclude-result-prefixes", 20, false, false, 2);
        final XSLTAttributeDef extensionElementPrefixesAttr = new XSLTAttributeDef(null, "extension-element-prefixes", 15, false, false, 2);
        final XSLTAttributeDef idAttr = new XSLTAttributeDef(null, "id", 1, false, false, 2);
        final XSLTAttributeDef versionAttrRequired = new XSLTAttributeDef(null, "version", 13, true, false, 2);
        final XSLTElementDef stylesheetElemDef = new XSLTElementDef(this, "http://www.w3.org/1999/XSL/Transform", "stylesheet", "transform", topLevelElements, new XSLTAttributeDef[] { extensionElementPrefixesAttr, excludeResultPrefixesAttr, idAttr, versionAttrRequired, spaceAttr }, new ProcessorStylesheetElement(), null, true, -1, false);
        importDef.setElements(new XSLTElementDef[] { stylesheetElemDef, resultElement, unknownElement });
        includeDef.setElements(new XSLTElementDef[] { stylesheetElemDef, resultElement, unknownElement });
        this.build(null, null, null, new XSLTElementDef[] { stylesheetElemDef, whiteSpaceOnly, resultElement, unknownElement }, null, new ProcessorStylesheetDoc(), null);
    }
    
    public Hashtable getElemsAvailable() {
        return this.m_availElems;
    }
    
    void addAvailableElement(final QName elemName) {
        this.m_availElems.put(elemName, elemName);
    }
    
    public boolean elementAvailable(final QName elemName) {
        return this.m_availElems.containsKey(elemName);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
