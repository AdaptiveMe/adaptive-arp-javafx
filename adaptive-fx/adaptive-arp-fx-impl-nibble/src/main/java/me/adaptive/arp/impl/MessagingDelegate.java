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

import me.adaptive.arp.api.BasePIMDelegate;
import me.adaptive.arp.api.IMessaging;
import me.adaptive.arp.api.IMessagingCallback;

/**
 Interface for Managing the Messaging operations
 Auto-generated implementation of IMessaging specification.
 */
public class MessagingDelegate extends BasePIMDelegate implements IMessaging {

    /**
     Default Constructor.
     */
    public MessagingDelegate() {
        super();
    }

    /**
     Send text SMS

     @param number   to send
     @param text     to send
     @param callback with the result
     @since v2.0
     */
    public void sendSMS(String number, String text, IMessagingCallback callback) {
        // TODO: Not implemented.
        throw new UnsupportedOperationException(this.getClass().getName() + ":sendSMS");
    }

}
/**
 ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
