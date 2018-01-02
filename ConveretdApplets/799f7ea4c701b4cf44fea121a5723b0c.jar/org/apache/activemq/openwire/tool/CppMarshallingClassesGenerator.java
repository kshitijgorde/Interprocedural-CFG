// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
import org.codehaus.jam.JAnnotation;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jam.JClass;
import org.codehaus.jam.JAnnotationValue;
import org.codehaus.jam.JProperty;
import java.io.PrintWriter;

public class CppMarshallingClassesGenerator extends CppMarshallingHeadersGenerator
{
    @Override
    protected String getFilePostFix() {
        return ".cpp";
    }
    
    protected void generateUnmarshalBodyForProperty(final PrintWriter out, final JProperty property, final JAnnotationValue size) {
        out.print("    ");
        final String setter = property.getSetter().getSimpleName();
        final String type = property.getType().getSimpleName();
        if (type.equals("boolean")) {
            out.println("info." + setter + "( bs.readBoolean() );");
        }
        else if (type.equals("byte")) {
            out.println("info." + setter + "( DataStreamMarshaller.readByte(dataIn) );");
        }
        else if (type.equals("char")) {
            out.println("info." + setter + "( DataStreamMarshaller.readChar(dataIn) );");
        }
        else if (type.equals("short")) {
            out.println("info." + setter + "( DataStreamMarshaller.readShort(dataIn) );");
        }
        else if (type.equals("int")) {
            out.println("info." + setter + "( DataStreamMarshaller.readInt(dataIn) );");
        }
        else if (type.equals("long")) {
            out.println("info." + setter + "( UnmarshalLong(wireFormat, dataIn, bs) );");
        }
        else if (type.equals("String")) {
            out.println("info." + setter + "( readString(dataIn, bs) );");
        }
        else if (type.equals("byte[]") || type.equals("ByteSequence")) {
            if (size != null) {
                out.println("info." + setter + "( readBytes(dataIn, " + size.asInt() + ") );");
            }
            else {
                out.println("info." + setter + "( readBytes(dataIn, bs.readBoolean()) );");
            }
        }
        else if (this.isThrowable(property.getType())) {
            out.println("info." + setter + "( unmarshalBrokerError(wireFormat, dataIn, bs) );");
        }
        else if (this.isCachedProperty(property)) {
            out.println("info." + setter + "( (" + type + ") unmarshalCachedObject(wireFormat, dataIn, bs) );");
        }
        else {
            out.println("info." + setter + "( (" + type + ") unmarshalNestedObject(wireFormat, dataIn, bs) );");
        }
    }
    
    protected void generateUnmarshalBodyForArrayProperty(final PrintWriter out, final JProperty property, final JAnnotationValue size) {
        final JClass propertyType = property.getType();
        final String arrayType = propertyType.getArrayComponentType().getSimpleName();
        final String setter = property.getGetter().getSimpleName();
        out.println();
        if (size != null) {
            out.println("    {");
            out.println("        " + arrayType + "[] value = new " + arrayType + "[" + size.asInt() + "];");
            out.println("        for( int i=0; i < " + size.asInt() + "; i++ ) {");
            out.println("            value[i] = (" + arrayType + ") unmarshalNestedObject(wireFormat,dataIn, bs);");
            out.println("        }");
            out.println("        info." + setter + "( value );");
            out.println("    }");
        }
        else {
            out.println("    if (bs.readBoolean()) {");
            out.println("        short size = DataStreamMarshaller.readShort(dataIn);");
            out.println("        " + arrayType + "[] value = new " + arrayType + "[size];");
            out.println("        for( int i=0; i < size; i++ ) {");
            out.println("            value[i] = (" + arrayType + ") unmarshalNestedObject(wireFormat,dataIn, bs);");
            out.println("        }");
            out.println("        info." + setter + "( value );");
            out.println("    }");
            out.println("    else {");
            out.println("        info." + setter + "( null );");
            out.println("    }");
        }
    }
    
    protected int generateMarshal1Body(final PrintWriter out) {
        final List properties = this.getProperties();
        int baseSize = 0;
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            final JClass propertyType = property.getType();
            final String type = propertyType.getSimpleName();
            final String getter = "info." + property.getGetter().getSimpleName() + "()";
            out.print(this.indent);
            if (type.equals("boolean")) {
                out.println("bs.writeBoolean(" + getter + ");");
            }
            else if (type.equals("byte")) {
                ++baseSize;
            }
            else if (type.equals("char")) {
                ++baseSize;
            }
            else if (type.equals("short")) {
                ++baseSize;
            }
            else if (type.equals("int")) {
                ++baseSize;
            }
            else if (type.equals("long")) {
                out.println("rc += marshal1Long(wireFormat, " + getter + ", bs);");
            }
            else if (type.equals("String")) {
                out.println("rc += writeString(" + getter + ", bs);");
            }
            else if (type.equals("byte[]") || type.equals("ByteSequence")) {
                if (size == null) {
                    out.println("bs.writeBoolean(" + getter + "!=null);");
                    out.println("    rc += " + getter + "==null ? 0 : " + getter + ".Length+4;");
                }
                else {
                    baseSize += size.asInt();
                }
            }
            else if (propertyType.isArrayType()) {
                if (size != null) {
                    out.println("rc += marshalObjectArrayConstSize(wireFormat, " + getter + ", bs, " + size.asInt() + ");");
                }
                else {
                    out.println("rc += marshalObjectArray(wireFormat, " + getter + ", bs);");
                }
            }
            else if (this.isThrowable(propertyType)) {
                out.println("rc += marshalBrokerError(wireFormat, " + getter + ", bs);");
            }
            else if (this.isCachedProperty(property)) {
                out.println("rc += marshal1CachedObject(wireFormat, " + getter + ", bs);");
            }
            else {
                out.println("rc += marshal1NestedObject(wireFormat, " + getter + ", bs);");
            }
        }
        return baseSize;
    }
    
    protected void generateMarshal2Body(final PrintWriter out) {
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            final JClass propertyType = property.getType();
            final String type = propertyType.getSimpleName();
            final String getter = "info." + property.getGetter().getSimpleName() + "()";
            out.print(this.indent);
            if (type.equals("boolean")) {
                out.println("bs.readBoolean();");
            }
            else if (type.equals("byte")) {
                out.println("DataStreamMarshaller.writeByte(" + getter + ", dataOut);");
            }
            else if (type.equals("char")) {
                out.println("DataStreamMarshaller.writeChar(" + getter + ", dataOut);");
            }
            else if (type.equals("short")) {
                out.println("DataStreamMarshaller.writeShort(" + getter + ", dataOut);");
            }
            else if (type.equals("int")) {
                out.println("DataStreamMarshaller.writeInt(" + getter + ", dataOut);");
            }
            else if (type.equals("long")) {
                out.println("marshal2Long(wireFormat, " + getter + ", dataOut, bs);");
            }
            else if (type.equals("String")) {
                out.println("writeString(" + getter + ", dataOut, bs);");
            }
            else if (type.equals("byte[]") || type.equals("ByteSequence")) {
                if (size != null) {
                    out.println("dataOut.write(" + getter + ", 0, " + size.asInt() + ");");
                }
                else {
                    out.println("if(bs.readBoolean()) {");
                    out.println("       DataStreamMarshaller.writeInt(" + getter + ".Length, dataOut);");
                    out.println("       dataOut.write(" + getter + ");");
                    out.println("    }");
                }
            }
            else if (propertyType.isArrayType()) {
                if (size != null) {
                    out.println("marshalObjectArrayConstSize(wireFormat, " + getter + ", dataOut, bs, " + size.asInt() + ");");
                }
                else {
                    out.println("marshalObjectArray(wireFormat, " + getter + ", dataOut, bs);");
                }
            }
            else if (this.isThrowable(propertyType)) {
                out.println("marshalBrokerError(wireFormat, " + getter + ", dataOut, bs);");
            }
            else if (this.isCachedProperty(property)) {
                out.println("marshal2CachedObject(wireFormat, " + getter + ", dataOut, bs);");
            }
            else {
                out.println("marshal2NestedObject(wireFormat, " + getter + ", dataOut, bs);");
            }
        }
    }
    
    @Override
    protected void generateFile(final PrintWriter out) throws Exception {
        this.generateLicence(out);
        out.println("#include \"marshal/" + this.className + ".hpp\"");
        out.println("");
        out.println("using namespace apache::activemq::client::marshal;");
        out.println("");
        out.println("/*");
        out.println(" *  Marshalling code for Open Wire Format for " + this.jclass.getSimpleName() + "");
        out.println(" *");
        out.println(" * NOTE!: This file is autogenerated - do not modify!");
        out.println(" *        if you need to make a change, please see the Groovy scripts in the");
        out.println(" *        activemq-core module");
        out.println(" */");
        out.println("");
        out.println("" + this.className + "::" + this.className + "()");
        out.println("{");
        out.println("    // no-op");
        out.println("}");
        out.println("");
        out.println("" + this.className + "::~" + this.className + "()");
        out.println("{");
        out.println("    // no-op");
        out.println("}");
        out.println("");
        if (!this.isAbstractClass()) {
            out.println("");
            out.println("");
            out.println("IDataStructure* " + this.className + "::createObject() ");
            out.println("{");
            out.println("    return new " + this.jclass.getSimpleName() + "();");
            out.println("}");
            out.println("");
            out.println("char " + this.className + "::getDataStructureType() ");
            out.println("{");
            out.println("    return " + this.jclass.getSimpleName() + ".ID_" + this.jclass.getSimpleName() + ";");
            out.println("}");
        }
        out.println("");
        out.println("    /* ");
        out.println("     * Un-marshal an object instance from the data input stream");
        out.println("     */ ");
        out.println("void " + this.className + "::unmarshal(ProtocolFormat& wireFormat, Object o, BinaryReader& dataIn, BooleanStream& bs) ");
        out.println("{");
        out.println("    base.unmarshal(wireFormat, o, dataIn, bs);");
        final List properties = this.getProperties();
        final boolean marshallerAware = this.isMarshallerAware();
        if (!properties.isEmpty() || marshallerAware) {
            out.println("");
            out.println("    " + this.jclass.getSimpleName() + "& info = (" + this.jclass.getSimpleName() + "&) o;");
        }
        if (marshallerAware) {
            out.println("");
            out.println("    info.beforeUnmarshall(wireFormat);");
            out.println("        ");
        }
        this.generateTightUnmarshalBody(out);
        if (marshallerAware) {
            out.println("");
            out.println("    info.afterUnmarshall(wireFormat);");
        }
        out.println("");
        out.println("}");
        out.println("");
        out.println("");
        out.println("/*");
        out.println(" * Write the booleans that this object uses to a BooleanStream");
        out.println(" */");
        out.println("int " + this.className + "::marshal1(ProtocolFormat& wireFormat, Object& o, BooleanStream& bs) {");
        out.println("    " + this.jclass.getSimpleName() + "& info = (" + this.jclass.getSimpleName() + "&) o;");
        if (marshallerAware) {
            out.println("");
            out.println("    info.beforeMarshall(wireFormat);");
        }
        out.println("");
        out.println("    int rc = base.marshal1(wireFormat, info, bs);");
        final int baseSize = this.generateMarshal1Body(out);
        out.println("");
        out.println("    return rc + " + baseSize + ";");
        out.println("}");
        out.println("");
        out.println("/* ");
        out.println(" * Write a object instance to data output stream");
        out.println(" */");
        out.println("void " + this.className + "::marshal2(ProtocolFormat& wireFormat, Object& o, BinaryWriter& dataOut, BooleanStream& bs) {");
        out.println("    base.marshal2(wireFormat, o, dataOut, bs);");
        if (!properties.isEmpty() || marshallerAware) {
            out.println("");
            out.println("    " + this.jclass.getSimpleName() + "& info = (" + this.jclass.getSimpleName() + "&) o;");
        }
        this.generateMarshal2Body(out);
        if (marshallerAware) {
            out.println("");
            out.println("    info.afterMarshall(wireFormat);");
        }
        out.println("");
        out.println("}");
    }
    
    @Override
    public void generateFactory(final PrintWriter out) {
        this.generateLicence(out);
        out.println("");
        out.println("// Marshalling code for Open Wire Format");
        out.println("//");
        out.println("//");
        out.println("// NOTE!: This file is autogenerated - do not modify!");
        out.println("//        if you need to make a change, please see the Groovy scripts in the");
        out.println("//        activemq-openwire module");
        out.println("//");
        out.println("");
        out.println("#include \"marshal/" + this.className + ".hpp\"");
        out.println("");
        final List list = new ArrayList(this.getConcreteClasses());
        Collections.sort((List<Object>)list, new Comparator() {
            @Override
            public int compare(final Object o1, final Object o2) {
                final JClass c1 = (JClass)o1;
                final JClass c2 = (JClass)o2;
                return c1.getSimpleName().compareTo(c2.getSimpleName());
            }
        });
        for (final JClass jclass : list) {
            out.println("#include \"marshal/" + jclass.getSimpleName() + "Marshaller.hpp\"");
        }
        out.println("");
        out.println("");
        out.println("using namespace apache::activemq::client::marshal;");
        out.println("");
        out.println("");
        out.println("void MarshallerFactory::configure(ProtocolFormat& format) ");
        out.println("{");
        for (final JClass jclass : list) {
            out.println("    format.addMarshaller(new " + jclass.getSimpleName() + "Marshaller());");
        }
        out.println("");
        out.println("}");
    }
}
