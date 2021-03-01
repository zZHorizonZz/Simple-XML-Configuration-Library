package com.horizon.other;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

public interface DataSection {

    void initialize(File file);

    void save();

    void saveElement(String objectPath, String value);

    void loadElement(String currentPath, NodeList nodeList);

    Object getValue(String path);

    void setValue(String path, Object value);

    Integer getInteger(String path);

    String getString(String path);

    Double getDouble(String path);

    Float getFloat(String path);

    void setInteger(String path, Integer value);

    void setString(String path, String value);

    void setDouble(String path, Double value);

    void setFloat(String path, Float value);
}
