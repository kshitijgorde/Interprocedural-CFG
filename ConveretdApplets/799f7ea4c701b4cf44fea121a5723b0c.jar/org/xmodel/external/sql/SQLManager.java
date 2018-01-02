// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import java.sql.PreparedStatement;
import java.sql.Connection;
import org.xmodel.external.CachingException;
import org.xmodel.IModelObject;

public interface SQLManager
{
    void configure(final IModelObject p0) throws CachingException;
    
    Connection getConnection() throws CachingException;
    
    PreparedStatement prepareStatement(final String p0) throws CachingException;
    
    PreparedStatement prepareStatement(final String p0, final int p1, final int p2, final int p3) throws CachingException;
}
