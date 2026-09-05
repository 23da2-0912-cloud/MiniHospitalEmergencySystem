package hospital;

class PatientBSTNode {
    Patient patient;
    PatientBSTNode left;
    PatientBSTNode right;

    PatientBSTNode(Patient patient) {
        this.patient = patient;
    }
}

public class PatientBST {
    private PatientBSTNode root;

    public void insert(Patient patient) {
        root = insertRecursive(root, patient);
    }

    private PatientBSTNode insertRecursive(PatientBSTNode node, Patient patient) {
        if (node == null) {
            return new PatientBSTNode(patient);
        }

        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRecursive(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRecursive(node.right, patient);
        }

        return node;
    }

    public Patient search(int patientId) {
        PatientBSTNode current = root;

        while (current != null) {
            if (patientId == current.patient.getPatientId()) {
                return current.patient;
            } else if (patientId < current.patient.getPatientId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public void delete(int patientId) {
        root = deleteRecursive(root, patientId);
    }

    private PatientBSTNode deleteRecursive(PatientBSTNode node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRecursive(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRecursive(node.right, patientId);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            PatientBSTNode smallest = findSmallest(node.right);
            node.patient = smallest.patient;
            node.right = deleteRecursive(node.right, smallest.patient.getPatientId());
        }

        return node;
    }

    private PatientBSTNode findSmallest(PatientBSTNode node) {
        PatientBSTNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }

        System.out.println("\nPatients in ascending Patient ID order:");
        inOrder(root);
    }

    private void inOrder(PatientBSTNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.patient);
            inOrder(node.right);
        }
    }
}
