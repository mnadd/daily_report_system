package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_ATT)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_ATT_GET_ALL,
            query = JpaConst.Q_ATT_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_ATT_GET_COUNT,
            query = JpaConst.Q_ATT_GET_COUNT_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {

    @Id
    @Column(name = JpaConst.ATT_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = JpaConst.ATT_COL_EMP, nullable = false)
    private Employee employee;

    @Column(name = JpaConst.ATT_COL_ATT_DATE, nullable = false)
    private LocalDate attendanceDate;

    @Column(name = JpaConst.ATT_COL_IN, nullable = false)
    private LocalDateTime in;

    @Column(name = JpaConst.ATT_COL_OUT, nullable = false)
    private LocalDateTime out;

    @Column(name = JpaConst.ATT_COL_PERMIT_FLAG)
    private Integer permitFlag;

}
