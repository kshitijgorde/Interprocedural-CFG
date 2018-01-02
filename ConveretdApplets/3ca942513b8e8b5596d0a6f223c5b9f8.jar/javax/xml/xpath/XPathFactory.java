// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.xpath;

public abstract class XPathFactory
{
    public static final String DEFAULT_OBJECT_MODEL_URI = "http://java.sun.com/jaxp/xpath/dom";
    public static final String DEFAULT_PROPERTY_NAME = "javax.xml.xpath.XPathFactory";
    
    public static final XPathFactory newInstance() {
        try {
            return newInstance("http://java.sun.com/jaxp/xpath/dom");
        }
        catch (XPathFactoryConfigurationException ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public static final XPathFactory newInstance(final String s) throws XPathFactoryConfigurationException {
        if (s == null) {
            throw new NullPointerException("null uri in XPathFactory.newInstance(String uri)");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("uri cannot be empty string in XPathFactory.newInstance(String uri)");
        }
        try {
            return (XPathFactory)XPathFactoryFinder.find("javax.xml.xpath.XPathFactory", s, "org.apache.xpath.jaxp.XPathFactoryImpl");
        }
        catch (XPathFactoryFinder.ConfigurationError configurationError) {
            if (configurationError.getException() != null) {
                throw new XPathFactoryConfigurationException(configurationError.getException());
            }
            throw new XPathFactoryConfigurationException(configurationError.getMessage());
        }
    }
    
    public abstract boolean isObjectModelSupported(final String p0);
    
    public abstract void setFeature(final String p0, final boolean p1) throws XPathFactoryConfigurationException;
    
    public abstract boolean getFeature(final String p0) throws XPathFactoryConfigurationException;
    
    public abstract void setXPathVariableResolver(final XPathVariableResolver p0);
    
    public abstract void setXPathFunctionResolver(final XPathFunctionResolver p0);
    
    public abstract XPath newXPath();
}
