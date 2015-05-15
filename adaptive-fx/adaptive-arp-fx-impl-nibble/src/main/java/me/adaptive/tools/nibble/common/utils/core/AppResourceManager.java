package me.adaptive.tools.nibble.common.utils.core;

import me.adaptive.arp.api.AppRegistryBridge;
import me.adaptive.arp.api.AppResourceData;
import me.adaptive.arp.api.ILogging;
import me.adaptive.arp.api.ILoggingLogLevel;
import me.adaptive.tools.nibble.common.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * Application Resource Manager
 */
public class AppResourceManager {

    // Logger
    private static final String LOG_TAG = "AppResourceManager";
    private static ILogging logger;

    // Singleton instance
    private static AppResourceManager instance = null;

    public static AppResourceManager getInstance() {
        if (instance == null) {
            instance = new AppResourceManager();
            logger = AppRegistryBridge.getInstance().getLoggingBridge();
        }
        return instance;
    }

    /**
     * Retrieve a web resource stored inside the ARP packer by url
     *
     * @param url Url of the resource
     * @return Application Resource Data
     */
    public AppResourceData retrieveWebResource(String url) {

        // TODO: Implement AppPacker Reader for Android. remove this code (LEGACY)

        String file = url.replaceFirst("https://adaptiveapp/", "www/");
        return this.retriveResource(file);
    }

    /**
     * Retrieve a config resource stored inside the ARP packer by url
     *
     * @param url Url of the resource
     * @return Application Resource Data
     */
    public AppResourceData retrieveConfigResource(String url) {

        // TODO: Implement AppPacker Reader for Android. remove this code (LEGACY)

        return this.retriveResource("config/" + url);
    }

    /**
     * REtrieve a common resource for the application
     *
     * @param url Url of the resource
     * @return Application Resource Data
     */
    private AppResourceData retriveResource(String url) {

        // TODO: Implement AppPacker Reader for Android. remove this code (LEGACY)

        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "Retrieveing resource: " + url);

        // Remove the anchor attributes of url's of single page application
        int index = url.indexOf("#");
        if (index > 0)
            url = url.substring(0, index);

        AppResourceData resource = new AppResourceData();
        InputStream is;
        try {
            is = new FileInputStream(url);

            resource.setData(Utils.getBytesFromInputStream(is));
            resource.setId("1");
            resource.setRawType(URLConnection.guessContentTypeFromStream(is));
            resource.setRawLength(0);
            resource.setCooked(false);
            resource.setCookedType("");
            resource.setCookedLength(0);

            is.close();

        } catch (IOException e) {
            System.out.println("------------- ERROR: " + e.getMessage());
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            resource.setData("<html><body><h1>404</h1></body></html>".getBytes());
        }

        return resource;
    }
}
