// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class ProcessorFactory
{
    private static Logger logger;
    private static final String walkerPackage = "org.jdesktop.j3d.loaders.collada.xml_walker.";
    
    static {
        ProcessorFactory.logger = Logger.getLogger("collada.objectfactory");
    }
    
    public static Processor createProcessor(final Object schemaObj, final Processor parentProcessor) {
        if (schemaObj == null) {
            return null;
        }
        final Class schemaClass = schemaObj.getClass();
        final String schemaClassName = schemaClass.getName();
        String schemaObjName = schemaClassName.substring(schemaClassName.lastIndexOf(46) + 1);
        if (schemaObjName.indexOf(36) != 0) {
            schemaObjName = schemaObjName.substring(schemaObjName.lastIndexOf(36) + 1);
        }
        try {
            System.out.println("Looking for org.jdesktop.j3d.loaders.collada.xml_walker." + schemaObjName + "Processor");
            final Class walkerClass = Class.forName("org.jdesktop.j3d.loaders.collada.xml_walker." + schemaObjName + "Processor");
            final Constructor con = walkerClass.getConstructor(schemaClass, Processor.class);
            return con.newInstance(schemaObj, parentProcessor);
        }
        catch (ClassNotFoundException ex5) {
            ProcessorFactory.logger.warning("No Handler for " + schemaClass + "  looking for " + schemaObjName);
        }
        catch (NoSuchMethodException ex) {
            ProcessorFactory.logger.warning("No constructor " + schemaObjName + "(" + schemaClassName + ")");
            ex.printStackTrace();
        }
        catch (InstantiationException ex2) {
            ex2.printStackTrace();
        }
        catch (IllegalAccessException ex3) {
            ex3.printStackTrace();
        }
        catch (InvocationTargetException ex4) {
            ex4.printStackTrace();
        }
        return null;
    }
}
