// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform;

public abstract class TransformerFactory
{
    public static TransformerFactory newInstance() throws TransformerFactoryConfigurationError {
        try {
            return (TransformerFactory)FactoryFinder.find("javax.xml.transform.TransformerFactory", "org.apache.xalan.processor.TransformerFactoryImpl");
        }
        catch (FactoryFinder.ConfigurationError configurationError) {
            throw new TransformerFactoryConfigurationError(configurationError.getException(), configurationError.getMessage());
        }
    }
    
    public abstract Transformer newTransformer(final Source p0) throws TransformerConfigurationException;
    
    public abstract Transformer newTransformer() throws TransformerConfigurationException;
    
    public abstract Templates newTemplates(final Source p0) throws TransformerConfigurationException;
    
    public abstract Source getAssociatedStylesheet(final Source p0, final String p1, final String p2, final String p3) throws TransformerConfigurationException;
    
    public abstract void setURIResolver(final URIResolver p0);
    
    public abstract URIResolver getURIResolver();
    
    public abstract boolean getFeature(final String p0);
    
    public abstract void setAttribute(final String p0, final Object p1) throws IllegalArgumentException;
    
    public abstract Object getAttribute(final String p0) throws IllegalArgumentException;
    
    public abstract void setErrorListener(final ErrorListener p0) throws IllegalArgumentException;
    
    public abstract ErrorListener getErrorListener();
    
    public abstract void setFeature(final String p0, final boolean p1) throws TransformerConfigurationException;
}
