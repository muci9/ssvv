package com.ssvv.Service.XMLFileService;

import com.ssvv.Domain.TemaLab;
import com.ssvv.Exceptions.ValidatorException;
import com.ssvv.Repository.XMLFileRepository.TemaLabXMLRepo;

public class TemaLabXMLService extends AbstractXMLService<Integer,TemaLab>{
    private TemaLabXMLRepo xmlrepo;

    public TemaLabXMLService(TemaLabXMLRepo xmlrepo)  {
        super(xmlrepo);
    }

    public void prelungireTemaLab(String nr,String descr,String sl,String sp,int sc) throws ValidatorException {
        if(sc<=Integer.parseInt(sp)){
            String sln=Integer.toString(Integer.parseInt(sl)+1) ;
            String[] params={nr,descr,sln,sp};
            update(params);
        }

    }
    @Override
    protected TemaLab extractEntity(String[] params){
        return new TemaLab(Integer.parseInt(params[0]),params[1],Integer.parseInt(params[2]),Integer.parseInt(params[3]));
    }

}