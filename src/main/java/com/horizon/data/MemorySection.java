package com.horizon.data;

import com.horizon.other.DataSection;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemorySection implements DataSection {

    private File file;
    protected final Map<String, Object> dataMap = new LinkedHashMap<>();

    @Override
    public void initialize(File file) {
        try {
            if (file == null || !file.exists())
                throw new FileNotFoundException("File can not be null!");

            this.file = file;
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactory.setXIncludeAware(false);
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilderFactory.setExpandEntityReferences(false);

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(this.file);

            document.getDocumentElement().normalize();

            Element documentElement = document.getDocumentElement();
            String currentPath = documentElement.getNodeName();
            loadElement(currentPath, documentElement.getChildNodes());
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save() {

    }

    @Override
    public void loadElement(String currentPath, NodeList nodeList) {
        for (int index = 0; index < nodeList.getLength(); index++) {

            Node node = nodeList.item(index);
            String nodePathBuilder = currentPath + "." + node.getNodeName();
            if (node.getNodeType() == Node.ELEMENT_NODE)
                if (node.hasChildNodes())
                    loadElement(nodePathBuilder, node.getChildNodes());

            if (node.getNodeType() == Node.TEXT_NODE)
                setValue(nodePathBuilder, node.getNodeValue());

        }
    }

    @Override
    public Object getValue(String path) {
        try {
            if (!dataMap.containsKey(path))
                throw new NullPointerException("This configuration does not contains value with this path!");

            return dataMap.get(path);
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void setValue(String path, Object value) {
        if (dataMap.containsKey(path))
            dataMap.replace(path, value);

        dataMap.put(path, value);
    }

    @Override
    public Integer getInteger(String path) {
        return Integer.parseInt((String) getValue(path));
    }

    @Override
    public String getString(String path) {
        return (String) getValue(path);
    }

    @Override
    public Double getDouble(String path) {
        return Double.parseDouble((String) getValue(path));
    }

    @Override
    public Float getFloat(String path) {
        return Float.parseFloat((String) getValue(path));
    }

    @Override
    public void setInteger(String path, Integer value) {

    }

    @Override
    public void setString(String path, String value) {

    }

    @Override
    public void setDouble(String path, Double value) {

    }

    @Override
    public void setFloat(String path, Float value) {

    }

    public File getFile() {
        return file;
    }

    public Map<String, Object> getMap() {
        return dataMap;
    }
}
