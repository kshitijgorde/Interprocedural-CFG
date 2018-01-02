// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.codehaus.jam.JAnnotation;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jam.JPackage;
import org.codehaus.jam.JProperty;
import java.io.PrintWriter;
import org.codehaus.jam.JClass;
import java.io.File;

public class JavaTestsGenerator extends MultiSourceGenerator
{
    protected String targetDir;
    
    public JavaTestsGenerator() {
        this.targetDir = "src/test/java";
    }
    
    @Override
    public Object run() {
        if (this.destDir == null) {
            this.destDir = new File(this.targetDir + "/org/apache/activemq/openwire/v" + this.getOpenwireVersion());
        }
        return super.run();
    }
    
    @Override
    protected String getClassName(final JClass jclass) {
        if (this.isAbstract(jclass)) {
            return super.getClassName(jclass) + "TestSupport";
        }
        return super.getClassName(jclass) + "Test";
    }
    
    @Override
    protected String getBaseClassName(final JClass jclass) {
        String answer = "DataFileGeneratorTestSupport";
        if (this.superclass != null) {
            final String name = this.superclass.getSimpleName();
            if (name != null && !name.equals("JNDIBaseStorable") && !name.equals("DataStructureSupport") && !name.equals("Object")) {
                answer = name + "Test";
                if (this.isAbstract(this.getJclass().getSuperclass())) {
                    answer += "Support";
                }
            }
        }
        return answer;
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
    
    @Override
    protected void generateFile(final PrintWriter out) {
        this.generateLicence(out);
        out.println("package org.apache.activemq.openwire.v" + this.openwireVersion + ";");
        out.println("");
        out.println("import java.io.DataInputStream;");
        out.println("import java.io.DataOutputStream;");
        out.println("import java.io.IOException;");
        out.println("");
        out.println("import org.apache.activemq.openwire.*;");
        out.println("import org.apache.activemq.command.*;");
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
        out.println(" * Test case for the OpenWire marshalling for " + this.jclass.getSimpleName() + "");
        out.println(" *");
        out.println(" *");
        out.println(" * NOTE!: This file is auto generated - do not modify!");
        out.println(" *        if you need to make a change, please see the modify the groovy scripts in the");
        out.println(" *        under src/gram/script and then use maven openwire:generate to regenerate ");
        out.println(" *        this file.");
        out.println(" *");
        out.println(" * ");
        out.println(" */");
        out.println("public " + this.getAbstractClassText() + "class " + this.className + " extends " + this.baseClass + " {");
        out.println("");
        if (!this.isAbstractClass()) {
            out.println("");
            out.println("    public static " + this.jclass.getSimpleName() + "Test SINGLETON = new " + this.jclass.getSimpleName() + "Test();");
            out.println("");
            out.println("    public Object createObject() throws Exception {");
            out.println("        " + this.jclass.getSimpleName() + " info = new " + this.jclass.getSimpleName() + "();");
            out.println("        populateObject(info);");
            out.println("        return info;");
            out.println("    }");
        }
        out.println("");
        out.println("    protected void populateObject(Object object) throws Exception {");
        out.println("        super.populateObject(object);");
        out.println("        " + this.getJclass().getSimpleName() + " info = (" + this.getJclass().getSimpleName() + ") object;");
        out.println("");
        final TestDataGenerator generator = new TestDataGenerator();
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getAnnotation("openwire:property");
            String size = this.stringValue(annotation, "size");
            final String testSize = this.stringValue(annotation, "testSize");
            final String type = property.getType().getSimpleName();
            final String propertyName = property.getSimpleName();
            if ("-1".equals(testSize)) {
                continue;
            }
            final String setterName = property.getSetter().getSimpleName();
            if (type.equals("boolean")) {
                out.println("        info." + setterName + "(" + generator.createBool() + ");");
            }
            else if (type.equals("byte")) {
                out.println("        info." + setterName + "(" + generator.createByte() + ");");
            }
            else if (type.equals("char")) {
                out.println("        info." + setterName + "(" + generator.createChar() + ");");
            }
            else if (type.equals("short")) {
                out.println("        info." + setterName + "(" + generator.createShort() + ");");
            }
            else if (type.equals("int")) {
                out.println("        info." + setterName + "(" + generator.createInt() + ");");
            }
            else if (type.equals("long")) {
                out.println("        info." + setterName + "(" + generator.createLong() + ");");
            }
            else if (type.equals("byte[]")) {
                out.println("        info." + setterName + "(" + generator.createByteArray(propertyName) + ");");
            }
            else if (type.equals("String")) {
                out.println("        info." + setterName + "(\"" + generator.createString(propertyName) + "\");");
            }
            else if (type.equals("ByteSequence")) {
                out.println("        {");
                out.println("            byte data[] = " + generator.createByteArray(propertyName) + ";");
                out.println("            info." + setterName + "(new org.apache.activemq.util.ByteSequence(data,0,data.length));");
                out.println("}");
            }
            else if (type.equals("Throwable")) {
                out.println("        info." + setterName + "(createThrowable(\"" + generator.createString(propertyName) + "\"));");
            }
            else if (property.getType().isArrayType()) {
                final String arrayType = property.getType().getArrayComponentType().getSimpleName();
                if (size == null) {
                    size = "2";
                }
                if (arrayType == this.jclass.getSimpleName()) {
                    size = "0";
                }
                out.println("        {");
                out.println("            " + arrayType + " value[] = new " + arrayType + "[" + size + "];");
                out.println("            for( int i=0; i < " + size + "; i++ ) {");
                out.println("                value[i] = create" + arrayType + "(\"" + generator.createString(propertyName) + "\");");
                out.println("            }");
                out.println("            info." + setterName + "(value);");
                out.println("        }");
            }
            else {
                out.println("        info." + setterName + "(create" + type + "(\"" + generator.createString(propertyName) + "\"));");
            }
        }
        out.println("    }");
        out.println("}");
    }
    
    public String getTargetDir() {
        return this.targetDir;
    }
    
    public void setTargetDir(final String targetDir) {
        this.targetDir = targetDir;
    }
}
