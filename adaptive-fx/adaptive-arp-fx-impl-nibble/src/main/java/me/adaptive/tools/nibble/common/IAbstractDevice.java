package me.adaptive.tools.nibble.common;

import me.adaptive.arp.api.*;

/**
 * Created by clozano on 22/05/15.
 */
public interface IAbstractDevice {
    /**
     * Returns the current device information
     *
     * @return Device Information Bean
     */
    DeviceInfo getDeviceInfo();

    /**
     * Method that returns the current orientation of the emulator.
     *
     * @return Enum value that represents a orientation
     */
    ICapabilitiesOrientation getDeviceOrientationCurrent();

    /**
     * Method that returns the current orientation of the display in the emulator.
     *
     * @return Enum value that represents a orientation
     */
    ICapabilitiesOrientation getDisplayOrientationCurrent();

    /**
     * External method to notify all the registered device orientation listeners that
     * a new device orientation event is fired by the emulator
     *
     * @param event Device orientation event with all the information of the change
     */
    void notifyDeviceOrientationListeners(RotationEvent event);

    /**
     * External method to notify all the registered display orientation listeners that
     * a new display orientation event is fired by the emulator
     *
     * @param event Display orientation event with all the information of the change
     */
    void notifyDisplayOrientationListeners(RotationEvent event);

    /**
     * External method to notify all the registered device button listeners that
     * a new button has been pressed
     *
     * @param button Button pressed
     */
    void notifyDeviceButtonListeners(Button button);

    /**
     * External method to notify all the registered device button listeners that
     * a new acceleration event is fired.
     *
     * @param acceleration Acceleration event
     */
    void notifyAccelerationListeners(Acceleration acceleration);

    /**
     * External method to notify all the registered device button listeners that
     * a new geolocation event is fired.
     *
     * @param geolocation Geolocation event
     */
    void notifyGeolocationListeners(Geolocation geolocation);

    /**
     * External method to notify all the registered device button listeners that
     * a new Network Status event is fired.
     *
     * @param networkEvent Network event
     */
    void notifyNetworkStatusListeners(NetworkEvent networkEvent);

    /**
     * Obtains the default orientation of the device/display. If no default orientation is available on
     * the platform, this method will return the current orientation. To capture device or display orientation
     * changes please use the IDevice and IDisplay functions and listeners API respectively.
     *
     * @return The default orientation for the device/display.
     */
    ICapabilitiesOrientation getOrientationDefault();

    /**
     * Provides the device/display orientations supported by the platform. A platform will usually
     * support at least one orientation. This is usually PortaitUp.
     *
     * @return The orientations supported by the device/display of the platform.
     */
    ICapabilitiesOrientation[] getOrientationsSupported();

    /**
     * Determines whether a specific hardware button is supported for interaction.
     *
     * @param type Type of feature to check.
     * @return true is supported, false otherwise.
     */
    boolean hasButtonSupport(ICapabilitiesButton type);

    /**
     * Determines whether a specific Communication capability is supported by
     * the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    boolean hasCommunicationSupport(ICapabilitiesCommunication type);

    /**
     * Determines whether a specific Data capability is supported by the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    boolean hasDataSupport(ICapabilitiesData type);

    /**
     * Determines whether a specific Media capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    boolean hasMediaSupport(ICapabilitiesMedia type);

    /**
     * Determines whether a specific Net capability is supported by the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    boolean hasNetSupport(ICapabilitiesNet type);

    /**
     * Determines whether a specific Notification capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    boolean hasNotificationSupport(ICapabilitiesNotification type);

    /**
     * Determines whether the device/display supports a given orientation.
     *
     * @param orientation Orientation type.
     * @return True if the given orientation is supported, false otherwise.
     */
    boolean hasOrientationSupport(ICapabilitiesOrientation orientation);

    /**
     * Determines whether a specific Sensor capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    boolean hasSensorSupport(ICapabilitiesSensor type);

    /**
     * Returns if the device has been modified in anyhow
     *
     * @return true if the device has been modified; false otherwise
     */
    boolean isDeviceModified();
}
