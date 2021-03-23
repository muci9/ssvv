package com.ssvv.Service.TxtFileService;
import com.ssvv.Domain.*;
import com.ssvv.Repository.TxtFileRepository.NotaFileRepo;
import com.ssvv.Domain.Nota;

public class NotaService extends AbstractService<Integer, Nota> {
    public NotaService(NotaFileRepo notaRepo){
        super(notaRepo);
    }
}
