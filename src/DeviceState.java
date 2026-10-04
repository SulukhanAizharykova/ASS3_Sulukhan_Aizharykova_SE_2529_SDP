public final class DeviceState {
    private boolean poweredOn;
    private int volume;

    public String applySettings(String deviceType, int requestedVolume) {
        if (requestedVolume < 0 || requestedVolume > 100) {
            throw new IllegalArgumentException("Volume must be between 0 and 100");
        }
        poweredOn = true;
        volume = requestedVolume;
        return deviceType + " power=" + (poweredOn ? "ON" : "OFF") + " volume=" + volume;
    }
}
