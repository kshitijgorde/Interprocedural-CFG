// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import org.xidget.ifeature.IWidgetContainerFeature;
import org.xmodel.xaction.XActionException;
import org.xmodel.xml.XmlIO;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Iterator;
import org.xidget.IXidget;
import java.util.Collections;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xidget.ifeature.ILayoutFeature;
import java.util.ArrayList;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.XPath;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class LayoutAttachAction extends GuardedAction
{
    private static final IExpression thisExpr;
    private String xidgetID;
    private IExpression xidgetExpr;
    private List<Attachment> attachments;
    
    static {
        thisExpr = XPath.createExpression(".");
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.xidgetID = Xlate.get(root, "xid", (String)null);
        this.xidgetExpr = xActionDocument.getExpression("xidget", true);
        if (this.xidgetID == null && this.xidgetExpr == null) {
            this.xidgetExpr = xActionDocument.getExpression("xidgets", true);
        }
        this.attachments = new ArrayList<Attachment>();
        ILayoutFeature.Side[] values;
        for (int length = (values = ILayoutFeature.Side.values()).length, i = 0; i < length; ++i) {
            final ILayoutFeature.Side side1 = values[i];
            final IModelObject firstChild = root.getFirstChild(side1.name());
            if (firstChild != null) {
                final String value = Xlate.get(firstChild, "anchor", (String)null);
                if (value != null) {
                    System.err.println("Warning: deprecated use of 'anchor' attribute. Use 'side' instead.");
                    firstChild.setAttribute("side", value);
                    firstChild.removeAttribute("anchor");
                }
                final Attachment attachment = new Attachment();
                attachment.side1 = side1;
                attachment.side2 = ILayoutFeature.Side.valueOf(Xlate.get(firstChild, "side", firstChild.getType()));
                attachment.xidgetExpr = Xlate.get(firstChild, "attach", LayoutAttachAction.thisExpr);
                attachment.offsetExpr = Xlate.get(firstChild, "offset", (IExpression)null);
                attachment.percentExpr = Xlate.get(firstChild, "percent", (IExpression)null);
                attachment.handleExpr = Xlate.get(firstChild, "handle", (IExpression)null);
                this.attachments.add(attachment);
            }
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Creator instance = Creator.getInstance();
        final IModelObject object = context.getObject();
        final IXidget xidget = instance.findXidget(object);
        List<IModelObject> list = null;
        if (this.xidgetID != null) {
            for (final IModelObject modelObject : object.getChildren()) {
                if (modelObject.getID().equals(this.xidgetID)) {
                    list = Collections.singletonList(modelObject);
                    break;
                }
            }
        }
        else if (this.xidgetExpr != null) {
            list = this.xidgetExpr.query(context, null);
        }
        if (list == null) {
            return null;
        }
        final Iterator<IModelObject> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            final IXidget xidget2 = instance.findXidget(iterator2.next());
            if (xidget2 == null) {
                return null;
            }
            final Iterator<Attachment> iterator3 = this.attachments.iterator();
            while (iterator3.hasNext()) {
                this.createNodes(instance, iterator3.next(), context, xidget, xidget2);
            }
        }
        return null;
    }
    
    private void createNodes(final Creator creator, final Attachment attachment, final IContext context, final IXidget xidget, final IXidget xidget2) {
        final StatefulContext statefulContext = new StatefulContext(context, xidget2.getConfig());
        IXidget xidget3 = xidget;
        if (attachment.xidgetExpr != null) {
            final List<IXidget> children = xidget.getChildren();
            final int index = children.indexOf(xidget2);
            final IXidget xidget4 = (index == 0) ? xidget : children.get(index - 1);
            final IXidget xidget5 = (index == children.size() - 1) ? xidget : children.get(index + 1);
            attachment.xidgetExpr.setVariable("previous", xidget4.getConfig());
            attachment.xidgetExpr.setVariable("next", xidget5.getConfig());
            final IModelObject queryFirst = attachment.xidgetExpr.queryFirst(context);
            if (queryFirst == null) {
                return;
            }
            xidget3 = creator.findXidget(queryFirst);
        }
        if (xidget2 == xidget3) {
            throw new XActionException("Cannot make attachment to self: " + XmlIO.toString(this.getDocument().getRoot()));
        }
        final ILayoutFeature layoutFeature = xidget.getFeature(ILayoutFeature.class);
        final IWidgetContainerFeature widgetContainerFeature = xidget.getFeature(IWidgetContainerFeature.class);
        if (attachment.percentExpr != null) {
            if (xidget2 == xidget) {
                throw new XActionException("Containers cannot have proportional attachments: " + XmlIO.toString(this.getDocument().getRoot()));
            }
            if (xidget3 != xidget) {
                throw new XActionException("Proportional attachments must be specified relative to the container: " + XmlIO.toString(this.getDocument().getRoot()));
            }
            layoutFeature.attachContainer(xidget2, attachment.side1, (float)attachment.percentExpr.evaluateNumber(statefulContext, 0.0), (attachment.percentExpr.getType(statefulContext) == IExpression.ResultType.NODES) ? attachment.percentExpr.queryFirst(statefulContext) : null, (attachment.offsetExpr != null) ? ((int)attachment.offsetExpr.evaluateNumber(statefulContext, 0.0)) : 0, attachment.handleExpr != null && attachment.handleExpr.evaluateBoolean(statefulContext));
        }
        else if (xidget3 == xidget) {
            int n = 0;
            if (attachment.offsetExpr != null) {
                n = (int)attachment.offsetExpr.evaluateNumber(statefulContext, 0.0);
            }
            layoutFeature.attachContainer(xidget2, attachment.side1, n);
        }
        else {
            int spacing = widgetContainerFeature.getSpacing();
            if (attachment.side1 == ILayoutFeature.Side.right && attachment.side2 == ILayoutFeature.Side.left) {
                spacing = -spacing;
            }
            if (attachment.side1 == ILayoutFeature.Side.bottom && attachment.side2 == ILayoutFeature.Side.top) {
                spacing = -spacing;
            }
            if (attachment.offsetExpr != null) {
                spacing = (int)attachment.offsetExpr.evaluateNumber(statefulContext, 0.0);
            }
            layoutFeature.attachPeer(xidget2, attachment.side1, xidget3, attachment.side2, spacing);
        }
    }
    
    final class Attachment
    {
        ILayoutFeature.Side side1;
        ILayoutFeature.Side side2;
        IExpression xidgetExpr;
        IExpression offsetExpr;
        IExpression percentExpr;
        IExpression handleExpr;
    }
}
