package com.ssvv.Service.TxtFileService;
import com.ssvv.Exceptions.ValidatorException;
import com.ssvv.Domain.*;
import com.ssvv.Repository.TxtFileRepository.TemaLabFileRepo;
import com.ssvv.Domain.TemaLab;

public class TemaLabService extends AbstractService<Integer, TemaLab> {
    //StudentFileRepo stdRepo;
    public TemaLabService(TemaLabFileRepo tmLbRepo){
        super(tmLbRepo);
    }

    public void prelungireTemaLab(String nr,String descr,String sl,String sp,int sc) throws ValidatorException {
        if(sc<=Integer.parseInt(sp)){
            String sln=Integer.toString(Integer.parseInt(sl)+1) ;
            String[] params={nr,descr,sln,sp};
            upd(params);
        }

    }
    /*
    @Override
    public Student extractEntity(String[] info){
        return new Student("","",2,"","");
    }
    */
}
