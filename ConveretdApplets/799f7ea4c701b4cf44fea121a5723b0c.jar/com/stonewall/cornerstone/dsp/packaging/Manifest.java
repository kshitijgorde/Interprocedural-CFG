// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.packaging;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.zip.ZipEntry;
import java.util.jar.JarEntry;
import java.io.OutputStream;
import java.util.jar.JarOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.Iterator;
import java.util.List;
import org.xmodel.Element;
import java.util.HashSet;
import com.stonewall.cornerstone.component.ProductDescriptor;
import java.util.Set;
import org.xmodel.IModelObject;

public class Manifest extends java.util.jar.Manifest
{
    private IModelObject root;
    private String hw;
    private String vendor;
    private Set<Resource> resourcesToJar;
    
    Manifest(final String vendor, final String hw, final ProductDescriptor pd) {
        this.resourcesToJar = new HashSet<Resource>();
        (this.root = new Element("dsp:manifest")).setAttribute("xsi:schemaLocation", "http://www.stonewallnetworks.com/ns/dsp http://schema.stonewallnetworks.com/ns/dsp/package.xsd");
        this.hw = hw;
        this.vendor = vendor;
        this.root.getCreateChild("dsp:hardware").setValue(hw);
        this.root.getCreateChild("dsp:vendor").setValue(vendor);
        this.root.addChild(pd.getRoot().cloneTree());
    }
    
    void addDeviceDef(final DeviceDef def, final List<ResourcePath> paths, final List<String> rootPaths) {
        final IModelObject entry = new Element("dsp:entry");
        this.root.addChild(entry);
        entry.setAttribute("software", def.getSoftware());
        for (final ResourcePath path : paths) {
            entry.addChild(path.getRoot());
            for (final Resource resource : path.resources()) {
                final String text = resource.getText();
                this.addResource(entry, text, resource.getId(rootPaths));
                this.resourcesToJar.add(resource);
            }
        }
    }
    
    private void addResource(final IModelObject entry, final String text, final String id) {
        final IExpression xpath = XPath.createExpression("./dsp:resource[@id = $id]");
        xpath.setVariable("id", id);
        final IModelObject e = xpath.queryFirst(entry);
        if (e != null) {
            entry.removeChild(e);
        }
        final IModelObject r = entry.getCreateChild("dsp:resource", id);
        r.setValue(text);
    }
    
    void writeJar() throws Exception {
        final java.util.jar.Manifest m = new java.util.jar.Manifest();
        m.getMainAttributes().putValue("Manifest-Version", "1.0");
        m.getMainAttributes().putValue("Created-By", "1.5.0_04 (Sun Microsystems Inc.)");
        final String home = System.getProperty("cornerstone.home", "");
        final String jarFileName = String.valueOf(home) + "lib" + File.separator + this.vendor + "_" + this.hw + ".jar";
        final JarOutputStream jar = new JarOutputStream(new FileOutputStream(new File(jarFileName)), m);
        final JarEntry fileEntry = new JarEntry("manifest.xml");
        jar.putNextEntry(fileEntry);
        final ModelBuilder builder = new ModelBuilder();
        builder.writeModel(this.root, jar, IXmlIO.Style.printable);
        final String mode = System.getProperty("mode", "jar");
        if (mode.equals("jar")) {
            for (final Resource r : this.resourcesToJar) {
                this.jarResource(r, jar);
            }
        }
        jar.close();
        jar.flush();
    }
    
    private void jarResource(final Resource r, final JarOutputStream jar) throws IOException {
        final File file = r.getFile();
        final FileInputStream fis = new FileInputStream(file);
        final BufferedInputStream bis = new BufferedInputStream(fis);
        final String path = file.getPath().replace(File.separator, "/");
        final JarEntry fileEntry = new JarEntry(path);
        jar.putNextEntry(fileEntry);
        final byte[] data = new byte[1024];
        int byteCount;
        while ((byteCount = bis.read(data, 0, 1024)) > -1) {
            jar.write(data, 0, byteCount);
        }
    }
}
