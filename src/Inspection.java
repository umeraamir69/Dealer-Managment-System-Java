import java.time.LocalDate;

public class Inspection {
    private String inspectionID;
    private String registrationNumber;
    private LocalDate inspectionDate;
    private String remarks;
    private float outerCondition;
    private float innerCondition;
    private boolean accident;
    private String ownerStatus;
    private String documentsStatus;

    public void setInspectionID(String inspectionID) {
        this.inspectionID = inspectionID;
    }

    public String getInspectionID() {
        return inspectionID;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setOuterCondition(float outerCondition) {
        this.outerCondition = outerCondition;
    }

    public float getOuterCondition() {
        return outerCondition;
    }

    public void setInnerCondition(float innerCondition) {
        this.innerCondition = innerCondition;
    }

    public float getInnerCondition() {
        return innerCondition;
    }

    public void setAccident(boolean accident) {
        this.accident = accident;
    }

    public boolean getAccident() {
        return accident;
    }

    public void setOwnerStatus(String ownerStatus) {
        this.ownerStatus = ownerStatus;
    }

    public String getOwnerStatus() {
        return ownerStatus;
    }

    public void setDocumentsStatus(String documentsStatus) {
        this.documentsStatus = documentsStatus;
    }

    public String getDocumentsStatus() {
        return documentsStatus;
    }

    public void addInspection(String inspectionID, String registrationNumber, LocalDate inspectionDate, String remarks,
                              float outerCondition, float innerCondition, boolean accident, String ownerStatus, String documentsStatus) {
        setInspectionID(inspectionID);
        setRegistrationNumber(registrationNumber);
        setInspectionDate(inspectionDate);
        setRemarks(remarks);
        setOuterCondition(outerCondition);
        setInnerCondition(innerCondition);
        setAccident(accident);
        setOwnerStatus(ownerStatus);
        setDocumentsStatus(documentsStatus);
    }

    public void updateInspection(String remarks, float outerCondition, float innerCondition, boolean accident,
                                 String ownerStatus, String documentsStatus) {
        setRemarks(remarks);
        setOuterCondition(outerCondition);
        setInnerCondition(innerCondition);
        setAccident(accident);
        setOwnerStatus(ownerStatus);
        setDocumentsStatus(documentsStatus);
    }
}
