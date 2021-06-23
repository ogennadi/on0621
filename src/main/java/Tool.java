public enum Tool {
    LADW(ToolType.LADDER, "Werner"),
    CHNS(ToolType.CHAINSAW, "Stihl"),
    JAKR(ToolType.JACKHAMMER, "Ridgid"),
    JAKD(ToolType.JACKHAMMER, "DeWalt");

    private final ToolType type;
    private final String brand;

    Tool(ToolType type, String brand) {
        this.type = type;
        this.brand = brand;
    }

    public ToolType getToolType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }
}
