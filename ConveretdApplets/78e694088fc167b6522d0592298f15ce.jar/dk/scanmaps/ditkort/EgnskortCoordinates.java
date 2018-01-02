// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Point;

public class EgnskortCoordinates
{
    private static Point alsUpper;
    private static Point alsLower;
    private static Point cphUpper;
    private static Point cphLower;
    private static Point anholtUpper;
    private static Point anholtLower;
    private static Point bogoeUpper;
    private static Point bogoeLower;
    private static Point bornholmUpper;
    private static Point bornholmLower;
    private static Point endelaveUpper;
    private static Point endelaveLower;
    private static Point fanoeUpper;
    private static Point fanoeLower;
    private static Point femoeUpper;
    private static Point femoeLower;
    private static Point furUpper;
    private static Point furLower;
    private static Point hesseloeUpper;
    private static Point hesseloeLower;
    private static Point laesoeUpper;
    private static Point laesoeLower;
    private static Point mandoeUpper;
    private static Point mandoeLower;
    private static Point nsUpper;
    private static Point nsLower;
    private static Point odenseUpper;
    private static Point odenseLower;
    private static Point roemoeUpper;
    private static Point roemoeLower;
    private static Point samsoeUpper;
    private static Point samsoeLower;
    private static Point sejeroeUpper;
    private static Point sejeroeLower;
    private static Point silkeborgUpper;
    private static Point silkeborgLower;
    private static Point soUpper;
    private static Point soLower;
    private static Point skagenUpper;
    private static Point skagenLower;
    private static Point taasingeUpper;
    private static Point taasingeLower;
    private static Point venoeUpper;
    private static Point venoeLower;
    private static Point aalborgUpper;
    private static Point aalborgLower;
    private static Point aarhusUpper;
    private static Point aarhusLower;
    private static Point langelandFirstUpper;
    private static Point langelandFirstLower;
    private static Point langelandSecondUpper;
    private static Point langelandSecondLower;
    private static Point langelandThirdUpper;
    private static Point langelandThirdLower;
    private static Point aeroFirstUpper;
    private static Point aeroFirstLower;
    private static Point aeroSecondUpper;
    private static Point aeroSecondLower;
    private static Point moenFirstUpper;
    private static Point moenFirstLower;
    private static Point moenSecondUpper;
    private static Point moenSecondLower;
    private static Point fejoeFirstUpper;
    private static Point fejoeFirstLower;
    private static Point fejoeSecondUpper;
    private static Point fejoeSecondLower;
    
    static {
        EgnskortCoordinates.alsUpper = new Point(170, 504);
        EgnskortCoordinates.alsLower = new Point(220, 551);
        EgnskortCoordinates.cphUpper = new Point(461, 371);
        EgnskortCoordinates.cphLower = new Point(509, 409);
        EgnskortCoordinates.anholtUpper = new Point(357, 186);
        EgnskortCoordinates.anholtLower = new Point(381, 205);
        EgnskortCoordinates.bogoeUpper = new Point(419, 518);
        EgnskortCoordinates.bogoeLower = new Point(443, 537);
        EgnskortCoordinates.bornholmUpper = new Point(418, 24);
        EgnskortCoordinates.bornholmLower = new Point(473, 83);
        EgnskortCoordinates.endelaveUpper = new Point(227, 367);
        EgnskortCoordinates.endelaveLower = new Point(251, 386);
        EgnskortCoordinates.fanoeUpper = new Point(30, 432);
        EgnskortCoordinates.fanoeLower = new Point(48, 460);
        EgnskortCoordinates.femoeUpper = new Point(365, 513);
        EgnskortCoordinates.femoeLower = new Point(389, 533);
        EgnskortCoordinates.furUpper = new Point(93, 169);
        EgnskortCoordinates.furLower = new Point(116, 189);
        EgnskortCoordinates.hesseloeUpper = new Point(374, 282);
        EgnskortCoordinates.hesseloeLower = new Point(398, 301);
        EgnskortCoordinates.laesoeUpper = new Point(290, 74);
        EgnskortCoordinates.laesoeLower = new Point(331, 111);
        EgnskortCoordinates.mandoeUpper = new Point(44, 459);
        EgnskortCoordinates.mandoeLower = new Point(68, 478);
        EgnskortCoordinates.nsUpper = new Point(401, 299);
        EgnskortCoordinates.nsLower = new Point(485, 341);
        EgnskortCoordinates.odenseUpper = new Point(237, 433);
        EgnskortCoordinates.odenseLower = new Point(266, 456);
        EgnskortCoordinates.roemoeUpper = new Point(44, 480);
        EgnskortCoordinates.roemoeLower = new Point(63, 512);
        EgnskortCoordinates.samsoeUpper = new Point(262, 329);
        EgnskortCoordinates.samsoeLower = new Point(285, 377);
        EgnskortCoordinates.sejeroeUpper = new Point(318, 343);
        EgnskortCoordinates.sejeroeLower = new Point(341, 362);
        EgnskortCoordinates.silkeborgUpper = new Point(151, 297);
        EgnskortCoordinates.silkeborgLower = new Point(199, 335);
        EgnskortCoordinates.soUpper = new Point(342, 327);
        EgnskortCoordinates.soLower = new Point(398, 366);
        EgnskortCoordinates.skagenUpper = new Point(190, 0);
        EgnskortCoordinates.skagenLower = new Point(270, 39);
        EgnskortCoordinates.taasingeUpper = new Point(265, 508);
        EgnskortCoordinates.taasingeLower = new Point(287, 531);
        EgnskortCoordinates.venoeUpper = new Point(54, 221);
        EgnskortCoordinates.venoeLower = new Point(78, 241);
        EgnskortCoordinates.aalborgUpper = new Point(184, 122);
        EgnskortCoordinates.aalborgLower = new Point(213, 146);
        EgnskortCoordinates.aarhusUpper = new Point(209, 292);
        EgnskortCoordinates.aarhusLower = new Point(239, 315);
        EgnskortCoordinates.langelandFirstUpper = new Point(274, 488);
        EgnskortCoordinates.langelandFirstLower = new Point(313, 506);
        EgnskortCoordinates.langelandSecondUpper = new Point(287, 506);
        EgnskortCoordinates.langelandSecondLower = new Point(314, 531);
        EgnskortCoordinates.langelandThirdUpper = new Point(274, 531);
        EgnskortCoordinates.langelandThirdLower = new Point(314, 573);
        EgnskortCoordinates.aeroFirstUpper = new Point(231, 522);
        EgnskortCoordinates.aeroFirstLower = new Point(263, 531);
        EgnskortCoordinates.aeroSecondUpper = new Point(231, 532);
        EgnskortCoordinates.aeroSecondLower = new Point(271, 556);
        EgnskortCoordinates.moenFirstUpper = new Point(436, 501);
        EgnskortCoordinates.moenFirstLower = new Point(488, 517);
        EgnskortCoordinates.moenSecondUpper = new Point(444, 517);
        EgnskortCoordinates.moenSecondLower = new Point(488, 539);
        EgnskortCoordinates.fejoeFirstUpper = new Point(351, 517);
        EgnskortCoordinates.fejoeFirstLower = new Point(363, 532);
        EgnskortCoordinates.fejoeSecondUpper = new Point(350, 532);
        EgnskortCoordinates.fejoeSecondLower = new Point(375, 537);
    }
    
    public static Egn detectRectangularRegion(final Point point) {
        if (isInBound(EgnskortCoordinates.alsUpper, EgnskortCoordinates.alsLower, point)) {
            return new Egn(EgnskortCoordinates.alsUpper, EgnskortCoordinates.alsLower, "0250418", "Als");
        }
        if (isInBound(EgnskortCoordinates.cphUpper, EgnskortCoordinates.cphLower, point)) {
            return new Egn(EgnskortCoordinates.cphUpper, EgnskortCoordinates.cphLower, "0250417", "Amager & K\u00f8benhavn");
        }
        if (isInBound(EgnskortCoordinates.anholtUpper, EgnskortCoordinates.anholtLower, point)) {
            return new Egn(EgnskortCoordinates.anholtUpper, EgnskortCoordinates.anholtLower, "0250433", "Anholt");
        }
        if (isInBound(EgnskortCoordinates.bogoeUpper, EgnskortCoordinates.bogoeLower, point)) {
            return new Egn(EgnskortCoordinates.bogoeUpper, EgnskortCoordinates.bogoeLower, "0250440", "Bog\u00f8");
        }
        if (isInBound(EgnskortCoordinates.bornholmUpper, EgnskortCoordinates.bornholmLower, point)) {
            return new Egn(EgnskortCoordinates.bornholmUpper, EgnskortCoordinates.bornholmLower, "0250428", "Bornholm");
        }
        if (isInBound(EgnskortCoordinates.endelaveUpper, EgnskortCoordinates.endelaveLower, point)) {
            return new Egn(EgnskortCoordinates.endelaveUpper, EgnskortCoordinates.endelaveLower, "0250438", "Endelave");
        }
        if (isInBound(EgnskortCoordinates.fanoeUpper, EgnskortCoordinates.fanoeLower, point)) {
            return new Egn(EgnskortCoordinates.fanoeUpper, EgnskortCoordinates.fanoeLower, "0250431", "Fan\u00f8");
        }
        if (isInBound(EgnskortCoordinates.femoeUpper, EgnskortCoordinates.femoeLower, point)) {
            return new Egn(EgnskortCoordinates.femoeUpper, EgnskortCoordinates.femoeLower, "0250434", "Fem\u00f8");
        }
        if (isInBound(EgnskortCoordinates.furUpper, EgnskortCoordinates.furLower, point)) {
            return new Egn(EgnskortCoordinates.furUpper, EgnskortCoordinates.furLower, "0250437", "Fur");
        }
        if (isInBound(EgnskortCoordinates.hesseloeUpper, EgnskortCoordinates.hesseloeLower, point)) {
            return new Egn(EgnskortCoordinates.hesseloeUpper, EgnskortCoordinates.hesseloeLower, "0250432", "Hessel\u00f8");
        }
        if (isInBound(EgnskortCoordinates.laesoeUpper, EgnskortCoordinates.laesoeLower, point)) {
            return new Egn(EgnskortCoordinates.laesoeUpper, EgnskortCoordinates.laesoeLower, "0250429", "L\u00e6s\u00f8");
        }
        if (isInBound(EgnskortCoordinates.mandoeUpper, EgnskortCoordinates.mandoeLower, point)) {
            return new Egn(EgnskortCoordinates.mandoeUpper, EgnskortCoordinates.mandoeLower, "0250441", "Mand\u00f8");
        }
        if (isInBound(EgnskortCoordinates.nsUpper, EgnskortCoordinates.nsLower, point)) {
            return new Egn(EgnskortCoordinates.nsUpper, EgnskortCoordinates.nsLower, "0250424", "Nordsj\u00e6lland");
        }
        if (isInBound(EgnskortCoordinates.odenseUpper, EgnskortCoordinates.odenseLower, point)) {
            return new Egn(EgnskortCoordinates.odenseUpper, EgnskortCoordinates.odenseLower, "0250442", "Odense");
        }
        if (isInBound(EgnskortCoordinates.roemoeUpper, EgnskortCoordinates.roemoeLower, point)) {
            return new Egn(EgnskortCoordinates.roemoeUpper, EgnskortCoordinates.roemoeLower, "0250421", "R\u00f8m\u00f8");
        }
        if (isInBound(EgnskortCoordinates.samsoeUpper, EgnskortCoordinates.samsoeLower, point)) {
            return new Egn(EgnskortCoordinates.samsoeUpper, EgnskortCoordinates.samsoeLower, "0250427", "Sams\u00f8");
        }
        if (isInBound(EgnskortCoordinates.sejeroeUpper, EgnskortCoordinates.sejeroeLower, point)) {
            return new Egn(EgnskortCoordinates.sejeroeUpper, EgnskortCoordinates.sejeroeLower, "0250436", "Sejer\u00f8");
        }
        if (isInBound(EgnskortCoordinates.silkeborgUpper, EgnskortCoordinates.silkeborgLower, point)) {
            return new Egn(EgnskortCoordinates.silkeborgUpper, EgnskortCoordinates.silkeborgLower, "0250419", "Silkeborg");
        }
        if (isInBound(EgnskortCoordinates.soUpper, EgnskortCoordinates.soLower, point)) {
            return new Egn(EgnskortCoordinates.soUpper, EgnskortCoordinates.soLower, "0250423", "Sj\u00e6llands Odde");
        }
        if (isInBound(EgnskortCoordinates.skagenUpper, EgnskortCoordinates.skagenLower, point)) {
            return new Egn(EgnskortCoordinates.skagenUpper, EgnskortCoordinates.skagenLower, "0250426", "Skagen");
        }
        if (isInBound(EgnskortCoordinates.taasingeUpper, EgnskortCoordinates.taasingeLower, point)) {
            return new Egn(EgnskortCoordinates.taasingeUpper, EgnskortCoordinates.taasingeLower, "0250420", "T\u00e5singe");
        }
        if (isInBound(EgnskortCoordinates.venoeUpper, EgnskortCoordinates.venoeLower, point)) {
            return new Egn(EgnskortCoordinates.venoeUpper, EgnskortCoordinates.venoeLower, "0250439", "Ven\u00f8");
        }
        if (isInBound(EgnskortCoordinates.aalborgUpper, EgnskortCoordinates.aalborgLower, point)) {
            return new Egn(EgnskortCoordinates.aalborgUpper, EgnskortCoordinates.aalborgLower, "0250444", "Aalborg");
        }
        if (isInBound(EgnskortCoordinates.aarhusUpper, EgnskortCoordinates.aarhusLower, point)) {
            return new Egn(EgnskortCoordinates.aarhusUpper, EgnskortCoordinates.aarhusLower, "0250443", "\u00c5rhus");
        }
        if (isInBound(EgnskortCoordinates.langelandFirstUpper, EgnskortCoordinates.langelandFirstLower, point) || isInBound(EgnskortCoordinates.langelandSecondUpper, EgnskortCoordinates.langelandSecondLower, point) || isInBound(EgnskortCoordinates.langelandThirdUpper, EgnskortCoordinates.langelandThirdLower, point)) {
            return new Egn(EgnskortCoordinates.langelandFirstUpper, EgnskortCoordinates.langelandThirdLower, "0250425", "Langeland");
        }
        if (isInBound(EgnskortCoordinates.aeroFirstUpper, EgnskortCoordinates.aeroFirstLower, point) || isInBound(EgnskortCoordinates.aeroSecondUpper, EgnskortCoordinates.aeroSecondLower, point)) {
            return new Egn(EgnskortCoordinates.aeroFirstUpper, EgnskortCoordinates.aeroSecondLower, "0250422", "\u00c6r\u00f8");
        }
        if (isInBound(EgnskortCoordinates.moenFirstUpper, EgnskortCoordinates.moenFirstLower, point) || isInBound(EgnskortCoordinates.moenSecondUpper, EgnskortCoordinates.moenSecondLower, point)) {
            return new Egn(EgnskortCoordinates.moenFirstUpper, EgnskortCoordinates.moenSecondLower, "0250430", "M\u00f8n");
        }
        if (isInBound(EgnskortCoordinates.fejoeFirstUpper, EgnskortCoordinates.fejoeFirstLower, point) || isInBound(EgnskortCoordinates.fejoeSecondUpper, EgnskortCoordinates.fejoeSecondLower, point)) {
            return new Egn(EgnskortCoordinates.fejoeFirstUpper, EgnskortCoordinates.fejoeSecondLower, "0250435", "Fej\u00f8");
        }
        return null;
    }
    
    private static boolean isInBound(final Point upper, final Point lower, final Point point) {
        return point.x > upper.x && point.x < lower.x && point.y > upper.y && point.y < lower.y;
    }
}
