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
import me.adaptive.arp.impl.*;

/**
 * Class for registering all the Delegates when the emulator is started. All the delegates has to been populated
 * before the first use. This class is instantiated by the emulator, when creates a new device.
 */
public class ApiBootstrap {

    /**
     * This method is responsible for populating all the delegates for the current API definition.
     *
     * @throws Exception Exception creating and populating the delegates
     */
    public static void bootstrap() throws Exception {

        // Register Logging delegate
        AppRegistryBridge.getInstance().getLoggingBridge().setDelegate(new LoggingDelegate());

        // Register the application/platform delegates
        AppRegistryBridge.getInstance().getPlatformContext().setDelegate(new AppContextDelegate());
        AppRegistryBridge.getInstance().getPlatformContextWeb().setDelegate(new AppContextWebviewDelegate());

        // Register all the delegates
        AppRegistryBridge.getInstance().getAccelerationBridge().setDelegate(new AccelerationDelegate());
        AppRegistryBridge.getInstance().getBrowserBridge().setDelegate(new BrowserDelegate());
        AppRegistryBridge.getInstance().getCapabilitiesBridge().setDelegate(new CapabilitiesDelegate());
        AppRegistryBridge.getInstance().getContactBridge().setDelegate(new ContactDelegate());
        AppRegistryBridge.getInstance().getDatabaseBridge().setDelegate(new DatabaseDelegate());
        AppRegistryBridge.getInstance().getDeviceBridge().setDelegate(new DeviceDelegate());
        AppRegistryBridge.getInstance().getDisplayBridge().setDelegate(new DisplayDelegate());
        AppRegistryBridge.getInstance().getFileBridge().setDelegate(new FileDelegate());
        AppRegistryBridge.getInstance().getFileSystemBridge().setDelegate(new FileSystemDelegate());
        AppRegistryBridge.getInstance().getGeolocationBridge().setDelegate(new GeolocationDelegate());
        AppRegistryBridge.getInstance().getGlobalizationBridge().setDelegate(new GlobalizationDelegate());
        AppRegistryBridge.getInstance().getLifecycleBridge().setDelegate(new LifecycleDelegate());
        AppRegistryBridge.getInstance().getMailBridge().setDelegate(new MailDelegate());
        AppRegistryBridge.getInstance().getMessagingBridge().setDelegate(new MessagingDelegate());
        AppRegistryBridge.getInstance().getNetworkReachabilityBridge().setDelegate(new NetworkReachabilityDelegate());
        AppRegistryBridge.getInstance().getNetworkStatusBridge().setDelegate(new NetworkStatusDelegate());
        AppRegistryBridge.getInstance().getOSBridge().setDelegate(new OSDelegate());
        AppRegistryBridge.getInstance().getRuntimeBridge().setDelegate(new RuntimeDelegate());
        AppRegistryBridge.getInstance().getSecurityBridge().setDelegate(new SecurityDelegate());
        AppRegistryBridge.getInstance().getServiceBridge().setDelegate(new ServiceDelegate());
        AppRegistryBridge.getInstance().getTelephonyBridge().setDelegate(new TelephonyDelegate());
        AppRegistryBridge.getInstance().getVideoBridge().setDelegate(new VideoDelegate());
    }
}
