// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.codehaus.jam.JAnnotation;
import org.codehaus.jam.JProperty;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.codehaus.jam.JClass;
import java.util.List;
import java.io.PrintWriter;
import java.io.File;

public class CHeadersGenerator extends SingleSourceGenerator
{
    protected String targetDir;
    
    public CHeadersGenerator() {
        this.targetDir = "./src/lib/openwire";
    }
    
    @Override
    public Object run() {
        this.filePostFix = ".h";
        if (this.destFile == null) {
            this.destFile = new File(this.targetDir + "/ow_commands_v" + this.getOpenwireVersion() + ".h");
        }
        return super.run();
    }
    
    public String getTargetDir() {
        return this.targetDir;
    }
    
    public void setTargetDir(final String targetDir) {
        this.targetDir = targetDir;
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
    
    String changeCase(final String value) {
        final StringBuffer b = new StringBuffer();
        final char[] cs = value.toCharArray();
        for (int i = 0; i < cs.length; ++i) {
            final char c = cs[i];
            if (Character.isUpperCase(c)) {
                b.append('_');
                b.append(Character.toLowerCase(c));
            }
            else {
                b.append(c);
            }
        }
        return b.toString();
    }
    
    String toPropertyCase(final String value) {
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }
    
    @Override
    protected List<JClass> sort(final List source) {
        final LinkedHashMap<JClass, JClass> rc = new LinkedHashMap<JClass, JClass>();
        final ArrayList classes = new ArrayList(source);
        Collections.sort((List<Object>)classes, new Comparator() {
            @Override
            public int compare(final Object o1, final Object o2) {
                final JClass c1 = (JClass)o1;
                final JClass c2 = (JClass)o2;
                return c1.getSimpleName().compareTo(c2.getSimpleName());
            }
        });
        final HashMap<JClass, JClass> classNames = new HashMap<JClass, JClass>();
        for (final JClass c : classes) {
            classNames.put(c, c);
        }
        for (final JClass c : classes) {
            if (!classNames.containsKey(c.getSuperclass())) {
                rc.put(c, c);
            }
        }
        for (final JClass c : classes) {
            if (!rc.containsKey(c)) {
                rc.put(c, c);
            }
        }
        return new ArrayList<JClass>(rc.keySet());
    }
    
    void generateFields(final PrintWriter out, final JClass jclass) {
        if (jclass.getSuperclass() == null || jclass.getSuperclass().getSimpleName().equals("Object")) {
            out.println("");
            out.println("   ow_byte structType;");
        }
        else {
            this.generateFields(out, jclass.getSuperclass());
        }
        final ArrayList<JProperty> properties = new ArrayList<JProperty>();
        jclass.getDeclaredProperties();
        for (int i = 0; i < jclass.getDeclaredProperties().length; ++i) {
            final JProperty p = jclass.getDeclaredProperties()[i];
            if (this.isValidProperty(p)) {
                properties.add(p);
            }
        }
        for (final JProperty property : properties) {
            final JAnnotation annotation = property.getGetter().getAnnotation("openwire:property");
            final String name = this.toPropertyCase(property.getSimpleName());
            final String type = property.getType().getQualifiedName();
            if (type.equals("boolean")) {
                out.println("   ow_" + type + " " + name + ";");
            }
            else if (type.equals("byte")) {
                out.println("   ow_" + type + " " + name + ";");
            }
            else if (type.equals("char")) {
                out.println("   ow_" + type + " " + name + ";");
            }
            else if (type.equals("short")) {
                out.println("   ow_" + type + " " + name + ";");
            }
            else if (type.equals("int")) {
                out.println("   ow_" + type + " " + name + ";");
            }
            else if (type.equals("long")) {
                out.println("   ow_" + type + " " + name + ";");
            }
            else if (type.equals("byte[]")) {
                out.println("   ow_byte_array *" + name + ";");
            }
            else if (type.equals("org.apache.activeio.packet.ByteSequence")) {
                out.println("   ow_byte_array *" + name + ";");
            }
            else if (type.equals("org.apache.activeio.packet.ByteSequence")) {
                out.println("   ow_byte_array *" + name + ";");
            }
            else if (type.equals("java.lang.String")) {
                out.println("   ow_string *" + name + ";");
            }
            else if (property.getType().isArrayType()) {
                out.println("   ow_DataStructure_array *" + name + ";");
            }
            else if (this.isThrowable(property.getType())) {
                out.println("   ow_throwable *" + name + ";");
            }
            else {
                out.println("   struct ow_" + property.getType().getSimpleName() + " *" + name + ";");
            }
        }
    }
    
    @Override
    protected void generateSetup(final PrintWriter out) {
        this.generateLicence(out);
        out.println("");
        out.println("/*****************************************************************************************");
        out.println(" *  ");
        out.println(" * NOTE!: This file is auto generated - do not modify!");
        out.println(" *        if you need to make a change, please see the modify the groovy scripts in the");
        out.println(" *        under src/gram/script and then use maven openwire:generate to regenerate ");
        out.println(" *        this file.");
        out.println(" *  ");
        out.println(" *****************************************************************************************/");
        out.println(" ");
        out.println("#ifndef OW_COMMANDS_V" + this.openwireVersion + "_H");
        out.println("#define OW_COMMANDS_V" + this.openwireVersion + "_H");
        out.println("");
        out.println("#include \"ow.h\"");
        out.println("");
        out.println("#ifdef __cplusplus");
        out.println("extern \"C\" {");
        out.println("#endif /* __cplusplus */");
        out.println("      ");
        out.println("#define OW_WIREFORMAT_VERSION " + this.openwireVersion + "");
        out.println("#define OW_WIREFORMAT_STACK_TRACE_MASK     0x00000001;");
        out.println("#define OW_WIREFORMAT_TCP_NO_DELAY_MASK    0x00000002;");
        out.println("#define OW_WIREFORMAT_CACHE_MASK           0x00000004;");
        out.println("#define OW_WIREFORMAT_COMPRESSION_MASK     0x00000008;");
        for (final JClass jclass : this.sortedClasses) {
            final String name = jclass.getSimpleName();
            final String type = ("ow_" + name).toUpperCase() + "_TYPE";
            if (!this.isAbstract(jclass)) {
                out.println("#define " + type + " " + this.getOpenWireOpCode(jclass));
            }
        }
        out.println("      ");
        out.println("apr_status_t ow_bitmarshall(ow_bit_buffer *buffer, ow_DataStructure *object);");
        out.println("apr_status_t ow_marshall(ow_byte_buffer *buffer, ow_DataStructure *object);");
    }
    
    @Override
    protected void generateFile(final PrintWriter out) throws Exception {
        final String structName = this.jclass.getSimpleName();
        out.println("");
        out.println("typedef struct ow_" + structName + " {");
        this.generateFields(out, this.jclass);
        out.println("");
        out.println("} ow_" + structName + ";");
        out.println("ow_" + structName + " *ow_" + structName + "_create(apr_pool_t *pool);");
        out.println("ow_boolean ow_is_a_" + structName + "(ow_DataStructure *object);");
    }
    
    @Override
    protected void generateTearDown(final PrintWriter out) {
        out.println("");
        out.println("#ifdef __cplusplus");
        out.println("}");
        out.println("#endif");
        out.println("");
        out.println("#endif  /* ! OW_COMMANDS_V" + this.openwireVersion + "_H */");
    }
}
