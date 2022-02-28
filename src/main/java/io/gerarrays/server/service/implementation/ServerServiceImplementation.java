package io.gerarrays.server.service.implementation;

import io.gerarrays.server.enumeration.Status;
import io.gerarrays.server.model.Server;
import io.gerarrays.server.repo.ServerRepo;
import io.gerarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {} ",server.getName());
        server.setImageUrl(setServerImageUrl());//when creating a new server in the db we will get the server obj from the user, but we will set the image, not get it from the user
        return serverRepo.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {} ",ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers"); //we don't want to get all the servers back from the repo, only up to the limit we pass in to the method
        return serverRepo.findAll(of(0,limit)).toList();// of is part of the static import for org.springframework.data.domain.PageRequest.of;
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {//JPA clever enough to know that if the server exists it is an update if not, it will do an insert
        log.info("Updating server: {} ",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {} ",id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl(){
        String[] imageNames = {"server1.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+imageNames[new Random().nextInt(4)]).toUriString();//cerate a url to a random image from the array
    }
}
