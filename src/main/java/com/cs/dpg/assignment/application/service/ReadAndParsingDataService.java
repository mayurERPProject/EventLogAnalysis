package com.cs.dpg.assignment.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cs.dpg.assignment.application.domain.EventData;
import com.cs.dpg.assignment.application.domain.EventDataDomain;
import com.cs.dpg.assignment.application.function.TransformObjectFunction;
import com.cs.dpg.assignment.application.function.imp.TransformObjectFunctionImpl;
import com.cs.dpg.assignment.application.utility.ConvertJSONToObject;

@Service(value = "readAndParsingDataService")
public class ReadAndParsingDataService {

	private static Logger log = Logger.getLogger(ReadAndParsingDataService.class.getName());

	public Map<String, EventDataDomain> readAndParseEventData(String eventData) throws Exception {
		log.info("ReadAndParsingDataService.readAndParseEventData START");
		Stream<String> str1 = Stream.of(eventData.replace("}", "},"))
				.map(x -> new String(x).substring(0, x.length() - 1).concat("]"));
		List<EventData> eventDataList = Stream
				.of(ConvertJSONToObject.convertJsonStringToObject(str1.collect(Collectors.joining()),
						EventData[].class))
				.sorted((e1, e2) -> e1.getId().compareTo(e2.getId())).collect(Collectors.toList());
		Map<String, EventDataDomain> eventDataDomainMap = new HashMap<String, EventDataDomain>();
		TransformObjectFunction<Map<String, EventDataDomain>, EventData> function = new TransformObjectFunctionImpl();
		for (EventData eventDataObj : eventDataList) {
			eventDataDomainMap = function.getEventDataWithCalculationDurationAndAlert(eventDataDomainMap, eventDataObj);
		}
		log.info("ReadAndParsingDataService.readAndParseEventData END");
		return eventDataDomainMap;
	}
}
