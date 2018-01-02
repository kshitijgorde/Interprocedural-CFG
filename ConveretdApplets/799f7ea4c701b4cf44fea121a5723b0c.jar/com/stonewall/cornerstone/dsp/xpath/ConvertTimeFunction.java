// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import java.util.Collections;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class ConvertTimeFunction extends Function
{
    public static final String name = "dsp:convert-time";
    
    @Override
    public String getName() {
        return "dsp:convert-time";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        this.assertType(context, 1, IExpression.ResultType.STRING);
        final IModelObject node = this.getArguments().get(0).queryFirst(context).cloneObject();
        final String conversion = this.getArguments().get(1).evaluateString();
        final String unit = Xlate.get(node, "unit", "seconds");
        if (unit.equals("none")) {
            throw new ExpressionException(this.getArguments().get(0), "No unit attribute on node.");
        }
        int value = Xlate.get(node, 0);
        if (value == 0) {
            node.setValue(0);
            return Collections.singletonList(node);
        }
        if (conversion.equals("seconds")) {
            value = this.convertToSeconds(value, unit);
        }
        if (conversion.equals("minutes")) {
            value = this.convertToMinutes(value, unit);
        }
        if (conversion.equals("hours")) {
            value = this.convertToHours(value, unit);
        }
        if (conversion.equals("days")) {
            value = this.convertToDays(value, unit);
        }
        node.setValue(value);
        node.setAttribute("unit", conversion);
        return Collections.singletonList(node);
    }
    
    private int convertToSeconds(final int value, final String unit) {
        int result = value;
        if (unit.equals("minutes")) {
            result = value * 60;
        }
        if (unit.equals("hours")) {
            result = value * 3600;
        }
        if (unit.equals("days")) {
            result = value * 86400;
        }
        return result;
    }
    
    private int convertToMinutes(final int value, final String unit) {
        int result = value;
        if (unit.equals("seconds")) {
            result = value / 60;
        }
        if (unit.equals("hours")) {
            result = value * 60;
        }
        if (unit.equals("days")) {
            result = value * 1440;
        }
        return result;
    }
    
    private int convertToHours(final int value, final String unit) {
        int result = value;
        if (unit.equals("seconds")) {
            result = value / 3600;
        }
        if (unit.equals("minutes")) {
            result = value / 60;
        }
        if (unit.equals("days")) {
            result = value * 24;
        }
        return result;
    }
    
    private int convertToDays(final int value, final String unit) {
        int result = value;
        if (unit.equals("seconds")) {
            result = value / 86400;
        }
        if (unit.equals("minutes")) {
            result = value / 1440;
        }
        if (unit.equals("hours")) {
            result = value / 24;
        }
        return result;
    }
    
    @Override
    public void bind(final IContext context) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void unbind(final IContext context) {
        throw new UnsupportedOperationException();
    }
}
