// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.net.URL;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.IModelObject;
import java.util.HashMap;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Map;

public class Predefined
{
    private static Map<EntityFactory.EntityType, String> map;
    private static Log log;
    
    static {
        (Predefined.map = new HashMap<EntityFactory.EntityType, String>()).put(EntityFactory.EntityType.ipService, "db/services.xml");
        Predefined.map.put(EntityFactory.EntityType.tag, "db/tags.xml");
        Predefined.log = Log.getLog(Predefined.class);
    }
    
    public static IModelObject load(final EntityFactory.EntityType type) throws Exception {
        try {
            final ModelBuilder builder = new ModelBuilder();
            final URL url = ClassLoader.getSystemResource(Predefined.map.get(type));
            return builder.buildModel(url);
        }
        catch (Exception e) {
            Predefined.log.error("Cannot load predefined file:" + Predefined.map.get(type), e);
            throw e;
        }
    }
}
