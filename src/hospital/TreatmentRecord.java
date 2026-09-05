package hospital;

public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String doctorName;
    private String treatment;
    private String treatmentDate;

    public TreatmentRecord(int patientId, String patientName,
                           String doctorName, String treatment,
                           String treatmentDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.treatment = treatment;
        this.treatmentDate = treatmentDate;
    }

    public int getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               ", Name: " + patientName +
               ", Doctor: " + doctorName +
               ", Treatment: " + treatment +
               ", Date: " + treatmentDate;
    }
}
