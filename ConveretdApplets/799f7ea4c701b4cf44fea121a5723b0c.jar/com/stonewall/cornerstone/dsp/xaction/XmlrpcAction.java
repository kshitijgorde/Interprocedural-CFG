// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import org.xmodel.xpath.expression.IExpression;
import java.util.Iterator;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.dsp.command.XmlrpcCommand;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.XPath;
import org.xmodel.IPath;
import org.xmodel.IModelObject;
import java.util.List;

public class XmlrpcAction extends CommandAction
{
    private List<IModelObject> params;
    private String expect;
    private String target;
    private String method;
    private IPath expectPath;
    
    public XmlrpcAction() {
        this.expectPath = XPath.createPath("ancestor::*/expect");
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        final IModelObject root = document.getRoot();
        this.target = (String)root.getChildValue("target");
        this.method = (String)root.getChildValue("method");
        this.params = root.getChildren("param");
        this.expect = document.getString(this.expectPath);
    }
    
    public DeviceCommand createCommand(final IContext context) {
        final XmlrpcCommand command = new XmlrpcCommand();
        command.addExpect(this.expect);
        command.setTarget(this.target);
        command.setMethod(this.method);
        for (final IModelObject param : this.params) {
            final String className = Xlate.get(param, "class", (String)null);
            final String string = Xlate.get(param, "").trim();
            final IExpression expression = XPath.createExpression(string);
            final ClassLoader loader = this.getClass().getClassLoader();
            try {
                final Class clazz = loader.loadClass(className);
                if (String.class.isAssignableFrom(clazz)) {
                    command.addParameter(expression.evaluateString(context));
                }
                if (Number.class.isAssignableFrom(clazz)) {
                    command.addParameter(expression.evaluateNumber(context));
                }
                if (!IModelObject.class.isAssignableFrom(clazz)) {
                    continue;
                }
                final ModelBuilder builder = new ModelBuilder();
                final IModelObject o = expression.queryFirst(context);
                command.addParameter(builder.writeModel(o, IXmlIO.Style.printable));
            }
            catch (ClassNotFoundException e) {
                XmlrpcAction.log.error("Unable to set parameter on command: " + param, e);
            }
        }
        return command;
    }
}
