// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator;

import java.util.Iterator;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import org.jfree.util.Log;
import java.io.IOException;
import org.jfree.xml.generator.model.MultiplexMappingInfo;
import org.jfree.xml.generator.model.ManualMappingInfo;
import org.jfree.xml.generator.model.MappingModel;
import org.jfree.xml.generator.model.ClassDescription;
import org.jfree.xml.generator.model.DescriptionModel;
import org.jfree.io.IOUtils;
import java.util.Collection;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;
import org.jfree.util.HashNMap;

public class SplittingModelWriter extends ModelWriter
{
    private HashNMap classDescriptionByPackage;
    private ArrayList sources;
    private File targetFile;
    private String extension;
    private String plainFileName;
    private HashNMap manualMappingByPackage;
    private HashNMap multiplexMappingByPackage;
    
    public synchronized void write(final String s) throws IOException {
        final DescriptionModel model = this.getModel();
        this.sources = new ArrayList(Arrays.asList(model.getSources()));
        this.targetFile = new File(s);
        this.plainFileName = IOUtils.getInstance().stripFileExtension(this.targetFile.getName());
        this.extension = IOUtils.getInstance().getFileExtension(s);
        this.classDescriptionByPackage = new HashNMap();
        for (int i = 0; i < model.size(); ++i) {
            final ClassDescription value = model.get(i);
            if (value.getSource() == null) {
                this.classDescriptionByPackage.add(this.plainFileName + "-" + getPackage(value.getObjectClass()) + this.extension, value);
            }
            else {
                this.classDescriptionByPackage.add(value.getSource(), value);
            }
        }
        final MappingModel mappingModel = model.getMappingModel();
        final ManualMappingInfo[] manualMapping = mappingModel.getManualMapping();
        this.manualMappingByPackage = new HashNMap();
        for (int j = 0; j < manualMapping.length; ++j) {
            final ManualMappingInfo manualMappingInfo = manualMapping[j];
            if (manualMappingInfo.getSource() == null) {
                this.manualMappingByPackage.add("", manualMappingInfo);
            }
            else {
                this.manualMappingByPackage.add(manualMappingInfo.getSource(), manualMappingInfo);
            }
        }
        final MultiplexMappingInfo[] multiplexMapping = mappingModel.getMultiplexMapping();
        this.multiplexMappingByPackage = new HashNMap();
        for (int k = 0; k < multiplexMapping.length; ++k) {
            final MultiplexMappingInfo multiplexMappingInfo = multiplexMapping[k];
            if (multiplexMappingInfo.getSource() == null) {
                this.multiplexMappingByPackage.add("", multiplexMappingInfo);
            }
            else {
                this.multiplexMappingByPackage.add(multiplexMappingInfo.getSource(), multiplexMappingInfo);
            }
        }
        final Object[] array = this.classDescriptionByPackage.keySet().toArray();
        for (int l = 0; l < array.length; ++l) {
            final String s2 = (String)array[l];
            if (!s2.equals("")) {
                this.writePackageFile(s2);
            }
        }
        this.writeMasterFile();
        this.manualMappingByPackage = null;
        this.multiplexMappingByPackage = null;
        this.classDescriptionByPackage = null;
        this.sources = null;
    }
    
    private void writePackageFile(final String s) throws IOException {
        final Iterator all = this.classDescriptionByPackage.getAll(s);
        final Iterator all2 = this.manualMappingByPackage.getAll(s);
        final Iterator all3 = this.multiplexMappingByPackage.getAll(s);
        if (!all.hasNext() && !all2.hasNext() && !all3.hasNext()) {
            return;
        }
        Log.debug("Writing included file: " + s);
        this.sources.remove(s);
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(this.targetFile.getParentFile(), s)), "UTF-8"));
        ModelWriter.writeXMLHeader(bufferedWriter);
        this.writeStandardComment(bufferedWriter, this.getModel().getModelComments());
        this.getWriterSupport().writeTag(bufferedWriter, "objects");
        while (all.hasNext()) {
            this.writeClassDescription(bufferedWriter, all.next());
        }
        while (all2.hasNext()) {
            this.writeManualMapping(bufferedWriter, all2.next());
        }
        while (all3.hasNext()) {
            this.writeMultiplexMapping(bufferedWriter, all3.next());
        }
        this.writeCloseComment(bufferedWriter, this.getModel().getModelComments());
        this.getWriterSupport().writeCloseTag(bufferedWriter, "objects");
        bufferedWriter.close();
    }
    
    public static String getPackage(final Class clazz) {
        final String name = clazz.getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex <= 0) {
            return "";
        }
        return name.substring(0, lastIndex);
    }
    
    private void writeMasterFile() throws IOException {
        Log.debug("Writing master file: " + this.targetFile);
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.targetFile), "UTF-8"));
        ModelWriter.writeXMLHeader(bufferedWriter);
        this.writeStandardComment(bufferedWriter, this.getModel().getModelComments());
        this.getWriterSupport().writeTag(bufferedWriter, "objects");
        for (int i = 0; i < this.sources.size(); ++i) {
            final String s = this.sources.get(i);
            if (!s.equals("")) {
                this.writeTag(bufferedWriter, "include", "src", s, this.getModel().getIncludeComment(s));
            }
        }
        final Object[] array = this.classDescriptionByPackage.keySet().toArray();
        Arrays.sort(array);
        for (int j = 0; j < array.length; ++j) {
            final String s2 = (String)array[j];
            if (!s2.equals("")) {
                this.writeTag(bufferedWriter, "include", "src", s2, this.getModel().getIncludeComment(s2));
            }
        }
        final Iterator all = this.classDescriptionByPackage.getAll("");
        while (all.hasNext()) {
            this.writeClassDescription(bufferedWriter, all.next());
        }
        final Iterator all2 = this.manualMappingByPackage.getAll("");
        while (all2.hasNext()) {
            this.writeManualMapping(bufferedWriter, all2.next());
        }
        final Iterator all3 = this.multiplexMappingByPackage.getAll("");
        while (all3.hasNext()) {
            this.writeMultiplexMapping(bufferedWriter, all3.next());
        }
        this.writeCloseComment(bufferedWriter, this.getModel().getModelComments());
        this.getWriterSupport().writeCloseTag(bufferedWriter, "objects");
        bufferedWriter.close();
    }
}
