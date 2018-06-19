package br.com.xyinc.validacao;

import java.util.Locale;

import javax.validation.MessageInterpolator;

/**
 * delegates to a MessageInterpolator implementation but enforce a given Locale
 */
public class LocaleSpecificMessageInterpolator implements MessageInterpolator {
    private final MessageInterpolator defaultInterpolator;
	private final Locale defaultLocale;

    public LocaleSpecificMessageInterpolator(MessageInterpolator interpolator, Locale locale) {
        this.defaultLocale = locale;
        this.defaultInterpolator = interpolator;
    }

    /**
     * enforece the locale passed to the interpolator
     */
    public String interpolate(String message, 
                              Context context) {
        return defaultInterpolator.interpolate(message, 
                                               context, 
                                               this.defaultLocale);
    }

    // no real use, implemented for completeness
    public String interpolate(String message,
                              Context context,
                              Locale locale) {
        return defaultInterpolator.interpolate(message, context, locale);
    }
}