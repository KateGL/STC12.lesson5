import java.io.*;

public class EmployeeCollectionExt extends EmployeeCollection implements Externalizable {

    @Override
    boolean writeToFile(){
        try {
            FileOutputStream outStream = new FileOutputStream(this.fileName);
            ObjectOutputStream outStreamObject = new ObjectOutputStream(outStream);
            this.writeExternal(outStreamObject);
            outStreamObject.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    EmployeeCollection readFromFile(){
        try {
            FileInputStream inpStream = new FileInputStream(this.fileName);
            ObjectInputStream inpStreamObject = new ObjectInputStream(inpStream);
            EmployeeCollectionExt readedCollection = new EmployeeCollectionExt();
            readedCollection.readExternal(inpStreamObject);
            inpStreamObject.close();
            return readedCollection;
        } catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeExternal(ObjectOutput out) throws IOException{
        try {
            out.writeObject(this.list.size());
            for(int i =0; i <this.list.size(); i++){
                Employee empl = this.list.get(i);
                empl.writeExternal(out);
            }
            out.writeObject(this.getSalarySumm());
        }catch (IOException e){
            throw e;
        }

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        try {
            int listSize = (int) in.readObject();
            this.list.clear();

            for(int i =0; i < listSize; i++){
                Employee empl = new Employee();
                empl.readExternal(in);
                this.list.add(empl);
            }
            Integer salarySumm = (Integer) in.readObject();
            System.out.println("readed SalarySum:"+salarySumm);

        }catch (IOException | ClassNotFoundException e){
            throw e;
        }
    }

}
