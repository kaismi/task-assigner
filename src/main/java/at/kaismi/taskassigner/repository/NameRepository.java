package at.kaismi.taskassigner.repository;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class NameRepository {

    public static Set<String> names = Sets.newHashSet();

}
