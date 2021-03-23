package com.ssvv.tests;

import com.ssvv.Repository.XMLFileRepository.StudentXMLRepo;
import com.ssvv.Service.XMLFileService.StudentXMLService;
import com.ssvv.Validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;

public class StudentXmlServiceTests {

    private StudentXMLService studentXMLService;
    private StudentXMLRepo studentXMLRepo;
    private StudentValidator studentValidator;
    private final String studentXmlServiceTestFile = "studentXmlServiceTestFile.xml";


    @Before
    public void setup() {
        studentValidator = new StudentValidator();
        studentXMLRepo = new StudentXMLRepo(studentValidator, studentXmlServiceTestFile);
        studentXMLService = new StudentXMLService(studentXMLRepo);
    }

    @Test
    public void addStudentWithAllFieldsValid() {

    }

    @Test
    public void addStudentWithInvalidId() {

    }
}
