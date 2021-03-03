package com.horizon.other;

/**
 * Interface for main class of configuration.
 *
 * @author Horizon
 *
 * @version 1.0
 */
public interface Configuration {

    /**
     * Automatically handles loading of configuration file. If the
     * configuration file does not exist, it will be created. If
     * file exists, it will be loaded automatically.
     *
     * @since 1.0
     */
    void initialize();

    /**
     * Loads data from specified file location.
     *
     * If this function is called in {@link com.horizon.XMLConfiguration} it
     * will rewrite whole {@link com.horizon.data.MemorySection}
     *
     * If the file is null an exception will be thrown.
     *
     * @since 1.0
     */
    void load(boolean newFile);

    /**
     * Creates a new file or load it automatically if is used in {@link com.horizon.XMLConfiguration}
     *
     * @since 1.0
     */
    void create();

    /**
     * Automatically handles saving of configuration file if is used in {@link com.horizon.XMLConfiguration}.
     *
     * @since 1.0
     */
    void save(boolean refactor);
}
