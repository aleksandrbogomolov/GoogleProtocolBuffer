package com.aleksandrbogomolov.repository;

import com.aleksandrbogomolov.domain.Domain;

public interface DeveloperRepository {

    Domain.Developer findBySoft(Domain.Developer.Software soft);
}
