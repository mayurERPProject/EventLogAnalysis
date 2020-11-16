package com.cs.dpg.assignment.application.function;

import java.util.Map;

import com.cs.dpg.assignment.application.domain.EventData;
import com.cs.dpg.assignment.application.domain.EventDataDomain;

@FunctionalInterface
public  interface TransformObjectFunction<T,R> {

	Map<String, EventDataDomain> getEventDataWithCalculationDurationAndAlert(Map<String, EventDataDomain> map,EventData e);
}
