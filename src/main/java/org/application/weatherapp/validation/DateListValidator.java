package org.application.weatherapp.validation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateListValidator implements ConstraintValidator<ValidDateList, List<String>>{
    @Override
    public void initialize(ValidDateList constraintAnnotation){}

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null || value.isEmpty()){
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        for(String date: value){
            try{
                LocalDate.parse(date, formatter);
            }catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
