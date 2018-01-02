// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.crypto.SignatureVerifier;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.Locale;
import anon.terms.TermsAndConditionsMixInfo;
import anon.infoservice.ServiceOperator;
import anon.terms.TermsAndConditionsResponseHandler;
import anon.crypto.XMLSignature;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import anon.crypto.XMLEncryption;
import anon.util.Base64;
import anon.client.crypto.KeyPool;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.text.ParseException;
import anon.terms.template.TermsAndConditionsTemplate;
import anon.util.JAPMessages;
import anon.terms.TermsAndConditions;
import anon.client.crypto.IASymMixCipher;
import anon.client.crypto.ASymMixCipherPlainRSA;
import anon.client.crypto.ASymMixCipherRSAOAEP;
import logging.LogHolder;
import logging.LogType;
import anon.util.XMLParseException;
import java.security.SignatureException;
import anon.infoservice.MixInfo;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.Database;
import anon.util.XMLUtil;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import anon.terms.TermsAndConditionsReadException;
import anon.terms.TermsAndConditionsRequest;
import anon.infoservice.MixCascade;
import anon.client.crypto.ControlChannelCipher;
import anon.client.crypto.SymCipher;

public class KeyExchangeManager
{
    private int m_mixCascadeCertificateLock;
    private Object m_internalSynchronization;
    private boolean m_protocolWithTimestamp;
    private boolean m_protocolWithReplay;
    private boolean m_paymentRequired;
    private boolean m_bEnhancedChannelEncryption;
    private SymCipher m_firstMixSymmetricCipher;
    private ControlChannelCipher m_controlchannelCipher;
    private boolean m_chainProtocolWithFlowControl;
    private int m_upstreamSendMe;
    private int m_downstreamSendMe;
    private FixedRatioChannelsDescription m_fixedRatioChannelsDescription;
    private MixParameters[] m_mixParameters;
    private SymCipher m_multiplexerInputStreamCipher;
    private SymCipher m_multiplexerOutputStreamCipher;
    private MixCascade m_cascade;
    private TermsAndConditionsRequest m_tnCRequest;
    private TermsAndConditionsReadException tcrException;
    private boolean tcOverallAccept;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$MixInfo;
    
    public KeyExchangeManager(final InputStream inputStream, final OutputStream outputStream, final MixCascade mixCascade, final ITrustModel trustModel) throws XMLParseException, SignatureException, IOException, UnknownProtocolVersionException, TrustException, TermsAndConditionsReadException, IllegalTCRequestPostConditionException {
        this.tcrException = null;
        this.tcOverallAccept = true;
        try {
            this.m_mixCascadeCertificateLock = -1;
            this.m_internalSynchronization = new Object();
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            int i = dataInputStream.readUnsignedShort();
            final byte[] array = new byte[i];
            while (i > 0) {
                final int read = inputStream.read(array, array.length - i, i);
                if (read == -1) {
                    throw new EOFException("EOF detected while reading initial XML structure.");
                }
                i -= read;
            }
            this.m_cascade = new MixCascade(XMLUtil.toXMLDocument(array).getDocumentElement(), Long.MAX_VALUE, mixCascade.getId());
            TrustException ex = null;
            SignatureException ex2 = null;
            if (mixCascade.isUserDefined()) {
                this.m_cascade.setUserDefined(true, mixCascade);
                Database.getInstance((KeyExchangeManager.class$anon$infoservice$MixCascade == null) ? (KeyExchangeManager.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : KeyExchangeManager.class$anon$infoservice$MixCascade).remove(this.m_cascade);
                Database.getInstance((KeyExchangeManager.class$anon$infoservice$MixCascade == null) ? (KeyExchangeManager.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : KeyExchangeManager.class$anon$infoservice$MixCascade).update(this.m_cascade);
                Database.getInstance((KeyExchangeManager.class$anon$infoservice$MixInfo == null) ? (KeyExchangeManager.class$anon$infoservice$MixInfo = class$("anon.infoservice.MixInfo")) : KeyExchangeManager.class$anon$infoservice$MixInfo).update(new MixInfo(this.m_cascade.getCertPath()));
                trustModel.checkTrust(this.m_cascade);
            }
            else {
                final MixCascade mixCascade2 = (MixCascade)Database.getInstance((KeyExchangeManager.class$anon$infoservice$MixCascade == null) ? (KeyExchangeManager.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : KeyExchangeManager.class$anon$infoservice$MixCascade).getEntryById(this.m_cascade.getId());
                if (mixCascade2 != null) {
                    if (!this.m_cascade.compareMixIDs(mixCascade2)) {
                        Database.getInstance((KeyExchangeManager.class$anon$infoservice$MixCascade == null) ? (KeyExchangeManager.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : KeyExchangeManager.class$anon$infoservice$MixCascade).remove(mixCascade2);
                    }
                    int n = 0;
                    int n2 = 0;
                    try {
                        trustModel.checkTrust(this.m_cascade);
                        n = 1;
                    }
                    catch (TrustException ex3) {
                        ex = ex3;
                    }
                    catch (SignatureException ex4) {
                        ex2 = ex4;
                    }
                    try {
                        trustModel.checkTrust(mixCascade2);
                        n2 = 1;
                    }
                    catch (TrustException ex6) {}
                    catch (SignatureException ex7) {}
                    if (n != n2) {
                        Database.getInstance((KeyExchangeManager.class$anon$infoservice$MixCascade == null) ? (KeyExchangeManager.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : KeyExchangeManager.class$anon$infoservice$MixCascade).remove(mixCascade2);
                    }
                }
            }
            if (ex != null) {
                throw ex;
            }
            if (ex2 != null) {
                throw ex2;
            }
            if (this.m_cascade.getMixProtocolVersion() == null) {
                throw new XMLParseException("##__null__##", "MixProtocolVersion (channel) node expected in received XML structure.");
            }
            this.m_protocolWithTimestamp = false;
            this.m_protocolWithReplay = false;
            this.m_bEnhancedChannelEncryption = false;
            this.m_paymentRequired = this.m_cascade.isPayment();
            if (!this.m_cascade.isPaymentProtocolSupported()) {
                throw new UnknownProtocolVersionException("Unsupported payment protocol version ('" + this.m_cascade.getPaymentProtocolVersion() + "').");
            }
            this.m_firstMixSymmetricCipher = null;
            this.m_controlchannelCipher = null;
            LogHolder.log(7, LogType.NET, "Cascade is using channel-protocol version '" + this.m_cascade.getMixProtocolVersion() + "'.");
            if (!this.m_cascade.getMixProtocolVersion().equals("0.2")) {
                if (this.m_cascade.getMixProtocolVersion().equals("0.4")) {
                    this.m_firstMixSymmetricCipher = new SymCipher();
                }
                else if (this.m_cascade.getMixProtocolVersion().equals("0.81")) {
                    this.m_protocolWithTimestamp = false;
                    this.m_protocolWithReplay = true;
                    this.m_firstMixSymmetricCipher = new SymCipher();
                }
                else if (this.m_cascade.getMixProtocolVersion().equalsIgnoreCase("0.9")) {
                    this.m_firstMixSymmetricCipher = new SymCipher();
                }
                else {
                    if (!this.m_cascade.getMixProtocolVersion().equalsIgnoreCase("0.10")) {
                        throw new UnknownProtocolVersionException("Unknown channel protocol version used ('" + this.m_cascade.getMixProtocolVersion() + "').");
                    }
                    this.m_firstMixSymmetricCipher = new SymCipher();
                    this.m_bEnhancedChannelEncryption = true;
                }
            }
            this.m_mixParameters = new MixParameters[this.m_cascade.getNumberOfMixes()];
            this.m_tnCRequest = new TermsAndConditionsRequest();
            for (int j = 0; j < this.m_cascade.getNumberOfMixes(); ++j) {
                final MixInfo mixInfo = this.m_cascade.getMixInfo(j);
                if (mixInfo == null) {
                    throw new XMLParseException("Could not get MixInfo object for Mix " + j + "!");
                }
                if (j > 0 && !mixInfo.isVerified()) {
                    throw new SignatureException("Received XML structure has an invalid signature for Mix " + Integer.toString(j + 1) + ".");
                }
                final Element xmlStructure = mixInfo.getXmlStructure();
                ASymMixCipherPlainRSA aSymMixCipherPlainRSA;
                if (this.m_bEnhancedChannelEncryption) {
                    aSymMixCipherPlainRSA = new ASymMixCipherRSAOAEP();
                }
                else {
                    aSymMixCipherPlainRSA = new ASymMixCipherPlainRSA();
                }
                this.m_mixParameters[j] = new MixParameters(mixInfo.getId(), aSymMixCipherPlainRSA);
                if (this.m_mixParameters[j].getMixCipher().setPublicKey(xmlStructure) != 0) {
                    throw new XMLParseException("Received XML structure contains an invalid public key for Mix " + Integer.toString(j) + ".");
                }
                if (this.m_cascade.isTermsAndConditionsConfirmationRequired()) {
                    final ServiceOperator serviceOperator = mixInfo.getServiceOperator();
                    final TermsAndConditionsMixInfo termsAndConditionMixInfo = mixInfo.getTermsAndConditionMixInfo();
                    if (termsAndConditionMixInfo != null) {
                        try {
                            TermsAndConditions termsAndConditions = TermsAndConditions.getTermsAndConditions(serviceOperator);
                            if (termsAndConditions == null || !termsAndConditions.isMostRecent(termsAndConditionMixInfo.getDate()) || termsAndConditions.isSignatureObsolete()) {
                                int n3 = 0;
                                if (termsAndConditions != null) {
                                    TermsAndConditions.removeTermsAndConditions(termsAndConditions);
                                    n3 = ((termsAndConditions.isSignatureObsolete() && termsAndConditions.isAccepted()) ? 1 : 0);
                                }
                                termsAndConditions = new TermsAndConditions(serviceOperator, termsAndConditionMixInfo.getDate());
                                if (n3 == 0) {
                                    if (this.tcrException == null) {
                                        this.tcrException = new TermsAndConditionsReadException();
                                    }
                                    this.tcrException.addTermsAndConditonsToRead(termsAndConditions);
                                }
                                else {
                                    termsAndConditions.setAccepted(true);
                                }
                                TermsAndConditions.storeTermsAndConditions(termsAndConditions);
                            }
                            else if (!termsAndConditions.isAccepted()) {
                                if (this.tcrException == null) {
                                    this.tcrException = new TermsAndConditionsReadException();
                                }
                                this.tcrException.addTermsAndConditonsToRead(termsAndConditions);
                            }
                            final Locale locale = JAPMessages.getLocale();
                            final String s = termsAndConditionMixInfo.hasTranslation(locale) ? locale.getLanguage().trim().toLowerCase() : termsAndConditionMixInfo.getDefaultLanguage();
                            if (!s.equals(termsAndConditionMixInfo.getDefaultLanguage()) && !termsAndConditions.hasDefaultTranslation()) {
                                this.m_tnCRequest.addCustomizedSectionsRequest(serviceOperator, termsAndConditionMixInfo.getDefaultLanguage());
                                if (TermsAndConditionsTemplate.getById(termsAndConditionMixInfo.getDefaultTemplateRefId(), false) == null) {
                                    this.m_tnCRequest.addTemplateRequest(serviceOperator, termsAndConditionMixInfo.getDefaultLanguage(), termsAndConditionMixInfo.getDefaultTemplateRefId());
                                }
                            }
                            final String templateRefId = termsAndConditionMixInfo.getTemplateRefId(s);
                            if (TermsAndConditionsTemplate.getById(templateRefId, false) == null) {
                                this.m_tnCRequest.addTemplateRequest(serviceOperator, s, templateRefId);
                            }
                            if (!termsAndConditions.hasTranslation(s)) {
                                this.m_tnCRequest.addCustomizedSectionsRequest(serviceOperator, s);
                            }
                        }
                        catch (ParseException ex8) {
                            LogHolder.log(3, LogType.NET, "tc mix info " + termsAndConditionMixInfo.getId() + " has an invalid date format: " + termsAndConditionMixInfo.getDate());
                        }
                    }
                    else {
                        LogHolder.log(4, LogType.NET, "Cascade requires Terms And Conditions confirmation but Mix " + mixInfo.getName() + " does not send any TC Infos!");
                    }
                }
                if (j == this.m_cascade.getNumberOfMixes() - 1) {
                    final NodeList elementsByTagName = xmlStructure.getElementsByTagName("MixProtocolVersion");
                    if (elementsByTagName.getLength() == 0) {
                        throw new XMLParseException("##__null__##", "MixProtocolVersion (chain) node expected in received XML structure.");
                    }
                    final String value = XMLUtil.parseValue(elementsByTagName.item(0), null);
                    if (value == null) {
                        throw new XMLParseException("##__null__##", "MixProtocolVersion (chain) node has no value.");
                    }
                    final String trim = value.trim();
                    this.m_chainProtocolWithFlowControl = false;
                    this.m_fixedRatioChannelsDescription = null;
                    LogHolder.log(7, LogType.NET, "Cascade is using chain-protocol version '" + trim + "'.");
                    if (!trim.equals("0.3")) {
                        if (trim.equals("0.6")) {
                            this.m_chainProtocolWithFlowControl = true;
                            final Node firstChildByName = XMLUtil.getFirstChildByName(xmlStructure, "FlowControl");
                            if (firstChildByName == null) {
                                throw new XMLParseException("##__null__##", "FlowControl node expected in received XML structure.");
                            }
                            final Node firstChildByName2 = XMLUtil.getFirstChildByName(firstChildByName, "UpstreamSendMe");
                            if (firstChildByName2 == null) {
                                throw new XMLParseException("##__null__##", "UpstreamSendMe node expected in received XML structure.");
                            }
                            final Node firstChildByName3 = XMLUtil.getFirstChildByName(firstChildByName, "DownstreamSendMe");
                            if (firstChildByName3 == null) {
                                throw new XMLParseException("##__null__##", "DownstreamSendMe node expected in received XML structure.");
                            }
                            this.m_upstreamSendMe = XMLUtil.parseValue(firstChildByName2, -1);
                            if (this.m_upstreamSendMe <= 0) {
                                throw new XMLParseException("##__null__##", "Got wrong value for UpstreamSendMe in received XML structure.");
                            }
                            this.m_downstreamSendMe = XMLUtil.parseValue(firstChildByName3, -1);
                            if (this.m_downstreamSendMe <= 0) {
                                throw new XMLParseException("##__null__##", "Got wrong value for DownstreamSendMe in received XML structure.");
                            }
                        }
                        else {
                            if (!trim.equals("0.5")) {
                                throw new UnknownProtocolVersionException("Unknown chain protocol version used ('" + trim + "').");
                            }
                            final NodeList elementsByTagName2 = xmlStructure.getElementsByTagName("DownstreamPackets");
                            if (elementsByTagName2.getLength() == 0) {
                                throw new XMLParseException("##__null__##", "DownstreamPackets node expected in received XML structure.");
                            }
                            final int value2 = XMLUtil.parseValue(elementsByTagName2.item(0), -1);
                            if (value2 < 1) {
                                throw new XMLParseException("DownstreamPackets", "Node has an invalid value.");
                            }
                            final NodeList elementsByTagName3 = xmlStructure.getElementsByTagName("ChannelTimeout");
                            if (elementsByTagName3.getLength() == 0) {
                                throw new XMLParseException("##__null__##", "ChannelTimeout node expected in received XML structure.");
                            }
                            final long n4 = XMLUtil.parseValue(elementsByTagName3.item(0), -1);
                            if (n4 < 1L) {
                                throw new XMLParseException("ChannelTimeout node has an invalid value.");
                            }
                            final long n5 = 1000L * n4;
                            final NodeList elementsByTagName4 = xmlStructure.getElementsByTagName("ChainTimeout");
                            if (elementsByTagName4.getLength() == 0) {
                                throw new XMLParseException("##__null__##", "ChainTimeout node expected in received XML structure.");
                            }
                            final long n6 = XMLUtil.parseValue(elementsByTagName4.item(0), -1);
                            if (n6 < 1L) {
                                throw new XMLParseException("ChainTimeout", "Node has an invalid value.");
                            }
                            this.m_fixedRatioChannelsDescription = new FixedRatioChannelsDescription(value2, n5, 1000L * n6);
                        }
                    }
                }
                else if (j == 0 && XMLUtil.getFirstChildByName(xmlStructure, "SupportsEncrypedControlChannels") != null) {
                    this.m_controlchannelCipher = new ControlChannelCipher();
                }
            }
            this.m_multiplexerInputStreamCipher = new SymCipher();
            this.m_multiplexerOutputStreamCipher = new SymCipher();
            KeyPool.start();
            LogHolder.log(7, LogType.NET, "Starting key exchange...");
            if (this.m_firstMixSymmetricCipher == null) {
                final MixPacket mixPacket = new MixPacket(0);
                final byte[] bytes = "KEYPACKET".getBytes();
                System.arraycopy(bytes, 0, mixPacket.getPayloadData(), 0, bytes.length);
                final byte[] array2 = new byte[32];
                KeyPool.getKey(array2, 0);
                KeyPool.getKey(array2, 16);
                System.arraycopy(array2, 0, mixPacket.getPayloadData(), bytes.length, array2.length);
                this.m_mixParameters[0].getMixCipher().encrypt(mixPacket.getPayloadData(), 0, mixPacket.getPayloadData(), 0);
                outputStream.write(mixPacket.getRawPacket());
                this.m_multiplexerInputStreamCipher.setEncryptionKeyAES(array2, 0, 16);
                this.m_multiplexerOutputStreamCipher.setEncryptionKeyAES(array2, 16, 16);
            }
            else {
                final Document document = XMLUtil.createDocument();
                if (document == null) {
                    throw new XMLParseException("Cannot create XML document for key exchange.");
                }
                final Element element = document.createElement("JAPKeyExchange");
                element.setAttribute("version", "0.1");
                final Element element2 = document.createElement("LinkEncryption");
                final byte[] array3 = new byte[64];
                KeyPool.getKey(array3, 0);
                KeyPool.getKey(array3, 16);
                KeyPool.getKey(array3, 32);
                KeyPool.getKey(array3, 48);
                this.m_multiplexerOutputStreamCipher.setEncryptionKeyAES(array3, 0, 32);
                this.m_multiplexerInputStreamCipher.setEncryptionKeyAES(array3, 32, 32);
                XMLUtil.setValue(element2, Base64.encode(array3, true));
                element.appendChild(element2);
                final Element element3 = document.createElement("MixEncryption");
                final byte[] array4 = new byte[32];
                KeyPool.getKey(array4, 0);
                KeyPool.getKey(array4, 16);
                this.m_firstMixSymmetricCipher.setEncryptionKeyAES(array4, 0, 32);
                XMLUtil.setValue(element3, Base64.encode(array4, true));
                element.appendChild(element3);
                document.appendChild(element);
                if (this.m_controlchannelCipher != null) {
                    final Element element4 = document.createElement("ControlChannelEncryption");
                    final byte[] array5 = new byte[32];
                    KeyPool.getKey(array5, 0);
                    KeyPool.getKey(array5, 16);
                    this.m_controlchannelCipher.setSentKey(array5, 0, 16);
                    this.m_controlchannelCipher.setRecvKey(array5, 16, 16);
                    XMLUtil.setValue(element4, Base64.encode(array5, true));
                    element.appendChild(element4);
                }
                final Element element5 = document.createElement("ReplayDetection");
                if (this.m_protocolWithReplay) {
                    XMLUtil.setValue(element5, "true");
                }
                else {
                    XMLUtil.setValue(element5, "false");
                }
                element.appendChild(element5);
                XMLEncryption.encryptElement(element, this.m_mixParameters[0].getMixCipher().getPublicKey());
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final byte[] byteArray = XMLUtil.toByteArray(document);
                final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(byteArray.length);
                dataOutputStream.flush();
                byteArrayOutputStream.write(byteArray);
                byteArrayOutputStream.flush();
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.flush();
                int k = dataInputStream.readUnsignedShort();
                final byte[] array6 = new byte[k];
                while (k > 0) {
                    final int read2 = inputStream.read(array6, array6.length - k, k);
                    if (read2 == -1) {
                        throw new EOFException("EOF detected while reading symmetric key signature XML structure.");
                    }
                    k -= read2;
                }
                final Document xmlDocument = XMLUtil.toXMLDocument(array6);
                Element documentElement2;
                if (this.m_protocolWithReplay) {
                    final Element documentElement = xmlDocument.getDocumentElement();
                    Element element6 = (Element)((Element)documentElement.getFirstChild()).getFirstChild();
                    for (int l = 0; l < this.m_cascade.getNumberOfMixes(); ++l) {
                        for (int n7 = 0; n7 < this.m_cascade.getNumberOfMixes(); ++n7) {
                            if (element6.getAttribute("id").equals(this.m_mixParameters[n7].getMixId())) {
                                this.m_mixParameters[n7].setReplayOffset(Integer.parseInt(element6.getFirstChild().getFirstChild().getNodeValue()));
                            }
                        }
                        element6 = (Element)element6.getNextSibling();
                    }
                    MixParameters.m_referenceTime = System.currentTimeMillis() / 1000L;
                    documentElement2 = (Element)documentElement.getLastChild();
                }
                else {
                    documentElement2 = xmlDocument.getDocumentElement();
                }
                if (documentElement2 == null) {
                    throw new XMLParseException("##__root__##", "No document element in received symmetric key signature XML structure.");
                }
                document.getDocumentElement().appendChild(XMLUtil.importNode(document, documentElement2, true));
                if (!XMLSignature.verifyFast(document, this.m_cascade.getCertPath().getEndEntityKeys())) {
                    throw new SignatureException("Invalid symmetric keys signature received.");
                }
            }
            if (this.m_cascade.isTermsAndConditionsConfirmationRequired()) {
                if (this.m_tnCRequest.hasResourceRequests()) {
                    final Document document2 = XMLUtil.createDocument();
                    this.m_tnCRequest.toXmlElement(document2);
                    final String string = XMLUtil.toString(document2);
                    if (string != null) {
                        final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        final DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream2);
                        dataOutputStream2.writeShort(string.length());
                        dataOutputStream2.writeBytes(string);
                        outputStream.write(byteArrayOutputStream2.toByteArray());
                        outputStream.flush();
                        final int int1 = dataInputStream.readInt();
                        final byte[] array7 = new byte[int1];
                        inputStream.read(array7, 0, int1);
                        final Document xmlDocument2 = XMLUtil.toXMLDocument(array7);
                        if (xmlDocument2 != null) {
                            TermsAndConditionsResponseHandler.get().handleXMLResourceResponse(xmlDocument2, this.m_tnCRequest);
                        }
                    }
                }
                final Document document3 = XMLUtil.createDocument();
                Element element7;
                if (this.tcrException != null) {
                    element7 = document3.createElement("TermsAndConditionsInterrupt");
                }
                else {
                    element7 = document3.createElement("TermsAndConditionsConfirm");
                    XMLUtil.setAttribute(element7, "accepted", true);
                }
                document3.appendChild(element7);
                final String string2 = XMLUtil.toString(document3);
                final ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                final DataOutputStream dataOutputStream3 = new DataOutputStream(byteArrayOutputStream3);
                dataOutputStream3.writeShort(string2.length());
                dataOutputStream3.writeBytes(string2);
                outputStream.write(byteArrayOutputStream3.toByteArray());
                outputStream.flush();
                if (this.tcrException != null) {
                    throw this.tcrException;
                }
            }
        }
        catch (SignatureException ex5) {
            this.removeCertificateLock();
            throw ex5;
        }
    }
    
    public boolean isProtocolWithTimestamp() {
        return this.m_protocolWithTimestamp;
    }
    
    public boolean isPaymentRequired() {
        return this.m_paymentRequired;
    }
    
    public boolean isChainProtocolWithFlowControl() {
        return this.m_chainProtocolWithFlowControl;
    }
    
    public int getUpstreamSendMe() {
        return this.m_upstreamSendMe;
    }
    
    public int getDownstreamSendMe() {
        return this.m_downstreamSendMe;
    }
    
    public FixedRatioChannelsDescription getFixedRatioChannelsDescription() {
        return this.m_fixedRatioChannelsDescription;
    }
    
    public SymCipher getFirstMixSymmetricCipher() {
        return this.m_firstMixSymmetricCipher;
    }
    
    public SymCipher getMultiplexerInputStreamCipher() {
        return this.m_multiplexerInputStreamCipher;
    }
    
    public SymCipher getMultiplexerOutputStreamCipher() {
        return this.m_multiplexerOutputStreamCipher;
    }
    
    public MixParameters[] getMixParameters() {
        return this.m_mixParameters;
    }
    
    public MixCascade getConnectedCascade() {
        return this.m_cascade;
    }
    
    public void removeCertificateLock() {
        synchronized (this.m_internalSynchronization) {
            if (this.m_mixCascadeCertificateLock != -1) {
                SignatureVerifier.getInstance().getVerificationCertificateStore().removeCertificateLock(this.m_mixCascadeCertificateLock);
                this.m_mixCascadeCertificateLock = -1;
            }
        }
    }
    
    public boolean isProtocolWithEnhancedChannelEncryption() {
        return this.m_bEnhancedChannelEncryption;
    }
    
    public ControlChannelCipher getControlChannelCipher() {
        return this.m_controlchannelCipher;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
