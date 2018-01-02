// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.util;

import org.apache.bcel.classfile.ConstantString;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantFieldref;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.ConstantMethodref;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Constant;
import java.io.PrintWriter;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.Constants;

final class ConstantHTML implements Constants
{
    private String class_name;
    private String class_package;
    private ConstantPool constant_pool;
    private PrintWriter file;
    private String[] constant_ref;
    private Constant[] constants;
    private Method[] methods;
    
    ConstantHTML(final String dir, final String class_name, final String class_package, final Method[] methods, final ConstantPool constant_pool) throws IOException {
        this.class_name = class_name;
        this.class_package = class_package;
        this.constant_pool = constant_pool;
        this.methods = methods;
        this.constants = constant_pool.getConstantPool();
        this.file = new PrintWriter(new FileOutputStream(dir + class_name + "_cp.html"));
        (this.constant_ref = new String[this.constants.length])[0] = "&lt;unknown&gt;";
        this.file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
        for (int i = 1; i < this.constants.length; ++i) {
            if (i % 2 == 0) {
                this.file.print("<TR BGCOLOR=\"#C0C0C0\"><TD>");
            }
            else {
                this.file.print("<TR BGCOLOR=\"#A0A0A0\"><TD>");
            }
            if (this.constants[i] != null) {
                this.writeConstant(i);
            }
            this.file.print("</TD></TR>\n");
        }
        this.file.println("</TABLE></BODY></HTML>");
        this.file.close();
    }
    
    String referenceConstant(final int index) {
        return this.constant_ref[index];
    }
    
    private void writeConstant(final int index) {
        final byte tag = this.constants[index].getTag();
        this.file.println("<H4> <A NAME=cp" + index + ">" + index + "</A> " + Constants.CONSTANT_NAMES[tag] + "</H4>");
        switch (tag) {
            case 10:
            case 11: {
                int class_index;
                int name_index;
                if (tag == 10) {
                    final ConstantMethodref c = (ConstantMethodref)this.constant_pool.getConstant(index, (byte)10);
                    class_index = c.getClassIndex();
                    name_index = c.getNameAndTypeIndex();
                }
                else {
                    final ConstantInterfaceMethodref c2 = (ConstantInterfaceMethodref)this.constant_pool.getConstant(index, (byte)11);
                    class_index = c2.getClassIndex();
                    name_index = c2.getNameAndTypeIndex();
                }
                final String method_name = this.constant_pool.constantToString(name_index, (byte)12);
                final String html_method_name = Class2HTML.toHTML(method_name);
                final String method_class = this.constant_pool.constantToString(class_index, (byte)7);
                String short_method_class = Utility.compactClassName(method_class);
                short_method_class = Utility.compactClassName(method_class);
                short_method_class = Utility.compactClassName(short_method_class, this.class_package + ".", true);
                final ConstantNameAndType c3 = (ConstantNameAndType)this.constant_pool.getConstant(name_index, (byte)12);
                final String signature = this.constant_pool.constantToString(c3.getSignatureIndex(), (byte)1);
                final String[] args = Utility.methodSignatureArgumentTypes(signature, false);
                final String type = Utility.methodSignatureReturnType(signature, false);
                final String ret_type = Class2HTML.referenceType(type);
                final StringBuffer buf = new StringBuffer("(");
                for (int i = 0; i < args.length; ++i) {
                    buf.append(Class2HTML.referenceType(args[i]));
                    if (i < args.length - 1) {
                        buf.append(",&nbsp;");
                    }
                }
                buf.append(")");
                final String arg_types = buf.toString();
                String ref;
                if (method_class.equals(this.class_name)) {
                    ref = "<A HREF=\"" + this.class_name + "_code.html#method" + this.getMethodNumber(method_name + signature) + "\" TARGET=Code>" + html_method_name + "</A>";
                }
                else {
                    ref = "<A HREF=\"" + method_class + ".html" + "\" TARGET=_top>" + short_method_class + "</A>." + html_method_name;
                }
                this.constant_ref[index] = ret_type + "&nbsp;<A HREF=\"" + this.class_name + "_cp.html#cp" + class_index + "\" TARGET=Constants>" + short_method_class + "</A>.<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + html_method_name + "</A>&nbsp;" + arg_types;
                this.file.println("<P><TT>" + ret_type + "&nbsp;" + ref + arg_types + "&nbsp;</TT>\n<UL>" + "<LI><A HREF=\"#cp" + class_index + "\">Class index(" + class_index + ")</A>\n" + "<LI><A HREF=\"#cp" + name_index + "\">NameAndType index(" + name_index + ")</A></UL>");
                break;
            }
            case 9: {
                final ConstantFieldref c4 = (ConstantFieldref)this.constant_pool.getConstant(index, (byte)9);
                final int class_index = c4.getClassIndex();
                final int name_index = c4.getNameAndTypeIndex();
                final String field_class = this.constant_pool.constantToString(class_index, (byte)7);
                String short_field_class = Utility.compactClassName(field_class);
                short_field_class = Utility.compactClassName(short_field_class, this.class_package + ".", true);
                final String field_name = this.constant_pool.constantToString(name_index, (byte)12);
                String ref;
                if (field_class.equals(this.class_name)) {
                    ref = "<A HREF=\"" + field_class + "_methods.html#field" + field_name + "\" TARGET=Methods>" + field_name + "</A>";
                }
                else {
                    ref = "<A HREF=\"" + field_class + ".html\" TARGET=_top>" + short_field_class + "</A>." + field_name + "\n";
                }
                this.constant_ref[index] = "<A HREF=\"" + this.class_name + "_cp.html#cp" + class_index + "\" TARGET=Constants>" + short_field_class + "</A>.<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + field_name + "</A>";
                this.file.println("<P><TT>" + ref + "</TT><BR>\n" + "<UL>" + "<LI><A HREF=\"#cp" + class_index + "\">Class(" + class_index + ")</A><BR>\n" + "<LI><A HREF=\"#cp" + name_index + "\">NameAndType(" + name_index + ")</A></UL>");
                break;
            }
            case 7: {
                final ConstantClass c5 = (ConstantClass)this.constant_pool.getConstant(index, (byte)7);
                final int name_index = c5.getNameIndex();
                final String class_name2 = this.constant_pool.constantToString(index, tag);
                String short_class_name = Utility.compactClassName(class_name2);
                short_class_name = Utility.compactClassName(short_class_name, this.class_package + ".", true);
                final String ref = "<A HREF=\"" + class_name2 + ".html\" TARGET=_top>" + short_class_name + "</A>";
                this.constant_ref[index] = "<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + short_class_name + "</A>";
                this.file.println("<P><TT>" + ref + "</TT><UL>" + "<LI><A HREF=\"#cp" + name_index + "\">Name index(" + name_index + ")</A></UL>\n");
                break;
            }
            case 8: {
                final ConstantString c6 = (ConstantString)this.constant_pool.getConstant(index, (byte)8);
                final int name_index = c6.getStringIndex();
                final String str = Class2HTML.toHTML(this.constant_pool.constantToString(index, tag));
                this.file.println("<P><TT>" + str + "</TT><UL>" + "<LI><A HREF=\"#cp" + name_index + "\">Name index(" + name_index + ")</A></UL>\n");
                break;
            }
            case 12: {
                final ConstantNameAndType c7 = (ConstantNameAndType)this.constant_pool.getConstant(index, (byte)12);
                final int name_index = c7.getNameIndex();
                final int signature_index = c7.getSignatureIndex();
                this.file.println("<P><TT>" + Class2HTML.toHTML(this.constant_pool.constantToString(index, tag)) + "</TT><UL>" + "<LI><A HREF=\"#cp" + name_index + "\">Name index(" + name_index + ")</A>\n" + "<LI><A HREF=\"#cp" + signature_index + "\">Signature index(" + signature_index + ")</A></UL>\n");
                break;
            }
            default: {
                this.file.println("<P><TT>" + Class2HTML.toHTML(this.constant_pool.constantToString(index, tag)) + "</TT>\n");
                break;
            }
        }
    }
    
    private final int getMethodNumber(final String str) {
        for (int i = 0; i < this.methods.length; ++i) {
            final String cmp = this.methods[i].getName() + this.methods[i].getSignature();
            if (cmp.equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
