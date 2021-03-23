package com.ssvv.Repository.MemoryRepository;
import com.ssvv.Validator.IValidator;
import com.ssvv.Domain.Nota;

public class NotaRepo extends AbstractCrudRepo<Integer,Nota > {
    public NotaRepo(IValidator<Nota> v){ super(v);
    }
}