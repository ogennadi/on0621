public enum Tool {
    LADW(ToolType.LADDER),
    CHNS(ToolType.CHAINSAW),
    JAKR(ToolType.JACKHAMMER),
    JAKD(ToolType.JACKHAMMER);

    private final ToolType type;

    Tool(ToolType type) {
        this.type = type;
    }

    public ToolType getToolType() {
        return type;
    }
}
