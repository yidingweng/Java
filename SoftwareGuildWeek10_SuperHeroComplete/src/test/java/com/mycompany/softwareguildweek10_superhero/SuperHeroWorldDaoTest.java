/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.Location;
import com.mycompany.softwareguildweek10_superhero.model.Organization;
import com.mycompany.softwareguildweek10_superhero.model.Sighting;
import com.mycompany.softwareguildweek10_superhero.model.SuperHero;
import com.mycompany.softwareguildweek10_superhero.model.SuperPower;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yidingweng
 */
public class SuperHeroWorldDaoTest {
    SuperHeroWorldDao dao;
    
    public SuperHeroWorldDaoTest(){
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    @Before
    public void setUp() {
     ApplicationContext ctx
      = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("superHeroWorldDao", SuperHeroWorldDao.class);
        
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingid());
        }
        
        List<SuperHero> superHeros = dao.getAllSuperHeros();
        for (SuperHero currentSuperHero : superHeros) {
            dao.deleteSuperHero(currentSuperHero.getSuperHeroid());
        }
        
        List<SuperPower> superPowers = dao.getAllSuperPowers();
        for (SuperPower currentSuperPower : superPowers) {
            dao.deleteSuperPower(currentSuperPower.getSuperPowerid());
        }

        List<Location> locations = dao.getAllLocations();
        for (Location currentlocation : locations) {
            dao.deleteLocation(currentlocation.getLocationid());
        }
        
        List<Organization> organizations = dao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            dao.deleteOrganization(currentOrganization.getOrganizationid());
        }

    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void addGetLocation() {
        Location location = new Location();
        location.setLocationName("Tokyo");
        location.setDescription("The fallen world");
        location.setAddressInfo("123th street Okinawa");
        location.setLatitude(35.6895);
        location.setLongitude(139.6917);

        dao.addLocation(location);

        Location fromDao = dao.getLocationByid(location.getLocationid());
        assertEquals(fromDao.getLocationName(), location.getLocationName());
        assertEquals(fromDao.getDescription(), location.getDescription());
        assertEquals(fromDao.getAddressInfo(), location.getAddressInfo());
        assertEquals(fromDao.getLatitude(), location.getLatitude(), 0.0001);
        assertEquals(fromDao.getLongitude(), location.getLongitude(), 0.0001);
    }
    
    @Test
    public void updateLocation() {
        Location tokyo = new Location();
        tokyo.setLocationName("Tokyo");
        tokyo.setDescription("The fallen world");
        tokyo.setAddressInfo("123th street Okinawa");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);

        dao.addLocation(tokyo);
        
        tokyo.setLocationName("Tokyo Japan");
        tokyo.setDescription("sushi manufacturer");
        tokyo.setAddressInfo("45th street Akihabara");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);
        
        dao.updateLocation(tokyo);

        Location fromDao = dao.getLocationByid(tokyo.getLocationid());
        assertEquals(fromDao.getLocationName(), "Tokyo Japan");
        assertEquals(fromDao.getDescription(), "sushi manufacturer");
        assertEquals(fromDao.getAddressInfo(), "45th street Akihabara");
        assertEquals(fromDao.getLatitude(), 35.6895, 0.0001);
        assertEquals(fromDao.getLongitude(), 139.6917, 0.0001);
    }
    
    @Test
    public void getAllLocations() {
        Location tokyo = new Location();
        tokyo.setLocationName("Tokyo");
        tokyo.setDescription("The fallen world");
        tokyo.setAddressInfo("123th street Okinawa");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);

        dao.addLocation(tokyo);
        
        Location newYork = new Location();
        newYork.setLocationName("New York");
        newYork.setDescription("Headquarter");
        newYork.setAddressInfo("13th street Queens");
        newYork.setLatitude(40.7128);
        newYork.setLongitude(-74.0059);
        
        dao.addLocation(newYork);
        
        List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        locations.add(newYork);
        
        //assertEquals(dao.getAllLocations().get(0), locations.get(0));////////////why I cannot compare the address of the same object????????
        assertEquals(dao.getAllLocations().get(0).getLocationid(), locations.get(0).getLocationid());
        assertEquals(dao.getAllLocations().get(0).getDescription(), locations.get(0).getDescription());
        assertEquals(dao.getAllLocations().get(1).getLocationid(), locations.get(1).getLocationid());
        assertEquals(dao.getAllLocations().get(1).getLatitude(), locations.get(1).getLatitude(),0.0001);
    }
    
    @Test
    public void deleteLocation() {
        Location location = new Location();
        location.setLocationName("Tokyo");
        location.setDescription("The fallen world");
        location.setAddressInfo("123th street Okinawa");
        location.setLatitude(35.6895);
        location.setLongitude(139.6917);

        dao.addLocation(location);

        Location fromDao = dao.getLocationByid(location.getLocationid());
        assertEquals(fromDao.getLocationName(), location.getLocationName());
        assertEquals(fromDao.getDescription(), location.getDescription());
        assertEquals(fromDao.getAddressInfo(), location.getAddressInfo());
        assertEquals(fromDao.getLatitude(), location.getLatitude(), 0.0001);
        assertEquals(fromDao.getLongitude(), location.getLongitude(), 0.0001);
        dao.deleteLocation(location.getLocationid());
        assertNull(dao.getLocationByid(location.getLocationid()));
    }
    
    @Test
    public void addGetSuperPower() {
        SuperPower superPower = new SuperPower();
        superPower.setPowerName("Thunder");
        superPower.setDescription("Create thunder");
        dao.addSuperPower(superPower);

        SuperPower fromDao = dao.getSuperPowerByid(superPower.getSuperPowerid());
        assertEquals(fromDao.getPowerName(), superPower.getPowerName());
        assertEquals(fromDao.getDescription(), superPower.getDescription());
    }
    
    @Test
    public void updateSuperPower() {
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        thunder.setPowerName("Thunder with optional storm");
        thunder.setDescription("Create thunder, sometimes there might be storm");
        
        dao.updateSuperPower(thunder);
        
        SuperPower fromDao = dao.getSuperPowerByid(thunder.getSuperPowerid());
        assertEquals(fromDao.getPowerName(), "Thunder with optional storm");
        assertEquals(fromDao.getDescription(), "Create thunder, sometimes there might be storm");
    }
    
    @Test
    public void getALLSuperPowers() {
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        
        dao.addSuperPower(thunder);
        
        SuperPower thunderStorm = new SuperPower();
        thunderStorm.setPowerName("Thunder with storm");
        
        dao.addSuperPower(thunderStorm);
        
        List<SuperPower> superPowers = new ArrayList<>();
        
        superPowers.add(thunder);
        superPowers.add(thunderStorm);
        
        assertEquals(superPowers.get(0).getPowerName(), dao.getAllSuperPowers().get(0).getPowerName());
        assertEquals(dao.getAllSuperPowers().get(0).getPowerName(),"Thunder");
        assertEquals(superPowers.get(1).getDescription(), dao.getAllSuperPowers().get(1).getDescription());
    }
    
    @Test
    public void deleteSuperPower() {
        SuperPower superPower = new SuperPower();
        superPower.setPowerName("Thunder");
        superPower.setDescription("Create thunder");
        dao.addSuperPower(superPower);

        SuperPower fromDao = dao.getSuperPowerByid(superPower.getSuperPowerid());
        assertEquals(fromDao.getPowerName(), superPower.getPowerName());
        assertEquals(fromDao.getDescription(), superPower.getDescription());
        dao.deleteSuperPower(superPower.getSuperPowerid());
        assertNull(dao.getSuperPowerByid(superPower.getSuperPowerid()));
    }
    
    @Test
    public void addGetOrganization() {
        Organization organization = new Organization();
        organization.setOrganizationName("CIA");
        organization.setDescription("Central Inteliengence Agency");
        organization.setAddress("23th ave, Washington DC");
        organization.setEmail("intel@cia.org");

        dao.addOrganization(organization);

        Organization fromDao = dao.getOrganizationByid(organization.getOrganizationid());
        assertEquals(fromDao.getOrganizationName(), organization.getOrganizationName());
        assertEquals(fromDao.getDescription(), organization.getDescription());
        assertEquals(fromDao.getAddress(), organization.getAddress());
        assertEquals(fromDao.getEmail(), organization.getEmail());
    }
    
    @Test
    public void updateOrganization() {
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        cia.setDescription("Chile India America");
        cia.setAddress("An unknown restaurant");
        cia.setEmail("unknown@cia.org");
        
        dao.updateOrganization(cia);
        
        Organization fromDao = dao.getOrganizationByid(cia.getOrganizationid());
        assertEquals(fromDao.getOrganizationName(), "CIA");
        assertEquals(fromDao.getDescription(), "Chile India America");
        assertEquals(fromDao.getAddress(), "An unknown restaurant");
        assertEquals(fromDao.getEmail(), "unknown@cia.org");
    }
    
    @Test
    public void getAllOrganizations() {
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Organization anotherCia = new Organization();
        anotherCia.setOrganizationName("CIA");
        anotherCia.setDescription("Chile India America");
        anotherCia.setAddress("An unknown restaurant");
        anotherCia.setEmail("unknown@cia.org");
        
        dao.addOrganization(anotherCia);
        
        Organization fakeCia = new Organization();
        fakeCia.setOrganizationName("CIA");
        fakeCia.setDescription("Fake Central Inteliengence Agency");
        fakeCia.setAddress("A fake restaurant");
        fakeCia.setEmail("unknown@fakecia.org");
        
        dao.addOrganization(fakeCia);
        
        List<Organization> organizations = new ArrayList<>();
        
        organizations.add(cia);
        organizations.add(anotherCia);
        organizations.add(fakeCia);
        
        assertEquals(dao.getAllOrganizations().get(1).getOrganizationName(), "CIA");
        assertEquals(dao.getAllOrganizations().get(1).getOrganizationName(), organizations.get(1).getOrganizationName());
        assertEquals(dao.getAllOrganizations().get(0).getAddress(), organizations.get(0).getAddress());
        assertEquals(dao.getAllOrganizations().get(0).getEmail(), organizations.get(0).getEmail());
        assertEquals(dao.getAllOrganizations().get(2).getOrganizationid(), organizations.get(2).getOrganizationid());
        assertEquals(dao.getAllOrganizations().get(2).getDescription(), organizations.get(2).getDescription());
    }
    
    @Test
    public void addgetSuperHero(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Location tokyo = new Location();
        tokyo.setLocationName("Tokyo");
        tokyo.setDescription("The fallen world");
        tokyo.setAddressInfo("123th street Okinawa");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);

        dao.addLocation(tokyo);
        
        /*Sighting sighting1 = new Sighting();
        sighting1.setSuperHeroid(0);
        sighting1.setLocationid(0);
        sighting1.
        
        dao.addSighting(sighting1);*/
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        khoi.setSuperPowers(superPowers);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        khoi.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        
        dao.addSuperHero(khoi);
        
        SuperHero khoiCopy = dao.getSuperHeroByid(khoi.getSuperHeroid());
        assertEquals(khoiCopy.getSuperHeroName(), khoi.getSuperHeroName());
        assertEquals(khoiCopy.getDescription(), khoi.getDescription());
        assertEquals(khoiCopy.getNature(), khoi.getNature());
        List<SuperPower> khoiPowers = khoi.getSuperPowers();
        List<SuperPower> khoiCopyPowers = khoiCopy.getSuperPowers();
        assertEquals(khoiPowers.size(),khoiCopyPowers.size());
        
        List<Organization> khoiOrganizations = khoi.getOrganizations();
        List<Organization> khoiCopyOrganizations = khoiCopy.getOrganizations();
        assertEquals(khoiOrganizations.size(),khoiCopyOrganizations.size());
       
        //assertEquals(khoiPowers.get(0), khoiCopyPowers.get(0));
        //assertEquals(khoiCopy.getDescription(), khoi.getDescription());
    }
    
    @Test
    public void updateSuperHero(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        SuperPower thunderStorm = new SuperPower();
        thunderStorm.setPowerName("Thunder with storm");
        
        dao.addSuperPower(thunderStorm);
        
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Organization stem = new Organization();
        stem.setOrganizationName("STEM");
        stem.setDescription("Science Technology Engineering Mathematics");
        stem.setAddress("23th ave, Dallas");
        stem.setEmail("math@stem.org");

        dao.addOrganization(stem);
        
        
        /*Sighting sighting1 = new Sighting();
        sighting1.setSuperHeroid(0);
        sighting1.setLocationid(0);
        sighting1.
        
        dao.addSighting(sighting1);*/
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        khoi.setSuperPowers(superPowers);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        khoi.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        
        dao.addSuperHero(khoi);
        
        List<SuperPower> superPowers2 = new ArrayList<>();
        superPowers2.add(thunderStorm);
        khoi.setSuperPowers(superPowers2);
        
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(stem);
        khoi.setOrganizations(organizations2);
        
        dao.updateSuperHero(khoi);
        
        SuperHero khoiCopy = dao.getSuperHeroByid(khoi.getSuperHeroid());
        assertEquals(khoiCopy.getSuperHeroName(), khoi.getSuperHeroName());
        assertEquals(khoiCopy.getDescription(), khoi.getDescription());
        assertEquals(khoiCopy.getNature(), khoi.getNature());
        List<SuperPower> khoiPowers = khoi.getSuperPowers();
        List<SuperPower> khoiCopyPowers = khoiCopy.getSuperPowers();
        assertEquals(khoiPowers.size(),khoiCopyPowers.size());
        
        List<Organization> khoiOrganizations = khoi.getOrganizations();
        List<Organization> khoiCopyOrganizations = khoiCopy.getOrganizations();
        assertEquals(khoiOrganizations.get(0).getOrganizationName(),khoiCopyOrganizations.get(0).getOrganizationName());
       
        //assertEquals(khoiPowers.get(0), khoiCopyPowers.get(0));
        //assertEquals(khoiCopy.getDescription(), khoi.getDescription());
    }
    
    @Test
    public void getAllSuperHeros(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        Organization stem = new Organization();
        stem.setOrganizationName("STEM");
        stem.setDescription("Science Technology Engineering Mathematics");
        stem.setAddress("23th ave, Dallas");
        stem.setEmail("math@stem.org");

        dao.addOrganization(stem);
        
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(stem);
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        khoi.setSuperPowers(superPowers);
        khoi.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        dao.addSuperHero(khoi);
        
        SuperHero ian = new SuperHero();
        ian.setSuperHeroName("Ian");
        ian.setDescription("Grandson of Khoi");
        ian.setNature("Hero");
        ian.setSuperPowers(superPowers);
        ian.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        dao.addSuperHero(ian);
        
        SuperHero roy = new SuperHero();
        roy.setSuperHeroName("Roy");
        roy.setDescription("Grandson of Khoi");
        roy.setNature("Hero");
        roy.setSuperPowers(superPowers);
        roy.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        dao.addSuperHero(roy);
        
        assertEquals(3,dao.getAllSuperHeros().size());
        assertEquals("Khoi",dao.getAllSuperHeros().get(0).getSuperHeroName());
        assertEquals("Ian",dao.getAllSuperHeros().get(1).getSuperHeroName());
        assertEquals("Roy",dao.getAllSuperHeros().get(2).getSuperHeroName());
    }
    
    @Test
    public void getSuperHerosBySuperPoweridByOrganizationid(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        SuperPower thunderStorm = new SuperPower();
        thunderStorm.setPowerName("Thunder with storm");
        
        dao.addSuperPower(thunderStorm);
        
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Organization stem = new Organization();
        stem.setOrganizationName("STEM");
        stem.setDescription("Science Technology Engineering Mathematics");
        stem.setAddress("23th ave, Dallas");
        stem.setEmail("math@stem.org");

        dao.addOrganization(stem);
        
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        khoi.setSuperPowers(superPowers);
        khoi.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        dao.addSuperHero(khoi);
        
        SuperHero ian = new SuperHero();
        ian.setSuperHeroName("Ian");
        ian.setDescription("Grandson of Khoi");
        ian.setNature("Hero");
        ian.setSuperPowers(superPowers);
        ian.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        dao.addSuperHero(ian);
        
        List<SuperHero> superHeroOfCia = dao.getSuperHerosByOrganizationid(cia.getOrganizationid());
        assertEquals(superHeroOfCia.get(0).getSuperHeroName(),"Khoi");
        assertEquals(superHeroOfCia.get(1).getSuperHeroName(),"Ian");
        assertEquals(2,superHeroOfCia.size());
        
        List<SuperHero> superHeroOfThunder = dao.getSuperHerosBySuperPowerid(thunder.getSuperPowerid());
        assertEquals(superHeroOfThunder.get(0).getSuperHeroName(),"Khoi");
        assertEquals(superHeroOfThunder.get(1).getSuperHeroName(),"Ian");
        assertEquals(2,superHeroOfCia.size());
        assertEquals(2,superHeroOfThunder.size());
    }
    
    @Test
    public void deleteSuperHero(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Location tokyo = new Location();
        tokyo.setLocationName("Tokyo");
        tokyo.setDescription("The fallen world");
        tokyo.setAddressInfo("123th street Okinawa");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);

        dao.addLocation(tokyo);
        
        /*Sighting sighting1 = new Sighting();
        sighting1.setSuperHeroid(0);
        sighting1.setLocationid(0);
        sighting1.
        
        dao.addSighting(sighting1);*/
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        khoi.setSuperPowers(superPowers);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        khoi.setOrganizations(organizations);
        /*List<Location> locations = new ArrayList<>();
        locations.add(tokyo);
        khoi.setLocations(locations);*/
        
        dao.addSuperHero(khoi);
        dao.deleteSuperHero(khoi.getSuperHeroid());
        assertNull(dao.getSuperHeroByid(khoi.getSuperHeroid()));
    }
    
    @Test
    public void addgetdeleteSighting(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Location tokyo = new Location();
        tokyo.setLocationName("Tokyo");
        tokyo.setDescription("The fallen world");
        tokyo.setAddressInfo("123th street Okinawa");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);

        dao.addLocation(tokyo);
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        khoi.setSuperPowers(superPowers);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        khoi.setOrganizations(organizations);
        
        dao.addSuperHero(khoi);
        
        List<SuperHero> superHeros = new ArrayList<>();
        superHeros.add(khoi);
        //superHeros.add(khoi);
        
        Sighting inTokyo = new Sighting();
        inTokyo.setLocationid(tokyo.getLocationid());
        inTokyo.setSuperHeros(superHeros);
        inTokyo.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        
        dao.addSighting(inTokyo);
        
        assertEquals(tokyo.getLocationid(),dao.getAllSightings().get(0).getLocationid());
        System.out.println(dao.getAllSightings().get(0).getSuperHeros().get(0).getSuperHeroName());
        assertEquals("Khoi",dao.getAllSightings().get(0).getSuperHeros().get(0).getSuperHeroName());
        assertEquals("Grandson of Darth Vader",dao.getAllSightings().get(0).getSuperHeros().get(0).getDescription());
        
        assertEquals(1,dao.getAllSightings().size());
        assertEquals(1,dao.getAllSightings().get(0).getSuperHeros().size());
        dao.deleteSighting(inTokyo.getSightingid());
        assertEquals(0,dao.getAllSightings().size());
        
        
    }
    
    @Test
    public void update_getSightingsBySightingDate(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Location tokyo = new Location();
        tokyo.setLocationName("Tokyo");
        tokyo.setDescription("The fallen world");
        tokyo.setAddressInfo("123th street Okinawa");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);

        dao.addLocation(tokyo);
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        khoi.setSuperPowers(superPowers);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        khoi.setOrganizations(organizations);
        
        dao.addSuperHero(khoi);
        
        List<SuperHero> superHeros = new ArrayList<>();
        superHeros.add(khoi);
        //superHeros.add(khoi);
        
        Sighting inTokyo = new Sighting();
        inTokyo.setLocationid(tokyo.getLocationid());
        inTokyo.setSuperHeros(superHeros);
        LocalDate date = LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE);
        inTokyo.setSightingDate(date);
        
        dao.addSighting(inTokyo);
        
        List<Sighting> fromDao = dao.getSightingsBySightingDate(date);
        assertEquals(1,fromDao.size());
        
        LocalDate date2 = LocalDate.parse("2014-11-11", 
                         DateTimeFormatter.ISO_DATE);
        
        inTokyo.setSightingDate(date2);
        dao.updateSighting(inTokyo);
        List<Sighting> fromDao2 = dao.getSightingsBySightingDate(date);
        assertEquals(0,fromDao2.size());        
        
    }
    
    @Test
    public void getSightingsByLocationidAndSightingDate(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        dao.addSuperPower(thunder);
        
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Location tokyo = new Location();
        tokyo.setLocationName("Tokyo");
        tokyo.setDescription("The fallen world");
        tokyo.setAddressInfo("123th street Okinawa");
        tokyo.setLatitude(35.6895);
        tokyo.setLongitude(139.6917);

        dao.addLocation(tokyo);
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        khoi.setSuperPowers(superPowers);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        khoi.setOrganizations(organizations);
        
        dao.addSuperHero(khoi);
        
        List<SuperHero> superHeros = new ArrayList<>();
        superHeros.add(khoi);
        //superHeros.add(khoi);
        
        Sighting inTokyo = new Sighting();
        inTokyo.setLocationid(tokyo.getLocationid());
        inTokyo.setSuperHeros(superHeros);
        LocalDate date = LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE);
        inTokyo.setSightingDate(date);
        
        dao.addSighting(inTokyo);
        
        List<Sighting> fromDao = dao.getSightingsByLocationidAndSightingDate(tokyo.getLocationid(),date);
        assertEquals(1,fromDao.size());
        
        LocalDate date2 = LocalDate.parse("2014-11-11", 
                         DateTimeFormatter.ISO_DATE);
        
        inTokyo.setSightingDate(date2);
        dao.updateSighting(inTokyo);
        List<Sighting> fromDao2 = dao.getSightingsByLocationidAndSightingDate(tokyo.getLocationid(),inTokyo.getSightingDate());
        assertEquals(1,fromDao2.size());
        fromDao = dao.getSightingsByLocationidAndSightingDate(tokyo.getLocationid(),date);
        assertEquals(0,fromDao.size());
    }
    
    @Test
    public void findSuperPowersForSuperHero(){
        SuperPower thunder = new SuperPower();
        thunder.setPowerName("Thunder");
        thunder.setDescription("Create thunder");
        
        SuperPower coding = new SuperPower();
        coding.setPowerName("coding");
        coding.setDescription("Write code");
        
        dao.addSuperPower(thunder);
        dao.addSuperPower(coding);
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(thunder);
        superPowers.add(coding);
        khoi.setSuperPowers(superPowers);
        
        assertEquals(superPowers.get(0).getSuperPowerid(), khoi.getSuperPowers().get(0).getSuperPowerid());
        assertEquals(superPowers.get(1).getSuperPowerid(), khoi.getSuperPowers().get(1).getSuperPowerid());
    }
    
    @Test
    public void findOrganizationsForSuperHero(){
        Organization cia = new Organization();
        cia.setOrganizationName("CIA");
        cia.setDescription("Central Inteliengence Agency");
        cia.setAddress("23th ave, Washington DC");
        cia.setEmail("intel@cia.org");

        dao.addOrganization(cia);
        
        Organization stem = new Organization();
        stem.setOrganizationName("STEM");
        stem.setDescription("Science Technology Engineering Mathematics");
        stem.setAddress("23th ave, Dallas");
        stem.setEmail("math@stem.org");

        dao.addOrganization(stem);
        
        SuperHero khoi = new SuperHero();
        khoi.setSuperHeroName("Khoi");
        khoi.setDescription("Grandson of Darth Vader");
        khoi.setNature("Villain");
        List<Organization> organizations = new ArrayList<>();
        organizations.add(cia);
        organizations.add(stem);
        khoi.setOrganizations(organizations);
        
        assertEquals(organizations.get(0).getOrganizationName(), khoi.getOrganizations().get(0).getOrganizationName());
        assertEquals(organizations.get(1).getOrganizationName(), khoi.getOrganizations().get(1).getOrganizationName());
    }
}
