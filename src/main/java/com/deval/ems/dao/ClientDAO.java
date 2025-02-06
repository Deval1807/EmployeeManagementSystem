package com.deval.ems.dao;

import com.deval.ems.model.Client;
import com.deval.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientDAO {

    @Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate jdbcTemplate2;

    /**
     * This method maps the ResultSet(result from SQL operation) to Client object
     */
    private static final class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Client(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location")
            );
        }
    }

    /**
     * Method to query the postgres DB and fetch all the clients
     * @param pageNo the requested page no. of the response (for pagination)
     * @param limit the size of the response (no of rows) (for pagination)
     * @param sortBy the parameter used to sort the result
     * @return a list of client object
     */
    public List<Client> getClientsFromPostgresDB(int pageNo, int limit, String sortBy) {
        int offset = (pageNo - 1)*limit;

        // sort by ascending if sortBy starts with '+' and descending if starts with '-'
        String order = sortBy.startsWith("-") ? "DESC" : "ASC";;
        String columnName = sortBy.substring(1);

        String sql = "SELECT * FROM clients ORDER BY " + columnName + " " + order + " LIMIT ? OFFSET ?";

        return jdbcTemplate2.query(sql, new Object[]{limit, offset}, new ClientRowMapper());
    }
}
