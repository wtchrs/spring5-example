package springexample.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePasswordCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePasswordCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "currentPassword", "required");
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
        ValidationUtils.rejectIfEmpty(errors, "newPasswordConfirm", "required");
        if (!((ChangePasswordCommand)target).isNewPasswordEqualToConfirm()){
            errors.rejectValue("newPasswordConfirm", "nomatch.confirmPassword");
        }
    }
}
