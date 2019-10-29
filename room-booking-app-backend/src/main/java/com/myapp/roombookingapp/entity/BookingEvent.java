package com.myapp.roombookingapp.entity;

import com.myapp.roombookingapp.util.enums.RoomNumber;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * BookingEvent.
 *
 * @author Ivan_Semenov
 */
@Entity
@Table(name = "booking_event")
public class BookingEvent {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "room_number")
    private RoomNumber roomNumber;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIME)
    private Date startDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    public BookingEvent() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(RoomNumber roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEvent that = (BookingEvent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roomNumber, that.roomNumber) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, roomNumber, startDate, duration, description);
    }
}
