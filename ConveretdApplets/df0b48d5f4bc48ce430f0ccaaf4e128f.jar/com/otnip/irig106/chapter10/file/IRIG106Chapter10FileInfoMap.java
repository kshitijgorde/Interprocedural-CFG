// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.file;

import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.io.File;

class IRIG106Chapter10FileInfoMap
{
    private static final File infoFile;
    private static HashMap<File, IRIG106Chapter10FileInfo> infoMap;
    
    static IRIG106Chapter10FileInfo getInfo(final File file) {
        IRIG106Chapter10FileInfo info = IRIG106Chapter10FileInfoMap.infoMap.get(file);
        if (info != null && info.lastModified != file.lastModified()) {
            info = null;
            removeInfo(file);
        }
        return info;
    }
    
    static void setInfo(final File file, final IRIG106Chapter10FileInfo info) {
        IRIG106Chapter10FileInfoMap.infoMap.put(file, info);
        save();
    }
    
    private static void removeInfo(final File file) {
        IRIG106Chapter10FileInfoMap.infoMap.remove(file);
        save();
    }
    
    private static void save() {
        try {
            final FileOutputStream fos = new FileOutputStream(IRIG106Chapter10FileInfoMap.infoFile);
            final BufferedOutputStream bos = new BufferedOutputStream(fos);
            final ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(IRIG106Chapter10FileInfoMap.infoMap);
            oos.close();
        }
        catch (Exception ex) {
            System.err.println("Error Saving Info Map:  " + ex.getMessage());
        }
    }
    
    static {
        File temp = new File(System.getProperty("user.home"));
        temp = new File(temp, ".oTnip");
        temp = new File(temp, "irig106chapter10");
        temp.mkdirs();
        infoFile = new File(temp, "InfoMap.serializable");
        try {
            final FileInputStream fis = new FileInputStream(IRIG106Chapter10FileInfoMap.infoFile);
            final BufferedInputStream bis = new BufferedInputStream(fis);
            final ObjectInputStream ois = new ObjectInputStream(bis);
            IRIG106Chapter10FileInfoMap.infoMap = (HashMap<File, IRIG106Chapter10FileInfo>)ois.readObject();
            fis.close();
        }
        catch (Exception ex) {
            System.err.println("Could Not Load InfoMap - Creating New Map");
            IRIG106Chapter10FileInfoMap.infoMap = new HashMap<File, IRIG106Chapter10FileInfo>();
        }
    }
}
