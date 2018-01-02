// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import org.xmodel.external.AbstractCachingPolicy;
import java.sql.Timestamp;
import java.sql.Date;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.sql.Connection;
import org.xmodel.ModelAlgorithms;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.CachingException;
import java.util.Iterator;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.xpath.XPath;
import java.util.Collection;
import org.xmodel.Xlate;
import java.util.ArrayList;
import org.xmodel.ModelObjectFactory;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import java.util.HashMap;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import java.util.List;
import org.xmodel.IModelObjectFactory;
import java.util.Map;
import org.xmodel.external.ConfiguredCachingPolicy;

public class SQLDirectCachingPolicy extends ConfiguredCachingPolicy
{
    private static Map<String, Class<? extends ISQLProvider>> R;
    private ISQLProvider S;
    private IModelObjectFactory O;
    private _B M;
    private _A[] Q;
    private String N;
    private List<String> P;
    private String T;
    
    public SQLDirectCachingPolicy() {
        this(new UnboundedCache());
    }
    
    public SQLDirectCachingPolicy(final ICache cache) {
        super(cache);
        this.M = new _B(cache);
        if (SQLDirectCachingPolicy.R == null) {
            (SQLDirectCachingPolicy.R = new HashMap<String, Class<? extends ISQLProvider>>()).put("mysql", MySQLProvider.class);
            SQLDirectCachingPolicy.R.put("sqlserver", SQLServerProvider.class);
        }
    }
    
    @Override
    public void configure(final IContext context, final IModelObject modelObject) throws CachingException {
        this.S = A(modelObject);
        this.O = new ModelObjectFactory();
        this.P = new ArrayList<String>(1);
        for (final IModelObject modelObject2 : modelObject.getChildren("key")) {
            final String value = Xlate.get(modelObject2, (String)null);
            if (Xlate.get(modelObject2, "primary", false)) {
                this.N = value;
            }
            else {
                this.P.add(value);
            }
        }
        final ArrayList<String> list = new ArrayList<String>();
        list.add("id");
        list.add(this.N);
        list.addAll((Collection<?>)this.P);
        this.M.setStaticAttributes(list.toArray(new String[0]));
        this.T = Xlate.childGet(modelObject, "rows", modelObject.getParent().getType());
        this.defineNextStage(XPath.createExpression(this.T), this.M, true);
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        System.err.println("sync: " + externalReference);
        this.syncTable(externalReference);
    }
    
    protected void syncTable(final IExternalReference externalReference) throws CachingException {
        try {
            this.Q = this.getColumns(externalReference);
            final PreparedStatement a = this.A(externalReference);
            final ResultSet executeQuery = a.executeQuery();
            final IModelObject cloneObject = externalReference.cloneObject();
            while (executeQuery.next()) {
                final IModelObject object = this.O.createObject(externalReference, this.T);
                object.setID(executeQuery.getString(1));
                for (int i = 0; i < this.P.size(); ++i) {
                    object.setAttribute(this.P.get(i), executeQuery.getObject(i + 2));
                }
                cloneObject.addChild(object);
            }
            this.update(externalReference, cloneObject);
            a.getConnection().close();
            a.close();
        }
        catch (SQLException ex) {
            throw new CachingException("Unable to cache reference: " + externalReference, ex);
        }
    }
    
    protected IModelObject createRowPrototype(final IExternalReference externalReference) throws CachingException {
        try {
            final IModelObject object = this.O.createObject(externalReference.getParent(), this.T);
            ModelAlgorithms.copyAttributes(externalReference, object);
            final PreparedStatement b = this.B(externalReference);
            final ResultSet executeQuery = b.executeQuery();
            if (executeQuery.next()) {
                for (int i = 0; i < this.Q.length; ++i) {
                    if (!this.Q[i].B.equals(this.N)) {
                        if (this.P.contains(this.Q[i].B)) {
                            object.setAttribute(this.Q[i].B, this.A(externalReference, null, executeQuery, i));
                        }
                        else {
                            final IModelObject object2 = this.getFactory().createObject(object, this.Q[i].B);
                            object2.setValue(this.A(externalReference, object2, executeQuery, i));
                            object.addChild(object2);
                        }
                    }
                }
            }
            b.getConnection().close();
            b.close();
            return object;
        }
        catch (SQLException ex) {
            throw new CachingException("Unable to cache reference: " + externalReference, ex);
        }
    }
    
    private Object A(final IModelObject modelObject, final IModelObject modelObject2, final ResultSet set, final int n) throws SQLException {
        switch (this.Q[n].C) {
            case 91: {
                return set.getDate(n + 1).getTime();
            }
            case -4: {
                if (modelObject2 == null) {
                    throw new CachingException("Blobs cannot be mapped to attributes.");
                }
                return new BlobAccess(this.A(modelObject, modelObject2));
            }
            default: {
                return set.getObject(n + 1);
            }
        }
    }
    
    @Override
    public void insert(final IExternalReference externalReference, final IModelObject modelObject, final int n, final boolean b) throws CachingException {
        externalReference.addChild(this.M.createExternalTree(modelObject, true, externalReference));
    }
    
    @Override
    public void remove(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
        if (modelObject.getParent() == externalReference) {
            modelObject.removeFromParent();
        }
    }
    
    private static ISQLProvider A(final IModelObject modelObject) throws CachingException {
        final String childGet = Xlate.childGet(modelObject, "provider", (String)null);
        if (childGet == null) {
            throw new CachingException("SQL database provider not defined.");
        }
        final Class<? extends ISQLProvider> clazz = SQLDirectCachingPolicy.R.get(childGet);
        if (clazz == null) {
            throw new CachingException(String.format("Provider %s not supported.", childGet));
        }
        try {
            final ISQLProvider isqlProvider = (ISQLProvider)clazz.newInstance();
            isqlProvider.configure(modelObject);
            return isqlProvider;
        }
        catch (Exception ex) {
            throw new CachingException(ex.getMessage());
        }
    }
    
    public _A[] getColumns(final IExternalReference externalReference) throws CachingException {
        try {
            final Connection connection = this.S.getConnection();
            final ResultSet columns = connection.getMetaData().getColumns(null, null, Xlate.get((IModelObject)externalReference, "table", ""), null);
            final ArrayList<_A> list = new ArrayList<_A>();
            while (columns.next()) {
                final _A a = new _A((_A)null);
                a.B = columns.getString("COLUMN_NAME");
                a.C = columns.getInt("DATA_TYPE");
                list.add(a);
            }
            connection.close();
            return list.toArray(new _A[0]);
        }
        catch (SQLException ex) {
            throw new CachingException("Unable to get column names for table: " + externalReference, ex);
        }
    }
    
    private PreparedStatement A(final IExternalReference externalReference) throws SQLException {
        final String value = Xlate.get((IModelObject)externalReference, "table", (String)null);
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(this.N);
        for (final String s : this.P) {
            sb.append(",");
            sb.append(s);
        }
        sb.append(" FROM ");
        sb.append(value);
        return this.S.prepareStatement(sb.toString());
    }
    
    private PreparedStatement B(final IExternalReference externalReference) throws SQLException {
        final String value = Xlate.get(externalReference.getParent(), "table", (String)null);
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append("FROM ");
        sb.append(value);
        sb.append(" WHERE ");
        sb.append(this.N);
        sb.append("=?");
        final PreparedStatement prepareStatement = this.S.prepareStatement(sb.toString());
        prepareStatement.setString(1, externalReference.getID());
        return prepareStatement;
    }
    
    private PreparedStatement A(final IModelObject modelObject, final IModelObject modelObject2) throws SQLException {
        final String value = Xlate.get(modelObject.getParent(), "table", (String)null);
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(modelObject2.getType());
        sb.append(" FROM ");
        sb.append(value);
        sb.append(" WHERE ");
        sb.append(this.N);
        sb.append("=?");
        final PreparedStatement prepareStatement = this.S.prepareStatement(sb.toString());
        prepareStatement.setString(1, modelObject.getID());
        return prepareStatement;
    }
    
    protected void setField(final PreparedStatement preparedStatement, final int n, final int n2, final IModelObject modelObject) throws CachingException, SQLException {
        final String b = this.Q[n].B;
        final Object o = this.P.contains(this.Q[n].B) ? modelObject.getAttribute(b) : modelObject.getFirstChild(b).getValue();
        if (o == null) {
            preparedStatement.setNull(n2, this.Q[n].C);
        }
        else {
            if (o instanceof File) {
                try {
                    final File file = (File)o;
                    preparedStatement.setBinaryStream(n2, new FileInputStream(file), (int)file.length());
                    return;
                }
                catch (IOException ex) {
                    throw new CachingException("Unable to open file in table row: " + modelObject + ", file=" + o, ex);
                }
            }
            if (this.Q[n].C == 91) {
                preparedStatement.setDate(n2, new Date(Long.parseLong(o.toString())));
            }
            else if (this.Q[n].C == 93) {
                preparedStatement.setTimestamp(n2, new Timestamp(Long.parseLong(o.toString())));
            }
            else {
                preparedStatement.setObject(n2, o);
            }
        }
    }
    
    public PreparedStatement createInsertStatement(final IExternalReference externalReference, final List<IModelObject> list) throws SQLException {
        final String value = Xlate.get((IModelObject)externalReference, "table", (String)null);
        if (this.Q == null) {
            this.Q = this.getColumns(externalReference);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(value);
        sb.append(" VALUES");
        sb.append("(?");
        for (int i = 1; i < this.Q.length; ++i) {
            sb.append(",?");
        }
        sb.append(")");
        final PreparedStatement prepareStatement = this.S.prepareStatement(sb.toString());
        for (final IModelObject modelObject : list) {
            prepareStatement.setString(1, modelObject.getID());
            for (int j = 0; j < this.Q.length; ++j) {
                if (!this.Q[j].B.equals(this.N)) {
                    this.setField(prepareStatement, j, j + 1, modelObject);
                }
            }
            prepareStatement.addBatch();
        }
        return prepareStatement;
    }
    
    public PreparedStatement createUpdateStatement(final IExternalReference externalReference, final List<IModelObject> list) throws SQLException {
        final String value = Xlate.get((IModelObject)externalReference, "table", (String)null);
        final StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(value);
        sb.append(" SET ");
        int n = 1;
        for (int i = 0; i < this.Q.length; ++i) {
            if (!this.Q[i].B.equals(this.N)) {
                if (n == 0) {
                    sb.append(",");
                }
                sb.append(this.Q[i].B);
                sb.append("=?");
                n = 0;
            }
        }
        sb.append(" WHERE ");
        sb.append(this.N);
        sb.append("=?");
        final PreparedStatement prepareStatement = this.S.prepareStatement(sb.toString());
        for (final IModelObject modelObject : list) {
            int n2 = 1;
            for (int j = 0; j < this.Q.length; ++j) {
                if (!this.Q[j].B.equals(this.N)) {
                    this.setField(prepareStatement, j, n2++, modelObject);
                }
            }
            prepareStatement.setString(n2, modelObject.getID());
            prepareStatement.addBatch();
        }
        return prepareStatement;
    }
    
    public PreparedStatement createDeleteStatement(final IExternalReference externalReference, final List<IModelObject> list) throws SQLException {
        final String value = Xlate.get((IModelObject)externalReference, "table", (String)null);
        final StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(value);
        sb.append(" WHERE ");
        sb.append(this.N);
        sb.append("=?");
        final PreparedStatement prepareStatement = this.S.prepareStatement(sb.toString());
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            prepareStatement.setString(1, iterator.next().getID());
            prepareStatement.addBatch();
        }
        return prepareStatement;
    }
    
    private class _A
    {
        String B;
        int C;
        
        @Override
        public String toString() {
            return String.valueOf(this.B) + ":" + this.C;
        }
    }
    
    private class _B extends AbstractCachingPolicy
    {
        protected _B(final ICache cache) {
            super(cache);
        }
        
        public void setStaticAttributes(final String[] staticAttributes) {
            super.setStaticAttributes(staticAttributes);
        }
        
        @Override
        public void sync(final IExternalReference externalReference) throws CachingException {
            System.err.println("Syncing row: " + externalReference.getID());
            this.update(externalReference, SQLDirectCachingPolicy.this.createRowPrototype(externalReference));
        }
        
        @Override
        public void insert(final IExternalReference externalReference, final IModelObject modelObject, final int n, final boolean b) throws CachingException {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void remove(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
            throw new UnsupportedOperationException();
        }
    }
}
