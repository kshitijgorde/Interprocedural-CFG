// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers;

import org.jboss.console.manager.interfaces.impl.SimpleTreeNodeMenuEntryImpl;
import org.jboss.console.manager.interfaces.impl.SeparatorTreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.impl.MBeanResource;
import javax.management.ObjectName;
import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.impl.SimpleTreeNode;
import org.jboss.console.manager.interfaces.impl.SimpleResourceTreeNode;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.impl.HttpLinkTreeAction;
import org.jboss.console.manager.interfaces.TreeNode;

public class TreeNodeFactory
{
    public static final int NAME = 0;
    public static final int DESCRIPTION = 1;
    public static final int ICON_URL = 2;
    public static final int DEFAULT_URL = 3;
    public static final int MENU_ENTRIES = 4;
    public static final int SUB_NODES = 5;
    public static final int SUB_RESOURCES = 6;
    public static final int MANAGEABLE_RESOURCES = 7;
    
    public static TreeNode createTreeNode(final Object[] content) throws Exception {
        if (content.length != 7 && content.length != 8) {
            throw new Exception("Bad number of parameters");
        }
        final String name = (String)content[0];
        final String description = (String)content[1];
        final String iconUrl = (String)content[2];
        final String defaultUrl = (String)content[3];
        final TreeAction action = new HttpLinkTreeAction(defaultUrl);
        final TreeNodeMenuEntry[] menuEntries = createTreeMenus((Object[])content[4]);
        TreeNode[] subNodes = null;
        final Object[] genericSubNodes = (Object[])content[5];
        if (genericSubNodes != null && genericSubNodes.length > 0) {
            subNodes = new TreeNode[genericSubNodes.length];
            for (int i = 0; i < genericSubNodes.length; ++i) {
                subNodes[i] = createTreeNode((Object[])genericSubNodes[i]);
            }
        }
        else {
            subNodes = new TreeNode[0];
        }
        ResourceTreeNode[] subResNodes = null;
        final Object[] genericSubResNodes = (Object[])content[6];
        if (genericSubResNodes != null && genericSubResNodes.length > 0) {
            subResNodes = new ResourceTreeNode[genericSubResNodes.length];
            for (int j = 0; j < genericSubResNodes.length; ++j) {
                subResNodes[j] = (ResourceTreeNode)createTreeNode((Object[])genericSubResNodes[j]);
            }
        }
        else {
            subResNodes = new ResourceTreeNode[0];
        }
        if (content.length - 1 == 7) {
            final ManageableResource res = createManageableResource(content[7]);
            return new SimpleResourceTreeNode(name, description, iconUrl, action, menuEntries, subNodes, subResNodes, res);
        }
        return new SimpleTreeNode(name, description, iconUrl, action, menuEntries, subNodes, subResNodes);
    }
    
    public static ManageableResource createManageableResource(final Object content) throws Exception {
        final Object[] realContent = (Object[])content;
        return new MBeanResource(new ObjectName((String)realContent[0]), (String)realContent[1]);
    }
    
    protected static TreeNodeMenuEntry[] createTreeMenus(final Object[] content) throws Exception {
        TreeNodeMenuEntry[] menuEntries = null;
        if (content != null && content.length > 0) {
            menuEntries = new TreeNodeMenuEntry[content.length];
            int i = 0;
            while (i < content.length) {
                if (content[i] == null) {
                    menuEntries[i] = new SeparatorTreeNodeMenuEntry();
                    ++i;
                }
                else {
                    final String text = (String)content[i];
                    final TreeAction action = new HttpLinkTreeAction((String)content[i + 1]);
                    menuEntries[i] = new SimpleTreeNodeMenuEntryImpl(text, action);
                    i += 2;
                }
            }
        }
        else {
            menuEntries = new TreeNodeMenuEntry[0];
        }
        return menuEntries;
    }
}
