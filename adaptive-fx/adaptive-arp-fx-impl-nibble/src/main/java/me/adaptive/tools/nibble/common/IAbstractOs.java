package me.adaptive.tools.nibble.common;

import me.adaptive.arp.api.Email;
import me.adaptive.arp.api.IMessagingCallback;
import me.adaptive.arp.api.ITelephonyStatus;
import me.adaptive.arp.api.OSInfo;

/**
 * Created by clozano on 22/05/15.
 */
public interface IAbstractOs {
    /**
     * Returns the Operating System information encapsulated in a bean.
     *
     * @return Operating System information
     */
    OSInfo getOsInfo();

    /**
     * Method that plays a url video inside the emulator. The emulator should
     * implement a video player in JavaFx
     *
     * @param url URl of the stream
     */
    void playStream(String url);

    /**
     * This method executes a javascript inside the emulator's webview
     *
     * @param javaScriptText Javascript to execute
     */
    void executeJavaScript(String javaScriptText);

    /**
     * Method for sending an SMS to a concrete number with a text. The method executes a
     * callback when there is a result to show
     *
     * @param number   Number to send the SMS
     * @param text     Text to send.
     * @param callback Callback fired when there is a result.
     */
    void sendSMS(String number, String text, IMessagingCallback callback);

    /**
     * Method for sending an Email to one or more receivers.  The method executes a
     * callback when there is a result to show
     *
     * @param data     Email data encapsulated into a bean
     * @param callback Callback fired when there is a result.
     */
    void sendEmail(Email data, IMessagingCallback callback);

    /**
     * Method that opens a browser on the operating system of the running emulator.
     *
     * @param url URl to open. HAs to be a valid URL
     * @return Returns the result of the operation.
     */
    boolean openExtenalBrowser(String url);

    /**
     * Method for emulating a call in the current emulator and operating system.
     *
     * @param number Number to call
     * @return Staus of the call
     */
    ITelephonyStatus call(String number);

    /**
     * Method for consulting the user agent of the current emulator and
     * operating System
     *
     * @return USer agent descriptor
     */
    String getUserAgent();
}
