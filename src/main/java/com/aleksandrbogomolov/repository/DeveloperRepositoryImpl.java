package com.aleksandrbogomolov.repository;

import com.aleksandrbogomolov.domain.Domain;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class DeveloperRepositoryImpl implements DeveloperRepository {

    private Collection<Domain.Developer> developers = mockRepository();

    @Override
    public Domain.Developer findBySoft(Domain.Software soft) {
        for (Domain.Developer d : developers) {
            for (Domain.Software s : d.getSoftList()) {
                if (soft.equals(s)) return d;
            }
        }
        return null;
    }

    private Domain.Developer developer(int id, String n, String e, Collection<String> soft) {
        Collection<Domain.Software> softwares =
                soft.stream()
                    .map(s -> Domain.Software.newBuilder().setName(s).build())
                    .collect(Collectors.toList());

        return Domain.Developer.newBuilder().setId(id).setName(n).setEmail(e).addAllSoft(softwares).build();
    }

    private Collection<Domain.Developer> mockRepository() {
        return Arrays.asList(
                developer(1, "Chris", "chris@email.com", Arrays.asList("RESTService", "SpringApp")),
                developer(2, "Josh", "josh@email.com", Arrays.asList("ServerApp", "GradlePlugin")),
                developer(3, "Matt", "matt@email.com", Arrays.asList("Chat", "ConsoleReader"))
        );
    }
}
