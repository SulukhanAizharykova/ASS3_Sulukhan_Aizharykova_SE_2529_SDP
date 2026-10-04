import java.util.Objects;

public abstract class Remote {
    private final String id;
    private final int volumePreset;
    private Device implementation; // The interface-typed Bridge link.

    protected Remote(String id, int volumePreset, Device implementation) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Remote ID must not be blank");
        }
        this.id = id;
        this.volumePreset = volumePreset;
        setImplementation(implementation);
    }

    public final String getId() {
        return id;
    }

    public final int getVolumePreset() {
        return volumePreset;
    }

    public final void setImplementation(Device implementation) {
        this.implementation = Objects.requireNonNull(implementation, "implementation");
    }

    protected final String applyPreset() {
        return implementation.applySettings(volumePreset);
    }

    public abstract String execute();
}
