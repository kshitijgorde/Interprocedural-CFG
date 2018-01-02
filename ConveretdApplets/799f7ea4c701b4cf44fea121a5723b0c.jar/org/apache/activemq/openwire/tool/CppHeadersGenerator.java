// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.codehaus.jam.JClass;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jam.JProperty;
import java.io.PrintWriter;

public class CppHeadersGenerator extends CppClassesGenerator
{
    @Override
    protected String getFilePostFix() {
        return ".hpp";
    }
    
    @Override
    protected void generateFile(final PrintWriter out) {
        this.generateLicence(out);
        out.println("#ifndef ActiveMQ_" + this.className + "_hpp_");
        out.println("#define ActiveMQ_" + this.className + "_hpp_");
        out.println("");
        out.println("// Turn off warning message for ignored exception specification");
        out.println("#ifdef _MSC_VER");
        out.println("#pragma warning( disable : 4290 )");
        out.println("#endif");
        out.println("");
        out.println("#include <string>");
        out.println("#include \"activemq/command/" + this.baseClass + ".hpp\"");
        final List properties = this.getProperties();
        for (final JProperty property : properties) {
            if (!property.getType().isPrimitiveType() && !property.getType().getSimpleName().equals("String") && !property.getType().getSimpleName().equals("ByteSequence")) {
                String includeName = this.toCppType(property.getType());
                if (property.getType().isArrayType()) {
                    final JClass arrayType = property.getType().getArrayComponentType();
                    if (arrayType.isPrimitiveType()) {
                        continue;
                    }
                }
                if (includeName.startsWith("array<")) {
                    includeName = includeName.substring(6, includeName.length() - 1);
                }
                else if (includeName.startsWith("p<")) {
                    includeName = includeName.substring(2, includeName.length() - 1);
                }
                if (includeName.equals("IDataStructure")) {
                    out.println("#include \"activemq/" + includeName + ".hpp\"");
                }
                else {
                    out.println("#include \"activemq/command/" + includeName + ".hpp\"");
                }
            }
        }
        out.println("");
        out.println("#include \"activemq/protocol/IMarshaller.hpp\"");
        out.println("#include \"ppr/io/IOutputStream.hpp\"");
        out.println("#include \"ppr/io/IInputStream.hpp\"");
        out.println("#include \"ppr/io/IOException.hpp\"");
        out.println("#include \"ppr/util/ifr/array\"");
        out.println("#include \"ppr/util/ifr/p\"");
        out.println("");
        out.println("namespace apache");
        out.println("{");
        out.println("  namespace activemq");
        out.println("  {");
        out.println("    namespace command");
        out.println("    {");
        out.println("      using namespace ifr;");
        out.println("      using namespace std;");
        out.println("      using namespace apache::activemq;");
        out.println("      using namespace apache::activemq::protocol;");
        out.println("      using namespace apache::ppr::io;");
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
        out.println("class " + this.className + " : public " + this.baseClass + "");
        out.println("{");
        out.println("protected:");
        for (final JProperty property : properties) {
            final String type = this.toCppType(property.getType());
            final String name = this.decapitalize(property.getSimpleName());
            out.println("    " + type + " " + name + " ;");
        }
        out.println("");
        out.println("public:");
        out.println("    const static unsigned char TYPE = " + this.getOpenWireOpCode(this.jclass) + ";");
        out.println("");
        out.println("public:");
        out.println("    " + this.className + "() ;");
        out.println("    virtual ~" + this.className + "() ;");
        out.println("");
        out.println("    virtual unsigned char getDataStructureType() ;");
        for (final JProperty property : properties) {
            final String type = this.toCppType(property.getType());
            final String propertyName = property.getSimpleName();
            final String parameterName = this.decapitalize(propertyName);
            out.println("");
            out.println("    virtual " + type + " get" + propertyName + "() ;");
            out.println("    virtual void set" + propertyName + "(" + type + " " + parameterName + ") ;");
        }
        out.println("");
        out.println("    virtual int marshal(p<IMarshaller> marshaller, int mode, p<IOutputStream> ostream) throw (IOException) ;");
        out.println("    virtual void unmarshal(p<IMarshaller> marshaller, int mode, p<IInputStream> istream) throw (IOException) ;");
        out.println("} ;");
        out.println("");
        out.println("/* namespace */");
        out.println("    }");
        out.println("  }");
        out.println("}");
        out.println("");
        out.println("#endif /*ActiveMQ_" + this.className + "_hpp_*/");
    }
}
