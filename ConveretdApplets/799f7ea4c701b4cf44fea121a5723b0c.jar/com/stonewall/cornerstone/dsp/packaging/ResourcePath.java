// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.packaging;

import org.xmodel.Xlate;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.xmodel.IModelObject;

public class ResourcePath
{
    private IModelObject root;
    public static String basePath;
    
    static {
        ResourcePath.basePath = "com" + File.separator + "stonewall" + File.separator + "cornerstone" + File.separator + "dsp";
    }
    
    ResourcePath(final IModelObject root) {
        this.root = root;
    }
    
    List<Resource> resources() {
        final List<Resource> resources = new ArrayList<Resource>();
        final File file = new File(this.loadPath());
        final File[] files = file.listFiles();
        if (files == null) {
            return resources;
        }
        for (int i = 0; i < files.length; ++i) {
            final File f = files[i];
            if (f.isFile()) {
                resources.add(new Resource(f, this.basePath()));
            }
        }
        return resources;
    }
    
    String loadPath() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.basePath());
        builder.append(File.separator);
        String text = Xlate.get(this.root, "");
        text = text.replace(".", File.separator);
        builder.append(text);
        return builder.toString();
    }
    
    IModelObject getRoot() {
        return this.root;
    }
    
    String basePath() {
        final StringBuilder builder = new StringBuilder();
        final String home = System.getProperty("cornerstone.home", "");
        builder.append(home);
        builder.append(ResourcePath.basePath);
        return builder.toString();
    }
}
