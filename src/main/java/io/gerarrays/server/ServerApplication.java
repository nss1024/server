package io.gerarrays.server;

import io.gerarrays.server.enumeration.Status;
import io.gerarrays.server.model.Server;
import io.gerarrays.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null,"192.168.1.160","Ubuntu Linux","16 Gb","Personal PC",
					"http://localhost:8080/server/image/server1.png",Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.161","Fedora Linux","16 Gb","Dell Tower",
					"http://localhost:8080/server/image/server2.png",Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.162","MS 2008","32 Gb","Mail Server",
					"http://localhost:8080/server/image/server3.png",Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.163","Red Hat Linux","16 Gb","Web Server",
					"http://localhost:8080/server/image/server4.png",Status.SERVER_UP));

		};
	}


}
