package me.adaptive.tools.nibble.common;

import me.adaptive.tools.nibble.common.types.CompatibilityType;

public abstract class AbstractDevice {

    private String deviceName;
    private String deviceModel;
    private String deviceVendor;
    private CompatibilityType deviceType;
    private String fxView;
    private String osType;

    public AbstractDevice(String deviceName, String deviceModel, String deviceVendor, CompatibilityType deviceType) {
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.deviceVendor = deviceVendor;
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceVendor() {
        return deviceVendor;
    }

    public CompatibilityType getDeviceType() {
        return deviceType;
    }

    public final String getFxView() {
        return fxView;
    }

    protected final void setFxView(String fxView) {
        this.fxView = fxView;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        return "AbstractDevice{" +
                "deviceVendor='" + deviceVendor + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                '}';
    }
}