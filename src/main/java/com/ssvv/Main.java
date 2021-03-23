package com.ssvv;

import com.ssvv.Exceptions.*;
import com.ssvv.Repository.XMLFileRepository.NotaXMLRepo;
import com.ssvv.Repository.XMLFileRepository.StudentXMLRepo;
import com.ssvv.Repository.XMLFileRepository.TemaLabXMLRepo;
import com.ssvv.Service.XMLFileService.NotaXMLService;
import com.ssvv.Service.XMLFileService.StudentXMLService;
import com.ssvv.Service.XMLFileService.TemaLabXMLService;
import com.ssvv.Validator.*;
import com.ssvv.UI.*;
import com.ssvv.Exceptions.ValidatorException;
import com.ssvv.UI.ui;
import com.ssvv.Validator.NotaValidator;
import com.ssvv.Validator.StudentValidator;
import com.ssvv.Validator.TemaLabValidator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ValidatorException {
        //System.out.println("Hello World!");
        StudentValidator vs=new StudentValidator();
        TemaLabValidator vt=new TemaLabValidator();
        NotaValidator vn=new NotaValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"NotaXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        NotaXMLService ntsrv=new NotaXMLService(ntrepo);
        ui ui=new ui(stsrv,tmsrv,ntsrv);
        ui.run();
    }
}