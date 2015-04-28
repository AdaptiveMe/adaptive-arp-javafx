package me.adaptive.tools.nibble.common;

import me.adaptive.tools.nibble.common.types.CompatibilityType;

public abstract class AbstractOs {

    private String osName;
    private String osVersion;
    private String osVendor;
    private CompatibilityType osType;
    private String fxView;

    public AbstractOs(String osName, String osVersion, String osVendor, CompatibilityType osType) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.osVendor = osVendor;
        this.osType = osType;
    }

    public String getOsName() {
        return osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getOsVendor() {
        return osVendor;
    }

    public CompatibilityType getOsType() {
        return osType;
    }

    public final String getFxView() {
        return fxView;
    }

    protected final void setFxView(String fxView) {
        this.fxView = fxView;
    }

    @Override
    public String toString() {
        return "AbstractOs{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osVendor='" + osVendor + '\'' +
                ", osType=" + osType +
                '}';
    }
}