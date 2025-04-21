package com.reminder.ulti.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD }) // Chỉ áp dụng cho field
@Retention(RetentionPolicy.RUNTIME) // Chạy lúc runtime
@Constraint(validatedBy = UniqueEmailValidator.class) // Chỉ định validator
@Documented
public @interface UniqueEmail {
	String message() default "Email is already in use"; // Thông báo lỗi
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
