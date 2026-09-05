package hospital;

class QueueNode {
    Patient patient;
    QueueNode next;

    QueueNode(Patient patient) {
        this.patient = patient;
    }
}

public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;

    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Patient dequeue() {
        if (front == null) {
            return null;
        }

        Patient patient = front.patient;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return patient;
    }

    public void display() {
        if (front == null) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        System.out.println("\nPatients currently waiting:");
        QueueNode current = front;

        while (current != null) {
            System.out.println(current.patient);
            current = current.next;
        }
    }
}
