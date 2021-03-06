package models;

import java.time.LocalDate;
import java.time.LocalTime;

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
            name = JpaConst.Q_ATT_GET_ALL_MINE,
            query = JpaConst.Q_ATT_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_ATT_COUNT_ALL_MINE,
            query = JpaConst.Q_ATT_COUNT_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_ATT_GET_BY_EMP_AND_DATE,
            query = JpaConst.Q_ATT_GET_BY_EMP_AND_DATE_DEF)
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

    @Column(name = JpaConst.ATT_COL_START, nullable = false)
    private LocalTime start;

    @Column(name = JpaConst.ATT_COL_FINISH, nullable = true)
    private LocalTime finish;

    @Column(name = JpaConst.ATT_COL_ACT_TIME, nullable = true)
    private LocalTime actualTime;

}
