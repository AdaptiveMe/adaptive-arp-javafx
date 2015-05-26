package me.adaptive.tools.nibble.common;

import me.adaptive.arp.api.ILoggingLogLevel;
import me.adaptive.arp.api.Lifecycle;

import java.io.Serializable;


public interface IAbstractApp extends Serializable {
    /**
     * Abstract method implemented by the emulator to show the errors of the native
     * platform inside the Javascript console of the webview emulator.
     * The output format should be: ("[DEBUG - \(category)] \(message)")
     *
     * @param level    Log level
     * @param category Category/tag name to identify/filter the log.
     * @param message  Message to be logged
     */
    void log(ILoggingLogLevel level, String category, String message);

    /**
     * Returns the root folder of the application running in the emulator. This method is used to obtain
     * reference paths to application files.
     *
     * @return Application Root Path
     */
    String getApplicationPath();

    /**
     * Returns the path of the temporary folder created for every instance of the emulator (or the
     * same if they're in sync) to create provisional data for testing the File, Database delegates.
     *
     * @return Path to temporary folder
     */
    String getTempDirectory();

    /**
     * External method to notify all the registered lifecycle listeners that
     * a new lifecycle event is fired by the emulator
     *
     * @param lifecycle Lifecycle event with all the information of the change
     */
    void notifyLifecycleListeners(Lifecycle lifecycle);

    /**
     * Method that closes the actual application and displays the main page of
     * the operating system.
     */
    void dismissApplication();

    /**
     * Method that opens a url inside the embedded browser of the application.
     *
     * @param url            Url to show
     * @param title          Window title
     * @param backButtonText Back button label
     * @return Returns the result of the operation
     */
    boolean openInternalBrowser(String url, String title, String backButtonText);

    /**
     * Method for opening a browser embedded into the application in a modal window
     *
     * @param url            Url to open
     * @param title          Title of the Navigation bar
     * @param backButtonText Title of the Back button bar
     * @return The result of the operation
     */
    boolean openInternalBrowserModal(String url, String title, String backButtonText);

    /**
     * Method for closing the splash screen of the application
     *
     * @return Result of the operation
     */
    boolean dismissSplashScreen();

    /**
     * Whether the application is in background or not
     *
     * @return true if the application is in background;false otherwise
     */
    boolean isBackground();
}
