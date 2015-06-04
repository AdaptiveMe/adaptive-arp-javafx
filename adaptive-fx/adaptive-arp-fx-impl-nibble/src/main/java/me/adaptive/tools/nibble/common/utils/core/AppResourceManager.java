/*
 * --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------
 *
 * (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 * -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 * permissions and limitations under the License.
 *
 * Original author:
 *
 *     * Carlos Lozano Diez
 *             <http://github.com/carloslozano>
 *             <http://twitter.com/adaptivecoder>
 *             <mailto:carlos@adaptive.me>
 *
 * Contributors:
 *
 *     * Ferran Vila Conesa
 *              <http://github.com/fnva>
 *              <http://twitter.com/ferran_vila>
 *              <mailto:ferran.vila.conesa@gmail.com>
 *
 *     * See source code files for contributors.
 *
 * Release:
 *
 *     * @version v2.0.2
 *
 * -------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */
package me.adaptive.tools.nibble.common.utils.core;

import me.adaptive.arp.api.AppRegistryBridge;
import me.adaptive.arp.api.AppResourceData;
import me.adaptive.arp.api.ILogging;
import me.adaptive.arp.api.ILoggingLogLevel;
import me.adaptive.tools.nibble.common.AbstractEmulator;
import me.adaptive.tools.nibble.common.IAbstractApp;
import me.adaptive.tools.nibble.common.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * Application Resource Manager
 */
public class AppResourceManager {

    private IAbstractApp app;

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

        String file = url.replaceFirst("https://adaptiveapp/", "www" + File.separator);
        return this.retrieveResource(file);
    }

    /**
     * Retrieve a config resource stored inside the ARP packer by url
     *
     * @param url Url of the resource
     * @return Application Resource Data
     */
    public AppResourceData retrieveConfigResource(String url) {

        return this.retrieveResource(File.separator + ".." + File.separator + "config" + File.separator + url);
    }

    /**
     * REtrieve a common resource for the application
     *
     * @param url Url of the resource
     * @return Application Resource Data
     */
    private AppResourceData retrieveResource(String url) {

        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "Retrieveing resource: " + url);
        app = AbstractEmulator.getCurrentEmulator().getApp();

        // Remove the anchor attributes of url's of single page application
        int index = url.indexOf("#");
        if (index > 0)
            url = url.substring(0, index);

        AppResourceData resource = new AppResourceData();
        InputStream is;
        try {
            is = new FileInputStream(app.getApplicationPath() + url);

            resource.setData(Utils.getBytesFromInputStream(is));
            resource.setId("1");
            resource.setRawType(URLConnection.guessContentTypeFromStream(is));
            resource.setRawLength(0);
            resource.setCooked(false);
            resource.setCookedType("");
            resource.setCookedLength(0);

            is.close();

        } catch (IOException e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            resource.setData("<html><body><h1>404</h1></body></html>".getBytes());
        }

        return resource;
    }
}
