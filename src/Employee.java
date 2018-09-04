import java.io.*;
import java.util.Objects;

enum Job {PROGRAMMER, STUDENT, TEACHER, BABYSITTER, CLEANER, HR, DIRECTOR}

public class Employee implements Serializable, Externalizable {
    private String name;
    private Integer age;
    private Job job;
    private Integer salary;


    public Employee() {
    }

    public Employee(String name, Integer age, Job job, Integer salary){
        setName(name);
        setAge(age);
        setJob(job);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job=" + job +
                ", salary=" + salary +
                '}';
    }

    public void writeExternal(ObjectOutput out) throws IOException{
        try {
            out.writeObject(this.name);
            out.writeObject(this.age);
            out.writeObject(this.job);
            out.writeObject(this.salary);
        }catch (IOException e){
            throw e;
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        try {
            this.name = (String) in.readObject();
            this.age = (Integer) in.readObject();
            this.job = (Job) in.readObject();
            this.salary = (Integer) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            throw e;
        }
    }
}
