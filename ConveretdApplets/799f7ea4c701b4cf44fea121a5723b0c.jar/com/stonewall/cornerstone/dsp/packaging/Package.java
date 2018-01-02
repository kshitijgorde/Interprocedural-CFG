// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.packaging;

import java.io.File;
import java.util.Collection;
import java.util.Set;
import org.xmodel.Xlate;
import java.util.HashSet;
import com.stonewall.cornerstone.utility.HashMultiMap;
import java.util.Iterator;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.component.ProductDescriptor;
import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.IModelObject;

public class Package
{
    private IModelObject root;
    
    Package(final IModelObject doc) {
        this.root = doc;
    }
    
    String getVendor() {
        return this.root.getCreateChild("dsp:vendorDef").getID();
    }
    
    List<DeviceDef> getDeviceDefs() {
        final List<DeviceDef> defs = new ArrayList<DeviceDef>();
        final IModelObject vendor = this.root.getCreateChild("dsp:vendorDef");
        final IExpression path = XPath.createExpression("./dsp:deviceDef");
        final List<IModelObject> elements = path.query(this.root, null);
        for (final IModelObject def : elements) {
            final List<IModelObject> l = def.getChildren("dsp:software");
            for (final IModelObject sw : l) {
                defs.add(new DeviceDef(def, sw, vendor, ProductDescriptor.getInstance()));
            }
        }
        return defs;
    }
    
    HashMultiMap<String, DeviceDef> getDeviceDefsByHardware() {
        final HashMultiMap<String, DeviceDef> map = new HashMultiMap<String, DeviceDef>();
        final IModelObject vendor = this.root.getFirstChild("dsp:vendorDef");
        final Set<String> types = new HashSet<String>();
        IExpression path = XPath.createExpression("./dsp:deviceDef/@hardware");
        final List<IModelObject> hardware = path.query(this.root, null);
        for (final IModelObject a : hardware) {
            types.add(Xlate.get(a, (String)null));
        }
        for (final String type : types) {
            path = XPath.createExpression("./dsp:deviceDef[@hardware=$type]");
            path.setVariable("type", type);
            final List<IModelObject> defs = path.query(this.root, null);
            for (final IModelObject def : defs) {
                final List<IModelObject> l = def.getChildren("dsp:software");
                for (final IModelObject sw : l) {
                    map.put(type, new DeviceDef(def, sw, vendor, ProductDescriptor.getInstance()));
                }
            }
        }
        return map;
    }
    
    List<ResourcePath> paths(final DeviceDef def) {
        final List<ResourcePath> paths = new ArrayList<ResourcePath>();
        paths.addAll(this.paths(def.getResourceGroupId()));
        return paths;
    }
    
    private List<ResourcePath> paths(final String groupId) {
        final List<ResourcePath> paths = new ArrayList<ResourcePath>();
        final IExpression xpath = XPath.createExpression("./dsp:resourceGroup[@id = $id]");
        xpath.setVariable("id", groupId);
        final IModelObject group = xpath.queryFirst(this.root);
        final String extendz = Xlate.get(group.getFirstChild("dsp:extends"), (String)null);
        if (extendz != null) {
            paths.addAll(this.paths(extendz));
        }
        final List<IModelObject> resourcePaths = group.getChildren("dsp:resourcePath");
        for (final IModelObject e : resourcePaths) {
            paths.add(new ResourcePath(e.cloneTree()));
        }
        return paths;
    }
    
    List<ResourcePath> paths() {
        final List<ResourcePath> paths = new ArrayList<ResourcePath>();
        final IExpression xpath = XPath.createExpression("./dsp:resourceGroup/dsp:resourcePath");
        final List<IModelObject> resourcePaths = xpath.query(this.root, null);
        for (final IModelObject e : resourcePaths) {
            paths.add(new ResourcePath(e.cloneTree()));
        }
        return paths;
    }
    
    List<String> resourceRoots() {
        final List<String> roots = new ArrayList<String>();
        final IExpression xpath = XPath.createExpression("./dsp:resourceGroup/dsp:resourceRoot");
        final List<IModelObject> resourcePaths = xpath.query(this.root, null);
        for (final IModelObject e : resourcePaths) {
            String text = Xlate.get(e, "");
            text = text.replace(".", File.separator);
            roots.add(text);
        }
        return roots;
    }
}
