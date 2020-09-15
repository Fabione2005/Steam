package com.fabione.steam.bussines;

import org.junit.Before;
import org.junit.Test;

import com.fabione.steam.exception.PlayerInfoException;
import com.fabione.steam.service.business.EmailValidatorServiceImpl;
import com.fabione.steam.utils.TestUtilsForGroovy;

public class EmailValidationTest {

    private EmailValidatorServiceImpl emailValidation;

    @Before
    public void setUp() {
    	emailValidation = new EmailValidatorServiceImpl();
        TestUtilsForGroovy.setEmailPropierties(emailValidation);
    }


    @Test(expected = Test.None.class /* no exception expected */)
    public void shouldReturnTrueWhenValidMail() {

        String email = "test@test.cl";
        emailValidation.validateEmail(email);
    }

    @Test(expected = PlayerInfoException.class)
    public void shouldReturnFalseWhenInValidMail() {

        String email = "testtest.cl";
        emailValidation.validateEmail(email);

    }
}
