package hospital;

class VisitNode {
    Visit visit;
    VisitNode next;

    VisitNode(Visit visit) {
        this.visit = visit;
    }
}

public class VisitLinkedList {
    private VisitNode head;

    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);

        if (head == null) {
            head = newNode;
            return;
        }

        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        VisitNode current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public Visit searchVisit(int visitId) {
        VisitNode current = head;

        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }

        return null;
    }

    public void displayHistory() {
        if (head == null) {
            System.out.println("Visit history is empty.");
            return;
        }

        System.out.println("\nPatient visit history:");
        VisitNode current = head;

        while (current != null) {
            System.out.println(current.visit);
            current = current.next;
        }
    }
}
