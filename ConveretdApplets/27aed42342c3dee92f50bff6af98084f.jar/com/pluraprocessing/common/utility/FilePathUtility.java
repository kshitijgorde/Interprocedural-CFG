// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import com.pluraprocessing.common.domain.WorkVersion;
import java.io.File;
import java.util.UUID;

public class FilePathUtility
{
    public static String getDataFileDirectoryString(final UUID id, final String serverName, final int numberDisks, final String baseFileLocation, final String nodeServerDiskName) {
        return getDataFileDirectoryString(id, serverName, numberDisks, baseFileLocation, nodeServerDiskName, File.separator);
    }
    
    public static String getDataWebDirectoryString(final UUID id, final String serverName, final int numberDisks, final String baseFileLocation, final String nodeServerDiskName) {
        return getDataFileDirectoryString(id, serverName, numberDisks, baseFileLocation, nodeServerDiskName, "/");
    }
    
    public static String getDataFileDirectoryString(final UUID id, final String serverName, final int numberDisks, final String baseFileLocation, final String nodeServerDiskName, final String fileSeparator) {
        final String idStr = id.toString();
        final String[] numbers = idStr.split("-");
        final int dir1 = (int)(Long.decode("0x" + numbers[0]) % 10L);
        final int dir2 = (int)(Long.decode("0x" + numbers[1]) % 10L);
        final int dir3 = (int)(Long.decode("0x" + numbers[2]) % 10L);
        final int dir4 = (int)(Long.decode("0x" + numbers[3]) % 10L);
        final int diskNumber = dir1 % numberDisks;
        final StringBuilder location = new StringBuilder(fileSeparator).append(serverName).append(fileSeparator).append(nodeServerDiskName).append(diskNumber).append(baseFileLocation).append(fileSeparator).append(dir1).append(fileSeparator).append(dir2).append(fileSeparator).append(dir3).append(fileSeparator).append(dir4);
        return location.toString();
    }
    
    public static String getJarFileLocationString(final String executableId, final boolean isRestricted) {
        final String versionJar = WorkVersion.getVersionFileString(executableId);
        String filePath = "/plura/java/";
        if (isRestricted) {
            filePath = "/plura/restrictedjava/";
        }
        return String.valueOf(filePath) + "pluracustomer" + versionJar + ".jar";
    }
}
