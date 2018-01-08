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
public enum SearchTerm {
    FIRST_NAME, LAST_NAME, COMPANY, PHONE, EMAIL
}//doesn't make sense to search by everything, but the only things from above, so restrict keys to only four things
//ignore search terms that doesn't make sense
