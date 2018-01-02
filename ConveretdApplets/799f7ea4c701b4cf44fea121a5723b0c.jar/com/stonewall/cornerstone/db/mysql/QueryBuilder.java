// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import java.util.Date;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.utility.SecureDocument;
import org.xmodel.IModelObject;

public class QueryBuilder extends com.stonewall.cornerstone.db.QueryBuilder
{
    public QueryBuilder() {
    }
    
    public QueryBuilder(final String content) {
        super(content);
    }
    
    @Override
    public void setString(final String name, final IModelObject value) {
        if (value != null) {
            final SecureDocument sd = new SecureDocument(value);
            sd.encrypt();
            final ModelBuilder builder = new ModelBuilder();
            final String s = builder.writeModel(value, IXmlIO.Style.compact);
            this.setString(name, s);
        }
        else {
            this.set(name, "null");
        }
    }
    
    @Override
    public void setString(final String name, final Date value) {
        if (value != null) {
            super.setString(name, value);
        }
        else {
            this.set(name, "null");
        }
    }
    
    @Override
    public void setString(final String name, final String value) {
        if (value != null) {
            super.setString(name, value);
        }
        else {
            this.set(name, "null");
        }
    }
    
    @Override
    protected String escape(final String s) {
        return (s != null && s.length() > 0) ? s.replaceAll("\\'", "\\\\\\\\'") : s;
    }
}
