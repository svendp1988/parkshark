package south.park.parkshark.entities;

public enum MembershipLevels {
    BRONZE(0,0,4), SILVER(10,20,6), GOLD(40,30,24);

    private final int maxAllocationTime;
    private final int pricePerHourReductionPercentage;
    private final int monthlyCost;

    MembershipLevels(int monthlyCost, int pricePerHourReductionPercentage, int maxAllocationTime) {
        this.monthlyCost = monthlyCost;
        this.pricePerHourReductionPercentage = pricePerHourReductionPercentage;
        this.maxAllocationTime = maxAllocationTime;
    }

    public int getMaxAllocationTime() {
        return maxAllocationTime;
    }

    public int getPricePerHourReductionPercentage() {
        return pricePerHourReductionPercentage;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }
}
