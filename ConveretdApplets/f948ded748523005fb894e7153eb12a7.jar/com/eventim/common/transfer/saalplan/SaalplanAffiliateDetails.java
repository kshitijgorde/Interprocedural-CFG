// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Date;
import java.io.Serializable;

public class SaalplanAffiliateDetails extends SaalplanBaseDetails implements Serializable
{
    private int aenderungAdressdaten;
    private int aenderungKosten;
    private int aenderungPersonendaten;
    private int aenderungZahldaten;
    private int[] affiliatePoolIds;
    private String affiliatePoolList;
    private int agenturLoginRequired;
    private int blacklist;
    private int borderGreenYellow;
    private int captchaMode;
    private String ctsKennung;
    private String directory;
    private int eigenbestandVerkaufbar;
    private String email;
    private int flexPorto;
    private String kuerzel;
    private int kundenDatenLevel;
    private int kundenLoginRequired;
    private Date lastUpdate;
    private int lastUpdateUserId;
    private int maxOrderpositionen;
    private int maxmenge;
    private String orderMailSubject;
    private int plattform;
    private double porto1;
    private double porto2;
    private double porto3;
    private double porto4;
    private double porto5;
    private String sprache;
    private int ticketPriceDisplayMode;
    private double ticketgebuehr;
    private String trackingHtml;
    
    public SaalplanAffiliateDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanAffiliateDetails() {
    }
    
    public int getAenderungAdressdaten() {
        return this.aenderungAdressdaten;
    }
    
    public int getAenderungKosten() {
        return this.aenderungKosten;
    }
    
    public int getAenderungPersonendaten() {
        return this.aenderungPersonendaten;
    }
    
    public int getAenderungZahldaten() {
        return this.aenderungZahldaten;
    }
    
    public int[] getAffiliatePoolIds() {
        return this.affiliatePoolIds;
    }
    
    public String getAffiliatePoolList() {
        return this.affiliatePoolList;
    }
    
    public int getAgenturLoginRequired() {
        return this.agenturLoginRequired;
    }
    
    public int getBlacklist() {
        return this.blacklist;
    }
    
    public int getBorderGreenYellow() {
        return this.borderGreenYellow;
    }
    
    public int getCaptchaMode() {
        return this.captchaMode;
    }
    
    public String getCtsKennung() {
        return this.ctsKennung;
    }
    
    public String getDirectory() {
        return this.directory;
    }
    
    public int getEigenbestandVerkaufbar() {
        return this.eigenbestandVerkaufbar;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public int getFlexPorto() {
        return this.flexPorto;
    }
    
    public String getKuerzel() {
        return this.kuerzel;
    }
    
    public int getKundenDatenLevel() {
        return this.kundenDatenLevel;
    }
    
    public int getKundenLoginRequired() {
        return this.kundenLoginRequired;
    }
    
    public Date getLastUpdate() {
        return this.lastUpdate;
    }
    
    public int getLastUpdateUserId() {
        return this.lastUpdateUserId;
    }
    
    public int getMaxOrderpositionen() {
        return this.maxOrderpositionen;
    }
    
    public int getMaxmenge() {
        return this.maxmenge;
    }
    
    public String getOrderMailSubject() {
        return this.orderMailSubject;
    }
    
    public int getPlattform() {
        return this.plattform;
    }
    
    public double getPorto1() {
        return this.porto1;
    }
    
    public double getPorto2() {
        return this.porto2;
    }
    
    public double getPorto3() {
        return this.porto3;
    }
    
    public double getPorto4() {
        return this.porto4;
    }
    
    public double getPorto5() {
        return this.porto5;
    }
    
    public Object getPrimaryKey() {
        return this.kuerzel;
    }
    
    public String getSprache() {
        return this.sprache;
    }
    
    public int getTicketPriceDisplayMode() {
        return this.ticketPriceDisplayMode;
    }
    
    public double getTicketgebuehr() {
        return this.ticketgebuehr;
    }
    
    public String getTrackingHtml() {
        return this.trackingHtml;
    }
    
    public void setAenderungAdressdaten(final int arg0) {
        this.aenderungAdressdaten = arg0;
    }
    
    public void setAenderungKosten(final int arg0) {
        this.aenderungKosten = arg0;
    }
    
    public void setAenderungPersonendaten(final int arg0) {
        this.aenderungPersonendaten = arg0;
    }
    
    public void setAenderungZahldaten(final int arg0) {
        this.aenderungZahldaten = arg0;
    }
    
    public void setAffiliatePoolIds(final int[] affiliatePoolIds) {
        this.affiliatePoolIds = affiliatePoolIds;
    }
    
    public void setAffiliatePoolList(final String affiliatePoolList) {
        this.affiliatePoolList = affiliatePoolList;
    }
    
    public void setAgenturLoginRequired(final int arg0) {
        this.agenturLoginRequired = arg0;
    }
    
    public void setBlacklist(final int arg0) {
        this.blacklist = arg0;
    }
    
    public void setBorderGreenYellow(final int arg0) {
        this.borderGreenYellow = arg0;
    }
    
    public void setCaptchaMode(final int arg0) {
        this.captchaMode = arg0;
    }
    
    public void setCtsKennung(final String arg0) {
        this.ctsKennung = arg0;
    }
    
    public void setDirectory(final String directory) {
        this.directory = ((directory != null) ? directory.replace('\\', '/') : directory);
    }
    
    public void setEigenbestandVerkaufbar(final int eigenbestandVerkaufbar) {
        this.eigenbestandVerkaufbar = eigenbestandVerkaufbar;
    }
    
    public void setEmail(final String arg0) {
        this.email = arg0;
    }
    
    public void setFlexPorto(final int arg0) {
        this.flexPorto = arg0;
    }
    
    public void setKuerzel(final String arg0) {
        this.kuerzel = arg0;
    }
    
    public void setKundenDatenLevel(final int arg0) {
        this.kundenDatenLevel = arg0;
    }
    
    public void setKundenLoginRequired(final int arg0) {
        this.kundenLoginRequired = arg0;
    }
    
    public void setLastUpdate(final Date arg0) {
        this.lastUpdate = arg0;
    }
    
    public void setLastUpdateUserId(final int arg0) {
        this.lastUpdateUserId = arg0;
    }
    
    public void setMaxOrderpositionen(final int arg0) {
        this.maxOrderpositionen = arg0;
    }
    
    public void setMaxmenge(final int arg0) {
        this.maxmenge = arg0;
    }
    
    public void setOrderMailSubject(final String arg0) {
        this.orderMailSubject = arg0;
    }
    
    public void setPlattform(final int arg0) {
        this.plattform = arg0;
    }
    
    public void setPorto1(final double arg0) {
        this.porto1 = arg0;
    }
    
    public void setPorto2(final double arg0) {
        this.porto2 = arg0;
    }
    
    public void setPorto3(final double arg0) {
        this.porto3 = arg0;
    }
    
    public void setPorto4(final double arg0) {
        this.porto4 = arg0;
    }
    
    public void setPorto5(final double arg0) {
        this.porto5 = arg0;
    }
    
    public void setSprache(final String arg0) {
        this.sprache = arg0;
    }
    
    public void setTicketPriceDisplayMode(final int ticketPriceDisplayMode) {
        this.ticketPriceDisplayMode = ticketPriceDisplayMode;
    }
    
    public void setTicketgebuehr(final double arg0) {
        this.ticketgebuehr = arg0;
    }
    
    public void setTrackingHtml(final String arg0) {
        this.trackingHtml = arg0;
    }
    
    public String toString() {
        String returnString = "";
        returnString += this.kuerzel;
        returnString = returnString + ", " + this.directory;
        returnString = returnString + ", " + this.email;
        returnString = returnString + ", " + this.porto1;
        returnString = returnString + ", " + this.porto2;
        returnString = returnString + ", " + this.porto3;
        returnString = returnString + ", " + this.porto4;
        returnString = returnString + ", " + this.porto5;
        returnString = returnString + ", " + this.sprache;
        returnString = returnString + ", " + this.plattform;
        returnString = returnString + ", " + this.ctsKennung;
        returnString = returnString + ", " + this.blacklist;
        returnString = returnString + ", " + this.flexPorto;
        returnString = returnString + ", " + this.ticketgebuehr;
        returnString = returnString + ", " + this.kundenDatenLevel;
        returnString = returnString + ", " + this.orderMailSubject;
        returnString = returnString + ", " + this.maxmenge;
        returnString = returnString + ", " + this.agenturLoginRequired;
        returnString = returnString + ", " + this.kundenLoginRequired;
        returnString = returnString + ", " + this.aenderungPersonendaten;
        returnString = returnString + ", " + this.aenderungAdressdaten;
        returnString = returnString + ", " + this.aenderungZahldaten;
        returnString = returnString + ", " + this.aenderungKosten;
        returnString = returnString + ", " + this.borderGreenYellow;
        returnString = returnString + ", " + this.trackingHtml;
        returnString = returnString + ", " + this.maxOrderpositionen;
        returnString = returnString + ", " + this.affiliatePoolList;
        returnString = returnString + ", " + this.affiliatePoolIds;
        returnString = returnString + ", " + this.eigenbestandVerkaufbar;
        returnString = returnString + ", " + this.lastUpdate;
        returnString = returnString + ", " + this.lastUpdateUserId;
        return returnString;
    }
}
