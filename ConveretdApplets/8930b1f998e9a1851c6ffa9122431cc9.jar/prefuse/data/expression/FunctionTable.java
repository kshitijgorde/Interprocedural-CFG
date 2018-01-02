// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.visual.expression.ValidatedPredicate;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.visual.expression.QueryExpression;
import prefuse.visual.expression.SearchPredicate;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.visual.expression.HoverPredicate;
import prefuse.visual.expression.GroupSizeFunction;
import java.util.HashMap;

public class FunctionTable
{
    private static HashMap s_functionTable;
    
    public static boolean hasFunction(final String s) {
        return FunctionTable.s_functionTable.containsKey(s);
    }
    
    public static void addFunction(final String s, final Class clazz) {
        if (!Function.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Type argument must be a subclass of FunctionExpression.");
        }
        if (hasFunction(s)) {
            throw new IllegalArgumentException("Function with that name already exists");
        }
        final String lowerCase = s.toLowerCase();
        final String upperCase = s.toUpperCase();
        if (!s.equals(lowerCase) && !s.equals(upperCase)) {
            throw new IllegalArgumentException("Name can't have mixed case, try \"" + upperCase + "\" instead.");
        }
        FunctionTable.s_functionTable.put(lowerCase, clazz);
        FunctionTable.s_functionTable.put(upperCase, clazz);
    }
    
    public static Function createFunction(final String s) {
        final Class<Function> clazz = FunctionTable.s_functionTable.get(s);
        if (clazz == null) {
            throw new IllegalArgumentException("Unrecognized function name");
        }
        try {
            return clazz.newInstance();
        }
        catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new RuntimeException(ex2);
        }
    }
    
    static {
        FunctionTable.s_functionTable = new HashMap();
        addFunction("ROW", RowFunction.class);
        addFunction("ISNODE", IsNodeFunction.class);
        addFunction("ISEDGE", IsEdgeFunction.class);
        addFunction("DEGREE", DegreeFunction.class);
        addFunction("INDEGREE", InDegreeFunction.class);
        addFunction("OUTDEGREE", OutDegreeFunction.class);
        addFunction("CHILDCOUNT", ChildCountFunction.class);
        addFunction("TREEDEPTH", TreeDepthFunction.class);
        addFunction("ABS", AbsFunction.class);
        addFunction("ACOS", AcosFunction.class);
        addFunction("ASIN", AsinFunction.class);
        addFunction("ATAN", AtanFunction.class);
        addFunction("ATAN2", Atan2Function.class);
        addFunction("CEIL", CeilFunction.class);
        addFunction("CEILING", CeilFunction.class);
        addFunction("COS", CosFunction.class);
        addFunction("COT", CotFunction.class);
        addFunction("DEGREES", DegreesFunction.class);
        addFunction("E", EFunction.class);
        addFunction("EXP", ExpFunction.class);
        addFunction("FLOOR", FloorFunction.class);
        addFunction("LOG", LogFunction.class);
        addFunction("LOG2", Log2Function.class);
        addFunction("LOG10", Log10Function.class);
        addFunction("MAX", MaxFunction.class);
        addFunction("MIN", MaxFunction.class);
        addFunction("MOD", MaxFunction.class);
        addFunction("PI", PiFunction.class);
        addFunction("POW", PowFunction.class);
        addFunction("POWER", PowFunction.class);
        addFunction("RADIANS", RadiansFunction.class);
        addFunction("RAND", RandFunction.class);
        addFunction("ROUND", RoundFunction.class);
        addFunction("SIGN", SignFunction.class);
        addFunction("SIN", SinFunction.class);
        addFunction("SQRT", SqrtFunction.class);
        addFunction("SUM", SumFunction.class);
        addFunction("TAN", TanFunction.class);
        addFunction("SAFELOG10", SafeLog10Function.class);
        addFunction("SAFESQRT", SafeSqrtFunction.class);
        addFunction("CAP", CapFunction.class);
        addFunction("CONCAT", ConcatFunction.class);
        addFunction("CONCAT_WS", ConcatWsFunction.class);
        addFunction("FORMAT", FormatFunction.class);
        addFunction("INSERT", RPadFunction.class);
        addFunction("LENGTH", LengthFunction.class);
        addFunction("LOWER", LowerFunction.class);
        addFunction("LCASE", LowerFunction.class);
        addFunction("LEFT", LeftFunction.class);
        addFunction("LPAD", LPadFunction.class);
        addFunction("MID", SubstringFunction.class);
        addFunction("POSITION", PositionFunction.class);
        addFunction("REVERSE", ReverseFunction.class);
        addFunction("REPEAT", RepeatFunction.class);
        addFunction("REPLACE", ReplaceFunction.class);
        addFunction("RIGHT", RightFunction.class);
        addFunction("RPAD", RPadFunction.class);
        addFunction("SPACE", SpaceFunction.class);
        addFunction("SUBSTRING", SubstringFunction.class);
        addFunction("UPPER", UpperFunction.class);
        addFunction("UCASE", UpperFunction.class);
        addFunction("RGB", RGBFunction.class);
        addFunction("RGBA", RGBAFunction.class);
        addFunction("GRAY", GrayFunction.class);
        addFunction("HEX", HexFunction.class);
        addFunction("HSB", HSBFunction.class);
        addFunction("HSBA", HSBAFunction.class);
        addFunction("COLORINTERP", ColorInterpFunction.class);
        addFunction("GROUPSIZE", GroupSizeFunction.class);
        addFunction("HOVER", HoverPredicate.class);
        addFunction("INGROUP", InGroupPredicate.class);
        addFunction("MATCH", SearchPredicate.class);
        addFunction("QUERY", QueryExpression.class);
        addFunction("VISIBLE", VisiblePredicate.class);
        addFunction("VALIDATED", ValidatedPredicate.class);
    }
}
