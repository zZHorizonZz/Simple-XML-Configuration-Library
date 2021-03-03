package com.horizon.other;

import org.w3c.dom.NodeList;

import java.io.File;

/**
 * Interface that defines methods for {@link com.horizon.data.MemorySection}.
 * It is in interface if someone needs to create own xml data handling.
 *
 * @author Horizon
 * @version 1.0
 */
public interface DataSection {


    /**
     * Handles creation of {@link org.w3c.dom.Document} through {@link javax.xml.parsers.DocumentBuilderFactory}.
     * If file is new and is used in {@link com.horizon.data.MemorySection}, this method will insert the new document
     * {@link org.w3c.dom.Node} with name "data".
     *
     * @param file    File that contains data or it can be used for xml configuration. If file is null and exception is thrown.
     * @param newFile If file is new.
     * @since 1.0
     */
    void initialize(File file, boolean newFile);

    /**
     * Handles save of the data that are saved in the datamap to the xml file.
     * If we want to reformat the file set parameter to true. Do not use reformatting
     * if you just saving file set false otherwise it will add additional blank lines.
     *
     * @param reformat If file can be reformatted if is used in {@link com.horizon.data.MemorySection}.
     * @since 1.0
     */
    void save(boolean reformat);

    /**
     * Handles save of data to the datamap. If we want to save the data to the xml file
     * we need to use {@code save(false)} method. If is used in {@link com.horizon.data.MemorySection}.
     *
     * @param objectPath - Path to the value.
     * @param value      - Value in String format.
     * @since 1.0
     */
    void saveElement(String objectPath, String value);

    /**
     * This method is used for loading the file. This is not necessary
     * needed to be in this interface. If is used in {@link com.horizon.data.MemorySection}
     * it will load the element from {@link NodeList}.
     *
     * @param currentPath Current path of the node.
     * @param nodeList    List of child nodes of the parent node.
     * @since 1.0
     */
    void loadElement(String currentPath, NodeList nodeList);

    /**
     * Tries to find the {@link Object} in datamap if is used in {@link com.horizon.data.MemorySection}.
     *
     * @param path Path of the object
     * @return {@link Object} if is found otherwise it will return {@code null}.
     */
    Object getValue(String path);

    /**
     * Sets the value to the data map if is used in {@link com.horizon.data.MemorySection}.
     *
     * @param path Path of the object
     * @param value Value currently must be {@link String} in the future it can be used for {@link java.util.List}, etc.
     */
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
