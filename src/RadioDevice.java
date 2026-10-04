public final class RadioDevice implements Device {
    private final DeviceState state = new DeviceState();

    @Override
    public String applySettings(int requestedVolume) {
        return state.applySettings("RADIO", requestedVolume);
    }
}
