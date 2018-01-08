/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero.dao;

import com.mycompany.softwareguildweek10_superhero.model.Location;
import com.mycompany.softwareguildweek10_superhero.model.Organization;
import com.mycompany.softwareguildweek10_superhero.model.Sighting;
import com.mycompany.softwareguildweek10_superhero.model.SuperHero;
import com.mycompany.softwareguildweek10_superhero.model.SuperPower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yidingweng
 */
public class SuperHeroWorldDaoDbImpl implements SuperHeroWorldDao{
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //Group1_Location
    private static final String SQL_INSERT_LOCATION
    = "insert into Location (LocationName, Description, AddressInfo, Latitude, "
    + "Longitude) values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
        = "delete from Location where Locationid = ?";

    private static final String SQL_UPDATE_LOCATION
        = "update Location set LocationName = ?, Description = ?, AddressInfo = ?, "
        + "Latitude = ?, Longitude = ? where Locationid = ?";

    private static final String SQL_SELECT_LOCATION
        = "select * from Location where Locationid = ?";
    
    private static final String SQL_SELECT_ALL_LOCATIONS
        = "select * from Location";
    
    private static final String SQL_SELECT_LOCATIONS_BY_SUPERHERO_ID //a more complicated situation
        //with joint, need to look at bridge table contants many to many relationships
        = "select lo.Locationid, lo.LocationName, lo.Description, lo.AddressInfo, "
        + "lo.Latitude, lo.Longitude from Location lo "
        + "join Sighting si on lo.Locationid = si.Locationid where "
        + "si.SuperHeroid = ?";
    
    //Group2 SuperHero AND Membership And Sighting And HeroPower
    private static final String SQL_INSERT_SUPERHERO
        = "insert into SuperHero "
        + "(SuperHeroName, Description, Nature) "
        + "values (?, ?, ?)";
    private static final String SQL_INSERT_HEROPOWER//FOR HELPER METHOD_1
        = "insert into HeroPower (SuperHeroid, SuperPowerid) values(?, ?)";
    
    private static final String SQL_INSERT_MEMBERSHIP//FOR HELPER METHOD_2
        = "insert into Membership (SuperHeroid, Organizationid) values(?, ?)";
    
    private static final String SQL_INSERT_SUPERHEROSIGHTING//FOR HELPER METHOD_3
        = "insert into SuperHeroSighting (SuperHeroid, Sightingid) values(?, ?)";
    
    private static final String SQL_DELETE_SUPERHERO
        = "delete from SuperHero where SuperHeroid = ?";

    private static final String SQL_DELETE_HEROPOWER//DELETE_REL_1
        = "delete from HeroPower where SuperHeroid = ?";
    
    private static final String SQL_DELETE_HEROPOWER2//DELETE_REL_1
        = "delete from HeroPower where SuperPowerid = ?";
    
    private static final String SQL_DELETE_MEMBERSHIP//DELETE_REL_2
        = "delete from Membership where SuperHeroid = ?";
    
    private static final String SQL_DELETE_MEMBERSHIP2
        = "delete from Membership where Organizationid = ?";
    
    private static final String SQL_DELETE_SUPERHEROSIGHTING//DELETE_REL_3
        = "delete from SuperHeroSighting where Sightingid = ?";
    
    private static final String SQL_DELETE_SUPERHEROSIGHTING2
        = "delete from SuperHeroSighting where SuperHeroid = ?";

    private static final String SQL_UPDATE_SUPERHERO
        = "update SuperHero set SuperHeroName = ?, Description = ?, Nature = ? "
        + "where SuperHeroid = ?";

    private static final String SQL_SELECT_SUPERHERO
        = "select * from SuperHero where SuperHeroid = ?";

    private static final String SQL_SELECT_ALL_SUPERHEROS
        = "select * from SuperHero";

    private static final String SQL_SELECT_SUPERHEROS_BY_SUPERPOWER_ID
       = "select sh.SuperHeroid, sh.SuperHeroName, sh.Description, sh.Nature "
       + "from SuperHero sh join HeroPower hp on SuperPowerid "
       + "where sh.SuperHeroid = hp.SuperHeroid "
       + "and hp.SuperPowerid = ?;";
    
    private static final String SQL_SELECT_SUPERHEROS_BY_ORGANIZATION_ID
       = "select sh.SuperHeroid, sh.SuperHeroName, sh.Description, sh.Nature "
       + "from SuperHero sh join Membership me on Organizationid "
       + "where sh.SuperHeroid = me.SuperHeroid "
       + "and me.Organizationid = ?;";
    
    private static final String SQL_SELECT_SUPERHEROS_BY_SIGHTING_ID
       = "select sh.SuperHeroid, sh.SuperHeroName, sh.Description, sh.Nature "
        + "from SuperHero sh "
        + "join SuperHeroSighting shs on sh.SuperHeroid = shs.SuperHeroid where "
        + "shs.Sightingid = ?";
    
    private static final String SQL_SELECT_SUPERHEROS_BY_LOCATION_ID
       = "select sh.SuperHeroid, sh.SuperHeroName, sh.Description, sh.Nature "
       + "from SuperHero sh join Sighting si on Locationid "
       + "where sh.SuperHeroid = si.SuperHeroid "
       + "and si.Locationid = ?";
    
    
    
    //Group3_SuperPower
    private static final String SQL_INSERT_SUPERPOWER
        = "insert into SuperPower (PowerName, Description) values (?, ?)";
    
    private static final String SQL_DELETE_SUPERPOWER
        = "delete from SuperPower where SuperPowerid = ?";
    
    private static final String SQL_UPDATE_SUPERPOWER
        = "update SuperPower set PowerName = ?, Description = ? where SuperPowerid = ?";
    
    private static final String SQL_SELECT_SUPERPOWER
        = "select * from SuperPower where SuperPowerid = ?";
    
    private static final String SQL_SELECT_ALL_SUPERPOWERS
        = "select * from SuperPower";
    
    private static final String SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID //a more complicated situation
        //with joint, need to look at bridge table contants many to many relationships
        = "select sp.SuperPowerid, sp.PowerName, sp.Description from SuperPower sp "
        + "join HeroPower hp on sp.SuperPowerid = hp.SuperPowerid where "
        + "hp.SuperHeroid = ?";
    
    //Group4_Organization
    private static final String SQL_INSERT_ORGANIZATION
        = "insert into Organization (OrganizationName, Description, Address, `Email`) values (?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
        = "delete from Organization where Organizationid = ?";

    private static final String SQL_UPDATE_ORGANIZATION
        = "update Organization set OrganizationName = ?, Description = ?, Address = ?, "
        + "Email = ? where Organizationid = ?";

    private static final String SQL_SELECT_ORGANIZATION
        = "select * from Organization where Organizationid = ?";
    
    private static final String SQL_SELECT_ALL_ORGANIZATIONS
        = "select * from Organization";
    
    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID //a more complicated situation
        //with joint, need to look at bridge table contants many to many relationships
        = "select o.Organizationid, o.OrganizationName, o.Description, o.Address, "
        + "o.Email from Organization o "
        + "join Membership me on o.Organizationid = me.Organizationid where "
        + "me.SuperHeroid = ?";
    
    //Group5_Sighting
    private static final String SQL_INSERT_SIGHTING
        = "insert into Sighting (Locationid, Date) values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
        = "delete from Sighting where Sightingid = ?";

    private static final String SQL_UPDATE_SIGHTING
        = "update Sighting set Locationid = ?, Date = ? "
        + "where Sightingid = ?";
    
    private static final String SQL_SELECT_SIGHTING
        = "select * from Sighting where Sightingid = ?";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS
        = "select * from Sighting";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_SIGHTINGDATE
        = "select * from Sighting where Date = ?";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATIONID
        = "select * from Sighting where Locationid = ?";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATIONID_AND_SIGHTINGDATE
        = "select * from Sighting where Locationid = ? "
        + "and Date = ? ";
    
    private static final String SQL_SELECT_SIGHTINGS_ORDERBY_DATE
        = "select * from Sighting ORDER BY `Date` DESC";
    
    //Group1_Location
    @Override//tttttttttttttt
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
            location.getLocationName(),
            location.getDescription(),
            location.getAddressInfo(),
            location.getLatitude(),
            location.getLongitude());

        int locationid = 
            jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);//??????

        location.setLocationid(locationid);
    }

    @Override//tttttttttttttt
    public void deleteLocation(int locationid) {
        deleteSightingsByLocationid(locationid);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationid);
    }

    @Override//tttttttttttttt
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
            location.getLocationName(),
            location.getDescription(),
            location.getAddressInfo(),
            location.getLatitude(),
            location.getLongitude(),
            location.getLocationid());
        
    }

    @Override//tttttttttttttt
    public Location getLocationByid(int locationid) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, 
                                               new LocationMapper(), 
                                               locationid);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override//tttttttttttttt
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, 
                                  new LocationMapper());
    }
    
    //mapping_Location
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location lo = new Location();
            lo.setLocationid(rs.getInt("Locationid"));
            lo.setLocationName(rs.getString("LocationName"));
            lo.setDescription(rs.getString("Description"));
            lo.setAddressInfo(rs.getString("AddressInfo"));
            lo.setLatitude(rs.getDouble("Latitude"));
            lo.setLongitude(rs.getDouble("Longitude"));
            return lo;
        }
    }
    
    //Group2_SuperHero
    @Override//tttttttttttttt
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperHero(SuperHero superHero) {
        // first insert into SuperHero table and get newly generated SuperHeroid
        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
            superHero.getSuperHeroName(),
            superHero.getDescription(),
            superHero.getNature());
        int superHeroid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                                   Integer.class);
                
        superHero.setSuperHeroid(superHeroid);
        // now update the HeroPower table
        insertHeroPower(superHero);
        // now update the Membership table
        insertMembership(superHero);
        // now update the Sighting table
        //insertSighting(superHero);
    }

    @Override//tttttttttttttt
    public void deleteSuperHero(int superHeroid) {
        //delete heroPower relationship for this superHero
        jdbcTemplate.update(SQL_DELETE_HEROPOWER, superHeroid);
        //delete membership relationship for this superHero
        jdbcTemplate.update(SQL_DELETE_MEMBERSHIP, superHeroid);
        
        jdbcTemplate.update(SQL_DELETE_SUPERHEROSIGHTING2, superHeroid);
        //delete sighting relationship for this superHero
        //jdbcTemplate.update(SQL_DELETE_SIGHTING, superHeroid);
        //delete superHero
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superHeroid);
    }

    @Override//tttttttttttttt
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperHero(SuperHero superHero) {
        // update books table
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
            superHero.getSuperHeroName(),
            superHero.getDescription(),
            superHero.getNature(),
            superHero.getSuperHeroid());
                
        // delete HeroPower relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_HEROPOWER, superHero.getSuperHeroid());
        insertHeroPower(superHero);
        // delete Membership relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_MEMBERSHIP, superHero.getSuperHeroid());
        insertMembership(superHero);
        // delete Sighting relationships and then reset them
        //jdbcTemplate.update(SQL_DELETE_SIGHTING, superHero.getSuperHeroid());
        //insertSighting(superHero);
    }

    @Override//tttttttttttttt
    public SuperHero getSuperHeroByid(int superHeroid) {
        try {
            //get the properties from the books table
            SuperHero superHero = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, 
                                                    new SuperHeroMapper(), 
                                                    superHeroid);
            //get the Authors for this book and set list on the book
            superHero.setSuperPowers(findSuperPowersForSuperHero(superHero));
            superHero.setOrganizations(findOrganizationsForSuperHero(superHero));
            //superHero.setLocations(findLocationsForSuperHero(superHero));
            return superHero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override//tttttttttttttt
    public List<SuperHero> getAllSuperHeros() {
        // get all the superHeros
        List<SuperHero> superHeroList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROS, 
                                                 new SuperHeroMapper());
        // set the list of superPower and list of organizations and list of locations for each superHero
        return associateSuperPowersAndOrganizationsAndLocationsWithSuperHeros(superHeroList);
    }
    
    @Override//tttttttttttttt
    public List<SuperHero> getSuperHerosBySuperPowerid(int superPowerid) {
        // get the superHeros use this superPower
        List<SuperHero> superHeroList = 
                jdbcTemplate.query(SQL_SELECT_SUPERHEROS_BY_SUPERPOWER_ID, 
                                   new SuperHeroMapper(), 
                                   superPowerid);
        // set the list of superPower and list of organizations and list of locations for each superHero
        return associateSuperPowersAndOrganizationsAndLocationsWithSuperHeros(superHeroList);
    }
    
    @Override
    public List<SuperHero> getSuperHerosByLocationid(int locationid) {
        // get the superHeros use this location
        List<SuperHero> superHeroList = 
                jdbcTemplate.query(SQL_SELECT_SUPERHEROS_BY_LOCATION_ID, 
                                   new SuperHeroMapper(), 
                                   locationid);
        // set the list of superPower and list of organizations and list of locations for each superHero
        return associateSuperPowersAndOrganizationsAndLocationsWithSuperHeros(superHeroList);
    }
    
    @Override//tttttttttttttt
    public List<SuperHero> getSuperHerosByOrganizationid(int organizationid) {
        // get the superHeros use this organization
        List<SuperHero> superHeroList = 
                jdbcTemplate.query(SQL_SELECT_SUPERHEROS_BY_ORGANIZATION_ID, 
                                   new SuperHeroMapper(), 
                                   organizationid);
        // set the list of superPower and list of organizations and list of locations for each superHero
        return associateSuperPowersAndOrganizationsAndLocationsWithSuperHeros(superHeroList);
    }
    
    //mapping_SuperHero
    private static final class SuperHeroMapper implements RowMapper<SuperHero> {

        @Override
        public SuperHero mapRow(ResultSet rs, int i) throws SQLException {
            SuperHero sh = new SuperHero();
            sh.setSuperHeroid(rs.getInt("SuperHeroid"));
            sh.setSuperHeroName(rs.getString("SuperHeroName"));
            sh.setDescription(rs.getString("Description"));
            sh.setNature(rs.getString("Nature"));
            return sh;
        }
    }
    
    //Group3_SuperPower
    @Override//tttttttttttttt
    public void addSuperPower(SuperPower superPower){
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
            superPower.getPowerName(),
            superPower.getDescription());

        int superPowerid = 
            jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        superPower.setSuperPowerid(superPowerid);
    };
    
    @Override//tttttttttttttt
    public void deleteSuperPower(int superPowerid){
        jdbcTemplate.update(SQL_DELETE_HEROPOWER2, superPowerid);
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superPowerid);
    };
    
    @Override//tttttttttttttt
    public void updateSuperPower(SuperPower superPower){
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
            superPower.getPowerName(),
            superPower.getDescription(),
            superPower.getSuperPowerid());
    };
    
    @Override//tttttttttttttt
    public SuperPower getSuperPowerByid(int superPowerid){
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, 
                                               new SuperPowerMapper(), 
                                               superPowerid);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    };
    
    @Override//tttttttttttttt
    public List<SuperPower> getAllSuperPowers(){
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, 
                                  new SuperPowerMapper());
    };
    //mapping_SuperPower
    private static final class SuperPowerMapper implements RowMapper<SuperPower> {

        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower sp = new SuperPower();
            sp.setSuperPowerid(rs.getInt("SuperPowerid"));
            sp.setPowerName(rs.getString("PowerName"));
            sp.setDescription(rs.getString("Description"));
            return sp;
        }
    }

    //Group4_Organization
    @Override//tttttttttttttt
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
            organization.getOrganizationName(),
            organization.getDescription(),
            organization.getAddress(),
            organization.getEmail());

        int organizationid = 
            jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        organization.setOrganizationid(organizationid);
    }

    @Override//tttttttttttttt
    public void deleteOrganization(int organizationid) {
        jdbcTemplate.update(SQL_DELETE_MEMBERSHIP2, organizationid);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationid);
    }

    @Override//tttttttttttttt
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
            organization.getOrganizationName(),
            organization.getDescription(),
            organization.getAddress(),
            organization.getEmail(),
            organization.getOrganizationid());
    }

    @Override//tttttttttttttt
    public Organization getOrganizationByid(int organizationid) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, 
                                               new OrganizationMapper(), 
                                               organizationid);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override//tttttttttttttt
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, 
                                  new OrganizationMapper());
    }
    
    //mapping_Organization
    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization or = new Organization();
            or.setOrganizationid(rs.getInt("Organizationid"));
            or.setOrganizationName(rs.getString("OrganizationName"));
            or.setDescription(rs.getString("Description"));
            or.setAddress(rs.getString("Address"));
            or.setEmail(rs.getString("Email"));
            return or;
        }
    }
    
    //Group5_Sighting
    @Override//tttttttttttttt
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting){
        // first insert into SuperHero table and get newly generated SuperHeroid
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
            sighting.getLocationid(),
            java.sql.Date.valueOf(sighting.getSightingDate()));
        
        int sightingid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                                   Integer.class);
                
        sighting.setSightingid(sightingid);
        insertSuperHeroSighting(sighting);
    }
    
    @Override//tttttttttttttt
    public void deleteSighting(int sightingid){
        jdbcTemplate.update(SQL_DELETE_SUPERHEROSIGHTING, sightingid);
        
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingid);
    }
    
    @Override//tttttttttttttt
    public void updateSighting(Sighting sighting){
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
            sighting.getLocationid(),
            java.sql.Date.valueOf(sighting.getSightingDate()),
            sighting.getSightingid());
        
        jdbcTemplate.update(SQL_DELETE_SUPERHEROSIGHTING, sighting.getSightingid());
        insertSuperHeroSighting(sighting);
        
    }
    
    @Override//tttttttttttttt
    public Sighting getSightingByid(int sightingid){
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, 
                                               new SightingMapper(), 
                                               sightingid);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override//tttttttttttttt
    public List<Sighting> getAllSightings(){
        
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, 
                                                 new SightingMapper());
        // set the list of superPower and list of organizations and list of locations for each superHero
        return associateSuperHerosWithSightings(sightingList);
    }
    
    @Override
    public List<Sighting> getSightingsOrderByDate(){
        List<Sighting> sightingList = new ArrayList<Sighting>();
        sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_ORDERBY_DATE, 
                                                 new SightingMapper());
        return associateSuperHerosWithSightings(sightingList);
    }
    
    @Override
    public List<Sighting> getSightingsByLocationidAndSightingDate(int locationid, LocalDate sightingDate) {
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATIONID_AND_SIGHTINGDATE, 
                                   new SightingMapper(), 
                                   locationid,
                                   java.sql.Date.valueOf(sightingDate));
        // set the list of superPower and list of organizations and list of locations for each superHero
        return associateSuperHerosWithSightings(sightingList);
    }

    @Override//tttttttttttttt
    public List<Sighting> getSightingsBySightingDate(LocalDate sightingDate) {
        // get the superHeros use this organization
      
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SIGHTINGDATE, 
                                   new SightingMapper(), 
                                   java.sql.Date.valueOf(sightingDate));
        // set the list of superPower and list of organizations and list of locations for each superHero
        return associateSuperHerosWithSightings(sightingList);
    }
    
    public void deleteSightingsByLocationid(int locationid) {//??????????????????????????????????????????????????????????????????????????????????????
        // get the superHeros use this organization
      
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATIONID, 
                                   new SightingMapper(), 
                                   locationid);
        for (int i = 0;i < sightingList.size();i++){
            deleteSighting(sightingList.get(i).getSightingid());
           
        }
        
    }
    
    //mapping_Organization
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting si = new Sighting();
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            //String date = rs.getString("Date");
            //LocalDate localDate = LocalDate.parse(date, formatter);
            si.setSightingid(rs.getInt("Sightingid"));
            si.setLocationid(rs.getInt("Locationid"));
            si.setSightingDate(rs.getDate("Date").toLocalDate());
            
            return si;
        }
    }
    
    //helper Methods
    //HELPER METHOD_1 //tttttttttttttt
    private void insertHeroPower(SuperHero superHero) {
        final int superHeroid = superHero.getSuperHeroid();
        final List<SuperPower> superPowers = superHero.getSuperPowers();

        // Update the HeroPower bridge table with an entry for 
        // each superPower of this superHero
        for (SuperPower currentSuperPower : superPowers) {
            jdbcTemplate.update(SQL_INSERT_HEROPOWER, 
                                superHeroid, 
                                currentSuperPower.getSuperPowerid());
        }
    }
    
    //HELPER METHOD_2 //tttttttttttttt
    private void insertMembership(SuperHero superHero) {
        final int superHeroid = superHero.getSuperHeroid();
        final List<Organization> organizations = superHero.getOrganizations();

        for (Organization currentOrganization : organizations) {
            jdbcTemplate.update(SQL_INSERT_MEMBERSHIP, 
                                superHeroid, 
                                currentOrganization.getOrganizationid());
        }
    }
    
    //HELPER METHOD_3 //tttttttttttttt
    private void insertSuperHeroSighting(Sighting sighting) {
        final int sightingid = sighting.getSightingid();
        final List<SuperHero> superHeros = sighting.getSuperHeros();

        for (SuperHero currentSuperHero: superHeros) {
            int heroID = currentSuperHero.getSuperHeroid();
            jdbcTemplate.update(SQL_INSERT_SUPERHEROSIGHTING, 
                                currentSuperHero.getSuperHeroid(),
                                sightingid);
        }
    }
    
    //HELPER METHOD_1.1 //tttttttttttttt
    private List<SuperPower> findSuperPowersForSuperHero(SuperHero superHero) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID, 
                                  new SuperPowerMapper(), 
                                  superHero.getSuperHeroid());
    }
    
    //HELPER METHOD_2.1 //tttttttttttttt
    private List<Organization> findOrganizationsForSuperHero(SuperHero superHero) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID, 
                                    new OrganizationMapper(), 
                                    superHero.getSuperHeroid());
    }
    
    //HELPER METHOD_3.1
    /*private List<Location> findLocationsForSuperHero(SuperHero superHero) {
        return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_SUPERHERO_ID, 
                                    new LocationMapper(), 
                                    superHero.getSuperHeroid());
    }*/
    
    //WAS HELPER METHOD_4.1 //tttttttttttttt
    @Override
    public List<SuperHero> findSuperHerosForSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_SUPERHEROS_BY_SIGHTING_ID, 
                                    new SuperHeroMapper(), 
                                    sighting.getSightingid());
    }
    
    //HELPER METHOD_4
    private List<SuperHero> associateSuperPowersAndOrganizationsAndLocationsWithSuperHeros(List<SuperHero> superHeroList) {
        // set the complete list of author ids for each book
        for (SuperHero currentSuperHero : superHeroList) {
            // add SuperPowers to current SuperHero
            currentSuperHero.setSuperPowers(findSuperPowersForSuperHero(currentSuperHero));
            // add Organizations to current SuperHero
            currentSuperHero.setOrganizations(findOrganizationsForSuperHero(currentSuperHero));
            // add Locations to current SuperHero
            //currentSuperHero.setLocations(findLocationsForSuperHero(currentSuperHero));
        }
        return superHeroList;
    }
    
    //HELPER METHOD_5
    private List<Sighting> associateSuperHerosWithSightings(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            // add SuperPowers to current SuperHero
            currentSighting.setSuperHeros(findSuperHerosForSighting(currentSighting));
            // add Organizations to current SuperHero
            
        }
        return sightingList;
    }
}
