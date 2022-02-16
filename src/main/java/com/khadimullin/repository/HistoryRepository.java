package com.khadimullin.repository;

import com.khadimullin.model.HistoryWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryWeather, Integer> {

}