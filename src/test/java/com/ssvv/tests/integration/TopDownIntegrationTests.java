package com.ssvv.tests.integration;

import com.ssvv.Domain.Nota;
import com.ssvv.Domain.Student;
import com.ssvv.Domain.TemaLab;
import com.ssvv.Exceptions.ValidatorException;
import com.ssvv.Repository.XMLFileRepository.NotaXMLRepo;
import com.ssvv.Repository.XMLFileRepository.StudentXMLRepo;
import com.ssvv.Repository.XMLFileRepository.TemaLabXMLRepo;
import com.ssvv.Service.XMLFileService.NotaXMLService;
import com.ssvv.Service.XMLFileService.StudentXMLService;
import com.ssvv.Service.XMLFileService.TemaLabXMLService;
import com.ssvv.Validator.NotaValidator;
import com.ssvv.Validator.StudentValidator;
import com.ssvv.Validator.TemaLabValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TopDownIntegrationTests {

    private StudentValidator studentValidator;
    private TemaLabValidator temaLabValidator;
    private NotaValidator notaValidator;

    private StudentXMLRepo studentRepo;
    private TemaLabXMLRepo temaLabRepo;
    private NotaXMLRepo notaRepo;

    private StudentXMLService studentService;
    private TemaLabXMLService temaLabService;
    private NotaXMLService notaService;

    private final String studentFilename = "student-integration-tests.xml";
    private final String temaLabFilename = "tema-lab-integration-tests.xml";
    private final String notaFilename = "nota-integration-tests.xml";

    @Before
    public void setup() {
        studentValidator = new StudentValidator();
        studentRepo = new StudentXMLRepo(studentValidator, studentFilename);
        studentService = new StudentXMLService(studentRepo);

        temaLabValidator = new TemaLabValidator();
        temaLabRepo = new TemaLabXMLRepo(temaLabValidator, temaLabFilename);
        temaLabService = new TemaLabXMLService(temaLabRepo);

        notaValidator = new NotaValidator();
        notaRepo = new NotaXMLRepo(notaValidator, notaFilename);
        notaService = new NotaXMLService(notaRepo);
    }

    @Test
    public void addStudentTest() {
        try {
            studentService.add(new String[]{"1", "Valid Student Name", "935", "validEmail@mail.com", "Valid tutor"});
            Student retrievedStudent = studentService.findOne("1");
            Assert.assertEquals("Retrieved student id is not equal to id used in add.\n", "1", retrievedStudent.getId());
            Assert.assertEquals("Retrieved student email is not correct.\n", "validEmail@mail.com", retrievedStudent.getEmail());
            Assert.assertEquals("Retrieved student group is not as expected.\n", "935", String.valueOf(retrievedStudent.getGrupa()));
            Assert.assertEquals("Retrieved student name is not as expected.\n", "Valid Student Name", retrievedStudent.getNume());
            Assert.assertEquals("Retrieved student tutor is not as expected.\n", "Valid tutor", retrievedStudent.getIndrumator());
        } catch (ValidatorException e) {
            Assert.fail("Service add should've worked with valid student parameters.\n");
        }
    }

    @Test
    public void addStudentAndAddAssignmentIntegrationTest() {
        try {
            studentService.add(new String[]{"1", "Valid Student Name", "935", "validEmail@mail.com", "Valid tutor"});
            Student retrievedStudent = studentService.findOne("1");
            Assert.assertNotNull("Retrieved student is not as expected.\n", retrievedStudent);
            Assert.assertEquals("Retrieved student id is not equal to id used in add.\n", "1", retrievedStudent.getId());
            Assert.assertEquals("Retrieved student email is not correct.\n", "validEmail@mail.com", retrievedStudent.getEmail());
            Assert.assertEquals("Retrieved student group is not as expected.\n", "935", String.valueOf(retrievedStudent.getGrupa()));
            Assert.assertEquals("Retrieved student name is not as expected.\n", "Valid Student Name", retrievedStudent.getNume());
            Assert.assertEquals("Retrieved student tutor is not as expected.\n", "Valid tutor", retrievedStudent.getIndrumator());
            temaLabService.add(new String[]{"1", "Valid description", "14", "1"});
            TemaLab retrievedTemaLab = temaLabService.findOne(1);
            Assert.assertNotNull("Retrieved assignment is not as expected.\n", retrievedTemaLab);
            Assert.assertEquals("Retrieved assignment id is not correct\n", Integer.valueOf(1), retrievedTemaLab.getId());
            Assert.assertEquals("Retrieved assignment description is not as expected!\n", "Valid description", retrievedTemaLab.getDescriere());
            Assert.assertEquals("Retrieved assignment deadline is not equal to value used in add.\n", 14, retrievedTemaLab.getTermenLimita());
            Assert.assertEquals("Retrieved assignment received week number is not the same as the one used for insert.\n", 1,
                    retrievedTemaLab.getSaptammanaPredarii());
        } catch (ValidatorException e) {
            Assert.fail("Service add should've worked with valid student parameters and valid assignment parameters.\n,");
        }
    }

    @Test
    public void addStudentWithAddAssignmentWithAddGradeIntegrationTest() {
        try {
            studentService.add(new String[]{"1", "Valid Student Name", "935", "validEmail@mail.com", "Valid tutor"});
            Student retrievedStudent = studentService.findOne("1");
            Assert.assertNotNull("Retrieved student is not as expected.\n", retrievedStudent);
            Assert.assertEquals("Retrieved student id is not equal to id used in add.\n", "1", retrievedStudent.getId());
            Assert.assertEquals("Retrieved student email is not correct.\n", "validEmail@mail.com", retrievedStudent.getEmail());
            Assert.assertEquals("Retrieved student group is not as expected.\n", "935", String.valueOf(retrievedStudent.getGrupa()));
            Assert.assertEquals("Retrieved student name is not as expected.\n", "Valid Student Name", retrievedStudent.getNume());
            Assert.assertEquals("Retrieved student tutor is not as expected.\n", "Valid tutor", retrievedStudent.getIndrumator());
            temaLabService.add(new String[]{"1", "Valid description", "14", "1"});
            TemaLab retrievedTemaLab = temaLabService.findOne(1);
            Assert.assertNotNull("Retrieved assignment is not as expected.\n", retrievedTemaLab);
            Assert.assertEquals("Retrieved assignment id is not correct\n", Integer.valueOf(1), retrievedTemaLab.getId());
            Assert.assertEquals("Retrieved assignment description is not as expected!\n", "Valid description", retrievedTemaLab.getDescriere());
            Assert.assertEquals("Retrieved assignment deadline is not equal to value used in add.\n", 14, retrievedTemaLab.getTermenLimita());
            Assert.assertEquals("Retrieved assignment received week number is not the same as the one used for insert.\n", 1,
                    retrievedTemaLab.getSaptammanaPredarii());
            LocalDateTime nowLDT = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
            notaService.add(new String[] {"1", "1", "1", "8.5", nowLDT.format(dtf)});
            Nota retrievedGrade = notaService.findOne(1);
            Assert.assertNotNull("Grade retrieved failed.\n", retrievedGrade);
            Assert.assertEquals("Retrieved grade id is not as expected.\n", Integer.valueOf(1), retrievedGrade.getId());
            Assert.assertEquals("Retrieved grade student id is not as expected.\n", "1", retrievedGrade.getStudentId());
            Assert.assertEquals("Retrieved grade assignment id is not as expected.\n", Integer.valueOf(1), retrievedGrade.getTemaLabId());
            Assert.assertEquals("Retrieved grade value is not as expected.\n", Double.valueOf(8.5), Double.valueOf(retrievedGrade.getValoare()));
            Assert.assertEquals("Retrieved grade date is not as expected.\n", nowLDT.format(dtf), retrievedGrade.getLdt().format(dtf));

            Student studentFromGradeStudentId = studentService.findOne(retrievedGrade.getStudentId());
            Assert.assertNotNull("Student id from grade was not able to be used to retrieve a student.\n", studentFromGradeStudentId);

            TemaLab temaLabFromGradeTemaLabId = temaLabService.findOne(retrievedGrade.getTemaLabId());
            Assert.assertNotNull("Assignment id from grade was not able to be used to retrieve an assignment.\n", temaLabFromGradeTemaLabId);

        } catch (ValidatorException e) {
            Assert.fail("Service add should've worked with valid student parameters and valid assignment parameters.\n,");
        }
    }

}
