// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import java.net.URI;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObjectFactory;

public interface ICachingPolicy
{
    ICache getCache();
    
    void setFactory(final IModelObjectFactory p0);
    
    IModelObjectFactory getFactory();
    
    void defineNextStage(final IExpression p0, final ICachingPolicy p1, final boolean p2);
    
    void defineNextStage(final IModelObject p0);
    
    IExternalReference createExternalTree(final IModelObject p0, final boolean p1, final IExternalReference p2);
    
    void sync(final IExternalReference p0) throws CachingException;
    
    ITransaction transaction();
    
    void clear(final IExternalReference p0) throws CachingException;
    
    void insert(final IExternalReference p0, final String p1, final int p2, final boolean p3) throws CachingException;
    
    void insert(final IExternalReference p0, final IModelObject p1, final int p2, final boolean p3) throws CachingException;
    
    void update(final IExternalReference p0, final String p1) throws CachingException;
    
    void update(final IExternalReference p0, final IModelObject p1) throws CachingException;
    
    void remove(final IExternalReference p0, final String p1) throws CachingException;
    
    void remove(final IExternalReference p0, final IModelObject p1) throws CachingException;
    
    String[] getStaticAttributes();
    
    void readAttributeAccess(final IExternalReference p0, final String p1);
    
    void readChildrenAccess(final IExternalReference p0);
    
    void writeAttributeAccess(final IExternalReference p0, final String p1);
    
    void writeChildrenAccess(final IExternalReference p0);
    
    URI getURI(final IExternalReference p0) throws CachingException;
    
    String toString(final String p0);
}
