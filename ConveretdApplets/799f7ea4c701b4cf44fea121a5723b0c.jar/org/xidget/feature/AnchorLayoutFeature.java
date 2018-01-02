// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature;

import org.xidget.layout.WidgetHandle;
import org.xidget.ifeature.IWidgetContainerFeature;
import java.util.Stack;
import java.util.HashSet;
import org.xidget.layout.MaxNode;
import org.xidget.layout.AnchorNode;
import org.xidget.layout.OffsetNode;
import org.xidget.layout.InternalBrace;
import org.xmodel.IModelObject;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import java.util.Collection;
import org.xmodel.xpath.expression.IContext;
import org.xidget.Log;
import org.xidget.ifeature.ILabelFeature;
import java.util.ArrayList;
import org.xidget.layout.Margins;
import org.xidget.layout.ConstantNode;
import java.util.Iterator;
import org.xidget.layout.Bounds;
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.HashMap;
import org.xidget.layout.IComputeNode;
import java.util.List;
import java.util.Map;
import org.xmodel.xaction.ScriptAction;
import org.xidget.IXidget;
import org.xidget.ifeature.ILayoutFeature;

public class AnchorLayoutFeature implements ILayoutFeature
{
    private IXidget xidget;
    private ScriptAction script;
    private Map<IXidget, NodeGroup> groups;
    private List<IComputeNode> sorted;
    private float width;
    private float height;
    
    public AnchorLayoutFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.groups = new HashMap<IXidget, NodeGroup>();
        this.invalidate();
    }
    
    public AnchorLayoutFeature(final IXidget xidget, final ScriptAction script) {
        this(xidget);
        this.script = script;
    }
    
    @Override
    public void invalidate() {
        this.sorted = null;
        this.groups.clear();
        this.width = 0.0f;
        this.height = 0.0f;
    }
    
    @Override
    public void layout(final StatefulContext statefulContext) {
        final Bounds defaultBounds = this.xidget.getFeature(IWidgetFeature.class).getDefaultBounds();
        if (this.width == defaultBounds.width && this.height == defaultBounds.height) {
            return;
        }
        this.width = defaultBounds.width;
        this.height = defaultBounds.height;
        this.layoutChildren(statefulContext);
        this.initContainerNodes();
        this.initLabels();
        if (this.sorted == null) {
            this.compile(statefulContext);
        }
        if (this.sorted != null) {
            final Iterator<IComputeNode> iterator = this.sorted.iterator();
            while (iterator.hasNext()) {
                iterator.next().reset();
            }
            final Iterator<IComputeNode> iterator2 = this.sorted.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().update();
            }
            final Iterator<IComputeNode> iterator3 = this.sorted.iterator();
            while (iterator3.hasNext()) {
                iterator3.next().update();
            }
        }
        this.updateChildrenBounds();
        this.updateContainerSize();
    }
    
    private void layoutChildren(final StatefulContext statefulContext) {
        final Iterator<IXidget> iterator = this.xidget.getChildren().iterator();
        while (iterator.hasNext()) {
            final ILayoutFeature layoutFeature = iterator.next().getFeature(ILayoutFeature.class);
            if (layoutFeature != null) {
                layoutFeature.layout(statefulContext);
            }
        }
    }
    
    private void initContainerNodes() {
        final IWidgetFeature widgetFeature = this.xidget.getFeature(IWidgetFeature.class);
        final Margins insideMargins = widgetFeature.getInsideMargins();
        final IComputeNode createNode = this.getCreateNode(this.xidget, Side.top);
        createNode.clearDependencies();
        createNode.addDependency(new ConstantNode(insideMargins.y0));
        final IComputeNode createNode2 = this.getCreateNode(this.xidget, Side.left);
        createNode2.clearDependencies();
        createNode2.addDependency(new ConstantNode(insideMargins.x0));
        final Bounds defaultBounds = widgetFeature.getDefaultBounds();
        if (defaultBounds.width >= 0.0f) {
            final IComputeNode createNode3 = this.getCreateNode(this.xidget, Side.right);
            createNode3.clearDependencies();
            createNode3.addDependency(new ConstantNode(defaultBounds.width - insideMargins.x1));
        }
        if (defaultBounds.height >= 0.0f) {
            final IComputeNode createNode4 = this.getCreateNode(this.xidget, Side.bottom);
            createNode4.clearDependencies();
            createNode4.addDependency(new ConstantNode(defaultBounds.height - insideMargins.y1));
        }
    }
    
    private void initLabels() {
        final ArrayList<ILabelFeature> list = new ArrayList<ILabelFeature>();
        int labelWidth = 0;
        final Iterator<IXidget> iterator = this.xidget.getChildren().iterator();
        while (iterator.hasNext()) {
            final ILabelFeature labelFeature = iterator.next().getFeature(ILabelFeature.class);
            int labelWidth2 = 0;
            if (labelFeature != null) {
                labelWidth2 = labelFeature.getLabelWidth();
            }
            if (labelWidth2 == 0) {
                if (labelWidth > 0) {
                    break;
                }
                continue;
            }
            else {
                if (labelWidth2 > labelWidth) {
                    labelWidth = labelWidth2;
                }
                list.add(labelFeature);
            }
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().setLabelWidth(labelWidth);
        }
    }
    
    private void updateChildrenBounds() {
        for (final Map.Entry<IXidget, NodeGroup> entry : this.groups.entrySet()) {
            final IXidget xidget = entry.getKey();
            final NodeGroup nodeGroup = entry.getValue();
            if (xidget == this.xidget) {
                continue;
            }
            final IWidgetFeature widgetFeature = xidget.getFeature(IWidgetFeature.class);
            final Bounds defaultBounds = widgetFeature.getDefaultBounds();
            if (nodeGroup.top != null && nodeGroup.top.hasValue()) {
                defaultBounds.y = nodeGroup.top.getValue();
            }
            if (nodeGroup.left != null && nodeGroup.left.hasValue()) {
                defaultBounds.x = nodeGroup.left.getValue();
            }
            if (nodeGroup.right != null && nodeGroup.right.hasValue()) {
                if (nodeGroup.left == null || !nodeGroup.left.hasValue()) {
                    Log.printf("layout", "Width of child not constrained: %s\n", xidget);
                }
                defaultBounds.width = nodeGroup.right.getValue() - nodeGroup.left.getValue();
            }
            if (nodeGroup.bottom != null && nodeGroup.bottom.hasValue()) {
                if (nodeGroup.top == null || !nodeGroup.top.hasValue()) {
                    Log.printf("layout", "Height of child not constrained: %s\n", xidget);
                }
                defaultBounds.height = nodeGroup.bottom.getValue() - nodeGroup.top.getValue();
            }
            widgetFeature.setComputedBounds(defaultBounds.x, defaultBounds.y, defaultBounds.width, defaultBounds.height);
        }
    }
    
    private void updateContainerSize() {
        final IWidgetFeature widgetFeature = this.xidget.getFeature(IWidgetFeature.class);
        final Bounds defaultBounds = widgetFeature.getDefaultBounds();
        final IComputeNode createNode = this.getCreateNode(this.xidget, Side.right);
        final IComputeNode createNode2 = this.getCreateNode(this.xidget, Side.bottom);
        final Margins insideMargins = widgetFeature.getInsideMargins();
        if (createNode.hasValue()) {
            defaultBounds.width = createNode.getValue() + insideMargins.x1;
        }
        if (createNode2.hasValue()) {
            defaultBounds.height = createNode2.getValue() + insideMargins.y1;
        }
        widgetFeature.setComputedBounds(defaultBounds.x, defaultBounds.y, defaultBounds.width, defaultBounds.height);
    }
    
    public List<IComputeNode> getAllNodes() {
        return this.sorted;
    }
    
    private void compile(final StatefulContext statefulContext) {
        Log.printf("layout", "Compile: %s\n", this.xidget);
        if (this.script == null) {
            this.loadScript(statefulContext);
        }
        if (this.script != null) {
            this.script.run(new StatefulContext(statefulContext, this.xidget.getConfig()));
        }
        else {
            this.createDefaultLayout();
        }
        final Iterator<IXidget> iterator = this.xidget.getChildren().iterator();
        while (iterator.hasNext()) {
            this.addInternalBraces(iterator.next());
        }
        final ArrayList<IComputeNode> list = new ArrayList<IComputeNode>();
        for (final NodeGroup nodeGroup : this.groups.values()) {
            if (nodeGroup.top != null) {
                list.add(nodeGroup.top);
            }
            if (nodeGroup.left != null) {
                list.add(nodeGroup.left);
            }
            if (nodeGroup.right != null) {
                list.add(nodeGroup.right);
            }
            if (nodeGroup.bottom != null) {
                list.add(nodeGroup.bottom);
            }
        }
        this.sorted = sort(list);
        for (int i = 0; i < this.sorted.size(); ++i) {
            Log.printf("layout", "%d: %s\n", i, this.sorted.get(i));
        }
    }
    
    private void loadScript(final StatefulContext statefulContext) {
        final IModelObject config = this.xidget.getConfig();
        if (config.getAttribute("layout") != null) {
            final IModelObject queryFirst = Xlate.get(config, "layout", (IExpression)null).queryFirst(statefulContext);
            if (queryFirst != null) {
                final XActionDocument xActionDocument = new XActionDocument(queryFirst);
                xActionDocument.addPackage("org.xidget.layout.xaction");
                xActionDocument.setClassLoader(this.getClass().getClassLoader());
                this.script = xActionDocument.createScript(new String[0]);
            }
        }
        else {
            final IModelObject firstChild = config.getFirstChild("layout");
            if (firstChild != null) {
                final XActionDocument xActionDocument2 = new XActionDocument(firstChild);
                xActionDocument2.addPackage("org.xidget.layout.xaction");
                xActionDocument2.setClassLoader(this.getClass().getClassLoader());
                this.script = xActionDocument2.createScript(new String[0]);
            }
        }
    }
    
    private void addInternalBraces(final IXidget xidget) {
        final NodeGroup nodeGroup = this.getNodeGroup(xidget);
        if (this.isNodeFree(nodeGroup.top)) {
            nodeGroup.top.addDependency(new InternalBrace("TBBrace", xidget, nodeGroup.bottom, Side.top));
        }
        else if (this.isNodeFree(nodeGroup.bottom)) {
            nodeGroup.bottom.addDependency(new InternalBrace("BTBrace", xidget, nodeGroup.top, Side.bottom));
        }
        if (this.isNodeFree(nodeGroup.left)) {
            nodeGroup.left.addDependency(new InternalBrace("LRBrace", xidget, nodeGroup.right, Side.left));
        }
        else if (this.isNodeFree(nodeGroup.right)) {
            nodeGroup.right.addDependency(new InternalBrace("RLBrace", xidget, nodeGroup.left, Side.right));
        }
    }
    
    @Override
    public void attachContainer(final IXidget xidget, final Side side, final int n) {
        final IComputeNode createNode = this.getCreateNode(xidget, side);
        final IComputeNode createNode2 = this.getCreateNode(this.xidget, side);
        if (n != 0) {
            createNode.addDependency(new OffsetNode(createNode2, n));
        }
        else {
            createNode.addDependency(createNode2);
        }
    }
    
    @Override
    public void attachContainer(final IXidget xidget, final Side side, final float n, final IModelObject modelObject, final int n2, final boolean handle) {
        final IComputeNode createNode = this.getCreateNode(xidget, side);
        IComputeNode computeNode;
        IComputeNode computeNode2;
        if (side == Side.top || side == Side.bottom) {
            computeNode = this.getCreateNode(this.xidget, Side.top);
            computeNode2 = this.getCreateNode(this.xidget, Side.bottom);
        }
        else {
            computeNode = this.getCreateNode(this.xidget, Side.left);
            computeNode2 = this.getCreateNode(this.xidget, Side.right);
        }
        final AnchorNode anchorNode = new AnchorNode(computeNode, computeNode2, side, n, modelObject, n2);
        anchorNode.setHandle(handle);
        createNode.addDependency(anchorNode);
    }
    
    @Override
    public void packContainer(final List<IXidget> list, final Side side, final int n) {
        if (side == Side.left || side == Side.top) {
            return;
        }
        final IComputeNode createNode = this.getCreateNode(this.xidget, side);
        if (list.size() > 1) {
            final MaxNode maxNode = new MaxNode();
            final Iterator<IXidget> iterator = list.iterator();
            while (iterator.hasNext()) {
                maxNode.addDependency(this.getCreateNode(iterator.next(), side));
            }
            if (n != 0) {
                createNode.addDependency(new OffsetNode(maxNode, n));
            }
            else {
                createNode.addDependency(maxNode);
            }
        }
        else {
            final IComputeNode createNode2 = this.getCreateNode(list.get(0), side);
            if (n != 0) {
                createNode.addDependency(new OffsetNode(createNode2, n));
            }
            else {
                createNode.addDependency(createNode2);
            }
        }
    }
    
    @Override
    public void attachPeer(final IXidget xidget, final Side side, final IXidget xidget2, final int n) {
        final IComputeNode createNode = this.getCreateNode(xidget, side);
        final IComputeNode createNode2 = this.getCreateNode(xidget2, this.getOpposite(side));
        if (n != 0) {
            createNode.addDependency(new OffsetNode(createNode2, n));
        }
        else {
            createNode.addDependency(createNode2);
        }
    }
    
    @Override
    public void attachPeer(final IXidget xidget, final Side side, final IXidget xidget2, final Side side2, final int n) {
        final IComputeNode createNode = this.getCreateNode(xidget, side);
        final IComputeNode createNode2 = this.getCreateNode(xidget2, side2);
        if (n != 0 && side != side2) {
            createNode.addDependency(new OffsetNode(createNode2, n));
        }
        else {
            createNode.addDependency(createNode2);
        }
    }
    
    @Override
    public void attachNext(final IXidget xidget, final Side side, final Side side2, final int n) {
        final List<IXidget> children = this.xidget.getChildren();
        final int n2 = children.indexOf(xidget) + 1;
        if (n2 < children.size()) {
            this.attachPeer(xidget, side, children.get(n2), side2, n);
        }
        else {
            this.attachContainer(xidget, side, n);
        }
    }
    
    @Override
    public void attachPrevious(final IXidget xidget, final Side side, final Side side2, final int n) {
        final List<IXidget> children = this.xidget.getChildren();
        final int n2 = children.indexOf(xidget) - 1;
        if (n2 >= 0) {
            this.attachPeer(xidget, side, children.get(n2), side2, n);
        }
        else {
            this.attachContainer(xidget, side, n);
        }
    }
    
    private boolean isNodeFree(final IComputeNode computeNode) {
        return computeNode.getDependencies().size() == 0;
    }
    
    private Side getOpposite(final Side side) {
        switch (side) {
            case top: {
                return Side.bottom;
            }
            case left: {
                return Side.right;
            }
            case right: {
                return Side.left;
            }
            case bottom: {
                return Side.top;
            }
            default: {
                return null;
            }
        }
    }
    
    private NodeGroup getNodeGroup(final IXidget xidget) {
        NodeGroup nodeGroup = this.groups.get(xidget);
        if (nodeGroup == null) {
            nodeGroup = new NodeGroup(xidget);
            this.groups.put(xidget, nodeGroup);
        }
        return nodeGroup;
    }
    
    private IComputeNode getCreateNode(final IXidget xidget, final Side side) {
        final NodeGroup nodeGroup = this.getNodeGroup(xidget);
        switch (side) {
            case top: {
                return nodeGroup.top;
            }
            case left: {
                return nodeGroup.left;
            }
            case right: {
                return nodeGroup.right;
            }
            case bottom: {
                return nodeGroup.bottom;
            }
            default: {
                return null;
            }
        }
    }
    
    private static List<IComputeNode> sort(final Collection<IComputeNode> collection) {
        final ArrayList<IComputeNode> list = new ArrayList<IComputeNode>();
        final HashSet<IComputeNode> set = new HashSet<IComputeNode>();
        for (final IComputeNode computeNode : collection) {
            if (set.contains(computeNode)) {
                continue;
            }
            final Stack<IComputeNode> stack = new Stack<IComputeNode>();
            stack.push(computeNode);
            while (!stack.empty()) {
                final IComputeNode computeNode2 = stack.peek();
                boolean b = false;
                for (final IComputeNode computeNode3 : computeNode2.getDependencies()) {
                    if (!set.contains(computeNode3)) {
                        b = true;
                        stack.push(computeNode3);
                        set.add(computeNode3);
                    }
                }
                if (!b) {
                    list.add(computeNode2);
                    stack.pop();
                }
            }
        }
        return list;
    }
    
    private void createDefaultLayout() {
        final Bounds defaultBounds = this.xidget.getFeature(IWidgetFeature.class).getDefaultBounds();
        final List<IXidget> children = this.xidget.getChildren();
        if (children.size() == 0) {
            return;
        }
        if (defaultBounds.width < 0.0f) {
            defaultBounds.width = 300.0f;
        }
        if (defaultBounds.height < 0.0f && !this.defaultHeightDefined(children)) {
            defaultBounds.height = 300.0f;
        }
        final int spacing = this.xidget.getFeature(IWidgetContainerFeature.class).getSpacing();
        final int n = spacing / 2;
        float n3;
        final float n2 = n3 = 1.0f / children.size();
        final int n4 = children.size() - 1;
        if (defaultBounds.height >= 0.0f) {
            for (int i = 0; i < n4; ++i, n3 += n2) {
                this.attachContainer(children.get(i), Side.bottom, n3, null, -n, true);
            }
            this.attachContainer(children.get(0), Side.top, 0);
            this.attachContainer(children.get(n4), Side.bottom, 0);
        }
        else {
            this.attachContainer(children.get(0), Side.top, 0);
            this.getNodeGroup(this.xidget).bottom.addDependency(new OffsetNode(this.getNodeGroup(children.get(n4)).bottom, 0));
        }
        for (final IXidget xidget : children) {
            this.attachContainer(xidget, Side.left, 0);
            this.attachContainer(xidget, Side.right, 0);
        }
        for (int j = 1; j <= n4; ++j) {
            this.attachPeer(children.get(j), Side.top, children.get(j - 1), Side.bottom, spacing);
        }
    }
    
    private boolean defaultHeightDefined(final List<IXidget> list) {
        final Iterator<IXidget> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getFeature(IWidgetFeature.class).getDefaultBounds().height < 0.0f) {
                return false;
            }
        }
        return true;
    }
    
    private class NodeGroup
    {
        IComputeNode top;
        IComputeNode left;
        IComputeNode right;
        IComputeNode bottom;
        
        public NodeGroup(final IXidget xidget) {
            this.top = new WidgetHandle(xidget, Side.top, 0);
            this.left = new WidgetHandle(xidget, Side.left, 0);
            this.right = new WidgetHandle(xidget, Side.right, 0);
            this.bottom = new WidgetHandle(xidget, Side.bottom, 0);
        }
    }
}
