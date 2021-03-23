package com.ssvv.tests;

import com.ssvv.Domain.TemaLab;
import com.ssvv.Exceptions.ValidatorException;
import com.ssvv.Validator.TemaLabValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TemaLabValidatorTests {

    private TemaLabValidator temaLabValidator;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setup() {
        temaLabValidator = new TemaLabValidator();
    }

    @Test
    public void testValidateAssignmentWithInvalidDueDate() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        exceptionRule.expectMessage("Termen limita invalid\n");

        TemaLab temaLabFailDueDate = new TemaLab(1, "Test validator", 15, 13);
        temaLabValidator.validate(temaLabFailDueDate);
    }

    @Test
    public void testValidateAssignmentWithInvalidDescription() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        exceptionRule.expectMessage("Descriere tema invalida\n");

        TemaLab temaLabFailDescription = new TemaLab(1, "", 12, 10);
        temaLabValidator.validate(temaLabFailDescription);
    }
}
