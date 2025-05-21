public enum Size {
    SMALL(0.7f),
    MEDIUM(1.0f),
    LARGE(1.3f);

    private final float priceMultiplier;

    Size(float priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public float getPriceMultiplier() {
        return priceMultiplier;
    }
}