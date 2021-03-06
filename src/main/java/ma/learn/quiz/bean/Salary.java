package ma.learn.quiz.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Month;
import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import ma.learn.quiz.bean.Prof;

@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Prof prof;
    private int annee;
    private int mois;
    private String  code;
    private BigDecimal nbrSessionMensuel;
    private boolean payer;
    private BigDecimal totalPayment;
    private BigDecimal totalBonusClassAverage;
    private BigDecimal totalBonusWorkload;

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public BigDecimal getTotalBonusClassAverage() {
        return totalBonusClassAverage;
    }

    public void setTotalBonusClassAverage(BigDecimal totalBonusClassAverage) {
        this.totalBonusClassAverage = totalBonusClassAverage;
    }

    public BigDecimal getTotalBonusWorkload() {
        return totalBonusWorkload;
    }

    public void setTotalBonusWorkload(BigDecimal totalBonusWorkload) {
        this.totalBonusWorkload = totalBonusWorkload;
    }

    public Salary(Prof prof, int annee, int mois, String code, BigDecimal nbrSessionMensuel, boolean payer) {
        this.prof = prof;
        this.annee = annee;
        this.mois = mois;
        this.code = code;
        this.nbrSessionMensuel = nbrSessionMensuel;
        this.payer = payer;
    }

    public Salary() {
    }

    public boolean isPayer() {
        return payer;
    }

    public void setPayer(boolean payer) {
        this.payer = payer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getNbrSessionMensuel() {
        return nbrSessionMensuel;
    }

    public void setNbrSessionMensuel(BigDecimal nbrSessionMensuel) {
        this.nbrSessionMensuel = nbrSessionMensuel;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }
}
