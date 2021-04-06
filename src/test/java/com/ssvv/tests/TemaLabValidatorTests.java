package com.ssvv.tests;

import com.ssvv.Domain.TemaLab;
import com.ssvv.Exceptions.ValidatorException;
import com.ssvv.Repository.XMLFileRepository.TemaLabXMLRepo;
import com.ssvv.Service.XMLFileService.TemaLabXMLService;
import com.ssvv.Validator.TemaLabValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;

public class TemaLabValidatorTests {

    private TemaLabValidator temaLabValidator;
    private TemaLabXMLRepo temaLabXMLRepo;
    private TemaLabXMLService temaLabXMLService;
    private String filename = "tema-lab-tests.xml";

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setup() {
        temaLabValidator = new TemaLabValidator();
        temaLabXMLRepo = new TemaLabXMLRepo(temaLabValidator, filename);
        temaLabXMLService = new TemaLabXMLService(temaLabXMLRepo);
    }

    @Test
    public void testAddAssignmentTC1() throws ValidatorException {
        exceptionRule.expect(NumberFormatException.class);

        String[] params = new String[] {"", "Test", "12", "10"};
        temaLabXMLService.add(params);
    }

    @Test
    public void testAddAssignmentTC2() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        exceptionRule.expectMessage("Descriere tema invalida\n");

        String[] params = new String[] {"1", "", "14", "1"};
        temaLabXMLService.add(params);
    }

    @Test
    public void testAddAssignmentTC3() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        exceptionRule.expectMessage("Sapatamana predarii invalida\n");

        String[] params = new String[] {"1", "Test", "1", "0"};
        temaLabXMLService.add(params);
    }

//    @Test
//    public void testAddAssignmentTC3() throws ValidatorException {
//        exceptionRule.expect(ValidatorException.class);
//        exceptionRule.expectMessage("Sapatamana predarii invalida\n");
//
//        String[] params = new String[] {"1", "Test", "1", "15"};
//        temaLabXMLService.add(params);
//    }

    @Test
    public void testAddAssignmentTC4() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        exceptionRule.expectMessage("Termen limita invalid\n");

        String[] params = new String[] {"1", "Test", "0", "1"};
        temaLabXMLService.add(params);
    }

    @Test
    public void testAddAssignmentTC5() throws ValidatorException {
        String[] params = new String[] {"1", "Test", "2", "1"};

        temaLabXMLService.add(params);
        Assert.assertNotNull(temaLabXMLService.findOne(1));
    }

    @Test
    public void testAddAssignmentTC6() throws ValidatorException {
        String[] params1 = new String[] {"1", "Test 1", "2", "1"};
        String[] params2 = new String[] {"1", "Test 2", "2", "1"};
        int size = 0;
        temaLabXMLService.add(params1);
        temaLabXMLService.add(params1);
        for (TemaLab temaLab : temaLabXMLService.findAll()) {
            size++;
        }
        Assert.assertEquals("Element IDs are not unique.", 1, size);
        Assert.assertEquals("New element replaced previous.", "Test 1", temaLabXMLService.findOne(1).getDescriere());
    }
}
