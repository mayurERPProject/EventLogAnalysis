package com.cs.dpg.assignment.application.function.imp;

import java.util.Map;

import org.apache.log4j.Logger;

import com.cs.dpg.assignment.application.constant.EventStatusConstant;
import com.cs.dpg.assignment.application.domain.EventData;
import com.cs.dpg.assignment.application.domain.EventDataDomain;
import com.cs.dpg.assignment.application.function.TransformObjectFunction;

public class TransformObjectFunctionImpl implements TransformObjectFunction<Map<String, EventDataDomain>, EventData> {

	private static Logger log = Logger.getLogger(TransformObjectFunctionImpl.class.getName());

	@Override
	public Map<String, EventDataDomain> getEventDataWithCalculationDurationAndAlert(Map<String, EventDataDomain> map,
			EventData eventData) {
		EventDataDomain eventDataDomain = new EventDataDomain();
		log.info("TransformObjectFunctionImpl.getEventDataWithCalculationDurationAndAlert START");
		if (map.containsKey(eventData.getId())) {
			eventDataDomain = map.get(eventData.getId());
			if (!eventDataDomain.getState().equals(eventData.getState())) {
				if (eventData.getState().equals(EventStatusConstant.FINISHED)) {
					eventDataDomain.setDuration(eventData.getTimestamp() - eventDataDomain.getTimestamp());
				} else {
					eventDataDomain.setDuration(eventDataDomain.getTimestamp() - eventData.getTimestamp());
				}
				if (eventDataDomain.getDuration() != null && eventDataDomain.getDuration() > 4) {
					eventDataDomain.setAlert(true);
				}
			}
		} else {
			eventDataDomain.setEventId(eventData.getId());
			eventDataDomain.setType(eventData.getType());
			eventDataDomain.setHost(eventData.getHost());
			eventDataDomain.setTimestamp(eventData.getTimestamp());
			eventDataDomain.setState(eventData.getState());
			map.putIfAbsent(eventData.getId(), eventDataDomain);
		}
		log.info("TransformObjectFunctionImpl.getEventDataWithCalculationDurationAndAlert END");
		return map;
	}

}
