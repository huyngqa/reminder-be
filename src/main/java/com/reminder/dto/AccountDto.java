package com.reminder.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.reminder.ulti.validation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AccountDto implements Validator {

    private Integer id;

    @NotBlank(message = "vui lòng nhập email !")
    @Email(message = "vui lòng nhập đúng định dạng !")
    @UniqueEmail
    private String email;

    @NotBlank(message = "vui lòng nhập số điện thoại !")
    @Pattern(regexp = "^(090|093|097)\\d{7}$", message = "vui lòng nhập đúng định dạng !")
    private String phone;

    @NotBlank(message = "vui lòng nhập mật khẩu!")
    @Length(min = 6, message = "Mật khẩu yếu !")
    @Length(max = 50, message = "Tối đa 50 ký tự")
    private String password;
    
    private Boolean isAdmin;


    public AccountDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}