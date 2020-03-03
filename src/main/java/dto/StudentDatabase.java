package dto;

import modelo.Student;

public class StudentDatabase {
    public void insert(Student student) {
        System.out.println("insert student in DB " + student);
  }

  public void modify(Student student) {
    System.out.println("update student in DB " + student);
  }

  public void delete(Student student) {
    System.out.println("deete student in DB " + student);
  }
    
}
