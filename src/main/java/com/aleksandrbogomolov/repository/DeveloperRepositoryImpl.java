package com.aleksandrbogomolov.repository;

import com.aleksandrbogomolov.domain.DeveloperProtos;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class DeveloperRepositoryImpl implements DeveloperRepository {

    private Collection<DeveloperProtos.Developer> developers = mockRepository();

    @Override
    public DeveloperProtos.Developer findBySoft(String soft) {
        for (DeveloperProtos.Developer d : developers) {
            for (DeveloperProtos.Developer.Software s : d.getSoftList()) {
                if (soft.equals(s.getName())) return d;
            }
        }
        return null;
    }

    private DeveloperProtos.Developer developer(int id, String n, String e, Collection<String> soft) {
        Collection<DeveloperProtos.Developer.Software> softwares =
                soft.stream()
                    .map(s -> DeveloperProtos.Developer.Software.newBuilder().setName(s).build())
                    .collect(Collectors.toList());

        return DeveloperProtos.Developer.newBuilder().setId(id).setName(n).setEmail(e).addAllSoft(softwares).build();
    }

    private Collection<DeveloperProtos.Developer> mockRepository() {
        return Arrays.asList(
                developer(1, "Chris", "chris@email.com", Arrays.asList("RESTService", "SpringApp")),
                developer(2, "Josh", "josh@email.com", Arrays.asList("ServerApp", "GradlePlugin")),
                developer(3, "Matt", "matt@email.com", Arrays.asList("Chat", "ConsoleReader"))
        );
    }
}
