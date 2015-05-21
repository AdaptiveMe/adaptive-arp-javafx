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

import me.adaptive.arp.api.AppRegistryBridge;
import me.adaptive.arp.api.ILogging;
import me.adaptive.arp.api.ILoggingLogLevel;
import me.adaptive.tools.nibble.common.utils.parser.Constants;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class PListParser {

    /**
     * Logger tag for this class
     */
    private static final String LOG_TAG = "PListParser";

    /**
     * Logger instance
     */
    private ILogging logger;

    /**
     * Singleton instance for this class
     */
    private static PListParser instance = null;

    /**
     * Class constructor. Instantiate elements.
     */
    protected PListParser() {
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
    }

    /**
     * Singleton getter or constructor in case of null
     *
     * @return Singleton instance for this class
     */
    public static PListParser getInstance() {
        if (instance == null) {
            instance = new PListParser();
        }
        return instance;
    }

    public PList parse(InputStream is) {

        PList plist = new PList();

        try {

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(is, "UTF-8");

            int event = parser.next();
            while (event != XMLStreamReader.END_DOCUMENT) {

                String name;
                switch (event) {
                    case XMLStreamReader.START_ELEMENT:
                        name = parser.getName().getLocalPart();
                        if (Constants.DICT_TAG.equals(name)) {
                            plist.setValues(parseDictionary(parser));
                            return plist;
                        }
                        break;
                }
                event = parser.next();
            }

        } catch (Exception ex) {
            plist = null;
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Parsing plist: " + ex.getMessage());
        }

        return plist;
    }

    /**
     * Method for parsing a dictionary from a XMLPullParser
     *
     * @param parser XmlPullParser to generate the dictionary
     * @return Returns a Map<String,String> with all the information of the xml
     */
    private Map<String, String> parseDictionary(XMLStreamReader parser) throws XMLStreamException {

        Map<String, String> map = new HashMap<>();

        String key = "";
        String value = "";
        int event = parser.next();
        while (event != XMLStreamReader.END_DOCUMENT) {
            switch (event) {
                case XMLStreamReader.START_ELEMENT:
                    String name = parser.getName().getLocalPart();
                    if (Constants.KEY_TAG.equals(name)) {
                        key = parser.getElementText();
                    } else if (Constants.VALUE_TAG.equals(name)) {
                        value = parser.getElementText();
                        map.put(key, value);
                    }
                    break;
            }
            event = parser.next();
        }

        return map;
    }

}