/**
 * --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------
 * <p/>
 * (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 * -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 * permissions and limitations under the License.
 * <p/>
 * Original author:
 * <p/>
 * Carlos Lozano Diez
 * <http://github.com/carloslozano>
 * <http://twitter.com/adaptivecoder>
 * <mailto:carlos@adaptive.me>
 * <p/>
 * Contributors:
 * <p/>
 * Ferran Vila Conesa
 * <http://github.com/fnva>
 * <http://twitter.com/ferran_vila>
 * <mailto:ferran.vila.conesa@gmail.com>
 * <p/>
 * See source code files for contributors.
 * <p/>
 * Release:
 *
 * @version v2.2.0
 * <p/>
 * -------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */
package me.adaptive.tools.nibble.common;


import me.adaptive.arp.api.AppRegistryBridge;
import me.adaptive.arp.impl.*;

public class ApiBootstrap {

    public static void bootstrap() throws Exception {
        // Register Logging delegate
        AppRegistryBridge.getInstance().getLoggingBridge().setDelegate(new LoggingDelegate());

        // Register the application delegates
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
