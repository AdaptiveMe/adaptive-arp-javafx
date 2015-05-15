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
package me.adaptive.tools.nibble.common;

import me.adaptive.arp.api.AppRegistryBridge;
import me.adaptive.arp.api.ILifecycleListener;
import me.adaptive.arp.api.ILoggingLogLevel;
import me.adaptive.arp.api.Lifecycle;
import me.adaptive.arp.impl.LifecycleDelegate;

/**
 * Abstract class for inter-communication between the emulator (adaptive-tools-nibble)
 * and the API implementation. This class is for sharing information about the
 * running application of the current emulator
 */
public abstract class AbstractApp {

    /**
     * Abstract method implemented by the emulator to show the errors of the native
     * platform inside the Javascript console of the webview emulator.
     * The output format should be: ("[DEBUG - \(category)] \(message)")
     *
     * @param level    Log level
     * @param category Category/tag name to identify/filter the log.
     * @param message  Message to be logged
     */
    public abstract void log(ILoggingLogLevel level, String category, String message);

    /**
     * Returns the root folder of the application running in the emulator. This method is used to obtain
     * reference paths to application files.
     *
     * @return Application Root Path
     */
    public abstract String getApplicationPath();

    /**
     * Returns the path of the temporary folder created for every instance of the emulator (or the
     * same if they're in sync) to create provisional data for testing the File, Database delegates.
     *
     * @return Path to temporary folder
     */
    public abstract String getTempDirectory();

    /**
     * External method to notify all the registered lifecycle listeners that
     * a new lifecycle event is fired by the emulator
     *
     * @param lifecycle Lifecycle event with all the information of the change
     */
    public void notifyLifecycleListeners(Lifecycle lifecycle) {

        LifecycleDelegate delegate = (LifecycleDelegate) AppRegistryBridge.getInstance().getLifecycleBridge().getDelegate();

        for (ILifecycleListener listener : delegate.getListeners()) {
            listener.onResult(lifecycle);
        }
    }

    /**
     * Method that closes the actual application and displays the main page of
     * the operating system.
     */
    public abstract void dismissApplication();

    /**
     * Method that opens a url inside the embedded browser of the application.
     *
     * @param url            Url to show
     * @param title          Window title
     * @param backButtonText Back button label
     * @return Returns the result of the operation
     */
    public abstract boolean openInternalBrowser(String url, String title, String backButtonText);
}