package com.manning.javapersistence.ch03.validation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ModelValidation {
    @Test
    public void validateItem() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(new Date());

        Set<ConstraintViolation<Item>> violations = validator.validate(item);

        ConstraintViolation<Item> violation = violations.iterator().next();
        String failedPropertyName = violation.getPropertyPath().iterator().next().getName();

        assertAll(
            () -> assertEquals(1, violations.size()),
            () -> assertEquals("auctionEnd", failedPropertyName),
            () -> {
                if (Locale.getDefault().getLanguage().equals("en")) {
                    assertEquals(violation.getMessage(), "must be a future date");
                }
            }
        );
    }
}
