// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform;

import java.util.Properties;

public interface Templates
{
    Transformer newTransformer() throws TransformerConfigurationException;
    
    Properties getOutputProperties();
}
