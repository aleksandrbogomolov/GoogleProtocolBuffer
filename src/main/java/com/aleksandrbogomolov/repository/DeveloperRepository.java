package com.aleksandrbogomolov.repository;

import com.aleksandrbogomolov.domain.DeveloperProtos;

public interface DeveloperRepository {

    DeveloperProtos.Developer findBySoft(String soft);
}
