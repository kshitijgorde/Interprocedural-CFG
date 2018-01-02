// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.thirdparty;

import java.net.URL;
import com.zylom.properties.client.ClientProperties;
import java.applet.Applet;

public abstract class GameProperties
{
    public static String[] DATAGATHERINGKEYS;
    public static String DATAGATHERING_URL;
    public static boolean OBFUSCATED;
    protected Applet applet;
    public static String highscoreDestinationTarget;
    public static String highscoreDestinationURL;
    protected static ClientProperties properties;
    
    static {
        GameProperties.OBFUSCATED = false;
    }
    
    private URL decoyGetCodeBase() {
        return this.applet.getCodeBase();
    }
    
    private String decoyGetParameter(final String parameter) {
        return this.applet.getParameter(parameter);
    }
    
    private void decoyShowDocument(final String spec) throws Exception {
        this.applet.getAppletContext().showDocument(new URL(spec));
    }
    
    public static ClientProperties getProperties() {
        return GameProperties.properties;
    }
    
    public void init(final Applet applet) {
        this.applet = applet;
        String language = this.decoyGetParameter(StaticStrings.PARAM_LANGUAGE);
        String country = this.decoyGetParameter(StaticStrings.PARAM_COUNTRY);
        String lookandfeel = this.decoyGetParameter(StaticStrings.PARAM_LOOKANDFEEL);
        language = ((language == null) ? StaticStrings.PARAM_LANGUAGE_DEFAULT : language);
        country = ((country == null) ? StaticStrings.PARAM_COUNTRY_DEFAULT : country);
        lookandfeel = ((lookandfeel == null) ? StaticStrings.PARAM_LOOKANDFEEL_DEFAULT : lookandfeel);
        try {
            GameProperties.properties = new ClientProperties(this.decoyGetCodeBase(), StaticStrings.PATH_PROPERTIES, language, country, lookandfeel);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            try {
                this.showErrorPage();
            }
            catch (Exception ex2) {
                try {
                    this.decoyShowDocument(StaticStrings.PARAM_PROPERTIESERRORPAGE_DEFAULT);
                }
                catch (Exception ex3) {}
            }
            return;
        }
        this.initGameProperties();
        this.initProperties();
    }
    
    private void initGameProperties() {
        try {
            GameProperties.highscoreDestinationTarget = GameProperties.properties.getString("highscoreDestinationTarget");
            GameProperties.highscoreDestinationURL = GameProperties.properties.getString("highscoreDestinationURL");
            GameProperties.DATAGATHERING_URL = GameProperties.properties.getString("DATAGATHERING_URL");
            GameProperties.DATAGATHERINGKEYS = GameProperties.properties.getArrayString("DATAGATHERINGKEYS");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public abstract void initProperties();
    
    private void showErrorPage() {
        String propertiesErrorPage = this.decoyGetParameter(StaticStrings.PARAM_PROPERTIESERRORPAGE);
        propertiesErrorPage = ((propertiesErrorPage == null) ? StaticStrings.PARAM_PROPERTIESERRORPAGE_DEFAULT : propertiesErrorPage);
        try {
            this.decoyShowDocument(propertiesErrorPage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
