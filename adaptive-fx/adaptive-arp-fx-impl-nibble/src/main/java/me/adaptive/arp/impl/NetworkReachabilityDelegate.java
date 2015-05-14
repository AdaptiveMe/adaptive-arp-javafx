/**
 * --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------
 * <p>
 * (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 * -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 * permissions and limitations under the License.
 * <p>
 * Original author:
 * <p>
 * Carlos Lozano Diez
 * <http://github.com/carloslozano>
 * <http://twitter.com/adaptivecoder>
 * <mailto:carlos@adaptive.me>
 * <p>
 * Contributors:
 * <p>
 * Ferran Vila Conesa
 * <http://github.com/fnva>
 * <http://twitter.com/ferran_vila>
 * <mailto:ferran.vila.conesa@gmail.com>
 * <p>
 * See source code files for contributors.
 * <p>
 * Release:
 *
 * @version v2.2.0
 * <p>
 * -------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */

package me.adaptive.arp.impl;

import me.adaptive.arp.api.BaseCommunicationDelegate;
import me.adaptive.arp.api.INetworkReachability;
import me.adaptive.arp.api.INetworkReachabilityCallback;

/**
 Interface for Managing the Network reachability operations
 Auto-generated implementation of INetworkReachability specification.
 */
public class NetworkReachabilityDelegate extends BaseCommunicationDelegate implements INetworkReachability {

    /**
     Default Constructor.
     */
    public NetworkReachabilityDelegate() {
        super();
    }

    /**
     Whether there is connectivity to a host, via domain name or ip address, or not.

     @param host     domain name or ip address of host.
     @param callback Callback called at the end.
     @since v2.0
     */
    public void isNetworkReachable(String host, INetworkReachabilityCallback callback) {
        // TODO: Not implemented.
        throw new UnsupportedOperationException(this.getClass().getName() + ":isNetworkReachable");
    }

    /**
     Whether there is connectivity to an url of a service or not.

     @param url      to look for
     @param callback Callback called at the end
     @since v2.0
     */
    public void isNetworkServiceReachable(String url, INetworkReachabilityCallback callback) {
        // TODO: Not implemented.
        throw new UnsupportedOperationException(this.getClass().getName() + ":isNetworkServiceReachable");
    }

}
/**
 ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
