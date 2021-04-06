package com.ssvv.tests.bigbang;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BigBangTests {

    private TemaLabValidator temaLabValidator;
    private TemaLabXMLRepo temaLabXMLRepo;
    private TemaLabXMLService temaLabXMLService;
    private final String temaLabXMLFile = "tema-lab-bigbang-tests.xml";

    private StudentValidator studentValidator;
    private StudentXMLRepo studentXMLRepo;
    private StudentXMLService studentXMLService;
    private final String studentXmlFile = "student-bigbang-tests.xml";

    private NotaValidator notaValidator;
    private NotaXMLRepo notaXMLRepo;
    private NotaXMLService notaXMLService;
    private final String notaXmlFile = "nota-bigbang-tests.xml";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        temaLabValidator = new TemaLabValidator();
        temaLabXMLRepo = new TemaLabXMLRepo(temaLabValidator, temaLabXMLFile);
        temaLabXMLService = new TemaLabXMLService(temaLabXMLRepo);

        studentValidator = new StudentValidator();
        studentXMLRepo = new StudentXMLRepo(studentValidator, studentXmlFile);
        studentXMLService = new StudentXMLService(studentXMLRepo);

        notaValidator = new NotaValidator();
        notaXMLRepo = new NotaXMLRepo(notaValidator, notaXmlFile);
        notaXMLService = new NotaXMLService(notaXMLRepo);
    }

    @Test
    public void testAddStudent() throws ValidatorException {
        String[] paramsValid = new String[] {"1", "Test Student", "935", "test@email.com", "Test indrumator"};
        studentXMLService.add(paramsValid);
        Assert.assertNotNull(studentXMLService.findOne("1"));
    }

    @Test
    public void testAddAssignment() throws ValidatorException {
        String[] paramsValid = new String[] {"1", "Test assignment", "1", "14"};
        temaLabXMLService.add(paramsValid);
        Assert.assertNotNull(temaLabXMLService.findOne(1));
    }

    @Test
    public void testAddGrade() throws ValidatorException {
        String[] paramsGradeInvalidIdStudent = new String[] {"1", "", "1", "10.0", "12:00 06.04.2021"};
        expectedException.expect(ValidatorException.class);
        expectedException.expectMessage("Id student invalid\n");

        notaXMLService.add(paramsGradeInvalidIdStudent);
    }

    @Test
    public void testAddAll() throws ValidatorException {
        String[] paramsValidStudent = new String[] {"1", "Test Student", "935", "test@email.com", "Test indrumator"};
        String[] paramsValidAssignment = new String[] {"1", "Test assignment", "1", "14"};
        String[] paramsValidGrade = new String[] {"1", "1", "1", "8.0", "12:00 06.04.2021"};

        studentXMLService.add(paramsValidStudent);
        temaLabXMLService.add(paramsValidAssignment);
        notaXMLService.add(paramsValidGrade);

        Assert.assertNotNull(notaXMLService.findOne(1));
        Assert.assertNotNull(temaLabXMLService.findOne(1));
        Assert.assertNotNull(studentXMLService.findOne("1"));
    }

}
