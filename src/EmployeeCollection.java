import java.util.ArrayList;
import java.util.List;

public class EmployeeCollection {
    ArrayList<Employee> list = new ArrayList<Employee>();

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    String filename = "employee.txt";

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

    boolean writeToFile(){
        return false;
    }

    EmployeeCollection readFromFile(){
        return null;
    }
}
