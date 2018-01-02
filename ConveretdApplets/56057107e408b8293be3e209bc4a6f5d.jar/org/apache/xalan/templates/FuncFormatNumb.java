// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.SAXSourceLocator;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import org.apache.xpath.Expression;
import org.apache.xpath.objects.XString;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import org.apache.xml.utils.QName;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.functions.WrongNumberArgsException;
import org.apache.xpath.functions.Function3Args;

public class FuncFormatNumb extends Function3Args
{
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum > 3 || argNum < 2) {
            throw new WrongNumberArgsException("3");
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final ElemTemplateElement templElem = (ElemTemplateElement)xctxt.getNamespaceContext();
        final StylesheetRoot ss = templElem.getStylesheetRoot();
        DecimalFormat formatter = null;
        DecimalFormatSymbols dfs = null;
        final double num = this.getArg0().execute(xctxt).num();
        final String patternStr = this.getArg1().execute(xctxt).str();
        if (patternStr.indexOf(164) > 0) {
            ss.error(82);
        }
        try {
            final Expression arg2Expr = this.getArg2();
            if (arg2Expr != null) {
                final String dfName = arg2Expr.execute(xctxt).str();
                final QName qname = new QName(dfName, xctxt.getNamespaceContext());
                dfs = ss.getDecimalFormatComposed(qname);
                if (dfs == null) {
                    this.warn(xctxt, 17, new Object[] { dfName });
                }
                else {
                    formatter = new DecimalFormat();
                    formatter.setDecimalFormatSymbols(dfs);
                    formatter.applyLocalizedPattern(patternStr);
                }
            }
            if (formatter == null) {
                if (ss.getDecimalFormatCount() > 0) {
                    dfs = ss.getDecimalFormatComposed(new QName(""));
                }
                if (dfs != null) {
                    formatter = new DecimalFormat();
                    formatter.setDecimalFormatSymbols(dfs);
                    formatter.applyLocalizedPattern(patternStr);
                }
                else {
                    dfs = new DecimalFormatSymbols();
                    dfs.setInfinity("Infinity");
                    dfs.setNaN("NaN");
                    formatter = new DecimalFormat();
                    formatter.setDecimalFormatSymbols(dfs);
                    if (patternStr != null) {
                        formatter.applyLocalizedPattern(patternStr);
                    }
                }
            }
            return new XString(formatter.format(num));
        }
        catch (Exception ex) {
            templElem.error(91, new Object[] { patternStr });
            return XString.EMPTYSTRING;
        }
    }
    
    public void warn(final XPathContext xctxt, final int msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createWarning(msg, args);
        final ErrorListener errHandler = xctxt.getErrorListener();
        errHandler.warning(new TransformerException(formattedMsg, xctxt.getSAXLocator()));
    }
}
