public final class BasicRemote extends Remote {
    public BasicRemote(String id, Device implementation) {
        super(id, 30, implementation);
    }

    @Override
    public String execute() {
        return applyPreset();
    }
}
