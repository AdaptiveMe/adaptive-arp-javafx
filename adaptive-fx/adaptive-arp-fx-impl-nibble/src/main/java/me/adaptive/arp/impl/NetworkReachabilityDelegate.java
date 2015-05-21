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
package me.adaptive.arp.impl;

import me.adaptive.arp.api.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Interface for Managing the Network reachability operations
 * Auto-generated implementation of INetworkReachability specification.
 */
public class NetworkReachabilityDelegate extends BaseCommunicationDelegate implements INetworkReachability {

    /**
     * Logging Tag for this Implementation
     */
    private static final String LOG_TAG = "NetworkReachabilityDelegate";

    /**
     * Logger reference
     */
    private ILogging logger;

    /**
     * Default Constructor.
     */
    public NetworkReachabilityDelegate() {
        super();
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
    }

    /**
     * Whether there is connectivity to a host, via domain name or ip address, or not.
     *
     * @param host     domain name or ip address of host.
     * @param callback Callback called at the end.
     * @since v2.0
     */
    public void isNetworkReachable(String host, INetworkReachabilityCallback callback) {
        checkHttpConnection(host, callback);
    }

    /**
     * Whether there is connectivity to an url of a service or not.
     *
     * @param url      to look for
     * @param callback Callback called at the end
     * @since v2.0
     */
    public void isNetworkServiceReachable(String url, INetworkReachabilityCallback callback) {
        checkHttpConnection(url, callback);
    }

    /**
     * Check the connectivity with a host
     *
     * @param testUrl URl for testing purposes
     * @param cb      Callback
     */
    private void checkHttpConnection(String testUrl, INetworkReachabilityCallback cb) {

        boolean hasScheme = testUrl.contains("://");
        if (!hasScheme) {
            testUrl = "http://" + testUrl;
        }

        try {
            URL url = new URL(testUrl);
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(10 * 1000);
            urlc.connect();
            if (urlc.getResponseCode() == 200) {
                logger.log(ILoggingLogLevel.Debug, LOG_TAG, "Connection: Success !");
                cb.onResult(true);
            } else {
                logger.log(ILoggingLogLevel.Error, LOG_TAG, "Connection: Failure ! response code " + urlc.getResponseCode());
                cb.onError(INetworkReachabilityCallbackError.NoResponse);
            }
        } catch (MalformedURLException e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Connection: Failure ! MalformedURLException");
            cb.onError(INetworkReachabilityCallbackError.WrongParams);
        } catch (IOException e) {
            cb.onError(INetworkReachabilityCallbackError.NotAllowed);
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Connection: Failure ! IOException");
        }
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
