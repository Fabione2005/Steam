package com.fabione.steam.bussines;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.fabione.steam.exception.PlayerInfoException;
import com.fabione.steam.service.EmailValidatorServiceImpl;

public class EmailValidationTest {

    private EmailValidatorServiceImpl emailValidation;

    @Before
    public void setUp() {
    	emailValidation = new EmailValidatorServiceImpl();
        ReflectionTestUtils.setField(emailValidation, "regexEmail", "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z][cl])$");
        ReflectionTestUtils.setField(emailValidation, "emailMsg", "aaaaaaa@dominio.cl");
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
