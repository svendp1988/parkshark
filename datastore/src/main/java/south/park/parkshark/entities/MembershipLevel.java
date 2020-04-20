package south.park.parkshark.entities;

public enum MembershipLevel {
    BRONZE(0,0,4), SILVER(10,20,6), GOLD(40,30,24);

    private final int maxAllocationTime;
    private final int pricePerHourReductionRate;
    private final int monthlyCost;

    MembershipLevel(int monthly, int pricePerHourReductionPercentage, int maxAllocationTime) {
        this.monthlyCost = monthly;
        this.pricePerHourReductionRate = pricePerHourReductionPercentage;
        this.maxAllocationTime = maxAllocationTime;
    }

    public int getMaxAllocationTime() {
        return maxAllocationTime;
    }

    public int getPricePerHourReductionRate() {
        return pricePerHourReductionRate;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }
}
