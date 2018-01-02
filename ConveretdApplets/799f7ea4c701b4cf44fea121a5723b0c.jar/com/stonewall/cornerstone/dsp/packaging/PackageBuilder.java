// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.packaging;

import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.Collection;
import java.util.ArrayList;
import com.stonewall.cornerstone.utility.HashMultiMap;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.component.ProductDescriptor;

public class PackageBuilder
{
    public static String packagingPath;
    private ProductDescriptor pd;
    private Package common;
    static final Log log;
    
    static {
        PackageBuilder.packagingPath = "com" + File.separator + "stonewall" + File.separator + "cornerstone" + File.separator + "dsp" + File.separator + "packaging";
        log = Log.getLog(PackageBuilder.class);
    }
    
    public PackageBuilder() {
        this.pd = ProductDescriptor.getInstance();
    }
    
    public static void main(final String[] args) {
        final PackageBuilder builder = new PackageBuilder();
        final String home = System.getProperty("cornerstone.home", "");
        if (!home.equals("")) {
            System.setProperty("cornerstone.home", String.valueOf(home) + File.separator);
        }
        if (args.length > 0) {
            final String name = args[0];
            builder.build(name);
        }
        else {
            builder.build();
        }
    }
    
    private void build(final String name) {
        this.common = this.getPackage("commonPackage.xml");
        final Package pkg = this.getPackage(name);
        this.process(pkg);
    }
    
    private void build() {
        this.common = this.getPackage("commonPackage.xml");
        final List<Package> inputPackages = this.getPackages();
        for (final Package pkg : inputPackages) {
            this.process(pkg);
        }
    }
    
    private void process(final Package pkg) {
        try {
            final HashMultiMap<String, DeviceDef> defs = pkg.getDeviceDefsByHardware();
            for (final String type : defs.keySet()) {
                this.jar(type, defs.getList(type), pkg);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jar(final String hw, final List<DeviceDef> defs, final Package pkg) throws Exception {
        final String vendor = pkg.getVendor();
        System.out.println("Creating manifest for vendor: " + vendor + " and hardware: " + hw);
        final Manifest manifest = new Manifest(vendor, hw, this.pd);
        for (final DeviceDef def : defs) {
            final List<ResourcePath> paths = new ArrayList<ResourcePath>();
            final List<String> rootPaths = new ArrayList<String>();
            if (this.common != null) {
                paths.addAll(this.common.paths());
                rootPaths.addAll(this.common.resourceRoots());
            }
            paths.addAll(pkg.paths(def));
            rootPaths.addAll(pkg.resourceRoots());
            manifest.addDeviceDef(def, paths, rootPaths);
        }
        manifest.writeJar();
    }
    
    private Package getPackage(final String name) {
        InputStream is = null;
        try {
            final ModelBuilder builder = new ModelBuilder();
            final String home = System.getProperty("cornerstone.home", "");
            final String filename = String.valueOf(home) + PackageBuilder.packagingPath + File.separator + name;
            System.out.println("Processing package file: " + filename);
            final File file = new File(filename);
            is = new FileInputStream(file);
            return new Package(builder.buildModel(is));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    private List<Package> getPackages() {
        final List<Package> pkgs = new ArrayList<Package>();
        try {
            final ModelBuilder builder = new ModelBuilder();
            final String home = System.getProperty("cornerstone.home", "");
            final File directory = new File(String.valueOf(home) + PackageBuilder.packagingPath);
            System.out.println("Processing package files in directory: " + home + PackageBuilder.packagingPath);
            final File[] files = directory.listFiles(new PackageFileFilter());
            for (int i = 0; i < files.length; ++i) {
                final InputStream is = new FileInputStream(files[i]);
                pkgs.add(new Package(builder.buildModel(is)));
                is.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return pkgs;
    }
    
    public class PackageFileFilter implements FilenameFilter
    {
        @Override
        public boolean accept(final File f, final String g) {
            return g.endsWith(".xml") && !g.equals("commonPackage.xml");
        }
    }
}
