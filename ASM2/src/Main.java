import Student.Student;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Student> students;
    private Scanner scanner;

    public Main() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble();
        students.add(new Student(id, name, marks));
    }

    public void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        int id = scanner.nextInt();
        for (Student student : students) {
            if (student.id == id) {
                System.out.print("Enter new name: ");
                scanner.nextLine();  // Consume newline
                student.name = scanner.nextLine();
                System.out.print("Enter new marks: ");
                student.marks = scanner.nextDouble();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        students.removeIf(student -> student.id == id);
    }

    public void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();
        for (Student student : students) {
            if (student.id == id) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void sortstudentbyMergeSort() {
        mergeSort(students, 0, students.size() - 1);
    }

    private static void mergeSort(ArrayList<Student> list, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(list, left, middle);
            mergeSort(list, middle + 1, right);
            merge(list, left, middle, right);
        }
    }

    private static void merge(ArrayList<Student> list, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        ArrayList<Student> L = new ArrayList<>();
        ArrayList<Student> R = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            L.add(list.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            R.add(list.get(middle + 1 + j));
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L.get(i).marks >= R.get(j).marks) {
                list.set(k, L.get(i));
                i++;
            } else {
                list.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, L.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, R.get(j));
            j++;
            k++;
        }
    }

    public void sortStudentsByQuickSort() {
        quickSort(0, students.size() - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = students.get(high).marks;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students.get(j).marks >= pivot) {  // Change comparison for descending order
                i++;
                // Swap
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        // Swap
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    public static void main(String[] args) {
        Main sm = new Main();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("=== Student Management System ===");
            System.out.println("\n1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Search Student");
            System.out.println("6. Sort Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    sm.addStudent();
                    break;
                case 2:
                    sm.editStudent();
                    break;
                case 3:
                    sm.deleteStudent();
                    break;
                case 4:
                    sm.displayStudents();
                    break;
                case 5:
                    sm.searchStudent();
                    break;
                case 6:
                    System.out.println("Choose Sorting Method:");
                    System.out.println("1. Merge Sort");
                    System.out.println("2. Quick Sort");
                    int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        sm.sortstudentbyMergeSort();
                        System.out.println("Students sorted by Merge Sort");
                    } else if (sortChoice == 2) {
                        sm.sortStudentsByQuickSort();
                        System.out.println("Students sorted by Quick Sort");
                    } else {
                        System.out.println("Invalid choice. Returning to main menu.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
