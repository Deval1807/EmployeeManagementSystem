package com.deval.ems.controller;

import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.model.Client;
import com.deval.ems.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Client", description = "Simple APIs for Client Management System")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Logger to log all the necessary info
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * Controller to handle the request to get all the clients
     * @param pageNo the requested page no. of the response (for pagination) default page = 1st page
     * @param limit the size of the response (no of rows) (for pagination) default size = 10 rows
     * @return a list of client objects
     */
    @GetMapping()
    public List<Client> getAllClients(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int limit
    ) {
        logger.info("IN: Request to fetch all clietns");
        List<Client> clients = clientService.getAllClients(pageNo, limit);
        logger.info("OUT: Fetched all the clients");
        return clients;
    }

}
