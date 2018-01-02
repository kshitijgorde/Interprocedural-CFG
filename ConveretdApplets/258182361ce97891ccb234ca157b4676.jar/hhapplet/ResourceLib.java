// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.util.Enumeration;
import java.net.MalformedURLException;
import BsscXML.BsscXML;
import java.util.Vector;
import XMLConsumer.WhResource;
import java.util.Hashtable;

public class ResourceLib
{
    public static final String RES_CANCEL = "Cancel";
    public static final String RES_CANTOPENURL = "CantOpenURLorFile";
    public static final String RES_CANTSEARCH = "CantSearch";
    public static final String RES_COMPLETINGCONTENTS = "CompletingContents";
    public static final String RES_CONTENTS = "Contents";
    public static final String RES_DEFINITION = "Definition";
    public static final String RES_DISPLAY = "Display";
    public static final String RES_DONE = "Done";
    public static final String RES_FIND = "Find";
    public static final String RES_GLOSSARY = "Glossary";
    public static final String RES_INDEX = "Index";
    public static final String RES_INDEXINPUTPROMPT = "IndexInputPrompt";
    public static final String RES_INDEXSELECTPROMPT = "RelateTopicListPrompt";
    public static final String RES_LOADINGCONTENTS = "LoadingContents";
    public static final String RES_LOADINGDATA = "LoadingData";
    public static final String RES_LOADINGFTS = "LoadingFTS";
    public static final String RES_LOADINGGLOSSARY = "LoadingGlossary";
    public static final String RES_LOADINGINDEX = "LoadingIndex";
    public static final String RES_LOADINGTOC = "LoadingTOCItem";
    public static final String RES_SEARCH = "Search";
    public static final String RES_SEARCHING = "Searching";
    public static final String RES_SEARCHINPUTPROMPT = "FtsInputPrompt";
    public static final String RES_TERM = "Term";
    public static final String RES_TOPICFOUND = "TopicsFound";
    public static final String RES_TOPICNOTFOUND = "TopicsNotFound";
    public static final String RES_SORTORDER = "langorder";
    public static final String RES_ENGLISH = "English";
    public static final String RES_MERGEERR1 = "MergeError1";
    public static final String RES_MERGEERR2 = "MergeError2";
    private static Hashtable m_resTable;
    private static WhResource m_whResource;
    
    public static boolean SetRes(final String s, final String s2) {
        if (ResourceLib.m_resTable != null) {
            ResourceLib.m_resTable.put(s, s2);
            return true;
        }
        return false;
    }
    
    public static String GetRes(final String s) {
        if (ResourceLib.m_resTable != null) {
            return ResourceLib.m_resTable.get(s);
        }
        return null;
    }
    
    public static Vector getStopWords() {
        if (ResourceLib.m_whResource != null) {
            return ResourceLib.m_whResource.getStopWords();
        }
        return null;
    }
    
    static {
        ResourceLib.m_resTable = new Hashtable();
        ResourceLib.m_whResource = null;
    }
    
    public static int getFirstShowPaneIndex() {
        if (ResourceLib.m_whResource != null) {
            return ResourceLib.m_whResource.getFirstShowPane();
        }
        return 0;
    }
    
    public static void InitRes() {
        SetRes("Cancel", "Cancel");
        SetRes("CantOpenURLorFile", "Can't open URL or file ");
        SetRes("CantSearch", "Help cannot search for that phrase!");
        SetRes("CompletingContents", "Completing Contents...");
        SetRes("Contents", "Contents");
        SetRes("Definition", "Definition:");
        SetRes("Display", "Display");
        SetRes("Done", "Done");
        SetRes("Find", "Find");
        SetRes("Glossary", "Glossary");
        SetRes("Index", "Index");
        SetRes("IndexInputPrompt", "Type in the keyword to find:");
        SetRes("RelateTopicListPrompt", "Click a topic, then click Display.");
        SetRes("LoadingData", "Loading, please wait...");
        SetRes("LoadingFTS", "Loading Search Data...");
        SetRes("LoadingGlossary", "Loading Glossary Data...");
        SetRes("LoadingIndex", "Loading Index...");
        SetRes("LoadingTOCItem", "Loading Table of Contents:");
        SetRes("Search", "Search");
        SetRes("Searching", "Searching ...");
        SetRes("FtsInputPrompt", "Type in the word(s) to search for:");
        SetRes("Term", "Term:");
        SetRes("TopicsFound", "Topics Found");
        SetRes("LoadingContents", "Loading contents, please wait...");
        SetRes("TopicsNotFound", "No Topics Found.");
        SetRes("English", "true");
        SetRes("langorder", "");
        SetRes("MergeError1", "The merged Help system");
        SetRes("MergeError2", "is using a different language from the master Help system, which will cause the index and full-text search functionality to be disabled in the merged Help system.");
    }
    
    public static boolean LoadResource(final String s) {
        try {
            if (ResourceLib.m_whResource == null) {
                (ResourceLib.m_whResource = new WhResource(URLFileHandler.makeURL(BsscXML.getDocumentBase(), s, null))).process();
            }
            final Enumeration<String> keys = ResourceLib.m_resTable.keys();
            while (keys.hasMoreElements()) {
                final String s2 = keys.nextElement();
                final String value = ResourceLib.m_whResource.getValue(s2);
                if (value != null) {
                    ResourceLib.m_resTable.put(s2, value);
                }
            }
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public static PaneSetting getPaneSetting(final String s) {
        if (ResourceLib.m_whResource != null) {
            return ResourceLib.m_whResource.getPaneSetting(s);
        }
        return null;
    }
    
    public static Vector getStems() {
        if (ResourceLib.m_whResource != null) {
            return ResourceLib.m_whResource.getStems();
        }
        return null;
    }
}
