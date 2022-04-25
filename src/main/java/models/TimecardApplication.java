package models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_APP)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_APP_GET_ALL,
            query = JpaConst.Q_APP_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_APP_GET_COUNT,
            query = JpaConst.Q_APP_GET_COUNT_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimecardApplication {

    @Id
    @Column(name = JpaConst.APP_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = JpaConst.APP_COL_EMP, nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = JpaConst.APP_COL_ATT, nullable = false)
    private Attendance attendance;

    @Column(name = JpaConst.APP_COL_APP_DATE, nullable = false)
    private LocalDate timecardApplicationDate;

    @Column(name = JpaConst.APP_COL_APP_TYPE_FLAG, nullable = false)
    private Integer typeFlag;

    @Column(name = JpaConst.APP_COL_TIME, nullable = false)
    private LocalTime time;

    @Lob
    @Column(name = JpaConst.APP_COL_CONTENT, nullable = false)
    private String appContent;

    @Column(name = JpaConst.APP_COL_COMMENT, length = 255, nullable = true)
    private String comment;

    @Column(name = JpaConst.APP_COL_APP_APPROVE, nullable = true)
    private Integer appApprove;

}
