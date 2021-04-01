package com.example.features;

import java.util.Arrays;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

public class OperatingSystemCondition implements ExecutionCondition {

	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
		var result = ConditionEvaluationResult.enabled("@ConditionalTestOperatingSystem is not available");
		var element = context.getElement();
		var condition = AnnotationUtils.findAnnotation(element, ConditionalTestOperatingSystem.class);
		if (condition.isPresent()) {
			var os = OperatingSystemEnum.determineCurrentOs();
			var operatingSystems = condition.get().value();
			var found = Arrays.stream(operatingSystems)
					          .filter(val -> val.equals(os))
					          .findFirst();
			if (found.isPresent())
				result = ConditionEvaluationResult.enabled("@ConditionalTestOperatingSystem is present and enabled");
			else	
				result = ConditionEvaluationResult.disabled("@ConditionalTestOperatingSystem is NOT present and disabled");
		}
		return result;
	}

}
