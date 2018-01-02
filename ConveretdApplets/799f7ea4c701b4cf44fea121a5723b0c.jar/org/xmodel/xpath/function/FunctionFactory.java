// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import java.util.Iterator;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.custom.StaticFunction;
import org.xmodel.xpath.function.custom.SortFunction;
import org.xmodel.xsd.SchemaFunction;
import org.xmodel.xpath.function.custom.PrintfFunction;
import org.xmodel.xpath.function.custom.ParseXmlFunction;
import org.xmodel.xpath.function.custom.NosyncFunction;
import org.xmodel.xpath.function.custom.HashCodeFunction;
import org.xmodel.xpath.function.custom.FormatFunction;
import org.xmodel.xpath.function.custom.EvaluateFunction;
import org.xmodel.xpath.function.custom.DereferenceFunction;
import org.xmodel.xpath.function.custom.CreatePathFunction;
import java.util.Hashtable;

public class FunctionFactory implements IFunctionFactory
{
    private static ThreadLocal<FunctionFactory> A;
    private Hashtable<String, Function> C;
    private Hashtable<String, Class> B;
    
    static {
        FunctionFactory.A = new ThreadLocal<FunctionFactory>();
    }
    
    private void A() {
        final Class[] array = { BooleanFunction.class, CeilingFunction.class, CollectionFunction.class, ConcatFunction.class, ContainsFunction.class, CountFunction.class, CreatePathFunction.class, DeepEqualFunction.class, DereferenceFunction.class, DistinctValuesFunction.class, DocFunction.class, EmptyFunction.class, EvaluateFunction.class, FalseFunction.class, FloorFunction.class, FormatFunction.class, IDFunction.class, HashCodeFunction.class, IndexOfFunction.class, LastFunction.class, LowercaseFunction.class, MatchesFunction.class, NotFunction.class, NumberFunction.class, NameFunction.class, NosyncFunction.class, ParseXmlFunction.class, PositionFunction.class, PrintfFunction.class, ReplaceFunction.class, ReverseFunction.class, RoundFunction.class, SchemaFunction.class, SortFunction.class, StartsWithFunction.class, StaticFunction.class, StringFunction.class, StringJoinFunction.class, StringLengthFunction.class, SubstringAfterFunction.class, SubstringBeforeFunction.class, SubstringFunction.class, SumFunction.class, TranslateFunction.class, TraceFunction.class, TrueFunction.class, UppercaseFunction.class };
        this.B = new Hashtable<String, Class>();
        for (int i = 0; i < array.length; ++i) {
            try {
                final IExpression expression = array[i].newInstance();
                this.B.put(expression.getName(), array[i]);
                this.B.put("fn:" + expression.getName(), array[i]);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        this.C = new Hashtable<String, Function>();
    }
    
    @Override
    public void register(final String s, final Class<? extends Function> clazz) {
        this.C.remove(s);
        this.B.put(s, clazz);
    }
    
    @Override
    public void register(final String s, final Function function) {
        this.B.remove(s);
        this.C.put(s, function);
    }
    
    @Override
    public IExpression createFunction(String substring) {
        if (substring.startsWith("fn:")) {
            substring = substring.substring(3);
        }
        try {
            final Class<IExpression> clazz = this.B.get(substring);
            if (clazz != null) {
                return clazz.newInstance();
            }
            final Function function = this.C.get(substring);
            if (function != null) {
                return (IExpression)function.clone();
            }
            return null;
        }
        catch (InstantiationException ex) {
            System.err.println(ex);
            return null;
        }
        catch (IllegalAccessException ex2) {
            System.err.println(ex2);
            return null;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.B.keySet()) {
            sb.append(s);
            sb.append(" = ");
            sb.append(this.B.get(s));
            sb.append('\n');
        }
        for (final String s2 : this.C.keySet()) {
            sb.append(s2);
            sb.append(" = ");
            sb.append(this.C.get(s2));
            sb.append('\n');
        }
        return sb.toString();
    }
    
    public static FunctionFactory getInstance() {
        FunctionFactory functionFactory = FunctionFactory.A.get();
        if (functionFactory == null) {
            functionFactory = new FunctionFactory();
            functionFactory.A();
            FunctionFactory.A.set(functionFactory);
        }
        return functionFactory;
    }
}
