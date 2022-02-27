package io.gerarrays.server.repo;

import io.gerarrays.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository <Server, Long>{

    Server findByIpAddress(String ipAddress);//if we name a method with findBy -> JPA knows it will do a Select, value must be unique

}
