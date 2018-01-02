// 
// Decompiled by Procyon v0.5.30
// 

package jstella.runner;

import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Map;

public class JStellaRunnerUtil
{
    public static final String CONFIG_KEY_USERDATA_DIR = "jstella.dir.userdata";
    public static final String CONFIG_KEY_DEFAULT_ROM_DIR = "jstella.dir.roms";
    public static final String CONFIG_KEY_DEFAULT_STATE_DIR = "jstella.dir.states";
    public static final String CONFIG_KEY_REPOSITORY_DIR = "jstella.dir.repository";
    public static final String CONFIG_KEY_DEFAULT_SCREEN = "jstella.defaultscreen";
    public static final String CONFIG_KEY_REPOSITORY_MODE = "jstella.repositorymode";
    public static final String CONFIG_KEY_GAME_MD5 = "jstella.game.md5";
    public static final String CONFIG_KEY_GAME_MAKER = "jstella.game.maker";
    public static final String CONFIG_KEY_GAME_YEAR = "jstella.game.year";
    public static final String CONFIG_KEY_GAME_NOTE = "jstella.game.note";
    public static final String CONFIG_KEY_GAME_MISC = "jstella.game.misc";
    public static final String CONFIG_KEY_CARTRIDGE_TYPE = "jstella.game.cartridge.type";
    public static final String CONFIG_KEY_CARTRIDGE_MODEL = "jstella.game.cartridge.model";
    public static final String CONFIG_KEY_GAME_RARITY = "jstella.game.cartridge.rarity";
    public static final String CONFIG_KEY_CONTROLLER_LEFT = "jstella.controller.left.type";
    public static final String CONFIG_KEY_CONTROLLER_RIGHT = "jstella.controller.right.type";
    public static final String CONFIG_KEY_DISPLAY_FORMAT = "jstella.display.format";
    public static final String CONFIG_KEY_DISPLAY_FRAMERATE = "jstella.display.framerate";
    public static final String CONFIG_KEY_DISPLAY_WIDTH = "jstella.display.width";
    public static final String CONFIG_KEY_DISPLAY_XSTART = "jstella.display.xstart";
    public static final String CONFIG_KEY_DISPLAY_HEIGHT = "jstella.display.height";
    public static final String CONFIG_KEY_DISPLAY_YSTART = "jstella.display.ystart";
    public static final String CONFIG_KEY_CONSOLE_RIGHT_DIFFICULTY = "jstella.console.switch.difficulty.right";
    public static final String CONFIG_KEY_CONSOLE_LEFT_DIFFICULTY = "jstella.console.switch.difficulty.left";
    public static final String CONFIG_KEY_CONSOLE_TVMODE = "jstella.console.switch.tvmode";
    public static final String CONFIG_KEY_AUDIO_STEREOMODE = "jstella.audio.stereomode";
    public static final String CONFIG_KEY_DISPLAY_ANTIFLICKERMODE = "jstella.display.antiflickermode";
    public static final String CONFIG_KEY_CLEAR_PREVIOUS_CONTROLS = "jstella.control.clearprevious";
    public static final String CONFIG_KEY_SWITCHON_PLAYER0DIFFICULTY = "jstella.switchon.player0difficulty";
    public static final String CONFIG_KEY_SWITCHON_PLAYER1DIFFICULTY = "jstella.switchon.player1difficulty";
    public static final String CONFIG_KEY_STEREO_MODE = "jstella.stereomode";
    public static final String CONFIG_VALUE_DEFAULT_SCREEN_SNOW = "snow";
    public static final String CONFIG_VALUE_DEFAULT_SCREEN_TEST_PATTERN = "testpattern";
    public static final String CONFIG_VALUE_TRUE = "true";
    public static final String CONFIG_VALUE_FALSE = "false";
    public static final String CONFIG_FILENAME = "jstella.cfg";
    public static final String DEFAULT_JSTELLA_DIR = "jstella";
    public static final String DEFAULT_REPOSITORY_SUBDIR = "repository";
    public static final String DEFAULT_STATE_SUBDIR = "savedgames";
    public static final String DEFAULT_ROMS_SUBDIR = "roms";
    public static final String DEFAULT_STATE_NAME = "mysavedgame.jssg";
    public static final String STRING_EXIT_TO_REPOSITORY = "Exit to repository";
    public static final String STRING_EXIT_JSTELLA = "Exit JStella";
    public static final String RESOURCE_DIR = "/jstella/resources/";
    public static final String RESOURCE_WIZARD_HTML_INTRO = "/jstella/resources/JStellaWizard1.html";
    public static final String RESOURCE_WIZARD_HTML_CHOOSE_ROM_DIR = "/jstella/resources/JStellaWizard2.html";
    public static final String RESOURCE_ICON_32 = "/jstella/resources/jstellaicon32.gif";
    public static final String RESOURCE_ICON_64 = "/jstella/resources/jstellaicon64.gif";
    private static final Map<String, String> myConfiguration;
    
    public static byte[] readByteArrayFromFile(final File aFile) {
        byte[] zReturn = null;
        try {
            final BufferedInputStream zBIS = new BufferedInputStream(new FileInputStream(aFile));
            final ByteArrayOutputStream zBAOS = new ByteArrayOutputStream();
            int zInt = 0;
            while ((zInt = zBIS.read()) != -1) {
                zBAOS.write(zInt);
            }
            zBAOS.close();
            zBIS.close();
            zReturn = zBAOS.toByteArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return zReturn;
    }
    
    public static byte[] readByteArrayFromStream(final InputStream aStream) throws IOException {
        byte[] zReturn = null;
        final ByteArrayOutputStream zBAOS = new ByteArrayOutputStream();
        int zInt = 0;
        if (aStream != null) {
            while ((zInt = aStream.read()) != -1) {
                zBAOS.write(zInt);
            }
        }
        zBAOS.close();
        zReturn = zBAOS.toByteArray();
        return zReturn;
    }
    
    public static String getFilenameWithoutExtension(final String aFilename) {
        final int zExtensionIndex = aFilename.lastIndexOf(".");
        if (zExtensionIndex != -1) {
            return aFilename.substring(0, zExtensionIndex);
        }
        return aFilename;
    }
    
    public static String getFilenameWithoutExtension(final File aFile) {
        final String zName = aFile.getName();
        return getFilenameWithoutExtension(zName);
    }
    
    public static boolean isConfigAffirmative(final String aConfigKey) {
        return containsAffirmativeValue(getConfiguration(), aConfigKey);
    }
    
    public static boolean isConfigNegative(final String aConfigKey) {
        return containsNegativeValue(getConfiguration(), aConfigKey);
    }
    
    public static boolean isConfigUndecided(final String aConfigKey) {
        final String zValue = getConfiguration().get(aConfigKey);
        return zValue == null || zValue.trim().equals("");
    }
    
    public static boolean containsAffirmativeValue(final Map<String, String> aConfigMap, final String aKey) {
        return aConfigMap.containsKey(aKey) && isAffirmative(aConfigMap.get(aKey));
    }
    
    public static boolean containsNegativeValue(final Map<String, String> aConfigMap, final String aKey) {
        if (aConfigMap.get(aKey) == null) {
            return false;
        }
        final String zValue = aConfigMap.get(aKey).trim();
        return !zValue.equals("") && !isAffirmative(aConfigMap.get(aKey));
    }
    
    public static boolean isAffirmative(final String aValue) {
        boolean zAffirmative = false;
        if (aValue != null) {
            final String zStr = aValue.trim().toLowerCase();
            if (zStr.equals("true") || zStr.equals("yes")) {
                zAffirmative = true;
            }
        }
        return zAffirmative;
    }
    
    public static void setConfiguration(final Map<String, String> aConfigMap) {
        clearConfiguration();
        JStellaRunnerUtil.myConfiguration.putAll(aConfigMap);
    }
    
    public static Map<String, String> getConfiguration() {
        return JStellaRunnerUtil.myConfiguration;
    }
    
    public static void clearConfiguration() {
        JStellaRunnerUtil.myConfiguration.clear();
    }
    
    public static boolean isValidDirectory(final String aDirectory) {
        boolean zReturn = false;
        if (aDirectory != null && !aDirectory.trim().equals("")) {
            final File zDir = new File(aDirectory);
            if (zDir.exists()) {
                zReturn = true;
            }
        }
        return zReturn;
    }
    
    public static File getValidDirectory(final String aConfigKey, final Map<String, String> aConfigMap) {
        File zReturn = null;
        if (aConfigMap.containsKey(aConfigKey)) {
            final String zFileName = aConfigMap.get(aConfigKey);
            final File zFile = (zFileName != null) ? new File(zFileName) : null;
            if (zFile != null && zFile.isDirectory()) {
                zReturn = zFile;
            }
        }
        return zReturn;
    }
    
    public static Map<String, String> readConfigFromStream(final InputStream aStream) throws IOException {
        final Map<String, String> zReturn = new HashMap<String, String>();
        BufferedReader zBR = new BufferedReader(new InputStreamReader(aStream));
        boolean zEndOfFile = false;
        while (!zEndOfFile) {
            final String zLine = zBR.readLine();
            if (zLine == null) {
                zEndOfFile = true;
            }
            else {
                final int zEqualIndex = zLine.indexOf("=");
                if (zEqualIndex == -1) {
                    continue;
                }
                final String zKey = zLine.substring(0, zEqualIndex);
                final String zValue = zLine.substring(zEqualIndex + 1);
                zReturn.put(zKey, zValue);
            }
        }
        zBR = null;
        return zReturn;
    }
    
    public static void writeConfigToStream(final OutputStream aStream, final Map<String, String> aConfigMap) throws IOException {
        final BufferedWriter zBW = new BufferedWriter(new OutputStreamWriter(aStream));
        final List<String> zKeyList = new ArrayList<String>(aConfigMap.keySet());
        for (final String zKey : zKeyList) {
            final String zValue = aConfigMap.get(zKey);
            zBW.write("" + zKey + "=" + zValue);
            zBW.newLine();
        }
        zBW.flush();
    }
    
    public static boolean isEmptyString(final String aStr) {
        return aStr == null || aStr.trim().equals("");
    }
    
    static {
        myConfiguration = new HashMap<String, String>();
    }
}
