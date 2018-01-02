// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.functions.WrongNumberArgsException;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.SAXSourceLocator;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import org.apache.xpath.Expression;
import org.apache.xpath.objects.XString;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.text.DecimalFormat;
import org.apache.xml.utils.QName;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.functions.Function3Args;

public class FuncFormatNumb extends Function3Args
{
    static final long serialVersionUID = -8869935264870858636L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final ElemTemplateElement templElem = (ElemTemplateElement)xctxt.getNamespaceContext();
        final StylesheetRoot ss = templElem.getStylesheetRoot();
        DecimalFormat formatter = null;
        DecimalFormatSymbols dfs = null;
        final double num = this.getArg0().execute(xctxt).num();
        final String patternStr = this.getArg1().execute(xctxt).str();
        if (patternStr.indexOf(164) > 0) {
            ss.error("ER_CURRENCY_SIGN_ILLEGAL");
        }
        try {
            final Expression arg2Expr = this.getArg2();
            if (null != arg2Expr) {
                final String dfName = arg2Expr.execute(xctxt).str();
                final QName qname = new QName(dfName, xctxt.getNamespaceContext());
                dfs = ss.getDecimalFormatComposed(qname);
                if (null == dfs) {
                    this.warn(xctxt, "WG_NO_DECIMALFORMAT_DECLARATION", new Object[] { dfName });
                }
                else {
                    formatter = new DecimalFormat();
                    formatter.setDecimalFormatSymbols(dfs);
                    formatter.applyLocalizedPattern(patternStr);
                }
            }
            if (null == formatter) {
                dfs = ss.getDecimalFormatComposed(new QName(""));
                if (dfs != null) {
                    formatter = new DecimalFormat();
                    formatter.setDecimalFormatSymbols(dfs);
                    formatter.applyLocalizedPattern(patternStr);
                }
                else {
                    dfs = new DecimalFormatSymbols(Locale.US);
                    dfs.setInfinity("Infinity");
                    dfs.setNaN("NaN");
                    formatter = new DecimalFormat();
                    formatter.setDecimalFormatSymbols(dfs);
                    if (null != patternStr) {
                        formatter.applyLocalizedPattern(patternStr);
                    }
                }
            }
            return new XString(formatter.format(num));
        }
        catch (Exception iae) {
            templElem.error("ER_MALFORMED_FORMAT_STRING", new Object[] { patternStr });
            return XString.EMPTYSTRING;
        }
    }
    
    public void warn(final XPathContext xctxt, final String msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createWarning(msg, args);
        final ErrorListener errHandler = xctxt.getErrorListener();
        errHandler.warning(new TransformerException(formattedMsg, xctxt.getSAXLocator()));
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum > 3 || argNum < 2) {
            this.reportWrongNumberArgs();
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XSLMessages.createMessage("ER_TWO_OR_THREE", null));
    }
}
