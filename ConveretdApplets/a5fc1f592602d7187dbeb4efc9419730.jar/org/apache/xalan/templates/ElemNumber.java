// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.util.NoSuchElementException;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xml.utils.res.XResourceBundle;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xml.utils.StringBufferPool;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import org.w3c.dom.Node;
import java.util.Locale;
import org.apache.xpath.NodeSetDTM;
import org.apache.xml.utils.NodeVector;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.transformer.CountersTable;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import org.xml.sax.SAXException;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xalan.transformer.DecimalToRoman;
import org.apache.xpath.XPath;

public class ElemNumber extends ElemTemplateElement
{
    private XPath m_countMatchPattern;
    private XPath m_fromMatchPattern;
    private int m_level;
    private XPath m_valueExpr;
    private AVT m_format_avt;
    private AVT m_lang_avt;
    private AVT m_lettervalue_avt;
    private AVT m_groupingSeparator_avt;
    private AVT m_groupingSize_avt;
    private static final DecimalToRoman[] m_romanConvertTable;
    private static char[] m_alphaCountTable;
    
    public ElemNumber() {
        this.m_countMatchPattern = null;
        this.m_fromMatchPattern = null;
        this.m_level = 1;
        this.m_valueExpr = null;
        this.m_format_avt = null;
        this.m_lang_avt = null;
        this.m_lettervalue_avt = null;
        this.m_groupingSeparator_avt = null;
        this.m_groupingSize_avt = null;
    }
    
    public void setCount(final XPath v) {
        this.m_countMatchPattern = v;
    }
    
    public XPath getCount() {
        return this.m_countMatchPattern;
    }
    
    public void setFrom(final XPath v) {
        this.m_fromMatchPattern = v;
    }
    
    public XPath getFrom() {
        return this.m_fromMatchPattern;
    }
    
    public void setLevel(final int v) {
        this.m_level = v;
    }
    
    public int getLevel() {
        return this.m_level;
    }
    
    public void setValue(final XPath v) {
        this.m_valueExpr = v;
    }
    
    public XPath getValue() {
        return this.m_valueExpr;
    }
    
    public void setFormat(final AVT v) {
        this.m_format_avt = v;
    }
    
    public AVT getFormat() {
        return this.m_format_avt;
    }
    
    public void setLang(final AVT v) {
        this.m_lang_avt = v;
    }
    
    public AVT getLang() {
        return this.m_lang_avt;
    }
    
    public void setLetterValue(final AVT v) {
        this.m_lettervalue_avt = v;
    }
    
    public AVT getLetterValue() {
        return this.m_lettervalue_avt;
    }
    
    public void setGroupingSeparator(final AVT v) {
        this.m_groupingSeparator_avt = v;
    }
    
    public AVT getGroupingSeparator() {
        return this.m_groupingSeparator_avt;
    }
    
    public void setGroupingSize(final AVT v) {
        this.m_groupingSize_avt = v;
    }
    
    public AVT getGroupingSize() {
        return this.m_groupingSize_avt;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        final Vector vnames = cstate.getVariableNames();
        if (null != this.m_countMatchPattern) {
            this.m_countMatchPattern.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_format_avt) {
            this.m_format_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_fromMatchPattern) {
            this.m_fromMatchPattern.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_groupingSeparator_avt) {
            this.m_groupingSeparator_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_groupingSize_avt) {
            this.m_groupingSize_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_lang_avt) {
            this.m_lang_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_lettervalue_avt) {
            this.m_lettervalue_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_valueExpr) {
            this.m_valueExpr.fixupVariables(vnames, cstate.getGlobalsSize());
        }
    }
    
    public int getXSLToken() {
        return 35;
    }
    
    public String getNodeName() {
        return "number";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        final int sourceNode = transformer.getXPathContext().getCurrentNode();
        final String countString = this.getCountString(transformer, sourceNode);
        try {
            transformer.getResultTreeHandler().characters(countString.toCharArray(), 0, countString.length());
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
        }
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    int findAncestor(final XPathContext xctxt, final XPath fromMatchPattern, final XPath countMatchPattern, int context, final ElemNumber namespaceContext) throws TransformerException {
        for (DTM dtm = xctxt.getDTM(context); -1 != context; context = dtm.getParent(context)) {
            if (null != fromMatchPattern && fromMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                break;
            }
            if (null != countMatchPattern && countMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                break;
            }
        }
        return context;
    }
    
    private int findPrecedingOrAncestorOrSelf(final XPathContext xctxt, final XPath fromMatchPattern, final XPath countMatchPattern, int context, final ElemNumber namespaceContext) throws TransformerException {
        final DTM dtm = xctxt.getDTM(context);
        while (-1 != context) {
            if (null != fromMatchPattern && fromMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                context = -1;
                break;
            }
            if (null != countMatchPattern && countMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                break;
            }
            final int prevSibling = dtm.getPreviousSibling(context);
            if (-1 == prevSibling) {
                context = dtm.getParent(context);
            }
            else {
                context = dtm.getLastChild(prevSibling);
                if (context != -1) {
                    continue;
                }
                context = prevSibling;
            }
        }
        return context;
    }
    
    XPath getCountMatchPattern(final XPathContext support, final int contextNode) throws TransformerException {
        XPath countMatchPattern = this.m_countMatchPattern;
        final DTM dtm = support.getDTM(contextNode);
        if (null == countMatchPattern) {
            switch (dtm.getNodeType(contextNode)) {
                case 1: {
                    MyPrefixResolver resolver;
                    if (dtm.getNamespaceURI(contextNode) == null) {
                        resolver = new MyPrefixResolver(dtm.getNode(contextNode), dtm, contextNode, false);
                    }
                    else {
                        resolver = new MyPrefixResolver(dtm.getNode(contextNode), dtm, contextNode, true);
                    }
                    countMatchPattern = new XPath(dtm.getNodeName(contextNode), this, resolver, 1, support.getErrorListener());
                    break;
                }
                case 2: {
                    countMatchPattern = new XPath("@" + dtm.getNodeName(contextNode), this, this, 1, support.getErrorListener());
                    break;
                }
                case 3:
                case 4: {
                    countMatchPattern = new XPath("text()", this, this, 1, support.getErrorListener());
                    break;
                }
                case 8: {
                    countMatchPattern = new XPath("comment()", this, this, 1, support.getErrorListener());
                    break;
                }
                case 9: {
                    countMatchPattern = new XPath("/", this, this, 1, support.getErrorListener());
                    break;
                }
                case 7: {
                    countMatchPattern = new XPath("pi(" + dtm.getNodeName(contextNode) + ")", this, this, 1, support.getErrorListener());
                    break;
                }
                default: {
                    countMatchPattern = null;
                    break;
                }
            }
        }
        return countMatchPattern;
    }
    
    String getCountString(final TransformerImpl transformer, final int sourceNode) throws TransformerException {
        long[] list = null;
        final XPathContext xctxt = transformer.getXPathContext();
        final CountersTable ctable = transformer.getCountersTable();
        if (null != this.m_valueExpr) {
            final XObject countObj = this.m_valueExpr.execute(xctxt, sourceNode, this);
            final long count = (long)Math.floor(countObj.num() + 0.5);
            list = new long[] { count };
        }
        else if (3 == this.m_level) {
            list = new long[] { ctable.countNode(xctxt, this, sourceNode) };
        }
        else {
            final NodeVector ancestors = this.getMatchingAncestors(xctxt, sourceNode, 1 == this.m_level);
            final int lastIndex = ancestors.size() - 1;
            if (lastIndex >= 0) {
                list = new long[lastIndex + 1];
                for (int i = lastIndex; i >= 0; --i) {
                    final int target = ancestors.elementAt(i);
                    list[lastIndex - i] = ctable.countNode(xctxt, this, target);
                }
            }
        }
        return (null != list) ? this.formatNumberList(transformer, list, sourceNode) : "";
    }
    
    public int getPreviousNode(final XPathContext xctxt, int pos) throws TransformerException {
        final XPath countMatchPattern = this.getCountMatchPattern(xctxt, pos);
        final DTM dtm = xctxt.getDTM(pos);
        if (3 == this.m_level) {
            final XPath fromMatchPattern = this.m_fromMatchPattern;
            while (-1 != pos) {
                int next = dtm.getPreviousSibling(pos);
                if (-1 == next) {
                    next = dtm.getParent(pos);
                    if (-1 != next && ((null != fromMatchPattern && fromMatchPattern.getMatchScore(xctxt, next) != Double.NEGATIVE_INFINITY) || dtm.getNodeType(next) == 9)) {
                        pos = -1;
                        break;
                    }
                }
                else {
                    int child = next;
                    while (-1 != child) {
                        child = dtm.getLastChild(next);
                        if (-1 != child) {
                            next = child;
                        }
                    }
                }
                pos = next;
                if (-1 != pos) {
                    if (null == countMatchPattern) {
                        break;
                    }
                    if (countMatchPattern.getMatchScore(xctxt, pos) != Double.NEGATIVE_INFINITY) {
                        break;
                    }
                    continue;
                }
            }
        }
        else {
            while (-1 != pos) {
                pos = dtm.getPreviousSibling(pos);
                if (-1 != pos) {
                    if (null == countMatchPattern) {
                        break;
                    }
                    if (countMatchPattern.getMatchScore(xctxt, pos) != Double.NEGATIVE_INFINITY) {
                        break;
                    }
                    continue;
                }
            }
        }
        return pos;
    }
    
    public int getTargetNode(final XPathContext xctxt, final int sourceNode) throws TransformerException {
        int target = -1;
        final XPath countMatchPattern = this.getCountMatchPattern(xctxt, sourceNode);
        if (3 == this.m_level) {
            target = this.findPrecedingOrAncestorOrSelf(xctxt, this.m_fromMatchPattern, countMatchPattern, sourceNode, this);
        }
        else {
            target = this.findAncestor(xctxt, this.m_fromMatchPattern, countMatchPattern, sourceNode, this);
        }
        return target;
    }
    
    NodeVector getMatchingAncestors(final XPathContext xctxt, int node, final boolean stopAtFirstFound) throws TransformerException {
        final NodeSetDTM ancestors = new NodeSetDTM(xctxt.getDTMManager());
        final XPath countMatchPattern = this.getCountMatchPattern(xctxt, node);
        for (DTM dtm = xctxt.getDTM(node); -1 != node; node = dtm.getParent(node)) {
            if (null != this.m_fromMatchPattern && this.m_fromMatchPattern.getMatchScore(xctxt, node) != Double.NEGATIVE_INFINITY && !stopAtFirstFound) {
                break;
            }
            if (null == countMatchPattern) {
                System.out.println("Programmers error! countMatchPattern should never be null!");
            }
            if (countMatchPattern.getMatchScore(xctxt, node) != Double.NEGATIVE_INFINITY) {
                ancestors.addElement(node);
                if (stopAtFirstFound) {
                    break;
                }
            }
        }
        return ancestors;
    }
    
    Locale getLocale(final TransformerImpl transformer, final int contextNode) throws TransformerException {
        Locale locale = null;
        if (null != this.m_lang_avt) {
            final XPathContext xctxt = transformer.getXPathContext();
            final String langValue = this.m_lang_avt.evaluate(xctxt, contextNode, this);
            if (null != langValue) {
                locale = new Locale(langValue.toUpperCase(), "");
                if (null == locale) {
                    transformer.getMsgMgr().warn(this, null, xctxt.getDTM(contextNode).getNode(contextNode), "WG_LOCALE_NOT_FOUND", new Object[] { langValue });
                    locale = Locale.getDefault();
                }
            }
        }
        else {
            locale = Locale.getDefault();
        }
        return locale;
    }
    
    private DecimalFormat getNumberFormatter(final TransformerImpl transformer, final int contextNode) throws TransformerException {
        final Locale locale = (Locale)this.getLocale(transformer, contextNode).clone();
        DecimalFormat formatter = null;
        final String digitGroupSepValue = (null != this.m_groupingSeparator_avt) ? this.m_groupingSeparator_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
        if (digitGroupSepValue != null && !this.m_groupingSeparator_avt.isSimple() && digitGroupSepValue.length() != 1) {
            transformer.getMsgMgr().warn(this, "WG_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "name", this.m_groupingSeparator_avt.getName() });
        }
        final String nDigitsPerGroupValue = (null != this.m_groupingSize_avt) ? this.m_groupingSize_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
        if (null != digitGroupSepValue && null != nDigitsPerGroupValue && digitGroupSepValue.length() > 0) {
            try {
                formatter = (DecimalFormat)NumberFormat.getNumberInstance(locale);
                formatter.setGroupingSize(Integer.valueOf(nDigitsPerGroupValue));
                final DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
                symbols.setGroupingSeparator(digitGroupSepValue.charAt(0));
                formatter.setDecimalFormatSymbols(symbols);
                formatter.setGroupingUsed(true);
            }
            catch (NumberFormatException ex) {
                formatter.setGroupingUsed(false);
            }
        }
        return formatter;
    }
    
    String formatNumberList(final TransformerImpl transformer, final long[] list, final int contextNode) throws TransformerException {
        final FastStringBuffer formattedNumber = StringBufferPool.get();
        String numStr;
        try {
            final int nNumbers = list.length;
            int numberWidth = 1;
            char numberType = '1';
            String lastSepString = null;
            String formatTokenString = null;
            String lastSep = ".";
            boolean isFirstToken = true;
            String formatValue = (null != this.m_format_avt) ? this.m_format_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
            if (null == formatValue) {
                formatValue = "1";
            }
            final NumberFormatStringTokenizer formatTokenizer = new NumberFormatStringTokenizer(formatValue);
            for (int i = 0; i < nNumbers; ++i) {
                if (formatTokenizer.hasMoreTokens()) {
                    String formatToken = formatTokenizer.nextToken();
                    if (Character.isLetterOrDigit(formatToken.charAt(formatToken.length() - 1))) {
                        numberWidth = formatToken.length();
                        numberType = formatToken.charAt(numberWidth - 1);
                    }
                    else if (formatTokenizer.isLetterOrDigitAhead()) {
                        formatTokenString = formatToken;
                        while (formatTokenizer.nextIsSep()) {
                            formatToken = formatTokenizer.nextToken();
                            formatTokenString += formatToken;
                        }
                        if (!isFirstToken) {
                            lastSep = formatTokenString;
                        }
                        formatToken = formatTokenizer.nextToken();
                        numberWidth = formatToken.length();
                        numberType = formatToken.charAt(numberWidth - 1);
                    }
                    else {
                        lastSepString = formatToken;
                        while (formatTokenizer.hasMoreTokens()) {
                            formatToken = formatTokenizer.nextToken();
                            lastSepString += formatToken;
                        }
                    }
                }
                if (null != formatTokenString && isFirstToken) {
                    formattedNumber.append(formatTokenString);
                }
                else if (null != lastSep && !isFirstToken) {
                    formattedNumber.append(lastSep);
                }
                this.getFormattedNumber(transformer, contextNode, numberType, numberWidth, list[i], formattedNumber);
                isFirstToken = false;
            }
            while (formatTokenizer.isLetterOrDigitAhead()) {
                formatTokenizer.nextToken();
            }
            if (lastSepString != null) {
                formattedNumber.append(lastSepString);
            }
            while (formatTokenizer.hasMoreTokens()) {
                final String formatToken = formatTokenizer.nextToken();
                formattedNumber.append(formatToken);
            }
            numStr = formattedNumber.toString();
        }
        finally {
            StringBufferPool.free(formattedNumber);
        }
        return numStr;
    }
    
    private void getFormattedNumber(final TransformerImpl transformer, final int contextNode, final char numberType, final int numberWidth, final long listElement, final FastStringBuffer formattedNumber) throws TransformerException {
        final String letterVal = (this.m_lettervalue_avt != null) ? this.m_lettervalue_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
        switch (numberType) {
            case 'A': {
                if (ElemNumber.m_alphaCountTable == null) {
                    final XResourceBundle thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", this.getLocale(transformer, contextNode));
                    final char[] alphabet = ElemNumber.m_alphaCountTable = (char[])thisBundle.getObject("alphabet");
                }
                this.int2alphaCount(listElement, ElemNumber.m_alphaCountTable, formattedNumber);
                break;
            }
            case 'a': {
                if (ElemNumber.m_alphaCountTable == null) {
                    final XResourceBundle thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", this.getLocale(transformer, contextNode));
                    final char[] alphabet = ElemNumber.m_alphaCountTable = (char[])thisBundle.getObject("alphabet");
                }
                final FastStringBuffer stringBuf = StringBufferPool.get();
                try {
                    this.int2alphaCount(listElement, ElemNumber.m_alphaCountTable, stringBuf);
                    formattedNumber.append(stringBuf.toString().toLowerCase(this.getLocale(transformer, contextNode)));
                    break;
                }
                finally {
                    StringBufferPool.free(stringBuf);
                }
            }
            case 'I': {
                formattedNumber.append(this.long2roman(listElement, true));
                break;
            }
            case 'i': {
                formattedNumber.append(this.long2roman(listElement, true).toLowerCase(this.getLocale(transformer, contextNode)));
                break;
            }
            case '\u3042': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "HA"));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                formattedNumber.append(this.int2singlealphaCount(listElement, (char[])thisBundle2.getObject("alphabet")));
                break;
            }
            case '\u3044': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "HI"));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                formattedNumber.append(this.int2singlealphaCount(listElement, (char[])thisBundle2.getObject("alphabet")));
                break;
            }
            case '\u30a2': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "A"));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                formattedNumber.append(this.int2singlealphaCount(listElement, (char[])thisBundle2.getObject("alphabet")));
                break;
            }
            case '\u30a4': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "I"));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                formattedNumber.append(this.int2singlealphaCount(listElement, (char[])thisBundle2.getObject("alphabet")));
                break;
            }
            case '\u4e00': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("zh", "CN"));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                this.int2alphaCount(listElement, (char[])thisBundle2.getObject("alphabet"), formattedNumber);
                break;
            }
            case '\u58f9': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("zh", "TW"));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                this.int2alphaCount(listElement, (char[])thisBundle2.getObject("alphabet"), formattedNumber);
                break;
            }
            case '\u0e51': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("th", ""));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                this.int2alphaCount(listElement, (char[])thisBundle2.getObject("alphabet"), formattedNumber);
                break;
            }
            case '\u05d0': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("he", ""));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                this.int2alphaCount(listElement, (char[])thisBundle2.getObject("alphabet"), formattedNumber);
                break;
            }
            case '\u10d0': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ka", ""));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                this.int2alphaCount(listElement, (char[])thisBundle2.getObject("alphabet"), formattedNumber);
                break;
            }
            case '\u03b1': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("el", ""));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                this.int2alphaCount(listElement, (char[])thisBundle2.getObject("alphabet"), formattedNumber);
                break;
            }
            case '\u0430': {
                final XResourceBundle thisBundle2 = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("cy", ""));
                if (letterVal != null && letterVal.equals("traditional")) {
                    formattedNumber.append(this.tradAlphaCount(listElement, thisBundle2));
                    break;
                }
                this.int2alphaCount(listElement, (char[])thisBundle2.getObject("alphabet"), formattedNumber);
                break;
            }
            default: {
                final DecimalFormat formatter = this.getNumberFormatter(transformer, contextNode);
                final String padString = (formatter == null) ? String.valueOf(0) : formatter.format(0L);
                final String numString = (formatter == null) ? String.valueOf(listElement) : formatter.format(listElement);
                for (int nPadding = numberWidth - numString.length(), k = 0; k < nPadding; ++k) {
                    formattedNumber.append(padString);
                }
                formattedNumber.append(numString);
                break;
            }
        }
    }
    
    String getZeroString() {
        return "0";
    }
    
    protected String int2singlealphaCount(final long val, final char[] table) {
        final int radix = table.length;
        if (val > radix) {
            return this.getZeroString();
        }
        return new Character(table[(int)val - 1]).toString();
    }
    
    protected void int2alphaCount(long val, final char[] aTable, final FastStringBuffer stringBuf) {
        final int radix = aTable.length;
        final char[] table = new char[aTable.length];
        int i;
        for (i = 0; i < aTable.length - 1; ++i) {
            table[i + 1] = aTable[i];
        }
        table[0] = aTable[i];
        final char[] buf = new char[100];
        int charPos = buf.length - 1;
        int lookupIndex = 1;
        long correction = 0L;
        do {
            correction = ((lookupIndex == 0 || (correction != 0L && lookupIndex == radix - 1)) ? (radix - 1) : 0L);
            lookupIndex = (int)(val + correction) % radix;
            val /= radix;
            if (lookupIndex == 0 && val == 0L) {
                break;
            }
            buf[charPos--] = table[lookupIndex];
        } while (val > 0L);
        stringBuf.append(buf, charPos + 1, buf.length - charPos - 1);
    }
    
    protected String tradAlphaCount(long val, final XResourceBundle thisBundle) {
        if (val > Long.MAX_VALUE) {
            this.error("ER_NUMBER_TOO_BIG");
            return "#error";
        }
        char[] table = null;
        int lookupIndex = 1;
        final char[] buf = new char[100];
        int charPos = 0;
        final int[] groups = (int[])thisBundle.getObject("numberGroups");
        final String[] tables = (String[])thisBundle.getObject("tables");
        final String numbering = thisBundle.getString("numbering");
        if (numbering.equals("multiplicative-additive")) {
            final String mult_order = thisBundle.getString("multiplierOrder");
            final long[] multiplier = (long[])thisBundle.getObject("multiplier");
            final char[] zeroChar = (char[])thisBundle.getObject("zero");
            int i;
            for (i = 0; i < multiplier.length && val < multiplier[i]; ++i) {}
            while (i < multiplier.length) {
                if (val < multiplier[i]) {
                    if (zeroChar.length == 0) {
                        ++i;
                    }
                    else {
                        if (buf[charPos - 1] != zeroChar[0]) {
                            buf[charPos++] = zeroChar[0];
                        }
                        ++i;
                    }
                }
                else if (val >= multiplier[i]) {
                    final long mult = val / multiplier[i];
                    val %= multiplier[i];
                    int k = 0;
                    while (k < groups.length) {
                        lookupIndex = 1;
                        if (mult / groups[k] <= 0L) {
                            ++k;
                        }
                        else {
                            final char[] THEletters = (char[])thisBundle.getObject(tables[k]);
                            table = new char[THEletters.length + 1];
                            int j;
                            for (j = 0; j < THEletters.length; ++j) {
                                table[j + 1] = THEletters[j];
                            }
                            table[0] = THEletters[j - 1];
                            lookupIndex = (int)mult / groups[k];
                            if (lookupIndex == 0 && mult == 0L) {
                                break;
                            }
                            final char multiplierChar = ((char[])thisBundle.getObject("multiplierChar"))[i];
                            if (lookupIndex >= table.length) {
                                return "#error";
                            }
                            if (mult_order.equals("precedes")) {
                                buf[charPos++] = multiplierChar;
                                buf[charPos++] = table[lookupIndex];
                                break;
                            }
                            if (lookupIndex != 1 || i != multiplier.length - 1) {
                                buf[charPos++] = table[lookupIndex];
                            }
                            buf[charPos++] = multiplierChar;
                            break;
                        }
                    }
                    ++i;
                }
                if (i >= multiplier.length) {
                    break;
                }
            }
        }
        for (int count = 0; count < groups.length; ++count) {
            if (val / groups[count] > 0L) {
                final char[] theletters = (char[])thisBundle.getObject(tables[count]);
                table = new char[theletters.length + 1];
                int l;
                for (l = 0; l < theletters.length; ++l) {
                    table[l + 1] = theletters[l];
                }
                table[0] = theletters[l - 1];
                lookupIndex = (int)val / groups[count];
                val %= groups[count];
                if (lookupIndex == 0 && val == 0L) {
                    break;
                }
                if (lookupIndex >= table.length) {
                    return "#error";
                }
                buf[charPos++] = table[lookupIndex];
            }
        }
        return new String(buf, 0, charPos);
    }
    
    protected String long2roman(long val, final boolean prefixesAreOK) {
        if (val <= 0L) {
            return this.getZeroString();
        }
        String roman = "";
        int place = 0;
        if (val <= 3999L) {
            while (true) {
                if (val < ElemNumber.m_romanConvertTable[place].m_postValue) {
                    if (prefixesAreOK && val >= ElemNumber.m_romanConvertTable[place].m_preValue) {
                        roman += ElemNumber.m_romanConvertTable[place].m_preLetter;
                        val -= ElemNumber.m_romanConvertTable[place].m_preValue;
                    }
                    ++place;
                    if (val <= 0L) {
                        break;
                    }
                    continue;
                }
                else {
                    roman += ElemNumber.m_romanConvertTable[place].m_postLetter;
                    val -= ElemNumber.m_romanConvertTable[place].m_postValue;
                }
            }
        }
        else {
            roman = "#error";
        }
        return roman;
    }
    
    public void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs) {
            if (null != this.m_countMatchPattern) {
                this.m_countMatchPattern.getExpression().callVisitors(this.m_countMatchPattern, visitor);
            }
            if (null != this.m_fromMatchPattern) {
                this.m_fromMatchPattern.getExpression().callVisitors(this.m_fromMatchPattern, visitor);
            }
            if (null != this.m_valueExpr) {
                this.m_valueExpr.getExpression().callVisitors(this.m_valueExpr, visitor);
            }
            if (null != this.m_format_avt) {
                this.m_format_avt.callVisitors(visitor);
            }
            if (null != this.m_groupingSeparator_avt) {
                this.m_groupingSeparator_avt.callVisitors(visitor);
            }
            if (null != this.m_groupingSize_avt) {
                this.m_groupingSize_avt.callVisitors(visitor);
            }
            if (null != this.m_lang_avt) {
                this.m_lang_avt.callVisitors(visitor);
            }
            if (null != this.m_lettervalue_avt) {
                this.m_lettervalue_avt.callVisitors(visitor);
            }
        }
        super.callChildVisitors(visitor, callAttrs);
    }
    
    static {
        m_romanConvertTable = new DecimalToRoman[] { new DecimalToRoman(1000L, "M", 900L, "CM"), new DecimalToRoman(500L, "D", 400L, "CD"), new DecimalToRoman(100L, "C", 90L, "XC"), new DecimalToRoman(50L, "L", 40L, "XL"), new DecimalToRoman(10L, "X", 9L, "IX"), new DecimalToRoman(5L, "V", 4L, "IV"), new DecimalToRoman(1L, "I", 1L, "I") };
        ElemNumber.m_alphaCountTable = null;
    }
    
    private class MyPrefixResolver implements PrefixResolver
    {
        DTM dtm;
        int handle;
        boolean handleNullPrefix;
        
        public MyPrefixResolver(final Node xpathExpressionContext, final DTM dtm, final int handle, final boolean handleNullPrefix) {
            this.dtm = dtm;
            this.handle = handle;
            this.handleNullPrefix = handleNullPrefix;
        }
        
        public String getNamespaceForPrefix(final String prefix) {
            return this.dtm.getNamespaceURI(this.handle);
        }
        
        public String getNamespaceForPrefix(final String prefix, final Node context) {
            return this.getNamespaceForPrefix(prefix);
        }
        
        public String getBaseIdentifier() {
            return ElemNumber.this.getBaseIdentifier();
        }
        
        public boolean handlesNullPrefixes() {
            return this.handleNullPrefix;
        }
    }
    
    class NumberFormatStringTokenizer
    {
        private int currentPosition;
        private int maxPosition;
        private String str;
        
        public NumberFormatStringTokenizer(final String str) {
            this.str = str;
            this.maxPosition = str.length();
        }
        
        public void reset() {
            this.currentPosition = 0;
        }
        
        public String nextToken() {
            if (this.currentPosition >= this.maxPosition) {
                throw new NoSuchElementException();
            }
            final int start = this.currentPosition;
            while (this.currentPosition < this.maxPosition && Character.isLetterOrDigit(this.str.charAt(this.currentPosition))) {
                ++this.currentPosition;
            }
            if (start == this.currentPosition && !Character.isLetterOrDigit(this.str.charAt(this.currentPosition))) {
                ++this.currentPosition;
            }
            return this.str.substring(start, this.currentPosition);
        }
        
        public boolean isLetterOrDigitAhead() {
            for (int pos = this.currentPosition; pos < this.maxPosition; ++pos) {
                if (Character.isLetterOrDigit(this.str.charAt(pos))) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean nextIsSep() {
            return !Character.isLetterOrDigit(this.str.charAt(this.currentPosition));
        }
        
        public boolean hasMoreTokens() {
            return this.currentPosition < this.maxPosition;
        }
        
        public int countTokens() {
            int count = 0;
            int currpos = this.currentPosition;
            while (currpos < this.maxPosition) {
                final int start = currpos;
                while (currpos < this.maxPosition && Character.isLetterOrDigit(this.str.charAt(currpos))) {
                    ++currpos;
                }
                if (start == currpos && !Character.isLetterOrDigit(this.str.charAt(currpos))) {
                    ++currpos;
                }
                ++count;
            }
            return count;
        }
    }
}
