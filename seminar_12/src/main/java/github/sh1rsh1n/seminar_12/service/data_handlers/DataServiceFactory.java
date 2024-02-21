package github.sh1rsh1n.seminar_12.service.data_handlers;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataServiceFactory {

    private final Map<String, DataService> dataServiceMap;

    public DataServiceFactory(Map<String, DataService> dataServiceMap) {
        this.dataServiceMap = dataServiceMap;
    }

    public DataService getService(String dataType) {
        return dataServiceMap.get(dataType);
    }
}
