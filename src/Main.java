public class Main {


    public static void main(String[] args) {
        String default_filename = "employee.txt";
        EmployeeCollection emplCollection = new EmployeeCollection();
        emplCollection.setFileName(default_filename);

        System.out.println("----------------------------");
        System.out.println("Test for Serializeble: ");
        System.out.println("----------------------------");
        mainTest(emplCollection);

        System.out.println("----------------------------");
        System.out.println("Test for Externalizable: ");
        System.out.println("----------------------------");
        EmployeeCollectionExt emplCollectionExt = new EmployeeCollectionExt();
        emplCollection.setFileName(default_filename);
        mainTest(emplCollectionExt);

    }

    public static void mainTest(EmployeeCollection emplCollection) {

        emplCollection.save(new Employee("James", 18, Job.STUDENT, 18_000));
        EmployeeCollection rColl = emplCollection.readFromFile();
        System.out.println("readed: "+rColl.toString());

        emplCollection.save(new Employee("Kate", 35, Job.PROGRAMMER, 120_000 ));
        rColl = emplCollection.readFromFile();
        System.out.println("readed: "+rColl.toString());

        emplCollection.save(new Employee("Mark", 30, Job.PROGRAMMER, 70_000));
        rColl = emplCollection.readFromFile();
        System.out.println("readed: "+rColl.toString());

        System.out.println(emplCollection.toString());
        emplCollection.saveOrUpdate(new Employee("Mark", 31, Job.PROGRAMMER, 71_000));
        System.out.println(emplCollection.toString());
        rColl = emplCollection.readFromFile();
        System.out.println("readed: "+rColl.toString());

        emplCollection.changeAllWork(Job.PROGRAMMER, Job.TEACHER);
        System.out.println(emplCollection.toString());
        rColl = emplCollection.readFromFile();
        System.out.println("readed: "+rColl.toString());
        System.out.println("Get PROGRAMMERs: "+emplCollection.getByJob(Job.PROGRAMMER));
        System.out.println("Get TEACHERs: "+emplCollection.getByJob(Job.TEACHER));
    }

}
