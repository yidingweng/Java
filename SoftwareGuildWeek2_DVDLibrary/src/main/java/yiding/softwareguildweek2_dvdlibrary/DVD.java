/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek2_dvdlibrary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yidingweng
 */
public class DVD {
    protected String title;
    protected LocalDate releaseDate;
    protected String mPAARating;
    protected String directorName;
    protected String studio;
    protected String userRating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String date) {
        releaseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.releaseDate = releaseDate;
    }

    public String getmPAARating() {
        return mPAARating;
    }

    public void setmPAARating(String mPAARating) {
        this.mPAARating = mPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}
