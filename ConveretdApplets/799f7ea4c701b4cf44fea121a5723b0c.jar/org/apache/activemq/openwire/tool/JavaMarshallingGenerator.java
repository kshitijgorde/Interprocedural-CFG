// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.codehaus.jam.JAnnotationValue;
import org.codehaus.jam.JAnnotation;
import org.codehaus.jam.JProperty;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.io.Writer;
import java.io.FileWriter;
import org.codehaus.jam.JPackage;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import org.codehaus.jam.JClass;
import java.util.List;

public class JavaMarshallingGenerator extends MultiSourceGenerator
{
    protected List<JClass> concreteClasses;
    protected File factoryFile;
    protected String factoryFileName;
    protected String indent;
    protected String targetDir;
    
    public JavaMarshallingGenerator() {
        this.concreteClasses = new ArrayList<JClass>();
        this.factoryFileName = "MarshallerFactory";
        this.indent = "    ";
        this.targetDir = "src/main/java";
    }
    
    @Override
    public Object run() {
        if (this.destDir == null) {
            this.destDir = new File(this.targetDir + "/org/apache/activemq/openwire/v" + this.getOpenwireVersion());
        }
        final Object answer = super.run();
        this.processFactory();
        return answer;
    }
    
    @Override
    protected void generateFile(final PrintWriter out) throws Exception {
        this.generateLicence(out);
        out.println("");
        out.println("package org.apache.activemq.openwire.v" + this.getOpenwireVersion() + ";");
        out.println("");
        out.println("import java.io.DataInput;");
        out.println("import java.io.DataOutput;");
        out.println("import java.io.IOException;");
        out.println("");
        out.println("import org.apache.activemq.openwire.*;");
        out.println("import org.apache.activemq.command.*;");
        out.println("");
        out.println("");
        for (int i = 0; i < this.getJclass().getImportedPackages().length; ++i) {
            final JPackage pkg = this.getJclass().getImportedPackages()[i];
            for (int j = 0; j < pkg.getClasses().length; ++j) {
                final JClass clazz = pkg.getClasses()[j];
                out.println("import " + clazz.getQualifiedName() + ";");
            }
        }
        out.println("");
        out.println("/**");
        out.println(" * Marshalling code for Open Wire Format for " + this.getClassName() + "");
        out.println(" *");
        out.println(" *");
        out.println(" * NOTE!: This file is auto generated - do not modify!");
        out.println(" *        if you need to make a change, please see the modify the groovy scripts in the");
        out.println(" *        under src/gram/script and then use maven openwire:generate to regenerate ");
        out.println(" *        this file.");
        out.println(" *");
        out.println(" * ");
        out.println(" */");
        out.println("public " + this.getAbstractClassText() + "class " + this.getClassName() + " extends " + this.getBaseClass() + " {");
        out.println("");
        if (!this.isAbstractClass()) {
            out.println("    /**");
            out.println("     * Return the type of Data Structure we marshal");
            out.println("     * @return short representation of the type data structure");
            out.println("     */");
            out.println("    public byte getDataStructureType() {");
            out.println("        return " + this.getJclass().getSimpleName() + ".DATA_STRUCTURE_TYPE;");
            out.println("    }");
            out.println("    ");
            out.println("    /**");
            out.println("     * @return a new object instance");
            out.println("     */");
            out.println("    public DataStructure createObject() {");
            out.println("        return new " + this.getJclass().getSimpleName() + "();");
            out.println("    }");
            out.println("");
        }
        out.println("    /**");
        out.println("     * Un-marshal an object instance from the data input stream");
        out.println("     *");
        out.println("     * @param o the object to un-marshal");
        out.println("     * @param dataIn the data input stream to build the object from");
        out.println("     * @throws IOException");
        out.println("     */");
        out.println("    public void tightUnmarshal(OpenWireFormat wireFormat, Object o, DataInput dataIn, BooleanStream bs) throws IOException {");
        out.println("        super.tightUnmarshal(wireFormat, o, dataIn, bs);");
        if (!this.getProperties().isEmpty()) {
            out.println("");
            out.println("        " + this.getJclass().getSimpleName() + " info = (" + this.getJclass().getSimpleName() + ")o;");
        }
        if (this.isMarshallerAware()) {
            out.println("");
            out.println("        info.beforeUnmarshall(wireFormat);");
            out.println("        ");
        }
        this.generateTightUnmarshalBody(out);
        if (this.isMarshallerAware()) {
            out.println("");
            out.println("        info.afterUnmarshall(wireFormat);");
        }
        out.println("");
        out.println("    }");
        out.println("");
        out.println("");
        out.println("    /**");
        out.println("     * Write the booleans that this object uses to a BooleanStream");
        out.println("     */");
        out.println("    public int tightMarshal1(OpenWireFormat wireFormat, Object o, BooleanStream bs) throws IOException {");
        if (!this.getProperties().isEmpty()) {
            out.println("");
            out.println("        " + this.getJclass().getSimpleName() + " info = (" + this.getJclass().getSimpleName() + ")o;");
        }
        if (this.isMarshallerAware()) {
            out.println("");
            out.println("        info.beforeMarshall(wireFormat);");
        }
        out.println("");
        out.println("        int rc = super.tightMarshal1(wireFormat, o, bs);");
        final int baseSize = this.generateTightMarshal1Body(out);
        out.println("");
        out.println("        return rc + " + baseSize + ";");
        out.println("    }");
        out.println("");
        out.println("    /**");
        out.println("     * Write a object instance to data output stream");
        out.println("     *");
        out.println("     * @param o the instance to be marshaled");
        out.println("     * @param dataOut the output stream");
        out.println("     * @throws IOException thrown if an error occurs");
        out.println("     */");
        out.println("    public void tightMarshal2(OpenWireFormat wireFormat, Object o, DataOutput dataOut, BooleanStream bs) throws IOException {");
        out.println("        super.tightMarshal2(wireFormat, o, dataOut, bs);");
        if (!this.getProperties().isEmpty()) {
            out.println("");
            out.println("        " + this.getJclass().getSimpleName() + " info = (" + this.getJclass().getSimpleName() + ")o;");
        }
        this.generateTightMarshal2Body(out);
        if (this.isMarshallerAware()) {
            out.println("");
            out.println("        info.afterMarshall(wireFormat);");
        }
        out.println("");
        out.println("    }");
        out.println("");
        out.println("    /**");
        out.println("     * Un-marshal an object instance from the data input stream");
        out.println("     *");
        out.println("     * @param o the object to un-marshal");
        out.println("     * @param dataIn the data input stream to build the object from");
        out.println("     * @throws IOException");
        out.println("     */");
        out.println("    public void looseUnmarshal(OpenWireFormat wireFormat, Object o, DataInput dataIn) throws IOException {");
        out.println("        super.looseUnmarshal(wireFormat, o, dataIn);");
        if (!this.getProperties().isEmpty()) {
            out.println("");
            out.println("        " + this.getJclass().getSimpleName() + " info = (" + this.getJclass().getSimpleName() + ")o;");
        }
        if (this.isMarshallerAware()) {
            out.println("");
            out.println("        info.beforeUnmarshall(wireFormat);");
            out.println("        ");
        }
        this.generateLooseUnmarshalBody(out);
        if (this.isMarshallerAware()) {
            out.println("");
            out.println("        info.afterUnmarshall(wireFormat);");
        }
        out.println("");
        out.println("    }");
        out.println("");
        out.println("");
        out.println("    /**");
        out.println("     * Write the booleans that this object uses to a BooleanStream");
        out.println("     */");
        out.println("    public void looseMarshal(OpenWireFormat wireFormat, Object o, DataOutput dataOut) throws IOException {");
        if (!this.getProperties().isEmpty()) {
            out.println("");
            out.println("        " + this.getJclass().getSimpleName() + " info = (" + this.getJclass().getSimpleName() + ")o;");
        }
        if (this.isMarshallerAware()) {
            out.println("");
            out.println("        info.beforeMarshall(wireFormat);");
        }
        out.println("");
        out.println("        super.looseMarshal(wireFormat, o, dataOut);");
        this.generateLooseMarshalBody(out);
        out.println("");
        out.println("    }");
        out.println("}");
    }
    
    private void generateLicence(final PrintWriter out) {
        out.println("/**");
        out.println(" *");
        out.println(" * Licensed to the Apache Software Foundation (ASF) under one or more");
        out.println(" * contributor license agreements.  See the NOTICE file distributed with");
        out.println(" * this work for additional information regarding copyright ownership.");
        out.println(" * The ASF licenses this file to You under the Apache License, Version 2.0");
        out.println(" * (the \"License\"); you may not use this file except in compliance with");
        out.println(" * the License.  You may obtain a copy of the License at");
        out.println(" *");
        out.println(" * http://www.apache.org/licenses/LICENSE-2.0");
        out.println(" *");
        out.println(" * Unless required by applicable law or agreed to in writing, software");
        out.println(" * distributed under the License is distributed on an \"AS IS\" BASIS,");
        out.println(" * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.");
        out.println(" * See the License for the specific language governing permissions and");
        out.println(" * limitations under the License.");
        out.println(" */");
    }
    
    protected void processFactory() {
        if (this.factoryFile == null) {
            this.factoryFile = new File(this.destDir, this.factoryFileName + this.filePostFix);
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(this.factoryFile));
            this.generateFactory(out);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    protected void generateFactory(final PrintWriter out) {
        this.generateLicence(out);
        out.println("");
        out.println("package org.apache.activemq.openwire.v" + this.getOpenwireVersion() + ";");
        out.println("");
        out.println("import org.apache.activemq.openwire.DataStreamMarshaller;");
        out.println("import org.apache.activemq.openwire.OpenWireFormat;");
        out.println("");
        out.println("/**");
        out.println(" * MarshallerFactory for Open Wire Format.");
        out.println(" *");
        out.println(" *");
        out.println(" * NOTE!: This file is auto generated - do not modify!");
        out.println(" *        if you need to make a change, please see the modify the groovy scripts in the");
        out.println(" *        under src/gram/script and then use maven openwire:generate to regenerate ");
        out.println(" *        this file.");
        out.println(" *");
        out.println(" * ");
        out.println(" */");
        out.println("public class MarshallerFactory {");
        out.println("");
        out.println("    /**");
        out.println("     * Creates a Map of command type -> Marshallers");
        out.println("     */");
        out.println("    static final private DataStreamMarshaller marshaller[] = new DataStreamMarshaller[256];");
        out.println("    static {");
        out.println("");
        final List<JClass> list = new ArrayList<JClass>(this.getConcreteClasses());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(final Object o1, final Object o2) {
                final JClass c1 = (JClass)o1;
                final JClass c2 = (JClass)o2;
                return c1.getSimpleName().compareTo(c2.getSimpleName());
            }
        });
        for (final JClass jclass : list) {
            out.println("        add(new " + jclass.getSimpleName() + "Marshaller());");
        }
        out.println("");
        out.println("    }");
        out.println("");
        out.println("    static private void add(DataStreamMarshaller dsm) {");
        out.println("        marshaller[dsm.getDataStructureType()] = dsm;");
        out.println("    }");
        out.println("    ");
        out.println("    static public DataStreamMarshaller[] createMarshallerMap(OpenWireFormat wireFormat) {");
        out.println("        return marshaller;");
        out.println("    }");
        out.println("}");
    }
    
    @Override
    protected void processClass(final JClass jclass) {
        super.processClass(jclass);
        if (!jclass.isAbstract()) {
            this.concreteClasses.add(jclass);
        }
    }
    
    @Override
    protected String getClassName(final JClass jclass) {
        return super.getClassName(jclass) + "Marshaller";
    }
    
    @Override
    protected String getBaseClassName(final JClass jclass) {
        String answer = "BaseDataStreamMarshaller";
        final JClass superclass = jclass.getSuperclass();
        if (superclass != null) {
            final String superName = superclass.getSimpleName();
            if (!superName.equals("Object") && !superName.equals("JNDIBaseStorable") && !superName.equals("DataStructureSupport")) {
                answer = superName + "Marshaller";
            }
        }
        return answer;
    }
    
    @Override
    protected void initialiseManuallyMaintainedClasses() {
    }
    
    protected void generateTightUnmarshalBody(final PrintWriter out) {
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            final JClass propertyType = property.getType();
            final String propertyTypeName = propertyType.getSimpleName();
            if (propertyType.isArrayType() && !propertyTypeName.equals("byte[]")) {
                this.generateTightUnmarshalBodyForArrayProperty(out, property, size);
            }
            else {
                this.generateTightUnmarshalBodyForProperty(out, property, size);
            }
        }
    }
    
    protected void generateTightUnmarshalBodyForProperty(final PrintWriter out, final JProperty property, final JAnnotationValue size) {
        final String setter = property.getSetter().getSimpleName();
        final String type = property.getType().getSimpleName();
        if (type.equals("boolean")) {
            out.println("        info." + setter + "(bs.readBoolean());");
        }
        else if (type.equals("byte")) {
            out.println("        info." + setter + "(dataIn.readByte());");
        }
        else if (type.equals("char")) {
            out.println("        info." + setter + "(dataIn.readChar());");
        }
        else if (type.equals("short")) {
            out.println("        info." + setter + "(dataIn.readShort());");
        }
        else if (type.equals("int")) {
            out.println("        info." + setter + "(dataIn.readInt());");
        }
        else if (type.equals("long")) {
            out.println("        info." + setter + "(tightUnmarshalLong(wireFormat, dataIn, bs));");
        }
        else if (type.equals("String")) {
            out.println("        info." + setter + "(tightUnmarshalString(dataIn, bs));");
        }
        else if (type.equals("byte[]")) {
            if (size != null) {
                out.println("        info." + setter + "(tightUnmarshalConstByteArray(dataIn, bs, " + size.asInt() + "));");
            }
            else {
                out.println("        info." + setter + "(tightUnmarshalByteArray(dataIn, bs));");
            }
        }
        else if (type.equals("ByteSequence")) {
            out.println("        info." + setter + "(tightUnmarshalByteSequence(dataIn, bs));");
        }
        else if (this.isThrowable(property.getType())) {
            out.println("        info." + setter + "((" + property.getType().getQualifiedName() + ") tightUnmarsalThrowable(wireFormat, dataIn, bs));");
        }
        else if (this.isCachedProperty(property)) {
            out.println("        info." + setter + "((" + property.getType().getQualifiedName() + ") tightUnmarsalCachedObject(wireFormat, dataIn, bs));");
        }
        else {
            out.println("        info." + setter + "((" + property.getType().getQualifiedName() + ") tightUnmarsalNestedObject(wireFormat, dataIn, bs));");
        }
    }
    
    protected void generateTightUnmarshalBodyForArrayProperty(final PrintWriter out, final JProperty property, final JAnnotationValue size) {
        final JClass propertyType = property.getType();
        final String arrayType = propertyType.getArrayComponentType().getQualifiedName();
        final String setter = property.getSetter().getSimpleName();
        out.println();
        if (size != null) {
            out.println("        {");
            out.println("            " + arrayType + " value[] = new " + arrayType + "[" + size.asInt() + "];");
            out.println("            for( int i=0; i < " + size.asInt() + "; i++ ) {");
            out.println("                value[i] = (" + arrayType + ") tightUnmarsalNestedObject(wireFormat,dataIn, bs);");
            out.println("            }");
            out.println("            info." + setter + "(value);");
            out.println("        }");
        }
        else {
            out.println("        if (bs.readBoolean()) {");
            out.println("            short size = dataIn.readShort();");
            out.println("            " + arrayType + " value[] = new " + arrayType + "[size];");
            out.println("            for( int i=0; i < size; i++ ) {");
            out.println("                value[i] = (" + arrayType + ") tightUnmarsalNestedObject(wireFormat,dataIn, bs);");
            out.println("            }");
            out.println("            info." + setter + "(value);");
            out.println("        }");
            out.println("        else {");
            out.println("            info." + setter + "(null);");
            out.println("        }");
        }
    }
    
    protected int generateTightMarshal1Body(final PrintWriter out) {
        final List properties = this.getProperties();
        int baseSize = 0;
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            final JClass propertyType = property.getType();
            final String type = propertyType.getSimpleName();
            final String getter = "info." + property.getGetter().getSimpleName() + "()";
            if (type.equals("boolean")) {
                out.println("        bs.writeBoolean(" + getter + ");");
            }
            else if (type.equals("byte")) {
                ++baseSize;
            }
            else if (type.equals("char")) {
                baseSize += 2;
            }
            else if (type.equals("short")) {
                baseSize += 2;
            }
            else if (type.equals("int")) {
                baseSize += 4;
            }
            else if (type.equals("long")) {
                out.println("        rc+=tightMarshalLong1(wireFormat, " + getter + ", bs);");
            }
            else if (type.equals("String")) {
                out.println("        rc += tightMarshalString1(" + getter + ", bs);");
            }
            else if (type.equals("byte[]")) {
                if (size == null) {
                    out.println("        rc += tightMarshalByteArray1(" + getter + ", bs);");
                }
                else {
                    out.println("        rc += tightMarshalConstByteArray1(" + getter + ", bs, " + size.asInt() + ");");
                }
            }
            else if (type.equals("ByteSequence")) {
                out.println("        rc += tightMarshalByteSequence1(" + getter + ", bs);");
            }
            else if (propertyType.isArrayType()) {
                if (size != null) {
                    out.println("        rc += tightMarshalObjectArrayConstSize1(wireFormat, " + getter + ", bs, " + size.asInt() + ");");
                }
                else {
                    out.println("        rc += tightMarshalObjectArray1(wireFormat, " + getter + ", bs);");
                }
            }
            else if (this.isThrowable(propertyType)) {
                out.println("        rc += tightMarshalThrowable1(wireFormat, " + getter + ", bs);");
            }
            else if (this.isCachedProperty(property)) {
                out.println("        rc += tightMarshalCachedObject1(wireFormat, (DataStructure)" + getter + ", bs);");
            }
            else {
                out.println("        rc += tightMarshalNestedObject1(wireFormat, (DataStructure)" + getter + ", bs);");
            }
        }
        return baseSize;
    }
    
    protected void generateTightMarshal2Body(final PrintWriter out) {
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            final JClass propertyType = property.getType();
            final String type = propertyType.getSimpleName();
            final String getter = "info." + property.getGetter().getSimpleName() + "()";
            if (type.equals("boolean")) {
                out.println("        bs.readBoolean();");
            }
            else if (type.equals("byte")) {
                out.println("        dataOut.writeByte(" + getter + ");");
            }
            else if (type.equals("char")) {
                out.println("        dataOut.writeChar(" + getter + ");");
            }
            else if (type.equals("short")) {
                out.println("        dataOut.writeShort(" + getter + ");");
            }
            else if (type.equals("int")) {
                out.println("        dataOut.writeInt(" + getter + ");");
            }
            else if (type.equals("long")) {
                out.println("        tightMarshalLong2(wireFormat, " + getter + ", dataOut, bs);");
            }
            else if (type.equals("String")) {
                out.println("        tightMarshalString2(" + getter + ", dataOut, bs);");
            }
            else if (type.equals("byte[]")) {
                if (size != null) {
                    out.println("        tightMarshalConstByteArray2(" + getter + ", dataOut, bs, " + size.asInt() + ");");
                }
                else {
                    out.println("        tightMarshalByteArray2(" + getter + ", dataOut, bs);");
                }
            }
            else if (type.equals("ByteSequence")) {
                out.println("        tightMarshalByteSequence2(" + getter + ", dataOut, bs);");
            }
            else if (propertyType.isArrayType()) {
                if (size != null) {
                    out.println("        tightMarshalObjectArrayConstSize2(wireFormat, " + getter + ", dataOut, bs, " + size.asInt() + ");");
                }
                else {
                    out.println("        tightMarshalObjectArray2(wireFormat, " + getter + ", dataOut, bs);");
                }
            }
            else if (this.isThrowable(propertyType)) {
                out.println("        tightMarshalThrowable2(wireFormat, " + getter + ", dataOut, bs);");
            }
            else if (this.isCachedProperty(property)) {
                out.println("        tightMarshalCachedObject2(wireFormat, (DataStructure)" + getter + ", dataOut, bs);");
            }
            else {
                out.println("        tightMarshalNestedObject2(wireFormat, (DataStructure)" + getter + ", dataOut, bs);");
            }
        }
    }
    
    protected void generateLooseMarshalBody(final PrintWriter out) {
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            final JClass propertyType = property.getType();
            final String type = propertyType.getSimpleName();
            final String getter = "info." + property.getGetter().getSimpleName() + "()";
            if (type.equals("boolean")) {
                out.println("        dataOut.writeBoolean(" + getter + ");");
            }
            else if (type.equals("byte")) {
                out.println("        dataOut.writeByte(" + getter + ");");
            }
            else if (type.equals("char")) {
                out.println("        dataOut.writeChar(" + getter + ");");
            }
            else if (type.equals("short")) {
                out.println("        dataOut.writeShort(" + getter + ");");
            }
            else if (type.equals("int")) {
                out.println("        dataOut.writeInt(" + getter + ");");
            }
            else if (type.equals("long")) {
                out.println("        looseMarshalLong(wireFormat, " + getter + ", dataOut);");
            }
            else if (type.equals("String")) {
                out.println("        looseMarshalString(" + getter + ", dataOut);");
            }
            else if (type.equals("byte[]")) {
                if (size != null) {
                    out.println("        looseMarshalConstByteArray(wireFormat, " + getter + ", dataOut, " + size.asInt() + ");");
                }
                else {
                    out.println("        looseMarshalByteArray(wireFormat, " + getter + ", dataOut);");
                }
            }
            else if (type.equals("ByteSequence")) {
                out.println("        looseMarshalByteSequence(wireFormat, " + getter + ", dataOut);");
            }
            else if (propertyType.isArrayType()) {
                if (size != null) {
                    out.println("        looseMarshalObjectArrayConstSize(wireFormat, " + getter + ", dataOut, " + size.asInt() + ");");
                }
                else {
                    out.println("        looseMarshalObjectArray(wireFormat, " + getter + ", dataOut);");
                }
            }
            else if (this.isThrowable(propertyType)) {
                out.println("        looseMarshalThrowable(wireFormat, " + getter + ", dataOut);");
            }
            else if (this.isCachedProperty(property)) {
                out.println("        looseMarshalCachedObject(wireFormat, (DataStructure)" + getter + ", dataOut);");
            }
            else {
                out.println("        looseMarshalNestedObject(wireFormat, (DataStructure)" + getter + ", dataOut);");
            }
        }
    }
    
    protected void generateLooseUnmarshalBody(final PrintWriter out) {
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            final JClass propertyType = property.getType();
            final String propertyTypeName = propertyType.getSimpleName();
            if (propertyType.isArrayType() && !propertyTypeName.equals("byte[]")) {
                this.generateLooseUnmarshalBodyForArrayProperty(out, property, size);
            }
            else {
                this.generateLooseUnmarshalBodyForProperty(out, property, size);
            }
        }
    }
    
    protected void generateLooseUnmarshalBodyForProperty(final PrintWriter out, final JProperty property, final JAnnotationValue size) {
        final String setter = property.getSetter().getSimpleName();
        final String type = property.getType().getSimpleName();
        if (type.equals("boolean")) {
            out.println("        info." + setter + "(dataIn.readBoolean());");
        }
        else if (type.equals("byte")) {
            out.println("        info." + setter + "(dataIn.readByte());");
        }
        else if (type.equals("char")) {
            out.println("        info." + setter + "(dataIn.readChar());");
        }
        else if (type.equals("short")) {
            out.println("        info." + setter + "(dataIn.readShort());");
        }
        else if (type.equals("int")) {
            out.println("        info." + setter + "(dataIn.readInt());");
        }
        else if (type.equals("long")) {
            out.println("        info." + setter + "(looseUnmarshalLong(wireFormat, dataIn));");
        }
        else if (type.equals("String")) {
            out.println("        info." + setter + "(looseUnmarshalString(dataIn));");
        }
        else if (type.equals("byte[]")) {
            if (size != null) {
                out.println("        info." + setter + "(looseUnmarshalConstByteArray(dataIn, " + size.asInt() + "));");
            }
            else {
                out.println("        info." + setter + "(looseUnmarshalByteArray(dataIn));");
            }
        }
        else if (type.equals("ByteSequence")) {
            out.println("        info." + setter + "(looseUnmarshalByteSequence(dataIn));");
        }
        else if (this.isThrowable(property.getType())) {
            out.println("        info." + setter + "((" + property.getType().getQualifiedName() + ") looseUnmarsalThrowable(wireFormat, dataIn));");
        }
        else if (this.isCachedProperty(property)) {
            out.println("        info." + setter + "((" + property.getType().getQualifiedName() + ") looseUnmarsalCachedObject(wireFormat, dataIn));");
        }
        else {
            out.println("        info." + setter + "((" + property.getType().getQualifiedName() + ") looseUnmarsalNestedObject(wireFormat, dataIn));");
        }
    }
    
    protected void generateLooseUnmarshalBodyForArrayProperty(final PrintWriter out, final JProperty property, final JAnnotationValue size) {
        final JClass propertyType = property.getType();
        final String arrayType = propertyType.getArrayComponentType().getQualifiedName();
        final String setter = property.getSetter().getSimpleName();
        out.println();
        if (size != null) {
            out.println("        {");
            out.println("            " + arrayType + " value[] = new " + arrayType + "[" + size.asInt() + "];");
            out.println("            for( int i=0; i < " + size.asInt() + "; i++ ) {");
            out.println("                value[i] = (" + arrayType + ") looseUnmarsalNestedObject(wireFormat,dataIn);");
            out.println("            }");
            out.println("            info." + setter + "(value);");
            out.println("        }");
        }
        else {
            out.println("        if (dataIn.readBoolean()) {");
            out.println("            short size = dataIn.readShort();");
            out.println("            " + arrayType + " value[] = new " + arrayType + "[size];");
            out.println("            for( int i=0; i < size; i++ ) {");
            out.println("                value[i] = (" + arrayType + ") looseUnmarsalNestedObject(wireFormat,dataIn);");
            out.println("            }");
            out.println("            info." + setter + "(value);");
            out.println("        }");
            out.println("        else {");
            out.println("            info." + setter + "(null);");
            out.println("        }");
        }
    }
    
    protected String getMandatoryFlag(final JAnnotation annotation) {
        final JAnnotationValue value = annotation.getValue("mandatory");
        if (value != null) {
            final String text = value.asString();
            if (text != null && text.equalsIgnoreCase("true")) {
                return "true";
            }
        }
        return "false";
    }
    
    public List<JClass> getConcreteClasses() {
        return this.concreteClasses;
    }
    
    public void setConcreteClasses(final List<JClass> concreteClasses) {
        this.concreteClasses = concreteClasses;
    }
    
    public File getFactoryFile() {
        return this.factoryFile;
    }
    
    public void setFactoryFile(final File factoryFile) {
        this.factoryFile = factoryFile;
    }
    
    public String getFactoryFileName() {
        return this.factoryFileName;
    }
    
    public void setFactoryFileName(final String factoryFileName) {
        this.factoryFileName = factoryFileName;
    }
    
    public String getIndent() {
        return this.indent;
    }
    
    public void setIndent(final String indent) {
        this.indent = indent;
    }
    
    public String getTargetDir() {
        return this.targetDir;
    }
    
    public void setTargetDir(final String sourceDir) {
        this.targetDir = sourceDir;
    }
}
