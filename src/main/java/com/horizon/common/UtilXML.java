package com.horizon.common;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * There are some features that can be used globally. Like saving
 * {@link Document} to the file or {@link DocumentBuilderFactory} creation.
 *
 * @author Horizon
 * @version 1.0
 */
public class UtilXML {

    public static void transformDocument(Document document, File file, boolean refactor) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Transformer transformer = transformerFactory.newTransformer();
            if(refactor)
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static DocumentBuilderFactory createDocumentBuilderFactory(boolean xIncludeAware, boolean expandEntityReferences) {
        DocumentBuilderFactory documentBuilderFactory = null;

        try {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();

            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactory.setXIncludeAware(xIncludeAware);
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilderFactory.setExpandEntityReferences(expandEntityReferences);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return documentBuilderFactory;
    }
}
