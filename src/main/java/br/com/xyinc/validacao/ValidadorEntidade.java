package br.com.xyinc.validacao;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidadorEntidade {

	private Locale locale;

	public ValidadorEntidade() {
		locale = Locale.getDefault();
	}

	public ValidadorEntidade(final Locale locale) {
		this.locale = locale;
	}

	public <E> void validate(final E e) throws ValidacaoExcecao {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			Set<ConstraintViolation<E>> violations = getValidator(factory).validate(e);

			if (!violations.isEmpty()) {
				throw new ValidacaoExcecao(violations.iterator().next().getMessage());
			}
		}
	}

	private Validator getValidator(final ValidatorFactory factory) {
		MessageInterpolator interpolator = new LocaleSpecificMessageInterpolator(factory.getMessageInterpolator(),
				locale);
		return factory.usingContext().messageInterpolator(interpolator).getValidator();
	}
}
