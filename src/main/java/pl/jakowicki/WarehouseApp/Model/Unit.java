package pl.jakowicki.WarehouseApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;
    private String unitName;
    private String sign;

    Unit(){}
//
//    public Unit(String unitName, String sign) {
//        this.unitName = unitName;
//        this.sign = sign;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unit_id=" + id +
                ", unitName='" + unitName + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
