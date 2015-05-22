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
import me.adaptive.tools.nibble.common.IAbstractApp;
import me.adaptive.tools.nibble.common.IAbstractDevice;
import me.adaptive.tools.nibble.common.AbstractEmulator;
import me.adaptive.tools.nibble.common.utils.Utils;

import java.io.*;
import java.util.Properties;

/**
 * Interface for Managing the Security operations
 * Auto-generated implementation of ISecurity specification.
 */
public class SecurityDelegate extends BaseSecurityDelegate implements ISecurity {

    /**
     * Logging Tag for this Implementation
     */
    private static final String LOG_TAG = "SecurityDelegate";

    /**
     * Logger reference
     */
    private ILogging logger;

    /**
     * Default Constructor.
     */
    public SecurityDelegate() {
        super();
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
    }

    /**
     * Stores in the device internal storage the specified item/s.
     *
     * @param keyValues        Array containing the items to store on the device internal memory.
     * @param publicAccessName The name of the shared internal storage object (if needed).
     * @param callback         callback to be executed upon function result.
     * @since v2.0
     */
    public void setSecureKeyValuePairs(SecureKeyPair[] keyValues, String publicAccessName, ISecurityResultCallback callback) {

        // MARK: since in the emulator we don't have a security keychaing
        // we are emulating the keychain with a properties file

        Properties props = new Properties();
        IAbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();

        for (SecureKeyPair k : keyValues) {
            props.setProperty(k.getSecureKey(), k.getSecureData());
        }

        try {

            File f = new File(app.getTempDirectory() + "/" + publicAccessName);
            OutputStream out = new FileOutputStream(f);
            props.store(out, null);

            callback.onResult(keyValues);

        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            callback.onError(ISecurityResultCallbackError.Unknown);
        }
    }

    /**
     * Retrieves from the device internal storage the entry/entries containing the specified key names.
     *
     * @param keys             Array with the key names to retrieve.
     * @param publicAccessName The name of the shared internal storage object (if needed).
     * @param callback         callback to be executed upon function result.
     * @since v2.0
     */
    public void getSecureKeyValuePairs(String[] keys, String publicAccessName, ISecurityResultCallback callback) {

        // MARK: since in the emulator we don't have a security keychaing
        // we are emulating the keychain with a properties file

        Properties props = new Properties();
        IAbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        SecureKeyPair[] keyValues = new SecureKeyPair[0];
        InputStream is;

        try {

            File f = new File(app.getTempDirectory() + "/" + publicAccessName);
            is = new FileInputStream(f);
            props.load(is);

            for (String k : keys) {
                keyValues = Utils.append(keyValues, new SecureKeyPair(k, props.getProperty(k)));
            }

            callback.onResult(keyValues);

        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            callback.onError(ISecurityResultCallbackError.Unknown);
        }
    }

    /**
     * Deletes from the device internal storage the entry/entries containing the specified key names.
     *
     * @param keys             Array with the key names to delete.
     * @param publicAccessName The name of the shared internal storage object (if needed).
     * @param callback         callback to be executed upon function result.
     * @since v2.0
     */
    public void deleteSecureKeyValuePairs(String[] keys, String publicAccessName, ISecurityResultCallback callback) {

        // MARK: since in the emulator we don't have a security keychaing
        // we are emulating the keychain with a properties file

        Properties props = new Properties();
        IAbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        SecureKeyPair[] keyValues = new SecureKeyPair[0];
        InputStream is;

        try {

            File f = new File(app.getTempDirectory() + "/" + publicAccessName);
            is = new FileInputStream(f);
            props.load(is);

            for (String k : keys) {
                props.remove(k);
            }

            OutputStream out = new FileOutputStream(f);
            props.store(out, null);

            callback.onResult(keyValues);

        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            callback.onError(ISecurityResultCallbackError.Unknown);
        }
    }

    /**
     * Returns if the device has been modified in anyhow
     *
     * @return true if the device has been modified; false otherwise
     * @since v2.0
     */
    public boolean isDeviceModified() {

        IAbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.isDeviceModified();
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
