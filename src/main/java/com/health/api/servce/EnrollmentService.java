package com.health.api.servce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentService {

    private Logger log = LoggerFactory.getLogger(EnrollmentService.class);

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param data
     * @throws Exception
     */
    public void addEnrollee(Map<String, Object> data) throws Exception {
        this.validate(data, new String[] { "id", "name", "dateOfBirth", "isActive" });

        boolean hasPhone = data.containsKey("phone");

        List<Object> args = new ArrayList<Object>();
        args.add(data.get("id"));
        args.add(data.get("name"));
        args.add(data.get("isActive"));
        args.add(data.get("dateOfBirth"));

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO enrollee (id,name,isActive,dateOfBirth");
        if (hasPhone) {
            sb.append(",phone");
        }
        sb.append(") values (?,?,?,?");
        if (hasPhone) {
            sb.append(",?");
            args.add(data.get("phone"));
        }
        sb.append(")");

        jdbc.update(sb.toString(), args.toArray());
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param enrolleeId
     * @param data
     * @throws Exception
     */
    public void updateEnrollee(long id, Map<String, Object> data) throws Exception {

        this.getEnrollee(id);

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE enrollee SET ");

        List<Object> args = new ArrayList<Object>();

        for (String key : new String[] { "name", "dateOfBirth", "isActive", "phone" }) {
            if (data.containsKey(key)) {
                Object v = data.get(key);
                if (v == null) {
                    continue;
                }
                sb.append(key + "=?,");
                args.add(v);
                log.info("Update enrollee - Key: {}, Value:{}", key, v);
            }
        }

        args.add(id);

        String sql = sb.substring(0, sb.length() - 1);
        sql += " WHERE id=?";

        log.info("SQL: {}", sql);

        jdbc.update(sql, args.toArray());
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Map<String, Object> getEnrollee(long id) throws Exception {
        List<Map<String, Object>> res = jdbc.queryForList("SELECT * FROM enrollee where id=" + id);
        if (res.size() == 0) {
            throw new Exception("Enrollee not found");
        }
        return res.get(0);
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @return
     */
    public List<Map<String, Object>> getEnrollees() {
        return jdbc.queryForList("SELECT * FROM enrollee");
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param id
     */
    public void deleteEnrollee(long id) {
        log.info("deleteEnrollee: {}", id);
        jdbc.update("DELETE FROM enrollee WHERE id=" + id);
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param enrolleeId
     * @param data
     * @throws Exception
     */
    public void addDependent(long enrolleeId, Map<String, Object> data) throws Exception {

        this.validate(data, new String[] { "id", "name", "dateOfBirth" });

        this.getEnrollee(enrolleeId);

        jdbc.update(("INSERT INTO dependent (id,enrolleeId,name,dateOfBirth) VALUES(?,?,?,?)"), //
                data.get("id"), //
                enrolleeId, //
                data.get("name"), //
                data.get("dateOfBirth") //
        );
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param enrolleeId
     * @param dependentId
     * @param data
     * @throws Exception
     */
    public void updateDependent(long dependentId, Map<String, Object> data) throws Exception {

        this.getDependent(dependentId);

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE dependent SET ");

        List<Object> args = new ArrayList<Object>();

        for (String key : new String[] { "name", "dateOfBirth" }) {
            if (data.containsKey(key)) {
                Object v = data.get(key);
                if (v != null) {
                    sb.append(key + "=?,");
                    args.add(v);
                }
            }
        }

        args.add(dependentId);

        String sql = sb.substring(0, sb.length() - 1);
        sql += " WHERE id=?";

        log.info("SQL: {}", sql);

        jdbc.update(sql, args.toArray());
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param did
     * @return
     * @throws Exception
     */
    public Map<String, Object> getDependent(long did) throws Exception {
        List<Map<String, Object>> res = jdbc.queryForList("SELECT * FROM dependent where id=" + did);
        if (res.size() == 0) {
            throw new Exception("Dependent not found");
        }
        return res.get(0);
    }

    /**
     * 
     * @param id
     */
    public void deleteDependent(long id) {
        log.info("deleteDependent: {}", id);
        jdbc.update("DELETE FROM dependent WHERE id=" + id);
    }

    /**
     * --------------------------------------------------------------------------------
     * 
     * @param data
     * @param fields
     * @throws Exception
     */
    private void validate(Map<String, Object> data, String[] fields) throws Exception {
        for (String key : fields) {
            if (!data.containsKey(key)) {
                throw new Exception("Mandatory field missing " + key);
            }
        }
    }
}
