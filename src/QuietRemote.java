public final class QuietRemote extends Remote {
    public QuietRemote(String id, Device implementation) {
        super(id, 5, implementation);
    }

    @Override
    public String execute() {
        return applyPreset();
    }
}
