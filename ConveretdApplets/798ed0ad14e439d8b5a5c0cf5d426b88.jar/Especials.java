import java.util.StringTokenizer;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Especials
{
    public static Hashtable simbols2wiris;
    
    static {
        Especials.simbols2wiris = new Hashtable();
        final StringTokenizer stringTokenizer = new StringTokenizer("\\{#{#\\}#}#\\&#&#\\$#$#\\^#^#\\}#}#\\space# #\\quote#\"#\\rightarrow#->#\\longrightarrow#-->#\\Rightarrow#=>#\\Longrightarrow#==>#\\leftarrow#<-#\\longleftarrow#<--#\\Leftarrow#<=#\\Longleftarrow#<==#\\delayedruletuple#:=>#\\delayedruletuple#:=>#\\mapsto#->#\\longmapsto#-->#\\\\#\\#\\%#%#\\i#&&#\\caret#\\caret#\\Opi# Pi_ #\\Oe# E_ #\\Oi# i_ #\\Oj# j_ #\\infty# infinity #\\pinfty# infinity #\\minfty# -infinity #\\leq# <= #\\geq# >= #\\eq# == #\\neq# != #\\assign# := #\\prima#'#+#+#-#-#*#*#/#/#=#=#:#:#\\espai# #\\beginselection#\\beginselection#\\endselection#\\endselection#\\backslash#\\#\\row#row#\\column#column#\\empty#{}#\\ang# #\\pm# #\\hat#^#\\utri# #\\pminfty# unsigned_infinity #\\cup# union_operator #\\cap# intersection_operator #\\in# belongs_operator #\\notin# not_belongs_operator #\\times# times_cross_operator #\\wedge# wedge_operator #\\vee# vee_operator #\\NN# Natural #\\ZZ# Integer #\\QQ# Rational #\\RR# Real #\\CC# Complex #\\SIamper# ampere #\\SImol# mole #\\SImeter# meter #\\SIgram# gram #\\SIsecond# second #\\SIampere# ampere #\\SIkelvin# kelvin #\\SImole# mole #\\SIcandela# candela #\\SIliter# liter #\\SIradian# radian #\\SIsteradian# steradian #\\SIhertz# hertz #\\SInewton# newton #\\SIpascal# pascal #\\SIjoule# joule #\\SIwatt# watt #\\SIcoulomb# coulomb #\\SIvolt# volt #\\SIfarad# farad #\\SIohm# ohm #\\SIsiemens# siemens #\\SIweber# weber #\\SItesla# tesla #\\SIhenry# henry #\\SIlumen# lumen #\\SIlux# lux #\\SIbecquerel# becquerel #\\SIgray# gray #\\SIsievert# sievert #\\SIkatal# katal #\\SIminute# minute #\\SIhour# hour #\\SIbar# bar #\\SIangledegree# angle_degree #\\SIangleminute# angle_minute #\\SIanglesecond# angle_second ", "#");
        while (stringTokenizer.hasMoreElements()) {
            Especials.simbols2wiris.put(stringTokenizer.nextElement(), stringTokenizer.nextElement());
        }
    }
}
