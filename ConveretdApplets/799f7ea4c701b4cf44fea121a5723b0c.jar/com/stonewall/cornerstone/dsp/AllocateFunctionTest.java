// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.utility.ModelBuilder;
import junit.framework.TestCase;

public class AllocateFunctionTest extends TestCase
{
    public void testAllocate1() {
        final ModelBuilder builder = new ModelBuilder();
        try {
            DSP.init();
            final String config = "<en:deviceConfig>  <en:policy id='1'/>  <en:policy id='2'/>  <en:policy id='4'/></en:deviceConfig>";
            final IModelObject c = builder.buildModel(config);
            final IExpression expr = XPath.createExpression("dsp:allocate(./en:policy/@id)");
            final String next = expr.evaluateString(new Context(c));
            assertTrue(next.equals("3"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testAllocate2() {
        final ModelBuilder io = new ModelBuilder();
        try {
            DSP.init();
            final String config = "<en:deviceConfig></en:deviceConfig>";
            final IModelObject c = io.buildModel(config);
            final IExpression expr = XPath.createExpression("dsp:allocate(./en:policy/@id)");
            final String next = expr.evaluateString(new Context(c));
            assertTrue(next.equals("1"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testAllocate3() {
        final ModelBuilder io = new ModelBuilder();
        try {
            DSP.init();
            final String config = "<en:deviceConfig></en:deviceConfig>";
            final IModelObject c = io.buildModel(config);
            final IExpression expr = XPath.createExpression("dsp:allocate(./en:policy/@id, 4)");
            final String next = expr.evaluateString(new Context(c));
            assertTrue(next.equals("4"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testAllocate4() {
        final ModelBuilder io = new ModelBuilder();
        try {
            DSP.init();
            final String config = "<en:deviceConfig>  <en:policy id='1'/>  <en:policy id='2'/>  <en:policy id='3'/></en:deviceConfig>";
            final IModelObject c = io.buildModel(config);
            final IExpression expr = XPath.createExpression("dsp:allocate(./en:policy/@id)");
            final String next = expr.evaluateString(new Context(c));
            assertTrue(next.equals("4"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
