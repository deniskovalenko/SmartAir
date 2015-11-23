package com.smartair.utils.validators;

import com.smartair.model.chart.ChartSearchFilterModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
