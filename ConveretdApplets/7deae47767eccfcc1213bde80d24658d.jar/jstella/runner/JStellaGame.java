// 
// Decompiled by Procyon v0.5.30
// 

package jstella.runner;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import jstella.util.JStellaObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import jstella.cart.Cartridge;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;
import java.io.Serializable;

public class JStellaGame implements Serializable
{
    private static final long serialVersionUID = -2686211561325623816L;
    public static Map<String, String> myKeyTranslations;
    private static Random myRandomizer;
    private Long myUID;
    private String myGameFilename;
    private String myGameTitle;
    private byte[] myROMData;
    private byte[] myGraphicData;
    private String myMD5;
    private Map<String, String> myGameConfig;
    
    public JStellaGame() {
        this.myUID = JStellaGame.myRandomizer.nextLong();
        this.myGameFilename = "";
        this.myGameTitle = "";
        this.myROMData = null;
        this.myGraphicData = null;
        this.myMD5 = "";
        this.myGameConfig = new HashMap<String, String>();
    }
    
    public byte[] getROMData() {
        return this.myROMData;
    }
    
    public byte[] getGraphicData() {
        return this.myGraphicData;
    }
    
    public void setGraphicData(final byte[] aData) {
        this.myGraphicData = aData;
    }
    
    public String getGameTitle() {
        return this.myGameTitle;
    }
    
    public void setGameTitle(final String aTitle) {
        this.myGameTitle = aTitle;
    }
    
    public String getGameFilename() {
        return this.myGameFilename;
    }
    
    public void setGameFilename(final String aFilename) {
        this.myGameFilename = aFilename;
    }
    
    public Map<String, String> getGameConfig() {
        return this.myGameConfig;
    }
    
    public String getMD5() {
        return this.myGameConfig.get("jstella.game.md5");
    }
    
    public void setMD5(final String aMD5) {
        this.myGameConfig.put("jstella.game.md5", aMD5);
    }
    
    public void setROMData(final byte[] aData, final boolean aUpdateMD5) {
        this.myROMData = aData;
        if (aUpdateMD5) {
            this.updateMD5();
        }
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (this.myGameConfig == null) {
            this.myGameConfig = new HashMap<String, String>();
        }
        if (this.myMD5 == null) {
            this.updateMD5();
        }
    }
    
    public void importMetadata(final JStellaGame aSource) {
        if (this.getGameTitle() == null || this.getGameTitle().equals("")) {
            this.setGameTitle(aSource.getGameTitle());
        }
        if (this.getGameFilename() == null || this.getGameFilename().equals("")) {
            this.setGameFilename(aSource.getGameFilename());
        }
        if (this.getROMData() == null || this.getROMData().length == 0) {
            this.setROMData(aSource.getROMData(), false);
        }
        if (this.getGraphicData() == null || this.getGraphicData().length == 0) {
            this.setGraphicData(aSource.getGraphicData());
        }
        final List<String> zKeyList = new ArrayList<String>(aSource.getGameConfig().keySet());
        for (final String zKey : zKeyList) {
            if (!this.getGameConfig().containsKey(zKey)) {
                this.getGameConfig().put(zKey, aSource.getGameConfig().get(zKey));
            }
        }
    }
    
    public void dispose() {
        this.myGraphicData = null;
        this.myROMData = null;
        this.myGameTitle = null;
        this.myGameFilename = null;
        this.myGameConfig = null;
    }
    
    public void updateMD5() {
        if (this.myROMData == null) {
            this.setMD5("");
        }
        else {
            this.setMD5(Cartridge.calculateMD5(this.myROMData));
        }
    }
    
    public static JStellaGame loadGameFile(final File aGameFile) throws ClassNotFoundException, IOException {
        JStellaGame zReturn = null;
        final ObjectInputStream zOIS = new JStellaObjectInputStream(new FileInputStream(aGameFile));
        final Object zObj = zOIS.readObject();
        zOIS.close();
        if (zObj instanceof JStellaGame) {
            zReturn = (JStellaGame)zObj;
            zReturn.setGameFilename(aGameFile.getName());
        }
        return zReturn;
    }
    
    public File saveGameFile(final File aRepositoryDir) throws IOException {
        assert !JStellaRunnerUtil.isEmptyString(this.getGameFilename());
        final File zOutputFile = new File(aRepositoryDir, this.getGameFilename());
        final ObjectOutputStream zOOS = new ObjectOutputStream(new FileOutputStream(zOutputFile));
        zOOS.writeObject(this);
        zOOS.close();
        return zOutputFile;
    }
    
    public String toString() {
        if (!JStellaRunnerUtil.isEmptyString(this.getGameTitle())) {
            return this.getGameTitle();
        }
        return this.getGameFilename();
    }
    
    public JStellaGame createMetadataObject() {
        System.out.println("Creating metadata for: " + this.getGameTitle());
        final JStellaGame zMetadata = new JStellaGame();
        zMetadata.myGameTitle = this.myGameTitle;
        zMetadata.myMD5 = this.myMD5;
        zMetadata.myGameFilename = this.myGameFilename;
        if (this.myGraphicData != null) {
            zMetadata.myGraphicData = new byte[this.myGraphicData.length];
            System.arraycopy(this.myGraphicData, 0, zMetadata.myGraphicData, 0, this.myGraphicData.length);
        }
        zMetadata.myGameConfig.putAll(this.myGameConfig);
        return zMetadata;
    }
    
    static {
        JStellaObjectInputStream.registerClass(JStellaGame.class, -2686211561325623816L);
        (JStellaGame.myKeyTranslations = new HashMap<String, String>()).put("jstella.game.maker", "Maker");
        JStellaGame.myKeyTranslations.put("jstella.game.year", "Year");
        JStellaGame.myKeyTranslations.put("jstella.game.cartridge.type", "Cartridge type");
        JStellaGame.myKeyTranslations.put("jstella.game.cartridge.model", "Cartridge model");
        JStellaGame.myKeyTranslations.put("jstella.game.note", "Notes");
        JStellaGame.myKeyTranslations.put("jstella.console.switch.difficulty.left", "Left difficulty");
        JStellaGame.myKeyTranslations.put("jstella.console.switch.difficulty.right", "Right difficulty");
        JStellaGame.myKeyTranslations.put("jstella.controller.left.type", "Left controller");
        JStellaGame.myKeyTranslations.put("jstella.controller.right.type", "Right controller");
        JStellaGame.myKeyTranslations.put("jstella.display.format", "Display format");
        JStellaGame.myKeyTranslations.put("jstella.display.framerate", "Display framerate");
        JStellaGame.myKeyTranslations.put("jstella.display.width", "Display width");
        JStellaGame.myKeyTranslations.put("jstella.display.height", "Display height");
        JStellaGame.myKeyTranslations.put("jstella.display.xstart", "Display x-start");
        JStellaGame.myKeyTranslations.put("jstella.display.ystart", "Display y-start");
        JStellaGame.myKeyTranslations.put("jstella.display.antiflickermode", "Antiflicker mode");
        JStellaGame.myKeyTranslations.put("jstella.game.misc", "Misc");
        JStellaGame.myRandomizer = new Random();
    }
}
