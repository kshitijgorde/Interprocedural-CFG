// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.xml;

import java.util.Iterator;
import java.util.ArrayList;

public class ExecutableParser
{
    public static ArrayList<String> getExecutableIds(final String xml) {
        final ArrayList<String> idList = new ArrayList<String>();
        final ArrayList<String> executableNodes = XmlStringParser.getAllNodesWithoutChildren("e", xml);
        idList.ensureCapacity(executableNodes.size());
        for (final String executableNode : executableNodes) {
            idList.add(XmlStringParser.getAttribute("e", "id", executableNode));
        }
        return idList;
    }
}
