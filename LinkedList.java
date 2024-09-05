package Hospital.Managment.System;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {
    private static Node head;

    private static class Node {
        New_Patient.Patient data;
        Node next;

        Node(New_Patient.Patient data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void addFirst(New_Patient.Patient data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public static void addLast(New_Patient.Patient data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public static void removeFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    public static void removeLast() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
    }

    public static void remove(New_Patient.Patient data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && !temp.next.data.equals(data)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public static List<New_Patient.Patient> getAllPatients() {
        List<New_Patient.Patient> patients = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            patients.add(temp.data);
            temp = temp.next;
        }
        return patients;
    }

    public static boolean isEmpty() {
        return head == null;
    }
}
