package com.horizon.data;

import com.horizon.common.UtilXML;
import com.horizon.other.DataSection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemorySection implements DataSection {

    private File file;
    private Document xmlDocument;
    protected final Map<String, Object> dataMap = new LinkedHashMap<>();

    @Override
    public void initialize(File file, boolean newFile) {
        try {
            if (file == null || !file.exists())
                throw new FileNotFoundException("File can not be null!");

            this.file = file;
            DocumentBuilder documentBuilder = UtilXML.createDocumentBuilderFactory(false, false).newDocumentBuilder();

            if (newFile) {
                this.xmlDocument = documentBuilder.newDocument();
                this.xmlDocument.appendChild(this.xmlDocument.createElement("data"));
            } else {
                this.xmlDocument = documentBuilder.parse(this.file);
                this.xmlDocument.getDocumentElement().normalize();
            }

            if (xmlDocument.getDocumentElement() != null) {
                Element documentElement = this.xmlDocument.getDocumentElement();
                String currentPath = documentElement.getNodeName();
                loadElement(currentPath, documentElement.getChildNodes());
            }
        } catch (ParserConfigurationException | IOException | SAXException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save(boolean reformat) {
        for (Map.Entry<String, Object> dataEntry : dataMap.entrySet()) {
            String path = dataEntry.getKey();
            Object dataValue = dataEntry.getValue();

            if (dataValue instanceof String)
                saveElement(path, (String) dataValue);
        }

        UtilXML.transformDocument(this.xmlDocument, this.file, reformat);
    }

    @Override
    public void saveElement(String objectPath, String value) {
        String[] pathElements = objectPath.split("\\.");
        int pathIndex = 0;
        Node currentNode = xmlDocument.getDocumentElement();

        if(!currentNode.getNodeName().equalsIgnoreCase(pathElements[0]))
            this.xmlDocument.renameNode(currentNode, null, pathElements[0]);

        masterLoop:
        for (String path : pathElements) {
            pathIndex++;
            if (pathIndex == 1)
                continue;

            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                for (int index = 0; index < currentNode.getChildNodes().getLength(); index++) {
                    Node childNode = currentNode.getChildNodes().item(index);
                    if (childNode.getNodeName().equalsIgnoreCase(path)) {
                        currentNode = childNode;
                        continue masterLoop;
                    }
                }

                Element element = this.xmlDocument.createElement(path);
                currentNode.appendChild(element);
                currentNode = element;
            }
        }

        if (currentNode.getFirstChild() != null) {
            currentNode.getFirstChild().setNodeValue(value);
        } else {
            currentNode.appendChild(this.xmlDocument.createTextNode(value));
        }

    }

    @Override
    public void loadElement(String currentPath, NodeList nodeList) {
        for (int index = 0; index < nodeList.getLength(); index++) {

            Node node = nodeList.item(index);
            String nodePathBuilder = currentPath + "." + node.getNodeName();

            if (node.getNodeType() == Node.ELEMENT_NODE)
                if (node.hasChildNodes())
                    loadElement(nodePathBuilder, node.getChildNodes());

            if (node.getNodeType() == Node.TEXT_NODE) {
                if (node.getNodeValue() != null && nodeList.getLength() == 1)
                    setValue(currentPath, node.getNodeValue());
            }
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
    public Long getLong(String path) {
        return Long.parseLong((String) getValue(path));
    }

    @Override
    public void setInteger(String path, Integer value) {
        setValue(path, String.valueOf(value));
    }

    @Override
    public void setString(String path, String value) {
        setValue(path, value);
    }

    @Override
    public void setDouble(String path, Double value) {
        setValue(path, String.valueOf(value));
    }

    @Override
    public void setFloat(String path, Float value) {
        setValue(path, String.valueOf(value));
    }

    @Override
    public void setLong(String path, Long value) {
        setValue(path, String.valueOf(value));
    }

    public File getFile() {
        return file;
    }

    public Map<String, Object> getMap() {
        return dataMap;
    }
}
