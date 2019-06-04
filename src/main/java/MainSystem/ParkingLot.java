package MainSystem;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "parkinglot", schema = "public", catalog = "postgres")
public class ParkingLot {
    private int id;
    private Boolean isoccupied;

    public ParkingLot() {
        isoccupied = false;
    }



    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name="incrementator", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isoccupied")
    public Boolean getIsoccupied() {
        return isoccupied;
    }

    public void setIsoccupied(Boolean isoccupied) {
        this.isoccupied = isoccupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingLot that = (ParkingLot) o;

        if (id != that.id) return false;
        if (isoccupied != null ? !isoccupied.equals(that.isoccupied) : that.isoccupied != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isoccupied != null ? isoccupied.hashCode() : 0);
        return result;
    }
}
