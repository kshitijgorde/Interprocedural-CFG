// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.packaging;

import com.stonewall.cornerstone.entity.Device;
import java.util.Iterator;
import java.io.InputStream;
import java.io.FileInputStream;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.ArrayList;
import java.io.File;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.utility.HashMultiMap;
import java.util.List;

public class Packaging
{
    private static List<String> supportedDefs;
    private static List<String> packagePaths;
    private static HashMultiMap<String, DeviceDef> defsByVendor;
    private static HashMultiMap<String, DeviceDef> defsByHardware;
    static final Log log;
    
    static {
        log = Log.getLog(Packaging.class);
    }
    
    public static void init(final File[] files) throws Exception {
        Packaging.supportedDefs = new ArrayList<String>();
        Packaging.defsByVendor = new HashMultiMap<String, DeviceDef>();
        Packaging.defsByHardware = new HashMultiMap<String, DeviceDef>();
        Packaging.packagePaths = new ArrayList<String>();
        final List<Package> packages = new ArrayList<Package>();
        for (int i = 0; i < files.length; ++i) {
            final File f = files[i];
            if (f.getName().contains("Package.xml") && !f.getName().equals("commonPackage.xml")) {
                try {
                    Packaging.packagePaths.add(f.getPath());
                    final ModelBuilder builder = new ModelBuilder();
                    packages.add(new Package(builder.buildModel(new FileInputStream(f))));
                }
                catch (Exception e) {
                    Packaging.log.error("Problem parsing package=" + f.getName(), e);
                    throw e;
                }
            }
        }
        for (final Package pkg : packages) {
            try {
                final String vendor = pkg.getVendor();
                for (final DeviceDef def : pkg.getDeviceDefs()) {
                    Packaging.defsByVendor.put(vendor, def);
                    Packaging.defsByHardware.put(def.getHardware(), def);
                    Packaging.supportedDefs.add(String.valueOf(def.getSoftware()) + def.getHardware());
                }
            }
            catch (Exception e) {
                Packaging.log.error("init definitions", e);
            }
        }
    }
    
    public static boolean isSupported(final Device device) {
        return Packaging.supportedDefs.contains(String.valueOf(device.getSoftware()) + device.getHardware());
    }
    
    public static List<DeviceDef> getDeviceDefs(final String vendor, final String hardware) {
        if (hardware != null) {
            return Packaging.defsByHardware.getList(hardware);
        }
        if (vendor != null) {
            return Packaging.defsByVendor.getList(vendor);
        }
        return Packaging.defsByVendor.values();
    }
    
    public static List<DeviceDef> uniqueDefsByResource(final List<DeviceDef> defs) {
        final List<DeviceDef> uniqueDefs = new ArrayList<DeviceDef>();
        final HashMultiMap<String, DeviceDef> uniqueIds = new HashMultiMap<String, DeviceDef>();
        for (final DeviceDef def : defs) {
            uniqueIds.put(def.getResourceGroupId(), def);
        }
        for (final String id : uniqueIds.keySet()) {
            uniqueDefs.add(uniqueIds.getFirst(id));
        }
        return uniqueDefs;
    }
    
    public static List<String> getPackagePaths() {
        return Packaging.packagePaths;
    }
}
