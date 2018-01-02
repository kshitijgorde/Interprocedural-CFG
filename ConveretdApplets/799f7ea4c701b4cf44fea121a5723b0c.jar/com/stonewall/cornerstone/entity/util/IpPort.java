// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public class IpPort
{
    private static TreeBidiMap protocol;
    private static Map<String, Integer> protocol2;
    private static TreeBidiMap svcPorts;
    private static Map<String, Integer> svcPorts2;
    private static Map<String, icmpType> icmpMapping;
    int port;
    String svcName;
    
    static {
        (IpPort.protocol = new TreeBidiMap()).put((Object)"HOPOPT", (Object)0);
        IpPort.protocol.put((Object)"ICMP", (Object)1);
        IpPort.protocol.put((Object)"IGMP", (Object)2);
        IpPort.protocol.put((Object)"CGP", (Object)3);
        IpPort.protocol.put((Object)"IP", (Object)4);
        IpPort.protocol.put((Object)"ST", (Object)5);
        IpPort.protocol.put((Object)"TCP", (Object)6);
        IpPort.protocol.put((Object)"CBT", (Object)7);
        IpPort.protocol.put((Object)"EGP", (Object)8);
        IpPort.protocol.put((Object)"IGP", (Object)9);
        IpPort.protocol.put((Object)"BBN-RCC-MON", (Object)10);
        IpPort.protocol.put((Object)"NVP-II", (Object)11);
        IpPort.protocol.put((Object)"PUP", (Object)12);
        IpPort.protocol.put((Object)"ARGUS", (Object)13);
        IpPort.protocol.put((Object)"EMCON", (Object)14);
        IpPort.protocol.put((Object)"XNET", (Object)15);
        IpPort.protocol.put((Object)"CHAOS", (Object)16);
        IpPort.protocol.put((Object)"UDP", (Object)17);
        IpPort.protocol.put((Object)"MUX", (Object)18);
        IpPort.protocol.put((Object)"DCN-MEAS", (Object)19);
        IpPort.protocol.put((Object)"HMP", (Object)20);
        IpPort.protocol.put((Object)"PRM", (Object)21);
        IpPort.protocol.put((Object)"XNS-IDP", (Object)22);
        IpPort.protocol.put((Object)"TRUNK-1", (Object)23);
        IpPort.protocol.put((Object)"TRUNK-2", (Object)24);
        IpPort.protocol.put((Object)"LEAF-1", (Object)25);
        IpPort.protocol.put((Object)"LEAF-2", (Object)26);
        IpPort.protocol.put((Object)"RDP", (Object)27);
        IpPort.protocol.put((Object)"IRTP", (Object)28);
        IpPort.protocol.put((Object)"ISO-TP4", (Object)29);
        IpPort.protocol.put((Object)"NETBLT", (Object)30);
        IpPort.protocol.put((Object)"MFE-NSP", (Object)31);
        IpPort.protocol.put((Object)"MERIT-INP", (Object)32);
        IpPort.protocol.put((Object)"DCCP", (Object)33);
        IpPort.protocol.put((Object)"3PC", (Object)34);
        IpPort.protocol.put((Object)"IDPR", (Object)35);
        IpPort.protocol.put((Object)"XTP", (Object)36);
        IpPort.protocol.put((Object)"DDP", (Object)37);
        IpPort.protocol.put((Object)"IDPR-CMTP", (Object)38);
        IpPort.protocol.put((Object)"TP++", (Object)39);
        IpPort.protocol.put((Object)"IL", (Object)40);
        IpPort.protocol.put((Object)"IPv6", (Object)41);
        IpPort.protocol.put((Object)"SDRP", (Object)42);
        IpPort.protocol.put((Object)"IPv6-Route", (Object)43);
        IpPort.protocol.put((Object)"IPv6-Frag", (Object)44);
        IpPort.protocol.put((Object)"IDRP", (Object)45);
        IpPort.protocol.put((Object)"RSVP", (Object)46);
        IpPort.protocol.put((Object)"GRE", (Object)47);
        IpPort.protocol.put((Object)"MHRP", (Object)48);
        IpPort.protocol.put((Object)"BNA", (Object)49);
        IpPort.protocol.put((Object)"ESP", (Object)50);
        IpPort.protocol.put((Object)"AH", (Object)51);
        IpPort.protocol.put((Object)"I-NLSP", (Object)52);
        IpPort.protocol.put((Object)"SWIPE", (Object)53);
        IpPort.protocol.put((Object)"NARP", (Object)54);
        IpPort.protocol.put((Object)"MOBILE", (Object)55);
        IpPort.protocol.put((Object)"TLSP", (Object)56);
        IpPort.protocol.put((Object)"SKIP", (Object)57);
        IpPort.protocol.put((Object)"IPv6-ICMP", (Object)58);
        IpPort.protocol.put((Object)"IPv6-NoNxt", (Object)59);
        IpPort.protocol.put((Object)"IPv6-Opts", (Object)60);
        IpPort.protocol.put((Object)"ANYHOST", (Object)61);
        IpPort.protocol.put((Object)"CFTP", (Object)62);
        IpPort.protocol.put((Object)"LOCAL", (Object)63);
        IpPort.protocol.put((Object)"SAT-EXPAK", (Object)64);
        IpPort.protocol.put((Object)"KRYPTOLAN", (Object)65);
        IpPort.protocol.put((Object)"RVD", (Object)66);
        IpPort.protocol.put((Object)"IPPC", (Object)67);
        IpPort.protocol.put((Object)"DISTFS", (Object)68);
        IpPort.protocol.put((Object)"SAT-MON", (Object)69);
        IpPort.protocol.put((Object)"VISA", (Object)70);
        IpPort.protocol.put((Object)"IPCV", (Object)71);
        IpPort.protocol.put((Object)"CPNX", (Object)72);
        IpPort.protocol.put((Object)"CPHB", (Object)73);
        IpPort.protocol.put((Object)"WSN", (Object)74);
        IpPort.protocol.put((Object)"PVP", (Object)75);
        IpPort.protocol.put((Object)"BR-SAT-MON", (Object)76);
        IpPort.protocol.put((Object)"SUN-ND", (Object)77);
        IpPort.protocol.put((Object)"WB-MON", (Object)78);
        IpPort.protocol.put((Object)"WB-EXPAK", (Object)79);
        IpPort.protocol.put((Object)"ISO-IP", (Object)80);
        IpPort.protocol.put((Object)"VMTP", (Object)81);
        IpPort.protocol.put((Object)"SECURE-VMTP", (Object)82);
        IpPort.protocol.put((Object)"VINES", (Object)83);
        IpPort.protocol.put((Object)"TTP", (Object)84);
        IpPort.protocol.put((Object)"NSFNET-IGP", (Object)85);
        IpPort.protocol.put((Object)"DGP", (Object)86);
        IpPort.protocol.put((Object)"TCF", (Object)87);
        IpPort.protocol.put((Object)"EIGRP", (Object)88);
        IpPort.protocol.put((Object)"OSPFIGP", (Object)89);
        IpPort.protocol.put((Object)"Sprite-RPC", (Object)90);
        IpPort.protocol.put((Object)"LARP", (Object)91);
        IpPort.protocol.put((Object)"MTP", (Object)92);
        IpPort.protocol.put((Object)"AX.25", (Object)93);
        IpPort.protocol.put((Object)"IPIP", (Object)94);
        IpPort.protocol.put((Object)"MICP", (Object)95);
        IpPort.protocol.put((Object)"SCC-SP", (Object)96);
        IpPort.protocol.put((Object)"ETHERIP", (Object)97);
        IpPort.protocol.put((Object)"ENCAP", (Object)98);
        IpPort.protocol.put((Object)"ENCRYPT", (Object)99);
        IpPort.protocol.put((Object)"GMTP", (Object)100);
        IpPort.protocol.put((Object)"IFMP", (Object)101);
        IpPort.protocol.put((Object)"PNNI", (Object)102);
        IpPort.protocol.put((Object)"PIM", (Object)103);
        IpPort.protocol.put((Object)"ARIS", (Object)104);
        IpPort.protocol.put((Object)"SCPS", (Object)105);
        IpPort.protocol.put((Object)"QNX", (Object)106);
        IpPort.protocol.put((Object)"A/N", (Object)107);
        IpPort.protocol.put((Object)"IPComp", (Object)108);
        IpPort.protocol.put((Object)"SNP", (Object)109);
        IpPort.protocol.put((Object)"Compaq-Peer", (Object)110);
        IpPort.protocol.put((Object)"IPX-in-IP", (Object)111);
        IpPort.protocol.put((Object)"VRRP", (Object)112);
        IpPort.protocol.put((Object)"PGM", (Object)113);
        IpPort.protocol.put((Object)"0-HOP", (Object)114);
        IpPort.protocol.put((Object)"L2TP", (Object)115);
        IpPort.protocol.put((Object)"DDX", (Object)116);
        IpPort.protocol.put((Object)"IATP", (Object)117);
        IpPort.protocol.put((Object)"STP", (Object)118);
        IpPort.protocol.put((Object)"SRP", (Object)119);
        IpPort.protocol.put((Object)"UTI", (Object)120);
        IpPort.protocol.put((Object)"SMP", (Object)121);
        IpPort.protocol.put((Object)"SM", (Object)122);
        IpPort.protocol.put((Object)"PTP", (Object)123);
        IpPort.protocol.put((Object)"ISIS", (Object)124);
        IpPort.protocol.put((Object)"FIRE", (Object)125);
        IpPort.protocol.put((Object)"CRTP", (Object)126);
        IpPort.protocol.put((Object)"CRUDP", (Object)127);
        IpPort.protocol.put((Object)"SSCOPMCE", (Object)128);
        IpPort.protocol.put((Object)"IPLT", (Object)129);
        IpPort.protocol.put((Object)"SPS", (Object)130);
        IpPort.protocol.put((Object)"PIPE", (Object)131);
        IpPort.protocol.put((Object)"SCTP", (Object)132);
        IpPort.protocol.put((Object)"FC", (Object)133);
        IpPort.protocol.put((Object)"RSVP-E2E-IGN", (Object)134);
        IpPort.protocol.put((Object)"MHDR", (Object)135);
        IpPort.protocol.put((Object)"UDPLite", (Object)136);
        IpPort.protocol.put((Object)"MPLS-in-IP", (Object)137);
        (IpPort.protocol2 = new HashMap<String, Integer>()).put("GGP", 3);
        IpPort.protocol2.put("SEP", 33);
        IpPort.protocol2.put("IPv6-Flag", 44);
        IpPort.protocol2.put("DFS", 68);
        IpPort.protocol2.put("PRIVATE", 99);
        IpPort.protocol2.put("IPx-inIP", 111);
        IpPort.protocol2.put("0HOP", 114);
        IpPort.protocol2.put("RSVP-E2E-IGNORE", 134);
        (IpPort.svcPorts = new TreeBidiMap()).put((Object)"ANY", (Object)0);
        IpPort.svcPorts.put((Object)"TCPMUX", (Object)1);
        IpPort.svcPorts.put((Object)"RJE", (Object)5);
        IpPort.svcPorts.put((Object)"ECHO", (Object)7);
        IpPort.svcPorts.put((Object)"DISCARD", (Object)9);
        IpPort.svcPorts.put((Object)"SYSTAT", (Object)11);
        IpPort.svcPorts.put((Object)"DAYTIME", (Object)13);
        IpPort.svcPorts.put((Object)"QOTD", (Object)17);
        IpPort.svcPorts.put((Object)"MSP", (Object)18);
        IpPort.svcPorts.put((Object)"CHARGEN", (Object)19);
        IpPort.svcPorts.put((Object)"FTPDATA", (Object)20);
        IpPort.svcPorts.put((Object)"FTP", (Object)21);
        IpPort.svcPorts.put((Object)"SSH", (Object)22);
        IpPort.svcPorts.put((Object)"TELNET", (Object)23);
        IpPort.svcPorts.put((Object)"SMTP", (Object)25);
        IpPort.svcPorts.put((Object)"NSWFE", (Object)27);
        IpPort.svcPorts.put((Object)"MSGICP", (Object)29);
        IpPort.svcPorts.put((Object)"MSGAUTH", (Object)31);
        IpPort.svcPorts.put((Object)"DSP", (Object)33);
        IpPort.svcPorts.put((Object)"TIME", (Object)37);
        IpPort.svcPorts.put((Object)"RAP", (Object)38);
        IpPort.svcPorts.put((Object)"RLP", (Object)39);
        IpPort.svcPorts.put((Object)"GRAPHICS", (Object)41);
        IpPort.svcPorts.put((Object)"NAME", (Object)42);
        IpPort.svcPorts.put((Object)"WHOIS", (Object)43);
        IpPort.svcPorts.put((Object)"MPMFLAGS", (Object)44);
        IpPort.svcPorts.put((Object)"MPM", (Object)45);
        IpPort.svcPorts.put((Object)"MPMSND", (Object)46);
        IpPort.svcPorts.put((Object)"NIFTP", (Object)47);
        IpPort.svcPorts.put((Object)"AUDITD", (Object)48);
        IpPort.svcPorts.put((Object)"TACACS", (Object)49);
        IpPort.svcPorts.put((Object)"REMAILCK", (Object)50);
        IpPort.svcPorts.put((Object)"LEMAINT", (Object)51);
        IpPort.svcPorts.put((Object)"XNSTIME", (Object)52);
        IpPort.svcPorts.put((Object)"DOMAIN", (Object)53);
        IpPort.svcPorts.put((Object)"XNSCH", (Object)54);
        IpPort.svcPorts.put((Object)"ISIGL", (Object)55);
        IpPort.svcPorts.put((Object)"XNSAUTH", (Object)56);
        IpPort.svcPorts.put((Object)"XNSMAIL", (Object)58);
        IpPort.svcPorts.put((Object)"NIMAIL", (Object)61);
        IpPort.svcPorts.put((Object)"ACAS", (Object)62);
        IpPort.svcPorts.put((Object)"WHOIS++", (Object)63);
        IpPort.svcPorts.put((Object)"COVIA", (Object)64);
        IpPort.svcPorts.put((Object)"TACACSDS", (Object)65);
        IpPort.svcPorts.put((Object)"SQL*NET", (Object)66);
        IpPort.svcPorts.put((Object)"BOOTPS", (Object)67);
        IpPort.svcPorts.put((Object)"BOOTPC", (Object)68);
        IpPort.svcPorts.put((Object)"TFTP", (Object)69);
        IpPort.svcPorts.put((Object)"GOPHER", (Object)70);
        IpPort.svcPorts.put((Object)"NETRJS1", (Object)71);
        IpPort.svcPorts.put((Object)"NETRJS2", (Object)72);
        IpPort.svcPorts.put((Object)"NETRJS3", (Object)73);
        IpPort.svcPorts.put((Object)"NETRJS4", (Object)74);
        IpPort.svcPorts.put((Object)"DEOS", (Object)76);
        IpPort.svcPorts.put((Object)"VETTCP", (Object)78);
        IpPort.svcPorts.put((Object)"FINGER", (Object)79);
        IpPort.svcPorts.put((Object)"HTTP", (Object)80);
        IpPort.svcPorts.put((Object)"HOSTNAME", (Object)101);
        IpPort.svcPorts.put((Object)"RTELNET", (Object)107);
        IpPort.svcPorts.put((Object)"SNAGAS", (Object)108);
        IpPort.svcPorts.put((Object)"POP2", (Object)109);
        IpPort.svcPorts.put((Object)"POP3", (Object)110);
        IpPort.svcPorts.put((Object)"SUNRPC", (Object)111);
        IpPort.svcPorts.put((Object)"IDENT", (Object)113);
        IpPort.svcPorts.put((Object)"NNTP", (Object)119);
        IpPort.svcPorts.put((Object)"NTP", (Object)123);
        IpPort.svcPorts.put((Object)"MSRPC", (Object)135);
        IpPort.svcPorts.put((Object)"NBNAME", (Object)137);
        IpPort.svcPorts.put((Object)"NBDATA", (Object)138);
        IpPort.svcPorts.put((Object)"NBSESSION", (Object)139);
        IpPort.svcPorts.put((Object)"IMAP", (Object)143);
        IpPort.svcPorts.put((Object)"SNMP", (Object)161);
        IpPort.svcPorts.put((Object)"SNMPTRAP", (Object)162);
        IpPort.svcPorts.put((Object)"XDMCP", (Object)177);
        IpPort.svcPorts.put((Object)"BGP", (Object)179);
        IpPort.svcPorts.put((Object)"RIS", (Object)180);
        IpPort.svcPorts.put((Object)"UNIFY", (Object)181);
        IpPort.svcPorts.put((Object)"AUDIT", (Object)182);
        IpPort.svcPorts.put((Object)"OCBINDER", (Object)183);
        IpPort.svcPorts.put((Object)"OCSERVER", (Object)184);
        IpPort.svcPorts.put((Object)"REMOTEKIS", (Object)185);
        IpPort.svcPorts.put((Object)"KIS", (Object)186);
        IpPort.svcPorts.put((Object)"ACI", (Object)187);
        IpPort.svcPorts.put((Object)"MUMPS", (Object)188);
        IpPort.svcPorts.put((Object)"QFT", (Object)189);
        IpPort.svcPorts.put((Object)"GACP", (Object)190);
        IpPort.svcPorts.put((Object)"PROSPERO", (Object)191);
        IpPort.svcPorts.put((Object)"OSUNMS", (Object)192);
        IpPort.svcPorts.put((Object)"SRMP", (Object)193);
        IpPort.svcPorts.put((Object)"IRC", (Object)194);
        IpPort.svcPorts.put((Object)"DNSIX", (Object)195);
        IpPort.svcPorts.put((Object)"DLS", (Object)197);
        IpPort.svcPorts.put((Object)"DLSMON", (Object)198);
        IpPort.svcPorts.put((Object)"SMUX", (Object)199);
        IpPort.svcPorts.put((Object)"SRC", (Object)200);
        IpPort.svcPorts.put((Object)"QMTP", (Object)209);
        IpPort.svcPorts.put((Object)"Z39.50", (Object)210);
        IpPort.svcPorts.put((Object)"914CG", (Object)211);
        IpPort.svcPorts.put((Object)"ANET", (Object)212);
        IpPort.svcPorts.put((Object)"IPX", (Object)213);
        IpPort.svcPorts.put((Object)"VPMWSCS", (Object)214);
        IpPort.svcPorts.put((Object)"SOFTPC", (Object)215);
        IpPort.svcPorts.put((Object)"CAILIC", (Object)216);
        IpPort.svcPorts.put((Object)"DBASE", (Object)217);
        IpPort.svcPorts.put((Object)"MPP", (Object)218);
        IpPort.svcPorts.put((Object)"UARPS", (Object)219);
        IpPort.svcPorts.put((Object)"IMAP3", (Object)220);
        IpPort.svcPorts.put((Object)"FLNSPX", (Object)221);
        IpPort.svcPorts.put((Object)"RSHSPX", (Object)222);
        IpPort.svcPorts.put((Object)"CDC", (Object)223);
        IpPort.svcPorts.put((Object)"LDAP", (Object)389);
        IpPort.svcPorts.put((Object)"MOBILE-IP", (Object)434);
        IpPort.svcPorts.put((Object)"HTTPS", (Object)443);
        IpPort.svcPorts.put((Object)"PIM-AUTO-RP", (Object)496);
        IpPort.svcPorts.put((Object)"IKE", (Object)500);
        IpPort.svcPorts.put((Object)"EXEC", (Object)512);
        IpPort.svcPorts.put((Object)"LOGIN", (Object)513);
        IpPort.svcPorts.put((Object)"SYSLOG", (Object)514);
        IpPort.svcPorts.put((Object)"LPD", (Object)515);
        IpPort.svcPorts.put((Object)"TALK", (Object)517);
        IpPort.svcPorts.put((Object)"RIP", (Object)520);
        IpPort.svcPorts.put((Object)"UUCP", (Object)540);
        IpPort.svcPorts.put((Object)"KLOGIN", (Object)543);
        IpPort.svcPorts.put((Object)"KSHELL", (Object)544);
        IpPort.svcPorts.put((Object)"LDAPS", (Object)636);
        IpPort.svcPorts.put((Object)"KERBEROS", (Object)750);
        IpPort.svcPorts.put((Object)"FTPSDATA", (Object)989);
        IpPort.svcPorts.put((Object)"FTPS", (Object)990);
        IpPort.svcPorts.put((Object)"NAS", (Object)991);
        IpPort.svcPorts.put((Object)"TELNETS", (Object)992);
        IpPort.svcPorts.put((Object)"IMAPS", (Object)993);
        IpPort.svcPorts.put((Object)"IRCS", (Object)994);
        IpPort.svcPorts.put((Object)"POP3S", (Object)995);
        IpPort.svcPorts.put((Object)"VSINET", (Object)996);
        IpPort.svcPorts.put((Object)"MAITRD", (Object)997);
        IpPort.svcPorts.put((Object)"PUPARP", (Object)998);
        IpPort.svcPorts.put((Object)"APPLIX", (Object)999);
        IpPort.svcPorts.put((Object)"CADLOCK2", (Object)1000);
        IpPort.svcPorts.put((Object)"SURF", (Object)1010);
        IpPort.svcPorts.put((Object)"LOTUSNOTES", (Object)1352);
        IpPort.svcPorts.put((Object)"CITRIX-ICA", (Object)1494);
        IpPort.svcPorts.put((Object)"SQLNET", (Object)1521);
        IpPort.svcPorts.put((Object)"STREAMWORKS", (Object)1558);
        IpPort.svcPorts.put((Object)"RADIUS", (Object)1645);
        IpPort.svcPorts.put((Object)"RADIUS-ACCT", (Object)1646);
        IpPort.svcPorts.put((Object)"L2TP", (Object)1701);
        IpPort.svcPorts.put((Object)"H323", (Object)1720);
        IpPort.svcPorts.put((Object)"PPTP", (Object)1723);
        IpPort.svcPorts.put((Object)"NETSHOW", (Object)1755);
        IpPort.svcPorts.put((Object)"HELLO", (Object)1789);
        IpPort.svcPorts.put((Object)"MGCP", (Object)2427);
        IpPort.svcPorts.put((Object)"CTIQBE", (Object)2748);
        IpPort.svcPorts.put((Object)"SIP", (Object)5060);
        IpPort.svcPorts.put((Object)"AOL", (Object)5190);
        IpPort.svcPorts.put((Object)"SECUREID", (Object)5510);
        IpPort.svcPorts.put((Object)"PCANYWHERE-DATA", (Object)5631);
        IpPort.svcPorts.put((Object)"PCANYWHERE-STATUS", (Object)5632);
        IpPort.svcPorts.put((Object)"VDOLIVE", (Object)7000);
        IpPort.svcPorts.put((Object)"REAL", (Object)7070);
        IpPort.svcPorts.put((Object)"CUSEEME", (Object)7648);
        IpPort.svcPorts.put((Object)"RTSP", (Object)8559);
        (IpPort.svcPorts2 = new HashMap<String, Integer>()).put("*", 0);
        IpPort.svcPorts2.put("FTP-DATA", 20);
        IpPort.svcPorts2.put("FTP-CTRL", 21);
        IpPort.svcPorts2.put("SMTPS", 25);
        IpPort.svcPorts2.put("NAMESERVER", 42);
        IpPort.svcPorts2.put("NICNAME", 43);
        IpPort.svcPorts2.put("DNS", 53);
        IpPort.svcPorts2.put("WWW", 80);
        IpPort.svcPorts2.put("NETBIOS-NS", 137);
        IpPort.svcPorts2.put("NBDATAGRAM", 138);
        IpPort.svcPorts2.put("NETBIOS-DGM", 138);
        IpPort.svcPorts2.put("NETBIOS-SSN", 139);
        IpPort.svcPorts2.put("IMAP4", 143);
        IpPort.svcPorts2.put("SNMP-TRAP", 162);
        IpPort.svcPorts2.put("SNMP_TRAP", 162);
        IpPort.svcPorts2.put("ISAKMP", 500);
        IpPort.svcPorts2.put("BIFF", 512);
        IpPort.svcPorts2.put("WHOD", 513);
        IpPort.svcPorts2.put("WHO", 513);
        IpPort.svcPorts2.put("CMD", 514);
        IpPort.svcPorts2.put("SHELL", 514);
        IpPort.svcPorts2.put("SECUREID-UDP", 5510);
        IpPort.svcPorts2.put("REAL-AUDIO-VIDEO", 7070);
        IpPort.svcPorts2.put("REALAUDIO", 7070);
        IpPort.svcPorts2.put("REALVIDEO", 7070);
        IpPort.svcPorts2.put("REAL-AUDIO", 7070);
        IpPort.svcPorts2.put("REAL-VIDEO", 7070);
        (IpPort.icmpMapping = new HashMap<String, icmpType>()).put("ECHOREPLY", icmpType.ECHOREPLY);
        IpPort.icmpMapping.put("UNREACHABLE", icmpType.UNREACHABLE);
        IpPort.icmpMapping.put("DSTUNREACHABLE", icmpType.UNREACHABLE);
        IpPort.icmpMapping.put("DESTINATIONUNREACHABLE", icmpType.UNREACHABLE);
        IpPort.icmpMapping.put("SOURCEQUENCH", icmpType.SOURCEQUENCH);
        IpPort.icmpMapping.put("SRCQUENCH", icmpType.SOURCEQUENCH);
        IpPort.icmpMapping.put("REDIRECT", icmpType.REDIRECT);
        IpPort.icmpMapping.put("ALTADDRESS", icmpType.ALTADDRESS);
        IpPort.icmpMapping.put("ALTERNATEADDRESS", icmpType.ALTADDRESS);
        IpPort.icmpMapping.put("ALTERNATEHOSTADDRESS", icmpType.ALTADDRESS);
        IpPort.icmpMapping.put("ECHO", icmpType.ECHOREQUEST);
        IpPort.icmpMapping.put("ECHOREQUEST", icmpType.ECHOREQUEST);
        IpPort.icmpMapping.put("ROUTERADV", icmpType.ROUTERADV);
        IpPort.icmpMapping.put("ROUTERADVERTISEMENT", icmpType.ROUTERADV);
        IpPort.icmpMapping.put("ROUTER-ADVERTISEMENT", icmpType.ROUTERADV);
        IpPort.icmpMapping.put("ROUTERSOLIC", icmpType.ROUTERSOLIC);
        IpPort.icmpMapping.put("ROUTERSOLICITATION", icmpType.ROUTERSOLIC);
        IpPort.icmpMapping.put("TIMEEXCEEDED", icmpType.TIMEEXCEEDED);
        IpPort.icmpMapping.put("PARAMETER", icmpType.PARAMETER);
        IpPort.icmpMapping.put("PARAMETERPROBLEM", icmpType.PARAMETER);
        IpPort.icmpMapping.put("TIMESTAMP", icmpType.TIMEREQUEST);
        IpPort.icmpMapping.put("TIMESTAMPREQUEST", icmpType.TIMEREQUEST);
        IpPort.icmpMapping.put("TIMEREQUEST", icmpType.TIMEREQUEST);
        IpPort.icmpMapping.put("TIMESTAMPREPLY", icmpType.TIMEREPLY);
        IpPort.icmpMapping.put("TIMEREPLY", icmpType.TIMEREPLY);
        IpPort.icmpMapping.put("INFOREQUEST", icmpType.INFOREQUEST);
        IpPort.icmpMapping.put("INFORMATIONREQUEST", icmpType.INFOREQUEST);
        IpPort.icmpMapping.put("MASKREQUEST", icmpType.MASKREQUEST);
        IpPort.icmpMapping.put("ADDRMASKREQUEST", icmpType.MASKREQUEST);
        IpPort.icmpMapping.put("ADDRESSMASKREQUEST", icmpType.MASKREQUEST);
        IpPort.icmpMapping.put("TRACEROUTE", icmpType.TRACEROUTE);
        IpPort.icmpMapping.put("CONVERSION", icmpType.CONVERSION);
        IpPort.icmpMapping.put("CONVERSIONERROR", icmpType.CONVERSION);
        IpPort.icmpMapping.put("DATAGRAMCONVERSIONERROR", icmpType.CONVERSION);
        IpPort.icmpMapping.put("MOBILEHOST", icmpType.MOBILEHOST);
        IpPort.icmpMapping.put("MOBILEHOSTREDIRECT", icmpType.MOBILEHOST);
        IpPort.icmpMapping.put("MOBILEREDIRECT", icmpType.MOBILEHOST);
        IpPort.icmpMapping.put("IPV6WHERERU", icmpType.IPV6WHERERU);
        IpPort.icmpMapping.put("IPV6WHEREAREYOU", icmpType.IPV6WHERERU);
        IpPort.icmpMapping.put("IPV6IAMHERE", icmpType.IPV6IAMHERE);
        IpPort.icmpMapping.put("MOBILEREQUEST", icmpType.MOBILEREQUEST);
        IpPort.icmpMapping.put("MOBILEREGISTRATIONREQUEST", icmpType.MOBILEREQUEST);
        IpPort.icmpMapping.put("MOBILEREPLY", icmpType.MOBILEREPLY);
        IpPort.icmpMapping.put("MOBILEREGISTRATIONREPLY", icmpType.MOBILEREPLY);
        IpPort.icmpMapping.put("NAMEREQUEST", icmpType.NAMEREQUEST);
        IpPort.icmpMapping.put("DOMAINNAMEREQUEST", icmpType.NAMEREQUEST);
        IpPort.icmpMapping.put("NAMEREPLY", icmpType.NAMEREPLY);
        IpPort.icmpMapping.put("DOMAINNAMEREPLY", icmpType.NAMEREPLY);
        IpPort.icmpMapping.put("SKIP", icmpType.SKIP);
        IpPort.icmpMapping.put("PHOTURIS", icmpType.PHOTURIS);
    }
    
    public IpPort(final int port) throws Exception {
        this.port = 0;
        this.svcName = "";
        if (port < 0 || port > 65535) {
            throw new Exception("Invalid Port Number");
        }
        this.port = port;
    }
    
    public static String getProtocolName(final int port) {
        return (String)IpPort.protocol.getKey((Object)port);
    }
    
    public static int getProtocolPort(String name) {
        name = name.toUpperCase();
        name = name.replace(" ", "");
        if (name.length() == 0) {
            return -1;
        }
        if (name.equals("ANY")) {
            return -1;
        }
        if (IpPort.protocol.containsKey((Object)name)) {
            return (int)IpPort.protocol.get((Object)name.toUpperCase());
        }
        if (IpPort.protocol2.containsKey(name)) {
            return IpPort.protocol2.get(name.toUpperCase());
        }
        name = name.replace("-", "");
        name = name.replace("/", "");
        if (IpPort.protocol.containsKey((Object)name)) {
            return (int)IpPort.protocol.get((Object)name.toUpperCase());
        }
        if (IpPort.protocol2.containsKey(name)) {
            return IpPort.protocol2.get(name.toUpperCase());
        }
        return -1;
    }
    
    public static String getServiceName(final int port) {
        return (String)IpPort.svcPorts.getKey((Object)port);
    }
    
    public static int getServicePort(String name) {
        name = name.toUpperCase();
        name = name.replace(" ", "");
        if (name.length() == 0) {
            return 0;
        }
        if (IpPort.svcPorts.containsKey((Object)name)) {
            return (int)IpPort.svcPorts.get((Object)name);
        }
        if (IpPort.svcPorts2.containsKey(name)) {
            return IpPort.svcPorts2.get(name);
        }
        name = name.replace("-", "");
        name = name.replace("/", "");
        if (IpPort.svcPorts.containsKey((Object)name)) {
            return (int)IpPort.svcPorts.get((Object)name);
        }
        if (IpPort.svcPorts2.containsKey(name)) {
            return IpPort.svcPorts2.get(name);
        }
        return -1;
    }
    
    public static int getIcmpType(String name) {
        name = name.toUpperCase();
        name = name.replace(" ", "");
        name = name.replace("-", "");
        name = name.replace("/", "");
        if (IpPort.icmpMapping.containsKey(name)) {
            return IpPort.icmpMapping.get(name).type();
        }
        return -1;
    }
    
    public static String[] getProtocols() {
        final ArrayList<String> protocols = new ArrayList<String>();
        protocols.addAll(IpPort.protocol.keySet());
        Collections.sort(protocols);
        return protocols.toArray(new String[0]);
    }
    
    public static String[] getServices() {
        final ArrayList<String> services = new ArrayList<String>();
        services.addAll(IpPort.svcPorts.keySet());
        services.addAll(IpPort.svcPorts2.keySet());
        Collections.sort(services);
        return services.toArray(new String[0]);
    }
    
    public static void main(final String[] args) {
        System.out.println(getServiceName(80));
        System.out.println(getServicePort("HTTP"));
        try {
            final String svc = svcRange.valueOf("ANY").name();
            System.out.println(svc);
        }
        catch (IllegalArgumentException iaEx) {
            iaEx.printStackTrace();
        }
        System.out.println(svcRange.valueOf("ANY").srcRange());
        System.out.println(svcRange.valueOf("ANY").dstRange());
    }
    
    public enum icmpType
    {
        ECHOREPLY("ECHOREPLY", 0, 0), 
        UNREACHABLE("UNREACHABLE", 1, 3), 
        SOURCEQUENCH("SOURCEQUENCH", 2, 4), 
        REDIRECT("REDIRECT", 3, 5), 
        ALTADDRESS("ALTADDRESS", 4, 6), 
        ECHOREQUEST("ECHOREQUEST", 5, 8), 
        ROUTERADV("ROUTERADV", 6, 9), 
        ROUTERSOLIC("ROUTERSOLIC", 7, 10), 
        TIMEEXCEEDED("TIMEEXCEEDED", 8, 11), 
        PARAMETER("PARAMETER", 9, 12), 
        TIMEREQUEST("TIMEREQUEST", 10, 13), 
        TIMEREPLY("TIMEREPLY", 11, 14), 
        INFOREQUEST("INFOREQUEST", 12, 15), 
        INFOREPLY("INFOREPLY", 13, 16), 
        MASKREQUEST("MASKREQUEST", 14, 17), 
        MASKREPLY("MASKREPLY", 15, 18), 
        TRACEROUTE("TRACEROUTE", 16, 30), 
        CONVERSION("CONVERSION", 17, 31), 
        MOBILEHOST("MOBILEHOST", 18, 32), 
        IPV6WHERERU("IPV6WHERERU", 19, 33), 
        IPV6IAMHERE("IPV6IAMHERE", 20, 34), 
        MOBILEREQUEST("MOBILEREQUEST", 21, 35), 
        MOBILEREPLY("MOBILEREPLY", 22, 36), 
        NAMEREQUEST("NAMEREQUEST", 23, 37), 
        NAMEREPLY("NAMEREPLY", 24, 38), 
        SKIP("SKIP", 25, 39), 
        PHOTURIS("PHOTURIS", 26, 40);
        
        private final int type;
        
        private icmpType(final String s, final int n, final int type) {
            this.type = type;
        }
        
        public int type() {
            return this.type;
        }
    }
    
    public enum svcRange
    {
        ANY("ANY", 0, "0", "0-65535", "0-65535"), 
        AOL("AOL", 1, "6", "0-65535", "5190-5194"), 
        BGP("BGP", 2, "6", "0-65535", "179-179"), 
        DNS("DNS", 3, "17", "0-65535", "53-53"), 
        FINGER("FINGER", 4, "6", "0-65535", "79-79"), 
        FTP("FTP", 5, "6", "0-65535", "21-21"), 
        GOPHER("GOPHER", 6, "7", "0-65535", "70-70"), 
        H323("H323", 7, "6", "0-65535", "1720-1720"), 
        HTTP("HTTP", 8, "6", "0-65535", "80-80"), 
        HTTPS("HTTPS", 9, "6", "0-65535", "443-443"), 
        IKE("IKE", 10, "17", "0-65535", "500-500"), 
        IMAP("IMAP", 11, "6", "0-65535", "143-143"), 
        IRC("IRC", 12, "6", "0-65535", "6660-6669"), 
        L2TP("L2TP", 13, "17", "0-65535", "1701-1701"), 
        LDAP("LDAP", 14, "6", "0-65535", "389-389"), 
        MAIL("MAIL", 15, "6", "0-65535", "25-25"), 
        NETMEET("NETMEET", 16, "6", "0-65535", "1720-1720"), 
        NETMEETING("NETMEETING", 17, "6", "0-65535", "1720-1720"), 
        NFS("NFS", 18, "17", "0-65535", "111-111"), 
        NNTP("NNTP", 19, "6", "0-65535", "119-119"), 
        NSGLOBAL("NSGLOBAL", 20, "6", "0-65535", "15397-15397"), 
        NSM("NSM", 21, "17", "0-65535", "69-69"), 
        NTP("NTP", 22, "17", "0-65535", "123-123"), 
        OSPF("OSPF", 23, "89", "0-65535", "0-65535"), 
        PING("PING", 24, "1", "0-65535", "0-65535"), 
        POP3("POP3", 25, "6", "0-65535", "110-110"), 
        PPTP("PPTP", 26, "6", "0-65535", "1723-1723"), 
        REALMEDIA("REALMEDIA", 27, "6", "0-65535", "7070-7070"), 
        RIP("RIP", 28, "17", "0-65535", "520-520"), 
        RLOGIN("RLOGIN", 29, "6", "0-65535", "513-513"), 
        RSH("RSH", 30, "6", "0-65535", "514-514"), 
        SIP("SIP", 31, "17", "0-65535", "5060-5060"), 
        SMTP("SMTP", 32, "6", "0-65535", "25-25"), 
        SNMP("SNMP", 33, "17", "0-65535", "161-161"), 
        SQLNET1("SQLNET1", 34, "6", "0-65535", "1525-1525"), 
        SQLNET2("SQLNET2", 35, "6", "0-65535", "1521-1521"), 
        SQLNETV1("SQLNETV1", 36, "6", "0-65535", "1525-1525"), 
        SQLNETV2("SQLNETV2", 37, "6", "0-65535", "1521-1521"), 
        SSH("SSH", 38, "6", "0-65535", "22-22"), 
        SUNRPC("SUNRPC", 39, "17", "0-65535", "111-111"), 
        SYSLOG("SYSLOG", 40, "17", "0-65535", "514-514"), 
        TALK("TALK", 41, "17", "0-65535", "517-518"), 
        TCPANY("TCPANY", 42, "6", "0-65535", "0-65535"), 
        TELNET("TELNET", 43, "6", "0-65535", "22-22"), 
        TFTP("TFTP", 44, "17", "0-65535", "69-69"), 
        TRACEROUTE("TRACEROUTE", 45, "1", "0-65535", "0-65535"), 
        UDPANY("UDPANY", 46, "17", "0-65535", "0-65535"), 
        UUCP("UUCP", 47, "17", "0-65535", "540-540"), 
        VDOLIFE("VDOLIFE", 48, "6", "0-65535", "7000-7010"), 
        WAIS("WAIS", 49, "6", "0-65535", "210-210"), 
        WINFRAME("WINFRAME", 50, "6", "0-65535", "1494-1494"), 
        XWINDOWS("XWINDOWS", 51, "6", "0-65535", "6000-6063");
        
        private final String protocol;
        private final String srcRange;
        private final String dstRange;
        
        private svcRange(final String s, final int n, final String protocol, final String srcRange, final String dstRange) {
            this.protocol = protocol;
            this.srcRange = srcRange;
            this.dstRange = dstRange;
        }
        
        public String protocol() {
            return this.protocol;
        }
        
        public String srcRange() {
            return this.srcRange;
        }
        
        public String dstRange() {
            return this.dstRange;
        }
    }
}
