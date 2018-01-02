// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator;

import org.jfree.xml.generator.model.PropertyType;
import org.jfree.xml.generator.model.IgnoredPropertyInfo;
import org.jfree.xml.generator.model.PropertyInfo;
import org.jfree.xml.generator.model.ClassDescription;
import org.jfree.xml.generator.model.TypeInfo;
import org.jfree.xml.generator.model.MultiplexMappingInfo;
import org.jfree.xml.generator.model.ManualMappingInfo;
import org.jfree.xml.writer.AttributeList;
import org.jfree.xml.generator.model.Comments;
import java.io.IOException;
import java.io.Writer;
import org.jfree.xml.generator.model.DescriptionModel;
import org.jfree.xml.writer.XMLWriterSupport;
import org.jfree.xml.writer.SafeTagList;

public class ModelWriter
{
    private static SafeTagList safeTags;
    private XMLWriterSupport writerSupport;
    private DescriptionModel model;
    
    public static SafeTagList getSafeTags() {
        if (ModelWriter.safeTags == null) {
            (ModelWriter.safeTags = new SafeTagList()).add("objects");
            ModelWriter.safeTags.add("object");
            ModelWriter.safeTags.add("constructor");
            ModelWriter.safeTags.add("element-property");
            ModelWriter.safeTags.add("lookup");
            ModelWriter.safeTags.add("attribute-property");
            ModelWriter.safeTags.add("parameter");
            ModelWriter.safeTags.add("include");
            ModelWriter.safeTags.add("ignore");
            ModelWriter.safeTags.add("manual");
            ModelWriter.safeTags.add("mapping");
            ModelWriter.safeTags.add("type");
        }
        return ModelWriter.safeTags;
    }
    
    public ModelWriter() {
        this.writerSupport = new XMLWriterSupport(getSafeTags(), 0);
    }
    
    public DescriptionModel getModel() {
        return this.model;
    }
    
    public void setModel(final DescriptionModel model) {
        this.model = model;
    }
    
    public static void writeXMLHeader(final Writer writer) throws IOException {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.write(XMLWriterSupport.getLineSeparator());
    }
    
    protected void writeStandardComment(final Writer writer, final Comments comments) throws IOException {
        if (comments == null || comments.getOpenTagComment() == null) {
            writer.write("<!-- CVSTag: $Id: ModelWriter.java,v 1.13 2004/04/26 19:15:48 taqua Exp $ " + comments + " -->");
            writer.write(XMLWriterSupport.getLineSeparator());
        }
        else {
            this.writeComment(writer, comments.getOpenTagComment());
        }
    }
    
    protected void writeComment(final Writer writer, final String[] array) throws IOException {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.writerSupport.indent(writer, 3);
            writer.write("<!--");
            writer.write(array[i]);
            writer.write("-->");
            writer.write(XMLWriterSupport.getLineSeparator());
        }
    }
    
    protected void writeOpenComment(final Writer writer, final Comments comments) throws IOException {
        if (comments == null) {
            return;
        }
        if (comments.getOpenTagComment() == null) {
            return;
        }
        this.writeComment(writer, comments.getOpenTagComment());
    }
    
    protected void writeCloseComment(final Writer writer, final Comments comments) throws IOException {
        if (comments == null) {
            return;
        }
        if (comments.getCloseTagComment() == null) {
            return;
        }
        this.writeComment(writer, comments.getCloseTagComment());
    }
    
    protected void writeTag(final Writer writer, final String s, final AttributeList list, final Comments comments) throws IOException {
        if (comments == null) {
            this.writerSupport.writeTag(writer, s, list, true);
        }
        else {
            this.writeOpenComment(writer, comments);
            if (comments.getCloseTagComment() != null) {
                this.writerSupport.writeTag(writer, s, list, false);
                this.writeCloseComment(writer, comments);
                this.writerSupport.writeCloseTag(writer, s);
            }
            else {
                this.writerSupport.writeTag(writer, s, list, true);
            }
        }
    }
    
    protected void writeTag(final Writer writer, final String s, final String s2, final String s3, final Comments comments) throws IOException {
        if (comments == null) {
            this.writerSupport.writeTag(writer, s, s2, s3, true);
        }
        else {
            this.writeOpenComment(writer, comments);
            if (comments.getCloseTagComment() != null) {
                this.writerSupport.writeTag(writer, s, s2, s3, false);
                this.writeCloseComment(writer, comments);
                this.writerSupport.writeCloseTag(writer, s);
            }
            else {
                this.writerSupport.writeTag(writer, s, s2, s3, true);
            }
        }
    }
    
    public void write(final Writer writer) throws IOException {
        this.writeStandardComment(writer, this.model.getModelComments());
        this.writerSupport.writeTag(writer, "objects");
        final String[] sources = this.model.getSources();
        for (int i = 0; i < sources.length; ++i) {
            this.writeTag(writer, "include", "src", sources[i], this.model.getIncludeComment(sources[i]));
        }
        for (int j = 0; j < this.model.size(); ++j) {
            this.writeClassDescription(writer, this.model.get(j));
        }
        final ManualMappingInfo[] manualMapping = this.getModel().getMappingModel().getManualMapping();
        for (int k = 0; k < manualMapping.length; ++k) {
            this.writeManualMapping(writer, manualMapping[k]);
        }
        final MultiplexMappingInfo[] multiplexMapping = this.getModel().getMappingModel().getMultiplexMapping();
        for (int l = 0; l < multiplexMapping.length; ++l) {
            this.writeMultiplexMapping(writer, multiplexMapping[l]);
        }
        this.writeCloseComment(writer, this.model.getModelComments());
        this.writerSupport.writeCloseTag(writer, "objects");
    }
    
    protected void writeManualMapping(final Writer writer, final ManualMappingInfo manualMappingInfo) throws IOException {
        final AttributeList list = new AttributeList();
        list.setAttribute("class", manualMappingInfo.getBaseClass().getName());
        list.setAttribute("read-handler", manualMappingInfo.getReadHandler().getName());
        list.setAttribute("write-handler", manualMappingInfo.getWriteHandler().getName());
        this.writeTag(writer, "manual", list, manualMappingInfo.getComments());
    }
    
    protected void writeMultiplexMapping(final Writer writer, final MultiplexMappingInfo multiplexMappingInfo) throws IOException {
        final TypeInfo[] childClasses = multiplexMappingInfo.getChildClasses();
        final AttributeList list = new AttributeList();
        list.setAttribute("base-class", multiplexMappingInfo.getBaseClass().getName());
        list.setAttribute("type-attribute", multiplexMappingInfo.getTypeAttribute());
        this.getWriterSupport().writeTag(writer, "mapping", list, false);
        for (int i = 0; i < childClasses.length; ++i) {
            final AttributeList list2 = new AttributeList();
            list2.setAttribute("name", childClasses[i].getName());
            list2.setAttribute("class", childClasses[i].getType().getName());
            this.writeTag(writer, "type", list2, childClasses[i].getComments());
        }
        this.getWriterSupport().writeCloseTag(writer, "mapping");
    }
    
    protected void writeClassDescription(final Writer writer, final ClassDescription classDescription) throws IOException {
        if (classDescription.isUndefined()) {
            return;
        }
        final AttributeList list = new AttributeList();
        list.setAttribute("class", classDescription.getName());
        if (classDescription.getRegisterKey() != null) {
            list.setAttribute("register-name", classDescription.getRegisterKey());
        }
        if (classDescription.isPreserve()) {
            list.setAttribute("ignore", "true");
        }
        this.writerSupport.writeTag(writer, "object", list, false);
        final TypeInfo[] constructorDescription = classDescription.getConstructorDescription();
        if (constructorDescription != null && constructorDescription.length != 0) {
            this.writerSupport.writeTag(writer, "constructor");
            for (int i = 0; i < constructorDescription.length; ++i) {
                final AttributeList list2 = new AttributeList();
                list2.setAttribute("class", constructorDescription[i].getType().getName());
                list2.setAttribute("property", constructorDescription[i].getName());
                this.writeTag(writer, "parameter", list2, constructorDescription[i].getComments());
            }
            this.writerSupport.writeCloseTag(writer, "constructor");
        }
        final PropertyInfo[] properties = classDescription.getProperties();
        for (int j = 0; j < properties.length; ++j) {
            this.writePropertyInfo(writer, properties[j]);
        }
        this.writerSupport.writeCloseTag(writer, "object");
    }
    
    private void writePropertyInfo(final Writer writer, final PropertyInfo propertyInfo) throws IOException {
        final AttributeList list = new AttributeList();
        list.setAttribute("name", propertyInfo.getName());
        if (propertyInfo instanceof IgnoredPropertyInfo) {
            this.writeTag(writer, "ignore", list, propertyInfo.getComments());
            return;
        }
        if (propertyInfo.getPropertyType().equals(PropertyType.ATTRIBUTE)) {
            list.setAttribute("attribute", propertyInfo.getXmlName());
            list.setAttribute("handler", propertyInfo.getXmlHandler());
            this.writeTag(writer, "attribute-property", list, propertyInfo.getComments());
        }
        else if (propertyInfo.getPropertyType().equals(PropertyType.ELEMENT)) {
            if (propertyInfo.getComments() == null || propertyInfo.getComments().getOpenTagComment() == null) {
                this.writerSupport.indent(writer, 3);
                writer.write("<!-- property type is " + propertyInfo.getType() + " -->");
                writer.write(System.getProperty("line.separator", "\n"));
            }
            list.setAttribute("element", propertyInfo.getXmlName());
            this.writeTag(writer, "element-property", list, propertyInfo.getComments());
        }
        else {
            list.setAttribute("lookup", propertyInfo.getXmlName());
            this.writeTag(writer, "lookup", list, propertyInfo.getComments());
        }
    }
    
    public XMLWriterSupport getWriterSupport() {
        return this.writerSupport;
    }
}
