package com.gmail.sergiusz.mazan.games.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContentTypeValidator implements ConstraintValidator<ContentType, MultipartFile> {

    private String contentType;

    @Override
    public void initialize(ContentType constraintAnnotation) {
        this.contentType = constraintAnnotation.contentType();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if(multipartFile == null || multipartFile.isEmpty())
            return false;
        String contentType = multipartFile.getContentType();
        if(contentType == null || !contentType.equals(this.contentType))
            return false;
        else
            return true;
    }
}
