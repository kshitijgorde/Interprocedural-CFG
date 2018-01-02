// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import java.util.Iterator;
import java.util.List;
import org.codehaus.jam.JProperty;
import java.io.PrintWriter;
import org.codehaus.jam.JClass;
import java.io.File;

public class CppClassesGenerator extends MultiSourceGenerator
{
    protected String targetDir;
    
    public CppClassesGenerator() {
        this.targetDir = "./src/main/cpp";
    }
    
    @Override
    public Object run() {
        this.filePostFix = this.getFilePostFix();
        if (this.destDir == null) {
            this.destDir = new File(this.targetDir + "/activemq/command");
        }
        return super.run();
    }
    
    protected String getFilePostFix() {
        return ".cpp";
    }
    
    public String toCppType(final JClass type) {
        String name = type.getSimpleName();
        if (name.equals("String")) {
            return "p<string>";
        }
        if (type.isArrayType()) {
            if (name.equals("byte[]")) {
                name = "char[]";
            }
            else if (name.equals("DataStructure[]")) {
                name = "IDataStructure[]";
            }
            return "array<" + name.substring(0, name.length() - 2) + ">";
        }
        if (name.equals("Throwable") || name.equals("Exception")) {
            return "p<BrokerError>";
        }
        if (name.equals("ByteSequence")) {
            return "array<char>";
        }
        if (name.equals("boolean")) {
            return "bool";
        }
        if (name.equals("long")) {
            return "long long";
        }
        if (name.equals("byte")) {
            return "char";
        }
        if (name.equals("Command") || name.equals("DataStructure")) {
            return "p<I" + name + ">";
        }
        if (!type.isPrimitiveType()) {
            return "p<" + name + ">";
        }
        return name;
    }
    
    public String toCppDefaultValue(final JClass type) {
        final String name = type.getSimpleName();
        if (name.equals("boolean")) {
            return "false";
        }
        if (!type.isPrimitiveType()) {
            return "NULL";
        }
        return "0";
    }
    
    public String toMarshalMethodName(final JClass type) {
        final String name = type.getSimpleName();
        if (name.equals("String")) {
            return "marshalString";
        }
        if (type.isArrayType()) {
            if (type.getArrayComponentType().isPrimitiveType() && name.equals("byte[]")) {
                return "marshalByteArray";
            }
            return "marshalObjectArray";
        }
        else {
            if (name.equals("ByteSequence")) {
                return "marshalByteArray";
            }
            if (name.equals("short")) {
                return "marshalShort";
            }
            if (name.equals("int")) {
                return "marshalInt";
            }
            if (name.equals("long")) {
                return "marshalLong";
            }
            if (name.equals("byte")) {
                return "marshalByte";
            }
            if (name.equals("double")) {
                return "marshalDouble";
            }
            if (name.equals("float")) {
                return "marshalFloat";
            }
            if (name.equals("boolean")) {
                return "marshalBoolean";
            }
            if (!type.isPrimitiveType()) {
                return "marshalObject";
            }
            return name;
        }
    }
    
    public String toUnmarshalMethodName(final JClass type) {
        final String name = type.getSimpleName();
        if (name.equals("String")) {
            return "unmarshalString";
        }
        if (type.isArrayType()) {
            if (type.getArrayComponentType().isPrimitiveType() && name.equals("byte[]")) {
                return "unmarshalByteArray";
            }
            return "unmarshalObjectArray";
        }
        else {
            if (name.equals("ByteSequence")) {
                return "unmarshalByteArray";
            }
            if (name.equals("short")) {
                return "unmarshalShort";
            }
            if (name.equals("int")) {
                return "unmarshalInt";
            }
            if (name.equals("long")) {
                return "unmarshalLong";
            }
            if (name.equals("byte")) {
                return "unmarshalByte";
            }
            if (name.equals("double")) {
                return "unmarshalDouble";
            }
            if (name.equals("float")) {
                return "unmarshalFloat";
            }
            if (name.equals("boolean")) {
                return "unmarshalBoolean";
            }
            if (!type.isPrimitiveType()) {
                return "unmarshalObject";
            }
            return name;
        }
    }
    
    public String toUnmarshalCast(final JClass type) {
        final String name = this.toCppType(type);
        if (name.startsWith("p<")) {
            return "p_cast<" + name.substring(2);
        }
        if (name.startsWith("array<") && type.isArrayType() && !type.getArrayComponentType().isPrimitiveType() && !type.getSimpleName().equals("ByteSequence")) {
            return "array_cast<" + name.substring(6);
        }
        return "";
    }
    
    protected void generateLicence(final PrintWriter out) {
        out.println("/**");
        out.println(" * Licensed to the Apache Software Foundation (ASF) under one or more");
        out.println(" * contributor license agreements.  See the NOTICE file distributed with");
        out.println(" * this work for additional information regarding copyright ownership.");
        out.println(" * The ASF licenses this file to You under the Apache License, Version 2.0");
        out.println(" * (the \"License\"); you may not use this file except in compliance with");
        out.println(" * the License.  You may obtain a copy of the License at");
        out.println(" *");
        out.println(" *      http://www.apache.org/licenses/LICENSE-2.0");
        out.println(" *");
        out.println(" * Unless required by applicable law or agreed to in writing, software");
        out.println(" * distributed under the License is distributed on an \"AS IS\" BASIS,");
        out.println(" * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.");
        out.println(" * See the License for the specific language governing permissions and");
        out.println(" * limitations under the License.");
        out.println(" */");
    }
    
    @Override
    protected void generateFile(final PrintWriter out) throws Exception {
        this.generateLicence(out);
        out.println("#include \"activemq/command/" + this.className + ".hpp\"");
        out.println("");
        out.println("using namespace apache::activemq::command;");
        out.println("");
        out.println("/*");
        out.println(" *");
        out.println(" *  Command and marshalling code for OpenWire format for " + this.className + "");
        out.println(" *");
        out.println(" *");
        out.println(" *  NOTE!: This file is autogenerated - do not modify!");
        out.println(" *         if you need to make a change, please see the Groovy scripts in the");
        out.println(" *         activemq-core module");
        out.println(" *");
        out.println(" */");
        out.println("" + this.className + "::" + this.className + "()");
        out.println("{");
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            final String value = this.toCppDefaultValue(property.getType());
            final String propertyName = property.getSimpleName();
            final String parameterName = this.decapitalize(propertyName);
            out.println("    this->" + parameterName + " = " + value + " ;");
        }
        out.println("}");
        out.println("");
        out.println("" + this.className + "::~" + this.className + "()");
        out.println("{");
        out.println("}");
        out.println("");
        out.println("unsigned char " + this.className + "::getDataStructureType()");
        out.println("{");
        out.println("    return " + this.className + "::TYPE ; ");
        out.println("}");
        for (final JProperty property : properties) {
            final String type = this.toCppType(property.getType());
            final String propertyName = property.getSimpleName();
            final String parameterName = this.decapitalize(propertyName);
            out.println("");
            out.println("        ");
            out.println("" + type + " " + this.className + "::get" + propertyName + "()");
            out.println("{");
            out.println("    return " + parameterName + " ;");
            out.println("}");
            out.println("");
            out.println("void " + this.className + "::set" + propertyName + "(" + type + " " + parameterName + ")");
            out.println("{");
            out.println("    this->" + parameterName + " = " + parameterName + " ;");
            out.println("}");
        }
        out.println("");
        out.println("int " + this.className + "::marshal(p<IMarshaller> marshaller, int mode, p<IOutputStream> ostream) throw (IOException)");
        out.println("{");
        out.println("    int size = 0 ;");
        out.println("");
        out.println("    size += " + this.baseClass + "::marshal(marshaller, mode, ostream) ; ");
        for (final JProperty property : properties) {
            final String marshalMethod = this.toMarshalMethodName(property.getType());
            final String propertyName = this.decapitalize(property.getSimpleName());
            out.println("    size += marshaller->" + marshalMethod + "(" + propertyName + ", mode, ostream) ; ");
        }
        out.println("    return size ;");
        out.println("}");
        out.println("");
        out.println("void " + this.className + "::unmarshal(p<IMarshaller> marshaller, int mode, p<IInputStream> istream) throw (IOException)");
        out.println("{");
        out.println("    " + this.baseClass + "::unmarshal(marshaller, mode, istream) ; ");
        for (final JProperty property : properties) {
            final String cast = this.toUnmarshalCast(property.getType());
            final String unmarshalMethod = this.toUnmarshalMethodName(property.getType());
            final String propertyName2 = this.decapitalize(property.getSimpleName());
            out.println("    " + propertyName2 + " = " + cast + "(marshaller->" + unmarshalMethod + "(mode, istream)) ; ");
        }
        out.println("}");
    }
    
    public String getTargetDir() {
        return this.targetDir;
    }
    
    public void setTargetDir(final String targetDir) {
        this.targetDir = targetDir;
    }
}
