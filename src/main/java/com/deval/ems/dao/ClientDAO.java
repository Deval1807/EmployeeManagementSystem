package com.deval.ems.dao;

import com.deval.ems.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO {

    @Autowired
    @Qualifier("postgresClientsJdbcTemplate")
    private JdbcTemplate postgresClientsJdbcTemplate;

    /**
     * Method to query the postgres DB and fetch all the clients
     * @param pageNo the requested page no. of the response (for pagination)
     * @param limit the size of the response (no of rows) (for pagination)
     * @param sortBy the parameter used to sort the result
     * @param searchName parameter to search a name in the DB
     * @return a list of client object
     */
    public List<Client> getClientsFromPostgresDB(int pageNo, int limit, String sortBy, String searchName) {
        int offset = (pageNo - 1)*limit;

        // sort by ascending if sortBy starts with '+' and descending if starts with '-'
        String order = sortBy.startsWith("-") ? "DESC" : "ASC";;
        String columnName = sortBy.substring(1);
        List<Object> params = new ArrayList<>();

        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM clients");

        if(searchName!=null) {
            sqlBuilder.append(" WHERE name LIKE ?");
            params.add("%" + searchName + "%");
        }

        sqlBuilder.append(" ORDER BY ")
                .append(columnName)
                .append(" ")
                .append(order)
                .append(" LIMIT ? OFFSET ?");

        params.add(limit);
        params.add(offset);

        return postgresClientsJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),
                (rs, rowNum) -> new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                )
        );
    }
}
