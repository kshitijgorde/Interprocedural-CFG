// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator;

import java.util.Hashtable;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import org.jfree.xml.generator.model.DescriptionModel;
import java.io.File;
import org.jfree.util.LogTarget;
import org.jfree.util.PrintStreamLogTarget;
import org.jfree.util.Log;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Properties;
import java.net.URL;

public final class DescriptionGenerator
{
    static /* synthetic */ Class class$org$jfree$xml$generator$DescriptionGenerator;
    
    private static Properties loadProperties(final URL url) {
        final Properties properties = new Properties();
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            properties.load(bufferedInputStream);
            bufferedInputStream.close();
        }
        catch (Exception ex) {
            System.err.println("Unable to load properties from " + url);
        }
        return properties;
    }
    
    public static void main(final String[] array) throws Exception {
        Log.getInstance().addTarget(new PrintStreamLogTarget());
        URL url = ((DescriptionGenerator.class$org$jfree$xml$generator$DescriptionGenerator == null) ? (DescriptionGenerator.class$org$jfree$xml$generator$DescriptionGenerator = class$("org.jfree.xml.generator.DescriptionGenerator")) : DescriptionGenerator.class$org$jfree$xml$generator$DescriptionGenerator).getResource("generator.properties");
        if (array.length > 0) {
            url = new File(array[0]).toURL();
        }
        final Properties loadProperties = loadProperties(url);
        final String property = loadProperties.getProperty("attributedefinition");
        if (property != null) {
            ModelBuilder.getInstance().addAttributeHandlers(loadProperties(new URL(url, property)));
        }
        final String property2 = loadProperties.getProperty("sourcedirectory", ".");
        final String property3 = loadProperties.getProperty("targetfile", "model.xml");
        DescriptionModel load = null;
        try {
            load = new DefaultModelReader().load(property3);
        }
        catch (Exception ex) {
            Log.debug("Unable to load default model. Ignoring...");
        }
        final DescriptionModel generate = generate(property2, loadProperties, load);
        generate.prune();
        writeMultiFile(property3, generate);
        System.exit(0);
    }
    
    public static DescriptionModel generate(final String s, final Properties properties, final DescriptionModel descriptionModel) {
        final JavaSourceCollector javaSourceCollector = new JavaSourceCollector(new File(s));
        for (final String s2 : ((Hashtable<String, V>)properties).keySet()) {
            if (s2.startsWith("ignore.baseclass.")) {
                javaSourceCollector.addIgnoredBaseClass(properties.getProperty(s2));
            }
            else {
                if (!s2.startsWith("ignore.package.")) {
                    continue;
                }
                javaSourceCollector.addIgnoredPackage(properties.getProperty(s2));
            }
        }
        javaSourceCollector.collectFiles();
        return ModelBuilder.getInstance().buildModel(javaSourceCollector, descriptionModel);
    }
    
    public static void writeSingleFile(final String s, final DescriptionModel model) throws IOException {
        Log.debug("Writing ...");
        final ModelWriter modelWriter = new ModelWriter();
        modelWriter.setModel(model);
        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(s));
        modelWriter.write(bufferedWriter);
        bufferedWriter.close();
    }
    
    public static void writeMultiFile(final String s, final DescriptionModel model) throws IOException {
        Log.debug("Writing multiple files ...");
        final SplittingModelWriter splittingModelWriter = new SplittingModelWriter();
        splittingModelWriter.setModel(model);
        splittingModelWriter.write(s);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
