// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.util.NoSuchElementException;
import java.text.NumberFormat;
import org.apache.xpath.NodeSet;
import java.text.DecimalFormat;
import java.util.Locale;
import org.apache.xml.utils.res.XResourceBundle;
import org.apache.xml.utils.NodeVector;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.transformer.CountersTable;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.StringBufferPool;
import org.w3c.dom.Element;
import org.apache.xpath.XPathContext;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
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
    
    static {
        m_romanConvertTable = new DecimalToRoman[] { new DecimalToRoman(1000L, "M", 900L, "CM"), new DecimalToRoman(500L, "D", 400L, "CD"), new DecimalToRoman(100L, "C", 90L, "XC"), new DecimalToRoman(50L, "L", 40L, "XL"), new DecimalToRoman(10L, "X", 9L, "IX"), new DecimalToRoman(5L, "V", 4L, "IV"), new DecimalToRoman(1L, "I", 1L, "I") };
        ElemNumber.m_alphaCountTable = null;
    }
    
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
    
    public Node appendChild(final Node newChild) throws DOMException {
        this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        final String countString = this.getCountString(transformer, sourceNode);
        try {
            transformer.getResultTreeHandler().characters(countString.toCharArray(), 0, countString.length());
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
    
    Node findAncestor(final XPathContext xctxt, final XPath fromMatchPattern, final XPath countMatchPattern, Node context, final Element namespaceContext) throws TransformerException {
        while (context != null) {
            if (fromMatchPattern != null && fromMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                break;
            }
            if (countMatchPattern != null && countMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                break;
            }
            context = xctxt.getDOMHelper().getParentOfNode(context);
        }
        return context;
    }
    
    private Node findPrecedingOrAncestorOrSelf(final XPathContext xctxt, final XPath fromMatchPattern, final XPath countMatchPattern, Node context, final Element namespaceContext) throws TransformerException {
        while (context != null) {
            if (fromMatchPattern != null && fromMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                context = null;
                break;
            }
            if (countMatchPattern != null && countMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
                break;
            }
            final Node prevSibling = context.getPreviousSibling();
            if (prevSibling == null) {
                context = xctxt.getDOMHelper().getParentOfNode(context);
            }
            else {
                context = prevSibling.getLastChild();
                if (context != null) {
                    continue;
                }
                context = prevSibling;
            }
        }
        return context;
    }
    
    String formatNumberList(final TransformerImpl transformer, final int[] list, final Node contextNode) throws TransformerException {
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
            String formatValue = (this.m_format_avt != null) ? this.m_format_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
            if (formatValue == null) {
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
                            formatTokenString = String.valueOf(formatTokenString) + formatToken;
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
                            lastSepString = String.valueOf(lastSepString) + formatToken;
                        }
                    }
                }
                if (formatTokenString != null && isFirstToken) {
                    formattedNumber.append(formatTokenString);
                }
                else if (lastSep != null && !isFirstToken) {
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
    
    public XPath getCount() {
        return this.m_countMatchPattern;
    }
    
    XPath getCountMatchPattern(final XPathContext support, final Node contextNode) throws TransformerException {
        XPath countMatchPattern = this.m_countMatchPattern;
        if (countMatchPattern == null) {
            switch (contextNode.getNodeType()) {
                case 1: {
                    countMatchPattern = new XPath(contextNode.getNodeName(), this, this, 1, support.getErrorListener());
                    break;
                }
                case 2: {
                    countMatchPattern = new XPath("@" + contextNode.getNodeName(), this, this, 1, support.getErrorListener());
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
                    countMatchPattern = new XPath("pi(" + contextNode.getNodeName() + ")", this, this, 1, support.getErrorListener());
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
    
    String getCountString(final TransformerImpl transformer, final Node sourceNode) throws TransformerException {
        int[] list = null;
        final XPathContext xctxt = transformer.getXPathContext();
        final CountersTable ctable = transformer.getCountersTable();
        if (this.m_valueExpr != null) {
            final XObject countObj = this.m_valueExpr.execute(xctxt, sourceNode, this);
            final int count = (int)countObj.num();
            list = new int[] { count };
        }
        else if (this.m_level == 3) {
            list = new int[] { ctable.countNode(xctxt, this, sourceNode) };
        }
        else {
            final NodeVector ancestors = this.getMatchingAncestors(xctxt, sourceNode, this.m_level == 1);
            final int lastIndex = ancestors.size() - 1;
            if (lastIndex >= 0) {
                list = new int[lastIndex + 1];
                for (int i = lastIndex; i >= 0; --i) {
                    final Node target = ancestors.elementAt(i);
                    list[lastIndex - i] = ctable.countNode(xctxt, this, target);
                }
            }
        }
        return (list != null) ? this.formatNumberList(transformer, list, sourceNode) : "";
    }
    
    public AVT getFormat() {
        return this.m_format_avt;
    }
    
    private void getFormattedNumber(final TransformerImpl transformer, final Node contextNode, final char numberType, final int numberWidth, final int listElement, final FastStringBuffer formattedNumber) throws TransformerException {
        final DecimalFormat formatter = this.getNumberFormatter(transformer, contextNode);
        final String padString = formatter.format(0L);
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
                final String numString = formatter.format(listElement);
                for (int nPadding = numberWidth - numString.length(), k = 0; k < nPadding; ++k) {
                    formattedNumber.append(padString);
                }
                formattedNumber.append(numString);
                break;
            }
        }
    }
    
    public XPath getFrom() {
        return this.m_fromMatchPattern;
    }
    
    public AVT getGroupingSeparator() {
        return this.m_groupingSeparator_avt;
    }
    
    public AVT getGroupingSize() {
        return this.m_groupingSize_avt;
    }
    
    public AVT getLang() {
        return this.m_lang_avt;
    }
    
    public AVT getLetterValue() {
        return this.m_lettervalue_avt;
    }
    
    public int getLevel() {
        return this.m_level;
    }
    
    Locale getLocale(final TransformerImpl transformer, final Node contextNode) throws TransformerException {
        Locale locale = null;
        if (this.m_lang_avt != null) {
            final String langValue = this.m_lang_avt.evaluate(transformer.getXPathContext(), contextNode, this);
            if (langValue != null) {
                locale = new Locale(langValue.toUpperCase(), "");
                if (locale == null) {
                    transformer.getMsgMgr().warn(this, null, contextNode, 5, new Object[] { langValue });
                    locale = Locale.getDefault();
                }
            }
        }
        else {
            locale = Locale.getDefault();
        }
        return locale;
    }
    
    NodeVector getMatchingAncestors(final XPathContext xctxt, Node node, final boolean stopAtFirstFound) throws TransformerException {
        final NodeSet ancestors = new NodeSet();
        final XPath countMatchPattern = this.getCountMatchPattern(xctxt, node);
        while (node != null) {
            if (this.m_fromMatchPattern != null && this.m_fromMatchPattern.getMatchScore(xctxt, node) != Double.NEGATIVE_INFINITY && !stopAtFirstFound) {
                break;
            }
            if (countMatchPattern == null) {
                System.out.println("Programmers error! countMatchPattern should never be null!");
            }
            if (countMatchPattern.getMatchScore(xctxt, node) != Double.NEGATIVE_INFINITY) {
                ancestors.addElement(node);
                if (stopAtFirstFound) {
                    break;
                }
            }
            node = xctxt.getDOMHelper().getParentOfNode(node);
        }
        return ancestors;
    }
    
    public String getNodeName() {
        return "number";
    }
    
    private DecimalFormat getNumberFormatter(final TransformerImpl transformer, final Node contextNode) throws TransformerException {
        final Locale locale = (Locale)this.getLocale(transformer, contextNode).clone();
        final DecimalFormat formatter = (DecimalFormat)NumberFormat.getNumberInstance(locale);
        final String digitGroupSepValue = (this.m_groupingSeparator_avt != null) ? this.m_groupingSeparator_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
        final String nDigitsPerGroupValue = (this.m_groupingSize_avt != null) ? this.m_groupingSize_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
        if (digitGroupSepValue != null && nDigitsPerGroupValue != null) {
            try {
                formatter.setGroupingSize(Integer.valueOf(nDigitsPerGroupValue));
                formatter.getDecimalFormatSymbols().setGroupingSeparator(digitGroupSepValue.charAt(0));
                formatter.setGroupingUsed(true);
            }
            catch (NumberFormatException ex) {
                formatter.setGroupingUsed(false);
            }
        }
        return formatter;
    }
    
    public Node getPreviousNode(final XPathContext xctxt, Node pos) throws TransformerException {
        final XPath countMatchPattern = this.getCountMatchPattern(xctxt, pos);
        if (this.m_level == 3) {
            final XPath fromMatchPattern = this.m_fromMatchPattern;
            while (pos != null) {
                Node next = pos.getPreviousSibling();
                if (next == null) {
                    next = pos.getParentNode();
                    if (next != null && ((fromMatchPattern != null && fromMatchPattern.getMatchScore(xctxt, next) != Double.NEGATIVE_INFINITY) || next.getNodeType() == 9)) {
                        pos = null;
                        break;
                    }
                }
                else {
                    Node child = next;
                    while (child != null) {
                        child = next.getLastChild();
                        if (child != null) {
                            next = child;
                        }
                    }
                }
                pos = next;
                if (pos != null) {
                    if (countMatchPattern == null) {
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
            while (pos != null) {
                pos = pos.getPreviousSibling();
                if (pos != null) {
                    if (countMatchPattern == null) {
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
    
    public Node getTargetNode(final XPathContext xctxt, final Node sourceNode) throws TransformerException {
        Node target = null;
        final XPath countMatchPattern = this.getCountMatchPattern(xctxt, sourceNode);
        if (this.m_level == 3) {
            target = this.findPrecedingOrAncestorOrSelf(xctxt, this.m_fromMatchPattern, countMatchPattern, sourceNode, this);
        }
        else {
            target = this.findAncestor(xctxt, this.m_fromMatchPattern, countMatchPattern, sourceNode, this);
        }
        return target;
    }
    
    public XPath getValue() {
        return this.m_valueExpr;
    }
    
    public int getXSLToken() {
        return 35;
    }
    
    String getZeroString() {
        return "0";
    }
    
    protected void int2alphaCount(int val, final char[] aTable, final FastStringBuffer stringBuf) {
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
        int correction = 0;
        do {
            correction = ((lookupIndex == 0 || (correction != 0 && lookupIndex == radix - 1)) ? (radix - 1) : 0);
            lookupIndex = (val + correction) % radix;
            val /= radix;
            if (lookupIndex == 0 && val == 0) {
                break;
            }
            buf[charPos--] = table[lookupIndex];
        } while (val > 0);
        stringBuf.append(buf, charPos + 1, buf.length - charPos - 1);
    }
    
    protected String int2singlealphaCount(final int val, final char[] table) {
        final int radix = table.length;
        if (val > radix) {
            return this.getZeroString();
        }
        return new Character(table[val - 1]).toString();
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
                        roman = String.valueOf(roman) + ElemNumber.m_romanConvertTable[place].m_preLetter;
                        val -= ElemNumber.m_romanConvertTable[place].m_preValue;
                    }
                    ++place;
                    if (val <= 0L) {
                        break;
                    }
                    continue;
                }
                else {
                    roman = String.valueOf(roman) + ElemNumber.m_romanConvertTable[place].m_postLetter;
                    val -= ElemNumber.m_romanConvertTable[place].m_postValue;
                }
            }
        }
        else {
            roman = "#error";
        }
        return roman;
    }
    
    public void setCount(final XPath v) {
        this.m_countMatchPattern = v;
    }
    
    public void setFormat(final AVT v) {
        this.m_format_avt = v;
    }
    
    public void setFrom(final XPath v) {
        this.m_fromMatchPattern = v;
    }
    
    public void setGroupingSeparator(final AVT v) {
        this.m_groupingSeparator_avt = v;
    }
    
    public void setGroupingSize(final AVT v) {
        this.m_groupingSize_avt = v;
    }
    
    public void setLang(final AVT v) {
        this.m_lang_avt = v;
    }
    
    public void setLetterValue(final AVT v) {
        this.m_lettervalue_avt = v;
    }
    
    public void setLevel(final int v) {
        this.m_level = v;
    }
    
    public void setValue(final XPath v) {
        this.m_valueExpr = v;
    }
    
    protected String tradAlphaCount(int val, final XResourceBundle thisBundle) {
        char[] table = null;
        int lookupIndex = 1;
        final char[] buf = new char[100];
        int charPos = 0;
        final int[] groups = (int[])thisBundle.getObject("numberGroups");
        final String[] tables = (String[])thisBundle.getObject("tables");
        final String numbering = thisBundle.getString("numbering");
        if (numbering.equals("multiplicative-additive")) {
            final String mult_order = thisBundle.getString("multiplierOrder");
            final int[] multiplier = (int[])thisBundle.getObject("multiplier");
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
                    final int mult = val / multiplier[i];
                    val %= multiplier[i];
                    int k = 0;
                    while (k < groups.length) {
                        lookupIndex = 1;
                        if (mult / groups[k] <= 0) {
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
                            lookupIndex = mult / groups[k];
                            if (lookupIndex == 0 && mult == 0) {
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
            if (val / groups[count] > 0) {
                final char[] theletters = (char[])thisBundle.getObject(tables[count]);
                table = new char[theletters.length + 1];
                int l;
                for (l = 0; l < theletters.length; ++l) {
                    table[l + 1] = theletters[l];
                }
                table[0] = theletters[l - 1];
                lookupIndex = val / groups[count];
                val %= groups[count];
                if (lookupIndex == 0 && val == 0) {
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
    
    class NumberFormatStringTokenizer
    {
        private int currentPosition;
        private int maxPosition;
        private String str;
        
        public NumberFormatStringTokenizer(final String str) {
            this.str = str;
            this.maxPosition = str.length();
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
        
        public boolean hasMoreTokens() {
            return this.currentPosition < this.maxPosition;
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
        
        public void reset() {
            this.currentPosition = 0;
        }
    }
}
