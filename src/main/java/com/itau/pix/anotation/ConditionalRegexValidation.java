package com.itau.pix.anotation;


import org.apache.commons.beanutils.BeanUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

@Repeatable(ConditionalRegexValidations.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ConditionalRegexValidation.Validator.class})
public @interface ConditionalRegexValidation {
    String message() default "Field is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regex();

    String conditionalProperty();

    String property();

    String value();

    class Validator implements ConstraintValidator<ConditionalRegexValidation, Object> {

        private Pattern pattern;
        private String conditionalProperty;
        private String conditionalValue;
        private String property;
        private String message;

        @Override
        public void initialize(ConditionalRegexValidation constraintAnnotation) {
            conditionalProperty = constraintAnnotation.conditionalProperty();
            conditionalValue = constraintAnnotation.value();
            property = constraintAnnotation.property();
            message = constraintAnnotation.message();
            pattern = Pattern.compile(constraintAnnotation.regex(), Pattern.CASE_INSENSITIVE);
        }

        @Override
        public boolean isValid(Object object, ConstraintValidatorContext context) {
            try {
                String conditionalPropertyValue = BeanUtils.getProperty(object, conditionalProperty);
                if (!conditionalValue.equalsIgnoreCase(conditionalPropertyValue)) {
                    return true;
                }
                String propertyValue = BeanUtils.getProperty(object, property);

                if (pattern.matcher(propertyValue).matches()) {
                    return true;
                }

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(property)
                        .addConstraintViolation();
                return false;
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
