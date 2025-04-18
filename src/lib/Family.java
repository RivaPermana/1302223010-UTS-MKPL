package lib;

import java.util.List;

public class Family {

    private MaritalStatus maritalStatus;
    private List<Child> children;

    public Family(MaritalStatus maritalStatus, List<Child> children) {
        this.maritalStatus = maritalStatus;
        this.children = children;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public List<Child> getChildren() {
        return children;
    }

    public int getNumberOfChildren() {
        return children.size();
    }

    public boolean isMarried() {
        return maritalStatus == MaritalStatus.MARRIED;
    }
}
