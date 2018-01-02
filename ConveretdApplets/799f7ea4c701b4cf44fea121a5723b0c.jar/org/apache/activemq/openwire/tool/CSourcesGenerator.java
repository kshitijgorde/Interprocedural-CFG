// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.codehaus.jam.JAnnotationValue;
import org.codehaus.jam.JAnnotation;
import java.util.Iterator;
import org.codehaus.jam.JClass;
import org.codehaus.jam.JProperty;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;

public class CSourcesGenerator extends CHeadersGenerator
{
    @Override
    public Object run() {
        this.filePostFix = ".c";
        if (this.destFile == null) {
            this.destFile = new File(this.targetDir + "/ow_commands_v" + this.getOpenwireVersion() + ".c");
        }
        return super.run();
    }
    
    @Override
    protected List sort(final List source) {
        return source;
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
        out.println("");
        out.println("");
        out.println("#include \"ow_commands_v" + this.openwireVersion + ".h\"");
        out.println("");
        out.println("#define SUCCESS_CHECK( f ) { apr_status_t rc=f; if(rc!=APR_SUCCESS) return rc; }");
        out.println("");
    }
    
    @Override
    protected void generateFile(final PrintWriter out) throws Exception {
        final ArrayList<JProperty> properties = new ArrayList<JProperty>();
        this.jclass.getDeclaredProperties();
        for (int i = 0; i < this.jclass.getDeclaredProperties().length; ++i) {
            final JProperty p = this.jclass.getDeclaredProperties()[i];
            if (this.isValidProperty(p)) {
                properties.add(p);
            }
        }
        final String name = this.jclass.getSimpleName();
        String type = ("ow_" + name).toUpperCase() + "_TYPE";
        String baseName = "DataStructure";
        for (JClass superclass = this.jclass.getSuperclass(); superclass.getSuperclass() != null; superclass = superclass.getSuperclass()) {
            if (this.sortedClasses.contains(superclass)) {
                baseName = superclass.getSimpleName();
                break;
            }
        }
        out.println("ow_boolean ow_is_a_" + name + "(ow_DataStructure *object) {");
        out.println("   if( object == 0 )");
        out.println("      return 0;");
        out.println("      ");
        out.println("   switch(object->structType) {");
        for (final JClass sub : this.sortedClasses) {
            final String subtype = "OW_" + sub.getSimpleName().toUpperCase() + "_TYPE";
            if (this.jclass.isAssignableFrom(sub) && !this.isAbstract(sub)) {
                out.println("");
                out.println("   case " + subtype + ":");
            }
        }
        out.println("");
        out.println("      return 1;");
        out.println("   }");
        out.println("   return 0;");
        out.println("}");
        if (!this.isAbstract(this.jclass)) {
            out.println("");
            out.println("");
            out.println("ow_" + name + " *ow_" + name + "_create(apr_pool_t *pool) ");
            out.println("{");
            out.println("   ow_" + name + " *value = apr_pcalloc(pool,sizeof(ow_" + name + "));");
            out.println("   if( value!=0 ) {");
            out.println("      ((ow_DataStructure*)value)->structType = " + type + ";");
            out.println("   }");
            out.println("   return value;");
            out.println("}");
        }
        out.println("");
        out.println("");
        out.println("apr_status_t ow_marshal1_" + name + "(ow_bit_buffer *buffer, ow_" + name + " *object)");
        out.println("{");
        out.println("   ow_marshal1_" + baseName + "(buffer, (ow_" + baseName + "*)object);");
        for (final JProperty property : properties) {
            final String propname = this.toPropertyCase(property.getSimpleName());
            final boolean cached = this.isCachedProperty(property);
            final JAnnotation annotation = property.getGetter().getAnnotation("openwire:property");
            final JAnnotationValue size = annotation.getValue("size");
            type = property.getType().getQualifiedName();
            if (type.equals("boolean")) {
                out.println("   ow_bit_buffer_append(buffer, object->" + propname + ");");
            }
            else if (!type.equals("byte")) {
                if (!type.equals("char")) {
                    if (!type.equals("short")) {
                        if (!type.equals("int")) {
                            if (type.equals("long")) {
                                out.println("   ow_marshal1_long(buffer, object->" + propname + ");");
                            }
                            else if (type.equals("byte[]")) {
                                if (size == null) {
                                    out.println("   ow_bit_buffer_append(buffer,  object->" + propname + "!=0 );");
                                }
                            }
                            else if (type.equals("org.apache.activeio.packet.ByteSequence")) {
                                if (size == null) {
                                    out.println("   ow_bit_buffer_append(buffer,  object->" + propname + "!=0 );");
                                }
                            }
                            else if (type.equals("java.lang.String")) {
                                out.println("   ow_marshal1_string(buffer, object->" + propname + ");");
                            }
                            else if (property.getType().isArrayType()) {
                                if (size != null) {
                                    out.println("   SUCCESS_CHECK(ow_marshal1_DataStructure_array_const_size(buffer, object->" + propname + ", " + size.asInt() + "));");
                                }
                                else {
                                    out.println("   SUCCESS_CHECK(ow_marshal1_DataStructure_array(buffer, object->" + propname + "));");
                                }
                            }
                            else if (this.isThrowable(property.getType())) {
                                out.println("   SUCCESS_CHECK(ow_marshal1_throwable(buffer, object->" + propname + "));");
                            }
                            else if (cached) {
                                out.println("   SUCCESS_CHECK(ow_marshal1_cached_object(buffer, (ow_DataStructure*)object->" + propname + "));");
                            }
                            else {
                                out.println("   SUCCESS_CHECK(ow_marshal1_nested_object(buffer, (ow_DataStructure*)object->" + propname + "));");
                            }
                        }
                    }
                }
            }
            out.println("");
        }
        out.println("   ");
        out.println("   return APR_SUCCESS;");
        out.println("}");
        out.println("apr_status_t ow_marshal2_" + name + "(ow_byte_buffer *buffer, ow_bit_buffer *bitbuffer, ow_" + name + " *object)");
        out.println("{");
        out.println("   ow_marshal2_" + baseName + "(buffer, bitbuffer, (ow_" + baseName + "*)object);   ");
        for (final JProperty property : properties) {
            final JAnnotation annotation2 = property.getGetter().getAnnotation("openwire:property");
            final JAnnotationValue size2 = annotation2.getValue("size");
            final Object propname2 = this.toPropertyCase(property.getSimpleName());
            final boolean cached2 = this.isCachedProperty(property);
            type = property.getType().getQualifiedName();
            if (type.equals("boolean")) {
                out.println("   ow_bit_buffer_read(bitbuffer);");
            }
            else if (type.equals("byte")) {
                out.println("   SUCCESS_CHECK(ow_byte_buffer_append_" + type + "(buffer, object->" + propname2 + "));");
            }
            else if (type.equals("char")) {
                out.println("   SUCCESS_CHECK(ow_byte_buffer_append_" + type + "(buffer, object->" + propname2 + "));");
            }
            else if (type.equals("short")) {
                out.println("   SUCCESS_CHECK(ow_byte_buffer_append_" + type + "(buffer, object->" + propname2 + "));");
            }
            else if (type.equals("int")) {
                out.println("   SUCCESS_CHECK(ow_byte_buffer_append_" + type + "(buffer, object->" + propname2 + "));");
            }
            else if (type.equals("long")) {
                out.println("   SUCCESS_CHECK(ow_marshal2_long(buffer, bitbuffer, object->" + propname2 + "));");
            }
            else if (type.equals("byte[]")) {
                if (size2 != null) {
                    out.println("   SUCCESS_CHECK(ow_marshal2_byte_array_const_size(buffer, object->" + propname2 + ", " + size2.asInt() + "));");
                }
                else {
                    out.println("   SUCCESS_CHECK(ow_marshal2_byte_array(buffer, bitbuffer, object->" + propname2 + "));");
                }
            }
            else if (type.equals("org.apache.activeio.packet.ByteSequence")) {
                if (size2 != null) {
                    out.println("   SUCCESS_CHECK(ow_marshal2_byte_array_const_size(buffer, object->" + propname2 + ", " + size2.asInt() + "));");
                }
                else {
                    out.println("   SUCCESS_CHECK(ow_marshal2_byte_array(buffer, bitbuffer, object->" + propname2 + "));");
                }
            }
            else if (type.equals("java.lang.String")) {
                out.println("   SUCCESS_CHECK(ow_marshal2_string(buffer, bitbuffer, object->" + propname2 + "));");
            }
            else if (property.getType().isArrayType()) {
                if (size2 != null) {
                    out.println("   SUCCESS_CHECK(ow_marshal2_DataStructure_array_const_size(buffer, bitbuffer, object->" + propname2 + ", " + size2.asInt() + "));");
                }
                else {
                    out.println("   SUCCESS_CHECK(ow_marshal2_DataStructure_array(buffer, bitbuffer, object->" + propname2 + "));");
                }
            }
            else if (this.isThrowable(property.getType())) {
                out.println("   SUCCESS_CHECK(ow_marshal2_throwable(buffer, bitbuffer, object->" + propname2 + "));");
            }
            else if (cached2) {
                out.println("   SUCCESS_CHECK(ow_marshal2_cached_object(buffer, bitbuffer, (ow_DataStructure*)object->" + propname2 + "));");
            }
            else {
                out.println("   SUCCESS_CHECK(ow_marshal2_nested_object(buffer, bitbuffer, (ow_DataStructure*)object->" + propname2 + "));");
            }
            out.println("");
        }
        out.println("   ");
        out.println("   return APR_SUCCESS;");
        out.println("}");
        out.println("");
        out.println("apr_status_t ow_unmarshal_" + name + "(ow_byte_array *buffer, ow_bit_buffer *bitbuffer, ow_" + name + " *object, apr_pool_t *pool)");
        out.println("{");
        out.println("   ow_unmarshal_" + baseName + "(buffer, bitbuffer, (ow_" + baseName + "*)object, pool);   ");
        for (final JProperty property : properties) {
            final JAnnotation annotation2 = property.getGetter().getAnnotation("openwire:property");
            final JAnnotationValue size2 = annotation2.getValue("size");
            final String propname3 = this.toPropertyCase(property.getSimpleName());
            final boolean cached2 = this.isCachedProperty(property);
            type = property.getType().getQualifiedName();
            if (type.equals("boolean")) {
                out.println("   object->" + propname3 + " = ow_bit_buffer_read(bitbuffer);");
            }
            else if (type.equals("byte")) {
                out.println("   SUCCESS_CHECK(ow_byte_array_read_" + type + "(buffer, &object->" + propname3 + "));");
            }
            else if (type.equals("char")) {
                out.println("   SUCCESS_CHECK(ow_byte_array_read_" + type + "(buffer, &object->" + propname3 + "));");
            }
            else if (type.equals("short")) {
                out.println("   SUCCESS_CHECK(ow_byte_array_read_" + type + "(buffer, &object->" + propname3 + "));");
            }
            else if (type.equals("int")) {
                out.println("   SUCCESS_CHECK(ow_byte_array_read_" + type + "(buffer, &object->" + propname3 + "));");
            }
            else if (type.equals("long")) {
                out.println("   SUCCESS_CHECK(ow_unmarshal_long(buffer, bitbuffer, &object->" + propname3 + ", pool));");
            }
            else if (type.equals("byte[]")) {
                if (size2 != null) {
                    out.println("   SUCCESS_CHECK(ow_unmarshal_byte_array_const_size(buffer, &object->" + propname3 + ", " + size2.asInt() + ", pool));");
                }
                else {
                    out.println("   SUCCESS_CHECK(ow_unmarshal_byte_array(buffer, bitbuffer, &object->" + propname3 + ", pool));");
                }
            }
            else if (type.equals("org.apache.activeio.packet.ByteSequence")) {
                if (size2 != null) {
                    out.println("   SUCCESS_CHECK(ow_unmarshal_byte_array_const_size(buffer, &object->" + propname3 + ", " + size2.asInt() + ", pool));");
                }
                else {
                    out.println("   SUCCESS_CHECK(ow_unmarshal_byte_array(buffer, bitbuffer, &object->" + propname3 + ", pool));");
                }
            }
            else if (type.equals("java.lang.String")) {
                out.println("   SUCCESS_CHECK(ow_unmarshal_string(buffer, bitbuffer, &object->" + propname3 + ", pool));");
            }
            else if (property.getType().isArrayType()) {
                if (size2 != null) {
                    out.println("   SUCCESS_CHECK(ow_unmarshal_DataStructure_array_const_size(buffer, bitbuffer, &object->" + propname3 + ", " + size2.asInt() + ", pool));");
                }
                else {
                    out.println("   SUCCESS_CHECK(ow_unmarshal_DataStructure_array(buffer, bitbuffer, &object->" + propname3 + ", pool));");
                }
            }
            else if (this.isThrowable(property.getType())) {
                out.println("   SUCCESS_CHECK(ow_unmarshal_throwable(buffer, bitbuffer, &object->" + propname3 + ", pool));");
            }
            else if (cached2) {
                out.println("   SUCCESS_CHECK(ow_unmarshal_cached_object(buffer, bitbuffer, (ow_DataStructure**)&object->" + propname3 + ", pool));");
            }
            else {
                out.println("   SUCCESS_CHECK(ow_unmarshal_nested_object(buffer, bitbuffer, (ow_DataStructure**)&object->" + propname3 + ", pool));");
            }
            out.println("");
        }
        out.println("   ");
        out.println("   return APR_SUCCESS;");
        out.println("}");
    }
    
    @Override
    protected void generateTearDown(final PrintWriter out) {
        out.println("");
        out.println("ow_DataStructure *ow_create_object(ow_byte type, apr_pool_t *pool)");
        out.println("{");
        out.println("   switch( type ) {");
        for (final JClass jclass : this.sortedClasses) {
            final String name = jclass.getSimpleName();
            final String type = ("ow_" + name).toUpperCase() + "_TYPE";
            if (!this.isAbstract(jclass)) {
                out.println("      case " + type + ": return (ow_DataStructure *)ow_" + name + "_create(pool);");
            }
        }
        out.println("");
        out.println("   }");
        out.println("   return 0;");
        out.println("}");
        out.println("");
        out.println("apr_status_t ow_marshal1_object(ow_bit_buffer *buffer, ow_DataStructure *object)");
        out.println("{");
        out.println("   switch( object->structType ) {");
        for (final JClass jclass : this.sortedClasses) {
            final String name = jclass.getSimpleName();
            final String type = ("ow_" + name).toUpperCase() + "_TYPE";
            if (!this.isAbstract(jclass)) {
                out.println("      case " + type + ": return ow_marshal1_" + name + "(buffer, (ow_" + name + "*)object);");
            }
        }
        out.println("");
        out.println("   }");
        out.println("   return APR_EGENERAL;");
        out.println("}");
        out.println("");
        out.println("apr_status_t ow_marshal2_object(ow_byte_buffer *buffer, ow_bit_buffer *bitbuffer, ow_DataStructure *object)");
        out.println("{");
        out.println("   switch( object->structType ) {");
        for (final JClass jclass : this.sortedClasses) {
            final String name = jclass.getSimpleName();
            final String type = ("ow_" + name).toUpperCase() + "_TYPE";
            if (!this.isAbstract(jclass)) {
                out.println("      case " + type + ": return ow_marshal2_" + name + "(buffer, bitbuffer, (ow_" + name + "*)object);");
            }
        }
        out.println("");
        out.println("   }");
        out.println("   return APR_EGENERAL;");
        out.println("}");
        out.println("");
        out.println("apr_status_t ow_unmarshal_object(ow_byte_array *buffer, ow_bit_buffer *bitbuffer, ow_DataStructure *object, apr_pool_t *pool)");
        out.println("{");
        out.println("   switch( object->structType ) {");
        for (final JClass jclass : this.sortedClasses) {
            final String name = jclass.getSimpleName();
            final String type = ("ow_" + name).toUpperCase() + "_TYPE";
            if (!this.isAbstract(jclass)) {
                out.println("      case " + type + ": return ow_unmarshal_" + name + "(buffer, bitbuffer, (ow_" + name + "*)object, pool);");
            }
        }
        out.println("");
        out.println("   }");
        out.println("   return APR_EGENERAL;");
        out.println("}");
    }
}
