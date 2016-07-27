import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class SpringJDBC {
    private JdbcTemplate jdbcTemplate;

    public SpringJDBC(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
