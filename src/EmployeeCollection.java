import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCollection  implements Serializable {
    ArrayList<Employee> list = new ArrayList<Employee>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    String fileName = "employee.txt";

    /***
     Считаем что у нас name+age уникальные ключ
     *
     */

    boolean save(Employee empl){
        if(this.list.add(empl)){
            return this.writeToFile();
        }
        return false;
    }

    boolean delete(Employee empl){
        if(this.list.remove(empl)){
            return this.writeToFile();
        }
        return false;
    }

    //
    Employee getByName(String name){
        for(Employee empl: this.list){
            if(empl.getName() == name){
                return empl;
            }
        }
        return null;
    }

    List<Employee> getByJob(Job job){
        List<Employee> listByJob = new ArrayList<Employee>();
        for(Employee empl: this.list){
            if(empl.getJob() == job){
                listByJob.add(empl);
            }
        }
        return listByJob;
    }


    boolean saveOrUpdate(Employee empl){
        Employee emplCurrent = getByName(empl.getName());
        if(emplCurrent == null){
            return this.save(empl);
        }
        //надо заменить у текущего все поля
        emplCurrent.setAge(empl.getAge());
        emplCurrent.setJob(empl.getJob());
        emplCurrent.setSalary(empl.getSalary());
        return this.writeToFile();
    }

    boolean changeAllWork(Job currentJob, Job newJob){
        boolean success = false;
        for(Employee empl: this.list){
            if(empl.getJob() == currentJob){
                empl.setJob(newJob);
                success = true;
            }
        }

        if(success){
            return this.writeToFile();
        }
        return false;
    }

    Integer getSalarySumm(){
        Integer result = 0;
        for(Employee empl: this.list){
            result += empl.getSalary();
        }
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeCollection{" +
                "list=" + list +
                '}';
    }

    boolean writeToFile() {
        try {
            FileOutputStream outStream = new FileOutputStream(this.fileName);
            ObjectOutputStream outStreamObject = new ObjectOutputStream(outStream);
            outStreamObject.writeObject(this);
            outStreamObject.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    EmployeeCollection readFromFile(){
        try {
            FileInputStream inpStream = new FileInputStream(this.fileName);
            ObjectInputStream inpStreamObject = new ObjectInputStream(inpStream);
            EmployeeCollection readedCollection =  (EmployeeCollection)inpStreamObject.readObject();
            inpStreamObject.close();
            return readedCollection;
        } catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



