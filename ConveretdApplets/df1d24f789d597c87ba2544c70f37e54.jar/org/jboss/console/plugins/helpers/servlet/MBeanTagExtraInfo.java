// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.servlet;

import javax.servlet.jsp.tagext.VariableInfo;
import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;

public class MBeanTagExtraInfo extends TagExtraInfo
{
    public VariableInfo[] getVariableInfo(final TagData data) {
        return new VariableInfo[] { new VariableInfo(data.getAttributeString("id"), data.getAttributeString("intf"), true, 2) };
    }
}
