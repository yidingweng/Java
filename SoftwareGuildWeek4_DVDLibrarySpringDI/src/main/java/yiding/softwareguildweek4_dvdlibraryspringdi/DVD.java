/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek4_dvdlibraryspringdi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author yidingweng
 */
public class DVD {
    @NotEmpty(message = "You must supply a value for Title.")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    protected String title;
    @Length(max = 50, message = "Release Date must be no more than 20 characters in length.")
    protected LocalDate releaseDate;
    @Length(max = 50, message = "MPAA-rating must be no more than 50 characters in length.")
    protected String mPAARating;
    @Length(max = 50, message = "Director Name must be no more than 50 characters in length.")
    protected String directorName;
    @Length(max = 50, message = "Studio must be no more than 50 characters in length.")
    protected String studio;
    @Length(max = 50, message = "User-rating must be no more than 50 characters in length.")
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
