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
package me.adaptive.tools.nibble.common.utils.parser.plist;

import me.adaptive.arp.api.KeyPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PList {

    /**
     * Map object
     */
    private Map<String, String> map;

    /**
     * Default Constructor
     */
    public PList() {
        map = new HashMap<String, String>();
    }

    /**
     * Set a new map entry
     *
     * @param key   Entry key identifier
     * @param value Entry value
     */
    public void setValue(String key, String value) {
        map.put(key, value);
    }

    /**
     * Returns a key value
     *
     * @param key Entry key identifier
     * @return the string value
     */
    public String getValue(String key) {
        return map.get(key);
    }

    /**
     * Get the map
     *
     * @return the Map
     */
    public Map<String, String> getValues() {
        return map;
    }

    /**
     * Set the map object
     *
     * @param values to set
     */
    public void setValues(Map<String, String> values) {
        map.putAll(values);
    }

    /**
     * Convert Map object to KeyPair[]
     *
     * @return The resulting KeyPair[]
     */
    public KeyPair[] getKeyPair() {

        List<KeyPair> result = new ArrayList<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.add(new KeyPair(entry.getKey(), entry.getValue()));
        }

        return result.toArray(new KeyPair[result.size()]);
    }

}
