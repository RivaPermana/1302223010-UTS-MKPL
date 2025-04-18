package lib;

public class Family {
    private final MaritalStatus maritalStatus;
    private final int numberOfChildren;

    public Family(MaritalStatus maritalStatus, int numberOfChildren) {
        this.maritalStatus = maritalStatus;
        this.numberOfChildren = Math.min(numberOfChildren, 3); // max 3 anak sesuai ketentuan pajak
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public boolean isMarried() {
        return maritalStatus == MaritalStatus.MARRIED;
    }
}
