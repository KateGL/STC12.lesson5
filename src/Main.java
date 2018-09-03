public class Main {


    public static void main(String[] args) {
        String default_filename = "employee.txt";
        EmployeeCollection emplCollection = new EmployeeCollection();
        emplCollection.save(new Employee("James", 18, Job.STUDENT, 18_000));
        emplCollection.save(new Employee("Kate", 35, Job.PROGRAMMER, 120_000 ));
        emplCollection.save(new Employee("Mark", 30, Job.PROGRAMMER, 70_000));

        System.out.println(emplCollection.toString());
        emplCollection.setFilename(default_filename);
        emplCollection.saveOrUpdate(new Employee("Mark", 31, Job.PROGRAMMER, 71_000));
        System.out.println(emplCollection.toString());

        emplCollection.changeAllWork(Job.PROGRAMMER, Job.TEACHER);
        System.out.println(emplCollection.toString());

    }

}
