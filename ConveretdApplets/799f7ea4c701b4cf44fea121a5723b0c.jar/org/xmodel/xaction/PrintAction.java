// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.List;
import org.xmodel.xml.IXmlIO;
import org.xmodel.xml.XmlIO;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;

public class PrintAction extends GuardedAction
{
    private String \u00f9;
    private String \u00fa;
    private IExpression \u00fb;
    private static /* synthetic */ int[] \u00f8;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.\u00f9 = Conventions.getVarName(root, false, "assign");
        this.\u00fa = Xlate.get(root, "style", "printable");
        this.\u00fb = xActionDocument.getExpression(root);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final XmlIO xmlIO = new XmlIO();
        xmlIO.setOutputStyle(IXmlIO.Style.valueOf(this.\u00fa));
        final StringBuilder sb = new StringBuilder();
        switch (B()[this.\u00fb.getType(context).ordinal()]) {
            case 1: {
                final Iterator<IModelObject> iterator = this.\u00fb.query(context, null).iterator();
                while (iterator.hasNext()) {
                    sb.append(xmlIO.write(iterator.next()));
                }
                final int length = sb.length() - 1;
                if (length >= 0 && sb.charAt(length) == '\n') {
                    sb.setLength(length);
                    break;
                }
                break;
            }
            case 2: {
                sb.append(this.\u00fb.evaluateString(context));
                break;
            }
            case 4: {
                sb.append(this.\u00fb.evaluateBoolean(context));
                break;
            }
            case 3: {
                sb.append(this.\u00fb.evaluateNumber(context));
                break;
            }
        }
        if (this.\u00f9 != null && context instanceof StatefulContext) {
            ((StatefulContext)context).set(this.\u00f9, sb.toString());
        }
        else {
            System.out.println(sb.toString());
        }
        return null;
    }
    
    static /* synthetic */ int[] B() {
        final int[] \u00f8 = PrintAction.\u00f8;
        if (\u00f8 != null) {
            return \u00f8;
        }
        final int[] \u00f82 = new int[IExpression.ResultType.values().length];
        try {
            \u00f82[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00f82[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00f82[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00f82[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u00f82[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return PrintAction.\u00f8 = \u00f82;
    }
}
