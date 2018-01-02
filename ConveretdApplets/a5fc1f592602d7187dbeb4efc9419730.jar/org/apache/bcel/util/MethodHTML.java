// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.util;

import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.ExceptionTable;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ConstantValue;
import org.apache.bcel.classfile.Utility;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.Method;
import java.io.PrintWriter;
import org.apache.bcel.Constants;

final class MethodHTML implements Constants
{
    private String class_name;
    private PrintWriter file;
    private ConstantHTML constant_html;
    private AttributeHTML attribute_html;
    
    MethodHTML(final String dir, final String class_name, final Method[] methods, final Field[] fields, final ConstantHTML constant_html, final AttributeHTML attribute_html) throws IOException {
        this.class_name = class_name;
        this.attribute_html = attribute_html;
        this.constant_html = constant_html;
        (this.file = new PrintWriter(new FileOutputStream(dir + class_name + "_methods.html"))).println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
        this.file.println("<TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Type</TH><TH ALIGN=LEFT>Field&nbsp;name</TH></TR>");
        for (int i = 0; i < fields.length; ++i) {
            this.writeField(fields[i]);
        }
        this.file.println("</TABLE>");
        this.file.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Return&nbsp;type</TH><TH ALIGN=LEFT>Method&nbsp;name</TH><TH ALIGN=LEFT>Arguments</TH></TR>");
        for (int j = 0; j < methods.length; ++j) {
            this.writeMethod(methods[j], j);
        }
        this.file.println("</TABLE></BODY></HTML>");
        this.file.close();
    }
    
    private void writeField(final Field field) throws IOException {
        final String type = Utility.signatureToString(field.getSignature());
        final String name = field.getName();
        String access = Utility.accessToString(field.getAccessFlags());
        access = Utility.replace(access, " ", "&nbsp;");
        this.file.print("<TR><TD><FONT COLOR=\"#FF0000\">" + access + "</FONT></TD>\n<TD>" + Class2HTML.referenceType(type) + "</TD><TD><A NAME=\"field" + name + "\">" + name + "</A></TD>");
        final Attribute[] attributes = field.getAttributes();
        for (int i = 0; i < attributes.length; ++i) {
            this.attribute_html.writeAttribute(attributes[i], name + "@" + i);
        }
        for (int j = 0; j < attributes.length; ++j) {
            if (attributes[j].getTag() == 1) {
                final String str = ((ConstantValue)attributes[j]).toString();
                this.file.print("<TD>= <A HREF=\"" + this.class_name + "_attributes.html#" + name + "@" + j + "\" TARGET=\"Attributes\">" + str + "</TD>\n");
                break;
            }
        }
        this.file.println("</TR>");
    }
    
    private final void writeMethod(final Method method, final int method_number) throws IOException {
        final String signature = method.getSignature();
        final String[] args = Utility.methodSignatureArgumentTypes(signature, false);
        final String type = Utility.methodSignatureReturnType(signature, false);
        final String name = method.getName();
        String access = Utility.accessToString(method.getAccessFlags());
        final Attribute[] attributes = method.getAttributes();
        access = Utility.replace(access, " ", "&nbsp;");
        final String html_name = Class2HTML.toHTML(name);
        this.file.print("<TR VALIGN=TOP><TD><FONT COLOR=\"#FF0000\"><A NAME=method" + method_number + ">" + access + "</A></FONT></TD>");
        this.file.print("<TD>" + Class2HTML.referenceType(type) + "</TD><TD>" + "<A HREF=" + this.class_name + "_code.html#method" + method_number + " TARGET=Code>" + html_name + "</A></TD>\n<TD>(");
        for (int i = 0; i < args.length; ++i) {
            this.file.print(Class2HTML.referenceType(args[i]));
            if (i < args.length - 1) {
                this.file.print(", ");
            }
        }
        this.file.print(")</TD></TR>");
        for (int j = 0; j < attributes.length; ++j) {
            this.attribute_html.writeAttribute(attributes[j], "method" + method_number + "@" + j, method_number);
            final byte tag = attributes[j].getTag();
            if (tag == 3) {
                this.file.print("<TR VALIGN=TOP><TD COLSPAN=2></TD><TH ALIGN=LEFT>throws</TH><TD>");
                final int[] exceptions = ((ExceptionTable)attributes[j]).getExceptionIndexTable();
                for (int k = 0; k < exceptions.length; ++k) {
                    this.file.print(this.constant_html.referenceConstant(exceptions[k]));
                    if (k < exceptions.length - 1) {
                        this.file.print(", ");
                    }
                }
                this.file.println("</TD></TR>");
            }
            else if (tag == 2) {
                final Attribute[] c_a = ((Code)attributes[j]).getAttributes();
                for (int k = 0; k < c_a.length; ++k) {
                    this.attribute_html.writeAttribute(c_a[k], "method" + method_number + "@" + j + "@" + k, method_number);
                }
            }
        }
    }
}
