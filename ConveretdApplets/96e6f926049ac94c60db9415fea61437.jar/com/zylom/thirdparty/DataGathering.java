// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.thirdparty;

import com.zylom.net.client.HTTPProtocol;
import java.util.Vector;
import com.zylom.net.client.PDU;
import com.zylom.net.client.Connection;
import java.text.MessageFormat;
import java.net.URL;
import java.applet.AppletContext;
import com.zylom.cipher.CipherInteger;
import java.applet.Applet;

public class DataGathering
{
    Applet applet;
    CipherInteger cipherScore;
    protected String dataGameID;
    protected String dataGameSetID;
    protected String[] dataGatheringKeys;
    protected String[] dataGatheringValues;
    protected int dataHighestLevel;
    protected int dataHighestScore;
    protected String dataSkinnedGameID;
    protected String dataVisitID;
    GameProperties gameProperties;
    protected boolean sendDataGathering;
    protected boolean sendScores;
    
    public DataGathering(final Applet applet, final GameProperties gameProperties) {
        this.sendDataGathering = true;
        this.sendScores = true;
        this.dataHighestLevel = -1;
        this.dataHighestScore = -1;
        this.applet = applet;
        this.gameProperties = gameProperties;
        this.dataGameID = ((this.decoyGetParameter(StaticStrings.PARAM_GAMEID) != null) ? this.decoyGetParameter(StaticStrings.PARAM_GAMEID) : "");
        this.dataSkinnedGameID = ((this.decoyGetParameter(StaticStrings.PARAM_SKINNEDGAMEID) != null) ? this.decoyGetParameter(StaticStrings.PARAM_SKINNEDGAMEID) : "");
        this.dataGameSetID = ((this.decoyGetParameter(StaticStrings.PARAM_GAMESETID) != null) ? this.decoyGetParameter(StaticStrings.PARAM_GAMESETID) : "");
        this.dataVisitID = ((this.decoyGetParameter(StaticStrings.PARAM_VISITID) != null) ? this.decoyGetParameter(StaticStrings.PARAM_VISITID) : "");
        this.sendDataGathering = !this.GetBooleanParameter(StaticStrings.PARAM_SENDDATAGATHERING, false);
        this.sendScores = !this.GetBooleanParameter(StaticStrings.PARAM_SENDSCORES, false);
        (this.cipherScore = new CipherInteger()).init(System.currentTimeMillis(), this.dataVisitID);
    }
    
    public boolean GetBooleanParameter(final String parameter, final boolean defaultValue) {
        final String s = this.decoyGetParameter(parameter);
        if (s == null) {
            return defaultValue;
        }
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("1");
    }
    
    public AppletContext decoyGetAppletContext() {
        return this.applet.getAppletContext();
    }
    
    private URL decoyGetCodeBase() {
        return this.applet.getCodeBase();
    }
    
    private String decoyGetCodeBaseHost() {
        return this.decoyGetCodeBase().getHost();
    }
    
    public URL decoyGetDocumentBase() {
        return this.applet.getDocumentBase();
    }
    
    public String decoyGetDocumentBaseHost() {
        return this.decoyGetDocumentBase().getHost();
    }
    
    private String decoyGetParameter(final String parameter) {
        return this.applet.getParameter(parameter);
    }
    
    private static String format(final String pattern, final Object s0) {
        return format(pattern, new Object[] { s0 });
    }
    
    private static String format(String pattern, final Object[] arguments) {
        int start;
        int index;
        for (start = 0, index = 0, index = pattern.indexOf(39, start); index != -1; index = pattern.indexOf(39, start)) {
            pattern = pattern.substring(0, index) + '\'' + pattern.substring(index);
            start = index + 2;
        }
        return MessageFormat.format(pattern, arguments);
    }
    
    private void initialShowDocument(final String spec) throws Exception {
        this.decoyGetAppletContext().showDocument(new URL(spec));
    }
    
    private void localShowDocument(final String protocol, final String host, final String file, final String target) throws Exception {
        if (target.equals("")) {
            this.decoyGetAppletContext().showDocument(new URL(protocol, host, file));
        }
        else {
            this.decoyGetAppletContext().showDocument(new URL(protocol, host, file), target);
        }
    }
    
    private void localShowDocumentDocumentBase(final String spec, final String target) throws Exception {
        if (target.equals("")) {
            this.decoyGetAppletContext().showDocument(new URL(this.decoyGetDocumentBase(), spec));
        }
        else {
            this.decoyGetAppletContext().showDocument(new URL(this.decoyGetDocumentBase(), spec), target);
        }
    }
    
    private void localShowDocumentDocumentBase(final String protocol, final String file, final String target) throws Exception {
        this.localShowDocument(protocol, this.decoyGetDocumentBaseHost(), file, target);
    }
    
    private void setHighscore(final int score, final int level) {
        this.dataHighestScore = ((score > this.dataHighestScore) ? score : this.dataHighestScore);
        this.dataHighestLevel = ((level > this.dataHighestLevel) ? level : this.dataHighestLevel);
    }
    
    private void showErrorPage() {
        String propertiesErrorPage = this.decoyGetParameter(StaticStrings.PARAM_PROPERTIESERRORPAGE);
        propertiesErrorPage = ((propertiesErrorPage == null) ? StaticStrings.PARAM_PROPERTIESERRORPAGE_DEFAULT : propertiesErrorPage);
        try {
            this.initialShowDocument(propertiesErrorPage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void submitDataGathering(final boolean isMuted, final int nrOfGamesPlayed, final int nrOfRestarts, final int loadTime, final int playTime, final int idleTime) {
        if (!this.sendDataGathering) {
            return;
        }
        try {
            final GameProperties gameProperties = this.gameProperties;
            this.dataGatheringKeys = GameProperties.DATAGATHERINGKEYS;
        }
        catch (Exception ex) {}
        (this.dataGatheringValues = new String[11])[0] = this.dataVisitID;
        this.dataGatheringValues[1] = this.dataSkinnedGameID;
        this.dataGatheringValues[2] = this.dataGameSetID;
        this.dataGatheringValues[3] = String.valueOf(!isMuted);
        this.dataGatheringValues[4] = ((this.dataHighestLevel == -1) ? null : String.valueOf(this.dataHighestLevel));
        this.dataGatheringValues[5] = ((this.dataHighestScore == -1) ? null : String.valueOf(this.dataHighestScore));
        this.dataGatheringValues[6] = String.valueOf(nrOfGamesPlayed);
        this.dataGatheringValues[7] = String.valueOf(nrOfRestarts);
        this.dataGatheringValues[8] = String.valueOf(playTime);
        this.dataGatheringValues[9] = String.valueOf(loadTime);
        this.dataGatheringValues[10] = String.valueOf(idleTime);
        try {
            final String[] keys = this.dataGatheringKeys;
            final String[] values = this.dataGatheringValues;
            final GameProperties gameProperties2 = this.gameProperties;
            final Connection con = new Connection(format(GameProperties.DATAGATHERING_URL, this.decoyGetCodeBaseHost()));
            con.setEncrypted(true);
            con.setUseBrowser(true);
            final PDU pdu = new PDU();
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] != null && values[i] != null) {
                    pdu.add(keys[i], values[i]);
                }
            }
            con.sendData(pdu);
            try {
                con.flush(10000);
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void submitHighscore(final CipherInteger score, final CipherInteger level) {
        this.setHighscore(score.getValue(), level.getValue());
        if (!this.sendScores) {
            return;
        }
        final Vector parameters = new Vector();
        parameters.addElement(String.valueOf(this.dataGameID));
        parameters.addElement(String.valueOf(score.getValue()));
        parameters.addElement(score.getCipherValue());
        final String urlParameters = HTTPProtocol.encryptServletParameters(parameters);
        if (this.decoyGetParameter(StaticStrings.PARAM_ENDGAMEPAGETARGET) == null || this.decoyGetParameter(StaticStrings.PARAM_ENDGAMEPAGEURL) == null) {
            String destinationURL = "";
            String destinationTarget = "";
            try {
                final GameProperties gameProperties = this.gameProperties;
                destinationURL = GameProperties.highscoreDestinationURL;
            }
            catch (Exception ex) {
                this.showErrorPage();
            }
            try {
                final GameProperties gameProperties2 = this.gameProperties;
                destinationTarget = GameProperties.highscoreDestinationTarget;
            }
            catch (Exception ex) {
                this.showErrorPage();
            }
            try {
                this.localShowDocumentDocumentBase(StaticStrings.HTTP, destinationURL + urlParameters, destinationTarget);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            try {
                this.localShowDocumentDocumentBase(this.decoyGetParameter(StaticStrings.PARAM_ENDGAMEPAGEURL), this.decoyGetParameter(StaticStrings.PARAM_ENDGAMEPAGETARGET));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }
}
