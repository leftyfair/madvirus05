package madvirus.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import madvirus.model.command.RegisterCommand;

public class RegisterCommandValidator implements Validator {

	private static final String emailRegExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

	@Override
	public boolean supports(Class<?> clazz) {

		return RegisterCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegisterCommand registerCommand = (RegisterCommand) target;
		if (registerCommand.getEmail() == null || registerCommand.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			Matcher matcher = Pattern.compile(emailRegExp).matcher(registerCommand.getEmail());
			if(!matcher.matches()) errors.rejectValue("email", "bad");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		if(!registerCommand.isPasswordEqualToConfirmPassword()) errors.rejectValue("confirmPassword", "nomatch");
		
	}

}
