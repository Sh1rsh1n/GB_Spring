package github.sh1rsh1n.seminar_12.service.data_handlers;


import org.springframework.stereotype.Service;

@Service
public interface DataService {
    void writeData(String filename, String data);
}
