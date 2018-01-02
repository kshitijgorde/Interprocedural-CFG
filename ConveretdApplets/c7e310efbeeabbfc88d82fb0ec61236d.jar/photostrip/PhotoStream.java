// 
// Decompiled by Procyon v0.5.30
// 

package photostrip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import javax.xml.xpath.XPath;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import java.util.ArrayList;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;
import java.net.URL;

public class PhotoStream
{
    public static List<PhotoResource> parse(final URL url) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        final DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = fact.newDocumentBuilder();
        final Document doc = builder.parse(url.openStream());
        final XPathFactory xpfact = XPathFactory.newInstance();
        final XPath xp = xpfact.newXPath();
        final NodeList list = (NodeList)xp.evaluate("/feed/entry", doc, XPathConstants.NODESET);
        final List<PhotoResource> photos = new ArrayList<PhotoResource>();
        for (int i = 0; i < list.getLength(); ++i) {
            final Element entry = (Element)list.item(i);
            final NodeList feedNode = entry.getElementsByTagName("link");
            final Element link = (Element)feedNode.item(0);
            final String pageUrl = link.getAttribute("href");
            final Node contentNode = entry.getElementsByTagName("content").item(0);
            final String content = contentNode.getTextContent();
            final List<String> results = extract("src=\"(.*?)\"", content);
            p("image = " + results.get(1));
            photos.add(new PhotoResource(results.get(1), pageUrl));
        }
        return photos;
    }
    
    private static List<String> extract(final String string, final String content) {
        final Pattern pattern = Pattern.compile(string);
        final Matcher matcher = pattern.matcher(content);
        matcher.find();
        final List<String> results = new ArrayList<String>();
        for (int i = 0; i <= matcher.groupCount(); ++i) {
            results.add(matcher.group(i));
        }
        return results;
    }
    
    private static void p(final String string) {
        System.out.println(string);
    }
}
