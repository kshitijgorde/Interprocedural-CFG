// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Date;
import java.io.Serializable;

public class SaalplanPromotionDetails extends SaalplanBaseDetails implements Serializable
{
    private Integer id;
    private int state;
    private long tdlBase;
    private int tdlCodeMaxNumberOfOrders;
    private int tdlCodeMaxNumberOfTickets;
    private int tdlCodeMaxNumberTicketsPerDeal;
    private int tdlCodeMaxNumberTicketsPerEvent;
    private int tdlCodeMinNumberTicketsPerDeal;
    private int tdlCodeMinNumberTicketsPerEvent;
    private int tdlDeliveryFlag;
    private String tdlDescription;
    private int tdlFlag;
    private String tdlName;
    private int tdlPaymentFlag;
    private int tdlPromotionId;
    private int tdlSoldNumbersOfOrders;
    private int tdlSoldNumbersOfTickets;
    private double tdlSoldTotalMaxAmount;
    private int tdlTimezoneId;
    private double tdlTotalMaxAmount;
    private int tdlTotalNumbersOfOrders;
    private int tdlTotalNumbersOfTickets;
    private Date tdlValidFrom;
    private Date tdlValidTo;
    
    public SaalplanPromotionDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanPromotionDetails() {
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public int getState() {
        return this.state;
    }
    
    public long getTdlBase() {
        return this.tdlBase;
    }
    
    public int getTdlCodeMaxNumberOfOrders() {
        return this.tdlCodeMaxNumberOfOrders;
    }
    
    public int getTdlCodeMaxNumberOfTickets() {
        return this.tdlCodeMaxNumberOfTickets;
    }
    
    public int getTdlCodeMaxNumberTicketsPerDeal() {
        return this.tdlCodeMaxNumberTicketsPerDeal;
    }
    
    public int getTdlCodeMaxNumberTicketsPerEvent() {
        return this.tdlCodeMaxNumberTicketsPerEvent;
    }
    
    public int getTdlCodeMinNumberTicketsPerDeal() {
        return this.tdlCodeMinNumberTicketsPerDeal;
    }
    
    public int getTdlCodeMinNumberTicketsPerEvent() {
        return this.tdlCodeMinNumberTicketsPerEvent;
    }
    
    public int getTdlDeliveryFlag() {
        return this.tdlDeliveryFlag;
    }
    
    public String getTdlDescription() {
        return this.tdlDescription;
    }
    
    public int getTdlFlag() {
        return this.tdlFlag;
    }
    
    public String getTdlName() {
        return this.tdlName;
    }
    
    public int getTdlPaymentFlag() {
        return this.tdlPaymentFlag;
    }
    
    public int getTdlPromotionId() {
        return this.tdlPromotionId;
    }
    
    public int getTdlSoldNumbersOfOrders() {
        return this.tdlSoldNumbersOfOrders;
    }
    
    public int getTdlSoldNumbersOfTickets() {
        return this.tdlSoldNumbersOfTickets;
    }
    
    public double getTdlSoldTotalMaxAmount() {
        return this.tdlSoldTotalMaxAmount;
    }
    
    public int getTdlTimezoneId() {
        return this.tdlTimezoneId;
    }
    
    public double getTdlTotalMaxAmount() {
        return this.tdlTotalMaxAmount;
    }
    
    public int getTdlTotalNumbersOfOrders() {
        return this.tdlTotalNumbersOfOrders;
    }
    
    public int getTdlTotalNumbersOfTickets() {
        return this.tdlTotalNumbersOfTickets;
    }
    
    public Date getTdlValidFrom() {
        return this.tdlValidFrom;
    }
    
    public Date getTdlValidTo() {
        return this.tdlValidTo;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public void setState(final int state) {
        this.state = state;
    }
    
    public void setTdlBase(final long tdlBase) {
        this.tdlBase = tdlBase;
    }
    
    public void setTdlCodeMaxNumberOfOrders(final int tdlCodeMaxNumberOfOrders) {
        this.tdlCodeMaxNumberOfOrders = tdlCodeMaxNumberOfOrders;
    }
    
    public void setTdlCodeMaxNumberOfTickets(final int tdlCodeMaxNumberOfTickets) {
        this.tdlCodeMaxNumberOfTickets = tdlCodeMaxNumberOfTickets;
    }
    
    public void setTdlCodeMaxNumberTicketsPerDeal(final int tdlCodeMaxNumberTicketsPerDeal) {
        this.tdlCodeMaxNumberTicketsPerDeal = tdlCodeMaxNumberTicketsPerDeal;
    }
    
    public void setTdlCodeMaxNumberTicketsPerEvent(final int tdlCodeMaxNumberTicketsPerEvent) {
        this.tdlCodeMaxNumberTicketsPerEvent = tdlCodeMaxNumberTicketsPerEvent;
    }
    
    public void setTdlCodeMinNumberTicketsPerDeal(final int tdlCodeMinNumberTicketsPerDeal) {
        this.tdlCodeMinNumberTicketsPerDeal = tdlCodeMinNumberTicketsPerDeal;
    }
    
    public void setTdlCodeMinNumberTicketsPerEvent(final int tdlCodeMinNumberTicketsPerEvent) {
        this.tdlCodeMinNumberTicketsPerEvent = tdlCodeMinNumberTicketsPerEvent;
    }
    
    public void setTdlDeliveryFlag(final int tdlDeliveryFlag) {
        this.tdlDeliveryFlag = tdlDeliveryFlag;
    }
    
    public void setTdlDescription(final String tdlDescription) {
        this.tdlDescription = tdlDescription;
    }
    
    public void setTdlFlag(final int tdlFlag) {
        this.tdlFlag = tdlFlag;
    }
    
    public void setTdlName(final String tdlName) {
        this.tdlName = tdlName;
    }
    
    public void setTdlPaymentFlag(final int tdlPaymentFlag) {
        this.tdlPaymentFlag = tdlPaymentFlag;
    }
    
    public void setTdlPromotionId(final int tdlPromotionId) {
        this.tdlPromotionId = tdlPromotionId;
    }
    
    public void setTdlSoldNumbersOfOrders(final int tdlSoldNumbersOfOrders) {
        this.tdlSoldNumbersOfOrders = tdlSoldNumbersOfOrders;
    }
    
    public void setTdlSoldNumbersOfTickets(final int tdlSoldNumbersOfTickets) {
        this.tdlSoldNumbersOfTickets = tdlSoldNumbersOfTickets;
    }
    
    public void setTdlSoldTotalMaxAmount(final double tdlSoldTotalMaxAmount) {
        this.tdlSoldTotalMaxAmount = tdlSoldTotalMaxAmount;
    }
    
    public void setTdlTimezoneId(final int tdlTimezoneId) {
        this.tdlTimezoneId = tdlTimezoneId;
    }
    
    public void setTdlTotalMaxAmount(final double tdlTotalMaxAmount) {
        this.tdlTotalMaxAmount = tdlTotalMaxAmount;
    }
    
    public void setTdlTotalNumbersOfOrders(final int tdlTotalNumbersOfOrders) {
        this.tdlTotalNumbersOfOrders = tdlTotalNumbersOfOrders;
    }
    
    public void setTdlTotalNumbersOfTickets(final int tdlTotalNumbersOfTickets) {
        this.tdlTotalNumbersOfTickets = tdlTotalNumbersOfTickets;
    }
    
    public void setTdlValidFrom(final Date tdlValidFrom) {
        this.tdlValidFrom = tdlValidFrom;
    }
    
    public void setTdlValidTo(final Date tdlValidTo) {
        this.tdlValidTo = tdlValidTo;
    }
}
