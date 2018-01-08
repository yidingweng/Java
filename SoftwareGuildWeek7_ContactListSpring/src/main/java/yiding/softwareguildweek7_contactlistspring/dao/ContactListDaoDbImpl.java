/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek7_contactlistspring.dao;

/**
 *
 * @author yidingweng
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import yiding.softwareguildweek7_contactlistspring.model.Contact;

public class ContactListDaoDbImpl implements ContactListDao {//the prepare statement,
    private static final String SQL_INSERT_CONTACT
            = "insert into contacts "
            + "(first_name, last_name, company, phone, email) "
            + "values (?, ?, ?, ?, ?)";//it has five entries
    private static final String SQL_DELETE_CONTACT
            = "delete from contacts where contact_id = ?";
    private static final String SQL_SELECT_CONTACT
            = "select * from contacts where contact_id = ?";
    private static final String SQL_UPDATE_CONTACT
            = "update contacts set "
            + "first_name = ?, last_name = ?, company = ?, "
            + "phone = ?, email = ? "
            + "where contact_id = ?";
    private static final String SQL_SELECT_ALL_CONTACTS
            = "select * from contacts";
    private static final String SQL_SELECT_CONTACTS_BY_LAST_NAME
            = "select * from contacts where last_name = ?";
    private static final String SQL_SELECT_CONTACTS_BY_COMPANY
            = "select * from contacts where company = ?";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)//when we add things, we
    //are inserting, and also asking database to get index id, so it's two way
    //
    public Contact addContact(Contact contact) {//update from JDBC template
        jdbcTemplate.update(SQL_INSERT_CONTACT,//to match up SQL_INSERT_CONTACT, it also need 5 fields
                contact.getFirstName(),
                contact.getLastName(),
                contact.getCompany(),
                contact.getPhone(),
                contact.getEmail());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                                                Integer.class);
        // set the new id value on the contact object and return it
        contact.setContactId(newId);//take contact, and set Id
        return contact;
    }

    @Override
    public void removeContact(long contactId) {//SQL_DELETE_CONTACT prepare statement only take one parameter
        jdbcTemplate.update(SQL_DELETE_CONTACT, contactId);//We are not using transaction, becasue we only do one thing
    }
    
    @Override
    public void updateContact(Contact contact) {//SQL_UPDATE_CONTACT prepare statement
        jdbcTemplate.update(SQL_UPDATE_CONTACT,
            contact.getFirstName(),
            contact.getLastName(),
            contact.getCompany(),
            contact.getPhone(),
            contact.getEmail(),
            contact.getContactId());//the contact id, which fills where clause
    }
    
    //the rest, we get things back, use query, to get a list of things back
    @Override
    public List<Contact> getAllContacts() {//give a list back of all DTOs, 
        //take a row, turns into DTO, no matter how many, 100 or more, it will return a list
        return jdbcTemplate.query(SQL_SELECT_ALL_CONTACTS, new ContactMapper());
    }

    @Override
    public Contact getContactById(long contactId) {//only expect one contact
        try {//use query for object, only exactly one thing back, 
            return jdbcTemplate.queryForObject(SQL_SELECT_CONTACT,//prepare statement, 
                    new ContactMapper(), contactId);
        } catch (EmptyResultDataAccessException ex) {//it's ok if return nothing
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
        return getAllContacts();
        } else {
            // build a prepared statement based on the user's search
            // terms
            StringBuilder sQuery = 
                    new StringBuilder("select * from contacts where ");
            // we dynamially build the where clause, or prepare statement
            int numParams = criteria.size();
            int paramPosition = 0;
            // we'll put the positional parameters into an array, the 
            // order of the parameters will match the order in which we 
            // get the search criteria from the map
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            // build up the where clause based on the key/value pairs in 
            // the map build where clause and positional parameter array
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                // if we are not the first one in, we must add an AND to 
                // the where clause
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                // now append our criteria name
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                // grab the value for this search criteria and put it into 
                // the paramVals array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

        return jdbcTemplate.query(sQuery.toString(),
                new ContactMapper(),
                paramVals);
        }
    }
    
    private static final class ContactMapper implements RowMapper<Contact> {
        //contactMapper implement RowMapper, which only has this one method mapRow
        //knows database
        //knows DTO, so it's a interface
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException { //it return a contact, 
            //two parameters, rowNum means which row it is, rs means that content in that row
            Contact contact = new Contact();
            contact.setContactId(rs.getLong("contact_id"));//"contact_id" is the column name, the string representation
            contact.setFirstName(rs.getString("first_name"));
            contact.setLastName(rs.getString("last_name"));
            contact.setCompany(rs.getString("company"));
            contact.setPhone(rs.getString("phone"));
            contact.setEmail(rs.getString("email"));
            return contact;
        }
    }
}
