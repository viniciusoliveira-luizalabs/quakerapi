package com.api.exceptions;

import com.api.QuakeparserApplicationTests;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterBasedOnInheritance;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ExceptionsBaseTest extends QuakeparserApplicationTests {

    private List<PojoClass> classes;

    @Before
    public void setUp(){
        this.classes = PojoClassFactory.getPojoClassesRecursively("com.api.exception", new FilterBasedOnInheritance(Exception.class));
    }

    @Test
    public void validate(){
        Validator validator = ValidatorBuilder.create()
                .with(new SetterMustExistRule(),
                        new GetterMustExistRule())
                .with(new SetterTester(),
                        new GetterTester())
                .build();
        validator.validate(this.classes);
    }

}
