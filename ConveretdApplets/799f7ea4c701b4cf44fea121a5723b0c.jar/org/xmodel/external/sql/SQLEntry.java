// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.external.IExternalReference;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.xmodel.external.CachingException;
import java.sql.PreparedStatement;
import java.util.List;
import org.xmodel.xpath.XPath;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;

public class SQLEntry
{
    String E;
    String[] C;
    String[] B;
    IExpression[] D;
    private static /* synthetic */ int[] A;
    
    public SQLEntry(final IModelObject modelObject) {
        this.E = Xlate.childGet(modelObject, "statement", "");
        final List<IModelObject> children = modelObject.getChildren("param");
        this.D = new IExpression[children.size()];
        for (int i = 0; i < children.size(); ++i) {
            this.D[i] = XPath.createExpression(Xlate.get((IModelObject)children.get(i), ""));
        }
    }
    
    public String getSQL() {
        return this.E;
    }
    
    public String[] getTables(final PreparedStatement preparedStatement) throws CachingException {
        if (this.C != null) {
            return this.C;
        }
        try {
            final ResultSetMetaData metaData = preparedStatement.getMetaData();
            this.C = new String[metaData.getColumnCount()];
            for (int i = 0; i < this.C.length; ++i) {
                this.C[i] = metaData.getTableName(i);
            }
            return this.C;
        }
        catch (SQLException ex) {
            throw new CachingException("Unable to get table names from metadata.", ex);
        }
    }
    
    public String[] getColumns(final PreparedStatement preparedStatement) throws CachingException {
        if (this.B != null) {
            return this.B;
        }
        try {
            final ResultSetMetaData metaData = preparedStatement.getMetaData();
            this.B = new String[metaData.getColumnCount()];
            for (int i = 0; i < this.B.length; ++i) {
                this.B[i] = metaData.getColumnName(i);
            }
            return this.B;
        }
        catch (SQLException ex) {
            throw new CachingException("Unable to get column names from metadata.", ex);
        }
    }
    
    public void assignParameters(final IExternalReference externalReference, final PreparedStatement preparedStatement) throws CachingException {
        try {
            for (int i = 0; i < this.D.length; ++i) {
                final IExpression expression = this.D[i];
                switch (A()[expression.getType().ordinal()]) {
                    case 1: {
                        throw new CachingException("Illegal return type (NODES) for expression: " + expression);
                    }
                    case 2: {
                        preparedStatement.setString(i, expression.evaluateString(new Context(externalReference)));
                        break;
                    }
                    case 3: {
                        final double evaluateNumber = expression.evaluateNumber(new Context(externalReference));
                        final int n = (int)Math.floor(evaluateNumber);
                        if (n == evaluateNumber) {
                            preparedStatement.setInt(i, n);
                            break;
                        }
                        preparedStatement.setDouble(i, evaluateNumber);
                        break;
                    }
                    case 4: {
                        preparedStatement.setBoolean(i, expression.evaluateBoolean(new Context(externalReference)));
                        break;
                    }
                }
            }
        }
        catch (SQLException ex) {
            throw new CachingException("Error setting sql parameters for reference: " + externalReference, ex);
        }
    }
    
    static /* synthetic */ int[] A() {
        final int[] a = SQLEntry.A;
        if (a != null) {
            return a;
        }
        final int[] a2 = new int[IExpression.ResultType.values().length];
        try {
            a2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            a2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            a2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            a2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            a2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return SQLEntry.A = a2;
    }
}
