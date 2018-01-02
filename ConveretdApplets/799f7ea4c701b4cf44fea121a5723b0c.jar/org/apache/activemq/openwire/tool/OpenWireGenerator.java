// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.codehaus.jam.JAnnotation;
import org.codehaus.jam.JamClassIterator;
import org.codehaus.jam.JField;
import org.codehaus.jam.JClass;
import org.codehaus.jam.JAnnotationValue;
import org.codehaus.jam.JMethod;
import org.codehaus.jam.JProperty;
import org.codehaus.jam.JamService;

public abstract class OpenWireGenerator
{
    protected int openwireVersion;
    protected String filePostFix;
    protected JamService jam;
    
    public OpenWireGenerator() {
        this.filePostFix = ".java";
    }
    
    public boolean isValidProperty(final JProperty it) {
        final JMethod getter = it.getGetter();
        return getter != null && it.getSetter() != null && !getter.isStatic() && getter.getAnnotation("openwire:property") != null;
    }
    
    public boolean isCachedProperty(final JProperty it) {
        final JMethod getter = it.getGetter();
        if (!this.isValidProperty(it)) {
            return false;
        }
        final JAnnotationValue value = getter.getAnnotation("openwire:property").getValue("cache");
        return value != null && value.asBoolean();
    }
    
    public boolean isAbstract(final JClass j) {
        final JField[] fields = j.getFields();
        for (int i = 0; i < fields.length; ++i) {
            final JField field = fields[i];
            if (field.isStatic() && field.isPublic() && field.isFinal() && field.getSimpleName().equals("DATA_STRUCTURE_TYPE")) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isThrowable(final JClass j) {
        return j.getQualifiedName().equals(Throwable.class.getName()) || (j.getSuperclass() != null && this.isThrowable(j.getSuperclass()));
    }
    
    public boolean isMarshallAware(final JClass j) {
        if (this.filePostFix.endsWith("java")) {
            final JClass[] interfaces = j.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                if (interfaces[i].getQualifiedName().equals("org.apache.activemq.command.MarshallAware")) {
                    return true;
                }
            }
            return false;
        }
        final String simpleName = j.getSimpleName();
        return simpleName.equals("ActiveMQMessage") || simpleName.equals("WireFormatInfo");
    }
    
    public JamService getJam() {
        return this.jam;
    }
    
    public JamClassIterator getClasses() {
        return this.getJam().getClasses();
    }
    
    public int getOpenwireVersion() {
        return this.openwireVersion;
    }
    
    public void setOpenwireVersion(final int openwireVersion) {
        this.openwireVersion = openwireVersion;
    }
    
    public String toCSharpType(final JClass type) {
        final String name = type.getSimpleName();
        if (name.equals("String")) {
            return "string";
        }
        if (name.equals("Throwable") || name.equals("Exception")) {
            return "BrokerError";
        }
        if (name.equals("ByteSequence")) {
            return "byte[]";
        }
        if (name.equals("boolean")) {
            return "bool";
        }
        return name;
    }
    
    public String getOpenWireOpCode(final JClass element) {
        if (element != null) {
            final JAnnotation annotation = element.getAnnotation("openwire:marshaller");
            return this.stringValue(annotation, "code", "0");
        }
        return "0";
    }
    
    protected String stringValue(final JAnnotation annotation, final String name) {
        return this.stringValue(annotation, name, null);
    }
    
    protected String stringValue(final JAnnotation annotation, final String name, final String defaultValue) {
        if (annotation != null) {
            final JAnnotationValue value = annotation.getValue(name);
            if (value != null) {
                return value.asString();
            }
        }
        return defaultValue;
    }
    
    public void setJam(final JamService jam) {
        this.jam = jam;
    }
    
    public String decapitalize(final String text) {
        if (text == null) {
            return null;
        }
        return text.substring(0, 1).toLowerCase() + text.substring(1);
    }
    
    public String capitalize(final String text) {
        if (text == null) {
            return null;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
