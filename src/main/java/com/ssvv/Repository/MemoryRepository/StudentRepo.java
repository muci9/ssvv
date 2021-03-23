package com.ssvv.Repository.MemoryRepository;
import com.ssvv.Validator.IValidator;
import com.ssvv.Domain.Student;

public class StudentRepo extends AbstractCrudRepo<String, Student> {
    public StudentRepo(IValidator<Student> v){ super(v);
    }
}