package com.api.models;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterBasedOnInheritance;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class ModelsTest {

    private List<PojoClass> classes;

    @Before
    public void setUp(){
        this.classes = PojoClassFactory.getPojoClassesRecursively("com.api.models", new FilterBasedOnInheritance(Serializable.class));
    }

    @Test
    public void validate() throws Exception{
        Validator validator = ValidatorBuilder.create()
                .with(new SetterMustExistRule(),
                        new GetterMustExistRule())
                .with(new SetterTester(),
                        new GetterTester())
                .build();
        validator.validate(this.classes);

        for(PojoClass pojoClass : classes){
            Method toString = pojoClass.getClazz().getDeclaredMethod("toString", null);
            Method equals = pojoClass.getClazz().getDeclaredMethod("equals", Object.class);

            toString.invoke(pojoClass.getClazz().newInstance(), null);
            equals.invoke(pojoClass.getClazz().newInstance(), new Object());
        }
    }

}
