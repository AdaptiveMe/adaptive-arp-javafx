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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
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

    /**
     * Method for parsing a PList file from an inputstream. This method iterates all the file
     * and creates a Bean File with all the plist tree.
     *
     * @param is InputStream with the file stream
     * @return Returns a populated bean with all the plist information or null if there is one error.
     */
    public PList parse(InputStream is) {

        PList plist = new PList();

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            factory.setValidating(false);
            factory.setFeature("FEATURE_RELAXED", true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is, "UTF-8");

            int event = parser.next();
            while (event != XmlPullParser.END_DOCUMENT) {

                String name;
                switch (event) {
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (Constants.DICT_TAG.equals(name)) {
                            plist.setValues(parseDictionary(parser));
                        } else {
                            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Unexpected element found [" + event + "," + name + "]");
                            throw new XmlPullParserException("Unexpected element found [" + event + "," + name + "]");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (!Constants.PLIST_TAG.equals(name)) {
                            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Unexpected element found [" + event + "," + name + "]");
                            throw new XmlPullParserException("Unexpected element found [" + event + "," + name + "]");
                        }
                        break;
                }
                event = parser.next();
            }
        } catch (Exception ex) {
            plist = null;
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Parse Error: " + ex.getLocalizedMessage());
        }

        return plist;
    }

    /**
     * Method for parsing a dictionary from a XMLPullParser
     *
     * @param parser XmlPullParser to generate the dictionary
     * @return Returns a Map<String,String> with all the information of the xml
     * @throws IOException            Exception if the File is not founded
     * @throws XmlPullParserException XML parse exception
     */
    private Map<String, String> parseDictionary(XmlPullParser parser) throws IOException, XmlPullParserException {

        Map<String, String> map = new HashMap<>();

        String key = null;
        String value;
        int event = parser.next();
        while ((event != XmlPullParser.END_TAG) || !Constants.DICT_TAG.equals(parser.getName())) {
            switch (event) {
                case XmlPullParser.START_TAG:
                    String name = parser.getName();
                    if (Constants.KEY_TAG.equals(name)) {
                        key = getText(parser, Constants.KEY_TAG);
                    } else if (Constants.VALUE_TAG.equals(name)) {
                        value = getText(parser, Constants.VALUE_TAG);
                        map.put(key, value);
                    }
                    break;
            }
            event = parser.next();
        }

        return map;
    }

    /**
     * Method for obtaining a text from an xml tag from a XMLPullParser
     *
     * @param parser XmlPullParser to obtaining the text
     * @param tag    XML tag for reference
     * @return Returns a string with the text value
     * @throws IOException            Exception if the File is not founded
     * @throws XmlPullParserException XML parse exception
     */
    private String getText(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {

        int event = parser.getEventType();
        if ((event != XmlPullParser.START_TAG) || !tag.equals(parser.getName())) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Unexpected element found [" + event + "," + parser.getName() + "]");
            throw new XmlPullParserException("Unexpected element found [" + event + "," + parser.getName() + "]");
        }

        StringBuilder sb = new StringBuilder();
        event = parser.next();
        while (event == XmlPullParser.TEXT) {
            sb.append(parser.getText());
            event = parser.next();
        }

        String text = sb.toString();
        if ((event != XmlPullParser.END_TAG) || !tag.equals(parser.getName())) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Unexpected element found [" + event + "," + parser.getName() + "]");
            throw new XmlPullParserException("Unexpected element found [" + event + "," + parser.getName() + "]");
        }

        return text;

    }

}