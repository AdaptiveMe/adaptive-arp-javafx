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
import me.adaptive.arp.api.INetworkStatus;
import me.adaptive.arp.api.INetworkStatusListener;

/**
 Interface for Managing the Network status
 Auto-generated implementation of INetworkStatus specification.
 */
public class NetworkStatusDelegate extends BaseCommunicationDelegate implements INetworkStatus {

    /**
     Default Constructor.
     */
    public NetworkStatusDelegate() {
        super();
    }

    /**
     Add the listener for network status changes of the app

     @param listener Listener with the result
     @since v2.0
     */
    public void addNetworkStatusListener(INetworkStatusListener listener) {
        // TODO: Not implemented.
        throw new UnsupportedOperationException(this.getClass().getName() + ":addNetworkStatusListener");
    }

    /**
     Un-registers an existing listener from receiving network status events.

     @param listener Listener with the result
     @since v2.0
     */
    public void removeNetworkStatusListener(INetworkStatusListener listener) {
        // TODO: Not implemented.
        throw new UnsupportedOperationException(this.getClass().getName() + ":removeNetworkStatusListener");
    }

    /**
     Removes all existing listeners from receiving network status events.

     @since v2.0
     */
    public void removeNetworkStatusListeners() {
        // TODO: Not implemented.
        throw new UnsupportedOperationException(this.getClass().getName() + ":removeNetworkStatusListeners");
    }

}
/**
 ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
