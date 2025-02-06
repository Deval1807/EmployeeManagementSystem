package com.deval.ems.service;

import com.deval.ems.dao.ClientDAO;
import com.deval.ems.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    // Logger to log all the necessary info
    Logger logger = LoggerFactory.getLogger(ClientService.class);

    /**
     * Method to get all the clients from the DAO layer
     * @param pageNo the requested page no. of the response (for pagination)
     * @param limit the size of the response (no of rows) (for pagination)
     * @param sortBy the parameter used to sort the result
     * @return a list of client object
     */
    public List<Client> getAllClients(int pageNo, int limit, String sortBy) {
        logger.info("Getting all the clients");
        return clientDAO.getClientsFromPostgresDB(pageNo, limit, sortBy);
    }

}
