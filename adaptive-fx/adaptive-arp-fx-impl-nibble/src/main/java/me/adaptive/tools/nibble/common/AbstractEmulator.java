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
 * current emulator (Operating System and Device)
 */
public abstract class AbstractEmulator {

    // TODO: Make a relation between the OS-Device-Emulator

    // TODO: Logging to JS console

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

    // TODO: Add UUID to AbstractDevice
    // TODO: getDeviceOrientationCurrent
    // TODO: DeviceOrientationListeners
    // TODO: getDisplayOrientationCurrent
    // TODO: DisplayOrientationListeners
    // TODO: DeviceButtonListeners

    // TODO: Inform all the capabilities

    // TODO: Reference to Webview and Execute Javascript

    // TODO: DisplayOrientationChanges
    // TODO: getDisplayOrientationCurrent

    // TODO: Lifecycle Listeners

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

    // TODO: Paths..., Root path of application, Temp directory, etc...

    public abstract String getApplicationPath();

    public abstract String getTempDirectory();

    // TODO: VideoPlayer....
    // TODO: Dismiss Application
    // TODO: Dismiss SplashScreen
    // TODO: SMS
    // TODO: Mail
    // TODO: External/Internal Browser
}
