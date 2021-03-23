package com.ssvv.Service.TxtFileService;
import com.ssvv.Domain.*;
import com.ssvv.Repository.TxtFileRepository.StudentFileRepo;
import com.ssvv.Domain.Student;

public class StudentService extends AbstractService<String, Student> {
    //StudentFileRepo stdRepo;
    public StudentService(StudentFileRepo stdRepo){
        super(stdRepo);
    }
    /*
    @Override
    public Student extractEntity(String[] info){
        return new Student("","",2,"","");
    }
    */
}


