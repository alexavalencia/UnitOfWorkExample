package dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Student;
public class UnitOfWork implements IUnitOfWork<Student>{

    private Map<String, List<Student>> context;
    private StudentDatabase studentDatabase;
    
    public UnitOfWork (Map<String, List<Student>> context, StudentDatabase studentDatabase) {
    this.context = context;
    this.studentDatabase = studentDatabase;
  }

    @Override
    public void registerNew( Student student) {
        System.out.println("User create new student");
        register(student, IUnitOfWork.INSERT);
     }

    @Override
    public void registerModified(Student student) {
        System.out.println("User update  student");
        register(student, MODIFY);
    }

    @Override
    public void registerDeleted(Student student) {
        System.out.println("User delete student");
        register(student, DELETE);
    }

    @Override
    public void commit() {
        if (context == null || context.size() == 0) {
      return;
    }
    System.out.println("Commit started");
    if (context.containsKey(IUnitOfWork.INSERT)) {
        
      commitInsert();
    }

    if (context.containsKey(IUnitOfWork.MODIFY)) {
      commitModify();
    }
    if (context.containsKey(IUnitOfWork.DELETE)) {
      commitDelete();
    }
    System.out.println("Commit finished.");
    }
    private void register(Student student, String operation) {
        List<Student> studentsToOperate = context.get(operation);
        if (studentsToOperate == null) {
          studentsToOperate = new ArrayList<>();
        }
        studentsToOperate.add(student);
        context.put(operation, studentsToOperate);
    }
    private void commitInsert() {
    List<Student> studentsToBeInserted = context.get(IUnitOfWork.INSERT);
    for (Student student : studentsToBeInserted) {
      System.out.println("Saving {} to database.");
      studentDatabase.insert(student);
    }
  }

  private void commitModify() {
    List<Student> modifiedStudents = context.get(IUnitOfWork.MODIFY);
    for (Student student : modifiedStudents) {
      System.out.println("Modifying {} to database.");
      studentDatabase.modify(student);
    }
  }

  private void commitDelete() {
    List<Student> deletedStudents = context.get(IUnitOfWork.DELETE);
    for (Student student : deletedStudents) {
        System.out.println("Deleting {} to database.");
      studentDatabase.delete(student);
    }
  }
    
}
