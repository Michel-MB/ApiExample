package com.ussd.app.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcesNotFound extends RuntimeException{
    private String resourcename;
    private String fieldName;
    private Object fieldValue;


    public ResourcesNotFound(String resourcename, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s",resourcename,fieldName,fieldValue));
        this.resourcename = resourcename;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourcename() {
        return resourcename;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
