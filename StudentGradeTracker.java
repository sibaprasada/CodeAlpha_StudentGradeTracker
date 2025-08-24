import java.util.*;

public class StudentGradeTracker {

    static class Student {
        String name;
        String grade;

        Student(String name, String grade) {
            this.name = name;
            this.grade = grade.toUpperCase();
        }
    }

    static Map<String, Integer> gradeToScore = new HashMap<>();
    static Map<Integer, String> scoreToGrade = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        // Grade to Score Mapping
        initializeGradeMappings();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter student name: ");
            String name = sc.nextLine();

            String grade;
            while (true) {
                System.out.print("Enter grade (A1, A2, B1, B2, C, D, F): ");
                grade = sc.nextLine().toUpperCase();
                if (gradeToScore.containsKey(grade)) break;
                System.out.println("Invalid grade. Try again.");
            }

            students.add(new Student(name, grade));
        }

        displaySummary(students);
        sc.close();
    }

    static void initializeGradeMappings() {
        gradeToScore.put("A1", 100);
        gradeToScore.put("A2", 90);
        gradeToScore.put("B1", 80);
        gradeToScore.put("B2", 70);
        gradeToScore.put("C", 60);
        gradeToScore.put("D", 50);
        gradeToScore.put("F", 30);

        for (Map.Entry<String, Integer> entry : gradeToScore.entrySet()) {
            scoreToGrade.put(entry.getValue(), entry.getKey());
        }
    }

    static void displaySummary(ArrayList<Student> students) {
        System.out.println("\n--- Student Summary Report ---");
        int totalScore = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        for (Student s : students) {
            int score = gradeToScore.get(s.grade);
            totalScore += score;

            if (score > highest) {
                highest = score;
                topStudent = s.name;
            }
            if (score < lowest) {
                lowest = score;
                lowStudent = s.name;
            }

            System.out.println("Name: " + s.name + ", Grade: " + s.grade);
        }

        double average = totalScore / (double) students.size();
        String averageGrade = getClosestGrade((int) Math.round(average));

        System.out.println("\nAverage Score: " + average + " (" + averageGrade + ")");
        System.out.println("Highest Score: " + highest + " (" + scoreToGrade.get(highest) + ") by " + topStudent);
        System.out.println("Lowest Score: " + lowest + " (" + scoreToGrade.get(lowest) + ") by " + lowStudent);
    }

    static String getClosestGrade(int score) {
        int closest = 0;
        for (int val : scoreToGrade.keySet()) {
            if (Math.abs(score - val) < Math.abs(score - closest) || closest == 0)
                closest = val;
        }
        return scoreToGrade.get(closest);
    }
}
