package com.breathe.utils.validators;

import com.breathe.model.chart.ChartSearchFilterModel;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

/**
 * Created by denis on 17.04.15.
 */
public class ChartSearchFilterValidator implements Validator {
    public boolean supports(Class clazz) {
        return ChartSearchFilterModel.class.equals(clazz);
    }
    public void validate(Object obj, Errors e) {
        ChartSearchFilterModel f = (ChartSearchFilterModel) obj;
        if (f.getMode() > 2 || f.getMode() < 0 ) {
            e.rejectValue("mode", "not valid mode");
        };
    }
}
