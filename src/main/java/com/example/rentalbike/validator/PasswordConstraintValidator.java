package com.example.rentalbike.validator;

import com.example.rentalbike.annotation.ValidPassword;
import org.passay.LengthRule;
import org.passay.PasswordValidator;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation();

        if (password == null) {
            context.buildConstraintViolationWithTemplate("Password cannot be null").addConstraintViolation();
            return false;
        }

        PasswordValidator passwordValidator = new PasswordValidator(
                new LengthRule(8,25),
                new WhitespaceRule(),
                new UsernameRule(),
                new CharacterCharacteristicsRule(
                        new CharacterRule(EnglishCharacterData.UpperCase, 1),
                        new CharacterRule(EnglishCharacterData.Digit,1),
                        new CharacterRule(EnglishCharacterData.Special, 1)
                )
        );

        RuleResult ruleResult = passwordValidator.validate(new PasswordData(password));

        if (ruleResult.isValid()) {
            return true;
        }

        passwordValidator.getMessages(ruleResult).forEach(s ->
                context.buildConstraintViolationWithTemplate(s).addConstraintViolation());

        return false;
    }
}
