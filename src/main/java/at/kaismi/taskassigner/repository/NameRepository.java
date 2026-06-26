package at.kaismi.taskassigner.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class NameRepository {

    public static Set<String> names = new HashSet<>();

}
