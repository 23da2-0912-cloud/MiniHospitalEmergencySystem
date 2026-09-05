package hospital;

class StackNode {
    TreatmentRecord record;
    StackNode next;

    StackNode(TreatmentRecord record) {
        this.record = record;
    }
}

public class TreatmentStack {
    private StackNode top;

    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
    }

    public TreatmentRecord pop() {
        if (top == null) {
            return null;
        }

        TreatmentRecord record = top.record;
        top = top.next;
        return record;
    }

    public void display() {
        if (top == null) {
            System.out.println("Treatment stack is empty.");
            return;
        }

        System.out.println("\nCompleted treatment records (top to bottom):");
        StackNode current = top;

        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
    }
}
