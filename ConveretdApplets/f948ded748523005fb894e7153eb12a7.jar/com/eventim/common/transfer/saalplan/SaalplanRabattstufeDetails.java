// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import com.eventim.common.utils.scheduler.DateChecker;
import java.util.Date;

public class SaalplanRabattstufeDetails extends SaalplanBaseDetails
{
    private double aufschlag;
    private int autoupdate;
    private int bestandEigen;
    private int bestandFremdsystem;
    private int bestandKomm;
    private int bestandTdl;
    private double ekpreisHotel;
    private double ekpreisSonstiges;
    private double ekpreisStaedtecard;
    private double ekpreisTicket;
    private double ekpreisTransfer;
    private double ekpreisVerpflegung;
    private int erId;
    private int evId;
    private double exclFees;
    private int exclusiveAffiliatePoolId;
    private int fremdsystemId;
    private int gesperrteAffiliatePoolId;
    private int gruppeZu;
    private Integer id;
    private Date lastUpdate;
    private int lastUpdateUserId;
    private int maxZusammenhaengendeTDL;
    private int maxmenge;
    private int minmenge;
    private String name;
    private int nummer;
    private Date offsaleDate;
    private Date onsaleDate;
    private int pkId;
    private int prId;
    private double promorabatt;
    private SaalplanRabattstufeFeeDetails[] rabattstufeFeeDetails;
    private double refundierung;
    private int status;
    private double systemgebuehr;
    private int tdlGruppeZu;
    private int tdlMaxTickets;
    private int tdlMinTickets;
    private int tdlPreisRabattLinkId;
    private String tdlRabattKuerzel;
    private int tdlRabattstufeId;
    private double ticketgebuehr;
    private double vkpreis;
    private double vvgebuehr;
    
    public SaalplanRabattstufeDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanRabattstufeDetails() {
    }
    
    public boolean checkOnAndOffSaleDate() {
        return DateChecker.isNowBetween(this.getOnsaleDate(), this.getOffsaleDate());
    }
    
    public double getAufschlag() {
        return this.aufschlag;
    }
    
    public int getAutoupdate() {
        return this.autoupdate;
    }
    
    public int getBestandEigen() {
        return this.bestandEigen;
    }
    
    public int getBestandFremdsystem() {
        return this.bestandFremdsystem;
    }
    
    public int getBestandKomm() {
        return this.bestandKomm;
    }
    
    public int getBestandTdl() {
        return this.bestandTdl;
    }
    
    public double getEkpreisHotel() {
        return this.ekpreisHotel;
    }
    
    public double getEkpreisSonstiges() {
        return this.ekpreisSonstiges;
    }
    
    public double getEkpreisStaedtecard() {
        return this.ekpreisStaedtecard;
    }
    
    public double getEkpreisTicket() {
        return this.ekpreisTicket;
    }
    
    public double getEkpreisTransfer() {
        return this.ekpreisTransfer;
    }
    
    public double getEkpreisVerpflegung() {
        return this.ekpreisVerpflegung;
    }
    
    public int getErId() {
        return this.erId;
    }
    
    public int getEvId() {
        return this.evId;
    }
    
    public double getExclFees() {
        return this.exclFees;
    }
    
    public int getExclusiveAffiliatePoolId() {
        return this.exclusiveAffiliatePoolId;
    }
    
    public int getFremdsystemId() {
        return this.fremdsystemId;
    }
    
    public int getGesperrteAffiliatePoolId() {
        return this.gesperrteAffiliatePoolId;
    }
    
    public int getGruppeZu() {
        return this.gruppeZu;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public Date getLastUpdate() {
        return this.lastUpdate;
    }
    
    public int getLastUpdateUserId() {
        return this.lastUpdateUserId;
    }
    
    public int getMaxZusammenhaengendeTdl() {
        return this.maxZusammenhaengendeTDL;
    }
    
    public int getMaxmenge() {
        return this.maxmenge;
    }
    
    public int getMinmenge() {
        return this.minmenge;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getNummer() {
        return this.nummer;
    }
    
    public Date getOffsaleDate() {
        return this.offsaleDate;
    }
    
    public Date getOnsaleDate() {
        return this.onsaleDate;
    }
    
    public int getPkId() {
        return this.pkId;
    }
    
    public int getPrId() {
        return this.prId;
    }
    
    public Object getPrimaryKey() {
        return this.id;
    }
    
    public double getPromorabatt() {
        return this.promorabatt;
    }
    
    public SaalplanRabattstufeFeeDetails[] getRabattstufeFeeDetails() {
        return this.rabattstufeFeeDetails;
    }
    
    public double getRefundierung() {
        return this.refundierung;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public double getSystemgebuehr() {
        return this.systemgebuehr;
    }
    
    public int getTdlGruppeZu() {
        return this.tdlGruppeZu;
    }
    
    public int getTdlMaxTickets() {
        return this.tdlMaxTickets;
    }
    
    public int getTdlMinTickets() {
        return this.tdlMinTickets;
    }
    
    public int getTdlPreisRabattLinkId() {
        return this.tdlPreisRabattLinkId;
    }
    
    public String getTdlRabattKuerzel() {
        return this.tdlRabattKuerzel;
    }
    
    public int getTdlRabattstufeId() {
        return this.tdlRabattstufeId;
    }
    
    public double getTicketgebuehr() {
        return this.ticketgebuehr;
    }
    
    public double getVkpreis() {
        return this.vkpreis;
    }
    
    public double getVvgebuehr() {
        return this.vvgebuehr;
    }
    
    public void setAufschlag(final double arg0) {
        this.aufschlag = arg0;
    }
    
    public void setAutoupdate(final int arg0) {
        this.autoupdate = arg0;
    }
    
    public void setBestandEigen(final int arg0) {
        this.bestandEigen = arg0;
    }
    
    public void setBestandFremdsystem(final int arg0) {
        this.bestandFremdsystem = arg0;
    }
    
    public void setBestandKomm(final int arg0) {
        this.bestandKomm = arg0;
    }
    
    public void setBestandTdl(final int arg0) {
        this.bestandTdl = arg0;
    }
    
    public void setEkpreisHotel(final double arg0) {
        this.ekpreisHotel = arg0;
    }
    
    public void setEkpreisSonstiges(final double arg0) {
        this.ekpreisSonstiges = arg0;
    }
    
    public void setEkpreisStaedtecard(final double arg0) {
        this.ekpreisStaedtecard = arg0;
    }
    
    public void setEkpreisTicket(final double arg0) {
        this.ekpreisTicket = arg0;
    }
    
    public void setEkpreisTransfer(final double arg0) {
        this.ekpreisTransfer = arg0;
    }
    
    public void setEkpreisVerpflegung(final double arg0) {
        this.ekpreisVerpflegung = arg0;
    }
    
    public void setErId(final int arg0) {
        this.erId = arg0;
    }
    
    public void setEvId(final int arg0) {
        this.evId = arg0;
    }
    
    public void setExclFees(final double exclFees) {
        this.exclFees = exclFees;
    }
    
    public void setExclusiveAffiliatePoolId(final int exclusiveAffiliatePoolId) {
        this.exclusiveAffiliatePoolId = exclusiveAffiliatePoolId;
    }
    
    public void setFremdsystemId(final int arg0) {
        this.fremdsystemId = arg0;
    }
    
    public void setGesperrteAffiliatePoolId(final int gesperrteAffiliatePoolId) {
        this.gesperrteAffiliatePoolId = gesperrteAffiliatePoolId;
    }
    
    public void setGruppeZu(final int gruppeZu) {
        this.gruppeZu = gruppeZu;
    }
    
    public void setId(final Integer arg0) {
        this.id = arg0;
    }
    
    public void setLastUpdate(final Date arg0) {
        this.lastUpdate = arg0;
    }
    
    public void setLastUpdateUserId(final int arg0) {
        this.lastUpdateUserId = arg0;
    }
    
    public void setMaxZusammenhaengendeTdl(final int _maxZusammenhaengendeTDL) {
        this.maxZusammenhaengendeTDL = _maxZusammenhaengendeTDL;
    }
    
    public void setMaxmenge(final int _maxmenge) {
        this.maxmenge = _maxmenge;
    }
    
    public void setMinmenge(final int minmenge) {
        this.minmenge = minmenge;
    }
    
    public void setName(final String arg0) {
        this.name = arg0;
    }
    
    public void setNummer(final int arg0) {
        this.nummer = arg0;
    }
    
    public void setOffsaleDate(final Date _offsaleDate) {
        this.offsaleDate = _offsaleDate;
    }
    
    public void setOnsaleDate(final Date _onsaleDate) {
        this.onsaleDate = _onsaleDate;
    }
    
    public void setPkId(final int arg0) {
        this.pkId = arg0;
    }
    
    public void setPrId(final int prId) {
        this.prId = prId;
    }
    
    public void setPromorabatt(final double arg0) {
        this.promorabatt = arg0;
    }
    
    public void setRabattstufeFeeDetails(final SaalplanRabattstufeFeeDetails[] rabattstufeFeeDetails) {
        this.rabattstufeFeeDetails = rabattstufeFeeDetails;
    }
    
    public void setRefundierung(final double arg0) {
        this.refundierung = arg0;
    }
    
    public void setStatus(final int arg0) {
        this.status = arg0;
    }
    
    public void setSystemgebuehr(final double arg0) {
        this.systemgebuehr = arg0;
    }
    
    public void setTdlGruppeZu(final int tdlGruppeZu) {
        this.tdlGruppeZu = tdlGruppeZu;
    }
    
    public void setTdlMaxTickets(final int tdlMaxTickets) {
        this.tdlMaxTickets = tdlMaxTickets;
    }
    
    public void setTdlMinTickets(final int tdlMinTickets) {
        this.tdlMinTickets = tdlMinTickets;
    }
    
    public void setTdlPreisRabattLinkId(final int arg0) {
        this.tdlPreisRabattLinkId = arg0;
    }
    
    public void setTdlRabattKuerzel(final String tdlRabattKuerzel) {
        this.tdlRabattKuerzel = tdlRabattKuerzel;
    }
    
    public void setTdlRabattstufeId(final int arg0) {
        this.tdlRabattstufeId = arg0;
    }
    
    public void setTicketgebuehr(final double arg0) {
        this.ticketgebuehr = arg0;
    }
    
    public void setVkpreis(final double arg0) {
        this.vkpreis = arg0;
    }
    
    public void setVvgebuehr(final double arg0) {
        this.vvgebuehr = arg0;
    }
    
    public String toString() {
        String returnString = "";
        returnString += this.id;
        returnString = returnString + ", " + this.lastUpdate;
        returnString = returnString + ", " + this.lastUpdateUserId;
        returnString = returnString + ", " + this.erId;
        returnString = returnString + ", " + this.evId;
        returnString = returnString + ", " + this.pkId;
        returnString = returnString + ", " + this.name;
        returnString = returnString + ", " + this.nummer;
        returnString = returnString + ", " + this.bestandTdl;
        returnString = returnString + ", " + this.bestandEigen;
        returnString = returnString + ", " + this.bestandKomm;
        returnString = returnString + ", " + this.bestandFremdsystem;
        returnString = returnString + ", " + this.fremdsystemId;
        returnString = returnString + ", " + this.vkpreis;
        returnString = returnString + ", " + this.ekpreisTicket;
        returnString = returnString + ", " + this.ekpreisHotel;
        returnString = returnString + ", " + this.ekpreisTransfer;
        returnString = returnString + ", " + this.ekpreisStaedtecard;
        returnString = returnString + ", " + this.ekpreisVerpflegung;
        returnString = returnString + ", " + this.ekpreisSonstiges;
        returnString = returnString + ", " + this.vvgebuehr;
        returnString = returnString + ", " + this.systemgebuehr;
        returnString = returnString + ", " + this.aufschlag;
        returnString = returnString + ", " + this.refundierung;
        returnString = returnString + ", " + this.ticketgebuehr;
        returnString = returnString + ", " + this.autoupdate;
        returnString = returnString + ", " + this.promorabatt;
        returnString = returnString + ", " + this.tdlRabattstufeId;
        returnString = returnString + ", " + this.tdlPreisRabattLinkId;
        returnString = returnString + ", " + this.status;
        returnString = returnString + ", " + this.exclusiveAffiliatePoolId;
        returnString = returnString + ", " + this.gesperrteAffiliatePoolId;
        returnString = returnString + ", " + this.minmenge;
        returnString = returnString + ", " + this.maxmenge;
        returnString = returnString + ", " + this.maxZusammenhaengendeTDL;
        returnString = returnString + ", " + this.gruppeZu;
        returnString = returnString + ", " + this.tdlMinTickets;
        returnString = returnString + ", " + this.tdlMaxTickets;
        returnString = returnString + ", " + this.tdlGruppeZu;
        returnString = returnString + ", " + this.tdlRabattKuerzel;
        returnString = returnString + ", " + this.prId;
        returnString = returnString + ", " + this.exclFees;
        return returnString;
    }
}
