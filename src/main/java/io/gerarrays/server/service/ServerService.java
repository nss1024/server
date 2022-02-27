package io.gerarrays.server.service;

import io.gerarrays.server.model.Server;

import java.util.Collection;

public interface ServerService {

    Server create(Server server);
    Server pig (String ipAddress);
    Collection<Server> list (int limit);
    Server get (Long id);
    Server update(Server server);
    Boolean delete(Long id);

}
