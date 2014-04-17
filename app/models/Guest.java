package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.validation.ValidatorMeta;

import play.data.format.Formats;
import play.db.ebean.*;

@Entity
public class Guest extends Model implements Comparable<Guest> {

    @Id
    public Long id;

    public String guestOneName;

    public String guestTwoName;

    public Boolean hasExtraGuest;

    public String rsvpCode;

    public String email;

    public String phoneNumber;

    public String comments;

    public Boolean attending;

    @Formats.DateTime(pattern="MM/dd/yyyy")
    public Date modifiedDate = new Date();

    public static Finder<Long,Guest> find = new Finder<Long,Guest>(Long.class, Guest.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRsvpCode() {
        return rsvpCode;
    }

    public void setRsvpCode(String rsvpCode) {
        this.rsvpCode = rsvpCode;
    }

    public String getGuestOneName() {
        return guestOneName;
    }

    public void setGuestOneName(String guestOneName) {
        this.guestOneName = guestOneName;
    }

    public String getGuestTwoName() {
        return guestTwoName;
    }

    public void setGuestTwoName(String guestTwoName) {
        this.guestTwoName = guestTwoName;
    }

    public Boolean getHasExtraGuest() {
        return hasExtraGuest;
    }

    public void setHasExtraGuest(Boolean hasExtraGuest) {
        this.hasExtraGuest = hasExtraGuest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getAttending() {
        return attending;
    }

    public void setAttending(Boolean attending) {
        this.attending = attending;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public int compareTo(Guest otherGuest) {
        return otherGuest.modifiedDate.compareTo(this.modifiedDate);
    }

}