package modelo;

import dto.*;
import java.util.HashMap;
import java.util.List;
import modelo.Student;


public class Test {
   public static void main(String[] args) {
       Student ram = new Student(1, "Ram", "Street 9, Cupertino");
    Student shyam = new Student(2, "Shyam", "Z bridge, Pune");
    Student gopi = new Student(3, "Gopi", "Street 10, Mumbai");

    HashMap<String, List<Student>> context = new HashMap<>();
    StudentDatabase studentDatabase = new StudentDatabase();
    UnitOfWork unitOfwork = new UnitOfWork(context, studentDatabase);

    unitOfwork .registerNew(ram);
    unitOfwork .registerModified(shyam);
    unitOfwork .registerDeleted(gopi);
    unitOfwork .commit();
}
 
}
