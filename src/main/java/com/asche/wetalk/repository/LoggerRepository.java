package com.asche.wetalk.repository;

import com.asche.wetalk.entity.LoggerBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<LoggerBean, Integer> {

}
