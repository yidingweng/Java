/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek4_dvdlibraryspringdi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yidingweng
 */
public class DVDDaoDbImpl {
    private static final String SQL_INSERT_DVD
            = "insert into dvds "
            + "(title, releaseDate, mPAARating, directorName, studio, userRating) "
            + "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_DVD
            = "delete from dvds where title = ?";
    private static final String SQL_SELECT_DVD
            = "select * from dvds where title = ?";
    private static final String SQL_UPDATE_DVD
            = "update dvds set "
            + "title = ?, release = ?, mPAARating = ?, "
            + "directorName = ?, studio = ?, userRating = ?"
            + "where title = ?";
    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvds";
    private static final String SQL_SELECT_DVDS_BY_TITLE
            = "select * from contacts where title = ?";
    private static final String SQL_SELECT_DVDS_BY_DIRECTOR
            = "select * from contacts where directorName = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DVD addDVD(DVD dvd) {
        Timestamp timestamp = Timestamp.valueOf(dvd.getReleaseDate().atStartOfDay());
        jdbcTemplate.update(SQL_INSERT_DVD,
        dvd.getTitle(),
        timestamp,
        //dvd.getReleaseDate(),
        dvd.getmPAARating(),
        dvd.getDirectorName(),
        dvd.getStudio(),
        dvd.getUserRating());

        return dvd;
    }
    
    public void removeDVD(String title) {
        jdbcTemplate.update(SQL_DELETE_DVD, title);
    }

    public void updateDVD(DVD dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
        dvd.getTitle(),
        dvd.getReleaseDate(),
        dvd.getmPAARating(),
        dvd.getDirectorName(),
        dvd.getStudio(),
        dvd.getUserRating());
    }

    public List<DVD> getAllDVDs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DVDMapper());
    }

    public DVD getDVDByTitle(String title) {
        try {//use query for object, only exactly one thing back, 
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,//prepare statement, 
                    new DVDMapper(), title);
        } catch (EmptyResultDataAccessException ex) {//it's ok if return nothing
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
        return getAllDVDs();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from dvds where ");
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

        return jdbcTemplate.query(sQuery.toString(),
                new DVDMapper(),
                paramVals);
        }
    }
    private static final class DVDMapper implements RowMapper<DVD> {
        //contactMapper implement RowMapper, which only has this one method mapRow
        //knows database
        //knows DTO, so it's a interface
        @Override
        public DVD mapRow(ResultSet rs, int rowNum) throws SQLException { //it return a contact, 
            //two parameters, rowNum means which row it is, rs means that content in that row
            DVD dvd = new DVD();
            dvd.setTitle(rs.getString("title"));//"contact_id" is the column name, the string representation
            dvd.setReleaseDate(rs.getString("releaseDate"));
            dvd.setmPAARating(rs.getString("mPAARating"));
            dvd.setDirectorName(rs.getString("directorName"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setUserRating(rs.getString("userRating"));
            return dvd;
        }
    }
}
