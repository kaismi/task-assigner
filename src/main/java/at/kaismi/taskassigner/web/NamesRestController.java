package at.kaismi.taskassigner.web;

import at.kaismi.taskassigner.repository.NameRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/names") @RestController public class NamesRestController {

    @RequestMapping(method = RequestMethod.GET) public Set<String> getNames() {
        return NameRepository.names;
    }
}
