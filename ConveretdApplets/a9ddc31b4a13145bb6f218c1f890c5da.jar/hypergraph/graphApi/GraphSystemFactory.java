// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

import java.util.Properties;

public final class GraphSystemFactory
{
    public static GraphSystem createGraphSystem(final String s, final Properties properties) throws GraphException {
        try {
            return createGraphSystem(Class.forName(s), properties);
        }
        catch (ClassNotFoundException ex) {
            throw new GraphException("Cannot find class for GraphSystem. " + ex.getMessage());
        }
    }
    
    public static GraphSystem createGraphSystem(final Class clazz, final Properties properties) throws GraphException {
        try {
            final GraphSystem graphSystem = clazz.newInstance();
            graphSystem.setProperties(properties);
            return graphSystem;
        }
        catch (IllegalAccessException ex) {
            throw new GraphException("Cannot instantiate GraphSystem class " + clazz.getName() + " - " + ex.getMessage());
        }
        catch (InstantiationException ex2) {
            throw new GraphException("Cannot instantiate GraphSystem class " + clazz.getName() + " - " + ex2.getMessage());
        }
    }
    
    public static GraphSystem createGraphSystem(final Properties properties) throws GraphException {
        final String property = properties.getProperty("hypergraph.graphapi.graphsystem", null);
        if (property == null) {
            throw new GraphException("No value specified for property hypergraph.graphapi.graphsystem");
        }
        return createGraphSystem(property, properties);
    }
}
